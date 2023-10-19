package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.jdbc.ConnectDB;
import model.Account;
import model.Review;

public class ReviewDao {
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public void addReview(Review review) {
		String sql = "INSERT INTO REVIEW(CONTENT, STAR, BOOKID, ACCOUNTID, CREATED) VALUES(?, ?, ?, ?, ?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, review.getContent());
			pstm.setFloat(2, review.getStar());
			pstm.setInt(3, review.getBookid());
			pstm.setInt(4, review.getAccount().getId());
			pstm.setDate(5, review.getCreated());
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Review> getReviewsByBookIdEach3(int bookid, int exists){
		String sql = "SELECT R.*, A.FULLNAME "
				+ "FROM REVIEW R JOIN ACCOUNT A "
				+ "ON R.ACCOUNTID = A.ID "
				+ "AND R.BOOKID = " + bookid 
				+ " ORDER BY R.ID DESC LIMIT 3 OFFSET " + exists;
		List<Review> reviews = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Review r = new Review();
				r.setCreated(rs.getDate("created"));
				r.setContent(rs.getString("content"));
				r.setStar(rs.getFloat("star"));
				r.setBookid(rs.getInt("bookid"));
				
				Account account = new Account();
				account.setFullname(rs.getString("fullname"));
				
				r.setAccount(account);
				
				reviews.add(r);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reviews;
	}
	
	public float getMeanStarByBookid(int bookid) {
		String sql = "SELECT ROUND(AVG(STAR),1) AS MEANSTAR FROM REVIEW WHERE BOOKID = ? GROUP BY BOOKID";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bookid);
			rs = pstm.executeQuery();
			if(rs.next()) {
				return rs.getFloat("meanstar");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0f;
	}
	public int getReviewCountByBookid(int bookid) {
		String sql = "SELECT COUNT(*) AS REVCNT FROM REVIEW WHERE BOOKID = ? GROUP BY BOOKID";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, bookid);
			rs = pstm.executeQuery();
			if(rs.next()) {
				return rs.getInt("revcnt");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return 0;
	}
}
