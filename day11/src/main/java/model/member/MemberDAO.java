package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBC;

public class MemberDAO {
	String insert = "INSERT INTO MEMBER(MNAME, MID, MPW) VALUE(?,?,?)";
	String delete = "DELETE FROM MEMBER WHERE MID = ?";
	String selectOne = "SELECT * FROM MEMBER WHERE MID = ? AND MPW = ?";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private MemberVO data = null;
	private ArrayList<MemberVO> datas = new ArrayList<MemberVO>();
	

	public boolean insert(MemberVO vo) {	//ȸ������
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, vo.getMname());
			pstmt.setString(2, vo.getMid());
			pstmt.setString(3, vo.getMpw());
			
			if(vo.getMid() != null) {
				pstmt.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ȸ�����Կ���");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return false;
	}
	

	public boolean delete(MemberVO vo) {	//ȸ������
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, vo.getMid());
			
			if(vo.getMid() != null) {
				pstmt.executeUpdate();
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ȸ����������");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return false;
	}
	

	public MemberVO selectOne(MemberVO vo) {	//�α���
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(selectOne);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�α��ο���");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return data;
	}
	
	

}
