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
		
		String saveDir = request.getSession().getServletContext().getRealPath("images"); //���� �̹��� ���
		String encoding = "UTF-8";
		System.out.println("imgVO  :" + imgVO);
		System.out.println("������  :" + saveDir);


		
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
				System.out.println("���ε� ����");
				forward.setRedirect(false);
				forward.setPath("main.do");
			} else {
				System.out.println("���ε� ����");
				//���ε� ���н� ���� ����
				saveDir += "/" + imgVO.getFilename();	//���
				File file = new File(saveDir);			//���� ����
				if(file.exists()) {						//������ ������ 
					file.delete();
				}
				
				
				forward.setRedirect(false);
				forward.setPath("form.do");
			} 
		
		
		
		
		
		return forward;
	}

}
