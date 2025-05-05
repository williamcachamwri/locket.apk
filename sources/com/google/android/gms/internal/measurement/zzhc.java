package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import androidx.core.content.PermissionChecker;
import io.sentry.android.core.SentryLogcatAdapter;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
final class zzhc implements zzhb {
    private static zzhc zza;
    @Nullable
    private final Context zzb;
    @Nullable
    private final ContentObserver zzc;

    static zzhc zza(Context context) {
        zzhc zzhc;
        synchronized (zzhc.class) {
            if (zza == null) {
                zza = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new zzhc(context) : new zzhc();
            }
            zzhc = zza;
        }
        return zzhc;
    }

    /* access modifiers changed from: private */
    @Nullable
    /* renamed from: zzc */
    public final String zza(String str) {
        Context context = this.zzb;
        if (context != null && !zzgs.zza(context)) {
            try {
                return (String) zzha.zza(new zzhf(this, str));
            } catch (IllegalStateException | NullPointerException | SecurityException e) {
                SentryLogcatAdapter.e("GservicesLoader", "Unable to read GServices for: " + str, e);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzb(String str) {
        return zzgj.zza(this.zzb.getContentResolver(), str, (String) null);
    }

    private zzhc() {
        this.zzb = null;
        this.zzc = null;
    }

    private zzhc(Context context) {
        this.zzb = context;
        zzhe zzhe = new zzhe(this, (Handler) null);
        this.zzc = zzhe;
        context.getContentResolver().registerContentObserver(zzgi.zza, true, zzhe);
    }

    static synchronized void zza() {
        Context context;
        synchronized (zzhc.class) {
            zzhc zzhc = zza;
            if (!(zzhc == null || (context = zzhc.zzb) == null || zzhc.zzc == null)) {
                context.getContentResolver().unregisterContentObserver(zza.zzc);
            }
            zza = null;
        }
    }
}
