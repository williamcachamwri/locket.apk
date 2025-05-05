package com.google.ads.interactivemedia.pal;

import com.google.ads.interactivemedia.pal.NonceRequest;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzn extends NonceRequest {
    private final Boolean zza;
    private final Boolean zzb;
    private final Integer zzc;
    private final Integer zzd;
    private final Integer zze;
    private final Boolean zzf;
    private final Boolean zzg;
    private final String zzh;
    private final String zzi;
    private final String zzj;
    private final String zzk;
    private final String zzl;
    private final String zzm;
    private final String zzn;
    private final PlatformSignalCollector zzo;
    private final Set zzp;
    private final String zzq;

    /* synthetic */ zzn(Boolean bool, Boolean bool2, Integer num, Integer num2, Integer num3, Boolean bool3, Boolean bool4, String str, String str2, String str3, String str4, String str5, String str6, String str7, PlatformSignalCollector platformSignalCollector, Set set, String str8, zzm zzm2) {
        this.zza = bool;
        this.zzb = bool2;
        this.zzc = num;
        this.zzd = num2;
        this.zze = num3;
        this.zzf = bool3;
        this.zzg = bool4;
        this.zzh = str;
        this.zzi = str2;
        this.zzj = str3;
        this.zzk = str4;
        this.zzl = str5;
        this.zzm = str6;
        this.zzn = str7;
        this.zzo = platformSignalCollector;
        this.zzp = set;
        this.zzq = str8;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        r1 = r4.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0041, code lost:
        r1 = r4.zzd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0056, code lost:
        r1 = r4.zze;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006b, code lost:
        r1 = r4.zzf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0080, code lost:
        r1 = r4.zzg;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e9, code lost:
        r1 = r4.zzo;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.ads.interactivemedia.pal.NonceRequest
            r2 = 0
            if (r1 == 0) goto L_0x0118
            com.google.ads.interactivemedia.pal.NonceRequest r5 = (com.google.ads.interactivemedia.pal.NonceRequest) r5
            java.lang.Boolean r1 = r4.zza
            if (r1 != 0) goto L_0x0016
            java.lang.Boolean r1 = r5.zzb()
            if (r1 != 0) goto L_0x0118
            goto L_0x0020
        L_0x0016:
            java.lang.Boolean r3 = r5.zzb()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
        L_0x0020:
            java.lang.Boolean r1 = r4.zzb
            java.lang.Boolean r3 = r5.zzc()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
            java.lang.Integer r1 = r4.zzc
            if (r1 != 0) goto L_0x0037
            java.lang.Integer r1 = r5.zzf()
            if (r1 != 0) goto L_0x0118
            goto L_0x0041
        L_0x0037:
            java.lang.Integer r3 = r5.zzf()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
        L_0x0041:
            java.lang.Integer r1 = r4.zzd
            if (r1 != 0) goto L_0x004c
            java.lang.Integer r1 = r5.zzg()
            if (r1 != 0) goto L_0x0118
            goto L_0x0056
        L_0x004c:
            java.lang.Integer r3 = r5.zzg()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
        L_0x0056:
            java.lang.Integer r1 = r4.zze
            if (r1 != 0) goto L_0x0061
            java.lang.Integer r1 = r5.zzh()
            if (r1 != 0) goto L_0x0118
            goto L_0x006b
        L_0x0061:
            java.lang.Integer r3 = r5.zzh()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
        L_0x006b:
            java.lang.Boolean r1 = r4.zzf
            if (r1 != 0) goto L_0x0076
            java.lang.Boolean r1 = r5.zze()
            if (r1 != 0) goto L_0x0118
            goto L_0x0080
        L_0x0076:
            java.lang.Boolean r3 = r5.zze()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
        L_0x0080:
            java.lang.Boolean r1 = r4.zzg
            if (r1 != 0) goto L_0x008b
            java.lang.Boolean r1 = r5.zzd()
            if (r1 != 0) goto L_0x0118
            goto L_0x0095
        L_0x008b:
            java.lang.Boolean r3 = r5.zzd()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
        L_0x0095:
            java.lang.String r1 = r4.zzh
            java.lang.String r3 = r5.zzi()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
            java.lang.String r1 = r4.zzi
            java.lang.String r3 = r5.zzj()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
            java.lang.String r1 = r4.zzj
            java.lang.String r3 = r5.zzk()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
            java.lang.String r1 = r4.zzk
            java.lang.String r3 = r5.zzl()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
            java.lang.String r1 = r4.zzl
            java.lang.String r3 = r5.zzm()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
            java.lang.String r1 = r4.zzm
            java.lang.String r3 = r5.zzn()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
            java.lang.String r1 = r4.zzn
            java.lang.String r3 = r5.zzo()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
            com.google.ads.interactivemedia.pal.PlatformSignalCollector r1 = r4.zzo
            if (r1 != 0) goto L_0x00f4
            com.google.ads.interactivemedia.pal.PlatformSignalCollector r1 = r5.zza()
            if (r1 != 0) goto L_0x0118
            goto L_0x00ff
        L_0x00f4:
            com.google.ads.interactivemedia.pal.PlatformSignalCollector r3 = r5.zza()
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x00ff
            goto L_0x0118
        L_0x00ff:
            java.util.Set r1 = r4.zzp
            java.util.Set r3 = r5.zzq()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x0118
            java.lang.String r1 = r4.zzq
            java.lang.String r5 = r5.zzp()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x0118
            return r0
        L_0x0118:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.pal.zzn.equals(java.lang.Object):boolean");
    }

    public final NonceRequest.Builder toBuilder() {
        return new zzl(this, (zzk) null);
    }

    public final String toString() {
        Boolean bool = this.zza;
        Boolean bool2 = this.zzb;
        Integer num = this.zzc;
        Integer num2 = this.zzd;
        Integer num3 = this.zze;
        Boolean bool3 = this.zzf;
        Boolean bool4 = this.zzg;
        String str = this.zzh;
        String str2 = this.zzi;
        String str3 = this.zzj;
        String str4 = this.zzk;
        String str5 = this.zzl;
        String str6 = this.zzm;
        String str7 = this.zzn;
        String valueOf = String.valueOf(this.zzo);
        String obj = this.zzp.toString();
        return "NonceRequest{continuousPlayback=" + bool + ", iconsSupported=" + bool2 + ", nonceLengthLimit=" + num + ", videoPlayerHeight=" + num2 + ", videoPlayerWidth=" + num3 + ", willAdPlayMuted=" + bool3 + ", willAdAutoPlay=" + bool4 + ", descriptionURL=" + str + ", omidPartnerName=" + str2 + ", omidPartnerVersion=" + str3 + ", omidVersion=" + str4 + ", playerType=" + str5 + ", playerVersion=" + str6 + ", ppid=" + str7 + ", platformSignalCollector=" + valueOf + ", supportedApiFrameworks=" + obj + ", sessionId=" + this.zzq + "}";
    }

    public final PlatformSignalCollector zza() {
        return this.zzo;
    }

    public final Boolean zzb() {
        return this.zza;
    }

    public final Boolean zzc() {
        return this.zzb;
    }

    public final Boolean zzd() {
        return this.zzg;
    }

    public final Boolean zze() {
        return this.zzf;
    }

    public final Integer zzf() {
        return this.zzc;
    }

    public final Integer zzg() {
        return this.zzd;
    }

    public final Integer zzh() {
        return this.zze;
    }

    public final String zzi() {
        return this.zzh;
    }

    public final String zzj() {
        return this.zzi;
    }

    public final String zzk() {
        return this.zzj;
    }

    public final String zzl() {
        return this.zzk;
    }

    public final String zzm() {
        return this.zzl;
    }

    public final String zzn() {
        return this.zzm;
    }

    public final String zzo() {
        return this.zzn;
    }

    public final String zzp() {
        return this.zzq;
    }

    public final Set zzq() {
        return this.zzp;
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Boolean bool = this.zza;
        int i7 = 0;
        if (bool == null) {
            i = 0;
        } else {
            i = bool.hashCode();
        }
        int hashCode = (((i ^ 1000003) * 1000003) ^ this.zzb.hashCode()) * 1000003;
        Integer num = this.zzc;
        if (num == null) {
            i2 = 0;
        } else {
            i2 = num.hashCode();
        }
        int i8 = (hashCode ^ i2) * 1000003;
        Integer num2 = this.zzd;
        if (num2 == null) {
            i3 = 0;
        } else {
            i3 = num2.hashCode();
        }
        int i9 = (i8 ^ i3) * 1000003;
        Integer num3 = this.zze;
        if (num3 == null) {
            i4 = 0;
        } else {
            i4 = num3.hashCode();
        }
        int i10 = (i9 ^ i4) * 1000003;
        Boolean bool2 = this.zzf;
        if (bool2 == null) {
            i5 = 0;
        } else {
            i5 = bool2.hashCode();
        }
        int i11 = (i10 ^ i5) * 1000003;
        Boolean bool3 = this.zzg;
        if (bool3 == null) {
            i6 = 0;
        } else {
            i6 = bool3.hashCode();
        }
        int hashCode2 = (((((((((((((((i11 ^ i6) * 1000003) ^ this.zzh.hashCode()) * 1000003) ^ this.zzi.hashCode()) * 1000003) ^ this.zzj.hashCode()) * 1000003) ^ this.zzk.hashCode()) * 1000003) ^ this.zzl.hashCode()) * 1000003) ^ this.zzm.hashCode()) * 1000003) ^ this.zzn.hashCode()) * 1000003;
        PlatformSignalCollector platformSignalCollector = this.zzo;
        if (platformSignalCollector != null) {
            i7 = platformSignalCollector.hashCode();
        }
        return ((((hashCode2 ^ i7) * 1000003) ^ this.zzp.hashCode()) * 1000003) ^ this.zzq.hashCode();
    }
}
