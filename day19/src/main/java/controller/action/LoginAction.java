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

		String mcntt=request.getParameter("mcnt");
		String qid=request.getParameter("qid");
		String msgmid=request.getParameter("mid");
		
		
		MemberVO uVO = new MemberVO();
		MemberDAO uDAO = new MemberDAO();
		
		uVO.setId(request.getParameter("id"));
		uVO.setPasswd(request.getParameter("passwd"));
		
		int mcnt=2;
		if(mcntt!=null){
			mcnt=Integer.parseInt(mcntt);
		}
		
		if(uDAO.selectOne(uVO) != null) {
			session.setAttribute("mem", uVO);
			request.setAttribute("msgmid", msgmid);
			request.setAttribute("qid", qid);
			request.setAttribute("mcnt", mcnt);
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("main.do");
			
		}
		else {
			PrintWriter out=response.getWriter();
			out.println("<script>alert('로그인 실패!');history.go(-1);</script>");
		}
		

		return forward;
	}

}
