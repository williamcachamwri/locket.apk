package expo.modules.splashscreen.singletons;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: SplashScreen.kt */
final class SplashScreen$preventAutoHide$2 extends Lambda implements Function1<String, Unit> {
    public static final SplashScreen$preventAutoHide$2 INSTANCE = new SplashScreen$preventAutoHide$2();

    SplashScreen$preventAutoHide$2() {
        super(1);
    }

    public final void invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "it");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return Unit.INSTANCE;
    }
}
