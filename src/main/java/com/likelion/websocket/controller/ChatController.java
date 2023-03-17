package com.likelion.websocket.controller;

import com.likelion.websocket.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    // Một thư có đích /app/chat.sendMessage sẽ được định tuyến tơi phương thức sendMessage() được chú thích bằng @MessageMapping
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public") // gửi tin nhắn đến tất cả các khách hàng đang đăng ký với đường dẫn "/topic/public"
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    // Một thư có đích /app/chat.addUser sẽ được định tuyến tới phương thức addUser()
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
