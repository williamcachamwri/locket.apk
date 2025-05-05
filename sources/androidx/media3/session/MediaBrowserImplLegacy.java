package androidx.media3.session;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Log;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.legacy.MediaBrowserCompat;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

class MediaBrowserImplLegacy extends MediaControllerImplLegacy implements MediaBrowser.MediaBrowserImpl {
    private static final String TAG = "MB2ImplLegacy";
    /* access modifiers changed from: private */
    public final HashMap<MediaLibraryService.LibraryParams, MediaBrowserCompat> browserCompats = new HashMap<>();
    /* access modifiers changed from: private */
    public ImmutableMap<String, CommandButton> commandButtonsForMediaItems;
    private final MediaBrowser instance;
    private final HashMap<String, List<SubscribeCallback>> subscribeCallbacks = new HashMap<>();

    MediaBrowserImplLegacy(Context context, MediaBrowser mediaBrowser, SessionToken sessionToken, Bundle bundle, Looper looper, BitmapLoader bitmapLoader) {
        super(context, mediaBrowser, sessionToken, bundle, looper, bitmapLoader);
        this.instance = mediaBrowser;
        this.commandButtonsForMediaItems = ImmutableMap.of();
    }

    /* access modifiers changed from: package-private */
    public MediaBrowser getInstance() {
        return this.instance;
    }

    public void release() {
        for (MediaBrowserCompat disconnect : this.browserCompats.values()) {
            disconnect.disconnect();
        }
        this.browserCompats.clear();
        super.release();
    }

    public SessionCommands getAvailableSessionCommands() {
        if (getBrowserCompat() != null) {
            return super.getAvailableSessionCommands().buildUpon().addAllLibraryCommands().build();
        }
        return super.getAvailableSessionCommands();
    }

    public ImmutableList<CommandButton> getCommandButtonsForMediaItem(MediaItem mediaItem) {
        ImmutableList<String> immutableList = mediaItem.mediaMetadata.supportedCommands;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < immutableList.size(); i++) {
            CommandButton commandButton = this.commandButtonsForMediaItems.get(immutableList.get(i));
            if (!(commandButton == null || commandButton.sessionCommand == null)) {
                builder.add((Object) commandButton);
            }
        }
        return builder.build();
    }

    public ListenableFuture<LibraryResult<MediaItem>> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable(50000)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        SettableFuture create = SettableFuture.create();
        MediaBrowserCompat browserCompat = getBrowserCompat(libraryParams);
        if (browserCompat != null) {
            create.set(LibraryResult.ofItem(createRootMediaItem(browserCompat), (MediaLibraryService.LibraryParams) null));
        } else {
            Bundle convertToRootHints = LegacyConversions.convertToRootHints(libraryParams);
            convertToRootHints.putInt("androidx.media.utils.MediaBrowserCompat.extras.CUSTOM_BROWSER_ACTION_LIMIT", getInstance().getMaxCommandsForMediaItems());
            MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(getContext(), getConnectedToken().getComponentName(), new GetLibraryRootCallback(create, libraryParams), convertToRootHints);
            this.browserCompats.put(libraryParams, mediaBrowserCompat);
            mediaBrowserCompat.connect();
        }
        return create;
    }

    public ListenableFuture<LibraryResult<Void>> subscribe(String str, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable((int) SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        Bundle createOptionsForSubscription = createOptionsForSubscription(libraryParams);
        SettableFuture create = SettableFuture.create();
        SubscribeCallback subscribeCallback = new SubscribeCallback(str, createOptionsForSubscription, create);
        List list = this.subscribeCallbacks.get(str);
        if (list == null) {
            list = new ArrayList();
            this.subscribeCallbacks.put(str, list);
        }
        list.add(subscribeCallback);
        browserCompat.subscribe(str, createOptionsForSubscription, subscribeCallback);
        return create;
    }

    public ListenableFuture<LibraryResult<Void>> unsubscribe(String str) {
        if (!getInstance().isSessionCommandAvailable((int) SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        List list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-3));
        }
        for (int i = 0; i < list.size(); i++) {
            browserCompat.unsubscribe(str, (MediaBrowserCompat.SubscriptionCallback) list.get(i));
        }
        return Futures.immediateFuture(LibraryResult.ofVoid());
    }

    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable((int) SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        Bundle createOptionsWithPagingInfo = createOptionsWithPagingInfo(libraryParams, i, i2);
        SettableFuture create = SettableFuture.create();
        List<MediaBrowserCompat.MediaItem> childrenFromSubscription = getChildrenFromSubscription(str, i);
        evictChildrenFromSubscription(str);
        if (childrenFromSubscription != null) {
            create.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(childrenFromSubscription), new MediaLibraryService.LibraryParams.Builder().setExtras(createOptionsWithPagingInfo).build()));
        } else {
            browserCompat.subscribe(str, createOptionsWithPagingInfo, new GetChildrenCallback(create, str));
        }
        return create;
    }

    private List<MediaBrowserCompat.MediaItem> getChildrenFromSubscription(String str, int i) {
        List list = this.subscribeCallbacks.get(str);
        if (list == null) {
            return null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (((SubscribeCallback) list.get(i2)).canServeGetChildrenRequest(str, i)) {
                return ((SubscribeCallback) list.get(i2)).receivedChildren;
            }
        }
        return null;
    }

    private void evictChildrenFromSubscription(String str) {
        List list = this.subscribeCallbacks.get(str);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (((SubscribeCallback) list.get(i)).receivedChildren != null) {
                    List unused = ((SubscribeCallback) list.get(i)).receivedChildren = null;
                    return;
                }
            }
        }
    }

    public ListenableFuture<LibraryResult<MediaItem>> getItem(String str) {
        if (!getInstance().isSessionCommandAvailable((int) SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        final SettableFuture create = SettableFuture.create();
        browserCompat.getItem(str, new MediaBrowserCompat.ItemCallback() {
            public void onItemLoaded(MediaBrowserCompat.MediaItem mediaItem) {
                if (mediaItem != null) {
                    create.set(LibraryResult.ofItem(LegacyConversions.convertToMediaItem(mediaItem), (MediaLibraryService.LibraryParams) null));
                } else {
                    create.set(LibraryResult.ofError(-3));
                }
            }

            public void onError(String str) {
                create.set(LibraryResult.ofError(-1));
            }
        });
        return create;
    }

    public ListenableFuture<LibraryResult<Void>> search(String str, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable((int) SessionCommand.COMMAND_CODE_LIBRARY_SEARCH)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        browserCompat.search(str, getExtras(libraryParams), new MediaBrowserCompat.SearchCallback() {
            public void onSearchResult(String str, Bundle bundle, List<MediaBrowserCompat.MediaItem> list) {
                MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new MediaBrowserImplLegacy$2$$ExternalSyntheticLambda1(this, str, list));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onSearchResult$0$androidx-media3-session-MediaBrowserImplLegacy$2  reason: not valid java name */
            public /* synthetic */ void m918lambda$onSearchResult$0$androidxmedia3sessionMediaBrowserImplLegacy$2(String str, List list, MediaBrowser.Listener listener) {
                listener.onSearchResultChanged(MediaBrowserImplLegacy.this.getInstance(), str, list.size(), (MediaLibraryService.LibraryParams) null);
            }

            public void onError(String str, Bundle bundle) {
                MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new MediaBrowserImplLegacy$2$$ExternalSyntheticLambda0(this, str));
            }

            /* access modifiers changed from: package-private */
            /* renamed from: lambda$onError$1$androidx-media3-session-MediaBrowserImplLegacy$2  reason: not valid java name */
            public /* synthetic */ void m917lambda$onError$1$androidxmedia3sessionMediaBrowserImplLegacy$2(String str, MediaBrowser.Listener listener) {
                listener.onSearchResultChanged(MediaBrowserImplLegacy.this.getInstance(), str, 0, (MediaLibraryService.LibraryParams) null);
            }
        });
        return Futures.immediateFuture(LibraryResult.ofVoid());
    }

    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        if (!getInstance().isSessionCommandAvailable((int) SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT)) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-100));
        }
        final SettableFuture create = SettableFuture.create();
        Bundle createOptionsWithPagingInfo = createOptionsWithPagingInfo(libraryParams, i, i2);
        createOptionsWithPagingInfo.putInt("android.media.browse.extra.PAGE", i);
        createOptionsWithPagingInfo.putInt("android.media.browse.extra.PAGE_SIZE", i2);
        browserCompat.search(str, createOptionsWithPagingInfo, new MediaBrowserCompat.SearchCallback() {
            public void onSearchResult(String str, Bundle bundle, List<MediaBrowserCompat.MediaItem> list) {
                create.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(list), (MediaLibraryService.LibraryParams) null));
            }

            public void onError(String str, Bundle bundle) {
                create.set(LibraryResult.ofError(-1));
            }
        });
        return create;
    }

    public ListenableFuture<SessionResult> sendCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        MediaBrowserCompat browserCompat = getBrowserCompat();
        if (browserCompat == null || (!this.instance.isSessionCommandAvailable(sessionCommand) && !isContainedInCommandButtonsForMediaItems(sessionCommand))) {
            return Futures.immediateFuture(new SessionResult(-4));
        }
        final SettableFuture create = SettableFuture.create();
        browserCompat.sendCustomAction(sessionCommand.customAction, bundle, new MediaBrowserCompat.CustomActionCallback() {
            public void onResult(String str, Bundle bundle, Bundle bundle2) {
                Bundle bundle3 = new Bundle(bundle);
                bundle3.putAll(bundle2);
                create.set(new SessionResult(0, bundle3));
            }

            public void onError(String str, Bundle bundle, Bundle bundle2) {
                Bundle bundle3 = new Bundle(bundle);
                bundle3.putAll(bundle2);
                create.set(new SessionResult(-1, bundle3));
            }
        });
        return create;
    }

    private boolean isContainedInCommandButtonsForMediaItems(SessionCommand sessionCommand) {
        CommandButton commandButton;
        if (sessionCommand.commandCode == 0 && (commandButton = this.commandButtonsForMediaItems.get(sessionCommand.customAction)) != null && Objects.equals(commandButton.sessionCommand, sessionCommand)) {
            return true;
        }
        return false;
    }

    private MediaBrowserCompat getBrowserCompat(MediaLibraryService.LibraryParams libraryParams) {
        return this.browserCompats.get(libraryParams);
    }

    private static Bundle createOptionsForSubscription(MediaLibraryService.LibraryParams libraryParams) {
        return libraryParams == null ? new Bundle() : new Bundle(libraryParams.extras);
    }

    private static Bundle createOptionsWithPagingInfo(MediaLibraryService.LibraryParams libraryParams, int i, int i2) {
        Bundle createOptionsForSubscription = createOptionsForSubscription(libraryParams);
        createOptionsForSubscription.putInt("android.media.browse.extra.PAGE", i);
        createOptionsForSubscription.putInt("android.media.browse.extra.PAGE_SIZE", i2);
        return createOptionsForSubscription;
    }

    private static Bundle getExtras(MediaLibraryService.LibraryParams libraryParams) {
        if (libraryParams != null) {
            return libraryParams.extras;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public MediaItem createRootMediaItem(MediaBrowserCompat mediaBrowserCompat) {
        String root = mediaBrowserCompat.getRoot();
        return new MediaItem.Builder().setMediaId(root).setMediaMetadata(new MediaMetadata.Builder().setIsBrowsable(true).setMediaType(20).setIsPlayable(false).setExtras(mediaBrowserCompat.getExtras()).build()).build();
    }

    private class GetLibraryRootCallback extends MediaBrowserCompat.ConnectionCallback {
        private final MediaLibraryService.LibraryParams params;
        private final SettableFuture<LibraryResult<MediaItem>> result;

        public GetLibraryRootCallback(SettableFuture<LibraryResult<MediaItem>> settableFuture, MediaLibraryService.LibraryParams libraryParams) {
            this.result = settableFuture;
            this.params = libraryParams;
        }

        public void onConnected() {
            ArrayList parcelableArrayList;
            MediaBrowserCompat mediaBrowserCompat = (MediaBrowserCompat) MediaBrowserImplLegacy.this.browserCompats.get(this.params);
            if (mediaBrowserCompat == null) {
                this.result.set(LibraryResult.ofError(-1));
                return;
            }
            Bundle extras = mediaBrowserCompat.getExtras();
            if (!(extras == null || (parcelableArrayList = extras.getParcelableArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ROOT_LIST")) == null)) {
                ImmutableMap.Builder builder = null;
                for (int i = 0; i < parcelableArrayList.size(); i++) {
                    CommandButton convertCustomBrowseActionToCommandButton = LegacyConversions.convertCustomBrowseActionToCommandButton((Bundle) parcelableArrayList.get(i));
                    if (convertCustomBrowseActionToCommandButton != null) {
                        if (builder == null) {
                            builder = new ImmutableMap.Builder().putAll(MediaBrowserImplLegacy.this.commandButtonsForMediaItems);
                        }
                        builder.put(((SessionCommand) Assertions.checkNotNull(convertCustomBrowseActionToCommandButton.sessionCommand)).customAction, convertCustomBrowseActionToCommandButton);
                    }
                }
                if (builder != null) {
                    ImmutableMap unused = MediaBrowserImplLegacy.this.commandButtonsForMediaItems = builder.buildKeepingLast();
                }
            }
            this.result.set(LibraryResult.ofItem(MediaBrowserImplLegacy.this.createRootMediaItem(mediaBrowserCompat), LegacyConversions.convertToLibraryParams(MediaBrowserImplLegacy.this.context, extras)));
        }

        public void onConnectionSuspended() {
            onConnectionFailed();
        }

        public void onConnectionFailed() {
            this.result.set(LibraryResult.ofError(-3));
            MediaBrowserImplLegacy.this.release();
        }
    }

    private class SubscribeCallback extends MediaBrowserCompat.SubscriptionCallback {
        private final SettableFuture<LibraryResult<Void>> future;
        /* access modifiers changed from: private */
        public List<MediaBrowserCompat.MediaItem> receivedChildren;
        private final Bundle subscriptionOptions;
        private final String subscriptionParentId;

        public SubscribeCallback(String str, Bundle bundle, SettableFuture<LibraryResult<Void>> settableFuture) {
            this.subscriptionParentId = str;
            this.subscriptionOptions = bundle;
            this.future = settableFuture;
        }

        public void onError(String str) {
            onError(this.subscriptionParentId, this.subscriptionOptions);
        }

        public void onError(String str, Bundle bundle) {
            onErrorInternal(this.subscriptionParentId, this.subscriptionOptions);
        }

        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoadedInternal(this.subscriptionParentId, list);
        }

        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            onChildrenLoadedInternal(this.subscriptionParentId, list);
        }

        private void onErrorInternal(String str, Bundle bundle) {
            if (this.future.isDone()) {
                MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new MediaBrowserImplLegacy$SubscribeCallback$$ExternalSyntheticLambda1(this, str, bundle));
            }
            this.future.set(LibraryResult.ofError(-1));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onErrorInternal$0$androidx-media3-session-MediaBrowserImplLegacy$SubscribeCallback  reason: not valid java name */
        public /* synthetic */ void m920lambda$onErrorInternal$0$androidxmedia3sessionMediaBrowserImplLegacy$SubscribeCallback(String str, Bundle bundle, MediaBrowser.Listener listener) {
            listener.onChildrenChanged(MediaBrowserImplLegacy.this.getInstance(), str, Integer.MAX_VALUE, new MediaLibraryService.LibraryParams.Builder().setExtras(bundle).build());
        }

        private void onChildrenLoadedInternal(String str, List<MediaBrowserCompat.MediaItem> list) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaBrowserImplLegacy.TAG, "SubscribeCallback.onChildrenLoaded(): Ignoring empty parentId");
                return;
            }
            MediaBrowserCompat browserCompat = MediaBrowserImplLegacy.this.getBrowserCompat();
            if (browserCompat != null) {
                if (list == null) {
                    onError(this.subscriptionParentId, this.subscriptionOptions);
                    return;
                }
                MediaLibraryService.LibraryParams convertToLibraryParams = LegacyConversions.convertToLibraryParams(MediaBrowserImplLegacy.this.context, browserCompat.getNotifyChildrenChangedOptions());
                this.receivedChildren = list;
                MediaBrowserImplLegacy.this.getInstance().notifyBrowserListener(new MediaBrowserImplLegacy$SubscribeCallback$$ExternalSyntheticLambda0(this, str, list, convertToLibraryParams));
                this.future.set(LibraryResult.ofVoid());
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onChildrenLoadedInternal$1$androidx-media3-session-MediaBrowserImplLegacy$SubscribeCallback  reason: not valid java name */
        public /* synthetic */ void m919lambda$onChildrenLoadedInternal$1$androidxmedia3sessionMediaBrowserImplLegacy$SubscribeCallback(String str, List list, MediaLibraryService.LibraryParams libraryParams, MediaBrowser.Listener listener) {
            listener.onChildrenChanged(MediaBrowserImplLegacy.this.getInstance(), str, list.size(), libraryParams);
        }

        public boolean canServeGetChildrenRequest(String str, int i) {
            if (!this.subscriptionParentId.equals(str) || this.receivedChildren == null || i != this.subscriptionOptions.getInt("android.media.browse.extra.PAGE", 0)) {
                return false;
            }
            return true;
        }
    }

    private class GetChildrenCallback extends MediaBrowserCompat.SubscriptionCallback {
        private final SettableFuture<LibraryResult<ImmutableList<MediaItem>>> future;
        private final String parentId;

        public GetChildrenCallback(SettableFuture<LibraryResult<ImmutableList<MediaItem>>> settableFuture, String str) {
            this.future = settableFuture;
            this.parentId = str;
        }

        public void onError(String str) {
            onErrorInternal();
        }

        public void onError(String str, Bundle bundle) {
            onErrorInternal();
        }

        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list) {
            onChildrenLoadedInternal(str, list);
        }

        public void onChildrenLoaded(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
            onChildrenLoadedInternal(str, list);
        }

        private void onErrorInternal() {
            this.future.set(LibraryResult.ofError(-1));
        }

        private void onChildrenLoadedInternal(String str, List<MediaBrowserCompat.MediaItem> list) {
            if (TextUtils.isEmpty(str)) {
                Log.w(MediaBrowserImplLegacy.TAG, "GetChildrenCallback.onChildrenLoaded(): Ignoring empty parentId");
                return;
            }
            MediaBrowserCompat browserCompat = MediaBrowserImplLegacy.this.getBrowserCompat();
            if (browserCompat == null) {
                this.future.set(LibraryResult.ofError(-100));
                return;
            }
            browserCompat.unsubscribe(this.parentId, this);
            if (list == null) {
                this.future.set(LibraryResult.ofError(-1));
            } else {
                this.future.set(LibraryResult.ofItemList(LegacyConversions.convertBrowserItemListToMediaItemList(list), (MediaLibraryService.LibraryParams) null));
            }
        }
    }
}
