package com.google.firebase.auth.internal;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzag;
import com.google.android.gms.internal.p002firebaseauthapi.zzagm;
import com.google.android.gms.internal.p002firebaseauthapi.zzx;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.recaptcha.RecaptchaTasksClient;
import java.util.List;
import org.apache.commons.io.IOUtils;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzby implements Continuation<zzagm, Task<RecaptchaTasksClient>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzbv zzb;

    public final /* synthetic */ Object then(Task task) throws Exception {
        String str;
        if (!task.isSuccessful()) {
            return Tasks.forException(new zzbw((String) Preconditions.checkNotNull(((Exception) Preconditions.checkNotNull(task.getException())).getMessage())));
        }
        zzagm zzagm = (zzagm) task.getResult();
        String zza2 = zzagm.zza();
        if (zzag.zzc(zza2)) {
            return Tasks.forException(new zzbw("No Recaptcha Enterprise siteKey configured for tenant/project " + this.zza));
        }
        List<String> zza3 = zzx.zza((char) IOUtils.DIR_SEPARATOR_UNIX).zza((CharSequence) zza2);
        if (zza3.size() != 4) {
            str = null;
        } else {
            str = zza3.get(3);
        }
        if (TextUtils.isEmpty(str)) {
            return Tasks.forException(new Exception("Invalid siteKey format " + zza2));
        }
        if (Log.isLoggable("RecaptchaHandler", 4)) {
            Log.i("RecaptchaHandler", "Successfully obtained site key for tenant " + this.zza);
        }
        Task<RecaptchaTasksClient> zza4 = this.zzb.zzb.zza((Application) this.zzb.zza.getApplicationContext(), str);
        zzbv.zza(this.zzb, zzagm, zza4, this.zza);
        return zza4;
    }

    zzby(zzbv zzbv, String str) {
        this.zza = str;
        this.zzb = zzbv;
    }
}
