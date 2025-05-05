package com.locket.Locket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.d("Locket", "Begin: AlarmReceiver.onReceive");
        new AppWidgetAlarm(context).restartAlarm();
        Log.d("Locket", "End: AlarmReceiver.onReceive");
    }
}
