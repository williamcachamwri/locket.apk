package com.locket.Locket.Ads;

import com.google.firebase.analytics.FirebaseAnalytics;

public enum NimbusAdResponseType {
    SUCCESS(FirebaseAnalytics.Param.SUCCESS),
    ERROR("error"),
    CANCELLATION("cancellation");
    
    private final String rawValue;

    private NimbusAdResponseType(String str) {
        this.rawValue = str;
    }

    public String getRawValue() {
        return this.rawValue;
    }

    public static NimbusAdResponseType fromRawValue(String str) {
        for (NimbusAdResponseType nimbusAdResponseType : values()) {
            if (nimbusAdResponseType.rawValue.equals(str)) {
                return nimbusAdResponseType;
            }
        }
        throw new IllegalArgumentException("No enum constant with value " + str);
    }
}
