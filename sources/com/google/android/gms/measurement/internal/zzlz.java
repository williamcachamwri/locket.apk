package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzlz implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ zzls zzc;

    zzlz(zzls zzls, AtomicReference atomicReference, zzo zzo) {
        this.zza = atomicReference;
        this.zzb = zzo;
        this.zzc = zzls;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                if (!this.zzc.zzk().zzo().zzh()) {
                    this.zzc.zzj().zzv().zza("Analytics storage consent denied; will not get app instance id");
                    this.zzc.zzm().zzc((String) null);
                    this.zzc.zzk().zze.zza((String) null);
                    this.zza.set((Object) null);
                    this.zza.notify();
                    return;
                }
                zzgb zza2 = this.zzc.zzb;
                if (zza2 == null) {
                    this.zzc.zzj().zzg().zza("Failed to get app instance id");
                    this.zza.notify();
                    return;
                }
                Preconditions.checkNotNull(this.zzb);
                this.zza.set(zza2.zzb(this.zzb));
                String str = (String) this.zza.get();
                if (str != null) {
                    this.zzc.zzm().zzc(str);
                    this.zzc.zzk().zze.zza(str);
                }
                this.zzc.zzar();
                this.zza.notify();
            } catch (RemoteException e) {
                try {
                    this.zzc.zzj().zzg().zza("Failed to get app instance id", e);
                    this.zza.notify();
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}
