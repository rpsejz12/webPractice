package controller.common;

public class PageSet {
	
	private int cnt; //�� �� ����
	private int pCnt; //�� ������ ����
	private int start; //�� ������ ����
	private int end; //�� ������ ����
	
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
