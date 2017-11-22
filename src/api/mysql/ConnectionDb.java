package api.mysql;

import java.sql.*;

import com.mysql.jdbc.ResultSetMetaData;

public class ConnectionDb {
	/* ���ݿ��û��� */
	private String user = "szc";
	/* ���ݿ����� */
	private String password = "qq3345678";
	/*
	 * ���ݿ��ַ ��ַĬ��Ϊ�����ݿ��ַ
	 */
	public String dbAddress = "123.207.11.102";
	/*
	 * ���ݿ��ַ �˿�Ĭ��Ϊ3306
	 */
	public String port = "3306";
	/* ��Ҫ���ӵ����ݿ� */
	public String dataBase;
	/* statement����ִ��SQL��� */
	private Statement statement;
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement stmt;

	/* ���캯��Ϊ��������ʱ������ֵΪ���ݿ�����Ĭ�����������ݿ� */
	public ConnectionDb(String dataBase) {
		this.dataBase = dataBase;
	}

	/* ���캯��Ϊ��������ʱ����һ������Ϊ���ݿ��ַ���ڶ���Ϊ���ݿ��������ݿ�˿�ΪĬ�϶˿� */
	public ConnectionDb(String dbAddress, String dataBase) {
		this.dbAddress = dbAddress;
		this.dataBase = dataBase;
	}

	/* ���캯��Ϊ��������ʱ����һ������Ϊ���ݿ��ַ���ڶ���Ϊ���ݿ�˿ڣ�������Ϊ���ݿ��� */
	public ConnectionDb(String dbAddress, String port, String dataBase) {
		this.dbAddress = dbAddress;
		this.port = port;
		this.dataBase = dataBase;
	}

	public void setDbAddress(String dbAddress) {
		this.dbAddress = dbAddress;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}

	/* �������ݿ�õ�ִ��SQL����statement */
	public void connection() {
		// ����������
		String driver = "com.mysql.jdbc.Driver";
		// ƴ������url
		String url = "jdbc:mysql://" + dbAddress + ":" + port + "/" + dataBase;
		try {
			// ������������
			Class.forName(driver);
			// �������ݿ�
			this.conn = DriverManager.getConnection(url, user, password);
			// statement����ִ��SQL���
			this.statement = conn.createStatement();
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* prepareStatement */
	public int quary_pre(String sql) {
		int result = 0;
		try {
			this.stmt = conn.prepareStatement(sql);
			int i = stmt.executeUpdate();            //ִ�в������ݲ���������Ӱ�������
			result = i;
		} catch (SQLException e) {
			//e.printStackTrace();
			result  = -1;
		}
		return result;
	}
	
	/* ִ��SQL��亯�� */
	public ResultSet quary(String sql) {
		try {
			this.rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/* �����������Ϊjson��ʽ */
	public String quary_json(String sql){
		try {
			this.rs = statement.executeQuery(sql);
			
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData() ;
			// �еĸ���
	    	int columnCount = rsmd.getColumnCount();
	    	// ����
	    	String columnName = null;
	    	
	    	// json���ƴ��
	    	String result = "[";  
	    	while( rs.next() ) {
	    		result = result + "{";
	    		for(int i = 1;i <= columnCount;i++) {
	    			columnName = rsmd.getColumnName(i); 
	    		    result = result + "\"" + columnName + "\":\"" + rs.getString(columnName)+ "\"";
	    		    // ����������һ����ӡ�����
	    		    if(i != columnCount) 
	    		    	result = result + ",";
	        	}
	    		result = result + "}";
	    		// ����������һ����ӡ�����
	    		if(!rs.isLast())
	    			result = result + ",";
	    	}
	    	result = result + "]";
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* �����ͷ�MySQL���� */
	public void close() throws SQLException {
		this.conn.close();
	}
}
