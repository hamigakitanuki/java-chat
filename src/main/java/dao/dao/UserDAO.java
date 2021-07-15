package dao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.bean.ChatRoomBean;
import dao.bean.ChatRoomRecordBean;

public class UserDAO extends DAOBase {

	public UserDAO() {
		tableName = "user";
		columns = "email, password, name, type";
	}

	@Override
	public Object resultSetToBean(ResultSet rs) throws SQLException {

		ChatRoomBean bean = new ChatRoomBean();
		ChatRoomRecordBean record = new ChatRoomRecordBean();
		while (rs.next()) {
			// 値セット
			record.setChatRoomName(rs.getString("chat_room_name"));
			record.setPassword(rs.getString("password"));

			bean.addRecord(record);
		}
		return bean;
	}

}
