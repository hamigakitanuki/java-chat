package chat;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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


    String param = request.getParameter("param");


    // データが無い場合、「hello world」に基本データに設定する。


    if (param == null || param.isEmpty()) {


      param = "all";


    }


    // WebSessionに「TestSession」キーでデータを格納する。

    System.out.println(param);
    request.getSession().setAttribute("chatRoomId", param);


    // Web応答は「Session In OK」にする。


    response.getWriter().append("Session In OK");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
