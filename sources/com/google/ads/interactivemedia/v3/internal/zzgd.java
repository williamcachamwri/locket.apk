package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.signals.SecureSignalsAdapter;
import com.google.ads.interactivemedia.v3.impl.data.zzbp;
import com.google.ads.interactivemedia.v3.impl.data.zzbq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzgd {
    private final List zza = new ArrayList(0);
    private final TaskCompletionSource zzb = new TaskCompletionSource();
    private final TaskCompletionSource zzc = new TaskCompletionSource();
    private final Context zzd;
    private final ExecutorService zze;
    private final zzfd zzf;
    private Integer zzg;

    public zzgd(Context context, ExecutorService executorService, zzfd zzfd) {
        this.zzd = context;
        this.zze = executorService;
        this.zzf = zzfd;
    }

    public static /* synthetic */ Task zza(zzgd zzgd, Task task) {
        List list = (List) task.getResult();
        return Tasks.whenAllComplete((Collection<? extends Task<?>>) list).continueWith(zzgd.zze, new zzfw(list));
    }

    private final void zzi(zzbq zzbq, Exception exc) {
        this.zzf.zzg(zzbp.NATIVE_ESP, zzbq, exc);
    }

    private final void zzj(zzft zzft) {
        this.zza.remove(zzft);
    }

    private static final Exception zzk(zzft zzft, Exception exc) {
        String zze2 = zzft.zze();
        String zzf2 = zzft.zzf();
        return new Exception("Exception with SecureSignalsAdapter " + zze2 + ":" + zzf2, exc);
    }

    public final Task zzb() {
        this.zzb.getTask().continueWith(this.zze, new zzgb(this)).continueWithTask(this.zze, new zzfy(this)).continueWith(this.zze, new zzfz(this)).continueWith(this.zze, new zzgc(this));
        return this.zzc.getTask();
    }

    public final Task zzc(List list, Integer num) {
        if (num.intValue() == 0) {
            this.zzb.trySetException(new Exception("No adapters to load"));
            return this.zzb.getTask();
        }
        this.zzg = num;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            zzft zzft = null;
            try {
                Class<?> cls = Class.forName(str, false, getClass().getClassLoader());
                Class[] interfaces = cls.getInterfaces();
                String name = SecureSignalsAdapter.class.getName();
                int length = interfaces.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (interfaces[i].getName().equals(name)) {
                        zzft = new zzft((SecureSignalsAdapter) cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]), str, this.zzd);
                        break;
                    } else {
                        i++;
                    }
                }
            } catch (Throwable unused) {
            }
            if (zzft != null) {
                try {
                    this.zza.add(zzft);
                } catch (Exception e) {
                    zzi(zzbq.LOAD_ADAPTER, new Exception("Exception with SecureSignalsAdapter ".concat(String.valueOf(str)), e));
                }
            }
        }
        this.zzb.trySetResult(this.zza);
        return this.zzb.getTask();
    }

    public final List zze() {
        Task task;
        try {
            Task continueWith = this.zzc.getTask().continueWith(this.zze, new zzfx(this)).continueWithTask(this.zze, new zzfy(this)).continueWith(this.zze, new zzfz(this));
            Integer num = this.zzg;
            if (num == null) {
                task = Tasks.forResult(new ArrayList());
            } else {
                task = Tasks.withTimeout(continueWith, (long) num.intValue(), TimeUnit.MILLISECONDS).addOnFailureListener(new zzga(this));
            }
            return (List) Tasks.await(task);
        } catch (InterruptedException | ExecutionException unused) {
            return new ArrayList();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Exception exc) {
        zzi(zzbq.COLLECT_SIGNALS, exc);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(zzft zzft, Exception exc) {
        zzj(zzft);
        zzi(zzbq.COLLECT_SIGNALS, zzk(zzft, exc));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzh(zzft zzft, Exception exc) {
        zzj(zzft);
        zzi(zzbq.INIT, zzk(zzft, exc));
    }
}
