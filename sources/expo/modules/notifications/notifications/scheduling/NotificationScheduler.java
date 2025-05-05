package expo.modules.notifications.notifications.scheduling;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014JF\u0010\u0016\u001a\u00020\u00172<\u0010\u0018\u001a8\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0015\u0012\u0013\u0018\u00010\u001e¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\n0\u0019j\u0002` H\u0004J\b\u0010!\u001a\u00020\"H\u0016J\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110&H\u0014J\u0014\u0010'\u001a\u0004\u0018\u00010\u00152\b\u0010(\u001a\u0004\u0018\u00010)H\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006*"}, d2 = {"Lexpo/modules/notifications/notifications/scheduling/NotificationScheduler;", "Lexpo/modules/kotlin/modules/Module;", "()V", "handler", "Landroid/os/Handler;", "schedulingContext", "Landroid/content/Context;", "getSchedulingContext", "()Landroid/content/Context;", "cancelAllScheduledNotificationsAsync", "", "promise", "Lexpo/modules/kotlin/Promise;", "cancelScheduledNotificationAsync", "identifier", "", "createNotificationRequest", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "content", "Lexpo/modules/notifications/notifications/model/NotificationContent;", "notificationTrigger", "Lexpo/modules/notifications/notifications/interfaces/NotificationTrigger;", "createResultReceiver", "Landroid/os/ResultReceiver;", "body", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "resultCode", "Landroid/os/Bundle;", "resultData", "Lexpo/modules/notifications/ResultReceiverBody;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "serializeScheduledNotificationRequests", "", "requests", "", "triggerFromParams", "params", "Lexpo/modules/core/arguments/ReadableArguments;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationScheduler.kt */
public class NotificationScheduler extends Module {
    private final Handler handler = new Handler(Looper.getMainLooper());

    /* access modifiers changed from: protected */
    public Context getSchedulingContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    /* access modifiers changed from: protected */
    public final ResultReceiver createResultReceiver(Function2<? super Integer, ? super Bundle, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, TtmlNode.TAG_BODY);
        return UtilsKt.createDefaultResultReceiver(this.handler, function2);
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        AsyncFunction asyncFunction2;
        AsyncFunction asyncFunction3;
        AsyncFunction asyncFunction4;
        AsyncFunction asyncFunction5;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoNotificationScheduler");
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("getAllScheduledNotificationsAsync", new AnyType[0], new NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$1(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("getAllScheduledNotificationsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$2.INSTANCE))}, new NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$3(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("getAllScheduledNotificationsAsync", asyncFunction);
            ObjectDefinitionBuilder objectDefinitionBuilder2 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction2 = new AsyncFunctionWithPromiseComponent("scheduleNotificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$4.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$5.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$6.INSTANCE))}, new NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$7(this));
            } else {
                asyncFunction2 = new AsyncFunctionComponent("scheduleNotificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$8.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$9.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$10.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$11.INSTANCE))}, new NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$12(this));
            }
            objectDefinitionBuilder2.getAsyncFunctions().put("scheduleNotificationAsync", asyncFunction2);
            ObjectDefinitionBuilder objectDefinitionBuilder3 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction3 = new AsyncFunctionWithPromiseComponent("cancelScheduledNotificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$13.INSTANCE))}, new NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$14(this));
            } else {
                asyncFunction3 = new AsyncFunctionComponent("cancelScheduledNotificationAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$15.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$16.INSTANCE))}, new NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$17(this));
            }
            objectDefinitionBuilder3.getAsyncFunctions().put("cancelScheduledNotificationAsync", asyncFunction3);
            ObjectDefinitionBuilder objectDefinitionBuilder4 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction4 = new AsyncFunctionWithPromiseComponent("cancelAllScheduledNotificationsAsync", new AnyType[0], new NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$18(this));
            } else {
                asyncFunction4 = new AsyncFunctionComponent("cancelAllScheduledNotificationsAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$19.INSTANCE))}, new NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$20(this));
            }
            objectDefinitionBuilder4.getAsyncFunctions().put("cancelAllScheduledNotificationsAsync", asyncFunction4);
            ObjectDefinitionBuilder objectDefinitionBuilder5 = moduleDefinitionBuilder;
            if (Promise.class == Promise.class) {
                asyncFunction5 = new AsyncFunctionWithPromiseComponent("getNextTriggerDateAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$21.INSTANCE))}, new NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$22(this));
            } else {
                asyncFunction5 = new AsyncFunctionComponent("getNextTriggerDateAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(ReadableArguments.class), true, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$23.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Promise.class), false, NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$24.INSTANCE))}, new NotificationScheduler$definition$lambda$4$$inlined$AsyncFunction$25(this));
            }
            objectDefinitionBuilder5.getAsyncFunctions().put("getNextTriggerDateAsync", asyncFunction5);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    public void cancelScheduledNotificationAsync(String str, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        NotificationsService.Companion.removeScheduledNotification(getSchedulingContext(), str, createResultReceiver(new NotificationScheduler$cancelScheduledNotificationAsync$1(promise)));
    }

    public void cancelAllScheduledNotificationsAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        NotificationsService.Companion.removeAllScheduledNotifications(getSchedulingContext(), createResultReceiver(new NotificationScheduler$cancelAllScheduledNotificationsAsync$1(promise)));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: java.lang.Number} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v34, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: java.lang.Number} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final expo.modules.notifications.notifications.interfaces.NotificationTrigger triggerFromParams(expo.modules.core.arguments.ReadableArguments r9) throws expo.modules.core.errors.InvalidArgumentException {
        /*
            r8 = this;
            r0 = 0
            if (r9 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "channelId"
            java.lang.String r7 = r9.getString(r1, r0)
            java.lang.String r1 = "type"
            java.lang.String r1 = r9.getString(r1)
            if (r1 == 0) goto L_0x0183
            int r2 = r1.hashCode()
            java.lang.String r3 = "minute"
            java.lang.String r4 = "hour"
            switch(r2) {
                case -791707519: goto L_0x0133;
                case -734561654: goto L_0x00cd;
                case 3076014: goto L_0x00a0;
                case 95346201: goto L_0x0063;
                case 738950403: goto L_0x0051;
                case 913014450: goto L_0x001f;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x0183
        L_0x001f:
            java.lang.String r2 = "timeInterval"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0183
            java.lang.String r1 = "seconds"
            java.lang.Object r1 = r9.get(r1)
            boolean r2 = r1 instanceof java.lang.Number
            if (r2 == 0) goto L_0x0034
            r0 = r1
            java.lang.Number r0 = (java.lang.Number) r0
        L_0x0034:
            if (r0 == 0) goto L_0x0049
            expo.modules.notifications.notifications.triggers.TimeIntervalTrigger r1 = new expo.modules.notifications.notifications.triggers.TimeIntervalTrigger
            long r2 = r0.longValue()
            java.lang.String r0 = "repeats"
            boolean r9 = r9.getBoolean(r0)
            r1.<init>(r2, r9, r7)
            expo.modules.notifications.notifications.interfaces.NotificationTrigger r1 = (expo.modules.notifications.notifications.interfaces.NotificationTrigger) r1
            goto L_0x017a
        L_0x0049:
            expo.modules.core.errors.InvalidArgumentException r9 = new expo.modules.core.errors.InvalidArgumentException
            java.lang.String r0 = "Invalid value provided as interval of trigger."
            r9.<init>((java.lang.String) r0)
            throw r9
        L_0x0051:
            java.lang.String r9 = "channel"
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x0183
            expo.modules.notifications.notifications.triggers.ChannelAwareTrigger r9 = new expo.modules.notifications.notifications.triggers.ChannelAwareTrigger
            r9.<init>((java.lang.String) r7)
            r1 = r9
            expo.modules.notifications.notifications.interfaces.NotificationTrigger r1 = (expo.modules.notifications.notifications.interfaces.NotificationTrigger) r1
            goto L_0x017a
        L_0x0063:
            java.lang.String r2 = "daily"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0183
            java.lang.Object r1 = r9.get(r4)
            boolean r2 = r1 instanceof java.lang.Number
            if (r2 == 0) goto L_0x0076
            java.lang.Number r1 = (java.lang.Number) r1
            goto L_0x0077
        L_0x0076:
            r1 = r0
        L_0x0077:
            java.lang.Object r9 = r9.get(r3)
            boolean r2 = r9 instanceof java.lang.Number
            if (r2 == 0) goto L_0x0082
            r0 = r9
            java.lang.Number r0 = (java.lang.Number) r0
        L_0x0082:
            if (r1 == 0) goto L_0x0098
            if (r0 == 0) goto L_0x0098
            expo.modules.notifications.notifications.triggers.DailyTrigger r9 = new expo.modules.notifications.notifications.triggers.DailyTrigger
            int r1 = r1.intValue()
            int r0 = r0.intValue()
            r9.<init>(r1, r0, r7)
            r1 = r9
            expo.modules.notifications.notifications.interfaces.NotificationTrigger r1 = (expo.modules.notifications.notifications.interfaces.NotificationTrigger) r1
            goto L_0x017a
        L_0x0098:
            expo.modules.core.errors.InvalidArgumentException r9 = new expo.modules.core.errors.InvalidArgumentException
            java.lang.String r0 = "Invalid value(s) provided for daily trigger."
            r9.<init>((java.lang.String) r0)
            throw r9
        L_0x00a0:
            java.lang.String r2 = "date"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0183
            java.lang.String r1 = "timestamp"
            java.lang.Object r9 = r9.get(r1)
            boolean r1 = r9 instanceof java.lang.Number
            if (r1 == 0) goto L_0x00b5
            r0 = r9
            java.lang.Number r0 = (java.lang.Number) r0
        L_0x00b5:
            if (r0 == 0) goto L_0x00c5
            expo.modules.notifications.notifications.triggers.DateTrigger r9 = new expo.modules.notifications.notifications.triggers.DateTrigger
            long r0 = r0.longValue()
            r9.<init>((long) r0, (java.lang.String) r7)
            r1 = r9
            expo.modules.notifications.notifications.interfaces.NotificationTrigger r1 = (expo.modules.notifications.notifications.interfaces.NotificationTrigger) r1
            goto L_0x017a
        L_0x00c5:
            expo.modules.core.errors.InvalidArgumentException r9 = new expo.modules.core.errors.InvalidArgumentException
            java.lang.String r0 = "Invalid value provided as date of trigger."
            r9.<init>((java.lang.String) r0)
            throw r9
        L_0x00cd:
            java.lang.String r2 = "yearly"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0183
            java.lang.String r1 = "day"
            java.lang.Object r1 = r9.get(r1)
            boolean r2 = r1 instanceof java.lang.Number
            if (r2 == 0) goto L_0x00e2
            java.lang.Number r1 = (java.lang.Number) r1
            goto L_0x00e3
        L_0x00e2:
            r1 = r0
        L_0x00e3:
            java.lang.String r2 = "month"
            java.lang.Object r2 = r9.get(r2)
            boolean r5 = r2 instanceof java.lang.Number
            if (r5 == 0) goto L_0x00f0
            java.lang.Number r2 = (java.lang.Number) r2
            goto L_0x00f1
        L_0x00f0:
            r2 = r0
        L_0x00f1:
            java.lang.Object r4 = r9.get(r4)
            boolean r5 = r4 instanceof java.lang.Number
            if (r5 == 0) goto L_0x00fc
            java.lang.Number r4 = (java.lang.Number) r4
            goto L_0x00fd
        L_0x00fc:
            r4 = r0
        L_0x00fd:
            java.lang.Object r9 = r9.get(r3)
            boolean r3 = r9 instanceof java.lang.Number
            if (r3 == 0) goto L_0x0108
            r0 = r9
            java.lang.Number r0 = (java.lang.Number) r0
        L_0x0108:
            if (r1 == 0) goto L_0x012b
            if (r2 == 0) goto L_0x012b
            if (r4 == 0) goto L_0x012b
            if (r0 == 0) goto L_0x012b
            expo.modules.notifications.notifications.triggers.YearlyTrigger r9 = new expo.modules.notifications.notifications.triggers.YearlyTrigger
            int r3 = r1.intValue()
            int r1 = r2.intValue()
            int r5 = r4.intValue()
            int r6 = r0.intValue()
            r2 = r9
            r4 = r1
            r2.<init>(r3, r4, r5, r6, r7)
            r1 = r9
            expo.modules.notifications.notifications.interfaces.NotificationTrigger r1 = (expo.modules.notifications.notifications.interfaces.NotificationTrigger) r1
            goto L_0x017a
        L_0x012b:
            expo.modules.core.errors.InvalidArgumentException r9 = new expo.modules.core.errors.InvalidArgumentException
            java.lang.String r0 = "Invalid value(s) provided for yearly trigger."
            r9.<init>((java.lang.String) r0)
            throw r9
        L_0x0133:
            java.lang.String r2 = "weekly"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0183
            java.lang.String r1 = "weekday"
            java.lang.Object r1 = r9.get(r1)
            boolean r2 = r1 instanceof java.lang.Number
            if (r2 == 0) goto L_0x0148
            java.lang.Number r1 = (java.lang.Number) r1
            goto L_0x0149
        L_0x0148:
            r1 = r0
        L_0x0149:
            java.lang.Object r2 = r9.get(r4)
            boolean r4 = r2 instanceof java.lang.Number
            if (r4 == 0) goto L_0x0154
            java.lang.Number r2 = (java.lang.Number) r2
            goto L_0x0155
        L_0x0154:
            r2 = r0
        L_0x0155:
            java.lang.Object r9 = r9.get(r3)
            boolean r3 = r9 instanceof java.lang.Number
            if (r3 == 0) goto L_0x0160
            r0 = r9
            java.lang.Number r0 = (java.lang.Number) r0
        L_0x0160:
            if (r1 == 0) goto L_0x017b
            if (r2 == 0) goto L_0x017b
            if (r0 == 0) goto L_0x017b
            expo.modules.notifications.notifications.triggers.WeeklyTrigger r9 = new expo.modules.notifications.notifications.triggers.WeeklyTrigger
            int r1 = r1.intValue()
            int r2 = r2.intValue()
            int r0 = r0.intValue()
            r9.<init>(r1, r2, r0, r7)
            r1 = r9
            expo.modules.notifications.notifications.interfaces.NotificationTrigger r1 = (expo.modules.notifications.notifications.interfaces.NotificationTrigger) r1
        L_0x017a:
            return r1
        L_0x017b:
            expo.modules.core.errors.InvalidArgumentException r9 = new expo.modules.core.errors.InvalidArgumentException
            java.lang.String r0 = "Invalid value(s) provided for weekly trigger."
            r9.<init>((java.lang.String) r0)
            throw r9
        L_0x0183:
            expo.modules.core.errors.InvalidArgumentException r9 = new expo.modules.core.errors.InvalidArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Trigger of type: "
            r0.<init>(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = " is not supported on Android."
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r9.<init>((java.lang.String) r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.notifications.scheduling.NotificationScheduler.triggerFromParams(expo.modules.core.arguments.ReadableArguments):expo.modules.notifications.notifications.interfaces.NotificationTrigger");
    }

    /* access modifiers changed from: protected */
    public NotificationRequest createNotificationRequest(String str, NotificationContent notificationContent, NotificationTrigger notificationTrigger) {
        Intrinsics.checkNotNullParameter(str, "identifier");
        Intrinsics.checkNotNullParameter(notificationContent, "content");
        return new NotificationRequest(str, notificationContent, notificationTrigger);
    }

    /* access modifiers changed from: protected */
    public List<Bundle> serializeScheduledNotificationRequests(Collection<? extends NotificationRequest> collection) {
        Intrinsics.checkNotNullParameter(collection, "requests");
        Iterable<NotificationRequest> iterable = collection;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (NotificationRequest bundle : iterable) {
            arrayList.add(NotificationSerializer.toBundle(bundle));
        }
        return (List) arrayList;
    }
}
