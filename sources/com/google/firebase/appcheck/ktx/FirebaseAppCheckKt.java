package com.google.firebase.appcheck.ktx;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.AppCheckToken;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.ktx.Firebase;
import io.sentry.protocol.App;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\tH\u0002\u001a\r\u0010\n\u001a\u00020\u000b*\u00020\tH\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\f"}, d2 = {"appCheck", "Lcom/google/firebase/appcheck/FirebaseAppCheck;", "Lcom/google/firebase/ktx/Firebase;", "getAppCheck", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/appcheck/FirebaseAppCheck;", "app", "Lcom/google/firebase/FirebaseApp;", "component1", "", "Lcom/google/firebase/appcheck/AppCheckToken;", "component2", "", "com.google.firebase-appcheck-firebase-appcheck"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: FirebaseAppCheck.kt */
public final class FirebaseAppCheckKt {
    public static final FirebaseAppCheck getAppCheck(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseAppCheck instance = FirebaseAppCheck.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final FirebaseAppCheck appCheck(Firebase firebase2, FirebaseApp firebaseApp) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, App.TYPE);
        FirebaseAppCheck instance = FirebaseAppCheck.getInstance(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app)");
        return instance;
    }

    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final String component1(AppCheckToken appCheckToken) {
        Intrinsics.checkNotNullParameter(appCheckToken, "<this>");
        String token = appCheckToken.getToken();
        Intrinsics.checkNotNullExpressionValue(token, "token");
        return token;
    }

    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final long component2(AppCheckToken appCheckToken) {
        Intrinsics.checkNotNullParameter(appCheckToken, "<this>");
        return appCheckToken.getExpireTimeMillis();
    }
}
