package controller;

import java.util.ArrayList;
import java.util.Iterator;
import model.MemberDAO;
import model.MemberVO;

public class memController {
	
	public static MemberVO memvo = new MemberVO();
	public static MemberDAO memdao = new MemberDAO();
	public static ArrayList<MemberVO> memal = new ArrayList<MemberVO>();
	
	public void start() {
		memal = memdao.SelectAll();
		for(MemberVO vo : memal) {
			System.out.println(vo);
		}
	}
	
	
	
	public boolean login(MemberVO loginvo) { // ∫Û¡Ó¿« ±‚¥…
		boolean flag = false;
		Iterator<MemberVO> memitr = memal.iterator();
		while(memitr.hasNext()) {
			memvo = memitr.next();
			if(memvo.getUserID().equals(loginvo.getUserID()) && memvo.getUserPW().equals(loginvo.getUserPW())) {
				return true;
			}		
		}
		return flag;
	}
	
	

	public void signUp(MemberVO memvo) {
		for(MemberVO vo: memal) {
			System.out.println(vo);
		}
		memdao.Insert(memvo);
	}
	
	

	


}
