package com.google.android.play.core.appupdate.testing;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.appupdate.zzc;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
public class FakeAppUpdateManager implements AppUpdateManager {
    private final zzc zza;
    private final Context zzb;
    private final List zzc = new ArrayList();
    private int zzd = 0;
    private int zze = 0;
    private boolean zzf = false;
    private int zzg = 0;
    private Integer zzh = null;
    private int zzi = 0;
    private long zzj = 0;
    private long zzk = 0;
    private boolean zzl = false;
    private boolean zzm = false;
    private boolean zzn = false;
    private Integer zzo;

    public FakeAppUpdateManager(Context context) {
        this.zza = new zzc(context);
        this.zzb = context;
    }

    private static int zza() {
        return 67108864;
    }

    private final int zzb() {
        if (!this.zzf) {
            return 1;
        }
        int i = this.zzd;
        return (i == 0 || i == 4 || i == 5 || i == 6) ? 2 : 3;
    }

    private final void zzc() {
        this.zza.zzd(InstallState.zza(this.zzd, this.zzj, this.zzk, this.zze, this.zzb.getPackageName()));
    }

    private final boolean zzd(AppUpdateInfo appUpdateInfo, AppUpdateOptions appUpdateOptions) {
        if (!appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions) && (!AppUpdateOptions.defaultOptions(appUpdateOptions.appUpdateType()).equals(appUpdateOptions) || !appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions.appUpdateType()))) {
            return false;
        }
        if (appUpdateOptions.appUpdateType() == 1) {
            this.zzm = true;
            this.zzo = 1;
        } else {
            this.zzl = true;
            this.zzo = 0;
        }
        return true;
    }

    public Task<Void> completeUpdate() {
        if (this.zze != 0) {
            return Tasks.forException(new InstallException(this.zze));
        }
        int i = this.zzd;
        if (i == 11) {
            this.zzd = 3;
            this.zzn = true;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
            return Tasks.forResult(null);
        } else if (i == 3) {
            return Tasks.forException(new InstallException(-8));
        } else {
            return Tasks.forException(new InstallException(-7));
        }
    }

    public void downloadCompletes() {
        int i = this.zzd;
        if (i == 2 || i == 1) {
            this.zzd = 11;
            this.zzj = 0;
            this.zzk = 0;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
                return;
            }
            Integer num2 = 1;
            if (num2.equals(this.zzo)) {
                completeUpdate();
            }
        }
    }

    public void downloadFails() {
        int i = this.zzd;
        if (i == 1 || i == 2) {
            this.zzd = 5;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
            this.zzo = null;
            this.zzm = false;
            this.zzd = 0;
        }
    }

    public void downloadStarts() {
        if (this.zzd == 1) {
            this.zzd = 2;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
        }
    }

    public Task<AppUpdateInfo> getAppUpdateInfo() {
        PendingIntent pendingIntent;
        PendingIntent pendingIntent2;
        PendingIntent pendingIntent3;
        PendingIntent pendingIntent4;
        PendingIntent pendingIntent5;
        PendingIntent pendingIntent6;
        if (this.zze != 0) {
            return Tasks.forException(new InstallException(this.zze));
        }
        if (zzb() == 2) {
            if (this.zzc.contains(0)) {
                pendingIntent6 = PendingIntent.getBroadcast(this.zzb, 0, new Intent(), zza());
                pendingIntent5 = PendingIntent.getBroadcast(this.zzb, 0, new Intent(), zza());
            } else {
                pendingIntent6 = null;
                pendingIntent5 = null;
            }
            if (this.zzc.contains(1)) {
                PendingIntent broadcast = PendingIntent.getBroadcast(this.zzb, 0, new Intent(), zza());
                pendingIntent3 = pendingIntent6;
                pendingIntent2 = PendingIntent.getBroadcast(this.zzb, 0, new Intent(), zza());
                pendingIntent4 = broadcast;
            } else {
                pendingIntent3 = pendingIntent6;
                pendingIntent4 = null;
                pendingIntent2 = null;
            }
            pendingIntent = pendingIntent5;
        } else {
            pendingIntent4 = null;
            pendingIntent3 = null;
            pendingIntent2 = null;
            pendingIntent = null;
        }
        String packageName = this.zzb.getPackageName();
        int i = this.zzg;
        int zzb2 = zzb();
        int i2 = this.zzd;
        Integer num = this.zzh;
        int i3 = this.zzi;
        long j = this.zzj;
        long j2 = this.zzk;
        HashMap hashMap = r1;
        HashMap hashMap2 = new HashMap();
        return Tasks.forResult(AppUpdateInfo.zzb(packageName, i, zzb2, i2, num, i3, j, j2, 0, 0, pendingIntent4, pendingIntent3, pendingIntent2, pendingIntent, hashMap));
    }

    public Integer getTypeForUpdateInProgress() {
        return this.zzo;
    }

    public void installCompletes() {
        if (this.zzd == 3) {
            this.zzd = 4;
            this.zzf = false;
            this.zzg = 0;
            this.zzh = null;
            this.zzi = 0;
            this.zzj = 0;
            this.zzk = 0;
            this.zzm = false;
            this.zzn = false;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
            this.zzo = null;
            this.zzd = 0;
        }
    }

    public void installFails() {
        if (this.zzd == 3) {
            this.zzd = 5;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
            this.zzo = null;
            this.zzn = false;
            this.zzm = false;
            this.zzd = 0;
        }
    }

    public boolean isConfirmationDialogVisible() {
        return this.zzl;
    }

    public boolean isImmediateFlowVisible() {
        return this.zzm;
    }

    public boolean isInstallSplashScreenVisible() {
        return this.zzn;
    }

    public void registerListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.zza.zzb(installStateUpdatedListener);
    }

    public void setBytesDownloaded(long j) {
        if (this.zzd == 2 && j <= this.zzk) {
            this.zzj = j;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
        }
    }

    public void setClientVersionStalenessDays(Integer num) {
        if (this.zzf) {
            this.zzh = num;
        }
    }

    public void setInstallErrorCode(int i) {
        this.zze = i;
    }

    public void setTotalBytesToDownload(long j) {
        if (this.zzd == 2) {
            this.zzk = j;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
        }
    }

    public void setUpdateAvailable(int i) {
        this.zzf = true;
        this.zzc.clear();
        this.zzc.add(0);
        this.zzc.add(1);
        this.zzg = i;
    }

    public void setUpdateNotAvailable() {
        this.zzf = false;
        this.zzh = null;
    }

    public void setUpdatePriority(int i) {
        if (this.zzf) {
            this.zzi = i;
        }
    }

    public final Task<Integer> startUpdateFlow(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions) {
        if (zzd(appUpdateInfo, appUpdateOptions)) {
            return Tasks.forResult(-1);
        }
        return Tasks.forException(new InstallException(-6));
    }

    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int i, Activity activity, int i2) {
        return zzd(appUpdateInfo, AppUpdateOptions.newBuilder(i).build());
    }

    public void unregisterListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.zza.zzc(installStateUpdatedListener);
    }

    public void userAcceptsUpdate() {
        if (this.zzl || this.zzm) {
            this.zzl = false;
            this.zzd = 1;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
        }
    }

    public void userCancelsDownload() {
        int i = this.zzd;
        if (i == 1 || i == 2) {
            this.zzd = 6;
            Integer num = 0;
            if (num.equals(this.zzo)) {
                zzc();
            }
            this.zzo = null;
            this.zzm = false;
            this.zzd = 0;
        }
    }

    public void userRejectsUpdate() {
        if (this.zzl || this.zzm) {
            this.zzl = false;
            this.zzm = false;
            this.zzo = null;
            this.zzd = 0;
        }
    }

    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int i, IntentSenderForResultStarter intentSenderForResultStarter, int i2) {
        return zzd(appUpdateInfo, AppUpdateOptions.newBuilder(i).build());
    }

    public void setUpdateAvailable(int i, int i2) {
        this.zzf = true;
        this.zzc.clear();
        this.zzc.add(Integer.valueOf(i2));
        this.zzg = i;
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions, int i) {
        return zzd(appUpdateInfo, appUpdateOptions);
    }

    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, ActivityResultLauncher<IntentSenderRequest> activityResultLauncher, AppUpdateOptions appUpdateOptions) {
        return zzd(appUpdateInfo, appUpdateOptions);
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, IntentSenderForResultStarter intentSenderForResultStarter, AppUpdateOptions appUpdateOptions, int i) {
        return zzd(appUpdateInfo, appUpdateOptions);
    }
}
