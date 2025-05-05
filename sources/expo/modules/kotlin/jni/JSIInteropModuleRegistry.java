package expo.modules.kotlin.jni;

import com.facebook.jni.HybridData;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.soloader.SoLoader;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.ModuleRegistry;
import expo.modules.kotlin.defaultmodules.CoreModule;
import expo.modules.kotlin.exception.JavaScriptEvaluateException;
import expo.modules.kotlin.sharedobjects.SharedObject;
import expo.modules.kotlin.sharedobjects.SharedObjectId;
import expo.modules.kotlin.sharedobjects.SharedObjectRegistry;
import io.sentry.protocol.SentryStackFrame;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000 .2\u00020\u0001:\u0001.B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\f\u001a\u00020\rH J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\t\u0010\u0010\u001a\u00020\u000fH J\u0011\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H J\b\u0010\u0015\u001a\u00020\u000fH\u0004J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0019\u001a\u00020\u0014H\u0007J\u0013\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u001bH\u0007¢\u0006\u0002\u0010\u001cJ\t\u0010\u001d\u001a\u00020\rH J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0019\u001a\u00020\u0014H\u0007J\t\u0010 \u001a\u00020\u000bH J!\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H J\u0006\u0010(\u001a\u00020\u000fJ\u0011\u0010(\u001a\u00020\u000f2\u0006\u0010$\u001a\u00020%H J\u0018\u0010)\u001a\u00020\u000f2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\rH\u0007J\t\u0010-\u001a\u00020\u000fH R\"\u0010\u0005\u001a\u0010\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00030\u00030\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lexpo/modules/kotlin/jni/JSIInteropModuleRegistry;", "Lexpo/modules/kotlin/jni/Destructible;", "appContext", "Lexpo/modules/kotlin/AppContext;", "(Lexpo/modules/kotlin/AppContext;)V", "appContextHolder", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "getAppContextHolder$expo_modules_core_release", "()Ljava/lang/ref/WeakReference;", "mHybridData", "Lcom/facebook/jni/HybridData;", "createObject", "Lexpo/modules/kotlin/jni/JavaScriptObject;", "deallocate", "", "drainJSEventLoop", "evaluateScript", "Lexpo/modules/kotlin/jni/JavaScriptValue;", "script", "", "finalize", "getCoreModuleObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "getJavaScriptModuleObject", "name", "getJavaScriptModulesName", "", "()[Ljava/lang/String;", "global", "hasModule", "", "initHybrid", "installJSI", "jsRuntimePointer", "", "jniDeallocator", "Lexpo/modules/kotlin/jni/JNIDeallocator;", "jsInvokerHolder", "Lcom/facebook/react/turbomodule/core/CallInvokerHolderImpl;", "installJSIForTests", "registerSharedObject", "native", "", "js", "wasDeallocated", "Companion", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: JSIInteropModuleRegistry.kt */
public final class JSIInteropModuleRegistry implements Destructible {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final WeakReference<AppContext> appContextHolder;
    private final HybridData mHybridData = initHybrid();

    private final native HybridData initHybrid();

    public final native JavaScriptObject createObject();

    public final native void drainJSEventLoop();

    public final native JavaScriptValue evaluateScript(String str) throws JavaScriptEvaluateException;

    public final native JavaScriptObject global();

    public final native void installJSI(long j, JNIDeallocator jNIDeallocator, CallInvokerHolderImpl callInvokerHolderImpl);

    public final native void installJSIForTests(JNIDeallocator jNIDeallocator);

    public final native void wasDeallocated();

    public JSIInteropModuleRegistry(AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContextHolder = new WeakReference<>(appContext);
    }

    public final WeakReference<AppContext> getAppContextHolder$expo_modules_core_release() {
        return this.appContextHolder;
    }

    public final void installJSIForTests() {
        Object obj = this.appContextHolder.get();
        Intrinsics.checkNotNull(obj);
        installJSIForTests(((AppContext) obj).getJniDeallocator());
    }

    public final JavaScriptModuleObject getJavaScriptModuleObject(String str) {
        ModuleRegistry registry;
        ModuleHolder<?> moduleHolder;
        Intrinsics.checkNotNullParameter(str, "name");
        AppContext appContext = (AppContext) this.appContextHolder.get();
        if (appContext == null || (registry = appContext.getRegistry()) == null || (moduleHolder = registry.getModuleHolder(str)) == null) {
            return null;
        }
        return moduleHolder.getJsObject();
    }

    public final boolean hasModule(String str) {
        ModuleRegistry registry;
        Intrinsics.checkNotNullParameter(str, "name");
        AppContext appContext = (AppContext) this.appContextHolder.get();
        if (appContext == null || (registry = appContext.getRegistry()) == null) {
            return false;
        }
        return registry.hasModule(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        r0 = (java.lang.String[]) (r0 = (r0 = (r0 = r0.getRegistry()).getRegistry()).keySet()).toArray(new java.lang.String[0]);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String[] getJavaScriptModulesName() {
        /*
            r3 = this;
            java.lang.ref.WeakReference<expo.modules.kotlin.AppContext> r0 = r3.appContextHolder
            java.lang.Object r0 = r0.get()
            expo.modules.kotlin.AppContext r0 = (expo.modules.kotlin.AppContext) r0
            r1 = 0
            if (r0 == 0) goto L_0x0029
            expo.modules.kotlin.ModuleRegistry r0 = r0.getRegistry()
            if (r0 == 0) goto L_0x0029
            java.util.Map r0 = r0.getRegistry()
            if (r0 == 0) goto L_0x0029
            java.util.Set r0 = r0.keySet()
            if (r0 == 0) goto L_0x0029
            java.util.Collection r0 = (java.util.Collection) r0
            java.lang.String[] r2 = new java.lang.String[r1]
            java.lang.Object[] r0 = r0.toArray(r2)
            java.lang.String[] r0 = (java.lang.String[]) r0
            if (r0 != 0) goto L_0x002b
        L_0x0029:
            java.lang.String[] r0 = new java.lang.String[r1]
        L_0x002b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.jni.JSIInteropModuleRegistry.getJavaScriptModulesName():java.lang.String[]");
    }

    public final void registerSharedObject(Object obj, JavaScriptObject javaScriptObject) {
        SharedObjectRegistry sharedObjectRegistry$expo_modules_core_release;
        Intrinsics.checkNotNullParameter(obj, SentryStackFrame.JsonKeys.NATIVE);
        Intrinsics.checkNotNullParameter(javaScriptObject, "js");
        AppContext appContext = (AppContext) this.appContextHolder.get();
        if (appContext != null && (sharedObjectRegistry$expo_modules_core_release = appContext.getSharedObjectRegistry$expo_modules_core_release()) != null) {
            SharedObjectId.m2280boximpl(sharedObjectRegistry$expo_modules_core_release.m2290add5WKnsLU$expo_modules_core_release((SharedObject) obj, javaScriptObject));
        }
    }

    public final JavaScriptModuleObject getCoreModuleObject() {
        ModuleHolder<CoreModule> coreModule$expo_modules_core_release;
        AppContext appContext = (AppContext) this.appContextHolder.get();
        if (appContext == null || (coreModule$expo_modules_core_release = appContext.getCoreModule$expo_modules_core_release()) == null) {
            return null;
        }
        return coreModule$expo_modules_core_release.getJsObject();
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        deallocate();
    }

    public void deallocate() {
        this.mHybridData.resetNative();
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lexpo/modules/kotlin/jni/JSIInteropModuleRegistry$Companion;", "", "()V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: JSIInteropModuleRegistry.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        SoLoader.loadLibrary("expo-modules-core");
    }
}
