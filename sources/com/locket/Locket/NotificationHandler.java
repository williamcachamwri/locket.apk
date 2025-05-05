package com.locket.Locket;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.webkit.Profile;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import expo.modules.notifications.service.NotificationsService;

public class NotificationHandler extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("Locket", "Begin: NotificationHandler.onMessageReceived");
        Log.d("remoteMessage", String.valueOf(remoteMessage));
        if (!String.valueOf(remoteMessage).contains("firebase.messaging.RemoteMessage") || remoteMessage.getNotification() == null) {
            Intent intent = new Intent(getApplicationContext(), Widget.class);
            intent.setAction(Widget.ACTION_NOTIFICATION_UPDATE);
            sendBroadcast(intent);
        } else {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationsService.NOTIFICATION_KEY);
            if (notificationManager.areNotificationsEnabled() && (remoteMessage.getData().get("conversationId") == null || !NotificationStateManager.getInstance().getSuppressedConversationId().equals(remoteMessage.getData().get("conversationId")))) {
                Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
                if (launchIntentForPackage == null) {
                    launchIntentForPackage = new Intent(this, MainActivity.class);
                }
                launchIntentForPackage.putExtra("moment", remoteMessage.getData().get("moment"));
                launchIntentForPackage.putExtra("conversationId", remoteMessage.getData().get("conversationId"));
                launchIntentForPackage.addFlags(67108864);
                NotificationCompat.Builder contentIntent = new NotificationCompat.Builder((Context) this, Profile.DEFAULT_PROFILE_NAME).setSmallIcon(R.drawable.ic_stat_locket_white).setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_stat_locket_white)).setColor(getResources().getColor(R.color.colorPrimary, getTheme())).setContentTitle(remoteMessage.getNotification().getTitle()).setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this, 0, launchIntentForPackage, 1107296256));
                notificationManager.createNotificationChannel(new NotificationChannel(Profile.DEFAULT_PROFILE_NAME, "Default channel", 3));
                notificationManager.notify(0, contentIntent.build());
            }
        }
        Log.d("Locket", "End: NotificationHandler.onMessageReceived");
    }
}
