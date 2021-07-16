package dao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.bean.BaseRecordBean;
import dao.bean.ChatBean;
import dao.bean.ChatRecordBean;
import dao.exception.DatabaseException;
import dao.exception.SystemException;

public class ChatDAO extends DAOBase {

	public ChatDAO() {
		tableName = "chat";
		columns = "chat_room_member_id, chat_room_id, message, type";
	}

	@Override
	public Object resultSetToBean(ResultSet rs) throws SQLException {

		ChatBean bean = new ChatBean();
		ChatRecordBean record = new ChatRecordBean();
		while (rs.next()) {
			// 値セット
			record.setMessage(rs.getString("message"));
			record.setType(rs.getInt("type"));

			bean.addRecord(record);
		}
		return bean;
	}

	@Override
	public ChatBean getBean() {
		ChatBean chatBean = new ChatBean();
		try {
			chatBean = (ChatBean) super.getBean();
		} catch (DatabaseException | SystemException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return chatBean;
	}

	public int create(ChatRecordBean record) {
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
