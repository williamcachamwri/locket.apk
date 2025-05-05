package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Base64;
import androidx.media3.common.C;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.auth.PhoneAuthCredential;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaew  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaew {
    /* access modifiers changed from: private */
    public static final Logger zza = new Logger("FirebaseAuth", "SmsRetrieverHelper");
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    /* access modifiers changed from: private */
    public final HashMap<String, zzaez> zzd = new HashMap<>();

    /* access modifiers changed from: package-private */
    public final zzade zza(zzade zzade, String str) {
        return new zzaex(this, zzade, str);
    }

    static String zza(String str) {
        Matcher matcher = Pattern.compile("(?<!\\d)\\d{6}(?!\\d)").matcher(str);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final String zzb() {
        Signature[] signatureArr;
        try {
            String packageName = this.zzb.getPackageName();
            if (Build.VERSION.SDK_INT < 28) {
                signatureArr = Wrappers.packageManager(this.zzb).getPackageInfo(packageName, 64).signatures;
            } else {
                signatureArr = Wrappers.packageManager(this.zzb).getPackageInfo(packageName, C.BUFFER_FLAG_FIRST_SAMPLE).signingInfo.getApkContentsSigners();
            }
            String zza2 = zza(packageName, signatureArr[0].toCharsString());
            if (zza2 != null) {
                return zza2;
            }
            zza.e("Hash generation failed.", new Object[0]);
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            zza.e("Unable to find package to obtain hash.", new Object[0]);
            return null;
        }
    }

    private static String zza(String str, String str2) {
        String str3 = str + " " + str2;
        try {
            MessageDigest instance = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
            instance.update(str3.getBytes(StandardCharsets.UTF_8));
            String substring = Base64.encodeToString(Arrays.copyOf(instance.digest(), 9), 3).substring(0, 11);
            zza.d("Package: " + str + " -- Hash: " + substring, new Object[0]);
            return substring;
        } catch (NoSuchAlgorithmException e) {
            zza.e("NoSuchAlgorithm: " + e.getMessage(), new Object[0]);
            return null;
        }
    }

    static /* synthetic */ void zza(zzaew zzaew, String str) {
        zzaez zzaez = zzaew.zzd.get(str);
        if (zzaez != null && !zzag.zzc(zzaez.zzd) && !zzag.zzc(zzaez.zze) && !zzaez.zzb.isEmpty()) {
            for (zzade zza2 : zzaez.zzb) {
                zza2.zza(PhoneAuthCredential.zza(zzaez.zzd, zzaez.zze));
            }
            zzaez.zzh = true;
        }
    }

    zzaew(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzb = context;
        this.zzc = scheduledExecutorService;
    }

    /* access modifiers changed from: private */
    public final void zze(String str) {
        zzaez zzaez = this.zzd.get(str);
        if (zzaez != null && !zzaez.zzh && !zzag.zzc(zzaez.zzd)) {
            zza.w("Timed out waiting for SMS.", new Object[0]);
            for (zzade zza2 : zzaez.zzb) {
                zza2.zza(zzaez.zzd);
            }
            zzaez.zzi = true;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: zzf */
    public final void zzb(String str) {
        zzaez zzaez = this.zzd.get(str);
        if (zzaez != null) {
            if (!zzaez.zzi) {
                zze(str);
            }
            zzc(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzade zzade, String str) {
        zzaez zzaez = this.zzd.get(str);
        if (zzaez != null) {
            zzaez.zzb.add(zzade);
            if (zzaez.zzg) {
                zzade.zzb(zzaez.zzd);
            }
            if (zzaez.zzh) {
                zzade.zza(PhoneAuthCredential.zza(zzaez.zzd, zzaez.zze));
            }
            if (zzaez.zzi) {
                zzade.zza(zzaez.zzd);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(String str) {
        zzaez zzaez = this.zzd.get(str);
        if (zzaez != null) {
            if (zzaez.zzf != null && !zzaez.zzf.isDone()) {
                zzaez.zzf.cancel(false);
            }
            zzaez.zzb.clear();
            this.zzd.remove(str);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(String str, zzade zzade, long j, boolean z) {
        this.zzd.put(str, new zzaez(j, z));
        zzb(zzade, str);
        zzaez zzaez = this.zzd.get(str);
        if (zzaez.zza <= 0) {
            zza.w("Timeout of 0 specified; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzaez.zzf = this.zzc.schedule(new zzaev(this, str), zzaez.zza, TimeUnit.SECONDS);
        if (!zzaez.zzc) {
            zza.w("SMS auto-retrieval unavailable; SmsRetriever will not start.", new Object[0]);
            return;
        }
        zzafa zzafa = new zzafa(this, str);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(SmsRetriever.SMS_RETRIEVED_ACTION);
        zzc.zza(this.zzb.getApplicationContext(), zzafa, intentFilter);
        SmsRetriever.getClient(this.zzb).startSmsRetriever().addOnFailureListener(new zzaey(this));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzd(String str) {
        return this.zzd.get(str) != null;
    }
}
