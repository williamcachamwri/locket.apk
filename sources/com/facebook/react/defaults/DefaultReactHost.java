package com.facebook.react.defaults;

import android.content.Context;
import com.facebook.react.JSEngineResolutionAlgorithm;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.defaults.DefaultTurboModuleManagerDelegate;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.ReactNativeConfig;
import com.facebook.react.runtime.BindingsInstaller;
import com.facebook.react.runtime.JSCInstance;
import com.facebook.react.runtime.JSEngineInstance;
import com.facebook.react.runtime.ReactHostImpl;
import com.facebook.react.runtime.hermes.HermesInstance;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J<\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/react/defaults/DefaultReactHost;", "", "()V", "reactHost", "Lcom/facebook/react/ReactHost;", "getDefaultReactHost", "context", "Landroid/content/Context;", "reactNativeHost", "Lcom/facebook/react/ReactNativeHost;", "packageList", "", "Lcom/facebook/react/ReactPackage;", "jsMainModulePath", "", "jsBundleAssetPath", "isHermesEnabled", "", "ReactAndroid_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultReactHost.kt */
public final class DefaultReactHost {
    public static final DefaultReactHost INSTANCE = new DefaultReactHost();
    private static ReactHost reactHost;

    /* access modifiers changed from: private */
    public static final void getDefaultReactHost$lambda$0(ReadableMapBuffer readableMapBuffer) {
    }

    private DefaultReactHost() {
    }

    public static /* synthetic */ ReactHost getDefaultReactHost$default(Context context, List list, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            str = FirebaseAnalytics.Param.INDEX;
        }
        if ((i & 8) != 0) {
            str2 = FirebaseAnalytics.Param.INDEX;
        }
        if ((i & 16) != 0) {
            z = true;
        }
        return getDefaultReactHost(context, list, str, str2, z);
    }

    @JvmStatic
    public static final ReactHost getDefaultReactHost(Context context, List<? extends ReactPackage> list, String str, String str2, boolean z) {
        JSEngineResolutionAlgorithm jSEngineResolutionAlgorithm;
        Context context2 = context;
        String str3 = str2;
        Intrinsics.checkNotNullParameter(context, "context");
        List<? extends ReactPackage> list2 = list;
        Intrinsics.checkNotNullParameter(list, "packageList");
        Intrinsics.checkNotNullParameter(str, "jsMainModulePath");
        Intrinsics.checkNotNullParameter(str3, "jsBundleAssetPath");
        if (reactHost == null) {
            JSBundleLoader createAssetLoader = JSBundleLoader.createAssetLoader(context, "assets://" + str3, true);
            JSEngineInstance hermesInstance = z ? new HermesInstance() : new JSCInstance();
            Intrinsics.checkNotNullExpressionValue(createAssetLoader, "jsBundleLoader");
            DefaultReactHostDelegate defaultReactHostDelegate = new DefaultReactHostDelegate(str, createAssetLoader, list, hermesInstance, (BindingsInstaller) null, (ReactNativeConfig) null, (Function1) null, new DefaultTurboModuleManagerDelegate.Builder(), 112, (DefaultConstructorMarker) null);
            DefaultReactHost$$ExternalSyntheticLambda0 defaultReactHost$$ExternalSyntheticLambda0 = new DefaultReactHost$$ExternalSyntheticLambda0();
            ComponentFactory componentFactory = new ComponentFactory();
            DefaultComponentsRegistry.Companion.register(componentFactory);
            ReactHostImpl reactHostImpl = new ReactHostImpl(context, defaultReactHostDelegate, componentFactory, true, defaultReactHost$$ExternalSyntheticLambda0, true);
            if (z) {
                jSEngineResolutionAlgorithm = JSEngineResolutionAlgorithm.HERMES;
            } else {
                jSEngineResolutionAlgorithm = JSEngineResolutionAlgorithm.JSC;
            }
            reactHostImpl.setJSEngineResolutionAlgorithm(jSEngineResolutionAlgorithm);
            reactHost = reactHostImpl;
        }
        ReactHost reactHost2 = reactHost;
        Intrinsics.checkNotNull(reactHost2, "null cannot be cast to non-null type com.facebook.react.ReactHost");
        return reactHost2;
    }

    @JvmStatic
    public static final ReactHost getDefaultReactHost(Context context, ReactNativeHost reactNativeHost) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(reactNativeHost, "reactNativeHost");
        if (reactNativeHost instanceof DefaultReactNativeHost) {
            return ((DefaultReactNativeHost) reactNativeHost).toReactHost(context);
        }
        throw new IllegalArgumentException("You can call getDefaultReactHost only with instances of DefaultReactNativeHost".toString());
    }
}
