package com.potato.core.service;

import com.potato.core.app.SessionField;

import java.util.Map;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 16:06
 * Description:
 */
public interface SessionStorage {
    void saveSession(String id, Map<SessionField, Object> map);

    <T> T getValue(String id, SessionField attributes,Class<T> tClass);

    Long removeSession(String id);

    boolean existSession(String id);

    void expire(String id, int days);
}
