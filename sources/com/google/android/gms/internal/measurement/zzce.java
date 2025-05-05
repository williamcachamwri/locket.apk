package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzce extends zzcm {
    private final String zzc;
    private final boolean zzd;
    private final zzco zze;
    private final zzcc zzf;
    private final zzcb zzg;
    private final zzcn zzh;

    /* synthetic */ zzce(String str, boolean z, zzco zzco, zzcc zzcc, zzcb zzcb, zzcn zzcn, zzcg zzcg) {
        this(str, false, zzco, (zzcc) null, (zzcb) null, zzcn);
    }

    public final int hashCode() {
        int hashCode = (((((this.zzc.hashCode() ^ 1000003) * 1000003) ^ (this.zzd ? 1231 : 1237)) * 1000003) ^ this.zze.hashCode()) * 1000003;
        zzcc zzcc = this.zzf;
        int i = 0;
        int hashCode2 = (hashCode ^ (zzcc == null ? 0 : zzcc.hashCode())) * 1000003;
        zzcb zzcb = this.zzg;
        if (zzcb != null) {
            i = zzcb.hashCode();
        }
        return ((hashCode2 ^ i) * 1000003) ^ this.zzh.hashCode();
    }

    public final zzcc zza() {
        return this.zzf;
    }

    public final zzcb zzb() {
        return this.zzg;
    }

    public final zzco zzc() {
        return this.zze;
    }

    public final zzcn zzd() {
        return this.zzh;
    }

    public final String zze() {
        return this.zzc;
    }

    public final String toString() {
        String str = this.zzc;
        boolean z = this.zzd;
        String valueOf = String.valueOf(this.zze);
        String valueOf2 = String.valueOf(this.zzf);
        String valueOf3 = String.valueOf(this.zzg);
        return "FileComplianceOptions{fileOwner=" + str + ", hasDifferentDmaOwner=" + z + ", fileChecks=" + valueOf + ", dataForwardingNotAllowedResolver=" + valueOf2 + ", multipleProductIdGroupsResolver=" + valueOf3 + ", filePurpose=" + String.valueOf(this.zzh) + "}";
    }

    private zzce(String str, boolean z, zzco zzco, zzcc zzcc, zzcb zzcb, zzcn zzcn) {
        this.zzc = str;
        this.zzd = z;
        this.zze = zzco;
        this.zzf = null;
        this.zzg = null;
        this.zzh = zzcn;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002b, code lost:
        r1 = r4.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0040, code lost:
        r1 = r4.zzg;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.gms.internal.measurement.zzcm
            r2 = 0
            if (r1 == 0) goto L_0x0062
            com.google.android.gms.internal.measurement.zzcm r5 = (com.google.android.gms.internal.measurement.zzcm) r5
            java.lang.String r1 = r4.zzc
            java.lang.String r3 = r5.zze()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0062
            boolean r1 = r4.zzd
            boolean r3 = r5.zzf()
            if (r1 != r3) goto L_0x0062
            com.google.android.gms.internal.measurement.zzco r1 = r4.zze
            com.google.android.gms.internal.measurement.zzco r3 = r5.zzc()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0062
            com.google.android.gms.internal.measurement.zzcc r1 = r4.zzf
            if (r1 != 0) goto L_0x0036
            com.google.android.gms.internal.measurement.zzcc r1 = r5.zza()
            if (r1 != 0) goto L_0x0062
            goto L_0x0040
        L_0x0036:
            com.google.android.gms.internal.measurement.zzcc r3 = r5.zza()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0062
        L_0x0040:
            com.google.android.gms.internal.measurement.zzcb r1 = r4.zzg
            if (r1 != 0) goto L_0x004b
            com.google.android.gms.internal.measurement.zzcb r1 = r5.zzb()
            if (r1 != 0) goto L_0x0062
            goto L_0x0055
        L_0x004b:
            com.google.android.gms.internal.measurement.zzcb r3 = r5.zzb()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0062
        L_0x0055:
            com.google.android.gms.internal.measurement.zzcn r1 = r4.zzh
            com.google.android.gms.internal.measurement.zzcn r5 = r5.zzd()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x0062
            return r0
        L_0x0062:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzce.equals(java.lang.Object):boolean");
    }

    public final boolean zzf() {
        return this.zzd;
    }
}
