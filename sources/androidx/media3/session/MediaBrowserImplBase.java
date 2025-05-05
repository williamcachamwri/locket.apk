package androidx.media3.session;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Log;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.SequencedFutureManager;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

class MediaBrowserImplBase extends MediaControllerImplBase implements MediaBrowser.MediaBrowserImpl {
    private final MediaBrowser instance;

    private interface RemoteLibrarySessionTask {
        void run(IMediaSession iMediaSession, int i) throws RemoteException;
    }

    MediaBrowserImplBase(Context context, MediaBrowser mediaBrowser, SessionToken sessionToken, Bundle bundle, Looper looper) {
        super(context, mediaBrowser, sessionToken, bundle, looper);
        this.instance = mediaBrowser;
    }

    /* access modifiers changed from: package-private */
    public MediaBrowser getInstance() {
        return this.instance;
    }

    public ListenableFuture<LibraryResult<MediaItem>> getLibraryRoot(final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(50000, new RemoteLibrarySessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                MediaLibraryService.LibraryParams libraryParams = libraryParams;
                iMediaSession.getLibraryRoot(mediaControllerStub, i, libraryParams == null ? null : libraryParams.toBundle());
            }
        });
    }

    public ListenableFuture<LibraryResult<Void>> subscribe(final String str, final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE, new RemoteLibrarySessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str = str;
                MediaLibraryService.LibraryParams libraryParams = libraryParams;
                iMediaSession.subscribe(mediaControllerStub, i, str, libraryParams == null ? null : libraryParams.toBundle());
            }
        });
    }

    public ListenableFuture<LibraryResult<Void>> unsubscribe(final String str) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE, new RemoteLibrarySessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.unsubscribe(MediaBrowserImplBase.this.controllerStub, i, str);
            }
        });
    }

    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        final String str2 = str;
        final int i3 = i;
        final int i4 = i2;
        final MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN, new RemoteLibrarySessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str = str2;
                int i2 = i3;
                int i3 = i4;
                MediaLibraryService.LibraryParams libraryParams = libraryParams2;
                iMediaSession.getChildren(mediaControllerStub, i, str, i2, i3, libraryParams == null ? null : libraryParams.toBundle());
            }
        });
    }

    public ListenableFuture<LibraryResult<MediaItem>> getItem(final String str) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM, new RemoteLibrarySessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                iMediaSession.getItem(MediaBrowserImplBase.this.controllerStub, i, str);
            }
        });
    }

    public ListenableFuture<LibraryResult<Void>> search(final String str, final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_SEARCH, new RemoteLibrarySessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str = str;
                MediaLibraryService.LibraryParams libraryParams = libraryParams;
                iMediaSession.search(mediaControllerStub, i, str, libraryParams == null ? null : libraryParams.toBundle());
            }
        });
    }

    public ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        final String str2 = str;
        final int i3 = i;
        final int i4 = i2;
        final MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT, new RemoteLibrarySessionTask() {
            public void run(IMediaSession iMediaSession, int i) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str = str2;
                int i2 = i3;
                int i3 = i4;
                MediaLibraryService.LibraryParams libraryParams = libraryParams2;
                iMediaSession.getSearchResult(mediaControllerStub, i, str, i2, i3, libraryParams == null ? null : libraryParams.toBundle());
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void notifySearchResultChanged(String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        if (isConnected()) {
            getInstance().notifyBrowserListener(new MediaBrowserImplBase$$ExternalSyntheticLambda1(this, str, i, libraryParams));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$notifySearchResultChanged$0$androidx-media3-session-MediaBrowserImplBase  reason: not valid java name */
    public /* synthetic */ void m916lambda$notifySearchResultChanged$0$androidxmedia3sessionMediaBrowserImplBase(String str, int i, MediaLibraryService.LibraryParams libraryParams, MediaBrowser.Listener listener) {
        listener.onSearchResultChanged(getInstance(), str, i, libraryParams);
    }

    /* access modifiers changed from: package-private */
    public void notifyChildrenChanged(String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        if (isConnected()) {
            getInstance().notifyBrowserListener(new MediaBrowserImplBase$$ExternalSyntheticLambda0(this, str, i, libraryParams));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$notifyChildrenChanged$1$androidx-media3-session-MediaBrowserImplBase  reason: not valid java name */
    public /* synthetic */ void m915lambda$notifyChildrenChanged$1$androidxmedia3sessionMediaBrowserImplBase(String str, int i, MediaLibraryService.LibraryParams libraryParams, MediaBrowser.Listener listener) {
        listener.onChildrenChanged(getInstance(), str, i, libraryParams);
    }

    private <V> ListenableFuture<LibraryResult<V>> dispatchRemoteLibrarySessionTask(int i, RemoteLibrarySessionTask remoteLibrarySessionTask) {
        IMediaSession sessionInterfaceWithSessionCommandIfAble = getSessionInterfaceWithSessionCommandIfAble(i);
        if (sessionInterfaceWithSessionCommandIfAble == null) {
            return Futures.immediateFuture(LibraryResult.ofError(-4));
        }
        SequencedFutureManager.SequencedFuture createSequencedFuture = this.sequencedFutureManager.createSequencedFuture(LibraryResult.ofError(1));
        try {
            remoteLibrarySessionTask.run(sessionInterfaceWithSessionCommandIfAble, createSequencedFuture.getSequenceNumber());
        } catch (RemoteException e) {
            Log.w(MediaControllerImplBase.TAG, "Cannot connect to the service or the session is gone", e);
            this.sequencedFutureManager.setFutureResult(createSequencedFuture.getSequenceNumber(), LibraryResult.ofError(-100));
        }
        return createSequencedFuture;
    }
}
