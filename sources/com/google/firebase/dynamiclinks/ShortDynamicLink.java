package com.google.firebase.dynamiclinks;

import android.net.Uri;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@Deprecated
public interface ShortDynamicLink {

    @Retention(RetentionPolicy.SOURCE)
    public @interface Suffix {
        public static final int SHORT = 2;
        public static final int UNGUESSABLE = 1;
    }

    @Deprecated
    public interface Warning {
        @Deprecated
        String getCode();

        @Deprecated
        String getMessage();
    }

    @Deprecated
    Uri getPreviewLink();

    @Deprecated
    Uri getShortLink();

    @Deprecated
    List<? extends Warning> getWarnings();
}
