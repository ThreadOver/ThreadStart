package JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
public class DBUTile {
		private static BasicDataSource datasource;
		static {
			Properties pop = new Properties();
			InputStream is = DBUTile.class.getClassLoader().getResourceAsStream("JDBC.properties");
			try {
				pop.load(is);
				String driver = pop.getProperty("driver");
				String url = pop.getProperty("url");
				String uname = pop.getProperty("username");
				String pass = pop.getProperty("password");
				String min = pop.getProperty("initSize");
				String max = pop.getProperty("maxSize");
				datasource = new BasicDataSource();
				datasource.setDriverClassName(driver);
				datasource.setUrl(url);
				datasource.setUsername(uname);
				datasource.setPassword(pass);
				datasource.setInitialSize(Integer.parseInt(min));
				datasource.setMaxActive(Integer.parseInt(max));	
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public static Connection getconn() throws SQLException {
			return datasource.getConnection();
		}
		
		public static void close(Connection conn,Statement stat,ResultSet rs) {
			try {
				if(rs!=null) {
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(stat!=null) {
				stat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn!=null) {
				//归还前把自动提交打开
				conn.setAutoCommit(true);
				conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
}
