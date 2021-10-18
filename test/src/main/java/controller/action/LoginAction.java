package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.member.MemberVO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;
		HttpSession session=request.getSession();
		
		MemberDAO mDAO=new MemberDAO();
		MemberVO mVO=new MemberVO();
		
		mVO.setId(request.getParameter("id"));
		mVO.setPw(request.getParameter("pw"));
		
		mVO = mDAO.login(mVO);
		System.out.println("mvo:" + mVO);
		
		if(mVO!=null) {
			session.setAttribute("seUser", mVO);
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("main.do");
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out=response.getWriter();
	        out.println("<script>alert('로그인 실패!');history.go(-1);</script>");
		}
		
		
		return forward;
	}

}
