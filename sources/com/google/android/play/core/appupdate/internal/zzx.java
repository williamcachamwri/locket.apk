package com.google.android.play.core.appupdate.internal;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.appupdate.zzl;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
public final class zzx {
    private static final Map zza = new HashMap();
    /* access modifiers changed from: private */
    public final Context zzb;
    /* access modifiers changed from: private */
    public final zzm zzc;
    private final String zzd;
    /* access modifiers changed from: private */
    public final List zze = new ArrayList();
    private final Set zzf = new HashSet();
    /* access modifiers changed from: private */
    public final Object zzg = new Object();
    /* access modifiers changed from: private */
    public boolean zzh;
    private final Intent zzi;
    private final WeakReference zzj;
    private final IBinder.DeathRecipient zzk = new zzp(this);
    /* access modifiers changed from: private */
    public final AtomicInteger zzl = new AtomicInteger(0);
    /* access modifiers changed from: private */
    public ServiceConnection zzm;
    /* access modifiers changed from: private */
    public IInterface zzn;
    private final zzl zzo;

    public zzx(Context context, zzm zzm2, String str, Intent intent, zzl zzl2, zzs zzs) {
        this.zzb = context;
        this.zzc = zzm2;
        this.zzd = "AppUpdateService";
        this.zzi = intent;
        this.zzo = zzl2;
        this.zzj = new WeakReference((Object) null);
    }

    public static /* synthetic */ void zzj(zzx zzx) {
        zzx.zzc.zzd("reportBinderDeath", new Object[0]);
        zzs zzs = (zzs) zzx.zzj.get();
        if (zzs != null) {
            zzx.zzc.zzd("calling onBinderDied", new Object[0]);
            zzs.zza();
        } else {
            zzx.zzc.zzd("%s : Binder has died.", zzx.zzd);
            for (zzn zzc2 : zzx.zze) {
                zzc2.zzc(zzx.zzv());
            }
            zzx.zze.clear();
        }
        synchronized (zzx.zzg) {
            zzx.zzw();
        }
    }

    static /* bridge */ /* synthetic */ void zzn(zzx zzx, TaskCompletionSource taskCompletionSource) {
        zzx.zzf.add(taskCompletionSource);
        taskCompletionSource.getTask().addOnCompleteListener(new zzo(zzx, taskCompletionSource));
    }

    static /* bridge */ /* synthetic */ void zzq(zzx zzx) {
        zzx.zzc.zzd("linkToDeath", new Object[0]);
        try {
            zzx.zzn.asBinder().linkToDeath(zzx.zzk, 0);
        } catch (RemoteException e) {
            zzx.zzc.zzc(e, "linkToDeath failed", new Object[0]);
        }
    }

    static /* bridge */ /* synthetic */ void zzr(zzx zzx) {
        zzx.zzc.zzd("unlinkToDeath", new Object[0]);
        zzx.zzn.asBinder().unlinkToDeath(zzx.zzk, 0);
    }

    /* access modifiers changed from: private */
    public final void zzw() {
        for (TaskCompletionSource trySetException : this.zzf) {
            trySetException.trySetException(zzv());
        }
        this.zzf.clear();
    }

    public final Handler zzc() {
        Handler handler;
        Map map = zza;
        synchronized (map) {
            if (!map.containsKey(this.zzd)) {
                HandlerThread handlerThread = new HandlerThread(this.zzd, 10);
                handlerThread.start();
                map.put(this.zzd, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) map.get(this.zzd);
        }
        return handler;
    }

    public final IInterface zze() {
        return this.zzn;
    }

    public final void zzs(zzn zzn2, TaskCompletionSource taskCompletionSource) {
        zzc().post(new zzq(this, zzn2.zzb(), taskCompletionSource, zzn2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzt(TaskCompletionSource taskCompletionSource, Task task) {
        synchronized (this.zzg) {
            this.zzf.remove(taskCompletionSource);
        }
    }

    public final void zzu(TaskCompletionSource taskCompletionSource) {
        synchronized (this.zzg) {
            this.zzf.remove(taskCompletionSource);
        }
        zzc().post(new zzr(this));
    }

    private final RemoteException zzv() {
        return new RemoteException(String.valueOf(this.zzd).concat(" : Binder has died."));
    }

    static /* bridge */ /* synthetic */ void zzp(zzx zzx, zzn zzn2) {
        if (zzx.zzn == null && !zzx.zzh) {
            zzx.zzc.zzd("Initiate binding to the service.", new Object[0]);
            zzx.zze.add(zzn2);
            zzw zzw = new zzw(zzx, (zzv) null);
            zzx.zzm = zzw;
            zzx.zzh = true;
            if (!zzx.zzb.bindService(zzx.zzi, zzw, 1)) {
                zzx.zzc.zzd("Failed to bind to the service.", new Object[0]);
                zzx.zzh = false;
                for (zzn zzc2 : zzx.zze) {
                    zzc2.zzc(new zzy());
                }
                zzx.zze.clear();
            }
        } else if (zzx.zzh) {
            zzx.zzc.zzd("Waiting to bind to the service.", new Object[0]);
            zzx.zze.add(zzn2);
        } else {
            zzn2.run();
        }
    }
}
