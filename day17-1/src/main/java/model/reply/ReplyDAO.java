package model.reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JNDI;

public class ReplyDAO {

	String selectOne = "select * from reply where id = ? order by rid asc";
	String selectOne1 = "select * from reply where rid = ?";
	String insert = "insert into reply(rid,mid,id,rdate,rmsg) values(nvl((select max(rid) from reply),0)+1,?,?,sysdate,?)";			
	String delete = "delete from reply where rid = ?";						

	String mUpdateAdd = "update message set replycount = replycount + 1 where mid = ?";
	String mUpdateMinus = "update message set replycount = replycount - 1 where mid = ?";
	String mSelectOne ="select * from message where mid = ?";

	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	Boolean flag = false;

	public boolean insert(ReplyVO vo) throws SQLException {
		System.out.println("rVO :"+vo);
		conn = JNDI.connect();

		try {
			conn.setAutoCommit(false); // set autocommit=0;

			pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, vo.getMid());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getRmsg());

			if(vo.getId() != null) { 	//댓글 추가			
				pstmt.executeUpdate();
			}

			pstmt = conn.prepareStatement(mUpdateAdd);
			pstmt.setInt(1, vo.getMid());
			if(vo.getMid() != 0) {		//댓글수 증가
				pstmt.executeUpdate();
			}
			
			flag = true;
			conn.commit();
			System.out.println("rinsert 커밋 성공");
			conn.setAutoCommit(true); // set autocommit=0;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("rinsert 트랜젝션 오류");
			e.printStackTrace();
			conn.rollback();
			conn.setAutoCommit(true); // set autocommit=0;	
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;
	}

	public boolean delete(ReplyVO vo) throws SQLException {
		System.out.println("rVO :"+vo);
		conn = JNDI.connect();

			try {
				conn.setAutoCommit(false); // set autocommit=0;
				pstmt = conn.prepareStatement(delete);
				pstmt.setInt(1, vo.getRid());

				if(vo.getRid() != 0) { 	//댓글 삭제			
					pstmt.executeUpdate();
				}

				pstmt = conn.prepareStatement(mUpdateMinus);
				pstmt.setInt(1, vo.getMid());
				if(vo.getMid() != 0) {		//댓글수 감소
					pstmt.executeUpdate();
				}
				
				flag  = true;
				
				conn.commit();
				System.out.println("rdelete 커밋 성공");
				conn.setAutoCommit(true); // set autocommit=0;	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("rdelete 트랜젝션 오류");
			e.printStackTrace();
			conn.rollback();
			conn.setAutoCommit(true); // set autocommit=0;	
			
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;
	}
}
