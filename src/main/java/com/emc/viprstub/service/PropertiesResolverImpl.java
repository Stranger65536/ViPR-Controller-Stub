/*
 * Copyright 1994-2018 EMC Corporation. All rights reserved.
 */
package com.emc.viprstub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PropertiesResolverImpl implements PropertiesResolver {
    private static final Pattern PATTERN = Pattern.compile("\\$\\{(?<key>.+)}");

    private final Random random;
    private final Map<String, String> placeholders;

    @Autowired
    public PropertiesResolverImpl(@Value("${node}") final int nodeId) {
        this.random = new Random(nodeId);
        this.placeholders = new HashMap<>(64, 1.0f);
    }

    @Override
    public String resolve(final String text) {
        String temp = text;
        Matcher matcher;
        while ((matcher = PATTERN.matcher(temp)).find()) {
            final String key = matcher.group("key");
            final String value;
            if (placeholders.containsKey(key)) {
                value = placeholders.get(key);
            } else {
                final byte[] bytes = new byte[32];
                random.nextBytes(bytes);
                value = UUID.nameUUIDFromBytes(bytes).toString();
                placeholders.put(key, value);
            }
            temp = matcher.replaceFirst(value);
        }
        return temp;
    }
}
