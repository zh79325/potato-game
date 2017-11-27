package com.potato.controller;

import com.potato.core.config.GameServerConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/25 17:49
 * Description:
 */
@RestController
@RequestMapping("api")
public class ConfigController {
    @RequestMapping(value = "getServerConfig", method = RequestMethod.GET)
    public ServerConfig getServerConfig()   {
        ServerConfig config = new ServerConfig();
        config.setWebsocket("localhost:"+ GameServerConfig.WEB_SOCKET_PORT+"/websocket");
        return config;
    }
}
