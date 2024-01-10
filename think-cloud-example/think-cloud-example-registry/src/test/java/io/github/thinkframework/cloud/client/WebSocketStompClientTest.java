package io.github.thinkframework.cloud.client;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class WebSocketStompClientTest {

    @Test
    public void a() throws InterruptedException, ExecutionException {
        WebSocketClient webSocketClient = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new StringMessageConverter());
//        stompClient.setTaskScheduler(taskScheduler); // for heartbeats

        CountDownLatch countDownLatch = new CountDownLatch(1);
        String url = "ws://127.0.0.1:18761/stomp";
        ListenableFuture<StompSession> listenableFuture =  stompClient.connect(url, new StompSessionHandlerAdapter(){
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                session.subscribe("/topic/greetings", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return null;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        System.out.println(payload);
                        countDownLatch.countDown();
                    }
                });
                session.send("/app/string","{\"name\":\"hello\"}");
//
//                session.

            }
        });
        StompSession session = listenableFuture.get();
        countDownLatch.await();
    }


    @Test
    public void c() throws InterruptedException, ExecutionException {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
//        webSocketClient.

        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new StringMessageConverter());
//        stompClient.setTaskScheduler(taskScheduler); // for heartbeats

        CountDownLatch countDownLatch = new CountDownLatch(1);
        String url = "ws://127.0.0.1:18761/stomp";
        ListenableFuture<StompSession> listenableFuture =  stompClient.connect(url, new StompSessionHandlerAdapter(){
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                session.subscribe("/topic/greetings", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return String.class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        System.out.println(payload);
                        countDownLatch.countDown();
                    }
                });
                session.send("/app/subscribe","{\"name\":\"hello\"}");
//
//                session.

            }
        });
        StompSession session = listenableFuture.get();
        countDownLatch.await();
    }


    @Test
    public void b() throws InterruptedException, ExecutionException {
        WebSocketClient webSocketClient = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new StringMessageConverter());
//        stompClient.setTaskScheduler(taskScheduler); // for heartbeats

        CountDownLatch countDownLatch = new CountDownLatch(1);
        String url = "ws://127.0.0.1:18761/stomp";
        ListenableFuture<StompSession> listenableFuture =  stompClient.connect(url, new StompSessionHandlerAdapter(){
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                session.subscribe("/topic/greetings", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return null;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        System.out.println(payload);
                        countDownLatch.countDown();
                    }
                });
                session.send("/app/abc","{\"name\":\"hello\"}");
//
//                session.

            }
        });
        StompSession session = listenableFuture.get();
        countDownLatch.await();
    }
}
