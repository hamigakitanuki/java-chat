package dao.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatRecordBean extends BaseRecordBean {
  private String name;
  private String message;
  private String date;

  private int chatRoomMemberId;
  private int chatRoomId;
  private int type;

  public ChatRecordBean() {
    super();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getChatRoomMemberId() {
    return chatRoomMemberId;
  }

  public void setChatRoomMemberId(int chatRoomMemberId) {
    this.chatRoomMemberId = chatRoomMemberId;
  }

  public int getChatRoomId() {
    return chatRoomId;
  }

  public void setChatRoomId(int chatRoomId) {
    this.chatRoomId = chatRoomId;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }
  
  public String getValues() {
	 Date d = new Date();
     SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	 return String.format("values(%d, %d, '%s', %d, '%s')", chatRoomMemberId, chatRoomId, message, type, df.format(d));
  }

}
