package model.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.common.JNDI;

public class ReplyDAO {
	
							
	String insert = "insert into reply(rid,mid,id,rdate,rmsg) values(nvl((select max(rid) from reply),0)+1,?,?,sysdate,?)";			
	String delete = "delete from reply where rid = ?";						
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	

	public boolean insert(ReplyVO vo) {
		System.out.println(vo);

		try {
			conn = JNDI.connect();
			pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, vo.getMid());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getRmsg());
			if(vo.getId() != null) {
				pstmt.executeUpdate();
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return false;
	}
	
	public boolean delete(ReplyVO vo) {
		try {
			conn = JNDI.connect();
			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, vo.getRid());
			
			if(vo.getRid() != 0) {
				pstmt.executeUpdate();
				System.out.println("rdelete ¼º°ø");
				return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return false;
	}
}
