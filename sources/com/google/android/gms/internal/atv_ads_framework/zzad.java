package com.google.android.gms.internal.atv_ads_framework;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.content.pm.PackageInfoCompat;
import com.amplitude.analytics.BuildConfig;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public final class zzad {
    public static zza zza(Context context) {
        if (context.getPackageManager().hasSystemFeature("com.google.android.feature.AMATI_EXPERIENCE")) {
            return zza.LAUNCHER_X;
        }
        if (context.getPackageManager().hasSystemFeature("amazon.hardware.fire_tv")) {
            return zza.FIRE_TV;
        }
        if (context.getPackageManager().hasSystemFeature("com.google.android.tv.custom_launcher") && zzg(context, "com.google.android.tvrecommendations")) {
            return zza.CUSTOM;
        }
        if (context.getPackageManager().hasSystemFeature("android.software.leanback") && zzg(context, "com.google.android.tvlauncher")) {
            return zza.TV_LAUNCHER;
        }
        if (!context.getPackageManager().hasSystemFeature("android.software.leanback") || !zzg(context, "com.google.android.leanbacklauncher")) {
            return zza.UNKNOWN;
        }
        return zza.LEANBACK;
    }

    public static zzab zzb(Context context) {
        String str;
        String str2;
        zzt zza = zzu.zza();
        zza.zza(context.getApplicationContext().getPackageName());
        String zze = zze(context.getApplicationContext().getPackageName(), context);
        if (!zze.isEmpty()) {
            zza.zzb(zze);
        }
        zzj zza2 = zzk.zza();
        zza2.zza(zzd());
        zza2.zzb(zzf(context));
        zzk zzk = (zzk) zza2.zzi();
        zzr zza3 = zzs.zza();
        zza3.zza(BuildConfig.VERSION_NAME);
        zzs zzs = (zzs) zza3.zzi();
        zza zza4 = zza(context);
        zzo zza5 = zzp.zza();
        zza zza6 = zza.TV_LAUNCHER;
        int ordinal = zza4.ordinal();
        if (ordinal != 0) {
            str2 = "";
            if (ordinal == 1) {
                zza5.zzc(3);
                String str3 = str2;
                str2 = zze("com.google.android.apps.tv.launcherx", context);
                str = str3;
            } else if (ordinal != 2) {
                if (ordinal == 3) {
                    zza5.zzc(5);
                } else if (ordinal == 4) {
                    zza5.zzc(6);
                } else if (ordinal == 5) {
                    zza5.zzc(1);
                }
                str = str2;
            } else {
                zza5.zzc(4);
                str = zze("com.google.android.tvrecommendations", context);
            }
        } else {
            zza5.zzc(2);
            str2 = zze("com.google.android.tvlauncher", context);
            str = zze("com.google.android.tvrecommendations", context);
        }
        if (!str2.isEmpty()) {
            zza5.zzb(str2);
        }
        if (!str.isEmpty()) {
            zza5.zza(str);
        }
        zzab zza7 = zzac.zza();
        zza7.zzd(zza);
        zza7.zza(zzk);
        zza7.zze(zzs);
        zza7.zzc(zza5);
        return zza7;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x005a, code lost:
        if (r2 != 2) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        throw r2;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:56:0x0142 */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00c0 A[Catch:{ all -> 0x0127, all -> 0x012c, zzax -> 0x0143 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ec A[Catch:{ all -> 0x0127, all -> 0x012c, zzax -> 0x0143 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f1 A[Catch:{ all -> 0x0127, all -> 0x012c, zzax -> 0x0143 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.atv_ads_framework.zzbh zzc(android.content.Context r13) {
        /*
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            com.google.android.gms.internal.atv_ads_framework.zzbg r1 = new com.google.android.gms.internal.atv_ads_framework.zzbg
            r1.<init>()
            java.lang.String r2 = zzd()
            boolean r3 = r2.isEmpty()
            if (r3 == 0) goto L_0x001a
            com.google.android.gms.internal.atv_ads_framework.zzx r2 = com.google.android.gms.internal.atv_ads_framework.zzx.SIGNAL_COLLECTION_ERROR_BUILD_FINGERPRINT_PREFIX
            r0.add(r2)
            goto L_0x0023
        L_0x001a:
            com.google.android.gms.internal.atv_ads_framework.zzb r3 = com.google.android.gms.internal.atv_ads_framework.zzb.BUILD_FINGERPRINT_PREFIX
            java.lang.String r3 = r3.zza()
            r1.zza(r3, r2)
        L_0x0023:
            com.google.android.gms.internal.atv_ads_framework.zza r2 = zza(r13)
            com.google.android.gms.internal.atv_ads_framework.zzb r3 = com.google.android.gms.internal.atv_ads_framework.zzb.LAUNCHER_TYPE
            java.lang.String r3 = r3.zza()
            java.lang.String r4 = r2.zza()
            r1.zza(r3, r4)
            boolean r3 = zzf(r13)
            if (r3 == 0) goto L_0x0045
            com.google.android.gms.internal.atv_ads_framework.zzb r3 = com.google.android.gms.internal.atv_ads_framework.zzb.OPERATOR_TIER
            java.lang.String r3 = r3.zza()
            java.lang.String r4 = "1"
            r1.zza(r3, r4)
        L_0x0045:
            com.google.android.gms.internal.atv_ads_framework.zzb r3 = com.google.android.gms.internal.atv_ads_framework.zzb.SDK_VERSION
            java.lang.String r3 = r3.zza()
            java.lang.String r4 = "1.0.0"
            r1.zza(r3, r4)
            int r2 = r2.ordinal()
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0079
            if (r2 == r4) goto L_0x005d
            if (r2 == r3) goto L_0x0094
            goto L_0x00af
        L_0x005d:
            java.lang.String r2 = "com.google.android.apps.tv.launcherx"
            java.lang.String r2 = zze(r2, r13)
            boolean r5 = r2.isEmpty()
            if (r5 == 0) goto L_0x006f
            com.google.android.gms.internal.atv_ads_framework.zzx r2 = com.google.android.gms.internal.atv_ads_framework.zzx.SIGNAL_COLLECTION_ERROR_NAME_NOT_FOUND
            r0.add(r2)
            goto L_0x00af
        L_0x006f:
            com.google.android.gms.internal.atv_ads_framework.zzb r5 = com.google.android.gms.internal.atv_ads_framework.zzb.LAUNCHERX_VERSION_CODE
            java.lang.String r5 = r5.zza()
            r1.zza(r5, r2)
            goto L_0x00af
        L_0x0079:
            java.lang.String r2 = "com.google.android.tvlauncher"
            java.lang.String r2 = zze(r2, r13)
            boolean r5 = r2.isEmpty()
            if (r5 == 0) goto L_0x008b
            com.google.android.gms.internal.atv_ads_framework.zzx r2 = com.google.android.gms.internal.atv_ads_framework.zzx.SIGNAL_COLLECTION_ERROR_NAME_NOT_FOUND
            r0.add(r2)
            goto L_0x0094
        L_0x008b:
            com.google.android.gms.internal.atv_ads_framework.zzb r5 = com.google.android.gms.internal.atv_ads_framework.zzb.TVLAUNCHER_VERSION_CODE
            java.lang.String r5 = r5.zza()
            r1.zza(r5, r2)
        L_0x0094:
            java.lang.String r2 = "com.google.android.tvrecommendations"
            java.lang.String r2 = zze(r2, r13)
            boolean r5 = r2.isEmpty()
            if (r5 == 0) goto L_0x00a6
            com.google.android.gms.internal.atv_ads_framework.zzx r2 = com.google.android.gms.internal.atv_ads_framework.zzx.SIGNAL_COLLECTION_ERROR_NAME_NOT_FOUND
            r0.add(r2)
            goto L_0x00af
        L_0x00a6:
            com.google.android.gms.internal.atv_ads_framework.zzb r5 = com.google.android.gms.internal.atv_ads_framework.zzb.TVRECOMMENDATIONS_VERSION_CODE
            java.lang.String r5 = r5.zza()
            r1.zza(r5, r2)
        L_0x00af:
            com.google.android.gms.internal.atv_ads_framework.zzbg r2 = new com.google.android.gms.internal.atv_ads_framework.zzbg     // Catch:{ zzax -> 0x0143 }
            r2.<init>()     // Catch:{ zzax -> 0x0143 }
            android.net.Uri r5 = android.net.Uri.EMPTY     // Catch:{ zzax -> 0x0143 }
            com.google.android.gms.internal.atv_ads_framework.zza r6 = zza(r13)     // Catch:{ zzax -> 0x0143 }
            int r6 = r6.ordinal()     // Catch:{ zzax -> 0x0143 }
            if (r6 == 0) goto L_0x00d7
            if (r6 == r4) goto L_0x00d4
            if (r6 == r3) goto L_0x00d7
            r3 = 3
            if (r6 == r3) goto L_0x00cf
            r3 = 4
            if (r6 == r3) goto L_0x00cf
            r3 = 5
            if (r6 == r3) goto L_0x00cf
        L_0x00cd:
            r7 = r5
            goto L_0x00da
        L_0x00cf:
            com.google.android.gms.internal.atv_ads_framework.zzbh r2 = r2.zzc()     // Catch:{ zzax -> 0x0143 }
            goto L_0x011f
        L_0x00d4:
            android.net.Uri r5 = com.google.android.gms.internal.atv_ads_framework.zzd.zza     // Catch:{ zzax -> 0x0143 }
            goto L_0x00cd
        L_0x00d7:
            android.net.Uri r5 = com.google.android.gms.internal.atv_ads_framework.zzd.zzb     // Catch:{ zzax -> 0x0143 }
            goto L_0x00cd
        L_0x00da:
            android.content.pm.PackageManager r3 = r13.getPackageManager()     // Catch:{ zzax -> 0x0143 }
            java.lang.String r5 = r7.getAuthority()     // Catch:{ zzax -> 0x0143 }
            com.google.android.gms.internal.atv_ads_framework.zzaw.zza(r5)     // Catch:{ zzax -> 0x0143 }
            r12 = 0
            android.content.pm.ProviderInfo r3 = r3.resolveContentProvider(r5, r12)     // Catch:{ zzax -> 0x0143 }
            if (r3 != 0) goto L_0x00f1
            com.google.android.gms.internal.atv_ads_framework.zzbh r2 = r2.zzc()     // Catch:{ zzax -> 0x0143 }
            goto L_0x011f
        L_0x00f1:
            android.content.ContentResolver r6 = r13.getContentResolver()     // Catch:{ zzax -> 0x0143 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r3 = r6.query(r7, r8, r9, r10, r11)     // Catch:{ zzax -> 0x0143 }
            com.google.android.gms.internal.atv_ads_framework.zzaw.zza(r3)     // Catch:{ zzax -> 0x0143 }
        L_0x0100:
            boolean r5 = r3.moveToNext()     // Catch:{ all -> 0x0127 }
            if (r5 == 0) goto L_0x0118
            java.lang.String r5 = r3.getString(r12)     // Catch:{ all -> 0x0127 }
            com.google.android.gms.internal.atv_ads_framework.zzaw.zza(r5)     // Catch:{ all -> 0x0127 }
            java.lang.String r6 = r3.getString(r4)     // Catch:{ all -> 0x0127 }
            com.google.android.gms.internal.atv_ads_framework.zzaw.zza(r6)     // Catch:{ all -> 0x0127 }
            r2.zza(r5, r6)     // Catch:{ all -> 0x0127 }
            goto L_0x0100
        L_0x0118:
            r3.close()     // Catch:{ zzax -> 0x0143 }
            com.google.android.gms.internal.atv_ads_framework.zzbh r2 = r2.zzc()     // Catch:{ zzax -> 0x0143 }
        L_0x011f:
            com.google.android.gms.internal.atv_ads_framework.zzbi r2 = r2.entrySet()     // Catch:{ zzax -> 0x0143 }
            r1.zzb(r2)     // Catch:{ zzax -> 0x0143 }
            goto L_0x0148
        L_0x0127:
            r2 = move-exception
            r3.close()     // Catch:{ all -> 0x012c }
            goto L_0x0142
        L_0x012c:
            r3 = move-exception
            java.lang.Class<java.lang.Throwable> r5 = java.lang.Throwable.class
            java.lang.String r6 = "addSuppressed"
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x0142 }
            java.lang.Class<java.lang.Throwable> r7 = java.lang.Throwable.class
            r4[r12] = r7     // Catch:{ Exception -> 0x0142 }
            java.lang.reflect.Method r4 = r5.getDeclaredMethod(r6, r4)     // Catch:{ Exception -> 0x0142 }
            java.lang.Object[] r3 = new java.lang.Object[]{r3}     // Catch:{ Exception -> 0x0142 }
            r4.invoke(r2, r3)     // Catch:{ Exception -> 0x0142 }
        L_0x0142:
            throw r2     // Catch:{ zzax -> 0x0143 }
        L_0x0143:
            com.google.android.gms.internal.atv_ads_framework.zzx r2 = com.google.android.gms.internal.atv_ads_framework.zzx.SIGNAL_COLLECTION_ERROR_NULL_CONTENT_PROVIDER_DATA
            r0.add(r2)
        L_0x0148:
            com.google.android.gms.internal.atv_ads_framework.zzbh r1 = r1.zzc()
            com.google.android.gms.internal.atv_ads_framework.zzf r13 = com.google.android.gms.internal.atv_ads_framework.zzf.zza(r13)
            com.google.android.gms.internal.atv_ads_framework.zzz r2 = com.google.android.gms.internal.atv_ads_framework.zzaa.zza()
            r2.zza(r0)
            com.google.android.gms.internal.atv_ads_framework.zzdh r0 = r2.zzi()
            com.google.android.gms.internal.atv_ads_framework.zzaa r0 = (com.google.android.gms.internal.atv_ads_framework.zzaa) r0
            r13.zzc(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.atv_ads_framework.zzad.zzc(android.content.Context):com.google.android.gms.internal.atv_ads_framework.zzbh");
    }

    private static String zzd() {
        String str;
        String str2 = Build.FINGERPRINT;
        if (!TextUtils.isEmpty(str2)) {
            List zzc = zzau.zzb(AbstractJsonLexerKt.COLON).zzc(str2);
            if (zzc.size() == 3) {
                str = (String) zzc.get(0);
                return zzaf.zza(str);
            }
        }
        str = "";
        return zzaf.zza(str);
    }

    private static String zze(String str, Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            if (packageInfo != null) {
                return Long.toString(PackageInfoCompat.getLongVersionCode(packageInfo));
            }
            return "";
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    private static boolean zzf(Context context) {
        return context.getPackageManager().hasSystemFeature("com.google.android.tv.operator_tier");
    }

    private static boolean zzg(Context context, String str) {
        try {
            if ((context.getPackageManager().getApplicationInfo(str, 0).flags & 1) != 0) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }
}
