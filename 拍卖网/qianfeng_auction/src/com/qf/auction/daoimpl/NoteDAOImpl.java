package com.qf.auction.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.qf.auction.dao.NoteDAO;
import com.qf.auction.entity.Note;
import com.qf.auction.util.JDBCUtil;

public class NoteDAOImpl implements NoteDAO {

	@Override
	public int addNote(Note note) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int executeCount = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement("insert into note (phone,validatecode,createTime) values(?,?,?)");
			preparedStatement.setString(1, note.getPhone());
			preparedStatement.setInt(2, note.getValidatecode());
			preparedStatement.setTimestamp(3, note.getCreateTime());
			executeCount = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(preparedStatement, connection);
		}
		return executeCount;
	}

	@Override
	public int findValidateCodeByPhoneNumber(String phonenumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet =null;
		int validateCode = 0;
		try {
			connection = JDBCUtil.getConnection();
			preparedStatement = connection.prepareStatement("select validatecode from note where phone = ? order by createtime desc limit 0,1");
			preparedStatement.setString(1,phonenumber);
			resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			//validateCode = resultSet.getInt(1);
			validateCode = resultSet.getInt("validateCode");
		 }
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.close(resultSet,preparedStatement, connection);
		}
		return validateCode;
	}

}
