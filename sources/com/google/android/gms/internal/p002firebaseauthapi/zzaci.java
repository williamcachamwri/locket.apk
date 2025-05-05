package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.internal.zzj;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaci  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzaci extends zzady<Void, zzj> {
    private final zzagg zzu;
    private final String zzv;

    public final String zza() {
        return this.zzv;
    }

    public zzaci(String str, ActionCodeSettings actionCodeSettings, String str2, String str3, String str4) {
        super(4);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        zzagg zzagg = new zzagg(actionCodeSettings.zza());
        this.zzu = zzagg;
        zzagg.zzb(str);
        zzagg.zza(actionCodeSettings);
        zzagg.zzc(str2);
        zzagg.zza(str3);
        this.zzv = str4;
    }

    public final void zzb() {
        zzb(null);
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zzb(this.zzu, (zzadf) this.zzb);
    }
}
