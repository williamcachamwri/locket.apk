package com.google.firebase.appcheck.internal.util;

public interface Clock {
    long currentTimeMillis();

    public static class DefaultClock implements Clock {
        public long currentTimeMillis() {
            return System.currentTimeMillis();
        }
    }
}
