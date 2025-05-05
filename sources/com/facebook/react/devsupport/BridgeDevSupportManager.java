package com.facebook.react.devsupport;

import android.content.Context;
import android.widget.Toast;
import com.facebook.common.logging.FLog;
import com.facebook.debug.holder.PrinterHolder;
import com.facebook.debug.tags.ReactDebugOverlayTags;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.R;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.SurfaceDelegateFactory;
import com.facebook.react.common.futures.SimpleSettableFuture;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.WebsocketJavaScriptExecutor;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSplitBundleCallback;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import com.facebook.react.packagerconnection.RequestHandler;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class BridgeDevSupportManager extends DevSupportManagerBase {
    private DevLoadingViewManager mDevLoadingViewManager;
    private boolean mIsSamplingProfilerEnabled = false;
    private ReactInstanceDevHelper mReactInstanceManagerHelper;

    /* access modifiers changed from: protected */
    public String getUniqueTag() {
        return "Bridge";
    }

    public BridgeDevSupportManager(Context context, ReactInstanceDevHelper reactInstanceDevHelper, String str, boolean z, RedBoxHandler redBoxHandler, DevBundleDownloadListener devBundleDownloadListener, int i, Map<String, RequestHandler> map, SurfaceDelegateFactory surfaceDelegateFactory, DevLoadingViewManager devLoadingViewManager) {
        super(context, reactInstanceDevHelper, str, z, redBoxHandler, devBundleDownloadListener, i, map, surfaceDelegateFactory, devLoadingViewManager);
        if (getDevSettings().isStartSamplingProfilerOnInit()) {
            if (!this.mIsSamplingProfilerEnabled) {
                toggleJSSamplingProfiler();
            } else {
                Toast.makeText(context, "JS Sampling Profiler was already running, so did not start the sampling profiler", 1).show();
            }
        }
        addCustomDevOption(this.mIsSamplingProfilerEnabled ? "Disable Sampling Profiler" : "Enable Sampling Profiler", new DevOptionHandler() {
            public void onOptionSelected() {
                BridgeDevSupportManager.this.toggleJSSamplingProfiler();
            }
        });
    }

    public void loadSplitBundleFromServer(final String str, final DevSplitBundleCallback devSplitBundleCallback) {
        fetchSplitBundleAndCreateBundleLoader(str, new DevSupportManagerBase.CallbackWithBundleLoader() {
            public void onSuccess(JSBundleLoader jSBundleLoader) {
                jSBundleLoader.loadScript(BridgeDevSupportManager.this.getCurrentContext().getCatalystInstance());
                ((HMRClient) BridgeDevSupportManager.this.getCurrentContext().getJSModule(HMRClient.class)).registerBundle(BridgeDevSupportManager.this.getDevServerHelper().getDevServerSplitBundleURL(str));
                devSplitBundleCallback.onSuccess();
            }

            public void onError(String str, Throwable th) {
                devSplitBundleCallback.onError(str, th);
            }
        });
    }

    /* access modifiers changed from: private */
    public WebsocketJavaScriptExecutor.JSExecutorConnectCallback getExecutorConnectCallback(final SimpleSettableFuture<Boolean> simpleSettableFuture) {
        return new WebsocketJavaScriptExecutor.JSExecutorConnectCallback() {
            public void onSuccess() {
                simpleSettableFuture.set(true);
                BridgeDevSupportManager.this.hideDevLoadingView();
            }

            public void onFailure(Throwable th) {
                BridgeDevSupportManager.this.hideDevLoadingView();
                FLog.e(ReactConstants.TAG, "Failed to connect to debugger!", th);
                simpleSettableFuture.setException(new IOException(BridgeDevSupportManager.this.getApplicationContext().getString(R.string.catalyst_debug_error), th));
            }
        };
    }

    private void reloadJSInProxyMode() {
        getDevServerHelper().launchJSDevtools();
        getReactInstanceDevHelper().onReloadWithJSDebugger(new JavaJSExecutor.Factory() {
            public JavaJSExecutor create() throws Exception {
                WebsocketJavaScriptExecutor websocketJavaScriptExecutor = new WebsocketJavaScriptExecutor();
                SimpleSettableFuture simpleSettableFuture = new SimpleSettableFuture();
                websocketJavaScriptExecutor.connect(BridgeDevSupportManager.this.getDevServerHelper().getWebsocketProxyURL(), BridgeDevSupportManager.this.getExecutorConnectCallback(simpleSettableFuture));
                try {
                    simpleSettableFuture.get(90, TimeUnit.SECONDS);
                    return websocketJavaScriptExecutor;
                } catch (ExecutionException e) {
                    throw ((Exception) e.getCause());
                } catch (InterruptedException | TimeoutException e2) {
                    throw new RuntimeException(e2);
                }
            }
        });
    }

    public void handleReloadJS() {
        UiThreadUtil.assertOnUiThread();
        ReactMarker.logMarker(ReactMarkerConstants.RELOAD, getDevSettings().getPackagerConnectionSettings().getDebugServerHost());
        hideRedboxDialog();
        if (getDevSettings().isRemoteJSDebugEnabled()) {
            PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: load from Proxy");
            showDevLoadingViewForRemoteJSEnabled();
            reloadJSInProxyMode();
            return;
        }
        PrinterHolder.getPrinter().logMessage(ReactDebugOverlayTags.RN_CORE, "RNCore: load from Server");
        reloadJSFromServer(getDevServerHelper().getDevServerBundleURL((String) Assertions.assertNotNull(getJSAppBundleName())));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0024, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        android.widget.Toast.makeText(getApplicationContext(), r1.toString() + " does not support Sampling Profiler", 1).show();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0049, code lost:
        r7.mIsSamplingProfilerEnabled = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x004b, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x007e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0026 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void toggleJSSamplingProfiler() {
        /*
            r7 = this;
            java.lang.String r0 = "Saved results from Profiler to "
            com.facebook.react.devsupport.ReactInstanceDevHelper r1 = r7.getReactInstanceDevHelper()
            com.facebook.react.bridge.JavaScriptExecutorFactory r1 = r1.getJavaScriptExecutorFactory()
            boolean r2 = r7.mIsSamplingProfilerEnabled
            r3 = 0
            r4 = 1
            if (r2 != 0) goto L_0x004c
            r1.startSamplingProfiler()     // Catch:{ UnsupportedOperationException -> 0x0026 }
            android.content.Context r0 = r7.getApplicationContext()     // Catch:{ UnsupportedOperationException -> 0x0026 }
            java.lang.String r2 = "Starting Sampling Profiler"
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r2, r3)     // Catch:{ UnsupportedOperationException -> 0x0026 }
            r0.show()     // Catch:{ UnsupportedOperationException -> 0x0026 }
        L_0x0020:
            r7.mIsSamplingProfilerEnabled = r4
            goto L_0x00aa
        L_0x0024:
            r0 = move-exception
            goto L_0x0049
        L_0x0026:
            android.content.Context r0 = r7.getApplicationContext()     // Catch:{ all -> 0x0024 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0024 }
            r2.<init>()     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0024 }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ all -> 0x0024 }
            java.lang.String r2 = " does not support Sampling Profiler"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0024 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0024 }
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r4)     // Catch:{ all -> 0x0024 }
            r0.show()     // Catch:{ all -> 0x0024 }
            goto L_0x0020
        L_0x0049:
            r7.mIsSamplingProfilerEnabled = r4
            throw r0
        L_0x004c:
            java.lang.String r2 = "sampling-profiler-trace"
            java.lang.String r5 = ".cpuprofile"
            android.content.Context r6 = r7.getApplicationContext()     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            java.io.File r6 = r6.getCacheDir()     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            java.io.File r2 = java.io.File.createTempFile(r2, r5, r6)     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            java.lang.String r2 = r2.getPath()     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            r1.stopSamplingProfiler(r2)     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            android.content.Context r5 = r7.getApplicationContext()     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            r6.<init>(r0)     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            java.lang.StringBuilder r0 = r6.append(r2)     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            android.widget.Toast r0 = android.widget.Toast.makeText(r5, r0, r4)     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            r0.show()     // Catch:{ IOException -> 0x00a1, UnsupportedOperationException -> 0x007e }
            goto L_0x00a8
        L_0x007c:
            r0 = move-exception
            goto L_0x00ab
        L_0x007e:
            android.content.Context r0 = r7.getApplicationContext()     // Catch:{ all -> 0x007c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x007c }
            r2.<init>()     // Catch:{ all -> 0x007c }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x007c }
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ all -> 0x007c }
            java.lang.String r2 = "does not support Sampling Profiler"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x007c }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x007c }
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r4)     // Catch:{ all -> 0x007c }
            r0.show()     // Catch:{ all -> 0x007c }
            goto L_0x00a8
        L_0x00a1:
            java.lang.String r0 = "ReactNative"
            java.lang.String r1 = "Could not create temporary file for saving results from Sampling Profiler"
            com.facebook.common.logging.FLog.e((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x007c }
        L_0x00a8:
            r7.mIsSamplingProfilerEnabled = r3
        L_0x00aa:
            return
        L_0x00ab:
            r7.mIsSamplingProfilerEnabled = r3
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.devsupport.BridgeDevSupportManager.toggleJSSamplingProfiler():void");
    }
}
