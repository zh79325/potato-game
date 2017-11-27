package com.potato.core.app.impl;


import com.potato.core.app.Session;
import com.potato.core.app.SessionField;
import com.potato.core.service.SessionStorage;
import javafx.event.EventDispatcher;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.potato.core.app.SessionField.*;


/**
 * The default implementation of the session class. This class is responsible
 * for receiving and sending events. For receiving it uses the
 * {@link #onEvent(Event)} method and for sending it uses the
 * {@link EventDispatcher} fireEvent method. The Method {@link #setId(Object)}
 * will throw {@link IllegalArgumentException} in this implementation class.
 *
 * @author Abraham Menacherry
 */
public abstract class DefaultSession implements Session {


    protected SessionStorage sessionStorage;
    /**
     * session id
     */
    protected  String id;

    public DefaultSession(String id) {
        this.id = id;
    }


    protected DefaultSession buildNewSession(SessionBuilder sessionBuilder) {
        Map<SessionField, Object> map = sessionBuilder.build();
        id = buildId();
        map.put(SessionField.ID, id);
        sessionStorage.saveSession(id, map);
        updateExpire();
        return this;
    }

    protected abstract String buildId();



    public static class SessionBuilder {
        Map<SessionField, Object> map = new HashMap<>();
        public Map<SessionField, Object> build() {
            long now = new Date().getTime();
            map.put(LAST_UPDATE_TIME, now);
            map.put(CREATE_TIME, now);
            return map;
        }
        public SessionBuilder status(Status status) {
            map.put(SessionField.STATUS, status);
            return this;
        }
        public <T> SessionBuilder data(T data) {
            map.put(SessionField.DATA_FIELD, data);
            return this;
        }
        public <T> SessionBuilder data(SessionField field,T data) {
            map.put(field, data);
            return this;
        }
        public <T> SessionBuilder value(String key, T value) {
            Map<String, Object> attrs = (Map<String, Object>) map.get(SessionField.ATTRIBUTES);
            if (attrs == null) {
                attrs = new HashMap<>();
                map.put(SessionField.ATTRIBUTES, attrs);
            }
            attrs.put(key, value);
            return this;
        }
    }


    @Override
    public String getId() {
        return id;
    }


    @Override
    public Object getAttribute(String key) {
        Map<String, Object> attrs = getSessionAttributes();
        if (attrs == null) {
            return null;
        }
        return attrs.get(key);
    }

    @Override
    public void removeAttribute(String key) {
        Map<String, Object> attrs = getSessionAttributes();
        if (attrs == null) {
            return;
        }
        attrs.remove(key);
        updateSession(SessionField.ATTRIBUTES, attrs);
    }

    @Override
    public void setAttribute(String key, Object value) {
        Map<String, Object> attrs = getSessionAttributes();
        if (attrs == null) {
            attrs = new HashMap<>();
        }
        attrs.put(key, value);
        updateSession(SessionField.ATTRIBUTES, attrs);
    }

    <T> void updateSession(SessionField field, T value) {
        Map<SessionField, Object> map = new HashMap<>();
        long now = new Date().getTime();
        map.put(LAST_UPDATE_TIME, now);
        map.put(field, value);
        sessionStorage.saveSession(id, map);

    }

    void updateExpire() {
        sessionStorage.expire(id, 30);
    }

    @Override
    public long getCreationTime() {
        long creationTime = sessionStorage.getValue(id, SessionField.CREATE_TIME, Long.class);
        return creationTime;
    }

    @Override
    public long getLastUpdateTime() {
        long creationTime = sessionStorage.getValue(id, SessionField.LAST_UPDATE_TIME, Long.class);
        return creationTime;
    }


    @Override
    public Status getStatus() {
        Status status = sessionStorage.getValue(id, SessionField.STATUS, Status.class);
        return status;
    }

    @Override
    public boolean isConnected() {
        return getStatus() == Status.CONNECTED;
    }


    @Override
    public void setStatus(Status status) {
        updateSession(SessionField.STATUS, status);

    }

    @Override
    public boolean exists() {
        return sessionStorage.existSession(id);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public synchronized void destory() {
        sessionStorage.removeSession(id);
    }


    public Map<String, Object> getSessionAttributes() {
        return sessionStorage.getValue(id, SessionField.ATTRIBUTES, Map.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultSession that = (DefaultSession) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public void setSessionStorage(SessionStorage storage) {
        this.sessionStorage=storage;
    }
}
