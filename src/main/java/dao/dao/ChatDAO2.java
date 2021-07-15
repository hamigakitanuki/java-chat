package dao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.bean.ChatBean;
import dao.bean.ChatRecordBean;

public class ChatDAO2 extends DAOBase2 {
  
	String tableName = "chat";
	
	public ChatDAO2() {
		
	}
	
	@Override
	public Object resultSetToBean(ResultSet rs) throws SQLException {
		ChatBean bean         = new ChatBean();
        ChatRecordBean record = new ChatRecordBean();

        // 値セット
        record.setName(rs.getString("name"));
        record.setMessage(rs.getString("message"));

        bean.addRecord(record);
        
        return bean;
	}
	
}
