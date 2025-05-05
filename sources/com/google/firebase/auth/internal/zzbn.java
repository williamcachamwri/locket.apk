package com.google.firebase.auth.internal;

import android.net.Uri;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final /* synthetic */ class zzbn implements Continuation {
    private /* synthetic */ Uri zza;

    public /* synthetic */ zzbn(Uri uri) {
        this.zza = uri;
    }

    public final Object then(Task task) {
        return RecaptchaActivity.zza(this.zza, task);
    }
}
