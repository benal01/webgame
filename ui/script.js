// Replace 'localhost:8080' with your server's host and port if different
let socket = new WebSocket('ws://localhost:8080/user');

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