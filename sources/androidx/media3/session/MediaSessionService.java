package androidx.media3.session;

import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import androidx.collection.ArrayMap;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.DefaultMediaNotificationProvider;
import androidx.media3.session.IMediaSessionService;
import androidx.media3.session.MediaNotification;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaSessionManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class MediaSessionService extends Service {
    public static final String SERVICE_INTERFACE = "androidx.media3.session.MediaSessionService";
    private static final String TAG = "MSessionService";
    private DefaultActionFactory actionFactory;
    private boolean defaultMethodCalled = false;
    private Listener listener;
    private final Object lock = new Object();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private MediaNotificationManager mediaNotificationManager;
    private MediaNotification.Provider mediaNotificationProvider;
    private final Map<String, MediaSession> sessions = new ArrayMap();
    private MediaSessionServiceStub stub;

    public interface Listener {
        void onForegroundServiceStartNotAllowedException() {
        }
    }

    public abstract MediaSession onGetSession(MediaSession.ControllerInfo controllerInfo);

    public void onCreate() {
        super.onCreate();
        synchronized (this.lock) {
            this.stub = new MediaSessionServiceStub(this);
        }
    }

    public final void addSession(MediaSession mediaSession) {
        MediaSession mediaSession2;
        Assertions.checkNotNull(mediaSession, "session must not be null");
        boolean z = true;
        Assertions.checkArgument(!mediaSession.isReleased(), "session is already released");
        synchronized (this.lock) {
            mediaSession2 = this.sessions.get(mediaSession.getId());
            if (mediaSession2 != null) {
                if (mediaSession2 != mediaSession) {
                    z = false;
                }
            }
            Assertions.checkArgument(z, "Session ID should be unique");
            this.sessions.put(mediaSession.getId(), mediaSession);
        }
        if (mediaSession2 == null) {
            Util.postOrRun(this.mainHandler, new MediaSessionService$$ExternalSyntheticLambda0(this, getMediaNotificationManager(), mediaSession));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addSession$0$androidx-media3-session-MediaSessionService  reason: not valid java name */
    public /* synthetic */ void m1096lambda$addSession$0$androidxmedia3sessionMediaSessionService(MediaNotificationManager mediaNotificationManager2, MediaSession mediaSession) {
        mediaNotificationManager2.addSession(mediaSession);
        mediaSession.setListener(new MediaSessionListener());
    }

    public final void removeSession(MediaSession mediaSession) {
        Assertions.checkNotNull(mediaSession, "session must not be null");
        synchronized (this.lock) {
            Assertions.checkArgument(this.sessions.containsKey(mediaSession.getId()), "session not found");
            this.sessions.remove(mediaSession.getId());
        }
        Util.postOrRun(this.mainHandler, new MediaSessionService$$ExternalSyntheticLambda1(getMediaNotificationManager(), mediaSession));
    }

    static /* synthetic */ void lambda$removeSession$1(MediaNotificationManager mediaNotificationManager2, MediaSession mediaSession) {
        mediaNotificationManager2.removeSession(mediaSession);
        mediaSession.clearListener();
    }

    public final List<MediaSession> getSessions() {
        ArrayList arrayList;
        synchronized (this.lock) {
            arrayList = new ArrayList(this.sessions.values());
        }
        return arrayList;
    }

    public final boolean isSessionAdded(MediaSession mediaSession) {
        boolean containsKey;
        synchronized (this.lock) {
            containsKey = this.sessions.containsKey(mediaSession.getId());
        }
        return containsKey;
    }

    public final void setListener(Listener listener2) {
        synchronized (this.lock) {
            this.listener = listener2;
        }
    }

    public final void clearListener() {
        synchronized (this.lock) {
            this.listener = null;
        }
    }

    public IBinder onBind(Intent intent) {
        String action;
        MediaSession onGetSession;
        if (intent == null || (action = intent.getAction()) == null) {
            return null;
        }
        action.hashCode();
        if (action.equals(SERVICE_INTERFACE)) {
            return getServiceBinder();
        }
        if (!action.equals("android.media.browse.MediaBrowserService") || (onGetSession = onGetSession(MediaSession.ControllerInfo.createLegacyControllerInfo())) == null) {
            return null;
        }
        addSession(onGetSession);
        return onGetSession.getLegacyBrowserServiceBinder();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        String customAction;
        if (intent == null) {
            return 1;
        }
        DefaultActionFactory actionFactory2 = getActionFactory();
        Uri data = intent.getData();
        MediaSession session = data != null ? MediaSession.getSession(data) : null;
        if (actionFactory2.isMediaAction(intent)) {
            if (session == null) {
                session = onGetSession(MediaSession.ControllerInfo.createLegacyControllerInfo());
                if (session == null) {
                    return 1;
                }
                addSession(session);
            }
            MediaSessionImpl impl = session.getImpl();
            impl.getApplicationHandler().post(new MediaSessionService$$ExternalSyntheticLambda3(impl, intent));
        } else if (session == null || !actionFactory2.isCustomAction(intent) || (customAction = actionFactory2.getCustomAction(intent)) == null) {
            return 1;
        } else {
            getMediaNotificationManager().onCustomAction(session, customAction, actionFactory2.getCustomActionExtras(intent));
        }
        return 1;
    }

    static /* synthetic */ void lambda$onStartCommand$2(MediaSessionImpl mediaSessionImpl, Intent intent) {
        MediaSession.ControllerInfo mediaNotificationControllerInfo = mediaSessionImpl.getMediaNotificationControllerInfo();
        if (mediaNotificationControllerInfo == null) {
            mediaNotificationControllerInfo = createFallbackMediaButtonCaller(intent);
        }
        if (!mediaSessionImpl.onMediaButtonEvent(mediaNotificationControllerInfo, intent)) {
            Log.d(TAG, "Ignored unrecognized media button intent.");
        }
    }

    private static MediaSession.ControllerInfo createFallbackMediaButtonCaller(Intent intent) {
        ComponentName component = intent.getComponent();
        return new MediaSession.ControllerInfo(new MediaSessionManager.RemoteUserInfo(component != null ? component.getPackageName() : SERVICE_INTERFACE, -1, -1), MediaLibraryInfo.VERSION_INT, 7, false, (MediaSession.ControllerCb) null, Bundle.EMPTY, 0);
    }

    public boolean isPlaybackOngoing() {
        return getMediaNotificationManager().isStartedInForeground();
    }

    public void pauseAllPlayersAndStopSelf() {
        List<MediaSession> sessions2 = getSessions();
        for (int i = 0; i < sessions2.size(); i++) {
            sessions2.get(i).getPlayer().setPlayWhenReady(false);
        }
        stopSelf();
    }

    public void onTaskRemoved(Intent intent) {
        if (!isPlaybackOngoing()) {
            stopSelf();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        synchronized (this.lock) {
            MediaSessionServiceStub mediaSessionServiceStub = this.stub;
            if (mediaSessionServiceStub != null) {
                mediaSessionServiceStub.release();
                this.stub = null;
            }
        }
    }

    @Deprecated
    public void onUpdateNotification(MediaSession mediaSession) {
        this.defaultMethodCalled = true;
    }

    public void onUpdateNotification(MediaSession mediaSession, boolean z) {
        onUpdateNotification(mediaSession);
        if (this.defaultMethodCalled) {
            getMediaNotificationManager().updateNotification(mediaSession, z);
        }
    }

    /* access modifiers changed from: protected */
    public final void setMediaNotificationProvider(MediaNotification.Provider provider) {
        Assertions.checkNotNull(provider);
        synchronized (this.lock) {
            this.mediaNotificationProvider = provider;
        }
    }

    /* access modifiers changed from: package-private */
    public IBinder getServiceBinder() {
        IBinder asBinder;
        synchronized (this.lock) {
            asBinder = ((MediaSessionServiceStub) Assertions.checkStateNotNull(this.stub)).asBinder();
        }
        return asBinder;
    }

    /* access modifiers changed from: package-private */
    public boolean onUpdateNotificationInternal(MediaSession mediaSession, boolean z) {
        try {
            onUpdateNotification(mediaSession, getMediaNotificationManager().shouldRunInForeground(mediaSession, z));
            return true;
        } catch (IllegalStateException e) {
            if (Util.SDK_INT < 31 || !Api31.instanceOfForegroundServiceStartNotAllowedException(e)) {
                throw e;
            }
            Log.e(TAG, "Failed to start foreground", e);
            onForegroundServiceStartNotAllowedException();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public MediaNotificationManager getMediaNotificationManager() {
        MediaNotificationManager mediaNotificationManager2;
        synchronized (this.lock) {
            if (this.mediaNotificationManager == null) {
                if (this.mediaNotificationProvider == null) {
                    this.mediaNotificationProvider = new DefaultMediaNotificationProvider.Builder(getApplicationContext()).build();
                }
                this.mediaNotificationManager = new MediaNotificationManager(this, this.mediaNotificationProvider, getActionFactory());
            }
            mediaNotificationManager2 = this.mediaNotificationManager;
        }
        return mediaNotificationManager2;
    }

    private DefaultActionFactory getActionFactory() {
        DefaultActionFactory defaultActionFactory;
        synchronized (this.lock) {
            if (this.actionFactory == null) {
                this.actionFactory = new DefaultActionFactory(this);
            }
            defaultActionFactory = this.actionFactory;
        }
        return defaultActionFactory;
    }

    private Listener getListener() {
        Listener listener2;
        synchronized (this.lock) {
            listener2 = this.listener;
        }
        return listener2;
    }

    private void onForegroundServiceStartNotAllowedException() {
        this.mainHandler.post(new MediaSessionService$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onForegroundServiceStartNotAllowedException$3$androidx-media3-session-MediaSessionService  reason: not valid java name */
    public /* synthetic */ void m1097lambda$onForegroundServiceStartNotAllowedException$3$androidxmedia3sessionMediaSessionService() {
        Listener listener2 = getListener();
        if (listener2 != null) {
            listener2.onForegroundServiceStartNotAllowedException();
        }
    }

    private final class MediaSessionListener implements MediaSession.Listener {
        private MediaSessionListener() {
        }

        public void onNotificationRefreshRequired(MediaSession mediaSession) {
            MediaSessionService.this.onUpdateNotificationInternal(mediaSession, false);
        }

        public boolean onPlayRequested(MediaSession mediaSession) {
            if (Util.SDK_INT < 31 || Util.SDK_INT >= 33 || MediaSessionService.this.getMediaNotificationManager().isStartedInForeground()) {
                return true;
            }
            return MediaSessionService.this.onUpdateNotificationInternal(mediaSession, true);
        }
    }

    private static final class MediaSessionServiceStub extends IMediaSessionService.Stub {
        private final Handler handler;
        private final MediaSessionManager mediaSessionManager;
        private final Set<IMediaController> pendingControllers = Collections.synchronizedSet(new HashSet());
        private final WeakReference<MediaSessionService> serviceReference;

        public MediaSessionServiceStub(MediaSessionService mediaSessionService) {
            this.serviceReference = new WeakReference<>(mediaSessionService);
            Context applicationContext = mediaSessionService.getApplicationContext();
            this.handler = new Handler(applicationContext.getMainLooper());
            this.mediaSessionManager = MediaSessionManager.getSessionManager(applicationContext);
        }

        public void connect(IMediaController iMediaController, Bundle bundle) {
            if (iMediaController != null && bundle != null) {
                try {
                    ConnectionRequest fromBundle = ConnectionRequest.fromBundle(bundle);
                    if (this.serviceReference.get() == null) {
                        try {
                            iMediaController.onDisconnected(0);
                        } catch (RemoteException unused) {
                        }
                    } else {
                        int callingPid = Binder.getCallingPid();
                        int callingUid = Binder.getCallingUid();
                        long clearCallingIdentity = Binder.clearCallingIdentity();
                        if (callingPid == 0) {
                            callingPid = fromBundle.pid;
                        }
                        MediaSessionManager.RemoteUserInfo remoteUserInfo = new MediaSessionManager.RemoteUserInfo(fromBundle.packageName, callingPid, callingUid);
                        boolean isTrustedForMediaControl = this.mediaSessionManager.isTrustedForMediaControl(remoteUserInfo);
                        this.pendingControllers.add(iMediaController);
                        try {
                            this.handler.post(new MediaSessionService$MediaSessionServiceStub$$ExternalSyntheticLambda0(this, iMediaController, remoteUserInfo, fromBundle, isTrustedForMediaControl));
                        } finally {
                            Binder.restoreCallingIdentity(clearCallingIdentity);
                        }
                    }
                } catch (RuntimeException e) {
                    Log.w(MediaSessionService.TAG, "Ignoring malformed Bundle for ConnectionRequest", e);
                }
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x0055 A[SYNTHETIC, Splitter:B:29:0x0055] */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x005c A[SYNTHETIC, Splitter:B:34:0x005c] */
        /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
        /* renamed from: lambda$connect$0$androidx-media3-session-MediaSessionService$MediaSessionServiceStub  reason: not valid java name */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ void m1098lambda$connect$0$androidxmedia3sessionMediaSessionService$MediaSessionServiceStub(androidx.media3.session.IMediaController r16, androidx.media3.session.legacy.MediaSessionManager.RemoteUserInfo r17, androidx.media3.session.ConnectionRequest r18, boolean r19) {
            /*
                r15 = this;
                r1 = r15
                r2 = r16
                r0 = r18
                java.util.Set<androidx.media3.session.IMediaController> r3 = r1.pendingControllers
                r3.remove(r2)
                r3 = 0
                r4 = 1
                java.lang.ref.WeakReference<androidx.media3.session.MediaSessionService> r5 = r1.serviceReference     // Catch:{ all -> 0x0059 }
                java.lang.Object r5 = r5.get()     // Catch:{ all -> 0x0059 }
                androidx.media3.session.MediaSessionService r5 = (androidx.media3.session.MediaSessionService) r5     // Catch:{ all -> 0x0059 }
                if (r5 != 0) goto L_0x001a
                r2.onDisconnected(r3)     // Catch:{ RemoteException -> 0x0019 }
            L_0x0019:
                return
            L_0x001a:
                androidx.media3.session.MediaSession$ControllerInfo r14 = new androidx.media3.session.MediaSession$ControllerInfo     // Catch:{ all -> 0x0059 }
                int r8 = r0.libraryVersion     // Catch:{ all -> 0x0059 }
                int r9 = r0.controllerInterfaceVersion     // Catch:{ all -> 0x0059 }
                androidx.media3.session.MediaSessionStub$Controller2Cb r11 = new androidx.media3.session.MediaSessionStub$Controller2Cb     // Catch:{ all -> 0x0059 }
                int r6 = r0.controllerInterfaceVersion     // Catch:{ all -> 0x0059 }
                r11.<init>(r2, r6)     // Catch:{ all -> 0x0059 }
                android.os.Bundle r12 = r0.connectionHints     // Catch:{ all -> 0x0059 }
                int r13 = r0.maxCommandsForMediaItems     // Catch:{ all -> 0x0059 }
                r6 = r14
                r7 = r17
                r10 = r19
                r6.<init>(r7, r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x0059 }
                androidx.media3.session.MediaSession r0 = r5.onGetSession(r14)     // Catch:{ Exception -> 0x004b }
                if (r0 != 0) goto L_0x003d
                r2.onDisconnected(r3)     // Catch:{ RemoteException -> 0x003c }
            L_0x003c:
                return
            L_0x003d:
                r5.addSession(r0)     // Catch:{ Exception -> 0x004b }
                r0.handleControllerConnectionFromService(r2, r14)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
                r4 = r3
                goto L_0x0053
            L_0x0045:
                r0 = move-exception
                r4 = r3
                goto L_0x005a
            L_0x0048:
                r0 = move-exception
                r4 = r3
                goto L_0x004c
            L_0x004b:
                r0 = move-exception
            L_0x004c:
                java.lang.String r5 = "MSessionService"
                java.lang.String r6 = "Failed to add a session to session service"
                androidx.media3.common.util.Log.w(r5, r6, r0)     // Catch:{ all -> 0x0059 }
            L_0x0053:
                if (r4 == 0) goto L_0x0058
                r2.onDisconnected(r3)     // Catch:{ RemoteException -> 0x0058 }
            L_0x0058:
                return
            L_0x0059:
                r0 = move-exception
            L_0x005a:
                if (r4 == 0) goto L_0x005f
                r2.onDisconnected(r3)     // Catch:{ RemoteException -> 0x005f }
            L_0x005f:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.session.MediaSessionService.MediaSessionServiceStub.m1098lambda$connect$0$androidxmedia3sessionMediaSessionService$MediaSessionServiceStub(androidx.media3.session.IMediaController, androidx.media3.session.legacy.MediaSessionManager$RemoteUserInfo, androidx.media3.session.ConnectionRequest, boolean):void");
        }

        public void release() {
            this.serviceReference.clear();
            this.handler.removeCallbacksAndMessages((Object) null);
            for (IMediaController onDisconnected : this.pendingControllers) {
                try {
                    onDisconnected.onDisconnected(0);
                } catch (RemoteException unused) {
                }
            }
        }
    }

    private static final class Api31 {
        private Api31() {
        }

        public static boolean instanceOfForegroundServiceStartNotAllowedException(IllegalStateException illegalStateException) {
            return illegalStateException instanceof ForegroundServiceStartNotAllowedException;
        }
    }
}
