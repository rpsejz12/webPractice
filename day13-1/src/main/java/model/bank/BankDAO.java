package model.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.common.JDBC;

public class BankDAO {
	BankVO data = null;
	ArrayList<BankVO> datas = null;
	Connection conn=null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	String selectAll="SELECT * FROM BANK";
	String selectOne="SELECT * FROM BANK WHERE NAME = ?";
	String transOut="update bank set balance=balance-? where NAME=?";
	String transIn="update bank set balance=balance+? where NAME=?";


	public ArrayList<BankVO> getBankAll() {
		conn = JDBC.connect();
		datas = new ArrayList<BankVO>();
		try {
			pstmt=conn.prepareStatement(selectAll);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				data = new BankVO();
				data.setBid(rs.getInt("bid"));
				data.setName(rs.getString("name"));
				data.setBalance(rs.getInt("balance"));
				datas.add(data);
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt, conn);
		}
		return datas;
	}
	
	public BankVO getBankOne(String name) {
		conn = JDBC.connect();

		try {
			pstmt=conn.prepareStatement(selectOne);
			pstmt.setString(1, name);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				data = new BankVO();
				data.setBid(rs.getInt("bid"));
				data.setName(rs.getString("name"));
				data.setBalance(rs.getInt("balance"));
				System.out.print("getBankOne"+ data);
			}
			rs.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt, conn);
		}
		return data;
	}
	
	
	public boolean trans(BankVO vo1, BankVO vo2,int balance) {
		conn = JDBC.connect();
		try {
			conn.setAutoCommit(false); // set autocommit=0;
			pstmt=conn.prepareStatement(transOut);
			pstmt.setInt(1, balance);
			pstmt.setString(2, vo1.getName());
			pstmt.executeUpdate();
			
			pstmt=conn.prepareStatement(transIn);
			pstmt.setInt(1, balance);
			pstmt.setString(2, vo2.getName());
			pstmt.executeUpdate();
			
			pstmt =conn.prepareStatement(selectOne);
			pstmt.setString(1, vo1.getName());
			ResultSet rs=pstmt.executeQuery();
			
			rs.next();
			if(rs.getInt("balance")<0) { // 잔액이 0미만이면,
				conn.rollback();
				return false;
			}
			else {
				conn.commit(); // commit;
			}
			rs.close();
			conn.setAutoCommit(true); // set autocommit=0;	
			JDBC.disconnect(pstmt, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return true;
	}
	
	
	

}
