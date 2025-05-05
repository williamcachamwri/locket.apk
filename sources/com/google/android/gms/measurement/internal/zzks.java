package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdo;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzks implements Runnable {
    private final /* synthetic */ zzdo zza;
    private final /* synthetic */ zzjq zzb;

    zzks(zzjq zzjq, zzdo zzdo) {
        this.zza = zzdo;
        this.zzb = zzjq;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0069 A[SYNTHETIC, Splitter:B:12:0x0069] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r7 = this;
            com.google.android.gms.measurement.internal.zzjq r0 = r7.zzb
            com.google.android.gms.measurement.internal.zznb r0 = r0.zzp()
            com.google.android.gms.measurement.internal.zzha r1 = r0.zzk()
            com.google.android.gms.measurement.internal.zzje r1 = r1.zzo()
            boolean r1 = r1.zzh()
            r2 = 0
            if (r1 != 0) goto L_0x0024
            com.google.android.gms.measurement.internal.zzgo r0 = r0.zzj()
            com.google.android.gms.measurement.internal.zzgq r0 = r0.zzv()
            java.lang.String r1 = "Analytics storage consent denied; will not get session id"
            r0.zza(r1)
        L_0x0022:
            r0 = r2
            goto L_0x0055
        L_0x0024:
            com.google.android.gms.measurement.internal.zzha r1 = r0.zzk()
            com.google.android.gms.common.util.Clock r3 = r0.zzb()
            long r3 = r3.currentTimeMillis()
            boolean r1 = r1.zza((long) r3)
            if (r1 != 0) goto L_0x0022
            com.google.android.gms.measurement.internal.zzha r1 = r0.zzk()
            com.google.android.gms.measurement.internal.zzhb r1 = r1.zzl
            long r3 = r1.zza()
            r5 = 0
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x0047
            goto L_0x0022
        L_0x0047:
            com.google.android.gms.measurement.internal.zzha r0 = r0.zzk()
            com.google.android.gms.measurement.internal.zzhb r0 = r0.zzl
            long r0 = r0.zza()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
        L_0x0055:
            if (r0 == 0) goto L_0x0069
            com.google.android.gms.measurement.internal.zzjq r1 = r7.zzb
            com.google.android.gms.measurement.internal.zzhy r1 = r1.zzu
            com.google.android.gms.measurement.internal.zzos r1 = r1.zzt()
            com.google.android.gms.internal.measurement.zzdo r2 = r7.zza
            long r3 = r0.longValue()
            r1.zza((com.google.android.gms.internal.measurement.zzdo) r2, (long) r3)
            return
        L_0x0069:
            com.google.android.gms.internal.measurement.zzdo r0 = r7.zza     // Catch:{ RemoteException -> 0x006f }
            r0.zza(r2)     // Catch:{ RemoteException -> 0x006f }
            return
        L_0x006f:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzjq r1 = r7.zzb
            com.google.android.gms.measurement.internal.zzhy r1 = r1.zzu
            com.google.android.gms.measurement.internal.zzgo r1 = r1.zzj()
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzg()
            java.lang.String r2 = "getSessionId failed with exception"
            r1.zza(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzks.run():void");
    }
}
