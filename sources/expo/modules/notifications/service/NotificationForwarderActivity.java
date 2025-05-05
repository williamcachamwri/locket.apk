package expo.modules.notifications.service;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import expo.modules.notifications.service.NotificationsService;
import expo.modules.notifications.service.delegates.ExpoHandlingDelegate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014¨\u0006\n"}, d2 = {"Lexpo/modules/notifications/service/NotificationForwarderActivity;", "Landroid/app/Activity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NotificationForwarderActivity.kt */
public final class NotificationForwarderActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        NotificationsService.Companion companion = NotificationsService.Companion;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        Intent createNotificationResponseBroadcastIntent = companion.createNotificationResponseBroadcastIntent(applicationContext, getIntent().getExtras());
        ExpoHandlingDelegate.Companion.openAppToForeground(this, NotificationsService.Companion.getNotificationResponseFromBroadcastIntent(createNotificationResponseBroadcastIntent));
        sendBroadcast(createNotificationResponseBroadcastIntent);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
