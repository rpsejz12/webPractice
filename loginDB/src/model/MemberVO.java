package model;

public class MemberVO {
	private String name;
	private String userID;
	private String userPW;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", userID=" + userID + ", userPW=" + userPW + "]";
	}
	
}
