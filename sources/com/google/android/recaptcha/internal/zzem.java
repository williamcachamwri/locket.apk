package com.google.android.recaptcha.internal;

import android.content.Context;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzem {
    private final String zza;
    private String zzb;
    private String zzc;
    private final Context zzd;
    private final zzeq zze;
    private String zzf;
    private Integer zzg;
    private final int zzh;

    private zzem(zzem zzem) {
        this(zzem.zza, zzem.zzb, zzem.zzc, zzem.zzh, zzem.zzd, zzem.zze);
        this.zzf = zzem.zzf;
        this.zzg = zzem.zzg;
    }

    private zzem(String str, String str2, String str3, int i, Context context, zzeq zzeq) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzh = i;
        this.zzd = context;
        this.zze = zzeq;
    }

    public /* synthetic */ zzem(String str, String str2, String str3, int i, Context context, zzeq zzeq, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, i, context, zzeq);
    }

    public final zzem zza() {
        return new zzem(this);
    }

    public final zzem zzb(int i) {
        this.zzg = Integer.valueOf(i);
        return this;
    }

    public final zzem zzc(String str) {
        this.zzf = str;
        return this;
    }

    public final String zzd() {
        return this.zzc;
    }

    public final void zze(zztz zztz) {
        this.zze.zza(zztz);
    }

    public final zzep zzf(int i) {
        String str = this.zzb;
        String str2 = this.zzc;
        String str3 = this.zzf;
        zzce zzce = new zzce();
        Context context = this.zzd;
        Integer num = this.zzg;
        return new zzep(i, this.zza, this.zzh, str, str2, str3, (String) null, this.zze, zzce, context, num);
    }
}
