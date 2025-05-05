package expo.modules.devmenu.modules;

import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.devmenu.DevMenuManager;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.UnexpectedException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"<anonymous>", "", "R", "P0", "<anonymous parameter 0>", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$5"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$21 extends Lambda implements Function2<Object[], Promise, Unit> {
    public DevMenuInternalModule$definition$lambda$11$$inlined$AsyncFunction$21() {
        super(2);
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Object obj;
        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = (String) promise;
        Iterator it = DevMenuManager.INSTANCE.getRegisteredCallbacks().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((DevMenuManager.Callback) obj).getName(), (Object) str)) {
                break;
            }
        }
        DevMenuManager.Callback callback = (DevMenuManager.Callback) obj;
        if (callback != null) {
            DevMenuManager.INSTANCE.sendEventToDelegateBridge("registeredCallbackFired", str);
            if (callback.getShouldCollapse()) {
                DevMenuManager.INSTANCE.closeMenu();
                return;
            }
            return;
        }
        throw new UnexpectedException("Callback with name: " + str + " is not registered");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}
