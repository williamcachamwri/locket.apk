package com.google.firebase.auth.internal;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzadg;
import com.google.android.gms.internal.p002firebaseauthapi.zzaep;
import com.google.android.gms.internal.p002firebaseauthapi.zzafb;
import com.google.android.gms.internal.p002firebaseauthapi.zzagh;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.integrity.IntegrityManager;
import com.google.android.play.core.integrity.IntegrityManagerFactory;
import com.google.android.play.core.integrity.IntegrityTokenResponse;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zza {
    /* access modifiers changed from: private */
    public static final String zza = "zza";
    private static final zza zzb = new zza();
    /* access modifiers changed from: private */
    public String zzc;

    public final Task<zzh> zza(FirebaseAuth firebaseAuth, String str, Activity activity, boolean z, boolean z2, boolean z3, RecaptchaAction recaptchaAction) {
        boolean z4 = z2;
        zzae zzae = (zzae) firebaseAuth.getFirebaseAuthSettings();
        zzcf zzc2 = zzcf.zzc();
        if (zzafb.zza(firebaseAuth.getApp()) || zzae.zze()) {
            return Tasks.forResult(new zzo().zza());
        }
        String str2 = zza;
        Log.i(str2, "ForceRecaptchaV2Flow from phoneAuthOptions = " + z4 + ", ForceRecaptchav2Flow from firebaseSettings = " + zzae.zzc());
        boolean z5 = z4 || zzae.zzc();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Task<String> zzb2 = zzc2.zzb();
        if (zzb2 != null) {
            if (zzb2.isSuccessful()) {
                return Tasks.forResult(new zzo().zzc(zzb2.getResult()).zza());
            }
            SentryLogcatAdapter.e(str2, "Error in previous reCAPTCHAV2 flow: " + zzb2.getException().getMessage());
            SentryLogcatAdapter.e(str2, "Continuing with application verification as normal");
        }
        if (z5 || z3) {
            zza(firebaseAuth, str, activity, z, z5, zzc2, (TaskCompletionSource<zzh>) taskCompletionSource);
        } else {
            firebaseAuth.initializeRecaptchaConfig().addOnCompleteListener(new zzc(this, taskCompletionSource, firebaseAuth, recaptchaAction, str, activity, z, false, zzc2));
        }
        return taskCompletionSource.getTask();
    }

    public static zza zza() {
        return zzb;
    }

    private zza() {
    }

    private final void zza(FirebaseAuth firebaseAuth, zzcf zzcf, Activity activity, TaskCompletionSource<zzh> taskCompletionSource) {
        Task task;
        if (activity == null) {
            taskCompletionSource.setException(new FirebaseAuthMissingActivityForRecaptchaException());
            return;
        }
        zzbm.zza(firebaseAuth.getApp().getApplicationContext(), firebaseAuth);
        Preconditions.checkNotNull(activity);
        TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
        if (!zzav.zza().zza(activity, (TaskCompletionSource<String>) taskCompletionSource2)) {
            task = Tasks.forException(zzadg.zza(new Status(17057, "reCAPTCHA flow already in progress")));
        } else {
            new zzaep(firebaseAuth, activity).zza();
            task = taskCompletionSource2.getTask();
        }
        task.addOnSuccessListener(new zzi(this, taskCompletionSource)).addOnFailureListener(new zzf(this, taskCompletionSource));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(TaskCompletionSource taskCompletionSource, FirebaseAuth firebaseAuth, zzcf zzcf, Activity activity, Task task) {
        if (task.isSuccessful() && task.getResult() != null && !TextUtils.isEmpty(((IntegrityTokenResponse) task.getResult()).token())) {
            taskCompletionSource.setResult(new zzo().zza(((IntegrityTokenResponse) task.getResult()).token()).zza());
            return;
        }
        SentryLogcatAdapter.e(zza, "Play Integrity Token fetch failed, falling back to Recaptcha" + (task.getException() == null ? "" : task.getException().getMessage()));
        zza(firebaseAuth, zzcf, activity, taskCompletionSource);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(TaskCompletionSource taskCompletionSource, FirebaseAuth firebaseAuth, RecaptchaAction recaptchaAction, String str, Activity activity, boolean z, boolean z2, zzcf zzcf, Task task) {
        if (!task.isSuccessful()) {
            SentryLogcatAdapter.e(zza, "Failed to initialize reCAPTCHA config: " + task.getException().getMessage());
        }
        if (firebaseAuth.zzb() == null || !firebaseAuth.zzb().zzb("PHONE_PROVIDER")) {
            TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
            zza(firebaseAuth, str, activity, z, z2, zzcf, (TaskCompletionSource<zzh>) taskCompletionSource);
            return;
        }
        RecaptchaAction recaptchaAction2 = recaptchaAction;
        firebaseAuth.zzb().zza(firebaseAuth.getTenantId(), false, recaptchaAction).addOnSuccessListener(new zzd(this, taskCompletionSource)).addOnFailureListener(new zze(this, firebaseAuth, str, activity, z, z2, zzcf, taskCompletionSource));
    }

    /* access modifiers changed from: private */
    public final void zza(FirebaseAuth firebaseAuth, String str, Activity activity, boolean z, boolean z2, zzcf zzcf, TaskCompletionSource<zzh> taskCompletionSource) {
        Task<zzagh> task;
        if (!z || z2) {
            zza(firebaseAuth, zzcf, activity, taskCompletionSource);
            return;
        }
        IntegrityManager create = IntegrityManagerFactory.create(firebaseAuth.getApp().getApplicationContext());
        if (!TextUtils.isEmpty(this.zzc)) {
            task = Tasks.forResult(new zzagh(this.zzc));
        } else {
            task = firebaseAuth.zza();
        }
        task.continueWithTask(firebaseAuth.zzf(), new zzg(this, str, create)).addOnCompleteListener(new zzb(this, taskCompletionSource, firebaseAuth, zzcf, activity));
    }

    public static boolean zza(Exception exc) {
        if (!(exc instanceof FirebaseAuthMissingActivityForRecaptchaException)) {
            return (exc instanceof FirebaseAuthException) && ((FirebaseAuthException) exc).getErrorCode().endsWith("UNAUTHORIZED_DOMAIN");
        }
        return true;
    }
}
