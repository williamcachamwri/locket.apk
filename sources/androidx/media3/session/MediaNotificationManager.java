package androidx.media3.session;

import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.media3.common.Player;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaNotification;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class MediaNotificationManager {
    private static final String TAG = "MediaNtfMng";
    private final MediaNotification.ActionFactory actionFactory;
    private final Map<MediaSession, ListenableFuture<MediaController>> controllerMap;
    private final Executor mainExecutor = new MediaNotificationManager$$ExternalSyntheticLambda7(new Handler(Looper.getMainLooper()));
    private MediaNotification mediaNotification;
    private final MediaNotification.Provider mediaNotificationProvider;
    private final MediaSessionService mediaSessionService;
    private final NotificationManagerCompat notificationManagerCompat;
    private final Intent startSelfIntent;
    private boolean startedInForeground;
    private int totalNotificationCount;

    public MediaNotificationManager(MediaSessionService mediaSessionService2, MediaNotification.Provider provider, MediaNotification.ActionFactory actionFactory2) {
        this.mediaSessionService = mediaSessionService2;
        this.mediaNotificationProvider = provider;
        this.actionFactory = actionFactory2;
        this.notificationManagerCompat = NotificationManagerCompat.from(mediaSessionService2);
        this.startSelfIntent = new Intent(mediaSessionService2, mediaSessionService2.getClass());
        this.controllerMap = new HashMap();
        this.startedInForeground = false;
    }

    public void addSession(MediaSession mediaSession) {
        if (!this.controllerMap.containsKey(mediaSession)) {
            MediaControllerListener mediaControllerListener = new MediaControllerListener(this.mediaSessionService, mediaSession);
            Bundle bundle = new Bundle();
            bundle.putBoolean(MediaController.KEY_MEDIA_NOTIFICATION_CONTROLLER_FLAG, true);
            ListenableFuture<MediaController> buildAsync = new MediaController.Builder(this.mediaSessionService, mediaSession.getToken()).setConnectionHints(bundle).setListener(mediaControllerListener).setApplicationLooper(Looper.getMainLooper()).buildAsync();
            this.controllerMap.put(mediaSession, buildAsync);
            buildAsync.addListener(new MediaNotificationManager$$ExternalSyntheticLambda5(this, buildAsync, mediaControllerListener, mediaSession), this.mainExecutor);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addSession$1$androidx-media3-session-MediaNotificationManager  reason: not valid java name */
    public /* synthetic */ void m1042lambda$addSession$1$androidxmedia3sessionMediaNotificationManager(ListenableFuture listenableFuture, MediaControllerListener mediaControllerListener, MediaSession mediaSession) {
        try {
            mediaControllerListener.onConnected(shouldShowNotification(mediaSession));
            ((MediaController) listenableFuture.get(0, TimeUnit.MILLISECONDS)).addListener(mediaControllerListener);
        } catch (InterruptedException | CancellationException | ExecutionException | TimeoutException unused) {
            this.mediaSessionService.removeSession(mediaSession);
        }
    }

    public void removeSession(MediaSession mediaSession) {
        ListenableFuture remove = this.controllerMap.remove(mediaSession);
        if (remove != null) {
            MediaController.releaseFuture(remove);
        }
    }

    public void onCustomAction(MediaSession mediaSession, String str, Bundle bundle) {
        MediaController connectedControllerForSession = getConnectedControllerForSession(mediaSession);
        if (connectedControllerForSession != null) {
            Util.postOrRun(new Handler(mediaSession.getPlayer().getApplicationLooper()), new MediaNotificationManager$$ExternalSyntheticLambda0(this, mediaSession, str, bundle, connectedControllerForSession));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCustomAction$3$androidx-media3-session-MediaNotificationManager  reason: not valid java name */
    public /* synthetic */ void m1044lambda$onCustomAction$3$androidxmedia3sessionMediaNotificationManager(MediaSession mediaSession, String str, Bundle bundle, MediaController mediaController) {
        if (!this.mediaNotificationProvider.handleCustomCommand(mediaSession, str, bundle)) {
            this.mainExecutor.execute(new MediaNotificationManager$$ExternalSyntheticLambda4(this, mediaController, str, bundle));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateNotification(androidx.media3.session.MediaSession r9, boolean r10) {
        /*
            r8 = this;
            androidx.media3.session.MediaSessionService r0 = r8.mediaSessionService
            boolean r0 = r0.isSessionAdded(r9)
            r1 = 1
            if (r0 == 0) goto L_0x0058
            boolean r0 = r8.shouldShowNotification(r9)
            if (r0 != 0) goto L_0x0010
            goto L_0x0058
        L_0x0010:
            int r0 = r8.totalNotificationCount
            int r0 = r0 + r1
            r8.totalNotificationCount = r0
            java.util.Map<androidx.media3.session.MediaSession, com.google.common.util.concurrent.ListenableFuture<androidx.media3.session.MediaController>> r1 = r8.controllerMap
            java.lang.Object r1 = r1.get(r9)
            com.google.common.util.concurrent.ListenableFuture r1 = (com.google.common.util.concurrent.ListenableFuture) r1
            if (r1 == 0) goto L_0x002c
            boolean r2 = r1.isDone()
            if (r2 == 0) goto L_0x002c
            java.lang.Object r1 = com.google.common.util.concurrent.Futures.getDone(r1)     // Catch:{ ExecutionException -> 0x002c }
            androidx.media3.session.MediaController r1 = (androidx.media3.session.MediaController) r1     // Catch:{ ExecutionException -> 0x002c }
            goto L_0x002d
        L_0x002c:
            r1 = 0
        L_0x002d:
            if (r1 == 0) goto L_0x0034
            com.google.common.collect.ImmutableList r1 = r1.getMediaButtonPreferences()
            goto L_0x0038
        L_0x0034:
            com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of()
        L_0x0038:
            r5 = r1
            androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda2 r6 = new androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda2
            r6.<init>(r8, r0, r9)
            android.os.Handler r0 = new android.os.Handler
            androidx.media3.common.Player r1 = r9.getPlayer()
            android.os.Looper r1 = r1.getApplicationLooper()
            r0.<init>(r1)
            androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda3 r1 = new androidx.media3.session.MediaNotificationManager$$ExternalSyntheticLambda3
            r2 = r1
            r3 = r8
            r4 = r9
            r7 = r10
            r2.<init>(r3, r4, r5, r6, r7)
            androidx.media3.common.util.Util.postOrRun(r0, r1)
            return
        L_0x0058:
            r8.maybeStopForegroundService(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaNotificationManager.updateNotification(androidx.media3.session.MediaSession, boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateNotification$5$androidx-media3-session-MediaNotificationManager  reason: not valid java name */
    public /* synthetic */ void m1046lambda$updateNotification$5$androidxmedia3sessionMediaNotificationManager(int i, MediaSession mediaSession, MediaNotification mediaNotification2) {
        this.mainExecutor.execute(new MediaNotificationManager$$ExternalSyntheticLambda1(this, i, mediaSession, mediaNotification2));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateNotification$7$androidx-media3-session-MediaNotificationManager  reason: not valid java name */
    public /* synthetic */ void m1048lambda$updateNotification$7$androidxmedia3sessionMediaNotificationManager(MediaSession mediaSession, ImmutableList immutableList, MediaNotification.Provider.Callback callback, boolean z) {
        this.mainExecutor.execute(new MediaNotificationManager$$ExternalSyntheticLambda6(this, mediaSession, this.mediaNotificationProvider.createNotification(mediaSession, immutableList, this.actionFactory, callback), z));
    }

    public boolean isStartedInForeground() {
        return this.startedInForeground;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldRunInForeground(MediaSession mediaSession, boolean z) {
        MediaController connectedControllerForSession = getConnectedControllerForSession(mediaSession);
        return connectedControllerForSession != null && (connectedControllerForSession.getPlayWhenReady() || z) && (connectedControllerForSession.getPlaybackState() == 3 || connectedControllerForSession.getPlaybackState() == 2);
    }

    /* access modifiers changed from: private */
    /* renamed from: onNotificationUpdated */
    public void m1045lambda$updateNotification$4$androidxmedia3sessionMediaNotificationManager(int i, MediaSession mediaSession, MediaNotification mediaNotification2) {
        if (i == this.totalNotificationCount) {
            m1047lambda$updateNotification$6$androidxmedia3sessionMediaNotificationManager(mediaSession, mediaNotification2, shouldRunInForeground(mediaSession, false));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: updateNotificationInternal */
    public void m1047lambda$updateNotification$6$androidxmedia3sessionMediaNotificationManager(MediaSession mediaSession, MediaNotification mediaNotification2, boolean z) {
        mediaNotification2.notification.extras.putParcelable(NotificationCompat.EXTRA_MEDIA_SESSION, (MediaSession.Token) mediaSession.getSessionCompat().getSessionToken().getToken());
        this.mediaNotification = mediaNotification2;
        if (z) {
            startForeground(mediaNotification2);
            return;
        }
        this.notificationManagerCompat.notify(mediaNotification2.notificationId, mediaNotification2.notification);
        maybeStopForegroundService(false);
    }

    private void maybeStopForegroundService(boolean z) {
        MediaNotification mediaNotification2;
        List<MediaSession> sessions = this.mediaSessionService.getSessions();
        int i = 0;
        while (i < sessions.size()) {
            if (!shouldRunInForeground(sessions.get(i), false)) {
                i++;
            } else {
                return;
            }
        }
        stopForeground(z);
        if (z && (mediaNotification2 = this.mediaNotification) != null) {
            this.notificationManagerCompat.cancel(mediaNotification2.notificationId);
            this.totalNotificationCount++;
            this.mediaNotification = null;
        }
    }

    private boolean shouldShowNotification(MediaSession mediaSession) {
        MediaController connectedControllerForSession = getConnectedControllerForSession(mediaSession);
        return (connectedControllerForSession == null || connectedControllerForSession.getCurrentTimeline().isEmpty() || connectedControllerForSession.getPlaybackState() == 1) ? false : true;
    }

    private MediaController getConnectedControllerForSession(MediaSession mediaSession) {
        ListenableFuture listenableFuture = this.controllerMap.get(mediaSession);
        if (listenableFuture == null || !listenableFuture.isDone()) {
            return null;
        }
        try {
            return (MediaController) Futures.getDone(listenableFuture);
        } catch (ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: sendCustomCommandIfCommandIsAvailable */
    public void m1043lambda$onCustomAction$2$androidxmedia3sessionMediaNotificationManager(MediaController mediaController, final String str, Bundle bundle) {
        SessionCommand sessionCommand;
        UnmodifiableIterator<SessionCommand> it = mediaController.getAvailableSessionCommands().commands.iterator();
        while (true) {
            if (!it.hasNext()) {
                sessionCommand = null;
                break;
            }
            sessionCommand = it.next();
            if (sessionCommand.commandCode == 0 && sessionCommand.customAction.equals(str)) {
                break;
            }
        }
        if (sessionCommand != null && mediaController.getAvailableSessionCommands().contains(sessionCommand)) {
            Futures.addCallback(mediaController.sendCustomCommand(new SessionCommand(str, bundle), Bundle.EMPTY), new FutureCallback<SessionResult>() {
                public void onSuccess(SessionResult sessionResult) {
                }

                public void onFailure(Throwable th) {
                    Log.w(MediaNotificationManager.TAG, "custom command " + str + " produced an error: " + th.getMessage(), th);
                }
            }, MoreExecutors.directExecutor());
        }
    }

    private static final class MediaControllerListener implements MediaController.Listener, Player.Listener {
        private final MediaSessionService mediaSessionService;
        private final MediaSession session;

        public MediaControllerListener(MediaSessionService mediaSessionService2, MediaSession mediaSession) {
            this.mediaSessionService = mediaSessionService2;
            this.session = mediaSession;
        }

        public void onConnected(boolean z) {
            if (z) {
                this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
            }
        }

        public void onMediaButtonPreferencesChanged(MediaController mediaController, List<CommandButton> list) {
            this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
        }

        public void onAvailableSessionCommandsChanged(MediaController mediaController, SessionCommands sessionCommands) {
            this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
        }

        public void onDisconnected(MediaController mediaController) {
            if (this.mediaSessionService.isSessionAdded(this.session)) {
                this.mediaSessionService.removeSession(this.session);
            }
            this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
        }

        public void onEvents(Player player, Player.Events events) {
            if (events.containsAny(4, 5, 14, 0)) {
                this.mediaSessionService.onUpdateNotificationInternal(this.session, false);
            }
        }
    }

    private void startForeground(MediaNotification mediaNotification2) {
        ContextCompat.startForegroundService(this.mediaSessionService, this.startSelfIntent);
        Util.setForegroundServiceNotification(this.mediaSessionService, mediaNotification2.notificationId, mediaNotification2.notification, 2, "mediaPlayback");
        this.startedInForeground = true;
    }

    private void stopForeground(boolean z) {
        if (Util.SDK_INT >= 24) {
            Api24.stopForeground(this.mediaSessionService, z);
        } else {
            this.mediaSessionService.stopForeground(z);
        }
        this.startedInForeground = false;
    }

    private static class Api24 {
        public static void stopForeground(MediaSessionService mediaSessionService, boolean z) {
            mediaSessionService.stopForeground(z ? 1 : 2);
        }

        private Api24() {
        }
    }
}
