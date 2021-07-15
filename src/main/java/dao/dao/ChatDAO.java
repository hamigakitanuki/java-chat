package dao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.bean.ChatBean;
import dao.bean.ChatRecordBean;

public class ChatDAO extends DAOBase {
  
	public ChatDAO() {
		tableName = "chat";
		columns   = "chat_room_member_id, chat_room_id, message, type";
	}
	
	@Override
	public Object resultSetToBean(ResultSet rs) throws SQLException {
	
		ChatBean bean         = new ChatBean();
        ChatRecordBean record = new ChatRecordBean();
        while (rs.next()) {
	        // 値セット
	        record.setMessage(rs.getString("message"));
	        record.setType(rs.getInt("type"));
	
	        bean.addRecord(record);
        }
        return bean;
	}
	
}
