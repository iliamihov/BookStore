package bg.pragmatic.bookstore.container;

public class User {
	
	private String name;
	private String password;
	private int accessLevel;
	
//	public enum Role {
//		   ADMINISTRATOR, SELLER
//		 }
	 
	public User(){
				
	}
	
	public User(String name, String password, int accessLevel){
		
		setName(name);
		setPassword(password);
		setAccessLevel(accessLevel);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEmptyName() {
		if(null != getName() && !getName().isEmpty()) return false;
		else return true;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

}
