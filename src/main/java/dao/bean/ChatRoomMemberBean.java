package dao.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatRoomMemberBean implements Serializable {
  private ArrayList<ChatRoomMemberRecordBean> chatRoomRecordArray;

  public ChatRoomMemberBean() {
    chatRoomRecordArray = new ArrayList<ChatRoomMemberRecordBean>();
  }

  public void addRecord(ChatRoomMemberRecordBean obj) {
    chatRoomRecordArray.add(obj);
  }

  public int getArraySize() {
    return chatRoomRecordArray.size();
  }

  public ArrayList<ChatRoomMemberRecordBean> getRecordArray() {
    return chatRoomRecordArray;
  }

  public void setChatRecordArray(ArrayList<ChatRoomMemberRecordBean> chatRoomRecordArray) {
    this.chatRoomRecordArray = chatRoomRecordArray;
  }
}
