package controller.common;

public class PageSet {
	
	private int cnt; //총 글 개수
	private int pCnt; //총 페이지 개수
	private int start; //총 페이지 개수
	private int end; //총 페이지 개수
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getpCnt() {
		return pCnt;
	}
	public void setpCnt(int pCnt) {
		this.pCnt = pCnt;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "PageSet [cnt=" + cnt + ", pCnt=" + pCnt + ", start=" + start + ", end=" + end + "]";
	}
	
	
	

}
