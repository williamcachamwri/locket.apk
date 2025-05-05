package com.google.ads.interactivemedia.v3.internal;

import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONObject;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzma {
    private final int zza;
    private final String zzb;
    private final Object zzc;
    private final Object zzd;

    /* synthetic */ zzma(int i, String str, Object obj, Object obj2, zzlz zzlz) {
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        zzls.zza().zzb(this);
    }

    public static zzma zze(int i, String str, float f, float f2) {
        return new zzlx(1, str, Float.valueOf(f), Float.valueOf(f2));
    }

    public static zzma zzf(int i, String str, int i2, int i3) {
        return new zzlv(1, str, Integer.valueOf(i2), Integer.valueOf(i3));
    }

    public static zzma zzg(int i, String str, long j, long j2) {
        return new zzlw(1, str, Long.valueOf(j), Long.valueOf(j2));
    }

    public static zzma zzh(int i, String str) {
        String str2 = null;
        zzly zzly = new zzly(1, "gads:sdk_core_constants:experiment_id", (String) null, (String) null);
        zzls.zza().zza(zzly);
        return zzly;
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(JSONObject jSONObject);

    public abstract Object zzb(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract Object zzc(SharedPreferences sharedPreferences);

    public final int zzd() {
        return this.zza;
    }

    public final Object zzi() {
        return zzls.zzc().zzd() ? this.zzd : this.zzc;
    }

    public final String zzj() {
        return this.zzb;
    }
}
