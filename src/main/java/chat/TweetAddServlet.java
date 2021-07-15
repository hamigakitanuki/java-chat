package chat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.bean.ChatRecordBean;
import dao.dao.ChatDAO2;
import dao.exception.DatabaseException;
import dao.exception.SystemException;

@WebServlet("/TweetAddServlet")
public class TweetAddServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String comment = request.getParameter("comment");
    Date dateObj = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String date = format.format(dateObj);
    ChatRecordBean twtrecord = new ChatRecordBean();
    twtrecord.setMessage(comment);
    twtrecord.setChatRoomMemberId(1);
    twtrecord.setChatRoomId(1);
    twtrecord.setType(1);

    try {
      ChatDAO2 dao = new ChatDAO2();
      int ret = dao.create(twtrecord);

      // HttpSession session = request.getSession();
      // session.setAttribute("RecordCount",ret);
      // session.setAttribute("StudentRecord", twtrecord);
      // getServletContext().getRequestDispatcher("/tweetList.jsp").forward(request,
      // response);

    } catch (SystemException e) {
      e.printStackTrace();
      HttpSession session = request.getSession();
      session.setAttribute("Except", e);
      getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    } catch (DatabaseException e) {
      e.printStackTrace();
      HttpSession session = request.getSession();
      session.setAttribute("Except", e);
      getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
      return;
    }
  }

}
