package dao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.bean.UserBean;
import dao.bean.UserRecordBean;

public class UserDAO extends DAOBase {

	public UserDAO() {
		tableName = "user";
		columns = "email, password, name, type";
	}

	@Override
	public Object resultSetToBean(ResultSet rs) throws SQLException {

		UserBean bean = new UserBean();
		UserRecordBean record = new UserRecordBean();
		while (rs.next()) {
			// 値セット
			record.setEmail(rs.getString("email"));
			record.setName(rs.getString("name"));
			record.setPassword(rs.getString("password"));
			record.setType(rs.getInt("type"));

			bean.addRecord(record);
		}
		return bean;
	}

}
