package com.google.android.gms.measurement.internal;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final /* synthetic */ class zzlf implements Runnable {
    private /* synthetic */ zzlg zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ Exception zzc;
    private /* synthetic */ byte[] zzd;
    private /* synthetic */ Map zze;

    public /* synthetic */ zzlf(zzlg zzlg, int i, Exception exc, byte[] bArr, Map map) {
        this.zza = zzlg;
        this.zzb = i;
        this.zzc = exc;
        this.zzd = bArr;
        this.zze = map;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
