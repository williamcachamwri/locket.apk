package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-iid@@21.1.0 */
class Store {
    final Context context;
    final SharedPreferences store;
    private final Map<String, Long> subtypeCreationTimes = new ArrayMap();

    /* compiled from: com.google.firebase:firebase-iid@@21.1.0 */
    static class Token {
        private static final long REFRESH_PERIOD_MILLIS = TimeUnit.DAYS.toMillis(7);
        final String appVersion;
        final long timestamp;
        final String token;

        private Token(String str, String str2, long j) {
            this.token = str;
            this.appVersion = str2;
            this.timestamp = j;
        }

        static String encode(String str, String str2, long j) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("token", str);
                jSONObject.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, str2);
                jSONObject.put("timestamp", j);
                return jSONObject.toString();
            } catch (JSONException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
                sb.append("Failed to encode token: ");
                sb.append(valueOf);
                SentryLogcatAdapter.w("FirebaseInstanceId", sb.toString());
                return null;
            }
        }

        static String getTokenOrNull(Token token2) {
            if (token2 == null) {
                return null;
            }
            return token2.token;
        }

        static Token parse(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            if (!str.startsWith("{")) {
                return new Token(str, (String) null, 0);
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new Token(jSONObject.getString("token"), jSONObject.getString(RemoteConfigConstants.RequestFieldKey.APP_VERSION), jSONObject.getLong("timestamp"));
            } catch (JSONException e) {
                String valueOf = String.valueOf(e);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 23);
                sb.append("Failed to parse token: ");
                sb.append(valueOf);
                SentryLogcatAdapter.w("FirebaseInstanceId", sb.toString());
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean needsRefresh(String str) {
            return System.currentTimeMillis() > this.timestamp + REFRESH_PERIOD_MILLIS || !str.equals(this.appVersion);
        }
    }

    public Store(Context context2) {
        this.context = context2;
        this.store = context2.getSharedPreferences("com.google.android.gms.appid", 0);
        checkForRestore("com.google.android.gms.appid-no-backup");
    }

    private void checkForRestore(String str) {
        File file = new File(ContextCompat.getNoBackupFilesDir(this.context), "com.google.android.gms.appid-no-backup");
        if (!file.exists()) {
            try {
                if (file.createNewFile() && !isEmpty()) {
                    Log.i("FirebaseInstanceId", "App restored, clearing state");
                    deleteAll();
                }
            } catch (IOException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.d("FirebaseInstanceId", valueOf.length() != 0 ? "Error creating file in no backup dir: ".concat(valueOf) : new String("Error creating file in no backup dir: "));
                }
            }
        }
    }

    static String createSubtypeInfoKey(String str, String str2) {
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 6);
        sb.append(str);
        sb.append("|S|cre");
        return sb.toString();
    }

    private String createTokenKey(String str, String str2, String str3) {
        int length = String.valueOf(str).length();
        StringBuilder sb = new StringBuilder(length + 4 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append("|T|");
        sb.append(str2);
        sb.append("|");
        sb.append(str3);
        return sb.toString();
    }

    private long getCreationTimeFromSharedPreferences(String str) {
        String string = this.store.getString(createSubtypeInfoKey(str, "cre"), (String) null);
        if (string == null) {
            return 0;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    private long writeCreationTimeToSharedPreferences(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.store.contains(createSubtypeInfoKey(str, "cre"))) {
            return getCreationTimeFromSharedPreferences(str);
        }
        SharedPreferences.Editor edit = this.store.edit();
        edit.putString(createSubtypeInfoKey(str, "cre"), String.valueOf(currentTimeMillis));
        edit.commit();
        return currentTimeMillis;
    }

    public synchronized void deleteAll() {
        this.subtypeCreationTimes.clear();
        this.store.edit().clear().commit();
    }

    public synchronized void deleteToken(String str, String str2, String str3) {
        String createTokenKey = createTokenKey(str, str2, str3);
        SharedPreferences.Editor edit = this.store.edit();
        edit.remove(createTokenKey);
        edit.commit();
    }

    public synchronized long getCreationTime(String str) {
        Long l = this.subtypeCreationTimes.get(str);
        if (l != null) {
            return l.longValue();
        }
        return getCreationTimeFromSharedPreferences(str);
    }

    public synchronized Token getToken(String str, String str2, String str3) {
        return Token.parse(this.store.getString(createTokenKey(str, str2, str3), (String) null));
    }

    public synchronized boolean isEmpty() {
        return this.store.getAll().isEmpty();
    }

    public synchronized void saveToken(String str, String str2, String str3, String str4, String str5) {
        String encode = Token.encode(str4, str5, System.currentTimeMillis());
        if (encode != null) {
            SharedPreferences.Editor edit = this.store.edit();
            edit.putString(createTokenKey(str, str2, str3), encode);
            edit.commit();
        }
    }

    public synchronized long setCreationTime(String str) {
        long writeCreationTimeToSharedPreferences;
        writeCreationTimeToSharedPreferences = writeCreationTimeToSharedPreferences(str);
        this.subtypeCreationTimes.put(str, Long.valueOf(writeCreationTimeToSharedPreferences));
        return writeCreationTimeToSharedPreferences;
    }
}
