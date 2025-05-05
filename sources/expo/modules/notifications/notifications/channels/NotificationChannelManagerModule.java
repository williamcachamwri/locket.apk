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
import expo.modules.notifications.notifications.channels.managers.NotificationsChannelManager;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.notifications.enums.NotificationImportance;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0003J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lexpo/modules/notifications/notifications/channels/NotificationChannelManagerModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "channelManager", "Lexpo/modules/notifications/notifications/channels/managers/NotificationsChannelManager;", "channelSerializer", "Lexpo/modules/notifications/notifications/channels/serializers/NotificationsChannelSerializer;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getImportanceFromOptions", "", "channelOptions", "Lexpo/modules/core/arguments/ReadableArguments;", "getNameFromOptions", "", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationChannelManagerModule.kt */
public class NotificationChannelManagerModule extends Module {
    /* access modifiers changed from: private */
    public NotificationsChannelManager channelManager;
    /* access modifiers changed from: private */
    public NotificationsChannelSerializer channelSerializer;

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoNotificationChannelManager");
            moduleDefinitionBuilder.getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new NotificationChannelManagerModule$definition$lambda$5$$inlined$OnCreate$1(this)));
            AsyncFunctionComponent asyncFunctionComponent = new AsyncFunctionComponent("getNotificationChannelsAsync", new AnyType[0], new NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunctionWithoutArgs$1(this));
            moduleDefinitionBuilder.getAsyncFunctions().put("getNotificationChannelsAsync", asyncFunctionComponent);
            AsyncFunction asyncFunction4 = asyncFunctionComponent;
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("getNotificationChannelAsync", new AnyType[0], new NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("getNotificationChannelAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$2.INSTANCE))}, new NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("getNotificationChannelAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (ReadableArguments.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("setNotificationChannelAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$4.INSTANCE))}, new NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$5(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("setNotificationChannelAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$6.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false, NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$7.INSTANCE))}, new NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$8(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("setNotificationChannelAsync", asyncFunction2);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("deleteNotificationChannelAsync", new AnyType[0], new NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$9(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("deleteNotificationChannelAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$10.INSTANCE))}, new NotificationChannelManagerModule$definition$lambda$5$$inlined$AsyncFunction$11(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("deleteNotificationChannelAsync", asyncFunction3);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final CharSequence getNameFromOptions(ReadableArguments readableArguments) {
        String string = readableArguments.getString("name");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    /* access modifiers changed from: private */
    public final int getImportanceFromOptions(ReadableArguments readableArguments) {
        return ((NotificationImportance) Objects.requireNonNull(NotificationImportance.fromEnumValue(readableArguments.getInt(NotificationsChannelSerializer.IMPORTANCE_KEY, NotificationImportance.DEFAULT.getEnumValue())))).getNativeValue();
    }
}
