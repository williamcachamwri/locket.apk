package com.google.firebase.auth.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorSession;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class zzah extends MultiFactor {
    /* access modifiers changed from: private */
    public final zzad zza;

    public final Task<Void> enroll(MultiFactorAssertion multiFactorAssertion, String str) {
        Preconditions.checkNotNull(multiFactorAssertion);
        zzad zzad = this.zza;
        return FirebaseAuth.getInstance(zzad.zza()).zza((FirebaseUser) zzad, multiFactorAssertion, str);
    }

    public final Task<MultiFactorSession> getSession() {
        return this.zza.getIdToken(false).continueWithTask(new zzak(this));
    }

    public final Task<Void> unenroll(MultiFactorInfo multiFactorInfo) {
        Preconditions.checkNotNull(multiFactorInfo);
        return unenroll(multiFactorInfo.getUid());
    }

    public final Task<Void> unenroll(String str) {
        Preconditions.checkNotEmpty(str);
        zzad zzad = this.zza;
        return FirebaseAuth.getInstance(zzad.zza()).zza((FirebaseUser) zzad, str);
    }

    public final List<MultiFactorInfo> getEnrolledFactors() {
        return this.zza.zzi();
    }

    public zzah(zzad zzad) {
        Preconditions.checkNotNull(zzad);
        this.zza = zzad;
    }
}
