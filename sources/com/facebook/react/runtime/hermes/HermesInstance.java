package com.facebook.react.runtime.hermes;

import com.facebook.jni.HybridData;
import com.facebook.react.runtime.JSEngineInstance;
import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/facebook/react/runtime/hermes/HermesInstance;", "Lcom/facebook/react/runtime/JSEngineInstance;", "()V", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HermesInstance.kt */
public final class HermesInstance extends JSEngineInstance {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @JvmStatic
    protected static final native HybridData initHybrid();

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HermesInstance() {
        /*
            r1 = this;
            com.facebook.jni.HybridData r0 = initHybrid()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r1.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.runtime.hermes.HermesInstance.<init>():void");
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H ¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/runtime/hermes/HermesInstance$Companion;", "", "()V", "initHybrid", "Lcom/facebook/jni/HybridData;", "ReactAndroid_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HermesInstance.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: protected */
        @JvmStatic
        public final HybridData initHybrid() {
            return HermesInstance.initHybrid();
        }

        private Companion() {
        }
    }

    static {
        SoLoader.loadLibrary("hermesinstancejni");
    }
}
