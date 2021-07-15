package dao.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatRoomMemberBean implements Serializable {
  private ArrayList<ChatRoomMemberRecordBean> chatRoomMemberRecordArray;

  public ChatRoomMemberBean() {
    chatRoomMemberRecordArray = new ArrayList<ChatRoomMemberRecordBean>();
  }

  public void addRecord(ChatRoomMemberRecordBean obj) {
    chatRoomMemberRecordArray.add(obj);
  }

  public int getArraySize() {
    return chatRoomMemberRecordArray.size();
  }

  public ArrayList<ChatRoomMemberRecordBean> getRecordArray() {
    return chatRoomMemberRecordArray;
  }

  public void setChatRecordArray(ArrayList<ChatRoomMemberRecordBean> chatRoomMemberRecordArray) {
    this.chatRoomMemberRecordArray = chatRoomMemberRecordArray;
  }
}
