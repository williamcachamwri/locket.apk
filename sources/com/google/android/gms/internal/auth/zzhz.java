package com.google.android.gms.internal.auth;

import android.util.Base64;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
public final class zzhz implements zzhx {
    public static final zzdc zza;
    public static final zzdc zzb;
    public static final zzdc zzc;
    public static final zzdc zzd;
    public static final zzdc zze;
    public static final zzdc zzf;
    public static final zzdc zzg;
    public static final zzdc zzh;
    public static final zzdc zzi;
    public static final zzdc zzj;
    public static final zzdc zzk;
    public static final zzdc zzl;
    public static final zzdc zzm;

    static {
        zzcz zza2 = new zzcz(zzcr.zza("com.google.android.gms.auth_account")).zzb().zza();
        zza = zza2.zzc("getTokenRefactor__account_data_service_sample_percentage", 0.0d);
        zzb = zza2.zze("getTokenRefactor__account_data_service_tokenAPI_usable", true);
        zzc = zza2.zzd("getTokenRefactor__account_manager_timeout_seconds", 20);
        zzd = zza2.zzd("getTokenRefactor__android_id_shift", 0);
        try {
            zze = zza2.zzf("getTokenRefactor__blocked_packages", zzhs.zzp(Base64.decode("ChNjb20uYW5kcm9pZC52ZW5kaW5nCiBjb20uZ29vZ2xlLmFuZHJvaWQuYXBwcy5tZWV0aW5ncwohY29tLmdvb2dsZS5hbmRyb2lkLmFwcHMubWVzc2FnaW5n", 3)), zzhy.zza);
            zzf = zza2.zze("getTokenRefactor__chimera_get_token_evolved", true);
            zzg = zza2.zzd("getTokenRefactor__clear_token_timeout_seconds", 20);
            zzh = zza2.zzd("getTokenRefactor__default_task_timeout_seconds", 20);
            zzi = zza2.zze("getTokenRefactor__gaul_accounts_api_evolved", false);
            zzj = zza2.zze("getTokenRefactor__gaul_token_api_evolved", false);
            zzk = zza2.zzd("getTokenRefactor__get_token_timeout_seconds", 120);
            zzl = zza2.zze("getTokenRefactor__gms_account_authenticator_evolved", true);
            zzm = zza2.zzc("getTokenRefactor__gms_account_authenticator_sample_percentage", 0.0d);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    public final zzhs zza() {
        return (zzhs) zze.zzb();
    }

    public final boolean zzb() {
        return ((Boolean) zzi.zzb()).booleanValue();
    }

    public final boolean zzc() {
        return ((Boolean) zzj.zzb()).booleanValue();
    }
}
