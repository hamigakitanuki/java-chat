package dao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.bean.BaseRecordBean;
import dao.bean.ChatRoomBean;
import dao.bean.ChatRoomRecordBean;
import dao.exception.DatabaseException;
import dao.exception.SystemException;

public class ChatRoomDAO extends DAOBase {

	public ChatRoomDAO() {
		tableName = "chat_room";
		columns = "chat_room_name, password, created_at";
	}

	@Override
	public Object resultSetToBean(ResultSet rs) throws SQLException {

		ChatRoomBean bean = new ChatRoomBean();

		while (rs.next()) {
			// 値セット
			ChatRoomRecordBean record = new ChatRoomRecordBean();
			record.setChatRoomName(rs.getString("chat_room_name"));
			record.setPassword(rs.getString("password"));
			record.setId(rs.getInt("id"));

			bean.addRecord(record);
		}
		return bean;
	}

	@Override
	public ChatRoomBean getBean() {
		ChatRoomBean chatRoomBean = new ChatRoomBean();
		try {
			chatRoomBean = (ChatRoomBean) super.getBean();
		} catch (DatabaseException | SystemException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return chatRoomBean;
	}

	public int create(ChatRoomRecordBean record) {
		int ret = 0;

		try {
			ret = super.create((BaseRecordBean) record);
		} catch (DatabaseException | SystemException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return ret;
	}
}
