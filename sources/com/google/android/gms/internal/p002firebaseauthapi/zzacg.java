package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacg  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzacg extends zzady<Void, Void> {
    private final zzagy zzu;

    public final String zza() {
        return "revokeAccessToken";
    }

    public zzacg(String str, String str2, String str3, String str4) {
        super(15);
        this.zzu = zzagy.zzg().zzd(str).zza(str2).zzc(str4).zzb(str3).zza(zzaga.ACCESS_TOKEN).zza();
    }

    public final void zzb() {
        zzb(null);
    }

    public final void zza(TaskCompletionSource<Void> taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzu, (zzadf) this.zzb);
    }
}
