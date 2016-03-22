package bg.pragmatic.bookstore.container;

public class Book {

	private int id;
	private String title;
	private String author;
	private String press;
	private int price;
	private boolean isForeign;
	private int amount;
	
	public Book(){
		
	}
	
	public Book (String title, String author, String press, int price, boolean isForeign, int amount){
		setTitle(title);
		setAuthor(author);
		setPress(press);
		setPrice(price);
		setForeign(isForeign);
		setAmount(amount);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isEmptyTitle() {
		if(null != getTitle() && !getTitle().isEmpty()) return false;
		else return true;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isEmptyAuthor() {
		if(null != getAuthor() && !getAuthor().isEmpty()) return false;
		else return true;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public boolean isEmptyPress() {
		if(null != getPress() && !getPress().isEmpty()) return false;
		else return true;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isForeign() {
		return isForeign;
	}
	public void setForeign(boolean isForeign) {
		this.isForeign = isForeign;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
