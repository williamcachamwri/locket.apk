package com.google.firebase.auth;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import io.sentry.Session;
import io.sentry.protocol.App;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006\u001a%\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\bø\u0001\u0000\u001a-\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\bø\u0001\u0000\u001a5\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00012\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\bø\u0001\u0000\u001a-\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u00112\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\bø\u0001\u0000\u001a%\u0010\u0017\u001a\u00020\u00182\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\bø\u0001\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0002\u0007\n\u0005\b20\u0001¨\u0006\u001a"}, d2 = {"auth", "Lcom/google/firebase/auth/FirebaseAuth;", "Lcom/google/firebase/Firebase;", "getAuth", "(Lcom/google/firebase/Firebase;)Lcom/google/firebase/auth/FirebaseAuth;", "app", "Lcom/google/firebase/FirebaseApp;", "actionCodeSettings", "Lcom/google/firebase/auth/ActionCodeSettings;", "init", "Lkotlin/Function1;", "Lcom/google/firebase/auth/ActionCodeSettings$Builder;", "", "Lkotlin/ExtensionFunctionType;", "oAuthProvider", "Lcom/google/firebase/auth/OAuthProvider;", "providerId", "", "Lcom/google/firebase/auth/OAuthProvider$Builder;", "firebaseAuth", "oAuthCredential", "Lcom/google/firebase/auth/AuthCredential;", "Lcom/google/firebase/auth/OAuthProvider$CredentialBuilder;", "userProfileChangeRequest", "Lcom/google/firebase/auth/UserProfileChangeRequest;", "Lcom/google/firebase/auth/UserProfileChangeRequest$Builder;", "java.com.google.android.gmscore.integ.client.firebase-auth-api_firebase-auth-api"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final class AuthKt {
    public static final ActionCodeSettings actionCodeSettings(Function1<? super ActionCodeSettings.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        ActionCodeSettings.Builder newBuilder = ActionCodeSettings.newBuilder();
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder(...)");
        function1.invoke(newBuilder);
        ActionCodeSettings build = newBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public static final AuthCredential oAuthCredential(String str, Function1<? super OAuthProvider.CredentialBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "providerId");
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        OAuthProvider.CredentialBuilder newCredentialBuilder = OAuthProvider.newCredentialBuilder(str);
        Intrinsics.checkNotNullExpressionValue(newCredentialBuilder, "newCredentialBuilder(...)");
        function1.invoke(newCredentialBuilder);
        AuthCredential build = newCredentialBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public static final FirebaseAuth auth(Firebase firebase2, FirebaseApp firebaseApp) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, App.TYPE);
        FirebaseAuth instance = FirebaseAuth.getInstance(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        return instance;
    }

    public static final FirebaseAuth getAuth(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseAuth instance = FirebaseAuth.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        return instance;
    }

    public static final OAuthProvider oAuthProvider(String str, Function1<? super OAuthProvider.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "providerId");
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        OAuthProvider.Builder newBuilder = OAuthProvider.newBuilder(str);
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder(...)");
        function1.invoke(newBuilder);
        OAuthProvider build = newBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public static final OAuthProvider oAuthProvider(String str, FirebaseAuth firebaseAuth, Function1<? super OAuthProvider.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "providerId");
        Intrinsics.checkNotNullParameter(firebaseAuth, "firebaseAuth");
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        OAuthProvider.Builder newBuilder = OAuthProvider.newBuilder(str, firebaseAuth);
        Intrinsics.checkNotNullExpressionValue(newBuilder, "newBuilder(...)");
        function1.invoke(newBuilder);
        OAuthProvider build = newBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }

    public static final UserProfileChangeRequest userProfileChangeRequest(Function1<? super UserProfileChangeRequest.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
        function1.invoke(builder);
        UserProfileChangeRequest build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }
}
