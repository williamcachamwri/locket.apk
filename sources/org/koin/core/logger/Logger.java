package org.koin.core.logger;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\b\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\fH\bJ\u001c\u0010\r\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\n\u001a\u00060\u000bj\u0002`\fH&J\u0015\u0010\u000e\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\fH\bJ\u0015\u0010\u000f\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\fH\bJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003J\"\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014H\bø\u0001\u0000J\u0019\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\bJ\u0015\u0010\u0015\u001a\u00020\t2\n\u0010\n\u001a\u00060\u000bj\u0002`\fH\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0016"}, d2 = {"Lorg/koin/core/logger/Logger;", "", "level", "Lorg/koin/core/logger/Level;", "(Lorg/koin/core/logger/Level;)V", "getLevel", "()Lorg/koin/core/logger/Level;", "setLevel", "debug", "", "msg", "", "Lorg/koin/core/logger/MESSAGE;", "display", "error", "info", "isAt", "", "lvl", "log", "Lkotlin/Function0;", "warn", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Logger.kt */
public abstract class Logger {
    private Level level;

    public Logger() {
        this((Level) null, 1, (DefaultConstructorMarker) null);
    }

    public abstract void display(Level level2, String str);

    public Logger(Level level2) {
        Intrinsics.checkNotNullParameter(level2, "level");
        this.level = level2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Logger(Level level2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Level.INFO : level2);
    }

    public final Level getLevel() {
        return this.level;
    }

    public final void setLevel(Level level2) {
        Intrinsics.checkNotNullParameter(level2, "<set-?>");
        this.level = level2;
    }

    public final void debug(String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        Level level2 = Level.DEBUG;
        if (isAt(level2)) {
            display(level2, str);
        }
    }

    public final void info(String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        Level level2 = Level.INFO;
        if (isAt(level2)) {
            display(level2, str);
        }
    }

    public final void warn(String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        Level level2 = Level.WARNING;
        if (isAt(level2)) {
            display(level2, str);
        }
    }

    public final void error(String str) {
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        Level level2 = Level.ERROR;
        if (isAt(level2)) {
            display(level2, str);
        }
    }

    public final boolean isAt(Level level2) {
        Intrinsics.checkNotNullParameter(level2, "lvl");
        return this.level.compareTo(level2) <= 0;
    }

    public final void log(Level level2, String str) {
        Intrinsics.checkNotNullParameter(level2, "lvl");
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_MESSAGE);
        if (isAt(level2)) {
            display(level2, str);
        }
    }

    public final void log(Level level2, Function0<String> function0) {
        Intrinsics.checkNotNullParameter(level2, "lvl");
        Intrinsics.checkNotNullParameter(function0, NotificationCompat.CATEGORY_MESSAGE);
        if (isAt(level2)) {
            display(level2, function0.invoke());
        }
    }
}
