package com.swmansion.reanimated;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.UIManagerModuleListener;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;

@ReactModule(name = "ReanimatedModule")
public class ReanimatedModule extends ReactContextBaseJavaModule implements LifecycleEventListener, UIManagerModuleListener {
    public static final String NAME = "ReanimatedModule";
    @Nullable
    private NodesManager mNodesManager;
    private ArrayList<UIThreadOperation> mOperations = new ArrayList<>();

    private interface UIThreadOperation {
        void execute(NodesManager nodesManager);
    }

    @ReactMethod
    public void addListener(String str) {
    }

    public String getName() {
        return NAME;
    }

    public void onHostDestroy() {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public ReanimatedModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public void initialize() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        reactApplicationContext.addLifecycleEventListener(this);
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIManagerListener(this);
    }

    public void onHostPause() {
        NodesManager nodesManager = this.mNodesManager;
        if (nodesManager != null) {
            nodesManager.onHostPause();
        }
    }

    public void onHostResume() {
        NodesManager nodesManager = this.mNodesManager;
        if (nodesManager != null) {
            nodesManager.onHostResume();
        }
    }

    public void willDispatchViewUpdates(UIManagerModule uIManagerModule) {
        if (!this.mOperations.isEmpty()) {
            final ArrayList<UIThreadOperation> arrayList = this.mOperations;
            this.mOperations = new ArrayList<>();
            uIManagerModule.addUIBlock(new UIBlock() {
                public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                    NodesManager nodesManager = ReanimatedModule.this.getNodesManager();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ((UIThreadOperation) it.next()).execute(nodesManager);
                    }
                }
            });
        }
    }

    public NodesManager getNodesManager() {
        if (this.mNodesManager == null) {
            this.mNodesManager = new NodesManager(getReactApplicationContext());
        }
        return this.mNodesManager;
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public void installTurboModule() {
        Utils.isChromeDebugger = getReactApplicationContext().getJavaScriptContextHolder().get() == 0;
        if (!Utils.isChromeDebugger) {
            getNodesManager().initWithContext(getReactApplicationContext());
        } else {
            SentryLogcatAdapter.w("[REANIMATED]", "Unable to create Reanimated Native Module. You can ignore this message if you are using Chrome Debugger now.");
        }
    }

    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        NodesManager nodesManager = this.mNodesManager;
        if (nodesManager != null) {
            nodesManager.onCatalystInstanceDestroy();
        }
    }
}
