package models;

import java.util.ArrayList;
import java.sql.*;

public class Category{
	private Integer categoryId;
	private String category;

	public Category(){
		
	}

	public Category(Integer categoryId){
		this.categoryId = categoryId;
	}

	public Category(Integer categoryId,String category){
		this.categoryId = categoryId;
		this.category = category;
	}

	public static ArrayList<Category> collectCategories(){
		ArrayList<Category> categories = new ArrayList<Category>();
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "select * from categories";

			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				categories.add(new Category(rs.getInt(1),rs.getString(2)));
			}

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return categories;
	}

	

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}

	public Integer getCategoryId(){
		return categoryId;
	}
}