package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.internal.TelemetryLogging;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: com.google.android.gms:play-services-ads-identifier@@18.2.0 */
public final class zzd {
    private static volatile zzd zza;
    private static final Object zzb = new Object();
    private static final Duration zzc = Duration.ofMinutes(30);
    private final TelemetryLoggingClient zzd;
    private final AtomicLong zze = new AtomicLong(-1);

    private zzd(Context context, String str) {
        this.zzd = TelemetryLogging.getClient(context, TelemetryLoggingOptions.builder().setApi("ads_identifier:api").build());
    }

    public static zzd zza(Context context) {
        if (zza == null) {
            synchronized (zzb) {
                if (zza == null) {
                    zza = new zzd(context, "ads_identifier:api");
                }
            }
        }
        return zza;
    }

    public static /* synthetic */ void zzb(zzd zzd2, long j, Exception exc) {
        ConnectionResult connectionResult;
        Log.i("AdvertisingIdClient", "getting error as ".concat(String.valueOf(exc.getMessage())));
        if ((exc instanceof ApiException) && (connectionResult = ((ApiException) exc).getStatus().getConnectionResult()) != null && connectionResult.getErrorCode() == 24) {
            zzd2.zze.set(j);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0079, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003d, code lost:
        if ((r3 - r1.zze.get()) > zzc.toMillis()) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzc(int r20, int r21, long r22, long r24, int r26) {
        /*
            r19 = this;
            r1 = r19
            java.lang.String r0 = "shouldSendLog "
            monitor-enter(r19)
            java.util.concurrent.atomic.AtomicLong r2 = r1.zze     // Catch:{ all -> 0x007a }
            long r3 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x007a }
            long r5 = r2.get()     // Catch:{ all -> 0x007a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x007a }
            r2.<init>(r0)     // Catch:{ all -> 0x007a }
            r2.append(r5)     // Catch:{ all -> 0x007a }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x007a }
            java.lang.String r2 = "AdvertisingIdClient"
            android.util.Log.i(r2, r0)     // Catch:{ all -> 0x007a }
            java.util.concurrent.atomic.AtomicLong r0 = r1.zze     // Catch:{ all -> 0x007a }
            long r5 = r0.get()     // Catch:{ all -> 0x007a }
            r7 = -1
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x002d
            goto L_0x003f
        L_0x002d:
            java.util.concurrent.atomic.AtomicLong r0 = r1.zze     // Catch:{ all -> 0x007a }
            long r5 = r0.get()     // Catch:{ all -> 0x007a }
            long r5 = r3 - r5
            java.time.Duration r0 = zzc     // Catch:{ all -> 0x007a }
            long r7 = r0.toMillis()     // Catch:{ all -> 0x007a }
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x0078
        L_0x003f:
            com.google.android.gms.common.internal.TelemetryLoggingClient r0 = r1.zzd     // Catch:{ all -> 0x007a }
            if (r0 != 0) goto L_0x0044
            goto L_0x0078
        L_0x0044:
            com.google.android.gms.common.internal.TelemetryData r2 = new com.google.android.gms.common.internal.TelemetryData     // Catch:{ all -> 0x007a }
            r5 = 1
            com.google.android.gms.common.internal.MethodInvocation[] r5 = new com.google.android.gms.common.internal.MethodInvocation[r5]     // Catch:{ all -> 0x007a }
            com.google.android.gms.common.internal.MethodInvocation r18 = new com.google.android.gms.common.internal.MethodInvocation     // Catch:{ all -> 0x007a }
            r7 = 35401(0x8a49, float:4.9607E-41)
            r9 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r6 = r18
            r8 = r21
            r10 = r22
            r12 = r24
            r17 = r26
            r6.<init>(r7, r8, r9, r10, r12, r14, r15, r16, r17)     // Catch:{ all -> 0x007a }
            r6 = 0
            r5[r6] = r18     // Catch:{ all -> 0x007a }
            java.util.List r5 = java.util.Arrays.asList(r5)     // Catch:{ all -> 0x007a }
            r2.<init>(r6, r5)     // Catch:{ all -> 0x007a }
            com.google.android.gms.tasks.Task r0 = r0.log(r2)     // Catch:{ all -> 0x007a }
            com.google.android.gms.ads.identifier.zzc r2 = new com.google.android.gms.ads.identifier.zzc     // Catch:{ all -> 0x007a }
            r2.<init>(r1, r3)     // Catch:{ all -> 0x007a }
            r0.addOnFailureListener(r2)     // Catch:{ all -> 0x007a }
            monitor-exit(r19)
            return
        L_0x0078:
            monitor-exit(r19)
            return
        L_0x007a:
            r0 = move-exception
            monitor-exit(r19)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.identifier.zzd.zzc(int, int, long, long, int):void");
    }
}
