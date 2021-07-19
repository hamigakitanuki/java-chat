
/*------------------------- データ -------------------------*/
var chatRoomId; // チャットルームID
var chatRoomMemberId; // チャットルームメンバーID
var webSocket // WebSocketのインスタンス

/*------------------------- メッセージ送信 -------------------------*/
function sendMessage() {
  let messageForm = document.querySelector('#message_form');
  let message = messageForm.value
  messageForm.value = "";

  // setMessage({
  //   message: message,
  //   chat_room_member_id: chatRoomMemberId
  // });
  $.ajax({
    url: 'ChatMessage',
    type: 'POST',
    // フォーム要素の内容をハッシュ形式に変換
    data: {
      'message': message,
      'chat_room_id': chatRoomId,
      'chat_room_member_id': chatRoomMemberId,
    },
    timeout: 5000,
  })
    .done(function (data) {
      // 通信成功時の処理を記述
      console.log(data);

    })
    .fail(function () {
      // 通信失敗時の処理を記述
      console.log("get messages error");
      return false;
    });
}

/*------------------------- チャットルームオープン -------------------------*/
function openChatRoom(chat_room_id) {
  chatRoomId = chat_room_id
  resetList();
  disconnect();
  getMessages();
}
/*------------------------- メッセージ一覧取得 -------------------------*/
function getMessages() {
  /* メッセージ一覧を削除 */
  $.ajax({
    url: 'ChatMessage',
    type: 'GET',
    contentType: 'application/json',
    // フォーム要素の内容をハッシュ形式に変換
    data: {
      'chat_room_id': chatRoomId
    },
    timeout: 5000,
  })
    .done(function (data) {
      // 通信成功時の処理を記述
      console.log(data);
      messages = data.messages;
      console.log(data.messages, messages);
      chatRoomMemberId = data.chat_room_member_id;
      setMessages(messages);
      setSocket();
    })
    .fail(function () {
      // 通信失敗時の処理を記述
      console.log("get messages error");
      return false;
    });

}
function getMessage() {
  /* メッセージ一覧を削除 */
  $.ajax({
    url: 'ChatLatestMessage',
    type: 'GET',
    contentType: 'application/json',
    // フォーム要素の内容をハッシュ形式に変換
    data: {
      'chat_room_id': chatRoomId
    },
    timeout: 5000,
  })
    .done(function (data) {
      // 通信成功時の処理を記述
      messages = data.messages;
      console.log(data.messages, messages);
      setMessages(messages);
    })
    .fail(function () {
      // 通信失敗時の処理を記述
      console.log("get messages error");
      return false;
    });

}
/*------------------------- メッセージ一覧削除 -------------------------*/
function resetList() {
  let messageList = document.querySelector('#message_list');
  messageList.innerHTML = "";
}
/*------------------------- メッセージ一覧表示 -------------------------*/
function setMessages(messages) {
  messages.forEach(message => {
    setMessage(message)
  });
}
/*------------------------- メッセージ追加 -------------------------*/
function setMessage(message) {
  let messageList = document.querySelector('#message_list')
  let messageListWrap = document.querySelector('#message_list_wrap')
  /*------------------------- メッセージの項目 -------------------------*/
  let messageWrap = document.createElement('li')

  /*------------------------- メッセージ内容 -------------------------*/
  let messageContent = document.createElement('p')
  messageContent.innerText = message.message

  /*------------------------- メッセージのアイコン -------------------------*/
  let messageIcon = document.createElement('span')
  messageIcon.innerText = message.message.substr(0, 1)
  messageIcon.classList.add('icon');
  messageIcon.classList.add('fx_al_center');
  messageIcon.classList.add('fx_ju_center');
  messageIcon.classList.add('icon_col_1');

  /*------------------------- 自分のメッセージは右 それ以外は左 -------------------------*/
  if (message.chat_room_member_id == chatRoomMemberId) {
    messageWrap.classList.add('right_messege');
    messageWrap.appendChild(messageContent);
    messageWrap.appendChild(messageIcon);
  } else {
    messageWrap.classList.add('left_messege');
    messageWrap.appendChild(messageIcon);
    messageWrap.appendChild(messageContent);
  }
  messageList.appendChild(messageWrap)

  messageListWrap.scrollTop = messageListWrap.scrollHeight

}

/*------------------------- ブロードキャスト関連 -------------------------*/

function setSocket() {
  // WebSocketオブジェクト生成(接続開始)
  webSocket = new WebSocket("ws://" + location.hostname + ":8080/WebSocket/broadsocket");
  // WebSocketが接続成功になれば呼ばれる関数。
  webSocket.onopen = function (message) {
    // コンソールにメッセージ出力
    console.log("Server connect");
  };
  // WebSocketが切断なれば呼ばれる関数。
  webSocket.onclose = function (message) {
    // コンソールにメッセージ出力
    console.log("Server Disconnect");
  };
  // WebSocketからエラーが発生する時に呼ばれる関数。
  webSocket.onerror = function (message) {
    // コンソールにメッセージ出力
    console.log("error");
  };
  // WebSocketからメッセージを受け取ったら呼ばれる関数。
  webSocket.onmessage = function (message) {
    // コンソールにメッセージ出力
    console.log("receive");
    getMessage();

  };
}

// Disconnectボタンを押下すると呼ばれる関数。
function disconnect() {
  if (webSocket) {
    // WebSocket切断
    webSocket.close();
  }
}
