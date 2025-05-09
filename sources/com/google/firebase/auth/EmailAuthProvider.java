package com.google.firebase.auth;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class EmailAuthProvider {
    public static final String EMAIL_LINK_SIGN_IN_METHOD = "emailLink";
    public static final String EMAIL_PASSWORD_SIGN_IN_METHOD = "password";
    public static final String PROVIDER_ID = "password";

    public static AuthCredential getCredential(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        return new EmailAuthCredential(str, str2);
    }

    public static AuthCredential getCredentialWithLink(String str, String str2) {
        if (EmailAuthCredential.zza(str2)) {
            return new EmailAuthCredential(str, (String) null, str2, (String) null, false);
        }
        throw new IllegalArgumentException("Given link is not a valid email link. Please use FirebaseAuth#isSignInWithEmailLink(String) to determine this before calling this function");
    }

    private EmailAuthProvider() {
    }
}
