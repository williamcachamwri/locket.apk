package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

class MediaSessionServiceLegacyStub extends MediaBrowserServiceCompat {
    private static final String TAG = "MSSLegacyStub";
    private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;
    private final MediaSessionManager manager;
    private final MediaSessionImpl sessionImpl;

    public MediaSessionServiceLegacyStub(MediaSessionImpl mediaSessionImpl) {
        this.manager = MediaSessionManager.getSessionManager(mediaSessionImpl.getContext());
        this.sessionImpl = mediaSessionImpl;
        this.connectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
    }

    public void initialize(MediaSessionCompat.Token token) {
        attachToBaseContext(this.sessionImpl.getContext());
        onCreate();
        setSessionToken(token);
    }

    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        MediaSessionManager.RemoteUserInfo currentBrowserInfo = getCurrentBrowserInfo();
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        MediaSession.ControllerInfo createControllerInfo = createControllerInfo(currentBrowserInfo, bundle);
        AtomicReference atomicReference = new AtomicReference();
        ConditionVariable conditionVariable = new ConditionVariable();
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new MediaSessionServiceLegacyStub$$ExternalSyntheticLambda0(this, atomicReference, createControllerInfo, conditionVariable));
        try {
            conditionVariable.block();
            MediaSession.ConnectionResult connectionResult = (MediaSession.ConnectionResult) atomicReference.get();
            if (!connectionResult.isAccepted) {
                return null;
            }
            this.connectedControllersManager.addController(currentBrowserInfo, createControllerInfo, connectionResult.availableSessionCommands, connectionResult.availablePlayerCommands);
            return MediaUtils.defaultBrowserRoot;
        } catch (InterruptedException e) {
            Log.e(TAG, "Couldn't get a result from onConnect", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onGetRoot$0$androidx-media3-session-MediaSessionServiceLegacyStub  reason: not valid java name */
    public /* synthetic */ void m1099lambda$onGetRoot$0$androidxmedia3sessionMediaSessionServiceLegacyStub(AtomicReference atomicReference, MediaSession.ControllerInfo controllerInfo, ConditionVariable conditionVariable) {
        atomicReference.set(this.sessionImpl.onConnectOnHandler(controllerInfo));
        conditionVariable.open();
    }

    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        result.sendResult(null);
    }

    public MediaSession.ControllerInfo createControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo, Bundle bundle) {
        return new MediaSession.ControllerInfo(remoteUserInfo, 0, 0, this.manager.isTrustedForMediaControl(remoteUserInfo), (MediaSession.ControllerCb) null, bundle, LegacyConversions.extractMaxCommandsForMediaItemFromRootHints(bundle));
    }

    public final MediaSessionManager getMediaSessionManager() {
        return this.manager;
    }

    public final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }
}
