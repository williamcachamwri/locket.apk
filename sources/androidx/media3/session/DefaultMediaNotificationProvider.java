package androidx.media3.session;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.media3.common.C;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.CommandButton;
import androidx.media3.session.MediaNotification;
import androidx.media3.session.MediaStyleNotificationHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import expo.modules.notifications.service.NotificationsService;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

public class DefaultMediaNotificationProvider implements MediaNotification.Provider {
    public static final String COMMAND_KEY_COMPACT_VIEW_INDEX = "androidx.media3.session.command.COMPACT_VIEW_INDEX";
    public static final String DEFAULT_CHANNEL_ID = "default_channel_id";
    public static final int DEFAULT_CHANNEL_NAME_RESOURCE_ID = R.string.default_notification_channel_name;
    public static final int DEFAULT_NOTIFICATION_ID = 1001;
    public static final String GROUP_KEY = "media3_group_key";
    private static final String TAG = "NotificationProvider";
    private final String channelId;
    private final int channelNameResourceId;
    private final Context context;
    private final NotificationIdProvider notificationIdProvider;
    private final NotificationManager notificationManager;
    private OnBitmapLoadedFutureCallback pendingOnBitmapLoadedFutureCallback;
    private int smallIconResourceId;

    public interface NotificationIdProvider {
        int getNotificationId(MediaSession mediaSession);
    }

    static /* synthetic */ int lambda$new$0(MediaSession mediaSession) {
        return 1001;
    }

    public final boolean handleCustomCommand(MediaSession mediaSession, String str, Bundle bundle) {
        return false;
    }

    public static final class Builder {
        private boolean built;
        /* access modifiers changed from: private */
        public String channelId = DefaultMediaNotificationProvider.DEFAULT_CHANNEL_ID;
        /* access modifiers changed from: private */
        public int channelNameResourceId = DefaultMediaNotificationProvider.DEFAULT_CHANNEL_NAME_RESOURCE_ID;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public NotificationIdProvider notificationIdProvider = new DefaultMediaNotificationProvider$Builder$$ExternalSyntheticLambda1();

        static /* synthetic */ int lambda$new$0(MediaSession mediaSession) {
            return 1001;
        }

        static /* synthetic */ int lambda$setNotificationId$1(int i, MediaSession mediaSession) {
            return i;
        }

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder setNotificationId(int i) {
            this.notificationIdProvider = new DefaultMediaNotificationProvider$Builder$$ExternalSyntheticLambda0(i);
            return this;
        }

        public Builder setNotificationIdProvider(NotificationIdProvider notificationIdProvider2) {
            this.notificationIdProvider = notificationIdProvider2;
            return this;
        }

        public Builder setChannelId(String str) {
            this.channelId = str;
            return this;
        }

        public Builder setChannelName(int i) {
            this.channelNameResourceId = i;
            return this;
        }

        public DefaultMediaNotificationProvider build() {
            Assertions.checkState(!this.built);
            DefaultMediaNotificationProvider defaultMediaNotificationProvider = new DefaultMediaNotificationProvider(this);
            this.built = true;
            return defaultMediaNotificationProvider;
        }
    }

    public DefaultMediaNotificationProvider(Context context2) {
        this(context2, new DefaultMediaNotificationProvider$$ExternalSyntheticLambda0(), DEFAULT_CHANNEL_ID, DEFAULT_CHANNEL_NAME_RESOURCE_ID);
    }

    public DefaultMediaNotificationProvider(Context context2, NotificationIdProvider notificationIdProvider2, String str, int i) {
        this.context = context2;
        this.notificationIdProvider = notificationIdProvider2;
        this.channelId = str;
        this.channelNameResourceId = i;
        this.notificationManager = (NotificationManager) Assertions.checkStateNotNull((NotificationManager) context2.getSystemService(NotificationsService.NOTIFICATION_KEY));
        this.smallIconResourceId = R.drawable.media3_notification_small_icon;
    }

    private DefaultMediaNotificationProvider(Builder builder) {
        this(builder.context, builder.notificationIdProvider, builder.channelId, builder.channelNameResourceId);
    }

    public final MediaNotification createNotification(MediaSession mediaSession, ImmutableList<CommandButton> immutableList, MediaNotification.ActionFactory actionFactory, MediaNotification.Provider.Callback callback) {
        ensureNotificationChannel();
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < immutableList.size(); i++) {
            CommandButton commandButton = (CommandButton) immutableList.get(i);
            if (commandButton.sessionCommand != null && commandButton.sessionCommand.commandCode == 0 && commandButton.isEnabled) {
                builder.add((Object) (CommandButton) immutableList.get(i));
            }
        }
        Player player = mediaSession.getPlayer();
        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this.context, this.channelId);
        int notificationId = this.notificationIdProvider.getNotificationId(mediaSession);
        MediaStyleNotificationHelper.MediaStyle mediaStyle = new MediaStyleNotificationHelper.MediaStyle(mediaSession);
        mediaStyle.setShowActionsInCompactView(addNotificationActions(mediaSession, getMediaButtons(mediaSession, player.getAvailableCommands(), builder.build(), !Util.shouldShowPlayButton(player, mediaSession.getShowPlayButtonIfPlaybackIsSuppressed())), builder2, actionFactory));
        if (player.isCommandAvailable(18)) {
            MediaMetadata mediaMetadata = player.getMediaMetadata();
            builder2.setContentTitle(getNotificationContentTitle(mediaMetadata)).setContentText(getNotificationContentText(mediaMetadata));
            ListenableFuture loadBitmapFromMetadata = mediaSession.getBitmapLoader().loadBitmapFromMetadata(mediaMetadata);
            if (loadBitmapFromMetadata != null) {
                OnBitmapLoadedFutureCallback onBitmapLoadedFutureCallback = this.pendingOnBitmapLoadedFutureCallback;
                if (onBitmapLoadedFutureCallback != null) {
                    onBitmapLoadedFutureCallback.discardIfPending();
                }
                if (loadBitmapFromMetadata.isDone()) {
                    try {
                        builder2.setLargeIcon((Bitmap) Futures.getDone(loadBitmapFromMetadata));
                    } catch (CancellationException | ExecutionException e) {
                        Log.w(TAG, getBitmapLoadErrorMessage(e));
                    }
                } else {
                    OnBitmapLoadedFutureCallback onBitmapLoadedFutureCallback2 = new OnBitmapLoadedFutureCallback(notificationId, builder2, callback);
                    this.pendingOnBitmapLoadedFutureCallback = onBitmapLoadedFutureCallback2;
                    Handler applicationHandler = mediaSession.getImpl().getApplicationHandler();
                    Objects.requireNonNull(applicationHandler);
                    Futures.addCallback(loadBitmapFromMetadata, onBitmapLoadedFutureCallback2, new DefaultMediaNotificationProvider$$ExternalSyntheticLambda1(applicationHandler));
                }
            }
        }
        long playbackStartTimeEpochMs = getPlaybackStartTimeEpochMs(player);
        boolean z = playbackStartTimeEpochMs != C.TIME_UNSET;
        if (!z) {
            playbackStartTimeEpochMs = 0;
        }
        builder2.setWhen(playbackStartTimeEpochMs).setShowWhen(z).setUsesChronometer(z);
        if (Util.SDK_INT >= 31) {
            Api31.setForegroundServiceBehavior(builder2);
        }
        return new MediaNotification(notificationId, builder2.setContentIntent(mediaSession.getSessionActivity()).setDeleteIntent(actionFactory.createMediaActionPendingIntent(mediaSession, 3)).setOnlyAlertOnce(true).setSmallIcon(this.smallIconResourceId).setStyle(mediaStyle).setVisibility(1).setOngoing(false).setGroup(GROUP_KEY).build());
    }

    public final void setSmallIcon(int i) {
        this.smallIconResourceId = i;
    }

    /* access modifiers changed from: protected */
    public ImmutableList<CommandButton> getMediaButtons(MediaSession mediaSession, Player.Commands commands, ImmutableList<CommandButton> immutableList, boolean z) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        if (commands.containsAny(7, 6)) {
            Bundle bundle = new Bundle();
            bundle.putInt(COMMAND_KEY_COMPACT_VIEW_INDEX, -1);
            builder.add((Object) new CommandButton.Builder(CommandButton.ICON_PREVIOUS).setPlayerCommand(6).setDisplayName(this.context.getString(R.string.media3_controls_seek_to_previous_description)).setExtras(bundle).build());
        }
        if (commands.contains(1)) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt(COMMAND_KEY_COMPACT_VIEW_INDEX, -1);
            if (z) {
                builder.add((Object) new CommandButton.Builder(CommandButton.ICON_PAUSE).setPlayerCommand(1).setExtras(bundle2).setDisplayName(this.context.getString(R.string.media3_controls_pause_description)).build());
            } else {
                builder.add((Object) new CommandButton.Builder(CommandButton.ICON_PLAY).setPlayerCommand(1).setExtras(bundle2).setDisplayName(this.context.getString(R.string.media3_controls_play_description)).build());
            }
        }
        if (commands.containsAny(9, 8)) {
            Bundle bundle3 = new Bundle();
            bundle3.putInt(COMMAND_KEY_COMPACT_VIEW_INDEX, -1);
            builder.add((Object) new CommandButton.Builder(CommandButton.ICON_NEXT).setPlayerCommand(8).setExtras(bundle3).setDisplayName(this.context.getString(R.string.media3_controls_seek_to_next_description)).build());
        }
        for (int i = 0; i < immutableList.size(); i++) {
            CommandButton commandButton = (CommandButton) immutableList.get(i);
            if (commandButton.sessionCommand != null && commandButton.sessionCommand.commandCode == 0) {
                builder.add((Object) commandButton);
            }
        }
        return builder.build();
    }

    /* access modifiers changed from: protected */
    public int[] addNotificationActions(MediaSession mediaSession, ImmutableList<CommandButton> immutableList, NotificationCompat.Builder builder, MediaNotification.ActionFactory actionFactory) {
        MediaSession mediaSession2 = mediaSession;
        NotificationCompat.Builder builder2 = builder;
        MediaNotification.ActionFactory actionFactory2 = actionFactory;
        int[] iArr = new int[3];
        int[] iArr2 = new int[3];
        Arrays.fill(iArr, -1);
        Arrays.fill(iArr2, -1);
        int i = 0;
        for (int i2 = 0; i2 < immutableList.size(); i2++) {
            CommandButton commandButton = (CommandButton) immutableList.get(i2);
            if (commandButton.sessionCommand != null) {
                builder2.addAction(actionFactory2.createCustomActionFromCustomCommandButton(mediaSession2, commandButton));
            } else {
                Assertions.checkState(commandButton.playerCommand != -1);
                builder2.addAction(actionFactory2.createMediaAction(mediaSession2, IconCompat.createWithResource(this.context, commandButton.iconResId), commandButton.displayName, commandButton.playerCommand));
            }
            if (i != 3) {
                int i3 = commandButton.extras.getInt(COMMAND_KEY_COMPACT_VIEW_INDEX, -1);
                if (i3 >= 0 && i3 < 3) {
                    i++;
                    iArr[i3] = i2;
                } else if (commandButton.playerCommand == 7 || commandButton.playerCommand == 6) {
                    iArr2[0] = i2;
                } else if (commandButton.playerCommand == 1) {
                    iArr2[1] = i2;
                } else if (commandButton.playerCommand == 9 || commandButton.playerCommand == 8) {
                    iArr2[2] = i2;
                }
            }
        }
        if (i == 0) {
            int i4 = 0;
            for (int i5 = 0; i5 < 3; i5++) {
                int i6 = iArr2[i5];
                if (i6 != -1) {
                    iArr[i4] = i6;
                    i4++;
                }
            }
        }
        for (int i7 = 0; i7 < 3; i7++) {
            if (iArr[i7] == -1) {
                return Arrays.copyOf(iArr, i7);
            }
        }
        return iArr;
    }

    /* access modifiers changed from: protected */
    public CharSequence getNotificationContentTitle(MediaMetadata mediaMetadata) {
        return mediaMetadata.title;
    }

    /* access modifiers changed from: protected */
    public CharSequence getNotificationContentText(MediaMetadata mediaMetadata) {
        return mediaMetadata.artist;
    }

    private void ensureNotificationChannel() {
        if (Util.SDK_INT >= 26 && this.notificationManager.getNotificationChannel(this.channelId) == null) {
            Api26.createNotificationChannel(this.notificationManager, this.channelId, this.context.getString(this.channelNameResourceId));
        }
    }

    private static long getPlaybackStartTimeEpochMs(Player player) {
        return (!player.isPlaying() || player.isPlayingAd() || player.isCurrentMediaItemDynamic() || player.getPlaybackParameters().speed != 1.0f) ? C.TIME_UNSET : System.currentTimeMillis() - player.getContentPosition();
    }

    private static class OnBitmapLoadedFutureCallback implements FutureCallback<Bitmap> {
        private final NotificationCompat.Builder builder;
        private boolean discarded;
        private final int notificationId;
        private final MediaNotification.Provider.Callback onNotificationChangedCallback;

        public OnBitmapLoadedFutureCallback(int i, NotificationCompat.Builder builder2, MediaNotification.Provider.Callback callback) {
            this.notificationId = i;
            this.builder = builder2;
            this.onNotificationChangedCallback = callback;
        }

        public void discardIfPending() {
            this.discarded = true;
        }

        public void onSuccess(Bitmap bitmap) {
            if (!this.discarded) {
                this.builder.setLargeIcon(bitmap);
                this.onNotificationChangedCallback.onNotificationChanged(new MediaNotification(this.notificationId, this.builder.build()));
            }
        }

        public void onFailure(Throwable th) {
            if (!this.discarded) {
                Log.w(DefaultMediaNotificationProvider.TAG, DefaultMediaNotificationProvider.getBitmapLoadErrorMessage(th));
            }
        }
    }

    private static class Api26 {
        private Api26() {
        }

        public static void createNotificationChannel(NotificationManager notificationManager, String str, String str2) {
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, 2);
            if (Util.SDK_INT <= 27) {
                notificationChannel.setShowBadge(false);
            }
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private static class Api31 {
        private Api31() {
        }

        public static void setForegroundServiceBehavior(NotificationCompat.Builder builder) {
            builder.setForegroundServiceBehavior(1);
        }
    }

    /* access modifiers changed from: private */
    public static String getBitmapLoadErrorMessage(Throwable th) {
        return "Failed to load bitmap: " + th.getMessage();
    }
}
