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
		
		

		String mcntt=request.getParameter("mcnt");					//페이징 처리를 위해 main에서 받아온 값
		String msgmid = request.getParameter("msgmid");				//현재 클릭한 메세지 mid 가져오기
		String msgmidd = (String)session.getAttribute("msgmidd");	//이전에 클릭한 메세지 mid 가져오기
		String qid = request.getParameter("qid");					//선택한 유저


		System.out.println("qid :" + qid);
		System.out.println("mcntt :" +mcntt);
		System.out.println("msgmid :" + msgmid);
		System.out.println("msgmidd :" + msgmidd);
		
		int mcnt=2;	
		if(mcntt!=null){	//main 에서 값이 넘어올 경우 int로 캐스팅
			mcnt=Integer.parseInt(mcntt);
		}		
		
		if(msgmid == null && msgmidd == null) {  //최초실행시	
			System.out.println("msgmid, msgmidd : null");
			msgmid = "0"; 		//초기값 0으로 설정
		}
		else if(msgmid ==null){		//더보기 클릭이외 모든 행동
			msgmid = msgmidd;
			System.out.println("msgmid == null");
		}
		else if(msgmidd == null){	//초기설정을 해서 나올 가능성이 없을거같은대?
			msgmidd = msgmid;
			System.out.println("msgmidd == null");
		}
		else{
			if(msgmidd.equals(msgmid)){		//이전과 같은 mid의 더보기 클릭시
				System.out.println("msgmid == msgmidd");
				mcnt +=3;
			}
			else{				// 이전과 다른 mid의 더보기 클릭시
				System.out.println("msgmid != msgmidd");
				mcnt = 5;
			}
		}
			
		session.setAttribute("msgmidd", msgmid);
		session.setAttribute("ulist", uDAO.selectAll());						//신규회원 리스트
		request.setAttribute("qid", qid);
		request.setAttribute("mcnt", mcnt);
		request.setAttribute("datas", mDAO.selectAll(qid, msgmid, mcnt));		//메세지 리스트
	

		forward.setRedirect(false);
		forward.setPath("main.jsp");
		return forward;
	}
	
	

}