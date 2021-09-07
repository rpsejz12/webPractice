package model.buy;

public class BuyVO {
	private String bname;
	private int bnum;
	private int bprice;
	
	
	
	@Override
	public String toString() {
		return "BuyVO [bname=" + bname + ", bnum=" + bnum + ", bprice=" + bprice + "]";
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public int getBprice() {
		return bprice;
	}
	public void setBprice(int bprice) {
		this.bprice = bprice;
	}
}
