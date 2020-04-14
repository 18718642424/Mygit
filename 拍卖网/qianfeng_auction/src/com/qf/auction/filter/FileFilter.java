package com.qf.auction.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.qf.auction.entity.User;

public class FileFilter implements Filter {

	// 实例化 log4j 记录日志的功能 getClass 把当前文件的反射传过去
	Logger logger = Logger.getLogger(getClass());

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		// 獲取用戶 訪問的文件地址
		// 假設用戶訪問的是 user_login.jsp 那麽這個 userFile 的值就是
		// /qianfeng_auction/user_login.jsp
		// 上面的 /qianfeng_auction 項目 是不能作爲 判斷條件 所以要 截取掉
		String userFile = request.getRequestURI();
		// 抓取用戶的IP
		String userAddress = request.getLocalAddr();
		// 抓取用戶的請求方法
		String userMethod = request.getMethod();
		// 抓取用戶向服務器傳輸的數據
		String userData = request.getQueryString() == null ? "無數據" : request
				.getQueryString();
		// 接下來 使用 logger 的 debug 級別打印日志
		try {
			Integer.parseInt("asd");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error("用戶的訪問的地址:" + userAddress + ",用戶的請求方法是:" + userMethod
					+ ",用戶訪問的文件為:" + userFile + ",用戶傳輸的數據為:" + userData + "");
			logger.error(e.getMessage());
		}
		// 每一個API 在被調用的時候 需要在日志打印 用戶的行爲
		logger.debug("用戶的訪問的地址:" + userAddress + ",用戶的請求方法是:" + userMethod
				+ ",用戶訪問的文件為:" + userFile + ",用戶傳輸的數據為:" + userData + "");
		// indexOf 指的是 搜索這個 字符在 字符串中的位置 搜索不到 返回 -1
		// substring 是字符串分割 第一個參數 指的是 從哪裏開始 割 第二個 參數 指的是 割多少
		// String temp1 = userFile.substring(1, userFile.length());
		// String temp2 = temp1.substring(temp1.indexOf("/"),
		// temp1.length());
		// /qianfeng_auction/user_login.jsp
		// indexOf 后面的两个参数 第一个 表示 搜索 "/" 关键字的位置 ， 第二个参数 表示 从 第一个位置开始
		// 搜索（默认是从0开始搜索）
		userFile.substring(userFile.indexOf("/", 1), userFile.length());
		if (userFile.contains("user_login.jsp")
				|| userFile.contains("number.jsp") || userFile.contains("/css")
				|| userFile.contains("/img") || userFile.contains("/images")
				|| userFile.contains("/js")
				|| userFile.contains("/AuctionLoginServlet")
				|| userFile.contains("/UserLoginServlet")
				|| userFile.contains("/AliPayReturnServlet")
				|| userFile.contains("/register.jsp")
				|| userFile.contains("/UserCheckUserNameServlet")) {
			// 放行
			arg2.doFilter(request, response);
		} else {
			// 判斷用戶是否登錄了 我們的系統
			User user = (User) request.getSession().getAttribute("user");
			if (user == null) {
				// 重定向到登錄
				response.sendRedirect("user_login.jsp");
			} else {
				// 進入到這裏證明 用戶已經登錄了
				// 放行 (訪問 用戶需要 訪問的文件)
				arg2.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

		String temp = "/qianfeng_auction/user_login.jsp";

		System.out.println(temp.indexOf("/", 1));

		System.out.println(temp.substring(temp.indexOf("/", 1), temp.length()));

		temp.substring(temp.indexOf("/", 1), temp.length());

		/*
		 * if (temp.contains("v")) { System.out.println("yes"); }
		 * 
		 * // System.out.println(temp.substring(2, 3));
		 * 
		 * System.out.println(temp.indexOf("項"));
		 */
	}

}
