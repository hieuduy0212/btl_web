package model;

public class Account {
	private int id;
	private String fullname;
	private String username;
	private String password;
	private String email;
	private int isAdmin;
	public Account() {
	}
	public Account(int id, String fullname, String username, String password, String email, int isAdmin) {
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.isAdmin = isAdmin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
}
