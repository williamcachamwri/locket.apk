package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
public final class zzar {
    private final String zza;
    private long zzb = -1;
    private final /* synthetic */ zzal zzc;

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0103  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.measurement.internal.zzap> zza() {
        /*
            r19 = this;
            r1 = r19
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String r6 = "app_id = ? and rowid > ?"
            java.lang.String r0 = r1.zza
            long r3 = r1.zzb
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String[] r7 = new java.lang.String[]{r0, r3}
            com.google.android.gms.measurement.internal.zzal r0 = r1.zzc     // Catch:{ SQLiteException -> 0x00e3, all -> 0x00e0 }
            android.database.sqlite.SQLiteDatabase r3 = r0.e_()     // Catch:{ SQLiteException -> 0x00e3, all -> 0x00e0 }
            java.lang.String r4 = "raw_events"
            r0 = 6
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00e3, all -> 0x00e0 }
            java.lang.String r0 = "rowid"
            r13 = 0
            r5[r13] = r0     // Catch:{ SQLiteException -> 0x00e3, all -> 0x00e0 }
            java.lang.String r0 = "name"
            r14 = 1
            r5[r14] = r0     // Catch:{ SQLiteException -> 0x00e3, all -> 0x00e0 }
            java.lang.String r0 = "timestamp"
            r15 = 2
            r5[r15] = r0     // Catch:{ SQLiteException -> 0x00e3, all -> 0x00e0 }
            java.lang.String r0 = "metadata_fingerprint"
            r11 = 3
            r5[r11] = r0     // Catch:{ SQLiteException -> 0x00e3, all -> 0x00e0 }
            java.lang.String r0 = "data"
            r10 = 4
            r5[r10] = r0     // Catch:{ SQLiteException -> 0x00e3, all -> 0x00e0 }
            java.lang.String r0 = "realtime"
            r9 = 5
            r5[r9] = r0     // Catch:{ SQLiteException -> 0x00e3, all -> 0x00e0 }
            r8 = 0
            r0 = 0
            java.lang.String r16 = "rowid"
            java.lang.String r17 = "1000"
            r12 = r9
            r9 = r0
            r15 = r10
            r10 = r16
            r14 = r11
            r11 = r17
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x00e3, all -> 0x00e0 }
            boolean r0 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            if (r0 != 0) goto L_0x0060
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            if (r3 == 0) goto L_0x005f
            r3.close()
        L_0x005f:
            return r0
        L_0x0060:
            long r5 = r3.getLong(r13)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            long r7 = r3.getLong(r14)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            long r9 = r3.getLong(r12)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            r17 = 1
            int r0 = (r9 > r17 ? 1 : (r9 == r17 ? 0 : -1))
            if (r0 != 0) goto L_0x0074
            r9 = 1
            goto L_0x0075
        L_0x0074:
            r9 = r13
        L_0x0075:
            byte[] r0 = r3.getBlob(r15)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            long r10 = r1.zzb     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            int r4 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r4 <= 0) goto L_0x0081
            r1.zzb = r5     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
        L_0x0081:
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r4 = com.google.android.gms.internal.measurement.zzfy.zzf.zze()     // Catch:{ IOException -> 0x00b5 }
            com.google.android.gms.internal.measurement.zzlb r0 = com.google.android.gms.measurement.internal.zzoo.zza(r4, (byte[]) r0)     // Catch:{ IOException -> 0x00b5 }
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r0 = (com.google.android.gms.internal.measurement.zzfy.zzf.zza) r0     // Catch:{ IOException -> 0x00b5 }
            r11 = 1
            java.lang.String r4 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            if (r4 == 0) goto L_0x0093
            goto L_0x0095
        L_0x0093:
            java.lang.String r4 = ""
        L_0x0095:
            com.google.android.gms.internal.measurement.zzfy$zzf$zza r4 = r0.zza((java.lang.String) r4)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            r10 = 2
            long r11 = r3.getLong(r10)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            r4.zzb((long) r11)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            com.google.android.gms.measurement.internal.zzap r11 = new com.google.android.gms.measurement.internal.zzap     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            com.google.android.gms.internal.measurement.zzlc r0 = r0.zzai()     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            com.google.android.gms.internal.measurement.zzjt r0 = (com.google.android.gms.internal.measurement.zzjt) r0     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            com.google.android.gms.internal.measurement.zzfy$zzf r0 = (com.google.android.gms.internal.measurement.zzfy.zzf) r0     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            r4 = r11
            r12 = r10
            r10 = r0
            r4.<init>(r5, r7, r9, r10)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            r2.add(r11)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            goto L_0x00cc
        L_0x00b5:
            r0 = move-exception
            r12 = 2
            com.google.android.gms.measurement.internal.zzal r4 = r1.zzc     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            com.google.android.gms.measurement.internal.zzgo r4 = r4.zzj()     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            java.lang.String r5 = "Data loss. Failed to merge raw event. appId"
            java.lang.String r6 = r1.zza     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r6)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            r4.zza(r5, r6, r0)     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
        L_0x00cc:
            boolean r0 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x00dd, all -> 0x00da }
            if (r0 != 0) goto L_0x00d8
            if (r3 == 0) goto L_0x00ff
            r3.close()
            goto L_0x00ff
        L_0x00d8:
            r12 = 5
            goto L_0x0060
        L_0x00da:
            r0 = move-exception
            r12 = r3
            goto L_0x0101
        L_0x00dd:
            r0 = move-exception
            r12 = r3
            goto L_0x00e5
        L_0x00e0:
            r0 = move-exception
            r12 = 0
            goto L_0x0101
        L_0x00e3:
            r0 = move-exception
            r12 = 0
        L_0x00e5:
            com.google.android.gms.measurement.internal.zzal r3 = r1.zzc     // Catch:{ all -> 0x0100 }
            com.google.android.gms.measurement.internal.zzgo r3 = r3.zzj()     // Catch:{ all -> 0x0100 }
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzg()     // Catch:{ all -> 0x0100 }
            java.lang.String r4 = "Data loss. Error querying raw events batch. appId"
            java.lang.String r5 = r1.zza     // Catch:{ all -> 0x0100 }
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r5)     // Catch:{ all -> 0x0100 }
            r3.zza(r4, r5, r0)     // Catch:{ all -> 0x0100 }
            if (r12 == 0) goto L_0x00ff
            r12.close()
        L_0x00ff:
            return r2
        L_0x0100:
            r0 = move-exception
        L_0x0101:
            if (r12 == 0) goto L_0x0106
            r12.close()
        L_0x0106:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzar.zza():java.util.List");
    }

    public zzar(zzal zzal, String str) {
        this.zzc = zzal;
        Preconditions.checkNotEmpty(str);
        this.zza = str;
    }
}
