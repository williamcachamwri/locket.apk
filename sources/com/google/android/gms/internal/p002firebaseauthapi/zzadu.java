package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Tasks;
import com.google.common.net.HttpHeaders;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import io.sentry.android.core.SentryLogcatAdapter;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadu  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzadu {
    private Context zza;
    private zzaen zzb;
    private String zzc;
    private final FirebaseApp zzd;
    private boolean zze;
    private String zzf;

    private static String zza(FirebaseApp firebaseApp) {
        InteropAppCheckTokenProvider interopAppCheckTokenProvider = FirebaseAuth.getInstance(firebaseApp).zzc().get();
        if (interopAppCheckTokenProvider == null) {
            return null;
        }
        try {
            AppCheckTokenResult appCheckTokenResult = (AppCheckTokenResult) Tasks.await(interopAppCheckTokenProvider.getToken(false));
            if (appCheckTokenResult.getError() != null) {
                SentryLogcatAdapter.w("LocalRequestInterceptor", "Error getting App Check token; using placeholder token instead. Error: " + String.valueOf(appCheckTokenResult.getError()));
            }
            return appCheckTokenResult.getToken();
        } catch (InterruptedException | ExecutionException e) {
            SentryLogcatAdapter.e("LocalRequestInterceptor", "Unexpected error getting App Check token: " + e.getMessage());
            return null;
        }
    }

    private static String zzb(FirebaseApp firebaseApp) {
        HeartBeatController heartBeatController = FirebaseAuth.getInstance(firebaseApp).zzd().get();
        if (heartBeatController != null) {
            try {
                return (String) Tasks.await(heartBeatController.getHeartBeatsHeader());
            } catch (InterruptedException | ExecutionException e) {
                SentryLogcatAdapter.w("LocalRequestInterceptor", "Unable to get heartbeats: " + e.getMessage());
            }
        }
        return null;
    }

    public zzadu(FirebaseApp firebaseApp, String str) {
        this(firebaseApp.getApplicationContext(), firebaseApp, str);
    }

    public zzadu(Context context, FirebaseApp firebaseApp, String str) {
        this.zze = false;
        this.zza = (Context) Preconditions.checkNotNull(context);
        this.zzd = (FirebaseApp) Preconditions.checkNotNull(firebaseApp);
        this.zzc = String.format("Android/%s/%s", new Object[]{"Fallback", str});
    }

    public final void zza(URLConnection uRLConnection) {
        String str;
        if (this.zze) {
            str = this.zzc + "/FirebaseUI-Android";
        } else {
            str = this.zzc + "/FirebaseCore-Android";
        }
        if (this.zzb == null) {
            this.zzb = new zzaen(this.zza);
        }
        uRLConnection.setRequestProperty("X-Android-Package", this.zzb.zzb());
        uRLConnection.setRequestProperty("X-Android-Cert", this.zzb.zza());
        uRLConnection.setRequestProperty(HttpHeaders.ACCEPT_LANGUAGE, zzadx.zza());
        uRLConnection.setRequestProperty("X-Client-Version", str);
        uRLConnection.setRequestProperty("X-Firebase-Locale", this.zzf);
        uRLConnection.setRequestProperty("X-Firebase-GMPID", this.zzd.getOptions().getApplicationId());
        uRLConnection.setRequestProperty("X-Firebase-Client", zzb(this.zzd));
        String zza2 = zza(this.zzd);
        if (!TextUtils.isEmpty(zza2)) {
            uRLConnection.setRequestProperty("X-Firebase-AppCheck", zza2);
        }
        this.zzf = null;
    }

    public final void zza(String str) {
        this.zze = !TextUtils.isEmpty(str);
    }

    public final void zzb(String str) {
        this.zzf = str;
    }
}
