package expo.modules.notifications.notifications.categories;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.tracing.Trace;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.errors.InvalidArgumentException;
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
import expo.modules.notifications.notifications.categories.NotificationActionRecord;
import expo.modules.notifications.notifications.categories.serializers.NotificationsCategoriesSerializer;
import expo.modules.notifications.notifications.model.NotificationAction;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.notifications.model.TextInputNotificationAction;
import expo.modules.notifications.service.NotificationsService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JF\u0010\r\u001a\u00020\u000e2<\u0010\u000f\u001a8\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u0010j\u0002`\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u001e\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0014J>\u0010%\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0!2\u0016\u0010(\u001a\u0012\u0012\u0004\u0012\u00020\u001d\u0012\u0006\u0012\u0004\u0018\u00010*\u0018\u00010)2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8DX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006+"}, d2 = {"Lexpo/modules/notifications/notifications/categories/ExpoNotificationCategoriesModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "serializer", "Lexpo/modules/notifications/notifications/categories/serializers/NotificationsCategoriesSerializer;", "getSerializer", "()Lexpo/modules/notifications/notifications/categories/serializers/NotificationsCategoriesSerializer;", "serializer$delegate", "Lkotlin/Lazy;", "createResultReceiver", "Landroid/os/ResultReceiver;", "body", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "resultCode", "Landroid/os/Bundle;", "resultData", "", "Lexpo/modules/notifications/ResultReceiverBody;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "deleteNotificationCategoryAsync", "identifier", "", "promise", "Lexpo/modules/kotlin/Promise;", "serializeCategories", "", "categories", "", "Lexpo/modules/notifications/notifications/model/NotificationCategory;", "setNotificationCategoryAsync", "actionArguments", "Lexpo/modules/notifications/notifications/categories/NotificationActionRecord;", "categoryOptions", "", "", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoNotificationCategoriesModule.kt */
public class ExpoNotificationCategoriesModule extends Module {
    private final Lazy serializer$delegate = LazyKt.lazy(new ExpoNotificationCategoriesModule$serializer$2(this));

    /* access modifiers changed from: protected */
    public final NotificationsCategoriesSerializer getSerializer() {
        return (NotificationsCategoriesSerializer) this.serializer$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoNotificationCategoriesModule");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("getNotificationCategoriesAsync", new AnyType[0], new ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("getNotificationCategoriesAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$2.INSTANCE))}, new ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("getNotificationCategoriesAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("setNotificationCategoryAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$4.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(List.class), false, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$5.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), true, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$6.INSTANCE))}, new ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$7(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("setNotificationCategoryAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$8.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(List.class), false, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$9.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Map.class), true, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$10.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$11.INSTANCE))}, new ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$12(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("setNotificationCategoryAsync", asyncFunction2);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("deleteNotificationCategoryAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$13.INSTANCE))}, new ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$14(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("deleteNotificationCategoryAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$15.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$16.INSTANCE))}, new ExpoNotificationCategoriesModule$definition$lambda$2$$inlined$AsyncFunction$17(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("deleteNotificationCategoryAsync", asyncFunction3);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final ResultReceiver createResultReceiver(Function2<? super Integer, ? super Bundle, Unit> function2) {
        return UtilsKt.createDefaultResultReceiver((Handler) null, function2);
    }

    public void setNotificationCategoryAsync(String str, List<NotificationActionRecord> list, Map<String, ? extends Object> map, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        Intrinsics.checkNotNullParameter(list, "actionArguments");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        List arrayList = new ArrayList();
        for (NotificationActionRecord next : list) {
            NotificationActionRecord.TextInput textInput = next.getTextInput();
            if (textInput != null) {
                arrayList.add(new TextInputNotificationAction(next.getIdentifier(), next.getButtonTitle(), next.getOptions().getOpensAppToForeground(), textInput.getPlaceholder()));
            } else {
                arrayList.add(new NotificationAction(next.getIdentifier(), next.getButtonTitle(), next.getOptions().getOpensAppToForeground()));
            }
        }
        if (!arrayList.isEmpty()) {
            NotificationsService.Companion.setCategory(getContext(), new NotificationCategory(str, (List<NotificationAction>) arrayList), createResultReceiver(new ExpoNotificationCategoriesModule$setNotificationCategoryAsync$1(promise, this)));
            return;
        }
        throw new InvalidArgumentException("Invalid arguments provided for notification category. Must provide at least one action.");
    }

    public void deleteNotificationCategoryAsync(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        NotificationsService.Companion.deleteCategory(getContext(), str, createResultReceiver(new ExpoNotificationCategoriesModule$deleteNotificationCategoryAsync$1(promise)));
    }

    /* access modifiers changed from: protected */
    public List<Bundle> serializeCategories(Collection<? extends NotificationCategory> collection) {
        Intrinsics.checkNotNullParameter(collection, "categories");
        Iterable<NotificationCategory> iterable = collection;
        NotificationsCategoriesSerializer serializer = getSerializer();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (NotificationCategory bundle : iterable) {
            arrayList.add(serializer.toBundle(bundle));
        }
        return (List) arrayList;
    }
}
