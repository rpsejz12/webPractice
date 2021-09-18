package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class SignupAction {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		
		MemberVO uVO = new MemberVO();
		MemberDAO uDAO = new MemberDAO();
		
		uVO.setId(request.getParameter("id"));
		uVO.setPasswd(request.getParameter("passwd"));
		uVO.setName(request.getParameter("name"));
		
		if(uDAO.insert(uVO)) {
			System.out.println("member insert ¼º°ø");
			forward.setRedirect(false);
			forward.setPath("main.do");
		}
		

		
		return forward;
	}

}
