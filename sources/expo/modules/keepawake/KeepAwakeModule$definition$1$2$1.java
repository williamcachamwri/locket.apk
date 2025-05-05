package expo.modules.keepawake;

import expo.modules.kotlin.Promise;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: KeepAwakeModule.kt */
final class KeepAwakeModule$definition$1$2$1 implements Runnable {
    final /* synthetic */ Promise $promise;

    KeepAwakeModule$definition$1$2$1(Promise promise) {
        this.$promise = promise;
    }

    public final void run() {
        this.$promise.resolve(true);
    }
}
