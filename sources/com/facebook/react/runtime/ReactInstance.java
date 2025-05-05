package com.facebook.react.runtime;

import android.content.res.AssetManager;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.react.ReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JSBundleLoaderDelegate;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.react.bridge.RuntimeScheduler;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.MessageQueueThreadSpec;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.fabric.BindingImpl;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.ReactNativeConfig;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.JavaTimerManager;
import com.facebook.react.modules.core.ReactChoreographer;
import com.facebook.react.turbomodule.core.CallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.NativeMethodCallInvokerHolderImpl;
import com.facebook.react.turbomodule.core.TurboModuleManager;
import com.facebook.react.uimanager.ComponentNameResolverManager;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.UIConstantsProviderManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.ViewManagerResolver;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;

final class ReactInstance {
    private static final String TAG = "ReactInstance";
    private static volatile boolean sIsLibraryLoaded;
    /* access modifiers changed from: private */
    public final BridgelessReactContext mBridgelessReactContext;
    @Nullable
    private ComponentNameResolverManager mComponentNameResolverManager;
    private final ReactHostDelegate mDelegate;
    private final FabricUIManager mFabricUIManager;
    private final HybridData mHybridData;
    /* access modifiers changed from: private */
    public final JavaTimerManager mJavaTimerManager;
    private final ReactQueueConfiguration mQueueConfiguration;
    private final List<ReactPackage> mReactPackages;
    private final TurboModuleManager mTurboModuleManager;
    @Nullable
    private UIConstantsProviderManager mUIConstantsProviderManager;
    private final Map<String, ViewManager> mViewManagers = new ConcurrentHashMap();

    private static native JSTimerExecutor createJSTimerExecutor();

    private native RuntimeExecutor getBufferedRuntimeExecutor();

    private native CallInvokerHolderImpl getJSCallInvokerHolder();

    private native NativeMethodCallInvokerHolderImpl getNativeMethodCallInvokerHolder();

    private native RuntimeScheduler getRuntimeScheduler();

    private native RuntimeExecutor getUnbufferedRuntimeExecutor();

    private native void handleMemoryPressureJs(int i);

    private native HybridData initHybrid(JSEngineInstance jSEngineInstance, MessageQueueThread messageQueueThread, MessageQueueThread messageQueueThread2, JavaTimerManager javaTimerManager, JSTimerExecutor jSTimerExecutor, ReactJsExceptionHandler reactJsExceptionHandler, @Nullable BindingsInstaller bindingsInstaller, boolean z);

    private native void installGlobals(boolean z);

    /* access modifiers changed from: private */
    public native void loadJSBundleFromAssets(AssetManager assetManager, String str);

    /* access modifiers changed from: private */
    public native void loadJSBundleFromFile(String str, String str2);

    private native void registerSegmentNative(int i, String str);

    /* access modifiers changed from: package-private */
    public native void callFunctionOnModule(String str, String str2, NativeArray nativeArray);

    static {
        loadLibraryIfNeeded();
    }

    ReactInstance(BridgelessReactContext bridgelessReactContext, ReactHostDelegate reactHostDelegate, ComponentFactory componentFactory, DevSupportManager devSupportManager, QueueThreadExceptionHandler queueThreadExceptionHandler, ReactJsExceptionHandler reactJsExceptionHandler, boolean z) {
        this.mBridgelessReactContext = bridgelessReactContext;
        this.mDelegate = reactHostDelegate;
        Systrace.beginSection(0, "ReactInstance.initialize");
        ReactQueueConfigurationImpl create = ReactQueueConfigurationImpl.create(ReactQueueConfigurationSpec.builder().setJSQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec("v_js")).setNativeModulesQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec("v_native")).build(), queueThreadExceptionHandler);
        this.mQueueConfiguration = create;
        FLog.d(TAG, "Calling initializeMessageQueueThreads()");
        bridgelessReactContext.initializeMessageQueueThreads(create);
        MessageQueueThread jSQueueThread = create.getJSQueueThread();
        MessageQueueThread nativeModulesQueueThread = create.getNativeModulesQueueThread();
        ReactChoreographer.initialize();
        if (z) {
            devSupportManager.startInspector();
        }
        JSTimerExecutor createJSTimerExecutor = createJSTimerExecutor();
        JavaTimerManager javaTimerManager = new JavaTimerManager(bridgelessReactContext, createJSTimerExecutor, ReactChoreographer.getInstance(), devSupportManager);
        this.mJavaTimerManager = javaTimerManager;
        bridgelessReactContext.addLifecycleEventListener(new LifecycleEventListener() {
            public void onHostResume() {
                ReactInstance.this.mJavaTimerManager.onHostResume();
            }

            public void onHostPause() {
                ReactInstance.this.mJavaTimerManager.onHostPause();
            }

            public void onHostDestroy() {
                ReactInstance.this.mJavaTimerManager.onHostDestroy();
            }
        });
        this.mHybridData = initHybrid(reactHostDelegate.getJsEngineInstance(), jSQueueThread, nativeModulesQueueThread, javaTimerManager, createJSTimerExecutor, reactJsExceptionHandler, reactHostDelegate.getBindingsInstaller(), Systrace.isTracing(0));
        RuntimeExecutor unbufferedRuntimeExecutor = getUnbufferedRuntimeExecutor();
        this.mComponentNameResolverManager = new ComponentNameResolverManager(unbufferedRuntimeExecutor, new ReactInstance$$ExternalSyntheticLambda0(this));
        Systrace.beginSection(0, "ReactInstance.initialize#initTurboModules");
        ArrayList arrayList = new ArrayList(reactHostDelegate.getReactPackages());
        this.mReactPackages = arrayList;
        arrayList.add(new CoreReactPackage(bridgelessReactContext.getDevSupportManager(), bridgelessReactContext.getDefaultHardwareBackBtnHandler()));
        TurboModuleManager turboModuleManager = new TurboModuleManager(unbufferedRuntimeExecutor, reactHostDelegate.getTurboModuleManagerDelegateBuilder().setPackages(arrayList).setReactApplicationContext(bridgelessReactContext).build(), getJSCallInvokerHolder(), getNativeMethodCallInvokerHolder());
        this.mTurboModuleManager = turboModuleManager;
        for (String module : turboModuleManager.getEagerInitModuleNames()) {
            this.mTurboModuleManager.getModule(module);
        }
        Systrace.endSection(0);
        if (ReactFeatureFlags.useNativeViewConfigsInBridgelessMode) {
            this.mUIConstantsProviderManager = new UIConstantsProviderManager(unbufferedRuntimeExecutor, new ReactInstance$$ExternalSyntheticLambda1(this));
        }
        Systrace.beginSection(0, "ReactInstance.initialize#initFabric");
        ViewManagerRegistry viewManagerRegistry = new ViewManagerRegistry((ViewManagerResolver) new ViewManagerResolver() {
            @Nullable
            public ViewManager getViewManager(String str) {
                return ReactInstance.this.createViewManager(str);
            }

            public Collection<String> getViewManagerNames() {
                return ReactInstance.this.getViewManagerNames();
            }
        });
        EventBeatManager eventBeatManager = new EventBeatManager();
        FabricUIManager fabricUIManager = new FabricUIManager(this.mBridgelessReactContext, viewManagerRegistry, eventBeatManager);
        this.mFabricUIManager = fabricUIManager;
        ReactNativeConfig reactNativeConfig = this.mDelegate.getReactNativeConfig(this.mTurboModuleManager);
        DisplayMetricsHolder.initDisplayMetricsIfNotInitialized(this.mBridgelessReactContext);
        new BindingImpl().register(getBufferedRuntimeExecutor(), getRuntimeScheduler(), fabricUIManager, eventBeatManager, componentFactory, reactNativeConfig);
        Systrace.endSection(0);
        fabricUIManager.initialize();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String[] lambda$new$0() {
        Collection<String> viewManagerNames = getViewManagerNames();
        if (viewManagerNames.size() >= 1) {
            return (String[]) viewManagerNames.toArray(new String[0]);
        }
        FLog.e(TAG, "No ViewManager names found");
        return new String[0];
    }

    private static synchronized void loadLibraryIfNeeded() {
        synchronized (ReactInstance.class) {
            if (!sIsLibraryLoaded) {
                SoLoader.loadLibrary("rninstance");
                sIsLibraryLoaded = true;
            }
        }
    }

    public ReactQueueConfiguration getReactQueueConfiguration() {
        return this.mQueueConfiguration;
    }

    public void loadJSBundle(JSBundleLoader jSBundleLoader) {
        Systrace.beginSection(0, "ReactInstance.loadJSBundle");
        jSBundleLoader.loadScript(new JSBundleLoaderDelegate() {
            public void loadScriptFromFile(String str, String str2, boolean z) {
                ReactInstance.this.mBridgelessReactContext.setSourceURL(str2);
                ReactInstance.this.loadJSBundleFromFile(str, str2);
            }

            public void loadSplitBundleFromFile(String str, String str2) {
                ReactInstance.this.loadJSBundleFromFile(str, str2);
            }

            public void loadScriptFromAssets(AssetManager assetManager, String str, boolean z) {
                ReactInstance.this.mBridgelessReactContext.setSourceURL(str);
                ReactInstance.this.loadJSBundleFromAssets(assetManager, str);
            }

            public void setSourceURLs(String str, String str2) {
                ReactInstance.this.mBridgelessReactContext.setSourceURL(str);
            }
        });
        Systrace.endSection(0);
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> cls) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return this.mTurboModuleManager.hasModule(reactModule.name());
        }
        return false;
    }

    public Collection<NativeModule> getNativeModules() {
        return new ArrayList(this.mTurboModuleManager.getModules());
    }

    @Nullable
    public <T extends NativeModule> T getNativeModule(Class<T> cls) {
        ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
        if (reactModule != null) {
            return getNativeModule(reactModule.name());
        }
        return null;
    }

    @Nullable
    public NativeModule getNativeModule(String str) {
        NativeModule module;
        synchronized (this.mTurboModuleManager) {
            module = this.mTurboModuleManager.getModule(str);
        }
        return module;
    }

    /* access modifiers changed from: package-private */
    public void prerenderSurface(ReactSurfaceImpl reactSurfaceImpl) {
        Systrace.beginSection(0, "ReactInstance.prerenderSurface");
        FLog.d(TAG, "call prerenderSurface with surface: " + reactSurfaceImpl.getModuleName());
        this.mFabricUIManager.startSurface(reactSurfaceImpl.getSurfaceHandler(), reactSurfaceImpl.getContext(), (View) null);
        Systrace.endSection(0);
    }

    /* access modifiers changed from: package-private */
    public void startSurface(ReactSurfaceImpl reactSurfaceImpl) {
        String str = TAG;
        FLog.d(str, "startSurface() is called with surface: " + reactSurfaceImpl.getSurfaceID());
        Systrace.beginSection(0, "ReactInstance.startSurface");
        ViewGroup view = reactSurfaceImpl.getView();
        if (view != null) {
            if (view.getId() != -1) {
                ReactSoftExceptionLogger.logSoftException(str, new IllegalViewOperationException("surfaceView's is NOT equal to View.NO_ID before calling startSurface."));
                view.setId(-1);
            }
            if (reactSurfaceImpl.isRunning()) {
                this.mFabricUIManager.attachRootView(reactSurfaceImpl.getSurfaceHandler(), view);
            } else {
                this.mFabricUIManager.startSurface(reactSurfaceImpl.getSurfaceHandler(), reactSurfaceImpl.getContext(), view);
            }
            Systrace.endSection(0);
            return;
        }
        throw new IllegalStateException("Starting surface without a view is not supported, use prerenderSurface instead.");
    }

    /* access modifiers changed from: package-private */
    public void stopSurface(ReactSurfaceImpl reactSurfaceImpl) {
        FLog.d(TAG, "stopSurface() is called with surface: " + reactSurfaceImpl.getSurfaceID());
        this.mFabricUIManager.stopSurface(reactSurfaceImpl.getSurfaceHandler());
    }

    /* access modifiers changed from: package-private */
    public void destroy() {
        FLog.d(TAG, "ReactInstance.destroy() is called.");
        this.mQueueConfiguration.destroy();
        this.mTurboModuleManager.onCatalystInstanceDestroy();
        this.mFabricUIManager.onCatalystInstanceDestroy();
        this.mHybridData.resetNative();
        this.mComponentNameResolverManager = null;
        this.mUIConstantsProviderManager = null;
    }

    public void handleMemoryPressure(int i) {
        try {
            handleMemoryPressureJs(i);
        } catch (NullPointerException unused) {
            ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Native method handleMemoryPressureJs is called earlier than librninstance.so got ready."));
        }
    }

    /* access modifiers changed from: package-private */
    public EventDispatcher getEventDispatcher() {
        return this.mFabricUIManager.getEventDispatcher();
    }

    /* access modifiers changed from: package-private */
    public FabricUIManager getUIManager() {
        return this.mFabricUIManager;
    }

    public void registerSegment(int i, String str) {
        registerSegmentNative(i, str);
    }

    /* access modifiers changed from: private */
    @Nullable
    public ViewManager createViewManager(String str) {
        ViewManager createViewManager;
        if (this.mViewManagers.containsKey(str)) {
            return this.mViewManagers.get(str);
        }
        List<ReactPackage> list = this.mReactPackages;
        if (!(this.mDelegate == null || list == null)) {
            synchronized (list) {
                for (ReactPackage next : list) {
                    if ((next instanceof ViewManagerOnDemandReactPackage) && (createViewManager = ((ViewManagerOnDemandReactPackage) next).createViewManager(this.mBridgelessReactContext, str)) != null) {
                        this.mViewManagers.put(str, createViewManager);
                        return createViewManager;
                    }
                }
            }
        }
        for (ReactPackage createViewManagers : list) {
            for (ViewManager next2 : createViewManagers.createViewManagers(this.mBridgelessReactContext)) {
                this.mViewManagers.put(next2.getName(), next2);
            }
        }
        return this.mViewManagers.get(str);
    }

    /* access modifiers changed from: private */
    public Collection<String> getViewManagerNames() {
        List<ReactPackage> list;
        Collection<String> viewManagerNames;
        HashSet hashSet = new HashSet();
        if (!(this.mDelegate == null || (list = this.mReactPackages) == null)) {
            synchronized (list) {
                for (ReactPackage next : list) {
                    if ((next instanceof ViewManagerOnDemandReactPackage) && (viewManagerNames = ((ViewManagerOnDemandReactPackage) next).getViewManagerNames(this.mBridgelessReactContext)) != null) {
                        hashSet.addAll(viewManagerNames);
                    }
                }
            }
        }
        return hashSet;
    }

    /* access modifiers changed from: private */
    /* renamed from: getUIManagerConstants */
    public NativeMap lambda$new$1() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        List<ReactPackage> list = this.mReactPackages;
        Iterator<ReactPackage> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!(it.next() instanceof ViewManagerOnDemandReactPackage)) {
                    z = false;
                    break;
                }
            } else {
                z = true;
                break;
            }
        }
        if (z) {
            for (String createViewManager : getViewManagerNames()) {
                arrayList.add(createViewManager(createViewManager));
            }
        } else {
            for (ReactPackage createViewManagers : list) {
                arrayList.addAll(createViewManagers.createViewManagers(this.mBridgelessReactContext));
            }
        }
        return Arguments.makeNativeMap(UIManagerModule.createConstants(arrayList, new HashMap(), new HashMap()));
    }
}
