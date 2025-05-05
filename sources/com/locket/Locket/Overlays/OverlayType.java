package com.locket.Locket.Overlays;

import io.sentry.android.core.SentryLogcatAdapter;

public enum OverlayType {
    CAPTION("caption");
    
    private String type;

    private OverlayType(String str) {
        this.type = str;
    }

    public static OverlayType getByType(String str) {
        if (str != null && !str.isEmpty()) {
            for (OverlayType overlayType : values()) {
                if (overlayType.type.equals(str)) {
                    return overlayType;
                }
            }
            SentryLogcatAdapter.e("CaptionType", "Invalid caption type: " + str);
        }
        return null;
    }
}
