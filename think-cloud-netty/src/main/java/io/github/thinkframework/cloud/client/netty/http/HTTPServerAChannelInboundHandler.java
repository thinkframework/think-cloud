package io.github.thinkframework.cloud.client.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;

import java.nio.charset.Charset;

/**
 * 调试用
 */
public class HTTPServerAChannelInboundHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private Netty4ClientHttpRequestFactory netty4ClientHttpRequestFactory;
    protected final Logger log = LoggerFactory.getLogger(HTTPServerAChannelInboundHandler.class);

    /**
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {

    }

    /**
     * Calls {@link ChannelHandlerContext#fireChannelReadComplete()} to forward
     * to the next {@link ChannelInboundHandler} in the {@link ChannelPipeline}.
     * <p>
     * Sub-classes may override this method to change behavior.
     *
     * @param ctx
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);

        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        fullHttpResponse.headers().add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        fullHttpResponse.content().writeBytes(Unpooled.copiedBuffer("{}", Charset.defaultCharset()));
        fullHttpResponse.release();

        ctx.writeAndFlush(fullHttpResponse).addListeners(ChannelFutureListener.CLOSE);
    }
}
