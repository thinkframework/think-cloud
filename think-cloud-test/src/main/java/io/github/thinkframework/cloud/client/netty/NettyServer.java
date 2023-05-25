package io.github.thinkframework.cloud.client.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerException;

/**
 *
 */
public class NettyServer implements WebServer {
    private ServerBootstrap serverBootstrap;

    public NettyServer(ServerBootstrap serverBootstrap) {
        this.serverBootstrap = serverBootstrap;
    }

    /**
     * Starts the web server. Calling this method on an already started server has no
     * effect.
     *
     * @throws WebServerException if the server cannot be started
     */
    @Override
    public void start() throws WebServerException {

        ChannelFuture channelFuture = null;
        try {
            channelFuture = serverBootstrap.bind(8888).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new WebServerException("启动失败.", e);
        }
    }

    /**
     * Stops the web server. Calling this method on an already stopped server has no
     * effect.
     *
     * @throws WebServerException if the server cannot be stopped
     */
    @Override
    public void stop() throws WebServerException {
        serverBootstrap.config().group().shutdownGracefully();
        serverBootstrap.config().childGroup().shutdownGracefully();
    }

    /**
     * Return the port this server is listening on.
     *
     * @return the port (or -1 if none)
     */
    @Override
    public int getPort() {
        return 8888;
    }
}
