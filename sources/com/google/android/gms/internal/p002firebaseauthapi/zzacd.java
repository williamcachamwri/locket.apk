package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.internal.zzad;
import com.google.firebase.auth.internal.zzj;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacd  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzacd extends zzady<Void, zzj> {
    private final String zzu;
    private final String zzv;
    private final String zzw;
    private final String zzx;

    public final String zza() {
        return "reauthenticateWithEmailPassword";
    }

    public zzacd(String str, String str2, String str3, String str4) {
        super(2);
        Preconditions.checkNotEmpty(str, "email cannot be null or empty");
        Preconditions.checkNotEmpty(str2, "password cannot be null or empty");
        this.zzu = str;
        this.zzv = str2;
        this.zzw = str3;
        this.zzx = str4;
    }

    public final void zzb() {
        zzad zza = zzabj.zza(this.zzc, this.zzk);
        if (this.zzd.getUid().equalsIgnoreCase(zza.getUid())) {
            ((zzj) this.zze).zza(this.zzj, zza);
            zzb(null);
            return;
        }
        zza(new Status(FirebaseError.ERROR_USER_MISMATCH));
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zzb(this.zzu, this.zzv, this.zzw, this.zzx, this.zzb);
    }
}
