package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p002firebaseauthapi.zzahh;
import com.google.android.gms.internal.p002firebaseauthapi.zzahn;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.internal.zzcb;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzt implements Continuation<zzahh, Task<TotpSecret>> {
    private final /* synthetic */ FirebaseAuth zza;

    public final /* synthetic */ Object then(Task task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException((Exception) Preconditions.checkNotNull(task.getException()));
        }
        zzahh zzahh = (zzahh) task.getResult();
        if (zzahh instanceof zzahn) {
            zzahn zzahn = (zzahn) zzahh;
            return Tasks.forResult(new zzcb(Preconditions.checkNotEmpty(zzahn.zzf()), Preconditions.checkNotEmpty(zzahn.zze()), zzahn.zzc(), zzahn.zzb(), zzahn.zzd(), Preconditions.checkNotEmpty(zzahn.zza()), this.zza));
        }
        throw new IllegalArgumentException("Response should be an instance of StartTotpMfaEnrollmentResponse but was " + zzahh.getClass().getName() + ".");
    }

    zzt(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }
}
