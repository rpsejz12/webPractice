package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberDAO;
import model.message.MessageDAO;
import model.message.MessageSet;

public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		ActionForward forward = new ActionForward();
		MessageDAO mDAO = new MessageDAO();
		MemberDAO uDAO = new MemberDAO();
		
		

		String mcntt=request.getParameter("mcnt");					//����¡ ó���� ���� main���� �޾ƿ� ��
		String msgmid = request.getParameter("msgmid");				//���� Ŭ���� �޼��� mid ��������
		String msgmidd = (String)session.getAttribute("msgmidd");	//������ Ŭ���� �޼��� mid ��������
		String qid = request.getParameter("qid");					//������ ����


		System.out.println("qid :" + qid);
		System.out.println("mcntt :" +mcntt);
		System.out.println("msgmid :" + msgmid);
		System.out.println("msgmidd :" + msgmidd);
		
		int mcnt=2;	
		if(mcntt!=null){	//main ���� ���� �Ѿ�� ��� int�� ĳ����
			mcnt=Integer.parseInt(mcntt);
		}		
		
		if(msgmid == null && msgmidd == null) {  //���ʽ����	
			System.out.println("msgmid, msgmidd : null");
			msgmid = "0"; 		//�ʱⰪ 0���� ����
		}
		else if(msgmid ==null){		//������ Ŭ���̿� ��� �ൿ
			msgmid = msgmidd;
			System.out.println("msgmid == null");
		}
		else if(msgmidd == null){	//�ʱ⼳���� �ؼ� ���� ���ɼ��� �����Ű�����?
			msgmidd = msgmid;
			System.out.println("msgmidd == null");
		}
		else{
			if(msgmidd.equals(msgmid)){		//������ ���� mid�� ������ Ŭ����
				System.out.println("msgmid == msgmidd");
				mcnt +=3;
			}
			else{				// ������ �ٸ� mid�� ������ Ŭ����
				System.out.println("msgmid != msgmidd");
				mcnt = 5;
			}
		}
			
		session.setAttribute("msgmidd", msgmid);
		session.setAttribute("ulist", uDAO.selectAll());						//�ű�ȸ�� ����Ʈ
		request.setAttribute("qid", qid);
		request.setAttribute("mcnt", mcnt);
		request.setAttribute("datas", mDAO.selectAll(qid, msgmid, mcnt));		//�޼��� ����Ʈ
	

		forward.setRedirect(false);
		forward.setPath("main.jsp");
		return forward;
	}
	
	

}