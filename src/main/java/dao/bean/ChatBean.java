package dao.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatBean implements Serializable {
  private ArrayList<ChatRecordBean> chatRecordArray;

  public ChatBean() {
    chatRecordArray = new ArrayList<ChatRecordBean>();
  }

  public void addRecord(ChatRecordBean obj) {
    chatRecordArray.add(obj);
  }

  public int getArraySize() {
    return chatRecordArray.size();
  }

  public ArrayList<ChatRecordBean> getRecordArray() {
    return chatRecordArray;
  }

  public void setChatRecordArray(ArrayList<ChatRecordBean> chatRecordArray) {
    this.chatRecordArray = chatRecordArray;
  }
}
