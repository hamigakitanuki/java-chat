package dao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.bean.ChatRoomMemberBean;
import dao.bean.ChatRoomMemberRecordBean;

public class ChatRoomMemberDAO extends DAOBase {

	public ChatRoomMemberDAO() {
		tableName = "chat_room_member";
		columns = "user_id, chat_room_id";
	}

	@Override
	public Object resultSetToBean(ResultSet rs) throws SQLException {

		ChatRoomMemberBean bean = new ChatRoomMemberBean();
		ChatRoomMemberRecordBean record = new ChatRoomMemberRecordBean();
		while (rs.next()) {
			// 値セット
			record.setUserId(rs.getInt("user_id"));
			record.setChatRoomId(rs.getInt("chat_room_id"));

			bean.addRecord(record);
		}
		return bean;
	}

	@Override
	public ChatRoomMemberBean getBean() {
		ChatRoomMemberBean chatRoomMemberBean = new ChatRoomMemberBean();
		try {
			chatRoomMemberBean = (ChatRoomMemberBean) super.getBean();
		} catch (DatabaseException | SystemException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return chatRoomMemberBean;
	}

	public int create(ChatRoomMemberRecordBean record) {
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
