package com.qf.auction.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

// properties 属性文件 可以达到 系统参数动态化的一个效果 
public class JDBCUtil {

	private static String url;
	private static String username;
	private static String password;
	private static String driver;

	// 下面这行代码是静态代码块
	// 文件在初始化的时候 第一 加载的就是它 而且只加载一次
	// 它的优先级比 构造函数 要高
	static {
		try {
			// 获取jdbc.properties 这个文件的输入流
			InputStream inputStream = JDBCUtil.class
					.getResourceAsStream("/jdbc.properties");
			Properties properties = new Properties();
			// 通过proerties 的 load 函数 来 实例化 proerties
			// 这句话 一旦执行过去 proerties 就被实例化（ 它 已经 知道 自己 有哪些 KEY 哪些 value）
			properties.load(inputStream);
			// getProperty 这个函数 表示 根据KEY 获取对应的 value
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			driver = properties.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 封装一个获取链接的函数
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// 查询的话用这个函数 进行关闭
	public static void close(ResultSet resultSet,
			PreparedStatement preparedStatement, Connection connection) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 增删改 没有RESULT 所以对close这个函数 进行方法重载
	public static void close(PreparedStatement preparedStatement,
			Connection connection) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
