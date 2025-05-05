package expo.modules.notifications;

import expo.modules.core.Promise;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toLegacyPromise", "Lexpo/modules/core/Promise;", "Lexpo/modules/kotlin/Promise;", "expo-notifications_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Exceptions.kt */
public final class ExceptionsKt {
    public static final Promise toLegacyPromise(expo.modules.kotlin.Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "<this>");
        return new ExceptionsKt$toLegacyPromise$1(promise);
    }
}
