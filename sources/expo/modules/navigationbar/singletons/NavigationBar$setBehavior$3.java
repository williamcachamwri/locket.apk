package expo.modules.navigationbar.singletons;

import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "m", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: NavigationBar.kt */
final class NavigationBar$setBehavior$3 extends Lambda implements Function1<String, Unit> {
    public static final NavigationBar$setBehavior$3 INSTANCE = new NavigationBar$setBehavior$3();

    NavigationBar$setBehavior$3() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(String str) {
        Intrinsics.checkNotNullParameter(str, "m");
        SentryLogcatAdapter.e("NavigationBar", str);
    }
}
