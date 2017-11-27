package com.potato.core.service.impl;


import com.potato.core.service.UniqueIDGeneratorService;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicLong;


/**
 * Uses an atomic long to increment and provide a unique id. This will not work
 * in case of clustered servers.
 *
 * @author Abraham.Menacherry
 */
public class SimpleUniqueIdGenerator implements UniqueIDGeneratorService {
    final String prefix;
    public static final AtomicLong ID = new AtomicLong(0l);

    public SimpleUniqueIdGenerator(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String generate() {
        if (StringUtils.isEmpty(prefix)) {
            return "" + ID.incrementAndGet();
        } else {
            return prefix + ID.incrementAndGet();
        }
    }

    @Override
    public String generateFor(@SuppressWarnings("rawtypes") Class klass) {
        if (StringUtils.isEmpty(prefix)) {
            return klass.getSimpleName() + ID.incrementAndGet();
        } else {
            return prefix + klass.getSimpleName() + ID.incrementAndGet();
        }
    }

}
