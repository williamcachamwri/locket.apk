package com.bumptech.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.RemoteViews;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Preconditions;
import expo.modules.notifications.service.NotificationsService;

public class NotificationTarget extends CustomTarget<Bitmap> {
    private final Context context;
    private final Notification notification;
    private final int notificationId;
    private final String notificationTag;
    private final RemoteViews remoteViews;
    private final int viewId;

    public NotificationTarget(Context context2, int i, RemoteViews remoteViews2, Notification notification2, int i2) {
        this(context2, i, remoteViews2, notification2, i2, (String) null);
    }

    public NotificationTarget(Context context2, int i, RemoteViews remoteViews2, Notification notification2, int i2, String str) {
        this(context2, Integer.MIN_VALUE, Integer.MIN_VALUE, i, remoteViews2, notification2, i2, str);
    }

    public NotificationTarget(Context context2, int i, int i2, int i3, RemoteViews remoteViews2, Notification notification2, int i4, String str) {
        super(i, i2);
        this.context = (Context) Preconditions.checkNotNull(context2, "Context must not be null!");
        this.notification = (Notification) Preconditions.checkNotNull(notification2, "Notification object can not be null!");
        this.remoteViews = (RemoteViews) Preconditions.checkNotNull(remoteViews2, "RemoteViews object can not be null!");
        this.viewId = i3;
        this.notificationId = i4;
        this.notificationTag = str;
    }

    private void update() {
        ((NotificationManager) Preconditions.checkNotNull((NotificationManager) this.context.getSystemService(NotificationsService.NOTIFICATION_KEY))).notify(this.notificationTag, this.notificationId, this.notification);
    }

    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
        setBitmap(bitmap);
    }

    public void onLoadCleared(Drawable drawable) {
        setBitmap((Bitmap) null);
    }

    private void setBitmap(Bitmap bitmap) {
        this.remoteViews.setImageViewBitmap(this.viewId, bitmap);
        update();
    }
}
