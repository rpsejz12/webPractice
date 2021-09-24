package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class SignupAction {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		
		
		MemberVO uVO = new MemberVO();
		MemberDAO uDAO = new MemberDAO();
		
		uVO.setId(request.getParameter("id"));
		uVO.setPasswd(request.getParameter("passwd"));
		uVO.setName(request.getParameter("name"));
		
		if(uDAO.insert(uVO)) {
			System.out.println("member insert 성공");
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main.do");
		}
		
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>alert('회원가입 실패!');history.go(-1);</script>");
		}
		

		
		return forward;
	}

}
