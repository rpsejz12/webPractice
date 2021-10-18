package common.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JNDI;

public class PageDAO {


	//전체 리스트의 총 개수를 가져오는 sql
	String count = "select count(*) from board";
	PageVO data = null;


	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;

	public PageVO paging(PageVO vo) {		
		conn = JNDI.connect();
		try {			
			pstmt=conn.prepareStatement(count);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setTotal(rs.getInt(1));
			}		
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}

		vo.setLastPage((vo.getTotal()-1)/vo.getPerPage()+1);	//마지막 페이지 설정	
		vo.setStart((vo.getCurPage()-1)*vo.getPerPage());		//페이지에 보여줄 게시물 시작
		vo.setEnd(vo.getStart()+vo.getPerPage());				//페이지에 보여줄 게시물 끝		

		vo.setStartPage((vo.getCurPage()-1)/vo.getPerPageSet()*vo.getPerPageSet()+1);	//목차 시작
		if(vo.getStartPage() < 1) {		//시작페이지가 1보다 작을경우 1로 설정
			vo.setStartPage(1);
		}
		
		vo.setEndPage(vo.getStartPage()+vo.getPerPageSet()-1);							//목차 끝
		if(vo.getEndPage() > vo.getLastPage()) {	//끝페이지가 마지막페이지보다 클경우 마지막페이지로 설정
			vo.setEndPage(vo.getLastPage());
		}
		return vo;

	}






}
