package com.potato.server;

import com.potato.core.config.GameServerConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/25 18:30
 * Description:
 */
@ComponentScan(basePackages = {"com.potato"})
@SpringBootApplication
@EnableAutoConfiguration
public class MainGameServer {

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> props = new HashMap<>();
        props.put("server.WEB_PORT", GameServerConfig.WEB_PORT);
        ApplicationContext context= new SpringApplicationBuilder().sources(MainGameServer.class)
                .properties(props)
                .run(args);

        ServerManager manager=  context.getBean(ServerManager.class);
        manager.startServers(GameServerConfig.TCP_PORT,GameServerConfig.WEB_SOCKET_PORT,GameServerConfig.UDP_PORT);
    }
}
