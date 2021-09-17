package controller.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.ActionForward;
import controller.action.LoginAction;
import controller.action.MainAction;
import controller.action.RinsertAction;


/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 사용자의 요청을 분석
		String uri=request.getRequestURI();
		String cp=request.getContextPath();
		String action=uri.substring(cp.length());
		System.out.println();
		System.out.println(action);

		System.out.println(uri);

		ActionForward forward=null;
		// 2) 컨트롤러랑 매핑
		if(action.equals("/main.do")) {		//메인페이지
			forward=new MainAction().execute(request, response);
		}
		else if(action.equals("/login.do")) {	//로그인
			forward=new LoginAction().execute(request, response);
		}
		else if(action.equals("/logout.do")) {	//로그아웃

		}
		else if(action.equals("/signup.do")) {	//회원가입

		}
		else if(action.equals("/signout.do")) {	//회원탈퇴

		}
		else if(action.equals("/update.do")) {	//회원정보 변경

		}
		else if(action.equals("/minsert.do")) {	//댓글 입력

		}
		else if(action.equals("/mdelete.do")) {	//댓글 삭제

		}
		else if(action.equals("/rinsert.do")) {	//대댓글 입력
			forward=new RinsertAction().execute(request, response);
		}
		else if(action.equals("/rdelete.do")) {	//대댓글 삭제

		}
		else if(action.equals("/fcnt.do")) {	//좋아요

		}


		else {
			// 에러페이지랑 연결
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/error/error404.jsp");
		}

		// 3) 사용자에게 결과 화면 출력
		if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		}
		else {
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}
}
