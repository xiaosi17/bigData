package com.example.bigData.util;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static AtomicLong counter = new AtomicLong(0);
    private static final int FIXED_LENGTH = 10; // 固定长度

    public static String generate() {
        long num = counter.incrementAndGet();
        return String.format("%0"+FIXED_LENGTH+"d", num);
    }
}
