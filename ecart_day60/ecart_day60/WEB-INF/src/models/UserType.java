package models;

public class UserType {
	private Integer userTypeId;
	private String userType;

	public UserType() {
		super();
	}

	public UserType(Integer userTypeId) {
		super();
		this.userTypeId = userTypeId;
	}

	public UserType(Integer userTypeId, String userType) {
		super();
		this.userTypeId = userTypeId;
		this.userType = userType;
	}


	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}	
}