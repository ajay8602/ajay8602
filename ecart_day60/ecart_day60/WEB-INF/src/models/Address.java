package models;

import java.sql.*;
import java.util.ArrayList;

public class Address{
	private Integer addressId;
	private User user;
	private String name;
	private String address;
	private City city;
	private String pin;
	private Boolean defaultAddress;

	public static final boolean DEFAULT_ADDRESS = true;
	public static final boolean NON_DEFAULT_ADDRESS = false;

	public Address(Integer addressId,String name, String address,String pin,City city){
		this.addressId = addressId;
		this.name = name;
		this.address = address;
		this.pin = pin;
		this.city = city;
	}

	public Address(User user,String address,City city,String pin){
		this.user = user;
		this.address = address;
		this.city = city;
		this.pin = pin;
	}

	public Address(User user,String address,City city,String pin,String name){
		this.user = user;
		this.address = address;
		this.city = city;
		this.pin = pin;
		this.name = name;
	}

	public Address(User user){
		this.user = user;
	}




	//################ Methods #####################
	public static ArrayList<Address> getAllAddresses(Integer userId){
		ArrayList<Address> addresses = new ArrayList<Address>();

		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "select address_id,name,address,pin,c.city_id,city "+
							"from addresses as a inner join cities as c "+
							"where a.city_id=c.city_id and user_id=?";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,userId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				addresses.add(new Address(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),new City(rs.getInt(5),rs.getString(6))));
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

		return addresses;
	}

	public void fetchAddress(){
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "select address_id,name,address,pin,c.city_id,city "+
							"from addresses as a inner join cities as c "+
						   "where a.city_id=c.city_id and user_id=? and default_address=1";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,user.getUserId());

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				addressId = rs.getInt(1);
				name = rs.getString(2);
				address = rs.getString(3);
				pin = rs.getString(4);
				city = new City(rs.getInt(5),rs.getString(6));
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

	public boolean saveAddress(){
		Connection con = null;
		Boolean flag = false;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "insert into addresses (user_id,address,city_id,pin,default_address,name) value (?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,user.getUserId());
			ps.setString(2,address);
			ps.setInt(3,city.getCityId());
			ps.setString(4,pin);
			
			if(name==null){
				ps.setBoolean(5,DEFAULT_ADDRESS);
				ps.setString(6,user.getFullName());
			}else{
				ps.setBoolean(5,NON_DEFAULT_ADDRESS);
				ps.setString(6,name);
			}

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


	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDefaultAddress(Boolean defaultAddress){
		this.defaultAddress = defaultAddress;
	}

	public Boolean isDefaultAddress(){
		return defaultAddress;
	}

	public void setPin(String pin){
		this.pin = pin;
	}

	public String getPin(){
		return pin;
	}

	public void setCity(City city){
		this.city = city;
	}

	public City getCity(){
		return city;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setAddressId(Integer addressId){
		this.addressId = addressId;
	}

	public Integer getAddressId(){
		return addressId;
	}
}