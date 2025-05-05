package com.google.android.gms.auth.api.identity;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p001authapi.zbaf;
import com.google.android.gms.internal.p001authapi.zbap;
import com.google.android.gms.internal.p001authapi.zbz;

/* compiled from: com.google.android.gms:play-services-auth@@21.2.0 */
public final class Identity {
    private Identity() {
    }

    public static AuthorizationClient getAuthorizationClient(Activity activity) {
        return new zbz((Activity) Preconditions.checkNotNull(activity), new zbb((zba) null).zbb());
    }

    public static CredentialSavingClient getCredentialSavingClient(Activity activity) {
        return new zbaf((Activity) Preconditions.checkNotNull(activity), new zbh());
    }

    public static SignInClient getSignInClient(Activity activity) {
        return new zbap((Activity) Preconditions.checkNotNull(activity), new zbu());
    }

    public static AuthorizationClient getAuthorizationClient(Context context) {
        return new zbz((Context) Preconditions.checkNotNull(context), new zbb((zba) null).zbb());
    }

    public static CredentialSavingClient getCredentialSavingClient(Context context) {
        return new zbaf((Context) Preconditions.checkNotNull(context), new zbh());
    }

    public static SignInClient getSignInClient(Context context) {
        return new zbap((Context) Preconditions.checkNotNull(context), new zbu());
    }
}
