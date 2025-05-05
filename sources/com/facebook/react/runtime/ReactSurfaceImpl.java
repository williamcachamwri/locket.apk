package com.facebook.react.runtime;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import androidx.camera.view.PreviewView$1$$ExternalSyntheticBackportWithForwarding0;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.fabric.SurfaceHandlerBinding;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.interfaces.fabric.SurfaceHandler;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.runtime.internal.bolts.Task;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

public class ReactSurfaceImpl implements ReactSurface {
    private Context mContext;
    private final AtomicReference<ReactHostImpl> mReactHost;
    private final SurfaceHandler mSurfaceHandler;
    private final AtomicReference<ReactSurfaceView> mSurfaceView;

    public static ReactSurfaceImpl createWithView(Context context, String str, @Nullable Bundle bundle) {
        ReactSurfaceImpl reactSurfaceImpl = new ReactSurfaceImpl(context, str, bundle);
        reactSurfaceImpl.attachView(new ReactSurfaceView(context, reactSurfaceImpl));
        return reactSurfaceImpl;
    }

    public ReactSurfaceImpl(Context context, String str, @Nullable Bundle bundle) {
        this(new SurfaceHandlerBinding(str), context);
        NativeMap nativeMap;
        if (bundle == null) {
            nativeMap = new WritableNativeMap();
        } else {
            nativeMap = (NativeMap) Arguments.fromBundle(bundle);
        }
        this.mSurfaceHandler.setProps(nativeMap);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.mSurfaceHandler.setLayoutConstraints(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, Integer.MIN_VALUE), 0, 0, doRTLSwap(context), isRTL(context), getPixelDensity(context));
    }

    @VisibleForTesting
    ReactSurfaceImpl(SurfaceHandler surfaceHandler, Context context) {
        this.mSurfaceView = new AtomicReference<>((Object) null);
        this.mReactHost = new AtomicReference<>((Object) null);
        this.mSurfaceHandler = surfaceHandler;
        this.mContext = context;
    }

    public void attach(ReactHostImpl reactHostImpl) {
        if (!PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.mReactHost, (Object) null, reactHostImpl)) {
            throw new IllegalStateException("This surface is already attached to a host!");
        }
    }

    public void attachView(ReactSurfaceView reactSurfaceView) {
        if (PreviewView$1$$ExternalSyntheticBackportWithForwarding0.m(this.mSurfaceView, (Object) null, reactSurfaceView)) {
            this.mContext = reactSurfaceView.getContext();
            return;
        }
        throw new IllegalStateException("Trying to call ReactSurface.attachView(), but the view is already attached.");
    }

    public void updateInitProps(Bundle bundle) {
        this.mSurfaceHandler.setProps((NativeMap) Arguments.fromBundle(bundle));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public ReactHostImpl getReactHost() {
        return this.mReactHost.get();
    }

    public void detach() {
        this.mReactHost.set((Object) null);
    }

    /* access modifiers changed from: package-private */
    public SurfaceHandler getSurfaceHandler() {
        return this.mSurfaceHandler;
    }

    @Nullable
    public ViewGroup getView() {
        return this.mSurfaceView.get();
    }

    public TaskInterface<Void> prerender() {
        ReactHostImpl reactHostImpl = this.mReactHost.get();
        if (reactHostImpl == null) {
            return Task.forError(new IllegalStateException("Trying to call ReactSurface.prerender(), but no ReactHost is attached."));
        }
        return reactHostImpl.prerenderSurface(this);
    }

    public TaskInterface<Void> start() {
        if (this.mSurfaceView.get() == null) {
            return Task.forError(new IllegalStateException("Trying to call ReactSurface.start(), but view is not created."));
        }
        ReactHostImpl reactHostImpl = this.mReactHost.get();
        if (reactHostImpl == null) {
            return Task.forError(new IllegalStateException("Trying to call ReactSurface.start(), but no ReactHost is attached."));
        }
        return reactHostImpl.startSurface(this);
    }

    public TaskInterface<Void> stop() {
        ReactHostImpl reactHostImpl = this.mReactHost.get();
        if (reactHostImpl == null) {
            return Task.forError(new IllegalStateException("Trying to call ReactSurface.stop(), but no ReactHost is attached."));
        }
        return reactHostImpl.stopSurface(this);
    }

    public int getSurfaceID() {
        return this.mSurfaceHandler.getSurfaceId();
    }

    public String getModuleName() {
        return this.mSurfaceHandler.getModuleName();
    }

    public void clear() {
        UiThreadUtil.runOnUiThread(new ReactSurfaceImpl$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clear$0() {
        ReactSurfaceView reactSurfaceView = (ReactSurfaceView) getView();
        if (reactSurfaceView != null) {
            reactSurfaceView.removeAllViews();
            reactSurfaceView.setId(-1);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void updateLayoutSpecs(int i, int i2, int i3, int i4) {
        this.mSurfaceHandler.setLayoutConstraints(i, i2, i3, i4, doRTLSwap(this.mContext), isRTL(this.mContext), getPixelDensity(this.mContext));
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public EventDispatcher getEventDispatcher() {
        ReactHostImpl reactHostImpl = this.mReactHost.get();
        if (reactHostImpl == null) {
            return null;
        }
        return reactHostImpl.getEventDispatcher();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isAttached() {
        return this.mReactHost.get() != null;
    }

    public boolean isRunning() {
        return this.mSurfaceHandler.isRunning();
    }

    public Context getContext() {
        return this.mContext;
    }

    private static float getPixelDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    private static boolean isRTL(Context context) {
        return I18nUtil.getInstance().isRTL(context);
    }

    private static boolean doRTLSwap(Context context) {
        return I18nUtil.getInstance().doLeftAndRightSwapInRTL(context);
    }
}
