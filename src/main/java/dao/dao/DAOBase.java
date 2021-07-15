package dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import dao.exception.DatabaseException;
import dao.exception.SystemException;
import dao.parameter.DAOParameters;
import dao.parameter.ExceptionParameters;





public class DAOBase{
  Connection con;

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

}