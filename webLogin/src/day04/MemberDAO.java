package day04;

import java.util.ArrayList;
import java.util.Iterator;

public class MemberDAO {
	ArrayList<MemberVO> datas=new ArrayList<MemberVO>();

	public ArrayList<MemberVO> getDatas() {
		return datas;
	}

	public void addMember(MemberVO vo) {
		datas.add(vo);
	}

	public boolean check(LoginBean login) { // ∫Û¡Ó¿« ±‚¥…
		boolean flag = false;
		Iterator<MemberVO> itr = datas.iterator();
		while(itr.hasNext()) {
			MemberVO vo = itr.next();
			if(vo.getUserID().equals(login.getUserID()) && vo.getUserPW().equals(login.getUserPW())) {
				return true;
			}
			
		}
		return flag;
	}

}
