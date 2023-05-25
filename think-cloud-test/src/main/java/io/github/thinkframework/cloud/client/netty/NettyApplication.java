package io.github.thinkframework.cloud.client.netty;

import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.boot.web.server.WebServerFactory;

/**
 * 测试类
 * 会被 spring boot启动类替代
 */
public class NettyApplication implements WebServerFactory {
    protected static final Logger log = LoggerFactory.getLogger(NettyApplication.class);
    public static void main(String[] args) {
        WebServer webServer = new NettyServerFactory().getWebServer(new StringDecoder(),
                new StringEncoder(),
                new ServerChannelInboundHandler());
        try {
            webServer.start(); // 启动服务
        } catch (WebServerException e) {
            log.error(e.getMessage(),e);
            webServer.stop();
        }
    }
}
