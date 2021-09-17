package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member.MemberVO;
import model.message.MessageDAO;
import model.reply.ReplyDAO;
import model.reply.ReplyVO;

public class RinsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{		
		HttpSession session=request.getSession();
		ActionForward forward = new ActionForward();

		String mcntt=request.getParameter("mcnt");
		String qid=request.getParameter("qid");
		String msgmid=request.getParameter("msgmid");
		String msgmidd=(String)session.getAttribute("msgmidd");
		MemberVO mVO = (MemberVO)session.getAttribute("mem");

		ReplyDAO rDAO=new ReplyDAO();
		ReplyVO rVO=new ReplyVO();

		MessageDAO mDAO=new MessageDAO();

		int mcnt=2;
		if(mcntt!=null){
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
		

		rVO.setMid(Integer.parseInt(request.getParameter("mid")));
		rVO.setId(mVO.getId());
		rVO.setRmsg(request.getParameter("rmsg"));

		try {
			rDAO.insert(rVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("rinsert ����");
			e.printStackTrace();
			forward.setRedirect(false);
			forward.setPath("/error/error404.jsp");
		}



		request.setAttribute("msgmid", msgmid);
		request.setAttribute("qid", qid);
		request.setAttribute("mcnt", mcnt);
		request.setAttribute("datas", mDAO.selectAll(qid, msgmid, mcnt));		//�޼��� ����Ʈ

		forward.setRedirect(false);
		forward.setPath("main.jsp");
		return forward;
	}

}
