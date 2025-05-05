package androidx.media3.session;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaDescriptionCompat;
import androidx.media3.session.legacy.MediaMetadataCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import androidx.media3.session.legacy.RatingCompat;
import androidx.media3.session.legacy.VolumeProviderCompat;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

class MediaSessionLegacyStub extends MediaSessionCompat.Callback {
    private static final int DEFAULT_CONNECTION_TIMEOUT_MS = 300000;
    private static final String DEFAULT_MEDIA_SESSION_TAG_DELIM = ".";
    private static final String DEFAULT_MEDIA_SESSION_TAG_PREFIX = "androidx.media3.session.id";
    private static final int PENDING_INTENT_FLAG_MUTABLE = (Util.SDK_INT >= 31 ? 33554432 : 0);
    private static final String TAG = "MediaSessionLegacyStub";
    private final ComponentName broadcastReceiverComponentName;
    private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;
    private final ConnectionTimeoutHandler connectionTimeoutHandler;
    private volatile long connectionTimeoutMs;
    private final ControllerLegacyCbForBroadcast controllerLegacyCbForBroadcast = new ControllerLegacyCbForBroadcast();
    /* access modifiers changed from: private */
    public FutureCallback<Bitmap> pendingBitmapLoadCallback;
    private final MediaButtonReceiver runtimeBroadcastReceiver;
    /* access modifiers changed from: private */
    public final MediaSessionCompat sessionCompat;
    private int sessionFlags;
    /* access modifiers changed from: private */
    public final MediaSessionImpl sessionImpl;
    private final MediaSessionManager sessionManager;
    /* access modifiers changed from: private */
    public VolumeProviderCompat volumeProviderCompat;

    private interface SessionTask {
        void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException;
    }

    private static <T> void ignoreFuture(Future<T> future) {
    }

    public void onSetCaptioningEnabled(boolean z) {
    }

    public MediaSessionLegacyStub(MediaSessionImpl mediaSessionImpl, Uri uri, Handler handler, Bundle bundle) {
        boolean z;
        ComponentName componentName;
        PendingIntent pendingIntent;
        this.sessionImpl = mediaSessionImpl;
        Context context = mediaSessionImpl.getContext();
        this.sessionManager = MediaSessionManager.getSessionManager(context);
        ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager2 = new ConnectedControllersManager<>(mediaSessionImpl);
        this.connectedControllersManager = connectedControllersManager2;
        this.connectionTimeoutMs = 300000;
        this.connectionTimeoutHandler = new ConnectionTimeoutHandler(mediaSessionImpl.getApplicationHandler().getLooper(), connectedControllersManager2);
        ComponentName queryPackageManagerForMediaButtonReceiver = queryPackageManagerForMediaButtonReceiver(context);
        this.broadcastReceiverComponentName = queryPackageManagerForMediaButtonReceiver;
        if (queryPackageManagerForMediaButtonReceiver == null || Util.SDK_INT < 31) {
            componentName = getServiceComponentByAction(context, MediaLibraryService.SERVICE_INTERFACE);
            componentName = componentName == null ? getServiceComponentByAction(context, MediaSessionService.SERVICE_INTERFACE) : componentName;
            z = componentName != null && !Objects.equals(componentName, queryPackageManagerForMediaButtonReceiver);
        } else {
            z = false;
            componentName = queryPackageManagerForMediaButtonReceiver;
        }
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON", uri);
        if (componentName == null) {
            MediaButtonReceiver mediaButtonReceiver = new MediaButtonReceiver();
            this.runtimeBroadcastReceiver = mediaButtonReceiver;
            IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_BUTTON");
            intentFilter.addDataScheme((String) Util.castNonNull(uri.getScheme()));
            Util.registerReceiverNotExported(context, mediaButtonReceiver, intentFilter);
            intent.setPackage(context.getPackageName());
            pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
            componentName = new ComponentName(context, context.getClass());
        } else {
            intent.setComponent(componentName);
            if (!z) {
                pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
            } else if (Util.SDK_INT >= 26) {
                pendingIntent = PendingIntent.getForegroundService(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
            } else {
                pendingIntent = PendingIntent.getService(context, 0, intent, PENDING_INTENT_FLAG_MUTABLE);
            }
            this.runtimeBroadcastReceiver = null;
        }
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, TextUtils.join(DEFAULT_MEDIA_SESSION_TAG_DELIM, new String[]{DEFAULT_MEDIA_SESSION_TAG_PREFIX, mediaSessionImpl.getId()}), Util.SDK_INT < 31 ? componentName : null, Util.SDK_INT >= 31 ? null : pendingIntent, bundle);
        this.sessionCompat = mediaSessionCompat;
        if (Util.SDK_INT >= 31 && queryPackageManagerForMediaButtonReceiver != null) {
            Api31.setMediaButtonBroadcastReceiver(mediaSessionCompat, queryPackageManagerForMediaButtonReceiver);
        }
        PendingIntent sessionActivity = mediaSessionImpl.getSessionActivity();
        if (sessionActivity != null) {
            mediaSessionCompat.setSessionActivity(sessionActivity);
        }
        mediaSessionCompat.setCallback(this, handler);
    }

    private static ComponentName queryPackageManagerForMediaButtonReceiver(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 0);
        if (queryBroadcastReceivers.size() == 1) {
            ResolveInfo resolveInfo = queryBroadcastReceivers.get(0);
            return new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        } else if (queryBroadcastReceivers.isEmpty()) {
            return null;
        } else {
            throw new IllegalStateException("Expected 1 broadcast receiver that handles android.intent.action.MEDIA_BUTTON, found " + queryBroadcastReceivers.size());
        }
    }

    public void start() {
        this.sessionCompat.setActive(true);
    }

    public void release() {
        if (Util.SDK_INT < 31) {
            if (this.broadcastReceiverComponentName == null) {
                setMediaButtonReceiver(this.sessionCompat, (PendingIntent) null);
            } else {
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON", this.sessionImpl.getUri());
                intent.setComponent(this.broadcastReceiverComponentName);
                setMediaButtonReceiver(this.sessionCompat, PendingIntent.getBroadcast(this.sessionImpl.getContext(), 0, intent, PENDING_INTENT_FLAG_MUTABLE));
            }
        }
        if (this.runtimeBroadcastReceiver != null) {
            this.sessionImpl.getContext().unregisterReceiver(this.runtimeBroadcastReceiver);
        }
        this.sessionCompat.release();
    }

    public MediaSessionCompat getSessionCompat() {
        return this.sessionCompat;
    }

    public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        Assertions.checkStateNotNull(str);
        if (!str.equals("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST")) {
            if (!str.equals("androidx.media3.session.SESSION_COMMAND_REQUEST_SESSION3_TOKEN") || resultReceiver == null) {
                SessionCommand sessionCommand = new SessionCommand(str, Bundle.EMPTY);
                dispatchSessionTaskWithSessionCommand(sessionCommand, (SessionTask) new MediaSessionLegacyStub$$ExternalSyntheticLambda20(this, sessionCommand, bundle, resultReceiver));
                return;
            }
            resultReceiver.send(0, this.sessionImpl.getToken().toBundle());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCommand$0$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1072lambda$onCommand$0$androidxmedia3sessionMediaSessionLegacyStub(SessionCommand sessionCommand, Bundle bundle, ResultReceiver resultReceiver, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl;
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        ListenableFuture<SessionResult> onCustomCommandOnHandler = mediaSessionImpl.onCustomCommandOnHandler(controllerInfo, sessionCommand, bundle);
        if (resultReceiver != null) {
            sendCustomCommandResultWhenReady(resultReceiver, onCustomCommandOnHandler);
        } else {
            ignoreFuture(onCustomCommandOnHandler);
        }
    }

    public void onCustomAction(String str, Bundle bundle) {
        if (!str.equals("androidx.media3.session.SESSION_COMMAND_MEDIA3_PLAY_REQUEST")) {
            SessionCommand sessionCommand = new SessionCommand(str, Bundle.EMPTY);
            dispatchSessionTaskWithSessionCommand(sessionCommand, (SessionTask) new MediaSessionLegacyStub$$ExternalSyntheticLambda16(this, sessionCommand, bundle));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCustomAction$1$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1073lambda$onCustomAction$1$androidxmedia3sessionMediaSessionLegacyStub(SessionCommand sessionCommand, Bundle bundle, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        MediaSessionImpl mediaSessionImpl = this.sessionImpl;
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        ignoreFuture(mediaSessionImpl.onCustomCommandOnHandler(controllerInfo, sessionCommand, bundle));
    }

    public boolean onMediaButtonEvent(Intent intent) {
        return this.sessionImpl.onMediaButtonEvent(new MediaSession.ControllerInfo((MediaSessionManager.RemoteUserInfo) Assertions.checkNotNull(this.sessionCompat.getCurrentControllerInfo()), 0, 0, false, (MediaSession.ControllerCb) null, Bundle.EMPTY, 0), intent);
    }

    /* access modifiers changed from: private */
    public void maybeUpdateFlags(PlayerWrapper playerWrapper) {
        int i = playerWrapper.isCommandAvailable(20) ? 4 : 0;
        if (this.sessionFlags != i) {
            this.sessionFlags = i;
            this.sessionCompat.setFlags(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void handleMediaPlayPauseOnHandler(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        dispatchSessionTaskWithPlayerCommand(1, new MediaSessionLegacyStub$$ExternalSyntheticLambda8(this), remoteUserInfo, true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$handleMediaPlayPauseOnHandler$2$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1069lambda$handleMediaPlayPauseOnHandler$2$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        Util.handlePlayPauseButtonAction(this.sessionImpl.getPlayerWrapper(), this.sessionImpl.shouldPlayIfSuppressed());
    }

    public void onPrepare() {
        dispatchSessionTaskWithPlayerCommand(2, new MediaSessionLegacyStub$$ExternalSyntheticLambda21(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrepare$3$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1077lambda$onPrepare$3$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().prepare();
    }

    public void onPrepareFromMediaId(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(str, (Uri) null, (String) null, bundle), false);
    }

    public void onPrepareFromSearch(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest((String) null, (Uri) null, str, bundle), false);
    }

    public void onPrepareFromUri(Uri uri, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest((String) null, uri, (String) null, bundle), false);
    }

    public void onPlay() {
        dispatchSessionTaskWithPlayerCommand(1, new MediaSessionLegacyStub$$ExternalSyntheticLambda5(this), this.sessionCompat.getCurrentControllerInfo(), false);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPlay$4$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1076lambda$onPlay$4$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.handleMediaControllerPlayRequest(controllerInfo, true);
    }

    public void onPlayFromMediaId(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest(str, (Uri) null, (String) null, bundle), true);
    }

    public void onPlayFromSearch(String str, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest((String) null, (Uri) null, str, bundle), true);
    }

    public void onPlayFromUri(Uri uri, Bundle bundle) {
        handleMediaRequest(createMediaItemForMediaRequest((String) null, uri, (String) null, bundle), true);
    }

    public void onPause() {
        dispatchSessionTaskWithPlayerCommand(1, new MediaSessionLegacyStub$$ExternalSyntheticLambda1(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPause$5$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1075lambda$onPause$5$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        Util.handlePauseButtonAction(this.sessionImpl.getPlayerWrapper());
    }

    public void onStop() {
        dispatchSessionTaskWithPlayerCommand(3, new MediaSessionLegacyStub$$ExternalSyntheticLambda17(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onStop$6$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1090lambda$onStop$6$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().stop();
    }

    public void onSeekTo(long j) {
        dispatchSessionTaskWithPlayerCommand(5, new MediaSessionLegacyStub$$ExternalSyntheticLambda3(this, j), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSeekTo$7$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1080lambda$onSeekTo$7$androidxmedia3sessionMediaSessionLegacyStub(long j, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekTo(j);
    }

    public void onSkipToNext() {
        if (this.sessionImpl.getPlayerWrapper().isCommandAvailable(9)) {
            dispatchSessionTaskWithPlayerCommand(9, new MediaSessionLegacyStub$$ExternalSyntheticLambda24(this), this.sessionCompat.getCurrentControllerInfo(), true);
        } else {
            dispatchSessionTaskWithPlayerCommand(8, new MediaSessionLegacyStub$$ExternalSyntheticLambda25(this), this.sessionCompat.getCurrentControllerInfo(), true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSkipToNext$8$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1085lambda$onSkipToNext$8$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToNext();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSkipToNext$9$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1086lambda$onSkipToNext$9$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToNextMediaItem();
    }

    public void onSkipToPrevious() {
        if (this.sessionImpl.getPlayerWrapper().isCommandAvailable(7)) {
            dispatchSessionTaskWithPlayerCommand(7, new MediaSessionLegacyStub$$ExternalSyntheticLambda10(this), this.sessionCompat.getCurrentControllerInfo(), true);
        } else {
            dispatchSessionTaskWithPlayerCommand(6, new MediaSessionLegacyStub$$ExternalSyntheticLambda12(this), this.sessionCompat.getCurrentControllerInfo(), true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSkipToPrevious$10$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1087lambda$onSkipToPrevious$10$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToPrevious();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSkipToPrevious$11$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1088lambda$onSkipToPrevious$11$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToPreviousMediaItem();
    }

    public void onSetPlaybackSpeed(float f) {
        if (f > 0.0f) {
            dispatchSessionTaskWithPlayerCommand(13, new MediaSessionLegacyStub$$ExternalSyntheticLambda14(this, f), this.sessionCompat.getCurrentControllerInfo(), true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSetPlaybackSpeed$12$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1081lambda$onSetPlaybackSpeed$12$androidxmedia3sessionMediaSessionLegacyStub(float f, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().setPlaybackSpeed(f);
    }

    public void onSkipToQueueItem(long j) {
        if (j >= 0) {
            dispatchSessionTaskWithPlayerCommand(10, new MediaSessionLegacyStub$$ExternalSyntheticLambda27(this, j), this.sessionCompat.getCurrentControllerInfo(), true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSkipToQueueItem$13$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1089lambda$onSkipToQueueItem$13$androidxmedia3sessionMediaSessionLegacyStub(long j, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekToDefaultPosition((int) j);
    }

    public void onFastForward() {
        dispatchSessionTaskWithPlayerCommand(12, new MediaSessionLegacyStub$$ExternalSyntheticLambda4(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onFastForward$14$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1074lambda$onFastForward$14$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekForward();
    }

    public void onRewind() {
        dispatchSessionTaskWithPlayerCommand(11, new MediaSessionLegacyStub$$ExternalSyntheticLambda9(this), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onRewind$15$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1079lambda$onRewind$15$androidxmedia3sessionMediaSessionLegacyStub(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().seekBack();
    }

    public void onSetRating(RatingCompat ratingCompat) {
        onSetRating(ratingCompat, (Bundle) null);
    }

    public void onSetRating(RatingCompat ratingCompat, Bundle bundle) {
        Rating convertToRating = LegacyConversions.convertToRating(ratingCompat);
        if (convertToRating == null) {
            Log.w(TAG, "Ignoring invalid RatingCompat " + ratingCompat);
        } else {
            dispatchSessionTaskWithSessionCommand((int) SessionCommand.COMMAND_CODE_SESSION_SET_RATING, (SessionTask) new MediaSessionLegacyStub$$ExternalSyntheticLambda13(this, convertToRating));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSetRating$16$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1082lambda$onSetRating$16$androidxmedia3sessionMediaSessionLegacyStub(Rating rating, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        MediaItem currentMediaItemWithCommandCheck = this.sessionImpl.getPlayerWrapper().getCurrentMediaItemWithCommandCheck();
        if (currentMediaItemWithCommandCheck != null) {
            ignoreFuture(this.sessionImpl.onSetRatingOnHandler(controllerInfo, currentMediaItemWithCommandCheck.mediaId, rating));
        }
    }

    public void onSetRepeatMode(int i) {
        dispatchSessionTaskWithPlayerCommand(15, new MediaSessionLegacyStub$$ExternalSyntheticLambda6(this, i), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSetRepeatMode$17$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1083lambda$onSetRepeatMode$17$androidxmedia3sessionMediaSessionLegacyStub(int i, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().setRepeatMode(LegacyConversions.convertToRepeatMode(i));
    }

    public void onSetShuffleMode(int i) {
        dispatchSessionTaskWithPlayerCommand(14, new MediaSessionLegacyStub$$ExternalSyntheticLambda22(this, i), this.sessionCompat.getCurrentControllerInfo(), true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSetShuffleMode$18$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1084lambda$onSetShuffleMode$18$androidxmedia3sessionMediaSessionLegacyStub(int i, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        this.sessionImpl.getPlayerWrapper().setShuffleModeEnabled(LegacyConversions.convertToShuffleModeEnabled(i));
    }

    public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        handleOnAddQueueItem(mediaDescriptionCompat, -1);
    }

    public void onAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        handleOnAddQueueItem(mediaDescriptionCompat, i);
    }

    public void onRemoveQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat != null) {
            dispatchSessionTaskWithPlayerCommand(20, new MediaSessionLegacyStub$$ExternalSyntheticLambda26(this, mediaDescriptionCompat), this.sessionCompat.getCurrentControllerInfo(), true);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onRemoveQueueItem$19$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1078lambda$onRemoveQueueItem$19$androidxmedia3sessionMediaSessionLegacyStub(MediaDescriptionCompat mediaDescriptionCompat, MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        String mediaId = mediaDescriptionCompat.getMediaId();
        if (TextUtils.isEmpty(mediaId)) {
            Log.w(TAG, "onRemoveQueueItem(): Media ID shouldn't be null");
            return;
        }
        PlayerWrapper playerWrapper = this.sessionImpl.getPlayerWrapper();
        if (!playerWrapper.isCommandAvailable(17)) {
            Log.w(TAG, "Can't remove item by ID without COMMAND_GET_TIMELINE being available");
            return;
        }
        Timeline currentTimeline = playerWrapper.getCurrentTimeline();
        Timeline.Window window = new Timeline.Window();
        for (int i = 0; i < currentTimeline.getWindowCount(); i++) {
            if (TextUtils.equals(currentTimeline.getWindow(i, window).mediaItem.mediaId, mediaId)) {
                playerWrapper.removeMediaItem(i);
                return;
            }
        }
    }

    public MediaSession.ControllerCb getControllerLegacyCbForBroadcast() {
        return this.controllerLegacyCbForBroadcast;
    }

    public ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }

    /* access modifiers changed from: package-private */
    public boolean canResumePlaybackOnStart() {
        return this.broadcastReceiverComponentName != null;
    }

    private void dispatchSessionTaskWithPlayerCommand(int i, SessionTask sessionTask, MediaSessionManager.RemoteUserInfo remoteUserInfo, boolean z) {
        if (!this.sessionImpl.isReleased()) {
            if (remoteUserInfo == null) {
                Log.d(TAG, "RemoteUserInfo is null, ignoring command=" + i);
            } else {
                Util.postOrRun(this.sessionImpl.getApplicationHandler(), new MediaSessionLegacyStub$$ExternalSyntheticLambda23(this, i, remoteUserInfo, sessionTask, z));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dispatchSessionTaskWithPlayerCommand$21$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1067lambda$dispatchSessionTaskWithPlayerCommand$21$androidxmedia3sessionMediaSessionLegacyStub(int i, MediaSessionManager.RemoteUserInfo remoteUserInfo, SessionTask sessionTask, boolean z) {
        if (!this.sessionImpl.isReleased()) {
            if (!this.sessionCompat.isActive()) {
                Log.w(TAG, "Ignore incoming player command before initialization. command=" + i + ", pid=" + remoteUserInfo.getPid());
                return;
            }
            MediaSession.ControllerInfo tryGetController = tryGetController(remoteUserInfo);
            if (tryGetController != null) {
                if (!this.connectedControllersManager.isPlayerCommandAvailable(tryGetController, i)) {
                    if (i == 1 && !this.sessionImpl.getPlayerWrapper().getPlayWhenReady()) {
                        Log.w(TAG, "Calling play() omitted due to COMMAND_PLAY_PAUSE not being available. If this play command has started the service for instance for playback resumption, this may prevent the service from being started into the foreground.");
                    }
                } else if (this.sessionImpl.onPlayerCommandRequestOnHandler(tryGetController, i) == 0) {
                    this.sessionImpl.callWithControllerForCurrentRequestSet(tryGetController, new MediaSessionLegacyStub$$ExternalSyntheticLambda19(sessionTask, tryGetController)).run();
                    if (z) {
                        this.sessionImpl.onPlayerInteractionFinishedOnHandler(tryGetController, new Player.Commands.Builder().add(i).build());
                    }
                }
            }
        }
    }

    static /* synthetic */ void lambda$dispatchSessionTaskWithPlayerCommand$20(SessionTask sessionTask, MediaSession.ControllerInfo controllerInfo) {
        try {
            sessionTask.run(controllerInfo);
        } catch (RemoteException e) {
            Log.w(TAG, "Exception in " + controllerInfo, e);
        }
    }

    private void dispatchSessionTaskWithSessionCommand(int i, SessionTask sessionTask) {
        dispatchSessionTaskWithSessionCommandInternal((SessionCommand) null, i, sessionTask, this.sessionCompat.getCurrentControllerInfo());
    }

    private void dispatchSessionTaskWithSessionCommand(SessionCommand sessionCommand, SessionTask sessionTask) {
        dispatchSessionTaskWithSessionCommandInternal(sessionCommand, 0, sessionTask, this.sessionCompat.getCurrentControllerInfo());
    }

    private void dispatchSessionTaskWithSessionCommandInternal(SessionCommand sessionCommand, int i, SessionTask sessionTask, MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        if (remoteUserInfo == null) {
            StringBuilder sb = new StringBuilder("RemoteUserInfo is null, ignoring command=");
            Object obj = sessionCommand;
            if (sessionCommand == null) {
                obj = Integer.valueOf(i);
            }
            Log.d(TAG, sb.append(obj).toString());
            return;
        }
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new MediaSessionLegacyStub$$ExternalSyntheticLambda7(this, sessionCommand, i, remoteUserInfo, sessionTask));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$dispatchSessionTaskWithSessionCommandInternal$22$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1068lambda$dispatchSessionTaskWithSessionCommandInternal$22$androidxmedia3sessionMediaSessionLegacyStub(SessionCommand sessionCommand, int i, MediaSessionManager.RemoteUserInfo remoteUserInfo, SessionTask sessionTask) {
        if (!this.sessionImpl.isReleased()) {
            if (!this.sessionCompat.isActive()) {
                Log.w(TAG, "Ignore incoming session command before initialization. command=" + (sessionCommand == null ? Integer.valueOf(i) : sessionCommand.customAction) + ", pid=" + remoteUserInfo.getPid());
                return;
            }
            MediaSession.ControllerInfo tryGetController = tryGetController(remoteUserInfo);
            if (tryGetController != null) {
                if (sessionCommand != null) {
                    if (!this.connectedControllersManager.isSessionCommandAvailable(tryGetController, sessionCommand)) {
                        return;
                    }
                } else if (!this.connectedControllersManager.isSessionCommandAvailable(tryGetController, i)) {
                    return;
                }
                try {
                    sessionTask.run(tryGetController);
                } catch (RemoteException e) {
                    Log.w(TAG, "Exception in " + tryGetController, e);
                }
            }
        }
    }

    private MediaSession.ControllerInfo tryGetController(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        MediaSession.ControllerInfo controller = this.connectedControllersManager.getController(remoteUserInfo);
        if (controller == null) {
            ControllerLegacyCb controllerLegacyCb = new ControllerLegacyCb(remoteUserInfo);
            MediaSession.ControllerInfo controllerInfo = new MediaSession.ControllerInfo(remoteUserInfo, 0, 0, this.sessionManager.isTrustedForMediaControl(remoteUserInfo), controllerLegacyCb, Bundle.EMPTY, 0);
            MediaSession.ConnectionResult onConnectOnHandler = this.sessionImpl.onConnectOnHandler(controllerInfo);
            if (!onConnectOnHandler.isAccepted) {
                try {
                    controllerLegacyCb.onDisconnected(0);
                    return null;
                } catch (RemoteException unused) {
                    return null;
                }
            } else {
                this.connectedControllersManager.addController(controllerInfo.getRemoteUserInfo(), controllerInfo, onConnectOnHandler.availableSessionCommands, onConnectOnHandler.availablePlayerCommands);
                controller = controllerInfo;
            }
        }
        this.connectionTimeoutHandler.disconnectControllerAfterTimeout(controller, this.connectionTimeoutMs);
        return controller;
    }

    public void setLegacyControllerDisconnectTimeoutMs(long j) {
        this.connectionTimeoutMs = j;
    }

    public void updateLegacySessionPlaybackState(PlayerWrapper playerWrapper) {
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new MediaSessionLegacyStub$$ExternalSyntheticLambda2(this, playerWrapper));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateLegacySessionPlaybackState$23$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1091lambda$updateLegacySessionPlaybackState$23$androidxmedia3sessionMediaSessionLegacyStub(PlayerWrapper playerWrapper) {
        this.sessionCompat.setPlaybackState(playerWrapper.createPlaybackStateCompat());
    }

    public void updateLegacySessionPlaybackStateAndQueue(PlayerWrapper playerWrapper) {
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new MediaSessionLegacyStub$$ExternalSyntheticLambda11(this, playerWrapper));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateLegacySessionPlaybackStateAndQueue$24$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1092lambda$updateLegacySessionPlaybackStateAndQueue$24$androidxmedia3sessionMediaSessionLegacyStub(PlayerWrapper playerWrapper) {
        Timeline timeline;
        this.sessionCompat.setPlaybackState(playerWrapper.createPlaybackStateCompat());
        ControllerLegacyCbForBroadcast controllerLegacyCbForBroadcast2 = this.controllerLegacyCbForBroadcast;
        if (playerWrapper.getAvailableCommands().contains(17)) {
            timeline = playerWrapper.getCurrentTimeline();
        } else {
            timeline = Timeline.EMPTY;
        }
        controllerLegacyCbForBroadcast2.updateQueue(timeline);
    }

    private void handleMediaRequest(MediaItem mediaItem, boolean z) {
        dispatchSessionTaskWithPlayerCommand(31, new MediaSessionLegacyStub$$ExternalSyntheticLambda15(this, mediaItem, z), this.sessionCompat.getCurrentControllerInfo(), false);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$handleMediaRequest$25$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1070lambda$handleMediaRequest$25$androidxmedia3sessionMediaSessionLegacyStub(MediaItem mediaItem, final boolean z, final MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        Futures.addCallback(this.sessionImpl.onSetMediaItemsOnHandler(controllerInfo, ImmutableList.of(mediaItem), -1, C.TIME_UNSET), new FutureCallback<MediaSession.MediaItemsWithStartPosition>() {
            public void onFailure(Throwable th) {
            }

            public void onSuccess(MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
                Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
                MediaSessionImpl access$100 = MediaSessionLegacyStub.this.sessionImpl;
                MediaSession.ControllerInfo controllerInfo = controllerInfo;
                Util.postOrRun(applicationHandler, access$100.callWithControllerForCurrentRequestSet(controllerInfo, new MediaSessionLegacyStub$1$$ExternalSyntheticLambda0(this, mediaItemsWithStartPosition, z, controllerInfo)));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onSuccess$0$androidx-media3-session-MediaSessionLegacyStub$1  reason: not valid java name */
            public /* synthetic */ void m1093lambda$onSuccess$0$androidxmedia3sessionMediaSessionLegacyStub$1(MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition, boolean z, MediaSession.ControllerInfo controllerInfo) {
                PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
                MediaUtils.setMediaItemsWithStartIndexAndPosition(playerWrapper, mediaItemsWithStartPosition);
                int playbackState = playerWrapper.getPlaybackState();
                if (playbackState == 1) {
                    playerWrapper.prepareIfCommandAvailable();
                } else if (playbackState == 4) {
                    playerWrapper.seekToDefaultPositionIfCommandAvailable();
                }
                if (z) {
                    playerWrapper.playIfCommandAvailable();
                }
                MediaSessionLegacyStub.this.sessionImpl.onPlayerInteractionFinishedOnHandler(controllerInfo, new Player.Commands.Builder().addAll(31, 2).addIf(1, z).build());
            }
        }, MoreExecutors.directExecutor());
    }

    private void handleOnAddQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        if (mediaDescriptionCompat == null) {
            return;
        }
        if (i == -1 || i >= 0) {
            dispatchSessionTaskWithPlayerCommand(20, new MediaSessionLegacyStub$$ExternalSyntheticLambda18(this, mediaDescriptionCompat, i), this.sessionCompat.getCurrentControllerInfo(), false);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$handleOnAddQueueItem$26$androidx-media3-session-MediaSessionLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1071lambda$handleOnAddQueueItem$26$androidxmedia3sessionMediaSessionLegacyStub(MediaDescriptionCompat mediaDescriptionCompat, final int i, final MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        if (TextUtils.isEmpty(mediaDescriptionCompat.getMediaId())) {
            Log.w(TAG, "onAddQueueItem(): Media ID shouldn't be empty");
            return;
        }
        Futures.addCallback(this.sessionImpl.onAddMediaItemsOnHandler(controllerInfo, ImmutableList.of(LegacyConversions.convertToMediaItem(mediaDescriptionCompat))), new FutureCallback<List<MediaItem>>() {
            public void onFailure(Throwable th) {
            }

            public void onSuccess(List<MediaItem> list) {
                Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
                MediaSessionImpl access$100 = MediaSessionLegacyStub.this.sessionImpl;
                MediaSession.ControllerInfo controllerInfo = controllerInfo;
                Util.postOrRun(applicationHandler, access$100.callWithControllerForCurrentRequestSet(controllerInfo, new MediaSessionLegacyStub$2$$ExternalSyntheticLambda0(this, i, list, controllerInfo)));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onSuccess$0$androidx-media3-session-MediaSessionLegacyStub$2  reason: not valid java name */
            public /* synthetic */ void m1094lambda$onSuccess$0$androidxmedia3sessionMediaSessionLegacyStub$2(int i, List list, MediaSession.ControllerInfo controllerInfo) {
                if (i == -1) {
                    MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper().addMediaItems(list);
                } else {
                    MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper().addMediaItems(i, list);
                }
                MediaSessionLegacyStub.this.sessionImpl.onPlayerInteractionFinishedOnHandler(controllerInfo, new Player.Commands.Builder().add(20).build());
            }
        }, MoreExecutors.directExecutor());
    }

    private static void sendCustomCommandResultWhenReady(ResultReceiver resultReceiver, ListenableFuture<SessionResult> listenableFuture) {
        listenableFuture.addListener(new MediaSessionLegacyStub$$ExternalSyntheticLambda0(listenableFuture, resultReceiver), MoreExecutors.directExecutor());
    }

    static /* synthetic */ void lambda$sendCustomCommandResultWhenReady$27(ListenableFuture listenableFuture, ResultReceiver resultReceiver) {
        SessionResult sessionResult;
        try {
            sessionResult = (SessionResult) Assertions.checkNotNull((SessionResult) listenableFuture.get(), "SessionResult must not be null");
        } catch (CancellationException e) {
            Log.w(TAG, "Custom command cancelled", e);
            sessionResult = new SessionResult(1);
        } catch (InterruptedException | ExecutionException e2) {
            Log.w(TAG, "Custom command failed", e2);
            sessionResult = new SessionResult(-1);
        }
        resultReceiver.send(sessionResult.resultCode, sessionResult.extras);
    }

    /* access modifiers changed from: private */
    public static void setMetadata(MediaSessionCompat mediaSessionCompat, MediaMetadataCompat mediaMetadataCompat) {
        mediaSessionCompat.setMetadata(mediaMetadataCompat);
    }

    private static void setMediaButtonReceiver(MediaSessionCompat mediaSessionCompat, PendingIntent pendingIntent) {
        mediaSessionCompat.setMediaButtonReceiver(pendingIntent);
    }

    /* access modifiers changed from: private */
    public static void setQueue(MediaSessionCompat mediaSessionCompat, List<MediaSessionCompat.QueueItem> list) {
        mediaSessionCompat.setQueue(list);
    }

    /* access modifiers changed from: private */
    public void setQueueTitle(MediaSessionCompat mediaSessionCompat, CharSequence charSequence) {
        if (!isQueueEnabled()) {
            charSequence = null;
        }
        mediaSessionCompat.setQueueTitle(charSequence);
    }

    /* access modifiers changed from: private */
    public boolean isQueueEnabled() {
        PlayerWrapper playerWrapper = this.sessionImpl.getPlayerWrapper();
        return playerWrapper.getAvailablePlayerCommands().contains(17) && playerWrapper.getAvailableCommands().contains(17);
    }

    private static MediaItem createMediaItemForMediaRequest(String str, Uri uri, String str2, Bundle bundle) {
        MediaItem.Builder builder = new MediaItem.Builder();
        if (str == null) {
            str = "";
        }
        return builder.setMediaId(str).setRequestMetadata(new MediaItem.RequestMetadata.Builder().setMediaUri(uri).setSearchQuery(str2).setExtras(bundle).build()).build();
    }

    private static final class ControllerLegacyCb implements MediaSession.ControllerCb {
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;

        public ControllerLegacyCb(MediaSessionManager.RemoteUserInfo remoteUserInfo2) {
            this.remoteUserInfo = remoteUserInfo2;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.remoteUserInfo);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != ControllerLegacyCb.class) {
                return false;
            }
            return Util.areEqual(this.remoteUserInfo, ((ControllerLegacyCb) obj).remoteUserInfo);
        }
    }

    private final class ControllerLegacyCbForBroadcast implements MediaSession.ControllerCb {
        private long lastDurationMs = C.TIME_UNSET;
        private String lastMediaId = "";
        private MediaMetadata lastMediaMetadata = MediaMetadata.EMPTY;
        private Uri lastMediaUri;

        public void onDisconnected(int i) throws RemoteException {
        }

        public ControllerLegacyCbForBroadcast() {
        }

        public void onAvailableCommandsChangedFromPlayer(int i, Player.Commands commands) {
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaSessionLegacyStub.this.maybeUpdateFlags(playerWrapper);
            MediaSessionLegacyStub.this.updateLegacySessionPlaybackState(playerWrapper);
        }

        public void onPlayerChanged(int i, PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) throws RemoteException {
            Timeline currentTimelineWithCommandCheck = playerWrapper2.getCurrentTimelineWithCommandCheck();
            if (playerWrapper == null || !Util.areEqual(playerWrapper.getCurrentTimelineWithCommandCheck(), currentTimelineWithCommandCheck)) {
                onTimelineChanged(i, currentTimelineWithCommandCheck, 0);
            }
            MediaMetadata playlistMetadataWithCommandCheck = playerWrapper2.getPlaylistMetadataWithCommandCheck();
            if (playerWrapper == null || !Util.areEqual(playerWrapper.getPlaylistMetadataWithCommandCheck(), playlistMetadataWithCommandCheck)) {
                onPlaylistMetadataChanged(i, playlistMetadataWithCommandCheck);
            }
            MediaMetadata mediaMetadataWithCommandCheck = playerWrapper2.getMediaMetadataWithCommandCheck();
            if (playerWrapper == null || !Util.areEqual(playerWrapper.getMediaMetadataWithCommandCheck(), mediaMetadataWithCommandCheck)) {
                onMediaMetadataChanged(i, mediaMetadataWithCommandCheck);
            }
            if (playerWrapper == null || playerWrapper.getShuffleModeEnabled() != playerWrapper2.getShuffleModeEnabled()) {
                onShuffleModeEnabledChanged(i, playerWrapper2.getShuffleModeEnabled());
            }
            if (playerWrapper == null || playerWrapper.getRepeatMode() != playerWrapper2.getRepeatMode()) {
                onRepeatModeChanged(i, playerWrapper2.getRepeatMode());
            }
            onDeviceInfoChanged(i, playerWrapper2.getDeviceInfo());
            MediaSessionLegacyStub.this.maybeUpdateFlags(playerWrapper2);
            MediaItem currentMediaItemWithCommandCheck = playerWrapper2.getCurrentMediaItemWithCommandCheck();
            if (playerWrapper == null || !Util.areEqual(playerWrapper.getCurrentMediaItemWithCommandCheck(), currentMediaItemWithCommandCheck)) {
                onMediaItemTransition(i, currentMediaItemWithCommandCheck, 3);
            } else {
                MediaSessionLegacyStub.this.updateLegacySessionPlaybackState(playerWrapper2);
            }
        }

        public void onPlayerError(int i, PlaybackException playbackException) {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public void setCustomLayout(int i, List<CommandButton> list) {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public void setMediaButtonPreferences(int i, List<CommandButton> list) {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public void onSessionExtrasChanged(int i, Bundle bundle) {
            MediaSessionLegacyStub.this.sessionCompat.setExtras(bundle);
            MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper().setLegacyExtras(bundle);
            MediaSessionLegacyStub.this.sessionCompat.setPlaybackState(MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper().createPlaybackStateCompat());
        }

        public void onSessionActivityChanged(int i, PendingIntent pendingIntent) {
            MediaSessionLegacyStub.this.sessionCompat.setSessionActivity(pendingIntent);
        }

        public void onError(int i, SessionError sessionError) {
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            playerWrapper.setLegacyError(false, LegacyConversions.convertToLegacyErrorCode(sessionError.code), sessionError.message, sessionError.extras);
            MediaSessionLegacyStub.this.sessionCompat.setPlaybackState(playerWrapper.createPlaybackStateCompat());
            playerWrapper.clearLegacyErrorStatus();
            MediaSessionLegacyStub.this.sessionCompat.setPlaybackState(playerWrapper.createPlaybackStateCompat());
        }

        public void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) {
            MediaSessionLegacyStub.this.sessionCompat.sendSessionEvent(sessionCommand.customAction, bundle);
        }

        public void onPlayWhenReadyChanged(int i, boolean z, int i2) throws RemoteException {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public void onPlaybackSuppressionReasonChanged(int i, int i2) throws RemoteException {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public void onPlaybackStateChanged(int i, int i2, PlaybackException playbackException) throws RemoteException {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public void onIsPlayingChanged(int i, boolean z) throws RemoteException {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public void onPositionDiscontinuity(int i, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i2) throws RemoteException {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public void onPlaybackParametersChanged(int i, PlaybackParameters playbackParameters) throws RemoteException {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public void onMediaItemTransition(int i, MediaItem mediaItem, int i2) throws RemoteException {
            updateMetadataIfChanged();
            if (mediaItem == null) {
                MediaSessionLegacyStub.this.sessionCompat.setRatingType(0);
            } else {
                MediaSessionLegacyStub.this.sessionCompat.setRatingType(LegacyConversions.getRatingCompatStyle(mediaItem.mediaMetadata.userRating));
            }
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        public void onMediaMetadataChanged(int i, MediaMetadata mediaMetadata) {
            updateMetadataIfChanged();
        }

        public void onTimelineChanged(int i, Timeline timeline, int i2) throws RemoteException {
            updateQueue(timeline);
            updateMetadataIfChanged();
        }

        /* access modifiers changed from: private */
        public void updateQueue(Timeline timeline) {
            if (!MediaSessionLegacyStub.this.isQueueEnabled() || timeline.isEmpty()) {
                MediaSessionLegacyStub.setQueue(MediaSessionLegacyStub.this.sessionCompat, (List<MediaSessionCompat.QueueItem>) null);
                return;
            }
            List<MediaItem> convertToMediaItemList = LegacyConversions.convertToMediaItemList(timeline);
            ArrayList arrayList = new ArrayList();
            MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0 mediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0 = new MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0(this, new AtomicInteger(0), convertToMediaItemList, arrayList);
            for (int i = 0; i < convertToMediaItemList.size(); i++) {
                MediaMetadata mediaMetadata = convertToMediaItemList.get(i).mediaMetadata;
                if (mediaMetadata.artworkData == null) {
                    arrayList.add((Object) null);
                    mediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0.run();
                } else {
                    ListenableFuture<Bitmap> decodeBitmap = MediaSessionLegacyStub.this.sessionImpl.getBitmapLoader().decodeBitmap(mediaMetadata.artworkData);
                    arrayList.add(decodeBitmap);
                    Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
                    Objects.requireNonNull(applicationHandler);
                    decodeBitmap.addListener(mediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda0, new MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda1(applicationHandler));
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$updateQueue$0$androidx-media3-session-MediaSessionLegacyStub$ControllerLegacyCbForBroadcast  reason: not valid java name */
        public /* synthetic */ void m1095lambda$updateQueue$0$androidxmedia3sessionMediaSessionLegacyStub$ControllerLegacyCbForBroadcast(AtomicInteger atomicInteger, List list, List list2) {
            if (atomicInteger.incrementAndGet() == list.size()) {
                handleBitmapFuturesAllCompletedAndSetQueue(list2, list);
            }
        }

        private void handleBitmapFuturesAllCompletedAndSetQueue(List<ListenableFuture<Bitmap>> list, List<MediaItem> list2) {
            Bitmap bitmap;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                ListenableFuture listenableFuture = list.get(i);
                if (listenableFuture != null) {
                    try {
                        bitmap = (Bitmap) Futures.getDone(listenableFuture);
                    } catch (CancellationException | ExecutionException e) {
                        Log.d(MediaSessionLegacyStub.TAG, "Failed to get bitmap", e);
                    }
                    arrayList.add(LegacyConversions.convertToQueueItem(list2.get(i), i, bitmap));
                }
                bitmap = null;
                arrayList.add(LegacyConversions.convertToQueueItem(list2.get(i), i, bitmap));
            }
            MediaSessionLegacyStub.setQueue(MediaSessionLegacyStub.this.sessionCompat, arrayList);
        }

        public void onPlaylistMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException {
            CharSequence queueTitle = MediaSessionLegacyStub.this.sessionCompat.getController().getQueueTitle();
            CharSequence charSequence = mediaMetadata.title;
            if (!TextUtils.equals(queueTitle, charSequence)) {
                MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
                mediaSessionLegacyStub.setQueueTitle(mediaSessionLegacyStub.sessionCompat, charSequence);
            }
        }

        public void onShuffleModeEnabledChanged(int i, boolean z) throws RemoteException {
            MediaSessionLegacyStub.this.sessionCompat.setShuffleMode(LegacyConversions.convertToPlaybackStateCompatShuffleMode(z));
        }

        public void onRepeatModeChanged(int i, int i2) throws RemoteException {
            MediaSessionLegacyStub.this.sessionCompat.setRepeatMode(LegacyConversions.convertToPlaybackStateCompatRepeatMode(i2));
        }

        public void onAudioAttributesChanged(int i, AudioAttributes audioAttributes) {
            if (MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper().getDeviceInfo().playbackType == 0) {
                MediaSessionLegacyStub.this.sessionCompat.setPlaybackToLocal(LegacyConversions.getLegacyStreamType(audioAttributes));
            }
        }

        public void onDeviceInfoChanged(int i, DeviceInfo deviceInfo) {
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            VolumeProviderCompat unused = MediaSessionLegacyStub.this.volumeProviderCompat = playerWrapper.createVolumeProviderCompat();
            if (MediaSessionLegacyStub.this.volumeProviderCompat == null) {
                MediaSessionLegacyStub.this.sessionCompat.setPlaybackToLocal(LegacyConversions.getLegacyStreamType(playerWrapper.getAudioAttributesWithCommandCheck()));
                return;
            }
            MediaSessionLegacyStub.this.sessionCompat.setPlaybackToRemote(MediaSessionLegacyStub.this.volumeProviderCompat);
        }

        public void onDeviceVolumeChanged(int i, int i2, boolean z) {
            if (MediaSessionLegacyStub.this.volumeProviderCompat != null) {
                VolumeProviderCompat access$700 = MediaSessionLegacyStub.this.volumeProviderCompat;
                if (z) {
                    i2 = 0;
                }
                access$700.setCurrentVolume(i2);
            }
        }

        public void onPeriodicSessionPositionInfoChanged(int i, SessionPositionInfo sessionPositionInfo, boolean z, boolean z2, int i2) throws RemoteException {
            MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
            mediaSessionLegacyStub.updateLegacySessionPlaybackState(mediaSessionLegacyStub.sessionImpl.getPlayerWrapper());
        }

        private void updateMetadataIfChanged() {
            long j;
            Bitmap bitmap;
            PlayerWrapper playerWrapper = MediaSessionLegacyStub.this.sessionImpl.getPlayerWrapper();
            MediaItem currentMediaItemWithCommandCheck = playerWrapper.getCurrentMediaItemWithCommandCheck();
            MediaMetadata mediaMetadataWithCommandCheck = playerWrapper.getMediaMetadataWithCommandCheck();
            if (playerWrapper.isCurrentMediaItemLiveWithCommandCheck()) {
                j = C.TIME_UNSET;
            } else {
                j = playerWrapper.getDurationWithCommandCheck();
            }
            long j2 = j;
            String str = currentMediaItemWithCommandCheck != null ? currentMediaItemWithCommandCheck.mediaId : "";
            Uri uri = (currentMediaItemWithCommandCheck == null || currentMediaItemWithCommandCheck.localConfiguration == null) ? null : currentMediaItemWithCommandCheck.localConfiguration.uri;
            if (!Objects.equals(this.lastMediaMetadata, mediaMetadataWithCommandCheck) || !Objects.equals(this.lastMediaId, str) || !Objects.equals(this.lastMediaUri, uri) || this.lastDurationMs != j2) {
                this.lastMediaId = str;
                this.lastMediaUri = uri;
                this.lastMediaMetadata = mediaMetadataWithCommandCheck;
                this.lastDurationMs = j2;
                ListenableFuture loadBitmapFromMetadata = MediaSessionLegacyStub.this.sessionImpl.getBitmapLoader().loadBitmapFromMetadata(mediaMetadataWithCommandCheck);
                if (loadBitmapFromMetadata != null) {
                    FutureCallback unused = MediaSessionLegacyStub.this.pendingBitmapLoadCallback = null;
                    if (loadBitmapFromMetadata.isDone()) {
                        try {
                            bitmap = (Bitmap) Futures.getDone(loadBitmapFromMetadata);
                        } catch (CancellationException | ExecutionException e) {
                            Log.w(MediaSessionLegacyStub.TAG, MediaSessionLegacyStub.getBitmapLoadErrorMessage(e));
                        }
                        MediaSessionLegacyStub.setMetadata(MediaSessionLegacyStub.this.sessionCompat, LegacyConversions.convertToMediaMetadataCompat(mediaMetadataWithCommandCheck, str, uri, j2, bitmap));
                    }
                    MediaSessionLegacyStub mediaSessionLegacyStub = MediaSessionLegacyStub.this;
                    final MediaMetadata mediaMetadata = mediaMetadataWithCommandCheck;
                    final String str2 = str;
                    final Uri uri2 = uri;
                    AnonymousClass1 r13 = r1;
                    final long j3 = j2;
                    AnonymousClass1 r1 = new FutureCallback<Bitmap>() {
                        public void onSuccess(Bitmap bitmap) {
                            if (this == MediaSessionLegacyStub.this.pendingBitmapLoadCallback) {
                                MediaSessionLegacyStub.setMetadata(MediaSessionLegacyStub.this.sessionCompat, LegacyConversions.convertToMediaMetadataCompat(mediaMetadata, str2, uri2, j3, bitmap));
                                MediaSessionLegacyStub.this.sessionImpl.onNotificationRefreshRequired();
                            }
                        }

                        public void onFailure(Throwable th) {
                            if (this == MediaSessionLegacyStub.this.pendingBitmapLoadCallback) {
                                Log.w(MediaSessionLegacyStub.TAG, MediaSessionLegacyStub.getBitmapLoadErrorMessage(th));
                            }
                        }
                    };
                    FutureCallback unused2 = mediaSessionLegacyStub.pendingBitmapLoadCallback = r13;
                    FutureCallback access$800 = MediaSessionLegacyStub.this.pendingBitmapLoadCallback;
                    Handler applicationHandler = MediaSessionLegacyStub.this.sessionImpl.getApplicationHandler();
                    Objects.requireNonNull(applicationHandler);
                    Futures.addCallback(loadBitmapFromMetadata, access$800, new MediaSessionLegacyStub$ControllerLegacyCbForBroadcast$$ExternalSyntheticLambda1(applicationHandler));
                }
                bitmap = null;
                MediaSessionLegacyStub.setMetadata(MediaSessionLegacyStub.this.sessionCompat, LegacyConversions.convertToMediaMetadataCompat(mediaMetadataWithCommandCheck, str, uri, j2, bitmap));
            }
        }
    }

    private static class ConnectionTimeoutHandler extends Handler {
        private static final int MSG_CONNECTION_TIMED_OUT = 1001;
        private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;

        public ConnectionTimeoutHandler(Looper looper, ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager2) {
            super(looper);
            this.connectedControllersManager = connectedControllersManager2;
        }

        public void handleMessage(Message message) {
            MediaSession.ControllerInfo controllerInfo = (MediaSession.ControllerInfo) message.obj;
            if (this.connectedControllersManager.isConnected(controllerInfo)) {
                try {
                    ((MediaSession.ControllerCb) Assertions.checkStateNotNull(controllerInfo.getControllerCb())).onDisconnected(0);
                } catch (RemoteException unused) {
                }
                this.connectedControllersManager.removeController(controllerInfo);
            }
        }

        public void disconnectControllerAfterTimeout(MediaSession.ControllerInfo controllerInfo, long j) {
            removeMessages(1001, controllerInfo);
            sendMessageDelayed(obtainMessage(1001, controllerInfo), j);
        }
    }

    /* access modifiers changed from: private */
    public static String getBitmapLoadErrorMessage(Throwable th) {
        return "Failed to load bitmap: " + th.getMessage();
    }

    private static ComponentName getServiceComponentByAction(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            return null;
        }
        ResolveInfo resolveInfo = queryIntentServices.get(0);
        return new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
    }

    private final class MediaButtonReceiver extends BroadcastReceiver {
        private MediaButtonReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            KeyEvent keyEvent;
            if (Util.areEqual(intent.getAction(), "android.intent.action.MEDIA_BUTTON")) {
                Uri data = intent.getData();
                if (Util.areEqual(data, data) && (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) != null) {
                    MediaSessionLegacyStub.this.sessionCompat.getController().dispatchMediaButtonEvent(keyEvent);
                }
            }
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static void setMediaButtonBroadcastReceiver(MediaSessionCompat mediaSessionCompat, ComponentName componentName) {
            try {
                ((android.media.session.MediaSession) Assertions.checkNotNull(mediaSessionCompat.getMediaSession())).setMediaButtonBroadcastReceiver(componentName);
            } catch (IllegalArgumentException e) {
                if (Build.MANUFACTURER.equals("motorola")) {
                    Log.e(MediaSessionLegacyStub.TAG, "caught IllegalArgumentException on a motorola device when attempting to set the media button broadcast receiver. See https://github.com/androidx/media/issues/1730 for details.", e);
                    return;
                }
                throw e;
            }
        }
    }
}
