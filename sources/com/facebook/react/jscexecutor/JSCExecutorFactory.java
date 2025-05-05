package com.facebook.react.jscexecutor;

import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.ReactConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/facebook/react/jscexecutor/JSCExecutorFactory;", "Lcom/facebook/react/bridge/JavaScriptExecutorFactory;", "appName", "", "deviceName", "(Ljava/lang/String;Ljava/lang/String;)V", "create", "Lcom/facebook/react/bridge/JavaScriptExecutor;", "startSamplingProfiler", "", "stopSamplingProfiler", "filename", "toString", "ReactAndroid_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JSCExecutorFactory.kt */
public final class JSCExecutorFactory implements JavaScriptExecutorFactory {
    private final String appName;
    private final String deviceName;

    public String toString() {
        return "JSIExecutor+JSCRuntime";
    }

    public JSCExecutorFactory(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "appName");
        Intrinsics.checkNotNullParameter(str2, "deviceName");
        this.appName = str;
        this.deviceName = str2;
    }

    public JavaScriptExecutor create() throws Exception {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("OwnerIdentity", ReactConstants.TAG);
        writableNativeMap.putString("AppIdentity", this.appName);
        writableNativeMap.putString("DeviceIdentity", this.deviceName);
        return new JSCExecutor(writableNativeMap);
    }

    public void startSamplingProfiler() {
        throw new UnsupportedOperationException("Starting sampling profiler not supported on " + this);
    }

    public void stopSamplingProfiler(String str) {
        Intrinsics.checkNotNullParameter(str, "filename");
        throw new UnsupportedOperationException("Stopping sampling profiler not supported on " + this);
    }
}
