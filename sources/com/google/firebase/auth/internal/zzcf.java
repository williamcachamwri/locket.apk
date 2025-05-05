package com.google.firebase.auth.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzcf {
    private static final zzcf zza = new zzcf();
    private final zzbm zzb;
    private final zzav zzc;

    public final Task<AuthResult> zza() {
        return this.zzb.zza();
    }

    public final Task<String> zzb() {
        return this.zzb.zzb();
    }

    public static zzcf zzc() {
        return zza;
    }

    private zzcf() {
        this(zzbm.zzc(), zzav.zza());
    }

    private zzcf(zzbm zzbm, zzav zzav) {
        this.zzb = zzbm;
        this.zzc = zzav;
    }

    public final void zza(Context context) {
        this.zzb.zza(context);
    }

    public final void zza(FirebaseAuth firebaseAuth) {
        this.zzb.zza(firebaseAuth);
    }

    public final boolean zza(Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth) {
        return this.zzc.zza(activity, taskCompletionSource, firebaseAuth);
    }

    public final boolean zza(Activity activity, TaskCompletionSource<AuthResult> taskCompletionSource, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        return this.zzc.zza(activity, taskCompletionSource, firebaseAuth, firebaseUser);
    }
}
