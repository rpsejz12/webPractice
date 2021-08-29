package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public class MemberDAO {


	String selectAll = "select * from member"; 					//회원목록 (이름,아이디,비번)
	String selectOne = "insert into member values(?,?)";		//로그읜 (아이디,비번)
	String insert = "insert into member values(?,?,?)";			//회원가입(이름, 아이디,비번)


	private static Connection conn = null;
	private static PreparedStatement pstmt = null;

	public void Insert(MemberVO memvo) {
		conn=JDBC.getConnection();
		try {
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, memvo.getName());
			pstmt.setString(2, memvo.getUserID());
			pstmt.setString(3, memvo.getUserPW());
			if (memvo.getName() != null) {
				pstmt.executeUpdate(); // ☆☆☆
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBC.close(conn, pstmt);
		}
	}

	public ArrayList<MemberVO> SelectAll() {
		MemberVO memvo = new MemberVO();
		ArrayList<MemberVO> memal = new ArrayList<MemberVO>();
		
		conn=JDBC.getConnection();
		
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(selectAll);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memvo.setName(rs.getString("name"));
				memvo.setUserID(rs.getString("userID"));
				memvo.setUserPW(rs.getString("userPW"));
				memal.add(memvo);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBC.close(conn, pstmt);
		}
		return memal;
	}

}
