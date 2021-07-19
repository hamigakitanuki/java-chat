package dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.bean.BaseRecordBean;
import dao.exception.DatabaseException;
import dao.exception.SystemException;
import dao.parameter.DAOParameters;
import dao.parameter.ExceptionParameters;

public class DAOBase{
  Connection con;
  Statement stmt;
  String tableName;
  String columns;
  String limit = "";
  String order = "";
  List<String> wheres = new ArrayList<String>();
  
  protected void open() throws DatabaseException,SystemException{
    try{
      con = DriverManager.getConnection(DAOParameters.CONNECT_STRING,DAOParameters.USERID,DAOParameters.PASSWORD);
    }catch(SQLException e){
      throw new DatabaseException(ExceptionParameters.DATABASE_CONNECTION_EXCEPTION_MESSAGE,e);
    }
  }

  protected void close(Statement stmt) throws DatabaseException{
    try{
      if(stmt != null){
        stmt.close();
      }
      if(con != null){
        con.close();
      }
    }catch(SQLException e){
      throw new DatabaseException(ExceptionParameters.DATABASE_CONNECTION_EXCEPTION_MESSAGE,e) ;
    }
  }
  
  // レコード取得処理
  public Object getBean() throws DatabaseException, SystemException {

	Object object = new Object();
	  
    this.open();

    try {
      stmt = con.createStatement();
      String sql = String.format("select * " + "from %s", this.tableName) + this.getWhere()  + this.order + this.limit;
      System.out.println(sql);
      ResultSet rs = stmt.executeQuery(sql);
	  object = resultSetToBean(rs);
      
    } catch (SQLException e) {
      throw new DatabaseException(ExceptionParameters.DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
    } finally {
      this.close(stmt);
    }
    return object;
  }
  
  // レコード追加処理
  public int create(BaseRecordBean record) throws DatabaseException, SystemException {
    int id = 0;
    this.open();

    try {
      stmt = con.createStatement();

      // valuesをセット
      String sql = String.format("INSERT INTO %s(%s) ", this.tableName, this.columns);
      sql += record.getValues();
      System.out.println(sql);
      stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
      ResultSet ret = stmt.getGeneratedKeys();
      ret.next();
      id = ret.getInt(1);

    } catch (SQLException e) {
      throw new DatabaseException(ExceptionParameters.DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
    } finally {
      this.close(stmt);
    }
    return id;
  }
  
  public void setWhere(String where) {
	  this.wheres.add(where);
  }
  
  public void setLimit(int limit) {
	  this.limit = String.format(" limit %d", limit);
  }
  
  public void latest() {
	  this.order = " order by created_at desc";
  }
  
  public String getWhere() {
	  String ret = "";
	  if (this.wheres.size() > 0 ) {
		  ret += " where ";
		  ret += String.join(" and ", this.wheres);
	  }
	  return ret;
  }
  
  // 仮の関数
  public Object resultSetToBean(ResultSet rs) throws SQLException {
	  return new Object();
  }
}