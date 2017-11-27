package com.potato.server.configuration;

import com.potato.core.config.GameServerConfig;
import com.potato.server.common.AbstractNettyServer;
import com.potato.server.common.NettyConfig;
import com.potato.server.common.NettyTCPServer;
import com.potato.server.common.NettyUDPServer;
import com.potato.server.common.channels.TcpChannelInitializer;
import com.potato.server.common.channels.UDPChannelInitializer;
import com.potato.server.common.channels.WsChannelInitalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 11:49
 * Description:
 */
@Component
public class ServerBuilder {


    @Qualifier("tcpConfig")
    @Autowired
    NettyConfig tcpConfig;


    @Qualifier("udpConfig")
    @Autowired
    NettyConfig udpConfig;

    public SBuilder getTcpBuilder() {
        return new TcpBuilder();
    }

    public SBuilder getWsBuilder() {
        return new WsBuilder();
    }

    public SBuilder getUdpBuilder() {
        return new UdpBuilder();
    }

    public interface SBuilder {

        AbstractNettyServer build();
    }

    class TcpBuilder implements SBuilder {

        @Override
        public AbstractNettyServer build() {

            NettyTCPServer tcpServer = new NettyTCPServer(tcpConfig, new TcpChannelInitializer());
            return tcpServer;
        }
    }

    class WsBuilder implements SBuilder {

        @Override
        public AbstractNettyServer build() {
            NettyTCPServer wsServer = new NettyTCPServer(tcpConfig, new WsChannelInitalizer(GameServerConfig.WEB_SOCKET_PORT));
            return wsServer;
        }
    }

    class UdpBuilder implements SBuilder {

        @Override
        public AbstractNettyServer build() {
            NettyUDPServer wsServer = new NettyUDPServer(udpConfig, new UDPChannelInitializer());
            return wsServer;
        }
    }
}
