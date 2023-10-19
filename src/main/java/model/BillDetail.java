package model;

public class BillDetail {
	private int quantity;
	private Book book;
	private int billid;
	public BillDetail() {
	}
	public BillDetail(int quantity, Book book, int billid) {
		this.quantity = quantity;
		this.book = book;
		this.billid = billid;
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
	public int getBillid() {
		return billid;
	}
	public void setBillid(int billid) {
		this.billid = billid;
	}	
}
