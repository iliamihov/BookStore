package bg.pragmatic.bookstore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bg.pragmatic.bookstore.container.Book;
import bg.pragmatic.bookstore.container.User;

public class Hsqldb {

		//Use for local file 
		private static final String dbName = "dbBookStore";
		
		//Use for HSQLDB server
		//private static final String dbName = "hsql://localhost/xdb";
	
		//Connection to the db - persist for life of the session
	    private Connection sqlConnection;	    

	    public Hsqldb() throws Exception {

	        // Load the HSQL Database Engine JDBC driver
	        Class.forName("org.hsqldb.jdbc.JDBCDriver");

	        // This will load the db files and start the database if it is not already running.
	        sqlConnection = DriverManager.getConnection("jdbc:hsqldb:" + dbName,    	// filenames
	                                           "SA",                     	// username
	                                           "");                     	// password
	    }

	    //use for SQL command SELECT
	    private ResultSet sqlQuery(String expression) throws SQLException {

	        Statement st = null;
	        ResultSet rs = null;

	        st = sqlConnection.createStatement();        
	        // statement objects can be reused with
	        // repeated calls to execute but we
	        // choose to make a new one each time
	        rs = st.executeQuery(expression);    // run the query

	        // do something with the result set.
	        st.close();      
	        return rs;
	    }
	    
	    //use for SQL commands CREATE, DROP, INSERT and UPDATE
	    private synchronized void sqlUpdate(String expression) throws SQLException {

	        Statement st = null;

	        st = sqlConnection.createStatement();    // statements

	        int i = st.executeUpdate(expression);    // run the query

	        if (i == -1) {
	            System.out.println("db error : " + expression);
	        }

	        st.close();
	    }   
	    
	    private void sqlShutdown() throws SQLException {

	    	//not used ATM/ Statement st = conn.createStatement();

	        // db writes out to files and performs clean shuts down
	        // otherwise there will be an unclean shutdown
	        //not used ATM/ st.execute("SHUTDOWN");
	        sqlConnection.close();
	    }
	
	    
	    public void createTables() {

	        try {

	            sqlUpdate(
		           	"CREATE TABLE bookstore (" + 
		           			"pk_int_id INT IDENTITY PRIMARY KEY," +
		           			"str_title VARCHAR(256)," + 
		           			"str_author VARCHAR(256)," +
		           			"str_press VARCHAR(256)," + 
		           			"int_price INT," + 
		           			"bool_isforegn BOOLEAN," + 
		           			"int_amount INT)"
	            		);
	            
	            sqlUpdate(
			           	"CREATE TABLE users (" + 
			           			"pk_str_name VARCHAR(256) PRIMARY KEY," +
			           			"str_password VARCHAR(256)," +
			           			"int_access_level INT)"
		            		);
	        } catch (SQLException ex) {

	            //Ignore. Should throw exception since table is already there.
	            //This will have no effect on the db.
	        }	    	
	    }
	    
	    private ResultSet selectBooks(Book b) throws SQLException{
	    	   
	    	StringBuffer strRequest = new StringBuffer();

	    	boolean isFieldEmpty = true;
	    	
	    	if(!b.isEmptyTitle()){
	    		strRequest.append(" str_title = " + b.getTitle());
	    		isFieldEmpty = false;
	    	}
	    	if(!b.isEmptyAuthor()){
	    		strRequest.append((!isFieldEmpty ? " AND": "") + " str_author = " + b.getAuthor());
	    		isFieldEmpty = false;
	    	}
	    	if(!b.isEmptyPress()){
	    		strRequest.append((!isFieldEmpty ? " AND": "") + " str_press =" + b.getPress());
	    		isFieldEmpty = false;
	    	}
	    	
	    	strRequest.append("SELECT * FROM bookstore " + (!isFieldEmpty ? "WHERE " + strRequest : ""));

	    	ResultSet rs = sqlQuery(strRequest.toString());
	    	
	    	return rs;	    	
	    }
	    
	    public void updateBook(Book b) throws SQLException{
	    	
	    	String strRequest = String.format(
	    			"UPDATE bookstore SET (str_title, str_author, str_press, int_price, bool_isforegn, int_amount) = " + 
	    			"('%s', '%s', '%s', %d, %b, %d) " + 
	    			"WHERE pk_int_id = %d",
	    			b.getTitle(),
	    			b.getAuthor(),
	    			b.getPress(),
	    			b.getPrice(),
	    			b.isForeign(),
	    			b.getAmount(),
	    			b.getId());

	    	sqlUpdate(strRequest);
	    }
	    
	    public void insertBook(Book b) throws SQLException{
	    	
	    	String strRequest = String.format(
	    			"INSERT INTO bookstore(str_title, str_author, str_press, int_price, bool_isforegn, int_amount) " + 
	    			"VALUES ('%s', '%s', '%s', %d, %b, %d)",
	    			b.getTitle(),
	    			b.getAuthor(),
	    			b.getPress(),
	    			b.getPrice(),
	    			b.isForeign(),
	    			b.getAmount());

	    	sqlUpdate(strRequest);
	    }
	    
	    
	    public void deleteBook(Book b) throws SQLException{
	    	
	    	String strRequest = String.format("DELETE FROM bookstore WHERE pk_int_id = %d", b.getId());
	    	sqlUpdate(strRequest);
	    }
	    
	    private ResultSet selectUser(User u) throws SQLException{
	    	
	    	StringBuffer strRequest = new StringBuffer();
	    	if(!u.isEmptyName()){
	    		
	    		strRequest.append("pk_str_name = " + u.getName());
	    	}
	    	
	    	strRequest.append("SELECT * FROM users " + (!u.isEmptyName() ? "WHERE " + strRequest : ""));
	    	ResultSet rs = sqlQuery(strRequest.toString());	    	
	    	return rs;	    	
	    }
	    
	    public void updateUser(User u) throws SQLException{
	    	
	    	String strRequest = String.format(
	    			"UPDATE users SET (pk_str_name, str_password, int_access_level) = " + 
	    			"('%s', '%s', %d) " + 
	    			"WHERE pk_str_name = %s",
	    			u.getName(),
	    			u.getPassword(),
	    			u.getAccessLevel(),
	    			u.getName()
	    			);

	    	sqlUpdate(strRequest);
	    }
	    
	    public void insertUser(User u) throws SQLException{
	    	
	    	String strRequest = String.format(
	    			"INSERT INTO users (pk_str_name, str_password, int_access_level) " + 
	    			"VALUES ('%s', '%s', %d)",
	    			u.getName(),
	    			u.getPassword(),
	    			u.getAccessLevel()
	    			);

	    	sqlUpdate(strRequest);
	    }
	    
	    
	    public void deleteUser(User u) throws SQLException{
	    	
	    	String strRequest = String.format("DELETE FROM users WHERE pk_str_name = %s", u.getName());
	    	sqlUpdate(strRequest);
	    }
	    
	    
	    
	public List<Book> getBookList(Book b) {

		List<Book> result = new ArrayList<Book>();

		try {

			ResultSet rs = selectBooks(b);

			if (rs != null) {

				// go through the rows
				for (; rs.next();) {

					Book res = new Book();

					// pk_int_id INT
					res.setId(Integer.parseInt(rs.getObject(1).toString()));

					// str_title VARCHAR(256)
					res.setTitle(rs.getObject(2).toString());

					// str_author VARCHAR(256)
					res.setAuthor(rs.getObject(3).toString());

					// str_press VARCHAR(256)
					res.setPress(rs.getObject(4).toString());

					// int_price INT
					res.setPrice(Integer.parseInt(rs.getObject(5).toString()));

					// bool_isforegn BOOLEAN
					res.setForeign(Boolean.parseBoolean(rs.getObject(6).toString()));

					// int_amount INT
					res.setAmount(Integer.parseInt(rs.getObject(7).toString()));

					result.add(res);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	    
	public List<User> getUserList(User u) {

		List<User> result = new ArrayList<User>();

		try {

			ResultSet rs = selectUser(u);

			if (rs != null) {

				// go through the rows
				for (; rs.next();) {

					User res = new User();

					//pk_str_name VARCHAR(256)
					res.setName(rs.getObject(1).toString());

					//str_password VARCHAR(256)
					res.setPassword(rs.getObject(2).toString());

					// int_access_level INT
					res.setAccessLevel(Integer.parseInt(rs.getObject(3).toString()));
					
					result.add(res);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	
}
