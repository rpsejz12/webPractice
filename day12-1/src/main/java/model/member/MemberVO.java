package model.member;

public class MemberVO {
	private int mpk;
	private String mname;
	private String mid;
	private String mpw;
	
	public int getMpk() {
		return mpk;
	}
	public void setMpk(int mpk) {
		this.mpk = mpk;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	@Override
	public String toString() {
		return "MemberVO [mpk=" + mpk + ", mname=" + mname + ", mid=" + mid + ", mpw=" + mpw + "]";
	}
	
	

}
