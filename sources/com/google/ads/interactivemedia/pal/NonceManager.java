package com.google.ads.interactivemedia.pal;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import com.google.android.gms.internal.pal.zzagc;
import com.google.android.gms.internal.pal.zzfm;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class NonceManager {
    static final zzagc zza = zzagc.zzc(3);
    static final zzagc zzb = zzagc.zzc(5);
    public static final /* synthetic */ int zzc = 0;
    /* access modifiers changed from: private */
    public final Context zzd;
    private final ExecutorService zze;
    private final Task zzf;
    private final zzax zzg;
    private final zzav zzh;
    private final String zzi;
    private boolean zzj = false;
    private String zzk;

    NonceManager(Context context, Handler handler, ExecutorService executorService, Task task, zzax zzax, String str) {
        this.zzd = context;
        this.zze = executorService;
        this.zzf = task;
        this.zzg = zzax;
        this.zzh = new zzav(handler, zzb);
        this.zzi = str;
    }

    static /* bridge */ /* synthetic */ Activity zza(NonceManager nonceManager) {
        Context context = nonceManager.zzd;
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return null;
    }

    public String getNonce() {
        return this.zzi;
    }

    public void sendAdClick() {
        Tasks.withTimeout(this.zzf.continueWith(this.zze, new zzan(this)), zza.zzd(), TimeUnit.MILLISECONDS).continueWith(this.zze, new zzao(this));
    }

    @Deprecated
    public void sendAdImpression() {
    }

    public void sendAdTouch(MotionEvent motionEvent) {
        Tasks.withTimeout(this.zzf.continueWith(this.zze, new zzal(motionEvent)), zza.zzd(), TimeUnit.MILLISECONDS).continueWith(this.zze, new zzam(this));
    }

    public void sendPlaybackEnd() {
        this.zzh.zzd();
        if (this.zzj) {
            this.zzj = false;
            this.zzg.zza(8, this.zzk);
        }
    }

    public void sendPlaybackStart() {
        if (!this.zzj) {
            this.zzj = true;
            Task withTimeout = Tasks.withTimeout(this.zzf.continueWith(this.zze, new zzas(this)), zza.zzd(), TimeUnit.MILLISECONDS);
            withTimeout.continueWith(this.zze, new zzap(this));
            withTimeout.continueWith(new zzaq(this));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzc(Task task) throws Exception {
        return ((zzfm) task.getResult()).zza(this.zzd, "");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzd(Task task) throws Exception {
        this.zzg.zza(4, task.isSuccessful() ? (String) task.getResult() : null);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zze(Task task) throws Exception {
        this.zzg.zza(5, (String) null);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzf(Task task) throws Exception {
        String str = task.isSuccessful() ? (String) task.getResult() : null;
        this.zzk = str;
        this.zzg.zza(6, str);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzg(Task task) throws Exception {
        if (!this.zzj) {
            return null;
        }
        this.zzh.zzc(new zzar(this));
        return null;
    }
}
