package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.internal.zzao;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class TotpMultiFactorGenerator {
    public static final String FACTOR_ID = "totp";

    public static Task<TotpSecret> generateSecret(MultiFactorSession multiFactorSession) {
        Preconditions.checkNotNull(multiFactorSession);
        zzao zzao = (zzao) multiFactorSession;
        return FirebaseAuth.getInstance(zzao.zza().zza()).zza(zzao);
    }

    public static TotpMultiFactorAssertion getAssertionForEnrollment(TotpSecret totpSecret, String str) {
        return new TotpMultiFactorAssertion((String) Preconditions.checkNotNull(str), (TotpSecret) Preconditions.checkNotNull(totpSecret), (String) null);
    }

    public static TotpMultiFactorAssertion getAssertionForSignIn(String str, String str2) {
        return new TotpMultiFactorAssertion((String) Preconditions.checkNotNull(str2), (TotpSecret) null, (String) Preconditions.checkNotNull(str));
    }

    private TotpMultiFactorGenerator() {
    }
}
