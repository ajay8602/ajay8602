package models;

import java.sql.*;
import java.util.ArrayList;

public class Seller{
	private Integer sellerId;
	private User user;
	private String sellerAccountName;
	private Timestamp startDate;

	public Seller(){
	
	}

	public Seller(Integer sellerId,String sellerAccountName){
		this.sellerId = sellerId;
		this.sellerAccountName = sellerAccountName;
	}

	public Seller(Integer sellerId,String sellerAccountName,Timestamp startDate){
		this.sellerId = sellerId;
		this.sellerAccountName = sellerAccountName;
		this.startDate = startDate;
	}

	public Seller(User user,String sellerAccountName){
		this.user = user;
		this.sellerAccountName = sellerAccountName;
	}


	//####################### Methods ###################

	public void createSellerAccount(){
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "insert into sellers (user_id,seller_account_name,start_date) value (?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1,user.getUserId());
			ps.setString(2,sellerAccountName);
			ps.setTimestamp(3,new Timestamp(new java.util.Date().getTime()));

			ps.executeUpdate();
			
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}	
		}
	}

	
	public static ArrayList collectSellerAccounts(Integer userId){
		ArrayList<Seller> sellers = new ArrayList<Seller>();
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "select * from sellers where user_id=?";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,userId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				sellers.add(new Seller(rs.getInt(1),rs.getString(3),rs.getTimestamp(4)));
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

		return sellers;
	}

	public void setStartDate(Timestamp startDate){
		this.startDate = startDate;
	}

	public Timestamp getStartDate(){
		return startDate;
	}

	public void setSellerAccountName(String sellerAccountName){
		this.sellerAccountName = sellerAccountName;
	}

	public String getSellerAccountName(){
		return sellerAccountName;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setSellerId(Integer sellerId){
		this.sellerId = sellerId;
	}

	public Integer getSellerId(){
		return sellerId;
	}
}