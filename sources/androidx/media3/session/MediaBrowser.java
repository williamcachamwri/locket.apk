package androidx.media3.session;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BitmapLoader;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaLibraryService;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

public final class MediaBrowser extends MediaController {
    private static final String WRONG_THREAD_ERROR_MESSAGE = "MediaBrowser method is called from a wrong thread. See javadoc of MediaController for details.";
    @NotOnlyInitialized
    private MediaBrowserImpl impl;

    public interface Listener extends MediaController.Listener {
        void onChildrenChanged(MediaBrowser mediaBrowser, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        }

        void onSearchResultChanged(MediaBrowser mediaBrowser, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        }
    }

    interface MediaBrowserImpl extends MediaController.MediaControllerImpl {
        ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult<MediaItem>> getItem(String str);

        ListenableFuture<LibraryResult<MediaItem>> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult<Void>> search(String str, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult<Void>> subscribe(String str, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult<Void>> unsubscribe(String str);
    }

    public static final class Builder {
        private Looper applicationLooper = Util.getCurrentOrMainLooper();
        private BitmapLoader bitmapLoader;
        private Bundle connectionHints = Bundle.EMPTY;
        private final Context context;
        private Listener listener = new Listener() {
        };
        private int maxCommandsForMediaItems;
        private final SessionToken token;

        public Builder(Context context2, SessionToken sessionToken) {
            this.context = (Context) Assertions.checkNotNull(context2);
            this.token = (SessionToken) Assertions.checkNotNull(sessionToken);
        }

        public Builder setConnectionHints(Bundle bundle) {
            this.connectionHints = new Bundle((Bundle) Assertions.checkNotNull(bundle));
            return this;
        }

        public Builder setListener(Listener listener2) {
            this.listener = (Listener) Assertions.checkNotNull(listener2);
            return this;
        }

        public Builder setApplicationLooper(Looper looper) {
            this.applicationLooper = (Looper) Assertions.checkNotNull(looper);
            return this;
        }

        public Builder setBitmapLoader(BitmapLoader bitmapLoader2) {
            this.bitmapLoader = (BitmapLoader) Assertions.checkNotNull(bitmapLoader2);
            return this;
        }

        public Builder setMaxCommandsForMediaItems(int i) {
            Assertions.checkArgument(i >= 0);
            this.maxCommandsForMediaItems = i;
            return this;
        }

        public ListenableFuture<MediaBrowser> buildAsync() {
            MediaControllerHolder mediaControllerHolder = new MediaControllerHolder(this.applicationLooper);
            if (this.token.isLegacySession() && this.bitmapLoader == null) {
                this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader(this.context));
            }
            Util.postOrRun(new Handler(this.applicationLooper), new MediaBrowser$Builder$$ExternalSyntheticLambda0(mediaControllerHolder, new MediaBrowser(this.context, this.token, this.connectionHints, this.listener, this.applicationLooper, mediaControllerHolder, this.bitmapLoader, this.maxCommandsForMediaItems)));
            return mediaControllerHolder;
        }
    }

    MediaBrowser(Context context, SessionToken sessionToken, Bundle bundle, Listener listener, Looper looper, MediaController.ConnectionCallback connectionCallback, BitmapLoader bitmapLoader, int i) {
        super(context, sessionToken, bundle, listener, looper, connectionCallback, bitmapLoader, i);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [androidx.media3.session.MediaBrowser$MediaBrowserImpl] */
    /* JADX WARNING: type inference failed for: r1v2, types: [androidx.media3.session.MediaBrowserImplBase] */
    /* JADX WARNING: type inference failed for: r1v3, types: [androidx.media3.session.MediaBrowserImplLegacy] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media3.session.MediaBrowser.MediaBrowserImpl createImpl(android.content.Context r9, androidx.media3.session.SessionToken r10, android.os.Bundle r11, android.os.Looper r12, androidx.media3.common.util.BitmapLoader r13) {
        /*
            r8 = this;
            boolean r0 = r10.isLegacySession()
            if (r0 == 0) goto L_0x0019
            androidx.media3.session.MediaBrowserImplLegacy r0 = new androidx.media3.session.MediaBrowserImplLegacy
            java.lang.Object r13 = androidx.media3.common.util.Assertions.checkNotNull(r13)
            r7 = r13
            androidx.media3.common.util.BitmapLoader r7 = (androidx.media3.common.util.BitmapLoader) r7
            r1 = r0
            r2 = r9
            r3 = r8
            r4 = r10
            r5 = r11
            r6 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7)
            goto L_0x0024
        L_0x0019:
            androidx.media3.session.MediaBrowserImplBase r0 = new androidx.media3.session.MediaBrowserImplBase
            r1 = r0
            r2 = r9
            r3 = r8
            r4 = r10
            r5 = r11
            r6 = r12
            r1.<init>(r2, r3, r4, r5, r6)
        L_0x0024:
            r8.impl = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaBrowser.createImpl(android.content.Context, androidx.media3.session.SessionToken, android.os.Bundle, android.os.Looper, androidx.media3.common.util.BitmapLoader):androidx.media3.session.MediaBrowser$MediaBrowserImpl");
    }

    public ListenableFuture<LibraryResult<MediaItem>> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        if (isConnected()) {
            return ((MediaBrowserImpl) Assertions.checkNotNull(this.impl)).getLibraryRoot(libraryParams);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<Void>> subscribe(String str, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        Assertions.checkNotEmpty(str, "parentId must not be empty");
        if (isConnected()) {
            return ((MediaBrowserImpl) Assertions.checkNotNull(this.impl)).subscribe(str, libraryParams);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<Void>> unsubscribe(String str) {
        verifyApplicationThread();
        Assertions.checkNotEmpty(str, "parentId must not be empty");
        if (isConnected()) {
            return ((MediaBrowserImpl) Assertions.checkNotNull(this.impl)).unsubscribe(str);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        Assertions.checkNotEmpty(str, "parentId must not be empty");
        boolean z = false;
        Assertions.checkArgument(i >= 0, "page must not be negative");
        if (i2 >= 1) {
            z = true;
        }
        Assertions.checkArgument(z, "pageSize must not be less than 1");
        if (isConnected()) {
            return ((MediaBrowserImpl) Assertions.checkNotNull(this.impl)).getChildren(str, i, i2, libraryParams);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<MediaItem>> getItem(String str) {
        verifyApplicationThread();
        Assertions.checkNotEmpty(str, "mediaId must not be empty");
        if (isConnected()) {
            return ((MediaBrowserImpl) Assertions.checkNotNull(this.impl)).getItem(str);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<Void>> search(String str, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        Assertions.checkNotEmpty(str, "query must not be empty");
        if (isConnected()) {
            return ((MediaBrowserImpl) Assertions.checkNotNull(this.impl)).search(str, libraryParams);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        Assertions.checkNotEmpty(str, "query must not be empty");
        boolean z = false;
        Assertions.checkArgument(i >= 0, "page must not be negative");
        if (i2 >= 1) {
            z = true;
        }
        Assertions.checkArgument(z, "pageSize must not be less than 1");
        if (isConnected()) {
            return ((MediaBrowserImpl) Assertions.checkNotNull(this.impl)).getSearchResult(str, i, i2, libraryParams);
        }
        return createDisconnectedFuture();
    }

    private static <V> ListenableFuture<LibraryResult<V>> createDisconnectedFuture() {
        return Futures.immediateFuture(LibraryResult.ofError(-100));
    }

    private void verifyApplicationThread() {
        Assertions.checkState(Looper.myLooper() == getApplicationLooper(), WRONG_THREAD_ERROR_MESSAGE);
    }

    /* access modifiers changed from: package-private */
    public void notifyBrowserListener(Consumer<Listener> consumer) {
        Listener listener = (Listener) this.listener;
        if (listener != null) {
            Util.postOrRun(this.applicationHandler, new MediaBrowser$$ExternalSyntheticLambda0(consumer, listener));
        }
    }
}
