package com.brentvatne.exoplayer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.CommandButton;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionService;
import androidx.media3.session.MediaStyleNotificationHelper;
import androidx.media3.session.SessionCommand;
import androidx.media3.ui.R;
import com.brentvatne.common.toolbox.DebugLog;
import expo.modules.notifications.service.NotificationsService;
import io.sentry.cache.EnvelopeCache;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000bH\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u000bH\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\nH\u0002J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0016J\u0012\u0010 \u001a\u0004\u0018\u00010\u000b2\u0006\u0010!\u001a\u00020\"H\u0016J\"\u0010#\u001a\u00020$2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0016J\u0012\u0010'\u001a\u00020\u00162\b\u0010(\u001a\u0004\u0018\u00010\u001eH\u0016J\u0018\u0010)\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020+H\u0016J\u001c\u0010,\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\n2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u000e\u0010.\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0002X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\r8\u0002X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/brentvatne/exoplayer/VideoPlaybackService;", "Landroidx/media3/session/MediaSessionService;", "()V", "binder", "Lcom/brentvatne/exoplayer/PlaybackServiceBinder;", "commandSeekBackward", "Landroidx/media3/session/SessionCommand;", "commandSeekForward", "mediaSessionsList", "", "Landroidx/media3/exoplayer/ExoPlayer;", "Landroidx/media3/session/MediaSession;", "seekBackwardBtn", "Landroidx/media3/session/CommandButton;", "seekForwardBtn", "sourceActivity", "Ljava/lang/Class;", "Landroid/app/Activity;", "buildNotification", "Landroid/app/Notification;", "session", "cleanup", "", "createSessionNotification", "hideAllNotifications", "hidePlayerNotification", "player", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onDestroy", "onGetSession", "controllerInfo", "Landroidx/media3/session/MediaSession$ControllerInfo;", "onStartCommand", "", "flags", "startId", "onTaskRemoved", "rootIntent", "onUpdateNotification", "startInForegroundRequired", "", "registerPlayer", "from", "unregisterPlayer", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlaybackService.kt */
public final class VideoPlaybackService extends MediaSessionService {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NOTIFICATION_CHANEL_ID = "RNVIDEO_SESSION_NOTIFICATION";
    private static final long SEEK_INTERVAL_MS = 10000;
    private static final String TAG = "VideoPlaybackService";
    private PlaybackServiceBinder binder = new PlaybackServiceBinder(this);
    private final SessionCommand commandSeekBackward;
    private final SessionCommand commandSeekForward;
    private Map<ExoPlayer, MediaSession> mediaSessionsList = new LinkedHashMap();
    private final CommandButton seekBackwardBtn;
    private final CommandButton seekForwardBtn;
    private Class<Activity> sourceActivity;

    public MediaSession onGetSession(MediaSession.ControllerInfo controllerInfo) {
        Intrinsics.checkNotNullParameter(controllerInfo, "controllerInfo");
        return null;
    }

    public VideoPlaybackService() {
        SessionCommand sessionCommand = new SessionCommand(Companion.COMMAND.SEEK_FORWARD.getStringValue(), Bundle.EMPTY);
        this.commandSeekForward = sessionCommand;
        SessionCommand sessionCommand2 = new SessionCommand(Companion.COMMAND.SEEK_BACKWARD.getStringValue(), Bundle.EMPTY);
        this.commandSeekBackward = sessionCommand2;
        CommandButton build = new CommandButton.Builder().setDisplayName("forward").setSessionCommand(sessionCommand).setIconResId(R.drawable.exo_notification_fastforward).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        this.seekForwardBtn = build;
        CommandButton build2 = new CommandButton.Builder().setDisplayName("backward").setSessionCommand(sessionCommand2).setIconResId(R.drawable.exo_notification_rewind).build();
        Intrinsics.checkNotNullExpressionValue(build2, "build(...)");
        this.seekBackwardBtn = build2;
    }

    public final void registerPlayer(ExoPlayer exoPlayer, Class<Activity> cls) {
        Intrinsics.checkNotNullParameter(exoPlayer, "player");
        Intrinsics.checkNotNullParameter(cls, "from");
        if (!this.mediaSessionsList.containsKey(exoPlayer)) {
            this.sourceActivity = cls;
            MediaSession build = new MediaSession.Builder(this, exoPlayer).setId("RNVideoPlaybackService_" + exoPlayer.hashCode()).setCallback((MediaSession.Callback) new VideoPlaybackCallback()).setCustomLayout((List<CommandButton>) Util.immutableListOf(this.seekForwardBtn, this.seekBackwardBtn)).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            this.mediaSessionsList.put(exoPlayer, build);
            addSession(build);
            startForeground(build.getPlayer().hashCode(), buildNotification(build));
        }
    }

    public final void unregisterPlayer(ExoPlayer exoPlayer) {
        Intrinsics.checkNotNullParameter(exoPlayer, "player");
        hidePlayerNotification(exoPlayer);
        MediaSession remove = this.mediaSessionsList.remove(exoPlayer);
        if (remove != null) {
            remove.release();
        }
        if (this.mediaSessionsList.isEmpty()) {
            cleanup();
            stopSelf();
        }
    }

    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        return this.binder;
    }

    public void onUpdateNotification(MediaSession mediaSession, boolean z) {
        Intrinsics.checkNotNullParameter(mediaSession, EnvelopeCache.PREFIX_CURRENT_SESSION_FILE);
        createSessionNotification(mediaSession);
    }

    public void onTaskRemoved(Intent intent) {
        cleanup();
        stopSelf();
    }

    public void onDestroy() {
        cleanup();
        Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).deleteNotificationChannel(NOTIFICATION_CHANEL_ID);
        super.onDestroy();
    }

    private final void createSessionNotification(MediaSession mediaSession) {
        Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        notificationManager.createNotificationChannel(new NotificationChannel(NOTIFICATION_CHANEL_ID, NOTIFICATION_CHANEL_ID, 2));
        if (mediaSession.getPlayer().getCurrentMediaItem() == null) {
            notificationManager.cancel(mediaSession.getPlayer().hashCode());
            return;
        }
        notificationManager.notify(mediaSession.getPlayer().hashCode(), buildNotification(mediaSession));
    }

    private final Notification buildNotification(MediaSession mediaSession) {
        int i;
        Context context = this;
        Class cls = this.sourceActivity;
        if (cls == null) {
            cls = getClass();
        }
        Intent intent = new Intent(context, cls);
        intent.setFlags(603979776);
        if (Build.VERSION.SDK_INT >= 33) {
            Notification build = new NotificationCompat.Builder(context, NOTIFICATION_CHANEL_ID).setSmallIcon(androidx.media3.session.R.drawable.media3_icon_circular_play).setStyle(new MediaStyleNotificationHelper.MediaStyle(mediaSession)).setContentIntent(PendingIntent.getActivity(context, 0, intent, 201326592)).build();
            Intrinsics.checkNotNull(build);
            return build;
        }
        int hashCode = mediaSession.getPlayer().hashCode();
        Class<VideoPlaybackService> cls2 = VideoPlaybackService.class;
        Intent intent2 = new Intent(context, cls2);
        intent2.putExtra("PLAYER_ID", hashCode);
        intent2.putExtra("ACTION", Companion.COMMAND.SEEK_BACKWARD.getStringValue());
        int i2 = hashCode * 10;
        PendingIntent service = PendingIntent.getService(context, i2, intent2, 167772160);
        Intent intent3 = new Intent(context, cls2);
        intent3.putExtra("PLAYER_ID", hashCode);
        intent3.putExtra("ACTION", Companion.COMMAND.TOGGLE_PLAY.getStringValue());
        PendingIntent service2 = PendingIntent.getService(context, i2 + 1, intent3, 167772160);
        Intent intent4 = new Intent(context, cls2);
        intent4.putExtra("PLAYER_ID", hashCode);
        intent4.putExtra("ACTION", Companion.COMMAND.SEEK_FORWARD.getStringValue());
        PendingIntent service3 = PendingIntent.getService(context, i2 + 2, intent4, 167772160);
        NotificationCompat.Builder addAction = new NotificationCompat.Builder(context, NOTIFICATION_CHANEL_ID).setVisibility(1).setSmallIcon(androidx.media3.session.R.drawable.media3_icon_circular_play).addAction(androidx.media3.session.R.drawable.media3_icon_rewind, "Seek Backward", service);
        if (mediaSession.getPlayer().isPlaying()) {
            i = androidx.media3.session.R.drawable.media3_icon_pause;
        } else {
            i = androidx.media3.session.R.drawable.media3_icon_play;
        }
        NotificationCompat.Builder contentIntent = addAction.addAction(i, "Toggle Play", service2).addAction(androidx.media3.session.R.drawable.media3_icon_fast_forward, "Seek Forward", service3).setStyle(new MediaStyleNotificationHelper.MediaStyle(mediaSession).setShowActionsInCompactView(0, 1, 2)).setContentTitle(mediaSession.getPlayer().getMediaMetadata().title).setContentText(mediaSession.getPlayer().getMediaMetadata().description).setContentIntent(PendingIntent.getActivity(context, 0, intent, 201326592));
        Uri uri = mediaSession.getPlayer().getMediaMetadata().artworkUri;
        Notification build2 = contentIntent.setLargeIcon(uri != null ? (Bitmap) mediaSession.getBitmapLoader().loadBitmap(uri).get() : null).setOngoing(true).build();
        Intrinsics.checkNotNull(build2);
        return build2;
    }

    private final void hidePlayerNotification(ExoPlayer exoPlayer) {
        Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancel(exoPlayer.hashCode());
    }

    private final void hideAllNotifications() {
        Object systemService = getSystemService(NotificationsService.NOTIFICATION_KEY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        ((NotificationManager) systemService).cancelAll();
    }

    private final void cleanup() {
        hideAllNotifications();
        for (Map.Entry<ExoPlayer, MediaSession> value : this.mediaSessionsList.entrySet()) {
            ((MediaSession) value.getValue()).release();
        }
        this.mediaSessionsList.clear();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        Object obj;
        boolean z;
        if (intent != null) {
            int intExtra = intent.getIntExtra("PLAYER_ID", -1);
            String stringExtra = intent.getStringExtra("ACTION");
            if (intExtra < 0) {
                DebugLog.w(TAG, "Received Command without playerId");
                return super.onStartCommand(intent, i, i2);
            } else if (stringExtra == null) {
                DebugLog.w(TAG, "Received Command without action command");
                return super.onStartCommand(intent, i, i2);
            } else {
                Iterator it = this.mediaSessionsList.values().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (((MediaSession) obj).getPlayer().hashCode() == intExtra) {
                        z = true;
                        continue;
                    } else {
                        z = false;
                        continue;
                    }
                    if (z) {
                        break;
                    }
                }
                MediaSession mediaSession = (MediaSession) obj;
                if (mediaSession == null) {
                    return super.onStartCommand(intent, i, i2);
                }
                Companion companion = Companion;
                companion.handleCommand(companion.commandFromString(stringExtra), mediaSession);
            }
        }
        return super.onStartCommand(intent, i, i2);
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/brentvatne/exoplayer/VideoPlaybackService$Companion;", "", "()V", "NOTIFICATION_CHANEL_ID", "", "SEEK_INTERVAL_MS", "", "TAG", "commandFromString", "Lcom/brentvatne/exoplayer/VideoPlaybackService$Companion$COMMAND;", "value", "handleCommand", "", "command", "session", "Landroidx/media3/session/MediaSession;", "COMMAND", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoPlaybackService.kt */
    public static final class Companion {

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        /* compiled from: VideoPlaybackService.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
            static {
                /*
                    com.brentvatne.exoplayer.VideoPlaybackService$Companion$COMMAND[] r0 = com.brentvatne.exoplayer.VideoPlaybackService.Companion.COMMAND.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.brentvatne.exoplayer.VideoPlaybackService$Companion$COMMAND r1 = com.brentvatne.exoplayer.VideoPlaybackService.Companion.COMMAND.SEEK_BACKWARD     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.brentvatne.exoplayer.VideoPlaybackService$Companion$COMMAND r1 = com.brentvatne.exoplayer.VideoPlaybackService.Companion.COMMAND.SEEK_FORWARD     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    com.brentvatne.exoplayer.VideoPlaybackService$Companion$COMMAND r1 = com.brentvatne.exoplayer.VideoPlaybackService.Companion.COMMAND.TOGGLE_PLAY     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    com.brentvatne.exoplayer.VideoPlaybackService$Companion$COMMAND r1 = com.brentvatne.exoplayer.VideoPlaybackService.Companion.COMMAND.PLAY     // Catch:{ NoSuchFieldError -> 0x002b }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                L_0x002b:
                    com.brentvatne.exoplayer.VideoPlaybackService$Companion$COMMAND r1 = com.brentvatne.exoplayer.VideoPlaybackService.Companion.COMMAND.PAUSE     // Catch:{ NoSuchFieldError -> 0x0034 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                    r2 = 5
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                L_0x0034:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.brentvatne.exoplayer.VideoPlaybackService.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/brentvatne/exoplayer/VideoPlaybackService$Companion$COMMAND;", "", "stringValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getStringValue", "()Ljava/lang/String;", "NONE", "SEEK_FORWARD", "SEEK_BACKWARD", "TOGGLE_PLAY", "PLAY", "PAUSE", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        /* compiled from: VideoPlaybackService.kt */
        public enum COMMAND {
            NONE("NONE"),
            SEEK_FORWARD("COMMAND_SEEK_FORWARD"),
            SEEK_BACKWARD("COMMAND_SEEK_BACKWARD"),
            TOGGLE_PLAY("COMMAND_TOGGLE_PLAY"),
            PLAY("COMMAND_PLAY"),
            PAUSE("COMMAND_PAUSE");
            
            private final String stringValue;

            public static EnumEntries<COMMAND> getEntries() {
                return $ENTRIES;
            }

            private COMMAND(String str) {
                this.stringValue = str;
            }

            public final String getStringValue() {
                return this.stringValue;
            }

            static {
                COMMAND[] $values;
                $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
            }
        }

        public final COMMAND commandFromString(String str) {
            Intrinsics.checkNotNullParameter(str, "value");
            if (Intrinsics.areEqual((Object) str, (Object) COMMAND.SEEK_FORWARD.getStringValue())) {
                return COMMAND.SEEK_FORWARD;
            }
            if (Intrinsics.areEqual((Object) str, (Object) COMMAND.SEEK_BACKWARD.getStringValue())) {
                return COMMAND.SEEK_BACKWARD;
            }
            if (Intrinsics.areEqual((Object) str, (Object) COMMAND.TOGGLE_PLAY.getStringValue())) {
                return COMMAND.TOGGLE_PLAY;
            }
            if (Intrinsics.areEqual((Object) str, (Object) COMMAND.PLAY.getStringValue())) {
                return COMMAND.PLAY;
            }
            if (Intrinsics.areEqual((Object) str, (Object) COMMAND.PAUSE.getStringValue())) {
                return COMMAND.PAUSE;
            }
            return COMMAND.NONE;
        }

        public final void handleCommand(COMMAND command, MediaSession mediaSession) {
            Intrinsics.checkNotNullParameter(command, "command");
            Intrinsics.checkNotNullParameter(mediaSession, EnvelopeCache.PREFIX_CURRENT_SESSION_FILE);
            int i = WhenMappings.$EnumSwitchMapping$0[command.ordinal()];
            if (i == 1) {
                mediaSession.getPlayer().seekTo(mediaSession.getPlayer().getContentPosition() - 10000);
            } else if (i == 2) {
                mediaSession.getPlayer().seekTo(mediaSession.getPlayer().getContentPosition() + 10000);
            } else if (i == 3) {
                handleCommand(mediaSession.getPlayer().isPlaying() ? COMMAND.PAUSE : COMMAND.PLAY, mediaSession);
            } else if (i == 4) {
                mediaSession.getPlayer().play();
            } else if (i != 5) {
                DebugLog.w(VideoPlaybackService.TAG, "Received COMMAND.NONE - was there an error?");
            } else {
                mediaSession.getPlayer().pause();
            }
        }
    }
}
