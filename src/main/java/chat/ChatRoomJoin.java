package chat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.bean.ChatRoomMemberRecordBean;
import dao.dao.ChatRoomMemberDAO;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/ChatRoomJoin")
public class ChatRoomJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatRoomJoin() throws ServletException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメータ取得
		int chatRoomId  = Integer.parseInt(request.getParameter("chat_room_id"));
		int userId      = (int)request.getSession().getAttribute("userId");
		
		// レコード作成
		ChatRoomMemberRecordBean chatRoomMemberRecod = new ChatRoomMemberRecordBean();
		chatRoomMemberRecod.setChatRoomId(chatRoomId);
		chatRoomMemberRecod.setUserId(userId);
		
		// レコード保存
		ChatRoomMemberDAO chatRoomMemberDao = new ChatRoomMemberDAO();
		chatRoomMemberDao.create(chatRoomMemberRecod);
		
		response.sendRedirect("Chat");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
