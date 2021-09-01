package model.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBC; // 20분에 시작! (*^▽^*)
import model.member.MemberVO;

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



	public ArrayList<MessageVO> searchDBList(MessageVO vo, String type){
		ArrayList<MessageVO> datas=new ArrayList();
		Connection conn=JDBC.connect();
		PreparedStatement pstmt=null;
		String sql = null;
		System.out.println(type);
		try{
			if(type.equals("1")) {
				sql=" select * from message where title like '%" + vo.getContent() + "%' order by mnum desc";
			}
			else if(type.equals("2")) {
				sql=" select * from message where writer like '%" + vo.getContent() + "%' order by mnum desc";
			}
			else if(type.equals("3")) {
				sql=" select * from message where content like '%" + vo.getContent() + "%' order by mnum desc";
			}
			else if(type.equals("4")) {
				sql=" select * from message where content like '%" + vo.getContent() + "%' or title like '%" + vo.getContent() + "%' order by mnum desc";
			}
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				MessageVO data = new MessageVO();
				data.setContent(rs.getString("content"));
				data.setMnum(rs.getInt("mnum"));
				data.setTitle(rs.getString("title"));
				data.setWdate(rs.getDate("wdate"));
				data.setWriter(rs.getString("writer"));
				datas.add(data);
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
		return datas;
	}



	public ArrayList<MessageVO> myList(MemberVO vo){
		System.out.println(vo);
		ArrayList<MessageVO> datas=new ArrayList();
		Connection conn=JDBC.connect();
		PreparedStatement pstmt=null;
		String sql = null;
		try{

			sql=" select * from message where writer = ? order by mnum desc";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBid());
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				MessageVO data = new MessageVO();
				data.setContent(rs.getString("content"));
				data.setMnum(rs.getInt("mnum"));
				data.setTitle(rs.getString("title"));
				data.setWdate(rs.getDate("wdate"));
				data.setWriter(rs.getString("writer"));
				datas.add(data);
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
		return datas;
	}







	public boolean insertDB(MessageVO vo) {
		boolean res= false;
		Connection conn=JDBC.connect();
		PreparedStatement pstmt=null;
		try{
			String sql="insert into message(writer, title, content, wdate) values(?,?,?,curdate());";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			if (vo.getTitle() != null) {
				pstmt.executeUpdate(); // ☆☆☆
				res=true;
			}
		}
		catch(Exception e){
			System.out.println("insertDB()에서 출력");
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt,conn);
		}
		return res;

	}

	public boolean deleteDB(MessageVO vo) {
		boolean res = false;

		Connection conn=JDBC.connect();
		PreparedStatement pstmt=null;
		try{
			String sql="delete from message where mnum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMnum());
			if (vo.getTitle() != null) {
				pstmt.executeUpdate(); // ☆☆☆
				res = true;
			}
		}
		catch(Exception e){
			System.out.println("deleteDB()에서 출력");
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt,conn);
		}
		return res;

	}


	public boolean updateDB(MessageVO vo) {
		boolean res = false;
		Connection conn=JDBC.connect();
		PreparedStatement pstmt=null;
		try{
			String sql="update message set title = ?, content = ?, wdate = curdate() where mnum = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.mnum);
			if (vo.getTitle() != null) {
				pstmt.executeUpdate(); // ☆☆☆
				res= true;

			}
		}
		catch(Exception e){
			System.out.println("updateDB()에서 출력");
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt,conn);
		}
		return res;

	}
}