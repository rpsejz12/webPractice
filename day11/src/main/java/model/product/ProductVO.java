package model.product;

public class ProductVO {
	/*ProductVO			ppk, pname, ptype, pprice*/
	private int ppk;
	private String pname;
	private String ptype;
	private int pprice;
	private int pnum;
	
	@Override
	public String toString() {
		return "ProductVO [ppk=" + ppk + ", pname=" + pname + ", ptype=" + ptype + ", pprice=" + pprice + ",pnum=" + pnum +"]";
	}
	public int getPpk() {
		return ppk;
	}
	public void setPpk(int ppk) {
		this.ppk = ppk;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	
	
}
