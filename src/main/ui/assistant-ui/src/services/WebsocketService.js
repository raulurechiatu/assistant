// import { Client } from '@stomp/stompjs';
// import $ from "jquery";
// // import Helper from "@/services/Helper";
//
// const stompClient = new Client({
//     brokerURL: 'ws://localhost:8080/chat'
// });
//
// console.log(stompClient);
//
// stompClient.onConnect = (frame) => {
//     setConnected(true);
//     console.log('Connected: ' + frame);
//     stompClient.subscribe('/topic/messages', (greeting) => {
//         showGreeting(JSON.parse(greeting.body).content);
//     });
// };
//
// stompClient.onWebSocketError = (error) => {
//     console.error('Error with websocket', error);
// };
//
// stompClient.onStompError = (frame) => {
//     console.error('Broker reported error: ' + frame.headers['message']);
//     console.error('Additional details: ' + frame.body);
// };
//
// function setConnected(connected) {
//     $("#connect").prop("disabled", connected);
//     $("#disconnect").prop("disabled", !connected);
//     if (connected) {
//         $("#conversation").show();
//     }
//     else {
//         $("#conversation").hide();
//     }
//     $("#greetings").html("");
// }
//
// function connect() {
//     console.log("Connect");
//     stompClient.activate();
// }
//
// function disconnect() {
//     stompClient.deactivate();
//     setConnected(false);
//     console.log("Disconnected");
// }
//
// function sendName() {
//     stompClient.publish({
//         destination: "/app/hello",
//         body: JSON.stringify({'name': $("#name").val()})
//     });
// }
//
// function showGreeting(message) {
//     $("#greetings").append("<tr><td>" + message + "</td></tr>");
// }
//
// $(function () {
//     console.log("TEST");
//     $("form").on('submit', (e) => e.preventDefault());
//     $( "#connect" ).click(() => connect());
//     $( "#disconnect" ).click(() => disconnect());
//     $( "#send" ).click(() => sendName());
// });

import ShelfItemService from "@/services/ShelfItemService";

class WebsocketService {

    init() {
        const websocket = new WebSocket("ws://localhost:8080/items");
        websocket.onopen    = this.onSocketOpen;
        websocket.onmessage = this.onSocketMessage;
        websocket.onerror   = this.onSocketError;
        return websocket;
    }

    onSocketOpen(){
        this.connection_ready = true;
    }

    onSocketMessage(evt){
        //we parse the json that we receive
        var message = evt.data;
        if (!message) return;
        message = JSON.parse(message);
        if (message.type === "ShelfItem") {
            ShelfItemService.processMessage(message.entity);
        }
        // if (!this.messages) this.messages = [];
        //check if it's our message or from a friend
        // this.messages.push( "" + message );
        //scroll to the bottom of the messages div
        // const messages_div = document.getElementById('messages');
        // messages_div.scrollTo({top: messages_div.scrollHeight, behavior: 'smooth'});
    }

    onSocketError(){
        this.connection_error = true;
    }
}

export default new WebsocketService();