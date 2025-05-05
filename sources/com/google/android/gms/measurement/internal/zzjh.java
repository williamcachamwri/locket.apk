package com.google.android.gms.measurement.internal;

import expo.modules.interfaces.permissions.PermissionsResponse;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.1.2 */
public enum zzjh {
    UNINITIALIZED("uninitialized"),
    POLICY("eu_consent_policy"),
    DENIED("denied"),
    GRANTED(PermissionsResponse.GRANTED_KEY);
    
    private final String zzf;

    public final String toString() {
        return this.zzf;
    }

    private zzjh(String str) {
        this.zzf = str;
    }
}
