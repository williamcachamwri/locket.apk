package com.google.ads.interactivemedia.v3.impl;

import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzal implements CompanionAdSlot {
    private int zza;
    private int zzb;
    private ViewGroup zzc;
    private String zzd;
    private final List zze = new ArrayList(1);

    public final void addClickListener(CompanionAdSlot.ClickListener clickListener) {
        this.zze.add(clickListener);
    }

    public final ViewGroup getContainer() {
        return this.zzc;
    }

    public final int getHeight() {
        return this.zzb;
    }

    public final int getWidth() {
        return this.zza;
    }

    public final boolean isFilled() {
        ViewGroup viewGroup = this.zzc;
        return (viewGroup == null || viewGroup.findViewWithTag(this.zzd) == null) ? false : true;
    }

    public final void removeClickListener(CompanionAdSlot.ClickListener clickListener) {
        this.zze.remove(clickListener);
    }

    public final void setContainer(ViewGroup viewGroup) {
        this.zzc = viewGroup;
    }

    public final void setSize(int i, int i2) {
        this.zza = i;
        this.zzb = i2;
    }

    public final List zza() {
        return this.zze;
    }

    public final void zzb(String str) {
        this.zzd = str;
    }
}
