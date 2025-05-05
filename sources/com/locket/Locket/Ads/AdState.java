package com.locket.Locket.Ads;

import android.view.View;
import com.locket.Locket.R;

final class AdState {
    private int index = 0;
    private NimbusAdLifecycleListener lifecycleListener;
    private NimbusAdLoader loader;
    private boolean requestInitiated = false;
    private String slotConfig = "history_timeline_static";

    AdState() {
    }

    /* access modifiers changed from: package-private */
    public NimbusAdLoader getLoader() {
        return this.loader;
    }

    /* access modifiers changed from: package-private */
    public NimbusAdLifecycleListener getLifecycleListener() {
        return this.lifecycleListener;
    }

    /* access modifiers changed from: package-private */
    public int getIndex() {
        return this.index;
    }

    /* access modifiers changed from: package-private */
    public boolean isRequestInitiated() {
        return this.requestInitiated;
    }

    /* access modifiers changed from: package-private */
    public String getSlotConfig() {
        return this.slotConfig;
    }

    /* access modifiers changed from: package-private */
    public void setLoader(NimbusAdLoader nimbusAdLoader) {
        this.loader = nimbusAdLoader;
    }

    /* access modifiers changed from: package-private */
    public void setLifecycleListener(NimbusAdLifecycleListener nimbusAdLifecycleListener) {
        this.lifecycleListener = nimbusAdLifecycleListener;
    }

    /* access modifiers changed from: package-private */
    public void setIndex(int i) {
        this.index = i;
    }

    /* access modifiers changed from: package-private */
    public void setRequestInitiated(boolean z) {
        this.requestInitiated = z;
    }

    /* access modifiers changed from: package-private */
    public void setSlotConfig(String str) {
        this.slotConfig = str;
    }

    static AdState getAdState(View view) {
        return (AdState) view.getTag(R.id.nimbus_ad_state);
    }
}
