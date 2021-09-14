package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.common.JNDI;



public class MemberDAO {
	String selectOne = "select * from member where id = ? and passwd = ?";	//�α���
	String selectAll = "select * from member"; 								//ȸ����� 
	String insert = "insert into member values(?,?,?)";						//ȸ������
	String delete = "delete from member where id = ?";						//ȸ��Ż��
	String update = "update member set passwd = ? where id = ?";			//ȸ����������

	MemberVO data = null;
	ArrayList<MemberVO> datas = null;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	
	public MemberVO selectOne(MemberVO vo) {		//�α���
		try{
			conn = JNDI.connect();
			pstmt=conn.prepareStatement(selectOne);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPasswd());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new MemberVO();
				data.setId(rs.getString("id"));
				data.setName(rs.getString("name"));
				data.setPasswd(rs.getString("passwd"));
			}	
			rs.close();
		}
		catch(Exception e){
			System.out.println(e);
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return data;
	}

	public ArrayList<MemberVO> selectAll(){
		datas= new ArrayList<MemberVO>();

		try {
			conn = JNDI.connect();
			pstmt = conn.prepareStatement(selectAll);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				data = new MemberVO();
				data.setId(rs.getString("id"));
				data.setName(rs.getString("name"));
				data.setPasswd(rs.getString("passwd"));
				datas.add(data);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return datas;
	}

	public boolean insert(MemberVO vo) {
		try {
			conn = JNDI.connect();
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPasswd());
			
			if(vo.getName() != null) {
				pstmt.executeUpdate();
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return false;
	}
	public boolean delete(MemberVO vo) {
		try {
			conn = JNDI.connect();
			pstmt = conn.prepareStatement(delete);
			pstmt.setString(1, vo.getId());
			if(vo.getId() != null) {
				pstmt.executeUpdate();
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return false;
	}
	
	public boolean update(MemberVO vo) {
		try {
			conn = JNDI.connect();
			pstmt = conn.prepareStatement(update);
			pstmt.setString(1, vo.getPasswd());
			pstmt.setString(2, vo.getId());
			if(vo.getName() != null) {
				pstmt.executeUpdate();
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JNDI.disconnect(pstmt, conn);
		}
		return false;
	}


}
