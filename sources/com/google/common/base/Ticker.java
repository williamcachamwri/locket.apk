package com.google.common.base;

@ElementTypesAreNonnullByDefault
public abstract class Ticker {
    private static final Ticker SYSTEM_TICKER = new Ticker() {
        public long read() {
            return System.nanoTime();
        }
    };

    public abstract long read();

    protected Ticker() {
    }

    public static Ticker systemTicker() {
        return SYSTEM_TICKER;
    }
}
