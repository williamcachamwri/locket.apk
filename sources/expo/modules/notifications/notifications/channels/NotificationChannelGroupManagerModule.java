package expo.modules.notifications.notifications.channels;

import androidx.tracing.Trace;
import expo.modules.core.arguments.ReadableArguments;
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
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelGroupManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelGroupSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lexpo/modules/notifications/notifications/channels/NotificationChannelGroupManagerModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "groupManager", "Lexpo/modules/notifications/notifications/channels/managers/NotificationsChannelGroupManager;", "groupSerializer", "Lexpo/modules/notifications/notifications/channels/serializers/NotificationsChannelGroupSerializer;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getNameFromOptions", "", "groupOptions", "Lexpo/modules/core/arguments/ReadableArguments;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationChannelGroupManagerModule.kt */
public final class NotificationChannelGroupManagerModule extends Module {
    /* access modifiers changed from: private */
    public NotificationsChannelGroupManager groupManager;
    /* access modifiers changed from: private */
    public NotificationsChannelGroupSerializer groupSerializer;

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoNotificationChannelGroupManager");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$OnCreate$1(this)));
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("getNotificationChannelGroupAsync", new AnyType[0], new NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("getNotificationChannelGroupAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$2.INSTANCE))}, new NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("getNotificationChannelGroupAsync", asyncFunction);
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getNotificationChannelGroupsAsync", new AnyType[0], new NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getNotificationChannelGroupsAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction4 = asyncFunctionComponent;
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (ReadableArguments.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("setNotificationChannelGroupAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$4.INSTANCE))}, new NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$5(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("setNotificationChannelGroupAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$6.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false, NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$7.INSTANCE))}, new NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$8(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("setNotificationChannelGroupAsync", asyncFunction2);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("deleteNotificationChannelGroupAsync", new AnyType[0], new NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$9(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("deleteNotificationChannelGroupAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$10.INSTANCE))}, new NotificationChannelGroupManagerModule$definition$lambda$5$$inlined$AsyncFunction$11(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("deleteNotificationChannelGroupAsync", asyncFunction3);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final String getNameFromOptions(ReadableArguments readableArguments) {
        String string = readableArguments.getString("name");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
}
