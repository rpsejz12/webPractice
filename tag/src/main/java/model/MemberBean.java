package model;

public class MemberBean {
	private String id;
	private String passwd;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	public boolean check(MemberBean vo) {
		if(vo.getId().equals("hong") & vo.getPasswd().equals("1234")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	

}
