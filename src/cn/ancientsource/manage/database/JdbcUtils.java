package cn.ancientsource.manage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
	/**
	 * @author Qiangwei_Luo
	 */
	//private static String url = "jdbc:mysql://localhost:3306/blue?useSSL=false";
	//private static String user;
	//private static String password;
	
	private JdbcUtils() {}
	
	static {
		// ע������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	// ��������
	public static Connection getConnextion(String url, String user, String password) throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
	// �ر�����    ʹ�� finally ȷ���ر��ڳ���ʱ��������ִ��
	public static void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if(rs != null) {
				rs.close();
				rs = null;
			}
		} catch(SQLException e){
			e.printStackTrace();
		} finally {
			try {
				if(st != null) {
					st.close();
					st = null;
				}
			} catch(SQLException e){
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
						conn = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
