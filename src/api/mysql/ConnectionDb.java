package api.mysql;

import java.sql.*;

import com.mysql.jdbc.ResultSetMetaData;

public class ConnectionDb {
	/* 数据库用户名 */
	private String user = "szc";
	/* 数据库密码 */
	private String password = "qq3345678";
	/*
	 * 数据库地址 地址默认为主数据库地址
	 */
	public String dbAddress = "123.207.11.102";
	/*
	 * 数据库地址 端口默认为3306
	 */
	public String port = "3306";
	/* 所要连接的数据库 */
	public String dataBase;
	/* statement用来执行SQL语句 */
	private Statement statement;
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement stmt;

	/* 构造函数为单个参数时，参数值为数据库名，默认连接主数据库 */
	public ConnectionDb(String dataBase) {
		this.dataBase = dataBase;
	}

	/* 构造函数为两个参数时，第一个参数为数据库地址，第二个为数据库名，数据库端口为默认端口 */
	public ConnectionDb(String dbAddress, String dataBase) {
		this.dbAddress = dbAddress;
		this.dataBase = dataBase;
	}

	/* 构造函数为三个参数时，第一个参数为数据库地址，第二个为数据库端口，第三个为数据库名 */
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

	/* 连接数据库得到执行SQL语句的statement */
	public void connection() {
		// 驱动程序名
		String driver = "com.mysql.jdbc.Driver";
		// 拼接连接url
		String url = "jdbc:mysql://" + dbAddress + ":" + port + "/" + dataBase;
		try {
			// 加载驱动程序
			Class.forName(driver);
			// 连续数据库
			this.conn = DriverManager.getConnection(url, user, password);
			// statement用来执行SQL语句
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
			int i = stmt.executeUpdate();            //执行插入数据操作，返回影响的行数
			result = i;
		} catch (SQLException e) {
			//e.printStackTrace();
			result  = -1;
		}
		return result;
	}
	
	/* 执行SQL语句函数 */
	public ResultSet quary(String sql) {
		try {
			this.rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/* 结果返回数据为json格式 */
	public String quary_json(String sql){
		try {
			this.rs = statement.executeQuery(sql);
			
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData() ;
			// 列的个数
	    	int columnCount = rsmd.getColumnCount();
	    	// 列名
	    	String columnName = null;
	    	
	    	// json结果拼接
	    	String result = "[";  
	    	while( rs.next() ) {
	    		result = result + "{";
	    		for(int i = 1;i <= columnCount;i++) {
	    			columnName = rsmd.getColumnName(i); 
	    		    result = result + "\"" + columnName + "\":\"" + rs.getString(columnName)+ "\"";
	    		    // 如果不是最后一列添加“，”
	    		    if(i != columnCount) 
	    		    	result = result + ",";
	        	}
	    		result = result + "}";
	    		// 如果不是最后一行添加“，”
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

	/* 主动释放MySQL连接 */
	public void close() throws SQLException {
		this.conn.close();
	}
}
