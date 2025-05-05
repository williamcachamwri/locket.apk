package com.google.ads.interactivemedia.v3.internal;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzek implements View.OnTouchListener {
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 1) {
            return false;
        }
        zzqf.zzh(motionEvent);
        return false;
    }
}
