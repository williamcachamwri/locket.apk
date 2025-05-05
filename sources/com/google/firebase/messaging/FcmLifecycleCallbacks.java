package com.google.firebase.messaging;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.firebase.messaging.Constants;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayDeque;
import java.util.Queue;

class FcmLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private static final int RECENTLY_LOGGED_MESSAGE_IDS_MAX_SIZE = 10;
    private final Queue<String> recentlyLoggedMessageIds = new ArrayDeque(10);

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    FcmLifecycleCallbacks() {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        Intent intent = activity.getIntent();
        if (intent != null) {
            m783lambda$onActivityCreated$0$comgooglefirebasemessagingFcmLifecycleCallbacks(intent);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: logNotificationOpen */
    public void m783lambda$onActivityCreated$0$comgooglefirebasemessagingFcmLifecycleCallbacks(Intent intent) {
        Bundle bundle = null;
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String messageId = MessagingAnalytics.getMessageId(extras);
                if (!TextUtils.isEmpty(messageId)) {
                    if (!this.recentlyLoggedMessageIds.contains(messageId)) {
                        this.recentlyLoggedMessageIds.add(messageId);
                    } else {
                        return;
                    }
                }
                bundle = extras.getBundle(Constants.MessageNotificationKeys.ANALYTICS_DATA);
            }
        } catch (RuntimeException e) {
            SentryLogcatAdapter.w(Constants.TAG, "Failed trying to get analytics data from Intent extras.", e);
        }
        if (MessagingAnalytics.shouldUploadScionMetrics(bundle)) {
            MessagingAnalytics.logNotificationOpen(bundle);
        }
    }
}
