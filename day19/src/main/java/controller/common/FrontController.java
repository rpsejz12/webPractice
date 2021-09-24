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
import controller.action.LogoutAction;
import controller.action.MainAction;
import controller.action.MdeleteAction;
import controller.action.MfavAction;
import controller.action.MinsertAction;
import controller.action.RdeleteAction;
import controller.action.RinsertAction;
import controller.action.SignoutAction;
import controller.action.SignupAction;
import controller.action.UupdateAction;


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
		// 1) ������� ��û�� �м�
		String uri=request.getRequestURI();
		String cp=request.getContextPath();
		String action=uri.substring(cp.length());

		System.out.println();
		System.out.println(action);
		System.out.println(uri);

		ActionForward forward=null;
		// 2) ��Ʈ�ѷ��� ����
		if(action.equals("/main.do")) {		//����������
			forward=new MainAction().execute(request, response);
		}
		else if(action.equals("/login.do")) {	//�α���
			forward=new LoginAction().execute(request, response);
		}
		else if(action.equals("/logout.do")) {	//�α׾ƿ�
			forward=new LogoutAction().execute(request, response);
		}
		else if(action.equals("/signup.do")) {	//ȸ������
			forward=new SignupAction().execute(request, response);
		}
		else if(action.equals("/signout.do")) {	//ȸ��Ż��
			forward=new SignoutAction().execute(request, response);
		}
		else if(action.equals("/uupdate.do")) {	//ȸ������ ����
			forward=new UupdateAction().execute(request, response);
		}
		else if(action.equals("/minsert.do")) {	//��� �Է�
			forward=new MinsertAction().execute(request, response);
		}
		else if(action.equals("/mdelete.do")) {	//��� ����
			forward=new MdeleteAction().execute(request, response);
		}
		else if(action.equals("/rinsert.do")) {	//���� �Է�
			forward=new RinsertAction().execute(request, response);
		}
		else if(action.equals("/rdelete.do")) {	//���� ����
			forward=new RdeleteAction().execute(request, response);
		}
		else if(action.equals("/mfav.do")) {	//���ƿ�
			forward=new MfavAction().execute(request, response);
		}


		else {
			// ������������ ����
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/error/error404.jsp");
		}

		if(forward != null) {
			// 3) ����ڿ��� ��� ȭ�� ���
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}
			else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
