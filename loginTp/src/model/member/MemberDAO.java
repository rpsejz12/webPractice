package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBC;

public class MemberDAO {
	private String selectOne = "SELECT * FROM MEMBER WHERE ID = ? AND PW = ?";
	private String selectAll = "SELECT * FROM MEMBER";
	private String insertOne = "INSERT INTO MEMBER(NAME, ID, PW) VALUE(?,?,?)";
	private String delete = "DELETE FROM MEMBER WHERE ID = ?";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private MemberVO data = null;
	private ArrayList<MemberVO> datas = new ArrayList<MemberVO>();
	

	public MemberVO login(MemberVO vo) {//�α���
		System.out.println("�α��� vo"+vo);
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(selectOne);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				data = new MemberVO();
				data.setPk(rs.getInt("pk"));
				data.setName(rs.getString("name"));
				data.setId(rs.getString("id"));
				data.setPw(rs.getString("pw"));
			}
			rs.close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("�α��ο��� �߻�");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		System.out.println("login:"+ data);
		return data;
	}
	
	public ArrayList<MemberVO> selectAll() {//ȸ�����
		System.out.println("ȸ����� ����");
		conn = JDBC.connect();
		
		try {
			pstmt = conn.prepareStatement(selectAll);
			System.out.println("1");
			rs = pstmt.executeQuery();
			System.out.println("2");
			
			while(rs.next()) {
				System.out.println("3");
				data = new MemberVO();
				data.setPk(rs.getInt("pk"));
				data.setName(rs.getString("name"));
				data.setId(rs.getString("id"));
				data.setPw(rs.getString("pw"));
				System.out.println("selectAll:" +data);
				datas.add(data);
			}
			System.out.println("4");
			rs.close();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ȸ����Ͽ��� �߻�");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		System.out.println("ȸ����� ��");
		return datas;
	}
	
	
	public boolean insertOne(MemberVO vo) {//ȸ������
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(insertOne);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPw());
			
			if (vo.getId() != null) {
				pstmt.executeUpdate(); // �١١�
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ȸ�����Կ��� �߻�");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		
		return false;

	}
	
	public boolean delete(MemberVO vo) { //ȸ��Ż��
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, vo.getId());
			
			if(vo.getId() != null) {
				pstmt.executeUpdate();
				return true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ȸ�����Կ��� �߻�");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		
		return false;
	}

}
