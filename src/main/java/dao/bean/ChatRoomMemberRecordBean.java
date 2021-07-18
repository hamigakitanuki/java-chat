package dao.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatRoomMemberRecordBean extends BaseRecordBean {
  private int user_id;
  private int chat_room_id;

  public ChatRoomMemberRecordBean() {
    super();
  }

  public String getValues() {
	Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    return String.format("values(%d, %d, '%s')", user_id, chat_room_id, df.format(d));
  }

  public int getUserId() {
    return user_id;
  }

  public void setUserId(int user_id) {
    this.user_id = user_id;
  }

  public int getChatRoomId() {
    return chat_room_id;
  }

  public void setChatRoomId(int chat_room_id) {
    this.chat_room_id = chat_room_id;
  }

}
