package com.potato.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/25 11:31
 * Description:
 */

@ComponentScan(basePackages = {"com.potato"})
@SpringBootApplication
public class PotatoWebMain {
    public static void main(String[] args) {
        SpringApplication.run(PotatoWebMain.class, args);
    }
}
