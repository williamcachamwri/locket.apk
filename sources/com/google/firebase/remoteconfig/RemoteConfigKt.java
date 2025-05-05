package com.google.firebase.remoteconfig;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import io.sentry.Session;
import io.sentry.protocol.App;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\n\u001a\u00020\u000b2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u0010\u001a\u0015\u0010\u0011\u001a\u00020\u0012*\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a\u0012\u0010\u0006\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0016\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0015\u0010\u0006\u001a\u00020\u0003*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0017"}, d2 = {"configUpdates", "Lkotlinx/coroutines/flow/Flow;", "Lcom/google/firebase/remoteconfig/ConfigUpdate;", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "getConfigUpdates", "(Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;)Lkotlinx/coroutines/flow/Flow;", "remoteConfig", "Lcom/google/firebase/Firebase;", "getRemoteConfig", "(Lcom/google/firebase/Firebase;)Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "remoteConfigSettings", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfigSettings;", "init", "Lkotlin/Function1;", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfigSettings$Builder;", "", "Lkotlin/ExtensionFunctionType;", "get", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfigValue;", "key", "", "app", "Lcom/google/firebase/FirebaseApp;", "com.google.firebase-firebase-config"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RemoteConfig.kt */
public final class RemoteConfigKt {
    public static final FirebaseRemoteConfig getRemoteConfig(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseRemoteConfig instance = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final FirebaseRemoteConfig remoteConfig(Firebase firebase2, FirebaseApp firebaseApp) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        Intrinsics.checkNotNullParameter(firebaseApp, App.TYPE);
        FirebaseRemoteConfig instance = FirebaseRemoteConfig.getInstance(firebaseApp);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app)");
        return instance;
    }

    public static final FirebaseRemoteConfigValue get(FirebaseRemoteConfig firebaseRemoteConfig, String str) {
        Intrinsics.checkNotNullParameter(firebaseRemoteConfig, "<this>");
        Intrinsics.checkNotNullParameter(str, "key");
        FirebaseRemoteConfigValue value = firebaseRemoteConfig.getValue(str);
        Intrinsics.checkNotNullExpressionValue(value, "this.getValue(key)");
        return value;
    }

    public static final FirebaseRemoteConfigSettings remoteConfigSettings(Function1<? super FirebaseRemoteConfigSettings.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        FirebaseRemoteConfigSettings.Builder builder = new FirebaseRemoteConfigSettings.Builder();
        function1.invoke(builder);
        FirebaseRemoteConfigSettings build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public static final Flow<ConfigUpdate> getConfigUpdates(FirebaseRemoteConfig firebaseRemoteConfig) {
        Intrinsics.checkNotNullParameter(firebaseRemoteConfig, "<this>");
        return FlowKt.callbackFlow(new RemoteConfigKt$configUpdates$1(firebaseRemoteConfig, (Continuation<? super RemoteConfigKt$configUpdates$1>) null));
    }
}
