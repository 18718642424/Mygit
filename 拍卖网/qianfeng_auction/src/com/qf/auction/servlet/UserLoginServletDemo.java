//package com.qf.auction.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class UserLoginServletDemo extends HttpServlet {
//
//	/**
//	 * Constructor of the object.
//	 */
//	public UserLoginServletDemo() {
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
//        // 设置响应头
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		// 向响应报文中写数据
//		out.println("<!DOCTYPE HTML>");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>Login Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.println("<form action='AuctionLoginServlet' method='post'>");
//		// 下面这两句 是 向 请求报文中 写数据 键值的格式
//		out.println("  用户名：<input name='username' value='test'/> </br>");
//		out.println("  密码：<input name='password' type='password' value='123'/> </br>");
//		out.println("<button>登录</button>");
//		out.println("</form>");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		
//		
////		doPost(request, response);
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
//	public void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
//		out.println("<HTML>");
//		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
//		out.println("  <BODY>");
//		out.print("    This is ");
//		out.print(this.getClass());
//		out.println(", using the POST method");
//		out.println("  </BODY>");
//		out.println("</HTML>");
//		out.flush();
//		out.close();
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
