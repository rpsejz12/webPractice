package model.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBC; // 20분에 시작! (*^▽^*)

public class MessageDAO {

	public ArrayList<MessageVO> getDBList(){
		Connection conn=JDBC.connect();
		ArrayList<MessageVO> datas=new ArrayList();
		PreparedStatement pstmt=null;
		try{
			String sql="select * from message order by mnum desc"; // 최근 게시글 상단 배치
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				MessageVO vo=new MessageVO();
				vo.setContent(rs.getString("content"));
				vo.setMnum(rs.getInt("mnum"));
				vo.setTitle(rs.getString("title"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setWriter(rs.getString("writer"));
				datas.add(vo);
			}
			rs.close();
		}
		catch(Exception e){
			System.out.println("getDBList()에서 출력");
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt,conn);
		}

		return datas;
	}

	public MessageVO getDBData(MessageVO vo){
		Connection conn=JDBC.connect();
		MessageVO data=null;
		PreparedStatement pstmt=null;
		try{
			String sql="select * from message where mnum=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMnum());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				data=new MessageVO();
				data.setContent(rs.getString("content"));
				data.setMnum(rs.getInt("mnum"));
				data.setTitle(rs.getString("title"));
				data.setWdate(rs.getDate("wdate"));
				data.setWriter(rs.getString("writer"));
			}
			rs.close();
		}
		catch(Exception e){
			System.out.println("getDBData()에서 출력");
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt,conn);
		}
		return data;
	}

	public void insertDB(MessageVO vo) {
		Connection conn=JDBC.connect();
		PreparedStatement pstmt=null;
		try{
			String sql="insert into message values((select nvl(max(mnum),0)+1 from message),?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			if (vo.getTitle() != null) {
				pstmt.executeUpdate(); // ☆☆☆
			}
		}
		catch(Exception e){
			System.out.println("insertDB()에서 출력");
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt,conn);
		}

	}

	public void deleteDB(MessageVO vo) {
		
		Connection conn=JDBC.connect();
		PreparedStatement pstmt=null;
		try{
			String sql="delete from message where mnum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMnum());
			if (vo.getTitle() != null) {
				pstmt.executeUpdate(); // ☆☆☆
			}
		}
		catch(Exception e){
			System.out.println("deleteDB()에서 출력");
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt,conn);
		}

	}


	public void updateDB(MessageVO vo) {
		
		Connection conn=JDBC.connect();
		PreparedStatement pstmt=null;
		try{
			String sql="update message set title = ?, content = ?, wdate = sysdate where mnum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.mnum);
			if (vo.getTitle() != null) {
				pstmt.executeUpdate(); // ☆☆☆
			}
		}
		catch(Exception e){
			System.out.println("updateDB()에서 출력");
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt,conn);
		}

	}
}
