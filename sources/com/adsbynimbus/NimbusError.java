package com.adsbynimbus;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0002\n\u000bB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/adsbynimbus/NimbusError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "errorType", "Lcom/adsbynimbus/NimbusError$ErrorType;", "message", "", "cause", "", "(Lcom/adsbynimbus/NimbusError$ErrorType;Ljava/lang/String;Ljava/lang/Throwable;)V", "ErrorType", "Listener", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NimbusError.kt */
public final class NimbusError extends Exception {
    public final ErrorType errorType;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/adsbynimbus/NimbusError$ErrorType;", "", "(Ljava/lang/String;I)V", "NOT_INITIALIZED", "NO_BID", "NETWORK_ERROR", "RENDERER_ERROR", "CONTROLLER_ERROR", "WEBVIEW_ERROR", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusError.kt */
    public enum ErrorType {
        NOT_INITIALIZED,
        NO_BID,
        NETWORK_ERROR,
        RENDERER_ERROR,
        CONTROLLER_ERROR,
        WEBVIEW_ERROR
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/NimbusError$Listener;", "", "onError", "", "error", "Lcom/adsbynimbus/NimbusError;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NimbusError.kt */
    public interface Listener {
        void onError(NimbusError nimbusError);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NimbusError(ErrorType errorType2, String str, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(errorType2, "errorType");
        Intrinsics.checkNotNullParameter(str, "message");
        this.errorType = errorType2;
    }
}
