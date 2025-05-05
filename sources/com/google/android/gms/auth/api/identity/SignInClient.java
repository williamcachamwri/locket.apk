package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.content.Intent;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-auth@@21.2.0 */
public interface SignInClient extends HasApiKey<zbu> {
    @Deprecated
    Task<BeginSignInResult> beginSignIn(BeginSignInRequest beginSignInRequest);

    String getPhoneNumberFromIntent(Intent intent) throws ApiException;

    Task<PendingIntent> getPhoneNumberHintIntent(GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest);

    @Deprecated
    SignInCredential getSignInCredentialFromIntent(Intent intent) throws ApiException;

    @Deprecated
    Task<PendingIntent> getSignInIntent(GetSignInIntentRequest getSignInIntentRequest);

    @Deprecated
    Task<Void> signOut();
}
