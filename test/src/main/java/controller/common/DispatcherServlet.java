package controller.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.action.ActionForward;
import controller.action.InsertAction;
import controller.action.LoginAction;
import controller.action.MainAction;
import controller.action.SignupAction;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri= request.getRequestURI();
		String cp=request.getContextPath();
		String action = uri.substring(cp.length());
		System.out.println("action :" + action);
		
		ActionForward forward=null; // null ���� �־���� �ϴ� ���� ???
	
		if(action.equals("/main.do")) { // ���� 
			forward=new MainAction().execute(request, response);
		} 
		else if(action.equals("/login.do")) { // �α���
			forward=new LoginAction().execute(request, response);
		}
		else if(action.equals("/signup.do")) { // ȸ������
			forward=new SignupAction().execute(request, response); 
		}
		else if(action.equals("/insert.do")) { // ���۵��
			forward=new InsertAction().execute(request, response); 
		}
		
		if (forward != null) { // ���� forward == null�̸� ���� �׼�ó���� ����� ���� ���� ��!
			if (forward.isRedirect()) { // redirect ���
				response.sendRedirect(forward.getPath());
			} else { // forward ��� - ��û ����� �����Ͽ� ������ �̵�
				// Dispatcher
				// : Ŭ���̾�Ʈ�κ��� ���ʿ� ���� ��û�� JSP/Servlet ������ ���ϴ� �ڿ����� ��û�� �ѱ�� ������ �����ϰų�,
				//   Ư�� �ڿ��� ó���� ��û�ϰ� ó�� ����� ������ ����� �����ϴ� Ŭ����
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath()); // ���� ���
				dispatcher.forward(request, response);
			}
		}
	}

}
