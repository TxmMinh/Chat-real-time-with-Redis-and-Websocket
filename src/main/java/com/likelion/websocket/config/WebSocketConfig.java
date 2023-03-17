package com.likelion.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // bật tính năng WebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Từ STOMP trong tên phương thức đến từ Spring Frameworks STOMP implementation
        // Đăng ký một websocket endpoint mà các máy khách sẽ sử dụng để kết nối với máy chủ /ws
        registry.addEndpoint("/ws").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Xác định các thư có đích bắt đầu bằng "/app" sẽ được định tuyến đến các phương thức xử lý tín nhắn
        // Chỉ định tiền tố /app cho các message gửi từ client lên server.
        registry.setApplicationDestinationPrefixes("/app");

        // Các thư có đích bắt đầu bằng “/topic” được định tuyến tới Message Broker (ở đây đang dùng bộ nhớ máy)
        // Chuyển các message từ server về lại client trên các đường dẫn có tiền tố là /topic.
        // Có thể sử dụng Message Broker bất kỳ để thay thế ví dụ RabbitMQ hoặc ActiveMQ
        registry.enableSimpleBroker("/topic");

        /**
         * Message Broker là một phần mềm trung gian giữa các ứng dụng để truyền tải thông điệp giữa chúng.
         * Có nhiều loại Message Broker khác nhau, nhưng trong ví dụ này, chúng ta sử dụng Simple Broker.
         */
    }
}
