package com.qf.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.UserBIZ;
import com.qf.auction.bizimpl.UserBIZImpl;
import com.qf.auction.entity.User;
import com.qf.auction.enums.UserStateEnum;

public class UserAddServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserAddServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 调用dopost函数 把 请求体 和 响应体传过去
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		// 注意控制层 开发规范 避免500错误给用户看到 避免空白页
		// 第一步
		// （1）接收用户传输过来的 用户名 密码 和 确认密码 （但是一定一定一定 要注意 前后对应 而且还区分大小写
		// （2）对比input 的 name 属性 是否 和 接收的 KEY 的名字保持一致
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		String repassWord = request.getParameter("repassword");
		// 第二步
		// （1）实例化 业务逻辑层 然后通过接口实现多态
		UserBIZ userBIZ = new UserBIZImpl();
		// （2）去实例化一个 用户 并且 设置用户这个对象 里面所有属性（除了ID之外 ，添加时间 和 修改时间就是服务器当前时间）
//		User user = new User();
//		user.setUserName(userName);
//		user.setPassWord(passWord);
//		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
//		user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		// （3）调用的 添加的函数 并将其 实例化好的用户对象 当做参数 传入到 这个调用函数中
		// （4）调用该函数（3）的返回值 要使用 对应的类型来接收
		String result = UserStateEnum.USER_ADD_FAIL.getValue();
		try {
			result = userBIZ.userAdd(userName, passWord, repassWord);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 第三步
		// 转发到UserListByPageServlet这个文件， 并把 （4） 通过URL
		// 传入到UserListByPageServlet这个文件中
		// 传值的时候要注意 KEY=msg (必须要前端的文件对应上)
		try {
			request.getRequestDispatcher(
					"UserListByPageServlet?msg=" + result + "").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
