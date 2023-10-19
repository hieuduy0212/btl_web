package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dao.jdbc.ConnectDB;
import model.Account;

public class AccountDao {
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	public boolean checkLogin(Account account) {
		String sql = "SELECT * FROM ACCOUNT WHERE PASSWORD = ? AND (USERNAME=? OR EMAIL = ?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, account.getPassword());
			pstm.setString(2, account.getUsername());
			pstm.setString(3, account.getEmail());
			rs = pstm.executeQuery();
			if(rs.next()) {
				account.setId(rs.getInt("id"));
				account.setFullname(rs.getString("fullname"));
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setEmail(rs.getString("email"));
				account.setIsAdmin(rs.getInt("is_admin"));
				return true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}
	
	public boolean getAccountByUsernameOrEmail(Account account) {
		String sql = "SELECT * FROM ACCOUNT WHERE USERNAME=? OR EMAIL = ?";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, account.getUsername());
			pstm.setString(2, account.getEmail());
			rs = pstm.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return false;
	}
	
	public void addNewAccount(Account account) { 
		String sql = "INSERT INTO ACCOUNT(FULLNAME, USERNAME, PASSWORD, EMAIL, IS_ADMIN) VALUES(?, ?, ?, ?, 0);";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, account.getFullname());
			pstm.setString(2, account.getUsername());
			pstm.setString(3, account.getPassword());
			pstm.setString(4, account.getEmail());
			pstm.executeUpdate();	
			
			sql = "SELECT LAST_INSERT_ID() AS IDNEW";
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				account.setId(rs.getInt("idnew")); 
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
