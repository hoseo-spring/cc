'use strict';
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');


var productId = null;
var sellerId = null;
var myId = null;
var opponentId = null;
var stompClient = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
	productId = document.querySelector('#productID').value.trim();
	sellerId = document.querySelector('#sellerID').value.trim();
    myId = document.querySelector('#myID').value.trim();
    opponentId = document.querySelector('#opponentID').value.trim();
	
    if(myId) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
	var channel = (sellerId != myId) ? myId : opponentId;
	// var channel = sellerId;

    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/recv/'+ productId + '.' + channel, onMessageReceived);

    // Tell your username to the server
    //stompClient.send("/app/chat.addUser",
    //    {},
    //    JSON.stringify({sender: username, type: 'JOIN'})
    //)

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    var channel = (sellerId != myId) ? myId : opponentId;
    if(messageContent && stompClient) {
        var chatMessage = {
            productId: productId,
            senderId : myId,
            receiverId : opponentId,
            content: messageInput.value
        };
        stompClient.send("/app/chat/send/" + productId + "." + channel, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    messageElement.classList.add('chat-message');
    
    if(message.senderId == myId) {
    	messageElement.style.borderRadius = "50px 0px 0px 50px";
    	messageElement.style.textAlign = "right";
    	messageElement.style.borderColor = "#69bb66";
    } else {
    	var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.senderId[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.senderId);

        messageElement.appendChild(avatarElement);
        
        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.senderId);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }
    
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    
    var dateElement = document.createElement('p');
    var dateConvert = function(date) {
    	var yyyymmdd = date.substring(0,10);
    	var hh = Number(date.substring(11,13)) + 9;
    	var mm = date.substring(14,16);
    	return yyyymmdd + " " + hh + ":" + mm;
    }
    var dateText = document.createTextNode(dateConvert(message.sendDttm));
    dateElement.appendChild(dateText);
    dateElement.style.color = "gray";
    dateElement.style.fontSize = "8px";

    messageElement.appendChild(textElement);
    messageElement.appendChild(dateElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
	
	var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)