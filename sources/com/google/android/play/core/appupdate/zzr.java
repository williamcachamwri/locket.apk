package com.google.android.play.core.appupdate;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.core.appupdate.internal.zzab;
import com.google.android.play.core.appupdate.internal.zzi;
import com.google.android.play.core.appupdate.internal.zzm;
import com.google.android.play.core.appupdate.internal.zzs;
import com.google.android.play.core.appupdate.internal.zzx;
import com.google.android.play.core.appupdate.internal.zzz;
import com.google.android.play.core.install.InstallException;
import io.sentry.SentryBaseEvent;
import io.sentry.protocol.SentryStackFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
final class zzr {
    /* access modifiers changed from: private */
    public static final zzm zzb = new zzm("AppUpdateService");
    private static final Intent zzc = new Intent("com.google.android.play.core.install.BIND_UPDATE_SERVICE").setPackage("com.android.vending");
    zzx zza;
    /* access modifiers changed from: private */
    public final String zzd;
    private final Context zze;
    private final zzt zzf;

    zzr(Context context, zzt zzt) {
        this.zzd = context.getPackageName();
        this.zze = context;
        this.zzf = zzt;
        if (zzab.zza(context)) {
            this.zza = new zzx(zzz.zza(context), zzb, "AppUpdateService", zzc, zzl.zza, (zzs) null);
        }
    }

    static /* bridge */ /* synthetic */ Bundle zzb(zzr zzr, String str) {
        Integer num;
        Bundle bundle = new Bundle();
        bundle.putAll(zzi());
        bundle.putString("package.name", str);
        try {
            num = Integer.valueOf(zzr.zze.getPackageManager().getPackageInfo(zzr.zze.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException unused) {
            zzb.zzb("The current version of the app could not be retrieved", new Object[0]);
            num = null;
        }
        if (num != null) {
            bundle.putInt("app.version.code", num.intValue());
        }
        return bundle;
    }

    static /* bridge */ /* synthetic */ AppUpdateInfo zzf(zzr zzr, Bundle bundle, String str) {
        Integer num;
        Bundle bundle2 = bundle;
        int i = bundle2.getInt("version.code", -1);
        int i2 = bundle2.getInt("update.availability");
        int i3 = bundle2.getInt("install.status", 0);
        if (bundle2.getInt("client.version.staleness", -1) == -1) {
            num = null;
        } else {
            num = Integer.valueOf(bundle2.getInt("client.version.staleness"));
        }
        int i4 = bundle2.getInt("in.app.update.priority", 0);
        long j = bundle2.getLong("bytes.downloaded");
        HashMap hashMap = r9;
        HashMap hashMap2 = new HashMap();
        hashMap2.put("blocking.destructive.intent", zzk(bundle2.getIntegerArrayList("update.precondition.failures:blocking.destructive.intent")));
        hashMap2.put("nonblocking.destructive.intent", zzk(bundle2.getIntegerArrayList("update.precondition.failures:nonblocking.destructive.intent")));
        hashMap2.put("blocking.intent", zzk(bundle2.getIntegerArrayList("update.precondition.failures:blocking.intent")));
        hashMap2.put("nonblocking.intent", zzk(bundle2.getIntegerArrayList("update.precondition.failures:nonblocking.intent")));
        return AppUpdateInfo.zzb(str, i, i2, i3, num, i4, j, bundle2.getLong("total.bytes.to.download"), bundle2.getLong("additional.size.required"), zzr.zzf.zza(), (PendingIntent) bundle2.getParcelable("blocking.intent"), (PendingIntent) bundle2.getParcelable("nonblocking.intent"), (PendingIntent) bundle2.getParcelable("blocking.destructive.intent"), (PendingIntent) bundle2.getParcelable("nonblocking.destructive.intent"), hashMap);
    }

    /* access modifiers changed from: private */
    public static Bundle zzi() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        Map zza2 = zzi.zza("app_update");
        bundle2.putInt("playcore_version_code", ((Integer) zza2.get(SentryBaseEvent.DEFAULT_PLATFORM)).intValue());
        if (zza2.containsKey(SentryStackFrame.JsonKeys.NATIVE)) {
            bundle2.putInt("playcore_native_version", ((Integer) zza2.get(SentryStackFrame.JsonKeys.NATIVE)).intValue());
        }
        if (zza2.containsKey("unity")) {
            bundle2.putInt("playcore_unity_version", ((Integer) zza2.get("unity")).intValue());
        }
        bundle.putAll(bundle2);
        bundle.putInt("playcore.version.code", 11004);
        return bundle;
    }

    private static Task zzj() {
        zzb.zzb("onError(%d)", -9);
        return Tasks.forException(new InstallException(-9));
    }

    private static HashSet zzk(ArrayList arrayList) {
        HashSet hashSet = new HashSet();
        if (arrayList != null) {
            hashSet.addAll(arrayList);
        }
        return hashSet;
    }

    public final Task zzd(String str) {
        if (this.zza == null) {
            return zzj();
        }
        zzb.zzd("completeUpdate(%s)", str);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zza.zzs(new zzn(this, taskCompletionSource, taskCompletionSource, str), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    public final Task zze(String str) {
        if (this.zza == null) {
            return zzj();
        }
        zzb.zzd("requestUpdateInfo(%s)", str);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zza.zzs(new zzm(this, taskCompletionSource, str, taskCompletionSource), taskCompletionSource);
        return taskCompletionSource.getTask();
    }
}
