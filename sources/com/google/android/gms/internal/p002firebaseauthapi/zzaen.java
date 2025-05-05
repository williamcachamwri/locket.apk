package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import io.sentry.android.core.SentryLogcatAdapter;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaen  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzaen {
    private final String zza;
    private final String zzb;

    public final String zza() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zza;
    }

    public zzaen(Context context) {
        this(context, context.getPackageName());
    }

    private zzaen(Context context, String str) {
        Preconditions.checkNotNull(context);
        String checkNotEmpty = Preconditions.checkNotEmpty(str);
        this.zza = checkNotEmpty;
        try {
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context, checkNotEmpty);
            if (packageCertificateHashBytes == null) {
                SentryLogcatAdapter.e("FBA-PackageInfo", "single cert required: " + str);
                this.zzb = null;
                return;
            }
            this.zzb = Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
        } catch (PackageManager.NameNotFoundException unused) {
            SentryLogcatAdapter.e("FBA-PackageInfo", "no pkg: " + str);
            this.zzb = null;
        }
    }
}
