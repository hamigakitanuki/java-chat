package dao.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatRoomBean implements Serializable {
  private ArrayList<ChatRoomRecordBean> chatRoomRecordArray;

  public ChatRoomBean() {
    chatRoomRecordArray = new ArrayList<ChatRoomRecordBean>();
  }

  public void addRecord(ChatRoomRecordBean obj) {
    chatRoomRecordArray.add(obj);
  }

  public int getArraySize() {
    return chatRoomRecordArray.size();
  }

  public ArrayList<ChatRoomRecordBean> getRecordArray() {
    return chatRoomRecordArray;
  }

  public void setChatRecordArray(ArrayList<ChatRoomRecordBean> chatRoomRecordArray) {
    this.chatRoomRecordArray = chatRoomRecordArray;
  }
}
