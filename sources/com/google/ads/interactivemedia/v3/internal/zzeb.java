package com.google.ads.interactivemedia.v3.internal;

import android.os.AsyncTask;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzeb extends AsyncTask {
    private zzec zza;
    protected final zzdt zzd;

    public zzeb(zzdt zzdt) {
        this.zzd = zzdt;
    }

    /* access modifiers changed from: protected */
    /* renamed from: zza */
    public void onPostExecute(String str) {
        zzec zzec = this.zza;
        if (zzec != null) {
            zzec.zza(this);
        }
    }

    public final void zzb(zzec zzec) {
        this.zza = zzec;
    }
}
