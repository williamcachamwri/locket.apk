package com.facebook.react.runtime;

import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BridgelessReactStateTracker {
    private static final String TAG = "BridgelessReact";
    private final boolean mShouldTrackStates;
    private final List<String> mStates = Collections.synchronizedList(new ArrayList());

    BridgelessReactStateTracker(boolean z) {
        this.mShouldTrackStates = z;
    }

    /* access modifiers changed from: package-private */
    public void enterState(String str) {
        FLog.w(TAG, str);
        if (this.mShouldTrackStates) {
            this.mStates.add(str);
        }
    }
}
