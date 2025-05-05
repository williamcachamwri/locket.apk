package expo.modules.notifications.notifications.handling;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.media3.common.C;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.notifications.notifications.NotificationSerializer;
import expo.modules.notifications.notifications.model.Notification;
import expo.modules.notifications.notifications.model.NotificationBehavior;
import expo.modules.notifications.service.NotificationsService;

public class SingleNotificationHandlerTask {
    private static final String HANDLE_NOTIFICATION_EVENT_NAME = "onHandleNotification";
    private static final String HANDLE_NOTIFICATION_TIMEOUT_EVENT_NAME = "onHandleNotificationTimeout";
    private static final int SECONDS_TO_TIMEOUT = 3;
    /* access modifiers changed from: private */
    public NotificationBehavior mBehavior;
    /* access modifiers changed from: private */
    public Context mContext;
    private NotificationsHandler mDelegate;
    private EventEmitter mEventEmitter;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public Notification mNotification;
    private Runnable mTimeoutRunnable = new SingleNotificationHandlerTask$$ExternalSyntheticLambda0(this);

    SingleNotificationHandlerTask(Context context, EventEmitter eventEmitter, Handler handler, Notification notification, NotificationsHandler notificationsHandler) {
        this.mContext = context;
        this.mHandler = handler;
        this.mEventEmitter = eventEmitter;
        this.mNotification = notification;
        this.mDelegate = notificationsHandler;
    }

    /* access modifiers changed from: package-private */
    public String getIdentifier() {
        return this.mNotification.getNotificationRequest().getIdentifier();
    }

    /* access modifiers changed from: package-private */
    public void start() {
        Bundle bundle = new Bundle();
        bundle.putString("id", getIdentifier());
        bundle.putBundle(NotificationsService.NOTIFICATION_KEY, NotificationSerializer.toBundle(this.mNotification));
        this.mEventEmitter.emit(HANDLE_NOTIFICATION_EVENT_NAME, bundle);
        this.mHandler.postDelayed(this.mTimeoutRunnable, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        finish();
    }

    /* access modifiers changed from: package-private */
    public void handleResponse(NotificationBehavior notificationBehavior, final Promise promise) {
        this.mBehavior = notificationBehavior;
        this.mHandler.post(new Runnable() {
            public void run() {
                NotificationsService.Companion.present(SingleNotificationHandlerTask.this.mContext, SingleNotificationHandlerTask.this.mNotification, SingleNotificationHandlerTask.this.mBehavior, new ResultReceiver(SingleNotificationHandlerTask.this.mHandler) {
                    /* access modifiers changed from: protected */
                    public void onReceiveResult(int i, Bundle bundle) {
                        super.onReceiveResult(i, bundle);
                        if (i == 0) {
                            promise.resolve((Object) null);
                            return;
                        }
                        promise.reject("ERR_NOTIFICATION_PRESENTATION_FAILED", "Notification presentation failed.", (Exception) bundle.getSerializable("exception"));
                    }
                });
                SingleNotificationHandlerTask.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleTimeout() {
        Bundle bundle = new Bundle();
        bundle.putString("id", getIdentifier());
        bundle.putBundle(NotificationsService.NOTIFICATION_KEY, NotificationSerializer.toBundle(this.mNotification));
        this.mEventEmitter.emit(HANDLE_NOTIFICATION_TIMEOUT_EVENT_NAME, bundle);
        finish();
    }

    /* access modifiers changed from: private */
    public void finish() {
        this.mHandler.removeCallbacks(this.mTimeoutRunnable);
        this.mDelegate.onTaskFinished(this);
    }
}
