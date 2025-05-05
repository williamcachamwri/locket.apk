package expo.modules.notifications.tokens;

import android.os.Bundle;
import androidx.tracing.Trace;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.notifications.tokens.interfaces.PushTokenListener;
import expo.modules.notifications.tokens.interfaces.PushTokenManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078BX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lexpo/modules/notifications/tokens/PushTokenModule;", "Lexpo/modules/kotlin/modules/Module;", "Lexpo/modules/notifications/tokens/interfaces/PushTokenListener;", "()V", "eventEmitter", "Lexpo/modules/core/interfaces/services/EventEmitter;", "tokenManager", "Lexpo/modules/notifications/tokens/interfaces/PushTokenManager;", "getTokenManager", "()Lexpo/modules/notifications/tokens/interfaces/PushTokenManager;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "onNewToken", "", "token", "", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PushTokenModule.kt */
public final class PushTokenModule extends Module implements PushTokenListener {
    /* access modifiers changed from: private */
    public EventEmitter eventEmitter;

    /* access modifiers changed from: private */
    public final PushTokenManager getTokenManager() {
        return (PushTokenManager) getAppContext().getLegacyModuleRegistry().getSingletonModule("PushTokenManager", PushTokenManager.class);
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoPushTokenManager");
            moduleDefinitionBuilder.Events("onDevicePushToken");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new PushTokenModule$definition$lambda$4$$inlined$OnCreate$1(this)));
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new PushTokenModule$definition$lambda$4$$inlined$OnDestroy$1(this)));
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("getDevicePushTokenAsync", new AnyType[0], new PushTokenModule$definition$lambda$4$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("getDevicePushTokenAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, PushTokenModule$definition$lambda$4$$inlined$AsyncFunction$2.INSTANCE))}, new PushTokenModule$definition$lambda$4$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("getDevicePushTokenAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("unregisterForNotificationsAsync", new AnyType[0], new PushTokenModule$definition$lambda$4$$inlined$AsyncFunction$4());
            } else {
                asyncFunction2 = new AsyncFunctionComponent("unregisterForNotificationsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, PushTokenModule$definition$lambda$4$$inlined$AsyncFunction$5.INSTANCE))}, new PushTokenModule$definition$lambda$4$$inlined$AsyncFunction$6());
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("unregisterForNotificationsAsync", asyncFunction2);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    public void onNewToken(String str) {
        Intrinsics.checkNotNullParameter(str, "token");
        EventEmitter eventEmitter2 = this.eventEmitter;
        if (eventEmitter2 != null) {
            Bundle bundle = new Bundle();
            bundle.putString("devicePushToken", str);
            eventEmitter2.emit("onDevicePushToken", bundle);
        }
    }
}
