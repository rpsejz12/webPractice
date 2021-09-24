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
			throws ServletException, IOException{		
		HttpSession session=request.getSession();
		ActionForward forward = null;


		MemberVO uVO = new MemberVO();
		MemberDAO uDAO = new MemberDAO();
		
		
		uVO.setId(request.getParameter("id"));
		uVO.setPasswd(request.getParameter("passwd"));
		
		if(uDAO.selectOne(uVO) != null) {
			forward = new ActionForward();
			session.setAttribute("mem", uDAO.selectOne(uVO));
			forward.setRedirect(false);
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
