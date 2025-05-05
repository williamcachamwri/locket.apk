package expo.modules.devmenu.modules;

import expo.modules.devmenu.DevMenuManager;
import expo.modules.kotlin.exception.UnexpectedException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "R", "P0", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$7"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$23 extends Lambda implements Function1<Object[], Object> {
    public DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$23() {
        super(1);
    }

    public final Object invoke(Object[] objArr) {
        Object obj;
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            Iterator it = DevMenuManager.INSTANCE.getRegisteredCallbacks().iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((DevMenuManager.Callback) obj).getName(), (Object) str2)) {
                    break;
                }
            }
            DevMenuManager.Callback callback = (DevMenuManager.Callback) obj;
            if (callback != null) {
                DevMenuManager.INSTANCE.sendEventToDelegateBridge("registeredCallbackFired", str2);
                if (callback.getShouldCollapse()) {
                    DevMenuManager.INSTANCE.closeMenu();
                }
                return Unit.INSTANCE;
            }
            throw new UnexpectedException("Callback with name: " + str2 + " is not registered");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}
