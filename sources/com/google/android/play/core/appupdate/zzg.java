package com.google.android.play.core.appupdate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.install.InstallStateUpdatedListener;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzg implements AppUpdateManager {
    private final zzr zza;
    private final zzc zzb;
    private final Context zzc;
    private final Handler zzd = new Handler(Looper.getMainLooper());

    zzg(zzr zzr, zzc zzc2, Context context) {
        this.zza = zzr;
        this.zzb = zzc2;
        this.zzc = context;
    }

    public final Task<Void> completeUpdate() {
        return this.zza.zzd(this.zzc.getPackageName());
    }

    public final Task<AppUpdateInfo> getAppUpdateInfo() {
        return this.zza.zze(this.zzc.getPackageName());
    }

    public final synchronized void registerListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.zzb.zzb(installStateUpdatedListener);
    }

    public final Task<Integer> startUpdateFlow(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions) {
        if (appUpdateInfo == null || activity == null || appUpdateOptions == null || appUpdateInfo.zzd()) {
            return Tasks.forException(new InstallException(-4));
        }
        if (!appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions)) {
            return Tasks.forException(new InstallException(-6));
        }
        appUpdateInfo.zzc();
        Intent intent = new Intent(activity, PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", appUpdateInfo.zza(appUpdateOptions));
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        intent.putExtra("result_receiver", new zze(this, this.zzd, taskCompletionSource));
        activity.startActivity(intent);
        return taskCompletionSource.getTask();
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int i, Activity activity, int i2) throws IntentSender.SendIntentException {
        AppUpdateOptions defaultOptions = AppUpdateOptions.defaultOptions(i);
        if (activity == null) {
            return false;
        }
        return startUpdateFlowForResult(appUpdateInfo, (IntentSenderForResultStarter) new zzf(this, activity), defaultOptions, i2);
    }

    public final synchronized void unregisterListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.zzb.zzc(installStateUpdatedListener);
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int i, IntentSenderForResultStarter intentSenderForResultStarter, int i2) throws IntentSender.SendIntentException {
        return startUpdateFlowForResult(appUpdateInfo, intentSenderForResultStarter, AppUpdateOptions.defaultOptions(i), i2);
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions, int i) throws IntentSender.SendIntentException {
        if (activity == null) {
            return false;
        }
        return startUpdateFlowForResult(appUpdateInfo, (IntentSenderForResultStarter) new zzf(this, activity), appUpdateOptions, i);
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, ActivityResultLauncher<IntentSenderRequest> activityResultLauncher, AppUpdateOptions appUpdateOptions) {
        if (appUpdateInfo == null || activityResultLauncher == null || appUpdateOptions == null || !appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions) || appUpdateInfo.zzd()) {
            return false;
        }
        appUpdateInfo.zzc();
        activityResultLauncher.launch(new IntentSenderRequest.Builder(appUpdateInfo.zza(appUpdateOptions).getIntentSender()).build());
        return true;
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, IntentSenderForResultStarter intentSenderForResultStarter, AppUpdateOptions appUpdateOptions, int i) throws IntentSender.SendIntentException {
        if (appUpdateInfo == null || intentSenderForResultStarter == null || appUpdateOptions == null || !appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions) || appUpdateInfo.zzd()) {
            return false;
        }
        appUpdateInfo.zzc();
        intentSenderForResultStarter.startIntentSenderForResult(appUpdateInfo.zza(appUpdateOptions).getIntentSender(), i, (Intent) null, 0, 0, 0, (Bundle) null);
        return true;
    }
}
