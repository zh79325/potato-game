package com.potato.server.configuration;

import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadFactory;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 12:01
 * Description:
 */
@Configuration
public class EventLoopConfig {

    int bossThreadCount = 2;

    int workerThreadCount = 2;

    @Qualifier("bossGroup")
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup getBossGroup(@Qualifier("bossThreadFactory")@Autowired ThreadFactory threadFactory) {
        NioEventLoopGroup group = new NioEventLoopGroup(bossThreadCount);
        return group;
    }

    @Qualifier("workerGroup")
    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup getWorkerGroup(@Qualifier("workerThreadFactory")@Autowired ThreadFactory threadFactory) {
        NioEventLoopGroup group = new NioEventLoopGroup(workerThreadCount);
        return group;
    }

    @Qualifier("bossThreadFactory")
    @Bean
    ThreadFactory getBossThreadFactory() {
        ThreadFactory executor = new NamedThreadFactory("Server-Boss");
        return executor;
    }

    @Qualifier("workerThreadFactory")
    @Bean
    ThreadFactory getWorkerThreadFactory() {
        ThreadFactory executor = new NamedThreadFactory("Server-Worker");
        return executor;
    }

}
