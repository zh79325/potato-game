package com.potato.core.service;

import com.potato.common.common.Player;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 18:09
 * Description:
 */
public interface PlayerService {
    Player getPlayer(String uid);
}
