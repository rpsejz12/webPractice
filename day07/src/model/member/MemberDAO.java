package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JDBC;

public class MemberDAO {


	String selectAll = "select * from member"; 					//회원목록 (이름,아이디,비번)
	String selectOne = "insert into member values(?,?)";		//로그읜 (아이디,비번)
	String insert = "insert into member(bname, bid, bpw) values(?,?,?)";			//회원가입(이름, 아이디,비번)
	String check = "select * from member where bid = ? and bpw = ?";
	String delete = "delete from member where bid = ?";

	private static Connection conn = null;
	private static PreparedStatement pstmt = null;

	
	
	public MemberVO check(MemberVO memvo) {
		System.out.println(memvo);
		conn=JDBC.connect();
		MemberVO vo = null;		
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(check);
			pstmt.setString(1, memvo.getBid());
			pstmt.setString(2, memvo.getBpw());
			rs = pstmt.executeQuery();
			System.out.println(memvo);
			System.out.println(vo);
			
			if(rs.next()) {
				vo = new MemberVO();
				vo.setBnum(rs.getInt("bnum"));
				vo.setBid(rs.getString("bid"));
				vo.setBpw(rs.getString("bpw"));
				
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return vo;
	}
	

	public boolean insert(MemberVO memvo) {
		System.out.println(memvo);
		boolean res = false;
		conn=JDBC.connect();	
		try {
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, memvo.getBname());
			pstmt.setString(2, memvo.getBid());
			pstmt.setString(3, memvo.getBpw());
			if (memvo.getBid() != null) {
				pstmt.executeUpdate(); // ☆☆☆
				res=true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}
	
	

	public boolean delete(MemberVO memvo) {
		System.out.println(memvo);
		boolean res = false;
		conn=JDBC.connect();	
		try {
			pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, memvo.getBid());
			if (memvo.getBid() != null) {
				pstmt.executeUpdate(); // ☆☆☆
				res=true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return res;
	}

}