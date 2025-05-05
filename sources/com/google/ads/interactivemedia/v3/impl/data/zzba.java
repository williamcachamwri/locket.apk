package com.google.ads.interactivemedia.v3.impl.data;

import android.view.View;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzba {
    public abstract zzbb build();

    public abstract zzba height(int i);

    public abstract zzba left(int i);

    public zzba locationOnScreenOfView(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return left(iArr[0]).top(iArr[1]).height(view.getHeight()).width(view.getWidth());
    }

    public abstract zzba top(int i);

    public abstract zzba width(int i);
}
