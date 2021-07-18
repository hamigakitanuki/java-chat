package dao.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatRoomRecordBean extends BaseRecordBean {
  private String chat_room_name;
  private String password;
  private int id;

  public ChatRoomRecordBean() {
    super();
  }

  public String getValues() {
    Date d = new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    return String.format("values('%s', '%s', '%s')", chat_room_name, password, df.format(d));
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
