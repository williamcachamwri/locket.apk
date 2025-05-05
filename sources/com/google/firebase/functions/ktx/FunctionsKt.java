package com.google.firebase.functions.ktx;

import com.google.firebase.FirebaseApp;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableOptions;
import com.google.firebase.functions.HttpsCallableReference;
import com.google.firebase.ktx.Firebase;
import io.sentry.Session;
import io.sentry.protocol.App;
import java.net.URL;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b\u001a-\u0010\t\u001a\u00020\n*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0007\u001a-\u0010\u0011\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010H\u0007\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0014"}, d2 = {"functions", "Lcom/google/firebase/functions/FirebaseFunctions;", "Lcom/google/firebase/ktx/Firebase;", "getFunctions", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/functions/FirebaseFunctions;", "app", "Lcom/google/firebase/FirebaseApp;", "regionOrCustomDomain", "", "getHttpsCallable", "Lcom/google/firebase/functions/HttpsCallableReference;", "name", "init", "Lkotlin/Function1;", "Lcom/google/firebase/functions/HttpsCallableOptions$Builder;", "", "Lkotlin/ExtensionFunctionType;", "getHttpsCallableFromUrl", "url", "Ljava/net/URL;", "com.google.firebase-firebase-functions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Functions.kt */
public final class FunctionsKt {
    public static final FirebaseFunctions getFunctions(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseFunctions instance = FirebaseFunctions.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final FirebaseFunctions functions(Firebase firebase2, String str) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(str, "regionOrCustomDomain");
        FirebaseFunctions instance = FirebaseFunctions.getInstance(str);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(regionOrCustomDomain)");
        return instance;
    }

    public static final FirebaseFunctions functions(Firebase firebase2, FirebaseApp firebaseApp) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, App.TYPE);
        FirebaseFunctions instance = FirebaseFunctions.getInstance(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app)");
        return instance;
    }

    public static final FirebaseFunctions functions(Firebase firebase2, FirebaseApp firebaseApp, String str) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, App.TYPE);
        Intrinsics.checkNotNullParameter(str, "regionOrCustomDomain");
        FirebaseFunctions instance = FirebaseFunctions.getInstance(firebaseApp, str);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app, regionOrCustomDomain)");
        return instance;
    }

    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final HttpsCallableReference getHttpsCallable(FirebaseFunctions firebaseFunctions, String str, Function1<? super HttpsCallableOptions.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(firebaseFunctions, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        HttpsCallableOptions.Builder builder = new HttpsCallableOptions.Builder();
        function1.invoke(builder);
        HttpsCallableReference httpsCallable = firebaseFunctions.getHttpsCallable(str, builder.build());
        Intrinsics.checkNotNullExpressionValue(httpsCallable, "getHttpsCallable(name, builder.build())");
        return httpsCallable;
    }

    @Deprecated(message = "Migrate to use the KTX API from the main module: https://firebase.google.com/docs/android/kotlin-migration.", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final HttpsCallableReference getHttpsCallableFromUrl(FirebaseFunctions firebaseFunctions, URL url, Function1<? super HttpsCallableOptions.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(firebaseFunctions, "<this>");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        HttpsCallableOptions.Builder builder = new HttpsCallableOptions.Builder();
        function1.invoke(builder);
        HttpsCallableReference httpsCallableFromUrl = firebaseFunctions.getHttpsCallableFromUrl(url, builder.build());
        Intrinsics.checkNotNullExpressionValue(httpsCallableFromUrl, "getHttpsCallableFromUrl(url, builder.build())");
        return httpsCallableFromUrl;
    }
}
