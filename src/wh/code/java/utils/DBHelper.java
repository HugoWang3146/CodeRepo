package wh.code.java.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DBHelper {

	private Connection conn;
	private PreparedStatement pst = null;
	private Statement st=null;
	private ResultSet rs = null;

	public DBHelper() {
		this.conn = getConnection();
	}

	private Connection getConnection() {
		String driverClass = null;
		String driverUrl = null;
		String databaseUser = null;
		String databasePassword = null;
		try {
			InputStream fis = this.getClass().getResourceAsStream("/db.properties"); // 加载数据库配置文件到内存中
			Properties p = new Properties();
			p.load(fis);

			driverClass = p.getProperty("driverClass"); // 获取数据库配置文件
			driverUrl = p.getProperty("driverUrl");
			databaseUser = p.getProperty("databaseUser");
			databasePassword = p.getProperty("databasePassword");

			Class.forName(driverClass);
			conn = DriverManager.getConnection(driverUrl, databaseUser, databasePassword);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {

		}
	}

	// insert update delete
	public int executeUpdate(String sql) {
		int count = 0;
		try {
			pst = conn.prepareStatement(sql);
			count = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// get resultset data
	public ResultSet excuteQeury(String sql) {
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return rs;
	}
	
	public List<Object> excuteQuery(String sql) {
		ResultSet rst = null;
		List<Object> resultList = null;
		try {
			pst = conn.prepareStatement(sql);
			rst = pst.executeQuery();
			resultList = DBHelper.convertList(rst);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return resultList;
	}
	
	//execute batch
	public int[] excuteBatch(String[] sqls){
		int[] result=null;
		try {
			boolean autoCommit=conn.getAutoCommit();
			conn.setAutoCommit(false);
			st=conn.createStatement();
			for(String sql:sqls){
				st.addBatch(sql);
			}
			result=st.executeBatch();
			conn.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private static List<Object> convertList(ResultSet rst) throws SQLException {
		List<Object> list = new ArrayList<Object>();
		ResultSetMetaData md = rst.getMetaData();
		int columnCount = md.getColumnCount();
		while (rst.next()) {
			Map<Object,Object> rowData = new HashMap<Object,Object>();
			for (int i = 1; i <= columnCount; i++) {
				rowData.put(md.getColumnName(i), rst.getObject(i));
			}
			list.add(rowData);
		}
		return list;
	}
	
}
