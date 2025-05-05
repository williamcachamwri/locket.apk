package com.google.android.gms.internal.atv_ads_framework;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
final class zzar extends zzat {
    final /* synthetic */ zzas zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzar(zzas zzas, zzau zzau, CharSequence charSequence) {
        super(zzau, charSequence);
        this.zza = zzas;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(int i) {
        return i + 1;
    }

    /* access modifiers changed from: package-private */
    public final int zzd(int i) {
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzaq.zzb(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            if (charSequence.charAt(i) == ':') {
                return i;
            }
            i++;
        }
        return -1;
    }
}
