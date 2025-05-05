package com.facebook.react.runtime;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.HMRClient;
import com.facebook.react.devsupport.ReactInstanceDevHelper;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;
import java.util.Map;
import javax.annotation.Nullable;

class BridgelessDevSupportManager extends DevSupportManagerBase {
    /* access modifiers changed from: private */
    public final ReactHostImpl mReactHost;

    /* access modifiers changed from: protected */
    public String getUniqueTag() {
        return "Bridgeless";
    }

    public BridgelessDevSupportManager(ReactHostImpl reactHostImpl, Context context, @Nullable String str) {
        super(context.getApplicationContext(), createInstanceDevHelper(reactHostImpl), str, true, (RedBoxHandler) null, (DevBundleDownloadListener) null, 2, (Map<String, RequestHandler>) null, (SurfaceDelegateFactory) null, (DevLoadingViewManager) null);
        this.mReactHost = reactHostImpl;
    }

    public void loadSplitBundleFromServer(final String str, final DevSplitBundleCallback devSplitBundleCallback) {
        fetchSplitBundleAndCreateBundleLoader(str, new DevSupportManagerBase.CallbackWithBundleLoader() {
            public void onSuccess(JSBundleLoader jSBundleLoader) {
                BridgelessDevSupportManager.this.mReactHost.loadBundle(jSBundleLoader).onSuccess(new Continuation<Boolean, Void>() {
                    public Void then(Task<Boolean> task) {
                        if (!task.getResult().equals(Boolean.TRUE)) {
                            return null;
                        }
                        String devServerSplitBundleURL = BridgelessDevSupportManager.this.getDevServerHelper().getDevServerSplitBundleURL(str);
                        ReactContext currentReactContext = BridgelessDevSupportManager.this.mReactHost.getCurrentReactContext();
                        if (currentReactContext != null) {
                            ((HMRClient) currentReactContext.getJSModule(HMRClient.class)).registerBundle(devServerSplitBundleURL);
                        }
                        devSplitBundleCallback.onSuccess();
                        return null;
                    }
                });
            }

            public void onError(String str, Throwable th) {
                devSplitBundleCallback.onError(str, th);
            }
        });
    }

    public void handleReloadJS() {
        hideRedboxDialog();
        this.mReactHost.reload("BridgelessDevSupportManager.handleReloadJS()");
    }

    private static ReactInstanceDevHelper createInstanceDevHelper(final ReactHostImpl reactHostImpl) {
        return new ReactInstanceDevHelper() {
            public void destroyRootView(View view) {
            }

            public void onReloadWithJSDebugger(JavaJSExecutor.Factory factory) {
            }

            public void onJSBundleLoadedFromServer() {
                throw new IllegalStateException("Not implemented for bridgeless mode");
            }

            public void toggleElementInspector() {
                ReactContext currentReactContext = ReactHostImpl.this.getCurrentReactContext();
                if (currentReactContext != null) {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("toggleElementInspector", (Object) null);
                }
            }

            public Activity getCurrentActivity() {
                return ReactHostImpl.this.getLastUsedActivity();
            }

            public JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
                throw new IllegalStateException("Not implemented for bridgeless mode");
            }

            public View createRootView(String str) {
                Activity currentActivity = getCurrentActivity();
                if (currentActivity == null || ReactHostImpl.this.isSurfaceWithModuleNameAttached(str)) {
                    return null;
                }
                ReactSurfaceImpl createWithView = ReactSurfaceImpl.createWithView(currentActivity, str, (Bundle) null);
                createWithView.attach(ReactHostImpl.this);
                createWithView.start();
                return createWithView.getView();
            }
        };
    }
}
