package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Set;

public class Product {
	private Integer productId;
	private Seller seller;
	private Category category;
	private String productName;
	private Integer quantity;
	private Integer sold;
	private Integer price;
	private Integer discount;
	private String description;
	private String warranty;
	private String returningPolicy;
	private String shipmentDetails;
	private String paymentDetails;

	public Product(){
		super();
	}

	public Product(Integer productId){
		this.productId = productId;
	}

	public Product(Seller seller, Category category, String productName, Integer quantity, Integer price,
			Integer discount) {
		super();
		this.seller = seller;
		this.category = category;
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}

	public Product(Integer productId,String productName,Integer quantity,Integer sold,Integer price,Integer discount){
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.sold = sold;
		this.price = price;
		this.discount = discount;
	}

	public Product(Integer productId,String productName,Integer quantity,Integer sold,Integer price,Integer discount,Seller seller){
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.sold = sold;
		this.price = price;
		this.discount = discount;
		this.seller = seller;
	}

	public Product(Integer productId,String productName,Integer price,Integer discount){
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.discount = discount;
	}



	//##################### METHODS ###########################
	public static ArrayList<Product> getProductDetailsForCart(Set<Integer> productIds){
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");
			
			String query = "select product_id,product_name,price,discount from products where product_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			for(Integer productId : productIds){
				ps.setInt(1,productId);
				ResultSet rs = ps.executeQuery();

				if(rs.next()){
					products.add(new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));
				}
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

		return products;
	}



	public static ArrayList<Product> searchProduct(String searchKey){
		ArrayList<Product> products = new ArrayList<Product>();
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "select product_id,product_name,quantity,sold,price,discount,p.seller_id,seller_account_name  "+ 
							"from products as p inner join sellers as s where p.seller_id=s.seller_id and product_name like '%"+searchKey+"%'";

			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				products.add(
							new Product(rs.getInt(1),
										rs.getString(2),
										rs.getInt(3),
										rs.getInt(4),
										rs.getInt(5),
										rs.getInt(6),
										new Seller(rs.getInt(7),rs.getString(8))));
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

		return products;
	}

	public void getProductInfo(){
		Connection con = null;		

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "select product_name,quantity,sold,price,discount,"+
							"description,warranty,returning_policy,shipment_details,"+
							"payment_details,p.seller_id,seller_account_name "+
							"from products as p inner join sellers as s "+
							"where product_id=? and p.seller_id=s.seller_id";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,productId);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				productName = rs.getString(1);
				quantity = rs.getInt(2);
				sold = rs.getInt(3);
				price = rs.getInt(4);
				discount = rs.getInt(5);
				description = rs.getString(6);
				warranty = rs.getString(7);
				returningPolicy = rs.getString(8);
				shipmentDetails = rs.getString(9);
				paymentDetails = rs.getString(10);
				seller = new Seller(rs.getInt(11),rs.getString(12));
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
	
	public static ArrayList<Product> collectAllProducts(int sellerId){
		Connection con = null;

		ArrayList<Product> products = new ArrayList<Product>(); 

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "select product_id,product_name,quantity,sold,price,discount "+ 
							"from products where seller_id=? order by product_id desc";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,sellerId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				products.add(new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6)));
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

		return products;
	}

	public boolean savePaymentDetails(){
		boolean flag = false;
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "update products set payment_details=? where product_id=?";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,paymentDetails);
			ps.setInt(2,productId);

			int res = ps.executeUpdate();

			if(res==1){
				flag = true;
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
		
		return flag;
	}

	public boolean saveShipmentDetails(){
		boolean flag = false;
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "update products set shipment_details=? where product_id=?";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,shipmentDetails);
			ps.setInt(2,productId);

			int res = ps.executeUpdate();

			if(res==1){
				flag = true;
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

		return flag;
	}


	public boolean saveReturningPolicy(){
		boolean flag = false;
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "update products set returning_policy=? where product_id=?";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,returningPolicy);
			ps.setInt(2,productId);

			int res = ps.executeUpdate();

			if(res==1){
				flag = true;
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

		return flag;
	}

	public boolean saveWarranty(){
		boolean flag = false;
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "update products set warranty=? where product_id=?";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,warranty);
			ps.setInt(2,productId);

			int res = ps.executeUpdate();

			if(res==1){
				flag = true;
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

		return flag;
	}


	public boolean saveDescription(){
		boolean flag = false;
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "update products set description=? where product_id=?";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,description);
			ps.setInt(2,productId);

			int res = ps.executeUpdate();

			if(res==1){
				flag = true;
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

		return flag;
	}



	public boolean saveProduct(){
		boolean flag = false;
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "insert into products (seller_id,category_id,product_name,quantity,price,discount) value (?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			ps.setInt(1,seller.getSellerId());
			ps.setInt(2,category.getCategoryId());
			ps.setString(3,productName);
			ps.setInt(4,quantity);
			ps.setInt(5,price);
			ps.setInt(6,discount);

			int res = ps.executeUpdate();
			if(res==1){
				flag = true;
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()){
					productId = rs.getInt(1);
				}
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

		return flag;
	}

	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getSold() {
		return sold;
	}
	public void setSold(Integer sold) {
		this.sold = sold;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWarranty() {
		return warranty;
	}
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	public String getReturningPolicy() {
		return returningPolicy;
	}
	public void setReturningPolicy(String returningPolicy) {
		this.returningPolicy = returningPolicy;
	}
	public String getShipmentDetails() {
		return shipmentDetails;
	}
	public void setShipmentDetails(String shipmentDetails) {
		this.shipmentDetails = shipmentDetails;
	}
	public String getPaymentDetails() {
		return paymentDetails;
	}
	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}	
}
