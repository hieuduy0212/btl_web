package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.jdbc.ConnectDB;
import model.Bill;
import model.BillDetail;
import model.Book;

public class BillDao {
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public void createBill(Bill bill) {
		String sql = "INSERT INTO BILL(SALEDAY, RECEIVER, TEL_RECEIVER, "
				+ "ADDRESS_RECEIVER, EMAIL_RECEIVER, ZIPCODE, ACCOUNTID ) "
				+ "VALUES(?,?,?,?,?,?,?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setDate(1, bill.getSaleday());
			pstm.setString(2, bill.getReceiver());
			pstm.setString(3, bill.getTelReceiver());
			pstm.setString(4, bill.getAddressReceiver());
			pstm.setString(5, bill.getEmailReceiver());
			pstm.setString(6, bill.getZipcode());
			pstm.setInt(7, bill.getAccount().getId());
			pstm.executeUpdate();
			
			sql = "SELECT LAST_INSERT_ID() AS IDNEW";
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				bill.setId(rs.getInt("idnew")); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeBill(int id) {
		String sql = "DELETE FROM BILL WHERE ID = " + id;
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			stm.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addBillDetail(BillDetail billDetail) {
		String sql = "INSERT INTO BILLDETAIL(QTY, BOOKID, BILLID) VALUES(?,?,?)";
		try {
			conn = ConnectDB.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, billDetail.getQuantity());
			pstm.setInt(2, billDetail.getBook().getId());
			pstm.setInt(3, billDetail.getBillid());
			pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Bill> getBillsByAccountId(int accountid){
		String sql = "SELECT B.ID, B.SALEDAY, B.RECEIVER, B.TEL_RECEIVER, B.ADDRESS_RECEIVER, B.EMAIL_RECEIVER, B.ZIPCODE, "
				+ "BD.QTY, BD.BOOKID, "
				+ "BK.TITLE "
				+ "FROM BILL B JOIN BILLDETAIL BD JOIN BOOK BK "
				+ "ON B.ID = BD.BILLID "
				+ "AND BD.BOOKID = BK.ID "
				+ "AND B.ACCOUNTID = " + accountid;
		List<Bill> bills = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Bill bill = new Bill();
				bill.setId(rs.getInt("id"));
				bill.setSaleday(rs.getDate("saleday"));
				bill.setReceiver(rs.getString("receiver"));
				bill.setTelReceiver(rs.getString("tel_receiver"));
				bill.setAddressReceiver(rs.getString("address_receiver"));
				bill.setEmailReceiver(rs.getString("email_receiver"));
				bill.setZipcode(rs.getString("zipcode"));
				
				BillDetail bd = new BillDetail();
				bd.setQuantity(rs.getInt("qty"));
				
				Book book = new Book();
				book.setId(rs.getInt("bookid"));
				book.setTitle(rs.getString("title"));
				
				bd.setBook(book);
				bill.getBillDetails().add(bd);
				bills.add(bill);
			}
			
			HashMap<Integer, Bill> map = new HashMap<>();
			for(Bill b : bills) {
				if(map.containsKey(b.getId())) {
					map.get(b.getId()).getBillDetails().add(b.getBillDetails().get(0));
				}else {
					map.put(b.getId(), b);
				}
			}
			bills.clear();
			for(Map.Entry<Integer, Bill> entry : map.entrySet()) {
				bills.add(entry.getValue());
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bills;
	}
	
	public void cancelBill(int billid) {
		String sql = "DELETE FROM BILL WHERE ID = " + billid;
		try {
			conn = ConnectDB.getConnection();
			stm = conn .createStatement();
			stm.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		
}
