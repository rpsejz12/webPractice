package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.message.MessageDAO;
import model.message.MessageVO;

public class MdeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();

		MessageVO mVO = new MessageVO();
		MessageDAO mDAO = new MessageDAO();

		mVO.setMid(Integer.parseInt(request.getParameter("mid")));

		if(mDAO.delete(mVO)) {
			System.out.println("message 삭제 성공");
			forward.setRedirect(false);
			forward.setPath("main.do");
		}

		return forward;
	}

}
