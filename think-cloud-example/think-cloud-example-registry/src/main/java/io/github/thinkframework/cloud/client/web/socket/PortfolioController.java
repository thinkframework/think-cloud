package io.github.thinkframework.cloud.client.web.socket;

import java.security.Principal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;


@Controller
public class PortfolioController {

    private static final Log logger = LogFactory.getLog(PortfolioController.class);


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @SubscribeMapping("/topic/greetings")
    public void getPositions(String principal) {
        System.out.println(principal);
    }

    @MessageMapping("/string")
    @SendTo("/topic/greetings")
    public void String(String body) {
        System.out.println(body);
//        return principal;
    }


    @MessageMapping("/subscribe")
    @SendTo("/topic/greetings")
    public String subscribe(String body) {
        System.out.println(body);
        return body;
    }


    @MessageMapping("/simp")
    public String sim(String body) {
        simpMessagingTemplate.send("", new Message<String>() {
            @Override
            public String getPayload() {
                return null;
            }

            @Override
            public MessageHeaders getHeaders() {
                return null;
            }
        });
        return body;
    }

    
    @MessageMapping("/abc")
    @SendTo("/topic/greetings")
    public HelloMessage executeTrade(HelloMessage principal) {
        System.out.println(principal);
        return principal;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}