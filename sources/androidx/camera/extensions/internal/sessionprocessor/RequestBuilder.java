package androidx.camera.extensions.internal.sessionprocessor;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.RequestProcessor;
import androidx.camera.extensions.internal.RequestOptionConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RequestBuilder {
    int mCaptureStageId;
    private Map<CaptureRequest.Key<?>, Object> mParameters = new HashMap();
    private List<Integer> mTargetOutputConfigIds = new ArrayList();
    private int mTemplateId = 1;

    RequestBuilder() {
    }

    /* access modifiers changed from: package-private */
    public RequestBuilder addTargetOutputConfigIds(int i) {
        this.mTargetOutputConfigIds.add(Integer.valueOf(i));
        return this;
    }

    /* access modifiers changed from: package-private */
    public RequestBuilder setParameters(CaptureRequest.Key<?> key, Object obj) {
        this.mParameters.put(key, obj);
        return this;
    }

    /* access modifiers changed from: package-private */
    public RequestBuilder setTemplateId(int i) {
        this.mTemplateId = i;
        return this;
    }

    public RequestBuilder setCaptureStageId(int i) {
        this.mCaptureStageId = i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public RequestProcessor.Request build() {
        return new RequestProcessorRequest(this.mTargetOutputConfigIds, this.mParameters, this.mTemplateId, this.mCaptureStageId);
    }

    static class RequestProcessorRequest implements RequestProcessor.Request {
        final int mCaptureStageId;
        final Config mParameterConfig;
        final List<Integer> mTargetOutputConfigIds;
        final int mTemplateId;

        RequestProcessorRequest(List<Integer> list, Map<CaptureRequest.Key<?>, Object> map, int i, int i2) {
            this.mTargetOutputConfigIds = list;
            this.mTemplateId = i;
            this.mCaptureStageId = i2;
            RequestOptionConfig.Builder builder = new RequestOptionConfig.Builder();
            for (CaptureRequest.Key next : map.keySet()) {
                builder.setCaptureRequestOption(next, map.get(next));
            }
            this.mParameterConfig = builder.build();
        }

        public List<Integer> getTargetOutputConfigIds() {
            return this.mTargetOutputConfigIds;
        }

        public Config getParameters() {
            return this.mParameterConfig;
        }

        public int getTemplateId() {
            return this.mTemplateId;
        }

        public int getCaptureStageId() {
            return this.mCaptureStageId;
        }
    }
}
