package com.google.firebase.auth;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public interface AdditionalUserInfo extends SafeParcelable {
    Map<String, Object> getProfile();

    String getProviderId();

    String getUsername();

    boolean isNewUser();
}
