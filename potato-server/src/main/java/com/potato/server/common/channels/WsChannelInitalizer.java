package com.potato.server.common.channels;

import com.potato.server.common.handlers.WsServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * @author <a href="http://www.waywardmonkeys.com/">Bruce Mitchener</a>
 */
public class WsChannelInitalizer extends ChannelInitializer<SocketChannel>
{

	// TODO make this configurable from spring.
	private static final int MAX_IDLE_SECONDS = 60;

	int port;

	public WsChannelInitalizer(int port){
		this.port=port;

	}
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("idleStateCheck", new IdleStateHandler(
				MAX_IDLE_SECONDS, MAX_IDLE_SECONDS, MAX_IDLE_SECONDS));
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new HttpObjectAggregator(65536));
		pipeline.addLast(new WebSocketServerCompressionHandler());
        pipeline.addLast(new WsServerHandler());
	}
}
