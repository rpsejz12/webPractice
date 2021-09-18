package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberVO;
import model.message.MessageDAO;
import model.message.MessageVO;

public class MinsertAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{		
		HttpSession session=request.getSession();
		ActionForward forward = new ActionForward();

		MemberVO uVO = (MemberVO)session.getAttribute("mem");
		
		MessageDAO mDAO = new MessageDAO();
		MessageVO mVO = new MessageVO();
		
		
		mVO.setId(uVO.getId());
		mVO.setMsg(request.getParameter("msg"));
		
		
		if(mDAO.insert(mVO)) {
			System.out.println("message insert ¼º°ø");
			forward.setRedirect(false);
			forward.setPath("main.do");
		}

		return forward;
	}

}
