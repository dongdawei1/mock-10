package com.mock.utils.forcheck.mysqlutils;

import static com.mock.utils.forcheck.PropertiesUtil.getProperty;
import static com.mock.constant.Constants.TESTNG__ENVIRONMENT;
import static java.lang.String.format;
import static java.lang.System.gc;
import static java.util.Arrays.asList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.asserts.SoftAssert;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MysqlUtils {
	
	//查询时的表名
	public static String tableName;
	/**
	 * testNg多套测试环境配置多个库名，一个表名，这里和springBoot非同一个表
	 */
	private static final Connection CONNECTION_PROJECT = getConnection(
			getProperty(TESTNG__ENVIRONMENT + "_mysql_host_port"), getProperty(TESTNG__ENVIRONMENT + "_mysql_username"),
			getProperty(TESTNG__ENVIRONMENT + "_mysql_password"), getProperty(TESTNG__ENVIRONMENT + "_dbName"));

	/**
	 * 其他数据库连接
	 */

//    private static final Connection CONNECTION = getConnection(
//        getProperty("uc_mysql_host_port"),
//        getProperty("uc_mysql_username"),
//        getProperty("uc_mysql_password"),
//        getProperty("uc_dbName"));
	/**
	 * 关闭数据库连接使用
	 */
	private static Connection[] connections = {
			// CONNECTION,
			CONNECTION_PROJECT };

	/**
	 * for one mysql db, use the same statement for all "update" operations.
	 */
	// private static final Statement STATEMENT_FOR_UPDATE =
	// getStatement(CONNECTION_UC);
	private static final Statement STATEMENT_FOR_UPDATE_PROJECT = getStatement(CONNECTION_PROJECT);
	private static List<Statement> statementsForUpdate = asList(
			// STATEMENT_FOR_UPDATE,
			STATEMENT_FOR_UPDATE_PROJECT);

	private static List<Statement> statementsForQuery = new ArrayList<>();
	private static Statement statementForQuery;

	/*********************************
	 * others
	 *******************************************/

	public static ResultSet resultSet;
	public static int rowCount = -1;

	public static SoftAssert softAssert = new SoftAssert();

	/**
	 * 创建数据库连接
	 */
	private static Connection getConnection(String hostPort, String username, String password, String dbName) {

		try {

			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(
					format("jdbc:mysql://%s/%s?useUnicode=true&characterEncoding=utf8", hostPort, dbName), username,
					password);

		} catch (Exception e) {

			throw new RuntimeException(e);

		}

	}

	/**
	 * 
	 * 实例化Statement对象
	 */
	private static Statement getStatement(Connection connection) {

		try {
			return connection.createStatement();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

//    public static ResultSet sqlQuery(String sqlFormat, Object... sqlFormatArgs) {
//        return sqlQuery(CONNECTION, sqlFormat, sqlFormatArgs);
//    }
//  public static void sqlUpdate(String sqlFormat, Object... sqlFormatArgs) {
//  sqlUpdate(STATEMENT_FOR_UPDATE, sqlFormat, sqlFormatArgs);
//}
	/**
	 * 
	 * 调用方法使用静态导入方法 入参格式'%s' 字符串 '%d' 数字 sqlQueryProject("select * from
	 * billing_record where string_字段='%s' ", "字段值");不检查总行数
	 */

	public static ResultSet sqlQueryProjectNoCheckRow(String sqlFormat, Boolean noCheckRow, Object... sqlFormatArgs) {
		return sqlQuery(CONNECTION_PROJECT, sqlFormat, noCheckRow,sqlFormatArgs);
	}
	/**
	 * 
	 * 查询后检查总行数，仅支持总行数===1
	 */
	public static ResultSet sqlQueryProjectCheckRow(String sqlFormat,  Object... sqlFormatArgs) {
		return sqlQuery(CONNECTION_PROJECT, sqlFormat, true,sqlFormatArgs);
	}
	/**
	 * 更新数据共用一个
	 **/
	public static void sqlUpdateBilling(String sqlFormat, Object... sqlFormatArgs) {
		sqlUpdate(STATEMENT_FOR_UPDATE_PROJECT, sqlFormat, sqlFormatArgs);
	}

	private static void sqlUpdate(Statement statement, String sqlFormat, Object... sqlFormatArgs) {
		String sql = format(sqlFormat, sqlFormatArgs);
		log.info("executing sql update: [{}]", sql);
		try {
			rowCount = statement.executeUpdate(sql);
			log.info("rowCount: [{}]", rowCount);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private static ResultSet sqlQuery(Connection connection, String sqlFormat, Boolean checkRow, Object... sqlFormatArgs) {

		String sql = format(sqlFormat, sqlFormatArgs);

		log.info("executing sql query: [{}]", sql);

		// get a statement for this query
		statementForQuery = getStatement(connection);
		statementsForQuery.add(statementForQuery);

		try {
			resultSet = statementForQuery.executeQuery(sql);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

      
        tableName = sql.toLowerCase().split(" from ")[1].split(" ")[0];
        if(checkRow) {
        	checkRow(1);
        }
		return resultSet;

	}

	/**
	 * 只适用于只落库一条数据的检查
	 * **/
	public static void checkRow(int row) {
		   int a=0;
           try {
			while(resultSet.next()){
			       a++;
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void closeJdbcConnections() {
		for (Connection connection : connections) {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void closeJdbcStatements() {
		statementsForUpdate.addAll(statementsForQuery);
		for (Statement statement : statementsForUpdate) {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void closeJdbcStatementsForQueryThenGc(ResultSet... excludings) {

		try {

			for (Statement statementForQuery : statementsForQuery) {
				if (statementForQuery != null && !asList(excludings).contains(statementForQuery.getResultSet())) {
					statementForQuery.close();
				}
			}

			statementsForQuery = new ArrayList<>(); // for gc

			for (ResultSet resultSet : excludings) {
				statementsForQuery.add(resultSet.getStatement());
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		gc();

	}

}
