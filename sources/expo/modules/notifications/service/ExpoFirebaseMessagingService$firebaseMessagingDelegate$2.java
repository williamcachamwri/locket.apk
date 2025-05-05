package expo.modules.notifications.service;

import expo.modules.notifications.service.delegates.FirebaseMessagingDelegate;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lexpo/modules/notifications/service/delegates/FirebaseMessagingDelegate;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoFirebaseMessagingService.kt */
final class ExpoFirebaseMessagingService$firebaseMessagingDelegate$2 extends Lambda implements Function0<FirebaseMessagingDelegate> {
    final /* synthetic */ ExpoFirebaseMessagingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExpoFirebaseMessagingService$firebaseMessagingDelegate$2(ExpoFirebaseMessagingService expoFirebaseMessagingService) {
        super(0);
        this.this$0 = expoFirebaseMessagingService;
    }

    public final FirebaseMessagingDelegate invoke() {
        return new FirebaseMessagingDelegate(this.this$0);
    }
}
