package com.facebook.react.defaults;

import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.soloader.SoLoader;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007J0\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007R\u001a\u0010\u0003\u001a\u00020\u00048FX\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\u00048FX\u0004¢\u0006\f\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007R\u001a\u0010\u000b\u001a\u00020\u00048FX\u0004¢\u0006\f\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u0007R\u000e\u0010\u000e\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u00048FX\u0004¢\u0006\f\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/facebook/react/defaults/DefaultNewArchitectureEntryPoint;", "", "()V", "bridgelessEnabled", "", "getBridgelessEnabled$annotations", "getBridgelessEnabled", "()Z", "concurrentReactEnabled", "getConcurrentReactEnabled$annotations", "getConcurrentReactEnabled", "fabricEnabled", "getFabricEnabled$annotations", "getFabricEnabled", "privateBridgelessEnabled", "privateConcurrentReactEnabled", "privateFabricEnabled", "privateTurboModulesEnabled", "turboModulesEnabled", "getTurboModulesEnabled$annotations", "getTurboModulesEnabled", "load", "", "dynamicLibraryName", "", "ReactAndroid_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultNewArchitectureEntryPoint.kt */
public final class DefaultNewArchitectureEntryPoint {
    public static final DefaultNewArchitectureEntryPoint INSTANCE = new DefaultNewArchitectureEntryPoint();
    private static boolean privateBridgelessEnabled;
    private static boolean privateConcurrentReactEnabled;
    private static boolean privateFabricEnabled;
    private static boolean privateTurboModulesEnabled;

    @JvmStatic
    public static /* synthetic */ void getBridgelessEnabled$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getConcurrentReactEnabled$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getFabricEnabled$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getTurboModulesEnabled$annotations() {
    }

    @JvmStatic
    public static final void load() {
        load$default(false, false, false, (String) null, 15, (Object) null);
    }

    @JvmStatic
    public static final void load(boolean z) {
        load$default(z, false, false, (String) null, 14, (Object) null);
    }

    @JvmStatic
    public static final void load(boolean z, boolean z2) {
        load$default(z, z2, false, (String) null, 12, (Object) null);
    }

    @JvmStatic
    public static final void load(boolean z, boolean z2, boolean z3) {
        load$default(z, z2, z3, (String) null, 8, (Object) null);
    }

    private DefaultNewArchitectureEntryPoint() {
    }

    public static /* synthetic */ void load$default(boolean z, boolean z2, boolean z3, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        if ((i & 8) != 0) {
            str = "appmodules";
        }
        load(z, z2, z3, str);
    }

    @JvmStatic
    public static final void load(boolean z, boolean z2, boolean z3, String str) {
        Intrinsics.checkNotNullParameter(str, "dynamicLibraryName");
        ReactFeatureFlags.useTurboModules = z;
        ReactFeatureFlags.enableFabricRenderer = z2;
        ReactFeatureFlags.unstable_useFabricInterop = z2;
        ReactFeatureFlags.enableBridgelessArchitecture = z3;
        ReactFeatureFlags.useNativeViewConfigsInBridgelessMode = z2 && z3;
        ReactFeatureFlags.unstable_useTurboModuleInterop = z3;
        privateFabricEnabled = z2;
        privateTurboModulesEnabled = z;
        privateConcurrentReactEnabled = z2;
        privateBridgelessEnabled = z3;
        SoLoader.loadLibrary("react_newarchdefaults");
        SoLoader.loadLibrary(str);
    }

    public static /* synthetic */ void load$default(DefaultNewArchitectureEntryPoint defaultNewArchitectureEntryPoint, boolean z, boolean z2, boolean z3, boolean z4, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        if ((i & 8) != 0) {
            z4 = true;
        }
        if ((i & 16) != 0) {
            str = "appmodules";
        }
        defaultNewArchitectureEntryPoint.load(z, z2, z3, z4, str);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Calling DefaultNewArchitectureEntryPoint.load() with different fabricEnabled and concurrentReactEnabled is deprecated. Please use a single flag for both Fabric and Concurrent React", replaceWith = @ReplaceWith(expression = "load(turboModulesEnabled, fabricEnabled, dynamicLibraryName)", imports = {}))
    public final void load(boolean z, boolean z2, boolean z3, boolean z4, String str) {
        Intrinsics.checkNotNullParameter(str, "dynamicLibraryName");
        load(z, z2, z3, str);
    }

    public static final boolean getFabricEnabled() {
        return privateFabricEnabled;
    }

    public static final boolean getTurboModulesEnabled() {
        return privateTurboModulesEnabled;
    }

    public static final boolean getConcurrentReactEnabled() {
        return privateConcurrentReactEnabled;
    }

    public static final boolean getBridgelessEnabled() {
        return privateBridgelessEnabled;
    }
}
