package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberVO;

public class SignoutAction {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ActionForward forward = new ActionForward();
		
		MemberVO uVO = new MemberVO();
		MemberDAO uDAO = new MemberDAO();
		
		uVO = (MemberVO)session.getAttribute("mem");
		
		if(uDAO.delete(uVO)) {
			System.out.println("member delete ¼º°ø");
			session.invalidate();
			forward.setRedirect(false);
			forward.setPath("main.do");
			
		}
		
		return forward;
	}

}
