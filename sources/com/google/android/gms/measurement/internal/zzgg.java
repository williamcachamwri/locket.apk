package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.measurement.internal.zzje;
import java.math.BigInteger;
import java.util.List;
import java.util.Locale;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public final class zzgg extends zzh {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private long zze;
    private long zzf;
    private List<String> zzg;
    private String zzh;
    private int zzi;
    private String zzj;
    private String zzk;
    private String zzl;
    private long zzm = 0;
    private String zzn = null;

    /* access modifiers changed from: package-private */
    public final int zzaa() {
        zzu();
        return this.zzi;
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int zzab() {
        zzu();
        return this.zzc;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0160  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x01bd  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x01c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.measurement.internal.zzo zza(java.lang.String r52) {
        /*
            r51 = this;
            r0 = r51
            r51.zzt()
            com.google.android.gms.measurement.internal.zzo r42 = new com.google.android.gms.measurement.internal.zzo
            java.lang.String r2 = r51.zzad()
            java.lang.String r3 = r51.zzae()
            r51.zzu()
            java.lang.String r4 = r0.zzb
            int r1 = r51.zzab()
            long r5 = (long) r1
            r51.zzu()
            java.lang.String r1 = r0.zzd
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)
            java.lang.String r7 = r0.zzd
            r51.zzu()
            r51.zzt()
            long r10 = r0.zze
            r12 = 0
            int r1 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r1 != 0) goto L_0x0049
            com.google.android.gms.measurement.internal.zzhy r1 = r0.zzu
            com.google.android.gms.measurement.internal.zzos r1 = r1.zzt()
            android.content.Context r10 = r51.zza()
            android.content.Context r11 = r51.zza()
            java.lang.String r11 = r11.getPackageName()
            long r10 = r1.zza((android.content.Context) r10, (java.lang.String) r11)
            r0.zze = r10
        L_0x0049:
            long r10 = r0.zze
            com.google.android.gms.measurement.internal.zzhy r1 = r0.zzu
            boolean r14 = r1.zzac()
            com.google.android.gms.measurement.internal.zzha r1 = r51.zzk()
            boolean r1 = r1.zzm
            r15 = 1
            r16 = r1 ^ 1
            r51.zzt()
            com.google.android.gms.measurement.internal.zzhy r1 = r0.zzu
            boolean r1 = r1.zzac()
            r17 = 0
            if (r1 != 0) goto L_0x006a
            r18 = r17
            goto L_0x0070
        L_0x006a:
            java.lang.String r1 = r51.zzah()
            r18 = r1
        L_0x0070:
            r19 = 0
            com.google.android.gms.measurement.internal.zzhy r1 = r0.zzu
            com.google.android.gms.measurement.internal.zzha r8 = r1.zzn()
            com.google.android.gms.measurement.internal.zzhb r8 = r8.zzc
            long r8 = r8.zza()
            int r23 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r23 != 0) goto L_0x0085
            long r8 = r1.zza
            goto L_0x008b
        L_0x0085:
            long r12 = r1.zza
            long r8 = java.lang.Math.min(r12, r8)
        L_0x008b:
            r25 = r8
            int r27 = r51.zzaa()
            com.google.android.gms.measurement.internal.zzag r1 = r51.zze()
            boolean r28 = r1.zzv()
            com.google.android.gms.measurement.internal.zzha r1 = r51.zzk()
            r1.zzt()
            android.content.SharedPreferences r1 = r1.zzg()
            java.lang.String r8 = "deferred_analytics_collection"
            r9 = 0
            boolean r29 = r1.getBoolean(r8, r9)
            java.lang.String r30 = r51.zzac()
            com.google.android.gms.measurement.internal.zzag r1 = r51.zze()
            java.lang.String r8 = "google_analytics_default_allow_ad_personalization_signals"
            java.lang.Boolean r1 = r1.zze(r8)
            if (r1 != 0) goto L_0x00be
            r31 = r17
            goto L_0x00c9
        L_0x00be:
            boolean r1 = r1.booleanValue()
            r1 = r1 ^ r15
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r31 = r1
        L_0x00c9:
            long r12 = r0.zzf
            java.util.List<java.lang.String> r1 = r0.zzg
            r32 = 0
            com.google.android.gms.measurement.internal.zzha r33 = r51.zzk()
            com.google.android.gms.measurement.internal.zzje r33 = r33.zzo()
            java.lang.String r33 = r33.zzf()
            java.lang.String r9 = r0.zzh
            if (r9 != 0) goto L_0x00e9
            com.google.android.gms.measurement.internal.zzos r9 = r51.zzq()
            java.lang.String r9 = r9.zzp()
            r0.zzh = r9
        L_0x00e9:
            java.lang.String r9 = r0.zzh
            boolean r35 = com.google.android.gms.internal.measurement.zznm.zza()
            if (r35 == 0) goto L_0x0118
            com.google.android.gms.measurement.internal.zzag r15 = r51.zze()
            r36 = r1
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r1 = com.google.android.gms.measurement.internal.zzbh.zzcx
            boolean r1 = r15.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r1)
            if (r1 == 0) goto L_0x011a
            com.google.android.gms.measurement.internal.zzha r1 = r51.zzk()
            com.google.android.gms.measurement.internal.zzje r1 = r1.zzo()
            com.google.android.gms.measurement.internal.zzje$zza r15 = com.google.android.gms.measurement.internal.zzje.zza.ANALYTICS_STORAGE
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzje.zza) r15)
            if (r1 != 0) goto L_0x011a
            r37 = r12
            r39 = r17
            r23 = 0
            r17 = r14
            goto L_0x0154
        L_0x0118:
            r36 = r1
        L_0x011a:
            r51.zzt()
            r37 = r12
            long r12 = r0.zzm
            r23 = 0
            int r1 = (r12 > r23 ? 1 : (r12 == r23 ? 0 : -1))
            if (r1 == 0) goto L_0x0147
            com.google.android.gms.common.util.Clock r1 = r51.zzb()
            long r12 = r1.currentTimeMillis()
            r17 = r14
            long r14 = r0.zzm
            long r12 = r12 - r14
            java.lang.String r1 = r0.zzl
            if (r1 == 0) goto L_0x0149
            r14 = 86400000(0x5265c00, double:4.2687272E-316)
            int r1 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r1 <= 0) goto L_0x0149
            java.lang.String r1 = r0.zzn
            if (r1 != 0) goto L_0x0149
            r51.zzag()
            goto L_0x0149
        L_0x0147:
            r17 = r14
        L_0x0149:
            java.lang.String r1 = r0.zzl
            if (r1 != 0) goto L_0x0150
            r51.zzag()
        L_0x0150:
            java.lang.String r1 = r0.zzl
            r39 = r1
        L_0x0154:
            com.google.android.gms.measurement.internal.zzag r1 = r51.zze()
            java.lang.String r12 = "google_analytics_sgtm_upload_enabled"
            java.lang.Boolean r1 = r1.zze(r12)
            if (r1 != 0) goto L_0x0163
            r43 = 0
            goto L_0x0169
        L_0x0163:
            boolean r1 = r1.booleanValue()
            r43 = r1
        L_0x0169:
            com.google.android.gms.measurement.internal.zzos r1 = r51.zzq()
            java.lang.String r12 = r51.zzad()
            long r44 = r1.zzc(r12)
            com.google.android.gms.measurement.internal.zzha r1 = r51.zzk()
            com.google.android.gms.measurement.internal.zzje r1 = r1.zzo()
            int r46 = r1.zza()
            com.google.android.gms.measurement.internal.zzha r1 = r51.zzk()
            com.google.android.gms.measurement.internal.zzax r1 = r1.zzn()
            java.lang.String r47 = r1.zzf()
            boolean r1 = com.google.android.gms.internal.measurement.zzpn.zza()
            if (r1 == 0) goto L_0x01a9
            com.google.android.gms.measurement.internal.zzag r1 = r51.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzbh.zzci
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r12)
            if (r1 == 0) goto L_0x01a9
            r51.zzq()
            int r1 = com.google.android.gms.measurement.internal.zzos.zzc()
            r48 = r1
            goto L_0x01ab
        L_0x01a9:
            r48 = 0
        L_0x01ab:
            boolean r1 = com.google.android.gms.internal.measurement.zzpn.zza()
            if (r1 == 0) goto L_0x01c8
            com.google.android.gms.measurement.internal.zzag r1 = r51.zze()
            com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean> r12 = com.google.android.gms.measurement.internal.zzbh.zzci
            boolean r1 = r1.zza((com.google.android.gms.measurement.internal.zzfz<java.lang.Boolean>) r12)
            if (r1 == 0) goto L_0x01c8
            com.google.android.gms.measurement.internal.zzos r1 = r51.zzq()
            long r12 = r1.zzm()
            r49 = r12
            goto L_0x01ca
        L_0x01c8:
            r49 = r23
        L_0x01ca:
            com.google.android.gms.measurement.internal.zzag r1 = r51.zze()
            java.lang.String r40 = r1.zzu()
            com.google.android.gms.measurement.internal.zzag r1 = r51.zze()
            r12 = 1
            com.google.android.gms.measurement.internal.zzjh r1 = r1.zzc((java.lang.String) r8, (boolean) r12)
            com.google.android.gms.measurement.internal.zzf r8 = new com.google.android.gms.measurement.internal.zzf
            r8.<init>(r1)
            java.lang.String r41 = r8.zzb()
            r34 = r36
            r1 = r42
            r35 = r9
            r8 = 106000(0x19e10, double:5.2371E-319)
            r36 = r37
            r12 = r52
            r13 = r17
            r14 = r16
            r15 = r18
            r16 = r19
            r18 = r25
            r20 = r27
            r21 = r28
            r22 = r29
            r23 = r30
            r24 = r31
            r25 = r36
            r27 = r34
            r28 = r32
            r29 = r33
            r30 = r35
            r31 = r39
            r32 = r43
            r33 = r44
            r35 = r46
            r36 = r47
            r37 = r48
            r38 = r49
            r1.<init>((java.lang.String) r2, (java.lang.String) r3, (java.lang.String) r4, (long) r5, (java.lang.String) r7, (long) r8, (long) r10, (java.lang.String) r12, (boolean) r13, (boolean) r14, (java.lang.String) r15, (long) r16, (long) r18, (int) r20, (boolean) r21, (boolean) r22, (java.lang.String) r23, (java.lang.Boolean) r24, (long) r25, (java.util.List<java.lang.String>) r27, (java.lang.String) r28, (java.lang.String) r29, (java.lang.String) r30, (java.lang.String) r31, (boolean) r32, (long) r33, (int) r35, (java.lang.String) r36, (int) r37, (long) r38, (java.lang.String) r40, (java.lang.String) r41)
            return r42
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgg.zza(java.lang.String):com.google.android.gms.measurement.internal.zzo");
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzaz zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzgg zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzgf zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgo zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzha zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhv zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzjq zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzlj zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzls zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznb zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzos zzq() {
        return super.zzq();
    }

    /* access modifiers changed from: package-private */
    public final String zzac() {
        zzu();
        return this.zzk;
    }

    /* access modifiers changed from: package-private */
    public final String zzad() {
        zzu();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    private final String zzah() {
        if (!zzpz.zza() || !zze().zza(zzbh.zzbr)) {
            try {
                Class<?> loadClass = zza().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                if (loadClass == null) {
                    return null;
                }
                try {
                    Object invoke = loadClass.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke((Object) null, new Object[]{zza()});
                    if (invoke == null) {
                        return null;
                    }
                    try {
                        return (String) loadClass.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(invoke, new Object[0]);
                    } catch (Exception unused) {
                        zzj().zzv().zza("Failed to retrieve Firebase Instance Id");
                        return null;
                    }
                } catch (Exception unused2) {
                    zzj().zzw().zza("Failed to obtain Firebase Analytics instance");
                    return null;
                }
            } catch (ClassNotFoundException unused3) {
                return null;
            }
        } else {
            zzj().zzp().zza("Disabled IID for tests.");
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzae() {
        zzt();
        zzu();
        Preconditions.checkNotNull(this.zzj);
        return this.zzj;
    }

    /* access modifiers changed from: package-private */
    public final List<String> zzaf() {
        return this.zzg;
    }

    zzgg(zzhy zzhy, long j) {
        super(zzhy);
        this.zzf = j;
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0130  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x015d  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0187 A[Catch:{ IllegalStateException -> 0x01c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0188 A[Catch:{ IllegalStateException -> 0x01c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0191 A[Catch:{ IllegalStateException -> 0x01c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01aa A[Catch:{ IllegalStateException -> 0x01c7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01e8  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0220  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x022b  */
    @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzx() {
        /*
            r11 = this;
            android.content.Context r0 = r11.zza()
            java.lang.String r0 = r0.getPackageName()
            android.content.Context r1 = r11.zza()
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            java.lang.String r2 = ""
            r3 = 0
            java.lang.String r4 = "unknown"
            java.lang.String r5 = "Unknown"
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 != 0) goto L_0x002d
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzg()
            java.lang.String r8 = "PackageManager is null, app identity information might be inaccurate. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r0)
            r7.zza(r8, r9)
            goto L_0x008c
        L_0x002d:
            java.lang.String r4 = r1.getInstallerPackageName(r0)     // Catch:{ IllegalArgumentException -> 0x0032 }
            goto L_0x0043
        L_0x0032:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzg()
            java.lang.String r8 = "Error retrieving app installer package name. appId"
            java.lang.Object r9 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r0)
            r7.zza(r8, r9)
        L_0x0043:
            if (r4 != 0) goto L_0x0048
            java.lang.String r4 = "manual_install"
            goto L_0x0051
        L_0x0048:
            java.lang.String r7 = "com.android.vending"
            boolean r7 = r7.equals(r4)
            if (r7 == 0) goto L_0x0051
            r4 = r2
        L_0x0051:
            android.content.Context r7 = r11.zza()     // Catch:{ NameNotFoundException -> 0x0079 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0079 }
            android.content.pm.PackageInfo r7 = r1.getPackageInfo(r7, r3)     // Catch:{ NameNotFoundException -> 0x0079 }
            if (r7 == 0) goto L_0x008c
            android.content.pm.ApplicationInfo r8 = r7.applicationInfo     // Catch:{ NameNotFoundException -> 0x0079 }
            java.lang.CharSequence r8 = r1.getApplicationLabel(r8)     // Catch:{ NameNotFoundException -> 0x0079 }
            boolean r9 = android.text.TextUtils.isEmpty(r8)     // Catch:{ NameNotFoundException -> 0x0079 }
            if (r9 != 0) goto L_0x0070
            java.lang.String r8 = r8.toString()     // Catch:{ NameNotFoundException -> 0x0079 }
            goto L_0x0071
        L_0x0070:
            r8 = r5
        L_0x0071:
            java.lang.String r5 = r7.versionName     // Catch:{ NameNotFoundException -> 0x0076 }
            int r6 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x0076 }
            goto L_0x008c
        L_0x0076:
            r7 = r5
            r5 = r8
            goto L_0x007a
        L_0x0079:
            r7 = r5
        L_0x007a:
            com.google.android.gms.measurement.internal.zzgo r8 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r8 = r8.zzg()
            java.lang.String r9 = "Error retrieving package info. appId, appName"
            java.lang.Object r10 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r0)
            r8.zza(r9, r10, r5)
            r5 = r7
        L_0x008c:
            r11.zza = r0
            r11.zzd = r4
            r11.zzb = r5
            r11.zzc = r6
            r4 = 0
            r11.zze = r4
            com.google.android.gms.measurement.internal.zzhy r4 = r11.zzu
            java.lang.String r4 = r4.zzu()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            r5 = 1
            if (r4 != 0) goto L_0x00b5
            com.google.android.gms.measurement.internal.zzhy r4 = r11.zzu
            java.lang.String r4 = r4.zzv()
            java.lang.String r6 = "am"
            boolean r4 = r6.equals(r4)
            if (r4 == 0) goto L_0x00b5
            r4 = r5
            goto L_0x00b6
        L_0x00b5:
            r4 = r3
        L_0x00b6:
            com.google.android.gms.measurement.internal.zzhy r6 = r11.zzu
            int r6 = r6.zzc()
            switch(r6) {
                case 0: goto L_0x014c;
                case 1: goto L_0x013e;
                case 2: goto L_0x0130;
                case 3: goto L_0x0122;
                case 4: goto L_0x0114;
                case 5: goto L_0x0106;
                case 6: goto L_0x00f8;
                case 7: goto L_0x00ea;
                case 8: goto L_0x00db;
                default: goto L_0x00bf;
            }
        L_0x00bf:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzo()
            java.lang.String r8 = "App measurement disabled"
            r7.zza(r8)
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzn()
            java.lang.String r8 = "Invalid scion state in identity"
            r7.zza(r8)
            goto L_0x0159
        L_0x00db:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzo()
            java.lang.String r8 = "App measurement disabled due to denied storage consent"
            r7.zza(r8)
            goto L_0x0159
        L_0x00ea:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzo()
            java.lang.String r8 = "App measurement disabled via the global data collection setting"
            r7.zza(r8)
            goto L_0x0159
        L_0x00f8:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzv()
            java.lang.String r8 = "App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics"
            r7.zza(r8)
            goto L_0x0159
        L_0x0106:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzp()
            java.lang.String r8 = "App measurement disabled via the init parameters"
            r7.zza(r8)
            goto L_0x0159
        L_0x0114:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzo()
            java.lang.String r8 = "App measurement disabled via the manifest"
            r7.zza(r8)
            goto L_0x0159
        L_0x0122:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzo()
            java.lang.String r8 = "App measurement disabled by setAnalyticsCollectionEnabled(false)"
            r7.zza(r8)
            goto L_0x0159
        L_0x0130:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzp()
            java.lang.String r8 = "App measurement deactivated via the init parameters"
            r7.zza(r8)
            goto L_0x0159
        L_0x013e:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzo()
            java.lang.String r8 = "App measurement deactivated via the manifest"
            r7.zza(r8)
            goto L_0x0159
        L_0x014c:
            com.google.android.gms.measurement.internal.zzgo r7 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r7 = r7.zzp()
            java.lang.String r8 = "App measurement collection enabled"
            r7.zza(r8)
        L_0x0159:
            if (r6 != 0) goto L_0x015d
            r6 = r5
            goto L_0x015e
        L_0x015d:
            r6 = r3
        L_0x015e:
            r11.zzj = r2
            r11.zzk = r2
            if (r4 == 0) goto L_0x016c
            com.google.android.gms.measurement.internal.zzhy r4 = r11.zzu
            java.lang.String r4 = r4.zzu()
            r11.zzk = r4
        L_0x016c:
            android.content.Context r4 = r11.zza()     // Catch:{ IllegalStateException -> 0x01c7 }
            com.google.android.gms.measurement.internal.zzhy r7 = r11.zzu     // Catch:{ IllegalStateException -> 0x01c7 }
            java.lang.String r7 = r7.zzx()     // Catch:{ IllegalStateException -> 0x01c7 }
            java.lang.String r8 = "google_app_id"
            com.google.android.gms.measurement.internal.zzhs r9 = new com.google.android.gms.measurement.internal.zzhs     // Catch:{ IllegalStateException -> 0x01c7 }
            r9.<init>(r4, r7)     // Catch:{ IllegalStateException -> 0x01c7 }
            java.lang.String r4 = r9.zza((java.lang.String) r8)     // Catch:{ IllegalStateException -> 0x01c7 }
            boolean r7 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IllegalStateException -> 0x01c7 }
            if (r7 == 0) goto L_0x0188
            goto L_0x0189
        L_0x0188:
            r2 = r4
        L_0x0189:
            r11.zzj = r2     // Catch:{ IllegalStateException -> 0x01c7 }
            boolean r2 = android.text.TextUtils.isEmpty(r4)     // Catch:{ IllegalStateException -> 0x01c7 }
            if (r2 != 0) goto L_0x01a8
            com.google.android.gms.measurement.internal.zzhs r2 = new com.google.android.gms.measurement.internal.zzhs     // Catch:{ IllegalStateException -> 0x01c7 }
            android.content.Context r4 = r11.zza()     // Catch:{ IllegalStateException -> 0x01c7 }
            com.google.android.gms.measurement.internal.zzhy r7 = r11.zzu     // Catch:{ IllegalStateException -> 0x01c7 }
            java.lang.String r7 = r7.zzx()     // Catch:{ IllegalStateException -> 0x01c7 }
            r2.<init>(r4, r7)     // Catch:{ IllegalStateException -> 0x01c7 }
            java.lang.String r4 = "admob_app_id"
            java.lang.String r2 = r2.zza((java.lang.String) r4)     // Catch:{ IllegalStateException -> 0x01c7 }
            r11.zzk = r2     // Catch:{ IllegalStateException -> 0x01c7 }
        L_0x01a8:
            if (r6 == 0) goto L_0x01d9
            com.google.android.gms.measurement.internal.zzgo r2 = r11.zzj()     // Catch:{ IllegalStateException -> 0x01c7 }
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzp()     // Catch:{ IllegalStateException -> 0x01c7 }
            java.lang.String r4 = "App measurement enabled for app package, google app id"
            java.lang.String r6 = r11.zza     // Catch:{ IllegalStateException -> 0x01c7 }
            java.lang.String r7 = r11.zzj     // Catch:{ IllegalStateException -> 0x01c7 }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ IllegalStateException -> 0x01c7 }
            if (r7 == 0) goto L_0x01c1
            java.lang.String r7 = r11.zzk     // Catch:{ IllegalStateException -> 0x01c7 }
            goto L_0x01c3
        L_0x01c1:
            java.lang.String r7 = r11.zzj     // Catch:{ IllegalStateException -> 0x01c7 }
        L_0x01c3:
            r2.zza(r4, r6, r7)     // Catch:{ IllegalStateException -> 0x01c7 }
            goto L_0x01d9
        L_0x01c7:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzgo r4 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r4 = r4.zzg()
            java.lang.String r6 = "Fetching Google App Id failed with exception. appId"
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzgo.zza((java.lang.String) r0)
            r4.zza(r6, r0, r2)
        L_0x01d9:
            r0 = 0
            r11.zzg = r0
            com.google.android.gms.measurement.internal.zzag r0 = r11.zze()
            java.lang.String r2 = "analytics.safelisted_events"
            java.util.List r0 = r0.zzg(r2)
            if (r0 == 0) goto L_0x021a
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L_0x01fd
            com.google.android.gms.measurement.internal.zzgo r2 = r11.zzj()
            com.google.android.gms.measurement.internal.zzgq r2 = r2.zzv()
            java.lang.String r4 = "Safelisted event list is empty. Ignoring"
            r2.zza(r4)
        L_0x01fb:
            r5 = r3
            goto L_0x021a
        L_0x01fd:
            java.util.Iterator r2 = r0.iterator()
        L_0x0201:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x021a
            java.lang.Object r4 = r2.next()
            java.lang.String r4 = (java.lang.String) r4
            com.google.android.gms.measurement.internal.zzos r6 = r11.zzq()
            java.lang.String r7 = "safelisted event"
            boolean r4 = r6.zzb((java.lang.String) r7, (java.lang.String) r4)
            if (r4 != 0) goto L_0x0201
            goto L_0x01fb
        L_0x021a:
            if (r5 == 0) goto L_0x021e
            r11.zzg = r0
        L_0x021e:
            if (r1 == 0) goto L_0x022b
            android.content.Context r0 = r11.zza()
            boolean r0 = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r0)
            r11.zzi = r0
            return
        L_0x022b:
            r11.zzi = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgg.zzx():void");
    }

    /* access modifiers changed from: package-private */
    public final void zzag() {
        String str;
        zzt();
        if (!zzk().zzo().zza(zzje.zza.ANALYTICS_STORAGE)) {
            zzj().zzc().zza("Analytics Storage consent is not granted");
            str = null;
        } else {
            byte[] bArr = new byte[16];
            zzq().zzv().nextBytes(bArr);
            str = String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
        }
        zzgq zzc2 = zzj().zzc();
        Object[] objArr = new Object[1];
        objArr[0] = str == null ? "null" : "not null";
        zzc2.zza(String.format("Resetting session stitching token to %s", objArr));
        this.zzl = str;
        this.zzm = zzb().currentTimeMillis();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb(String str) {
        String str2 = this.zzn;
        boolean z = str2 != null && !str2.equals(str);
        this.zzn = str;
        return z;
    }
}
