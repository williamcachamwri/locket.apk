package com.facebook.react.runtime;

import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.fabric.ReactNativeConfig;
import com.facebook.react.turbomodule.core.TurboModuleManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001$J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH&J\u0014\u0010\u001f\u001a\u00020 2\n\u0010!\u001a\u00060\"j\u0002`#H&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006%À\u0006\u0001"}, d2 = {"Lcom/facebook/react/runtime/ReactHostDelegate;", "", "bindingsInstaller", "Lcom/facebook/react/runtime/BindingsInstaller;", "getBindingsInstaller", "()Lcom/facebook/react/runtime/BindingsInstaller;", "jsBundleLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "getJsBundleLoader", "()Lcom/facebook/react/bridge/JSBundleLoader;", "jsEngineInstance", "Lcom/facebook/react/runtime/JSEngineInstance;", "getJsEngineInstance", "()Lcom/facebook/react/runtime/JSEngineInstance;", "jsMainModulePath", "", "getJsMainModulePath", "()Ljava/lang/String;", "reactPackages", "", "Lcom/facebook/react/ReactPackage;", "getReactPackages", "()Ljava/util/List;", "turboModuleManagerDelegateBuilder", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getTurboModuleManagerDelegateBuilder", "()Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getReactNativeConfig", "Lcom/facebook/react/fabric/ReactNativeConfig;", "turboModuleManager", "Lcom/facebook/react/turbomodule/core/TurboModuleManager;", "handleInstanceException", "", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "ReactHostDelegateBase", "ReactAndroid_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@UnstableReactNativeAPI
/* compiled from: ReactHostDelegate.kt */
public interface ReactHostDelegate {
    BindingsInstaller getBindingsInstaller();

    JSBundleLoader getJsBundleLoader();

    JSEngineInstance getJsEngineInstance();

    String getJsMainModulePath();

    ReactNativeConfig getReactNativeConfig(TurboModuleManager turboModuleManager);

    List<ReactPackage> getReactPackages();

    ReactPackageTurboModuleManagerDelegate.Builder getTurboModuleManagerDelegateBuilder();

    void handleInstanceException(Exception exc);

    @UnstableReactNativeAPI
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001Bt\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012'\b\u0002\u0010\u0011\u001a!\u0012\u0017\u0012\u00150\u0013j\u0002`\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u0012¢\u0006\u0002\u0010\u0019J\u0010\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020(H\u0016J\u0014\u0010)\u001a\u00020\u00182\n\u0010\u0017\u001a\u00060\u0013j\u0002`\u0014H\u0016R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR-\u0010\u0011\u001a!\u0012\u0017\u0012\u00150\u0013j\u0002`\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u0012X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u0006*"}, d2 = {"Lcom/facebook/react/runtime/ReactHostDelegate$ReactHostDelegateBase;", "Lcom/facebook/react/runtime/ReactHostDelegate;", "jsMainModulePath", "", "jsBundleLoader", "Lcom/facebook/react/bridge/JSBundleLoader;", "jsEngineInstance", "Lcom/facebook/react/runtime/JSEngineInstance;", "turboModuleManagerDelegateBuilder", "Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "reactPackages", "", "Lcom/facebook/react/ReactPackage;", "bindingsInstaller", "Lcom/facebook/react/runtime/BindingsInstaller;", "reactNativeConfig", "Lcom/facebook/react/fabric/ReactNativeConfig;", "exceptionHandler", "Lkotlin/Function1;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lkotlin/ParameterName;", "name", "error", "", "(Ljava/lang/String;Lcom/facebook/react/bridge/JSBundleLoader;Lcom/facebook/react/runtime/JSEngineInstance;Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;Ljava/util/List;Lcom/facebook/react/runtime/BindingsInstaller;Lcom/facebook/react/fabric/ReactNativeConfig;Lkotlin/jvm/functions/Function1;)V", "getBindingsInstaller", "()Lcom/facebook/react/runtime/BindingsInstaller;", "getJsBundleLoader", "()Lcom/facebook/react/bridge/JSBundleLoader;", "getJsEngineInstance", "()Lcom/facebook/react/runtime/JSEngineInstance;", "getJsMainModulePath", "()Ljava/lang/String;", "getReactPackages", "()Ljava/util/List;", "getTurboModuleManagerDelegateBuilder", "()Lcom/facebook/react/ReactPackageTurboModuleManagerDelegate$Builder;", "getReactNativeConfig", "turboModuleManager", "Lcom/facebook/react/turbomodule/core/TurboModuleManager;", "handleInstanceException", "ReactAndroid_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ReactHostDelegate.kt */
    public static final class ReactHostDelegateBase implements ReactHostDelegate {
        private final BindingsInstaller bindingsInstaller;
        private final Function1<Exception, Unit> exceptionHandler;
        private final JSBundleLoader jsBundleLoader;
        private final JSEngineInstance jsEngineInstance;
        private final String jsMainModulePath;
        private final ReactNativeConfig reactNativeConfig;
        private final List<ReactPackage> reactPackages;
        private final ReactPackageTurboModuleManagerDelegate.Builder turboModuleManagerDelegateBuilder;

        public ReactHostDelegateBase(String str, JSBundleLoader jSBundleLoader, JSEngineInstance jSEngineInstance, ReactPackageTurboModuleManagerDelegate.Builder builder, List<? extends ReactPackage> list, BindingsInstaller bindingsInstaller2, ReactNativeConfig reactNativeConfig2, Function1<? super Exception, Unit> function1) {
            Intrinsics.checkNotNullParameter(str, "jsMainModulePath");
            Intrinsics.checkNotNullParameter(jSBundleLoader, "jsBundleLoader");
            Intrinsics.checkNotNullParameter(jSEngineInstance, "jsEngineInstance");
            Intrinsics.checkNotNullParameter(builder, "turboModuleManagerDelegateBuilder");
            Intrinsics.checkNotNullParameter(list, "reactPackages");
            Intrinsics.checkNotNullParameter(reactNativeConfig2, "reactNativeConfig");
            Intrinsics.checkNotNullParameter(function1, "exceptionHandler");
            this.jsMainModulePath = str;
            this.jsBundleLoader = jSBundleLoader;
            this.jsEngineInstance = jSEngineInstance;
            this.turboModuleManagerDelegateBuilder = builder;
            this.reactPackages = list;
            this.bindingsInstaller = bindingsInstaller2;
            this.reactNativeConfig = reactNativeConfig2;
            this.exceptionHandler = function1;
        }

        public String getJsMainModulePath() {
            return this.jsMainModulePath;
        }

        public JSBundleLoader getJsBundleLoader() {
            return this.jsBundleLoader;
        }

        public JSEngineInstance getJsEngineInstance() {
            return this.jsEngineInstance;
        }

        public ReactPackageTurboModuleManagerDelegate.Builder getTurboModuleManagerDelegateBuilder() {
            return this.turboModuleManagerDelegateBuilder;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ ReactHostDelegateBase(java.lang.String r12, com.facebook.react.bridge.JSBundleLoader r13, com.facebook.react.runtime.JSEngineInstance r14, com.facebook.react.ReactPackageTurboModuleManagerDelegate.Builder r15, java.util.List r16, com.facebook.react.runtime.BindingsInstaller r17, com.facebook.react.fabric.ReactNativeConfig r18, kotlin.jvm.functions.Function1 r19, int r20, kotlin.jvm.internal.DefaultConstructorMarker r21) {
            /*
                r11 = this;
                r0 = r20
                r1 = r0 & 16
                if (r1 == 0) goto L_0x000c
                java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
                r7 = r1
                goto L_0x000e
            L_0x000c:
                r7 = r16
            L_0x000e:
                r1 = r0 & 32
                if (r1 == 0) goto L_0x0015
                r1 = 0
                r8 = r1
                goto L_0x0017
            L_0x0015:
                r8 = r17
            L_0x0017:
                r1 = r0 & 64
                if (r1 == 0) goto L_0x001f
                com.facebook.react.fabric.ReactNativeConfig r1 = com.facebook.react.fabric.ReactNativeConfig.DEFAULT_CONFIG
                r9 = r1
                goto L_0x0021
            L_0x001f:
                r9 = r18
            L_0x0021:
                r0 = r0 & 128(0x80, float:1.794E-43)
                if (r0 == 0) goto L_0x002b
                com.facebook.react.runtime.ReactHostDelegate$ReactHostDelegateBase$1 r0 = com.facebook.react.runtime.ReactHostDelegate.ReactHostDelegateBase.AnonymousClass1.INSTANCE
                kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
                r10 = r0
                goto L_0x002d
            L_0x002b:
                r10 = r19
            L_0x002d:
                r2 = r11
                r3 = r12
                r4 = r13
                r5 = r14
                r6 = r15
                r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.runtime.ReactHostDelegate.ReactHostDelegateBase.<init>(java.lang.String, com.facebook.react.bridge.JSBundleLoader, com.facebook.react.runtime.JSEngineInstance, com.facebook.react.ReactPackageTurboModuleManagerDelegate$Builder, java.util.List, com.facebook.react.runtime.BindingsInstaller, com.facebook.react.fabric.ReactNativeConfig, kotlin.jvm.functions.Function1, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public List<ReactPackage> getReactPackages() {
            return this.reactPackages;
        }

        public BindingsInstaller getBindingsInstaller() {
            return this.bindingsInstaller;
        }

        public ReactNativeConfig getReactNativeConfig(TurboModuleManager turboModuleManager) {
            Intrinsics.checkNotNullParameter(turboModuleManager, "turboModuleManager");
            return this.reactNativeConfig;
        }

        public void handleInstanceException(Exception exc) {
            Intrinsics.checkNotNullParameter(exc, "error");
            this.exceptionHandler.invoke(exc);
        }
    }
}
