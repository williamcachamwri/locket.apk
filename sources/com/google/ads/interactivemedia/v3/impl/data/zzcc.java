package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.FriendlyObstructionPurpose;
import com.google.ads.interactivemedia.v3.internal.zzps;

@zzps(zza = zzam.class)
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzcc {
    public static zzcb builder() {
        return new zzak();
    }

    /* access modifiers changed from: package-private */
    public abstract boolean attached();

    /* access modifiers changed from: package-private */
    public abstract zzbb bounds();

    /* access modifiers changed from: package-private */
    public abstract String detailedReason();

    /* access modifiers changed from: package-private */
    public abstract boolean hidden();

    /* access modifiers changed from: package-private */
    public abstract FriendlyObstructionPurpose purpose();

    /* access modifiers changed from: package-private */
    public abstract String type();
}
