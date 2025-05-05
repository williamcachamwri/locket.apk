package com.google.firebase.appcheck.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.internal.util.Logger;
import com.google.firebase.components.Lazy;

public class StorageHelper {
    static final String PREFS_TEMPLATE = "com.google.firebase.appcheck.store.%s";
    static final String TOKEN_KEY = "com.google.firebase.appcheck.APP_CHECK_TOKEN";
    static final String TOKEN_TYPE_KEY = "com.google.firebase.appcheck.TOKEN_TYPE";
    private static final Logger logger = new Logger("StorageHelper");
    private Lazy<SharedPreferences> sharedPreferences;

    enum TokenType {
        DEFAULT_APP_CHECK_TOKEN,
        UNKNOWN_APP_CHECK_TOKEN
    }

    public StorageHelper(Context context, String str) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotEmpty(str);
        this.sharedPreferences = new Lazy<>(new StorageHelper$$ExternalSyntheticLambda0(context, String.format(PREFS_TEMPLATE, new Object[]{str})));
    }

    public void saveAppCheckToken(AppCheckToken appCheckToken) {
        if (appCheckToken instanceof DefaultAppCheckToken) {
            this.sharedPreferences.get().edit().putString(TOKEN_KEY, ((DefaultAppCheckToken) appCheckToken).serializeTokenToString()).putString(TOKEN_TYPE_KEY, TokenType.DEFAULT_APP_CHECK_TOKEN.name()).apply();
        } else {
            this.sharedPreferences.get().edit().putString(TOKEN_KEY, appCheckToken.getToken()).putString(TOKEN_TYPE_KEY, TokenType.UNKNOWN_APP_CHECK_TOKEN.name()).apply();
        }
    }

    public AppCheckToken retrieveAppCheckToken() {
        String string = this.sharedPreferences.get().getString(TOKEN_TYPE_KEY, (String) null);
        String string2 = this.sharedPreferences.get().getString(TOKEN_KEY, (String) null);
        if (!(string == null || string2 == null)) {
            try {
                int i = AnonymousClass1.$SwitchMap$com$google$firebase$appcheck$internal$StorageHelper$TokenType[TokenType.valueOf(string).ordinal()];
                if (i == 1) {
                    return DefaultAppCheckToken.deserializeTokenFromJsonString(string2);
                }
                if (i == 2) {
                    return DefaultAppCheckToken.constructFromRawToken(string2);
                }
                logger.e("Reached unreachable section in #retrieveAppCheckToken()");
                return null;
            } catch (IllegalArgumentException e) {
                logger.e("Failed to parse TokenType of stored token  with type [" + string + "] with exception: " + e.getMessage());
                clearSharedPrefs();
            }
        }
        return null;
    }

    /* renamed from: com.google.firebase.appcheck.internal.StorageHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$appcheck$internal$StorageHelper$TokenType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.google.firebase.appcheck.internal.StorageHelper$TokenType[] r0 = com.google.firebase.appcheck.internal.StorageHelper.TokenType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$appcheck$internal$StorageHelper$TokenType = r0
                com.google.firebase.appcheck.internal.StorageHelper$TokenType r1 = com.google.firebase.appcheck.internal.StorageHelper.TokenType.DEFAULT_APP_CHECK_TOKEN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$appcheck$internal$StorageHelper$TokenType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.appcheck.internal.StorageHelper$TokenType r1 = com.google.firebase.appcheck.internal.StorageHelper.TokenType.UNKNOWN_APP_CHECK_TOKEN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.appcheck.internal.StorageHelper.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void clearSharedPrefs() {
        this.sharedPreferences.get().edit().remove(TOKEN_KEY).remove(TOKEN_TYPE_KEY).apply();
    }
}
