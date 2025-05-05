package com.google.android.gms.auth;

import android.app.PendingIntent;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.android.gms:play-services-auth-base@@18.0.10 */
public class UserRecoverableAuthException extends GoogleAuthException {
    private final Intent zza;
    private final PendingIntent zzb;
    private final zzn zzc;

    public UserRecoverableAuthException(String str, Intent intent) {
        this(str, intent, (PendingIntent) null, zzn.LEGACY);
    }

    public static UserRecoverableAuthException zza(String str, Intent intent, PendingIntent pendingIntent) {
        Preconditions.checkNotNull(intent);
        Preconditions.checkNotNull(pendingIntent);
        return new UserRecoverableAuthException(str, intent, pendingIntent, zzn.AUTH_INSTANTIATION);
    }

    public Intent getIntent() {
        Intent intent = this.zza;
        if (intent != null) {
            return new Intent(intent);
        }
        int ordinal = this.zzc.ordinal();
        if (ordinal == 0) {
            SentryLogcatAdapter.w("Auth", "Make sure that an intent was provided to class instantiation.");
            return null;
        } else if (ordinal == 1) {
            SentryLogcatAdapter.e("Auth", "This shouldn't happen. Gms API throwing this exception should support the recovery Intent.");
            return null;
        } else if (ordinal != 2) {
            return null;
        } else {
            SentryLogcatAdapter.e("Auth", "this instantiation of UserRecoverableAuthException doesn't support an Intent.");
            return null;
        }
    }

    private UserRecoverableAuthException(String str, Intent intent, PendingIntent pendingIntent, zzn zzn) {
        super(str);
        this.zzb = pendingIntent;
        this.zza = intent;
        this.zzc = (zzn) Preconditions.checkNotNull(zzn);
    }
}
