package chat;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.bean.ChatBean;
import dao.bean.ChatRecordBean;
import dao.dao.ChatDAO;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/ChatMessage")
@MultipartConfig(maxFileSize=1048576)
public class ChatMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final File uploadDir = new File("upload");  // ファイル保存先

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatMessage()  throws ServletException {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// Query Stringは「param」キーでデータを取得する。
//
//    String param = request.getParameter("param");
//
//    if (param == null || param.isEmpty()) {
//      param = "all";
//    }
//    // WebSessionに「TestSession」キーでデータを格納する。
//
//    System.out.println(param);
//    request.getSession().setAttribute("chatRoomId", param);
//
//    // Web応答は「Session In OK」にする。
//
//    response.getWriter().append("Session In OK");
		
		/* チャットルームIDを取得 */
		String chatRoomId = request.getParameter("chat_room_id");
		
		/* 指定のチャットルームのメッセージを取得 */
		ChatBean chatBean;
	    ChatDAO chatDao = new ChatDAO();
	    chatDao.setWhere(String.format("chat_room_id = %s", chatRoomId));
	    chatBean = chatDao.getBean();
	    ArrayList<ChatRecordBean> chatRecordArray = chatBean.getRecordArray();
	    List<String> messages = new ArrayList<String>();
	    for(ChatRecordBean record : chatRecordArray){
	    	System.out.println(record.getMessage());
	    	messages.add(String.format("{\"user_name\":\"%s\",\"message\": \"%s\"}", record.getName(), record.getMessage()));
	    }
	    
	    PrintWriter out = response.getWriter();
        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
        
        String jsonArray = String.format("{\"messages\": [%s]}", String.join(",", messages));
        out.print(jsonArray);
        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// パラメータを取得
		String message = request.getParameter("message");
		int chatRoomId = Integer.parseInt(request.getParameter("chat_room_id"));
		int chatRoomMemberId = Integer.parseInt(request.getParameter("chat_room_member_id"));
	    
		// 値をセット
		ChatRecordBean chatRecordBeam = new ChatRecordBean();
	    chatRecordBeam.setMessage(message);
	    chatRecordBeam.setChatRoomMemberId(chatRoomMemberId);
	    chatRecordBeam.setChatRoomId(chatRoomMemberId);
	    chatRecordBeam.setType(1);

	    ChatDAO dao = new ChatDAO();
	    dao.create(chatRecordBeam);
//		doGet(request, response);
//		
//		Part fPart = request.getPart("file");
//	    String fName = (new StringBuilder(2)
//	      .append("").append(System.currentTimeMillis())
//	      .append("_").append(fPart.getSubmittedFileName()
//	    ).toString());
//	    String path = getServletContext().getRealPath("/upload");
//	    System.out.println(path+File.separator+fName);
//	    fPart.write(path+File.separator+fName);
//
//		//	ブロードキャスト処理
//		String chatRoomId = (String)request.getSession().getAttribute("chatRoomId");
//		BroadSocket.bloadCastSend(chatRoomId, "/upload/"+fName);
//	    response.getWriter().append("Send Ok");

	}
}
