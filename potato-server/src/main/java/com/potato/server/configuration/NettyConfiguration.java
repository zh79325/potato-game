package com.potato.server.configuration;

import com.potato.server.common.NettyConfig;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static io.netty.channel.ChannelOption.*;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 13:07
 * Description:
 */
@Configuration
public class NettyConfiguration {


    @Autowired
    @Qualifier("bossGroup")
    NioEventLoopGroup bossGroup;


    @Autowired
    @Qualifier("workerGroup")
    NioEventLoopGroup workerGroup;





    @Bean
    @Qualifier("tcpConfig")
    NettyConfig getTcpNettyConfig(){
        NettyConfig config=new NettyConfig();
        Map<ChannelOption<?>, Object> tcpOptions=new HashMap<>();
        //can get from property ${so.keepalive}
        tcpOptions.put(SO_KEEPALIVE,true);
        //can get from property ${so.backlog}
        tcpOptions.put(SO_BACKLOG,100);


        config.setChannelOptions(tcpOptions);
        config.setBossGroup(bossGroup);
        config.setWorkerGroup(workerGroup);
        config.setPortNumber(-1);
        return config;
    }


    @Qualifier("udpConfig")
    @Bean
    NettyConfig getUdpNettyConfig(){
        NettyConfig config=new NettyConfig();
        Map<ChannelOption<?>, Object> tcpOptions=new HashMap<>();
        //can get from property ${so.sndbuf}
        tcpOptions.put(SO_SNDBUF,65536);
        //can get from property ${so.rcvbuf}
        tcpOptions.put(SO_RCVBUF,65536);
        //can get from property ${so.broadcast}
        tcpOptions.put(SO_BROADCAST,false);


        config.setChannelOptions(tcpOptions);
        config.setBossGroup(bossGroup);
        config.setPortNumber(-1);
        return config;
    }
}
