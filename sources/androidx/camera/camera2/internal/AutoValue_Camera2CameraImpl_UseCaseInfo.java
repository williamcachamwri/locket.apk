package androidx.camera.camera2.internal;

import android.util.Size;
import androidx.camera.camera2.internal.Camera2CameraImpl;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import java.util.List;

final class AutoValue_Camera2CameraImpl_UseCaseInfo extends Camera2CameraImpl.UseCaseInfo {
    private final List<UseCaseConfigFactory.CaptureType> captureTypes;
    private final SessionConfig sessionConfig;
    private final StreamSpec streamSpec;
    private final Size surfaceResolution;
    private final UseCaseConfig<?> useCaseConfig;
    private final String useCaseId;
    private final Class<?> useCaseType;

    AutoValue_Camera2CameraImpl_UseCaseInfo(String str, Class<?> cls, SessionConfig sessionConfig2, UseCaseConfig<?> useCaseConfig2, Size size, StreamSpec streamSpec2, List<UseCaseConfigFactory.CaptureType> list) {
        if (str != null) {
            this.useCaseId = str;
            if (cls != null) {
                this.useCaseType = cls;
                if (sessionConfig2 != null) {
                    this.sessionConfig = sessionConfig2;
                    if (useCaseConfig2 != null) {
                        this.useCaseConfig = useCaseConfig2;
                        this.surfaceResolution = size;
                        this.streamSpec = streamSpec2;
                        this.captureTypes = list;
                        return;
                    }
                    throw new NullPointerException("Null useCaseConfig");
                }
                throw new NullPointerException("Null sessionConfig");
            }
            throw new NullPointerException("Null useCaseType");
        }
        throw new NullPointerException("Null useCaseId");
    }

    /* access modifiers changed from: package-private */
    public String getUseCaseId() {
        return this.useCaseId;
    }

    /* access modifiers changed from: package-private */
    public Class<?> getUseCaseType() {
        return this.useCaseType;
    }

    /* access modifiers changed from: package-private */
    public SessionConfig getSessionConfig() {
        return this.sessionConfig;
    }

    /* access modifiers changed from: package-private */
    public UseCaseConfig<?> getUseCaseConfig() {
        return this.useCaseConfig;
    }

    /* access modifiers changed from: package-private */
    public Size getSurfaceResolution() {
        return this.surfaceResolution;
    }

    /* access modifiers changed from: package-private */
    public StreamSpec getStreamSpec() {
        return this.streamSpec;
    }

    /* access modifiers changed from: package-private */
    public List<UseCaseConfigFactory.CaptureType> getCaptureTypes() {
        return this.captureTypes;
    }

    public String toString() {
        return "UseCaseInfo{useCaseId=" + this.useCaseId + ", useCaseType=" + this.useCaseType + ", sessionConfig=" + this.sessionConfig + ", useCaseConfig=" + this.useCaseConfig + ", surfaceResolution=" + this.surfaceResolution + ", streamSpec=" + this.streamSpec + ", captureTypes=" + this.captureTypes + "}";
    }

    public boolean equals(Object obj) {
        Size size;
        StreamSpec streamSpec2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Camera2CameraImpl.UseCaseInfo)) {
            return false;
        }
        Camera2CameraImpl.UseCaseInfo useCaseInfo = (Camera2CameraImpl.UseCaseInfo) obj;
        if (this.useCaseId.equals(useCaseInfo.getUseCaseId()) && this.useCaseType.equals(useCaseInfo.getUseCaseType()) && this.sessionConfig.equals(useCaseInfo.getSessionConfig()) && this.useCaseConfig.equals(useCaseInfo.getUseCaseConfig()) && ((size = this.surfaceResolution) != null ? size.equals(useCaseInfo.getSurfaceResolution()) : useCaseInfo.getSurfaceResolution() == null) && ((streamSpec2 = this.streamSpec) != null ? streamSpec2.equals(useCaseInfo.getStreamSpec()) : useCaseInfo.getStreamSpec() == null)) {
            List<UseCaseConfigFactory.CaptureType> list = this.captureTypes;
            if (list == null) {
                if (useCaseInfo.getCaptureTypes() == null) {
                    return true;
                }
            } else if (list.equals(useCaseInfo.getCaptureTypes())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((this.useCaseId.hashCode() ^ 1000003) * 1000003) ^ this.useCaseType.hashCode()) * 1000003) ^ this.sessionConfig.hashCode()) * 1000003) ^ this.useCaseConfig.hashCode()) * 1000003;
        Size size = this.surfaceResolution;
        int i = 0;
        int hashCode2 = (hashCode ^ (size == null ? 0 : size.hashCode())) * 1000003;
        StreamSpec streamSpec2 = this.streamSpec;
        int hashCode3 = (hashCode2 ^ (streamSpec2 == null ? 0 : streamSpec2.hashCode())) * 1000003;
        List<UseCaseConfigFactory.CaptureType> list = this.captureTypes;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode3 ^ i;
    }
}
