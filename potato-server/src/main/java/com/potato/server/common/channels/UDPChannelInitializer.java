package com.potato.server.common.channels;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.DatagramChannel;


public class UDPChannelInitializer extends ChannelInitializer<DatagramChannel>
{


	@Override
	protected void initChannel(DatagramChannel ch) throws Exception {
		// pipeline is shared across all channels.
		ChannelPipeline pipeline = ch.pipeline();
	}

	
}
