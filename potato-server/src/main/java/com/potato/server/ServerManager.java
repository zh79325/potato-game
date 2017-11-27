package com.potato.server;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 11:39
 * Description:
 */
public interface ServerManager {

     void startServers(int tcpPort, int websocketPort, int udpPort) throws Exception;


     void stopServers() throws Exception;
}
