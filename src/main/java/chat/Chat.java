package chat;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.bean.ChatRoomBean;
import dao.dao.ChatRoomDAO;
import dao.exception.DatabaseException;
import dao.exception.SystemException;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/Chat")
@MultipartConfig(maxFileSize=1048576)
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final File uploadDir = new File("upload");  // ファイル保存先

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chat()  throws ServletException {
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
		
		try {
			ChatRoomBean chatRoomBean;
		    ChatRoomDAO dao = new ChatRoomDAO();
		    chatRoomBean = (ChatRoomBean) dao.getBean();
		    HttpSession session = request.getSession();
		    session.setAttribute("chatRoomBean", chatRoomBean);
		    getServletContext().getRequestDispatcher("/chat.jsp").forward(request, response);
		    
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
		Part fPart = request.getPart("file");
	    String fName = (new StringBuilder(2)
	      .append("").append(System.currentTimeMillis())
	      .append("_").append(fPart.getSubmittedFileName()
	    ).toString());
	    String path = getServletContext().getRealPath("/upload");
	    System.out.println(path+File.separator+fName);
	    fPart.write(path+File.separator+fName);

		//	ブロードキャスト処理
		String chatRoomId = (String)request.getSession().getAttribute("chatRoomId");
		BroadSocket.bloadCastSend(chatRoomId, "/upload/"+fName);
	    response.getWriter().append("Send Ok");

	}
}
