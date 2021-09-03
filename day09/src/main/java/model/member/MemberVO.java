package model.member;

public class MemberVO {
	String bname;
	String bid;
	String bpw;
	
	
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBpw() {
		return bpw;
	}
	public void setBpw(String bpw) {
		this.bpw = bpw;
	}
	
	
	@Override
	public String toString() {
		return "MemberVO [bname=" + bname + ", bid=" + bid + ", bpw=" + bpw + "]";
	}
	
	

}
