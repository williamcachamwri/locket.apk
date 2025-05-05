package com.adsbynimbus.internal;

import com.adsbynimbus.Nimbus;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n\u001a\u000e\u0010\u000b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\f"}, d2 = {"loggers", "", "Lcom/adsbynimbus/Nimbus$Logger;", "getLoggers", "()Ljava/util/Set;", "log", "", "priority", "", "message", "", "verbose", "core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Logger.kt */
public final class Logger {
    private static final Set<Nimbus.Logger> loggers = new LinkedHashSet();

    public static final Set<Nimbus.Logger> getLoggers() {
        return loggers;
    }

    public static final void log(int i, String str) {
        if (str != null) {
            for (Nimbus.Logger log : loggers) {
                log.log(i, str);
            }
        }
    }

    public static final void verbose(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        for (Nimbus.Logger log : loggers) {
            log.log(2, str);
        }
    }
}
