package com.babi.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 数据库处理基类
 * 
 * @author	LiWenhui
 * @Date	2018.03.19
 * @author	GuoLiangyun
 * @Date	2018.03.23
 */
public class BaseDAO {

	private Connection con = null;
	private Statement st = null;
	private PreparedStatement ps = null;
	private CallableStatement sc = null;

	private String mysqldriver = null;
	private String mysqlurl = null;
	private String username = null;
	private String password = null;

	/*
	 * 构造方法
	 */
	public BaseDAO(String driver, String url, String user, String pswd) {
		mysqldriver = driver;
		mysqlurl = url;
		username = user;
		password = pswd;
	}

	/**
	 * 获得连接对象
	 * 
	 * @return
	 */
	protected Connection getConnection() throws Exception {
		try {
			Class.forName(mysqldriver);
			con = DriverManager.getConnection(mysqlurl, username, password);
		} catch (Exception e) {
			throw e;
		}
		return con;

	}

	/**
	 * 声明语句对象
	 * 
	 * @return Statement
	 */
	private Statement getStatement() throws Exception {
		try {
			st = this.getConnection().createStatement();
		} catch (Exception e) {
			throw e;
		}

		return st;
	}

	/**
	 * 声明语句对象[预编译语句]
	 * 
	 * @return PreparedStatement
	 */
	protected PreparedStatement getPreparedStatement(String sql) throws Exception {
		try {
			ps = this.getConnection().prepareStatement(sql);
		} catch (Exception e) {
			throw e;
		}

		return ps;
	}

	/**
	 * 执行增加 删除 更改 sql语句
	 * 
	 * @param sql
	 * @return boolean
	 */
	public boolean executeByBoolean(String sql) throws Exception {
		boolean flag = true;
		try {
			flag = this.getStatement().execute(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.closeConnection();
		}
		return flag;
	}

	/**
	 * 执行增加 删除 更改 sql语句
	 * 
	 * @param sql
	 * @return int
	 */
	public int executeByInt(String sql) throws Exception {
		int result = -1;
		try {
			result = this.getStatement().executeUpdate(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			this.closeConnection();
		}
		return result;
	}

	/**
	 * 执行增加 删除 修改 sql语句 [预编译语句]
	 * 
	 * @param sql
	 * @param obj
	 * @return boolean
	 */
	public boolean executePreparedStatementByBoolean(String sql, Object[] obj) throws Exception {

		boolean flag = true;
		try {
			this.getPreparedStatement(sql);
			this.openTransaction();
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(1 + i, obj[i]);
			}
			flag = ps.execute();
			this.commit();
		} catch (Exception e) {
			this.rollback();
			throw e;
		} finally {
			this.closeTransaction();
			this.closeConnection();
		}

		return flag;
	}

	/**
	 * 执行增加 删除 修改 sql语句 [预编译语句]
	 * 
	 * @param sql
	 * @param obj
	 * @return boolean
	 */
	public int executePreparedStatementByInt(String sql, Object[] obj) throws Exception {

		int result = -1;

		try {
			this.getPreparedStatement(sql);
			this.openTransaction();
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(1 + i, obj[i]);
			}
			result = ps.executeUpdate();
			this.commit();
		} catch (Exception e) {
			this.rollback();
			throw e;
		} finally {
			this.closeTransaction();
			this.closeConnection();
		}

		return result;
	}

	/**
	 * 执行查询语句 [关闭连接]
	 * 
	 * @param sql
	 * @return ResultSet
	 */
	public ResultSet exectueQuery(String sql) throws Exception {
		ResultSet rs = null;
		try {
			rs = this.getStatement().executeQuery(sql);
		} catch (Exception e) {
			throw e;
		}
		// finally {
		// this.closeConnection();
		// }
		return rs;
	}

	/**
	 * 执行查询语句 [关闭连接]
	 * 
	 * @param sql
	 * @return ResultSet
	 */
	public ResultSet exectueQuery(String sql, Object[] obj) throws Exception {
		ResultSet rs = null;
		try {
			this.getPreparedStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			rs = ps.executeQuery();
		} catch (Exception e) {
			throw e;
		}
		return rs;
	}

	/**
	 * 事务
	 * 
	 * @param sqls
	 *            多条sql语句
	 * @return 受影响行数
	 */
	public int executeSqls(String[] sqls) throws Exception {
		int resule = -1;
		try {
			this.getStatement();
			con.setAutoCommit(false);
			for (int i = 0, num = 0; i < sqls.length; i++, num = 0) {
				num = st.executeUpdate(sqls[i]);
				if (num == 0) {
					con.rollback();
				}
				resule += num;
			}
			con.commit();

		} catch (Exception e) {
			try {
				con.rollback();
			} catch (Exception e2) {
				throw e2;
			}
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (Exception e2) {
				throw e2;
			}
			this.closeConnection();
		}
		return resule;
	}

	void openTransaction() throws Exception {
		try {
			if (con != null) {
				con.setAutoCommit(false);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	void rollback() throws Exception {
		try {
			if (con != null) {
				con.rollback();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	void commit() throws Exception {
		try {
			if (con != null) {
				con.commit();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	void closeTransaction() throws Exception {
		try {
			con.setAutoCommit(true);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 关闭所有声明语句对象和数据库连接
	 */
	public void closeConnection() throws Exception {
		try {
			if (st != null) {
				st.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (sc != null) {
				sc.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
