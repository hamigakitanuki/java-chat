package chat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.bean.ChatRoomBean;
import dao.bean.ChatRoomMemberBean;
import dao.bean.ChatRoomMemberRecordBean;
import dao.bean.UserBean;
import dao.bean.UserRecordBean;
import dao.dao.ChatRoomDAO;
import dao.dao.ChatRoomMemberDAO;
import dao.dao.UserDAO;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/Chat")
@MultipartConfig(maxFileSize = 1048576)
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final File uploadDir = new File("upload"); // ファイル保存先

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Chat() throws ServletException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Query Stringは「param」キーでデータを取得する。
		//
		// String param = request.getParameter("param");
		//
		// if (param == null || param.isEmpty()) {
		// param = "all";
		// }
		// // WebSessionに「TestSession」キーでデータを格納する。
		//
		// System.out.println(param);
		// request.getSession().setAttribute("chatRoomId", param);
		//
		// // Web応答は「Session In OK」にする。
		//
		// response.getWriter().append("Session In OK");

		HttpSession session = request.getSession();
		int userId;

		/* userIdがない場合は作成し、セッションに格納 */
		if (session.getAttribute("userId") == null) {
			// 値をセット
			UserRecordBean userRecordBeam = new UserRecordBean();
			userRecordBeam.setName("ゲスト" + new Random().nextInt(100));
			userRecordBeam.setType(5);

			UserDAO dao = new UserDAO();
			userId = dao.create(userRecordBeam);
			session.setAttribute("userId", userId);
		} else {
			userId = (int) session.getAttribute("userId");
		}

		// 自分のユーザー情報を取得
		UserDAO userDao = new UserDAO();
		userDao.setWhere(String.format("id = %d", userId));
		UserBean userBean = userDao.getBean();
		String userName = userBean.getRecordArray().get(0).getName();
		System.out.println(userName);

		/* チャットルームメンバーテーブルから参加済みのチャットルームを取得 */
		ChatRoomMemberBean chatRoomMemberBean;
		ChatRoomMemberDAO chatRoomMemberDao = new ChatRoomMemberDAO();
		chatRoomMemberDao.setWhere(String.format("user_id = %d", userId));
		chatRoomMemberBean = chatRoomMemberDao.getBean();

		/* chat_room_idを配列に変換 */
		List<String> chatRoomIds = new ArrayList<String>();
		for (ChatRoomMemberRecordBean chatRoomMemberRecordBean : chatRoomMemberBean.getRecordArray()) {
			chatRoomIds.add(String.valueOf(chatRoomMemberRecordBean.getChatRoomId()));
		}

		ChatRoomBean chatRoomBean = null;
		if (chatRoomIds.size() > 0) {
			/* joinしてwhereInで検索し取得 */
			ChatRoomDAO chatRoomDao = new ChatRoomDAO();
			chatRoomDao.setWhere(String.format("id in (%s)", String.join(",", chatRoomIds)));
			chatRoomBean = chatRoomDao.getBean();
		}

		request.setAttribute("chatRoomBean", chatRoomBean);
		request.setAttribute("userName", userName);
		getServletContext().getRequestDispatcher("/chat.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);

		Part fPart = request.getPart("file");
		String fName = (new StringBuilder(2).append("").append(System.currentTimeMillis()).append("_")
				.append(fPart.getSubmittedFileName()).toString());
		String path = getServletContext().getRealPath("/upload");
		System.out.println(path + File.separator + fName);
		fPart.write(path + File.separator + fName);

		// ブロードキャスト処理
		int chatRoomId = (int) request.getSession().getAttribute("chatRoomId");
		BroadSocket.bloadCastSend(chatRoomId);
		response.getWriter().append("Send Ok");

	}
}
