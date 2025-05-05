package com.locket.Locket.Ads;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import com.adsbynimbus.NimbusAdManager;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.locket.Locket.R;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.Map;
import java.util.Objects;

public final class NimbusAdsViewManager extends SimpleViewManager<FrameLayout> {
    private static final String REACT_CLASS = "NimbusReactNativeView";
    private static final int STATE_KEY = R.id.nimbus_ad_state;
    private static final String TAG = "NimbusAdsViewManager";
    private final NimbusAdManager adManager = new NimbusAdManager();
    private final NimbusAnalytics analytics = new NimbusAnalytics();

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public FrameLayout createViewInstance(ThemedReactContext themedReactContext) {
        ReactAdWrapper reactAdWrapper = new ReactAdWrapper(themedReactContext);
        reactAdWrapper.setId(View.generateViewId());
        NimbusAdLoader nimbusAdLoader = new NimbusAdLoader(this.adManager, this.analytics, new ReactAdEventEmitter(themedReactContext));
        Objects.requireNonNull(nimbusAdLoader);
        NimbusAdLifecycleListener nimbusAdLifecycleListener = new NimbusAdLifecycleListener(themedReactContext, new NimbusAdsViewManager$$ExternalSyntheticLambda0(nimbusAdLoader));
        AdState adState = new AdState();
        adState.setLoader(nimbusAdLoader);
        adState.setLifecycleListener(nimbusAdLifecycleListener);
        reactAdWrapper.setTag(STATE_KEY, adState);
        return reactAdWrapper;
    }

    @ReactProp(defaultInt = 0, name = "adIndex")
    public void setAdIndex(FrameLayout frameLayout, int i) {
        AdState adState = AdState.getAdState(frameLayout);
        if (adState != null) {
            adState.setIndex(i);
        }
    }

    @ReactProp(defaultBoolean = false, name = "loadAd")
    public void setLoadAd(FrameLayout frameLayout, boolean z) {
        AdState adState = AdState.getAdState(frameLayout);
        if (adState != null && z && !adState.isRequestInitiated()) {
            adState.setRequestInitiated(true);
            NimbusAdLoader loader = adState.getLoader();
            if (loader == null) {
                SentryLogcatAdapter.w(TAG, "No AdLoader present – cannot load ad.");
                adState.setRequestInitiated(false);
                return;
            }
            UiThreadUtil.runOnUiThread(new NimbusAdsViewManager$$ExternalSyntheticLambda1(frameLayout, adState, loader));
        }
    }

    static /* synthetic */ void lambda$setLoadAd$0(FrameLayout frameLayout, AdState adState, NimbusAdLoader nimbusAdLoader) {
        Activity currentActivity = ((ReactContext) frameLayout.getContext()).getCurrentActivity();
        if (currentActivity == null) {
            SentryLogcatAdapter.w(TAG, "No activity yet – will not load ad.");
            adState.setRequestInitiated(false);
            return;
        }
        nimbusAdLoader.load(currentActivity, frameLayout, adState.getIndex(), adState.getSlotConfig());
    }

    public void onDropViewInstance(FrameLayout frameLayout) {
        super.onDropViewInstance(frameLayout);
        AdState adState = AdState.getAdState(frameLayout);
        if (adState != null) {
            ((ReactContext) frameLayout.getContext()).removeLifecycleEventListener(adState.getLifecycleListener());
            if (adState.getLoader() != null && adState.getLoader().getController() != null) {
                adState.getLoader().getController().destroy();
            }
        }
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return ReactAdEventEmitter.EXPORTED_EVENTS;
    }
}
