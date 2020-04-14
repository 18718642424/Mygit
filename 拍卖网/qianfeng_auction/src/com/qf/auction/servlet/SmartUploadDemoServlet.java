package com.qf.auction.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;

public class SmartUploadDemoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SmartUploadDemoServlet() {
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		// 注意事项： 如果 form 表单 为 enctype = mutipart/form-data 的话 我们无法 通过 request
		// 来获取数据

		// smartupload 框架的使用流程
		// 1:实例化 smartUpload
		SmartUpload smartUpload = new SmartUpload();
		// 2:初始化 smartUpload
		try {
			// 这一步代码执行完毕 意味着 smartUpload 可以抓取到 用户提交的数据 和 用户提交的文件
			smartUpload.initialize(getServletConfig(), request, response);
			// 3: 设置文件提交的大小
			smartUpload.setMaxFileSize(1024 * 1024 * 10);
			// 4: 开启文件上传功能
			smartUpload.upload();
			// 5: 获取用户上传的文件
			File userFile = smartUpload.getFiles().getFile(0);
			// 6: 获取文件名的后缀
			String fileExt = userFile.getFileExt();
			// 7: 获取系统当前时间 精确到毫秒
			// HH （24小时制） hh(12小时制)
			String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
					.format(new Date()) + "." + fileExt;
            // 获取服务器的路径
			String hostPath = request.getSession().getServletContext().getRealPath("upload");
			// 把图片上传到服务器中
			// java.io.File.separator 是跨平台的文件符
			// 文件上传通常有两种方式  一种方式 一把文件存储到服务器上面
			// 还有一种方式 是把文件 存储到数据库中
			// 存储到服务中 性能较高 但是不易于数据的管理
			// 存储到数据中 性能消耗较大 但是易于数据的管理
			// 通常情况下用户相关的文件 存储到服务器中 （例：用户头像）
			// 系统相关的文件 存储到数据库中 (例： 比如工作流相关的文件)
			userFile.saveAs(hostPath + java.io.File.separator + fileName);
		} catch (Exception e) {
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
