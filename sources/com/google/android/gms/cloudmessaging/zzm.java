package com.google.android.gms.cloudmessaging;

import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
public final /* synthetic */ class zzm implements Handler.Callback {
    public final /* synthetic */ zzp zza;

    public /* synthetic */ zzm(zzp zzp) {
        this.zza = zzp;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0049, code lost:
        r5 = r5.getData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0054, code lost:
        if (r5.getBoolean("unsupported", false) == false) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        r3.zzc(new com.google.android.gms.cloudmessaging.zzt(4, "Not supported by GmsCore", (java.lang.Throwable) null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0063, code lost:
        r3.zza(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r5) {
        /*
            r4 = this;
            java.lang.String r0 = "Received response for unknown request: "
            java.lang.String r1 = "MessengerIpcClient"
            int r2 = r5.arg1
            r3 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r3)
            if (r1 == 0) goto L_0x0020
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Received response to request: "
            r1.<init>(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r3 = "MessengerIpcClient"
            android.util.Log.d(r3, r1)
        L_0x0020:
            com.google.android.gms.cloudmessaging.zzp r1 = r4.zza
            monitor-enter(r1)
            android.util.SparseArray r3 = r1.zze     // Catch:{ all -> 0x0068 }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ all -> 0x0068 }
            com.google.android.gms.cloudmessaging.zzs r3 = (com.google.android.gms.cloudmessaging.zzs) r3     // Catch:{ all -> 0x0068 }
            if (r3 != 0) goto L_0x0040
            java.lang.String r5 = "MessengerIpcClient"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0068 }
            r3.<init>(r0)     // Catch:{ all -> 0x0068 }
            r3.append(r2)     // Catch:{ all -> 0x0068 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0068 }
            io.sentry.android.core.SentryLogcatAdapter.w((java.lang.String) r5, (java.lang.String) r0)     // Catch:{ all -> 0x0068 }
            monitor-exit(r1)     // Catch:{ all -> 0x0068 }
            goto L_0x0066
        L_0x0040:
            android.util.SparseArray r0 = r1.zze     // Catch:{ all -> 0x0068 }
            r0.remove(r2)     // Catch:{ all -> 0x0068 }
            r1.zzf()     // Catch:{ all -> 0x0068 }
            monitor-exit(r1)     // Catch:{ all -> 0x0068 }
            android.os.Bundle r5 = r5.getData()
            java.lang.String r0 = "unsupported"
            r1 = 0
            boolean r0 = r5.getBoolean(r0, r1)
            if (r0 == 0) goto L_0x0063
            java.lang.String r5 = "Not supported by GmsCore"
            com.google.android.gms.cloudmessaging.zzt r0 = new com.google.android.gms.cloudmessaging.zzt
            r1 = 4
            r2 = 0
            r0.<init>(r1, r5, r2)
            r3.zzc(r0)
            goto L_0x0066
        L_0x0063:
            r3.zza(r5)
        L_0x0066:
            r5 = 1
            return r5
        L_0x0068:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0068 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cloudmessaging.zzm.handleMessage(android.os.Message):boolean");
    }
}
