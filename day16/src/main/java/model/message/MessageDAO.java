package model.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JNDI;
import model.reply.ReplyVO;

public class MessageDAO {

	String selectOne = "select * from message where mid = ?"; //

	String selectAll = "select * from message order by mdate desc";								//게시글 목록 
	String insert = "insert into message(id, msg, mdate) values(?,?,now())";				//게시글 등록
	String delete = "delete from message where mid = ?";						//게시글 삭제
	String rSelectAll = "select * from reply where mid=? order by rdate asc limit 0,?";
	String count = "SELECT COUNT(*) FROM reply where mid = ?";
	/*
	 * String update = "update message set passwd = ? where uid = ?"; //회원정보변경
	 */

	MessageVO data = null;
	ArrayList<MessageVO> datas = null;

	ReplyVO rData = null;
	ArrayList<ReplyVO> rDatas = null;


	MessageSet setData = null;
	ArrayList<MessageSet> setDatas = null;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;



	public ArrayList<MessageSet> selectAll(String mid, int page) {
		System.out.println("selectAll mid, page:"+ mid + page);

		setDatas = new ArrayList<MessageSet>();
		try {
			conn = JNDI.connect();
			pstmt = conn.prepareStatement(selectAll);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				rDatas = new ArrayList<ReplyVO>();
				data = new MessageVO();
				setData = new MessageSet();

				data.setMid(rs.getInt("mid"));
				data.setId(rs.getString("id"));
				data.setMsg(rs.getString("msg"));
				data.setFavcount(rs.getInt("favcount"));
				data.setReplycount(rs.getInt("replycount"));
				data.setMdate(rs.getDate("mdate"));

				pstmt = conn.prepareStatement(rSelectAll);
				pstmt.setInt(1, data.getMid());
				
				if(rs.getInt("mid") == Integer.parseInt(mid)) {
					pstmt.setInt(2, page);
				}
				else {
				pstmt.setInt(2, 2);
				}
				ResultSet rs1 = pstmt.executeQuery();
				while(rs1.next()) {
					rData = new ReplyVO();
					rData.setRid(rs1.getInt("rid"));
					rData.setMid(rs1.getInt("mid"));
					rData.setId(rs1.getString("id"));
					rData.setRdate(rs1.getDate("rdate"));
					rData.setRmsg(rs1.getString("rmsg"));
					rDatas.add(rData);				
				}
				rs1.close();
				

				setData.setM(data);
				setData.setRlist(rDatas);
				setDatas.add(setData);
				rs1.close();
			}			
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return setDatas;

	}


	public MessageVO selectOne() {

		try {
			conn = JNDI.connect();
			pstmt = conn.prepareStatement(selectOne);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				data = new MessageVO();
				data.setMid(rs.getInt("mid"));
				data.setId(rs.getString("id"));
				data.setMsg(rs.getString("msg"));
				data.setFavcount(rs.getInt("favcount"));
				data.setReplycount(rs.getInt("replycount"));
				data.setMdate(rs.getDate("mdate"));
				datas.add(data);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;

	}


	public Boolean delete(MessageVO vo) {
		System.out.println("Delete VO:"+ vo);

		try {
			conn = JNDI.connect();
			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, vo.getMid());

			if(vo.getMid() != 0) {
				pstmt.executeUpdate();
				System.out.println("mdelete 성공");
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



	public Boolean insert(MessageVO vo) {

		try {
			conn = JNDI.connect();
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getMsg());

			if(vo.getId()!=null) {
				System.out.println("게시글 등록 성공");
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







}
