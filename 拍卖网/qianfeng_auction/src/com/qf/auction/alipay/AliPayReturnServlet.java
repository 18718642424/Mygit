package com.qf.auction.alipay;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.auction.biz.AuctionRecordBIZ;
import com.qf.auction.biz.AuctionOrderBIZ;
import com.qf.auction.bizimpl.AuctionRecordBIZImpl;
import com.qf.auction.bizimpl.AuctionOrderBIZImpl;
import com.qf.auction.entity.Auction;
import com.qf.auction.entity.AuctionRecord;
import com.qf.auction.entity.AuctionOrder;
import com.qf.auction.entity.User;
import com.qf.auction.enums.AuctionStateEnum;
import com.qf.auction.enums.OrderStateEnum;
import com.qf.auction.util.AES;

public class AliPayReturnServlet extends HttpServlet {

	private static final String sKey = "www.qianfeng.com";

	/**
	 * Constructor of the object.
	 */
	public AliPayReturnServlet() {
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
		// 这里要接受 阿里传过来的数据
		// body 里面包含了 我们后续要做的 业务逻辑 的 关键参数 都在这个 body当中
		String body = request.getParameter("body");
		// String body =
		// ""+out_trade_no+","+userId+","+auctionId+","+total_amount+",";
		AuctionOrderBIZ orderBIZ = new AuctionOrderBIZImpl();
		AuctionOrder order = new AuctionOrder();
		try {
			// 对传输过来的数据 进行 解密操作（如果非 加密数据 则无法获取到值）
			String[] userprams = body.split(",");
			String orderno = AES.Decrypt(userprams[0], sKey);
			String userId = AES.Decrypt(userprams[1], sKey);
			String auctionId = AES.Decrypt(userprams[2], sKey);
			String price = AES.Decrypt(userprams[3], sKey);
			AuctionRecordBIZ auctionRecordBIZ = new AuctionRecordBIZImpl();
			AuctionRecord auctionRecord = new AuctionRecord();
			Auction auction = new Auction(Integer.parseInt(auctionId));
			User user = new User(Integer.parseInt(userId));
			auctionRecord.setAuction(auction);
			auctionRecord.setUser(user);
			auctionRecord.setAuctionTime(new Timestamp(System
					.currentTimeMillis()));
			auctionRecord.setAuctionPrice(Double.parseDouble(price));
			String auctionRecordResult = auctionRecordBIZ
					.addAuctionRecord(auctionRecord);
			if (auctionRecordResult.equals(AuctionStateEnum.AUCTION_SUCCESS
					.getValue())) {
				// 如果拍卖品 详情添加 成功接下来就可以 添加订单
				order.setOrderid(orderno);
				order.setAuctionid(Integer.parseInt(auctionId));
				order.setUserid(Integer.parseInt(userId));
				order.setPrice(Double.parseDouble(price));
				order.setCreatetime(new Timestamp(System.currentTimeMillis()));
				String orderResult = orderBIZ.addOrder(order);
				// 订单添加完成后 就可以告诉阿里 OK了
				if (orderResult.equals(OrderStateEnum.ORDER_ADD_SUCCESS
						.getValue())) {
					response.getWriter().print("ok");
				} else {
					response.getWriter().print("no");
					// F3 可以查看 变量声明的位置 和 函数定义的位置 和 文件的位置
					orderBIZ.addOrder(order, 0);
				}
			} else {
				// 否则的话 直接告诉阿里 失败了
				response.getWriter().print("no");
				orderBIZ.addOrder(order, 0);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				response.getWriter().print("no");
				orderBIZ.addOrder(order, 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// try {
		// // 在响应报文中 传一个ok 是为了 告诉阿里 不要再 来请求这个 文件了 因为这个订单 已经 录入系统了
		// // 返回no 代表 用户支付玩之后 在服务器中 并 没有完成 订单
		// response.getWriter().print("ok");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
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
