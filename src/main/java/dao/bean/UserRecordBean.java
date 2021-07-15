package dao.bean;

import java.io.Serializable;

public class UserRecordBean implements Serializable {
  private String email;
  private String password;
  private String name;
  private int type;

  public UserRecordBean() {
    super();
  }

  public String getValues() {
    return String.format("values('%s', '%s', '%s', $d)", email, password, name, type);
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

}
