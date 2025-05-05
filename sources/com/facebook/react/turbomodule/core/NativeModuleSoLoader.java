package com.facebook.react.turbomodule.core;

import com.facebook.soloader.SoLoader;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/facebook/react/turbomodule/core/NativeModuleSoLoader;", "", "()V", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NativeModuleSoLoader.kt */
public final class NativeModuleSoLoader {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static boolean isSoLibraryLoaded;

    @JvmStatic
    public static final synchronized void maybeLoadSoLibrary() {
        synchronized (NativeModuleSoLoader.class) {
            Companion.maybeLoadSoLibrary();
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/turbomodule/core/NativeModuleSoLoader$Companion;", "", "()V", "isSoLibraryLoaded", "", "maybeLoadSoLibrary", "", "ReactAndroid_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NativeModuleSoLoader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final synchronized void maybeLoadSoLibrary() {
            if (!NativeModuleSoLoader.isSoLibraryLoaded) {
                SoLoader.loadLibrary("turbomodulejsijni");
                NativeModuleSoLoader.isSoLibraryLoaded = true;
            }
        }
    }
}
