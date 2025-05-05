package com.brentvatne.react;

import com.brentvatne.exoplayer.DefaultReactExoplayerConfig;
import com.brentvatne.exoplayer.ReactExoplayerConfig;
import com.brentvatne.exoplayer.ReactExoplayerViewManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u00070\u0006J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001e\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000e0\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/brentvatne/react/ReactVideoPackage;", "Lcom/facebook/react/ReactPackage;", "config", "Lcom/brentvatne/exoplayer/ReactExoplayerConfig;", "(Lcom/brentvatne/exoplayer/ReactExoplayerConfig;)V", "createJSModules", "", "Ljava/lang/Class;", "Lcom/facebook/react/bridge/JavaScriptModule;", "createNativeModules", "Lcom/facebook/react/bridge/NativeModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "createViewManagers", "Lcom/facebook/react/uimanager/ViewManager;", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ReactVideoPackage.kt */
public final class ReactVideoPackage implements ReactPackage {
    private final ReactExoplayerConfig config;

    public ReactVideoPackage() {
        this((ReactExoplayerConfig) null, 1, (DefaultConstructorMarker) null);
    }

    public ReactVideoPackage(ReactExoplayerConfig reactExoplayerConfig) {
        this.config = reactExoplayerConfig;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ReactVideoPackage(ReactExoplayerConfig reactExoplayerConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : reactExoplayerConfig);
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        return CollectionsKt.listOf(new VideoDecoderInfoModule(reactApplicationContext), new VideoManagerModule(reactApplicationContext));
    }

    public final List<Class<? extends JavaScriptModule>> createJSModules() {
        return CollectionsKt.emptyList();
    }

    public List<ViewManager<?, ?>> createViewManagers(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        ReactExoplayerConfig reactExoplayerConfig = this.config;
        if (reactExoplayerConfig == null) {
            reactExoplayerConfig = new DefaultReactExoplayerConfig(reactApplicationContext);
        }
        return CollectionsKt.listOf(new ReactExoplayerViewManager(reactExoplayerConfig));
    }
}
