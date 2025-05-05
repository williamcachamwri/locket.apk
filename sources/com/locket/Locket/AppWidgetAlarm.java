package com.locket.Locket;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.Calendar;

public class AppWidgetAlarm {
    private final int ALARM_ID = 0;
    private final int INTERVAL_MILLIS = 480000;
    private Context mContext;

    public AppWidgetAlarm(Context context) {
        this.mContext = context;
    }

    public void startAlarm() {
        Log.d("Locket", "Begin: Start Alarm");
        Calendar instance = Calendar.getInstance();
        instance.add(14, 480000);
        Intent intent = new Intent(this.mContext, Widget.class);
        intent.setAction(Widget.ACTION_ALARM_UPDATE);
        ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).setRepeating(1, instance.getTimeInMillis(), 480000, PendingIntent.getBroadcast(this.mContext, 0, intent, 335544320));
    }

    public void stopAlarm() {
        Log.d("Locket", "Begin: Stop Alarm");
        Intent intent = new Intent(this.mContext, Widget.class);
        intent.setAction(Widget.ACTION_ALARM_UPDATE);
        ((AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(this.mContext, 0, intent, 335544320));
    }

    public void restartAlarm() {
        Log.d("Locket", "Begin: Restart Alarm");
        try {
            stopAlarm();
        } catch (Exception e) {
            e.printStackTrace();
            SentryLogcatAdapter.e("Locket", "Failed to stop alarm, possibly was not running");
        }
        try {
            startAlarm();
        } catch (Exception e2) {
            e2.printStackTrace();
            SentryLogcatAdapter.e("Locket", "Failed to start alarm, possibly already running");
        }
    }
}
