package expo.modules.notifications.service;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.notifications.service.interfaces.FirebaseMessagingDelegate;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048TX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lexpo/modules/notifications/service/ExpoFirebaseMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "firebaseMessagingDelegate", "Lexpo/modules/notifications/service/interfaces/FirebaseMessagingDelegate;", "getFirebaseMessagingDelegate", "()Lexpo/modules/notifications/service/interfaces/FirebaseMessagingDelegate;", "firebaseMessagingDelegate$delegate", "Lkotlin/Lazy;", "onDeletedMessages", "", "onMessageReceived", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoFirebaseMessagingService.kt */
public class ExpoFirebaseMessagingService extends FirebaseMessagingService {
    private final Lazy firebaseMessagingDelegate$delegate = LazyKt.lazy(new ExpoFirebaseMessagingService$firebaseMessagingDelegate$2(this));

    /* access modifiers changed from: protected */
    public FirebaseMessagingDelegate getFirebaseMessagingDelegate() {
        return (FirebaseMessagingDelegate) this.firebaseMessagingDelegate$delegate.getValue();
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        getFirebaseMessagingDelegate().onMessageReceived(remoteMessage);
    }

    public void onNewToken(String str) {
        Intrinsics.checkNotNullParameter(str, "token");
        getFirebaseMessagingDelegate().onNewToken(str);
    }

    public void onDeletedMessages() {
        getFirebaseMessagingDelegate().onDeletedMessages();
    }
}
