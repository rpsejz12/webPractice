package controller.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.img.ImgDAO;
import model.img.ImgVO;

public class DeleteAction {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		
		ImgDAO imgDAO = new ImgDAO();
		ImgVO imgVO = new ImgVO();
		String saveDir = request.getSession().getServletContext().getRealPath("images"); //���� �̹��� ���
		imgVO.setPk(Integer.parseInt(request.getParameter("pk")));
		imgVO.setFilename(request.getParameter("filename"));
		
		if(imgDAO.m_deleteDB(imgVO)) {
			//���� ����
			saveDir += "/"+imgVO.getFilename();
			File file = new File(saveDir);			//���� ����
			if(file.exists()) {						//������ ������ ���� 
				file.delete();
			}
			forward.setRedirect(true);
			forward.setPath("main.do");
		}
		else {
			System.out.println("���� ���� ����");
			forward.setRedirect(true);
			forward.setPath("main.do");
		}
		

		
		return forward;
	}

}
