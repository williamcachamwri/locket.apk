package com.google.firebase.auth.internal;

import android.app.Application;
import android.content.Context;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.internal.p002firebaseauthapi.zzagl;
import com.google.firebase.FirebaseApp;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzbz {
    private volatile int zza;
    /* access modifiers changed from: private */
    public final zzas zzb;
    /* access modifiers changed from: private */
    public volatile boolean zzc;

    public zzbz(FirebaseApp firebaseApp) {
        this(firebaseApp.getApplicationContext(), new zzas(firebaseApp));
    }

    private zzbz(Context context, zzas zzas) {
        this.zzc = false;
        this.zza = 0;
        this.zzb = zzas;
        BackgroundDetector.initialize((Application) context.getApplicationContext());
        BackgroundDetector.getInstance().addListener(new zzcc(this));
    }

    public final void zza() {
        this.zzb.zzb();
    }

    public final void zza(int i) {
        if (i > 0 && this.zza == 0) {
            this.zza = i;
            if (zzb()) {
                this.zzb.zzc();
            }
        } else if (i == 0 && this.zza != 0) {
            this.zzb.zzb();
        }
        this.zza = i;
    }

    public final void zza(zzagl zzagl) {
        if (zzagl != null) {
            long zza2 = zzagl.zza();
            if (zza2 <= 0) {
                zza2 = 3600;
            }
            zzas zzas = this.zzb;
            zzas.zza = zzagl.zzb() + (zza2 * 1000);
            zzas.zzb = -1;
            if (zzb()) {
                this.zzb.zzc();
            }
        }
    }

    /* access modifiers changed from: private */
    public final boolean zzb() {
        return this.zza > 0 && !this.zzc;
    }
}
