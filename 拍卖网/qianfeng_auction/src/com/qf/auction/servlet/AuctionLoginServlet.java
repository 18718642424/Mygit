//package com.qf.auction.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.qf.auction.entity.User;
//
//public class AuctionLoginServlet extends HttpServlet {
//
//	private static final Exception Exception = null;
//
//	/**
//	 * Constructor of the object.
//	 */
//	public AuctionLoginServlet() {
//		super();
//	}
//
//	/**
//	 * Destruction of the servlet. <br>
//	 */
//	public void destroy() {
//		super.destroy(); // Just puts "destroy" string in log
//		// Put your code here
//	}
//
//	/**
//	 * The doGet method of the servlet. <br>
//	 * 
//	 * This method is called when a form has its tag value method equals to get.
//	 * 
//	 * @param request
//	 *            the request send by the client to the server
//	 * @param response
//	 *            the response send by the server to the client
//	 * @throws ServletException
//	 *             if an error occurred
//	 * @throws IOException
//	 *             if an error occurred
//	 */
//	public void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// 接收用户请求
//		// getParameter 通过用户传输过来的KEY 抓取对应的值
//		String userName = request.getParameter("username");
//		String age = request.getParameter("age");
//		// 响应用户的请求
//		// 向 响应报文中写数据
//		response.getWriter().print("username:" + userName + "   age:" + age);
//	}
//
//	/**
//	 * The doPost method of the servlet. <br>
//	 * 
//	 * This method is called when a form has its tag value method equals to
//	 * post.
//	 * 
//	 * @param request
//	 *            the request send by the client to the server
//	 * @param response
//	 *            the response send by the server to the client
//	 * @throws ServletException
//	 *             if an error occurred
//	 * @throws IOException
//	 *             if an error occurred
//	 */
//	//
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// 接收用户请求
//		String userName = request.getParameter("username");
//		String passWord = request.getParameter("password");
//		User user = null;
//		// 判断用户请求参数的 合法性
//		// 尤其是做WEB开发的小伙伴 注意一个问题 判断用户参数合法性的一个 必经之路（必须有的判断）就是 要
//		// 判断这个参数 是否为NULL 是否为空字符串""
//		// 为啥判断这两个呢？
//		// NULL这种场景是 用户压根就没传这个参数过来
//		// ""空字符 这种场景是 用户没有填写数据
//		try {
//			if (userName == null || userName.equals("")) {
//				// 如果代码进入到这里说明 用户名不合法
//				response.setContentType("text/html;charset=utf-8");
//				PrintWriter out = response.getWriter();
//				// 向响应报文中写数据
//				out.println("<!DOCTYPE HTML>");
//				out.println("<HTML>");
//				out.println("  <HEAD><TITLE>Login Servlet</TITLE></HEAD>");
//				out.println("  <BODY>");
//				out.println("<div style='color:red'>用户名不能为空</div>");
//				out.println("  </BODY>");
//				out.println("</HTML>");
//				throw Exception;
//			} else if (passWord == null || passWord.equals("")) {
//				// 如果进入到这里说明 密码不合法
//				response.setContentType("text/html;charset=utf-8");
//				PrintWriter out = response.getWriter();
//				// 向响应报文中写数据
//				out.println("<!DOCTYPE HTML>");
//				out.println("<HTML>");
//				out.println("  <HEAD><TITLE>Login Servlet</TITLE></HEAD>");
//				out.println("  <BODY>");
//				out.println("<div style='color:red'>密码不能为空</div>");
//				out.println("  </BODY>");
//				out.println("</HTML>");
//				throw Exception;
//			}
//			// 如果代码能够进入到这里说明 用户名和密码输入正确
//			Connection connection = null;
//			PreparedStatement preparedStatement = null;
//			Statement statement = null;
//			ResultSet resultSet = null;
//			// arraylist 比较适合做数据迭代
//			// 允许存入 重复的对象
//			try {
//				// 1: 加载驱动
//				// 自动修复 快捷键是 ctrl + 1
//				Class.forName("com.mysql.jdbc.Driver");
//				// 2:创建数据库连接（实例化connection）
//				// 执行一个的函数的根据目的 就是为了 实例化 返回值
//				connection = DriverManager.getConnection(
//						"jdbc:mysql://localhost:3306/qianfeng", "root", "root");
//				// 3:实例化数据库实例 并执行SQL语句
//				// preparedStatement = connection
//				// .prepareStatement("select * from user where id = ? and isdelete = 1");
//				// preparedStatement.setInt(1, 1);
//				// preparedStatement = connection
//				// .prepareStatement("select * from user where userName=? and passWord=?");
//				statement = connection.createStatement();
//				resultSet = statement
//						.executeQuery("select * from user where userName='"
//								+ userName + "' and passWord='" + passWord
//								+ "'");
//				// preparedStatement.setString(1, userName);
//				// preparedStatement.setString(2, passWord);
//				// 4：实例化结果集
//				// resultSet = preparedStatement.executeQuery();
//				// 5: 迭代结果集
//				// .next() 函数指的 有数据就返回 TRUE 没数据 就返回 FALSE
//				while (resultSet.next()) {
//					// 如果代码能够进入到这里说明 查到了 用户
//					user = new User();
//					// 持久层开发 有一个规范 只要是操作到 数据库的字段 就改成大写 目的是为了提高代码的可读性
//					user.setId(resultSet.getInt("ID"));
//					user.setUserName(resultSet.getString("USERNAME"));
//					user.setPassWord(resultSet.getString("PASSWORD"));
//					user.setUpdateTime(resultSet.getTimestamp("UPDATETIME"));
//					user.setCreateTime(resultSet.getTimestamp("CREATETIME"));
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				// alt + shift +z 自动try cath (使用的前提是 要把代码 选中)
//				// 不管代码 是否是 一行代码 都不要不写 { }
//				try {
//					if (resultSet != null) {
//						resultSet.close();
//					}
//					if (preparedStatement != null) {
//						preparedStatement.close();
//					}
//					if (connection != null) {
//						connection.close();
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (user == null) {
//				// 如果进入到这里說明 用戶或者密碼輸入的不正確（數據庫中查不到對應的數據）
//				response.setContentType("text/html;charset=utf-8");
//				PrintWriter out = response.getWriter();
//				// 向响应报文中写数据
//				out.println("<!DOCTYPE HTML>");
//				out.println("<HTML>");
//				out.println("  <HEAD><TITLE>Login Servlet</TITLE></HEAD>");
//				out.println("  <BODY>");
//				out.println("<div style='color:red'>用户或密码错误</div>");
//				out.println("  </BODY>");
//				out.println("</HTML>");
//			} else {
//				// 如果能进入到这里说明登录成功
//				response.setContentType("text/html;charset=utf-8");
//				PrintWriter out = response.getWriter();
//				// 向响应报文中写数据
//				out.println("<!DOCTYPE HTML>");
//				out.println("<HTML>");
//				out.println("  <HEAD><TITLE>Login Servlet</TITLE></HEAD>");
//				out.println("  <BODY>");
//				out.println("<div style='color:red'>恭喜您登錄成功</div>");
//				out.println("  </BODY>");
//				out.println("</HTML>");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * Initialization of the servlet. <br>
//	 * 
//	 * @throws ServletException
//	 *             if an error occurs
//	 */
//	public void init() throws ServletException {
//		// Put your code here
//	}
//
//}
