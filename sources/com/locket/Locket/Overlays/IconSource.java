package com.locket.Locket.Overlays;

import com.google.android.gms.common.internal.ImagesContract;
import io.sentry.android.core.SentryLogcatAdapter;

public enum IconSource {
    LOCAL(ImagesContract.LOCAL),
    REMOTE("remote");
    
    private String source;

    private IconSource(String str) {
        this.source = str;
    }

    public static IconSource getBySource(String str) {
        if (str != null && !str.isEmpty()) {
            for (IconSource iconSource : values()) {
                if (iconSource.source.equals(str)) {
                    return iconSource;
                }
            }
            SentryLogcatAdapter.e("IconSource", "Invalid icon source: " + str);
        }
        return null;
    }
}
