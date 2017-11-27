package com.potato.core.app;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 15:37
 * Description:
 */
public interface SessionFactory {
    Session newSession();
    Session getSession(String id);
}
