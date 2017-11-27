package com.potato.core.app;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 15:48
 * Description:
 */
public interface SessionStorage {
    Session newSession();
    Session newSession(String id);
    Session getSession(String id);
}
