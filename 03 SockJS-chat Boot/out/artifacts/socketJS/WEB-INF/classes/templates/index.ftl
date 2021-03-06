<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Чат</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script>
        let webSocket;
        function connect() {
            webSocket = new SockJS("http://localhost:8081/chat");
            document.cookie = 'X-Authorization=' + '12345' + ';path=/';

            webSocket.onmessage = function receiveMessage(response) {
                let data = response['data'];
                let json = JSON.parse(data);
                $('#messagesList').first().before("<li>" + json['from'] + ' ' + json['text'] + "</li>")
            }

        }

        function sendMessage(text, pageId, room) {
            let message = {
                "text": text,
                "from": pageId,
                "room": room
            };

            webSocket.send(JSON.stringify(message));
        }
    </script>
</head>
<body onload="connect()">
<div>
    <label for="message">Текст сообщения</label>
    <input name="message" id="message" placeholder="Сообщение">
    <button onclick="sendMessage($('#message').val(), '${from}', parseInt(window.location.pathname.split('/')[2]))">Отправить</button>
    <h3>Сообщения</h3>
    <#list messages >
        <ul>
            <#items as message>
                <li id = "messagesList">${message.getFrom().name} ${message.text}</li>
            </#items>
        </ul>
    </#list>
</div>
</body>
</html>