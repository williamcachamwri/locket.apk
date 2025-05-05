package com.google.android.recaptcha.internal;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzar {
    public static final String zza(ContentResolver contentResolver) {
        Cursor query = contentResolver.query(Uri.parse("content://com.google.android.gsf.gservices"), (String[]) null, (String) null, new String[]{"android_id"}, (String) null);
        if (query == null || !query.moveToFirst() || query.getColumnCount() < 2) {
            return "";
        }
        String valueOf = String.valueOf(Long.parseLong(query.getString(1)));
        query.close();
        return valueOf;
    }
}
