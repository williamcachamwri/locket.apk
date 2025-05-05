package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration;
import com.google.ads.interactivemedia.v3.impl.data.zzbp;
import com.google.ads.interactivemedia.v3.impl.data.zzbq;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzez {
    private final Context zza;
    private final boolean zzb;
    private final long zzc;
    private final TestingConfiguration zzd;
    private final zzfd zze;
    private final zzew zzf;
    private final zzej zzg;

    public zzez(Context context, zzew zzew, zzfd zzfd, Map map, TestingConfiguration testingConfiguration) {
        int i;
        this.zza = context;
        this.zzb = !zzew.zza;
        long j = zzew.zzb;
        this.zzc = j <= 0 ? 150 : j;
        this.zzf = zzew;
        this.zzd = testingConfiguration;
        this.zze = zzfd;
        int i2 = zzew.zze;
        if (map != null) {
            try {
                i = Integer.parseInt((String) map.get("IDENTITY_TOKEN_CUSTOM_TIMEOUT_AND_MEASUREMENT"));
            } catch (NumberFormatException unused) {
                i = 0;
            }
            if (i > 0) {
                i2 = i;
            }
        }
        this.zzg = new zzej(new zzei(context, i2, zzfd));
    }

    private final Boolean zzb(zzeu zzeu) {
        try {
            return (Boolean) zzeu.zzc().get();
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            this.zze.zzg(zzbp.IDENTIFIER_INFO_FACTORY, zzbq.SAFE_BLOCKING_GET_IDLESS, e);
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0093  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.ads.interactivemedia.v3.impl.data.zzbn zza(com.google.ads.interactivemedia.v3.api.BaseRequest r12, com.google.ads.interactivemedia.v3.internal.zzeu r13, java.lang.String r14) {
        /*
            r11 = this;
            java.lang.String r0 = ""
            java.lang.Boolean r13 = r11.zzb(r13)
            boolean r13 = r13.booleanValue()
            r1 = 0
            if (r13 == 0) goto L_0x000e
            return r1
        L_0x000e:
            r13 = 0
            android.content.Context r2 = r11.zza     // Catch:{ Exception | NoClassDefFoundError -> 0x0023 }
            com.google.android.gms.ads.identifier.AdvertisingIdClient$Info r2 = com.google.android.gms.ads.identifier.AdvertisingIdClient.getAdvertisingIdInfo(r2)     // Catch:{ Exception | NoClassDefFoundError -> 0x0023 }
            java.lang.String r3 = r2.getId()     // Catch:{ Exception | NoClassDefFoundError -> 0x0023 }
            boolean r2 = r2.isLimitAdTrackingEnabled()     // Catch:{ Exception | NoClassDefFoundError -> 0x0023 }
            java.lang.String r4 = "adid"
            r7 = r2
            r5 = r3
        L_0x0021:
            r6 = r4
            goto L_0x004a
        L_0x0023:
            r2 = r13
            r3 = r0
        L_0x0025:
            android.content.Context r4 = r11.zza     // Catch:{ SettingNotFoundException -> 0x0042 }
            android.content.ContentResolver r4 = r4.getContentResolver()     // Catch:{ SettingNotFoundException -> 0x0042 }
            java.lang.String r5 = "advertising_id"
            java.lang.String r5 = android.provider.Settings.Secure.getString(r4, r5)     // Catch:{ SettingNotFoundException -> 0x0042 }
            java.lang.String r6 = "limit_ad_tracking"
            int r2 = android.provider.Settings.Secure.getInt(r4, r6)     // Catch:{ SettingNotFoundException -> 0x0042 }
            r3 = 1
            if (r2 != r3) goto L_0x003c
            r2 = r3
            goto L_0x003d
        L_0x003c:
            r2 = r13
        L_0x003d:
            java.lang.String r4 = "afai"
            r7 = r2
            goto L_0x0021
        L_0x0041:
            r3 = r5
        L_0x0042:
            java.lang.String r4 = "Failed to get advertising ID."
            com.google.ads.interactivemedia.v3.internal.zzfk.zzd(r4)
            r6 = r0
            r7 = r2
            r5 = r3
        L_0x004a:
            boolean r2 = r11.zzb
            if (r2 == 0) goto L_0x007b
            android.content.Context r2 = r11.zza     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0072, Exception | NoClassDefFoundError | NoSuchMethodError -> 0x006b }
            com.google.android.gms.appset.AppSetIdClient r2 = com.google.android.gms.appset.AppSet.getClient(r2)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0072, Exception | NoClassDefFoundError | NoSuchMethodError -> 0x006b }
            com.google.android.gms.tasks.Task r2 = r2.getAppSetIdInfo()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0072, Exception | NoClassDefFoundError | NoSuchMethodError -> 0x006b }
            long r3 = r11.zzc     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0072, Exception | NoClassDefFoundError | NoSuchMethodError -> 0x006b }
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0072, Exception | NoClassDefFoundError | NoSuchMethodError -> 0x006b }
            java.lang.Object r2 = com.google.android.gms.tasks.Tasks.await(r2, r3, r8)     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0072, Exception | NoClassDefFoundError | NoSuchMethodError -> 0x006b }
            com.google.android.gms.appset.AppSetIdInfo r2 = (com.google.android.gms.appset.AppSetIdInfo) r2     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0072, Exception | NoClassDefFoundError | NoSuchMethodError -> 0x006b }
            java.lang.String r3 = r2.getId()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0072, Exception | NoClassDefFoundError | NoSuchMethodError -> 0x006b }
            int r13 = r2.getScope()     // Catch:{ InterruptedException | ExecutionException | TimeoutException -> 0x0073, Exception | NoClassDefFoundError | NoSuchMethodError -> 0x006c }
            goto L_0x0078
        L_0x006b:
            r3 = r0
        L_0x006c:
            java.lang.String r2 = "Unable to contact the App Set SDK."
            com.google.ads.interactivemedia.v3.internal.zzfk.zzd(r2)
            goto L_0x0078
        L_0x0072:
            r3 = r0
        L_0x0073:
            java.lang.String r2 = "Timeout getting AppSet ID."
            com.google.ads.interactivemedia.v3.internal.zzfk.zzd(r2)
        L_0x0078:
            r9 = r13
            r8 = r3
            goto L_0x007d
        L_0x007b:
            r9 = r13
            r8 = r0
        L_0x007d:
            android.content.Context r13 = r11.zza
            com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration r2 = r11.zzd
            boolean r13 = com.google.ads.interactivemedia.v3.internal.zzel.zzc(r13, r2)
            com.google.ads.interactivemedia.v3.internal.zzex r12 = r12.zza()
            com.google.ads.interactivemedia.v3.internal.zzew r2 = r11.zzf
            android.content.Context r3 = r11.zza
            boolean r12 = r12.zza(r2, r3, r7, r13)
            if (r12 == 0) goto L_0x00a5
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r13)
            com.google.ads.interactivemedia.v3.internal.zzej r13 = r11.zzg
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x00a1
            com.google.ads.interactivemedia.v3.impl.zzau r1 = com.google.ads.interactivemedia.v3.impl.zzau.GTV
        L_0x00a1:
            java.lang.String r0 = r13.zza(r1, r14)
        L_0x00a5:
            r10 = r0
            com.google.ads.interactivemedia.v3.impl.data.zzbn r12 = com.google.ads.interactivemedia.v3.impl.data.zzbn.create(r5, r6, r7, r8, r9, r10)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzez.zza(com.google.ads.interactivemedia.v3.api.BaseRequest, com.google.ads.interactivemedia.v3.internal.zzeu, java.lang.String):com.google.ads.interactivemedia.v3.impl.data.zzbn");
    }
}
