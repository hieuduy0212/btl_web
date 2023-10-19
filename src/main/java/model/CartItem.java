package model;

public class CartItem {
	private int cartid;
	private int bookid;
	private int quantity;
	private Book book;
	public CartItem() {
	}
	
	public CartItem(int cartid, int bookid, int quantity, Book book) {
		this.cartid = cartid;
		this.bookid = bookid;
		this.quantity = quantity;
		this.book = book;
	}

	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
