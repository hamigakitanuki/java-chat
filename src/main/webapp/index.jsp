<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Web Socket Example</title>
</head>
<body>
  <form>
    <!-- ユーザ名を入力するテキストボックス(基本値は anonymous(匿名)だ。) -->
    <input id="user" type="text" value="anonymous">
    <!-- 送信メッセージを作成するテキストボックス -->
    <input id="textMessage" type="text">
    <!-- メッセージを送信するボタン -->
    <input onclick="sendMessage()" value="Send" type="button">
    <!-- WebSocket終了(切断)するボタン -->
    <input onclick="disconnect()" value="Disconnect" type="button">
  </form>
  <br />
  <!-- コンソール役をするテキストアリア、受信メッセージも表示する。 -->
  <textarea id="messageTextArea" rows="10" cols="50"></textarea>
  <script type="text/javascript">
  // 「WebSocketEx」はプロジェクト名
    // 「broadsocket」ホスト名
    // WebSocketオブジェクト生成(接続開始)
    var webSocket = new WebSocket("ws://localhost:8080/WebSocket/broadsocket");
    // コンソールのテキストアリア
    var messageTextArea = document.getElementById("messageTextArea");
    // WebSocketが接続成功になれば呼ばれる関数。
    webSocket.onopen = function(message) {
      // コンソールにメッセージ出力
      messageTextArea.value += "Server connect...\n";
    };
    // WebSocketが切断なれば呼ばれる関数。
    webSocket.onclose = function(message) {
      // コンソールにメッセージ出力
      messageTextArea.value += "Server Disconnect...\n";
    };
    // WebSocketからエラーが発生する時に呼ばれる関数。
    webSocket.onerror = function(message) {
      // コンソールにメッセージ出力
      messageTextArea.value += "error...\n";
    };
    // WebSocketからメッセージを受け取ったら呼ばれる関数。
    webSocket.onmessage = function(message) {
      // コンソールにメッセージ出力
      messageTextArea.value += message.data + "\n";
    };
    // Sendボタンを押下すると呼ばれる関数。
    function sendMessage() {
    	console.log($('form').serializeArray());
	   	$.ajax({
	   	    url: 'Chat',
	   	    type: 'POST',
	   	    dataType: 'json',
	   	    // フォーム要素の内容をハッシュ形式に変換
	   	    data: $('form').serializeArray(),
	   	    timeout: 5000,
	   	  })
	   	  .done(function(data) {
	   		  console.log(data);
	   	      // 通信成功時の処理を記述
	   	  })
	   	  .fail(function() {
	   	      // 通信失敗時の処理を記述
	   	  });
      // ユーザ名のテキストボックスのオブジェクト取得
      /* var user = document.getElementById("user"); */
      // 送信メッセージを作成するテキストボックスのオブジェクト取得。
      /* var message = document.getElementById("textMessage"); */
      // コンソールのテキストボックスに転送するメッセージを出力する。
      /* messageTextArea.value += user.value + "(me) => " + message.value + "\n"; */
      // WebSocketサーバにメッセージを転送する。(形式「{{ユーザ名}}メッセージ」)
      /* webSocket.send("{{" + user.value + "}}" + message.value); */
      // 送信メッセージを作成するテクストボックスを初期化する。
      message.value = "";
    }
    // Disconnectボタンを押下すると呼ばれる関数。
    function disconnect() {
      // WebSocket切断
      webSocket.close();
    }
  </script>
</body>
</html>