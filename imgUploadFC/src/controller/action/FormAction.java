package controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormAction {			//���ε� ��ư Ŭ���� ���ε� �������� �̵�

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(true);
		forward.setPath("form.jsp");
		return forward;
	}

}
