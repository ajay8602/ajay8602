package models;

import java.sql.*;

import java.util.ArrayList;

public class ProductPoint{
	private Integer productPointId;
	private Product product;
	private String pointHeader;
	private String pointValue;

	public ProductPoint(){
	
	}

	public ProductPoint(String pointHeader,String pointValue){
		this.pointHeader = pointHeader;
		this.pointValue = pointValue;
	}

	public static ArrayList<ProductPoint> collectAllProductPoints(Integer productId){
		ArrayList<ProductPoint> productPoints = new ArrayList<ProductPoint>();
		
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "select point_header,point_value from product_points where product_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,productId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				productPoints.add(new ProductPoint(rs.getString(1),rs.getString(2)));
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

		return productPoints;
	}

	public static void saveProductPoints(Integer productId,String[] pointTitels,String[] pointValues){
		Connection con = null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "insert into product_points (product_id,point_header,point_value) value (?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1,productId);

			for(int i=0;i<pointTitels.length;i++){
				
				ps.setString(2,pointTitels[i]);
				ps.setString(3,pointValues[i]);

				ps.executeUpdate();
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


	}

	public void setPointValue(String pointValue){
		this.pointValue = pointValue;
	}

	public String getPointValue(){
		return pointValue;
	}
	
	public void setPointHeader(String pointHeader){
		this.pointHeader = pointHeader;
	}

	public String getPointHeader(){
		return pointHeader;
	}

	public void setProduct(Product product){
		this.product = product;
	}

	public Product getProduct(){
		return product;
	}

	public void setPproductPointId(Integer productPointId){
		this.productPointId = productPointId;
	}

	public Integer getProductPointId(){
		return productPointId;
	}
}