<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ page import="java.util.*" %>
    <%@ page import="chat.*" %>
      <%@ page import="dao.bean.*" %>
        <!DOCTYPE html>
        <html lang="ja">

        <head>
          <meta charset="UTF-8">
          <meta http-equiv="X-UA-Compatible" content="IE=edge">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <link rel="shortcut icon" href="dist/favicon.ico">
          <title>スマートチャット</title>
          <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
          <link rel="stylesheet" href="dist/app.css">
        </head>

        <body>
          <header class="header_nav fx_al_center">
            <span class="fs_l">スマートチャット</span>
          </header>
          <div class="chat">
            <!-- チャットルームリスト -->
            <aside>
              <div class="chat_profile fx_al_center_block fx_column_block mt_30">
                <span class="profile_icon fx_al_center fx_ju_center icon_col_1">
                  <%= ((String)request.getAttribute("userName")).substring(0, 1) %>
                </span>
                <p class="mt_15 fs_m">
                  <%= request.getAttribute("userName") %>
                </p>

                <div class="fx_ju_center mt_15">
                  <button class="el_btn_theme fx_al_center" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    新しい部屋を作る
                    <svg class="icon_svg ml_10" aria-hidden="true" focusable="false" data-prefix="fas" data-icon="plus" class="svg-inline--fa fa-plus fa-w-14" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
                      <path fill="currentColor" d="M416 208H272V64c0-17.67-14.33-32-32-32h-32c-17.67 0-32 14.33-32 32v144H32c-17.67 0-32 14.33-32 32v32c0 17.67 14.33 32 32 32h144v144c0 17.67 14.33 32 32 32h32c17.67 0 32-14.33 32-32V304h144c17.67 0 32-14.33 32-32v-32c0-17.67-14.33-32-32-32z"></path>
                    </svg>
                  </button>
                </div>
              </div>
              <!-- チャットルーム作成 Modal -->
              <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">チャットルームを作成</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <form id="create_chat_room_form" class="fx_column_block" action="ChatRoom" method="post">
                        <label class="fx_column_block fs_m">
                          チャットルーム名(必須)
                          <input class="el_form_input" type="text" name="chat_room_name" placeholder="">
                        </label>
                        <label class="fx_column_block mt_30 fs_m">
                          パスワード
                          <input class="el_form_input" type="text" name="password" placeholder="">
                        </label>

                      </form>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">キャンセル</button>
                      <button type="button" class="btn btn-primary" onclick="document.querySelector('#create_chat_room_form').submit()">作成</button>
                    </div>
                  </div>
                </div>
              </div>

              <!-- 招待 Modal -->
              <div class="modal fade" id="inviteChatRoom" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">チャットルーム招待</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                      <div class="fx_column_block">
                        招待URL
                        <p id="invite_url" class="mt_15 fs_s"></p>
                        <button class="mt_15 el_btn_theme">コピー</button>

                      </div>
                    </div>
                  </div>
                </div>
              </div>

              <jsp:useBean id="chatRoomBean" class="dao.bean.ChatRoomBean" scope="request" />
              <!-- チャットルームが存在するときのみ表示 -->
              <% if (chatRoomBean !=null) { %>
                <ul class="chat_room_list fx_column_block mt_30">
                  <% ArrayList<ChatRoomRecordBean> chatRoomRecordArray = chatRoomBean.getRecordArray();
                    for(ChatRoomRecordBean record : chatRoomRecordArray){
                    %>
                    <li class="fx_al_center_block fx_ju_between_block" onclick="openChatRoom(<%= record.getId() %>)">
                      <div class="fx_al_center_block">
                        <span class="icon fx_al_center fx_ju_center icon_col_2">
                          <%= record.getChatRoomName().substring(0, 1) %>
                        </span>
                        <p>
                          <%= record.getChatRoomName() %>
                        </p>
                      </div>

                      <span class="icon_wrap" data-bs-toggle="modal" data-bs-target="#inviteChatRoom" onclick="setUrl(<%= record.getId() %>)">
                        <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="user-plus" class="svg-inline--fa fa-user-plus fa-w-20" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512">
                          <path fill="currentColor" d="M624 208h-64v-64c0-8.8-7.2-16-16-16h-32c-8.8 0-16 7.2-16 16v64h-64c-8.8 0-16 7.2-16 16v32c0 8.8 7.2 16 16 16h64v64c0 8.8 7.2 16 16 16h32c8.8 0 16-7.2 16-16v-64h64c8.8 0 16-7.2 16-16v-32c0-8.8-7.2-16-16-16zm-400 48c70.7 0 128-57.3 128-128S294.7 0 224 0 96 57.3 96 128s57.3 128 128 128zm89.6 32h-16.7c-22.2 10.2-46.9 16-72.9 16s-50.6-5.8-72.9-16h-16.7C60.2 288 0 348.2 0 422.4V464c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48v-41.6c0-74.2-60.2-134.4-134.4-134.4z"></path>
                        </svg>
                      </span>

                    </li>
                    <% } %>
                </ul>
                <% } %>
            </aside>

            <div class="chat_main">
              <!-- アナウンス -->
              <div class="chat_announce fx_al_center">
                あいうえお
              </div>

              <!-- メッセージ -->
              <div id="message_list_wrap" class="chat_message_ilst">
                <ul id="message_list" class="fx_column_block">
                  <li>チャットルームが選択されていません。</li>
                </ul>
              </div>

              <!-- チャットフォーム -->
              <div class="form_wrap">
                <label class="send_button fx_al_center fx_ju_center">
                  <input type="file">
                  <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="paperclip" class="svg-inline--fa fa-paperclip fa-w-14" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
                    <path fill="currentColor" d="M43.246 466.142c-58.43-60.289-57.341-157.511 1.386-217.581L254.392 34c44.316-45.332 116.351-45.336 160.671 0 43.89 44.894 43.943 117.329 0 162.276L232.214 383.128c-29.855 30.537-78.633 30.111-107.982-.998-28.275-29.97-27.368-77.473 1.452-106.953l143.743-146.835c6.182-6.314 16.312-6.422 22.626-.241l22.861 22.379c6.315 6.182 6.422 16.312.241 22.626L171.427 319.927c-4.932 5.045-5.236 13.428-.648 18.292 4.372 4.634 11.245 4.711 15.688.165l182.849-186.851c19.613-20.062 19.613-52.725-.011-72.798-19.189-19.627-49.957-19.637-69.154 0L90.39 293.295c-34.763 35.56-35.299 93.12-1.191 128.313 34.01 35.093 88.985 35.137 123.058.286l172.06-175.999c6.177-6.319 16.307-6.433 22.626-.256l22.877 22.364c6.319 6.177 6.434 16.307.256 22.626l-172.06 175.998c-59.576 60.938-155.943 60.216-214.77-.485z"></path>
                  </svg>
                </label>
                <textarea id="message_form" name="" placeholder="メッセージを入力"></textarea>
                <button type="button" onclick="sendMessage()" class="send_button fx_al_center fx_ju_center">
                  <svg aria-hidden="true" focusable="false" data-prefix="fas" data-icon="paper-plane" class="svg-inline--fa fa-paper-plane fa-w-16" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512">
                    <path fill="currentColor" d="M476 3.2L12.5 270.6c-18.1 10.4-15.8 35.6 2.2 43.2L121 358.4l287.3-253.2c5.5-4.9 13.3 2.6 8.6 8.3L176 407v80.5c0 23.6 28.5 32.9 42.5 15.8L282 426l124.6 52.2c14.2 6 30.4-2.9 33-18.2l72-432C515 7.8 493.3-6.8 476 3.2z"></path>
                  </svg>
                </button>
              </div>
            </div>
          </div>

          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
          <script src="dist/jquery-min.js"></script>
          <script src="dist/chat.js"></script>
        </body>


        </html>
