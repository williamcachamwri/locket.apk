package com.facebook.react.devsupport;

import com.facebook.fbreact.specs.NativeLogBoxSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "LogBox")
public class LogBoxModule extends NativeLogBoxSpec {
    public static final String NAME = "LogBox";
    private final DevSupportManager mDevSupportManager;
    /* access modifiers changed from: private */
    public final SurfaceDelegate mSurfaceDelegate;

    public LogBoxModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager) {
        super(reactApplicationContext);
        this.mDevSupportManager = devSupportManager;
        SurfaceDelegate createSurfaceDelegate = devSupportManager.createSurfaceDelegate("LogBox");
        if (createSurfaceDelegate != null) {
            this.mSurfaceDelegate = createSurfaceDelegate;
        } else {
            this.mSurfaceDelegate = new LogBoxDialogSurfaceDelegate(devSupportManager);
        }
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                LogBoxModule.this.mSurfaceDelegate.createContentView("LogBox");
            }
        });
    }

    public void show() {
        if (this.mSurfaceDelegate.isContentViewReady()) {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    LogBoxModule.this.mSurfaceDelegate.show();
                }
            });
        }
    }

    public void hide() {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                LogBoxModule.this.mSurfaceDelegate.hide();
            }
        });
    }

    public void invalidate() {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                LogBoxModule.this.mSurfaceDelegate.destroyContentView();
            }
        });
    }
}
