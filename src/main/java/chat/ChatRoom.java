package chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.bean.ChatRoomRecordBean;
import dao.dao.ChatRoomDAO;

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

	    ChatRoomDAO dao = new ChatRoomDAO();
	    dao.create(chatRoomRecod);


	}
}
