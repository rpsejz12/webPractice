package controller.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.reply.ReplyDAO;
import model.reply.ReplyVO;

public class RdeleteAction implements Action {
	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();

		ReplyVO rVO = new ReplyVO();
		ReplyDAO rDAO = new ReplyDAO();

		rVO.setRid(Integer.parseInt(request.getParameter("rid")));
		rVO.setMid(Integer.parseInt(request.getParameter("mid")));

		try {
			if(rDAO.delete(rVO)) {
				System.out.println("message 삭제 성공");
				forward.setRedirect(false);
				forward.setPath("main.do");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("message 삭제 실패");
		}

		return forward;
	}

}
