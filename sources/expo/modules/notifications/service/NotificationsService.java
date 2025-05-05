package expo.modules.notifications.service;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.core.app.RemoteInput;
import androidx.media3.common.C;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationAction;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.notifications.model.NotificationCategory;
import expo.modules.notifications.notifications.model.NotificationRequest;
import expo.modules.notifications.notifications.model.NotificationResponse;
import expo.modules.notifications.notifications.model.TextInputNotificationAction;
import expo.modules.notifications.notifications.model.TextInputNotificationResponse;
import expo.modules.notifications.service.delegates.ExpoCategoriesDelegate;
import expo.modules.notifications.service.delegates.ExpoHandlingDelegate;
import expo.modules.notifications.service.delegates.ExpoPresentationDelegate;
import expo.modules.notifications.service.delegates.ExpoSchedulingDelegate;
import expo.modules.notifications.service.interfaces.CategoriesDelegate;
import expo.modules.notifications.service.interfaces.HandlingDelegate;
import expo.modules.notifications.service.interfaces.PresentationDelegate;
import expo.modules.notifications.service.interfaces.SchedulingDelegate;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0016\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0018\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010 \u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010!\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\"\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010#\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006%"}, d2 = {"Lexpo/modules/notifications/service/NotificationsService;", "Landroid/content/BroadcastReceiver;", "()V", "getCategoriesDelegate", "Lexpo/modules/notifications/service/interfaces/CategoriesDelegate;", "context", "Landroid/content/Context;", "getHandlingDelegate", "Lexpo/modules/notifications/service/interfaces/HandlingDelegate;", "getPresentationDelegate", "Lexpo/modules/notifications/service/interfaces/PresentationDelegate;", "getSchedulingDelegate", "Lexpo/modules/notifications/service/interfaces/SchedulingDelegate;", "handleIntent", "", "intent", "Landroid/content/Intent;", "onDeleteCategory", "Landroid/os/Bundle;", "onDismissAllNotifications", "onDismissNotifications", "onGetAllPresentedNotifications", "onGetAllScheduledNotifications", "onGetCategories", "onGetScheduledNotification", "onNotificationTriggered", "onNotificationsDropped", "onPresentNotification", "onReceive", "onReceiveNotification", "onReceiveNotificationResponse", "onRemoveAllScheduledNotifications", "onRemoveScheduledNotifications", "onScheduleNotification", "onSetCategory", "onSetupScheduledNotifications", "Companion", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationsService.kt */
public class NotificationsService extends BroadcastReceiver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DELETE_CATEGORY_TYPE = "deleteCategory";
    private static final String DISMISS_ALL_TYPE = "dismissAll";
    private static final String DISMISS_SELECTED_TYPE = "dismissSelected";
    private static final String DROPPED_TYPE = "dropped";
    public static final int ERROR_CODE = 1;
    public static final String EVENT_TYPE_KEY = "type";
    public static final String EXCEPTION_KEY = "exception";
    private static final String GET_ALL_DISPLAYED_TYPE = "getAllDisplayed";
    private static final String GET_ALL_SCHEDULED_TYPE = "getAllScheduled";
    private static final String GET_CATEGORIES_TYPE = "getCategories";
    private static final String GET_SCHEDULED_TYPE = "getScheduled";
    public static final String IDENTIFIERS_KEY = "identifiers";
    public static final String IDENTIFIER_KEY = "identifier";
    public static final String NOTIFICATIONS_KEY = "notifications";
    public static final String NOTIFICATION_ACTION_KEY = "notificationAction";
    public static final String NOTIFICATION_BEHAVIOR_KEY = "notificationBehavior";
    public static final String NOTIFICATION_CATEGORIES_KEY = "notificationCategories";
    public static final String NOTIFICATION_CATEGORY_KEY = "notificationCategory";
    public static final String NOTIFICATION_EVENT_ACTION = "expo.modules.notifications.NOTIFICATION_EVENT";
    public static final String NOTIFICATION_KEY = "notification";
    public static final String NOTIFICATION_REQUESTS_KEY = "notificationRequests";
    public static final String NOTIFICATION_REQUEST_KEY = "notificationRequest";
    public static final String NOTIFICATION_RESPONSE_KEY = "notificationResponse";
    private static final String PRESENT_TYPE = "present";
    public static final String RECEIVER_KEY = "receiver";
    private static final String RECEIVE_RESPONSE_TYPE = "receiveResponse";
    private static final String RECEIVE_TYPE = "receive";
    private static final String REMOVE_ALL_TYPE = "removeAll";
    private static final String REMOVE_SELECTED_TYPE = "removeSelected";
    private static final String SCHEDULE_TYPE = "schedule";
    /* access modifiers changed from: private */
    public static final List<String> SETUP_ACTIONS = CollectionsKt.listOf("android.intent.action.BOOT_COMPLETED", "android.intent.action.REBOOT", "android.intent.action.MY_PACKAGE_REPLACED", "android.intent.action.QUICKBOOT_POWERON", "com.htc.intent.action.QUICKBOOT_POWERON");
    private static final String SET_CATEGORY_TYPE = "setCategory";
    public static final String SUCCEEDED_KEY = "succeeded";
    public static final int SUCCESS_CODE = 0;
    public static final String TEXT_INPUT_NOTIFICATION_RESPONSE_KEY = "textInputNotificationResponse";
    private static final String TRIGGER_TYPE = "trigger";
    public static final String USER_TEXT_RESPONSE_KEY = "userTextResponse";

    @Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u000102J\u001e\u00103\u001a\u0002042\u0006\u0010/\u001a\u0002002\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000208J\u0016\u00109\u001a\u0002042\u0006\u0010/\u001a\u0002002\u0006\u0010:\u001a\u00020\u0004J\"\u0010;\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010:\u001a\u00020\u00042\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J-\u0010?\u001a\u00020<2\u0006\u0010/\u001a\u0002002\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00040A2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>¢\u0006\u0002\u0010BJ\u001a\u0010C\u001a\u00020<2\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\u0016\u0010D\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010E\u001a\u00020.J\u0018\u0010F\u001a\u0004\u0018\u00010G2\u0006\u0010/\u001a\u0002002\u0006\u0010E\u001a\u00020.J\u001a\u0010H\u001a\u00020<2\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\u001a\u0010I\u001a\u00020<2\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J\u001a\u0010K\u001a\u00020<2\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\u000e\u0010L\u001a\u00020M2\u0006\u0010E\u001a\u00020.J\u0010\u0010N\u001a\u0004\u0018\u00010M2\u0006\u0010E\u001a\u00020.J\"\u0010O\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010:\u001a\u00020\u00042\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J\b\u0010P\u001a\u00020QH\u0004J\u0010\u0010R\u001a\u00020Q2\u0006\u0010:\u001a\u00020\u0004H\u0004J\u000e\u0010S\u001a\u00020<2\u0006\u0010/\u001a\u000200J\u0012\u0010T\u001a\u0004\u0018\u00010U2\u0006\u0010V\u001a\u00020WH\u0002J.\u0010X\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u00105\u001a\u0002062\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010Z2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\"\u0010[\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u00105\u001a\u0002062\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\u001a\u0010\\\u001a\u00020<2\u0006\u0010/\u001a\u0002002\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J\"\u0010]\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010:\u001a\u00020\u00042\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J(\u0010^\u001a\u00020<2\u0006\u0010/\u001a\u0002002\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00040_2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J\"\u0010`\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010a\u001a\u00020b2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010>J\"\u0010c\u001a\u00020<2\u0006\u0010/\u001a\u0002002\u0006\u0010d\u001a\u00020e2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010>J\u0016\u0010f\u001a\u00020<2\u0006\u0010E\u001a\u00020.2\u0006\u0010g\u001a\u00020MJ-\u0010h\u001a\u0004\u0018\u0001Hi\"\u0004\b\u0000\u0010i2\f\u0010j\u001a\b\u0012\u0004\u0012\u0002Hi0k2\b\u0010l\u001a\u0004\u0018\u00010UH\u0002¢\u0006\u0002\u0010mR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040$¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u000e\u0010'\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006n"}, d2 = {"Lexpo/modules/notifications/service/NotificationsService$Companion;", "", "()V", "DELETE_CATEGORY_TYPE", "", "DISMISS_ALL_TYPE", "DISMISS_SELECTED_TYPE", "DROPPED_TYPE", "ERROR_CODE", "", "EVENT_TYPE_KEY", "EXCEPTION_KEY", "GET_ALL_DISPLAYED_TYPE", "GET_ALL_SCHEDULED_TYPE", "GET_CATEGORIES_TYPE", "GET_SCHEDULED_TYPE", "IDENTIFIERS_KEY", "IDENTIFIER_KEY", "NOTIFICATIONS_KEY", "NOTIFICATION_ACTION_KEY", "NOTIFICATION_BEHAVIOR_KEY", "NOTIFICATION_CATEGORIES_KEY", "NOTIFICATION_CATEGORY_KEY", "NOTIFICATION_EVENT_ACTION", "NOTIFICATION_KEY", "NOTIFICATION_REQUESTS_KEY", "NOTIFICATION_REQUEST_KEY", "NOTIFICATION_RESPONSE_KEY", "PRESENT_TYPE", "RECEIVER_KEY", "RECEIVE_RESPONSE_TYPE", "RECEIVE_TYPE", "REMOVE_ALL_TYPE", "REMOVE_SELECTED_TYPE", "SCHEDULE_TYPE", "SETUP_ACTIONS", "", "getSETUP_ACTIONS", "()Ljava/util/List;", "SET_CATEGORY_TYPE", "SUCCEEDED_KEY", "SUCCESS_CODE", "TEXT_INPUT_NOTIFICATION_RESPONSE_KEY", "TRIGGER_TYPE", "USER_TEXT_RESPONSE_KEY", "createNotificationResponseBroadcastIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "extras", "Landroid/os/Bundle;", "createNotificationResponseIntent", "Landroid/app/PendingIntent;", "notification", "Lexpo/modules/notifications/notifications/model/Notification;", "action", "Lexpo/modules/notifications/notifications/model/NotificationAction;", "createNotificationTrigger", "identifier", "deleteCategory", "", "receiver", "Landroid/os/ResultReceiver;", "dismiss", "identifiers", "", "(Landroid/content/Context;[Ljava/lang/String;Landroid/os/ResultReceiver;)V", "dismissAll", "doWork", "intent", "findDesignatedBroadcastReceiver", "Landroid/content/pm/ActivityInfo;", "getAllPresented", "getAllScheduledNotifications", "resultReceiver", "getCategories", "getNotificationResponseFromBroadcastIntent", "Lexpo/modules/notifications/notifications/model/NotificationResponse;", "getNotificationResponseFromOpenIntent", "getScheduledNotification", "getUriBuilder", "Landroid/net/Uri$Builder;", "getUriBuilderForIdentifier", "handleDropped", "marshalObject", "", "objectToMarshal", "Landroid/os/Parcelable;", "present", "behavior", "Lexpo/modules/notifications/notifications/model/NotificationBehavior;", "receive", "removeAllScheduledNotifications", "removeScheduledNotification", "removeScheduledNotifications", "", "schedule", "notificationRequest", "Lexpo/modules/notifications/notifications/model/NotificationRequest;", "setCategory", "category", "Lexpo/modules/notifications/notifications/model/NotificationCategory;", "setNotificationResponseToIntent", "notificationResponse", "unmarshalObject", "T", "creator", "Landroid/os/Parcelable$Creator;", "byteArray", "(Landroid/os/Parcelable$Creator;[B)Ljava/lang/Object;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: NotificationsService.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<String> getSETUP_ACTIONS() {
            return NotificationsService.SETUP_ACTIONS;
        }

        public static /* synthetic */ void getAllPresented$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.getAllPresented(context, resultReceiver);
        }

        public final void getAllPresented(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().build());
            intent.putExtra("type", NotificationsService.GET_ALL_DISPLAYED_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void present$default(Companion companion, Context context, Notification notification, NotificationBehavior notificationBehavior, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                notificationBehavior = null;
            }
            if ((i & 8) != 0) {
                resultReceiver = null;
            }
            companion.present(context, notification, notificationBehavior, resultReceiver);
        }

        public final void present(Context context, Notification notification, NotificationBehavior notificationBehavior, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notification, NotificationsService.NOTIFICATION_KEY);
            String identifier = notification.getNotificationRequest().getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilderForIdentifier(identifier).appendPath(NotificationsService.PRESENT_TYPE).build());
            intent.putExtra("type", NotificationsService.PRESENT_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_KEY, notification);
            intent.putExtra(NotificationsService.NOTIFICATION_BEHAVIOR_KEY, notificationBehavior);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void receive$default(Companion companion, Context context, Notification notification, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.receive(context, notification, resultReceiver);
        }

        public final void receive(Context context, Notification notification, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notification, NotificationsService.NOTIFICATION_KEY);
            String identifier = notification.getNotificationRequest().getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "getIdentifier(...)");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilderForIdentifier(identifier).appendPath(NotificationsService.RECEIVE_TYPE).build());
            intent.putExtra("type", NotificationsService.RECEIVE_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_KEY, notification);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void dismiss$default(Companion companion, Context context, String[] strArr, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.dismiss(context, strArr, resultReceiver);
        }

        public final void dismiss(Context context, String[] strArr, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(strArr, NotificationsService.IDENTIFIERS_KEY);
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("dismiss").build());
            intent.putExtra("type", NotificationsService.DISMISS_SELECTED_TYPE);
            intent.putExtra(NotificationsService.IDENTIFIERS_KEY, strArr);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void dismissAll$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.dismissAll(context, resultReceiver);
        }

        public final void dismissAll(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("dismiss").build());
            intent.putExtra("type", NotificationsService.DISMISS_ALL_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public final void handleDropped(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION);
            intent.putExtra("type", NotificationsService.DROPPED_TYPE);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void getCategories$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.getCategories(context, resultReceiver);
        }

        public final void getCategories(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("categories").build());
            intent.putExtra("type", NotificationsService.GET_CATEGORIES_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void setCategory$default(Companion companion, Context context, NotificationCategory notificationCategory, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.setCategory(context, notificationCategory, resultReceiver);
        }

        public final void setCategory(Context context, NotificationCategory notificationCategory, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notificationCategory, "category");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("categories").appendPath(notificationCategory.getIdentifier()).build());
            intent.putExtra("type", NotificationsService.SET_CATEGORY_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_CATEGORY_KEY, notificationCategory);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void deleteCategory$default(Companion companion, Context context, String str, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.deleteCategory(context, str, resultReceiver);
        }

        public final void deleteCategory(Context context, String str, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "identifier");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("categories").appendPath(str).build());
            intent.putExtra("type", NotificationsService.DELETE_CATEGORY_TYPE);
            intent.putExtra("identifier", str);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void getAllScheduledNotifications$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.getAllScheduledNotifications(context, resultReceiver);
        }

        public final void getAllScheduledNotifications(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION);
            intent.putExtra("type", NotificationsService.GET_ALL_SCHEDULED_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void getScheduledNotification$default(Companion companion, Context context, String str, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.getScheduledNotification(context, str, resultReceiver);
        }

        public final void getScheduledNotification(Context context, String str, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "identifier");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").appendPath(str).build());
            intent.putExtra("type", NotificationsService.GET_SCHEDULED_TYPE);
            intent.putExtra("identifier", str);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void schedule$default(Companion companion, Context context, NotificationRequest notificationRequest, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.schedule(context, notificationRequest, resultReceiver);
        }

        public final void schedule(Context context, NotificationRequest notificationRequest, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notificationRequest, NotificationsService.NOTIFICATION_REQUEST_KEY);
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").appendPath(notificationRequest.getIdentifier()).build());
            intent.putExtra("type", "schedule");
            intent.putExtra(NotificationsService.NOTIFICATION_REQUEST_KEY, notificationRequest);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void removeScheduledNotification$default(Companion companion, Context context, String str, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.removeScheduledNotification(context, str, resultReceiver);
        }

        public final void removeScheduledNotification(Context context, String str, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "identifier");
            removeScheduledNotifications(context, CollectionsKt.listOf(str), resultReceiver);
        }

        public static /* synthetic */ void removeScheduledNotifications$default(Companion companion, Context context, Collection collection, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 4) != 0) {
                resultReceiver = null;
            }
            companion.removeScheduledNotifications(context, collection, resultReceiver);
        }

        public final void removeScheduledNotifications(Context context, Collection<String> collection, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(collection, NotificationsService.IDENTIFIERS_KEY);
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").build());
            intent.putExtra("type", NotificationsService.REMOVE_SELECTED_TYPE);
            intent.putExtra(NotificationsService.IDENTIFIERS_KEY, (String[]) collection.toArray(new String[0]));
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public static /* synthetic */ void removeAllScheduledNotifications$default(Companion companion, Context context, ResultReceiver resultReceiver, int i, Object obj) {
            if ((i & 2) != 0) {
                resultReceiver = null;
            }
            companion.removeAllScheduledNotifications(context, resultReceiver);
        }

        public final void removeAllScheduledNotifications(Context context, ResultReceiver resultReceiver) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION);
            intent.putExtra("type", NotificationsService.REMOVE_ALL_TYPE);
            intent.putExtra(NotificationsService.RECEIVER_KEY, resultReceiver);
            Unit unit = Unit.INSTANCE;
            doWork(context, intent);
        }

        public final void doWork(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            ActivityInfo findDesignatedBroadcastReceiver = findDesignatedBroadcastReceiver(context, intent);
            if (findDesignatedBroadcastReceiver != null) {
                intent.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
                context.sendBroadcast(intent);
                return;
            }
            SentryLogcatAdapter.e("expo-notifications", "No service capable of handling notifications found (intent = " + intent.getAction() + "). Ensure that you have configured your AndroidManifest.xml properly.");
        }

        /* access modifiers changed from: protected */
        public final Uri.Builder getUriBuilder() {
            Uri.Builder buildUpon = Uri.parse("expo-notifications://notifications/").buildUpon();
            Intrinsics.checkNotNullExpressionValue(buildUpon, "buildUpon(...)");
            return buildUpon;
        }

        /* access modifiers changed from: protected */
        public final Uri.Builder getUriBuilderForIdentifier(String str) {
            Intrinsics.checkNotNullParameter(str, "identifier");
            Uri.Builder appendPath = getUriBuilder().appendPath(str);
            Intrinsics.checkNotNullExpressionValue(appendPath, "appendPath(...)");
            return appendPath;
        }

        public final ActivityInfo findDesignatedBroadcastReceiver(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            Intent intent2 = new Intent(intent.getAction()).setPackage(context.getPackageName());
            Intrinsics.checkNotNullExpressionValue(intent2, "setPackage(...)");
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent2, 0);
            Intrinsics.checkNotNullExpressionValue(queryBroadcastReceivers, "queryBroadcastReceivers(...)");
            ResolveInfo resolveInfo = (ResolveInfo) CollectionsKt.firstOrNull(queryBroadcastReceivers);
            if (resolveInfo != null) {
                return resolveInfo.activityInfo;
            }
            return null;
        }

        public final PendingIntent createNotificationTrigger(Context context, String str) {
            String className;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "identifier");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath("scheduled").appendPath(str).appendPath(NotificationsService.TRIGGER_TYPE).build());
            ActivityInfo findDesignatedBroadcastReceiver = NotificationsService.Companion.findDesignatedBroadcastReceiver(context, intent);
            if (findDesignatedBroadcastReceiver != null) {
                intent.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
            }
            intent.putExtra("type", NotificationsService.TRIGGER_TYPE);
            intent.putExtra("identifier", str);
            int i = Build.VERSION.SDK_INT >= 31 ? 33554432 : 0;
            ComponentName component = intent.getComponent();
            PendingIntent broadcast = PendingIntent.getBroadcast(context, (component == null || (className = component.getClassName()) == null) ? NotificationsService.class.hashCode() : className.hashCode(), intent, i | C.BUFFER_FLAG_FIRST_SAMPLE);
            Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
            return broadcast;
        }

        public final PendingIntent createNotificationResponseIntent(Context context, Notification notification, NotificationAction notificationAction) {
            String className;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(notification, NotificationsService.NOTIFICATION_KEY);
            Intrinsics.checkNotNullParameter(notificationAction, "action");
            Intent intent = new Intent(NotificationsService.NOTIFICATION_EVENT_ACTION, getUriBuilder().appendPath(notification.getNotificationRequest().getIdentifier()).appendPath("actions").appendPath(notificationAction.getIdentifier()).build());
            ActivityInfo findDesignatedBroadcastReceiver = NotificationsService.Companion.findDesignatedBroadcastReceiver(context, intent);
            if (findDesignatedBroadcastReceiver != null) {
                intent.setComponent(new ComponentName(findDesignatedBroadcastReceiver.packageName, findDesignatedBroadcastReceiver.name));
            }
            intent.putExtra("type", NotificationsService.RECEIVE_RESPONSE_TYPE);
            intent.putExtra(NotificationsService.NOTIFICATION_KEY, notification);
            intent.putExtra(NotificationsService.NOTIFICATION_ACTION_KEY, notificationAction);
            if (notificationAction.opensAppToForeground()) {
                return ExpoHandlingDelegate.Companion.createPendingIntentForOpeningApp(context, intent, getNotificationResponseFromBroadcastIntent(intent));
            }
            int i = Build.VERSION.SDK_INT >= 31 ? 33554432 : 0;
            ComponentName component = intent.getComponent();
            PendingIntent broadcast = PendingIntent.getBroadcast(context, (component == null || (className = component.getClassName()) == null) ? NotificationsService.class.hashCode() : className.hashCode(), intent, i | C.BUFFER_FLAG_FIRST_SAMPLE);
            Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
            return broadcast;
        }

        /* JADX WARNING: type inference failed for: r8v4, types: [android.os.Parcelable] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final android.content.Intent createNotificationResponseBroadcastIntent(android.content.Context r7, android.os.Bundle r8) {
            /*
                r6 = this;
                java.lang.String r0 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.lang.String r0 = "notification"
                r1 = 0
                if (r8 == 0) goto L_0x0011
                android.os.Parcelable r2 = r8.getParcelable(r0)
                expo.modules.notifications.notifications.model.Notification r2 = (expo.modules.notifications.notifications.model.Notification) r2
                goto L_0x0012
            L_0x0011:
                r2 = r1
            L_0x0012:
                java.lang.String r3 = "notificationAction"
                if (r8 == 0) goto L_0x001d
                android.os.Parcelable r8 = r8.getParcelable(r3)
                r1 = r8
                expo.modules.notifications.notifications.model.NotificationAction r1 = (expo.modules.notifications.notifications.model.NotificationAction) r1
            L_0x001d:
                if (r2 == 0) goto L_0x007e
                if (r1 == 0) goto L_0x007e
                expo.modules.notifications.notifications.model.NotificationAction r8 = new expo.modules.notifications.notifications.model.NotificationAction
                java.lang.String r4 = r1.getIdentifier()
                java.lang.String r1 = r1.getTitle()
                r5 = 0
                r8.<init>(r4, r1, r5)
                android.content.Intent r1 = new android.content.Intent
                android.net.Uri$Builder r4 = r6.getUriBuilder()
                expo.modules.notifications.notifications.model.NotificationRequest r5 = r2.getNotificationRequest()
                java.lang.String r5 = r5.getIdentifier()
                android.net.Uri$Builder r4 = r4.appendPath(r5)
                java.lang.String r5 = "actions"
                android.net.Uri$Builder r4 = r4.appendPath(r5)
                java.lang.String r5 = r8.getIdentifier()
                android.net.Uri$Builder r4 = r4.appendPath(r5)
                android.net.Uri r4 = r4.build()
                java.lang.String r5 = "expo.modules.notifications.NOTIFICATION_EVENT"
                r1.<init>(r5, r4)
                expo.modules.notifications.service.NotificationsService$Companion r4 = expo.modules.notifications.service.NotificationsService.Companion
                android.content.pm.ActivityInfo r7 = r4.findDesignatedBroadcastReceiver(r7, r1)
                if (r7 == 0) goto L_0x006c
                android.content.ComponentName r4 = new android.content.ComponentName
                java.lang.String r5 = r7.packageName
                java.lang.String r7 = r7.name
                r4.<init>(r5, r7)
                r1.setComponent(r4)
            L_0x006c:
                java.lang.String r7 = "type"
                java.lang.String r4 = "receiveResponse"
                r1.putExtra(r7, r4)
                android.os.Parcelable r2 = (android.os.Parcelable) r2
                r1.putExtra(r0, r2)
                android.os.Parcelable r8 = (android.os.Parcelable) r8
                r1.putExtra(r3, r8)
                return r1
            L_0x007e:
                java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
                java.lang.String r8 = "notification and action should not be null"
                r7.<init>(r8)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.service.NotificationsService.Companion.createNotificationResponseBroadcastIntent(android.content.Context, android.os.Bundle):android.content.Intent");
        }

        public final NotificationResponse getNotificationResponseFromBroadcastIntent(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            Notification notification = (Notification) intent.getParcelableExtra(NotificationsService.NOTIFICATION_KEY);
            if (notification != null) {
                NotificationAction notificationAction = (NotificationAction) intent.getParcelableExtra(NotificationsService.NOTIFICATION_ACTION_KEY);
                if (notificationAction == null) {
                    throw new IllegalArgumentException("notificationAction not found in the intent extras.");
                } else if (!(notificationAction instanceof TextInputNotificationAction)) {
                    return new NotificationResponse(notificationAction, notification);
                } else {
                    String placeholder = ((TextInputNotificationAction) notificationAction).getPlaceholder();
                    if (placeholder == null) {
                        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
                        placeholder = resultsFromIntent != null ? resultsFromIntent.getString(NotificationsService.USER_TEXT_RESPONSE_KEY) : null;
                        if (placeholder == null) {
                            placeholder = "";
                        }
                    }
                    return new TextInputNotificationResponse(notificationAction, notification, placeholder);
                }
            } else {
                throw new IllegalArgumentException("notification not found in the intent extras.");
            }
        }

        public final NotificationResponse getNotificationResponseFromOpenIntent(Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            byte[] byteArrayExtra = intent.getByteArrayExtra(NotificationsService.NOTIFICATION_RESPONSE_KEY);
            if (byteArrayExtra != null) {
                Companion companion = NotificationsService.Companion;
                Parcelable.Creator creator = NotificationResponse.CREATOR;
                Intrinsics.checkNotNullExpressionValue(creator, "CREATOR");
                return (NotificationResponse) companion.unmarshalObject(creator, byteArrayExtra);
            }
            byte[] byteArrayExtra2 = intent.getByteArrayExtra(NotificationsService.TEXT_INPUT_NOTIFICATION_RESPONSE_KEY);
            if (byteArrayExtra2 == null) {
                return null;
            }
            Companion companion2 = NotificationsService.Companion;
            Parcelable.Creator creator2 = TextInputNotificationResponse.CREATOR;
            Intrinsics.checkNotNullExpressionValue(creator2, "CREATOR");
            return (NotificationResponse) companion2.unmarshalObject(creator2, byteArrayExtra2);
        }

        public final void setNotificationResponseToIntent(Intent intent, NotificationResponse notificationResponse) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            String str = NotificationsService.NOTIFICATION_RESPONSE_KEY;
            Intrinsics.checkNotNullParameter(notificationResponse, str);
            try {
                if (notificationResponse instanceof TextInputNotificationResponse) {
                    str = NotificationsService.TEXT_INPUT_NOTIFICATION_RESPONSE_KEY;
                }
                intent.putExtra(str, marshalObject(notificationResponse));
            } catch (Exception e) {
                SentryLogcatAdapter.e("expo-notifications", "Could not marshal notification response: " + notificationResponse.getActionIdentifier() + ".");
                e.printStackTrace();
            }
        }

        private final byte[] marshalObject(Parcelable parcelable) {
            Parcel obtain = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(obtain, "obtain(...)");
            parcelable.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            Intrinsics.checkNotNullExpressionValue(marshall, "marshall(...)");
            obtain.recycle();
            return marshall;
        }

        private final <T> T unmarshalObject(Parcelable.Creator<T> creator, byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            try {
                Parcel obtain = Parcel.obtain();
                Intrinsics.checkNotNullExpressionValue(obtain, "obtain(...)");
                obtain.unmarshall(bArr, 0, bArr.length);
                obtain.setDataPosition(0);
                T createFromParcel = creator.createFromParcel(obtain);
                obtain.recycle();
                return createFromParcel;
            } catch (Exception e) {
                SentryLogcatAdapter.e("expo-notifications", "Could not unmarshall NotificationResponse from Intent.extra.", e);
                return null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public PresentationDelegate getPresentationDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoPresentationDelegate(context);
    }

    /* access modifiers changed from: protected */
    public HandlingDelegate getHandlingDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoHandlingDelegate(context);
    }

    /* access modifiers changed from: protected */
    public CategoriesDelegate getCategoriesDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoCategoriesDelegate(context);
    }

    /* access modifiers changed from: protected */
    public SchedulingDelegate getSchedulingDelegate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new ExpoSchedulingDelegate(context);
    }

    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new NotificationsService$onReceive$1(this, context, intent, goAsync()), 31, (Object) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: android.os.Bundle} */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v30 */
    /* JADX WARNING: type inference failed for: r1v31 */
    /* JADX WARNING: type inference failed for: r1v32 */
    /* JADX WARNING: type inference failed for: r1v33 */
    /* JADX WARNING: type inference failed for: r1v34 */
    /* JADX WARNING: type inference failed for: r1v35 */
    /* JADX WARNING: type inference failed for: r1v36 */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0127, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0129, code lost:
        r2.send(0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        return;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleIntent(android.content.Context r7, android.content.Intent r8) {
        /*
            r6 = this;
            java.lang.String r0 = "Received event of unrecognized type: "
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
            java.lang.String r1 = "dupa"
            java.lang.String r2 = java.lang.String.valueOf(r8)
            io.sentry.android.core.SentryLogcatAdapter.e(r1, r2)
            if (r8 == 0) goto L_0x0025
            java.util.List<java.lang.String> r1 = SETUP_ACTIONS
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.lang.String r2 = r8.getAction()
            boolean r1 = kotlin.collections.CollectionsKt.contains(r1, r2)
            if (r1 == 0) goto L_0x0025
            r6.onSetupScheduledNotifications(r7, r8)
            goto L_0x0183
        L_0x0025:
            r1 = 0
            if (r8 == 0) goto L_0x002d
            java.lang.String r2 = r8.getAction()
            goto L_0x002e
        L_0x002d:
            r2 = r1
        L_0x002e:
            java.lang.String r3 = "expo.modules.notifications.NOTIFICATION_EVENT"
            java.lang.String r4 = ". Ignoring."
            if (r2 != r3) goto L_0x0184
            android.os.Bundle r2 = r8.getExtras()
            if (r2 == 0) goto L_0x0041
            java.lang.String r3 = "receiver"
            java.lang.Object r2 = r2.get(r3)
            goto L_0x0042
        L_0x0041:
            r2 = r1
        L_0x0042:
            boolean r3 = r2 instanceof android.os.ResultReceiver
            if (r3 == 0) goto L_0x0049
            android.os.ResultReceiver r2 = (android.os.ResultReceiver) r2
            goto L_0x004a
        L_0x0049:
            r2 = r1
        L_0x004a:
            java.lang.String r3 = "type"
            java.lang.String r3 = r8.getStringExtra(r3)     // Catch:{ Exception -> 0x0145 }
            if (r3 == 0) goto L_0x012e
            int r5 = r3.hashCode()     // Catch:{ Exception -> 0x0145 }
            switch(r5) {
                case -2144315324: goto L_0x011c;
                case -2002465847: goto L_0x010f;
                case -1734918526: goto L_0x0102;
                case -1326613834: goto L_0x00f5;
                case -1059891784: goto L_0x00e9;
                case -697920873: goto L_0x00dd;
                case -577380539: goto L_0x00d1;
                case -402879681: goto L_0x00c5;
                case -318277445: goto L_0x00b9;
                case -170315273: goto L_0x00ab;
                case 648465079: goto L_0x009e;
                case 998768146: goto L_0x0090;
                case 1082290915: goto L_0x0083;
                case 1282345597: goto L_0x0076;
                case 1925736384: goto L_0x0069;
                case 2039831424: goto L_0x005b;
                default: goto L_0x0059;
            }     // Catch:{ Exception -> 0x0145 }
        L_0x0059:
            goto L_0x012e
        L_0x005b:
            java.lang.String r1 = "setCategory"
            boolean r1 = r3.equals(r1)     // Catch:{ Exception -> 0x0145 }
            if (r1 == 0) goto L_0x012e
            android.os.Bundle r1 = r6.onSetCategory(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x0069:
            java.lang.String r5 = "dropped"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0145 }
            if (r5 == 0) goto L_0x012e
            r6.onNotificationsDropped(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x0076:
            java.lang.String r5 = "removeAll"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0145 }
            if (r5 == 0) goto L_0x012e
            r6.onRemoveAllScheduledNotifications(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x0083:
            java.lang.String r5 = "receive"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0145 }
            if (r5 == 0) goto L_0x012e
            r6.onReceiveNotification(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x0090:
            java.lang.String r1 = "getCategories"
            boolean r1 = r3.equals(r1)     // Catch:{ Exception -> 0x0145 }
            if (r1 == 0) goto L_0x012e
            android.os.Bundle r1 = r6.onGetCategories(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x009e:
            java.lang.String r5 = "dismissAll"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0145 }
            if (r5 == 0) goto L_0x012e
            r6.onDismissAllNotifications(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x00ab:
            java.lang.String r1 = "getScheduled"
            boolean r1 = r3.equals(r1)     // Catch:{ Exception -> 0x0145 }
            if (r1 == 0) goto L_0x012e
            android.os.Bundle r1 = r6.onGetScheduledNotification(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x00b9:
            java.lang.String r5 = "present"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0145 }
            if (r5 == 0) goto L_0x012e
            r6.onPresentNotification(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x00c5:
            java.lang.String r5 = "removeSelected"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0145 }
            if (r5 == 0) goto L_0x012e
            r6.onRemoveScheduledNotifications(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x00d1:
            java.lang.String r5 = "dismissSelected"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0145 }
            if (r5 == 0) goto L_0x012e
            r6.onDismissNotifications(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x00dd:
            java.lang.String r5 = "schedule"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0145 }
            if (r5 == 0) goto L_0x012e
            r6.onScheduleNotification(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x00e9:
            java.lang.String r5 = "trigger"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0145 }
            if (r5 == 0) goto L_0x012e
            r6.onNotificationTriggered(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x00f5:
            java.lang.String r1 = "getAllDisplayed"
            boolean r1 = r3.equals(r1)     // Catch:{ Exception -> 0x0145 }
            if (r1 == 0) goto L_0x012e
            android.os.Bundle r1 = r6.onGetAllPresentedNotifications(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x0102:
            java.lang.String r1 = "getAllScheduled"
            boolean r1 = r3.equals(r1)     // Catch:{ Exception -> 0x0145 }
            if (r1 == 0) goto L_0x012e
            android.os.Bundle r1 = r6.onGetAllScheduledNotifications(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x010f:
            java.lang.String r1 = "deleteCategory"
            boolean r1 = r3.equals(r1)     // Catch:{ Exception -> 0x0145 }
            if (r1 == 0) goto L_0x012e
            android.os.Bundle r1 = r6.onDeleteCategory(r7, r8)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0127
        L_0x011c:
            java.lang.String r5 = "receiveResponse"
            boolean r5 = r3.equals(r5)     // Catch:{ Exception -> 0x0145 }
            if (r5 == 0) goto L_0x012e
            r6.onReceiveNotificationResponse(r7, r8)     // Catch:{ Exception -> 0x0145 }
        L_0x0127:
            if (r2 == 0) goto L_0x0183
            r7 = 0
            r2.send(r7, r1)     // Catch:{ Exception -> 0x0145 }
            goto L_0x0183
        L_0x012e:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException     // Catch:{ Exception -> 0x0145 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0145 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0145 }
            java.lang.StringBuilder r0 = r1.append(r3)     // Catch:{ Exception -> 0x0145 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ Exception -> 0x0145 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0145 }
            r7.<init>(r0)     // Catch:{ Exception -> 0x0145 }
            throw r7     // Catch:{ Exception -> 0x0145 }
        L_0x0145:
            r7 = move-exception
            java.lang.String r8 = r8.getAction()
            java.lang.String r0 = r7.getMessage()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Action "
            r1.<init>(r3)
            java.lang.StringBuilder r8 = r1.append(r8)
            java.lang.String r1 = " failed: "
            java.lang.StringBuilder r8 = r8.append(r1)
            java.lang.StringBuilder r8 = r8.append(r0)
            java.lang.String r8 = r8.toString()
            java.lang.String r0 = "expo-notifications"
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r8)
            r7.printStackTrace()
            if (r2 == 0) goto L_0x0183
            android.os.Bundle r8 = new android.os.Bundle
            r8.<init>()
            java.lang.String r0 = "exception"
            java.io.Serializable r7 = (java.io.Serializable) r7
            r8.putSerializable(r0, r7)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            r7 = 1
            r2.send(r7, r8)
        L_0x0183:
            return
        L_0x0184:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            if (r8 == 0) goto L_0x018c
            java.lang.String r1 = r8.getAction()
        L_0x018c:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Received intent of unrecognized action: "
            r8.<init>(r0)
            java.lang.StringBuilder r8 = r8.append(r1)
            java.lang.StringBuilder r8 = r8.append(r4)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.service.NotificationsService.handleIntent(android.content.Context, android.content.Intent):void");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPresentNotification(android.content.Context r4, android.content.Intent r5) {
        /*
            r3 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "intent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            expo.modules.notifications.service.interfaces.PresentationDelegate r4 = r3.getPresentationDelegate(r4)
            android.os.Bundle r0 = r5.getExtras()
            r1 = 0
            if (r0 == 0) goto L_0x001e
            java.lang.String r2 = "notification"
            android.os.Parcelable r0 = r0.getParcelable(r2)
            expo.modules.notifications.notifications.model.Notification r0 = (expo.modules.notifications.notifications.model.Notification) r0
            goto L_0x001f
        L_0x001e:
            r0 = r1
        L_0x001f:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            android.os.Bundle r5 = r5.getExtras()
            if (r5 == 0) goto L_0x0031
            java.lang.String r1 = "notificationBehavior"
            android.os.Parcelable r5 = r5.getParcelable(r1)
            r1 = r5
            expo.modules.notifications.notifications.model.NotificationBehavior r1 = (expo.modules.notifications.notifications.model.NotificationBehavior) r1
        L_0x0031:
            r4.presentNotification(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.notifications.service.NotificationsService.onPresentNotification(android.content.Context, android.content.Intent):void");
    }

    public Bundle onGetAllPresentedNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NOTIFICATIONS_KEY, new ArrayList(getPresentationDelegate(context).getAllPresentedNotifications()));
        return bundle;
    }

    public void onDismissNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        PresentationDelegate presentationDelegate = getPresentationDelegate(context);
        Bundle extras = intent.getExtras();
        String[] stringArray = extras != null ? extras.getStringArray(IDENTIFIERS_KEY) : null;
        Intrinsics.checkNotNull(stringArray);
        presentationDelegate.dismissNotifications(ArraysKt.asList((T[]) stringArray));
    }

    public void onDismissAllNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getPresentationDelegate(context).dismissAllNotifications();
    }

    public void onReceiveNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        HandlingDelegate handlingDelegate = getHandlingDelegate(context);
        Parcelable parcelableExtra = intent.getParcelableExtra(NOTIFICATION_KEY);
        Intrinsics.checkNotNull(parcelableExtra);
        handlingDelegate.handleNotification((Notification) parcelableExtra);
    }

    public void onReceiveNotificationResponse(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getHandlingDelegate(context).handleNotificationResponse(Companion.getNotificationResponseFromBroadcastIntent(intent));
    }

    public void onNotificationsDropped(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getHandlingDelegate(context).handleNotificationsDropped();
    }

    public Bundle onGetCategories(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NOTIFICATION_CATEGORIES_KEY, new ArrayList(getCategoriesDelegate(context).getCategories()));
        return bundle;
    }

    public Bundle onSetCategory(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        CategoriesDelegate categoriesDelegate = getCategoriesDelegate(context);
        Parcelable parcelableExtra = intent.getParcelableExtra(NOTIFICATION_CATEGORY_KEY);
        Intrinsics.checkNotNull(parcelableExtra);
        bundle.putParcelable(NOTIFICATION_CATEGORY_KEY, categoriesDelegate.setCategory((NotificationCategory) parcelableExtra));
        return bundle;
    }

    public Bundle onDeleteCategory(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        CategoriesDelegate categoriesDelegate = getCategoriesDelegate(context);
        Bundle extras = intent.getExtras();
        String string = extras != null ? extras.getString("identifier") : null;
        Intrinsics.checkNotNull(string);
        bundle.putBoolean(SUCCEEDED_KEY, categoriesDelegate.deleteCategory(string));
        return bundle;
    }

    public Bundle onGetAllScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(NOTIFICATION_REQUESTS_KEY, new ArrayList(getSchedulingDelegate(context).getAllScheduledNotifications()));
        return bundle;
    }

    public Bundle onGetScheduledNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        Bundle bundle = new Bundle();
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        String string = extras != null ? extras.getString("identifier") : null;
        Intrinsics.checkNotNull(string);
        bundle.putParcelable(NOTIFICATION_REQUEST_KEY, schedulingDelegate.getScheduledNotification(string));
        return bundle;
    }

    public void onScheduleNotification(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        NotificationRequest notificationRequest = extras != null ? (NotificationRequest) extras.getParcelable(NOTIFICATION_REQUEST_KEY) : null;
        Intrinsics.checkNotNull(notificationRequest);
        schedulingDelegate.scheduleNotification(notificationRequest);
    }

    public void onNotificationTriggered(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        String string = extras != null ? extras.getString("identifier") : null;
        Intrinsics.checkNotNull(string);
        schedulingDelegate.triggerNotification(string);
    }

    public void onRemoveScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        SchedulingDelegate schedulingDelegate = getSchedulingDelegate(context);
        Bundle extras = intent.getExtras();
        String[] stringArray = extras != null ? extras.getStringArray(IDENTIFIERS_KEY) : null;
        Intrinsics.checkNotNull(stringArray);
        schedulingDelegate.removeScheduledNotifications(ArraysKt.asList((T[]) stringArray));
    }

    public void onRemoveAllScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getSchedulingDelegate(context).removeAllScheduledNotifications();
    }

    public void onSetupScheduledNotifications(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        getSchedulingDelegate(context).setupScheduledNotifications();
    }
}
