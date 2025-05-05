package com.google.firebase.messaging;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.firebase.Firebase;
import com.google.firebase.messaging.RemoteMessage;
import io.sentry.Session;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0019\b\u0004\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\bø\u0001\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\u0002\u0007\n\u0005\b20\u0001¨\u0006\u000e"}, d2 = {"messaging", "Lcom/google/firebase/messaging/FirebaseMessaging;", "Lcom/google/firebase/Firebase;", "getMessaging", "(Lcom/google/firebase/Firebase;)Lcom/google/firebase/messaging/FirebaseMessaging;", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "to", "", "init", "Lkotlin/Function1;", "Lcom/google/firebase/messaging/RemoteMessage$Builder;", "", "Lkotlin/ExtensionFunctionType;", "com.google.firebase-firebase-messaging"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Messaging.kt */
public final class MessagingKt {
    public static final FirebaseMessaging getMessaging(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseMessaging instance = FirebaseMessaging.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final RemoteMessage remoteMessage(String str, Function1<? super RemoteMessage.Builder, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, TypedValues.TransitionType.S_TO);
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        RemoteMessage.Builder builder = new RemoteMessage.Builder(str);
        function1.invoke(builder);
        RemoteMessage build = builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }
}
