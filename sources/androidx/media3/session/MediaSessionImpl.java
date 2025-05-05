package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.session.MediaSession;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.SequencedFutureManager;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import com.facebook.imagepipeline.transcoder.JpegTranscoderUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

class MediaSessionImpl {
    private static final String ANDROID_AUTOMOTIVE_LAUNCHER_PACKAGE_NAME = "com.android.car.carlauncher";
    private static final String ANDROID_AUTOMOTIVE_MEDIA_PACKAGE_NAME = "com.android.car.media";
    private static final String ANDROID_AUTO_PACKAGE_NAME = "com.google.android.projection.gearhead";
    private static final long DEFAULT_SESSION_POSITION_UPDATE_DELAY_MS = 3000;
    private static final SessionResult RESULT_WHEN_CLOSED = new SessionResult(1);
    private static final String SYSTEM_UI_PACKAGE_NAME = "com.android.systemui";
    public static final String TAG = "MediaSessionImpl";
    private static final String WRONG_THREAD_ERROR_MESSAGE = "Player callback method is called from a wrong thread. See javadoc of MediaSession for details.";
    private final Handler applicationHandler;
    private final BitmapLoader bitmapLoader;
    private MediaSessionServiceLegacyStub browserServiceLegacyStub;
    private final MediaSession.Callback callback;
    private boolean closed;
    private final ImmutableList<CommandButton> commandButtonsForMediaItems;
    private final Context context;
    private MediaSession.ControllerInfo controllerForCurrentRequest;
    private ImmutableList<CommandButton> customLayout;
    private final MediaSession instance;
    private boolean isMediaNotificationControllerConnected;
    private final boolean isPeriodicPositionUpdateEnabled;
    private final Object lock = new Object();
    private final Handler mainHandler;
    private ImmutableList<CommandButton> mediaButtonPreferences;
    private final MediaPlayPauseKeyHandler mediaPlayPauseKeyHandler;
    private MediaSession.Listener mediaSessionListener;
    /* access modifiers changed from: private */
    public final PlayerInfoChangedHandler onPlayerInfoChangedHandler;
    private final Runnable periodicSessionPositionInfoUpdateRunnable;
    private final boolean playIfSuppressed;
    /* access modifiers changed from: private */
    public PlayerInfo playerInfo;
    private PlayerListener playerListener;
    /* access modifiers changed from: private */
    public PlayerWrapper playerWrapper;
    private PendingIntent sessionActivity;
    private Bundle sessionExtras;
    private final String sessionId;
    /* access modifiers changed from: private */
    public final MediaSessionLegacyStub sessionLegacyStub;
    private long sessionPositionUpdateDelayMs;
    private final MediaSessionStub sessionStub;
    private final SessionToken sessionToken;
    private final Uri sessionUri;

    interface RemoteControllerTask {
        void run(MediaSession.ControllerCb controllerCb, int i) throws RemoteException;
    }

    public MediaSessionImpl(MediaSession mediaSession, Context context2, String str, Player player, PendingIntent pendingIntent, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, ImmutableList<CommandButton> immutableList3, MediaSession.Callback callback2, Bundle bundle, Bundle bundle2, BitmapLoader bitmapLoader2, boolean z, boolean z2) {
        MediaSession mediaSession2 = mediaSession;
        String str2 = str;
        Log.i(TAG, "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.5.1] [" + Util.DEVICE_DEBUG_INFO + "]");
        this.instance = mediaSession2;
        this.context = context2;
        this.sessionId = str2;
        this.sessionActivity = pendingIntent;
        ImmutableList<CommandButton> immutableList4 = immutableList;
        this.customLayout = immutableList4;
        ImmutableList<CommandButton> immutableList5 = immutableList2;
        this.mediaButtonPreferences = immutableList5;
        this.commandButtonsForMediaItems = immutableList3;
        this.callback = callback2;
        Bundle bundle3 = bundle2;
        this.sessionExtras = bundle3;
        this.bitmapLoader = bitmapLoader2;
        this.playIfSuppressed = z;
        this.isPeriodicPositionUpdateEnabled = z2;
        MediaSessionStub mediaSessionStub = new MediaSessionStub(this);
        this.sessionStub = mediaSessionStub;
        this.mainHandler = new Handler(Looper.getMainLooper());
        Looper applicationLooper = player.getApplicationLooper();
        Handler handler = new Handler(applicationLooper);
        this.applicationHandler = handler;
        this.playerInfo = PlayerInfo.DEFAULT;
        this.onPlayerInfoChangedHandler = new PlayerInfoChangedHandler(applicationLooper);
        this.mediaPlayPauseKeyHandler = new MediaPlayPauseKeyHandler(applicationLooper);
        Uri build = new Uri.Builder().scheme(MediaSessionImpl.class.getName()).appendPath(str2).appendPath(String.valueOf(SystemClock.elapsedRealtime())).build();
        this.sessionUri = build;
        MediaSessionLegacyStub mediaSessionLegacyStub = new MediaSessionLegacyStub(this, build, handler, bundle);
        this.sessionLegacyStub = mediaSessionLegacyStub;
        Handler handler2 = handler;
        this.sessionToken = new SessionToken(Process.myUid(), 0, MediaLibraryInfo.VERSION_INT, 4, context2.getPackageName(), mediaSessionStub, bundle, (MediaSession.Token) mediaSessionLegacyStub.getSessionCompat().getSessionToken().getToken());
        MediaSession.ConnectionResult build2 = new MediaSession.ConnectionResult.AcceptedResultBuilder(mediaSession2).build();
        SessionCommands sessionCommands = build2.availableSessionCommands;
        Handler handler3 = handler2;
        PlayerWrapper playerWrapper2 = new PlayerWrapper(player, z, immutableList4, immutableList5, sessionCommands, build2.availablePlayerCommands, bundle3);
        this.playerWrapper = playerWrapper2;
        Util.postOrRun(handler3, new MediaSessionImpl$$ExternalSyntheticLambda7(this, playerWrapper2));
        this.sessionPositionUpdateDelayMs = 3000;
        Objects.requireNonNull(this);
        this.periodicSessionPositionInfoUpdateRunnable = new MediaSessionImpl$$ExternalSyntheticLambda8(this);
        Objects.requireNonNull(this);
        Util.postOrRun(handler3, new MediaSessionImpl$$ExternalSyntheticLambda9(this));
    }

    public void setPlayer(Player player) {
        if (player != this.playerWrapper.getWrappedPlayer()) {
            setPlayerInternal(this.playerWrapper, new PlayerWrapper(player, this.playIfSuppressed, this.playerWrapper.getCustomLayout(), this.playerWrapper.getMediaButtonPreferences(), this.playerWrapper.getAvailableSessionCommands(), this.playerWrapper.getAvailablePlayerCommands(), this.playerWrapper.getLegacyExtras()));
        }
    }

    /* access modifiers changed from: private */
    public void setPlayerInternal(PlayerWrapper playerWrapper2, PlayerWrapper playerWrapper3) {
        this.playerWrapper = playerWrapper3;
        if (playerWrapper2 != null) {
            playerWrapper2.removeListener((Player.Listener) Assertions.checkStateNotNull(this.playerListener));
        }
        PlayerListener playerListener2 = new PlayerListener(this, playerWrapper3);
        playerWrapper3.addListener(playerListener2);
        this.playerListener = playerListener2;
        dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$$ExternalSyntheticLambda18(playerWrapper2, playerWrapper3));
        if (playerWrapper2 == null) {
            this.sessionLegacyStub.start();
        }
        this.playerInfo = playerWrapper3.createPlayerInfoForBundling();
        handleAvailablePlayerCommandsChanged(playerWrapper3.getAvailableCommands());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        androidx.media3.common.util.Util.postOrRun(r3.applicationHandler, new androidx.media3.session.MediaSessionImpl$$ExternalSyntheticLambda13(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0060, code lost:
        androidx.media3.common.util.Log.w(TAG, "Exception thrown while closing", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0049, code lost:
        r3.mediaPlayPauseKeyHandler.clearPendingPlayPauseTask();
        r3.applicationHandler.removeCallbacksAndMessages((java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void release() {
        /*
            r3 = this;
            java.lang.String r0 = "MediaSessionImpl"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Release "
            r1.<init>(r2)
            int r2 = java.lang.System.identityHashCode(r3)
            java.lang.String r2 = java.lang.Integer.toHexString(r2)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = " [AndroidXMedia3/1.5.1] ["
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = androidx.media3.common.util.Util.DEVICE_DEBUG_INFO
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = "] ["
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = androidx.media3.common.MediaLibraryInfo.registeredModules()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = "]"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            androidx.media3.common.util.Log.i(r0, r1)
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            boolean r1 = r3.closed     // Catch:{ all -> 0x0072 }
            if (r1 == 0) goto L_0x0045
            monitor-exit(r0)     // Catch:{ all -> 0x0072 }
            return
        L_0x0045:
            r1 = 1
            r3.closed = r1     // Catch:{ all -> 0x0072 }
            monitor-exit(r0)     // Catch:{ all -> 0x0072 }
            androidx.media3.session.MediaSessionImpl$MediaPlayPauseKeyHandler r0 = r3.mediaPlayPauseKeyHandler
            r0.clearPendingPlayPauseTask()
            android.os.Handler r0 = r3.applicationHandler
            r1 = 0
            r0.removeCallbacksAndMessages(r1)
            android.os.Handler r0 = r3.applicationHandler     // Catch:{ Exception -> 0x005f }
            androidx.media3.session.MediaSessionImpl$$ExternalSyntheticLambda13 r1 = new androidx.media3.session.MediaSessionImpl$$ExternalSyntheticLambda13     // Catch:{ Exception -> 0x005f }
            r1.<init>(r3)     // Catch:{ Exception -> 0x005f }
            androidx.media3.common.util.Util.postOrRun(r0, r1)     // Catch:{ Exception -> 0x005f }
            goto L_0x0067
        L_0x005f:
            r0 = move-exception
            java.lang.String r1 = "MediaSessionImpl"
            java.lang.String r2 = "Exception thrown while closing"
            androidx.media3.common.util.Log.w(r1, r2, r0)
        L_0x0067:
            androidx.media3.session.MediaSessionLegacyStub r0 = r3.sessionLegacyStub
            r0.release()
            androidx.media3.session.MediaSessionStub r0 = r3.sessionStub
            r0.release()
            return
        L_0x0072:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0072 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaSessionImpl.release():void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$2$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1064lambda$release$2$androidxmedia3sessionMediaSessionImpl() {
        PlayerListener playerListener2 = this.playerListener;
        if (playerListener2 != null) {
            this.playerWrapper.removeListener(playerListener2);
        }
    }

    public PlayerWrapper getPlayerWrapper() {
        return this.playerWrapper;
    }

    public Runnable callWithControllerForCurrentRequestSet(MediaSession.ControllerInfo controllerInfo, Runnable runnable) {
        return new MediaSessionImpl$$ExternalSyntheticLambda22(this, controllerInfo, runnable);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$callWithControllerForCurrentRequestSet$3$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1060lambda$callWithControllerForCurrentRequestSet$3$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerInfo controllerInfo, Runnable runnable) {
        this.controllerForCurrentRequest = controllerInfo;
        runnable.run();
        this.controllerForCurrentRequest = null;
    }

    public String getId() {
        return this.sessionId;
    }

    public Uri getUri() {
        return this.sessionUri;
    }

    public SessionToken getToken() {
        return this.sessionToken;
    }

    public List<MediaSession.ControllerInfo> getConnectedControllers() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.sessionStub.getConnectedControllersManager().getConnectedControllers());
        if (this.isMediaNotificationControllerConnected) {
            ImmutableList<MediaSession.ControllerInfo> connectedControllers = this.sessionLegacyStub.getConnectedControllersManager().getConnectedControllers();
            for (int i = 0; i < connectedControllers.size(); i++) {
                MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i);
                if (!isSystemUiController(controllerInfo)) {
                    arrayList.add(controllerInfo);
                }
            }
        } else {
            arrayList.addAll(this.sessionLegacyStub.getConnectedControllersManager().getConnectedControllers());
        }
        return arrayList;
    }

    public MediaSession.ControllerInfo getControllerForCurrentRequest() {
        MediaSession.ControllerInfo controllerInfo = this.controllerForCurrentRequest;
        if (controllerInfo != null) {
            return resolveControllerInfoForCallback(controllerInfo);
        }
        return null;
    }

    public boolean isConnected(MediaSession.ControllerInfo controllerInfo) {
        return this.sessionStub.getConnectedControllersManager().isConnected(controllerInfo) || this.sessionLegacyStub.getConnectedControllersManager().isConnected(controllerInfo);
    }

    /* access modifiers changed from: protected */
    public boolean isSystemUiController(MediaSession.ControllerInfo controllerInfo) {
        return controllerInfo != null && controllerInfo.getControllerVersion() == 0 && Objects.equals(controllerInfo.getPackageName(), SYSTEM_UI_PACKAGE_NAME);
    }

    public boolean isMediaNotificationController(MediaSession.ControllerInfo controllerInfo) {
        if (!Objects.equals(controllerInfo.getPackageName(), this.context.getPackageName()) || controllerInfo.getControllerVersion() == 0 || !controllerInfo.getConnectionHints().getBoolean(MediaController.KEY_MEDIA_NOTIFICATION_CONTROLLER_FLAG, false)) {
            return false;
        }
        return true;
    }

    public boolean isAutomotiveController(MediaSession.ControllerInfo controllerInfo) {
        return controllerInfo.getControllerVersion() == 0 && (controllerInfo.getPackageName().equals(ANDROID_AUTOMOTIVE_MEDIA_PACKAGE_NAME) || controllerInfo.getPackageName().equals(ANDROID_AUTOMOTIVE_LAUNCHER_PACKAGE_NAME));
    }

    public boolean isAutoCompanionController(MediaSession.ControllerInfo controllerInfo) {
        return controllerInfo.getControllerVersion() == 0 && controllerInfo.getPackageName().equals(ANDROID_AUTO_PACKAGE_NAME);
    }

    /* access modifiers changed from: protected */
    public MediaSession.ControllerInfo getSystemUiControllerInfo() {
        ImmutableList<MediaSession.ControllerInfo> connectedControllers = this.sessionLegacyStub.getConnectedControllersManager().getConnectedControllers();
        for (int i = 0; i < connectedControllers.size(); i++) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i);
            if (isSystemUiController(controllerInfo)) {
                return controllerInfo;
            }
        }
        return null;
    }

    public MediaSession.ControllerInfo getMediaNotificationControllerInfo() {
        ImmutableList<MediaSession.ControllerInfo> connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i = 0; i < connectedControllers.size(); i++) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i);
            if (isMediaNotificationController(controllerInfo)) {
                return controllerInfo;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isMediaNotificationControllerConnected() {
        return this.isMediaNotificationControllerConnected;
    }

    public ListenableFuture<SessionResult> setCustomLayout(MediaSession.ControllerInfo controllerInfo, ImmutableList<CommandButton> immutableList) {
        if (isMediaNotificationController(controllerInfo)) {
            this.playerWrapper.setCustomLayout(immutableList);
            this.sessionLegacyStub.updateLegacySessionPlaybackState(this.playerWrapper);
        }
        return dispatchRemoteControllerTask(controllerInfo, new MediaSessionImpl$$ExternalSyntheticLambda14(immutableList));
    }

    public void setCustomLayout(ImmutableList<CommandButton> immutableList) {
        this.customLayout = immutableList;
        this.playerWrapper.setCustomLayout(immutableList);
        dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$$ExternalSyntheticLambda16(immutableList));
    }

    public ListenableFuture<SessionResult> setMediaButtonPreferences(MediaSession.ControllerInfo controllerInfo, ImmutableList<CommandButton> immutableList) {
        if (isMediaNotificationController(controllerInfo)) {
            this.playerWrapper.setMediaButtonPreferences(immutableList);
            this.sessionLegacyStub.updateLegacySessionPlaybackState(this.playerWrapper);
        }
        return dispatchRemoteControllerTask(controllerInfo, new MediaSessionImpl$$ExternalSyntheticLambda10(immutableList));
    }

    public void setMediaButtonPreferences(ImmutableList<CommandButton> immutableList) {
        this.mediaButtonPreferences = immutableList;
        this.playerWrapper.setMediaButtonPreferences(immutableList);
        dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$$ExternalSyntheticLambda6(immutableList));
    }

    public ImmutableList<CommandButton> getCustomLayout() {
        return this.customLayout;
    }

    public ImmutableList<CommandButton> getMediaButtonPreferences() {
        return this.mediaButtonPreferences;
    }

    public ImmutableList<CommandButton> getCommandButtonsForMediaItems() {
        return this.commandButtonsForMediaItems;
    }

    public void setSessionExtras(Bundle bundle) {
        this.sessionExtras = bundle;
        dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$$ExternalSyntheticLambda4(bundle));
    }

    public void setSessionExtras(MediaSession.ControllerInfo controllerInfo, Bundle bundle) {
        if (this.sessionStub.getConnectedControllersManager().isConnected(controllerInfo)) {
            dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new MediaSessionImpl$$ExternalSyntheticLambda1(bundle));
            if (isMediaNotificationController(controllerInfo)) {
                dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$$ExternalSyntheticLambda2(bundle));
            }
        }
    }

    public Bundle getSessionExtras() {
        return this.sessionExtras;
    }

    public BitmapLoader getBitmapLoader() {
        return this.bitmapLoader;
    }

    public boolean shouldPlayIfSuppressed() {
        return this.playIfSuppressed;
    }

    public void setAvailableCommands(MediaSession.ControllerInfo controllerInfo, SessionCommands sessionCommands, Player.Commands commands) {
        if (this.sessionStub.getConnectedControllersManager().isConnected(controllerInfo)) {
            if (isMediaNotificationController(controllerInfo)) {
                setAvailableFrameworkControllerCommands(sessionCommands, commands);
                MediaSession.ControllerInfo systemUiControllerInfo = getSystemUiControllerInfo();
                if (systemUiControllerInfo != null) {
                    this.sessionLegacyStub.getConnectedControllersManager().updateCommandsFromSession(systemUiControllerInfo, sessionCommands, commands);
                }
            }
            this.sessionStub.getConnectedControllersManager().updateCommandsFromSession(controllerInfo, sessionCommands, commands);
            dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new MediaSessionImpl$$ExternalSyntheticLambda15(sessionCommands, commands));
            this.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(false, false);
            return;
        }
        this.sessionLegacyStub.getConnectedControllersManager().updateCommandsFromSession(controllerInfo, sessionCommands, commands);
    }

    public void broadcastCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$$ExternalSyntheticLambda3(sessionCommand, bundle));
    }

    /* access modifiers changed from: private */
    public void dispatchOnPlayerInfoChanged(PlayerInfo playerInfo2, boolean z, boolean z2) {
        int i;
        PlayerInfo generateAndCacheUniqueTrackGroupIds = this.sessionStub.generateAndCacheUniqueTrackGroupIds(playerInfo2);
        ImmutableList<MediaSession.ControllerInfo> connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i2 = 0; i2 < connectedControllers.size(); i2++) {
            MediaSession.ControllerInfo controllerInfo = connectedControllers.get(i2);
            try {
                ConnectedControllersManager<IBinder> connectedControllersManager = this.sessionStub.getConnectedControllersManager();
                SequencedFutureManager sequencedFutureManager = connectedControllersManager.getSequencedFutureManager(controllerInfo);
                if (sequencedFutureManager != null) {
                    i = sequencedFutureManager.obtainNextSequenceNumber();
                } else if (isConnected(controllerInfo)) {
                    i = 0;
                } else {
                    return;
                }
                ((MediaSession.ControllerCb) Assertions.checkStateNotNull(controllerInfo.getControllerCb())).onPlayerInfoChanged(i, generateAndCacheUniqueTrackGroupIds, MediaUtils.intersect(connectedControllersManager.getAvailablePlayerCommands(controllerInfo), getPlayerWrapper().getAvailableCommands()), z, z2);
            } catch (DeadObjectException unused) {
                onDeadObjectException(controllerInfo);
            } catch (RemoteException e) {
                Log.w(TAG, "Exception in " + controllerInfo.toString(), e);
            }
        }
    }

    public ListenableFuture<SessionResult> sendCustomCommand(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
        return dispatchRemoteControllerTask(controllerInfo, new MediaSessionImpl$$ExternalSyntheticLambda11(sessionCommand, bundle));
    }

    public void sendError(MediaSession.ControllerInfo controllerInfo, SessionError sessionError) {
        if (controllerInfo.getControllerVersion() != 0 && controllerInfo.getInterfaceVersion() < 4) {
            return;
        }
        if (isMediaNotificationController(controllerInfo) || controllerInfo.getControllerVersion() == 0) {
            dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$$ExternalSyntheticLambda32(sessionError));
        } else {
            dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new MediaSessionImpl$$ExternalSyntheticLambda33(sessionError));
        }
    }

    public void sendError(SessionError sessionError) {
        ImmutableList<MediaSession.ControllerInfo> connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i = 0; i < connectedControllers.size(); i++) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) connectedControllers.get(i);
            if (!isMediaNotificationController(controllerInfo)) {
                sendError(controllerInfo, sessionError);
            }
        }
        dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$$ExternalSyntheticLambda36(sessionError));
    }

    public MediaSession.ConnectionResult onConnectOnHandler(MediaSession.ControllerInfo controllerInfo) {
        ImmutableList<CommandButton> immutableList;
        ImmutableList<CommandButton> immutableList2;
        if (this.isMediaNotificationControllerConnected && isSystemUiController(controllerInfo)) {
            return new MediaSession.ConnectionResult.AcceptedResultBuilder(this.instance).setAvailableSessionCommands(this.playerWrapper.getAvailableSessionCommands()).setAvailablePlayerCommands(this.playerWrapper.getAvailablePlayerCommands()).setCustomLayout(this.playerWrapper.getCustomLayout()).setMediaButtonPreferences(this.playerWrapper.getMediaButtonPreferences()).build();
        }
        MediaSession.ConnectionResult connectionResult = (MediaSession.ConnectionResult) Assertions.checkNotNull(this.callback.onConnect(this.instance, controllerInfo), "Callback.onConnect must return non-null future");
        if (isMediaNotificationController(controllerInfo) && connectionResult.isAccepted) {
            this.isMediaNotificationControllerConnected = true;
            PlayerWrapper playerWrapper2 = this.playerWrapper;
            if (connectionResult.customLayout != null) {
                immutableList = connectionResult.customLayout;
            } else {
                immutableList = this.instance.getCustomLayout();
            }
            playerWrapper2.setCustomLayout(immutableList);
            PlayerWrapper playerWrapper3 = this.playerWrapper;
            if (connectionResult.mediaButtonPreferences != null) {
                immutableList2 = connectionResult.mediaButtonPreferences;
            } else {
                immutableList2 = this.instance.getMediaButtonPreferences();
            }
            playerWrapper3.setMediaButtonPreferences(immutableList2);
            setAvailableFrameworkControllerCommands(connectionResult.availableSessionCommands, connectionResult.availablePlayerCommands);
        }
        return connectionResult;
    }

    public void onPostConnectOnHandler(MediaSession.ControllerInfo controllerInfo) {
        if (!this.isMediaNotificationControllerConnected || !isSystemUiController(controllerInfo)) {
            this.callback.onPostConnect(this.instance, controllerInfo);
        }
    }

    public void onDisconnectedOnHandler(MediaSession.ControllerInfo controllerInfo) {
        if (this.isMediaNotificationControllerConnected) {
            if (!isSystemUiController(controllerInfo)) {
                if (isMediaNotificationController(controllerInfo)) {
                    this.isMediaNotificationControllerConnected = false;
                }
            } else {
                return;
            }
        }
        this.callback.onDisconnected(this.instance, controllerInfo);
    }

    public int onPlayerCommandRequestOnHandler(MediaSession.ControllerInfo controllerInfo, int i) {
        return this.callback.onPlayerCommandRequest(this.instance, resolveControllerInfoForCallback(controllerInfo), i);
    }

    public ListenableFuture<SessionResult> onSetRatingOnHandler(MediaSession.ControllerInfo controllerInfo, String str, Rating rating) {
        return (ListenableFuture) Assertions.checkNotNull(this.callback.onSetRating(this.instance, resolveControllerInfoForCallback(controllerInfo), str, rating), "Callback.onSetRating must return non-null future");
    }

    public ListenableFuture<SessionResult> onSetRatingOnHandler(MediaSession.ControllerInfo controllerInfo, Rating rating) {
        return (ListenableFuture) Assertions.checkNotNull(this.callback.onSetRating(this.instance, resolveControllerInfoForCallback(controllerInfo), rating), "Callback.onSetRating must return non-null future");
    }

    public ListenableFuture<SessionResult> onCustomCommandOnHandler(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
        return (ListenableFuture) Assertions.checkNotNull(this.callback.onCustomCommand(this.instance, resolveControllerInfoForCallback(controllerInfo), sessionCommand, bundle), "Callback.onCustomCommandOnHandler must return non-null future");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<List<MediaItem>> onAddMediaItemsOnHandler(MediaSession.ControllerInfo controllerInfo, List<MediaItem> list) {
        return (ListenableFuture) Assertions.checkNotNull(this.callback.onAddMediaItems(this.instance, resolveControllerInfoForCallback(controllerInfo), list), "Callback.onAddMediaItems must return a non-null future");
    }

    /* access modifiers changed from: protected */
    public ListenableFuture<MediaSession.MediaItemsWithStartPosition> onSetMediaItemsOnHandler(MediaSession.ControllerInfo controllerInfo, List<MediaItem> list, int i, long j) {
        return (ListenableFuture) Assertions.checkNotNull(this.callback.onSetMediaItems(this.instance, resolveControllerInfoForCallback(controllerInfo), list, i, j), "Callback.onSetMediaItems must return a non-null future");
    }

    /* access modifiers changed from: protected */
    public void onPlayerInteractionFinishedOnHandler(MediaSession.ControllerInfo controllerInfo, Player.Commands commands) {
        this.callback.onPlayerInteractionFinished(this.instance, resolveControllerInfoForCallback(controllerInfo), commands);
    }

    public void connectFromService(IMediaController iMediaController, MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.connect(iMediaController, controllerInfo);
    }

    public MediaSessionCompat getSessionCompat() {
        return this.sessionLegacyStub.getSessionCompat();
    }

    public void setLegacyControllerConnectionTimeoutMs(long j) {
        this.sessionLegacyStub.setLegacyControllerDisconnectTimeoutMs(j);
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }

    /* access modifiers changed from: protected */
    public Handler getApplicationHandler() {
        return this.applicationHandler;
    }

    /* access modifiers changed from: protected */
    public boolean isReleased() {
        boolean z;
        synchronized (this.lock) {
            z = this.closed;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public PendingIntent getSessionActivity() {
        return this.sessionActivity;
    }

    /* access modifiers changed from: protected */
    public void setSessionActivity(PendingIntent pendingIntent) {
        this.sessionActivity = pendingIntent;
        ImmutableList<MediaSession.ControllerInfo> connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i = 0; i < connectedControllers.size(); i++) {
            setSessionActivity((MediaSession.ControllerInfo) connectedControllers.get(i), pendingIntent);
        }
    }

    /* access modifiers changed from: protected */
    public void setSessionActivity(MediaSession.ControllerInfo controllerInfo, PendingIntent pendingIntent) {
        if (controllerInfo.getControllerVersion() >= 3 && this.sessionStub.getConnectedControllersManager().isConnected(controllerInfo)) {
            dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new MediaSessionImpl$$ExternalSyntheticLambda34(pendingIntent));
            if (isMediaNotificationController(controllerInfo)) {
                dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$$ExternalSyntheticLambda35(pendingIntent));
            }
        }
    }

    /* access modifiers changed from: protected */
    public MediaSession.ControllerInfo resolveControllerInfoForCallback(MediaSession.ControllerInfo controllerInfo) {
        return (!this.isMediaNotificationControllerConnected || !isSystemUiController(controllerInfo)) ? controllerInfo : (MediaSession.ControllerInfo) Assertions.checkNotNull(getMediaNotificationControllerInfo());
    }

    /* access modifiers changed from: protected */
    public IBinder getLegacyBrowserServiceBinder() {
        MediaSessionServiceLegacyStub mediaSessionServiceLegacyStub;
        synchronized (this.lock) {
            if (this.browserServiceLegacyStub == null) {
                this.browserServiceLegacyStub = createLegacyBrowserService(this.instance.getSessionCompat().getSessionToken());
            }
            mediaSessionServiceLegacyStub = this.browserServiceLegacyStub;
        }
        return mediaSessionServiceLegacyStub.onBind(new Intent("android.media.browse.MediaBrowserService"));
    }

    /* access modifiers changed from: protected */
    public MediaSessionServiceLegacyStub createLegacyBrowserService(MediaSessionCompat.Token token) {
        MediaSessionServiceLegacyStub mediaSessionServiceLegacyStub = new MediaSessionServiceLegacyStub(this);
        mediaSessionServiceLegacyStub.initialize(token);
        return mediaSessionServiceLegacyStub;
    }

    /* access modifiers changed from: protected */
    public void setSessionPositionUpdateDelayMsOnHandler(long j) {
        verifyApplicationThread();
        this.sessionPositionUpdateDelayMs = j;
        schedulePeriodicSessionPositionInfoChanges();
    }

    /* access modifiers changed from: protected */
    public MediaSessionServiceLegacyStub getLegacyBrowserService() {
        MediaSessionServiceLegacyStub mediaSessionServiceLegacyStub;
        synchronized (this.lock) {
            mediaSessionServiceLegacyStub = this.browserServiceLegacyStub;
        }
        return mediaSessionServiceLegacyStub;
    }

    /* access modifiers changed from: package-private */
    public boolean canResumePlaybackOnStart() {
        return this.sessionLegacyStub.canResumePlaybackOnStart();
    }

    /* access modifiers changed from: package-private */
    public void setMediaSessionListener(MediaSession.Listener listener) {
        this.mediaSessionListener = listener;
    }

    /* access modifiers changed from: package-private */
    public void clearMediaSessionListener() {
        this.mediaSessionListener = null;
    }

    /* access modifiers changed from: package-private */
    public void onNotificationRefreshRequired() {
        Util.postOrRun(this.mainHandler, new MediaSessionImpl$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onNotificationRefreshRequired$19$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1062lambda$onNotificationRefreshRequired$19$androidxmedia3sessionMediaSessionImpl() {
        MediaSession.Listener listener = this.mediaSessionListener;
        if (listener != null) {
            listener.onNotificationRefreshRequired(this.instance);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onPlayRequested() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            SettableFuture create = SettableFuture.create();
            this.mainHandler.post(new MediaSessionImpl$$ExternalSyntheticLambda17(this, create));
            try {
                return ((Boolean) create.get()).booleanValue();
            } catch (InterruptedException | ExecutionException e) {
                throw new IllegalStateException(e);
            }
        } else {
            MediaSession.Listener listener = this.mediaSessionListener;
            if (listener != null) {
                return listener.onPlayRequested(this.instance);
            }
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPlayRequested$20$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1063lambda$onPlayRequested$20$androidxmedia3sessionMediaSessionImpl(SettableFuture settableFuture) {
        settableFuture.set(Boolean.valueOf(onPlayRequested()));
    }

    /* access modifiers changed from: package-private */
    public void handleMediaControllerPlayRequest(MediaSession.ControllerInfo controllerInfo, final boolean z) {
        if (onPlayRequested()) {
            boolean z2 = false;
            boolean z3 = this.playerWrapper.isCommandAvailable(16) && this.playerWrapper.getCurrentMediaItem() != null;
            if (this.playerWrapper.isCommandAvailable(31) || this.playerWrapper.isCommandAvailable(20)) {
                z2 = true;
            }
            final MediaSession.ControllerInfo resolveControllerInfoForCallback = resolveControllerInfoForCallback(controllerInfo);
            final Player.Commands build = new Player.Commands.Builder().add(1).build();
            if (z3 || !z2) {
                if (!z3) {
                    Log.w(TAG, "Play requested without current MediaItem, but playback resumption prevented by missing available commands");
                }
                Util.handlePlayButtonAction(this.playerWrapper);
                if (z) {
                    onPlayerInteractionFinishedOnHandler(resolveControllerInfoForCallback, build);
                    return;
                }
                return;
            }
            Futures.addCallback((ListenableFuture) Assertions.checkNotNull(this.callback.onPlaybackResumption(this.instance, resolveControllerInfoForCallback), "Callback.onPlaybackResumption must return a non-null future"), new FutureCallback<MediaSession.MediaItemsWithStartPosition>() {
                public void onSuccess(MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
                    MediaSessionImpl mediaSessionImpl = MediaSessionImpl.this;
                    MediaSession.ControllerInfo controllerInfo = resolveControllerInfoForCallback;
                    mediaSessionImpl.callWithControllerForCurrentRequestSet(controllerInfo, new MediaSessionImpl$1$$ExternalSyntheticLambda0(this, mediaItemsWithStartPosition, z, controllerInfo, build)).run();
                }

                /* access modifiers changed from: package-private */
                /* renamed from: lambda$onSuccess$0$androidx-media3-session-MediaSessionImpl$1  reason: not valid java name */
                public /* synthetic */ void m1065lambda$onSuccess$0$androidxmedia3sessionMediaSessionImpl$1(MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition, boolean z, MediaSession.ControllerInfo controllerInfo, Player.Commands commands) {
                    MediaUtils.setMediaItemsWithStartIndexAndPosition(MediaSessionImpl.this.playerWrapper, mediaItemsWithStartPosition);
                    Util.handlePlayButtonAction(MediaSessionImpl.this.playerWrapper);
                    if (z) {
                        MediaSessionImpl.this.onPlayerInteractionFinishedOnHandler(controllerInfo, commands);
                    }
                }

                public void onFailure(Throwable th) {
                    if (th instanceof UnsupportedOperationException) {
                        Log.w(MediaSessionImpl.TAG, "UnsupportedOperationException: Make sure to implement MediaSession.Callback.onPlaybackResumption() if you add a media button receiver to your manifest or if you implement the recent media item contract with your MediaLibraryService.", th);
                    } else {
                        Log.e(MediaSessionImpl.TAG, "Failure calling MediaSession.Callback.onPlaybackResumption(): " + th.getMessage(), th);
                    }
                    Util.handlePlayButtonAction(MediaSessionImpl.this.playerWrapper);
                    if (z) {
                        MediaSessionImpl.this.onPlayerInteractionFinishedOnHandler(resolveControllerInfoForCallback, build);
                    }
                }
            }, new MediaSessionImpl$$ExternalSyntheticLambda12(this));
        }
    }

    private void setAvailableFrameworkControllerCommands(SessionCommands sessionCommands, Player.Commands commands) {
        boolean z = this.playerWrapper.getAvailablePlayerCommands().contains(17) != commands.contains(17);
        this.playerWrapper.setAvailableCommands(sessionCommands, commands);
        if (z) {
            this.sessionLegacyStub.updateLegacySessionPlaybackStateAndQueue(this.playerWrapper);
        } else {
            this.sessionLegacyStub.updateLegacySessionPlaybackState(this.playerWrapper);
        }
    }

    /* access modifiers changed from: private */
    public void dispatchRemoteControllerTaskToLegacyStub(RemoteControllerTask remoteControllerTask) {
        try {
            remoteControllerTask.run(this.sessionLegacyStub.getControllerLegacyCbForBroadcast(), 0);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception in using media1 API", e);
        }
    }

    private void dispatchOnPeriodicSessionPositionInfoChanged(SessionPositionInfo sessionPositionInfo) {
        ConnectedControllersManager<IBinder> connectedControllersManager = this.sessionStub.getConnectedControllersManager();
        ImmutableList<MediaSession.ControllerInfo> connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i = 0; i < connectedControllers.size(); i++) {
            MediaSession.ControllerInfo controllerInfo = connectedControllers.get(i);
            dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new MediaSessionImpl$$ExternalSyntheticLambda5(sessionPositionInfo, connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 16), connectedControllersManager.isPlayerCommandAvailable(controllerInfo, 17), controllerInfo));
        }
        try {
            this.sessionLegacyStub.getControllerLegacyCbForBroadcast().onPeriodicSessionPositionInfoChanged(0, sessionPositionInfo, true, true, 0);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception in using media1 API", e);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchRemoteControllerTaskWithoutReturn(RemoteControllerTask remoteControllerTask) {
        ImmutableList<MediaSession.ControllerInfo> connectedControllers = this.sessionStub.getConnectedControllersManager().getConnectedControllers();
        for (int i = 0; i < connectedControllers.size(); i++) {
            dispatchRemoteControllerTaskWithoutReturn(connectedControllers.get(i), remoteControllerTask);
        }
        try {
            remoteControllerTask.run(this.sessionLegacyStub.getControllerLegacyCbForBroadcast(), 0);
        } catch (RemoteException e) {
            Log.e(TAG, "Exception in using media1 API", e);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchRemoteControllerTaskWithoutReturn(MediaSession.ControllerInfo controllerInfo, RemoteControllerTask remoteControllerTask) {
        int i;
        try {
            SequencedFutureManager sequencedFutureManager = this.sessionStub.getConnectedControllersManager().getSequencedFutureManager(controllerInfo);
            if (sequencedFutureManager != null) {
                i = sequencedFutureManager.obtainNextSequenceNumber();
            } else if (isConnected(controllerInfo)) {
                i = 0;
            } else {
                return;
            }
            MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
            if (controllerCb != null) {
                remoteControllerTask.run(controllerCb, i);
            }
        } catch (DeadObjectException unused) {
            onDeadObjectException(controllerInfo);
        } catch (RemoteException e) {
            Log.w(TAG, "Exception in " + controllerInfo.toString(), e);
        }
    }

    private ListenableFuture<SessionResult> dispatchRemoteControllerTask(MediaSession.ControllerInfo controllerInfo, RemoteControllerTask remoteControllerTask) {
        int i;
        SequencedFutureManager.SequencedFuture sequencedFuture;
        try {
            SequencedFutureManager sequencedFutureManager = this.sessionStub.getConnectedControllersManager().getSequencedFutureManager(controllerInfo);
            if (sequencedFutureManager != null) {
                SequencedFutureManager.SequencedFuture createSequencedFuture = sequencedFutureManager.createSequencedFuture(RESULT_WHEN_CLOSED);
                SequencedFutureManager.SequencedFuture sequencedFuture2 = createSequencedFuture;
                i = createSequencedFuture.getSequenceNumber();
                sequencedFuture = createSequencedFuture;
            } else if (!isConnected(controllerInfo)) {
                return Futures.immediateFuture(new SessionResult(-100));
            } else {
                i = 0;
                sequencedFuture = Futures.immediateFuture(new SessionResult(0));
            }
            MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
            if (controllerCb != null) {
                remoteControllerTask.run(controllerCb, i);
            }
            return sequencedFuture;
        } catch (DeadObjectException unused) {
            onDeadObjectException(controllerInfo);
            return Futures.immediateFuture(new SessionResult(-100));
        } catch (RemoteException e) {
            Log.w(TAG, "Exception in " + controllerInfo.toString(), e);
            return Futures.immediateFuture(new SessionResult(-1));
        }
    }

    private void onDeadObjectException(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.getConnectedControllersManager().removeController(controllerInfo);
    }

    /* access modifiers changed from: private */
    public void verifyApplicationThread() {
        if (Looper.myLooper() != this.applicationHandler.getLooper()) {
            throw new IllegalStateException(WRONG_THREAD_ERROR_MESSAGE);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if (androidx.media3.session.MediaUtils.areSessionPositionInfosInSamePeriodOrAd(r0, r2.playerInfo.sessionPositionInfo) == false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        dispatchOnPeriodicSessionPositionInfoChanged(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        schedulePeriodicSessionPositionInfoChanges();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000a, code lost:
        r0 = r2.playerWrapper.createSessionPositionInfoForBundling();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        if (r2.onPlayerInfoChangedHandler.hasPendingPlayerInfoChangedUpdate() != false) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void notifyPeriodicSessionPositionInfoChangesOnHandler() {
        /*
            r2 = this;
            java.lang.Object r0 = r2.lock
            monitor-enter(r0)
            boolean r1 = r2.closed     // Catch:{ all -> 0x0029 }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            return
        L_0x0009:
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            androidx.media3.session.PlayerWrapper r0 = r2.playerWrapper
            androidx.media3.session.SessionPositionInfo r0 = r0.createSessionPositionInfoForBundling()
            androidx.media3.session.MediaSessionImpl$PlayerInfoChangedHandler r1 = r2.onPlayerInfoChangedHandler
            boolean r1 = r1.hasPendingPlayerInfoChangedUpdate()
            if (r1 != 0) goto L_0x0025
            androidx.media3.session.PlayerInfo r1 = r2.playerInfo
            androidx.media3.session.SessionPositionInfo r1 = r1.sessionPositionInfo
            boolean r1 = androidx.media3.session.MediaUtils.areSessionPositionInfosInSamePeriodOrAd(r0, r1)
            if (r1 == 0) goto L_0x0025
            r2.dispatchOnPeriodicSessionPositionInfoChanged(r0)
        L_0x0025:
            r2.schedulePeriodicSessionPositionInfoChanges()
            return
        L_0x0029:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0029 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaSessionImpl.notifyPeriodicSessionPositionInfoChangesOnHandler():void");
    }

    /* access modifiers changed from: private */
    public void schedulePeriodicSessionPositionInfoChanges() {
        this.applicationHandler.removeCallbacks(this.periodicSessionPositionInfoUpdateRunnable);
        if (this.isPeriodicPositionUpdateEnabled && this.sessionPositionUpdateDelayMs > 0) {
            if (this.playerWrapper.isPlaying() || this.playerWrapper.isLoading()) {
                this.applicationHandler.postDelayed(this.periodicSessionPositionInfoUpdateRunnable, this.sessionPositionUpdateDelayMs);
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleAvailablePlayerCommandsChanged(Player.Commands commands) {
        this.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(false, false);
        dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$$ExternalSyntheticLambda30(commands));
        dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$$ExternalSyntheticLambda31(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$handleAvailablePlayerCommandsChanged$23$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1061lambda$handleAvailablePlayerCommandsChanged$23$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerCb controllerCb, int i) throws RemoteException {
        controllerCb.onDeviceInfoChanged(i, this.playerInfo.deviceInfo);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onMediaButtonEvent(androidx.media3.session.MediaSession.ControllerInfo r8, android.content.Intent r9) {
        /*
            r7 = this;
            android.view.KeyEvent r0 = androidx.media3.session.DefaultActionFactory.getKeyEvent(r9)
            android.content.ComponentName r1 = r9.getComponent()
            java.lang.String r2 = r9.getAction()
            java.lang.String r3 = "android.intent.action.MEDIA_BUTTON"
            boolean r2 = java.util.Objects.equals(r2, r3)
            r3 = 0
            if (r2 == 0) goto L_0x00b4
            if (r1 == 0) goto L_0x0027
            java.lang.String r1 = r1.getPackageName()
            android.content.Context r2 = r7.context
            java.lang.String r2 = r2.getPackageName()
            boolean r1 = java.util.Objects.equals(r1, r2)
            if (r1 == 0) goto L_0x00b4
        L_0x0027:
            if (r0 == 0) goto L_0x00b4
            int r1 = r0.getAction()
            if (r1 == 0) goto L_0x0031
            goto L_0x00b4
        L_0x0031:
            r7.verifyApplicationThread()
            androidx.media3.session.MediaSession$Callback r1 = r7.callback
            androidx.media3.session.MediaSession r2 = r7.instance
            boolean r9 = r1.onMediaButtonEvent(r2, r8, r9)
            r1 = 1
            if (r9 == 0) goto L_0x0040
            return r1
        L_0x0040:
            int r9 = r0.getKeyCode()
            android.content.Context r2 = r7.context
            android.content.pm.PackageManager r2 = r2.getPackageManager()
            java.lang.String r4 = "android.software.leanback"
            boolean r2 = r2.hasSystemFeature(r4)
            r4 = 85
            r5 = 79
            if (r9 == r5) goto L_0x005e
            if (r9 == r4) goto L_0x005e
            androidx.media3.session.MediaSessionImpl$MediaPlayPauseKeyHandler r2 = r7.mediaPlayPauseKeyHandler
            r2.flush()
            goto L_0x0087
        L_0x005e:
            if (r2 != 0) goto L_0x0082
            int r2 = r8.getControllerVersion()
            if (r2 != 0) goto L_0x0082
            int r2 = r0.getRepeatCount()
            if (r2 == 0) goto L_0x006d
            goto L_0x0082
        L_0x006d:
            androidx.media3.session.MediaSessionImpl$MediaPlayPauseKeyHandler r2 = r7.mediaPlayPauseKeyHandler
            boolean r2 = r2.hasPendingPlayPauseTask()
            if (r2 == 0) goto L_0x007c
            androidx.media3.session.MediaSessionImpl$MediaPlayPauseKeyHandler r2 = r7.mediaPlayPauseKeyHandler
            r2.clearPendingPlayPauseTask()
            r2 = r1
            goto L_0x0088
        L_0x007c:
            androidx.media3.session.MediaSessionImpl$MediaPlayPauseKeyHandler r9 = r7.mediaPlayPauseKeyHandler
            r9.setPendingPlayPauseTask(r8, r0)
            return r1
        L_0x0082:
            androidx.media3.session.MediaSessionImpl$MediaPlayPauseKeyHandler r2 = r7.mediaPlayPauseKeyHandler
            r2.flush()
        L_0x0087:
            r2 = r3
        L_0x0088:
            boolean r6 = r7.isMediaNotificationControllerConnected()
            if (r6 != 0) goto L_0x00af
            if (r9 == r4) goto L_0x0092
            if (r9 != r5) goto L_0x009a
        L_0x0092:
            if (r2 == 0) goto L_0x009a
            androidx.media3.session.MediaSessionLegacyStub r8 = r7.sessionLegacyStub
            r8.onSkipToNext()
            return r1
        L_0x009a:
            int r8 = r8.getControllerVersion()
            if (r8 == 0) goto L_0x00ae
            androidx.media3.session.MediaSessionLegacyStub r8 = r7.sessionLegacyStub
            androidx.media3.session.legacy.MediaSessionCompat r8 = r8.getSessionCompat()
            androidx.media3.session.legacy.MediaControllerCompat r8 = r8.getController()
            r8.dispatchMediaButtonEvent(r0)
            return r1
        L_0x00ae:
            return r3
        L_0x00af:
            boolean r8 = r7.applyMediaButtonKeyEvent(r0, r2)
            return r8
        L_0x00b4:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaSessionImpl.onMediaButtonEvent(androidx.media3.session.MediaSession$ControllerInfo, android.content.Intent):boolean");
    }

    /* access modifiers changed from: private */
    public boolean applyMediaButtonKeyEvent(KeyEvent keyEvent, boolean z) {
        Runnable runnable;
        MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) Assertions.checkNotNull(this.instance.getMediaNotificationControllerInfo());
        int keyCode = keyEvent.getKeyCode();
        if ((keyCode == 85 || keyCode == 79) && z) {
            keyCode = 87;
        }
        if (keyCode == 126) {
            runnable = new MediaSessionImpl$$ExternalSyntheticLambda21(this, controllerInfo);
        } else if (keyCode != 127) {
            if (keyCode != 272) {
                if (keyCode != 273) {
                    switch (keyCode) {
                        case JpegTranscoderUtils.DEFAULT_JPEG_QUALITY /*85*/:
                            if (!getPlayerWrapper().getPlayWhenReady()) {
                                runnable = new MediaSessionImpl$$ExternalSyntheticLambda20(this, controllerInfo);
                                break;
                            } else {
                                runnable = new MediaSessionImpl$$ExternalSyntheticLambda19(this, controllerInfo);
                                break;
                            }
                        case 86:
                            runnable = new MediaSessionImpl$$ExternalSyntheticLambda28(this, controllerInfo);
                            break;
                        case 87:
                            break;
                        case 88:
                            break;
                        case TsExtractor.TS_STREAM_TYPE_DVBSUBS:
                            runnable = new MediaSessionImpl$$ExternalSyntheticLambda27(this, controllerInfo);
                            break;
                        case 90:
                            runnable = new MediaSessionImpl$$ExternalSyntheticLambda26(this, controllerInfo);
                            break;
                        default:
                            return false;
                    }
                }
                runnable = new MediaSessionImpl$$ExternalSyntheticLambda25(this, controllerInfo);
            }
            runnable = new MediaSessionImpl$$ExternalSyntheticLambda24(this, controllerInfo);
        } else {
            runnable = new MediaSessionImpl$$ExternalSyntheticLambda23(this, controllerInfo);
        }
        Util.postOrRun(getApplicationHandler(), new MediaSessionImpl$$ExternalSyntheticLambda29(this, runnable, controllerInfo));
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyMediaButtonKeyEvent$24$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1050lambda$applyMediaButtonKeyEvent$24$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.pauseForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyMediaButtonKeyEvent$25$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1051lambda$applyMediaButtonKeyEvent$25$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.playForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyMediaButtonKeyEvent$26$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1052lambda$applyMediaButtonKeyEvent$26$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.playForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyMediaButtonKeyEvent$27$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1053lambda$applyMediaButtonKeyEvent$27$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.pauseForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyMediaButtonKeyEvent$28$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1054lambda$applyMediaButtonKeyEvent$28$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.seekToNextForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyMediaButtonKeyEvent$29$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1055lambda$applyMediaButtonKeyEvent$29$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.seekToPreviousForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyMediaButtonKeyEvent$30$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1056lambda$applyMediaButtonKeyEvent$30$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.seekForwardForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyMediaButtonKeyEvent$31$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1057lambda$applyMediaButtonKeyEvent$31$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.seekBackForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyMediaButtonKeyEvent$32$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1058lambda$applyMediaButtonKeyEvent$32$androidxmedia3sessionMediaSessionImpl(MediaSession.ControllerInfo controllerInfo) {
        this.sessionStub.stopForControllerInfo(controllerInfo, Integer.MIN_VALUE);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$applyMediaButtonKeyEvent$33$androidx-media3-session-MediaSessionImpl  reason: not valid java name */
    public /* synthetic */ void m1059lambda$applyMediaButtonKeyEvent$33$androidxmedia3sessionMediaSessionImpl(Runnable runnable, MediaSession.ControllerInfo controllerInfo) {
        runnable.run();
        this.sessionStub.getConnectedControllersManager().flushCommandQueue(controllerInfo);
    }

    /* access modifiers changed from: private */
    public void postOrRunOnApplicationHandler(Runnable runnable) {
        Util.postOrRun(getApplicationHandler(), runnable);
    }

    private static class PlayerListener implements Player.Listener {
        private final WeakReference<PlayerWrapper> player;
        private final WeakReference<MediaSessionImpl> session;

        public PlayerListener(MediaSessionImpl mediaSessionImpl, PlayerWrapper playerWrapper) {
            this.session = new WeakReference<>(mediaSessionImpl);
            this.player = new WeakReference<>(playerWrapper);
        }

        public void onPlayerError(PlaybackException playbackException) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithPlayerError(playbackException);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda19(playbackException));
                }
            }
        }

        public void onMediaItemTransition(MediaItem mediaItem, int i) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithMediaItemTransitionReason(i);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda17(mediaItem, i));
                }
            }
        }

        public void onPlayWhenReadyChanged(boolean z, int i) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithPlayWhenReady(z, i, session2.playerInfo.playbackSuppressionReason);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda20(z, i));
                }
            }
        }

        public void onPlaybackSuppressionReasonChanged(int i) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithPlayWhenReady(session2.playerInfo.playWhenReady, session2.playerInfo.playWhenReadyChangeReason, i);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda7(i));
                }
            }
        }

        public void onPlaybackStateChanged(int i) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                PlayerWrapper playerWrapper = (PlayerWrapper) this.player.get();
                if (playerWrapper != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithPlaybackState(i, playerWrapper.getPlayerError());
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda11(i, playerWrapper));
                }
            }
        }

        public void onIsPlayingChanged(boolean z) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithIsPlaying(z);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda8(z));
                    session2.schedulePeriodicSessionPositionInfoChanges();
                }
            }
        }

        public void onIsLoadingChanged(boolean z) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithIsLoading(z);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda15(z));
                    session2.schedulePeriodicSessionPositionInfoChanges();
                }
            }
        }

        public void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithPositionInfos(positionInfo, positionInfo2, i);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda12(positionInfo, positionInfo2, i));
                }
            }
        }

        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithPlaybackParameters(playbackParameters);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda23(playbackParameters));
                }
            }
        }

        public void onSeekBackIncrementChanged(long j) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithSeekBackIncrement(j);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda2(j));
                }
            }
        }

        public void onSeekForwardIncrementChanged(long j) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithSeekForwardIncrement(j);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda0(j));
                }
            }
        }

        public void onTimelineChanged(Timeline timeline, int i) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                PlayerWrapper playerWrapper = (PlayerWrapper) this.player.get();
                if (playerWrapper != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithTimelineAndSessionPositionInfo(timeline, playerWrapper.createSessionPositionInfoForBundling(), i);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(false, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda13(timeline, i));
                }
            }
        }

        public void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithPlaylistMetadata(mediaMetadata);
                session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda1(mediaMetadata));
            }
        }

        public void onRepeatModeChanged(int i) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithRepeatMode(i);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda10(i));
                }
            }
        }

        public void onShuffleModeEnabledChanged(boolean z) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithShuffleModeEnabled(z);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda4(z));
                }
            }
        }

        public void onAudioAttributesChanged(AudioAttributes audioAttributes) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithAudioAttributes(audioAttributes);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda5(audioAttributes));
                }
            }
        }

        public void onVideoSizeChanged(VideoSize videoSize) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithVideoSize(videoSize);
                session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda9(videoSize));
            }
        }

        public void onVolumeChanged(float f) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithVolume(f);
                session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda6(f));
            }
        }

        public void onCues(CueGroup cueGroup) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = new PlayerInfo.Builder(session2.playerInfo).setCues(cueGroup).build();
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                }
            }
        }

        public void onDeviceInfoChanged(DeviceInfo deviceInfo) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithDeviceInfo(deviceInfo);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda16(deviceInfo));
                }
            }
        }

        public void onDeviceVolumeChanged(int i, boolean z) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithDeviceVolume(i, z);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda22(i, z));
                }
            }
        }

        public void onAvailableCommandsChanged(Player.Commands commands) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    session2.handleAvailablePlayerCommandsChanged(commands);
                }
            }
        }

        public void onTracksChanged(Tracks tracks) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithCurrentTracks(tracks);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, false);
                    session2.dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda3(tracks));
                }
            }
        }

        public void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithTrackSelectionParameters(trackSelectionParameters);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda14(trackSelectionParameters));
                }
            }
        }

        public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithMediaMetadata(mediaMetadata);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                    session2.dispatchRemoteControllerTaskToLegacyStub(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda21(mediaMetadata));
                }
            }
        }

        public void onRenderedFirstFrame() {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                session2.dispatchRemoteControllerTaskWithoutReturn(new MediaSessionImpl$PlayerListener$$ExternalSyntheticLambda18());
            }
        }

        public void onMaxSeekToPreviousPositionChanged(long j) {
            MediaSessionImpl session2 = getSession();
            if (session2 != null) {
                session2.verifyApplicationThread();
                if (((PlayerWrapper) this.player.get()) != null) {
                    PlayerInfo unused = session2.playerInfo = session2.playerInfo.copyWithMaxSeekToPreviousPositionMs(j);
                    session2.onPlayerInfoChangedHandler.sendPlayerInfoChangedMessage(true, true);
                }
            }
        }

        private MediaSessionImpl getSession() {
            return (MediaSessionImpl) this.session.get();
        }
    }

    private class MediaPlayPauseKeyHandler extends Handler {
        private Runnable playPauseTask;

        public MediaPlayPauseKeyHandler(Looper looper) {
            super(looper);
        }

        public void setPendingPlayPauseTask(MediaSession.ControllerInfo controllerInfo, KeyEvent keyEvent) {
            MediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0 mediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0 = new MediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0(this, controllerInfo, keyEvent);
            this.playPauseTask = mediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0;
            postDelayed(mediaSessionImpl$MediaPlayPauseKeyHandler$$ExternalSyntheticLambda0, (long) ViewConfiguration.getDoubleTapTimeout());
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$setPendingPlayPauseTask$0$androidx-media3-session-MediaSessionImpl$MediaPlayPauseKeyHandler  reason: not valid java name */
        public /* synthetic */ void m1066lambda$setPendingPlayPauseTask$0$androidxmedia3sessionMediaSessionImpl$MediaPlayPauseKeyHandler(MediaSession.ControllerInfo controllerInfo, KeyEvent keyEvent) {
            if (MediaSessionImpl.this.isMediaNotificationController(controllerInfo)) {
                boolean unused = MediaSessionImpl.this.applyMediaButtonKeyEvent(keyEvent, false);
            } else {
                MediaSessionImpl.this.sessionLegacyStub.handleMediaPlayPauseOnHandler((MediaSessionManager.RemoteUserInfo) Assertions.checkNotNull(controllerInfo.getRemoteUserInfo()));
            }
            this.playPauseTask = null;
        }

        public Runnable clearPendingPlayPauseTask() {
            Runnable runnable = this.playPauseTask;
            if (runnable == null) {
                return null;
            }
            removeCallbacks(runnable);
            Runnable runnable2 = this.playPauseTask;
            this.playPauseTask = null;
            return runnable2;
        }

        public boolean hasPendingPlayPauseTask() {
            return this.playPauseTask != null;
        }

        public void flush() {
            Runnable clearPendingPlayPauseTask = clearPendingPlayPauseTask();
            if (clearPendingPlayPauseTask != null) {
                Util.postOrRun(this, clearPendingPlayPauseTask);
            }
        }
    }

    private class PlayerInfoChangedHandler extends Handler {
        private static final int MSG_PLAYER_INFO_CHANGED = 1;
        private boolean excludeTimeline = true;
        private boolean excludeTracks = true;

        public PlayerInfoChangedHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                MediaSessionImpl mediaSessionImpl = MediaSessionImpl.this;
                PlayerInfo unused = mediaSessionImpl.playerInfo = mediaSessionImpl.playerInfo.copyWithTimelineAndSessionPositionInfo(MediaSessionImpl.this.getPlayerWrapper().getCurrentTimelineWithCommandCheck(), MediaSessionImpl.this.getPlayerWrapper().createSessionPositionInfoForBundling(), MediaSessionImpl.this.playerInfo.timelineChangeReason);
                MediaSessionImpl mediaSessionImpl2 = MediaSessionImpl.this;
                mediaSessionImpl2.dispatchOnPlayerInfoChanged(mediaSessionImpl2.playerInfo, this.excludeTimeline, this.excludeTracks);
                this.excludeTimeline = true;
                this.excludeTracks = true;
                return;
            }
            throw new IllegalStateException("Invalid message what=" + message.what);
        }

        public boolean hasPendingPlayerInfoChangedUpdate() {
            return hasMessages(1);
        }

        public void sendPlayerInfoChangedMessage(boolean z, boolean z2) {
            boolean z3 = false;
            this.excludeTimeline = this.excludeTimeline && z;
            if (this.excludeTracks && z2) {
                z3 = true;
            }
            this.excludeTracks = z3;
            if (!hasMessages(1)) {
                sendEmptyMessage(1);
            }
        }
    }
}
