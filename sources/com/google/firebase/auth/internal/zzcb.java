package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.TotpSecret;
import java.util.Locale;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzcb implements TotpSecret {
    private final String zza;
    private final String zzb;
    private final int zzc;
    private final int zzd;
    private final long zze;
    private String zzf;
    private FirebaseAuth zzg;

    public final int getCodeIntervalSeconds() {
        return this.zzd;
    }

    public final int getCodeLength() {
        return this.zzc;
    }

    public final long getEnrollmentCompletionDeadline() {
        return this.zze;
    }

    public final String generateQrCodeUrl() {
        return generateQrCodeUrl(Preconditions.checkNotEmpty(((FirebaseUser) Preconditions.checkNotNull(this.zzg.getCurrentUser(), "Current user cannot be null, since user is required to be logged in to enroll for TOTP MFA.")).getEmail(), "Email cannot be empty, since verified email is required to use MFA."), this.zzg.getApp().getName());
    }

    public final String generateQrCodeUrl(String str, String str2) {
        Preconditions.checkNotEmpty(str, "accountName cannot be empty.");
        Preconditions.checkNotEmpty(str2, "issuer cannot be empty.");
        return String.format((Locale) null, "otpauth://totp/%s:%s?secret=%s&issuer=%s&algorithm=%s&digits=%d", new Object[]{str2, str, this.zza, str2, this.zzb, Integer.valueOf(this.zzc)});
    }

    public final String getHashAlgorithm() {
        return this.zzb;
    }

    public final String getSessionInfo() {
        return this.zzf;
    }

    public final String getSharedSecretKey() {
        return this.zza;
    }

    public zzcb(String str, String str2, int i, int i2, long j, String str3, FirebaseAuth firebaseAuth) {
        Preconditions.checkNotEmpty(str3, "sessionInfo cannot be empty.");
        Preconditions.checkNotNull(firebaseAuth, "firebaseAuth cannot be null.");
        this.zza = Preconditions.checkNotEmpty(str, "sharedSecretKey cannot be empty. This is required to generate QR code URL.");
        this.zzb = Preconditions.checkNotEmpty(str2, "hashAlgorithm cannot be empty.");
        this.zzc = i;
        this.zzd = i2;
        this.zze = j;
        this.zzf = str3;
        this.zzg = firebaseAuth;
    }

    public final void openInOtpApp(String str) {
        Preconditions.checkNotEmpty(str, "qrCodeUrl cannot be empty.");
        try {
            zza(str);
        } catch (ActivityNotFoundException unused) {
            zza("https://play.google.com/store/search?q=otpauth&c=apps");
        }
    }

    public final void openInOtpApp(String str, String str2, Activity activity) {
        Preconditions.checkNotEmpty(str, "QrCodeUrl cannot be empty.");
        Preconditions.checkNotEmpty(str2, "FallbackUrl cannot be empty.");
        Preconditions.checkNotNull(activity, "Activity cannot be null.");
        try {
            zza(str, activity);
        } catch (ActivityNotFoundException unused) {
            zza(str2, activity);
        }
    }

    private final void zza(String str) {
        this.zzg.getApp().getApplicationContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)).addFlags(268435456));
    }

    private static void zza(String str, Activity activity) {
        Preconditions.checkNotNull(activity, "Activity cannot be null.");
        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)).addFlags(268435456));
    }
}
