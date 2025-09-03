let socket = new WebSocket('ws://localhost:8080/user');
let messages = []; // {message = type}
const textArea = document.getElementById('textArea');

//message, system
output = function(message, type) {
    messages[messages.length] = [message, type];
    textArea.innerHTML += '<div class="output '+type+'">' + message + ' <span class="timestamp">' + new Date().toLocaleTimeString() + '</span><br> </div>';
}



output("One", "message");
output("Two", "message");
output("Three", "message");
output("Four", "message");


output("System message", "system");


document.getElementById('loginBtn').onclick = function() {
    const username = document.getElementById('username').value;
    socket.send(username);
    document.getElementById('login').style.display = 'none';
    document.getElementById('chat').style.display = 'block';
    
}

document.getElementById('sendBtn').onclick = function() {
    const message = document.getElementById('messageInput').value;
    console.log('Sending to server:', message);
    socket.send(message);
}

// Websocket connection
socket.onopen = function(event) {
    console.log('WebSocket connection opened');
};

socket.onmessage = function(event) {
    console.log('Received from server:', event.data);
};

socket.onclose = function(event) {
    console.log('WebSocket connection closed');
};

socket.onerror = function(error) {
    console.error('WebSocket error:', error);
};