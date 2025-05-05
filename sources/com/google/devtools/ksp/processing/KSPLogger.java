package com.google.devtools.ksp.processing;

import com.google.devtools.ksp.symbol.KSNode;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u001c\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001c\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001c\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/processing/KSPLogger;", "", "error", "", "message", "", "symbol", "Lcom/google/devtools/ksp/symbol/KSNode;", "exception", "e", "", "info", "logging", "warn", "api"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KSPLogger.kt */
public interface KSPLogger {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: KSPLogger.kt */
    public static final class DefaultImpls {
    }

    void error(String str, KSNode kSNode);

    void exception(Throwable th);

    void info(String str, KSNode kSNode);

    void logging(String str, KSNode kSNode);

    void warn(String str, KSNode kSNode);

    static /* synthetic */ void logging$default(KSPLogger kSPLogger, String str, KSNode kSNode, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                kSNode = null;
            }
            kSPLogger.logging(str, kSNode);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: logging");
    }

    static /* synthetic */ void info$default(KSPLogger kSPLogger, String str, KSNode kSNode, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                kSNode = null;
            }
            kSPLogger.info(str, kSNode);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: info");
    }

    static /* synthetic */ void warn$default(KSPLogger kSPLogger, String str, KSNode kSNode, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                kSNode = null;
            }
            kSPLogger.warn(str, kSNode);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: warn");
    }

    static /* synthetic */ void error$default(KSPLogger kSPLogger, String str, KSNode kSNode, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                kSNode = null;
            }
            kSPLogger.error(str, kSNode);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: error");
    }
}
