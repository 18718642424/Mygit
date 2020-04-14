package com.qf.auction.bizimpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.qf.auction.biz.AuctionBIZ;
import com.qf.auction.dao.AuctionDAO;
import com.qf.auction.daoimpl.AuctionDAOImpl;
import com.qf.auction.entity.Auction;
import com.qf.auction.enums.AuctionStateEnum;
import com.qf.auction.util.StringUtil;

public class AuctionBIZImpl implements AuctionBIZ {

	AuctionDAO auctionDAO = new AuctionDAOImpl();

	@Override
	public List<Auction> findAuctionListByPage(BigDecimal pageIndex,
			BigDecimal pageSize) {
		// pageindex不管是什么 语言 基本都一个定律 就是 (pageindex-1)*pagesize
		// 在第一个参数的基础上 往后面查询 pagesize条数据 为什么是这样的呢？ 因为我们用的是MYSQL 数据库 它limit 关键字
		// 就是这样分页的
		return auctionDAO.findAuctionListByPage(
				pageIndex.subtract(new BigDecimal(1)).multiply(pageSize),
				pageSize);
	}

	@Override
	public BigDecimal getAllCount() {
		return auctionDAO.getAllCount();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> addAuction(ServletConfig servletConfig,
			HttpServletRequest request, HttpServletResponse response) {
		// 实例化 smartupload
		SmartUpload smartUpload = new SmartUpload();
		Map<String, Object> map = new HashMap<String, Object>();
		Auction auction = new Auction();
		boolean isvalidate = false;
		try {
			smartUpload.initialize(servletConfig, request, response);
			// 10MB 文件的大小 都可以上传
			smartUpload.setMaxFileSize(1024 * 1024 * 10);
			// 开启上传功能
			smartUpload.upload();
			// 获取用户上传的文件
			File userFile = smartUpload.getFiles().getFile(0);
			// 获取用户传输过来的数据
			// auctionName startPrice upset startTime endTime pic desc
			// 以上都是 用户传入的KEY
			String auctionName = smartUpload.getRequest().getParameter(
					"auctionName");
			String auctionStartPrice = smartUpload.getRequest().getParameter(
					"startPrice");
			String auctionUpset = smartUpload.getRequest()
					.getParameter("upset");
			String auctionStartTime = smartUpload.getRequest().getParameter(
					"startTime");
			String auctionEndTime = smartUpload.getRequest().getParameter(
					"endTime");
			String auctionDESC = smartUpload.getRequest().getParameter("desc");
			// 任何数据都不能为空 判断用户的操作行为 一旦有一条数据 用户没有输入 都为添加失败
			if (StringUtil.isEmpty(auctionName)
					|| StringUtil.isEmpty(auctionStartPrice)
					|| StringUtil.isEmpty(auctionUpset)
					|| StringUtil.isEmpty(auctionStartTime)
					|| StringUtil.isEmpty(auctionEndTime)
					|| StringUtil.isEmpty(auctionDESC)) {
				map.put("msg", AuctionStateEnum.AUCTION_ADD_FAIL.getValue());
				return map;
			}

			// 实例化 auction 中的属性
			auction.setAuctionName(auctionName);
			try {
				auction.setAuctionStartPrice(Double
						.parseDouble(auctionStartPrice));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				auction.setAuctionUpset(Double.parseDouble(auctionUpset));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				auction.setAuctionStartTime(Timestamp.valueOf(auctionStartTime));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				auction.setAuctionEndTime(Timestamp.valueOf(auctionEndTime));
			} catch (Exception e) {
				e.printStackTrace();
			}
			auction.setAuctionDESC(auctionDESC);
			if (StringUtil.notEmpty(auction.getAuctionName())
					&& auction.getAuctionStartPrice() != 0d
					&& auction.getAuctionUpset() != 0d
					&& StringUtil.notEmpty(auction.getAuctionStartTime())
					&& StringUtil.notEmpty(auction.getAuctionEndTime())
					&& StringUtil.notEmpty(auction.getAuctionDESC())) {
				isvalidate = true;
			}
			if (isvalidate) {
				// 接下来判断 用户是否 有上传文件
				if (userFile.getSize() > 0) {
					// 如果用户有上传的话 获取服务器的upload文件夹的路径
					String hostPath = request.getSession().getServletContext()
							.getRealPath("upload");
					// 生成文件名
					String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
							.format(new Date()) + "." + userFile.getFileExt();
					// 把生成的文件名 赋值给 拍卖品
					auction.setAuctionPICPath(fileName);
					// 把拍卖品添加到数据库
					int executeCount = auctionDAO.addAuction(auction);
					if (executeCount > 0) {
						// 如果能够添加成功则 把用户上传的文件上传的服务器 同时返回添加成功的状态
						userFile.saveAs(hostPath + java.io.File.separator
								+ fileName);
						// 如果代码能执行到 这里 说明 用户上传 拍卖品数据成功 同时 用户上传文件 成功
						map.put("msg",
								AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue());
						return map;
					} else {
						map.put("msg",
								AuctionStateEnum.AUCTION_ADD_FAIL.getValue());
						return map;
					}
				} else {
					// 如果代码进入到这里说明 用户没有上传文件
					int executeCount = auctionDAO.addAuction(auction);
					if (executeCount > 0) {
						map.put("msg",
								AuctionStateEnum.AUCTION_ADD_SUCCESS.getValue());
						return map;
					} else {
						map.put("msg",
								AuctionStateEnum.AUCTION_ADD_FAIL.getValue());
						return map;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("msg", AuctionStateEnum.AUCTION_ADD_FAIL.getValue());
		map.put("auction", auction);
		return map;
	}

	@Override
	public Auction findAuctionById(int auctionId) {
		// 直接调用DAO层的对应函数就可以了
		return auctionDAO.findAuctionById(auctionId);
	}

	@Override
	public String updateAuction(ServletConfig servletConfig,
			HttpServletRequest request, HttpServletResponse response) {
		// 实例化 smartupload
		SmartUpload smartUpload = new SmartUpload();
		try {
			smartUpload.initialize(servletConfig, request, response);
			// 10MB 文件的大小 都可以上传
			smartUpload.setMaxFileSize(1024 * 1024 * 10);
			// 开启上传功能
			smartUpload.upload();
			// 获取用户上传的文件
			File userFile = smartUpload.getFiles().getFile(0);
			// 获取用户传输过来的数据
			Auction auction = new Auction();
			// auctionName startPrice upset startTime endTime pic desc
			// 以上都是 用户传入的KEY
			String auctionId = smartUpload.getRequest().getParameter(
					"auctionId");
			String auctionName = smartUpload.getRequest().getParameter(
					"auctionName");
			String auctionStartPrice = smartUpload.getRequest().getParameter(
					"startPrice");
			String auctionUpset = smartUpload.getRequest()
					.getParameter("upset");
			String auctionStartTime = smartUpload.getRequest().getParameter(
					"startTime");
			String auctionEndTime = smartUpload.getRequest().getParameter(
					"endTime");
			String auctionDESC = smartUpload.getRequest().getParameter("desc");
			if (StringUtil.isEmpty(auctionId)) {
				return AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
			} else {
				auction.setAuctionID(Integer.parseInt(auctionId));
			}
			// 任何数据都不能为空 判断用户的操作行为 一旦有一条数据 用户没有输入 都为添加失败
			if (StringUtil.isEmpty(auctionName)
					|| StringUtil.isEmpty(auctionStartPrice)
					|| StringUtil.isEmpty(auctionUpset)
					|| StringUtil.isEmpty(auctionStartTime)
					|| StringUtil.isEmpty(auctionEndTime)
					|| StringUtil.isEmpty(auctionDESC)) {
				return AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
			}
			// 实例化 auction 中的属性
			auction.setAuctionName(auctionName);
			auction.setAuctionStartPrice(Double.parseDouble(auctionStartPrice));
			auction.setAuctionUpset(Double.parseDouble(auctionUpset));
			auction.setAuctionStartTime(Timestamp.valueOf(auctionStartTime));
			auction.setAuctionEndTime(Timestamp.valueOf(auctionEndTime));
			auction.setAuctionDESC(auctionDESC);
			String beforeFileName = smartUpload.getRequest().getParameter(
					"pic02");
			// 接下来判断 用户是否 有上传文件
			if (userFile.getSize() > 0) {
				// 如果用户有上传的话 获取服务器的upload文件夹的路径
				String hostPath = request.getSession().getServletContext()
						.getRealPath("upload");
				// 生成文件名
				String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS")
						.format(new Date()) + "." + userFile.getFileExt();
				// 把生成的文件名 赋值给 拍卖品
				auction.setAuctionPICPath(fileName);
				// 把拍卖品添加到数据库
				int executeCount = auctionDAO.updateAuction(auction);
				if (executeCount > 0) {
					// 把用户之前存储 的文件删除掉
					if (StringUtil.notEmpty(beforeFileName)) {
						java.io.File beforeFile = new java.io.File(hostPath
								+ java.io.File.separator + beforeFileName);
						beforeFile.delete();
					}
					// 如果能够添加成功则 把用户上传的文件上传的服务器 同时返回添加成功的状态
					userFile.saveAs(hostPath + java.io.File.separator
							+ fileName);
					// 如果代码能执行到 这里 说明 用户上传 拍卖品数据成功 同时 用户上传文件 成功
					return AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getValue();
				} else {
					return AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
				}
			} else {
				// 如果代码进入到这里说明 用户没有上传文件
				auction.setAuctionPICPath(beforeFileName);
				int executeCount = auctionDAO.updateAuction(auction);
				if (executeCount > 0) {
					return AuctionStateEnum.AUCTION_UPDATE_SUCCESS.getValue();
				} else {
					return AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果代码能混到这里 说明 添加失败了
		return AuctionStateEnum.AUCTION_UPDATE_FAIL.getValue();
	}

	@Override
	public boolean delAuctionById(String hostPath, int auctionId) {
		// 调用根据拍卖品ID查找 对应的拍卖这个函数 来抓取到 对应的 拍卖品
		Auction auction = findAuctionById(auctionId);
		int executeCount = auctionDAO.delAuctionById(auctionId);
		if (executeCount > 0) {
			// 进入到这里说明删除成功 成功后将 该 拍卖品的图片删除
			try {
				java.io.File file = new java.io.File(hostPath
						+ java.io.File.separator + auction.getAuctionPICPath());
				file.delete();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<Auction> searchAuction(String auctionName, String startTime,
			String endTime, String startPrice) {
		// where 1=1 动态查询必须要加上一个恒等的条件 目的是为了 拼接后续的动态（用户的搜素条件 决定了SQL 语句 就是 动态SQL
		// ）SQL 语句的拼接
		// 在写动态SQL 语句的时候 一定要 养成一个 后面写空格的习惯 如果没有这个习惯 很容易 出现 语法错误
		StringBuilder stringBuilder = new StringBuilder(
				"select * from auction where 1=1 ");
		// 下面的语句 一定注意 添加单引号
		if (StringUtil.notEmpty(auctionName)) {
			stringBuilder.append("and auctionname like '" + auctionName
					+ "%'         ");
		}
		if (StringUtil.notEmpty(startTime)) {
			stringBuilder.append("and auctionstarttime >= '"
					+ Timestamp.valueOf(startTime) + "'         ");
		}
		if (StringUtil.notEmpty(endTime)) {
			stringBuilder.append("and auctionendtime <= '"
					+ Timestamp.valueOf(endTime) + "'         ");
		}
		if (StringUtil.notEmpty(startPrice)) {
			stringBuilder.append("and auctionstartprice <= '"
					+ Double.parseDouble(startPrice) + "'           ");
		}
		return auctionDAO.searchAuction(stringBuilder.toString());
	}

	@Override
	public List<Auction> getAll() {
		return auctionDAO.getAll();
	}

	@Override
	public int updateAuctionStateByAuctionId(int auctionId) {
		return auctionDAO.updateAuctionStateByAuctionId(auctionId);
	}

	@Override
	public Map<String, Object> searchAuctionResult() {
		Map<String, Object> map = new HashMap<String, Object>();

		// 查找已经 竞拍结束的拍卖品
		List<Auction> endAuctions = auctionDAO.findEndAuction();
		// 查找还在 竞拍中的拍卖品
		List<Auction> notEndAuctions = auctionDAO.findNotEndAuction();
		map.put("end", endAuctions);
		map.put("notend", notEndAuctions);
		return map;
	}
}
