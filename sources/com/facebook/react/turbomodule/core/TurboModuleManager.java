package com.facebook.react.turbomodule.core;

import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.CxxModuleWrapper;
import com.facebook.react.bridge.JSIModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.turbomodule.core.TurboModuleInteropUtils;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.react.turbomodule.core.interfaces.NativeMethodCallInvokerHolder;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModuleRegistry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurboModuleManager implements JSIModule, TurboModuleRegistry {
    private final TurboModuleManagerDelegate mDelegate;
    private final List<String> mEagerInitModuleNames;
    private final HybridData mHybridData;
    private final ModuleProvider mLegacyModuleProvider;
    private final Object mModuleCleanupLock = new Object();
    private boolean mModuleCleanupStarted = false;
    private final Map<String, ModuleHolder> mModuleHolders = new HashMap();
    private final ModuleProvider mTurboModuleProvider;

    private interface ModuleProvider {
        NativeModule getModule(String str);
    }

    private native HybridData initHybrid(RuntimeExecutor runtimeExecutor, CallInvokerHolderImpl callInvokerHolderImpl, NativeMethodCallInvokerHolderImpl nativeMethodCallInvokerHolderImpl, TurboModuleManagerDelegate turboModuleManagerDelegate);

    private native void installJSIBindings(boolean z);

    static /* synthetic */ NativeModule lambda$new$0(String str) {
        return null;
    }

    public void initialize() {
    }

    static {
        NativeModuleSoLoader.maybeLoadSoLibrary();
    }

    public TurboModuleManager(RuntimeExecutor runtimeExecutor, TurboModuleManagerDelegate turboModuleManagerDelegate, CallInvokerHolder callInvokerHolder, NativeMethodCallInvokerHolder nativeMethodCallInvokerHolder) {
        ModuleProvider moduleProvider;
        this.mDelegate = turboModuleManagerDelegate;
        this.mHybridData = initHybrid(runtimeExecutor, (CallInvokerHolderImpl) callInvokerHolder, (NativeMethodCallInvokerHolderImpl) nativeMethodCallInvokerHolder, turboModuleManagerDelegate);
        installJSIBindings(shouldEnableLegacyModuleInterop());
        this.mEagerInitModuleNames = turboModuleManagerDelegate == null ? new ArrayList<>() : turboModuleManagerDelegate.getEagerInitModuleNames();
        ModuleProvider turboModuleManager$$ExternalSyntheticLambda0 = new TurboModuleManager$$ExternalSyntheticLambda0();
        if (turboModuleManagerDelegate == null) {
            moduleProvider = turboModuleManager$$ExternalSyntheticLambda0;
        } else {
            moduleProvider = new TurboModuleManager$$ExternalSyntheticLambda1(turboModuleManagerDelegate);
        }
        this.mTurboModuleProvider = moduleProvider;
        if (turboModuleManagerDelegate != null && shouldEnableLegacyModuleInterop()) {
            turboModuleManager$$ExternalSyntheticLambda0 = new TurboModuleManager$$ExternalSyntheticLambda2(turboModuleManagerDelegate);
        }
        this.mLegacyModuleProvider = turboModuleManager$$ExternalSyntheticLambda0;
    }

    static /* synthetic */ NativeModule lambda$new$1(TurboModuleManagerDelegate turboModuleManagerDelegate, String str) {
        return (NativeModule) turboModuleManagerDelegate.getModule(str);
    }

    static /* synthetic */ NativeModule lambda$new$2(TurboModuleManagerDelegate turboModuleManagerDelegate, String str) {
        NativeModule legacyModule = turboModuleManagerDelegate.getLegacyModule(str);
        if (legacyModule == null) {
            return null;
        }
        Assertions.assertCondition(!(legacyModule instanceof TurboModule), "NativeModule \"" + str + "\" is a TurboModule");
        return legacyModule;
    }

    private boolean isTurboModule(String str) {
        TurboModuleManagerDelegate turboModuleManagerDelegate = this.mDelegate;
        return turboModuleManagerDelegate != null && turboModuleManagerDelegate.unstable_isModuleRegistered(str);
    }

    private boolean isLegacyModule(String str) {
        TurboModuleManagerDelegate turboModuleManagerDelegate = this.mDelegate;
        return turboModuleManagerDelegate != null && turboModuleManagerDelegate.unstable_isLegacyModuleRegistered(str);
    }

    private boolean shouldEnableLegacyModuleInterop() {
        TurboModuleManagerDelegate turboModuleManagerDelegate = this.mDelegate;
        return turboModuleManagerDelegate != null && turboModuleManagerDelegate.unstable_shouldEnableLegacyModuleInterop();
    }

    private boolean shouldRouteTurboModulesThroughLegacyModuleInterop() {
        TurboModuleManagerDelegate turboModuleManagerDelegate = this.mDelegate;
        return turboModuleManagerDelegate != null && turboModuleManagerDelegate.unstable_shouldRouteTurboModulesThroughLegacyModuleInterop();
    }

    public List<String> getEagerInitModuleNames() {
        return this.mEagerInitModuleNames;
    }

    private static List<TurboModuleInteropUtils.MethodDescriptor> getMethodDescriptorsFromModule(NativeModule nativeModule) {
        return TurboModuleInteropUtils.getMethodDescriptorsFromModule(nativeModule);
    }

    private NativeModule getLegacyJavaModule(String str) {
        if (shouldRouteTurboModulesThroughLegacyModuleInterop()) {
            NativeModule module = getModule(str);
            if (!(module instanceof CxxModuleWrapper)) {
                return module;
            }
            return null;
        } else if (!isLegacyModule(str)) {
            return null;
        } else {
            NativeModule module2 = getModule(str);
            if ((module2 instanceof CxxModuleWrapper) || (module2 instanceof TurboModule)) {
                return null;
            }
            return module2;
        }
    }

    private CxxModuleWrapper getLegacyCxxModule(String str) {
        if (shouldRouteTurboModulesThroughLegacyModuleInterop()) {
            NativeModule module = getModule(str);
            if (module instanceof CxxModuleWrapper) {
                return (CxxModuleWrapper) module;
            }
            return null;
        } else if (!isLegacyModule(str)) {
            return null;
        } else {
            NativeModule module2 = getModule(str);
            if (!(module2 instanceof CxxModuleWrapper) || (module2 instanceof TurboModule)) {
                return null;
            }
            return (CxxModuleWrapper) module2;
        }
    }

    private CxxModuleWrapper getTurboLegacyCxxModule(String str) {
        if (shouldRouteTurboModulesThroughLegacyModuleInterop() || !isTurboModule(str)) {
            return null;
        }
        NativeModule module = getModule(str);
        if (!(module instanceof CxxModuleWrapper) || !(module instanceof TurboModule)) {
            return null;
        }
        return (CxxModuleWrapper) module;
    }

    private TurboModule getTurboJavaModule(String str) {
        if (shouldRouteTurboModulesThroughLegacyModuleInterop() || !isTurboModule(str)) {
            return null;
        }
        NativeModule module = getModule(str);
        if ((module instanceof CxxModuleWrapper) || !(module instanceof TurboModule)) {
            return null;
        }
        return (TurboModule) module;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0059, code lost:
        com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateStart(r4, r0.getModuleId());
        r1 = getOrCreateModule(r4, r0, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0065, code lost:
        if (r1 == null) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0067, code lost:
        com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateEnd(r4, r0.getModuleId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006f, code lost:
        com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateFail(r4, r0.getModuleId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0076, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.NativeModule getModule(java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r0 = "getModule(): Tried to get module \""
            java.lang.Object r1 = r3.mModuleCleanupLock
            monitor-enter(r1)
            boolean r2 = r3.mModuleCleanupStarted     // Catch:{ all -> 0x0077 }
            if (r2 == 0) goto L_0x003e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0077 }
            r2.<init>(r0)     // Catch:{ all -> 0x0077 }
            java.lang.StringBuilder r0 = r2.append(r4)     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = "\", but TurboModuleManager was tearing down. Returning null. Was legacy: "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0077 }
            boolean r2 = r3.isLegacyModule(r4)     // Catch:{ all -> 0x0077 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0077 }
            java.lang.String r2 = ". Was turbo: "
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0077 }
            boolean r4 = r3.isTurboModule(r4)     // Catch:{ all -> 0x0077 }
            java.lang.StringBuilder r4 = r0.append(r4)     // Catch:{ all -> 0x0077 }
            java.lang.String r0 = "."
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ all -> 0x0077 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0077 }
            r3.logError(r4)     // Catch:{ all -> 0x0077 }
            monitor-exit(r1)     // Catch:{ all -> 0x0077 }
            r4 = 0
            return r4
        L_0x003e:
            java.util.Map<java.lang.String, com.facebook.react.turbomodule.core.TurboModuleManager$ModuleHolder> r0 = r3.mModuleHolders     // Catch:{ all -> 0x0077 }
            boolean r0 = r0.containsKey(r4)     // Catch:{ all -> 0x0077 }
            if (r0 != 0) goto L_0x0050
            java.util.Map<java.lang.String, com.facebook.react.turbomodule.core.TurboModuleManager$ModuleHolder> r0 = r3.mModuleHolders     // Catch:{ all -> 0x0077 }
            com.facebook.react.turbomodule.core.TurboModuleManager$ModuleHolder r2 = new com.facebook.react.turbomodule.core.TurboModuleManager$ModuleHolder     // Catch:{ all -> 0x0077 }
            r2.<init>()     // Catch:{ all -> 0x0077 }
            r0.put(r4, r2)     // Catch:{ all -> 0x0077 }
        L_0x0050:
            java.util.Map<java.lang.String, com.facebook.react.turbomodule.core.TurboModuleManager$ModuleHolder> r0 = r3.mModuleHolders     // Catch:{ all -> 0x0077 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0077 }
            com.facebook.react.turbomodule.core.TurboModuleManager$ModuleHolder r0 = (com.facebook.react.turbomodule.core.TurboModuleManager.ModuleHolder) r0     // Catch:{ all -> 0x0077 }
            monitor-exit(r1)     // Catch:{ all -> 0x0077 }
            int r1 = r0.getModuleId()
            com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateStart(r4, r1)
            r1 = 1
            com.facebook.react.bridge.NativeModule r1 = r3.getOrCreateModule(r4, r0, r1)
            if (r1 == 0) goto L_0x006f
            int r0 = r0.getModuleId()
            com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateEnd(r4, r0)
            goto L_0x0076
        L_0x006f:
            int r0 = r0.getModuleId()
            com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateFail(r4, r0)
        L_0x0076:
            return r1
        L_0x0077:
            r4 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0077 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.turbomodule.core.TurboModuleManager.getModule(java.lang.String):com.facebook.react.bridge.NativeModule");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r5 == false) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateConstructStart(r3, r4.getModuleId());
        r5 = r2.mTurboModuleProvider.getModule(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0034, code lost:
        if (r5 != null) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        r5 = r2.mLegacyModuleProvider.getModule(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateConstructEnd(r3, r4.getModuleId());
        com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateSetUpStart(r3, r4.getModuleId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        if (r5 == null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r4.setModule(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0050, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0051, code lost:
        r5.initialize();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0058, code lost:
        logError("getOrCreateModule(): Unable to create module \"" + r3 + "\". Was legacy: " + isLegacyModule(r3) + ". Was turbo: " + isTurboModule(r3) + ".");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008c, code lost:
        com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateSetUpEnd(r3, r4.getModuleId());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0093, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r4.endCreatingModule();
        r4.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009a, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009b, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009f, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a4, code lost:
        if (r4.isCreatingModule() == false) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r4.wait();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00aa, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ac, code lost:
        if (r1 == false) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b5, code lost:
        r3 = r4.getModule();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00b9, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ba, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.react.bridge.NativeModule getOrCreateModule(java.lang.String r3, com.facebook.react.turbomodule.core.TurboModuleManager.ModuleHolder r4, boolean r5) {
        /*
            r2 = this;
            monitor-enter(r4)
            boolean r0 = r4.isDoneCreatingModule()     // Catch:{ all -> 0x00be }
            if (r0 == 0) goto L_0x0016
            if (r5 == 0) goto L_0x0010
            int r5 = r4.getModuleId()     // Catch:{ all -> 0x00be }
            com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateCacheHit(r3, r5)     // Catch:{ all -> 0x00be }
        L_0x0010:
            com.facebook.react.bridge.NativeModule r3 = r4.getModule()     // Catch:{ all -> 0x00be }
            monitor-exit(r4)     // Catch:{ all -> 0x00be }
            return r3
        L_0x0016:
            boolean r5 = r4.isCreatingModule()     // Catch:{ all -> 0x00be }
            r0 = 1
            r1 = 0
            if (r5 != 0) goto L_0x0023
            r4.startCreatingModule()     // Catch:{ all -> 0x00be }
            r5 = r0
            goto L_0x0024
        L_0x0023:
            r5 = r1
        L_0x0024:
            monitor-exit(r4)     // Catch:{ all -> 0x00be }
            if (r5 == 0) goto L_0x009f
            int r5 = r4.getModuleId()
            com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateConstructStart(r3, r5)
            com.facebook.react.turbomodule.core.TurboModuleManager$ModuleProvider r5 = r2.mTurboModuleProvider
            com.facebook.react.bridge.NativeModule r5 = r5.getModule(r3)
            if (r5 != 0) goto L_0x003c
            com.facebook.react.turbomodule.core.TurboModuleManager$ModuleProvider r5 = r2.mLegacyModuleProvider
            com.facebook.react.bridge.NativeModule r5 = r5.getModule(r3)
        L_0x003c:
            int r0 = r4.getModuleId()
            com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateConstructEnd(r3, r0)
            int r0 = r4.getModuleId()
            com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateSetUpStart(r3, r0)
            if (r5 == 0) goto L_0x0058
            monitor-enter(r4)
            r4.setModule(r5)     // Catch:{ all -> 0x0055 }
            monitor-exit(r4)     // Catch:{ all -> 0x0055 }
            r5.initialize()
            goto L_0x008c
        L_0x0055:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0055 }
            throw r3
        L_0x0058:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getOrCreateModule(): Unable to create module \""
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r1 = "\". Was legacy: "
            java.lang.StringBuilder r0 = r0.append(r1)
            boolean r1 = r2.isLegacyModule(r3)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = ". Was turbo: "
            java.lang.StringBuilder r0 = r0.append(r1)
            boolean r1 = r2.isTurboModule(r3)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "."
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.logError(r0)
        L_0x008c:
            int r0 = r4.getModuleId()
            com.facebook.react.turbomodule.core.TurboModulePerfLogger.moduleCreateSetUpEnd(r3, r0)
            monitor-enter(r4)
            r4.endCreatingModule()     // Catch:{ all -> 0x009c }
            r4.notifyAll()     // Catch:{ all -> 0x009c }
            monitor-exit(r4)     // Catch:{ all -> 0x009c }
            return r5
        L_0x009c:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x009c }
            throw r3
        L_0x009f:
            monitor-enter(r4)
        L_0x00a0:
            boolean r3 = r4.isCreatingModule()     // Catch:{ all -> 0x00bb }
            if (r3 == 0) goto L_0x00ac
            r4.wait()     // Catch:{ InterruptedException -> 0x00aa }
            goto L_0x00a0
        L_0x00aa:
            r1 = r0
            goto L_0x00a0
        L_0x00ac:
            if (r1 == 0) goto L_0x00b5
            java.lang.Thread r3 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00bb }
            r3.interrupt()     // Catch:{ all -> 0x00bb }
        L_0x00b5:
            com.facebook.react.bridge.NativeModule r3 = r4.getModule()     // Catch:{ all -> 0x00bb }
            monitor-exit(r4)     // Catch:{ all -> 0x00bb }
            return r3
        L_0x00bb:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00bb }
            throw r3
        L_0x00be:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x00be }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.turbomodule.core.TurboModuleManager.getOrCreateModule(java.lang.String, com.facebook.react.turbomodule.core.TurboModuleManager$ModuleHolder, boolean):com.facebook.react.bridge.NativeModule");
    }

    public Collection<NativeModule> getModules() {
        ArrayList<ModuleHolder> arrayList = new ArrayList<>();
        synchronized (this.mModuleCleanupLock) {
            arrayList.addAll(this.mModuleHolders.values());
        }
        ArrayList arrayList2 = new ArrayList();
        for (ModuleHolder moduleHolder : arrayList) {
            synchronized (moduleHolder) {
                if (moduleHolder.getModule() != null) {
                    arrayList2.add(moduleHolder.getModule());
                }
            }
        }
        return arrayList2;
    }

    public boolean hasModule(String str) {
        ModuleHolder moduleHolder;
        synchronized (this.mModuleCleanupLock) {
            moduleHolder = this.mModuleHolders.get(str);
        }
        if (moduleHolder == null) {
            return false;
        }
        synchronized (moduleHolder) {
            if (moduleHolder.getModule() != null) {
                return true;
            }
            return false;
        }
    }

    private void logError(String str) {
        FLog.e("TurboModuleManager", str);
        if (shouldRouteTurboModulesThroughLegacyModuleInterop()) {
            ReactSoftExceptionLogger.logSoftException("TurboModuleManager", new ReactNoCrashSoftException(str));
        }
    }

    public void onCatalystInstanceDestroy() {
        synchronized (this.mModuleCleanupLock) {
            this.mModuleCleanupStarted = true;
        }
        for (Map.Entry next : this.mModuleHolders.entrySet()) {
            NativeModule orCreateModule = getOrCreateModule((String) next.getKey(), (ModuleHolder) next.getValue(), false);
            if (orCreateModule != null) {
                orCreateModule.invalidate();
            }
        }
        this.mModuleHolders.clear();
        this.mHybridData.resetNative();
    }

    private static class ModuleHolder {
        private static volatile int sHolderCount;
        private volatile boolean mIsDoneCreatingModule = false;
        private volatile boolean mIsTryingToCreate = false;
        private volatile NativeModule mModule = null;
        private volatile int mModuleId = sHolderCount;

        public ModuleHolder() {
            sHolderCount++;
        }

        /* access modifiers changed from: package-private */
        public int getModuleId() {
            return this.mModuleId;
        }

        /* access modifiers changed from: package-private */
        public void setModule(NativeModule nativeModule) {
            this.mModule = nativeModule;
        }

        /* access modifiers changed from: package-private */
        public NativeModule getModule() {
            return this.mModule;
        }

        /* access modifiers changed from: package-private */
        public void startCreatingModule() {
            this.mIsTryingToCreate = true;
        }

        /* access modifiers changed from: package-private */
        public void endCreatingModule() {
            this.mIsTryingToCreate = false;
            this.mIsDoneCreatingModule = true;
        }

        /* access modifiers changed from: package-private */
        public boolean isDoneCreatingModule() {
            return this.mIsDoneCreatingModule;
        }

        /* access modifiers changed from: package-private */
        public boolean isCreatingModule() {
            return this.mIsTryingToCreate;
        }
    }
}
