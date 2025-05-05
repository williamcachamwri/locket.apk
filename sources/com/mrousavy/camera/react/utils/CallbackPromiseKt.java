package com.mrousavy.camera.react.utils;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a6\u0010\u0004\u001a\u00020\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\n"}, d2 = {"makeErrorCauseMap", "Lcom/facebook/react/bridge/ReadableMap;", "throwable", "", "makeErrorMap", "code", "", "message", "userInfo", "Lcom/facebook/react/bridge/WritableMap;", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: CallbackPromise.kt */
public final class CallbackPromiseKt {
    private static final ReadableMap makeErrorCauseMap(Throwable th) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("message", th.getMessage());
        createMap.putString("stacktrace", ExceptionsKt.stackTraceToString(th));
        if (th.getCause() != null) {
            Throwable cause = th.getCause();
            Intrinsics.checkNotNull(cause);
            createMap.putMap("cause", makeErrorCauseMap(cause));
        }
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }

    public static /* synthetic */ ReadableMap makeErrorMap$default(String str, String str2, Throwable th, WritableMap writableMap, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            th = null;
        }
        if ((i & 8) != 0) {
            writableMap = null;
        }
        return makeErrorMap(str, str2, th, writableMap);
    }

    public static final ReadableMap makeErrorMap(String str, String str2, Throwable th, WritableMap writableMap) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(UniversalFirebaseFunctionsModule.CODE_KEY, str);
        createMap.putString("message", str2);
        createMap.putMap("cause", th != null ? makeErrorCauseMap(th) : null);
        createMap.putMap("userInfo", writableMap);
        Intrinsics.checkNotNull(createMap);
        return createMap;
    }
}
