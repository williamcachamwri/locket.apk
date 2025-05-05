package androidx.media3.exoplayer.drm;

import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import androidx.media3.decoder.CryptoConfig;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import java.util.Map;
import java.util.UUID;

public final class ErrorStateDrmSession implements DrmSession {
    private final DrmSession.DrmSessionException error;

    public void acquire(DrmSessionEventListener.EventDispatcher eventDispatcher) {
    }

    public CryptoConfig getCryptoConfig() {
        return null;
    }

    public byte[] getOfflineLicenseKeySetId() {
        return null;
    }

    public int getState() {
        return 1;
    }

    public boolean playClearSamplesWithoutKeys() {
        return false;
    }

    public Map<String, String> queryKeyStatus() {
        return null;
    }

    public void release(DrmSessionEventListener.EventDispatcher eventDispatcher) {
    }

    public boolean requiresSecureDecoder(String str) {
        return false;
    }

    public ErrorStateDrmSession(DrmSession.DrmSessionException drmSessionException) {
        this.error = (DrmSession.DrmSessionException) Assertions.checkNotNull(drmSessionException);
    }

    public DrmSession.DrmSessionException getError() {
        return this.error;
    }

    public final UUID getSchemeUuid() {
        return C.UUID_NIL;
    }
}
