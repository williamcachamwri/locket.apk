package com.google.ads.interactivemedia.v3.impl;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.AdError;
import com.google.ads.interactivemedia.v3.internal.zzgi;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzam {
    private final zzaf zza;
    private final zzat zzb;
    private final ExecutorService zzc;
    private final zzbi zzd;
    private final String zze;
    private final zzgi zzf;
    private final float zzg;

    zzam(Context context, ExecutorService executorService, String str, zzaf zzaf, zzat zzat, zzgi zzgi, zzbi zzbi) {
        this.zzf = zzgi;
        this.zzc = executorService;
        this.zzb = zzat;
        this.zza = zzaf;
        this.zzd = zzbi;
        this.zze = str;
        this.zzg = context.getResources().getDisplayMetrics().density;
    }

    private final void zzb() {
        this.zzb.zzc(new zzd(new AdError(AdError.AdErrorType.LOAD, AdError.AdErrorCode.INTERNAL_ERROR, "Unable to parse companion information.")));
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x004e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.ads.interactivemedia.v3.impl.data.zzbu r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            if (r1 == 0) goto L_0x0100
            java.util.Map<java.lang.String, com.google.ads.interactivemedia.v3.impl.data.zzbe> r2 = r1.companions
            if (r2 != 0) goto L_0x000c
            goto L_0x0100
        L_0x000c:
            com.google.ads.interactivemedia.v3.impl.zzaf r3 = r0.zza
            java.util.Set r2 = r2.keySet()
            int r4 = r2.size()
            java.util.HashMap r4 = com.google.ads.interactivemedia.v3.internal.zzrz.zzb(r4)
            java.util.Iterator r2 = r2.iterator()
        L_0x001e:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0046
            java.lang.Object r5 = r2.next()
            java.lang.String r5 = (java.lang.String) r5
            java.util.Map r7 = r3.zza()
            java.lang.Object r7 = r7.get(r5)
            com.google.ads.interactivemedia.v3.api.CompanionAdSlot r7 = (com.google.ads.interactivemedia.v3.api.CompanionAdSlot) r7
            if (r7 == 0) goto L_0x003b
            android.view.ViewGroup r6 = r7.getContainer()
            goto L_0x003c
        L_0x003b:
            r6 = 0
        L_0x003c:
            if (r6 == 0) goto L_0x0042
            r4.put(r5, r6)
            goto L_0x001e
        L_0x0042:
            r17.zzb()
            goto L_0x001e
        L_0x0046:
            java.util.Set r2 = r4.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x004e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00ff
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r5 = r4.get(r3)
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            java.util.Map<java.lang.String, com.google.ads.interactivemedia.v3.impl.data.zzbe> r7 = r1.companions
            java.lang.Object r7 = r7.get(r3)
            r10 = r7
            com.google.ads.interactivemedia.v3.impl.data.zzbe r10 = (com.google.ads.interactivemedia.v3.impl.data.zzbe) r10
            com.google.ads.interactivemedia.v3.impl.zzaf r7 = r0.zza
            java.util.Map r7 = r7.zza()
            java.lang.Object r3 = r7.get(r3)
            com.google.ads.interactivemedia.v3.api.CompanionAdSlot r3 = (com.google.ads.interactivemedia.v3.api.CompanionAdSlot) r3
            r5.removeAllViews()
            com.google.ads.interactivemedia.v3.impl.zzal r3 = (com.google.ads.interactivemedia.v3.impl.zzal) r3
            java.util.List r13 = r3.zza()
            com.google.ads.interactivemedia.v3.impl.data.zzbd r7 = r10.type()
            int r7 = r7.ordinal()
            if (r7 == 0) goto L_0x00e4
            r8 = 2
            r9 = 1
            if (r7 == r9) goto L_0x0090
            if (r7 == r8) goto L_0x00e4
            r6 = 0
            goto L_0x00ee
        L_0x0090:
            java.util.concurrent.ExecutorService r7 = r0.zzc
            float r11 = r0.zzg
            com.google.ads.interactivemedia.v3.internal.zzfc r12 = new com.google.ads.interactivemedia.v3.internal.zzfc
            r12.<init>(r7, r11)
            android.content.Context r7 = r5.getContext()
            com.google.ads.interactivemedia.v3.impl.zzbi r11 = r0.zzd
            java.lang.String r14 = r0.zze
            java.lang.String r15 = r10.src()
            java.lang.String r6 = r10.size()
            if (r6 != 0) goto L_0x00ad
            r6 = 0
            goto L_0x00d4
        L_0x00ad:
            java.lang.String r9 = "x"
            r8 = -1
            java.lang.String[] r6 = r6.split(r9, r8)
            int r8 = r6.length
            r9 = 2
            if (r8 == r9) goto L_0x00bf
            com.google.ads.interactivemedia.v3.impl.data.zzbo r6 = new com.google.ads.interactivemedia.v3.impl.data.zzbo
            r8 = 0
            r6.<init>(r8, r8)
            goto L_0x00d4
        L_0x00bf:
            r8 = 0
            com.google.ads.interactivemedia.v3.impl.data.zzbo r9 = new com.google.ads.interactivemedia.v3.impl.data.zzbo
            r8 = r6[r8]
            int r8 = java.lang.Integer.parseInt(r8)
            r16 = 1
            r6 = r6[r16]
            int r6 = java.lang.Integer.parseInt(r6)
            r9.<init>(r8, r6)
            r6 = r9
        L_0x00d4:
            com.google.android.gms.tasks.Task r6 = r12.zzb(r15, r6)
            com.google.ads.interactivemedia.v3.internal.zzgi r15 = r0.zzf
            r8 = r7
            r9 = r11
            r11 = r6
            r12 = r14
            r14 = r15
            com.google.ads.interactivemedia.v3.impl.zzax r6 = com.google.ads.interactivemedia.v3.impl.zzax.zza(r8, r9, r10, r11, r12, r13, r14)
            goto L_0x00ee
        L_0x00e4:
            android.content.Context r6 = r5.getContext()
            com.google.ads.interactivemedia.v3.internal.zzgi r7 = r0.zzf
            com.google.ads.interactivemedia.v3.impl.zzap r6 = com.google.ads.interactivemedia.v3.impl.zzap.zza(r6, r10, r13, r7)
        L_0x00ee:
            if (r6 == 0) goto L_0x004e
            java.lang.String r7 = r0.zze
            r6.setTag(r7)
            java.lang.String r7 = r0.zze
            r3.zzb(r7)
            r5.addView(r6)
            goto L_0x004e
        L_0x00ff:
            return
        L_0x0100:
            r17.zzb()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.impl.zzam.zza(com.google.ads.interactivemedia.v3.impl.data.zzbu):void");
    }
}
