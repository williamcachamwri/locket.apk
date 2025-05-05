package expo.modules.notifications;

import expo.modules.core.Promise;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"expo/modules/notifications/ExceptionsKt$toLegacyPromise$1", "Lexpo/modules/core/Promise;", "reject", "", "c", "", "m", "e", "", "resolve", "value", "", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Exceptions.kt */
public final class ExceptionsKt$toLegacyPromise$1 implements Promise {
    final /* synthetic */ expo.modules.kotlin.Promise $newPromise;

    ExceptionsKt$toLegacyPromise$1(expo.modules.kotlin.Promise promise) {
        this.$newPromise = promise;
    }

    public void resolve(Object obj) {
        this.$newPromise.resolve(obj);
    }

    public void reject(String str, String str2, Throwable th) {
        expo.modules.kotlin.Promise promise = this.$newPromise;
        if (str == null) {
            str = "unknown";
        }
        promise.reject(str, str2, th);
    }
}
