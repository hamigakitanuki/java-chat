package dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
      String sql = String.format("select * " + "from %s", this.tableName);

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
    int ret = 0;
    this.open();

    try {
      stmt = con.createStatement();

      // valuesをセット
      String sql = String.format("INSERT INTO %s(%s) ", this.tableName, this.columns);
      sql += record.getValues();
      System.out.println(sql);
      ret = stmt.executeUpdate(sql);

    } catch (SQLException e) {
      throw new DatabaseException(ExceptionParameters.DATABASE_CONNECTION_EXCEPTION_MESSAGE, e);
    } finally {
      this.close(stmt);
    }
    return ret;
  }
  
  // 仮の関数
  public Object resultSetToBean(ResultSet rs) throws SQLException {
	  return new Object();
  }
}