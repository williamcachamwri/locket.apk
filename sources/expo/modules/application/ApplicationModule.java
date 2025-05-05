package expo.modules.application;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.tracing.Trace;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.objects.PropertyComponentBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\f8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\n \r*\u0004\u0018\u00010\u00040\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0006¨\u0006\u001a"}, d2 = {"Lexpo/modules/application/ApplicationModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "applicationName", "", "getApplicationName", "()Ljava/lang/String;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "packageManager", "Landroid/content/pm/PackageManager;", "kotlin.jvm.PlatformType", "getPackageManager", "()Landroid/content/pm/PackageManager;", "packageName", "getPackageName", "versionCode", "", "getVersionCode", "()I", "versionName", "getVersionName", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-application_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ApplicationModule.kt */
public final class ApplicationModule extends Module {
    /* access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoApplication");
            moduleDefinitionBuilder.Constants((Function0<? extends Map<String, ? extends Object>>) new ApplicationModule$definition$1$1(this));
            PropertyComponentBuilder propertyComponentBuilder = new PropertyComponentBuilder("androidId");
            propertyComponentBuilder.setGetter(new SyncFunctionComponent("get", new AnyType[0], new ApplicationModule$definition$lambda$4$$inlined$Property$1(this)));
            moduleDefinitionBuilder.getProperties().put("androidId", propertyComponentBuilder);
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getInstallationTimeAsync", new AnyType[0], new ApplicationModule$definition$lambda$4$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getInstallationTimeAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction2 = asyncFunctionComponent;
            AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent("getLastUpdateTimeAsync", new AnyType[0], new ApplicationModule$definition$lambda$4$$inlined$AsyncFunctionWithoutArgs$2(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getLastUpdateTimeAsync", asyncFunctionComponent2);
            AsyncFunction asyncFunction3 = asyncFunctionComponent2;
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("getInstallReferrerAsync", new AnyType[0], new ApplicationModule$definition$lambda$4$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("getInstallReferrerAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ApplicationModule$definition$lambda$4$$inlined$AsyncFunction$2.INSTANCE))}, new ApplicationModule$definition$lambda$4$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("getInstallReferrerAsync", asyncFunction);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final String getApplicationName() {
        return getContext().getApplicationInfo().loadLabel(getContext().getPackageManager()).toString();
    }

    /* access modifiers changed from: private */
    public final String getPackageName() {
        return getContext().getPackageName();
    }

    private final PackageManager getPackageManager() {
        return getContext().getPackageManager();
    }

    /* access modifiers changed from: private */
    public final String getVersionName() {
        PackageManager packageManager = getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "<get-packageManager>(...)");
        String packageName = getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "<get-packageName>(...)");
        return ApplicationModuleKt.getPackageInfoCompat(packageManager, packageName, 0).versionName;
    }

    /* access modifiers changed from: private */
    public final int getVersionCode() {
        PackageManager packageManager = getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "<get-packageManager>(...)");
        String packageName = getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "<get-packageName>(...)");
        return (int) ApplicationModuleKt.getLongVersionCode(ApplicationModuleKt.getPackageInfoCompat(packageManager, packageName, 0));
    }
}
