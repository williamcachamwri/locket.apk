package androidx.media3.session;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.widget.RemoteViews;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;

public class MediaStyleNotificationHelper {
    public static final String EXTRA_MEDIA3_SESSION = "androidx.media3.session";

    private MediaStyleNotificationHelper() {
    }

    public static class MediaStyle extends NotificationCompat.Style {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] actionsToShowInCompact;
        int remoteDeviceIconRes;
        PendingIntent remoteDeviceIntent;
        CharSequence remoteDeviceName;
        final MediaSession session;

        @Deprecated
        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            return this;
        }

        @Deprecated
        public MediaStyle setShowCancelButton(boolean z) {
            return this;
        }

        public static SessionToken getSessionToken(Notification notification) {
            Bundle bundle;
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras == null || (bundle = extras.getBundle(MediaStyleNotificationHelper.EXTRA_MEDIA3_SESSION)) == null) {
                return null;
            }
            return SessionToken.fromBundle(bundle);
        }

        public MediaStyle(MediaSession mediaSession) {
            this.session = mediaSession;
        }

        public MediaStyle setShowActionsInCompactView(int... iArr) {
            this.actionsToShowInCompact = iArr;
            return this;
        }

        public MediaStyle setRemotePlaybackInfo(CharSequence charSequence, int i, PendingIntent pendingIntent) {
            Assertions.checkArgument(charSequence != null);
            this.remoteDeviceName = charSequence;
            this.remoteDeviceIconRes = i;
            this.remoteDeviceIntent = pendingIntent;
            return this;
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            CharSequence charSequence;
            Notification.MediaStyle mediaSession = new Notification.MediaStyle().setMediaSession((MediaSession.Token) this.session.getSessionCompat().getSessionToken().getToken());
            int[] iArr = this.actionsToShowInCompact;
            if (iArr != null) {
                mediaSession.setShowActionsInCompactView(iArr);
            }
            if (Util.SDK_INT < 34 || (charSequence = this.remoteDeviceName) == null) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(mediaSession);
                Bundle bundle = new Bundle();
                bundle.putBundle(MediaStyleNotificationHelper.EXTRA_MEDIA3_SESSION, this.session.getToken().toBundle());
                notificationBuilderWithBuilderAccessor.getBuilder().addExtras(bundle);
                return;
            }
            Api34Impl.setRemotePlaybackInfo(mediaSession, charSequence, this.remoteDeviceIconRes, this.remoteDeviceIntent);
            notificationBuilderWithBuilderAccessor.getBuilder().setStyle(mediaSession);
        }

        /* access modifiers changed from: package-private */
        public RemoteViews generateContentView() {
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int size = this.mBuilder.mActions.size();
            int[] iArr = this.actionsToShowInCompact;
            if (iArr != null) {
                int min = Math.min(iArr.length, 3);
                applyStandardTemplate.removeAllViews(R.id.media_actions);
                if (min > 0) {
                    int i = 0;
                    while (i < min) {
                        if (i < size) {
                            applyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(this.mBuilder.mActions.get(iArr[i])));
                            i++;
                        } else {
                            throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", new Object[]{Integer.valueOf(i), Integer.valueOf(size - 1)}));
                        }
                    }
                }
            }
            applyStandardTemplate.setViewVisibility(R.id.end_padder, 0);
            return applyStandardTemplate;
        }

        private RemoteViews generateMediaActionButton(NotificationCompat.Action action) {
            boolean z = action.getActionIntent() == null;
            RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), R.layout.media3_notification_media_action);
            IconCompat iconCompat = action.getIconCompat();
            if (iconCompat != null) {
                remoteViews.setImageViewResource(R.id.action0, iconCompat.getResId());
            }
            if (!z) {
                remoteViews.setOnClickPendingIntent(R.id.action0, action.getActionIntent());
            }
            remoteViews.setContentDescription(R.id.action0, action.getTitle());
            return remoteViews;
        }

        /* access modifiers changed from: package-private */
        public int getContentViewLayoutResource() {
            return R.layout.media3_notification_template_media;
        }

        /* access modifiers changed from: package-private */
        public RemoteViews generateBigContentView() {
            int min = Math.min(this.mBuilder.mActions.size(), 5);
            RemoteViews applyStandardTemplate = applyStandardTemplate(false, getBigContentViewLayoutResource(min), false);
            applyStandardTemplate.removeAllViews(R.id.media_actions);
            if (min > 0) {
                for (int i = 0; i < min; i++) {
                    applyStandardTemplate.addView(R.id.media_actions, generateMediaActionButton(this.mBuilder.mActions.get(i)));
                }
            }
            return applyStandardTemplate;
        }

        /* access modifiers changed from: package-private */
        public int getBigContentViewLayoutResource(int i) {
            if (i <= 3) {
                return R.layout.media3_notification_template_big_media_narrow;
            }
            return R.layout.media3_notification_template_big_media;
        }
    }

    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        public DecoratedMediaCustomViewStyle(MediaSession mediaSession) {
            super(mediaSession);
        }

        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Util.SDK_INT < 24) {
                super.apply(notificationBuilderWithBuilderAccessor);
                return;
            }
            Notification.DecoratedMediaCustomViewStyle createDecoratedMediaCustomViewStyle = Api24Impl.createDecoratedMediaCustomViewStyle();
            if (this.actionsToShowInCompact != null) {
                createDecoratedMediaCustomViewStyle.setShowActionsInCompactView(this.actionsToShowInCompact);
            }
            if (Util.SDK_INT < 34 || this.remoteDeviceName == null) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle(createDecoratedMediaCustomViewStyle);
                Bundle bundle = new Bundle();
                bundle.putBundle(MediaStyleNotificationHelper.EXTRA_MEDIA3_SESSION, this.session.getToken().toBundle());
                notificationBuilderWithBuilderAccessor.getBuilder().addExtras(bundle);
                return;
            }
            Api34Impl.setRemotePlaybackInfo(createDecoratedMediaCustomViewStyle, this.remoteDeviceName, this.remoteDeviceIconRes, this.remoteDeviceIntent);
            notificationBuilderWithBuilderAccessor.getBuilder().setStyle(createDecoratedMediaCustomViewStyle);
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Util.SDK_INT >= 24) {
                return null;
            }
            boolean z = true;
            boolean z2 = this.mBuilder.getContentView() != null;
            if (!z2 && this.mBuilder.getBigContentView() == null) {
                z = false;
            }
            if (!z) {
                return null;
            }
            RemoteViews generateContentView = generateContentView();
            if (z2) {
                buildIntoRemoteViews(generateContentView, this.mBuilder.getContentView());
            }
            setBackgroundColor(generateContentView);
            return generateContentView;
        }

        /* access modifiers changed from: package-private */
        public int getContentViewLayoutResource() {
            if (this.mBuilder.getContentView() != null) {
                return R.layout.media3_notification_template_media_custom;
            }
            return super.getContentViewLayoutResource();
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViews;
            if (Util.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getBigContentView() != null) {
                remoteViews = this.mBuilder.getBigContentView();
            } else {
                remoteViews = this.mBuilder.getContentView();
            }
            if (remoteViews == null) {
                return null;
            }
            RemoteViews generateBigContentView = generateBigContentView();
            buildIntoRemoteViews(generateBigContentView, remoteViews);
            setBackgroundColor(generateBigContentView);
            return generateBigContentView;
        }

        /* access modifiers changed from: package-private */
        public int getBigContentViewLayoutResource(int i) {
            if (i <= 3) {
                return R.layout.media3_notification_template_big_media_narrow_custom;
            }
            return R.layout.media3_notification_template_big_media_custom;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViews;
            if (Util.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getHeadsUpContentView() != null) {
                remoteViews = this.mBuilder.getHeadsUpContentView();
            } else {
                remoteViews = this.mBuilder.getContentView();
            }
            if (remoteViews == null) {
                return null;
            }
            RemoteViews generateBigContentView = generateBigContentView();
            buildIntoRemoteViews(generateBigContentView, remoteViews);
            setBackgroundColor(generateBigContentView);
            return generateBigContentView;
        }

        private void setBackgroundColor(RemoteViews remoteViews) {
            int i;
            if (this.mBuilder.getColor() != 0) {
                i = this.mBuilder.getColor();
            } else {
                i = this.mBuilder.mContext.getResources().getColor(R.color.notification_material_background_media_default_color);
            }
            remoteViews.setInt(R.id.status_bar_latest_event_content, "setBackgroundColor", i);
        }
    }

    private static class Api24Impl {
        private Api24Impl() {
        }

        public static Notification.DecoratedMediaCustomViewStyle createDecoratedMediaCustomViewStyle() {
            return new Notification.DecoratedMediaCustomViewStyle();
        }
    }

    private static class Api34Impl {
        private Api34Impl() {
        }

        public static Notification.MediaStyle setRemotePlaybackInfo(Notification.MediaStyle mediaStyle, CharSequence charSequence, int i, PendingIntent pendingIntent) {
            mediaStyle.setRemotePlaybackInfo(charSequence, i, pendingIntent);
            return mediaStyle;
        }
    }
}
