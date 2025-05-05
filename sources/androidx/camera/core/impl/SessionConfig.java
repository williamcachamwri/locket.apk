package androidx.camera.core.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.params.InputConfiguration;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.AutoValue_SessionConfig_OutputConfig;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.internal.compat.workaround.SurfaceSorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SessionConfig {
    public static final int DEFAULT_SESSION_TYPE = 0;
    private static final List<Integer> SUPPORTED_TEMPLATE_PRIORITY = Arrays.asList(new Integer[]{1, 5, 3});
    private final List<CameraDevice.StateCallback> mDeviceStateCallbacks;
    private final ErrorListener mErrorListener;
    private InputConfiguration mInputConfiguration;
    private final List<OutputConfig> mOutputConfigs;
    /* access modifiers changed from: private */
    public final OutputConfig mPostviewOutputConfig;
    private final CaptureConfig mRepeatingCaptureConfig;
    private final List<CameraCaptureSession.StateCallback> mSessionStateCallbacks;
    private final int mSessionType;
    private final List<CameraCaptureCallback> mSingleCameraCaptureCallbacks;

    public interface ErrorListener {
        void onError(SessionConfig sessionConfig, SessionError sessionError);
    }

    public interface OptionUnpacker {
        void unpack(Size size, UseCaseConfig<?> useCaseConfig, Builder builder);
    }

    public enum SessionError {
        SESSION_ERROR_SURFACE_NEEDS_RESET,
        SESSION_ERROR_UNKNOWN
    }

    public static abstract class OutputConfig {
        public static final int SURFACE_GROUP_ID_NONE = -1;

        public static abstract class Builder {
            public abstract OutputConfig build();

            public abstract Builder setDynamicRange(DynamicRange dynamicRange);

            public abstract Builder setMirrorMode(int i);

            public abstract Builder setPhysicalCameraId(String str);

            public abstract Builder setSharedSurfaces(List<DeferrableSurface> list);

            public abstract Builder setSurface(DeferrableSurface deferrableSurface);

            public abstract Builder setSurfaceGroupId(int i);
        }

        public abstract DynamicRange getDynamicRange();

        public abstract int getMirrorMode();

        public abstract String getPhysicalCameraId();

        public abstract List<DeferrableSurface> getSharedSurfaces();

        public abstract DeferrableSurface getSurface();

        public abstract int getSurfaceGroupId();

        public static Builder builder(DeferrableSurface deferrableSurface) {
            return new AutoValue_SessionConfig_OutputConfig.Builder().setSurface(deferrableSurface).setSharedSurfaces(Collections.emptyList()).setPhysicalCameraId((String) null).setMirrorMode(-1).setSurfaceGroupId(-1).setDynamicRange(DynamicRange.SDR);
        }
    }

    SessionConfig(List<OutputConfig> list, List<CameraDevice.StateCallback> list2, List<CameraCaptureSession.StateCallback> list3, List<CameraCaptureCallback> list4, CaptureConfig captureConfig, ErrorListener errorListener, InputConfiguration inputConfiguration, int i, OutputConfig outputConfig) {
        this.mOutputConfigs = list;
        this.mDeviceStateCallbacks = Collections.unmodifiableList(list2);
        this.mSessionStateCallbacks = Collections.unmodifiableList(list3);
        this.mSingleCameraCaptureCallbacks = Collections.unmodifiableList(list4);
        this.mErrorListener = errorListener;
        this.mRepeatingCaptureConfig = captureConfig;
        this.mInputConfiguration = inputConfiguration;
        this.mSessionType = i;
        this.mPostviewOutputConfig = outputConfig;
    }

    public static SessionConfig defaultEmptySessionConfig() {
        return new SessionConfig(new ArrayList(), new ArrayList(0), new ArrayList(0), new ArrayList(0), new CaptureConfig.Builder().build(), (ErrorListener) null, (InputConfiguration) null, 0, (OutputConfig) null);
    }

    public InputConfiguration getInputConfiguration() {
        return this.mInputConfiguration;
    }

    public List<DeferrableSurface> getSurfaces() {
        ArrayList arrayList = new ArrayList();
        for (OutputConfig next : this.mOutputConfigs) {
            arrayList.add(next.getSurface());
            for (DeferrableSurface add : next.getSharedSurfaces()) {
                arrayList.add(add);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public List<OutputConfig> getOutputConfigs() {
        return this.mOutputConfigs;
    }

    public OutputConfig getPostviewOutputConfig() {
        return this.mPostviewOutputConfig;
    }

    public Config getImplementationOptions() {
        return this.mRepeatingCaptureConfig.getImplementationOptions();
    }

    public int getTemplateType() {
        return this.mRepeatingCaptureConfig.getTemplateType();
    }

    public int getSessionType() {
        return this.mSessionType;
    }

    public Range<Integer> getExpectedFrameRateRange() {
        return this.mRepeatingCaptureConfig.getExpectedFrameRateRange();
    }

    public List<CameraDevice.StateCallback> getDeviceStateCallbacks() {
        return this.mDeviceStateCallbacks;
    }

    public List<CameraCaptureSession.StateCallback> getSessionStateCallbacks() {
        return this.mSessionStateCallbacks;
    }

    public List<CameraCaptureCallback> getRepeatingCameraCaptureCallbacks() {
        return this.mRepeatingCaptureConfig.getCameraCaptureCallbacks();
    }

    public ErrorListener getErrorListener() {
        return this.mErrorListener;
    }

    public List<CameraCaptureCallback> getSingleCameraCaptureCallbacks() {
        return this.mSingleCameraCaptureCallbacks;
    }

    public CaptureConfig getRepeatingCaptureConfig() {
        return this.mRepeatingCaptureConfig;
    }

    public static int getHigherPriorityTemplateType(int i, int i2) {
        List<Integer> list = SUPPORTED_TEMPLATE_PRIORITY;
        return list.indexOf(Integer.valueOf(i)) >= list.indexOf(Integer.valueOf(i2)) ? i : i2;
    }

    public static final class CloseableErrorListener implements ErrorListener {
        private final ErrorListener mErrorListener;
        private final AtomicBoolean mIsClosed = new AtomicBoolean(false);

        public CloseableErrorListener(ErrorListener errorListener) {
            this.mErrorListener = errorListener;
        }

        public void onError(SessionConfig sessionConfig, SessionError sessionError) {
            if (!this.mIsClosed.get()) {
                this.mErrorListener.onError(sessionConfig, sessionError);
            }
        }

        public void close() {
            this.mIsClosed.set(true);
        }
    }

    static class BaseBuilder {
        final CaptureConfig.Builder mCaptureConfigBuilder = new CaptureConfig.Builder();
        final List<CameraDevice.StateCallback> mDeviceStateCallbacks = new ArrayList();
        ErrorListener mErrorListener;
        InputConfiguration mInputConfiguration;
        final Set<OutputConfig> mOutputConfigs = new LinkedHashSet();
        OutputConfig mPostviewOutputConfig;
        final List<CameraCaptureSession.StateCallback> mSessionStateCallbacks = new ArrayList();
        int mSessionType = 0;
        final List<CameraCaptureCallback> mSingleCameraCaptureCallbacks = new ArrayList();

        BaseBuilder() {
        }
    }

    public static class Builder extends BaseBuilder {
        public static Builder createFrom(UseCaseConfig<?> useCaseConfig, Size size) {
            OptionUnpacker sessionOptionUnpacker = useCaseConfig.getSessionOptionUnpacker((OptionUnpacker) null);
            if (sessionOptionUnpacker != null) {
                Builder builder = new Builder();
                sessionOptionUnpacker.unpack(size, useCaseConfig, builder);
                return builder;
            }
            throw new IllegalStateException("Implementation is missing option unpacker for " + useCaseConfig.getTargetName(useCaseConfig.toString()));
        }

        public Builder setInputConfiguration(InputConfiguration inputConfiguration) {
            this.mInputConfiguration = inputConfiguration;
            return this;
        }

        public Builder setTemplateType(int i) {
            this.mCaptureConfigBuilder.setTemplateType(i);
            return this;
        }

        public Builder setSessionType(int i) {
            this.mSessionType = i;
            return this;
        }

        public Builder setExpectedFrameRateRange(Range<Integer> range) {
            this.mCaptureConfigBuilder.setExpectedFrameRateRange(range);
            return this;
        }

        public Builder setPreviewStabilization(int i) {
            if (i != 0) {
                this.mCaptureConfigBuilder.setPreviewStabilization(i);
            }
            return this;
        }

        public Builder setVideoStabilization(int i) {
            if (i != 0) {
                this.mCaptureConfigBuilder.setVideoStabilization(i);
            }
            return this;
        }

        public Builder addTag(String str, Object obj) {
            this.mCaptureConfigBuilder.addTag(str, obj);
            return this;
        }

        public Builder addDeviceStateCallback(CameraDevice.StateCallback stateCallback) {
            if (this.mDeviceStateCallbacks.contains(stateCallback)) {
                return this;
            }
            this.mDeviceStateCallbacks.add(stateCallback);
            return this;
        }

        public Builder addAllDeviceStateCallbacks(Collection<CameraDevice.StateCallback> collection) {
            for (CameraDevice.StateCallback addDeviceStateCallback : collection) {
                addDeviceStateCallback(addDeviceStateCallback);
            }
            return this;
        }

        public Builder addSessionStateCallback(CameraCaptureSession.StateCallback stateCallback) {
            if (this.mSessionStateCallbacks.contains(stateCallback)) {
                return this;
            }
            this.mSessionStateCallbacks.add(stateCallback);
            return this;
        }

        public Builder addAllSessionStateCallbacks(List<CameraCaptureSession.StateCallback> list) {
            for (CameraCaptureSession.StateCallback addSessionStateCallback : list) {
                addSessionStateCallback(addSessionStateCallback);
            }
            return this;
        }

        public Builder addRepeatingCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            this.mCaptureConfigBuilder.addCameraCaptureCallback(cameraCaptureCallback);
            return this;
        }

        public Builder addAllRepeatingCameraCaptureCallbacks(Collection<CameraCaptureCallback> collection) {
            this.mCaptureConfigBuilder.addAllCameraCaptureCallbacks(collection);
            return this;
        }

        public Builder addCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            this.mCaptureConfigBuilder.addCameraCaptureCallback(cameraCaptureCallback);
            if (!this.mSingleCameraCaptureCallbacks.contains(cameraCaptureCallback)) {
                this.mSingleCameraCaptureCallbacks.add(cameraCaptureCallback);
            }
            return this;
        }

        public Builder addAllCameraCaptureCallbacks(Collection<CameraCaptureCallback> collection) {
            for (CameraCaptureCallback next : collection) {
                this.mCaptureConfigBuilder.addCameraCaptureCallback(next);
                if (!this.mSingleCameraCaptureCallbacks.contains(next)) {
                    this.mSingleCameraCaptureCallbacks.add(next);
                }
            }
            return this;
        }

        public boolean removeCameraCaptureCallback(CameraCaptureCallback cameraCaptureCallback) {
            return this.mCaptureConfigBuilder.removeCameraCaptureCallback(cameraCaptureCallback) || this.mSingleCameraCaptureCallbacks.remove(cameraCaptureCallback);
        }

        public List<CameraCaptureCallback> getSingleCameraCaptureCallbacks() {
            return Collections.unmodifiableList(this.mSingleCameraCaptureCallbacks);
        }

        public Builder setErrorListener(ErrorListener errorListener) {
            this.mErrorListener = errorListener;
            return this;
        }

        public Builder addSurface(DeferrableSurface deferrableSurface) {
            return addSurface(deferrableSurface, DynamicRange.SDR, (String) null, -1);
        }

        public Builder addSurface(DeferrableSurface deferrableSurface, DynamicRange dynamicRange, String str, int i) {
            this.mOutputConfigs.add(OutputConfig.builder(deferrableSurface).setPhysicalCameraId(str).setDynamicRange(dynamicRange).setMirrorMode(i).build());
            this.mCaptureConfigBuilder.addSurface(deferrableSurface);
            return this;
        }

        public Builder addOutputConfig(OutputConfig outputConfig) {
            this.mOutputConfigs.add(outputConfig);
            this.mCaptureConfigBuilder.addSurface(outputConfig.getSurface());
            for (DeferrableSurface addSurface : outputConfig.getSharedSurfaces()) {
                this.mCaptureConfigBuilder.addSurface(addSurface);
            }
            return this;
        }

        public Builder addNonRepeatingSurface(DeferrableSurface deferrableSurface) {
            return addNonRepeatingSurface(deferrableSurface, DynamicRange.SDR);
        }

        public Builder addNonRepeatingSurface(DeferrableSurface deferrableSurface, DynamicRange dynamicRange) {
            this.mOutputConfigs.add(OutputConfig.builder(deferrableSurface).setDynamicRange(dynamicRange).build());
            return this;
        }

        public Builder setPostviewSurface(DeferrableSurface deferrableSurface) {
            this.mPostviewOutputConfig = OutputConfig.builder(deferrableSurface).build();
            return this;
        }

        public Builder removeSurface(DeferrableSurface deferrableSurface) {
            OutputConfig outputConfig;
            Iterator it = this.mOutputConfigs.iterator();
            while (true) {
                if (!it.hasNext()) {
                    outputConfig = null;
                    break;
                }
                outputConfig = (OutputConfig) it.next();
                if (outputConfig.getSurface().equals(deferrableSurface)) {
                    break;
                }
            }
            if (outputConfig != null) {
                this.mOutputConfigs.remove(outputConfig);
            }
            this.mCaptureConfigBuilder.removeSurface(deferrableSurface);
            return this;
        }

        public Builder clearSurfaces() {
            this.mOutputConfigs.clear();
            this.mCaptureConfigBuilder.clearSurfaces();
            return this;
        }

        public Builder setImplementationOptions(Config config) {
            this.mCaptureConfigBuilder.setImplementationOptions(config);
            return this;
        }

        public Builder addImplementationOptions(Config config) {
            this.mCaptureConfigBuilder.addImplementationOptions(config);
            return this;
        }

        public SessionConfig build() {
            return new SessionConfig(new ArrayList(this.mOutputConfigs), new ArrayList(this.mDeviceStateCallbacks), new ArrayList(this.mSessionStateCallbacks), new ArrayList(this.mSingleCameraCaptureCallbacks), this.mCaptureConfigBuilder.build(), this.mErrorListener, this.mInputConfiguration, this.mSessionType, this.mPostviewOutputConfig);
        }
    }

    public static final class ValidatingBuilder extends BaseBuilder {
        private static final String TAG = "ValidatingBuilder";
        private List<ErrorListener> mErrorListeners = new ArrayList();
        private final SurfaceSorter mSurfaceSorter = new SurfaceSorter();
        private boolean mTemplateSet = false;
        private boolean mValid = true;

        public <T> void addImplementationOption(Config.Option<T> option, T t) {
            this.mCaptureConfigBuilder.addImplementationOption(option, t);
        }

        public void add(SessionConfig sessionConfig) {
            CaptureConfig repeatingCaptureConfig = sessionConfig.getRepeatingCaptureConfig();
            if (repeatingCaptureConfig.getTemplateType() != -1) {
                this.mTemplateSet = true;
                this.mCaptureConfigBuilder.setTemplateType(SessionConfig.getHigherPriorityTemplateType(repeatingCaptureConfig.getTemplateType(), this.mCaptureConfigBuilder.getTemplateType()));
            }
            setOrVerifyExpectFrameRateRange(repeatingCaptureConfig.getExpectedFrameRateRange());
            setPreviewStabilizationMode(repeatingCaptureConfig.getPreviewStabilizationMode());
            setVideoStabilizationMode(repeatingCaptureConfig.getVideoStabilizationMode());
            this.mCaptureConfigBuilder.addAllTags(sessionConfig.getRepeatingCaptureConfig().getTagBundle());
            this.mDeviceStateCallbacks.addAll(sessionConfig.getDeviceStateCallbacks());
            this.mSessionStateCallbacks.addAll(sessionConfig.getSessionStateCallbacks());
            this.mCaptureConfigBuilder.addAllCameraCaptureCallbacks(sessionConfig.getRepeatingCameraCaptureCallbacks());
            this.mSingleCameraCaptureCallbacks.addAll(sessionConfig.getSingleCameraCaptureCallbacks());
            if (sessionConfig.getErrorListener() != null) {
                this.mErrorListeners.add(sessionConfig.getErrorListener());
            }
            if (sessionConfig.getInputConfiguration() != null) {
                this.mInputConfiguration = sessionConfig.getInputConfiguration();
            }
            this.mOutputConfigs.addAll(sessionConfig.getOutputConfigs());
            this.mCaptureConfigBuilder.getSurfaces().addAll(repeatingCaptureConfig.getSurfaces());
            if (!getSurfaces().containsAll(this.mCaptureConfigBuilder.getSurfaces())) {
                Logger.d(TAG, "Invalid configuration due to capture request surfaces are not a subset of surfaces");
                this.mValid = false;
            }
            if (sessionConfig.getSessionType() != this.mSessionType && sessionConfig.getSessionType() != 0 && this.mSessionType != 0) {
                Logger.d(TAG, "Invalid configuration due to that two non-default session types are set");
                this.mValid = false;
            } else if (sessionConfig.getSessionType() != 0) {
                this.mSessionType = sessionConfig.getSessionType();
            }
            if (sessionConfig.mPostviewOutputConfig != null) {
                if (this.mPostviewOutputConfig == sessionConfig.mPostviewOutputConfig || this.mPostviewOutputConfig == null) {
                    this.mPostviewOutputConfig = sessionConfig.mPostviewOutputConfig;
                } else {
                    Logger.d(TAG, "Invalid configuration due to that two different postview output configs are set");
                    this.mValid = false;
                }
            }
            this.mCaptureConfigBuilder.addImplementationOptions(repeatingCaptureConfig.getImplementationOptions());
        }

        private void setOrVerifyExpectFrameRateRange(Range<Integer> range) {
            if (!range.equals(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED)) {
                if (this.mCaptureConfigBuilder.getExpectedFrameRateRange().equals(StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED)) {
                    this.mCaptureConfigBuilder.setExpectedFrameRateRange(range);
                } else if (!this.mCaptureConfigBuilder.getExpectedFrameRateRange().equals(range)) {
                    this.mValid = false;
                    Logger.d(TAG, "Different ExpectedFrameRateRange values");
                }
            }
        }

        private void setPreviewStabilizationMode(int i) {
            if (i != 0) {
                this.mCaptureConfigBuilder.setPreviewStabilization(i);
            }
        }

        private void setVideoStabilizationMode(int i) {
            if (i != 0) {
                this.mCaptureConfigBuilder.setVideoStabilization(i);
            }
        }

        private List<DeferrableSurface> getSurfaces() {
            ArrayList arrayList = new ArrayList();
            for (OutputConfig outputConfig : this.mOutputConfigs) {
                arrayList.add(outputConfig.getSurface());
                for (DeferrableSurface add : outputConfig.getSharedSurfaces()) {
                    arrayList.add(add);
                }
            }
            return arrayList;
        }

        public void clearSurfaces() {
            this.mOutputConfigs.clear();
            this.mCaptureConfigBuilder.clearSurfaces();
        }

        public boolean isValid() {
            return this.mTemplateSet && this.mValid;
        }

        public SessionConfig build() {
            if (this.mValid) {
                ArrayList arrayList = new ArrayList(this.mOutputConfigs);
                this.mSurfaceSorter.sort(arrayList);
                return new SessionConfig(arrayList, new ArrayList(this.mDeviceStateCallbacks), new ArrayList(this.mSessionStateCallbacks), new ArrayList(this.mSingleCameraCaptureCallbacks), this.mCaptureConfigBuilder.build(), !this.mErrorListeners.isEmpty() ? new SessionConfig$ValidatingBuilder$$ExternalSyntheticLambda0(this) : null, this.mInputConfiguration, this.mSessionType, this.mPostviewOutputConfig);
            }
            throw new IllegalArgumentException("Unsupported session configuration combination");
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$build$0$androidx-camera-core-impl-SessionConfig$ValidatingBuilder  reason: not valid java name */
        public /* synthetic */ void m186lambda$build$0$androidxcameracoreimplSessionConfig$ValidatingBuilder(SessionConfig sessionConfig, SessionError sessionError) {
            for (ErrorListener onError : this.mErrorListeners) {
                onError.onError(sessionConfig, sessionError);
            }
        }
    }
}
