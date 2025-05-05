package com.locket.Locket.Overlays;

import io.sentry.android.core.SentryLogcatAdapter;

public enum IconType {
    EMOJI("emoji"),
    SF_SYMBOL("sf_symbol"),
    IMAGE("image");
    
    private String iconType;

    private IconType(String str) {
        this.iconType = str;
    }

    public static IconType getByIconType(String str) {
        if (str != null && !str.isEmpty()) {
            for (IconType iconType2 : values()) {
                if (iconType2.iconType.equals(str)) {
                    return iconType2;
                }
            }
            SentryLogcatAdapter.e("IconType", "Invalid icon type: " + str);
        }
        return null;
    }
}
