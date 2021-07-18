package chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.bean.ChatRoomMemberRecordBean;
import dao.bean.ChatRoomRecordBean;
import dao.dao.ChatRoomDAO;
import dao.dao.ChatRoomMemberDAO;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/ChatRoom")
public class ChatRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatRoom() throws ServletException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメータ取得
		String chatRoomName              = request.getParameter("chat_room_name");
		String password                  = request.getParameter("password");
		
		System.out.println(chatRoomName);
		// レコード作成
		ChatRoomRecordBean chatRoomRecod = new ChatRoomRecordBean();
		chatRoomRecod.setChatRoomName(chatRoomName);
		chatRoomRecod.setPassword(password);

		// レコード保存
		ChatRoomDAO dao = new ChatRoomDAO();
		int chatRoomId = dao.create(chatRoomRecod);
		
		// レコード作成
		ChatRoomMemberRecordBean chatRoomMemberRecod = new ChatRoomMemberRecordBean();
		chatRoomMemberRecod.setChatRoomId(chatRoomId);
		// TODO ユーザーの取得を入れる
		chatRoomMemberRecod.setUserId(1);
		
		// レコード保存
		ChatRoomMemberDAO chatRoomMemberDao = new ChatRoomMemberDAO();
		chatRoomMemberDao.create(chatRoomMemberRecod);
		
		response.sendRedirect("Chat");
	}
}
