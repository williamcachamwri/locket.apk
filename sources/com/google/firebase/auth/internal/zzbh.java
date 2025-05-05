package com.google.firebase.auth.internal;

import android.net.Uri;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final /* synthetic */ class zzbh implements Continuation {
    private /* synthetic */ Uri zza;

    public /* synthetic */ zzbh(Uri uri) {
        this.zza = uri;
    }

    public final Object then(Task task) {
        return GenericIdpActivity.zza(this.zza, task);
    }
}
