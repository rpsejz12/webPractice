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


			if(pstmt.executeUpdate() != 0) {
				System.out.println("rinsert ����");
			}


			pstmt = conn.prepareStatement(mUpdateAdd);
			pstmt.setInt(1, vo.getMid());

			if(pstmt.executeUpdate() !=0) {
				System.out.println("rinsert,update ����");
			}

			flag = true;
			conn.commit();
			System.out.println("rinsert Ŀ�� ����");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("rinsert Ʈ������ ����");
			e.printStackTrace();
			flag = false;
		}finally {
			conn.setAutoCommit(true); // set autocommit=0;	
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

			if(pstmt.executeUpdate() != 0) {
				System.out.println("rdelete ����");
			}
			pstmt = conn.prepareStatement(mUpdateMinus);
			pstmt.setInt(1, vo.getMid());
			
			if(pstmt.executeUpdate() != 0) {
				System.out.println("rdelete,update ����");
			}

			flag = true;
			conn.commit();
			System.out.println("rdelete Ŀ�� ����");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("rdelete Ʈ������ ����");
			e.printStackTrace();
			flag = false;

		}finally {
			conn.setAutoCommit(true);
			JNDI.disconnect(pstmt, conn);
		}
		return flag;
	}
}
