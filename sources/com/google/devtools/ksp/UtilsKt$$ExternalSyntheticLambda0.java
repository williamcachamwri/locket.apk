package com.google.devtools.ksp;

import com.google.devtools.ksp.symbol.KSAnnotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class UtilsKt$$ExternalSyntheticLambda0 implements InvocationHandler {
    public final /* synthetic */ KSAnnotation f$0;
    public final /* synthetic */ Class f$1;
    public final /* synthetic */ ConcurrentHashMap f$2;

    public /* synthetic */ UtilsKt$$ExternalSyntheticLambda0(KSAnnotation kSAnnotation, Class cls, ConcurrentHashMap concurrentHashMap) {
        this.f$0 = kSAnnotation;
        this.f$1 = cls;
        this.f$2 = concurrentHashMap;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        return UtilsKt.createInvocationHandler$lambda$8(this.f$0, this.f$1, this.f$2, obj, method, objArr);
    }
}
