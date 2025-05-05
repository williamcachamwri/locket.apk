package com.locket.Locket.Overlays;

import com.facebook.hermes.intl.Constants;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.android.core.SentryLogcatAdapter;

public enum CaptionType {
    LOCATION(FirebaseAnalytics.Param.LOCATION),
    LOCKET_COUNT("locket_count"),
    MUSIC("music"),
    STANDARD(Constants.COLLATION_STANDARD),
    STATIC_CONTENT("static_content"),
    TIME("time"),
    WEATHER("weather"),
    STATUS("status");
    
    private String type;

    private CaptionType(String str) {
        this.type = str;
    }

    public static CaptionType getByType(String str) {
        if (str != null && !str.isEmpty()) {
            for (CaptionType captionType : values()) {
                if (captionType.type.equals(str)) {
                    return captionType;
                }
            }
            SentryLogcatAdapter.e("CaptionType", "Invalid caption type: " + str);
        }
        return null;
    }
}
