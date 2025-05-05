package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.CaptureRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Camera2SessionConfigBuilder {
    private List<Camera2OutputConfig> mCamera2OutputConfigs = new ArrayList();
    private Map<CaptureRequest.Key<?>, Object> mSessionParameters = new HashMap();
    private int mSessionTemplateId = 1;
    private int mSessionType = 0;

    Camera2SessionConfigBuilder() {
    }

    /* access modifiers changed from: package-private */
    public Camera2SessionConfigBuilder addOutputConfig(Camera2OutputConfig camera2OutputConfig) {
        this.mCamera2OutputConfigs.add(camera2OutputConfig);
        return this;
    }

    /* access modifiers changed from: package-private */
    public Camera2SessionConfigBuilder addSessionParameter(CaptureRequest.Key key, Object obj) {
        this.mSessionParameters.put(key, obj);
        return this;
    }

    /* access modifiers changed from: package-private */
    public Camera2SessionConfigBuilder setSessionTemplateId(int i) {
        this.mSessionTemplateId = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public Camera2SessionConfigBuilder setSessionType(int i) {
        this.mSessionType = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int getSessionTemplateId() {
        return this.mSessionTemplateId;
    }

    /* access modifiers changed from: package-private */
    public Map<CaptureRequest.Key<?>, Object> getSessionParameters() {
        return this.mSessionParameters;
    }

    /* access modifiers changed from: package-private */
    public List<Camera2OutputConfig> getCamera2OutputConfigs() {
        return this.mCamera2OutputConfigs;
    }

    /* access modifiers changed from: package-private */
    public Camera2SessionConfig build() {
        return new SessionConfigImpl(this.mSessionTemplateId, this.mSessionType, this.mSessionParameters, this.mCamera2OutputConfigs);
    }

    private static class SessionConfigImpl implements Camera2SessionConfig {
        private final List<Camera2OutputConfig> mCamera2OutputConfigs;
        private final Map<CaptureRequest.Key<?>, Object> mSessionParameters;
        private final int mSessionTemplateId;
        private final int mSessionType;

        SessionConfigImpl(int i, int i2, Map<CaptureRequest.Key<?>, Object> map, List<Camera2OutputConfig> list) {
            this.mSessionTemplateId = i;
            this.mSessionType = i2;
            this.mSessionParameters = map;
            this.mCamera2OutputConfigs = list;
        }

        public List<Camera2OutputConfig> getOutputConfigs() {
            return this.mCamera2OutputConfigs;
        }

        public Map<CaptureRequest.Key<?>, Object> getSessionParameters() {
            return this.mSessionParameters;
        }

        public int getSessionTemplateId() {
            return this.mSessionTemplateId;
        }

        public int getSessionType() {
            return this.mSessionType;
        }
    }
}
