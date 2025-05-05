package expo.modules.notifications.permissions;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.os.BundleKt;
import androidx.tracing.Trace;
import com.amplitude.api.DeviceInfo;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.interfaces.permissions.Permissions;
import expo.modules.interfaces.permissions.PermissionsResponse;
import expo.modules.interfaces.permissions.PermissionsStatus;
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
import expo.modules.notifications.ModuleNotFoundException;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import expo.modules.notifications.service.NotificationsService;
import java.util.Arrays;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0003J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0003R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lexpo/modules/notifications/permissions/NotificationPermissionsModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "permissions", "Lexpo/modules/interfaces/permissions/Permissions;", "getPermissions", "()Lexpo/modules/interfaces/permissions/Permissions;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getPermissionsWithPromiseImplApi33", "", "promise", "Lexpo/modules/kotlin/Promise;", "getPermissionsWithPromiseImplClassic", "requestPermissionsWithPromiseImplApi33", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationPermissionsModule.kt */
public final class NotificationPermissionsModule extends Module {
    private final Permissions getPermissions() {
        Permissions permissions = getAppContext().getPermissions();
        if (permissions != null) {
            return permissions;
        }
        throw new ModuleNotFoundException(Reflection.getOrCreateKotlinClass(Permissions.class));
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
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoNotificationPermissionsModule");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("getPermissionsAsync", new AnyType[0], new NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("getPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$2.INSTANCE))}, new NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("getPermissionsAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("requestPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true, NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$4.INSTANCE))}, new NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$5(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("requestPermissionsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true, NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$6.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$7.INSTANCE))}, new NotificationPermissionsModule$definition$lambda$2$$inlined$AsyncFunction$8(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("requestPermissionsAsync", asyncFunction2);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final void getPermissionsWithPromiseImplApi33(Promise promise) {
        Permissions permissions = getPermissions();
        NotificationPermissionsModule$$ExternalSyntheticLambda0 notificationPermissionsModule$$ExternalSyntheticLambda0 = new NotificationPermissionsModule$$ExternalSyntheticLambda0(this, promise);
        String[] access$getPERMISSIONS$p = NotificationPermissionsModuleKt.PERMISSIONS;
        permissions.getPermissions(notificationPermissionsModule$$ExternalSyntheticLambda0, (String[]) Arrays.copyOf(access$getPERMISSIONS$p, access$getPERMISSIONS$p.length));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00e7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void getPermissionsWithPromiseImplApi33$lambda$7(expo.modules.notifications.permissions.NotificationPermissionsModule r7, expo.modules.kotlin.Promise r8, java.util.Map r9) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "$promise"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "permissionsMap"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            android.content.Context r0 = r7.getContext()
            androidx.core.app.NotificationManagerCompat r0 = androidx.core.app.NotificationManagerCompat.from(r0)
            java.lang.String r1 = "from(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            boolean r1 = r0.areNotificationsEnabled()
            r2 = 1
            kotlin.Pair[] r3 = new kotlin.Pair[r2]
            int r0 = r0.getImportance()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r4 = "importance"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r4, r0)
            r4 = 0
            r3[r4] = r0
            android.os.Bundle r0 = androidx.core.os.BundleKt.bundleOf(r3)
            android.content.Context r7 = r7.getContext()
            java.lang.String r3 = "notification"
            java.lang.Object r7 = r7.getSystemService(r3)
            boolean r3 = r7 instanceof android.app.NotificationManager
            if (r3 == 0) goto L_0x0049
            android.app.NotificationManager r7 = (android.app.NotificationManager) r7
            goto L_0x004a
        L_0x0049:
            r7 = 0
        L_0x004a:
            if (r7 == 0) goto L_0x0055
            java.lang.String r3 = "interruptionFilter"
            int r7 = r7.getCurrentInterruptionFilter()
            r0.putInt(r3, r7)
        L_0x0055:
            boolean r7 = r9.isEmpty()
            if (r7 == 0) goto L_0x005d
        L_0x005b:
            r7 = r2
            goto L_0x0085
        L_0x005d:
            java.util.Set r7 = r9.entrySet()
            java.util.Iterator r7 = r7.iterator()
        L_0x0065:
            boolean r3 = r7.hasNext()
            if (r3 == 0) goto L_0x005b
            java.lang.Object r3 = r7.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r3 = r3.getValue()
            expo.modules.interfaces.permissions.PermissionsResponse r3 = (expo.modules.interfaces.permissions.PermissionsResponse) r3
            expo.modules.interfaces.permissions.PermissionsStatus r3 = r3.getStatus()
            expo.modules.interfaces.permissions.PermissionsStatus r5 = expo.modules.interfaces.permissions.PermissionsStatus.GRANTED
            if (r3 != r5) goto L_0x0081
            r3 = r2
            goto L_0x0082
        L_0x0081:
            r3 = r4
        L_0x0082:
            if (r3 != 0) goto L_0x0065
            r7 = r4
        L_0x0085:
            boolean r3 = r9.isEmpty()
            if (r3 == 0) goto L_0x008d
        L_0x008b:
            r3 = r2
            goto L_0x00b5
        L_0x008d:
            java.util.Set r3 = r9.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x0095:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x008b
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r5 = r5.getValue()
            expo.modules.interfaces.permissions.PermissionsResponse r5 = (expo.modules.interfaces.permissions.PermissionsResponse) r5
            expo.modules.interfaces.permissions.PermissionsStatus r5 = r5.getStatus()
            expo.modules.interfaces.permissions.PermissionsStatus r6 = expo.modules.interfaces.permissions.PermissionsStatus.DENIED
            if (r5 != r6) goto L_0x00b1
            r5 = r2
            goto L_0x00b2
        L_0x00b1:
            r5 = r4
        L_0x00b2:
            if (r5 != 0) goto L_0x0095
            r3 = r4
        L_0x00b5:
            boolean r5 = r9.isEmpty()
            if (r5 == 0) goto L_0x00bd
        L_0x00bb:
            r9 = r2
            goto L_0x00de
        L_0x00bd:
            java.util.Set r9 = r9.entrySet()
            java.util.Iterator r9 = r9.iterator()
        L_0x00c5:
            boolean r5 = r9.hasNext()
            if (r5 == 0) goto L_0x00bb
            java.lang.Object r5 = r9.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r5 = r5.getValue()
            expo.modules.interfaces.permissions.PermissionsResponse r5 = (expo.modules.interfaces.permissions.PermissionsResponse) r5
            boolean r5 = r5.getCanAskAgain()
            if (r5 != 0) goto L_0x00c5
            r9 = r4
        L_0x00de:
            if (r3 == 0) goto L_0x00e7
            expo.modules.interfaces.permissions.PermissionsStatus r1 = expo.modules.interfaces.permissions.PermissionsStatus.DENIED
            java.lang.String r1 = r1.getStatus()
            goto L_0x00ff
        L_0x00e7:
            if (r1 != 0) goto L_0x00f0
            expo.modules.interfaces.permissions.PermissionsStatus r1 = expo.modules.interfaces.permissions.PermissionsStatus.DENIED
            java.lang.String r1 = r1.getStatus()
            goto L_0x00ff
        L_0x00f0:
            if (r7 == 0) goto L_0x00f9
            expo.modules.interfaces.permissions.PermissionsStatus r1 = expo.modules.interfaces.permissions.PermissionsStatus.GRANTED
            java.lang.String r1 = r1.getStatus()
            goto L_0x00ff
        L_0x00f9:
            expo.modules.interfaces.permissions.PermissionsStatus r1 = expo.modules.interfaces.permissions.PermissionsStatus.UNDETERMINED
            java.lang.String r1 = r1.getStatus()
        L_0x00ff:
            r3 = 5
            kotlin.Pair[] r3 = new kotlin.Pair[r3]
            java.lang.String r5 = "expires"
            java.lang.String r6 = "never"
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r6)
            r3[r4] = r5
            java.lang.String r4 = "status"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r4, r1)
            r3[r2] = r1
            java.lang.String r1 = "canAskAgain"
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            kotlin.Pair r9 = kotlin.TuplesKt.to(r1, r9)
            r1 = 2
            r3[r1] = r9
            java.lang.String r9 = "granted"
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            kotlin.Pair r7 = kotlin.TuplesKt.to(r9, r7)
            r9 = 3
            r3[r9] = r7
            java.lang.String r7 = "android"
            kotlin.Pair r7 = kotlin.TuplesKt.to(r7, r0)
            r9 = 4
            r3[r9] = r7
            android.os.Bundle r7 = androidx.core.os.BundleKt.bundleOf(r3)
            r8.resolve(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.permissions.NotificationPermissionsModule.getPermissionsWithPromiseImplApi33$lambda$7(expo.modules.notifications.permissions.NotificationPermissionsModule, expo.modules.kotlin.Promise, java.util.Map):void");
    }

    /* access modifiers changed from: private */
    public final void getPermissionsWithPromiseImplClassic(Promise promise) {
        NotificationManagerCompat from = NotificationManagerCompat.from(getContext());
        Intrinsics.checkNotNullExpressionValue(from, "from(...)");
        boolean areNotificationsEnabled = from.areNotificationsEnabled();
        PermissionsStatus permissionsStatus = areNotificationsEnabled ? PermissionsStatus.GRANTED : PermissionsStatus.DENIED;
        boolean z = true;
        Bundle bundleOf = BundleKt.bundleOf(TuplesKt.to(NotificationsChannelSerializer.IMPORTANCE_KEY, Integer.valueOf(from.getImportance())));
        Object systemService = getContext().getSystemService(NotificationsService.NOTIFICATION_KEY);
        NotificationManager notificationManager = systemService instanceof NotificationManager ? (NotificationManager) systemService : null;
        if (notificationManager != null) {
            bundleOf.putInt("interruptionFilter", notificationManager.getCurrentInterruptionFilter());
        }
        Pair[] pairArr = new Pair[5];
        pairArr[0] = TuplesKt.to(PermissionsResponse.EXPIRES_KEY, "never");
        pairArr[1] = TuplesKt.to("status", permissionsStatus.getStatus());
        pairArr[2] = TuplesKt.to(PermissionsResponse.CAN_ASK_AGAIN_KEY, Boolean.valueOf(areNotificationsEnabled));
        if (permissionsStatus != PermissionsStatus.GRANTED) {
            z = false;
        }
        pairArr[3] = TuplesKt.to(PermissionsResponse.GRANTED_KEY, Boolean.valueOf(z));
        pairArr[4] = TuplesKt.to(DeviceInfo.OS_NAME, bundleOf);
        promise.resolve(BundleKt.bundleOf(pairArr));
    }

    /* access modifiers changed from: private */
    public final void requestPermissionsWithPromiseImplApi33(Promise promise) {
        Permissions permissions = getPermissions();
        NotificationPermissionsModule$$ExternalSyntheticLambda1 notificationPermissionsModule$$ExternalSyntheticLambda1 = new NotificationPermissionsModule$$ExternalSyntheticLambda1(this, promise);
        String[] access$getPERMISSIONS$p = NotificationPermissionsModuleKt.PERMISSIONS;
        permissions.askForPermissions(notificationPermissionsModule$$ExternalSyntheticLambda1, (String[]) Arrays.copyOf(access$getPERMISSIONS$p, access$getPERMISSIONS$p.length));
    }

    /* access modifiers changed from: private */
    public static final void requestPermissionsWithPromiseImplApi33$lambda$9(NotificationPermissionsModule notificationPermissionsModule, Promise promise, Map map) {
        Intrinsics.checkNotNullParameter(notificationPermissionsModule, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        notificationPermissionsModule.getPermissionsWithPromiseImplApi33(promise);
    }
}
