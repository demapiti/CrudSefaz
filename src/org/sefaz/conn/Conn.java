package org.sefaz.conn;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class Conn {
	private static BasicDataSource ds = null;
	
	private static DataSource getDataSource() {
		if(ds == null) {
			ds = new BasicDataSource();
			ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
			ds.setUsername("root");
			ds.setPassword("");
			ds.setUrl("jdbc:mysql://localhost/sefaz?useTimezone=true&serverTimezone=UTC&useSSL=false&rewriteBatchedStatements=true&relaxAutoCommit=true");
			ds.setInitialSize(20);
			ds.setMaxIdle(15);
			ds.setMaxTotal(20);
			ds.setMaxWaitMillis(5000);
		}
		return ds;
	}
	
	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}
}
