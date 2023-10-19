package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.jdbc.ConnectDB;
import model.Category;

public class CategoryDao {
	Statement stm = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection conn = null;
	
	public List<Category> getAllCategory(){
		String sql = "SELECT * FROM CATEGORY";
		List<Category> categories = new ArrayList<>();
		try {
			conn = ConnectDB.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Category category =  new Category();
				category.setId(rs.getInt("id"));
				category.setName(rs.getString("name"));
				categories.add(category);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return categories;
	}
}
