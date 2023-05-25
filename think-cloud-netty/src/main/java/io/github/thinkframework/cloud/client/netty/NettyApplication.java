package io.github.thinkframework.cloud.client.netty;

import io.github.thinkframework.cloud.client.netty.server.ServerChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.boot.web.server.WebServerFactory;

/**
 * 启动类
 * 会被 spring boot启动类替代
 */
public class NettyApplication {
    protected static final Logger log = LoggerFactory.getLogger(NettyApplication.class);

    public static void main(String[] args) {
        WebServer webServer = new NettyWebServerFactory()
                .getWebServer(new ChannelInitializer<SocketChannel>() { // 添加工作线程的hanler
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new StringDecoder(),
                                new StringEncoder(),
                                new ServerChannelInboundHandler());
                    }
                });
        try {
            log.debug("启动中.");
            webServer.start(); // 启动服务
            log.debug("启动成功.");
        } catch (WebServerException e) {
            log.error(e.getMessage(), e);
            webServer.stop();
        }
    }
}
