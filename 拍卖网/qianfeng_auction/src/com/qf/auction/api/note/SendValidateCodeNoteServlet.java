package com.qf.auction.api.note;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.NoteBIZ;
import com.qf.auction.bizimpl.NoteBIZImpl;
import com.qf.auction.entity.Note;

import net.sf.json.JSONObject;

public class SendValidateCodeNoteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String phone = request.getParameter("phoneNumber");
		String host = "http://x9xvvi.natappfree.cc/qianfeng_auction/UserSendNote?phoneNumber="+phone+"";
		boolean addResult = false;
		try {
			// 网络编程  第一件事  是 实例化 一个 URL   调用URL 构造函数  传入  请求 文件地址
			URL url = new URL(host);
			// 通过URL 实例化一个 urlconnection
			URLConnection urlConnection = url.openConnection();
			// 由于我们需要发送一条HTTP请求 所以 这里 我们需要一个 httpurlconnection 
			HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
			// 设置请求方法  POST 和 GET 需要大写
			httpURLConnection.setRequestMethod("POST");
			// 通过设置  请求属性 来模拟  谷歌浏览器行为
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3237.0 Safari/537.36");
			// 设置请求过时时间 为3秒
			httpURLConnection.setReadTimeout(3000);
			// 设置连接超时时间
			httpURLConnection.setConnectTimeout(3000);
			// 设置超时后 自动 重定向
			httpURLConnection.setInstanceFollowRedirects(true);
			// 设置抓取响应报文中的数据
			httpURLConnection.setDoOutput(true);
			// 建立连接  这个行为  就像 你在浏览器 输入地址  然后 敲回车的行为
			httpURLConnection.connect();
			// 获取输入流
			InputStream inputStream = httpURLConnection.getInputStream();
            // bufferReader 可以使 读取流的操作  更快
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			// 读取数据 然后把读取到的数据  实例化到 字符串中
			String result = bufferedReader.readLine();
			// 其实JSON 就是一堆字符串 转成对象  是为了方便操作
			JSONObject jsonObject = JSONObject.fromObject(result);
			// 服务器这边传来 3 条数据  一个是 state 代表  成功与否 （1  成功  2失败）  message (描述)  data(验证码)
			System.out.println(jsonObject.get("state"));
			System.out.println(jsonObject.get("message"));
			System.out.println(jsonObject.get("data"));
			//如果服务器给的state是1 的话 代表成功向聚合平台发送了短信的请求
			if (((Integer)jsonObject.get("state"))==1) {
				NoteBIZ noteBIZ = new NoteBIZImpl();
				Note note = new Note();
				note.setCreateTime(new Timestamp(System.currentTimeMillis()));
				note.setPhone(phone);
				note.setValidatecode((Integer)jsonObject.get("data"));
				addResult = noteBIZ.addNote(note);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			response.getWriter().print(addResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
