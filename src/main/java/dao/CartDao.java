package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.jdbc.ConnectDB;
import model.Cart;
import model.CartItem;

public class CartDao {
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public void createNewCart(int accountid) {
		String sql = "INSERT INTO CART(ID, ACCOUNTID) VALUES(?, ?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, accountid);
			pstm.setInt(2, accountid);
			pstm.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Cart getCartById(int accountid) {
		BookDao bookDao =  new BookDao();
		
		String sql = "SELECT *  "
				+ "FROM CART C JOIN CARTITEM CI JOIN BOOK B "
				+ "ON C.ID = CI.CARTID "
				+ "AND CI.BOOKID = B.ID "
				+ "AND B.IS_AVAILABEL = 1  "
				+ "AND C.ACCOUNTID = " + accountid;
		Cart cart = new Cart();
		cart.setId(accountid);
		List<CartItem> cartItems = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				CartItem ci = new CartItem();
				ci.setCartid(rs.getInt("cartid"));
				ci.setBookid(rs.getInt("bookid"));
				ci.setQuantity(rs.getInt("quantity"));
				ci.setBook(bookDao.getBookById(rs.getInt("bookid")));
				cartItems.add(ci);				
			}
			cart.setCartItems(cartItems);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cart;
	}
	
	public void addCartItem(CartItem ci) {
		String sql = "INSERT INTO CARTITEM(CARTID, BOOKID, QUANTITY) VALUES(?, ?, ?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ci.getCartid());
			pstm.setInt(2, ci.getBookid());
			pstm.setInt(3, ci.getQuantity());
			
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateCartItem(CartItem ci ) {
		String sql = "UPDATE CARTITEM SET QUANTITY = ? WHERE BOOKID = ? AND CARTID = ?";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,  ci.getQuantity());
			pstm.setInt(2, ci.getBookid());
			pstm.setInt(3, ci.getCartid());
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeCartItem(CartItem ci) {
		String sql = "DELETE FROM CARTITEM WHERE BOOKID = ? AND CARTID = ?";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, ci.getBookid());
			pstm.setInt(2, ci.getCartid());
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
