package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.internal.zzao;
import com.google.firebase.auth.internal.zzj;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacq  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzacq extends zzady<Void, zzj> {
    private final String zzaa;
    private final String zzab;
    private final String zzac;
    private final boolean zzad;
    private final String zzu;
    private final String zzv;
    private final String zzw;
    private final long zzx;
    private final boolean zzy;
    private final boolean zzz;

    public final String zza() {
        return "startMfaEnrollment";
    }

    public final void zzb() {
    }

    public zzacq(zzao zzao, String str, String str2, long j, boolean z, boolean z2, String str3, String str4, String str5, boolean z3) {
        super(8);
        Preconditions.checkNotNull(zzao);
        Preconditions.checkNotEmpty(str);
        this.zzu = Preconditions.checkNotEmpty(zzao.zzb());
        this.zzv = str;
        this.zzw = str2;
        this.zzx = j;
        this.zzy = z;
        this.zzz = z2;
        this.zzaa = str3;
        this.zzab = str4;
        this.zzac = str5;
        this.zzad = z3;
    }

    public final void zza(TaskCompletionSource taskCompletionSource, zzadh zzadh) {
        this.zzg = new zzaef(this, taskCompletionSource);
        zzadh.zza(this.zzu, this.zzv, this.zzw, this.zzx, this.zzy, this.zzz, this.zzaa, this.zzab, this.zzac, this.zzad, this.zzb);
    }
}
