package com.google.android.gms.internal.atv_ads_framework;

import com.google.android.gms.internal.atv_ads_framework.zzdf;
import com.google.android.gms.internal.atv_ads_framework.zzdh;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public class zzdf<MessageType extends zzdh<MessageType, BuilderType>, BuilderType extends zzdf<MessageType, BuilderType>> extends zzbz<MessageType, BuilderType> {
    protected zzdh zza;
    private final zzdh zzb;

    protected zzdf(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzA()) {
            this.zza = messagetype.zzq();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    /* renamed from: zzh */
    public final zzdf zzg() {
        zzdf zzdf = (zzdf) this.zzb.zze(5, (Object) null, (Object) null);
        zzdf.zza = zzk();
        return zzdf;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0036, code lost:
        if (r4 != false) goto L_0x0038;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final MessageType zzi() {
        /*
            r6 = this;
            com.google.android.gms.internal.atv_ads_framework.zzdh r0 = r6.zzk()
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            boolean r1 = r1.booleanValue()
            r2 = 1
            r3 = 0
            java.lang.Object r4 = r0.zze(r2, r3, r3)
            java.lang.Byte r4 = (java.lang.Byte) r4
            byte r4 = r4.byteValue()
            if (r4 != r2) goto L_0x0019
            goto L_0x0038
        L_0x0019:
            if (r4 == 0) goto L_0x0039
            com.google.android.gms.internal.atv_ads_framework.zzew r4 = com.google.android.gms.internal.atv_ads_framework.zzew.zza()
            java.lang.Class r5 = r0.getClass()
            com.google.android.gms.internal.atv_ads_framework.zzey r4 = r4.zzb(r5)
            boolean r4 = r4.zzh(r0)
            if (r1 == 0) goto L_0x0036
            if (r2 == r4) goto L_0x0031
            r1 = r3
            goto L_0x0032
        L_0x0031:
            r1 = r0
        L_0x0032:
            r2 = 2
            r0.zze(r2, r1, r3)
        L_0x0036:
            if (r4 == 0) goto L_0x0039
        L_0x0038:
            return r0
        L_0x0039:
            com.google.android.gms.internal.atv_ads_framework.zzfo r1 = new com.google.android.gms.internal.atv_ads_framework.zzfo
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzdf.zzi():com.google.android.gms.internal.atv_ads_framework.zzdh");
    }

    /* renamed from: zzj */
    public MessageType zzk() {
        if (!this.zza.zzA()) {
            return this.zza;
        }
        this.zza.zzv();
        return this.zza;
    }

    public final /* bridge */ /* synthetic */ zzeo zzl() {
        throw null;
    }

    /* access modifiers changed from: protected */
    public final void zzm() {
        if (!this.zza.zzA()) {
            zzn();
        }
    }

    /* access modifiers changed from: protected */
    public void zzn() {
        zzdh zzq = this.zzb.zzq();
        zzew.zza().zzb(zzq.getClass()).zze(zzq, this.zza);
        this.zza = zzq;
    }
}
