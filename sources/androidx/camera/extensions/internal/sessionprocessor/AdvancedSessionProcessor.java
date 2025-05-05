package androidx.camera.extensions.internal.sessionprocessor;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.media.Image;
import android.os.Build;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureFailure;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.OutputSurface;
import androidx.camera.core.impl.OutputSurfaceConfiguration;
import androidx.camera.core.impl.RequestProcessor;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.extensions.impl.advanced.Camera2OutputConfigImpl;
import androidx.camera.extensions.impl.advanced.Camera2SessionConfigImpl;
import androidx.camera.extensions.impl.advanced.ImageProcessorImpl;
import androidx.camera.extensions.impl.advanced.ImageReferenceImpl;
import androidx.camera.extensions.impl.advanced.OutputSurfaceConfigurationImpl;
import androidx.camera.extensions.impl.advanced.OutputSurfaceImpl;
import androidx.camera.extensions.impl.advanced.RequestProcessorImpl;
import androidx.camera.extensions.impl.advanced.SessionProcessorImpl;
import androidx.camera.extensions.internal.ClientVersion;
import androidx.camera.extensions.internal.ExtensionVersion;
import androidx.camera.extensions.internal.RequestOptionConfig;
import androidx.camera.extensions.internal.VendorExtender;
import androidx.camera.extensions.internal.Version;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class AdvancedSessionProcessor extends SessionProcessorBase {
    private static final String TAG = "AdvancedSessionProcessor";
    private final Context mContext;
    private final MutableLiveData<Integer> mCurrentExtensionTypeLiveData;
    private final ExtensionMetadataMonitor mExtensionMetadataMonitor;
    private final MutableLiveData<Integer> mExtensionStrengthLiveData;
    private final SessionProcessorImpl mImpl;
    private boolean mIsPostviewConfigured;
    private final int mMode;
    private SessionProcessorImplCaptureCallbackAdapter mRepeatingCaptureCallbackAdapter;
    private final VendorExtender mVendorExtender;
    private final boolean mWillReceiveOnCaptureCompleted;
    private HashMap<CaptureRequest.Key<?>, Object> mWorkingCaptureConfigMap;

    public /* bridge */ /* synthetic */ Set getSupportedCameraOperations() {
        return super.getSupportedCameraOperations();
    }

    public AdvancedSessionProcessor(SessionProcessorImpl sessionProcessorImpl, List<CaptureRequest.Key> list, VendorExtender vendorExtender, Context context) {
        this(sessionProcessorImpl, list, vendorExtender, context, 0);
    }

    public AdvancedSessionProcessor(SessionProcessorImpl sessionProcessorImpl, List<CaptureRequest.Key> list, VendorExtender vendorExtender, Context context, int i) {
        super(list);
        this.mIsPostviewConfigured = false;
        this.mWorkingCaptureConfigMap = new HashMap<>();
        this.mRepeatingCaptureCallbackAdapter = null;
        this.mImpl = sessionProcessorImpl;
        this.mVendorExtender = vendorExtender;
        this.mContext = context;
        this.mWillReceiveOnCaptureCompleted = vendorExtender.willReceiveOnCaptureCompleted();
        this.mMode = i;
        MutableLiveData<Integer> mutableLiveData = isCurrentExtensionModeAvailable() ? new MutableLiveData<>(Integer.valueOf(i)) : null;
        this.mCurrentExtensionTypeLiveData = mutableLiveData;
        MutableLiveData<Integer> mutableLiveData2 = isExtensionStrengthAvailable() ? new MutableLiveData<>(100) : null;
        this.mExtensionStrengthLiveData = mutableLiveData2;
        if (mutableLiveData == null && mutableLiveData2 == null) {
            this.mExtensionMetadataMonitor = null;
        } else {
            this.mExtensionMetadataMonitor = new ExtensionMetadataMonitor(mutableLiveData, mutableLiveData2);
        }
    }

    /* access modifiers changed from: protected */
    public Camera2SessionConfig initSessionInternal(String str, Map<String, CameraCharacteristics> map, OutputSurfaceConfiguration outputSurfaceConfiguration) {
        Camera2SessionConfigImpl camera2SessionConfigImpl;
        OutputSurfaceImplAdapter outputSurfaceImplAdapter = null;
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            camera2SessionConfigImpl = null;
        } else {
            camera2SessionConfigImpl = this.mImpl.initSession(str, map, this.mContext, new OutputSurfaceConfigurationImplAdapter(outputSurfaceConfiguration));
        }
        if (camera2SessionConfigImpl == null) {
            SessionProcessorImpl sessionProcessorImpl = this.mImpl;
            Context context = this.mContext;
            OutputSurfaceImplAdapter outputSurfaceImplAdapter2 = new OutputSurfaceImplAdapter(outputSurfaceConfiguration.getPreviewOutputSurface());
            OutputSurfaceImplAdapter outputSurfaceImplAdapter3 = new OutputSurfaceImplAdapter(outputSurfaceConfiguration.getImageCaptureOutputSurface());
            if (outputSurfaceConfiguration.getImageAnalysisOutputSurface() != null) {
                outputSurfaceImplAdapter = new OutputSurfaceImplAdapter(outputSurfaceConfiguration.getImageAnalysisOutputSurface());
            }
            camera2SessionConfigImpl = sessionProcessorImpl.initSession(str, map, context, outputSurfaceImplAdapter2, outputSurfaceImplAdapter3, outputSurfaceImplAdapter);
        }
        this.mIsPostviewConfigured = outputSurfaceConfiguration.getPostviewOutputSurface() != null;
        MutableLiveData<Integer> mutableLiveData = this.mCurrentExtensionTypeLiveData;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(Integer.valueOf(this.mMode));
        }
        MutableLiveData<Integer> mutableLiveData2 = this.mExtensionStrengthLiveData;
        if (mutableLiveData2 != null) {
            mutableLiveData2.postValue(100);
        }
        return convertToCamera2SessionConfig(camera2SessionConfigImpl);
    }

    private Camera2SessionConfig convertToCamera2SessionConfig(Camera2SessionConfigImpl camera2SessionConfigImpl) {
        Camera2SessionConfigBuilder camera2SessionConfigBuilder = new Camera2SessionConfigBuilder();
        for (Camera2OutputConfigImpl fromImpl : camera2SessionConfigImpl.getOutputConfigs()) {
            camera2SessionConfigBuilder.addOutputConfig(Camera2OutputConfigConverter.fromImpl(fromImpl));
        }
        for (CaptureRequest.Key key : camera2SessionConfigImpl.getSessionParameters().keySet()) {
            camera2SessionConfigBuilder.addSessionParameter(key, camera2SessionConfigImpl.getSessionParameters().get(key));
        }
        camera2SessionConfigBuilder.setSessionTemplateId(camera2SessionConfigImpl.getSessionTemplateId());
        if (ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) && ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            try {
                int sessionType = camera2SessionConfigImpl.getSessionType();
                if (sessionType == -1) {
                    sessionType = 0;
                }
                camera2SessionConfigBuilder.setSessionType(sessionType);
            } catch (NoSuchMethodError unused) {
                camera2SessionConfigBuilder.setSessionType(0);
            }
        }
        return camera2SessionConfigBuilder.build();
    }

    /* access modifiers changed from: protected */
    public void deInitSessionInternal() {
        synchronized (this.mLock) {
            this.mWorkingCaptureConfigMap = new HashMap<>();
            this.mRepeatingCaptureCallbackAdapter = null;
        }
        this.mImpl.deInitSession();
    }

    public boolean isCurrentExtensionModeAvailable() {
        return this.mVendorExtender.isCurrentExtensionModeAvailable();
    }

    public LiveData<Integer> getCurrentExtensionMode() {
        return this.mCurrentExtensionTypeLiveData;
    }

    public boolean isExtensionStrengthAvailable() {
        return this.mVendorExtender.isExtensionStrengthAvailable();
    }

    public void setExtensionStrength(int i) {
        SessionProcessorImplCaptureCallbackAdapter sessionProcessorImplCaptureCallbackAdapter;
        HashMap hashMap;
        if (isExtensionStrengthAvailable() && Build.VERSION.SDK_INT >= 34) {
            synchronized (this.mLock) {
                this.mExtensionStrength = i;
                this.mWorkingCaptureConfigMap.put(CaptureRequest.EXTENSION_STRENGTH, Integer.valueOf(this.mExtensionStrength));
                sessionProcessorImplCaptureCallbackAdapter = this.mRepeatingCaptureCallbackAdapter;
                hashMap = (HashMap) this.mWorkingCaptureConfigMap.clone();
            }
            this.mImpl.setParameters(hashMap);
            if (sessionProcessorImplCaptureCallbackAdapter != null) {
                this.mImpl.startRepeating(sessionProcessorImplCaptureCallbackAdapter);
            }
        }
    }

    public LiveData<Integer> getExtensionStrength() {
        return this.mExtensionStrengthLiveData;
    }

    public void setParameters(Config config) {
        HashMap<CaptureRequest.Key<?>, Object> convertConfigToMap;
        synchronized (this.mLock) {
            convertConfigToMap = convertConfigToMap(config);
            if (this.mExtensionStrength != -1 && Build.VERSION.SDK_INT >= 34) {
                convertConfigToMap.put(CaptureRequest.EXTENSION_STRENGTH, Integer.valueOf(this.mExtensionStrength));
            }
            this.mWorkingCaptureConfigMap = convertConfigToMap;
        }
        this.mImpl.setParameters(convertConfigToMap);
    }

    private static HashMap<CaptureRequest.Key<?>, Object> convertConfigToMap(Config config) {
        HashMap<CaptureRequest.Key<?>, Object> hashMap = new HashMap<>();
        RequestOptionConfig build = RequestOptionConfig.Builder.from(config).build();
        for (Config.Option next : build.listOptions()) {
            hashMap.put((CaptureRequest.Key) next.getToken(), build.retrieveOption(next));
        }
        return hashMap;
    }

    public void onCaptureSessionStart(RequestProcessor requestProcessor) {
        this.mImpl.onCaptureSessionStart(new RequestProcessorImplAdapter(requestProcessor));
    }

    public void onCaptureSessionEnd() {
        this.mImpl.onCaptureSessionEnd();
    }

    public int startCapture(boolean z, TagBundle tagBundle, SessionProcessor.CaptureCallback captureCallback) {
        Logger.d(TAG, "startCapture postviewEnabled = " + z + " mWillReceiveOnCaptureCompleted = " + this.mWillReceiveOnCaptureCompleted);
        SessionProcessorImplCaptureCallbackAdapter sessionProcessorImplCaptureCallbackAdapter = new SessionProcessorImplCaptureCallbackAdapter(captureCallback, tagBundle, this.mWillReceiveOnCaptureCompleted);
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !this.mIsPostviewConfigured || !z || !this.mVendorExtender.isPostviewAvailable()) {
            return this.mImpl.startCapture(sessionProcessorImplCaptureCallbackAdapter);
        }
        return this.mImpl.startCaptureWithPostview(sessionProcessorImplCaptureCallbackAdapter);
    }

    public int startRepeating(TagBundle tagBundle, SessionProcessor.CaptureCallback captureCallback) {
        SessionProcessorImplCaptureCallbackAdapter sessionProcessorImplCaptureCallbackAdapter;
        synchronized (this.mLock) {
            sessionProcessorImplCaptureCallbackAdapter = new SessionProcessorImplCaptureCallbackAdapter(captureCallback, tagBundle, this.mExtensionMetadataMonitor, this.mWillReceiveOnCaptureCompleted);
            this.mRepeatingCaptureCallbackAdapter = sessionProcessorImplCaptureCallbackAdapter;
        }
        return this.mImpl.startRepeating(sessionProcessorImplCaptureCallbackAdapter);
    }

    public int startTrigger(Config config, TagBundle tagBundle, SessionProcessor.CaptureCallback captureCallback) {
        HashMap<CaptureRequest.Key<?>, Object> convertConfigToMap = convertConfigToMap(config);
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_3) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_3)) {
            return -1;
        }
        return this.mImpl.startTrigger(convertConfigToMap, new SessionProcessorImplCaptureCallbackAdapter(captureCallback, tagBundle, this.mWillReceiveOnCaptureCompleted));
    }

    public void stopRepeating() {
        this.mImpl.stopRepeating();
        synchronized (this.mLock) {
            this.mRepeatingCaptureCallbackAdapter = null;
        }
    }

    public void abortCapture(int i) {
        this.mImpl.abortCapture(i);
    }

    public Pair<Long, Long> getRealtimeCaptureLatency() {
        if (!ClientVersion.isMinimumCompatibleVersion(Version.VERSION_1_4) || !ExtensionVersion.isMinimumCompatibleVersion(Version.VERSION_1_4)) {
            return null;
        }
        return this.mImpl.getRealtimeCaptureLatency();
    }

    public Map<Integer, List<Size>> getSupportedPostviewSize(Size size) {
        return this.mVendorExtender.getSupportedPostviewResolutions(size);
    }

    private static class OutputSurfaceImplAdapter implements OutputSurfaceImpl {
        private final OutputSurface mOutputSurface;

        OutputSurfaceImplAdapter(OutputSurface outputSurface) {
            this.mOutputSurface = outputSurface;
        }

        public Surface getSurface() {
            return this.mOutputSurface.getSurface();
        }

        public Size getSize() {
            return this.mOutputSurface.getSize();
        }

        public int getImageFormat() {
            return this.mOutputSurface.getImageFormat();
        }
    }

    private static class OutputSurfaceConfigurationImplAdapter implements OutputSurfaceConfigurationImpl {
        private final OutputSurfaceImpl mAnalysisOutputSurface;
        private final OutputSurfaceImpl mCaptureOutputSurface;
        private final OutputSurfaceImpl mPostviewOutputSurface;
        private final OutputSurfaceImpl mPreviewOutputSurface;

        OutputSurfaceConfigurationImplAdapter(OutputSurfaceConfiguration outputSurfaceConfiguration) {
            this.mPreviewOutputSurface = new OutputSurfaceImplAdapter(outputSurfaceConfiguration.getPreviewOutputSurface());
            this.mCaptureOutputSurface = new OutputSurfaceImplAdapter(outputSurfaceConfiguration.getImageCaptureOutputSurface());
            OutputSurfaceImplAdapter outputSurfaceImplAdapter = null;
            this.mAnalysisOutputSurface = outputSurfaceConfiguration.getImageAnalysisOutputSurface() != null ? new OutputSurfaceImplAdapter(outputSurfaceConfiguration.getImageAnalysisOutputSurface()) : null;
            this.mPostviewOutputSurface = outputSurfaceConfiguration.getPostviewOutputSurface() != null ? new OutputSurfaceImplAdapter(outputSurfaceConfiguration.getPostviewOutputSurface()) : outputSurfaceImplAdapter;
        }

        public OutputSurfaceImpl getPreviewOutputSurface() {
            return this.mPreviewOutputSurface;
        }

        public OutputSurfaceImpl getImageCaptureOutputSurface() {
            return this.mCaptureOutputSurface;
        }

        public OutputSurfaceImpl getImageAnalysisOutputSurface() {
            return this.mAnalysisOutputSurface;
        }

        public OutputSurfaceImpl getPostviewOutputSurface() {
            return this.mPostviewOutputSurface;
        }
    }

    private class RequestProcessorImplAdapter implements RequestProcessorImpl {
        private final RequestProcessor mRequestProcessor;

        RequestProcessorImplAdapter(RequestProcessor requestProcessor) {
            this.mRequestProcessor = requestProcessor;
        }

        public void setImageProcessor(int i, ImageProcessorImpl imageProcessorImpl) {
            AdvancedSessionProcessor.this.setImageProcessor(i, new ImageProcessorAdapter(imageProcessorImpl));
        }

        public int submit(RequestProcessorImpl.Request request, RequestProcessorImpl.Callback callback) {
            return this.mRequestProcessor.submit((RequestProcessor.Request) new RequestAdapter(request), (RequestProcessor.Callback) new CallbackAdapter(callback));
        }

        public int submit(List<RequestProcessorImpl.Request> list, RequestProcessorImpl.Callback callback) {
            ArrayList arrayList = new ArrayList();
            for (RequestProcessorImpl.Request requestAdapter : list) {
                arrayList.add(new RequestAdapter(requestAdapter));
            }
            return this.mRequestProcessor.submit((List<RequestProcessor.Request>) arrayList, (RequestProcessor.Callback) new CallbackAdapter(callback));
        }

        public int setRepeating(RequestProcessorImpl.Request request, RequestProcessorImpl.Callback callback) {
            return this.mRequestProcessor.setRepeating(new RequestAdapter(request), new CallbackAdapter(callback));
        }

        public void abortCaptures() {
            this.mRequestProcessor.abortCaptures();
        }

        public void stopRepeating() {
            this.mRequestProcessor.stopRepeating();
        }
    }

    private static class RequestAdapter implements RequestProcessor.Request {
        private final RequestProcessorImpl.Request mImplRequest;
        private final Config mParameters;
        private final List<Integer> mTargetOutputConfigIds;
        private final int mTemplateId;

        RequestAdapter(RequestProcessorImpl.Request request) {
            this.mImplRequest = request;
            ArrayList arrayList = new ArrayList();
            for (Integer add : request.getTargetOutputConfigIds()) {
                arrayList.add(add);
            }
            this.mTargetOutputConfigIds = arrayList;
            RequestOptionConfig.Builder builder = new RequestOptionConfig.Builder();
            for (CaptureRequest.Key key : request.getParameters().keySet()) {
                builder.setCaptureRequestOption(key, request.getParameters().get(key));
            }
            this.mParameters = builder.build();
            this.mTemplateId = request.getTemplateId().intValue();
        }

        public List<Integer> getTargetOutputConfigIds() {
            return this.mTargetOutputConfigIds;
        }

        public Config getParameters() {
            return this.mParameters;
        }

        public int getTemplateId() {
            return this.mTemplateId;
        }

        public RequestProcessorImpl.Request getImplRequest() {
            return this.mImplRequest;
        }
    }

    private static class ImageProcessorAdapter implements ImageProcessor {
        private final ImageProcessorImpl mImpl;

        ImageProcessorAdapter(ImageProcessorImpl imageProcessorImpl) {
            this.mImpl = imageProcessorImpl;
        }

        public void onNextImageAvailable(int i, long j, ImageReference imageReference, String str) {
            this.mImpl.onNextImageAvailable(i, j, new ImageReferenceImplAdapter(imageReference), str);
        }
    }

    private static class ImageReferenceImplAdapter implements ImageReferenceImpl {
        private final ImageReference mImageReference;

        ImageReferenceImplAdapter(ImageReference imageReference) {
            this.mImageReference = imageReference;
        }

        public boolean increment() {
            return this.mImageReference.increment();
        }

        public boolean decrement() {
            return this.mImageReference.decrement();
        }

        public Image get() {
            return this.mImageReference.get();
        }
    }

    private static class CallbackAdapter implements RequestProcessor.Callback {
        private final RequestProcessorImpl.Callback mCallback;

        CallbackAdapter(RequestProcessorImpl.Callback callback) {
            this.mCallback = callback;
        }

        public void onCaptureStarted(RequestProcessor.Request request, long j, long j2) {
            this.mCallback.onCaptureStarted(getImplRequest(request), j, j2);
        }

        public void onCaptureProgressed(RequestProcessor.Request request, CameraCaptureResult cameraCaptureResult) {
            CaptureResult captureResult = cameraCaptureResult.getCaptureResult();
            Preconditions.checkArgument(captureResult != null, "Cannot get CaptureResult from the cameraCaptureResult ");
            this.mCallback.onCaptureProgressed(getImplRequest(request), captureResult);
        }

        public void onCaptureCompleted(RequestProcessor.Request request, CameraCaptureResult cameraCaptureResult) {
            CaptureResult captureResult = cameraCaptureResult.getCaptureResult();
            Preconditions.checkArgument(captureResult instanceof TotalCaptureResult, "CaptureResult in cameraCaptureResult is not a TotalCaptureResult");
            this.mCallback.onCaptureCompleted(getImplRequest(request), (TotalCaptureResult) captureResult);
        }

        public void onCaptureFailed(RequestProcessor.Request request, CameraCaptureFailure cameraCaptureFailure) {
            Object captureFailure = cameraCaptureFailure.getCaptureFailure();
            Preconditions.checkArgument(captureFailure instanceof CaptureFailure, "CameraCaptureFailure does not contain CaptureFailure.");
            this.mCallback.onCaptureFailed(getImplRequest(request), (CaptureFailure) captureFailure);
        }

        public void onCaptureBufferLost(RequestProcessor.Request request, long j, int i) {
            this.mCallback.onCaptureBufferLost(getImplRequest(request), j, i);
        }

        public void onCaptureSequenceCompleted(int i, long j) {
            this.mCallback.onCaptureSequenceCompleted(i, j);
        }

        public void onCaptureSequenceAborted(int i) {
            this.mCallback.onCaptureSequenceAborted(i);
        }

        private RequestProcessorImpl.Request getImplRequest(RequestProcessor.Request request) {
            Preconditions.checkArgument(request instanceof RequestAdapter);
            return ((RequestAdapter) request).getImplRequest();
        }
    }

    private static class SessionProcessorImplCaptureCallbackAdapter implements SessionProcessorImpl.CaptureCallback {
        private final SessionProcessor.CaptureCallback mCaptureCallback;
        private final ExtensionMetadataMonitor mExtensionMetadataMonitor;
        private long mOnCaptureStartedTimestamp;
        private final TagBundle mTagBundle;
        private boolean mWillReceiveOnCaptureCompleted;

        SessionProcessorImplCaptureCallbackAdapter(SessionProcessor.CaptureCallback captureCallback, TagBundle tagBundle, boolean z) {
            this(captureCallback, tagBundle, (ExtensionMetadataMonitor) null, z);
        }

        SessionProcessorImplCaptureCallbackAdapter(SessionProcessor.CaptureCallback captureCallback, TagBundle tagBundle, ExtensionMetadataMonitor extensionMetadataMonitor, boolean z) {
            this.mOnCaptureStartedTimestamp = -1;
            this.mCaptureCallback = captureCallback;
            this.mTagBundle = tagBundle;
            this.mExtensionMetadataMonitor = extensionMetadataMonitor;
            this.mWillReceiveOnCaptureCompleted = z;
        }

        public void onCaptureStarted(int i, long j) {
            this.mOnCaptureStartedTimestamp = j;
            this.mCaptureCallback.onCaptureStarted(i, j);
        }

        public void onCaptureProcessStarted(int i) {
            this.mCaptureCallback.onCaptureProcessStarted(i);
        }

        public void onCaptureFailed(int i) {
            this.mCaptureCallback.onCaptureFailed(i);
        }

        public void onCaptureSequenceCompleted(int i) {
            if (!this.mWillReceiveOnCaptureCompleted) {
                this.mCaptureCallback.onCaptureCompleted(this.mOnCaptureStartedTimestamp, i, new KeyValueMapCameraCaptureResult(this.mOnCaptureStartedTimestamp, this.mTagBundle, Collections.emptyMap()));
                this.mCaptureCallback.onCaptureSequenceCompleted(i);
            }
        }

        public void onCaptureSequenceAborted(int i) {
            this.mCaptureCallback.onCaptureSequenceAborted(i);
        }

        public void onCaptureCompleted(long j, int i, Map<CaptureResult.Key, Object> map) {
            ExtensionMetadataMonitor extensionMetadataMonitor = this.mExtensionMetadataMonitor;
            if (extensionMetadataMonitor != null) {
                extensionMetadataMonitor.checkExtensionMetadata(map);
            }
            if (this.mWillReceiveOnCaptureCompleted) {
                this.mCaptureCallback.onCaptureCompleted(j, i, new KeyValueMapCameraCaptureResult(j, this.mTagBundle, map));
                this.mCaptureCallback.onCaptureSequenceCompleted(i);
            }
        }

        public void onCaptureProcessProgressed(int i) {
            this.mCaptureCallback.onCaptureProcessProgressed(i);
        }
    }

    private static class ExtensionMetadataMonitor {
        private final MutableLiveData<Integer> mCurrentExtensionTypeLiveData;
        private final MutableLiveData<Integer> mExtensionStrengthLiveData;

        private int convertExtensionMode(int i) {
            if (i == 0) {
                return 5;
            }
            if (i == 1) {
                return 4;
            }
            if (i == 2) {
                return 1;
            }
            if (i != 3) {
                return i != 4 ? 0 : 3;
            }
            return 2;
        }

        ExtensionMetadataMonitor(MutableLiveData<Integer> mutableLiveData, MutableLiveData<Integer> mutableLiveData2) {
            this.mCurrentExtensionTypeLiveData = mutableLiveData;
            this.mExtensionStrengthLiveData = mutableLiveData2;
        }

        /* access modifiers changed from: package-private */
        public void checkExtensionMetadata(Map<CaptureResult.Key, Object> map) {
            Object obj;
            Object obj2;
            if (Build.VERSION.SDK_INT >= 34) {
                if (!(this.mCurrentExtensionTypeLiveData == null || (obj2 = map.get(CaptureResult.EXTENSION_CURRENT_TYPE)) == null)) {
                    Integer num = (Integer) obj2;
                    if (!Objects.equals(this.mCurrentExtensionTypeLiveData.getValue(), Integer.valueOf(convertExtensionMode(num.intValue())))) {
                        this.mCurrentExtensionTypeLiveData.postValue(Integer.valueOf(convertExtensionMode(num.intValue())));
                    }
                }
                if (this.mExtensionStrengthLiveData != null && (obj = map.get(CaptureResult.EXTENSION_STRENGTH)) != null && !Objects.equals(this.mExtensionStrengthLiveData.getValue(), obj)) {
                    this.mExtensionStrengthLiveData.postValue((Integer) obj);
                }
            }
        }
    }
}
