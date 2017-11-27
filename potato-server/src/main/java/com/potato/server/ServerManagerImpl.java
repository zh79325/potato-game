package com.potato.server;

import com.potato.server.common.AbstractNettyServer;
import com.potato.server.configuration.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.potato.server.ServerManagerImpl.ServerType.*;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 11:45
 * Description:
 */
@Component
public class ServerManagerImpl implements ServerManager {

    @Autowired
    ServerBuilder builder;

    enum ServerType {
        UdpServer, TcpServer, WsServer
    }

    private Map<ServerType, AbstractNettyServer> servers = new HashMap<>();


    @Override
    public void startServers(int tcpPort, int websocketPort, int udpPort) throws Exception {
        if (tcpPort > 0) {
            AbstractNettyServer tcpServer = builder.getTcpBuilder().build();
            tcpServer.startServer(tcpPort);
            servers.put(TcpServer, tcpServer);
        }

        if (websocketPort > 0) {
            AbstractNettyServer wsServer = builder.getWsBuilder().build();
            wsServer.startServer(websocketPort);
            servers.put(WsServer, wsServer);
        }

        if (udpPort > 0) {
            AbstractNettyServer udpServer = builder.getUdpBuilder().build();
            udpServer.startServer(udpPort);
            servers.put(UdpServer, udpServer);
        }
    }


    @Override
    public void stopServers() throws Exception {

    }
}
