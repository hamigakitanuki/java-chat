package dao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.bean.ChatBean;
import dao.bean.ChatRecordBean;
import dao.exception.DatabaseException;
import dao.exception.SystemException;
import dao.parameter.ExceptionParameters;

public class ChatDAO extends DAOBase {
  private Statement stmt;

  // レコード取得処理
  public ChatBean getChatBean() throws DatabaseException, SystemException {

    ChatBean tweetBean = new ChatBean();

    this.open();

    try {
      stmt = con.createStatement();
      String sql = "select * " + "from chat "
          + "inner join chat_room_member on chat.chat_room_member_id  = chat_room_member.id "
          + "inner join user on chat_room_member.user_id  = user.id ";

      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {

        ChatRecordBean record = new ChatRecordBean();

        // 値セット
        record.setName(rs.getString("name"));
        record.setMessage(rs.getString("message"));

        tweetBean.addRecord(record);
      }
    } catch (SQLException e) {
      throw new DatabaseException(ExceptionParameters.DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
    } finally {
      this.close(stmt);
    }
    return tweetBean;
  }

  // レコード追加処理
  public int addTweet(ChatRecordBean record) throws DatabaseException, SystemException {
    int ret = 0;
    this.open();

    try {
      stmt = con.createStatement();

      // valuesをセット
      String sql = String.format(
          "INSERT INTO chat ('chat_room_member_id', 'chat_room_id', 'message', 'type') VALUES(%d, %d, %s, %d)",
          record.getChatRoomMemberId(), record.getChatRoomId(), record.getMessage(), record.getType());
      ret = stmt.executeUpdate(sql);

    } catch (SQLException e) {
      throw new DatabaseException(ExceptionParameters.DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
    } finally {
      this.close(stmt);
    }
    return ret;
  }
}
