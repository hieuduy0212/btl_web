package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.jdbc.ConnectDB;
import model.Book;
import model.BookStat;
import model.Category;

public class BookDao {
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public List<BookStat> getBookStat(){
		String sql =  "SELECT T1.*, C.NAME "
				+ "FROM ( "
					+ "SELECT B.ID, B.TITLE, B.AUTHOR,  B.RELEASEDATE, B.PAGES, SUM(BD.QTY) AS SALESAMOUNT, "
					+ "B.CATEGORYID , B.IS_AVAILABEL "
					+ "FROM BOOK B LEFT JOIN BILLDETAIL BD "
					+ "ON BD.BOOKID = B.ID GROUP BY B.ID ) AS T1  JOIN CATEGORY C "
					+ "ON T1.CATEGORYID = C.ID "
				+ "AND T1.IS_AVAILABEL = 1  "
				+ "ORDER BY T1.ID ASC";
		List<BookStat> bookStats = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				BookStat bs = new BookStat();
				bs.setId(rs.getInt("id"));
				bs.setTitle(rs.getString("title"));
				bs.setAuthor(rs.getString("author"));
				bs.setReleasedate(rs.getDate("releasedate"));
				bs.setPages(rs.getInt("pages"));
				bs.setSalesAmount(rs.getInt("salesamount"));
				
				Category c = new Category();
				c.setName(rs.getString("name"));
				
				bs.setCategory(c);
				
				bookStats.add(bs);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return bookStats;
	}
	public Book getBookById(int id) {
		String sql = "SELECT * "
				+ "FROM BOOK B JOIN CATEGORY C "
				+ "ON B.IS_AVAILABEL = 1 "
				+ "AND B.CATEGORYID = C.ID "
				+ "AND B.ID = " + id;
		Book book = new Book();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setDescription(rs.getString("description"));
				book.setReleasedate(rs.getDate("releasedate"));
				book.setPages(rs.getInt("pages"));
				book.setCover(rs.getString("cover"));
				
				Category category = new Category(rs.getInt("categoryid"), rs.getString("name"));
				
				book.setCategory(category);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(book.getId() != 0) return book;
		else return null;
	}
	public boolean addNewBook(Book book) {
		String sql = "INSERT INTO BOOK(TITLE, AUTHOR, DESCRIPTION,  RELEASEDATE, "
				+ "PAGES, COVER, CATEGORYID, IS_AVAILABEL) "
				+ "VALUES(?, ?, ?, ?, ?,?, ?, 1)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, book.getTitle());
			pstm.setString(2, book.getAuthor());
			pstm.setString(3, book.getDescription());
			pstm.setDate(4, book.getReleasedate());
			pstm.setInt(5, book.getPages());
			pstm.setString(6, book.getCover());
			pstm.setInt(7, book.getCategory().getId());
			int effectrow = pstm.executeUpdate();
			if(effectrow == 1) return true;
			else return false;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public boolean getBookByTitleAndAuthor(Book book) {
		String sql = "SELECT * "
				+ "FROM BOOK WHERE TITLE = ? "
				+ "AND AUTHOR = ? "
				+ "AND ID != " + book.getId() 
				+ " AND IS_AVAILABEL = 1";
		
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, book.getTitle());
			pstm.setString(2, book.getAuthor());
			rs = pstm.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public void deleteBookById(int bid) {
		String sql = "UPDATE BOOK SET IS_AVAILABEL = 0 WHERE ID = " + bid;
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			stm.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void editBook(Book book) {
		String sql = "UPDATE BOOK SET TITLE=?, AUTHOR=?, DESCRIPTION=?, RELEASEDATE=?, PAGES=?, COVER=?, CATEGORYID=? "
				+ "WHERE ID = ? AND IS_AVAILABEL = 1";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, book.getTitle());
			pstm.setString(2, book.getAuthor());
			pstm.setString(3, book.getDescription());
			pstm.setDate(4, book.getReleasedate());
			pstm.setInt(5, book.getPages());
			pstm.setString(6, book.getCover());
			pstm.setInt(7, book.getCategory().getId());
			pstm.setInt(8, book.getId());
			pstm.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Book> getAllBook(){
		String sql =  "SELECT * FROM BOOK WHERE IS_AVAILABEL = 1";
		List<Book> books = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setAuthor(rs.getString("author"));
				b.setDescription(rs.getString("description"));
				b.setReleasedate(rs.getDate("releasedate"));
				b.setPages(rs.getInt("pages"));
				b.setCover(rs.getString("cover"));				
				books.add(b);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return books;
		
	}
	
	
}
