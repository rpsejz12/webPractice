package model.member;

public class MemberVO {
	private int bnum;
	private String bname;
	private String bid;
	private String bpw;
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
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
		return "MemberVO [bnum=" + bnum + ", bname=" + bname + ", bid=" + bid + ", bpw=" + bpw + "]";
	}

	
	
	
	
}