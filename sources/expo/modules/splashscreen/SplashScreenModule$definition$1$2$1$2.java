package expo.modules.splashscreen;

import expo.modules.kotlin.Promise;
import expo.modules.splashscreen.exceptions.HideAsyncException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "m", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: SplashScreenModule.kt */
final class SplashScreenModule$definition$1$2$1$2 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ Promise $promise;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SplashScreenModule$definition$1$2$1$2(Promise promise) {
        super(1);
        this.$promise = promise;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "m");
        this.$promise.reject(new HideAsyncException(str));
    }
}
