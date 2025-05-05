package expo.modules.notifications.notifications.presentation;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.tracing.Trace;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.kotlin.Promise;
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
import expo.modules.notifications.UtilsKt;
import expo.modules.notifications.notifications.NotificationSerializer;
import expo.modules.notifications.notifications.interfaces.NotificationTrigger;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationContent;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.service.NotificationsService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014JF\u0010\u000f\u001a\u00020\u00102<\u0010\u0011\u001a8\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0015\u0012\u0013\u0018\u00010\u0017¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00190\u0012j\u0002`\u001aH\u0004J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\u0018\u0010 \u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00170\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H\u0014R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006&"}, d2 = {"Lexpo/modules/notifications/notifications/presentation/ExpoNotificationPresentationModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "createNotificationRequest", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "identifier", "", "content", "Lexpo/modules/notifications/notifications/model/NotificationContent;", "trigger", "Lexpo/modules/notifications/notifications/interfaces/NotificationTrigger;", "createResultReceiver", "Landroid/os/ResultReceiver;", "body", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "resultCode", "Landroid/os/Bundle;", "resultData", "", "Lexpo/modules/notifications/ResultReceiverBody;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "dismissAllNotificationsAsync", "promise", "Lexpo/modules/kotlin/Promise;", "dismissNotificationAsync", "serializeNotifications", "", "notifications", "", "Lexpo/modules/notifications/notifications/model/Notification;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoNotificationPresentationModule.kt */
public class ExpoNotificationPresentationModule extends Module {
    /* access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    /* access modifiers changed from: protected */
    public final ResultReceiver createResultReceiver(Function2<? super Integer, ? super Bundle, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, TtmlNode.TAG_BODY);
        return UtilsKt.createDefaultResultReceiver((Handler) null, function2);
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        AsyncFunction asyncFunction4;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoNotificationPresenter");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("presentNotificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$1.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false, ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$2.INSTANCE))}, new ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$3(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("presentNotificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$4.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false, ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$5.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$6.INSTANCE))}, new ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$7(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("presentNotificationAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("getPresentedNotificationsAsync", new AnyType[0], new ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$8(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("getPresentedNotificationsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$9.INSTANCE))}, new ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$10(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("getPresentedNotificationsAsync", asyncFunction2);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("dismissNotificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$11.INSTANCE))}, new ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$12(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("dismissNotificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$13.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$14.INSTANCE))}, new ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$15(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("dismissNotificationAsync", asyncFunction3);
            ObjectDefinitionBuilder objectDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction4 = new AsyncFunctionWithPromiseComponent("dismissAllNotificationsAsync", new AnyType[0], new ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$16(this));
            } else {
                asyncFunction4 = new AsyncFunctionComponent("dismissAllNotificationsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$17.INSTANCE))}, new ExpoNotificationPresentationModule$definition$lambda$3$$inlined$AsyncFunction$18(this));
            }
            objectDefinitionBuilder4.getAsyncFunctions().put("dismissAllNotificationsAsync", asyncFunction4);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: protected */
    public void dismissNotificationAsync(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        NotificationsService.Companion.dismiss(getContext(), new String[]{str}, createResultReceiver(new ExpoNotificationPresentationModule$dismissNotificationAsync$1(promise)));
    }

    /* access modifiers changed from: protected */
    public void dismissAllNotificationsAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        NotificationsService.Companion.dismissAll(getContext(), createResultReceiver(new ExpoNotificationPresentationModule$dismissAllNotificationsAsync$1(promise)));
    }

    /* access modifiers changed from: protected */
    public NotificationRequest createNotificationRequest(String str, NotificationContent notificationContent, NotificationTrigger notificationTrigger) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        Intrinsics.checkNotNullParameter(notificationContent, "content");
        return new NotificationRequest(str, notificationContent, (NotificationTrigger) null);
    }

    /* access modifiers changed from: protected */
    public List<Bundle> serializeNotifications(Collection<? extends Notification> collection) {
        Intrinsics.checkNotNullParameter(collection, NotificationsService.NOTIFICATIONS_KEY);
        Iterable<Notification> iterable = collection;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Notification bundle : iterable) {
            arrayList.add(NotificationSerializer.toBundle(bundle));
        }
        return (List) arrayList;
    }
}
