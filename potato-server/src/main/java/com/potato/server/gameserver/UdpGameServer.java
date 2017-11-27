package com.potato.server.gameserver;

import com.potato.server.common.NettyConfig;
import com.potato.server.common.NettyUDPServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 11:41
 * Description:
 */
public class UdpGameServer extends NettyUDPServer {
    public UdpGameServer(NettyConfig nettyConfig, ChannelInitializer<? extends Channel> channelInitializer) {
        super(nettyConfig, channelInitializer);
    }
}
