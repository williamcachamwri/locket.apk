package androidx.media3.session;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.media3.common.Player;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.IMediaController;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.PlayerInfo;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;

class MediaControllerStub extends IMediaController.Stub {
    private static final String TAG = "MediaControllerStub";
    public static final int VERSION_INT = 7;
    private final WeakReference<MediaControllerImplBase> controller;

    private interface ControllerTask<T extends MediaControllerImplBase> {
        void run(T t);
    }

    public MediaControllerStub(MediaControllerImplBase mediaControllerImplBase) {
        this.controller = new WeakReference<>(mediaControllerImplBase);
    }

    public void onSessionResult(int i, Bundle bundle) {
        if (bundle != null) {
            try {
                setControllerFutureResult(i, SessionResult.fromBundle(bundle));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for SessionResult", e);
            }
        }
    }

    public void onLibraryResult(int i, Bundle bundle) {
        if (bundle != null) {
            try {
                setControllerFutureResult(i, LibraryResult.fromUnknownBundle(bundle));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryResult", e);
            }
        }
    }

    public void onConnected(int i, Bundle bundle) {
        if (bundle != null) {
            try {
                dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda12(ConnectionState.fromBundle(bundle)));
            } catch (RuntimeException e) {
                Log.w(TAG, "Malformed Bundle for ConnectionResult. Disconnected from the session.", e);
                onDisconnected(i);
            }
        }
    }

    public void onDisconnected(int i) {
        dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda6());
    }

    static /* synthetic */ void lambda$onDisconnected$1(MediaControllerImplBase mediaControllerImplBase) {
        MediaController instance = mediaControllerImplBase.getInstance();
        MediaController instance2 = mediaControllerImplBase.getInstance();
        Objects.requireNonNull(instance2);
        instance.runOnApplicationLooper(new MediaControllerImplBase$$ExternalSyntheticLambda46(instance2));
    }

    public void onSetCustomLayout(int i, List<Bundle> list) {
        if (list != null) {
            try {
                int sessionInterfaceVersion = getSessionInterfaceVersion();
                if (sessionInterfaceVersion != -1) {
                    dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda10(i, BundleCollectionUtil.fromBundleList(new MediaControllerStub$$ExternalSyntheticLambda9(sessionInterfaceVersion), list)));
                }
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for CommandButton", e);
            }
        }
    }

    public void onSetMediaButtonPreferences(int i, List<Bundle> list) {
        if (list != null) {
            try {
                int sessionInterfaceVersion = getSessionInterfaceVersion();
                if (sessionInterfaceVersion != -1) {
                    dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda8(i, BundleCollectionUtil.fromBundleList(new MediaControllerStub$$ExternalSyntheticLambda7(sessionInterfaceVersion), list)));
                }
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for CommandButton", e);
            }
        }
    }

    public void onAvailableCommandsChangedFromSession(int i, Bundle bundle, Bundle bundle2) {
        if (bundle != null && bundle2 != null) {
            try {
                try {
                    dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda2(SessionCommands.fromBundle(bundle), Player.Commands.fromBundle(bundle2)));
                } catch (RuntimeException e) {
                    Log.w(TAG, "Ignoring malformed Bundle for Commands", e);
                }
            } catch (RuntimeException e2) {
                Log.w(TAG, "Ignoring malformed Bundle for SessionCommands", e2);
            }
        }
    }

    public void onAvailableCommandsChangedFromPlayer(int i, Bundle bundle) {
        if (bundle != null) {
            try {
                dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda13(Player.Commands.fromBundle(bundle)));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for Commands", e);
            }
        }
    }

    public void onCustomCommand(int i, Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            Log.w(TAG, "Ignoring custom command with null args.");
            return;
        }
        try {
            dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda3(i, SessionCommand.fromBundle(bundle), bundle2));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionCommand", e);
        }
    }

    public void onSessionActivityChanged(int i, PendingIntent pendingIntent) throws RemoteException {
        if (pendingIntent == null) {
            Log.w(TAG, "Ignoring null session activity intent");
        } else {
            dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda16(i, pendingIntent));
        }
    }

    public void onPeriodicSessionPositionInfoChanged(int i, Bundle bundle) {
        if (bundle != null) {
            try {
                dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda17(SessionPositionInfo.fromBundle(bundle)));
            } catch (RuntimeException e) {
                Log.w(TAG, "Ignoring malformed Bundle for SessionPositionInfo", e);
            }
        }
    }

    @Deprecated
    public void onPlayerInfoChanged(int i, Bundle bundle, boolean z) {
        onPlayerInfoChangedWithExclusions(i, bundle, new PlayerInfo.BundlingExclusions(z, true).toBundle());
    }

    public void onPlayerInfoChangedWithExclusions(int i, Bundle bundle, Bundle bundle2) {
        if (bundle != null && bundle2 != null) {
            try {
                int sessionInterfaceVersion = getSessionInterfaceVersion();
                if (sessionInterfaceVersion != -1) {
                    try {
                        dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda0(PlayerInfo.fromBundle(bundle, sessionInterfaceVersion), PlayerInfo.BundlingExclusions.fromBundle(bundle2)));
                    } catch (RuntimeException e) {
                        Log.w(TAG, "Ignoring malformed Bundle for BundlingExclusions", e);
                    }
                }
            } catch (RuntimeException e2) {
                Log.w(TAG, "Ignoring malformed Bundle for PlayerInfo", e2);
            }
        }
    }

    public void onExtrasChanged(int i, Bundle bundle) {
        if (bundle == null) {
            Log.w(TAG, "Ignoring null Bundle for extras");
        } else {
            dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda15(bundle));
        }
    }

    public void onError(int i, Bundle bundle) throws RemoteException {
        try {
            dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda4(i, SessionError.fromBundle(bundle)));
        } catch (RuntimeException e) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionError", e);
        }
    }

    public void onRenderedFirstFrame(int i) {
        dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda11());
    }

    public void onSearchResultChanged(int i, String str, int i2, Bundle bundle) throws RuntimeException {
        MediaLibraryService.LibraryParams libraryParams;
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onSearchResultChanged(): Ignoring empty query");
        } else if (i2 < 0) {
            Log.w(TAG, "onSearchResultChanged(): Ignoring negative itemCount: " + i2);
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
            dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda5(str, i2, libraryParams));
        }
    }

    public void onChildrenChanged(int i, String str, int i2, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParams;
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onChildrenChanged(): Ignoring empty parentId");
        } else if (i2 < 0) {
            Log.w(TAG, "onChildrenChanged(): Ignoring negative itemCount: " + i2);
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
            dispatchControllerTaskOnHandler(new MediaControllerStub$$ExternalSyntheticLambda14(str, i2, libraryParams));
        }
    }

    public void destroy() {
        this.controller.clear();
    }

    private <T> void setControllerFutureResult(int i, T t) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaControllerImplBase mediaControllerImplBase = (MediaControllerImplBase) this.controller.get();
            if (mediaControllerImplBase != null) {
                mediaControllerImplBase.setFutureResult(i, t);
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    private <T extends MediaControllerImplBase> void dispatchControllerTaskOnHandler(ControllerTask<T> controllerTask) {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaControllerImplBase mediaControllerImplBase = (MediaControllerImplBase) this.controller.get();
            if (mediaControllerImplBase != null) {
                Util.postOrRun(mediaControllerImplBase.getInstance().applicationHandler, new MediaControllerStub$$ExternalSyntheticLambda1(mediaControllerImplBase, controllerTask));
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    static /* synthetic */ void lambda$dispatchControllerTaskOnHandler$16(MediaControllerImplBase mediaControllerImplBase, ControllerTask controllerTask) {
        if (!mediaControllerImplBase.isReleased()) {
            controllerTask.run(mediaControllerImplBase);
        }
    }

    private int getSessionInterfaceVersion() {
        SessionToken connectedToken;
        MediaControllerImplBase mediaControllerImplBase = (MediaControllerImplBase) this.controller.get();
        if (mediaControllerImplBase == null || (connectedToken = mediaControllerImplBase.getConnectedToken()) == null) {
            return -1;
        }
        return connectedToken.getInterfaceVersion();
    }
}
