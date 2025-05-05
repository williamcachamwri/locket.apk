package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzaef  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzaef<ResultT, CallbackT> implements zzadw<ResultT> {
    private final zzady<ResultT, CallbackT> zza;
    private final TaskCompletionSource<ResultT> zzb;

    public zzaef(zzady<ResultT, CallbackT> zzady, TaskCompletionSource<ResultT> taskCompletionSource) {
        this.zza = zzady;
        this.zzb = taskCompletionSource;
    }

    public final void zza(ResultT resultt, Status status) {
        FirebaseUser firebaseUser;
        Preconditions.checkNotNull(this.zzb, "completion source cannot be null");
        if (status == null) {
            this.zzb.setResult(resultt);
        } else if (this.zza.zzq != null) {
            TaskCompletionSource<ResultT> taskCompletionSource = this.zzb;
            FirebaseAuth instance = FirebaseAuth.getInstance(this.zza.zzc);
            zzzl zzzl = this.zza.zzq;
            if ("reauthenticateWithCredential".equals(this.zza.zza()) || "reauthenticateWithCredentialWithData".equals(this.zza.zza())) {
                firebaseUser = this.zza.zzd;
            } else {
                firebaseUser = null;
            }
            taskCompletionSource.setException(zzadg.zza(instance, zzzl, firebaseUser));
        } else if (this.zza.zzn != null) {
            this.zzb.setException(zzadg.zza(status, this.zza.zzn, this.zza.zzo, this.zza.zzp));
        } else {
            this.zzb.setException(zzadg.zza(status));
        }
    }
}
