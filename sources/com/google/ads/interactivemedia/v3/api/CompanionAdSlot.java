package com.google.ads.interactivemedia.v3.api;

import android.view.ViewGroup;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public interface CompanionAdSlot {
    public static final int FLUID_SIZE = -2;

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    public interface ClickListener {
        void onCompanionAdClick();
    }

    void addClickListener(ClickListener clickListener);

    ViewGroup getContainer();

    int getHeight();

    int getWidth();

    boolean isFilled();

    void removeClickListener(ClickListener clickListener);

    void setContainer(ViewGroup viewGroup);

    void setSize(int i, int i2);
}
