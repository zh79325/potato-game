package com.potato.core.service;

import com.potato.core.app.PlayerSession;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 18:16
 * Description:
 */
public interface PlayerSessionFactory {
    PlayerSession getOrCreateSession(String uid,String from);
}
