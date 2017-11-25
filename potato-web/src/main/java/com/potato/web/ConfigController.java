package com.potato.web;

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
public class ConfigController {
    @RequestMapping(value = "/getServerConfig", method = RequestMethod.GET)
    public ServerConfig getServerConfig()   {
        ServerConfig config = new ServerConfig();
        config.setWebsocket("localhost:10001");
        return config;
    }
}
