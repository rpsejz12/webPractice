package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.common.JNDI;

public class MemberDAO {
	
	String insert = "insert into member values (?,?,?)";
	String selectOne = "select * from member where id = ? and pw = ?";
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	MemberVO data = null;
	boolean isSuccess = true;
	
	public MemberVO login(MemberVO vo){
		conn = JNDI.connect();
		try {	
			System.out.println("login vo :" + vo);
			pstmt = conn.prepareStatement(selectOne);	
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				data = new MemberVO();
				data.setId(rs.getString("id"));
				data.setPw(rs.getString("pw"));
				data.setName(rs.getString("name"));		
				System.out.println(data);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JNDI.disconnect(pstmt, conn);
		}		
		return data;
	}
	
	public boolean signup(MemberVO vo) {
		conn = JNDI.connect();
		try {
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			isSuccess = false;
		} finally {
			JNDI.disconnect(pstmt, conn);
		}			
		return isSuccess;
	}

}
