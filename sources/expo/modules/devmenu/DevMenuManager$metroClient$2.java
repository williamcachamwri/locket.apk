package expo.modules.devmenu;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0001\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuManager.kt */
final class DevMenuManager$metroClient$2 extends Lambda implements Function0 {
    public static final DevMenuManager$metroClient$2 INSTANCE = new DevMenuManager$metroClient$2();

    DevMenuManager$metroClient$2() {
        super(0);
    }

    public final Void invoke() {
        throw new IllegalStateException("DevMenu isn't available in release builds");
    }
}
