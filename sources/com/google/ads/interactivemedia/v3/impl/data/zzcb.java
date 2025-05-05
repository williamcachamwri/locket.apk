package com.google.ads.interactivemedia.v3.impl.data;

import android.view.View;
import com.google.ads.interactivemedia.v3.api.FriendlyObstructionPurpose;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzcb {
    public abstract zzcb attached(boolean z);

    public abstract zzcb bounds(zzbb zzbb);

    public abstract zzcc build();

    public abstract zzcb detailedReason(String str);

    public abstract zzcb hidden(boolean z);

    public abstract zzcb purpose(FriendlyObstructionPurpose friendlyObstructionPurpose);

    public abstract zzcb type(String str);

    /* access modifiers changed from: package-private */
    public zzcb view(View view) {
        return attached(view.isAttachedToWindow()).bounds(zzbb.builder().locationOnScreenOfView(view).build()).hidden(!view.isShown()).type(view.getClass().getCanonicalName());
    }
}
