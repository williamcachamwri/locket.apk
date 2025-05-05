package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzgm {
    private static zzgm zza;
    private static final Duration zzb = Duration.ofMinutes(30);
    private final zzhy zzc;
    private final TelemetryLoggingClient zzd;
    private final AtomicLong zze = new AtomicLong(-1);

    static zzgm zza(zzhy zzhy) {
        if (zza == null) {
            zza = new zzgm(zzhy.zza(), zzhy);
        }
        return zza;
    }

    private zzgm(Context context, zzhy zzhy) {
        this.zzd = TelemetryLogging.getClient(context, TelemetryLoggingOptions.builder().setApi("measurement:api").build());
        this.zzc = zzhy;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(long j, Exception exc) {
        this.zze.set(j);
    }

    public final synchronized void zza(int i, int i2, long j, long j2, int i3) {
        boolean z;
        synchronized (this) {
            long elapsedRealtime = this.zzc.zzb().elapsedRealtime();
            if (this.zze.get() != -1 && elapsedRealtime - this.zze.get() <= zzb.toMillis()) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                this.zzd.log(new TelemetryData(0, Arrays.asList(new MethodInvocation[]{new MethodInvocation(36301, i2, 0, j, j2, (String) null, (String) null, 0, i3)}))).addOnFailureListener(new zzgl(this, elapsedRealtime));
            }
        }
    }
}
