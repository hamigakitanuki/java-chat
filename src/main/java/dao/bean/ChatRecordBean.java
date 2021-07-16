package dao.bean;

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
	 return String.format("values(%d, %d, '%s', %d)", chatRoomMemberId, chatRoomId, message, type);
  }

}
