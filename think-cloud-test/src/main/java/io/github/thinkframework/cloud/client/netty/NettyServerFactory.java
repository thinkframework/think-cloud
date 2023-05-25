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
 *
 */
public class NettyServerFactory implements WebServerFactory {
    public WebServer getWebServer(ChannelHandler... handlers) {

        EventLoopGroup parentGroup = new NioEventLoopGroup(); // 默认线程池大小, cpu core * 2;
        EventLoopGroup childGroup = new NioEventLoopGroup(20); // todo 传入

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(parentGroup, childGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() { // 添加工作线程的hanler
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(handlers);
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true); // 工作线程的选项

        return new NettyServer(serverBootstrap);
    }
}
