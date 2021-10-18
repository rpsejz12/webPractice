package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.page.PageDAO;
import common.page.PageVO;
import model.board.BoardDAO;

public class MainAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		ActionForward forward= new ActionForward();		
		BoardDAO bDAO = new BoardDAO();	
		
		PageVO pVO = new PageVO();
		PageDAO pDAO = new PageDAO();
		
		int page = 1;
		String ppage = request.getParameter("page");
		if(ppage != null) {
			page = Integer.parseInt(ppage);
		}
		
		pVO.setCurPage(page);	//	���� ������	
		pVO.setPerPage(10);		//	������ �Խù� ��
		pVO.setPerPageSet(10);	//	������ ��		
		pVO = pDAO.paging(pVO);
	
		request.setAttribute("page", page);
		request.setAttribute("paging", pVO);
		request.setAttribute("datas", bDAO.board_selectDB_all(pVO));				
		forward.setRedirect(false);
		forward.setPath("main.jsp");
		return forward;
	}

}