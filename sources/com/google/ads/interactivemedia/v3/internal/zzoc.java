package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.facebook.device.yearclass.YearClass;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzoc implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    protected final zzoo zza;
    private final String zzb;
    private final String zzc;
    private final LinkedBlockingQueue zzd;
    private final HandlerThread zze;
    private final zznt zzf;
    private final long zzg = System.currentTimeMillis();
    private final int zzh;

    public zzoc(Context context, int i, int i2, String str, String str2, String str3, zznt zznt) {
        this.zzb = str;
        this.zzh = i2;
        this.zzc = str2;
        this.zzf = zznt;
        HandlerThread handlerThread = new HandlerThread("GassDGClient");
        this.zze = handlerThread;
        handlerThread.start();
        zzoo zzoo = new zzoo(context, handlerThread.getLooper(), this, this, 19621000);
        this.zza = zzoo;
        this.zzd = new LinkedBlockingQueue();
        zzoo.checkAvailabilityAndConnect();
    }

    private final void zzd(int i, long j, Exception exc) {
        this.zzf.zzc(i, System.currentTimeMillis() - j, exc);
    }

    public final void onConnected(Bundle bundle) {
        zzot zzc2 = zzc();
        if (zzc2 != null) {
            try {
                zzpa zzf2 = zzc2.zzf(new zzoy(1, this.zzh, this.zzb, this.zzc));
                zzd(5011, this.zzg, (Exception) null);
                this.zzd.put(zzf2);
            } catch (Throwable th) {
                zzb();
                this.zze.quit();
                throw th;
            }
            zzb();
            this.zze.quit();
        }
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            zzd(4012, this.zzg, (Exception) null);
            this.zzd.put(new zzpa((byte[]) null, 1));
        } catch (InterruptedException unused) {
        }
    }

    public final void onConnectionSuspended(int i) {
        try {
            zzd(4011, this.zzg, (Exception) null);
            this.zzd.put(new zzpa((byte[]) null, 1));
        } catch (InterruptedException unused) {
        }
    }

    public final zzpa zza(int i) {
        zzpa zzpa;
        try {
            zzpa = (zzpa) this.zzd.poll(50000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zzd(YearClass.CLASS_2009, this.zzg, e);
            zzpa = null;
        }
        zzd(3004, this.zzg, (Exception) null);
        if (zzpa != null) {
            if (zzpa.zzc == 7) {
                zznt.zzg(3);
            } else {
                zznt.zzg(2);
            }
        }
        return zzpa == null ? new zzpa((byte[]) null, 1) : zzpa;
    }

    public final void zzb() {
        zzoo zzoo = this.zza;
        if (zzoo == null) {
            return;
        }
        if (zzoo.isConnected() || this.zza.isConnecting()) {
            this.zza.disconnect();
        }
    }

    /* access modifiers changed from: protected */
    public final zzot zzc() {
        try {
            return this.zza.zzp();
        } catch (DeadObjectException | IllegalStateException unused) {
            return null;
        }
    }
}
