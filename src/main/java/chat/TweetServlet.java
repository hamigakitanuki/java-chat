package chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.bean.ChatBean;
import dao.dao.ChatDAO;
import dao.exception.DatabaseException;
import dao.exception.SystemException;

/**
 * Servlet implementation class TweetServlet
 */
@WebServlet("/TweetServlet")
public class TweetServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ChatBean tweetBean;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      ChatDAO dao = new ChatDAO();
      tweetBean = dao.getChatBean();
      HttpSession session = request.getSession();
      session.setAttribute("twtBean", tweetBean);
      getServletContext().getRequestDispatcher("/tweetList.jsp").forward(request, response);
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
