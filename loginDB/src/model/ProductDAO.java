package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {
	
	String selectAll = "select * from product";
	String insert = "insert into product values((select nvl(max(pk),0)+1 from product),?,?)";
	

	private static Connection conn = null;
	private static PreparedStatement pstmt = null;
	
	public void Insert(ProductVO pdvo) {
		conn=JDBC.getConnection();
		try {
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, pdvo.getName());
			pstmt.setInt(2, pdvo.getPrice());
			if (pdvo.getName() != null) {
				pstmt.executeUpdate(); // ¡Ù¡Ù¡Ù
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBC.close(conn, pstmt);
		}
	}
	
	
	public ArrayList<ProductVO> SelectAll() {
		ArrayList<ProductVO> pdal = new ArrayList<ProductVO>();
		
		conn=JDBC.getConnection();
		
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(selectAll);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO pdvo = new ProductVO();
				pdvo.setName(rs.getString("name"));
				pdvo.setPrice(rs.getInt("price"));
				pdal.add(pdvo);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			JDBC.close(conn, pstmt);
		}
		return pdal;
	}
	
	

}
