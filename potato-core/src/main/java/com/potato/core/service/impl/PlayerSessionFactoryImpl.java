package com.potato.core.service.impl;

import com.potato.common.common.Player;
import com.potato.core.app.PlayerSession;
import com.potato.core.service.PlayerService;
import com.potato.core.service.PlayerSessionFactory;
import com.potato.core.service.SessionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 18:16
 * Description:
 */
@Service
public class PlayerSessionFactoryImpl implements PlayerSessionFactory {
    @Autowired
    PlayerService playerService;

    @Autowired
    SessionStorage sessionStorage;


    @Override
    public PlayerSession getOrCreateSession(String uid, String from) {
        PlayerSession session=new PlayerSessionImpl(uid,from);
        session.setSessionStorage(sessionStorage);
        if(!session.exists()){
            Player player=playerService.getPlayer(uid);
            session=session.createNewSession(player);
        }
        return session;
    }
}
