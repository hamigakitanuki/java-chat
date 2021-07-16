package chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.bean.BaseRecordBean;
import dao.bean.ChatRoomRecordBean;
import dao.dao.ChatRoomDAO;
import dao.exception.DatabaseException;
import dao.exception.SystemException;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/ChatRoom")
public class ChatRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatRoom()  throws ServletException {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chatRoomName = request.getParameter("chat_room_name");
		String password = request.getParameter("password");
	    ChatRoomRecordBean chatRoomRecod= new ChatRoomRecordBean();
	    chatRoomRecod.setChatRoomName(chatRoomName);
	    chatRoomRecod.setPassword(password);

	    try {
	      ChatRoomDAO dao = new ChatRoomDAO();
	      int ret = dao.create((BaseRecordBean)chatRoomRecod);
	      System.out.println(ret);
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
