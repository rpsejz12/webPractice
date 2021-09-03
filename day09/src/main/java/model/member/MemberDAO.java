package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JDBC;

public class MemberDAO {
	
	String selectOne = "select * from member where bid = ? and bpw =?";
	private static Connection conn = null;
	private static PreparedStatement pstmt = null;

	
	
	public MemberVO selectOne(MemberVO vo) {
		System.out.println("입력받은 vo:" + vo);
		conn = JDBC.connect();
		MemberVO data = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(selectOne);
			pstmt.setString(1, vo.getBid());
			pstmt.setString(2, vo.getBpw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				data = new MemberVO();
				data.setBname(rs.getString("bname"));
				data.setBid(rs.getString("bid"));
				data.setBpw(rs.getString("bpw"));
				System.out.println("selectOne next:"+ data);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		
		
		return data;
	}

}
