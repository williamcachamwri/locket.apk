package com.google.firebase.appcheck.debug.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

public class StorageHelper {
    static final String DEBUG_SECRET_KEY = "com.google.firebase.appcheck.debug.DEBUG_SECRET";
    static final String PREFS_TEMPLATE = "com.google.firebase.appcheck.debug.store.%s";
    private final SharedPreferences sharedPreferences;

    public StorageHelper(Context context, String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(str);
        this.sharedPreferences = context.getSharedPreferences(String.format(PREFS_TEMPLATE, new Object[]{str}), 0);
    }

    public void saveDebugSecret(String str) {
        this.sharedPreferences.edit().putString(DEBUG_SECRET_KEY, str).apply();
    }

    public String retrieveDebugSecret() {
        return this.sharedPreferences.getString(DEBUG_SECRET_KEY, (String) null);
    }
}
