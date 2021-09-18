package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberVO;
import model.reply.ReplyDAO;
import model.reply.ReplyVO;

public class RinsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{		
		HttpSession session=request.getSession();
		ActionForward forward = new ActionForward();

		MemberVO mVO = (MemberVO)session.getAttribute("mem");
		ReplyDAO rDAO=new ReplyDAO();
		ReplyVO rVO=new ReplyVO();


		rVO.setMid(Integer.parseInt(request.getParameter("mid")));
		rVO.setId(mVO.getId());
		rVO.setRmsg(request.getParameter("rmsg"));

		try {
			rDAO.insert(rVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("rinsert ¿À·ù");
			e.printStackTrace();
			forward.setRedirect(false);
			forward.setPath("/error/error404.jsp");
		}


		forward.setRedirect(false);
		forward.setPath("main.do");
		return forward;
	}

}
