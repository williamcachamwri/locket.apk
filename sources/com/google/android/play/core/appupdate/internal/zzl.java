package com.google.android.play.core.appupdate.internal;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.google.android.play.core.listener.StateUpdatedListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
public abstract class zzl {
    protected final zzm zza;
    protected final Set zzb = new HashSet();
    private final IntentFilter zzc;
    private final Context zzd;
    private zzk zze = null;
    private volatile boolean zzf = false;

    protected zzl(zzm zzm, IntentFilter intentFilter, Context context) {
        this.zza = zzm;
        this.zzc = intentFilter;
        this.zzd = zzz.zza(context);
    }

    private final void zze() {
        zzk zzk;
        if (!this.zzb.isEmpty() && this.zze == null) {
            this.zze = new zzk(this, (zzj) null);
            if (Build.VERSION.SDK_INT >= 33) {
                this.zzd.registerReceiver(this.zze, this.zzc, 2);
            } else {
                this.zzd.registerReceiver(this.zze, this.zzc);
            }
        }
        if (this.zzb.isEmpty() && (zzk = this.zze) != null) {
            this.zzd.unregisterReceiver(zzk);
            this.zze = null;
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Context context, Intent intent);

    public final synchronized void zzb(StateUpdatedListener stateUpdatedListener) {
        this.zza.zzd("registerListener", new Object[0]);
        zzac.zza(stateUpdatedListener, "Registered Play Core listener should not be null.");
        this.zzb.add(stateUpdatedListener);
        zze();
    }

    public final synchronized void zzc(StateUpdatedListener stateUpdatedListener) {
        this.zza.zzd("unregisterListener", new Object[0]);
        zzac.zza(stateUpdatedListener, "Unregistered Play Core listener should not be null.");
        this.zzb.remove(stateUpdatedListener);
        zze();
    }

    public final synchronized void zzd(Object obj) {
        Iterator it = new HashSet(this.zzb).iterator();
        while (it.hasNext()) {
            ((StateUpdatedListener) it.next()).onStateUpdate(obj);
        }
    }
}
