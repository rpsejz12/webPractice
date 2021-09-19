package controller.action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.img.ImgDAO;
import model.img.ImgVO;

public class UploadAction {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		ImgVO imgVO = new ImgVO();
		ImgDAO imgDAO = new ImgDAO();
		
		String saveDir = request.getSession().getServletContext().getRealPath("images"); //서버 이미지 경로
		String encoding = "UTF-8";
		System.out.println("imgVO  :" + imgVO);
		System.out.println("절대경로  :" + saveDir);


		
			int maxSize = 16 * 1024 * 1024; //
			MultipartRequest multi;
			try {
				multi = new MultipartRequest(request, saveDir, maxSize, encoding,
						new DefaultFileRenamePolicy());
				imgVO.setTitle( multi.getParameter("title"));
				imgVO.setFilename((String)multi.getFilesystemName("filename"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
			if (imgDAO.m_insertDB(imgVO)) {
				System.out.println("업로드 성공");
				forward.setRedirect(false);
				forward.setPath("main.do");
			} else {
				System.out.println("업로드 실패");
				//업로드 실패시 파일 삭제
				saveDir += "/" + imgVO.getFilename();	//경로
				File file = new File(saveDir);			//파일 생성
				if(file.exists()) {						//파일이 있을시 
					file.delete();
				}
				
				
				forward.setRedirect(false);
				forward.setPath("form.do");
			} 
		
		
		
		
		
		return forward;
	}

}
