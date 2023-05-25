package io.github.thinkframework.cloud.client.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class ClientChannelInboundHandler extends ChannelInboundHandlerAdapter {

    protected final Logger log = LoggerFactory.getLogger(ClientChannelInboundHandler.class);


    @Override
    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.debug("channelRegistered: {}",channelHandlerContext);

    }

    @Override
    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.debug("channelUnregistered: {}",channelHandlerContext);

    }

    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.debug("channelActive: {}",channelHandlerContext);

    }

    @Override
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.debug("channelInactive: {}",channelHandlerContext);

    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        log.debug("channelRead: {}",channelHandlerContext);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.debug("channelReadComplete: {}",channelHandlerContext);

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        log.debug("userEventTriggered: {}",channelHandlerContext);

    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.debug("channelWritabilityChanged: {}",channelHandlerContext);

    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.debug("handlerAdded: {}",channelHandlerContext);

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        log.debug("handlerRemoved: {}",channelHandlerContext);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {
        log.error("exceptionCaught",channelHandlerContext);

    }
}
