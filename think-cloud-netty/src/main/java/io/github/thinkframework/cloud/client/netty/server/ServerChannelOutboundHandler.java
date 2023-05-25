package io.github.thinkframework.cloud.client.netty.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * 调试用
 */
public class ServerChannelOutboundHandler extends ChannelOutboundHandlerAdapter {

    protected final Logger log = LoggerFactory.getLogger(ServerChannelOutboundHandler.class);

}
