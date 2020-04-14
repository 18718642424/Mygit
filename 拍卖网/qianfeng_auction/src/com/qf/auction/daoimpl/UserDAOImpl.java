package com.qf.auction.daoimpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.qf.auction.dao.UserDAO;
import com.qf.auction.entity.User;
import com.qf.auction.util.JDBCUtil;

public class UserDAOImpl implements UserDAO {

	@Override
	public User userLogin(String userName, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			// 1: 加载驱动
			// 自动修复 快捷键是 ctrl + 1
			// 2:创建数据库连接（实例化connection）
			// 执行一个的函数的根据目的 就是为了 实例化 返回值
			// 3:实例化数据库实例 并执行SQL语句
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select * from user where userName=? and passWord=? and isdelete = 1");
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			// 4：实例化结果集
			resultSet = preparedStatement.executeQuery();
			// 5: 迭代结果集
			// .next() 函数指的 有数据就返回 TRUE 没数据 就返回 FALSE
			while (resultSet.next()) {
				// 如果代码能够进入到这里说明 查到了 用户
				user = new User();
				// 持久层开发 有一个规范 只要是操作到 数据库的字段 就改成大写 目的是为了提高代码的可读性
				user.setId(resultSet.getInt("ID"));
				user.setUserName(resultSet.getString("USERNAME"));
				user.setPassWord(resultSet.getString("PASSWORD"));
				user.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				user.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				user.setUserIsAdmin(resultSet.getBoolean("userisadmin"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// alt + shift +z 自动try cath (使用的前提是 要把代码 选中)
			// 不管代码 是否是 一行代码 都不要不写 { }
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		// !!!!!!!!!!!!!!!!!!!!
		return user;
	}

	@Override
	public int userRegister(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("insert into user (username,password,createtime,updatetime,phonenumber,email) values (?,?,?,?,?,?) ");
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassWord());
			preparedStatement.setTimestamp(3,
					new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(4,
					new Timestamp(System.currentTimeMillis()));
			preparedStatement.setString(5, user.getPhoneNumber());
			preparedStatement.setString(6, user.getEmail());
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public Set<User> findUserByPage(BigDecimal pageIndex, BigDecimal pageNum) {
		return null;
	}

	@Override
	public Set<User> getAll() {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Set<User> userSet = new HashSet<User>();
		try {
			// 1: 加载驱动
			// 自动修复 快捷键是 ctrl + 1
			Class.forName("com.mysql.jdbc.Driver");
			// 2:创建数据库连接（实例化connection）
			// 执行一个的函数的根据目的 就是为了 实例化 返回值
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/qianfeng", "root", "root");
			// 3:实例化数据库实例 并执行SQL语句
			preparedStatement = connection
					.prepareStatement("select * from user where isdelete = 1 ");
			// 4：实例化结果集
			resultSet = preparedStatement.executeQuery();
			// 5: 迭代结果集
			// .next() 函数指的 有数据就返回 TRUE 没数据 就返回 FALSE
			while (resultSet.next()) {
				// 如果代码能够进入到这里说明 查到了 用户
				// 持久层开发 有一个规范 只要是操作到 数据库的字段 就改成大写 目的是为了提高代码的可读性
				User user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setUserName(resultSet.getString("USERNAME"));
				user.setPassWord(resultSet.getString("PASSWORD"));
				user.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				user.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				userSet.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// alt + shift +z 自动try cath (使用的前提是 要把代码 选中)
			// 不管代码 是否是 一行代码 都不要不写 { }
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
		// !!!!!!!!!!!!!!!!!!!!
		return userSet;
	}

	@Override
	public List<User> findUserListByPage(BigDecimal pageIndex,
			BigDecimal pageSize) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> userList = new ArrayList<User>();
		try {
			// 1: 加载驱动
			// 自动修复 快捷键是 ctrl + 1
			Class.forName("com.mysql.jdbc.Driver");
			// 2:创建数据库连接（实例化connection）
			// 执行一个的函数的根据目的 就是为了 实例化 返回值
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/qianfeng", "root", "root");
			// 3:实例化数据库实例 并执行SQL语句
			preparedStatement = connection
					.prepareStatement("select * from user where isdelete = 1 limit ?,?");
			preparedStatement.setBigDecimal(1, pageIndex);
			preparedStatement.setBigDecimal(2, pageSize);
			// 4：实例化结果集
			resultSet = preparedStatement.executeQuery();
			// 5: 迭代结果集
			// .next() 函数指的 有数据就返回 TRUE 没数据 就返回 FALSE
			while (resultSet.next()) {
				// 如果代码能够进入到这里说明 查到了 用户
				// 持久层开发 有一个规范 只要是操作到 数据库的字段 就改成大写 目的是为了提高代码的可读性
				User user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setUserName(resultSet.getString("USERNAME"));
				user.setPassWord(resultSet.getString("PASSWORD"));
				user.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				user.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// alt + shift +z 自动try cath (使用的前提是 要把代码 选中)
			// 不管代码 是否是 一行代码 都不要不写 { }
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
		// !!!!!!!!!!!!!!!!!!!!
		return userList;
	}

	@Override
	public BigDecimal getAllCount() {

		// order by group by

		// select(7) * from(1) user where(2) id = (select(5) id from (3) user
		// where (4) username = '小强') group by(6) username order by(8) age desc

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		BigDecimal totalCount = new BigDecimal(0);
		try {
			// 1: 加载驱动
			// 自动修复 快捷键是 ctrl + 1
			Class.forName("com.mysql.jdbc.Driver");
			// 2:创建数据库连接（实例化connection）
			// 执行一个的函数的根据目的 就是为了 实例化 返回值
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/qianfeng", "root", "root");
			// 3:实例化数据库实例 并执行SQL语句
			preparedStatement = connection
					.prepareStatement("select count(*) from user where isdelete = 1 ");
			resultSet = preparedStatement.executeQuery();
			// 4：实例化结果集
			while (resultSet.next()) {
				// 抓取第一个字段
				totalCount = resultSet.getBigDecimal(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// alt + shift +z 自动try cath (使用的前提是 要把代码 选中)
			// 不管代码 是否是 一行代码 都不要不写 { }
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
		// !!!!!!!!!!!!!!!!!!!!
		return totalCount;
	}

	@Override
	public int addUser(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			// 1: 加载驱动
			// 自动修复 快捷键是 ctrl + 1
			Class.forName("com.mysql.jdbc.Driver");
			// 2:创建数据库连接（实例化connection）
			// 执行一个的函数的根据目的 就是为了 实例化 返回值
			connection = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/qianfeng?useUnicode=true&characterEncoding=utf-8",
							"root", "root");
			// 3:实例化数据库实例 并执行SQL语句
			preparedStatement = connection
					.prepareStatement("insert into user (username,password,createtime,updatetime) values (?,?,?,?) ");
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassWord());
			preparedStatement.setTimestamp(3, user.getCreateTime());
			preparedStatement.setTimestamp(4, user.getUpdateTime());
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// alt + shift +z 自动try cath (使用的前提是 要把代码 选中)
			try {
				// 不管代码 是否是 一行代码 都不要不写 { }
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
		// !!!!!!!!!!!!!!!!!!!!
		return executeCount;
	}

	@Override
	public int updateUser(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			// 1: 加载驱动
			// 自动修复 快捷键是 ctrl + 1
			Class.forName("com.mysql.jdbc.Driver");
			// 2:创建数据库连接（实例化connection）
			// 执行一个的函数的根据目的 就是为了 实例化 返回值
			connection = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/qianfeng?useUnicode=true&characterEncoding=utf-8",
							"root", "root");
			// 3:实例化数据库实例 并执行SQL语句
			preparedStatement = connection
					.prepareStatement("update user set username=?,password=?,updateTime=? where id =?");
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassWord());
			preparedStatement.setTimestamp(3, user.getUpdateTime());
			preparedStatement.setInt(4, user.getId());
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// alt + shift +z 自动try cath (使用的前提是 要把代码 选中)
			try {
				// 不管代码 是否是 一行代码 都不要不写 { }
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
		// !!!!!!!!!!!!!!!!!!!!
		return executeCount;
	}

	@Override
	public int userDel(int userid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			// 1: 加载驱动
			// 自动修复 快捷键是 ctrl + 1
			Class.forName("com.mysql.jdbc.Driver");
			// 2:创建数据库连接（实例化connection）
			// 执行一个的函数的根据目的 就是为了 实例化 返回值
			connection = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/qianfeng?useUnicode=true&characterEncoding=utf-8",
							"root", "root");
			// 3:实例化数据库实例 并执行SQL语句
			preparedStatement = connection
					.prepareStatement("update user set isdelete = 0 where id =?");
			preparedStatement.setInt(1, userid);
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// alt + shift +z 自动try cath (使用的前提是 要把代码 选中)
			try {
				// 不管代码 是否是 一行代码 都不要不写 { }
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
		// !!!!!!!!!!!!!!!!!!!!
		return executeCount;
	}

	@Override
	public List<User> searchUserByName(String username) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<User> userList = new ArrayList<User>();
		try {
			// 1: 加载驱动
			// 自动修复 快捷键是 ctrl + 1
			// 2:创建数据库连接（实例化connection）
			// 执行一个的函数的根据目的 就是为了 实例化 返回值
			connection = JDBCUtil.getConnection();
			// 3:实例化数据库实例 并执行SQL语句
			preparedStatement = connection
					.prepareStatement("select * from user where username like ? and isdelete = 1 ");
			// 以下为 后模糊 但是不要使用前模糊 %username (会引起全表搜索)
			preparedStatement.setString(1, username + "%");
			// 4：实例化结果集
			resultSet = preparedStatement.executeQuery();
			// 5: 迭代结果集
			// .next() 函数指的 有数据就返回 TRUE 没数据 就返回 FALSE
			while (resultSet.next()) {
				// 如果代码能够进入到这里说明 查到了 用户
				// 持久层开发 有一个规范 只要是操作到 数据库的字段 就改成大写 目的是为了提高代码的可读性
				User user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setUserName(resultSet.getString("USERNAME"));
				user.setPassWord(resultSet.getString("PASSWORD"));
				user.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				user.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// alt + shift +z 自动try cath (使用的前提是 要把代码 选中)
			// 不管代码 是否是 一行代码 都不要不写 { }
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		// !!!!!!!!!!!!!!!!!!!!
		return userList;
	}

	@Override
	public User findUserListByAuctionId(int auctionId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection
					.prepareStatement("select  * from user as us "
							+ "left join auctionrecord as atc on us.id = atc.USERID "
							+ "where  atc.AUCTIONID = ? "
							+ "order  by atc.AUCTIONPRICE  desc " + "limit 0,1");
			preparedStatement.setInt(1, auctionId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("ID"));
				user.setUserName(resultSet.getString("USERNAME"));
				user.setPassWord(resultSet.getString("PASSWORD"));
				user.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
				user.setCreateTime(resultSet.getTimestamp("CREATETIME"));
				user.setUserIsAdmin(resultSet.getBoolean("userisadmin"));
				user.setEmail(resultSet.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return user;
	}

	@Override
	public boolean checkUserNameIsExist(String userName) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isExist = false;
		try {
              connection = JDBCUtil.getConnection();
              // 查询字段 越多 越会影响 查询 速度  所以 才会有 投影查询这个概念（用到哪些字段查询 哪些字段）
              preparedStatement = connection.prepareStatement("select id from user where username = ?");
              preparedStatement.setString(1, userName);
              resultSet = preparedStatement.executeQuery();
              isExist = resultSet.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCUtil.close(resultSet, preparedStatement, connection);
		}
		return isExist;
	}
}
