package com.google.firebase.auth.internal;

import androidx.webkit.ProxyConfig;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzag;
import com.google.android.gms.internal.p002firebaseauthapi.zzagm;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaTasksClient;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzbv {
    FirebaseApp zza;
    zzbu zzb;
    private final Object zzc;
    private final Map<String, Task<RecaptchaTasksClient>> zzd;
    private zzagm zze;
    private FirebaseAuth zzf;

    private final Task<RecaptchaTasksClient> zzc(String str) {
        Task<RecaptchaTasksClient> task;
        synchronized (this.zzc) {
            task = this.zzd.get(str);
        }
        return task;
    }

    public final Task<String> zza(String str, Boolean bool, RecaptchaAction recaptchaAction) {
        String zzd2 = zzd(str);
        Task<RecaptchaTasksClient> zzc2 = zzc(zzd2);
        if (bool.booleanValue() || zzc2 == null) {
            zzc2 = zza(zzd2, bool);
        }
        return zzc2.continueWithTask(new zzbx(this, recaptchaAction));
    }

    public final Task<RecaptchaTasksClient> zza(String str, Boolean bool) {
        Task<RecaptchaTasksClient> zzc2;
        String zzd2 = zzd(str);
        if (bool.booleanValue() || (zzc2 = zzc(zzd2)) == null) {
            return this.zzf.zza("RECAPTCHA_ENTERPRISE").continueWithTask(new zzby(this, zzd2));
        }
        return zzc2;
    }

    private static String zzd(String str) {
        return zzag.zzc(str) ? ProxyConfig.MATCH_ALL_SCHEMES : str;
    }

    static /* synthetic */ void zza(zzbv zzbv, zzagm zzagm, Task task, String str) {
        synchronized (zzbv.zzc) {
            zzbv.zze = zzagm;
            zzbv.zzd.put(str, task);
        }
    }

    public zzbv(FirebaseApp firebaseApp, FirebaseAuth firebaseAuth) {
        this(firebaseApp, firebaseAuth, new zzbt());
    }

    private zzbv(FirebaseApp firebaseApp, FirebaseAuth firebaseAuth, zzbu zzbu) {
        this.zzc = new Object();
        this.zzd = new HashMap();
        this.zza = firebaseApp;
        this.zzf = firebaseAuth;
        this.zzb = zzbu;
    }

    public final boolean zza(String str) {
        String zzb2;
        Preconditions.checkNotNull(str);
        zzagm zzagm = this.zze;
        if (zzagm == null || (zzb2 = zzagm.zzb(str)) == null) {
            return false;
        }
        return zzb2.equals("AUDIT");
    }

    public final boolean zzb(String str) {
        boolean z;
        synchronized (this.zzc) {
            zzagm zzagm = this.zze;
            z = zzagm != null && zzagm.zzc(str);
        }
        return z;
    }
}
