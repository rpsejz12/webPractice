package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.board.BoardDAO;
import model.board.BoardVO;
import model.member.MemberVO;

public class InsertAction  implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;
		HttpSession session=request.getSession();

		BoardDAO bDAO=new BoardDAO();
		BoardVO bVO=new BoardVO();
		
		bVO.setTitle(request.getParameter("title"));
		bVO.setContent(request.getParameter("content"));
		bVO.setId(((MemberVO)session.getAttribute("seUser")).getId());
		
		System.out.println("bVO :" + bVO);
		
		if(bDAO.board_insert(bVO)) {
			forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("main.do");
		}
		
		else {
			response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out=response.getWriter();
	        out.println("<script>alert('글등록 실패!');history.go(-1);</script>");
		}
		return forward;
	}

}
