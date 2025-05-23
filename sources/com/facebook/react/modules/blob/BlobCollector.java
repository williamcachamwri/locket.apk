package com.facebook.react.modules.blob;

import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.ReactContext;
import com.facebook.soloader.SoLoader;

class BlobCollector {
    /* access modifiers changed from: private */
    public static native void nativeInstall(Object obj, long j);

    BlobCollector() {
    }

    static {
        SoLoader.loadLibrary("reactnativeblob");
    }

    static void install(final ReactContext reactContext, final BlobModule blobModule) {
        reactContext.runOnJSQueueThread(new Runnable() {
            public void run() {
                JavaScriptContextHolder javaScriptContextHolder = ReactContext.this.getJavaScriptContextHolder();
                if (javaScriptContextHolder != null && javaScriptContextHolder.get() != 0) {
                    BlobCollector.nativeInstall(blobModule, javaScriptContextHolder.get());
                }
            }
        });
    }
}
