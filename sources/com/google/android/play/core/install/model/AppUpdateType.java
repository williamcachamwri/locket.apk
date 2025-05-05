package com.google.android.play.core.install.model;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
/* compiled from: com.google.android.play:app-update@@2.1.0 */
public @interface AppUpdateType {
    public static final int FLEXIBLE = 0;
    public static final int IMMEDIATE = 1;
}
