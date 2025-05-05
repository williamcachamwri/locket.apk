package com.mrousavy.camera.react.utils;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.mrousavy.camera.core.CameraError;
import com.mrousavy.camera.core.UnknownCameraError;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0007"}, d2 = {"withPromise", "", "promise", "Lcom/facebook/react/bridge/Promise;", "closure", "Lkotlin/Function0;", "", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: withPromise.kt */
public final class WithPromiseKt {
    public static final void withPromise(Promise promise, Function0<? extends Object> function0) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Intrinsics.checkNotNullParameter(function0, "closure");
        try {
            promise.resolve(function0.invoke());
        } catch (Throwable th) {
            th.printStackTrace();
            CameraError unknownCameraError = th instanceof CameraError ? th : new UnknownCameraError(th);
            promise.reject(unknownCameraError.getDomain() + "/" + unknownCameraError.getId(), unknownCameraError.getMessage(), unknownCameraError.getCause());
        }
    }
}
