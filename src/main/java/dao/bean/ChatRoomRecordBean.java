package dao.bean;

import java.io.Serializable;

public class ChatRoomRecordBean implements Serializable {
  private String chat_room_name;
  private String password;

  public ChatRoomRecordBean() {
    super();
  }
  
  
  public String getValues() {
	 return String.format("values('%s', '%s')", chat_room_name, password);
  }

  public String getChatRoomName() {
    return chat_room_name;
  }

  public void setChatRoomName(String chat_room_name) {
    this.chat_room_name = chat_room_name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
