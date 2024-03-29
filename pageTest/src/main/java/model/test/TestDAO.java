package model.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.page.PageVO;
import model.common.JNDI;

public class TestDAO {
	String selectAll = "SELECT * FROM(SELECT A.*, ROWNUM AS RNUM FROM(SELECT * FROM TEST ORDER BY TPK) A WHERE ROWNUM < ?) WHERE RNUM >=?";
	String selelctAllm = "SELECT * FROM TEST LIMIT ?, 5";
	
	
	//sql이 긴 이유는 rownum > 10 이런식으로 사용이 안됨 rownum은 1부터 시작해야됨-- 설명하기가 어려운..
	
	TestVO data = null;
	ArrayList<TestVO> datas = new ArrayList<TestVO>();
	
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	
	
	public ArrayList<TestVO> t_selectAll(PageVO vo) {
		
		System.out.println("TestDAO :"+vo);

		conn = JNDI.connect();
		
		try {
			// oracle
			  pstmt = conn.prepareStatement(selectAll); pstmt.setInt(1, vo.getEnd());
			  pstmt.setInt(2, vo.getStart());
			 
			
			/* mysql
			 * pstmt = conn.prepareStatement(selelctAllm); pstmt.setInt(1, vo.getStart());
			 */
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				data = new TestVO();
				data.setTpk(rs.getInt("tpk"));
				data.setContent(rs.getString("content"));				
				datas.add(data);
			}
			rs.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}
		System.out.println("TestDAO datas:"+ datas);
		return datas;
		
	}

}
