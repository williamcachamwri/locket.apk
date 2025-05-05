package com.google.common.util.concurrent;

import java.util.logging.Logger;

@ElementTypesAreNonnullByDefault
final class LazyLogger {
    private final Object lock = new Object();
    private volatile Logger logger;
    private final String loggerName;

    LazyLogger(Class<?> cls) {
        this.loggerName = cls.getName();
    }

    /* access modifiers changed from: package-private */
    public Logger get() {
        Logger logger2 = this.logger;
        if (logger2 != null) {
            return logger2;
        }
        synchronized (this.lock) {
            Logger logger3 = this.logger;
            if (logger3 != null) {
                return logger3;
            }
            Logger logger4 = Logger.getLogger(this.loggerName);
            this.logger = logger4;
            return logger4;
        }
    }
}
