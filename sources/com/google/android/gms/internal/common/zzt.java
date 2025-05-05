package com.google.android.gms.internal.common;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
final class zzt extends zzw {
    final /* synthetic */ zzu zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzt(zzu zzu, zzx zzx, CharSequence charSequence) {
        super(zzx, charSequence);
        this.zza = zzu;
    }

    /* access modifiers changed from: package-private */
    public final int zzc(int i) {
        return i + 1;
    }

    /* access modifiers changed from: package-private */
    public final int zzd(int i) {
        CharSequence charSequence = this.zzb;
        int length = charSequence.length();
        zzs.zzb(i, length, FirebaseAnalytics.Param.INDEX);
        while (i < length) {
            zzu zzu = this.zza;
            if (zzu.zza.zza(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
