package androidx.media3.exoplayer.drm;

import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Pair;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.util.concurrent.SettableFuture;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public final class OfflineLicenseHelper {
    private static final Format FORMAT_WITH_EMPTY_DRM_INIT_DATA = new Format.Builder().setDrmInitData(new DrmInitData(new DrmInitData.SchemeData[0])).build();
    /* access modifiers changed from: private */
    public final ConditionVariable drmListenerConditionVariable = new ConditionVariable();
    private final DefaultDrmSessionManager drmSessionManager;
    private final DrmSessionEventListener.EventDispatcher eventDispatcher;
    private final Handler handler;
    private final HandlerThread handlerThread;

    public static OfflineLicenseHelper newWidevineInstance(String str, DataSource.Factory factory, DrmSessionEventListener.EventDispatcher eventDispatcher2) {
        return newWidevineInstance(str, false, factory, eventDispatcher2);
    }

    public static OfflineLicenseHelper newWidevineInstance(String str, boolean z, DataSource.Factory factory, DrmSessionEventListener.EventDispatcher eventDispatcher2) {
        return newWidevineInstance(str, z, factory, (Map<String, String>) null, eventDispatcher2);
    }

    public static OfflineLicenseHelper newWidevineInstance(String str, boolean z, DataSource.Factory factory, Map<String, String> map, DrmSessionEventListener.EventDispatcher eventDispatcher2) {
        return new OfflineLicenseHelper(new DefaultDrmSessionManager.Builder().setKeyRequestParameters(map).build(new HttpMediaDrmCallback(str, z, factory)), eventDispatcher2);
    }

    public OfflineLicenseHelper(DefaultDrmSessionManager defaultDrmSessionManager, DrmSessionEventListener.EventDispatcher eventDispatcher2) {
        this.drmSessionManager = defaultDrmSessionManager;
        this.eventDispatcher = eventDispatcher2;
        HandlerThread handlerThread2 = new HandlerThread("ExoPlayer:OfflineLicenseHelper");
        this.handlerThread = handlerThread2;
        handlerThread2.start();
        this.handler = new Handler(handlerThread2.getLooper());
        eventDispatcher2.addEventListener(new Handler(handlerThread2.getLooper()), new DrmSessionEventListener() {
            public void onDrmKeysLoaded(int i, MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            public void onDrmSessionManagerError(int i, MediaSource.MediaPeriodId mediaPeriodId, Exception exc) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            public void onDrmKeysRestored(int i, MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }

            public void onDrmKeysRemoved(int i, MediaSource.MediaPeriodId mediaPeriodId) {
                OfflineLicenseHelper.this.drmListenerConditionVariable.open();
            }
        });
    }

    public synchronized byte[] downloadLicense(Format format) throws DrmSession.DrmSessionException {
        Assertions.checkArgument(format.drmInitData != null);
        return acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(2, (byte[]) null, format);
    }

    public synchronized byte[] renewLicense(byte[] bArr) throws DrmSession.DrmSessionException {
        Assertions.checkNotNull(bArr);
        return acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(2, bArr, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
    }

    public synchronized void releaseLicense(byte[] bArr) throws DrmSession.DrmSessionException {
        Assertions.checkNotNull(bArr);
        acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(3, bArr, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
    }

    public synchronized Pair<Long, Long> getLicenseDurationRemainingSec(byte[] bArr) throws DrmSession.DrmSessionException {
        Pair<Long, Long> pair;
        Assertions.checkNotNull(bArr);
        try {
            DrmSession acquireFirstSessionOnHandlerThread = acquireFirstSessionOnHandlerThread(1, bArr, FORMAT_WITH_EMPTY_DRM_INIT_DATA);
            SettableFuture create = SettableFuture.create();
            this.handler.post(new OfflineLicenseHelper$$ExternalSyntheticLambda2(this, create, acquireFirstSessionOnHandlerThread));
            pair = (Pair) create.get();
            releaseManagerOnHandlerThread();
        } catch (DrmSession.DrmSessionException e) {
            if (e.getCause() instanceof KeysExpiredException) {
                return Pair.create(0L, 0L);
            }
            throw e;
        } catch (InterruptedException | ExecutionException e2) {
            throw new IllegalStateException(e2);
        } catch (Throwable th) {
            releaseManagerOnHandlerThread();
            throw th;
        }
        return pair;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getLicenseDurationRemainingSec$0$androidx-media3-exoplayer-drm-OfflineLicenseHelper  reason: not valid java name */
    public /* synthetic */ void m518lambda$getLicenseDurationRemainingSec$0$androidxmedia3exoplayerdrmOfflineLicenseHelper(SettableFuture settableFuture, DrmSession drmSession) {
        try {
            settableFuture.set((Pair) Assertions.checkNotNull(WidevineUtil.getLicenseDurationRemainingSec(drmSession)));
        } catch (Throwable th) {
            drmSession.release(this.eventDispatcher);
            throw th;
        }
        drmSession.release(this.eventDispatcher);
    }

    public void release() {
        this.handlerThread.quit();
    }

    private byte[] acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread(int i, byte[] bArr, Format format) throws DrmSession.DrmSessionException {
        DrmSession acquireFirstSessionOnHandlerThread = acquireFirstSessionOnHandlerThread(i, bArr, format);
        SettableFuture create = SettableFuture.create();
        this.handler.post(new OfflineLicenseHelper$$ExternalSyntheticLambda4(this, create, acquireFirstSessionOnHandlerThread));
        try {
            byte[] bArr2 = (byte[]) Assertions.checkNotNull((byte[]) create.get());
            releaseManagerOnHandlerThread();
            return bArr2;
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException(e);
        } catch (Throwable th) {
            releaseManagerOnHandlerThread();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread$1$androidx-media3-exoplayer-drm-OfflineLicenseHelper  reason: not valid java name */
    public /* synthetic */ void m517lambda$acquireSessionAndGetOfflineLicenseKeySetIdOnHandlerThread$1$androidxmedia3exoplayerdrmOfflineLicenseHelper(SettableFuture settableFuture, DrmSession drmSession) {
        try {
            settableFuture.set(drmSession.getOfflineLicenseKeySetId());
        } catch (Throwable th) {
            drmSession.release(this.eventDispatcher);
            throw th;
        }
        drmSession.release(this.eventDispatcher);
    }

    private DrmSession acquireFirstSessionOnHandlerThread(int i, byte[] bArr, Format format) throws DrmSession.DrmSessionException {
        Assertions.checkNotNull(format.drmInitData);
        SettableFuture create = SettableFuture.create();
        this.drmListenerConditionVariable.close();
        this.handler.post(new OfflineLicenseHelper$$ExternalSyntheticLambda0(this, i, bArr, create, format));
        try {
            DrmSession drmSession = (DrmSession) create.get();
            this.drmListenerConditionVariable.block();
            SettableFuture create2 = SettableFuture.create();
            this.handler.post(new OfflineLicenseHelper$$ExternalSyntheticLambda1(this, drmSession, create2));
            try {
                if (create2.get() == null) {
                    return drmSession;
                }
                throw ((DrmSession.DrmSessionException) create2.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new IllegalStateException(e);
            }
        } catch (InterruptedException | ExecutionException e2) {
            throw new IllegalStateException(e2);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$acquireFirstSessionOnHandlerThread$2$androidx-media3-exoplayer-drm-OfflineLicenseHelper  reason: not valid java name */
    public /* synthetic */ void m515lambda$acquireFirstSessionOnHandlerThread$2$androidxmedia3exoplayerdrmOfflineLicenseHelper(int i, byte[] bArr, SettableFuture settableFuture, Format format) {
        try {
            this.drmSessionManager.setPlayer((Looper) Assertions.checkNotNull(Looper.myLooper()), PlayerId.UNSET);
            this.drmSessionManager.prepare();
            this.drmSessionManager.setMode(i, bArr);
            settableFuture.set((DrmSession) Assertions.checkNotNull(this.drmSessionManager.acquireSession(this.eventDispatcher, format)));
        } catch (Throwable th) {
            settableFuture.setException(th);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$acquireFirstSessionOnHandlerThread$3$androidx-media3-exoplayer-drm-OfflineLicenseHelper  reason: not valid java name */
    public /* synthetic */ void m516lambda$acquireFirstSessionOnHandlerThread$3$androidxmedia3exoplayerdrmOfflineLicenseHelper(DrmSession drmSession, SettableFuture settableFuture) {
        try {
            DrmSession.DrmSessionException error = drmSession.getError();
            if (drmSession.getState() == 1) {
                drmSession.release(this.eventDispatcher);
                this.drmSessionManager.release();
            }
            settableFuture.set(error);
        } catch (Throwable th) {
            settableFuture.setException(th);
            drmSession.release(this.eventDispatcher);
            this.drmSessionManager.release();
        }
    }

    private void releaseManagerOnHandlerThread() {
        SettableFuture create = SettableFuture.create();
        this.handler.post(new OfflineLicenseHelper$$ExternalSyntheticLambda3(this, create));
        try {
            create.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$releaseManagerOnHandlerThread$4$androidx-media3-exoplayer-drm-OfflineLicenseHelper  reason: not valid java name */
    public /* synthetic */ void m519lambda$releaseManagerOnHandlerThread$4$androidxmedia3exoplayerdrmOfflineLicenseHelper(SettableFuture settableFuture) {
        try {
            this.drmSessionManager.release();
            settableFuture.set(null);
        } catch (Throwable th) {
            settableFuture.setException(th);
        }
    }
}
