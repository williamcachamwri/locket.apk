package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import com.google.ads.interactivemedia.v3.impl.data.zzbp;
import com.google.ads.interactivemedia.v3.impl.data.zzbq;
import com.google.ads.interactivemedia.v3.impl.zzba;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzet implements zzeu {
    private final zzba zza;
    private final Context zzb;
    private final ExecutorService zzc;
    /* access modifiers changed from: private */
    public Future zzd = null;
    private final zzes zze;
    private final zzfd zzf;
    private SharedPreferences.OnSharedPreferenceChangeListener zzg = null;

    public zzet(zzba zzba, Context context, ExecutorService executorService, zzes zzes, zzfd zzfd) {
        this.zza = zzba;
        this.zzb = context;
        this.zzc = executorService;
        this.zze = zzes;
        this.zzf = zzfd;
    }

    /* access modifiers changed from: private */
    public final Future zzf() {
        if (!this.zze.zzb) {
            return zzuk.zzb(false);
        }
        return zzuk.zzc(this.zza.zzd(zza()), new zzeq(), this.zzc);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0091 A[Catch:{ ClassCastException -> 0x009b }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map zza() {
        /*
            r11 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            android.content.Context r1 = r11.zzb
            android.content.SharedPreferences r1 = androidx.preference.PreferenceManager.getDefaultSharedPreferences(r1)
            if (r1 != 0) goto L_0x000f
            goto L_0x00a7
        L_0x000f:
            com.google.ads.interactivemedia.v3.internal.zzes r2 = r11.zze
            com.google.ads.interactivemedia.v3.internal.zzrp r2 = r2.zzc
            com.google.ads.interactivemedia.v3.internal.zzrr r2 = r2.entrySet()
            com.google.ads.interactivemedia.v3.internal.zzss r2 = r2.iterator()
        L_0x001d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00a7
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r3 = r3.getValue()
            java.lang.String r3 = (java.lang.String) r3
            boolean r5 = r1.contains(r4)
            if (r5 == 0) goto L_0x001d
            int r5 = r3.hashCode()     // Catch:{ ClassCastException -> 0x009b }
            r6 = -1950496919(0xffffffff8bbdc769, float:-7.310019E-32)
            r7 = 0
            r8 = 2
            r9 = 1
            r10 = -1
            if (r5 == r6) goto L_0x0067
            r6 = -1808118735(0xffffffff943a4c31, float:-9.405626E-27)
            if (r5 == r6) goto L_0x005d
            r6 = 1729365000(0x67140408, float:6.989846E23)
            if (r5 == r6) goto L_0x0053
            goto L_0x0071
        L_0x0053:
            java.lang.String r5 = "Boolean"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0071
            r3 = r8
            goto L_0x0072
        L_0x005d:
            java.lang.String r5 = "String"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0071
            r3 = r7
            goto L_0x0072
        L_0x0067:
            java.lang.String r5 = "Number"
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0071
            r3 = r9
            goto L_0x0072
        L_0x0071:
            r3 = r10
        L_0x0072:
            if (r3 == 0) goto L_0x0091
            if (r3 == r9) goto L_0x0085
            if (r3 == r8) goto L_0x0079
            goto L_0x001d
        L_0x0079:
            boolean r3 = r1.getBoolean(r4, r7)     // Catch:{ ClassCastException -> 0x009b }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ ClassCastException -> 0x009b }
            r0.put(r4, r3)     // Catch:{ ClassCastException -> 0x009b }
            goto L_0x001d
        L_0x0085:
            int r3 = r1.getInt(r4, r10)     // Catch:{ ClassCastException -> 0x009b }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ ClassCastException -> 0x009b }
            r0.put(r4, r3)     // Catch:{ ClassCastException -> 0x009b }
            goto L_0x001d
        L_0x0091:
            java.lang.String r3 = ""
            java.lang.String r3 = r1.getString(r4, r3)     // Catch:{ ClassCastException -> 0x009b }
            r0.put(r4, r3)     // Catch:{ ClassCastException -> 0x009b }
            goto L_0x001d
        L_0x009b:
            r3 = move-exception
            com.google.ads.interactivemedia.v3.internal.zzfd r4 = r11.zzf
            com.google.ads.interactivemedia.v3.impl.data.zzbp r5 = com.google.ads.interactivemedia.v3.impl.data.zzbp.IDENTITY_MANAGER
            com.google.ads.interactivemedia.v3.impl.data.zzbq r6 = com.google.ads.interactivemedia.v3.impl.data.zzbq.GET_CONSENT_SETTINGS
            r4.zzg(r5, r6, r3)
            goto L_0x001d
        L_0x00a7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzet.zza():java.util.Map");
    }

    public final Future zzc() {
        if (this.zzd == null) {
            this.zzf.zzg(zzbp.IDENTITY_MANAGER, zzbq.GET_IDLESS_STATE, new IllegalStateException("idLessState must be defined"));
            zze();
        }
        return this.zzd;
    }

    public final void zze() {
        this.zzd = zzf();
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.zzb.getApplicationContext());
        zzer zzer = new zzer(this);
        this.zzg = zzer;
        defaultSharedPreferences.registerOnSharedPreferenceChangeListener(zzer);
    }
}
