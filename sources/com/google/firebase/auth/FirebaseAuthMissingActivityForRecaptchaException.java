package com.google.firebase.auth;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class FirebaseAuthMissingActivityForRecaptchaException extends FirebaseAuthException {
    public FirebaseAuthMissingActivityForRecaptchaException() {
        super("ERROR_MISSING_ACTIVITY", "App verification failed - a valid Activity is required to complete the Recaptcha flow");
    }
}
