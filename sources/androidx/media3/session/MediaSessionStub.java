package androidx.media3.session;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.Surface;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BundleListRetriever;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.IMediaSession;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

final class MediaSessionStub extends IMediaSession.Stub {
    private static final String TAG = "MediaSessionStub";
    public static final int UNKNOWN_SEQUENCE_NUMBER = Integer.MIN_VALUE;
    public static final int VERSION_INT = 4;
    private final ConnectedControllersManager<IBinder> connectedControllersManager;
    private int nextUniqueTrackGroupIdPrefix;
    private final Set<MediaSession.ControllerInfo> pendingControllers = Collections.synchronizedSet(new HashSet());
    private final WeakReference<MediaSessionImpl> sessionImpl;
    private final MediaSessionManager sessionManager;
    private ImmutableBiMap<TrackGroup, String> trackGroupIdMap = ImmutableBiMap.of();

    private interface ControllerPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo);
    }

    private interface MediaItemPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List<MediaItem> list);
    }

    private interface MediaItemsWithStartPositionPlayerTask {
        void run(PlayerWrapper playerWrapper, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition);
    }

    private interface SessionTask<T, K extends MediaSessionImpl> {
        T run(K k, MediaSession.ControllerInfo controllerInfo, int i);
    }

    public MediaSessionStub(MediaSessionImpl mediaSessionImpl) {
        this.sessionImpl = new WeakReference<>(mediaSessionImpl);
        this.sessionManager = MediaSessionManager.getSessionManager(mediaSessionImpl.getContext());
        this.connectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
    }

    public ConnectedControllersManager<IBinder> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }

    private static void sendSessionResult(MediaSession.ControllerInfo controllerInfo, int i, SessionResult sessionResult) {
        try {
            ((MediaSession.ControllerCb) Assertions.checkStateNotNull(controllerInfo.getControllerCb())).onSessionResult(i, sessionResult);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to send result to controller " + controllerInfo, e);
        }
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultSuccess(Consumer<PlayerWrapper> consumer) {
        return sendSessionResultSuccess((ControllerPlayerTask) new MediaSessionStub$$ExternalSyntheticLambda25(consumer));
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultSuccess(ControllerPlayerTask controllerPlayerTask) {
        return new MediaSessionStub$$ExternalSyntheticLambda18(controllerPlayerTask);
    }

    static /* synthetic */ ListenableFuture lambda$sendSessionResultSuccess$1(ControllerPlayerTask controllerPlayerTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        if (mediaSessionImpl.isReleased()) {
            return Futures.immediateVoidFuture();
        }
        controllerPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), controllerInfo);
        sendSessionResult(controllerInfo, i, new SessionResult(0));
        return Futures.immediateVoidFuture();
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<Void>, K> sendSessionResultWhenReady(SessionTask<ListenableFuture<SessionResult>, K> sessionTask) {
        return new MediaSessionStub$$ExternalSyntheticLambda39(sessionTask);
    }

    static /* synthetic */ void lambda$sendSessionResultWhenReady$2(MediaSession.ControllerInfo controllerInfo, int i, ListenableFuture listenableFuture) {
        SessionResult sessionResult;
        try {
            sessionResult = (SessionResult) Assertions.checkNotNull((SessionResult) listenableFuture.get(), "SessionResult must not be null");
        } catch (CancellationException e) {
            Log.w(TAG, "Session operation cancelled", e);
            sessionResult = new SessionResult(1);
        } catch (InterruptedException | ExecutionException e2) {
            Log.w(TAG, "Session operation failed", e2);
            sessionResult = new SessionResult(e2.getCause() instanceof UnsupportedOperationException ? -6 : -1);
        }
        sendSessionResult(controllerInfo, i, sessionResult);
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<SessionResult>, K> handleMediaItemsWhenReady(SessionTask<ListenableFuture<List<MediaItem>>, K> sessionTask, MediaItemPlayerTask mediaItemPlayerTask) {
        return new MediaSessionStub$$ExternalSyntheticLambda47(sessionTask, mediaItemPlayerTask);
    }

    static /* synthetic */ ListenableFuture lambda$handleMediaItemsWhenReady$6(SessionTask sessionTask, MediaItemPlayerTask mediaItemPlayerTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        if (mediaSessionImpl.isReleased()) {
            return Futures.immediateFuture(new SessionResult(-100));
        }
        return Util.transformFutureAsync((ListenableFuture) sessionTask.run(mediaSessionImpl, controllerInfo, i), new MediaSessionStub$$ExternalSyntheticLambda52(mediaSessionImpl, controllerInfo, mediaItemPlayerTask));
    }

    static /* synthetic */ void lambda$handleMediaItemsWhenReady$4(MediaSessionImpl mediaSessionImpl, MediaItemPlayerTask mediaItemPlayerTask, MediaSession.ControllerInfo controllerInfo, List list) {
        if (!mediaSessionImpl.isReleased()) {
            mediaItemPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), controllerInfo, list);
        }
    }

    private static <K extends MediaSessionImpl> SessionTask<ListenableFuture<SessionResult>, K> handleMediaItemsWithStartPositionWhenReady(SessionTask<ListenableFuture<MediaSession.MediaItemsWithStartPosition>, K> sessionTask, MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask) {
        return new MediaSessionStub$$ExternalSyntheticLambda59(sessionTask, mediaItemsWithStartPositionPlayerTask);
    }

    static /* synthetic */ ListenableFuture lambda$handleMediaItemsWithStartPositionWhenReady$9(SessionTask sessionTask, MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        if (mediaSessionImpl.isReleased()) {
            return Futures.immediateFuture(new SessionResult(-100));
        }
        return Util.transformFutureAsync((ListenableFuture) sessionTask.run(mediaSessionImpl, controllerInfo, i), new MediaSessionStub$$ExternalSyntheticLambda11(mediaSessionImpl, controllerInfo, mediaItemsWithStartPositionPlayerTask));
    }

    static /* synthetic */ void lambda$handleMediaItemsWithStartPositionWhenReady$7(MediaSessionImpl mediaSessionImpl, MediaItemsWithStartPositionPlayerTask mediaItemsWithStartPositionPlayerTask, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
        if (!mediaSessionImpl.isReleased()) {
            mediaItemsWithStartPositionPlayerTask.run(mediaSessionImpl.getPlayerWrapper(), mediaItemsWithStartPosition);
        }
    }

    private static void sendLibraryResult(MediaSession.ControllerInfo controllerInfo, int i, LibraryResult<?> libraryResult) {
        try {
            ((MediaSession.ControllerCb) Assertions.checkStateNotNull(controllerInfo.getControllerCb())).onLibraryResult(i, libraryResult);
        } catch (RemoteException e) {
            Log.w(TAG, "Failed to send result to browser " + controllerInfo, e);
        }
    }

    private static <V, K extends MediaLibrarySessionImpl> SessionTask<ListenableFuture<Void>, K> sendLibraryResultWhenReady(SessionTask<ListenableFuture<LibraryResult<V>>, K> sessionTask) {
        return new MediaSessionStub$$ExternalSyntheticLambda80(sessionTask);
    }

    static /* synthetic */ void lambda$sendLibraryResultWhenReady$10(MediaSession.ControllerInfo controllerInfo, int i, ListenableFuture listenableFuture) {
        LibraryResult libraryResult;
        try {
            libraryResult = (LibraryResult) Assertions.checkNotNull((LibraryResult) listenableFuture.get(), "LibraryResult must not be null");
        } catch (CancellationException e) {
            Log.w(TAG, "Library operation cancelled", e);
            libraryResult = LibraryResult.ofError(1);
        } catch (InterruptedException | ExecutionException e2) {
            Log.w(TAG, "Library operation failed", e2);
            libraryResult = LibraryResult.ofError(-1);
        }
        sendLibraryResult(controllerInfo, i, libraryResult);
    }

    private <K extends MediaSessionImpl> void queueSessionTaskWithPlayerCommand(IMediaController iMediaController, int i, int i2, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
        if (controller != null) {
            queueSessionTaskWithPlayerCommandForControllerInfo(controller, i, i2, sessionTask);
        }
    }

    private <K extends MediaSessionImpl> void queueSessionTaskWithPlayerCommandForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i, int i2, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.sessionImpl.get();
            if (mediaSessionImpl != null) {
                if (!mediaSessionImpl.isReleased()) {
                    Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new MediaSessionStub$$ExternalSyntheticLambda0(this, controllerInfo, i2, i, mediaSessionImpl, sessionTask));
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$queueSessionTaskWithPlayerCommandForControllerInfo$14$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1108lambda$queueSessionTaskWithPlayerCommandForControllerInfo$14$androidxmedia3sessionMediaSessionStub(MediaSession.ControllerInfo controllerInfo, int i, int i2, MediaSessionImpl mediaSessionImpl, SessionTask sessionTask) {
        if (!this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, i)) {
            sendSessionResult(controllerInfo, i2, new SessionResult(-4));
            return;
        }
        int onPlayerCommandRequestOnHandler = mediaSessionImpl.onPlayerCommandRequestOnHandler(controllerInfo, i);
        if (onPlayerCommandRequestOnHandler != 0) {
            sendSessionResult(controllerInfo, i2, new SessionResult(onPlayerCommandRequestOnHandler));
        } else if (i == 27) {
            mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new MediaSessionStub$$ExternalSyntheticLambda54(sessionTask, mediaSessionImpl, controllerInfo, i2)).run();
            this.connectedControllersManager.addToCommandQueue(controllerInfo, i, new MediaSessionStub$$ExternalSyntheticLambda56());
        } else {
            this.connectedControllersManager.addToCommandQueue(controllerInfo, i, new MediaSessionStub$$ExternalSyntheticLambda57(sessionTask, mediaSessionImpl, controllerInfo, i2));
        }
    }

    static /* synthetic */ ListenableFuture lambda$queueSessionTaskWithPlayerCommandForControllerInfo$13(SessionTask sessionTask, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        return (ListenableFuture) sessionTask.run(mediaSessionImpl, controllerInfo, i);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, int i, int i2, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        dispatchSessionTaskWithSessionCommand(iMediaController, i, (SessionCommand) null, i2, sessionTask);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, int i, SessionCommand sessionCommand, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        dispatchSessionTaskWithSessionCommand(iMediaController, i, sessionCommand, 0, sessionTask);
    }

    private <K extends MediaSessionImpl> void dispatchSessionTaskWithSessionCommand(IMediaController iMediaController, int i, SessionCommand sessionCommand, int i2, SessionTask<ListenableFuture<Void>, K> sessionTask) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.sessionImpl.get();
            if (mediaSessionImpl != null) {
                if (!mediaSessionImpl.isReleased()) {
                    MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
                    if (controller == null) {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                        return;
                    }
                    Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new MediaSessionStub$$ExternalSyntheticLambda70(this, controller, sessionCommand, i, i2, sessionTask, mediaSessionImpl));
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dispatchSessionTaskWithSessionCommand$15$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1105lambda$dispatchSessionTaskWithSessionCommand$15$androidxmedia3sessionMediaSessionStub(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, int i, int i2, SessionTask sessionTask, MediaSessionImpl mediaSessionImpl) {
        if (this.connectedControllersManager.isConnected(controllerInfo)) {
            if (sessionCommand != null) {
                if (!this.connectedControllersManager.isSessionCommandAvailable(controllerInfo, sessionCommand)) {
                    sendSessionResult(controllerInfo, i, new SessionResult(-4));
                    return;
                }
            } else if (!this.connectedControllersManager.isSessionCommandAvailable(controllerInfo, i2)) {
                sendSessionResult(controllerInfo, i, new SessionResult(-4));
                return;
            }
            sessionTask.run(mediaSessionImpl, controllerInfo, i);
        }
    }

    /* access modifiers changed from: private */
    public static <T, K extends MediaSessionImpl> ListenableFuture<Void> handleSessionTaskWhenReady(K k, MediaSession.ControllerInfo controllerInfo, int i, SessionTask<ListenableFuture<T>, K> sessionTask, Consumer<ListenableFuture<T>> consumer) {
        if (k.isReleased()) {
            return Futures.immediateVoidFuture();
        }
        ListenableFuture run = sessionTask.run(k, controllerInfo, i);
        SettableFuture create = SettableFuture.create();
        run.addListener(new MediaSessionStub$$ExternalSyntheticLambda40(k, create, consumer, run), MoreExecutors.directExecutor());
        return create;
    }

    static /* synthetic */ void lambda$handleSessionTaskWhenReady$16(MediaSessionImpl mediaSessionImpl, SettableFuture settableFuture, Consumer consumer, ListenableFuture listenableFuture) {
        if (mediaSessionImpl.isReleased()) {
            settableFuture.set(null);
            return;
        }
        try {
            consumer.accept(listenableFuture);
            settableFuture.set(null);
        } catch (Throwable th) {
            settableFuture.setException(th);
        }
    }

    private int maybeCorrectMediaItemIndex(MediaSession.ControllerInfo controllerInfo, PlayerWrapper playerWrapper, int i) {
        return (!playerWrapper.isCommandAvailable(17) || this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 17) || !this.connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 16)) ? i : i + playerWrapper.getCurrentMediaItemIndex();
    }

    public void connect(IMediaController iMediaController, MediaSession.ControllerInfo controllerInfo) {
        if (iMediaController != null && controllerInfo != null) {
            MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.sessionImpl.get();
            if (mediaSessionImpl == null || mediaSessionImpl.isReleased()) {
                try {
                    iMediaController.onDisconnected(0);
                } catch (RemoteException unused) {
                }
            } else {
                this.pendingControllers.add(controllerInfo);
                Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new MediaSessionStub$$ExternalSyntheticLambda69(this, controllerInfo, mediaSessionImpl, iMediaController));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0144 A[SYNTHETIC, Splitter:B:81:0x0144] */
    /* renamed from: lambda$connect$17$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void m1104lambda$connect$17$androidxmedia3sessionMediaSessionStub(androidx.media3.session.MediaSession.ControllerInfo r24, androidx.media3.session.MediaSessionImpl r25, androidx.media3.session.IMediaController r26) {
        /*
            r23 = this;
            r15 = r23
            r0 = r24
            r14 = r25
            r13 = r26
            java.lang.String r1 = "Controller "
            r12 = 0
            java.util.Set<androidx.media3.session.MediaSession$ControllerInfo> r2 = r15.pendingControllers     // Catch:{ all -> 0x013f }
            r2.remove(r0)     // Catch:{ all -> 0x013f }
            boolean r2 = r25.isReleased()     // Catch:{ all -> 0x013f }
            if (r2 == 0) goto L_0x001a
            r13.onDisconnected(r12)     // Catch:{ RemoteException -> 0x0019 }
        L_0x0019:
            return
        L_0x001a:
            androidx.media3.session.MediaSession$ControllerCb r2 = r24.getControllerCb()     // Catch:{ all -> 0x013f }
            androidx.media3.session.MediaSessionStub$Controller2Cb r2 = (androidx.media3.session.MediaSessionStub.Controller2Cb) r2     // Catch:{ all -> 0x013f }
            java.lang.Object r2 = androidx.media3.common.util.Assertions.checkStateNotNull(r2)     // Catch:{ all -> 0x013f }
            androidx.media3.session.MediaSessionStub$Controller2Cb r2 = (androidx.media3.session.MediaSessionStub.Controller2Cb) r2     // Catch:{ all -> 0x013f }
            android.os.IBinder r2 = r2.getCallbackBinder()     // Catch:{ all -> 0x013f }
            androidx.media3.session.MediaSession$ConnectionResult r3 = r14.onConnectOnHandler(r0)     // Catch:{ all -> 0x013f }
            boolean r4 = r3.isAccepted     // Catch:{ all -> 0x013f }
            if (r4 != 0) goto L_0x003c
            boolean r4 = r24.isTrusted()     // Catch:{ all -> 0x013f }
            if (r4 != 0) goto L_0x003c
            r13.onDisconnected(r12)     // Catch:{ RemoteException -> 0x003b }
        L_0x003b:
            return
        L_0x003c:
            boolean r4 = r3.isAccepted     // Catch:{ all -> 0x013f }
            if (r4 != 0) goto L_0x0048
            androidx.media3.session.SessionCommands r3 = androidx.media3.session.SessionCommands.EMPTY     // Catch:{ all -> 0x013f }
            androidx.media3.common.Player$Commands r4 = androidx.media3.common.Player.Commands.EMPTY     // Catch:{ all -> 0x013f }
            androidx.media3.session.MediaSession$ConnectionResult r3 = androidx.media3.session.MediaSession.ConnectionResult.accept(r3, r4)     // Catch:{ all -> 0x013f }
        L_0x0048:
            androidx.media3.session.ConnectedControllersManager<android.os.IBinder> r4 = r15.connectedControllersManager     // Catch:{ all -> 0x013f }
            boolean r4 = r4.isConnected(r0)     // Catch:{ all -> 0x013f }
            java.lang.String r5 = "MediaSessionStub"
            if (r4 == 0) goto L_0x0068
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x013f }
            r4.<init>(r1)     // Catch:{ all -> 0x013f }
            java.lang.StringBuilder r1 = r4.append(r0)     // Catch:{ all -> 0x013f }
            java.lang.String r4 = " has sent connection request multiple times"
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ all -> 0x013f }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x013f }
            androidx.media3.common.util.Log.w(r5, r1)     // Catch:{ all -> 0x013f }
        L_0x0068:
            androidx.media3.session.ConnectedControllersManager<android.os.IBinder> r1 = r15.connectedControllersManager     // Catch:{ all -> 0x013f }
            androidx.media3.session.SessionCommands r4 = r3.availableSessionCommands     // Catch:{ all -> 0x013f }
            androidx.media3.common.Player$Commands r6 = r3.availablePlayerCommands     // Catch:{ all -> 0x013f }
            r1.addController(r2, r0, r4, r6)     // Catch:{ all -> 0x013f }
            androidx.media3.session.ConnectedControllersManager<android.os.IBinder> r1 = r15.connectedControllersManager     // Catch:{ all -> 0x013f }
            androidx.media3.session.SequencedFutureManager r16 = r1.getSequencedFutureManager((androidx.media3.session.MediaSession.ControllerInfo) r0)     // Catch:{ all -> 0x013f }
            if (r16 != 0) goto L_0x0082
            java.lang.String r0 = "Ignoring connection request from unknown controller info"
            androidx.media3.common.util.Log.w(r5, r0)     // Catch:{ all -> 0x013f }
            r13.onDisconnected(r12)     // Catch:{ RemoteException -> 0x0081 }
        L_0x0081:
            return
        L_0x0082:
            androidx.media3.session.PlayerWrapper r1 = r25.getPlayerWrapper()     // Catch:{ all -> 0x013f }
            androidx.media3.session.PlayerInfo r2 = r1.createPlayerInfoForBundling()     // Catch:{ all -> 0x013f }
            androidx.media3.session.PlayerInfo r17 = r15.generateAndCacheUniqueTrackGroupIds(r2)     // Catch:{ all -> 0x013f }
            androidx.media3.session.legacy.MediaSessionCompat r2 = r25.getSessionCompat()     // Catch:{ all -> 0x013f }
            androidx.media3.session.legacy.MediaSessionCompat$Token r2 = r2.getSessionToken()     // Catch:{ all -> 0x013f }
            java.lang.Object r2 = r2.getToken()     // Catch:{ all -> 0x013f }
            r18 = r2
            android.media.session.MediaSession$Token r18 = (android.media.session.MediaSession.Token) r18     // Catch:{ all -> 0x013f }
            androidx.media3.session.ConnectionState r11 = new androidx.media3.session.ConnectionState     // Catch:{ all -> 0x013f }
            r2 = 1005001300(0x3be71a54, float:0.007052699)
            r4 = 4
            android.app.PendingIntent r5 = r3.sessionActivity     // Catch:{ all -> 0x013f }
            if (r5 == 0) goto L_0x00ab
            android.app.PendingIntent r5 = r3.sessionActivity     // Catch:{ all -> 0x013f }
            goto L_0x00af
        L_0x00ab:
            android.app.PendingIntent r5 = r25.getSessionActivity()     // Catch:{ all -> 0x013f }
        L_0x00af:
            com.google.common.collect.ImmutableList<androidx.media3.session.CommandButton> r6 = r3.customLayout     // Catch:{ all -> 0x013f }
            if (r6 == 0) goto L_0x00b6
            com.google.common.collect.ImmutableList<androidx.media3.session.CommandButton> r6 = r3.customLayout     // Catch:{ all -> 0x013f }
            goto L_0x00ba
        L_0x00b6:
            com.google.common.collect.ImmutableList r6 = r25.getCustomLayout()     // Catch:{ all -> 0x013f }
        L_0x00ba:
            com.google.common.collect.ImmutableList<androidx.media3.session.CommandButton> r7 = r3.mediaButtonPreferences     // Catch:{ all -> 0x013f }
            if (r7 == 0) goto L_0x00c1
            com.google.common.collect.ImmutableList<androidx.media3.session.CommandButton> r7 = r3.mediaButtonPreferences     // Catch:{ all -> 0x013f }
            goto L_0x00c5
        L_0x00c1:
            com.google.common.collect.ImmutableList r7 = r25.getMediaButtonPreferences()     // Catch:{ all -> 0x013f }
        L_0x00c5:
            com.google.common.collect.ImmutableList r8 = r25.getCommandButtonsForMediaItems()     // Catch:{ all -> 0x013f }
            androidx.media3.session.SessionCommands r9 = r3.availableSessionCommands     // Catch:{ all -> 0x013f }
            androidx.media3.common.Player$Commands r10 = r3.availablePlayerCommands     // Catch:{ all -> 0x013f }
            androidx.media3.common.Player$Commands r19 = r1.getAvailableCommands()     // Catch:{ all -> 0x013f }
            androidx.media3.session.SessionToken r1 = r25.getToken()     // Catch:{ all -> 0x013f }
            android.os.Bundle r20 = r1.getExtras()     // Catch:{ all -> 0x013f }
            android.os.Bundle r1 = r3.sessionExtras     // Catch:{ all -> 0x013f }
            if (r1 == 0) goto L_0x00e0
            android.os.Bundle r1 = r3.sessionExtras     // Catch:{ all -> 0x013f }
            goto L_0x00e4
        L_0x00e0:
            android.os.Bundle r1 = r25.getSessionExtras()     // Catch:{ all -> 0x013f }
        L_0x00e4:
            r21 = r1
            r1 = r11
            r3 = r4
            r4 = r23
            r22 = r11
            r11 = r19
            r12 = r20
            r13 = r21
            r14 = r17
            r15 = r18
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ all -> 0x0139 }
            boolean r1 = r25.isReleased()     // Catch:{ all -> 0x0139 }
            if (r1 == 0) goto L_0x0106
            r1 = r26
            r2 = 0
            r1.onDisconnected(r2)     // Catch:{ RemoteException -> 0x0105 }
        L_0x0105:
            return
        L_0x0106:
            r1 = r26
            r2 = 0
            int r3 = r16.obtainNextSequenceNumber()     // Catch:{ RemoteException -> 0x0128, all -> 0x0126 }
            boolean r4 = r1 instanceof androidx.media3.session.MediaControllerStub     // Catch:{ RemoteException -> 0x0128, all -> 0x0126 }
            if (r4 == 0) goto L_0x0116
            android.os.Bundle r4 = r22.toBundleInProcess()     // Catch:{ RemoteException -> 0x0128, all -> 0x0126 }
            goto L_0x0120
        L_0x0116:
            int r4 = r24.getInterfaceVersion()     // Catch:{ RemoteException -> 0x0128, all -> 0x0126 }
            r5 = r22
            android.os.Bundle r4 = r5.toBundleForRemoteProcess(r4)     // Catch:{ RemoteException -> 0x0128, all -> 0x0126 }
        L_0x0120:
            r1.onConnected(r3, r4)     // Catch:{ RemoteException -> 0x0128, all -> 0x0126 }
            r3 = 1
            r12 = r3
            goto L_0x0129
        L_0x0126:
            r0 = move-exception
            goto L_0x013d
        L_0x0128:
            r12 = r2
        L_0x0129:
            if (r12 == 0) goto L_0x0133
            r3 = r25
            r3.onPostConnectOnHandler(r0)     // Catch:{ all -> 0x0131 }
            goto L_0x0133
        L_0x0131:
            r0 = move-exception
            goto L_0x0142
        L_0x0133:
            if (r12 != 0) goto L_0x0138
            r1.onDisconnected(r2)     // Catch:{ RemoteException -> 0x0138 }
        L_0x0138:
            return
        L_0x0139:
            r0 = move-exception
            r1 = r26
            r2 = 0
        L_0x013d:
            r12 = r2
            goto L_0x0142
        L_0x013f:
            r0 = move-exception
            r2 = r12
            r1 = r13
        L_0x0142:
            if (r12 != 0) goto L_0x0147
            r1.onDisconnected(r2)     // Catch:{ RemoteException -> 0x0147 }
        L_0x0147:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaSessionStub.m1104lambda$connect$17$androidxmedia3sessionMediaSessionStub(androidx.media3.session.MediaSession$ControllerInfo, androidx.media3.session.MediaSessionImpl, androidx.media3.session.IMediaController):void");
    }

    public void release() {
        for (MediaSession.ControllerInfo controllerCb : this.connectedControllersManager.getConnectedControllers()) {
            MediaSession.ControllerCb controllerCb2 = controllerCb.getControllerCb();
            if (controllerCb2 != null) {
                try {
                    controllerCb2.onDisconnected(0);
                } catch (RemoteException unused) {
                }
            }
        }
        for (MediaSession.ControllerInfo controllerCb3 : this.pendingControllers) {
            MediaSession.ControllerCb controllerCb4 = controllerCb3.getControllerCb();
            if (controllerCb4 != null) {
                try {
                    controllerCb4.onDisconnected(0);
                } catch (RemoteException unused2) {
                }
            }
        }
    }

    public void connect(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController != null && bundle != null) {
            try {
                ConnectionRequest fromBundle = ConnectionRequest.fromBundle(bundle);
                int callingUid = Binder.getCallingUid();
                int callingPid = Binder.getCallingPid();
                long clearCallingIdentity = Binder.clearCallingIdentity();
                if (callingPid == 0) {
                    callingPid = fromBundle.pid;
                }
                try {
                    MediaSessionManager.RemoteUserInfo remoteUserInfo = new MediaSessionManager.RemoteUserInfo(fromBundle.packageName, callingPid, callingUid);
                    connect(iMediaController, new MediaSession.ControllerInfo(remoteUserInfo, fromBundle.libraryVersion, fromBundle.controllerInterfaceVersion, this.sessionManager.isTrustedForMediaControl(remoteUserInfo), new Controller2Cb(iMediaController, fromBundle.controllerInterfaceVersion), fromBundle.connectionHints, fromBundle.maxCommandsForMediaItems));
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for ConnectionRequest", e);
            }
        }
    }

    public void stop(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController != null && (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) != null) {
            stopForControllerInfo(controller, i);
        }
    }

    public void stopForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 3, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda72()));
    }

    public void release(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.sessionImpl.get();
                if (mediaSessionImpl != null) {
                    if (!mediaSessionImpl.isReleased()) {
                        Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new MediaSessionStub$$ExternalSyntheticLambda6(this, iMediaController));
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$18$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1109lambda$release$18$androidxmedia3sessionMediaSessionStub(IMediaController iMediaController) {
        this.connectedControllersManager.removeController(iMediaController.asBinder());
    }

    public void onControllerResult(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController != null && bundle != null) {
            try {
                SessionResult fromBundle = SessionResult.fromBundle(bundle);
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    SequencedFutureManager sequencedFutureManager = this.connectedControllersManager.getSequencedFutureManager(iMediaController.asBinder());
                    if (sequencedFutureManager != null) {
                        sequencedFutureManager.setFutureResult(i, fromBundle);
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                    }
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for SessionResult", e);
            }
        }
    }

    public void play(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController != null && (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) != null) {
            playForControllerInfo(controller, i);
        }
    }

    public void playForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 1, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda29(this, controllerInfo)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$playForControllerInfo$19$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1107lambda$playForControllerInfo$19$androidxmedia3sessionMediaSessionStub(MediaSession.ControllerInfo controllerInfo, PlayerWrapper playerWrapper) {
        MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.sessionImpl.get();
        if (mediaSessionImpl != null && !mediaSessionImpl.isReleased()) {
            mediaSessionImpl.handleMediaControllerPlayRequest(controllerInfo, false);
        }
    }

    public void pause(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController != null && (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) != null) {
            pauseForControllerInfo(controller, i);
        }
    }

    public void pauseForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 1, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda42()));
    }

    public void prepare(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 2, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda63()));
        }
    }

    public void seekToDefaultPosition(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 4, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda36()));
        }
    }

    public void seekToDefaultPositionWithMediaItemIndex(IMediaController iMediaController, int i, int i2) {
        if (iMediaController != null && i2 >= 0) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 10, sendSessionResultSuccess((ControllerPlayerTask) new MediaSessionStub$$ExternalSyntheticLambda8(this, i2)));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekToDefaultPositionWithMediaItemIndex$21$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1114lambda$seekToDefaultPositionWithMediaItemIndex$21$androidxmedia3sessionMediaSessionStub(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.seekToDefaultPosition(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i));
    }

    public void seekTo(IMediaController iMediaController, int i, long j) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 5, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda10(j)));
        }
    }

    public void seekToWithMediaItemIndex(IMediaController iMediaController, int i, int i2, long j) {
        if (iMediaController != null && i2 >= 0) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 10, sendSessionResultSuccess((ControllerPlayerTask) new MediaSessionStub$$ExternalSyntheticLambda61(this, i2, j)));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$seekToWithMediaItemIndex$23$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1115lambda$seekToWithMediaItemIndex$23$androidxmedia3sessionMediaSessionStub(int i, long j, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.seekTo(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), j);
    }

    public void seekBack(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController != null && (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) != null) {
            seekBackForControllerInfo(controller, i);
        }
    }

    public void seekBackForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 11, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda30()));
    }

    public void seekForward(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController != null && (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) != null) {
            seekForwardForControllerInfo(controller, i);
        }
    }

    public void seekForwardForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 12, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda14()));
    }

    public void onCustomCommand(IMediaController iMediaController, int i, Bundle bundle, Bundle bundle2) {
        if (iMediaController != null && bundle != null && bundle2 != null) {
            try {
                SessionCommand fromBundle = SessionCommand.fromBundle(bundle);
                dispatchSessionTaskWithSessionCommand(iMediaController, i, fromBundle, sendSessionResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda21(fromBundle, bundle2)));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for SessionCommand", e);
            }
        }
    }

    public void setRatingWithMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) {
        if (iMediaController != null && str != null && bundle != null) {
            if (TextUtils.isEmpty(str)) {
                Log.w(TAG, "setRatingWithMediaId(): Ignoring empty mediaId");
                return;
            }
            try {
                dispatchSessionTaskWithSessionCommand(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_SET_RATING, sendSessionResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda22(str, Rating.fromBundle(bundle))));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for Rating", e);
            }
        }
    }

    public void setRating(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController != null && bundle != null) {
            try {
                dispatchSessionTaskWithSessionCommand(iMediaController, i, (int) SessionCommand.COMMAND_CODE_SESSION_SET_RATING, sendSessionResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda12(Rating.fromBundle(bundle))));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for Rating", e);
            }
        }
    }

    public void setPlaybackSpeed(IMediaController iMediaController, int i, float f) {
        if (iMediaController != null && f > 0.0f) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 13, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda53(f)));
        }
    }

    public void setPlaybackParameters(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController != null && bundle != null) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 13, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda16(PlaybackParameters.fromBundle(bundle))));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for PlaybackParameters", e);
            }
        }
    }

    public void setMediaItem(IMediaController iMediaController, int i, Bundle bundle) {
        setMediaItemWithResetPosition(iMediaController, i, bundle, true);
    }

    public void setMediaItemWithStartPosition(IMediaController iMediaController, int i, Bundle bundle, long j) {
        if (iMediaController != null && bundle != null) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 31, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new MediaSessionStub$$ExternalSyntheticLambda62(MediaItem.fromBundle(bundle), j), new MediaSessionStub$$ExternalSyntheticLambda3())));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    public void setMediaItemWithResetPosition(IMediaController iMediaController, int i, Bundle bundle, boolean z) {
        if (iMediaController != null && bundle != null) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 31, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new MediaSessionStub$$ExternalSyntheticLambda2(MediaItem.fromBundle(bundle), z), new MediaSessionStub$$ExternalSyntheticLambda3())));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    static /* synthetic */ ListenableFuture lambda$setMediaItemWithResetPosition$30(MediaItem mediaItem, boolean z, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        int i2;
        long j;
        ImmutableList of = ImmutableList.of(mediaItem);
        if (z) {
            i2 = -1;
        } else {
            i2 = mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex();
        }
        int i3 = i2;
        if (z) {
            j = C.TIME_UNSET;
        } else {
            j = mediaSessionImpl.getPlayerWrapper().getCurrentPosition();
        }
        return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, of, i3, j);
    }

    public void setMediaItems(IMediaController iMediaController, int i, IBinder iBinder) {
        setMediaItemsWithResetPosition(iMediaController, i, iBinder, true);
    }

    public void setMediaItemsWithResetPosition(IMediaController iMediaController, int i, IBinder iBinder, boolean z) {
        if (iMediaController != null && iBinder != null) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new MediaSessionStub$$ExternalSyntheticLambda9(BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda66(), BundleListRetriever.getList(iBinder)), z), new MediaSessionStub$$ExternalSyntheticLambda3())));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    static /* synthetic */ ListenableFuture lambda$setMediaItemsWithResetPosition$31(List list, boolean z, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i) {
        long j;
        int currentMediaItemIndex = z ? -1 : mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex();
        if (z) {
            j = C.TIME_UNSET;
        } else {
            j = mediaSessionImpl.getPlayerWrapper().getCurrentPosition();
        }
        return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, list, currentMediaItemIndex, j);
    }

    public void setMediaItemsWithStartIndex(IMediaController iMediaController, int i, IBinder iBinder, int i2, long j) {
        if (iMediaController != null && iBinder != null) {
            if (i2 == -1 || i2 >= 0) {
                try {
                    queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWithStartPositionWhenReady(new MediaSessionStub$$ExternalSyntheticLambda71(BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda66(), BundleListRetriever.getList(iBinder)), i2, j), new MediaSessionStub$$ExternalSyntheticLambda3())));
                } catch (RuntimeException e) {
                    Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
                }
            }
        }
    }

    static /* synthetic */ ListenableFuture lambda$setMediaItemsWithStartIndex$32(List list, int i, long j, MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i2) {
        int currentMediaItemIndex = i == -1 ? mediaSessionImpl.getPlayerWrapper().getCurrentMediaItemIndex() : i;
        if (i == -1) {
            j = mediaSessionImpl.getPlayerWrapper().getCurrentPosition();
        }
        return mediaSessionImpl.onSetMediaItemsOnHandler(controllerInfo, list, currentMediaItemIndex, j);
    }

    public void setPlaylistMetadata(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController != null && bundle != null) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 19, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda37(MediaMetadata.fromBundle(bundle))));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaMetadata", e);
            }
        }
    }

    public void addMediaItem(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController != null && bundle != null) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda67(MediaItem.fromBundle(bundle)), new MediaSessionStub$$ExternalSyntheticLambda68())));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    public void addMediaItemWithIndex(IMediaController iMediaController, int i, int i2, Bundle bundle) {
        if (iMediaController != null && bundle != null && i2 >= 0) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda74(MediaItem.fromBundle(bundle)), new MediaSessionStub$$ExternalSyntheticLambda75(this, i2))));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addMediaItemWithIndex$37$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1102lambda$addMediaItemWithIndex$37$androidxmedia3sessionMediaSessionStub(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.addMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), list);
    }

    public void addMediaItems(IMediaController iMediaController, int i, IBinder iBinder) {
        if (iMediaController != null && iBinder != null) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda85(BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda66(), BundleListRetriever.getList(iBinder))), new MediaSessionStub$$ExternalSyntheticLambda1())));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    public void addMediaItemsWithIndex(IMediaController iMediaController, int i, int i2, IBinder iBinder) {
        if (iMediaController != null && iBinder != null && i2 >= 0) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda49(BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda66(), BundleListRetriever.getList(iBinder))), new MediaSessionStub$$ExternalSyntheticLambda50(this, i2))));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addMediaItemsWithIndex$41$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1103lambda$addMediaItemsWithIndex$41$androidxmedia3sessionMediaSessionStub(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.addMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), list);
    }

    public void removeMediaItem(IMediaController iMediaController, int i, int i2) {
        if (iMediaController != null && i2 >= 0) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((ControllerPlayerTask) new MediaSessionStub$$ExternalSyntheticLambda58(this, i2)));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeMediaItem$42$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1110lambda$removeMediaItem$42$androidxmedia3sessionMediaSessionStub(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.removeMediaItem(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i));
    }

    public void removeMediaItems(IMediaController iMediaController, int i, int i2, int i3) {
        if (iMediaController != null && i2 >= 0 && i3 >= i2) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((ControllerPlayerTask) new MediaSessionStub$$ExternalSyntheticLambda82(this, i2, i3)));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$removeMediaItems$43$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1111lambda$removeMediaItems$43$androidxmedia3sessionMediaSessionStub(int i, int i2, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        playerWrapper.removeMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i2));
    }

    public void clearMediaItems(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda5()));
        }
    }

    public void moveMediaItem(IMediaController iMediaController, int i, int i2, int i3) {
        if (iMediaController != null && i2 >= 0 && i3 >= 0) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda78(i2, i3)));
        }
    }

    public void moveMediaItems(IMediaController iMediaController, int i, int i2, int i3, int i4) {
        if (iMediaController != null && i2 >= 0 && i3 >= i2 && i4 >= 0) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda20(i2, i3, i4)));
        }
    }

    public void replaceMediaItem(IMediaController iMediaController, int i, int i2, Bundle bundle) {
        if (iMediaController != null && bundle != null && i2 >= 0) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda43(MediaItem.fromBundle(bundle)), new MediaSessionStub$$ExternalSyntheticLambda45(this, i2))));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$replaceMediaItem$47$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1112lambda$replaceMediaItem$47$androidxmedia3sessionMediaSessionStub(int i, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        if (list.size() == 1) {
            playerWrapper.replaceMediaItem(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), (MediaItem) list.get(0));
        } else {
            playerWrapper.replaceMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i + 1), list);
        }
    }

    public void replaceMediaItems(IMediaController iMediaController, int i, int i2, int i3, IBinder iBinder) {
        if (iMediaController != null && iBinder != null && i2 >= 0 && i3 >= i2) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 20, sendSessionResultWhenReady(handleMediaItemsWhenReady(new MediaSessionStub$$ExternalSyntheticLambda77(BundleCollectionUtil.fromBundleList(new MediaSessionStub$$ExternalSyntheticLambda66(), BundleListRetriever.getList(iBinder))), new MediaSessionStub$$ExternalSyntheticLambda84(this, i2, i3))));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for MediaItem", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$replaceMediaItems$49$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1113lambda$replaceMediaItems$49$androidxmedia3sessionMediaSessionStub(int i, int i2, PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo, List list) {
        playerWrapper.replaceMediaItems(maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i), maybeCorrectMediaItemIndex(controllerInfo, playerWrapper, i2), list);
    }

    public void seekToPreviousMediaItem(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 6, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda26()));
        }
    }

    public void seekToNextMediaItem(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 8, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda55()));
        }
    }

    public void seekToPrevious(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController != null && (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) != null) {
            seekToPreviousForControllerInfo(controller, i);
        }
    }

    public void seekToPreviousForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 7, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda81()));
    }

    public void seekToNext(IMediaController iMediaController, int i) {
        MediaSession.ControllerInfo controller;
        if (iMediaController != null && (controller = this.connectedControllersManager.getController(iMediaController.asBinder())) != null) {
            seekToNextForControllerInfo(controller, i);
        }
    }

    public void seekToNextForControllerInfo(MediaSession.ControllerInfo controllerInfo, int i) {
        queueSessionTaskWithPlayerCommandForControllerInfo(controllerInfo, i, 9, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda46()));
    }

    public void setRepeatMode(IMediaController iMediaController, int i, int i2) {
        if (iMediaController != null) {
            if (i2 == 2 || i2 == 0 || i2 == 1) {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 15, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda31(i2)));
            }
        }
    }

    public void setShuffleModeEnabled(IMediaController iMediaController, int i, boolean z) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 14, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda4(z)));
        }
    }

    public void setVideoSurface(IMediaController iMediaController, int i, Surface surface) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 27, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda28(surface)));
        }
    }

    public void setVolume(IMediaController iMediaController, int i, float f) {
        if (iMediaController != null && f >= 0.0f && f <= 1.0f) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 24, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda38(f)));
        }
    }

    public void setDeviceVolume(IMediaController iMediaController, int i, int i2) {
        if (iMediaController != null && i2 >= 0) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 25, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda23(i2)));
        }
    }

    public void setDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2, int i3) {
        if (iMediaController != null && i2 >= 0) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 33, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda44(i2, i3)));
        }
    }

    public void increaseDeviceVolume(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 26, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda24()));
        }
    }

    public void increaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 34, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda19(i2)));
        }
    }

    public void decreaseDeviceVolume(IMediaController iMediaController, int i) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 26, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda15()));
        }
    }

    public void decreaseDeviceVolumeWithFlags(IMediaController iMediaController, int i, int i2) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 34, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda34(i2)));
        }
    }

    public void setDeviceMuted(IMediaController iMediaController, int i, boolean z) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 26, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda65(z)));
        }
    }

    public void setDeviceMutedWithFlags(IMediaController iMediaController, int i, boolean z, int i2) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 34, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda27(z, i2)));
        }
    }

    public void setAudioAttributes(IMediaController iMediaController, int i, Bundle bundle, boolean z) {
        if (iMediaController != null && bundle != null) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 35, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda60(AudioAttributes.fromBundle(bundle), z)));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for AudioAttributes", e);
            }
        }
    }

    public void setPlayWhenReady(IMediaController iMediaController, int i, boolean z) {
        if (iMediaController != null) {
            queueSessionTaskWithPlayerCommand(iMediaController, i, 1, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda17(z)));
        }
    }

    public void flushCommandQueue(IMediaController iMediaController) {
        if (iMediaController != null) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                MediaSessionImpl mediaSessionImpl = (MediaSessionImpl) this.sessionImpl.get();
                if (mediaSessionImpl != null) {
                    if (!mediaSessionImpl.isReleased()) {
                        MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(iMediaController.asBinder());
                        if (controller != null) {
                            Util.postOrRun(mediaSessionImpl.getApplicationHandler(), new MediaSessionStub$$ExternalSyntheticLambda41(this, controller));
                        }
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$flushCommandQueue$64$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1106lambda$flushCommandQueue$64$androidxmedia3sessionMediaSessionStub(MediaSession.ControllerInfo controllerInfo) {
        this.connectedControllersManager.flushCommandQueue(controllerInfo);
    }

    public void setTrackSelectionParameters(IMediaController iMediaController, int i, Bundle bundle) {
        if (iMediaController != null && bundle != null) {
            try {
                queueSessionTaskWithPlayerCommand(iMediaController, i, 29, sendSessionResultSuccess((Consumer<PlayerWrapper>) new MediaSessionStub$$ExternalSyntheticLambda7(this, TrackSelectionParameters.fromBundle(bundle))));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for TrackSelectionParameters", e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setTrackSelectionParameters$65$androidx-media3-session-MediaSessionStub  reason: not valid java name */
    public /* synthetic */ void m1116lambda$setTrackSelectionParameters$65$androidxmedia3sessionMediaSessionStub(TrackSelectionParameters trackSelectionParameters, PlayerWrapper playerWrapper) {
        playerWrapper.setTrackSelectionParameters(updateOverridesUsingUniqueTrackGroupIds(trackSelectionParameters));
    }

    public void getLibraryRoot(IMediaController iMediaController, int i, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParams;
        if (iMediaController != null) {
            if (bundle == null) {
                libraryParams = null;
            } else {
                try {
                    libraryParams = MediaLibraryService.LibraryParams.fromBundle(bundle);
                } catch (RuntimeException e) {
                    Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                    return;
                }
            }
            dispatchSessionTaskWithSessionCommand(iMediaController, i, 50000, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda83(libraryParams)));
        }
    }

    public void getItem(IMediaController iMediaController, int i, String str) {
        if (iMediaController != null) {
            if (TextUtils.isEmpty(str)) {
                Log.w(TAG, "getItem(): Ignoring empty mediaId");
            } else {
                dispatchSessionTaskWithSessionCommand(iMediaController, i, (int) SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda64(str)));
            }
        }
    }

    public void getChildren(IMediaController iMediaController, int i, String str, int i2, int i3, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParams;
        if (iMediaController != null) {
            if (TextUtils.isEmpty(str)) {
                Log.w(TAG, "getChildren(): Ignoring empty parentId");
            } else if (i2 < 0) {
                Log.w(TAG, "getChildren(): Ignoring negative page");
            } else if (i3 < 1) {
                Log.w(TAG, "getChildren(): Ignoring pageSize less than 1");
            } else {
                if (bundle == null) {
                    libraryParams = null;
                } else {
                    try {
                        libraryParams = MediaLibraryService.LibraryParams.fromBundle(bundle);
                    } catch (RuntimeException e) {
                        Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                        return;
                    }
                }
                dispatchSessionTaskWithSessionCommand(iMediaController, i, (int) SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda51(str, i2, i3, libraryParams)));
            }
        }
    }

    public void search(IMediaController iMediaController, int i, String str, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParams;
        if (iMediaController != null) {
            if (TextUtils.isEmpty(str)) {
                Log.w(TAG, "search(): Ignoring empty query");
                return;
            }
            if (bundle == null) {
                libraryParams = null;
            } else {
                try {
                    libraryParams = MediaLibraryService.LibraryParams.fromBundle(bundle);
                } catch (RuntimeException e) {
                    Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                    return;
                }
            }
            dispatchSessionTaskWithSessionCommand(iMediaController, i, (int) SessionCommand.COMMAND_CODE_LIBRARY_SEARCH, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda48(str, libraryParams)));
        }
    }

    public void getSearchResult(IMediaController iMediaController, int i, String str, int i2, int i3, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParams;
        if (iMediaController != null) {
            if (TextUtils.isEmpty(str)) {
                Log.w(TAG, "getSearchResult(): Ignoring empty query");
            } else if (i2 < 0) {
                Log.w(TAG, "getSearchResult(): Ignoring negative page");
            } else if (i3 < 1) {
                Log.w(TAG, "getSearchResult(): Ignoring pageSize less than 1");
            } else {
                if (bundle == null) {
                    libraryParams = null;
                } else {
                    try {
                        libraryParams = MediaLibraryService.LibraryParams.fromBundle(bundle);
                    } catch (RuntimeException e) {
                        Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                        return;
                    }
                }
                dispatchSessionTaskWithSessionCommand(iMediaController, i, (int) SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda13(str, i2, i3, libraryParams)));
            }
        }
    }

    public void subscribe(IMediaController iMediaController, int i, String str, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParams;
        if (iMediaController != null) {
            if (TextUtils.isEmpty(str)) {
                Log.w(TAG, "subscribe(): Ignoring empty parentId");
                return;
            }
            if (bundle == null) {
                libraryParams = null;
            } else {
                try {
                    libraryParams = MediaLibraryService.LibraryParams.fromBundle(bundle);
                } catch (RuntimeException e) {
                    Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e);
                    return;
                }
            }
            dispatchSessionTaskWithSessionCommand(iMediaController, i, (int) SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda79(str, libraryParams)));
        }
    }

    public void unsubscribe(IMediaController iMediaController, int i, String str) {
        if (iMediaController != null) {
            if (TextUtils.isEmpty(str)) {
                Log.w(TAG, "unsubscribe(): Ignoring empty parentId");
            } else {
                dispatchSessionTaskWithSessionCommand(iMediaController, i, (int) SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE, sendLibraryResultWhenReady(new MediaSessionStub$$ExternalSyntheticLambda76(str)));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public PlayerInfo generateAndCacheUniqueTrackGroupIds(PlayerInfo playerInfo) {
        ImmutableList<Tracks.Group> groups = playerInfo.currentTracks.getGroups();
        ImmutableList.Builder builder = ImmutableList.builder();
        ImmutableBiMap.Builder builder2 = ImmutableBiMap.builder();
        for (int i = 0; i < groups.size(); i++) {
            Tracks.Group group = (Tracks.Group) groups.get(i);
            TrackGroup mediaTrackGroup = group.getMediaTrackGroup();
            String str = this.trackGroupIdMap.get(mediaTrackGroup);
            if (str == null) {
                str = generateUniqueTrackGroupId(mediaTrackGroup);
            }
            builder2.put(mediaTrackGroup, str);
            builder.add((Object) group.copyWithId(str));
        }
        this.trackGroupIdMap = builder2.buildOrThrow();
        PlayerInfo copyWithCurrentTracks = playerInfo.copyWithCurrentTracks(new Tracks(builder.build()));
        if (copyWithCurrentTracks.trackSelectionParameters.overrides.isEmpty()) {
            return copyWithCurrentTracks;
        }
        TrackSelectionParameters.Builder clearOverrides = copyWithCurrentTracks.trackSelectionParameters.buildUpon().clearOverrides();
        UnmodifiableIterator<TrackSelectionOverride> it = copyWithCurrentTracks.trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            TrackSelectionOverride next = it.next();
            TrackGroup trackGroup = next.mediaTrackGroup;
            String str2 = this.trackGroupIdMap.get(trackGroup);
            if (str2 != null) {
                clearOverrides.addOverride(new TrackSelectionOverride(trackGroup.copyWithId(str2), (List<Integer>) next.trackIndices));
            } else {
                clearOverrides.addOverride(next);
            }
        }
        return copyWithCurrentTracks.copyWithTrackSelectionParameters(clearOverrides.build());
    }

    private TrackSelectionParameters updateOverridesUsingUniqueTrackGroupIds(TrackSelectionParameters trackSelectionParameters) {
        if (trackSelectionParameters.overrides.isEmpty()) {
            return trackSelectionParameters;
        }
        TrackSelectionParameters.Builder clearOverrides = trackSelectionParameters.buildUpon().clearOverrides();
        UnmodifiableIterator<TrackSelectionOverride> it = trackSelectionParameters.overrides.values().iterator();
        while (it.hasNext()) {
            TrackSelectionOverride next = it.next();
            TrackGroup trackGroup = (TrackGroup) this.trackGroupIdMap.inverse().get(next.mediaTrackGroup.id);
            if (trackGroup == null || next.mediaTrackGroup.length != trackGroup.length) {
                clearOverrides.addOverride(next);
            } else {
                clearOverrides.addOverride(new TrackSelectionOverride(trackGroup, (List<Integer>) next.trackIndices));
            }
        }
        return clearOverrides.build();
    }

    private String generateUniqueTrackGroupId(TrackGroup trackGroup) {
        StringBuilder sb = new StringBuilder();
        int i = this.nextUniqueTrackGroupIdPrefix;
        this.nextUniqueTrackGroupIdPrefix = i + 1;
        return sb.append(Util.intToStringMaxRadix(i)).append("-").append(trackGroup.id).toString();
    }

    static final class Controller2Cb implements MediaSession.ControllerCb {
        private final int controllerInterfaceVersion;
        private final IMediaController iController;

        public Controller2Cb(IMediaController iMediaController, int i) {
            this.iController = iMediaController;
            this.controllerInterfaceVersion = i;
        }

        public IBinder getCallbackBinder() {
            return this.iController.asBinder();
        }

        public void onSessionResult(int i, SessionResult sessionResult) throws RemoteException {
            this.iController.onSessionResult(i, sessionResult.toBundle());
        }

        public void onLibraryResult(int i, LibraryResult<?> libraryResult) throws RemoteException {
            this.iController.onLibraryResult(i, libraryResult.toBundle());
        }

        public void onPlayerInfoChanged(int i, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z2) throws RemoteException {
            Bundle bundle;
            boolean z3 = false;
            Assertions.checkState(this.controllerInterfaceVersion != 0);
            boolean z4 = z || !commands.contains(17);
            if (z2 || !commands.contains(30)) {
                z3 = true;
            }
            if (this.controllerInterfaceVersion >= 2) {
                PlayerInfo filterByAvailableCommands = playerInfo.filterByAvailableCommands(commands, z, z2);
                if (this.iController instanceof MediaControllerStub) {
                    bundle = filterByAvailableCommands.toBundleInProcess();
                } else {
                    bundle = filterByAvailableCommands.toBundleForRemoteProcess(this.controllerInterfaceVersion);
                }
                this.iController.onPlayerInfoChangedWithExclusions(i, bundle, new PlayerInfo.BundlingExclusions(z4, z3).toBundle());
                return;
            }
            this.iController.onPlayerInfoChanged(i, playerInfo.filterByAvailableCommands(commands, z, true).toBundleForRemoteProcess(this.controllerInterfaceVersion), z4);
        }

        public void setCustomLayout(int i, List<CommandButton> list) throws RemoteException {
            this.iController.onSetCustomLayout(i, BundleCollectionUtil.toBundleList(list, new ConnectionState$$ExternalSyntheticLambda0()));
        }

        public void setMediaButtonPreferences(int i, List<CommandButton> list) throws RemoteException {
            if (this.controllerInterfaceVersion >= 7) {
                this.iController.onSetMediaButtonPreferences(i, BundleCollectionUtil.toBundleList(list, new ConnectionState$$ExternalSyntheticLambda0()));
            } else {
                this.iController.onSetCustomLayout(i, BundleCollectionUtil.toBundleList(list, new ConnectionState$$ExternalSyntheticLambda0()));
            }
        }

        public void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException {
            this.iController.onSessionActivityChanged(i, pendingIntent);
        }

        public void onAvailableCommandsChangedFromSession(int i, SessionCommands sessionCommands, Player.Commands commands) throws RemoteException {
            this.iController.onAvailableCommandsChangedFromSession(i, sessionCommands.toBundle(), commands.toBundle());
        }

        public void onAvailableCommandsChangedFromPlayer(int i, Player.Commands commands) throws RemoteException {
            this.iController.onAvailableCommandsChangedFromPlayer(i, commands.toBundle());
        }

        public void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException {
            this.iController.onCustomCommand(i, sessionCommand.toBundle(), bundle);
        }

        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            Bundle bundle;
            IMediaController iMediaController = this.iController;
            if (libraryParams == null) {
                bundle = null;
            } else {
                bundle = libraryParams.toBundle();
            }
            iMediaController.onChildrenChanged(i, str, i2, bundle);
        }

        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            Bundle bundle;
            IMediaController iMediaController = this.iController;
            if (libraryParams == null) {
                bundle = null;
            } else {
                bundle = libraryParams.toBundle();
            }
            iMediaController.onSearchResultChanged(i, str, i2, bundle);
        }

        public void onDisconnected(int i) throws RemoteException {
            this.iController.onDisconnected(i);
        }

        public void onPeriodicSessionPositionInfoChanged(int i, SessionPositionInfo sessionPositionInfo, boolean z, boolean z2, int i2) throws RemoteException {
            this.iController.onPeriodicSessionPositionInfoChanged(i, sessionPositionInfo.filterByAvailableCommands(z, z2).toBundle(i2));
        }

        public void onRenderedFirstFrame(int i) throws RemoteException {
            this.iController.onRenderedFirstFrame(i);
        }

        public void onSessionExtrasChanged(int i, Bundle bundle) throws RemoteException {
            this.iController.onExtrasChanged(i, bundle);
        }

        public void onError(int i, SessionError sessionError) throws RemoteException {
            this.iController.onError(i, sessionError.toBundle());
        }

        public int hashCode() {
            return ObjectsCompat.hash(getCallbackBinder());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != Controller2Cb.class) {
                return false;
            }
            return Util.areEqual(getCallbackBinder(), ((Controller2Cb) obj).getCallbackBinder());
        }
    }
}
