package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzfo;
import com.google.android.gms.internal.measurement.zzfy;
import com.google.android.gms.internal.measurement.zznm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement@@22.1.2 */
final class zzt extends zznr {
    private String zza;
    private Set<Integer> zzb;
    private Map<Integer, zzv> zzc;
    private Long zzd;
    private Long zze;

    private final zzv zza(Integer num) {
        if (this.zzc.containsKey(num)) {
            return this.zzc.get(num);
        }
        zzv zzv = new zzv(this, this.zza);
        this.zzc.put(num, zzv);
        return zzv;
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final List<zzfy.zzd> zza(String str, List<zzfy.zzf> list, List<zzfy.zzo> list2, Long l, Long l2) {
        return zza(str, list, list2, l, l2, false);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0310  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0317 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzfy.zzd> zza(java.lang.String r24, java.util.List<com.google.android.gms.internal.measurement.zzfy.zzf> r25, java.util.List<com.google.android.gms.internal.measurement.zzfy.zzo> r26, java.lang.Long r27, java.lang.Long r28, boolean r29) {
        /*
            r23 = this;
            r10 = r23
            r11 = r25
            r12 = r26
            r13 = r29
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r25)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r26)
            r0 = r24
            r10.zza = r0
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r10.zzb = r0
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            r10.zzc = r0
            r0 = r27
            r10.zzd = r0
            r0 = r28
            r10.zze = r0
            java.util.Iterator r0 = r25.iterator()
        L_0x002f:
            boolean r1 = r0.hasNext()
            r14 = 0
            if (r1 == 0) goto L_0x004a
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzfy$zzf r1 = (com.google.android.gms.internal.measurement.zzfy.zzf) r1
            java.lang.String r2 = "_s"
            java.lang.String r1 = r1.zzg()
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x002f
            r1 = 1
            goto L_0x004b
        L_0x004a:
            r1 = r14
        L_0x004b:
            boolean r0 = com.google.android.gms.internal.measurement.zzoe.zza()
            if (r0 == 0) goto L_0x0062
            com.google.android.gms.measurement.internal.zzag r0 = r23.zze()
            java.lang.String r2 = r10.zza
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbh.zzbp
            boolean r0 = r0.zzf(r2, r3)
            if (r0 == 0) goto L_0x0062
            r16 = 1
            goto L_0x0064
        L_0x0062:
            r16 = r14
        L_0x0064:
            boolean r0 = com.google.android.gms.internal.measurement.zzoe.zza()
            if (r0 == 0) goto L_0x007b
            com.google.android.gms.measurement.internal.zzag r0 = r23.zze()
            java.lang.String r2 = r10.zza
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r3 = com.google.android.gms.measurement.internal.zzbh.zzbo
            boolean r0 = r0.zzf(r2, r3)
            if (r0 == 0) goto L_0x007b
            r17 = 1
            goto L_0x007d
        L_0x007b:
            r17 = r14
        L_0x007d:
            if (r1 == 0) goto L_0x00be
            com.google.android.gms.measurement.internal.zzal r2 = r23.zzh()
            java.lang.String r3 = r10.zza
            r2.zzal()
            r2.zzt()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.String r4 = "current_session_count"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r14)
            r0.put(r4, r5)
            android.database.sqlite.SQLiteDatabase r4 = r2.e_()     // Catch:{ SQLiteException -> 0x00ac }
            java.lang.String r5 = "events"
            java.lang.String r6 = "app_id = ?"
            java.lang.String[] r7 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x00ac }
            r4.update(r5, r0, r6, r7)     // Catch:{ SQLiteException -> 0x00ac }
            goto L_0x00be
        L_0x00ac:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzgo r2 = r2.zzj()
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzg()
            java.lang.String r4 = "Error resetting session-scoped event counts. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r3)
            r2.zza(r4, r3, r0)
        L_0x00be:
            java.util.Map r0 = java.util.Collections.emptyMap()
            if (r17 == 0) goto L_0x00d0
            if (r16 == 0) goto L_0x00d0
            com.google.android.gms.measurement.internal.zzal r0 = r23.zzh()
            java.lang.String r2 = r10.zza
            java.util.Map r0 = r0.zzn(r2)
        L_0x00d0:
            com.google.android.gms.measurement.internal.zzal r2 = r23.zzh()
            java.lang.String r3 = r10.zza
            java.util.Map r9 = r2.zzm(r3)
            boolean r2 = r9.isEmpty()
            if (r2 != 0) goto L_0x03c8
            java.util.HashSet r2 = new java.util.HashSet
            java.util.Set r3 = r9.keySet()
            r2.<init>(r3)
            if (r1 == 0) goto L_0x020b
            java.lang.String r1 = r10.zza
            com.google.android.gms.measurement.internal.zzal r3 = r23.zzh()
            java.lang.String r4 = r10.zza
            java.util.Map r3 = r3.zzo(r4)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap
            r1.<init>()
            boolean r4 = r9.isEmpty()
            if (r4 != 0) goto L_0x0209
            java.util.Set r4 = r9.keySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0110:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0209
            java.lang.Object r5 = r4.next()
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            java.lang.Object r6 = r9.get(r6)
            com.google.android.gms.internal.measurement.zzfy$zzm r6 = (com.google.android.gms.internal.measurement.zzfy.zzm) r6
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
            java.lang.Object r7 = r3.get(r7)
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L_0x01f7
            boolean r18 = r7.isEmpty()
            if (r18 == 0) goto L_0x013e
            goto L_0x01f7
        L_0x013e:
            com.google.android.gms.measurement.internal.zzoo r8 = r23.g_()
            java.util.List r14 = r6.zzi()
            java.util.List r8 = r8.zza((java.util.List<java.lang.Long>) r14, (java.util.List<java.lang.Integer>) r7)
            boolean r14 = r8.isEmpty()
            if (r14 != 0) goto L_0x0206
            com.google.android.gms.internal.measurement.zzjt$zzb r14 = r6.zzcd()
            r18 = r14
            com.google.android.gms.internal.measurement.zzjt$zzb r18 = (com.google.android.gms.internal.measurement.zzjt.zzb) r18
            com.google.android.gms.internal.measurement.zzfy$zzm$zza r14 = (com.google.android.gms.internal.measurement.zzfy.zzm.zza) r14
            com.google.android.gms.internal.measurement.zzfy$zzm$zza r14 = r14.zzb()
            com.google.android.gms.internal.measurement.zzfy$zzm$zza r8 = r14.zzb(r8)
            com.google.android.gms.measurement.internal.zzoo r14 = r23.g_()
            java.util.List r15 = r6.zzk()
            java.util.List r14 = r14.zza((java.util.List<java.lang.Long>) r15, (java.util.List<java.lang.Integer>) r7)
            com.google.android.gms.internal.measurement.zzfy$zzm$zza r15 = r8.zzd()
            r15.zzd(r14)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.List r15 = r6.zzh()
            java.util.Iterator r15 = r15.iterator()
        L_0x0182:
            boolean r18 = r15.hasNext()
            if (r18 == 0) goto L_0x01aa
            java.lang.Object r18 = r15.next()
            r19 = r3
            r3 = r18
            com.google.android.gms.internal.measurement.zzfy$zze r3 = (com.google.android.gms.internal.measurement.zzfy.zze) r3
            int r18 = r3.zza()
            r20 = r4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r18)
            boolean r4 = r7.contains(r4)
            if (r4 != 0) goto L_0x01a5
            r14.add(r3)
        L_0x01a5:
            r3 = r19
            r4 = r20
            goto L_0x0182
        L_0x01aa:
            r19 = r3
            r20 = r4
            com.google.android.gms.internal.measurement.zzfy$zzm$zza r3 = r8.zza()
            r3.zza(r14)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.List r4 = r6.zzj()
            java.util.Iterator r4 = r4.iterator()
        L_0x01c2:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x01e0
            java.lang.Object r6 = r4.next()
            com.google.android.gms.internal.measurement.zzfy$zzn r6 = (com.google.android.gms.internal.measurement.zzfy.zzn) r6
            int r14 = r6.zzb()
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            boolean r14 = r7.contains(r14)
            if (r14 != 0) goto L_0x01c2
            r3.add(r6)
            goto L_0x01c2
        L_0x01e0:
            com.google.android.gms.internal.measurement.zzfy$zzm$zza r4 = r8.zzc()
            r4.zzc(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            com.google.android.gms.internal.measurement.zzlc r4 = r8.zzai()
            com.google.android.gms.internal.measurement.zzjt r4 = (com.google.android.gms.internal.measurement.zzjt) r4
            com.google.android.gms.internal.measurement.zzfy$zzm r4 = (com.google.android.gms.internal.measurement.zzfy.zzm) r4
            r1.put(r3, r4)
            goto L_0x0202
        L_0x01f7:
            r19 = r3
            r20 = r4
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            r1.put(r3, r6)
        L_0x0202:
            r3 = r19
            r4 = r20
        L_0x0206:
            r14 = 0
            goto L_0x0110
        L_0x0209:
            r14 = r1
            goto L_0x020c
        L_0x020b:
            r14 = r9
        L_0x020c:
            java.util.Iterator r15 = r2.iterator()
        L_0x0210:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L_0x03c8
            java.lang.Object r1 = r15.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r18 = r1.intValue()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r18)
            java.lang.Object r1 = r14.get(r1)
            com.google.android.gms.internal.measurement.zzfy$zzm r1 = (com.google.android.gms.internal.measurement.zzfy.zzm) r1
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            androidx.collection.ArrayMap r7 = new androidx.collection.ArrayMap
            r7.<init>()
            if (r1 == 0) goto L_0x0278
            int r2 = r1.zza()
            if (r2 != 0) goto L_0x0242
            goto L_0x0278
        L_0x0242:
            java.util.List r2 = r1.zzh()
            java.util.Iterator r2 = r2.iterator()
        L_0x024a:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0278
            java.lang.Object r3 = r2.next()
            com.google.android.gms.internal.measurement.zzfy$zze r3 = (com.google.android.gms.internal.measurement.zzfy.zze) r3
            boolean r4 = r3.zzf()
            if (r4 == 0) goto L_0x024a
            int r4 = r3.zza()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            boolean r8 = r3.zze()
            if (r8 == 0) goto L_0x0273
            long r19 = r3.zzb()
            java.lang.Long r3 = java.lang.Long.valueOf(r19)
            goto L_0x0274
        L_0x0273:
            r3 = 0
        L_0x0274:
            r7.put(r4, r3)
            goto L_0x024a
        L_0x0278:
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap
            r8.<init>()
            if (r1 == 0) goto L_0x02c9
            int r2 = r1.zzc()
            if (r2 != 0) goto L_0x0286
            goto L_0x02c9
        L_0x0286:
            java.util.List r2 = r1.zzj()
            java.util.Iterator r2 = r2.iterator()
        L_0x028e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x02c9
            java.lang.Object r3 = r2.next()
            com.google.android.gms.internal.measurement.zzfy$zzn r3 = (com.google.android.gms.internal.measurement.zzfy.zzn) r3
            boolean r4 = r3.zzf()
            if (r4 == 0) goto L_0x02c4
            int r4 = r3.zza()
            if (r4 <= 0) goto L_0x02c4
            int r4 = r3.zzb()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            int r19 = r3.zza()
            r21 = r2
            r20 = 1
            int r2 = r19 + -1
            long r2 = r3.zza(r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r8.put(r4, r2)
            goto L_0x02c6
        L_0x02c4:
            r21 = r2
        L_0x02c6:
            r2 = r21
            goto L_0x028e
        L_0x02c9:
            if (r1 == 0) goto L_0x031e
            r2 = 0
        L_0x02cc:
            int r3 = r1.zzd()
            int r3 = r3 << 6
            if (r2 >= r3) goto L_0x031e
            java.util.List r3 = r1.zzk()
            boolean r3 = com.google.android.gms.measurement.internal.zzoo.zza((java.util.List<java.lang.Long>) r3, (int) r2)
            if (r3 == 0) goto L_0x0309
            com.google.android.gms.measurement.internal.zzgo r3 = r23.zzj()
            com.google.android.gms.measurement.internal.zzgq r3 = r3.zzp()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r18)
            r19 = r14
            java.lang.Integer r14 = java.lang.Integer.valueOf(r2)
            r20 = r15
            java.lang.String r15 = "Filter already evaluated. audience ID, filter ID"
            r3.zza(r15, r4, r14)
            r6.set(r2)
            java.util.List r3 = r1.zzi()
            boolean r3 = com.google.android.gms.measurement.internal.zzoo.zza((java.util.List<java.lang.Long>) r3, (int) r2)
            if (r3 == 0) goto L_0x030d
            r5.set(r2)
            r3 = 1
            goto L_0x030e
        L_0x0309:
            r19 = r14
            r20 = r15
        L_0x030d:
            r3 = 0
        L_0x030e:
            if (r3 != 0) goto L_0x0317
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            r7.remove(r3)
        L_0x0317:
            int r2 = r2 + 1
            r14 = r19
            r15 = r20
            goto L_0x02cc
        L_0x031e:
            r19 = r14
            r20 = r15
            java.lang.Integer r1 = java.lang.Integer.valueOf(r18)
            java.lang.Object r1 = r9.get(r1)
            r4 = r1
            com.google.android.gms.internal.measurement.zzfy$zzm r4 = (com.google.android.gms.internal.measurement.zzfy.zzm) r4
            if (r17 == 0) goto L_0x03a4
            if (r16 == 0) goto L_0x03a4
            java.lang.Integer r1 = java.lang.Integer.valueOf(r18)
            java.lang.Object r1 = r0.get(r1)
            java.util.List r1 = (java.util.List) r1
            if (r1 == 0) goto L_0x03a4
            java.lang.Long r2 = r10.zze
            if (r2 == 0) goto L_0x03a4
            java.lang.Long r2 = r10.zzd
            if (r2 != 0) goto L_0x0346
            goto L_0x03a4
        L_0x0346:
            java.util.Iterator r1 = r1.iterator()
        L_0x034a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x03a4
            java.lang.Object r2 = r1.next()
            com.google.android.gms.internal.measurement.zzfo$zzb r2 = (com.google.android.gms.internal.measurement.zzfo.zzb) r2
            int r3 = r2.zzb()
            java.lang.Long r14 = r10.zze
            long r14 = r14.longValue()
            r21 = 1000(0x3e8, double:4.94E-321)
            long r14 = r14 / r21
            boolean r2 = r2.zzi()
            if (r2 == 0) goto L_0x0372
            java.lang.Long r2 = r10.zzd
            long r14 = r2.longValue()
            long r14 = r14 / r21
        L_0x0372:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            boolean r2 = r7.containsKey(r2)
            if (r2 == 0) goto L_0x038a
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            r21 = r0
            java.lang.Long r0 = java.lang.Long.valueOf(r14)
            r7.put(r2, r0)
            goto L_0x038c
        L_0x038a:
            r21 = r0
        L_0x038c:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            boolean r0 = r8.containsKey(r0)
            if (r0 == 0) goto L_0x03a1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            java.lang.Long r2 = java.lang.Long.valueOf(r14)
            r8.put(r0, r2)
        L_0x03a1:
            r0 = r21
            goto L_0x034a
        L_0x03a4:
            r21 = r0
            com.google.android.gms.measurement.internal.zzv r0 = new com.google.android.gms.measurement.internal.zzv
            java.lang.String r3 = r10.zza
            r14 = 0
            r1 = r0
            r2 = r23
            r15 = 0
            r22 = r9
            r9 = r14
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            java.util.Map<java.lang.Integer, com.google.android.gms.measurement.internal.zzv> r1 = r10.zzc
            java.lang.Integer r2 = java.lang.Integer.valueOf(r18)
            r1.put(r2, r0)
            r14 = r19
            r15 = r20
            r0 = r21
            r9 = r22
            goto L_0x0210
        L_0x03c8:
            r15 = 0
            boolean r0 = com.google.android.gms.internal.measurement.zznm.zza()
            if (r0 == 0) goto L_0x03ee
            com.google.android.gms.measurement.internal.zzag r0 = r23.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzbh.zzcy
            boolean r0 = r0.zzf(r15, r1)
            if (r0 == 0) goto L_0x03ee
            r10.zza((java.util.List<com.google.android.gms.internal.measurement.zzfy.zzf>) r11, (boolean) r13)
            if (r13 == 0) goto L_0x03e6
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            return r0
        L_0x03e6:
            r10.zza((java.util.List<com.google.android.gms.internal.measurement.zzfy.zzo>) r12)
            java.util.List r0 = r23.zzu()
            return r0
        L_0x03ee:
            r1 = 1
            r10.zza((java.util.List<com.google.android.gms.internal.measurement.zzfy.zzf>) r11, (boolean) r1)
            r10.zza((java.util.List<com.google.android.gms.internal.measurement.zzfy.zzo>) r12)
            java.util.List r0 = r23.zzu()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long, boolean):java.util.List");
    }

    private final List<zzfy.zzd> zzu() {
        ArrayList arrayList = new ArrayList();
        Set<Integer> keySet = this.zzc.keySet();
        keySet.removeAll(this.zzb);
        for (Integer intValue : keySet) {
            int intValue2 = intValue.intValue();
            zzv zzv = this.zzc.get(Integer.valueOf(intValue2));
            Preconditions.checkNotNull(zzv);
            zzfy.zzd zza2 = zzv.zza(intValue2);
            arrayList.add(zza2);
            zzal zzh = zzh();
            String str = this.zza;
            zzfy.zzm zzd2 = zza2.zzd();
            zzh.zzal();
            zzh.zzt();
            Preconditions.checkNotEmpty(str);
            Preconditions.checkNotNull(zzd2);
            byte[] zzca = zzd2.zzca();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(intValue2));
            contentValues.put("current_results", zzca);
            try {
                if (zzh.e_().insertWithOnConflict("audience_filter_values", (String) null, contentValues, 5) == -1) {
                    zzh.zzj().zzg().zza("Failed to insert filter results (got -1). appId", zzgo.zza(str));
                }
            } catch (SQLiteException e) {
                zzh.zzj().zzg().zza("Error storing filter results. appId", zzgo.zza(str), e);
            }
        }
        return arrayList;
    }

    zzt(zznv zznv) {
        super(zznv);
    }

    private final void zza(List<zzfy.zzf> list, boolean z) {
        zzbb zzbb;
        int i;
        Map<Integer, List<zzfo.zzb>> map;
        long j;
        if (!list.isEmpty()) {
            String str = null;
            zzy zzy = new zzy(this);
            ArrayMap arrayMap = new ArrayMap();
            for (zzfy.zzf next : list) {
                zzfy.zzf zza2 = zzy.zza(this.zza, next);
                if (zza2 != null) {
                    zzal zzh = zzh();
                    String str2 = this.zza;
                    String zzg = zza2.zzg();
                    zzbb zzd2 = zzh.zzd(str2, next.zzg());
                    if (zzd2 == null) {
                        zzh.zzj().zzu().zza("Event aggregate wasn't created during raw event logging. appId, event", zzgo.zza(str2), zzh.zzi().zza(zzg));
                        zzbb = new zzbb(str2, next.zzg(), 1, 1, 1, next.zzd(), 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
                    } else {
                        zzbb = new zzbb(zzd2.zza, zzd2.zzb, zzd2.zzc + 1, zzd2.zzd + 1, zzd2.zze + 1, zzd2.zzf, zzd2.zzg, zzd2.zzh, zzd2.zzi, zzd2.zzj, zzd2.zzk);
                    }
                    zzbb zzbb2 = zzbb;
                    zzh().zza(zzbb2);
                    if (!zznm.zza() || !zze().zzf(str, zzbh.zzcy) || !z) {
                        long j2 = zzbb2.zzc;
                        String zzg2 = zza2.zzg();
                        Map<Integer, List<zzfo.zzb>> map2 = (Map) arrayMap.get(zzg2);
                        if (map2 == null) {
                            map2 = zzh().zzf(this.zza, zzg2);
                            arrayMap.put(zzg2, map2);
                        }
                        Map<Integer, List<zzfo.zzb>> map3 = map2;
                        for (Integer intValue : map3.keySet()) {
                            int intValue2 = intValue.intValue();
                            if (this.zzb.contains(Integer.valueOf(intValue2))) {
                                zzj().zzp().zza("Skipping failed audience ID", Integer.valueOf(intValue2));
                            } else {
                                Iterator it = map3.get(Integer.valueOf(intValue2)).iterator();
                                boolean z2 = true;
                                while (true) {
                                    if (!it.hasNext()) {
                                        i = intValue2;
                                        map = map3;
                                        j = j2;
                                        break;
                                    }
                                    zzfo.zzb zzb2 = (zzfo.zzb) it.next();
                                    zzx zzx = new zzx(this, this.zza, intValue2, zzb2);
                                    i = intValue2;
                                    zzx zzx2 = zzx;
                                    map = map3;
                                    j = j2;
                                    z2 = zzx.zza(this.zzd, this.zze, zza2, j2, zzbb2, zza(intValue2, zzb2.zzb()));
                                    if (!z2) {
                                        this.zzb.add(Integer.valueOf(i));
                                        break;
                                    }
                                    zza(Integer.valueOf(i)).zza((zzaa) zzx2);
                                    intValue2 = i;
                                    map3 = map;
                                    j2 = j;
                                }
                                if (!z2) {
                                    this.zzb.add(Integer.valueOf(i));
                                }
                                map3 = map;
                                j2 = j;
                                str = null;
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0125, code lost:
        if (r6.zzi() == false) goto L_0x012f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0127, code lost:
        r8 = java.lang.Integer.valueOf(r6.zza());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x012f, code lost:
        r5.zza("Invalid property filter ID. appId, id", r7, java.lang.String.valueOf(r8));
        r6 = false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zza(java.util.List<com.google.android.gms.internal.measurement.zzfy.zzo> r14) {
        /*
            r13 = this;
            boolean r0 = r14.isEmpty()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            java.util.Iterator r14 = r14.iterator()
        L_0x0010:
            boolean r1 = r14.hasNext()
            if (r1 == 0) goto L_0x0146
            java.lang.Object r1 = r14.next()
            com.google.android.gms.internal.measurement.zzfy$zzo r1 = (com.google.android.gms.internal.measurement.zzfy.zzo) r1
            java.lang.String r2 = r1.zzg()
            java.lang.Object r3 = r0.get(r2)
            java.util.Map r3 = (java.util.Map) r3
            if (r3 != 0) goto L_0x0035
            com.google.android.gms.measurement.internal.zzal r3 = r13.zzh()
            java.lang.String r4 = r13.zza
            java.util.Map r3 = r3.zzg(r4, r2)
            r0.put(r2, r3)
        L_0x0035:
            java.util.Set r2 = r3.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x003d:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0010
            java.lang.Object r4 = r2.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            java.util.Set<java.lang.Integer> r5 = r13.zzb
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)
            boolean r5 = r5.contains(r6)
            if (r5 == 0) goto L_0x006b
            com.google.android.gms.measurement.internal.zzgo r1 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgq r1 = r1.zzp()
            java.lang.String r2 = "Skipping failed audience ID"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r4)
            r1.zza(r2, r3)
            goto L_0x0010
        L_0x006b:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r4)
            java.lang.Object r5 = r3.get(r5)
            java.util.List r5 = (java.util.List) r5
            java.util.Iterator r5 = r5.iterator()
            r6 = 1
        L_0x007a:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x0139
            java.lang.Object r6 = r5.next()
            com.google.android.gms.internal.measurement.zzfo$zze r6 = (com.google.android.gms.internal.measurement.zzfo.zze) r6
            com.google.android.gms.measurement.internal.zzgo r7 = r13.zzj()
            r8 = 2
            boolean r7 = r7.zza((int) r8)
            r8 = 0
            if (r7 == 0) goto L_0x00d4
            com.google.android.gms.measurement.internal.zzgo r7 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzp()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r4)
            boolean r10 = r6.zzi()
            if (r10 == 0) goto L_0x00ad
            int r10 = r6.zza()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            goto L_0x00ae
        L_0x00ad:
            r10 = r8
        L_0x00ae:
            com.google.android.gms.measurement.internal.zzgh r11 = r13.zzi()
            java.lang.String r12 = r6.zze()
            java.lang.String r11 = r11.zzc(r12)
            java.lang.String r12 = "Evaluating filter. audience, filter, property"
            r7.zza(r12, r9, r10, r11)
            com.google.android.gms.measurement.internal.zzgo r7 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzp()
            com.google.android.gms.measurement.internal.zzoo r9 = r13.g_()
            java.lang.String r9 = r9.zza((com.google.android.gms.internal.measurement.zzfo.zze) r6)
            java.lang.String r10 = "Filter definition"
            r7.zza(r10, r9)
        L_0x00d4:
            boolean r7 = r6.zzi()
            if (r7 == 0) goto L_0x0113
            int r7 = r6.zza()
            r9 = 256(0x100, float:3.59E-43)
            if (r7 <= r9) goto L_0x00e3
            goto L_0x0113
        L_0x00e3:
            com.google.android.gms.measurement.internal.zzz r7 = new com.google.android.gms.measurement.internal.zzz
            java.lang.String r8 = r13.zza
            r7.<init>(r13, r8, r4, r6)
            java.lang.Long r8 = r13.zzd
            java.lang.Long r9 = r13.zze
            int r6 = r6.zza()
            boolean r6 = r13.zza((int) r4, (int) r6)
            boolean r6 = r7.zza(r8, r9, r1, r6)
            if (r6 == 0) goto L_0x0109
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            com.google.android.gms.measurement.internal.zzv r8 = r13.zza((java.lang.Integer) r8)
            r8.zza((com.google.android.gms.measurement.internal.zzaa) r7)
            goto L_0x007a
        L_0x0109:
            java.util.Set<java.lang.Integer> r5 = r13.zzb
            java.lang.Integer r7 = java.lang.Integer.valueOf(r4)
            r5.add(r7)
            goto L_0x0139
        L_0x0113:
            com.google.android.gms.measurement.internal.zzgo r5 = r13.zzj()
            com.google.android.gms.measurement.internal.zzgq r5 = r5.zzu()
            java.lang.String r7 = r13.zza
            java.lang.Object r7 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r7)
            boolean r9 = r6.zzi()
            if (r9 == 0) goto L_0x012f
            int r6 = r6.zza()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)
        L_0x012f:
            java.lang.String r6 = java.lang.String.valueOf(r8)
            java.lang.String r8 = "Invalid property filter ID. appId, id"
            r5.zza(r8, r7, r6)
            r6 = 0
        L_0x0139:
            if (r6 != 0) goto L_0x003d
            java.util.Set<java.lang.Integer> r5 = r13.zzb
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5.add(r4)
            goto L_0x003d
        L_0x0146:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzt.zza(java.util.List):void");
    }

    private final boolean zza(int i, int i2) {
        zzv zzv = this.zzc.get(Integer.valueOf(i));
        if (zzv == null) {
            return false;
        }
        return zzv.zzd.get(i2);
    }
}
