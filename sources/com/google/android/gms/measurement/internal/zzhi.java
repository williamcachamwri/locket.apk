package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.measurement.zzby;
import com.google.android.gms.internal.measurement.zzbz;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzhi implements ServiceConnection {
    final /* synthetic */ zzhf zza;
    /* access modifiers changed from: private */
    public final String zzb;

    zzhi(zzhf zzhf, String str) {
        this.zza = zzhf;
        this.zzb = str;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.zza.zza.zzj().zzu().zza("Install Referrer connection returned with null binder");
            return;
        }
        try {
            zzbz zza2 = zzby.zza(iBinder);
            if (zza2 == null) {
                this.zza.zza.zzj().zzu().zza("Install Referrer Service implementation was not found");
                return;
            }
            this.zza.zza.zzj().zzp().zza("Install Referrer Service connected");
            this.zza.zza.zzl().zzb((Runnable) new zzhh(this, zza2, this));
        } catch (RuntimeException e) {
            this.zza.zza.zzj().zzu().zza("Exception occurred while calling Install Referrer API", e);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zza.zzj().zzp().zza("Install Referrer Service disconnected");
    }
}
