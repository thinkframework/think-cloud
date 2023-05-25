package io.github.thinkframework.cloud.client.netty.http;

import io.github.thinkframework.cloud.client.netty.NettyServerFactory;
import io.github.thinkframework.cloud.client.netty.ServerChannelInboundHandler;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.boot.web.server.WebServerFactory;

/**
 * 测试类
 * 会被 spring boot启动类替代
 */
public class HttpApplication implements WebServerFactory {
    protected static final Logger log = LoggerFactory.getLogger(HttpApplication.class);
    public static void main(String[] args) {
        WebServer webServer = new NettyServerFactory().getWebServer(new HttpRequestDecoder(),
                new HttpObjectAggregator(65535),
                new HttpResponseEncoder(),
                new ChunkedWriteHandler(),
                new ServerChannelInboundHandler());
        try {
            webServer.start(); // 启动服务
        } catch (WebServerException e) {
            log.error(e.getMessage(),e);
            webServer.stop();
        }
    }
}
