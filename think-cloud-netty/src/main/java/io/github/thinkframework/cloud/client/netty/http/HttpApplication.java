package io.github.thinkframework.cloud.client.netty.http;

import io.github.thinkframework.cloud.client.netty.NettyWebServerFactory;
import io.github.thinkframework.cloud.client.netty.server.ServerChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
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
        WebServer webServer = new NettyWebServerFactory().getWebServer(new ChannelInitializer<SocketChannel>() { // 添加工作线程的hanler
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new HttpRequestDecoder(),
                        new HttpObjectAggregator(65535),
                        new HttpResponseEncoder(),
                        new ChunkedWriteHandler(),
                        new ServerChannelInboundHandler());
            }
        });
        try {
            webServer.start(); // 启动服务
        } catch (WebServerException e) {
            log.error(e.getMessage(),e);
            webServer.stop();
        }
    }
}
