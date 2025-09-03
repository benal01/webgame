let socket = new WebSocket('ws://localhost:8080/user');
let messages = []; // {message = type}
const textArea = document.getElementById('textArea');

//message, system
output = function(message, type) {
    messages[messages.length] = [message, type];
    textArea.innerHTML += '<div class="output '+type+'">' + message + ' <span class="timestamp">' + new Date().toLocaleTimeString() + '</span><br> </div>';
}

log = function(message) {
    console.log(message);
    output(message, "system");
}

document.getElementById('loginBtn').onclick = function() {
    const username = document.getElementById('username').value;
    socket.send(username);
    document.getElementById('login').style.display = 'none';
    document.getElementById('chat').style.display = 'block';
    
}

document.getElementById('sendBtn').onclick = function() {
    const message = document.getElementById('messageInput').value;
    log('Sending to server:', message);
    socket.send(message);
}



// Websocket connection
socket.onopen = function(event) {
    log('WebSocket connection opened');
};

socket.onmessage = function(event) {
    output(event.data, "message");
};

socket.onclose = function(event) {
    log('WebSocket connection closed');
};

socket.onerror = function(error) {
    log('WebSocket error:', error);
};