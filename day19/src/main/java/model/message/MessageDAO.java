package model.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JNDI;
import model.reply.ReplyVO;

public class MessageDAO {

	String selectOne = "select * from message where mid = ?"; //

	String selectAll1 = "select * from message order by mdate desc";		
	String selectAll2 = "select * from message where id = ? order by mdate desc";	//게시글 목록 
	String insert = "insert into message(mid, id, msg, mdate) values(nvl((select max(mid) from message),0)+1,?,?,sysdate)";				//게시글 등록
	String delete = "delete from message where mid = ?";							//게시글 삭제
	String update = "update message set favcount = favcount + 1 where mid = ?";		//좋아요 카운트 증가
	
	
	
	String rSelectAll = "select * from reply where mid=? and rownum <= ? order by rid desc";
	String rSelectAll1 = "select * from reply where mid=?";
	

	MessageVO data = null;
	ArrayList<MessageVO> datas = null;

	ReplyVO rData = null;
	ArrayList<ReplyVO> rDatas = null;


	MessageSet setData = null;
	ArrayList<MessageSet> setDatas = null;
	Boolean flag =false;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;



	public ArrayList<MessageSet> selectAll(String qid, String msgmid, int mcnt) {
		System.out.println("selectAll msgmid, mcnt :"+ msgmid + ", " + mcnt);		
		int fcnt = 2; //초기 mcnt
		setDatas = new ArrayList<MessageSet>();
		conn = JNDI.connect();
		try {
			if(qid == null || qid =="") {			// 선택한 유저가 없을시 전체유저 출력
			pstmt = conn.prepareStatement(selectAll1);
			rs = pstmt.executeQuery();
			}
			else {			//선택한 유저 있을시 선택한 유저만 출력
				pstmt = conn.prepareStatement(selectAll2);
				pstmt.setString(1, qid);
				rs = pstmt.executeQuery();
				
			}
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
				
				if(msgmid != null && !msgmid.equals("")) {		//선택한 메세지가 있을시 선택한 메세지의 reply를 mcnt개 만큼 출력
					if(rs.getInt("mid") == Integer.parseInt(msgmid)) {
						pstmt.setInt(2, mcnt);
					}
					else {		//선택한 메시지 외에는 fcnt 갯수 만큼 출력
						pstmt.setInt(2, fcnt);
					}
				}
				else {		//선택한 메세지가 없을시 fcnt 갯수 만큼 출력
					pstmt.setInt(2, fcnt);
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

		conn = JNDI.connect();
		try {
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

		conn = JNDI.connect();
		try {
			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, vo.getMid());

			if(vo.getMid() != 0) {
				pstmt.executeUpdate();
				System.out.println("mdelete 성공");
				flag = true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;

	}



	public Boolean insert(MessageVO vo) {

		conn = JNDI.connect();
		try {
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getMsg());

			if(vo.getId()!=null) {
				System.out.println("게시글 등록 성공");
				pstmt.executeUpdate();
				flag = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;

	}

	

	public Boolean update(MessageVO vo) {
		System.out.println(vo);

		conn = JNDI.connect();
		try {
			pstmt = conn.prepareStatement(update);
			pstmt.setInt(1, vo.getMid());

			if(vo.getMid()!=0) {		//좋아요 카운트
				System.out.println("fcnt update 성공");
				pstmt.executeUpdate();
				flag  = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return flag;

	}







}
