package expo.modules.devmenu.extensions;

import expo.modules.devmenu.devtools.DevMenuDevToolsDelegate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuExtension.kt */
/* synthetic */ class DevMenuExtension$devMenuItems$1$9$1 extends FunctionReferenceImpl implements Function0<Unit> {
    DevMenuExtension$devMenuItems$1$9$1(Object obj) {
        super(0, obj, DevMenuDevToolsDelegate.class, "openJSInspector", "openJSInspector()V", 0);
    }

    public final void invoke() {
        ((DevMenuDevToolsDelegate) this.receiver).openJSInspector();
    }
}
