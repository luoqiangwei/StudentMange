package cn.ancientsource.manage.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Jdbc {
	/**
	 * @author Qiangwei_Luo
	 */
	//private static String SQLlib = null;
	private static Connection conn = null;
	private static Statement st = null;
	private static ResultSet rs = null;
	private static int I_num = 0;
	
	// ������ MySql ������
	public static void linkSql(String url, String user, String password){
		try {
			// ��������
			conn = JdbcUtils.getConnextion(url, user, password);
			// ������
			/*while(rs.next()) {
				System.out.println(rs.getObject(1));
			}*/
		} catch(Exception e) {
			JOptionPane.showMessageDialog( null,"�������ݿ�ʧ��!");
			System.exit(1);
		}
	}
	
	// ���� MySql ���
	public static ResultSet SqlStatement (String SQLlib) throws SQLException {
		try {
			// �������
			st = conn.createStatement();
			// ִ�����
			rs = st.executeQuery(SQLlib);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ���ؽ���Դ������
		return rs;
	}
	
	// ִ�� INSERT��UPDATE �� DELETE ����Լ� SQL DDL�����ݶ������ԣ����
	public static int SqlStatementUpdate(String SQLlib) throws SQLException {
		// �������
		st = conn.createStatement();
		// ִ�����
		I_num = st.executeUpdate(SQLlib);
		
		return I_num;
	}
	
	// �ر����ݿ�
	public static void closeSql() {
		JdbcUtils.free(rs, st, conn);
	}
}
