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
		String saveDir = request.getSession().getServletContext().getRealPath("images"); //서버 이미지 경로
		imgVO.setPk(Integer.parseInt(request.getParameter("pk")));
		imgVO.setFilename(request.getParameter("filename"));
		
		if(imgDAO.m_deleteDB(imgVO)) {
			//파일 삭제
			saveDir += "/"+imgVO.getFilename();
			File file = new File(saveDir);			//파일 생성
			if(file.exists()) {						//파일이 있을시 삭제 
				file.delete();
			}
			forward.setRedirect(true);
			forward.setPath("main.do");
		}
		else {
			System.out.println("파일 삭제 실패");
			forward.setRedirect(true);
			forward.setPath("main.do");
		}
		

		
		return forward;
	}

}
