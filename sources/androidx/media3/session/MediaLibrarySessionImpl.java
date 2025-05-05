package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import androidx.media3.session.PlayerWrapper;
import androidx.media3.session.legacy.MediaSessionCompat;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class MediaLibrarySessionImpl extends MediaSessionImpl {
    private static final String RECENT_LIBRARY_ROOT_MEDIA_ID = "androidx.media3.session.recent.root";
    private final MediaLibraryService.MediaLibrarySession.Callback callback;
    private final HashMultimap<MediaSession.ControllerCb, String> controllerToSubscribedParentIds = HashMultimap.create();
    private final MediaLibraryService.MediaLibrarySession instance;
    private final int libraryErrorReplicationMode;
    private final HashMultimap<String, MediaSession.ControllerInfo> parentIdToSubscribedControllers = HashMultimap.create();

    private boolean isReplicationErrorCode(int i) {
        return i == -102 || i == -105;
    }

    public MediaLibrarySessionImpl(MediaLibraryService.MediaLibrarySession mediaLibrarySession, Context context, String str, Player player, PendingIntent pendingIntent, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, ImmutableList<CommandButton> immutableList3, MediaLibraryService.MediaLibrarySession.Callback callback2, Bundle bundle, Bundle bundle2, BitmapLoader bitmapLoader, boolean z, boolean z2, int i) {
        super(mediaLibrarySession, context, str, player, pendingIntent, immutableList, immutableList2, immutableList3, callback2, bundle, bundle2, bitmapLoader, z, z2);
        this.instance = mediaLibrarySession;
        this.callback = callback2;
        this.libraryErrorReplicationMode = i;
    }

    public List<MediaSession.ControllerInfo> getConnectedControllers() {
        List<MediaSession.ControllerInfo> connectedControllers = super.getConnectedControllers();
        MediaLibraryServiceLegacyStub legacyBrowserService = getLegacyBrowserService();
        if (legacyBrowserService != null) {
            connectedControllers.addAll(legacyBrowserService.getConnectedControllersManager().getConnectedControllers());
        }
        return connectedControllers;
    }

    public boolean isConnected(MediaSession.ControllerInfo controllerInfo) {
        if (super.isConnected(controllerInfo)) {
            return true;
        }
        MediaLibraryServiceLegacyStub legacyBrowserService = getLegacyBrowserService();
        if (legacyBrowserService == null || !legacyBrowserService.getConnectedControllersManager().isConnected(controllerInfo)) {
            return false;
        }
        return true;
    }

    public void clearReplicatedLibraryError() {
        PlayerWrapper playerWrapper = getPlayerWrapper();
        if (playerWrapper.getLegacyError() != null) {
            playerWrapper.clearLegacyErrorStatus();
            getSessionCompat().setPlaybackState(playerWrapper.createPlaybackStateCompat());
        }
    }

    public ListenableFuture<LibraryResult<MediaItem>> onGetLibraryRootOnHandler(MediaSession.ControllerInfo controllerInfo, MediaLibraryService.LibraryParams libraryParams) {
        if (libraryParams == null || !libraryParams.isRecent || !isSystemUiController(controllerInfo)) {
            return this.callback.onGetLibraryRoot(this.instance, resolveControllerInfoForCallback(controllerInfo), libraryParams);
        }
        if (!canResumePlaybackOnStart()) {
            return Futures.immediateFuture(LibraryResult.ofError(-6));
        }
        return Futures.immediateFuture(LibraryResult.ofItem(new MediaItem.Builder().setMediaId(RECENT_LIBRARY_ROOT_MEDIA_ID).setMediaMetadata(new MediaMetadata.Builder().setIsBrowsable(true).setIsPlayable(false).build()).build(), libraryParams));
    }

    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> onGetChildrenOnHandler(MediaSession.ControllerInfo controllerInfo, String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        if (!Objects.equals(str, RECENT_LIBRARY_ROOT_MEDIA_ID)) {
            ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> onGetChildren = this.callback.onGetChildren(this.instance, resolveControllerInfoForCallback(controllerInfo), str, i, i2, libraryParams);
            onGetChildren.addListener(new MediaLibrarySessionImpl$$ExternalSyntheticLambda7(this, onGetChildren, controllerInfo, i2), new MediaLibrarySessionImpl$$ExternalSyntheticLambda1(this));
            return onGetChildren;
        } else if (!canResumePlaybackOnStart()) {
            return Futures.immediateFuture(LibraryResult.ofError(-6));
        } else {
            if (getPlayerWrapper().getPlaybackState() == 1) {
                return getRecentMediaItemAtDeviceBootTime(controllerInfo, libraryParams);
            }
            return Futures.immediateFuture(LibraryResult.ofItemList(ImmutableList.of(new MediaItem.Builder().setMediaId("androidx.media3.session.recent.item").setMediaMetadata(new MediaMetadata.Builder().setIsBrowsable(false).setIsPlayable(true).build()).build()), libraryParams));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onGetChildrenOnHandler$0$androidx-media3-session-MediaLibrarySessionImpl  reason: not valid java name */
    public /* synthetic */ void m1036lambda$onGetChildrenOnHandler$0$androidxmedia3sessionMediaLibrarySessionImpl(ListenableFuture listenableFuture, MediaSession.ControllerInfo controllerInfo, int i) {
        LibraryResult libraryResult = (LibraryResult) tryGetFutureResult(listenableFuture);
        if (libraryResult != null) {
            maybeUpdateLegacyErrorState(controllerInfo, libraryResult);
            verifyResultItems(libraryResult, i);
        }
    }

    public ListenableFuture<LibraryResult<MediaItem>> onGetItemOnHandler(MediaSession.ControllerInfo controllerInfo, String str) {
        ListenableFuture<LibraryResult<MediaItem>> onGetItem = this.callback.onGetItem(this.instance, resolveControllerInfoForCallback(controllerInfo), str);
        onGetItem.addListener(new MediaLibrarySessionImpl$$ExternalSyntheticLambda8(this, onGetItem, controllerInfo), new MediaLibrarySessionImpl$$ExternalSyntheticLambda1(this));
        return onGetItem;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onGetItemOnHandler$1$androidx-media3-session-MediaLibrarySessionImpl  reason: not valid java name */
    public /* synthetic */ void m1037lambda$onGetItemOnHandler$1$androidxmedia3sessionMediaLibrarySessionImpl(ListenableFuture listenableFuture, MediaSession.ControllerInfo controllerInfo) {
        LibraryResult libraryResult = (LibraryResult) tryGetFutureResult(listenableFuture);
        if (libraryResult != null) {
            maybeUpdateLegacyErrorState(controllerInfo, libraryResult);
        }
    }

    public ListenableFuture<LibraryResult<Void>> onSubscribeOnHandler(MediaSession.ControllerInfo controllerInfo, String str, MediaLibraryService.LibraryParams libraryParams) {
        this.controllerToSubscribedParentIds.put((MediaSession.ControllerCb) Assertions.checkNotNull(controllerInfo.getControllerCb()), str);
        this.parentIdToSubscribedControllers.put(str, controllerInfo);
        ListenableFuture<LibraryResult<Void>> listenableFuture = (ListenableFuture) Assertions.checkNotNull(this.callback.onSubscribe(this.instance, resolveControllerInfoForCallback(controllerInfo), str, libraryParams), "onSubscribe must return non-null future");
        listenableFuture.addListener(new MediaLibrarySessionImpl$$ExternalSyntheticLambda2(this, listenableFuture, controllerInfo, str), new MediaLibrarySessionImpl$$ExternalSyntheticLambda1(this));
        return listenableFuture;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSubscribeOnHandler$2$androidx-media3-session-MediaLibrarySessionImpl  reason: not valid java name */
    public /* synthetic */ void m1040lambda$onSubscribeOnHandler$2$androidxmedia3sessionMediaLibrarySessionImpl(ListenableFuture listenableFuture, MediaSession.ControllerInfo controllerInfo, String str) {
        LibraryResult libraryResult = (LibraryResult) tryGetFutureResult(listenableFuture);
        if (libraryResult == null || libraryResult.resultCode != 0) {
            m1041lambda$onUnsubscribeOnHandler$3$androidxmedia3sessionMediaLibrarySessionImpl(controllerInfo, str);
        }
    }

    public ImmutableList<MediaSession.ControllerInfo> getSubscribedControllers(String str) {
        return ImmutableList.copyOf(this.parentIdToSubscribedControllers.get(str));
    }

    private boolean isSubscribed(MediaSession.ControllerCb controllerCb, String str) {
        return this.controllerToSubscribedParentIds.containsEntry(controllerCb, str);
    }

    public ListenableFuture<LibraryResult<Void>> onUnsubscribeOnHandler(MediaSession.ControllerInfo controllerInfo, String str) {
        ListenableFuture<LibraryResult<Void>> onUnsubscribe = this.callback.onUnsubscribe(this.instance, resolveControllerInfoForCallback(controllerInfo), str);
        onUnsubscribe.addListener(new MediaLibrarySessionImpl$$ExternalSyntheticLambda6(this, controllerInfo, str), new MediaLibrarySessionImpl$$ExternalSyntheticLambda1(this));
        return onUnsubscribe;
    }

    public void notifyChildrenChanged(String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        List<MediaSession.ControllerInfo> connectedControllers = this.instance.getConnectedControllers();
        for (int i2 = 0; i2 < connectedControllers.size(); i2++) {
            notifyChildrenChanged(connectedControllers.get(i2), str, i, libraryParams);
        }
    }

    public void notifyChildrenChanged(MediaSession.ControllerInfo controllerInfo, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        if (!isMediaNotificationControllerConnected() || !isMediaNotificationController(controllerInfo) || (controllerInfo = getSystemUiControllerInfo()) != null) {
            dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new MediaLibrarySessionImpl$$ExternalSyntheticLambda4(this, str, i, libraryParams));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$notifyChildrenChanged$4$androidx-media3-session-MediaLibrarySessionImpl  reason: not valid java name */
    public /* synthetic */ void m1035lambda$notifyChildrenChanged$4$androidxmedia3sessionMediaLibrarySessionImpl(String str, int i, MediaLibraryService.LibraryParams libraryParams, MediaSession.ControllerCb controllerCb, int i2) throws RemoteException {
        if (isSubscribed(controllerCb, str)) {
            controllerCb.onChildrenChanged(i2, str, i, libraryParams);
        }
    }

    public ListenableFuture<LibraryResult<Void>> onSearchOnHandler(MediaSession.ControllerInfo controllerInfo, String str, MediaLibraryService.LibraryParams libraryParams) {
        ListenableFuture<LibraryResult<Void>> onSearch = this.callback.onSearch(this.instance, resolveControllerInfoForCallback(controllerInfo), str, libraryParams);
        onSearch.addListener(new MediaLibrarySessionImpl$$ExternalSyntheticLambda3(this, onSearch, controllerInfo), new MediaLibrarySessionImpl$$ExternalSyntheticLambda1(this));
        return onSearch;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSearchOnHandler$5$androidx-media3-session-MediaLibrarySessionImpl  reason: not valid java name */
    public /* synthetic */ void m1039lambda$onSearchOnHandler$5$androidxmedia3sessionMediaLibrarySessionImpl(ListenableFuture listenableFuture, MediaSession.ControllerInfo controllerInfo) {
        LibraryResult libraryResult = (LibraryResult) tryGetFutureResult(listenableFuture);
        if (libraryResult != null) {
            maybeUpdateLegacyErrorState(controllerInfo, libraryResult);
        }
    }

    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> onGetSearchResultOnHandler(MediaSession.ControllerInfo controllerInfo, String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> onGetSearchResult = this.callback.onGetSearchResult(this.instance, resolveControllerInfoForCallback(controllerInfo), str, i, i2, libraryParams);
        onGetSearchResult.addListener(new MediaLibrarySessionImpl$$ExternalSyntheticLambda0(this, onGetSearchResult, controllerInfo, i2), new MediaLibrarySessionImpl$$ExternalSyntheticLambda1(this));
        return onGetSearchResult;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onGetSearchResultOnHandler$6$androidx-media3-session-MediaLibrarySessionImpl  reason: not valid java name */
    public /* synthetic */ void m1038lambda$onGetSearchResultOnHandler$6$androidxmedia3sessionMediaLibrarySessionImpl(ListenableFuture listenableFuture, MediaSession.ControllerInfo controllerInfo, int i) {
        LibraryResult libraryResult = (LibraryResult) tryGetFutureResult(listenableFuture);
        if (libraryResult != null) {
            maybeUpdateLegacyErrorState(controllerInfo, libraryResult);
            verifyResultItems(libraryResult, i);
        }
    }

    public void notifySearchResultChanged(MediaSession.ControllerInfo controllerInfo, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        if (!isMediaNotificationControllerConnected() || !isMediaNotificationController(controllerInfo) || (controllerInfo = getSystemUiControllerInfo()) != null) {
            dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new MediaLibrarySessionImpl$$ExternalSyntheticLambda5(str, i, libraryParams));
        }
    }

    public void onDisconnectedOnHandler(MediaSession.ControllerInfo controllerInfo) {
        UnmodifiableIterator it = ImmutableSet.copyOf(this.controllerToSubscribedParentIds.get((MediaSession.ControllerCb) Assertions.checkNotNull(controllerInfo.getControllerCb()))).iterator();
        while (it.hasNext()) {
            m1041lambda$onUnsubscribeOnHandler$3$androidxmedia3sessionMediaLibrarySessionImpl(controllerInfo, (String) it.next());
        }
        super.onDisconnectedOnHandler(controllerInfo);
    }

    /* access modifiers changed from: protected */
    public MediaLibraryServiceLegacyStub getLegacyBrowserService() {
        return (MediaLibraryServiceLegacyStub) super.getLegacyBrowserService();
    }

    /* access modifiers changed from: protected */
    public MediaSessionServiceLegacyStub createLegacyBrowserService(MediaSessionCompat.Token token) {
        MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub = new MediaLibraryServiceLegacyStub(this);
        mediaLibraryServiceLegacyStub.initialize(token);
        return mediaLibraryServiceLegacyStub;
    }

    /* access modifiers changed from: protected */
    public void dispatchRemoteControllerTaskWithoutReturn(MediaSessionImpl.RemoteControllerTask remoteControllerTask) {
        super.dispatchRemoteControllerTaskWithoutReturn(remoteControllerTask);
        MediaLibraryServiceLegacyStub legacyBrowserService = getLegacyBrowserService();
        if (legacyBrowserService != null) {
            try {
                remoteControllerTask.run(legacyBrowserService.getBrowserLegacyCbForBroadcast(), 0);
            } catch (RemoteException e) {
                Log.e(MediaSessionImpl.TAG, "Exception in using media1 API", e);
            }
        }
    }

    private void maybeUpdateLegacyErrorState(MediaSession.ControllerInfo controllerInfo, LibraryResult<?> libraryResult) {
        if (this.libraryErrorReplicationMode != 0 && controllerInfo.getControllerVersion() == 0) {
            PlayerWrapper playerWrapper = getPlayerWrapper();
            if (setLegacyErrorState(libraryResult)) {
                getSessionCompat().setPlaybackState(playerWrapper.createPlaybackStateCompat());
            } else if (libraryResult.resultCode == 0) {
                clearReplicatedLibraryError();
            }
        }
    }

    private boolean setLegacyErrorState(LibraryResult<?> libraryResult) {
        PlayerWrapper playerWrapper = getPlayerWrapper();
        boolean z = false;
        if (isReplicationErrorCode(libraryResult.resultCode)) {
            int convertToLegacyErrorCode = LegacyConversions.convertToLegacyErrorCode(libraryResult.resultCode);
            PlayerWrapper.LegacyError legacyError = playerWrapper.getLegacyError();
            if (legacyError == null || legacyError.code != convertToLegacyErrorCode) {
                String str = libraryResult.sessionError != null ? libraryResult.sessionError.message : "no error message provided";
                Bundle bundle = Bundle.EMPTY;
                if (libraryResult.params != null && libraryResult.params.extras.containsKey("android.media.extras.ERROR_RESOLUTION_ACTION_INTENT")) {
                    bundle = libraryResult.params.extras;
                } else if (libraryResult.sessionError != null) {
                    bundle = libraryResult.sessionError.extras;
                }
                if (this.libraryErrorReplicationMode == 1) {
                    z = true;
                }
                playerWrapper.setLegacyError(z, convertToLegacyErrorCode, str, bundle);
                return true;
            }
        }
        return false;
    }

    private static <T> T tryGetFutureResult(Future<T> future) {
        Assertions.checkState(future.isDone());
        try {
            return future.get();
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            Log.w(MediaSessionImpl.TAG, "Library operation failed", e);
            return null;
        }
    }

    private static void verifyResultItems(LibraryResult<ImmutableList<MediaItem>> libraryResult, int i) {
        if (libraryResult.resultCode == 0) {
            List list = (List) Assertions.checkNotNull((ImmutableList) libraryResult.value);
            if (list.size() > i) {
                throw new IllegalStateException("Invalid size=" + list.size() + ", pageSize=" + i);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: removeSubscription */
    public void m1041lambda$onUnsubscribeOnHandler$3$androidxmedia3sessionMediaLibrarySessionImpl(MediaSession.ControllerInfo controllerInfo, String str) {
        this.parentIdToSubscribedControllers.remove(str, controllerInfo);
        this.controllerToSubscribedParentIds.remove((MediaSession.ControllerCb) Assertions.checkNotNull(controllerInfo.getControllerCb()), str);
    }

    /* access modifiers changed from: private */
    public void postOrRunOnApplicationHandler(Runnable runnable) {
        Util.postOrRun(getApplicationHandler(), runnable);
    }

    private ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getRecentMediaItemAtDeviceBootTime(MediaSession.ControllerInfo controllerInfo, final MediaLibraryService.LibraryParams libraryParams) {
        final SettableFuture create = SettableFuture.create();
        if (isMediaNotificationControllerConnected()) {
            controllerInfo = (MediaSession.ControllerInfo) Assertions.checkNotNull(getMediaNotificationControllerInfo());
        }
        Futures.addCallback(this.callback.onPlaybackResumption(this.instance, controllerInfo), new FutureCallback<MediaSession.MediaItemsWithStartPosition>() {
            public void onSuccess(MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
                if (mediaItemsWithStartPosition.mediaItems.isEmpty()) {
                    create.set(LibraryResult.ofError(-2, libraryParams));
                    return;
                }
                create.set(LibraryResult.ofItemList(ImmutableList.of((MediaItem) mediaItemsWithStartPosition.mediaItems.get(Math.max(0, Math.min(mediaItemsWithStartPosition.startIndex, mediaItemsWithStartPosition.mediaItems.size() - 1)))), libraryParams));
            }

            public void onFailure(Throwable th) {
                create.set(LibraryResult.ofError(-1, libraryParams));
                Log.e(MediaSessionImpl.TAG, "Failed fetching recent media item at boot time: " + th.getMessage(), th);
            }
        }, MoreExecutors.directExecutor());
        return create;
    }
}
