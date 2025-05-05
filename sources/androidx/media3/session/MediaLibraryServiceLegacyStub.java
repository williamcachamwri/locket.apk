package androidx.media3.session;

import android.graphics.Bitmap;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

class MediaLibraryServiceLegacyStub extends MediaSessionServiceLegacyStub {
    private static final String TAG = "MLSLegacyStub";
    private final MediaSession.ControllerCb browserLegacyCbForBroadcast = new BrowserLegacyCbForBroadcast();
    /* access modifiers changed from: private */
    public final MediaLibrarySessionImpl librarySessionImpl;

    private static <T> void ignoreFuture(Future<T> future) {
    }

    public MediaLibraryServiceLegacyStub(MediaLibrarySessionImpl mediaLibrarySessionImpl) {
        super(mediaLibrarySessionImpl);
        this.librarySessionImpl = mediaLibrarySessionImpl;
    }

    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        MediaSession.ControllerInfo currentController;
        LibraryResult libraryResult;
        Bundle bundle2;
        if (super.onGetRoot(str, i, bundle) == null || (currentController = getCurrentController()) == null || !getConnectedControllersManager().isSessionCommandAvailable(currentController, 50000)) {
            return null;
        }
        MediaLibraryService.LibraryParams convertToLibraryParams = LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle);
        AtomicReference atomicReference = new AtomicReference();
        ConditionVariable conditionVariable = new ConditionVariable();
        Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda12(this, atomicReference, currentController, convertToLibraryParams, conditionVariable));
        try {
            conditionVariable.block();
            libraryResult = (LibraryResult) Assertions.checkNotNull((LibraryResult) ((ListenableFuture) atomicReference.get()).get(), "LibraryResult must not be null");
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            Log.e(TAG, "Couldn't get a result from onGetLibraryRoot", e);
            libraryResult = null;
        }
        if (libraryResult != null && libraryResult.resultCode == 0 && libraryResult.value != null) {
            if (libraryResult.params != null) {
                bundle2 = LegacyConversions.convertToRootHints(libraryResult.params);
            } else {
                bundle2 = new Bundle();
            }
            ((Bundle) Assertions.checkNotNull(bundle2)).putBoolean("android.media.browse.SEARCH_SUPPORTED", getConnectedControllersManager().isSessionCommandAvailable(currentController, (int) SessionCommand.COMMAND_CODE_LIBRARY_SEARCH));
            ImmutableList<CommandButton> commandButtonsForMediaItems = this.librarySessionImpl.getCommandButtonsForMediaItems();
            if (!commandButtonsForMediaItems.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < commandButtonsForMediaItems.size(); i2++) {
                    CommandButton commandButton = (CommandButton) commandButtonsForMediaItems.get(i2);
                    if (commandButton.sessionCommand != null && commandButton.sessionCommand.commandCode == 0) {
                        arrayList.add(LegacyConversions.convertToBundle(commandButton));
                    }
                }
                if (!arrayList.isEmpty()) {
                    bundle2.putParcelableArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ROOT_LIST", arrayList);
                }
            }
            return new MediaBrowserServiceCompat.BrowserRoot(((MediaItem) libraryResult.value).mediaId, bundle2);
        } else if (libraryResult == null || libraryResult.resultCode == 0) {
            return MediaUtils.defaultBrowserRoot;
        } else {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onGetRoot$0$androidx-media3-session-MediaLibraryServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1028lambda$onGetRoot$0$androidxmedia3sessionMediaLibraryServiceLegacyStub(AtomicReference atomicReference, MediaSession.ControllerInfo controllerInfo, MediaLibraryService.LibraryParams libraryParams, ConditionVariable conditionVariable) {
        atomicReference.set(this.librarySessionImpl.onGetLibraryRootOnHandler(controllerInfo, libraryParams));
        conditionVariable.open();
    }

    public void onSubscribe(String str, Bundle bundle) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController != null) {
            if (TextUtils.isEmpty(str)) {
                Log.w(TAG, "onSubscribe(): Ignoring empty id from " + currentController);
            } else {
                Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda6(this, currentController, bundle, str));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSubscribe$1$androidx-media3-session-MediaLibraryServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1032lambda$onSubscribe$1$androidxmedia3sessionMediaLibraryServiceLegacyStub(MediaSession.ControllerInfo controllerInfo, Bundle bundle, String str) {
        if (getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, (int) SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE)) {
            ignoreFuture(this.librarySessionImpl.onSubscribeOnHandler(controllerInfo, str, LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle)));
        }
    }

    public void onUnsubscribe(String str) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController != null) {
            if (TextUtils.isEmpty(str)) {
                Log.w(TAG, "onUnsubscribe(): Ignoring empty id from " + currentController);
            } else {
                Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda15(this, currentController, str));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onUnsubscribe$2$androidx-media3-session-MediaLibraryServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1033lambda$onUnsubscribe$2$androidxmedia3sessionMediaLibraryServiceLegacyStub(MediaSession.ControllerInfo controllerInfo, String str) {
        if (getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, (int) SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE)) {
            ignoreFuture(this.librarySessionImpl.onUnsubscribeOnHandler(controllerInfo, str));
        }
    }

    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        onLoadChildren(str, result, (Bundle) null);
    }

    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, Bundle bundle) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendResult(null);
        } else if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onLoadChildren(): Ignoring empty parentId from " + currentController);
            result.sendResult(null);
        } else {
            result.detach();
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda11(this, currentController, result, bundle, str));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onLoadChildren$3$androidx-media3-session-MediaLibraryServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1029lambda$onLoadChildren$3$androidxmedia3sessionMediaLibraryServiceLegacyStub(MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, Bundle bundle, String str) {
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, (int) SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN)) {
            result.sendResult(null);
            return;
        }
        if (bundle != null) {
            bundle.setClassLoader(this.librarySessionImpl.getContext().getClassLoader());
            try {
                int i = bundle.getInt("android.media.browse.extra.PAGE");
                int i2 = bundle.getInt("android.media.browse.extra.PAGE_SIZE");
                if (i >= 0 && i2 > 0) {
                    sendLibraryResultWithMediaItemsWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetChildrenOnHandler(controllerInfo, str, i, i2, LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle)), createMediaItemsToBrowserItemsAsyncFunction()));
                    return;
                }
            } catch (BadParcelableException unused) {
            }
        }
        sendLibraryResultWithMediaItemsWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetChildrenOnHandler(controllerInfo, str, 0, Integer.MAX_VALUE, (MediaLibraryService.LibraryParams) null), createMediaItemsToBrowserItemsAsyncFunction()));
    }

    public void onLoadItem(String str, MediaBrowserServiceCompat.Result<MediaBrowserCompat.MediaItem> result) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendResult(null);
        } else if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "Ignoring empty itemId from " + currentController);
            result.sendResult(null);
        } else {
            result.detach();
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda10(this, currentController, result, str));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onLoadItem$4$androidx-media3-session-MediaLibraryServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1030lambda$onLoadItem$4$androidxmedia3sessionMediaLibraryServiceLegacyStub(MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, String str) {
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, (int) SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM)) {
            result.sendResult(null);
        } else {
            sendLibraryResultWithMediaItemWhenReady(result, Util.transformFutureAsync(this.librarySessionImpl.onGetItemOnHandler(controllerInfo, str), createMediaItemToBrowserItemAsyncFunction()));
        }
    }

    public void onSearch(String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendResult(null);
        } else if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "Ignoring empty query from " + currentController);
            result.sendResult(null);
        } else if (currentController.getControllerCb() instanceof BrowserLegacyCb) {
            result.detach();
            Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda5(this, currentController, result, str, bundle));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSearch$5$androidx-media3-session-MediaLibraryServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1031lambda$onSearch$5$androidxmedia3sessionMediaLibraryServiceLegacyStub(MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, String str, Bundle bundle) {
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, (int) SessionCommand.COMMAND_CODE_LIBRARY_SEARCH)) {
            result.sendResult(null);
            return;
        }
        ((BrowserLegacyCb) Assertions.checkStateNotNull(controllerInfo.getControllerCb())).registerSearchRequest(controllerInfo, str, bundle, result);
        ignoreFuture(this.librarySessionImpl.onSearchOnHandler(controllerInfo, str, LegacyConversions.convertToLibraryParams(this.librarySessionImpl.getContext(), bundle)));
    }

    public void onCustomAction(String str, Bundle bundle, MediaBrowserServiceCompat.Result<Bundle> result) {
        MediaSession.ControllerInfo currentController = getCurrentController();
        if (currentController == null) {
            result.sendError((Bundle) null);
            return;
        }
        result.detach();
        Util.postOrRun(this.librarySessionImpl.getApplicationHandler(), new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda13(this, str, currentController, result, bundle));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onCustomAction$6$androidx-media3-session-MediaLibraryServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1027lambda$onCustomAction$6$androidxmedia3sessionMediaLibraryServiceLegacyStub(String str, MediaSession.ControllerInfo controllerInfo, MediaBrowserServiceCompat.Result result, Bundle bundle) {
        SessionCommand sessionCommand = new SessionCommand(str, Bundle.EMPTY);
        if (!getConnectedControllersManager().isSessionCommandAvailable(controllerInfo, sessionCommand)) {
            result.sendError((Bundle) null);
        } else {
            sendCustomActionResultWhenReady(result, this.librarySessionImpl.onCustomCommandOnHandler(controllerInfo, sessionCommand, bundle));
        }
    }

    public MediaSession.ControllerInfo createControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo, Bundle bundle) {
        return new MediaSession.ControllerInfo(remoteUserInfo, 0, 0, getMediaSessionManager().isTrustedForMediaControl(remoteUserInfo), new BrowserLegacyCb(remoteUserInfo), bundle, LegacyConversions.extractMaxCommandsForMediaItemFromRootHints(bundle));
    }

    public MediaSession.ControllerCb getBrowserLegacyCbForBroadcast() {
        return this.browserLegacyCbForBroadcast;
    }

    private MediaSession.ControllerInfo getCurrentController() {
        return getConnectedControllersManager().getController(getCurrentBrowserInfo());
    }

    private static void sendCustomActionResultWhenReady(MediaBrowserServiceCompat.Result<Bundle> result, ListenableFuture<SessionResult> listenableFuture) {
        listenableFuture.addListener(new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda0(listenableFuture, result), MoreExecutors.directExecutor());
    }

    static /* synthetic */ void lambda$sendCustomActionResultWhenReady$7(ListenableFuture listenableFuture, MediaBrowserServiceCompat.Result result) {
        try {
            result.sendResult(((SessionResult) Assertions.checkNotNull((SessionResult) listenableFuture.get(), "SessionResult must not be null")).extras);
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            Log.w(TAG, "Custom action failed", e);
            result.sendError((Bundle) null);
        }
    }

    private static void sendLibraryResultWithMediaItemWhenReady(MediaBrowserServiceCompat.Result<MediaBrowserCompat.MediaItem> result, ListenableFuture<MediaBrowserCompat.MediaItem> listenableFuture) {
        listenableFuture.addListener(new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda14(listenableFuture, result), MoreExecutors.directExecutor());
    }

    static /* synthetic */ void lambda$sendLibraryResultWithMediaItemWhenReady$8(ListenableFuture listenableFuture, MediaBrowserServiceCompat.Result result) {
        try {
            result.sendResult((MediaBrowserCompat.MediaItem) listenableFuture.get());
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            Log.w(TAG, "Library operation failed", e);
            result.sendResult(null);
        }
    }

    /* access modifiers changed from: private */
    public static void sendLibraryResultWithMediaItemsWhenReady(MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, ListenableFuture<List<MediaBrowserCompat.MediaItem>> listenableFuture) {
        listenableFuture.addListener(new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda1(listenableFuture, result), MoreExecutors.directExecutor());
    }

    static /* synthetic */ void lambda$sendLibraryResultWithMediaItemsWhenReady$9(ListenableFuture listenableFuture, MediaBrowserServiceCompat.Result result) {
        List list;
        try {
            List list2 = (List) listenableFuture.get();
            if (list2 == null) {
                list = null;
            } else {
                list = MediaUtils.truncateListBySize(list2, 262144);
            }
            result.sendResult(list);
        } catch (InterruptedException | CancellationException | ExecutionException e) {
            Log.w(TAG, "Library operation failed", e);
            result.sendResult(null);
        }
    }

    /* access modifiers changed from: private */
    public AsyncFunction<LibraryResult<ImmutableList<MediaItem>>, List<MediaBrowserCompat.MediaItem>> createMediaItemsToBrowserItemsAsyncFunction() {
        return new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda9(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createMediaItemsToBrowserItemsAsyncFunction$12$androidx-media3-session-MediaLibraryServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ ListenableFuture m1026lambda$createMediaItemsToBrowserItemsAsyncFunction$12$androidxmedia3sessionMediaLibraryServiceLegacyStub(LibraryResult libraryResult) throws Exception {
        Assertions.checkNotNull(libraryResult, "LibraryResult must not be null");
        SettableFuture create = SettableFuture.create();
        if (libraryResult.resultCode != 0 || libraryResult.value == null) {
            create.set(null);
            return create;
        }
        ImmutableList immutableList = (ImmutableList) libraryResult.value;
        if (immutableList.isEmpty()) {
            create.set(new ArrayList());
            return create;
        }
        ArrayList arrayList = new ArrayList();
        create.addListener(new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda3(create, arrayList), MoreExecutors.directExecutor());
        MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda4 mediaLibraryServiceLegacyStub$$ExternalSyntheticLambda4 = new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda4(this, new AtomicInteger(0), immutableList, arrayList, create);
        for (int i = 0; i < immutableList.size(); i++) {
            MediaMetadata mediaMetadata = ((MediaItem) immutableList.get(i)).mediaMetadata;
            if (mediaMetadata.artworkData == null) {
                arrayList.add((Object) null);
                mediaLibraryServiceLegacyStub$$ExternalSyntheticLambda4.run();
            } else {
                ListenableFuture<Bitmap> decodeBitmap = this.librarySessionImpl.getBitmapLoader().decodeBitmap(mediaMetadata.artworkData);
                arrayList.add(decodeBitmap);
                decodeBitmap.addListener(mediaLibraryServiceLegacyStub$$ExternalSyntheticLambda4, MoreExecutors.directExecutor());
            }
        }
        return create;
    }

    static /* synthetic */ void lambda$createMediaItemsToBrowserItemsAsyncFunction$10(SettableFuture settableFuture, List list) {
        if (settableFuture.isCancelled()) {
            cancelAllFutures(list);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createMediaItemsToBrowserItemsAsyncFunction$11$androidx-media3-session-MediaLibraryServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1025lambda$createMediaItemsToBrowserItemsAsyncFunction$11$androidxmedia3sessionMediaLibraryServiceLegacyStub(AtomicInteger atomicInteger, ImmutableList immutableList, List list, SettableFuture settableFuture) {
        if (atomicInteger.incrementAndGet() == immutableList.size()) {
            handleBitmapFuturesAllCompletedAndSetOutputFuture(list, immutableList, settableFuture);
        }
    }

    private void handleBitmapFuturesAllCompletedAndSetOutputFuture(List<ListenableFuture<Bitmap>> list, List<MediaItem> list2, SettableFuture<List<MediaBrowserCompat.MediaItem>> settableFuture) {
        Bitmap bitmap;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            ListenableFuture listenableFuture = list.get(i);
            if (listenableFuture != null) {
                try {
                    bitmap = (Bitmap) Futures.getDone(listenableFuture);
                } catch (CancellationException | ExecutionException e) {
                    Log.d(TAG, "Failed to get bitmap", e);
                }
                arrayList.add(LegacyConversions.convertToBrowserItem(list2.get(i), bitmap));
            }
            bitmap = null;
            arrayList.add(LegacyConversions.convertToBrowserItem(list2.get(i), bitmap));
        }
        settableFuture.set(arrayList);
    }

    private static <T> void cancelAllFutures(List<ListenableFuture<T>> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null) {
                list.get(i).cancel(false);
            }
        }
    }

    private AsyncFunction<LibraryResult<MediaItem>, MediaBrowserCompat.MediaItem> createMediaItemToBrowserItemAsyncFunction() {
        return new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda2(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createMediaItemToBrowserItemAsyncFunction$15$androidx-media3-session-MediaLibraryServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ ListenableFuture m1024lambda$createMediaItemToBrowserItemAsyncFunction$15$androidxmedia3sessionMediaLibraryServiceLegacyStub(LibraryResult libraryResult) throws Exception {
        Assertions.checkNotNull(libraryResult, "LibraryResult must not be null");
        SettableFuture create = SettableFuture.create();
        if (libraryResult.resultCode != 0 || libraryResult.value == null) {
            create.set(null);
            return create;
        }
        MediaItem mediaItem = (MediaItem) libraryResult.value;
        MediaMetadata mediaMetadata = mediaItem.mediaMetadata;
        if (mediaMetadata.artworkData == null) {
            create.set(LegacyConversions.convertToBrowserItem(mediaItem, (Bitmap) null));
            return create;
        }
        ListenableFuture<Bitmap> decodeBitmap = this.librarySessionImpl.getBitmapLoader().decodeBitmap(mediaMetadata.artworkData);
        create.addListener(new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda7(create, decodeBitmap), MoreExecutors.directExecutor());
        decodeBitmap.addListener(new MediaLibraryServiceLegacyStub$$ExternalSyntheticLambda8(decodeBitmap, create, mediaItem), MoreExecutors.directExecutor());
        return create;
    }

    static /* synthetic */ void lambda$createMediaItemToBrowserItemAsyncFunction$13(SettableFuture settableFuture, ListenableFuture listenableFuture) {
        if (settableFuture.isCancelled()) {
            listenableFuture.cancel(false);
        }
    }

    static /* synthetic */ void lambda$createMediaItemToBrowserItemAsyncFunction$14(ListenableFuture listenableFuture, SettableFuture settableFuture, MediaItem mediaItem) {
        Bitmap bitmap;
        try {
            bitmap = (Bitmap) Futures.getDone(listenableFuture);
        } catch (CancellationException | ExecutionException e) {
            Log.d(TAG, "failed to get bitmap", e);
            bitmap = null;
        }
        settableFuture.set(LegacyConversions.convertToBrowserItem(mediaItem, bitmap));
    }

    private static class SearchRequest {
        public final MediaSession.ControllerInfo controller;
        public final Bundle extras;
        public final String query;
        public final MediaSessionManager.RemoteUserInfo remoteUserInfo;
        public final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result;

        public SearchRequest(MediaSession.ControllerInfo controllerInfo, MediaSessionManager.RemoteUserInfo remoteUserInfo2, String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result2) {
            this.controller = controllerInfo;
            this.remoteUserInfo = remoteUserInfo2;
            this.query = str;
            this.extras = bundle;
            this.result = result2;
        }
    }

    private final class BrowserLegacyCb implements MediaSession.ControllerCb {
        private final Object lock = new Object();
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;
        private final List<SearchRequest> searchRequests = new ArrayList();

        public BrowserLegacyCb(MediaSessionManager.RemoteUserInfo remoteUserInfo2) {
            this.remoteUserInfo = remoteUserInfo2;
        }

        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            Bundle bundle = libraryParams != null ? libraryParams.extras : null;
            MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub = MediaLibraryServiceLegacyStub.this;
            MediaSessionManager.RemoteUserInfo remoteUserInfo2 = this.remoteUserInfo;
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            mediaLibraryServiceLegacyStub.notifyChildrenChanged(remoteUserInfo2, str, bundle);
        }

        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            ArrayList arrayList = new ArrayList();
            synchronized (this.lock) {
                for (int size = this.searchRequests.size() - 1; size >= 0; size--) {
                    SearchRequest searchRequest = this.searchRequests.get(size);
                    if (Util.areEqual(this.remoteUserInfo, searchRequest.remoteUserInfo) && searchRequest.query.equals(str)) {
                        arrayList.add(searchRequest);
                        this.searchRequests.remove(size);
                    }
                }
                if (arrayList.size() != 0) {
                    Util.postOrRun(MediaLibraryServiceLegacyStub.this.librarySessionImpl.getApplicationHandler(), new MediaLibraryServiceLegacyStub$BrowserLegacyCb$$ExternalSyntheticLambda0(this, arrayList));
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onSearchResultChanged$0$androidx-media3-session-MediaLibraryServiceLegacyStub$BrowserLegacyCb  reason: not valid java name */
        public /* synthetic */ void m1034lambda$onSearchResultChanged$0$androidxmedia3sessionMediaLibraryServiceLegacyStub$BrowserLegacyCb(List list) {
            int i;
            int i2;
            int i3;
            int i4;
            for (int i5 = 0; i5 < list.size(); i5++) {
                SearchRequest searchRequest = (SearchRequest) list.get(i5);
                if (searchRequest.extras != null) {
                    try {
                        searchRequest.extras.setClassLoader(MediaLibraryServiceLegacyStub.this.librarySessionImpl.getContext().getClassLoader());
                        i2 = searchRequest.extras.getInt("android.media.browse.extra.PAGE", -1);
                        i = searchRequest.extras.getInt("android.media.browse.extra.PAGE_SIZE", -1);
                    } catch (BadParcelableException unused) {
                        searchRequest.result.sendResult(null);
                        return;
                    }
                } else {
                    i2 = 0;
                    i = Integer.MAX_VALUE;
                }
                if (i2 < 0 || i < 1) {
                    i4 = 0;
                    i3 = Integer.MAX_VALUE;
                } else {
                    i4 = i2;
                    i3 = i;
                }
                MediaLibraryServiceLegacyStub.sendLibraryResultWithMediaItemsWhenReady(searchRequest.result, Util.transformFutureAsync(MediaLibraryServiceLegacyStub.this.librarySessionImpl.onGetSearchResultOnHandler(searchRequest.controller, searchRequest.query, i4, i3, LegacyConversions.convertToLibraryParams(MediaLibraryServiceLegacyStub.this.librarySessionImpl.getContext(), searchRequest.extras)), MediaLibraryServiceLegacyStub.this.createMediaItemsToBrowserItemsAsyncFunction()));
            }
        }

        /* access modifiers changed from: private */
        public void registerSearchRequest(MediaSession.ControllerInfo controllerInfo, String str, Bundle bundle, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
            synchronized (this.lock) {
                this.searchRequests.add(new SearchRequest(controllerInfo, controllerInfo.getRemoteUserInfo(), str, bundle, result));
            }
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.remoteUserInfo);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BrowserLegacyCb)) {
                return false;
            }
            return Util.areEqual(this.remoteUserInfo, ((BrowserLegacyCb) obj).remoteUserInfo);
        }
    }

    private final class BrowserLegacyCbForBroadcast implements MediaSession.ControllerCb {
        public void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
        }

        private BrowserLegacyCbForBroadcast() {
        }

        public void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException {
            if (libraryParams == null || libraryParams.extras == null) {
                MediaLibraryServiceLegacyStub.this.notifyChildrenChanged(str);
            } else {
                MediaLibraryServiceLegacyStub.this.notifyChildrenChanged(str, (Bundle) Util.castNonNull(libraryParams.extras));
            }
        }
    }
}
