package model.img;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JNDI;


public class ImgDAO {
	String selectAll = "SELECT * FROM IMGFILE"; 											
	String insert = "INSERT INTO IMGFILE VALUES((SELECT NVL(MAX(PK),0)+1 FROM IMGFILE),?,?)";
	String delete = "DELETE FROM IMGFILE WHERE PK = ?";						

	ImgVO data = null;
	ArrayList<ImgVO> datas = null;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	public ArrayList<ImgVO> selectDB_all(){
		datas = new ArrayList<ImgVO>();
		conn = JNDI.connect();
		try {
			pstmt=conn.prepareStatement(selectAll);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				data = new ImgVO();
				data.setPk(rs.getInt("pk"));
				data.setTitle(rs.getString("title"));
				data.setFilename(rs.getString("filename"));
				datas.add(data);
			}
			rs.close();
			System.out.println("ImageDAO 영화 전체 리스트 :" + datas);
		}
		catch (SQLException e) {
			System.out.println("ImageDAO 영화 전체 리스트 오류");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;	
	}
	
	
	public Boolean m_insertDB(ImgVO vo) { 	
		System.out.println("ImgDAO 영화 등록 VO :" + vo);
		conn = JNDI.connect();
		boolean flag = false;
		try {
			pstmt=conn.prepareStatement(insert);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getFilename());

			if(vo.getFilename() != null) {			//등록 성공
				pstmt.executeUpdate();
				System.out.println("ImgDAO 영화 등록 성공");
				flag = true;
			}
		}
		catch (SQLException e) {
			System.out.println("ImgDAO 영화 등록 오류");
			e.printStackTrace();
		}
		finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;							//등록 실패

	}
	
	public Boolean m_deleteDB(ImgVO vo) {
		boolean flag = false;
		conn = JNDI.connect();
		
		try {
			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, vo.getPk());
			
			if(vo.getPk() != 0) {
				pstmt.executeUpdate();
				System.out.println("ImgDAO 삭제 성공");
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return flag;
	}
	

}
