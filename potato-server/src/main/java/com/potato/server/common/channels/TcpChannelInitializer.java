package com.potato.server.common.channels;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;


public class TcpChannelInitializer extends
	ChannelInitializer<SocketChannel>
{
	// TODO make this configurable from spring.
	private static final int MAX_IDLE_SECONDS = 60;

	@Override
	protected void initChannel(SocketChannel ch) throws Exception 
	{
		// Create a default pipeline implementation.
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("idleStateCheck", new IdleStateHandler(
				MAX_IDLE_SECONDS, MAX_IDLE_SECONDS, MAX_IDLE_SECONDS));
		pipeline.addLast("multiplexer", createProtcolMultiplexerDecoder());
	}

	protected ChannelHandler createProtcolMultiplexerDecoder()
	{
		return null;
	}


}
