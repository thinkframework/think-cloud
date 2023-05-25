package io.github.thinkframework.cloud.client.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerFactory;

/**
 * 测试类
 * 会被 spring boot启动类替代
 */
public class NettyWebServerFactory implements WebServerFactory {
    public WebServer getWebServer(ChannelHandler childHandler) {

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 主线程, 默认线程池大小, cpu core * 2;
        EventLoopGroup workerGroup = new NioEventLoopGroup(20); // 工作线程 todo 传入

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(childHandler)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // 工作线程的选项

        return new NettyWebServer(serverBootstrap);
    }
}
