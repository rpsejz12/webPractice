package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.common.JDBC;

public class MemberDAO {



	String selectOne = "select * from member where mid = ? and mpw = ?";	//로그인
	String selectAll = "select * from member"; 								//회원목록 
	String insert = "insert into member(mname, mid, mpw) values(?,?,?)";	//회원가입
	String delete = "delete from member where mid = ?";						//회원탈퇴
	String update = "update member set mpw = ? where mid = ?";					//회원정보변경

	MemberVO data = null;
	ArrayList<MemberVO> datas = null;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;




	public MemberVO selectOne(MemberVO vo) {		//로그인
		try{
			conn = JDBC.connect();
			pstmt=conn.prepareStatement(selectOne);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpw());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new MemberVO();
				data.setMpk(rs.getInt("mpk"));
				data.setMname(rs.getString("mname"));
				data.setMid(rs.getString("mid"));
				data.setMpw(rs.getString("mpw"));
			}	
			rs.close();
		}
		catch(Exception e){
			System.out.println(e);
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return data;
	}

	public ArrayList<MemberVO> selectAll(){
		datas= new ArrayList<MemberVO>();

		try {
			conn = JDBC.connect();
			pstmt = conn.prepareStatement(selectAll);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				data = new MemberVO();
				data.setMpk(rs.getInt("mpk"));
				data.setMname(rs.getString("mname"));
				data.setMid(rs.getString("mid"));
				data.setMpw(rs.getString("mpw"));
				datas.add(data);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return datas;
	}

	public boolean insert(MemberVO vo) {
		try {
			conn = JDBC.connect();
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, vo.getMname());
			pstmt.setString(2, vo.getMid());
			pstmt.setString(3, vo.getMpw());
			if(vo.getMname() != null) {
				pstmt.executeUpdate();
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return false;
	}
	public boolean delete(MemberVO vo) {
		try {
			conn = JDBC.connect();
			pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, vo.getMid());
			if(vo.getMid() != null) {
				pstmt.executeUpdate();
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return false;
	}
	
	public boolean update(MemberVO vo) {
		try {
			conn = JDBC.connect();
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, vo.getMpw());
			pstmt.setString(2, vo.getMid());
			if(vo.getMname() != null) {
				pstmt.executeUpdate();
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return false;
	}

}
