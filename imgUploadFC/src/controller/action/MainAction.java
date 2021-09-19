package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.img.ImgDAO;

public class MainAction {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		HttpSession session=request.getSession();
		
		ImgDAO imgDAO = new ImgDAO();
		
		
		session.setAttribute("datas", imgDAO.selectDB_all());		
		forward.setRedirect(false);
		forward.setPath("main.jsp");
		return forward;
	}


}
