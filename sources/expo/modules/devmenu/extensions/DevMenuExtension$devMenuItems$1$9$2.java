package expo.modules.devmenu.extensions;

import expo.interfaces.devmenu.items.DevMenuAction;
import expo.interfaces.devmenu.items.DevMenuItemImportance;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lexpo/interfaces/devmenu/items/DevMenuAction;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuExtension.kt */
final class DevMenuExtension$devMenuItems$1$9$2 extends Lambda implements Function1<DevMenuAction, Unit> {
    public static final DevMenuExtension$devMenuItems$1$9$2 INSTANCE = new DevMenuExtension$devMenuItems$1$9$2();

    DevMenuExtension$devMenuItems$1$9$2() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DevMenuAction) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(DevMenuAction devMenuAction) {
        Intrinsics.checkNotNullParameter(devMenuAction, "$this$action");
        devMenuAction.setLabel(AnonymousClass1.INSTANCE);
        devMenuAction.setGlyphName(AnonymousClass2.INSTANCE);
        devMenuAction.setImportance(DevMenuItemImportance.LOW.getValue());
    }
}
