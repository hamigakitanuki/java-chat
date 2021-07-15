package dao.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class UserBean implements Serializable {
  private ArrayList<UserRecordBean> userRecordArray;

  public UserBean() {
    userRecordArray = new ArrayList<UserRecordBean>();
  }

  public void addRecord(UserRecordBean obj) {
    userRecordArray.add(obj);
  }

  public int getArraySize() {
    return userRecordArray.size();
  }

  public ArrayList<UserRecordBean> getRecordArray() {
    return userRecordArray;
  }

  public void setChatRecordArray(ArrayList<UserRecordBean> userRecordArray) {
    this.userRecordArray = userRecordArray;
  }
}
