package model.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.buy.BuyVO;
import model.common.JDBC;

public class ProductDAO {
	/*ProductVO			ppk, pname, ptype, pprice, pnum*/
	
	String insert = "INSERT INTO PRODUCT(PNAME, PTYPE, PPRICE, PNUM) VALUE (?,?,?,?)";
	String delete = "DELETE FROM PRODUCT WHERE PPK = ?";
	String selectAll = "SELECT * FROM PRODUCT";
	String selectOne = "SELECT * FROM PRODUCT WHERE PNAME = ?";
	String update = "UPDATE PRODUCT SET PNUM = PNUM - ? WHERE PNAME = ?";
	
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ProductVO data = null;
	private ArrayList<ProductVO> datas = new ArrayList<ProductVO>();
	
	public boolean insert(ProductVO vo) {		//��ǰ ���
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(insert);
			pstmt.setString(1, vo.getPname());
			pstmt.setString(2, vo.getPtype());
			pstmt.setInt(3, vo.getPprice());
			if(vo.getPname() != null) {
				pstmt.executeUpdate();
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ǰ��Ͽ���");
			
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return false;
	}
	
	public boolean delete(ProductVO vo) {		//��ǰ����
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(delete);
			pstmt.setInt(1, vo.getPpk());
			if(vo.getPname() != null) {
				pstmt.executeUpdate();
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ǰ��������");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return false;
	}
	
	public ArrayList<ProductVO> selectAll() {	//��ǰ���
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(selectAll);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				data = new ProductVO();
				data.setPpk(rs.getInt("ppk"));
				data.setPname(rs.getString("pname"));
				data.setPtype(rs.getString("ptype"));
				data.setPprice(rs.getInt("pprice"));
				data.setPnum(rs.getInt("pnum"));
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ǰ��¿���");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return datas;
	}
	
	

	public ProductVO selectOne(String bname, int bnum) {	//��ٱ���
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(selectOne);
			pstmt.setString(1, bname);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt("pnum")>=bnum) {
					
				data = new ProductVO();
				data.setPpk(rs.getInt("ppk"));
				data.setPname(rs.getString("pname"));
				data.setPtype(rs.getString("ptype"));
				data.setPprice(rs.getInt("pprice"));
				data.setPnum(rs.getInt("pnum"));
					
				}
				else {
					System.out.println("�����ʰ�");
					return null;
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ٱ����߰�(productDAO)����");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return data;
	}
	
	public boolean update(BuyVO vo) {		//��ǰ ���� ������Ʈ
		conn = JDBC.connect();
		try {
			pstmt = conn.prepareStatement(update);
			pstmt.setInt(1,vo.getBnum());
			pstmt.setString(2, vo.getBname());
			if(vo.getBname()!=null) {
				pstmt.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("��ǰ������Ʈ����");
		}finally {
			JDBC.disconnect(pstmt, conn);
		}
		return false;
	}
	
	

	public ArrayList<ProductVO> searchDBList(String content, String type){
		ArrayList<ProductVO> datas=new ArrayList();
		Connection conn=JDBC.connect();
		PreparedStatement pstmt=null;
		String sql = null;
		System.out.println(type);
		try{
			if(type.equals("1")) {
				sql=" select * from product where pname like '%" + content + "%'";
			}
			else if(type.equals("2")) {
				sql=" select * from product where ptype like '%" + content + "%'";
			}
			pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				ProductVO data = new ProductVO();
				data.setPpk(rs.getInt("ppk"));
				data.setPname(rs.getString("pname"));
				data.setPtype(rs.getString("ptype"));
				data.setPprice(rs.getInt("pprice"));
				data.setPnum(rs.getInt("pnum"));
				datas.add(data);
			}
			rs.close();
		}
		catch(Exception e){
			System.out.println("getDBData()���� ���");
			e.printStackTrace();
		}
		finally {
			JDBC.disconnect(pstmt,conn);
		}
		return datas;
	}


}
