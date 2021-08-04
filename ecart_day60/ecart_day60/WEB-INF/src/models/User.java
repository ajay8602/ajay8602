package models;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class User{
	//############# FIELDS ########################
	private Integer userId;
	private String userName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String email;
	private String password;
	private Date dob;
	private Status status;
	private UserType userType;
	private String activationCode;
	private String mobile;
	private String profpic;
	
	//############# CONSTRUCTORS ########################
	public User(){
		
	}

	public User(String userName, String email, String password,String activationCode) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.activationCode = activationCode;
	}

	//############# OTHER METHODS ########################
	public String getFullName(){
		return firstName+" "+middleName+" "+lastName;
	}


	public void saveProfpic(){
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "update users set profpic=? where user_id=?";

			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1,profpic);
			pst.setInt(2,userId);

			pst.executeUpdate();			

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


	public boolean saveProfile(){
		boolean flag = false;
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "update users set first_name=?,middle_name=?,last_name=?,dob=?,mobile=? where user_id=?";

			PreparedStatement pst = con.prepareStatement(query);

			pst.setString(1,firstName);
			pst.setString(2,middleName);
			pst.setString(3,lastName);
			pst.setDate(4,dob);
			pst.setString(5,mobile);

			pst.setInt(6,userId);
			
			int res = pst.executeUpdate();
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


	public String loginUser(String unameEmail,String password){		
		String message = null;
		
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");
			
			String query = "select password,status_id,user_id,username,email,first_name,middle_name,last_name,dob,user_type_id,mobile,profpic from users where (username=? or email=?)";

			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,unameEmail);
			pst.setString(2,unameEmail);

			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				String encPassword = rs.getString(1);
				StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
				if(spe.checkPassword(password,encPassword)){
					if(rs.getInt(2)==1){
						status = new Status(rs.getInt(2));
						userId = rs.getInt(3);
						userName = rs.getString(4);
						email = rs.getString(5);
						firstName = rs.getString(6);
						middleName = rs.getString(7);
						lastName = rs.getString(8);
						dob = rs.getDate(9);
						userType = new UserType(rs.getInt(10));
						//user.setStatus(new Status(rs.getInt(10)));
						mobile = rs.getString(11);
						profpic = rs.getString(12);

						message = "success";
					}else{
						message = "Either your account is inactive or blocked...";
					}
				}else{
					message = "Invalid Password...";
				}
			}else{
				message = "Invalid Username/Email...";
			}

		}catch(SQLException|ClassNotFoundException e){
			message = "Some internal problem...";
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}

		return message;
	}

	public static boolean uniqueKey(String key){
		Connection con = null;
		boolean flag = false;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "select user_id from users where username=? or email=?";

			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1,key);
			pst.setString(2,key);

			ResultSet rs = pst.executeQuery();
			if(rs.next()){
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


	public static boolean activateAccount(String userName,String activationCode){
		Connection con = null;
		boolean flag = false;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");
		
			String query = "update users set status_id=1,activation_code='' where username=? and activation_code=?";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1,userName);
			pst.setString(2,activationCode);

			int res = pst.executeUpdate();
			
			if(res==1)
				flag = true;

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{con.close();}catch(SQLException e){ e.printStackTrace(); }
		}

		return flag;
	}
	

	public boolean saveUser(){
		boolean flag = false;
		Connection con = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecomdb?user=root&password=1234");

			String query = "insert into users (username,email,password,activation_code) value (?,?,?,?)";

			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1,userName);
			pst.setString(2,email);
			StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
			pst.setString(3,spe.encryptPassword(password));
			pst.setString(4,activationCode);

			pst.executeUpdate();

			flag = true;
			
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try{ con.close(); }catch(SQLException e){ e.printStackTrace(); }
		}

		return flag;
	}



	//############# GETTER-SETTERS ########################
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void setUserType(UserType userType){
		this.userType = userType; 
	}

	public UserType getUserType(){
		return userType;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public void setActivationCode(String activationCode){
		this.activationCode = activationCode;
	}

	public String getActivationCode(){
		return activationCode;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setProfpic(String profpic){
		this.profpic = profpic;
	}

	public String getProfpic(){
		return profpic;
	}
}