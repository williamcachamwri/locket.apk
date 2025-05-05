package expo.modules.devmenu.modules;

import android.app.Activity;
import androidx.tracing.Trace;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.devmenu.DevMenuUtilsKt;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lexpo/modules/devmenu/modules/DevMenuModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "currentActivity", "Landroid/app/Activity;", "getCurrentActivity", "()Landroid/app/Activity;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-dev-menu_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuModule.kt */
public final class DevMenuModule extends Module {
    public final Activity getCurrentActivity() {
        Activity currentActivity = getAppContext().getCurrentActivity();
        if (currentActivity != null) {
            return currentActivity;
        }
        throw new Exceptions.MissingActivity();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name(DevMenuUtilsKt.DEV_MENU_TAG);
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("openMenu", new AnyType[0], new DevMenuModule$definition$lambda$5$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("openMenu", asyncFunctionComponent);
            AsyncFunction asyncFunction2 = asyncFunctionComponent;
            AsyncFunctionComponent asyncFunctionComponent2 = new AsyncFunctionComponent("closeMenu", new AnyType[0], new DevMenuModule$definition$lambda$5$$inlined$AsyncFunctionWithoutArgs$2());
            moduleDefinitionBuilder.getAsyncFunctions().put("closeMenu", asyncFunctionComponent2);
            AsyncFunction asyncFunction3 = asyncFunctionComponent2;
            AsyncFunctionComponent asyncFunctionComponent3 = new AsyncFunctionComponent("hideMenu", new AnyType[0], new DevMenuModule$definition$lambda$5$$inlined$AsyncFunctionWithoutArgs$3());
            moduleDefinitionBuilder.getAsyncFunctions().put("hideMenu", asyncFunctionComponent3);
            AsyncFunction asyncFunction4 = asyncFunctionComponent3;
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (ReadableArray.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("addDevMenuCallbacks", new AnyType[0], new DevMenuModule$definition$lambda$5$$inlined$AsyncFunction$1());
            } else {
                asyncFunction = new AsyncFunctionComponent("addDevMenuCallbacks", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArray.class), false, DevMenuModule$definition$lambda$5$$inlined$AsyncFunction$2.INSTANCE))}, new DevMenuModule$definition$lambda$5$$inlined$AsyncFunction$3());
            }
            objectDefinitionBuilder.getAsyncFunctions().put("addDevMenuCallbacks", asyncFunction);
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new DevMenuModule$definition$lambda$5$$inlined$OnDestroy$1()));
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }
}
