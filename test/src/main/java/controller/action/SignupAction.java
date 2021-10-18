package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;

public class SignupAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = null;
		
		MemberDAO mDAO=new MemberDAO();
		MemberVO mVO= new MemberVO();
		
		mVO.setId(request.getParameter("id"));
		mVO.setPw(request.getParameter("pw"));
		mVO.setName(request.getParameter("name"));
		
		
		if(mDAO.signup(mVO)) {
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("login.jsp");
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out=response.getWriter();
	        out.println("<script>alert('회원가입 실패!');history.go(-1);</script>");
		}
		return forward;
	}

}
