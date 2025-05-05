package com.google.ads.interactivemedia.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class ConsentSettings {

    /* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
    public static abstract class Builder {
        public abstract Builder allowStorage(Boolean bool);

        public abstract ConsentSettings build();

        public abstract Builder directedForChildOrUnknownAge(Boolean bool);

        public abstract Builder enableCookiesFor3pServerSideAdInsertion(Boolean bool);
    }

    public static Builder builder() {
        zzb zzb = new zzb();
        zzb.enableCookiesFor3pServerSideAdInsertion((Boolean) null);
        zzb.allowStorage(false);
        zzb.directedForChildOrUnknownAge(false);
        return zzb;
    }

    public abstract Builder toBuilder();

    /* access modifiers changed from: package-private */
    public abstract Boolean zza();

    /* access modifiers changed from: package-private */
    public abstract Boolean zzb();

    /* access modifiers changed from: package-private */
    public abstract Boolean zzc();
}
