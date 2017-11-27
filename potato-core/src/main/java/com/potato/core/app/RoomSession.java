package com.potato.core.app;

import com.potato.common.common.GameRoom;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 17:39
 * Description:
 */
public interface RoomSession extends Session {

    GameRoom getRoom();
}
