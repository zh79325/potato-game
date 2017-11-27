package com.potato.core.service.impl;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.potato.core.app.SessionField;
import com.potato.core.service.SessionStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Author     : zh_zhou@Ctrip.com
 * Copyright  : Ctrip Copyright (c) 2017
 * Company    : Ctrip
 * Create at  : 2017/11/26 16:26
 * Description:
 */
@Service
public class SessionStorageImpl implements SessionStorage {
    @Autowired
    @Qualifier("redisTemplate00")
    private StringRedisTemplate stringRedisTemplate;


    Map<Class, FastJsonRedisSerializer> serializerMap = new ConcurrentHashMap<>();

    <T> FastJsonRedisSerializer<T> getSerializer(Class<T> tClass) {
        if (!serializerMap.containsKey(tClass)) {
            FastJsonRedisSerializer<T> serializer = new FastJsonRedisSerializer(tClass);
            serializerMap.put(tClass, serializer);
        }
        return serializerMap.get(tClass);
    }

    @Override
    public void saveSession(String id, Map<SessionField, Object> map) {
        String key = genRedisKey(id);
        final byte[] keyBytes = getSerializer(String.class).serialize(key);
        final Map<byte[], byte[]> hashes = new HashMap<>();

        for (Map.Entry<SessionField, Object> entry : map.entrySet()) {
            SessionField field = entry.getKey();
            Object value = entry.getValue();

            byte[] fieldBytes = getSerializer(String.class).serialize(field.name());
            Class valueType = String.class;
            if (value != null) {
                valueType = value.getClass();
            }
            byte[] valueBytes = getSerializer(valueType).serialize(value);
            hashes.put(fieldBytes, valueBytes);

        }
        stringRedisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.hMSet(keyBytes, hashes);
                return null;
            }
        });
    }

    @Override
    public <T> T getValue(String id, SessionField field, final Class<T> tClass) {
        String key = genRedisKey(id);
        final byte[] keyBytes = getSerializer(String.class).serialize(key);
        final byte[] fieldBytes = getSerializer(String.class).serialize(field.name());
        T result = stringRedisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] values = connection.hGet(keyBytes, fieldBytes);
                T value = getSerializer(tClass).deserialize(values);
                return value;
            }
        });

        return result;
    }

    @Override
    public Long removeSession(String id) {
        String key = genRedisKey(id);
        final byte[] keyBytes = getSerializer(String.class).serialize(key);
        Long r = stringRedisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(keyBytes);
            }
        });
        return r;
    }

    @Override
    public boolean existSession(String id) {
        String key = genRedisKey(id);
        final byte[] keyBytes = getSerializer(String.class).serialize(key);
        Boolean r = stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(keyBytes);
            }
        });
        return r;
    }

    @Override
    public void expire(String id, final int days) {
        String key = genRedisKey(id);
        final byte[] keyBytes = getSerializer(String.class).serialize(key);
        stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                 connection.expire(keyBytes, TimeUnit.DAYS.toSeconds(days));
                 return null;
            }
        });
    }

    String genRedisKey(String id) {
        return String.format("game:%s", id);
    }
}
