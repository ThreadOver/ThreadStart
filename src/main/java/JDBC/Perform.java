package JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import instance.Road;
import instance.User;

public class Perform {
	//���û��浽�û���
	public void  save(User user) {
		String name = user.getUser();
		String password = user.getPassword();
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUTile.getconn();
			String sql ="insert into puser values(null,?,?)";
			stat = conn.prepareStatement(sql);
			stat.setString(1, name);
			stat.setString(2, password);
			stat.executeUpdate();
			System.out.println(name+password);
			System.out.println("�û�ע��ɹ�");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUTile.close(conn, stat, rs);
		}
	}
	
	//����·�����û���
	public void  saveRoad(Road road) {
		String name = road.getUsername();
		String password = road.getRoad();
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = DBUTile.getconn();
			String sql ="insert into Road values(null,?,?)";
			stat = conn.prepareStatement(sql);
			stat.setString(1, name);
			stat.setString(2, password);
			stat.executeUpdate();
			System.out.println("·������ɹ���"+password);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUTile.close(conn, stat, rs);
		}
	}
	
	//ƥ�����ݿ��û�������׼���¼
	public int readpuser(String name, String Password) {
		int count = 0 ;
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		try {
			conn = DBUTile.getconn();
			String sql = "select count(*) from puser where username = ? and password = ?";
			stat = conn.prepareStatement(sql);
			stat.setString(1,name);
			stat.setString(2, Password);
		rs = stat.executeQuery();
		while(rs.next()) {
			 count = rs.getInt(1);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUTile.close(conn, stat, rs);
		}
		return count;
	}
	
	//��ѯ���ݿ��е�·��
		public List<Road> readRord(String name) {
			int count = 0 ;
			Connection conn = null;
			PreparedStatement stat = null;
			ResultSet rs = null;
			List<Road> roads = new ArrayList<Road>();
			try {
				conn = DBUTile.getconn();
				String sql = "select password from road where username = ? ";
				stat = conn.prepareStatement(sql);
				stat.setString(1,name);
			rs = stat.executeQuery();
			
			while(rs.next()) {
				Road road = new Road();
				String path = rs.getString("password");
				System.out.println(path);
				road.setRoad(path);
				road.setUsername(name);
				roads.add(road);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBUTile.close(conn, stat, rs);
			}
			return roads;
		}
}
