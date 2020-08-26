package common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	private JDBCTemplate() {}
		public static Connection getConnection() {
			Connection conn =null;
			
			Properties prop =null;
			//드라이버 프로퍼티 만들기 sql파일에 넣기
			
			String fileName = JDBCTemplate.class.getResource("/sql/driver.properties").getPath();
			//.class 는  그 클래스의 정보를 가져오는데, 거기서 getResource(경로)까지 가져오면 절대 경로를 가져오는거고
			// getPath()해서 더하기 어디 파일에 대한 절대경로를 가져오겠다......?
			
			try {
				prop = new Properties();
				prop.load(new FileReader(fileName));
			
				Class.forName(prop.getProperty("driver"));
				conn = DriverManager.getConnection(prop.getProperty("url"),
													prop.getProperty("username"),
													prop.getProperty("password"));
				conn.setAutoCommit(false);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return conn;
	}
		public static void close(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(ResultSet rset) {
			try {
				if(rset != null && !rset.isClosed()) {
					rset.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void close(Statement stmt) {
			try {
				if(stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void commit(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void rollback(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
