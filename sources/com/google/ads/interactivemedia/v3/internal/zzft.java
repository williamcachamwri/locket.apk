package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.signals.SecureSignalsAdapter;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzft {
    /* access modifiers changed from: private */
    public final SecureSignalsAdapter zza;
    private final Context zzb;
    private final String zzc;
    /* access modifiers changed from: private */
    public final TaskCompletionSource zzd = new TaskCompletionSource();

    zzft(SecureSignalsAdapter secureSignalsAdapter, String str, Context context) {
        this.zza = secureSignalsAdapter;
        this.zzc = str;
        this.zzb = context;
    }

    /* access modifiers changed from: package-private */
    public final Task zzb() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zza.collectSignals(this.zzb, new zzfs(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    public final Task zzc() {
        this.zza.initialize(this.zzb, new zzfr(this));
        return this.zzd.getTask();
    }

    /* access modifiers changed from: package-private */
    public final String zze() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final String zzf() {
        return this.zza.getVersion().toString();
    }
}
