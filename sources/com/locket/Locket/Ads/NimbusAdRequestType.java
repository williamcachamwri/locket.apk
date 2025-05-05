package com.locket.Locket.Ads;

import io.sentry.ProfilingTraceData;

public enum NimbusAdRequestType {
    NORMAL(ProfilingTraceData.TRUNCATION_REASON_NORMAL),
    PREFETCH("prefetch");
    
    private final String rawValue;

    private NimbusAdRequestType(String str) {
        this.rawValue = str;
    }

    public String getRawValue() {
        return this.rawValue;
    }

    public static NimbusAdRequestType fromRawValue(String str) {
        for (NimbusAdRequestType nimbusAdRequestType : values()) {
            if (nimbusAdRequestType.rawValue.equals(str)) {
                return nimbusAdRequestType;
            }
        }
        throw new IllegalArgumentException("No enum constant with value " + str);
    }
}
