package androidx.camera.video;

import android.content.ContentValues;
import android.content.Context;
import android.location.Location;
import android.media.MediaMuxer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.MutableStateObservable;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.StateObservable;
import androidx.camera.core.impl.Timebase;
import androidx.camera.core.impl.utils.CloseGuardHelper;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.utils.ArrayRingBuffer;
import androidx.camera.core.internal.utils.RingBuffer;
import androidx.camera.video.MediaSpec;
import androidx.camera.video.StreamInfo;
import androidx.camera.video.VideoOutput;
import androidx.camera.video.VideoRecordEvent;
import androidx.camera.video.internal.DebugUtils;
import androidx.camera.video.internal.OutputStorage;
import androidx.camera.video.internal.VideoValidatedEncoderProfilesProxy;
import androidx.camera.video.internal.audio.AudioSettings;
import androidx.camera.video.internal.audio.AudioSource;
import androidx.camera.video.internal.audio.AudioSourceAccessException;
import androidx.camera.video.internal.compat.Api26Impl;
import androidx.camera.video.internal.compat.quirk.DeactivateEncoderSurfaceBeforeStopEncoderQuirk;
import androidx.camera.video.internal.compat.quirk.DeviceQuirks;
import androidx.camera.video.internal.compat.quirk.EncoderNotUsePersistentInputSurfaceQuirk;
import androidx.camera.video.internal.config.AudioConfigUtil;
import androidx.camera.video.internal.config.AudioMimeInfo;
import androidx.camera.video.internal.encoder.BufferCopiedEncodedData;
import androidx.camera.video.internal.encoder.EncodeException;
import androidx.camera.video.internal.encoder.EncodedData;
import androidx.camera.video.internal.encoder.Encoder;
import androidx.camera.video.internal.encoder.EncoderCallback;
import androidx.camera.video.internal.encoder.EncoderFactory;
import androidx.camera.video.internal.encoder.EncoderImpl;
import androidx.camera.video.internal.encoder.InvalidConfigException;
import androidx.camera.video.internal.encoder.OutputConfig;
import androidx.camera.video.internal.encoder.VideoEncoderInfo;
import androidx.camera.video.internal.encoder.VideoEncoderInfoImpl;
import androidx.camera.video.internal.utils.OutputUtil;
import androidx.camera.video.internal.utils.StorageUtil;
import androidx.camera.video.internal.workaround.CorrectNegativeLatLongForMediaMuxer;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class Recorder implements VideoOutput {
    private static final int AUDIO_CACHE_SIZE = 60;
    private static final Executor AUDIO_EXECUTOR = CameraXExecutors.newSequentialExecutor(CameraXExecutors.ioExecutor());
    static final EncoderFactory DEFAULT_ENCODER_FACTORY = new Recorder$$ExternalSyntheticLambda16();
    public static final QualitySelector DEFAULT_QUALITY_SELECTOR;
    private static final String INSUFFICIENT_STORAGE_ERROR_MSG = "Insufficient storage space. The available storage (%d bytes) is below the required threshold of %d bytes.";
    private static final String MEDIA_COLUMN = "_data";
    private static final MediaSpec MEDIA_SPEC_DEFAULT;
    private static final int NOT_PENDING = 0;
    /* access modifiers changed from: private */
    public static final OutputStorage.Factory OUTPUT_STORAGE_FACTORY_DEFAULT = new Recorder$$ExternalSyntheticLambda17();
    private static final int PENDING = 1;
    private static final Exception PENDING_RECORDING_ERROR_CAUSE_SOURCE_INACTIVE = new RuntimeException("The video frame producer became inactive before any data was received.");
    private static final Set<State> PENDING_STATES = Collections.unmodifiableSet(EnumSet.of(State.PENDING_RECORDING, State.PENDING_PAUSED));
    private static final long REQUIRED_FREE_STORAGE_DEFAULT_BYTES = 52428800;
    private static final long REQUIRED_FREE_STORAGE_UNSET = -1;
    private static final long RETRY_SETUP_VIDEO_DELAY_MS = 1000;
    private static final int RETRY_SETUP_VIDEO_MAX_COUNT = 3;
    private static final long SOURCE_NON_STREAMING_TIMEOUT_MS = 1000;
    private static final String TAG = "Recorder";
    private static final Set<State> VALID_NON_PENDING_STATES_WHILE_PENDING = Collections.unmodifiableSet(EnumSet.of(State.CONFIGURING, State.IDLING, State.RESETTING, State.STOPPING, State.ERROR));
    public static final int VIDEO_CAPABILITIES_SOURCE_CAMCORDER_PROFILE = 0;
    public static final int VIDEO_CAPABILITIES_SOURCE_CODEC_CAPABILITIES = 1;
    private static final VideoSpec VIDEO_SPEC_DEFAULT;
    static long sRetrySetupVideoDelayMs = 1000;
    static int sRetrySetupVideoMaxCount = 3;
    RecordingRecord mActiveRecordingRecord;
    Surface mActiveSurface;
    double mAudioAmplitude;
    Encoder mAudioEncoder;
    private final EncoderFactory mAudioEncoderFactory;
    Throwable mAudioErrorCause;
    OutputConfig mAudioOutputConfig;
    AudioSource mAudioSource;
    AudioState mAudioState;
    Integer mAudioTrackIndex;
    private long mAvailableBytesAboveRequired;
    long mDurationLimitNs;
    private final boolean mEncoderNotUsePersistentInputSurface;
    final List<ListenableFuture<Void>> mEncodingFutures;
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    long mFileSizeLimitInBytes;
    long mFirstRecordingAudioDataTimeUs;
    int mFirstRecordingVideoBitrate;
    long mFirstRecordingVideoDataTimeUs;
    RecordingRecord mInProgressRecording;
    boolean mInProgressRecordingStopping;
    private SurfaceRequest.TransformationInfo mInProgressTransformationInfo;
    boolean mIsAudioSourceSilenced;
    /* access modifiers changed from: private */
    public final MutableStateObservable<Boolean> mIsRecording;
    private long mLastGeneratedRecordingId;
    Surface mLatestSurface;
    SurfaceRequest mLatestSurfaceRequest;
    private final Object mLock = new Object();
    MediaMuxer mMediaMuxer;
    final MutableStateObservable<MediaSpec> mMediaSpec;
    private boolean mNeedsResetBeforeNextStart;
    private State mNonPendingState;
    private OutputStorage mOutputStorage;
    private final OutputStorage.Factory mOutputStorageFactory;
    Uri mOutputUri;
    final RingBuffer<EncodedData> mPendingAudioRingBuffer;
    EncodedData mPendingFirstVideoData;
    RecordingRecord mPendingRecordingRecord;
    long mPreviousRecordingAudioDataTimeUs;
    long mPreviousRecordingVideoDataTimeUs;
    long mRecordingBytes;
    long mRecordingDurationNs;
    int mRecordingStopError;
    Throwable mRecordingStopErrorCause;
    private final long mRequiredFreeStorageBytes;
    /* access modifiers changed from: private */
    public VideoValidatedEncoderProfilesProxy mResolvedEncoderProfiles;
    final Executor mSequentialExecutor;
    private SetupVideoTask mSetupVideoTask;
    private boolean mShouldSendResumeEvent;
    ScheduledFuture<?> mSourceNonStreamingTimeout;
    VideoOutput.SourceState mSourceState;
    private SurfaceRequest.TransformationInfo mSourceTransformationInfo;
    private State mState;
    int mStreamId;
    private final MutableStateObservable<StreamInfo> mStreamInfo;
    private final Executor mUserProvidedExecutor;
    private final int mVideoCapabilitiesSource;
    Encoder mVideoEncoder;
    Range<Integer> mVideoEncoderBitrateRange;
    /* access modifiers changed from: private */
    public final EncoderFactory mVideoEncoderFactory;
    VideoEncoderSession mVideoEncoderSession;
    VideoEncoderSession mVideoEncoderSessionToRelease;
    OutputConfig mVideoOutputConfig;
    Timebase mVideoSourceTimebase;
    Integer mVideoTrackIndex;

    enum AudioState {
        INITIALIZING,
        IDLING,
        DISABLED,
        ENABLED,
        ERROR_ENCODER,
        ERROR_SOURCE
    }

    enum State {
        CONFIGURING,
        PENDING_RECORDING,
        PENDING_PAUSED,
        IDLING,
        RECORDING,
        PAUSED,
        STOPPING,
        RESETTING,
        ERROR
    }

    static {
        QualitySelector fromOrderedList = QualitySelector.fromOrderedList(Arrays.asList(new Quality[]{Quality.FHD, Quality.HD, Quality.SD}), FallbackStrategy.higherQualityOrLowerThan(Quality.FHD));
        DEFAULT_QUALITY_SELECTOR = fromOrderedList;
        VideoSpec build = VideoSpec.builder().setQualitySelector(fromOrderedList).setAspectRatio(-1).build();
        VIDEO_SPEC_DEFAULT = build;
        MEDIA_SPEC_DEFAULT = MediaSpec.builder().setOutputFormat(-1).setVideoSpec(build).build();
    }

    Recorder(Executor executor, MediaSpec mediaSpec, int i, EncoderFactory encoderFactory, EncoderFactory encoderFactory2, OutputStorage.Factory factory, long j) {
        this.mEncoderNotUsePersistentInputSurface = DeviceQuirks.get(EncoderNotUsePersistentInputSurfaceQuirk.class) != null;
        this.mState = State.CONFIGURING;
        this.mNonPendingState = null;
        this.mStreamId = 0;
        this.mActiveRecordingRecord = null;
        this.mPendingRecordingRecord = null;
        this.mLastGeneratedRecordingId = 0;
        this.mInProgressRecording = null;
        this.mInProgressRecordingStopping = false;
        this.mInProgressTransformationInfo = null;
        this.mSourceTransformationInfo = null;
        this.mResolvedEncoderProfiles = null;
        this.mEncodingFutures = new ArrayList();
        this.mAudioTrackIndex = null;
        this.mVideoTrackIndex = null;
        this.mLatestSurface = null;
        this.mActiveSurface = null;
        this.mMediaMuxer = null;
        this.mAudioSource = null;
        this.mVideoEncoder = null;
        this.mVideoOutputConfig = null;
        this.mAudioEncoder = null;
        this.mAudioOutputConfig = null;
        this.mAudioState = AudioState.INITIALIZING;
        this.mOutputUri = Uri.EMPTY;
        this.mRecordingBytes = 0;
        this.mRecordingDurationNs = 0;
        this.mFirstRecordingVideoDataTimeUs = Long.MAX_VALUE;
        this.mFirstRecordingVideoBitrate = 0;
        this.mVideoEncoderBitrateRange = null;
        this.mFirstRecordingAudioDataTimeUs = Long.MAX_VALUE;
        this.mPreviousRecordingVideoDataTimeUs = Long.MAX_VALUE;
        this.mPreviousRecordingAudioDataTimeUs = Long.MAX_VALUE;
        this.mFileSizeLimitInBytes = 0;
        this.mDurationLimitNs = 0;
        this.mRecordingStopError = 1;
        this.mRecordingStopErrorCause = null;
        this.mPendingFirstVideoData = null;
        this.mPendingAudioRingBuffer = new ArrayRingBuffer(60);
        this.mAudioErrorCause = null;
        this.mIsAudioSourceSilenced = false;
        this.mSourceState = VideoOutput.SourceState.INACTIVE;
        this.mSourceNonStreamingTimeout = null;
        this.mNeedsResetBeforeNextStart = false;
        this.mVideoEncoderSessionToRelease = null;
        this.mAudioAmplitude = 0.0d;
        this.mShouldSendResumeEvent = false;
        this.mSetupVideoTask = null;
        this.mOutputStorage = null;
        this.mAvailableBytesAboveRequired = Long.MAX_VALUE;
        this.mUserProvidedExecutor = executor;
        executor = executor == null ? CameraXExecutors.ioExecutor() : executor;
        this.mExecutor = executor;
        Executor newSequentialExecutor = CameraXExecutors.newSequentialExecutor(executor);
        this.mSequentialExecutor = newSequentialExecutor;
        this.mMediaSpec = MutableStateObservable.withInitialState(composeRecorderMediaSpec(mediaSpec));
        this.mVideoCapabilitiesSource = i;
        this.mStreamInfo = MutableStateObservable.withInitialState(StreamInfo.of(this.mStreamId, internalStateToStreamState(this.mState)));
        this.mIsRecording = MutableStateObservable.withInitialState(false);
        this.mVideoEncoderFactory = encoderFactory;
        this.mAudioEncoderFactory = encoderFactory2;
        this.mOutputStorageFactory = factory;
        this.mVideoEncoderSession = new VideoEncoderSession(encoderFactory, newSequentialExecutor, executor);
        j = j == -1 ? REQUIRED_FREE_STORAGE_DEFAULT_BYTES : j;
        this.mRequiredFreeStorageBytes = j;
        Logger.d(TAG, "mRequiredFreeStorageBytes = " + StorageUtil.formatSize(j));
    }

    public void onSurfaceRequested(SurfaceRequest surfaceRequest) {
        onSurfaceRequested(surfaceRequest, Timebase.UPTIME);
    }

    public void onSurfaceRequested(SurfaceRequest surfaceRequest, Timebase timebase) {
        synchronized (this.mLock) {
            Logger.d(TAG, "Surface is requested in state: " + this.mState + ", Current surface: " + this.mStreamId);
            if (this.mState == State.ERROR) {
                setState(State.CONFIGURING);
            }
        }
        this.mSequentialExecutor.execute(new Recorder$$ExternalSyntheticLambda1(this, surfaceRequest, timebase));
    }

    public Observable<MediaSpec> getMediaSpec() {
        return this.mMediaSpec;
    }

    public Observable<StreamInfo> getStreamInfo() {
        return this.mStreamInfo;
    }

    public Observable<Boolean> isSourceStreamRequired() {
        return this.mIsRecording;
    }

    public void onSourceStateChanged(VideoOutput.SourceState sourceState) {
        this.mSequentialExecutor.execute(new Recorder$$ExternalSyntheticLambda11(this, sourceState));
    }

    public VideoCapabilities getMediaCapabilities(CameraInfo cameraInfo) {
        return getVideoCapabilities(cameraInfo, this.mVideoCapabilitiesSource);
    }

    public PendingRecording prepareRecording(Context context, FileOutputOptions fileOutputOptions) {
        return prepareRecordingInternal(context, fileOutputOptions);
    }

    public PendingRecording prepareRecording(Context context, FileDescriptorOutputOptions fileDescriptorOutputOptions) {
        return prepareRecordingInternal(context, fileDescriptorOutputOptions);
    }

    public PendingRecording prepareRecording(Context context, MediaStoreOutputOptions mediaStoreOutputOptions) {
        return prepareRecordingInternal(context, mediaStoreOutputOptions);
    }

    private PendingRecording prepareRecordingInternal(Context context, OutputOptions outputOptions) {
        Preconditions.checkNotNull(outputOptions, "The OutputOptions cannot be null.");
        return new PendingRecording(context, this, outputOptions);
    }

    public QualitySelector getQualitySelector() {
        return ((MediaSpec) getObservableData(this.mMediaSpec)).getVideoSpec().getQualitySelector();
    }

    public int getVideoCapabilitiesSource() {
        return this.mVideoCapabilitiesSource;
    }

    public int getAudioSource() {
        return ((MediaSpec) getObservableData(this.mMediaSpec)).getAudioSpec().getSource();
    }

    public Executor getExecutor() {
        return this.mUserProvidedExecutor;
    }

    public int getTargetVideoEncodingBitRate() {
        return ((MediaSpec) getObservableData(this.mMediaSpec)).getVideoSpec().getBitrate().getLower().intValue();
    }

    public int getAspectRatio() {
        return ((MediaSpec) getObservableData(this.mMediaSpec)).getVideoSpec().getAspectRatio();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0083, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001e, code lost:
        r4 = r3;
        r3 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.camera.video.Recording start(androidx.camera.video.PendingRecording r9) {
        /*
            r8 = this;
            java.lang.String r0 = "The given PendingRecording cannot be null."
            androidx.core.util.Preconditions.checkNotNull(r9, r0)
            java.lang.Object r0 = r8.mLock
            monitor-enter(r0)
            long r1 = r8.mLastGeneratedRecordingId     // Catch:{ all -> 0x00b6 }
            r3 = 1
            long r1 = r1 + r3
            r8.mLastGeneratedRecordingId = r1     // Catch:{ all -> 0x00b6 }
            androidx.camera.video.Recorder$State r3 = r8.mState     // Catch:{ all -> 0x00b6 }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x00b6 }
            r4 = 0
            r5 = 0
            switch(r3) {
                case 0: goto L_0x002b;
                case 1: goto L_0x0022;
                case 2: goto L_0x0022;
                case 3: goto L_0x002b;
                case 4: goto L_0x001c;
                case 5: goto L_0x001c;
                case 6: goto L_0x002b;
                case 7: goto L_0x002b;
                case 8: goto L_0x002b;
                default: goto L_0x001a;
            }     // Catch:{ all -> 0x00b6 }
        L_0x001a:
            goto L_0x0083
        L_0x001c:
            androidx.camera.video.Recorder$RecordingRecord r3 = r8.mActiveRecordingRecord     // Catch:{ all -> 0x00b6 }
        L_0x001e:
            r7 = r4
            r4 = r3
            r3 = r7
            goto L_0x0084
        L_0x0022:
            androidx.camera.video.Recorder$RecordingRecord r3 = r8.mPendingRecordingRecord     // Catch:{ all -> 0x00b6 }
            java.lang.Object r3 = androidx.core.util.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x00b6 }
            androidx.camera.video.Recorder$RecordingRecord r3 = (androidx.camera.video.Recorder.RecordingRecord) r3     // Catch:{ all -> 0x00b6 }
            goto L_0x001e
        L_0x002b:
            androidx.camera.video.Recorder$State r3 = r8.mState     // Catch:{ all -> 0x00b6 }
            androidx.camera.video.Recorder$State r6 = androidx.camera.video.Recorder.State.IDLING     // Catch:{ all -> 0x00b6 }
            if (r3 != r6) goto L_0x0041
            androidx.camera.video.Recorder$RecordingRecord r3 = r8.mActiveRecordingRecord     // Catch:{ all -> 0x00b6 }
            if (r3 != 0) goto L_0x003b
            androidx.camera.video.Recorder$RecordingRecord r3 = r8.mPendingRecordingRecord     // Catch:{ all -> 0x00b6 }
            if (r3 != 0) goto L_0x003b
            r3 = 1
            goto L_0x003c
        L_0x003b:
            r3 = r5
        L_0x003c:
            java.lang.String r6 = "Expected recorder to be idle but a recording is either pending or in progress."
            androidx.core.util.Preconditions.checkState(r3, r6)     // Catch:{ all -> 0x00b6 }
        L_0x0041:
            androidx.camera.video.Recorder$RecordingRecord r3 = androidx.camera.video.Recorder.RecordingRecord.from(r9, r1)     // Catch:{ IOException -> 0x0080 }
            android.content.Context r6 = r9.getApplicationContext()     // Catch:{ IOException -> 0x0080 }
            r3.initializeRecording(r6)     // Catch:{ IOException -> 0x0080 }
            r8.mPendingRecordingRecord = r3     // Catch:{ IOException -> 0x0080 }
            androidx.camera.video.Recorder$State r3 = r8.mState     // Catch:{ IOException -> 0x0080 }
            androidx.camera.video.Recorder$State r6 = androidx.camera.video.Recorder.State.IDLING     // Catch:{ IOException -> 0x0080 }
            if (r3 != r6) goto L_0x0064
            androidx.camera.video.Recorder$State r3 = androidx.camera.video.Recorder.State.PENDING_RECORDING     // Catch:{ IOException -> 0x0080 }
            r8.setState(r3)     // Catch:{ IOException -> 0x0080 }
            java.util.concurrent.Executor r3 = r8.mSequentialExecutor     // Catch:{ IOException -> 0x0080 }
            androidx.camera.video.Recorder$$ExternalSyntheticLambda13 r6 = new androidx.camera.video.Recorder$$ExternalSyntheticLambda13     // Catch:{ IOException -> 0x0080 }
            r6.<init>(r8)     // Catch:{ IOException -> 0x0080 }
            r3.execute(r6)     // Catch:{ IOException -> 0x0080 }
            goto L_0x0083
        L_0x0064:
            androidx.camera.video.Recorder$State r3 = r8.mState     // Catch:{ IOException -> 0x0080 }
            androidx.camera.video.Recorder$State r6 = androidx.camera.video.Recorder.State.ERROR     // Catch:{ IOException -> 0x0080 }
            if (r3 != r6) goto L_0x007a
            androidx.camera.video.Recorder$State r3 = androidx.camera.video.Recorder.State.PENDING_RECORDING     // Catch:{ IOException -> 0x0080 }
            r8.setState(r3)     // Catch:{ IOException -> 0x0080 }
            java.util.concurrent.Executor r3 = r8.mSequentialExecutor     // Catch:{ IOException -> 0x0080 }
            androidx.camera.video.Recorder$$ExternalSyntheticLambda14 r6 = new androidx.camera.video.Recorder$$ExternalSyntheticLambda14     // Catch:{ IOException -> 0x0080 }
            r6.<init>(r8)     // Catch:{ IOException -> 0x0080 }
            r3.execute(r6)     // Catch:{ IOException -> 0x0080 }
            goto L_0x0083
        L_0x007a:
            androidx.camera.video.Recorder$State r3 = androidx.camera.video.Recorder.State.PENDING_RECORDING     // Catch:{ IOException -> 0x0080 }
            r8.setState(r3)     // Catch:{ IOException -> 0x0080 }
            goto L_0x0083
        L_0x0080:
            r3 = move-exception
            r5 = 5
            goto L_0x0084
        L_0x0083:
            r3 = r4
        L_0x0084:
            monitor-exit(r0)     // Catch:{ all -> 0x00b6 }
            if (r4 != 0) goto L_0x00ae
            if (r5 == 0) goto L_0x00a9
            java.lang.String r0 = "Recorder"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "Recording was started when the Recorder had encountered error "
            r4.<init>(r6)
            java.lang.StringBuilder r4 = r4.append(r3)
            java.lang.String r4 = r4.toString()
            androidx.camera.core.Logger.e(r0, r4)
            androidx.camera.video.Recorder$RecordingRecord r0 = androidx.camera.video.Recorder.RecordingRecord.from(r9, r1)
            r8.finalizePendingRecording(r0, r5, r3)
            androidx.camera.video.Recording r9 = androidx.camera.video.Recording.createFinalizedFrom(r9, r1)
            return r9
        L_0x00a9:
            androidx.camera.video.Recording r9 = androidx.camera.video.Recording.from(r9, r1)
            return r9
        L_0x00ae:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "A recording is already in progress. Previous recordings must be stopped before a new recording can be started."
            r9.<init>(r0)
            throw r9
        L_0x00b6:
            r9 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00b6 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.Recorder.start(androidx.camera.video.PendingRecording):androidx.camera.video.Recording");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$start$2$androidx-camera-video-Recorder  reason: not valid java name */
    public /* synthetic */ void m247lambda$start$2$androidxcameravideoRecorder() {
        SurfaceRequest surfaceRequest = this.mLatestSurfaceRequest;
        if (surfaceRequest != null) {
            configureInternal(surfaceRequest, this.mVideoSourceTimebase, false);
            return;
        }
        throw new AssertionError("surface request is required to retry initialization.");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void pause(androidx.camera.video.Recording r5) {
        /*
            r4 = this;
            java.lang.String r0 = "pause() called on a recording that is no longer active: "
            java.lang.String r1 = "Called pause() from invalid state: "
            java.lang.Object r2 = r4.mLock
            monitor-enter(r2)
            androidx.camera.video.Recorder$RecordingRecord r3 = r4.mPendingRecordingRecord     // Catch:{ all -> 0x006f }
            boolean r3 = isSameRecording(r5, r3)     // Catch:{ all -> 0x006f }
            if (r3 != 0) goto L_0x002f
            androidx.camera.video.Recorder$RecordingRecord r3 = r4.mActiveRecordingRecord     // Catch:{ all -> 0x006f }
            boolean r3 = isSameRecording(r5, r3)     // Catch:{ all -> 0x006f }
            if (r3 != 0) goto L_0x002f
            java.lang.String r1 = "Recorder"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r3.<init>(r0)     // Catch:{ all -> 0x006f }
            androidx.camera.video.OutputOptions r5 = r5.getOutputOptions()     // Catch:{ all -> 0x006f }
            java.lang.StringBuilder r5 = r3.append(r5)     // Catch:{ all -> 0x006f }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x006f }
            androidx.camera.core.Logger.d(r1, r5)     // Catch:{ all -> 0x006f }
            monitor-exit(r2)     // Catch:{ all -> 0x006f }
            return
        L_0x002f:
            androidx.camera.video.Recorder$State r5 = r4.mState     // Catch:{ all -> 0x006f }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x006f }
            if (r5 == 0) goto L_0x005a
            r0 = 1
            if (r5 == r0) goto L_0x0053
            r0 = 3
            if (r5 == r0) goto L_0x005a
            r0 = 4
            if (r5 == r0) goto L_0x0041
            goto L_0x0058
        L_0x0041:
            androidx.camera.video.Recorder$State r5 = androidx.camera.video.Recorder.State.PAUSED     // Catch:{ all -> 0x006f }
            r4.setState(r5)     // Catch:{ all -> 0x006f }
            androidx.camera.video.Recorder$RecordingRecord r5 = r4.mActiveRecordingRecord     // Catch:{ all -> 0x006f }
            java.util.concurrent.Executor r0 = r4.mSequentialExecutor     // Catch:{ all -> 0x006f }
            androidx.camera.video.Recorder$$ExternalSyntheticLambda12 r1 = new androidx.camera.video.Recorder$$ExternalSyntheticLambda12     // Catch:{ all -> 0x006f }
            r1.<init>(r4, r5)     // Catch:{ all -> 0x006f }
            r0.execute(r1)     // Catch:{ all -> 0x006f }
            goto L_0x0058
        L_0x0053:
            androidx.camera.video.Recorder$State r5 = androidx.camera.video.Recorder.State.PENDING_PAUSED     // Catch:{ all -> 0x006f }
            r4.setState(r5)     // Catch:{ all -> 0x006f }
        L_0x0058:
            monitor-exit(r2)     // Catch:{ all -> 0x006f }
            return
        L_0x005a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x006f }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r0.<init>(r1)     // Catch:{ all -> 0x006f }
            androidx.camera.video.Recorder$State r1 = r4.mState     // Catch:{ all -> 0x006f }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x006f }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x006f }
            r5.<init>(r0)     // Catch:{ all -> 0x006f }
            throw r5     // Catch:{ all -> 0x006f }
        L_0x006f:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x006f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.Recorder.pause(androidx.camera.video.Recording):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void resume(androidx.camera.video.Recording r5) {
        /*
            r4 = this;
            java.lang.String r0 = "resume() called on a recording that is no longer active: "
            java.lang.String r1 = "Called resume() from invalid state: "
            java.lang.Object r2 = r4.mLock
            monitor-enter(r2)
            androidx.camera.video.Recorder$RecordingRecord r3 = r4.mPendingRecordingRecord     // Catch:{ all -> 0x006f }
            boolean r3 = isSameRecording(r5, r3)     // Catch:{ all -> 0x006f }
            if (r3 != 0) goto L_0x002f
            androidx.camera.video.Recorder$RecordingRecord r3 = r4.mActiveRecordingRecord     // Catch:{ all -> 0x006f }
            boolean r3 = isSameRecording(r5, r3)     // Catch:{ all -> 0x006f }
            if (r3 != 0) goto L_0x002f
            java.lang.String r1 = "Recorder"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r3.<init>(r0)     // Catch:{ all -> 0x006f }
            androidx.camera.video.OutputOptions r5 = r5.getOutputOptions()     // Catch:{ all -> 0x006f }
            java.lang.StringBuilder r5 = r3.append(r5)     // Catch:{ all -> 0x006f }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x006f }
            androidx.camera.core.Logger.d(r1, r5)     // Catch:{ all -> 0x006f }
            monitor-exit(r2)     // Catch:{ all -> 0x006f }
            return
        L_0x002f:
            androidx.camera.video.Recorder$State r5 = r4.mState     // Catch:{ all -> 0x006f }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x006f }
            if (r5 == 0) goto L_0x005a
            r0 = 5
            if (r5 == r0) goto L_0x0047
            r0 = 2
            if (r5 == r0) goto L_0x0041
            r0 = 3
            if (r5 == r0) goto L_0x005a
            goto L_0x0058
        L_0x0041:
            androidx.camera.video.Recorder$State r5 = androidx.camera.video.Recorder.State.PENDING_RECORDING     // Catch:{ all -> 0x006f }
            r4.setState(r5)     // Catch:{ all -> 0x006f }
            goto L_0x0058
        L_0x0047:
            androidx.camera.video.Recorder$State r5 = androidx.camera.video.Recorder.State.RECORDING     // Catch:{ all -> 0x006f }
            r4.setState(r5)     // Catch:{ all -> 0x006f }
            androidx.camera.video.Recorder$RecordingRecord r5 = r4.mActiveRecordingRecord     // Catch:{ all -> 0x006f }
            java.util.concurrent.Executor r0 = r4.mSequentialExecutor     // Catch:{ all -> 0x006f }
            androidx.camera.video.Recorder$$ExternalSyntheticLambda10 r1 = new androidx.camera.video.Recorder$$ExternalSyntheticLambda10     // Catch:{ all -> 0x006f }
            r1.<init>(r4, r5)     // Catch:{ all -> 0x006f }
            r0.execute(r1)     // Catch:{ all -> 0x006f }
        L_0x0058:
            monitor-exit(r2)     // Catch:{ all -> 0x006f }
            return
        L_0x005a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x006f }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r0.<init>(r1)     // Catch:{ all -> 0x006f }
            androidx.camera.video.Recorder$State r1 = r4.mState     // Catch:{ all -> 0x006f }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x006f }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x006f }
            r5.<init>(r0)     // Catch:{ all -> 0x006f }
            throw r5     // Catch:{ all -> 0x006f }
        L_0x006f:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x006f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.Recorder.resume(androidx.camera.video.Recording):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x007d, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0081, code lost:
        if (r14 != 10) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0083, code lost:
        androidx.camera.core.Logger.e(TAG, "Recording was stopped due to recording being garbage collected before any valid data has been produced.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008a, code lost:
        finalizePendingRecording(r2, 8, new java.lang.RuntimeException("Recording was stopped before any data could be produced.", r15));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop(androidx.camera.video.Recording r13, int r14, java.lang.Throwable r15) {
        /*
            r12 = this;
            java.lang.String r0 = "stop() called on a recording that is no longer active: "
            java.lang.Object r1 = r12.mLock
            monitor-enter(r1)
            androidx.camera.video.Recorder$RecordingRecord r2 = r12.mPendingRecordingRecord     // Catch:{ all -> 0x0097 }
            boolean r2 = isSameRecording(r13, r2)     // Catch:{ all -> 0x0097 }
            if (r2 != 0) goto L_0x002d
            androidx.camera.video.Recorder$RecordingRecord r2 = r12.mActiveRecordingRecord     // Catch:{ all -> 0x0097 }
            boolean r2 = isSameRecording(r13, r2)     // Catch:{ all -> 0x0097 }
            if (r2 != 0) goto L_0x002d
            java.lang.String r14 = "Recorder"
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ all -> 0x0097 }
            r15.<init>(r0)     // Catch:{ all -> 0x0097 }
            androidx.camera.video.OutputOptions r13 = r13.getOutputOptions()     // Catch:{ all -> 0x0097 }
            java.lang.StringBuilder r13 = r15.append(r13)     // Catch:{ all -> 0x0097 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x0097 }
            androidx.camera.core.Logger.d(r14, r13)     // Catch:{ all -> 0x0097 }
            monitor-exit(r1)     // Catch:{ all -> 0x0097 }
            return
        L_0x002d:
            androidx.camera.video.Recorder$State r0 = r12.mState     // Catch:{ all -> 0x0097 }
            int r0 = r0.ordinal()     // Catch:{ all -> 0x0097 }
            r2 = 0
            switch(r0) {
                case 0: goto L_0x0074;
                case 1: goto L_0x0062;
                case 2: goto L_0x0062;
                case 3: goto L_0x0074;
                case 4: goto L_0x0042;
                case 5: goto L_0x0042;
                case 6: goto L_0x0038;
                case 7: goto L_0x0038;
                default: goto L_0x0037;
            }     // Catch:{ all -> 0x0097 }
        L_0x0037:
            goto L_0x007c
        L_0x0038:
            androidx.camera.video.Recorder$RecordingRecord r0 = r12.mActiveRecordingRecord     // Catch:{ all -> 0x0097 }
            boolean r13 = isSameRecording(r13, r0)     // Catch:{ all -> 0x0097 }
            androidx.core.util.Preconditions.checkState(r13)     // Catch:{ all -> 0x0097 }
            goto L_0x007c
        L_0x0042:
            androidx.camera.video.Recorder$State r13 = androidx.camera.video.Recorder.State.STOPPING     // Catch:{ all -> 0x0097 }
            r12.setState(r13)     // Catch:{ all -> 0x0097 }
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ all -> 0x0097 }
            long r3 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0097 }
            long r8 = r13.toMicros(r3)     // Catch:{ all -> 0x0097 }
            androidx.camera.video.Recorder$RecordingRecord r7 = r12.mActiveRecordingRecord     // Catch:{ all -> 0x0097 }
            java.util.concurrent.Executor r13 = r12.mSequentialExecutor     // Catch:{ all -> 0x0097 }
            androidx.camera.video.Recorder$$ExternalSyntheticLambda3 r0 = new androidx.camera.video.Recorder$$ExternalSyntheticLambda3     // Catch:{ all -> 0x0097 }
            r5 = r0
            r6 = r12
            r10 = r14
            r11 = r15
            r5.<init>(r6, r7, r8, r10, r11)     // Catch:{ all -> 0x0097 }
            r13.execute(r0)     // Catch:{ all -> 0x0097 }
            goto L_0x007c
        L_0x0062:
            androidx.camera.video.Recorder$RecordingRecord r0 = r12.mPendingRecordingRecord     // Catch:{ all -> 0x0097 }
            boolean r13 = isSameRecording(r13, r0)     // Catch:{ all -> 0x0097 }
            androidx.core.util.Preconditions.checkState(r13)     // Catch:{ all -> 0x0097 }
            androidx.camera.video.Recorder$RecordingRecord r13 = r12.mPendingRecordingRecord     // Catch:{ all -> 0x0097 }
            r12.mPendingRecordingRecord = r2     // Catch:{ all -> 0x0097 }
            r12.restoreNonPendingState()     // Catch:{ all -> 0x0097 }
            r2 = r13
            goto L_0x007c
        L_0x0074:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0097 }
            java.lang.String r14 = "Calling stop() while idling or initializing is invalid."
            r13.<init>(r14)     // Catch:{ all -> 0x0097 }
            throw r13     // Catch:{ all -> 0x0097 }
        L_0x007c:
            monitor-exit(r1)     // Catch:{ all -> 0x0097 }
            if (r2 == 0) goto L_0x0096
            r13 = 10
            if (r14 != r13) goto L_0x008a
            java.lang.String r13 = "Recorder"
            java.lang.String r14 = "Recording was stopped due to recording being garbage collected before any valid data has been produced."
            androidx.camera.core.Logger.e(r13, r14)
        L_0x008a:
            java.lang.RuntimeException r13 = new java.lang.RuntimeException
            java.lang.String r14 = "Recording was stopped before any data could be produced."
            r13.<init>(r14, r15)
            r14 = 8
            r12.finalizePendingRecording(r2, r14, r13)
        L_0x0096:
            return
        L_0x0097:
            r13 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0097 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.Recorder.stop(androidx.camera.video.Recording, int, java.lang.Throwable):void");
    }

    /* access modifiers changed from: package-private */
    public void mute(Recording recording, boolean z) {
        synchronized (this.mLock) {
            if (isSameRecording(recording, this.mPendingRecordingRecord) || isSameRecording(recording, this.mActiveRecordingRecord)) {
                this.mSequentialExecutor.execute(new Recorder$$ExternalSyntheticLambda2(this, isSameRecording(recording, this.mPendingRecordingRecord) ? this.mPendingRecordingRecord : this.mActiveRecordingRecord, z));
            } else {
                Logger.d(TAG, "mute() called on a recording that is no longer active: " + recording.getOutputOptions());
            }
        }
    }

    private void finalizePendingRecording(RecordingRecord recordingRecord, int i, Throwable th) {
        recordingRecord.finalizeRecording(Uri.EMPTY);
        recordingRecord.updateVideoRecordEvent(VideoRecordEvent.finalizeWithError(recordingRecord.getOutputOptions(), RecordingStats.of(0, 0, AudioStats.of(1, this.mAudioErrorCause, 0.0d)), OutputResults.of(Uri.EMPTY), i, th));
    }

    /* access modifiers changed from: private */
    /* renamed from: onSurfaceRequestedInternal */
    public void m243lambda$onSurfaceRequested$0$androidxcameravideoRecorder(SurfaceRequest surfaceRequest, Timebase timebase) {
        SurfaceRequest surfaceRequest2 = this.mLatestSurfaceRequest;
        if (surfaceRequest2 != null && !surfaceRequest2.isServiced()) {
            this.mLatestSurfaceRequest.willNotProvideSurface();
        }
        this.mLatestSurfaceRequest = surfaceRequest;
        this.mVideoSourceTimebase = timebase;
        configureInternal(surfaceRequest, timebase, true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: onSourceStateChangedInternal */
    public void m242lambda$onSourceStateChanged$1$androidxcameravideoRecorder(VideoOutput.SourceState sourceState) {
        ScheduledFuture<?> scheduledFuture;
        Encoder encoder;
        VideoOutput.SourceState sourceState2 = this.mSourceState;
        this.mSourceState = sourceState;
        if (sourceState2 != sourceState) {
            Logger.d(TAG, "Video source has transitioned to state: " + sourceState);
            if (sourceState == VideoOutput.SourceState.INACTIVE) {
                if (this.mActiveSurface == null) {
                    SetupVideoTask setupVideoTask = this.mSetupVideoTask;
                    if (setupVideoTask != null) {
                        setupVideoTask.cancelFailedRetry();
                        this.mSetupVideoTask = null;
                    }
                    requestReset(4, (Throwable) null, false);
                    return;
                }
                this.mNeedsResetBeforeNextStart = true;
                RecordingRecord recordingRecord = this.mInProgressRecording;
                if (recordingRecord != null && !recordingRecord.isPersistent()) {
                    onInProgressRecordingInternalError(this.mInProgressRecording, 4, (Throwable) null);
                }
            } else if (sourceState == VideoOutput.SourceState.ACTIVE_NON_STREAMING && (scheduledFuture = this.mSourceNonStreamingTimeout) != null && scheduledFuture.cancel(false) && (encoder = this.mVideoEncoder) != null) {
                notifyEncoderSourceStopped(encoder);
            }
        } else {
            Logger.d(TAG, "Video source transitions to the same state: " + sourceState);
        }
    }

    /* access modifiers changed from: package-private */
    public void requestReset(int i, Throwable th, boolean z) {
        boolean z2;
        boolean z3;
        synchronized (this.mLock) {
            z2 = true;
            z3 = false;
            switch (this.mState.ordinal()) {
                case 0:
                case 3:
                case 8:
                    break;
                case 1:
                case 2:
                    updateNonPendingState(State.RESETTING);
                    break;
                case 4:
                case 5:
                    Preconditions.checkState(this.mInProgressRecording != null, "In-progress recording shouldn't be null when in state " + this.mState);
                    if (this.mActiveRecordingRecord == this.mInProgressRecording) {
                        if (!isPersistentRecordingInProgress()) {
                            setState(State.RESETTING);
                            z3 = true;
                            z2 = false;
                            break;
                        } else {
                            break;
                        }
                    } else {
                        throw new AssertionError("In-progress recording does not match the active recording. Unable to reset encoder.");
                    }
                case 6:
                    setState(State.RESETTING);
                    break;
            }
            z2 = false;
        }
        if (z2) {
            if (z) {
                resetVideo();
            } else {
                reset();
            }
        } else if (z3) {
            m248lambda$stop$5$androidxcameravideoRecorder(this.mInProgressRecording, -1, i, th);
        }
    }

    private void configureInternal(SurfaceRequest surfaceRequest, Timebase timebase, boolean z) {
        if (surfaceRequest.isServiced()) {
            Logger.w(TAG, "Ignore the SurfaceRequest since it is already served.");
            return;
        }
        surfaceRequest.setTransformationInfoListener(this.mSequentialExecutor, new Recorder$$ExternalSyntheticLambda15(this));
        Size resolution = surfaceRequest.getResolution();
        DynamicRange dynamicRange = surfaceRequest.getDynamicRange();
        VideoCapabilities videoCapabilities = getVideoCapabilities(surfaceRequest.getCamera().getCameraInfo());
        Quality findNearestHigherSupportedQualityFor = videoCapabilities.findNearestHigherSupportedQualityFor(resolution, dynamicRange);
        Logger.d(TAG, "Using supported quality of " + findNearestHigherSupportedQualityFor + " for surface size " + resolution);
        if (findNearestHigherSupportedQualityFor != Quality.NONE) {
            VideoValidatedEncoderProfilesProxy profiles = videoCapabilities.getProfiles(findNearestHigherSupportedQualityFor, dynamicRange);
            this.mResolvedEncoderProfiles = profiles;
            if (profiles == null) {
                throw new AssertionError("Camera advertised available quality but did not produce EncoderProfiles  for advertised quality.");
            }
        }
        SetupVideoTask setupVideoTask = this.mSetupVideoTask;
        if (setupVideoTask != null) {
            setupVideoTask.cancelFailedRetry();
        }
        SetupVideoTask setupVideoTask2 = new SetupVideoTask(surfaceRequest, timebase, z ? sRetrySetupVideoMaxCount : 0);
        this.mSetupVideoTask = setupVideoTask2;
        setupVideoTask2.start();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$configureInternal$7$androidx-camera-video-Recorder  reason: not valid java name */
    public /* synthetic */ void m240lambda$configureInternal$7$androidxcameravideoRecorder(SurfaceRequest.TransformationInfo transformationInfo) {
        this.mSourceTransformationInfo = transformationInfo;
    }

    private class SetupVideoTask {
        /* access modifiers changed from: private */
        public boolean mIsFailedRetryCanceled = false;
        /* access modifiers changed from: private */
        public final int mMaxRetryCount;
        /* access modifiers changed from: private */
        public int mRetryCount = 0;
        /* access modifiers changed from: private */
        public ScheduledFuture<?> mRetryFuture = null;
        /* access modifiers changed from: private */
        public final SurfaceRequest mSurfaceRequest;
        /* access modifiers changed from: private */
        public final Timebase mTimebase;

        static /* synthetic */ int access$408(SetupVideoTask setupVideoTask) {
            int i = setupVideoTask.mRetryCount;
            setupVideoTask.mRetryCount = i + 1;
            return i;
        }

        SetupVideoTask(SurfaceRequest surfaceRequest, Timebase timebase, int i) {
            this.mSurfaceRequest = surfaceRequest;
            this.mTimebase = timebase;
            this.mMaxRetryCount = i;
        }

        /* access modifiers changed from: package-private */
        public void start() {
            setupVideo(this.mSurfaceRequest, this.mTimebase);
        }

        /* access modifiers changed from: package-private */
        public void cancelFailedRetry() {
            if (!this.mIsFailedRetryCanceled) {
                this.mIsFailedRetryCanceled = true;
                ScheduledFuture<?> scheduledFuture = this.mRetryFuture;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                    this.mRetryFuture = null;
                }
            }
        }

        /* access modifiers changed from: private */
        public void setupVideo(SurfaceRequest surfaceRequest, Timebase timebase) {
            Recorder.this.safeToCloseVideoEncoder().addListener(new Recorder$SetupVideoTask$$ExternalSyntheticLambda0(this, surfaceRequest, timebase), Recorder.this.mSequentialExecutor);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$setupVideo$0$androidx-camera-video-Recorder$SetupVideoTask  reason: not valid java name */
        public /* synthetic */ void m253lambda$setupVideo$0$androidxcameravideoRecorder$SetupVideoTask(SurfaceRequest surfaceRequest, Timebase timebase) {
            if (surfaceRequest.isServiced() || (Recorder.this.mVideoEncoderSession.isConfiguredSurfaceRequest(surfaceRequest) && !Recorder.this.isPersistentRecordingInProgress())) {
                Logger.w(Recorder.TAG, "Ignore the SurfaceRequest " + surfaceRequest + " isServiced: " + surfaceRequest.isServiced() + " VideoEncoderSession: " + Recorder.this.mVideoEncoderSession + " has been configured with a persistent in-progress recording.");
                return;
            }
            final VideoEncoderSession videoEncoderSession = new VideoEncoderSession(Recorder.this.mVideoEncoderFactory, Recorder.this.mSequentialExecutor, Recorder.this.mExecutor);
            Recorder recorder = Recorder.this;
            ListenableFuture<Encoder> configure = videoEncoderSession.configure(surfaceRequest, timebase, (MediaSpec) recorder.getObservableData(recorder.mMediaSpec), Recorder.this.mResolvedEncoderProfiles);
            Recorder.this.mVideoEncoderSession = videoEncoderSession;
            Futures.addCallback(configure, new FutureCallback<Encoder>() {
                public void onSuccess(Encoder encoder) {
                    Logger.d(Recorder.TAG, "VideoEncoder is created. " + encoder);
                    if (encoder != null) {
                        boolean z = true;
                        Preconditions.checkState(Recorder.this.mVideoEncoderSession == videoEncoderSession);
                        if (Recorder.this.mVideoEncoder != null) {
                            z = false;
                        }
                        Preconditions.checkState(z);
                        Recorder.this.onVideoEncoderReady(videoEncoderSession);
                        Recorder.this.onConfigured();
                    }
                }

                public void onFailure(Throwable th) {
                    Logger.w(Recorder.TAG, "VideoEncoder Setup error: " + th, th);
                    if (SetupVideoTask.this.mRetryCount < SetupVideoTask.this.mMaxRetryCount) {
                        SetupVideoTask.access$408(SetupVideoTask.this);
                        ScheduledFuture unused = SetupVideoTask.this.mRetryFuture = Recorder.scheduleTask(new Recorder$SetupVideoTask$1$$ExternalSyntheticLambda0(this), Recorder.this.mSequentialExecutor, Recorder.sRetrySetupVideoDelayMs, TimeUnit.MILLISECONDS);
                        return;
                    }
                    Recorder.this.onEncoderSetupError(th);
                }

                /* access modifiers changed from: package-private */
                /* renamed from: lambda$onFailure$0$androidx-camera-video-Recorder$SetupVideoTask$1  reason: not valid java name */
                public /* synthetic */ void m254lambda$onFailure$0$androidxcameravideoRecorder$SetupVideoTask$1() {
                    if (!SetupVideoTask.this.mIsFailedRetryCanceled) {
                        Logger.d(Recorder.TAG, "Retry setupVideo #" + SetupVideoTask.this.mRetryCount);
                        SetupVideoTask setupVideoTask = SetupVideoTask.this;
                        setupVideoTask.setupVideo(setupVideoTask.mSurfaceRequest, SetupVideoTask.this.mTimebase);
                    }
                }
            }, Recorder.this.mSequentialExecutor);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isPersistentRecordingInProgress() {
        RecordingRecord recordingRecord = this.mInProgressRecording;
        return recordingRecord != null && recordingRecord.isPersistent();
    }

    /* access modifiers changed from: private */
    public ListenableFuture<Void> safeToCloseVideoEncoder() {
        Logger.d(TAG, "Try to safely release video encoder: " + this.mVideoEncoder);
        return this.mVideoEncoderSession.signalTermination();
    }

    /* access modifiers changed from: package-private */
    public void onVideoEncoderReady(final VideoEncoderSession videoEncoderSession) {
        Encoder videoEncoder = videoEncoderSession.getVideoEncoder();
        this.mVideoEncoder = videoEncoder;
        this.mVideoEncoderBitrateRange = ((VideoEncoderInfo) videoEncoder.getEncoderInfo()).getSupportedBitrateRange();
        this.mFirstRecordingVideoBitrate = this.mVideoEncoder.getConfiguredBitrate();
        Surface activeSurface = videoEncoderSession.getActiveSurface();
        this.mActiveSurface = activeSurface;
        setLatestSurface(activeSurface);
        videoEncoderSession.setOnSurfaceUpdateListener(this.mSequentialExecutor, new Recorder$$ExternalSyntheticLambda4(this));
        Futures.addCallback(videoEncoderSession.getReadyToReleaseFuture(), new FutureCallback<Encoder>() {
            public void onSuccess(Encoder encoder) {
                Logger.d(Recorder.TAG, "VideoEncoder can be released: " + encoder);
                if (encoder != null) {
                    if (Recorder.this.mSourceNonStreamingTimeout != null && Recorder.this.mSourceNonStreamingTimeout.cancel(false) && Recorder.this.mVideoEncoder != null && Recorder.this.mVideoEncoder == encoder) {
                        Recorder.notifyEncoderSourceStopped(Recorder.this.mVideoEncoder);
                    }
                    Recorder.this.mVideoEncoderSessionToRelease = videoEncoderSession;
                    Recorder.this.setLatestSurface((Surface) null);
                    Recorder recorder = Recorder.this;
                    recorder.requestReset(4, (Throwable) null, recorder.isPersistentRecordingInProgress());
                }
            }

            public void onFailure(Throwable th) {
                Logger.d(Recorder.TAG, "Error in ReadyToReleaseFuture: " + th);
            }
        }, this.mSequentialExecutor);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        androidx.core.util.Preconditions.checkState(isPersistentRecordingInProgress(), "Unexpectedly invoke onConfigured() when there's a non-persistent in-progress recording");
        r8 = 1;
        r2 = null;
        r6 = null;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        if (r9.mActiveRecordingRecord == null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        r2 = null;
        r6 = null;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005f, code lost:
        if (r9.mSourceState != androidx.camera.video.VideoOutput.SourceState.INACTIVE) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0061, code lost:
        r2 = r9.mPendingRecordingRecord;
        r9.mPendingRecordingRecord = null;
        restoreNonPendingState();
        r6 = PENDING_RECORDING_ERROR_CAUSE_SOURCE_INACTIVE;
        r7 = 4;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006d, code lost:
        r6 = null;
        r7 = 0;
        r8 = 0;
        r4 = makePendingRecordingActiveLocked(r9.mState);
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007e, code lost:
        r2 = null;
        r6 = null;
        r0 = false;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0082, code lost:
        r8 = r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onConfigured() {
        /*
            r9 = this;
            java.lang.String r0 = "Incorrectly invoke onConfigured() in state "
            java.lang.Object r1 = r9.mLock
            monitor-enter(r1)
            androidx.camera.video.Recorder$State r2 = r9.mState     // Catch:{ all -> 0x00bb }
            int r2 = r2.ordinal()     // Catch:{ all -> 0x00bb }
            r3 = 1
            r4 = 0
            r5 = 0
            switch(r2) {
                case 0: goto L_0x0079;
                case 1: goto L_0x0052;
                case 2: goto L_0x0050;
                case 3: goto L_0x003b;
                case 4: goto L_0x002c;
                case 5: goto L_0x002a;
                case 6: goto L_0x001c;
                case 7: goto L_0x003b;
                case 8: goto L_0x0013;
                default: goto L_0x0011;
            }     // Catch:{ all -> 0x00bb }
        L_0x0011:
            goto L_0x007e
        L_0x0013:
            java.lang.String r0 = "Recorder"
            java.lang.String r2 = "onConfigured() was invoked when the Recorder had encountered error"
            androidx.camera.core.Logger.e(r0, r2)     // Catch:{ all -> 0x00bb }
            goto L_0x007e
        L_0x001c:
            boolean r0 = r9.mEncoderNotUsePersistentInputSurface     // Catch:{ all -> 0x00bb }
            if (r0 == 0) goto L_0x0022
            goto L_0x007e
        L_0x0022:
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch:{ all -> 0x00bb }
            java.lang.String r2 = "Unexpectedly invoke onConfigured() in a STOPPING state when it's not waiting for a new surface."
            r0.<init>(r2)     // Catch:{ all -> 0x00bb }
            throw r0     // Catch:{ all -> 0x00bb }
        L_0x002a:
            r0 = r3
            goto L_0x002d
        L_0x002c:
            r0 = r5
        L_0x002d:
            boolean r2 = r9.isPersistentRecordingInProgress()     // Catch:{ all -> 0x00bb }
            java.lang.String r6 = "Unexpectedly invoke onConfigured() when there's a non-persistent in-progress recording"
            androidx.core.util.Preconditions.checkState(r2, r6)     // Catch:{ all -> 0x00bb }
            r8 = r3
            r2 = r4
            r6 = r2
            r7 = r5
            goto L_0x0083
        L_0x003b:
            java.lang.AssertionError r2 = new java.lang.AssertionError     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bb }
            r3.<init>(r0)     // Catch:{ all -> 0x00bb }
            androidx.camera.video.Recorder$State r0 = r9.mState     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x00bb }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00bb }
            r2.<init>(r0)     // Catch:{ all -> 0x00bb }
            throw r2     // Catch:{ all -> 0x00bb }
        L_0x0050:
            r0 = r3
            goto L_0x0053
        L_0x0052:
            r0 = r5
        L_0x0053:
            androidx.camera.video.Recorder$RecordingRecord r2 = r9.mActiveRecordingRecord     // Catch:{ all -> 0x00bb }
            if (r2 == 0) goto L_0x005b
            r2 = r4
            r6 = r2
            r7 = r5
            goto L_0x0082
        L_0x005b:
            androidx.camera.video.VideoOutput$SourceState r2 = r9.mSourceState     // Catch:{ all -> 0x00bb }
            androidx.camera.video.VideoOutput$SourceState r6 = androidx.camera.video.VideoOutput.SourceState.INACTIVE     // Catch:{ all -> 0x00bb }
            if (r2 != r6) goto L_0x006d
            androidx.camera.video.Recorder$RecordingRecord r2 = r9.mPendingRecordingRecord     // Catch:{ all -> 0x00bb }
            r9.mPendingRecordingRecord = r4     // Catch:{ all -> 0x00bb }
            r9.restoreNonPendingState()     // Catch:{ all -> 0x00bb }
            java.lang.Exception r6 = PENDING_RECORDING_ERROR_CAUSE_SOURCE_INACTIVE     // Catch:{ all -> 0x00bb }
            r7 = 4
            r8 = r5
            goto L_0x0083
        L_0x006d:
            androidx.camera.video.Recorder$State r2 = r9.mState     // Catch:{ all -> 0x00bb }
            androidx.camera.video.Recorder$RecordingRecord r2 = r9.makePendingRecordingActiveLocked(r2)     // Catch:{ all -> 0x00bb }
            r6 = r4
            r7 = r5
            r8 = r7
            r4 = r2
            r2 = r6
            goto L_0x0083
        L_0x0079:
            androidx.camera.video.Recorder$State r0 = androidx.camera.video.Recorder.State.IDLING     // Catch:{ all -> 0x00bb }
            r9.setState(r0)     // Catch:{ all -> 0x00bb }
        L_0x007e:
            r2 = r4
            r6 = r2
            r0 = r5
            r7 = r0
        L_0x0082:
            r8 = r7
        L_0x0083:
            monitor-exit(r1)     // Catch:{ all -> 0x00bb }
            if (r8 == 0) goto L_0x00af
            androidx.camera.video.Recorder$RecordingRecord r1 = r9.mInProgressRecording
            r9.updateEncoderCallbacks(r1, r3)
            androidx.camera.video.internal.encoder.Encoder r1 = r9.mVideoEncoder
            r1.start()
            boolean r1 = r9.mShouldSendResumeEvent
            if (r1 == 0) goto L_0x00a7
            androidx.camera.video.Recorder$RecordingRecord r1 = r9.mInProgressRecording
            androidx.camera.video.OutputOptions r2 = r1.getOutputOptions()
            androidx.camera.video.RecordingStats r3 = r9.getInProgressRecordingStats()
            androidx.camera.video.VideoRecordEvent$Resume r2 = androidx.camera.video.VideoRecordEvent.resume(r2, r3)
            r1.updateVideoRecordEvent(r2)
            r9.mShouldSendResumeEvent = r5
        L_0x00a7:
            if (r0 == 0) goto L_0x00ba
            androidx.camera.video.internal.encoder.Encoder r0 = r9.mVideoEncoder
            r0.pause()
            goto L_0x00ba
        L_0x00af:
            if (r4 == 0) goto L_0x00b5
            r9.startRecording(r4, r0)
            goto L_0x00ba
        L_0x00b5:
            if (r2 == 0) goto L_0x00ba
            r9.finalizePendingRecording(r2, r7, r6)
        L_0x00ba:
            return
        L_0x00bb:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00bb }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.Recorder.onConfigured():void");
    }

    private MediaSpec composeRecorderMediaSpec(MediaSpec mediaSpec) {
        MediaSpec.Builder builder = mediaSpec.toBuilder();
        if (mediaSpec.getVideoSpec().getAspectRatio() == -1) {
            builder.configureVideo(new Recorder$$ExternalSyntheticLambda18());
        }
        return builder.build();
    }

    private static boolean isSameRecording(Recording recording, RecordingRecord recordingRecord) {
        return recordingRecord != null && recording.getRecordingId() == recordingRecord.getRecordingId();
    }

    private void setupAudio(RecordingRecord recordingRecord) throws AudioSourceAccessException, InvalidConfigException {
        MediaSpec mediaSpec = (MediaSpec) getObservableData(this.mMediaSpec);
        AudioMimeInfo resolveAudioMimeInfo = AudioConfigUtil.resolveAudioMimeInfo(mediaSpec, this.mResolvedEncoderProfiles);
        Timebase timebase = Timebase.UPTIME;
        AudioSettings resolveAudioSettings = AudioConfigUtil.resolveAudioSettings(resolveAudioMimeInfo, mediaSpec.getAudioSpec());
        if (this.mAudioSource != null) {
            releaseCurrentAudioSource();
        }
        AudioSource audioSource = setupAudioSource(recordingRecord, resolveAudioSettings);
        this.mAudioSource = audioSource;
        Logger.d(TAG, String.format("Set up new audio source: 0x%x", new Object[]{Integer.valueOf(audioSource.hashCode())}));
        Encoder createEncoder = this.mAudioEncoderFactory.createEncoder(this.mExecutor, AudioConfigUtil.resolveAudioEncoderConfig(resolveAudioMimeInfo, timebase, resolveAudioSettings, mediaSpec.getAudioSpec()));
        this.mAudioEncoder = createEncoder;
        Encoder.EncoderInput input = createEncoder.getInput();
        if (input instanceof Encoder.ByteBufferInput) {
            this.mAudioSource.setBufferProvider((Encoder.ByteBufferInput) input);
            return;
        }
        throw new AssertionError("The EncoderInput of audio isn't a ByteBufferInput.");
    }

    private AudioSource setupAudioSource(RecordingRecord recordingRecord, AudioSettings audioSettings) throws AudioSourceAccessException {
        return recordingRecord.performOneTimeAudioSourceCreation(audioSettings, AUDIO_EXECUTOR);
    }

    private void releaseCurrentAudioSource() {
        final AudioSource audioSource = this.mAudioSource;
        if (audioSource != null) {
            this.mAudioSource = null;
            Logger.d(TAG, String.format("Releasing audio source: 0x%x", new Object[]{Integer.valueOf(audioSource.hashCode())}));
            Futures.addCallback(audioSource.release(), new FutureCallback<Void>() {
                public void onSuccess(Void voidR) {
                    Logger.d(Recorder.TAG, String.format("Released audio source successfully: 0x%x", new Object[]{Integer.valueOf(audioSource.hashCode())}));
                }

                public void onFailure(Throwable th) {
                    Logger.d(Recorder.TAG, String.format("An error occurred while attempting to release audio source: 0x%x", new Object[]{Integer.valueOf(audioSource.hashCode())}));
                }
            }, CameraXExecutors.directExecutor());
            return;
        }
        throw new AssertionError("Cannot release null audio source.");
    }

    /* access modifiers changed from: package-private */
    public void onEncoderSetupError(Throwable th) {
        RecordingRecord recordingRecord;
        synchronized (this.mLock) {
            recordingRecord = null;
            switch (this.mState.ordinal()) {
                case 0:
                    break;
                case 1:
                case 2:
                    RecordingRecord recordingRecord2 = this.mPendingRecordingRecord;
                    this.mPendingRecordingRecord = null;
                    recordingRecord = recordingRecord2;
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    throw new AssertionError("Encountered encoder setup error while in unexpected state " + this.mState + ": " + th);
            }
            setStreamId(-1);
            setState(State.ERROR);
        }
        if (recordingRecord != null) {
            finalizePendingRecording(recordingRecord, 7, th);
        }
    }

    /* access modifiers changed from: package-private */
    public void setupAndStartMediaMuxer(RecordingRecord recordingRecord) {
        int i;
        MediaMuxer performOneTimeMediaMuxerCreation;
        if (this.mMediaMuxer != null) {
            throw new AssertionError("Unable to set up media muxer when one already exists.");
        } else if (!isAudioEnabled() || !this.mPendingAudioRingBuffer.isEmpty()) {
            EncodedData encodedData = this.mPendingFirstVideoData;
            if (encodedData != null) {
                try {
                    this.mPendingFirstVideoData = null;
                    List<EncodedData> audioDataToWriteAndClearCache = getAudioDataToWriteAndClearCache(encodedData.getPresentationTimeUs());
                    long size = encodedData.size();
                    for (EncodedData size2 : audioDataToWriteAndClearCache) {
                        size += size2.size();
                    }
                    long j = this.mFileSizeLimitInBytes;
                    int i2 = 1;
                    if (j == 0 || size <= j) {
                        int i3 = 3;
                        try {
                            MediaSpec mediaSpec = (MediaSpec) getObservableData(this.mMediaSpec);
                            if (mediaSpec.getOutputFormat() == -1) {
                                i = supportedMuxerFormatOrDefaultFrom(this.mResolvedEncoderProfiles, MediaSpec.outputFormatToMuxerFormat(MEDIA_SPEC_DEFAULT.getOutputFormat()));
                            } else {
                                i = MediaSpec.outputFormatToMuxerFormat(mediaSpec.getOutputFormat());
                            }
                            performOneTimeMediaMuxerCreation = recordingRecord.performOneTimeMediaMuxerCreation(i, new Recorder$$ExternalSyntheticLambda9(this));
                            SurfaceRequest.TransformationInfo transformationInfo = this.mSourceTransformationInfo;
                            if (transformationInfo != null) {
                                setInProgressTransformationInfo(transformationInfo);
                                performOneTimeMediaMuxerCreation.setOrientationHint(transformationInfo.getRotationDegrees());
                            }
                            Location location = recordingRecord.getOutputOptions().getLocation();
                            if (location != null) {
                                Pair<Double, Double> adjustGeoLocation = CorrectNegativeLatLongForMediaMuxer.adjustGeoLocation(location.getLatitude(), location.getLongitude());
                                performOneTimeMediaMuxerCreation.setLocation((float) ((Double) adjustGeoLocation.first).doubleValue(), (float) ((Double) adjustGeoLocation.second).doubleValue());
                            }
                            this.mVideoTrackIndex = Integer.valueOf(performOneTimeMediaMuxerCreation.addTrack(this.mVideoOutputConfig.getMediaFormat()));
                            if (isAudioEnabled()) {
                                this.mAudioTrackIndex = Integer.valueOf(performOneTimeMediaMuxerCreation.addTrack(this.mAudioOutputConfig.getMediaFormat()));
                            }
                            try {
                                performOneTimeMediaMuxerCreation.start();
                                this.mMediaMuxer = performOneTimeMediaMuxerCreation;
                                writeVideoData(encodedData, recordingRecord);
                                for (EncodedData writeAudioData : audioDataToWriteAndClearCache) {
                                    writeAudioData(writeAudioData, recordingRecord);
                                }
                                if (encodedData != null) {
                                    encodedData.close();
                                }
                            } catch (IllegalStateException e) {
                                if (((OutputStorage) Preconditions.checkNotNull(this.mOutputStorage)).getAvailableBytes() < this.mRequiredFreeStorageBytes) {
                                    i2 = 3;
                                }
                                onInProgressRecordingInternalError(recordingRecord, i2, e);
                                if (encodedData != null) {
                                    encodedData.close();
                                }
                            }
                        } catch (IOException e2) {
                            if (!StorageUtil.isStorageFullException(e2)) {
                                i3 = 5;
                            }
                            onInProgressRecordingInternalError(recordingRecord, i3, e2);
                            if (encodedData != null) {
                                encodedData.close();
                            }
                        }
                    } else {
                        Logger.d(TAG, String.format("Initial data exceeds file size limit %d > %d", new Object[]{Long.valueOf(size), Long.valueOf(this.mFileSizeLimitInBytes)}));
                        onInProgressRecordingInternalError(recordingRecord, 2, (Throwable) null);
                        if (encodedData != null) {
                            encodedData.close();
                        }
                    }
                } catch (IllegalArgumentException e3) {
                    performOneTimeMediaMuxerCreation.release();
                    onInProgressRecordingInternalError(recordingRecord, 5, e3);
                    if (encodedData != null) {
                        encodedData.close();
                    }
                } catch (Throwable th) {
                    if (encodedData != null) {
                        try {
                            encodedData.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } else {
                throw new AssertionError("Media muxer cannot be started without an encoded video frame.");
            }
        } else {
            throw new AssertionError("Audio is enabled but no audio sample is ready. Cannot start media muxer.");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setupAndStartMediaMuxer$9$androidx-camera-video-Recorder  reason: not valid java name */
    public /* synthetic */ void m246lambda$setupAndStartMediaMuxer$9$androidxcameravideoRecorder(Uri uri) {
        this.mOutputUri = uri;
    }

    private List<EncodedData> getAudioDataToWriteAndClearCache(long j) {
        ArrayList arrayList = new ArrayList();
        while (!this.mPendingAudioRingBuffer.isEmpty()) {
            EncodedData dequeue = this.mPendingAudioRingBuffer.dequeue();
            if (dequeue.getPresentationTimeUs() >= j) {
                arrayList.add(dequeue);
            }
        }
        return arrayList;
    }

    private void startInternal(RecordingRecord recordingRecord) {
        AudioState audioState;
        AudioState audioState2;
        if (this.mInProgressRecording == null) {
            this.mInProgressRecording = recordingRecord;
            OutputStorage create = this.mOutputStorageFactory.create(recordingRecord.getOutputOptions());
            this.mOutputStorage = create;
            long availableBytes = create.getAvailableBytes();
            Logger.d(TAG, "availableBytes = " + StorageUtil.formatSize(availableBytes));
            long j = this.mRequiredFreeStorageBytes;
            if (availableBytes < j) {
                finalizeInProgressRecording(3, new IOException(String.format(INSUFFICIENT_STORAGE_ERROR_MSG, new Object[]{Long.valueOf(availableBytes), Long.valueOf(this.mRequiredFreeStorageBytes)})));
                return;
            }
            this.mAvailableBytesAboveRequired = availableBytes - j;
            if (recordingRecord.getOutputOptions().getFileSizeLimit() > 0) {
                this.mFileSizeLimitInBytes = Math.round(((double) recordingRecord.getOutputOptions().getFileSizeLimit()) * 0.95d);
                Logger.d(TAG, "File size limit in bytes: " + this.mFileSizeLimitInBytes);
            } else {
                this.mFileSizeLimitInBytes = 0;
            }
            if (recordingRecord.getOutputOptions().getDurationLimitMillis() > 0) {
                this.mDurationLimitNs = TimeUnit.MILLISECONDS.toNanos(recordingRecord.getOutputOptions().getDurationLimitMillis());
                Logger.d(TAG, "Duration limit in nanoseconds: " + this.mDurationLimitNs);
            } else {
                this.mDurationLimitNs = 0;
            }
            int ordinal = this.mAudioState.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    if (recordingRecord.hasAudioEnabled()) {
                        audioState2 = AudioState.ENABLED;
                    } else {
                        audioState2 = AudioState.DISABLED;
                    }
                    setAudioState(audioState2);
                } else if (ordinal == 2 || ordinal == 3 || ordinal == 4 || ordinal == 5) {
                    throw new AssertionError("Incorrectly invoke startInternal in audio state " + this.mAudioState);
                }
            } else if (recordingRecord.hasAudioEnabled()) {
                if (isAudioSupported()) {
                    try {
                        if (!this.mInProgressRecording.isPersistent() || this.mAudioEncoder == null) {
                            setupAudio(recordingRecord);
                        }
                        setAudioState(AudioState.ENABLED);
                    } catch (AudioSourceAccessException | InvalidConfigException e) {
                        Logger.e(TAG, "Unable to create audio resource with error: ", e);
                        if (e instanceof InvalidConfigException) {
                            audioState = AudioState.ERROR_ENCODER;
                        } else {
                            audioState = AudioState.ERROR_SOURCE;
                        }
                        setAudioState(audioState);
                        this.mAudioErrorCause = e;
                    }
                } else {
                    throw new AssertionError("The Recorder doesn't support recording with audio");
                }
            }
            updateEncoderCallbacks(recordingRecord, false);
            if (isAudioEnabled()) {
                this.mAudioSource.start(recordingRecord.isMuted());
                this.mAudioEncoder.start();
            }
            this.mVideoEncoder.start();
            RecordingRecord recordingRecord2 = this.mInProgressRecording;
            recordingRecord2.updateVideoRecordEvent(VideoRecordEvent.start(recordingRecord2.getOutputOptions(), getInProgressRecordingStats()));
            return;
        }
        throw new AssertionError("Attempted to start a new recording while another was in progress.");
    }

    private void updateEncoderCallbacks(RecordingRecord recordingRecord, boolean z) {
        if (!this.mEncodingFutures.isEmpty()) {
            ListenableFuture<List<V>> allAsList = Futures.allAsList(this.mEncodingFutures);
            if (!allAsList.isDone()) {
                allAsList.cancel(true);
            }
            this.mEncodingFutures.clear();
        }
        this.mEncodingFutures.add(CallbackToFutureAdapter.getFuture(new Recorder$$ExternalSyntheticLambda6(this, recordingRecord)));
        if (isAudioEnabled() && !z) {
            this.mEncodingFutures.add(CallbackToFutureAdapter.getFuture(new Recorder$$ExternalSyntheticLambda7(this, recordingRecord)));
        }
        Futures.addCallback(Futures.allAsList(this.mEncodingFutures), new FutureCallback<List<Void>>() {
            public void onSuccess(List<Void> list) {
                Logger.d(Recorder.TAG, "Encodings end successfully.");
                Recorder recorder = Recorder.this;
                recorder.finalizeInProgressRecording(recorder.mRecordingStopError, Recorder.this.mRecordingStopErrorCause);
            }

            public void onFailure(Throwable th) {
                Preconditions.checkState(Recorder.this.mInProgressRecording != null, "In-progress recording shouldn't be null");
                if (!Recorder.this.mInProgressRecording.isPersistent()) {
                    Logger.d(Recorder.TAG, "Encodings end with error: " + th);
                    Recorder recorder = Recorder.this;
                    recorder.finalizeInProgressRecording(recorder.mMediaMuxer == null ? 8 : 6, th);
                }
            }
        }, CameraXExecutors.directExecutor());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateEncoderCallbacks$10$androidx-camera-video-Recorder  reason: not valid java name */
    public /* synthetic */ Object m249lambda$updateEncoderCallbacks$10$androidxcameravideoRecorder(final RecordingRecord recordingRecord, final CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mVideoEncoder.setEncoderCallback(new EncoderCallback() {
            public void onEncodeStart() {
            }

            public void onEncodeStop() {
                completer.set(null);
            }

            public void onEncodeError(EncodeException encodeException) {
                completer.setException(encodeException);
            }

            public void onEncodedData(EncodedData encodedData) {
                boolean z;
                if (Recorder.this.mMediaMuxer != null) {
                    try {
                        Recorder.this.writeVideoData(encodedData, recordingRecord);
                        if (encodedData != null) {
                            encodedData.close();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                } else if (!Recorder.this.mInProgressRecordingStopping) {
                    if (Recorder.this.mPendingFirstVideoData != null) {
                        Recorder.this.mPendingFirstVideoData.close();
                        Recorder.this.mPendingFirstVideoData = null;
                        z = true;
                    } else {
                        z = false;
                    }
                    if (encodedData.isKeyFrame()) {
                        Recorder.this.mPendingFirstVideoData = encodedData;
                        if (!Recorder.this.isAudioEnabled() || !Recorder.this.mPendingAudioRingBuffer.isEmpty()) {
                            Logger.d(Recorder.TAG, "Received video keyframe. Starting muxer...");
                            Recorder.this.setupAndStartMediaMuxer(recordingRecord);
                            return;
                        } else if (z) {
                            Logger.d(Recorder.TAG, "Replaced cached video keyframe with newer keyframe.");
                            return;
                        } else {
                            Logger.d(Recorder.TAG, "Cached video keyframe while we wait for first audio sample before starting muxer.");
                            return;
                        }
                    } else {
                        if (z) {
                            Logger.d(Recorder.TAG, "Dropped cached keyframe since we have new video data and have not yet received audio data.");
                        }
                        Logger.d(Recorder.TAG, "Dropped video data since muxer has not yet started and data is not a keyframe.");
                        Recorder.this.mVideoEncoder.requestKeyFrame();
                        encodedData.close();
                        return;
                    }
                } else {
                    Logger.d(Recorder.TAG, "Drop video data since recording is stopping.");
                    encodedData.close();
                    return;
                }
                throw th;
            }

            public void onOutputConfigUpdate(OutputConfig outputConfig) {
                Recorder.this.mVideoOutputConfig = outputConfig;
            }
        }, this.mSequentialExecutor);
        return "videoEncodingFuture";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateEncoderCallbacks$12$androidx-camera-video-Recorder  reason: not valid java name */
    public /* synthetic */ Object m251lambda$updateEncoderCallbacks$12$androidxcameravideoRecorder(final RecordingRecord recordingRecord, final CallbackToFutureAdapter.Completer completer) throws Exception {
        final Recorder$$ExternalSyntheticLambda0 recorder$$ExternalSyntheticLambda0 = new Recorder$$ExternalSyntheticLambda0(this, completer);
        this.mAudioSource.setAudioSourceCallback(this.mSequentialExecutor, new AudioSource.AudioSourceCallback() {
            public void onSilenceStateChanged(boolean z) {
                if (Recorder.this.mIsAudioSourceSilenced != z) {
                    Recorder.this.mIsAudioSourceSilenced = z;
                    Recorder.this.updateInProgressStatusEvent();
                    return;
                }
                Logger.w(Recorder.TAG, "Audio source silenced transitions to the same state " + z);
            }

            public void onError(Throwable th) {
                Logger.e(Recorder.TAG, "Error occurred after audio source started.", th);
                if (th instanceof AudioSourceAccessException) {
                    recorder$$ExternalSyntheticLambda0.accept(th);
                }
            }

            public void onAmplitudeValue(double d) {
                Recorder.this.mAudioAmplitude = d;
            }
        });
        this.mAudioEncoder.setEncoderCallback(new EncoderCallback() {
            public void onEncodeStart() {
            }

            public void onEncodeStop() {
                completer.set(null);
            }

            public void onEncodeError(EncodeException encodeException) {
                if (Recorder.this.mAudioErrorCause == null) {
                    recorder$$ExternalSyntheticLambda0.accept(encodeException);
                }
            }

            public void onEncodedData(EncodedData encodedData) {
                if (Recorder.this.mAudioState == AudioState.DISABLED) {
                    encodedData.close();
                    throw new AssertionError("Audio is not enabled but audio encoded data is being produced.");
                } else if (Recorder.this.mMediaMuxer == null) {
                    if (!Recorder.this.mInProgressRecordingStopping) {
                        Recorder.this.mPendingAudioRingBuffer.enqueue(new BufferCopiedEncodedData(encodedData));
                        if (Recorder.this.mPendingFirstVideoData != null) {
                            Logger.d(Recorder.TAG, "Received audio data. Starting muxer...");
                            Recorder.this.setupAndStartMediaMuxer(recordingRecord);
                        } else {
                            Logger.d(Recorder.TAG, "Cached audio data while we wait for video keyframe before starting muxer.");
                        }
                    } else {
                        Logger.d(Recorder.TAG, "Drop audio data since recording is stopping.");
                    }
                    encodedData.close();
                    return;
                } else {
                    try {
                        Recorder.this.writeAudioData(encodedData, recordingRecord);
                        if (encodedData != null) {
                            encodedData.close();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                }
                throw th;
            }

            public void onOutputConfigUpdate(OutputConfig outputConfig) {
                Recorder.this.mAudioOutputConfig = outputConfig;
            }
        }, this.mSequentialExecutor);
        return "audioEncodingFuture";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$updateEncoderCallbacks$11$androidx-camera-video-Recorder  reason: not valid java name */
    public /* synthetic */ void m250lambda$updateEncoderCallbacks$11$androidxcameravideoRecorder(CallbackToFutureAdapter.Completer completer, Throwable th) {
        if (this.mAudioErrorCause == null) {
            if (th instanceof EncodeException) {
                setAudioState(AudioState.ERROR_ENCODER);
            } else {
                setAudioState(AudioState.ERROR_SOURCE);
            }
            this.mAudioErrorCause = th;
            updateInProgressStatusEvent();
            completer.set(null);
        }
    }

    /* access modifiers changed from: package-private */
    public void writeVideoData(EncodedData encodedData, RecordingRecord recordingRecord) {
        long j;
        String str;
        RecordingRecord recordingRecord2 = recordingRecord;
        if (this.mVideoTrackIndex != null) {
            long size = this.mRecordingBytes + encodedData.size();
            long j2 = this.mFileSizeLimitInBytes;
            if (j2 == 0 || size <= j2) {
                long presentationTimeUs = encodedData.getPresentationTimeUs();
                if (this.mFirstRecordingVideoDataTimeUs == Long.MAX_VALUE) {
                    this.mFirstRecordingVideoDataTimeUs = presentationTimeUs;
                    Logger.d(TAG, String.format("First video time: %d (%s)", new Object[]{Long.valueOf(presentationTimeUs), DebugUtils.readableUs(this.mFirstRecordingVideoDataTimeUs)}));
                    str = TAG;
                    j = 0;
                } else {
                    TimeUnit timeUnit = TimeUnit.MICROSECONDS;
                    long j3 = this.mFirstRecordingVideoDataTimeUs;
                    String str2 = TAG;
                    j = timeUnit.toNanos(presentationTimeUs - Math.min(j3, this.mFirstRecordingAudioDataTimeUs));
                    Preconditions.checkState(this.mPreviousRecordingVideoDataTimeUs != Long.MAX_VALUE, "There should be a previous data for adjusting the duration.");
                    long nanos = TimeUnit.MICROSECONDS.toNanos(presentationTimeUs - this.mPreviousRecordingVideoDataTimeUs) + j;
                    long j4 = this.mDurationLimitNs;
                    if (j4 == 0 || nanos <= j4) {
                        str = str2;
                    } else {
                        Logger.d(str2, String.format("Video data reaches duration limit %d > %d", new Object[]{Long.valueOf(nanos), Long.valueOf(this.mDurationLimitNs)}));
                        onInProgressRecordingInternalError(recordingRecord2, 9, (Throwable) null);
                        return;
                    }
                }
                try {
                    this.mMediaMuxer.writeSampleData(this.mVideoTrackIndex.intValue(), encodedData.getByteBuffer(), encodedData.getBufferInfo());
                    this.mRecordingBytes = size;
                    this.mRecordingDurationNs = j;
                    this.mPreviousRecordingVideoDataTimeUs = presentationTimeUs;
                    updateInProgressStatusEvent();
                    if (size > this.mAvailableBytesAboveRequired) {
                        long availableBytes = ((OutputStorage) Preconditions.checkNotNull(this.mOutputStorage)).getAvailableBytes();
                        Logger.d(str, "availableBytes = " + StorageUtil.formatSize(availableBytes));
                        long j5 = this.mRequiredFreeStorageBytes;
                        if (availableBytes < j5) {
                            onInProgressRecordingInternalError(recordingRecord2, 3, new IOException(String.format(INSUFFICIENT_STORAGE_ERROR_MSG, new Object[]{Long.valueOf(availableBytes), Long.valueOf(this.mRequiredFreeStorageBytes)})));
                        } else {
                            this.mAvailableBytesAboveRequired = availableBytes - j5;
                        }
                    }
                } catch (IllegalStateException e) {
                    onInProgressRecordingInternalError(recordingRecord2, ((OutputStorage) Preconditions.checkNotNull(this.mOutputStorage)).getAvailableBytes() < this.mRequiredFreeStorageBytes ? 3 : 1, e);
                }
            } else {
                Logger.d(TAG, String.format("Reach file size limit %d > %d", new Object[]{Long.valueOf(size), Long.valueOf(this.mFileSizeLimitInBytes)}));
                onInProgressRecordingInternalError(recordingRecord2, 2, (Throwable) null);
            }
        } else {
            throw new AssertionError("Video data comes before the track is added to MediaMuxer.");
        }
    }

    /* access modifiers changed from: package-private */
    public void writeAudioData(EncodedData encodedData, RecordingRecord recordingRecord) {
        RecordingRecord recordingRecord2 = recordingRecord;
        long size = this.mRecordingBytes + encodedData.size();
        long j = this.mFileSizeLimitInBytes;
        if (j == 0 || size <= j) {
            long presentationTimeUs = encodedData.getPresentationTimeUs();
            if (this.mFirstRecordingAudioDataTimeUs == Long.MAX_VALUE) {
                this.mFirstRecordingAudioDataTimeUs = presentationTimeUs;
                Logger.d(TAG, String.format("First audio time: %d (%s)", new Object[]{Long.valueOf(presentationTimeUs), DebugUtils.readableUs(this.mFirstRecordingAudioDataTimeUs)}));
            } else {
                TimeUnit timeUnit = TimeUnit.MICROSECONDS;
                long j2 = this.mFirstRecordingVideoDataTimeUs;
                String str = TAG;
                long nanos = timeUnit.toNanos(presentationTimeUs - Math.min(j2, this.mFirstRecordingAudioDataTimeUs));
                Preconditions.checkState(this.mPreviousRecordingAudioDataTimeUs != Long.MAX_VALUE, "There should be a previous data for adjusting the duration.");
                long nanos2 = nanos + TimeUnit.MICROSECONDS.toNanos(presentationTimeUs - this.mPreviousRecordingAudioDataTimeUs);
                long j3 = this.mDurationLimitNs;
                if (j3 != 0 && nanos2 > j3) {
                    Logger.d(str, String.format("Audio data reaches duration limit %d > %d", new Object[]{Long.valueOf(nanos2), Long.valueOf(this.mDurationLimitNs)}));
                    onInProgressRecordingInternalError(recordingRecord2, 9, (Throwable) null);
                    return;
                }
            }
            try {
                this.mMediaMuxer.writeSampleData(this.mAudioTrackIndex.intValue(), encodedData.getByteBuffer(), encodedData.getBufferInfo());
                this.mRecordingBytes = size;
                this.mPreviousRecordingAudioDataTimeUs = presentationTimeUs;
            } catch (IllegalStateException e) {
                onInProgressRecordingInternalError(recordingRecord2, ((OutputStorage) Preconditions.checkNotNull(this.mOutputStorage)).getAvailableBytes() < this.mRequiredFreeStorageBytes ? 3 : 1, e);
            }
        } else {
            Logger.d(TAG, String.format("Reach file size limit %d > %d", new Object[]{Long.valueOf(size), Long.valueOf(this.mFileSizeLimitInBytes)}));
            onInProgressRecordingInternalError(recordingRecord2, 2, (Throwable) null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: pauseInternal */
    public void m244lambda$pause$3$androidxcameravideoRecorder(RecordingRecord recordingRecord) {
        if (this.mInProgressRecording == recordingRecord && !this.mInProgressRecordingStopping) {
            if (isAudioEnabled()) {
                this.mAudioEncoder.pause();
            }
            this.mVideoEncoder.pause();
            RecordingRecord recordingRecord2 = this.mInProgressRecording;
            recordingRecord2.updateVideoRecordEvent(VideoRecordEvent.pause(recordingRecord2.getOutputOptions(), getInProgressRecordingStats()));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: resumeInternal */
    public void m245lambda$resume$4$androidxcameravideoRecorder(RecordingRecord recordingRecord) {
        if (this.mInProgressRecording == recordingRecord && !this.mInProgressRecordingStopping) {
            if (isAudioEnabled()) {
                this.mAudioEncoder.start();
            }
            Encoder encoder = this.mVideoEncoder;
            if (encoder != null) {
                encoder.start();
                RecordingRecord recordingRecord2 = this.mInProgressRecording;
                recordingRecord2.updateVideoRecordEvent(VideoRecordEvent.resume(recordingRecord2.getOutputOptions(), getInProgressRecordingStats()));
                return;
            }
            this.mShouldSendResumeEvent = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: stopInternal */
    public void m248lambda$stop$5$androidxcameravideoRecorder(RecordingRecord recordingRecord, long j, int i, Throwable th) {
        if (this.mInProgressRecording == recordingRecord && !this.mInProgressRecordingStopping) {
            this.mInProgressRecordingStopping = true;
            this.mRecordingStopError = i;
            this.mRecordingStopErrorCause = th;
            if (isAudioEnabled()) {
                clearPendingAudioRingBuffer();
                this.mAudioEncoder.stop(j);
            }
            EncodedData encodedData = this.mPendingFirstVideoData;
            if (encodedData != null) {
                encodedData.close();
                this.mPendingFirstVideoData = null;
            }
            if (this.mSourceState != VideoOutput.SourceState.ACTIVE_NON_STREAMING) {
                this.mSourceNonStreamingTimeout = scheduleTask(new Recorder$$ExternalSyntheticLambda8(this.mVideoEncoder), this.mSequentialExecutor, 1000, TimeUnit.MILLISECONDS);
            } else {
                notifyEncoderSourceStopped(this.mVideoEncoder);
            }
            this.mVideoEncoder.stop(j);
        }
    }

    static /* synthetic */ void lambda$stopInternal$13(Encoder encoder) {
        Logger.d(TAG, "The source didn't become non-streaming before timeout. Waited 1000ms");
        if (DeviceQuirks.get(DeactivateEncoderSurfaceBeforeStopEncoderQuirk.class) != null) {
            notifyEncoderSourceStopped(encoder);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: muteInternal */
    public void m241lambda$mute$6$androidxcameravideoRecorder(RecordingRecord recordingRecord, boolean z) {
        AudioSource audioSource;
        if (recordingRecord.isMuted() != z) {
            recordingRecord.mute(z);
            if (this.mInProgressRecording == recordingRecord && !this.mInProgressRecordingStopping && (audioSource = this.mAudioSource) != null) {
                audioSource.mute(z);
            }
        }
    }

    static void notifyEncoderSourceStopped(Encoder encoder) {
        if (encoder instanceof EncoderImpl) {
            ((EncoderImpl) encoder).signalSourceStopped();
        }
    }

    private void clearPendingAudioRingBuffer() {
        while (!this.mPendingAudioRingBuffer.isEmpty()) {
            this.mPendingAudioRingBuffer.dequeue();
        }
    }

    private void reset() {
        if (this.mAudioEncoder != null) {
            Logger.d(TAG, "Releasing audio encoder.");
            this.mAudioEncoder.release();
            this.mAudioEncoder = null;
            this.mAudioOutputConfig = null;
        }
        if (this.mAudioSource != null) {
            releaseCurrentAudioSource();
        }
        setAudioState(AudioState.INITIALIZING);
        resetVideo();
    }

    private void tryReleaseVideoEncoder() {
        VideoEncoderSession videoEncoderSession = this.mVideoEncoderSessionToRelease;
        if (videoEncoderSession != null) {
            Preconditions.checkState(videoEncoderSession.getVideoEncoder() == this.mVideoEncoder);
            Logger.d(TAG, "Releasing video encoder: " + this.mVideoEncoder);
            this.mVideoEncoderSessionToRelease.terminateNow();
            this.mVideoEncoderSessionToRelease = null;
            this.mVideoEncoder = null;
            this.mVideoOutputConfig = null;
            setLatestSurface((Surface) null);
            return;
        }
        safeToCloseVideoEncoder();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        setState(androidx.camera.video.Recorder.State.CONFIGURING);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onResetVideo() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            androidx.camera.video.Recorder$State r1 = r3.mState     // Catch:{ all -> 0x0039 }
            int r1 = r1.ordinal()     // Catch:{ all -> 0x0039 }
            r2 = 0
            switch(r1) {
                case 1: goto L_0x001c;
                case 2: goto L_0x001c;
                case 3: goto L_0x0016;
                case 4: goto L_0x000e;
                case 5: goto L_0x000e;
                case 6: goto L_0x0016;
                case 7: goto L_0x0016;
                case 8: goto L_0x000e;
                default: goto L_0x000d;
            }     // Catch:{ all -> 0x0039 }
        L_0x000d:
            goto L_0x0021
        L_0x000e:
            boolean r1 = r3.isPersistentRecordingInProgress()     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x0016
            r1 = r2
            goto L_0x0022
        L_0x0016:
            androidx.camera.video.Recorder$State r1 = androidx.camera.video.Recorder.State.CONFIGURING     // Catch:{ all -> 0x0039 }
            r3.setState(r1)     // Catch:{ all -> 0x0039 }
            goto L_0x0021
        L_0x001c:
            androidx.camera.video.Recorder$State r1 = androidx.camera.video.Recorder.State.CONFIGURING     // Catch:{ all -> 0x0039 }
            r3.updateNonPendingState(r1)     // Catch:{ all -> 0x0039 }
        L_0x0021:
            r1 = 1
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            r3.mNeedsResetBeforeNextStart = r2
            if (r1 == 0) goto L_0x0038
            androidx.camera.core.SurfaceRequest r0 = r3.mLatestSurfaceRequest
            if (r0 == 0) goto L_0x0038
            boolean r0 = r0.isServiced()
            if (r0 != 0) goto L_0x0038
            androidx.camera.core.SurfaceRequest r0 = r3.mLatestSurfaceRequest
            androidx.camera.core.impl.Timebase r1 = r3.mVideoSourceTimebase
            r3.configureInternal(r0, r1, r2)
        L_0x0038:
            return
        L_0x0039:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0039 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.Recorder.onResetVideo():void");
    }

    private void resetVideo() {
        if (this.mVideoEncoder != null) {
            Logger.d(TAG, "Releasing video encoder.");
            tryReleaseVideoEncoder();
        }
        onResetVideo();
    }

    private int internalAudioStateToAudioStatsState(AudioState audioState) {
        int ordinal = audioState.ordinal();
        if (ordinal == 0 || ordinal == 2) {
            return 1;
        }
        if (ordinal == 3) {
            RecordingRecord recordingRecord = this.mInProgressRecording;
            if (recordingRecord != null && recordingRecord.isMuted()) {
                return 5;
            }
            if (this.mIsAudioSourceSilenced) {
                return 2;
            }
            return 0;
        } else if (ordinal == 4) {
            return 3;
        } else {
            if (ordinal == 5) {
                return 4;
            }
            throw new AssertionError("Invalid internal audio state: " + audioState);
        }
    }

    private StreamInfo.StreamState internalStateToStreamState(State state) {
        return (state == State.RECORDING || (state == State.STOPPING && ((DeactivateEncoderSurfaceBeforeStopEncoderQuirk) DeviceQuirks.get(DeactivateEncoderSurfaceBeforeStopEncoderQuirk.class)) == null)) ? StreamInfo.StreamState.ACTIVE : StreamInfo.StreamState.INACTIVE;
    }

    /* access modifiers changed from: package-private */
    public boolean isAudioEnabled() {
        return this.mAudioState == AudioState.ENABLED;
    }

    /* access modifiers changed from: package-private */
    public void finalizeInProgressRecording(int i, Throwable th) {
        VideoRecordEvent.Finalize finalize;
        if (this.mInProgressRecording != null) {
            MediaMuxer mediaMuxer = this.mMediaMuxer;
            if (mediaMuxer != null) {
                try {
                    mediaMuxer.stop();
                    this.mMediaMuxer.release();
                } catch (IllegalStateException e) {
                    Logger.e(TAG, "MediaMuxer failed to stop or release with error: " + e.getMessage(), e);
                    if (i == 0) {
                        i = ((OutputStorage) Preconditions.checkNotNull(this.mOutputStorage)).getAvailableBytes() < this.mRequiredFreeStorageBytes ? 3 : 1;
                    }
                }
                this.mMediaMuxer = null;
            } else if (i == 0) {
                i = 8;
            }
            this.mInProgressRecording.finalizeRecording(this.mOutputUri);
            OutputOptions outputOptions = this.mInProgressRecording.getOutputOptions();
            RecordingStats inProgressRecordingStats = getInProgressRecordingStats();
            OutputResults of = OutputResults.of(this.mOutputUri);
            RecordingRecord recordingRecord = this.mInProgressRecording;
            if (i == 0) {
                finalize = VideoRecordEvent.finalize(outputOptions, inProgressRecordingStats, of);
            } else {
                finalize = VideoRecordEvent.finalizeWithError(outputOptions, inProgressRecordingStats, of, i, th);
            }
            recordingRecord.updateVideoRecordEvent(finalize);
            RecordingRecord recordingRecord2 = this.mInProgressRecording;
            this.mInProgressRecording = null;
            this.mInProgressRecordingStopping = false;
            this.mAudioTrackIndex = null;
            this.mVideoTrackIndex = null;
            this.mEncodingFutures.clear();
            this.mOutputUri = Uri.EMPTY;
            this.mRecordingBytes = 0;
            this.mRecordingDurationNs = 0;
            this.mFirstRecordingVideoDataTimeUs = Long.MAX_VALUE;
            this.mFirstRecordingAudioDataTimeUs = Long.MAX_VALUE;
            this.mPreviousRecordingVideoDataTimeUs = Long.MAX_VALUE;
            this.mPreviousRecordingAudioDataTimeUs = Long.MAX_VALUE;
            this.mRecordingStopError = 1;
            this.mRecordingStopErrorCause = null;
            this.mAudioErrorCause = null;
            this.mAudioAmplitude = 0.0d;
            this.mOutputStorage = null;
            this.mAvailableBytesAboveRequired = Long.MAX_VALUE;
            clearPendingAudioRingBuffer();
            setInProgressTransformationInfo((SurfaceRequest.TransformationInfo) null);
            int ordinal = this.mAudioState.ordinal();
            if (ordinal != 1) {
                if (ordinal == 2 || ordinal == 3) {
                    setAudioState(AudioState.IDLING);
                    this.mAudioSource.stop();
                } else if (ordinal == 4 || ordinal == 5) {
                    setAudioState(AudioState.INITIALIZING);
                }
                onRecordingFinalized(recordingRecord2);
                return;
            }
            throw new AssertionError("Incorrectly finalize recording when audio state is IDLING");
        }
        throw new AssertionError("Attempted to finalize in-progress recording, but no recording is in progress.");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: androidx.camera.video.Recorder$RecordingRecord} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: androidx.camera.video.Recorder$RecordingRecord} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v23, resolved type: androidx.camera.video.Recorder$RecordingRecord} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: androidx.camera.video.Recorder$RecordingRecord} */
    /* JADX WARNING: type inference failed for: r2v26 */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        r7 = r6;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0068, code lost:
        if (r8.mSourceState != androidx.camera.video.VideoOutput.SourceState.INACTIVE) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006a, code lost:
        r2 = r8.mPendingRecordingRecord;
        r8.mPendingRecordingRecord = null;
        setState(androidx.camera.video.Recorder.State.CONFIGURING);
        r3 = PENDING_RECORDING_ERROR_CAUSE_SOURCE_INACTIVE;
        r6 = 0;
        r7 = 4;
        r5 = 0;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007c, code lost:
        if (r8.mEncoderNotUsePersistentInputSurface == false) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007e, code lost:
        r8.mActiveSurface = null;
        r2 = r8.mLatestSurfaceRequest;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0082, code lost:
        if (r2 == null) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0088, code lost:
        if (r2.isServiced() != false) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008b, code lost:
        r3 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008c, code lost:
        updateNonPendingState(androidx.camera.video.Recorder.State.CONFIGURING);
        r2 = null;
        r5 = r3;
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0097, code lost:
        if (r8.mVideoEncoder == null) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0099, code lost:
        r3 = null;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r9 = makePendingRecordingActiveLocked(r8.mState);
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a6, code lost:
        r2 = null;
        r3 = null;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00aa, code lost:
        r2 = null;
        r3 = null;
        r0 = false;
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ae, code lost:
        r6 = r5;
        r7 = r6;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0025, code lost:
        r3 = r2;
        r2 = r2;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onRecordingFinalized(androidx.camera.video.Recorder.RecordingRecord r9) {
        /*
            r8 = this;
            java.lang.String r0 = "Unexpected state on finalize of recording: "
            java.lang.Object r1 = r8.mLock
            monitor-enter(r1)
            androidx.camera.video.Recorder$RecordingRecord r2 = r8.mActiveRecordingRecord     // Catch:{ all -> 0x00e1 }
            if (r2 != r9) goto L_0x00d9
            androidx.camera.core.impl.StateObservable r9 = r2.getRecordingState()     // Catch:{ all -> 0x00e1 }
            r9.removeObservers()     // Catch:{ all -> 0x00e1 }
            r9 = 0
            r8.mActiveRecordingRecord = r9     // Catch:{ all -> 0x00e1 }
            androidx.camera.video.Recorder$State r2 = r8.mState     // Catch:{ all -> 0x00e1 }
            int r2 = r2.ordinal()     // Catch:{ all -> 0x00e1 }
            r3 = 1
            r4 = 0
            switch(r2) {
                case 1: goto L_0x0063;
                case 2: goto L_0x0061;
                case 3: goto L_0x004c;
                case 4: goto L_0x0028;
                case 5: goto L_0x0028;
                case 6: goto L_0x0028;
                case 7: goto L_0x0020;
                default: goto L_0x001e;
            }     // Catch:{ all -> 0x00e1 }
        L_0x001e:
            goto L_0x00aa
        L_0x0020:
            r2 = r9
            r6 = r3
            r0 = r4
            r5 = r0
            r7 = r5
        L_0x0025:
            r3 = r2
            goto L_0x00b0
        L_0x0028:
            boolean r0 = r8.mEncoderNotUsePersistentInputSurface     // Catch:{ all -> 0x00e1 }
            if (r0 == 0) goto L_0x0045
            r8.mActiveSurface = r9     // Catch:{ all -> 0x00e1 }
            androidx.camera.core.SurfaceRequest r0 = r8.mLatestSurfaceRequest     // Catch:{ all -> 0x00e1 }
            if (r0 == 0) goto L_0x0039
            boolean r0 = r0.isServiced()     // Catch:{ all -> 0x00e1 }
            if (r0 != 0) goto L_0x0039
            goto L_0x003a
        L_0x0039:
            r3 = r4
        L_0x003a:
            androidx.camera.video.Recorder$State r0 = androidx.camera.video.Recorder.State.CONFIGURING     // Catch:{ all -> 0x00e1 }
            r8.setState(r0)     // Catch:{ all -> 0x00e1 }
            r2 = r9
            r5 = r3
            r0 = r4
            r6 = r0
        L_0x0043:
            r7 = r6
            goto L_0x0025
        L_0x0045:
            androidx.camera.video.Recorder$State r0 = androidx.camera.video.Recorder.State.IDLING     // Catch:{ all -> 0x00e1 }
            r8.setState(r0)     // Catch:{ all -> 0x00e1 }
            goto L_0x00aa
        L_0x004c:
            java.lang.AssertionError r9 = new java.lang.AssertionError     // Catch:{ all -> 0x00e1 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e1 }
            r2.<init>(r0)     // Catch:{ all -> 0x00e1 }
            androidx.camera.video.Recorder$State r0 = r8.mState     // Catch:{ all -> 0x00e1 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x00e1 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00e1 }
            r9.<init>(r0)     // Catch:{ all -> 0x00e1 }
            throw r9     // Catch:{ all -> 0x00e1 }
        L_0x0061:
            r0 = r3
            goto L_0x0064
        L_0x0063:
            r0 = r4
        L_0x0064:
            androidx.camera.video.VideoOutput$SourceState r2 = r8.mSourceState     // Catch:{ all -> 0x00e1 }
            androidx.camera.video.VideoOutput$SourceState r5 = androidx.camera.video.VideoOutput.SourceState.INACTIVE     // Catch:{ all -> 0x00e1 }
            if (r2 != r5) goto L_0x007a
            androidx.camera.video.Recorder$RecordingRecord r2 = r8.mPendingRecordingRecord     // Catch:{ all -> 0x00e1 }
            r8.mPendingRecordingRecord = r9     // Catch:{ all -> 0x00e1 }
            androidx.camera.video.Recorder$State r3 = androidx.camera.video.Recorder.State.CONFIGURING     // Catch:{ all -> 0x00e1 }
            r8.setState(r3)     // Catch:{ all -> 0x00e1 }
            java.lang.Exception r3 = PENDING_RECORDING_ERROR_CAUSE_SOURCE_INACTIVE     // Catch:{ all -> 0x00e1 }
            r5 = 4
            r6 = r4
            r7 = r5
            r5 = r6
            goto L_0x00b0
        L_0x007a:
            boolean r2 = r8.mEncoderNotUsePersistentInputSurface     // Catch:{ all -> 0x00e1 }
            if (r2 == 0) goto L_0x0095
            r8.mActiveSurface = r9     // Catch:{ all -> 0x00e1 }
            androidx.camera.core.SurfaceRequest r2 = r8.mLatestSurfaceRequest     // Catch:{ all -> 0x00e1 }
            if (r2 == 0) goto L_0x008b
            boolean r2 = r2.isServiced()     // Catch:{ all -> 0x00e1 }
            if (r2 != 0) goto L_0x008b
            goto L_0x008c
        L_0x008b:
            r3 = r4
        L_0x008c:
            androidx.camera.video.Recorder$State r2 = androidx.camera.video.Recorder.State.CONFIGURING     // Catch:{ all -> 0x00e1 }
            r8.updateNonPendingState(r2)     // Catch:{ all -> 0x00e1 }
            r2 = r9
            r5 = r3
            r6 = r4
            goto L_0x0043
        L_0x0095:
            androidx.camera.video.internal.encoder.Encoder r2 = r8.mVideoEncoder     // Catch:{ all -> 0x00e1 }
            if (r2 == 0) goto L_0x00a6
            androidx.camera.video.Recorder$State r2 = r8.mState     // Catch:{ all -> 0x00e1 }
            androidx.camera.video.Recorder$RecordingRecord r2 = r8.makePendingRecordingActiveLocked(r2)     // Catch:{ all -> 0x00e1 }
            r3 = r9
            r5 = r4
            r6 = r5
            r7 = r6
            r9 = r2
            r2 = r3
            goto L_0x00b0
        L_0x00a6:
            r2 = r9
            r3 = r2
            r5 = r4
            goto L_0x00ae
        L_0x00aa:
            r2 = r9
            r3 = r2
            r0 = r4
            r5 = r0
        L_0x00ae:
            r6 = r5
            r7 = r6
        L_0x00b0:
            monitor-exit(r1)     // Catch:{ all -> 0x00e1 }
            if (r5 == 0) goto L_0x00bb
            androidx.camera.core.SurfaceRequest r9 = r8.mLatestSurfaceRequest
            androidx.camera.core.impl.Timebase r0 = r8.mVideoSourceTimebase
            r8.configureInternal(r9, r0, r4)
            goto L_0x00d8
        L_0x00bb:
            if (r6 == 0) goto L_0x00c1
            r8.reset()
            goto L_0x00d8
        L_0x00c1:
            if (r9 == 0) goto L_0x00d3
            boolean r1 = r8.mEncoderNotUsePersistentInputSurface
            if (r1 != 0) goto L_0x00cb
            r8.startRecording(r9, r0)
            goto L_0x00d8
        L_0x00cb:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            java.lang.String r0 = "Attempt to start a pending recording while the Recorder is waiting for a new surface request."
            r9.<init>(r0)
            throw r9
        L_0x00d3:
            if (r2 == 0) goto L_0x00d8
            r8.finalizePendingRecording(r2, r7, r3)
        L_0x00d8:
            return
        L_0x00d9:
            java.lang.AssertionError r9 = new java.lang.AssertionError     // Catch:{ all -> 0x00e1 }
            java.lang.String r0 = "Active recording did not match finalized recording on finalize."
            r9.<init>(r0)     // Catch:{ all -> 0x00e1 }
            throw r9     // Catch:{ all -> 0x00e1 }
        L_0x00e1:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00e1 }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.Recorder.onRecordingFinalized(androidx.camera.video.Recorder$RecordingRecord):void");
    }

    /* access modifiers changed from: package-private */
    public void onInProgressRecordingInternalError(RecordingRecord recordingRecord, int i, Throwable th) {
        boolean z;
        if (recordingRecord == this.mInProgressRecording) {
            synchronized (this.mLock) {
                z = false;
                switch (this.mState.ordinal()) {
                    case 0:
                    case 3:
                    case 8:
                        throw new AssertionError("In-progress recording error occurred while in unexpected state: " + this.mState);
                    case 1:
                    case 2:
                    case 6:
                    case 7:
                        break;
                    case 4:
                    case 5:
                        setState(State.STOPPING);
                        z = true;
                        break;
                }
                if (recordingRecord != this.mActiveRecordingRecord) {
                    throw new AssertionError("Internal error occurred for recording but it is not the active recording.");
                }
            }
            if (z) {
                m248lambda$stop$5$androidxcameravideoRecorder(recordingRecord, -1, i, th);
                return;
            }
            return;
        }
        throw new AssertionError("Internal error occurred on recording that is not the current in-progress recording.");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: androidx.camera.video.Recorder$RecordingRecord} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: androidx.camera.video.Recorder$RecordingRecord} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v15, resolved type: androidx.camera.video.Recorder$RecordingRecord} */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void tryServicePendingRecording() {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            androidx.camera.video.Recorder$State r1 = r7.mState     // Catch:{ all -> 0x0054 }
            int r1 = r1.ordinal()     // Catch:{ all -> 0x0054 }
            r2 = 1
            r3 = 0
            r4 = 0
            if (r1 == r2) goto L_0x0015
            r5 = 2
            if (r1 == r5) goto L_0x0016
            r5 = r3
            r1 = r4
        L_0x0013:
            r2 = r1
            goto L_0x0047
        L_0x0015:
            r2 = r3
        L_0x0016:
            androidx.camera.video.Recorder$RecordingRecord r1 = r7.mActiveRecordingRecord     // Catch:{ all -> 0x0054 }
            if (r1 != 0) goto L_0x0043
            boolean r1 = r7.mNeedsResetBeforeNextStart     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x001f
            goto L_0x0043
        L_0x001f:
            androidx.camera.video.VideoOutput$SourceState r1 = r7.mSourceState     // Catch:{ all -> 0x0054 }
            androidx.camera.video.VideoOutput$SourceState r5 = androidx.camera.video.VideoOutput.SourceState.INACTIVE     // Catch:{ all -> 0x0054 }
            if (r1 != r5) goto L_0x0033
            androidx.camera.video.Recorder$RecordingRecord r1 = r7.mPendingRecordingRecord     // Catch:{ all -> 0x0054 }
            r7.mPendingRecordingRecord = r4     // Catch:{ all -> 0x0054 }
            r7.restoreNonPendingState()     // Catch:{ all -> 0x0054 }
            java.lang.Exception r3 = PENDING_RECORDING_ERROR_CAUSE_SOURCE_INACTIVE     // Catch:{ all -> 0x0054 }
            r5 = 4
            r6 = r3
            r3 = r2
            r2 = r6
            goto L_0x0047
        L_0x0033:
            androidx.camera.video.internal.encoder.Encoder r1 = r7.mVideoEncoder     // Catch:{ all -> 0x0054 }
            if (r1 == 0) goto L_0x0043
            androidx.camera.video.Recorder$State r1 = r7.mState     // Catch:{ all -> 0x0054 }
            androidx.camera.video.Recorder$RecordingRecord r1 = r7.makePendingRecordingActiveLocked(r1)     // Catch:{ all -> 0x0054 }
            r5 = r3
            r3 = r2
            r2 = r4
            r4 = r1
            r1 = r2
            goto L_0x0047
        L_0x0043:
            r5 = r3
            r1 = r4
            r3 = r2
            goto L_0x0013
        L_0x0047:
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            if (r4 == 0) goto L_0x004e
            r7.startRecording(r4, r3)
            goto L_0x0053
        L_0x004e:
            if (r1 == 0) goto L_0x0053
            r7.finalizePendingRecording(r1, r5, r2)
        L_0x0053:
            return
        L_0x0054:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.Recorder.tryServicePendingRecording():void");
    }

    private RecordingRecord makePendingRecordingActiveLocked(State state) {
        boolean z;
        if (state == State.PENDING_PAUSED) {
            z = true;
        } else if (state == State.PENDING_RECORDING) {
            z = false;
        } else {
            throw new AssertionError("makePendingRecordingActiveLocked() can only be called from a pending state.");
        }
        if (this.mActiveRecordingRecord == null) {
            RecordingRecord recordingRecord = this.mPendingRecordingRecord;
            if (recordingRecord != null) {
                this.mActiveRecordingRecord = recordingRecord;
                recordingRecord.getRecordingState().addObserver(CameraXExecutors.directExecutor(), new Observable.Observer<Boolean>() {
                    public void onNewData(Boolean bool) {
                        Recorder.this.mIsRecording.setState(bool);
                    }

                    public void onError(Throwable th) {
                        Recorder.this.mIsRecording.setError(th);
                    }
                });
                this.mPendingRecordingRecord = null;
                if (z) {
                    setState(State.PAUSED);
                } else {
                    setState(State.RECORDING);
                }
                return recordingRecord;
            }
            throw new AssertionError("Pending recording should exist when in a PENDING state.");
        }
        throw new AssertionError("Cannot make pending recording active because another recording is already active.");
    }

    private void startRecording(RecordingRecord recordingRecord, boolean z) {
        startInternal(recordingRecord);
        if (z) {
            m244lambda$pause$3$androidxcameravideoRecorder(recordingRecord);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateInProgressStatusEvent() {
        RecordingRecord recordingRecord = this.mInProgressRecording;
        if (recordingRecord != null) {
            recordingRecord.updateVideoRecordEvent(VideoRecordEvent.status(recordingRecord.getOutputOptions(), getInProgressRecordingStats()));
        }
    }

    /* access modifiers changed from: package-private */
    public RecordingStats getInProgressRecordingStats() {
        return RecordingStats.of(this.mRecordingDurationNs, this.mRecordingBytes, AudioStats.of(internalAudioStateToAudioStatsState(this.mAudioState), this.mAudioErrorCause, this.mAudioAmplitude));
    }

    /* access modifiers changed from: package-private */
    public <T> T getObservableData(StateObservable<T> stateObservable) {
        try {
            return stateObservable.fetchData().get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isAudioSupported() {
        return ((MediaSpec) getObservableData(this.mMediaSpec)).getAudioSpec().getChannelCount() != 0;
    }

    /* access modifiers changed from: package-private */
    public void setState(State state) {
        if (this.mState != state) {
            Logger.d(TAG, "Transitioning Recorder internal state: " + this.mState + " --> " + state);
            Set<State> set = PENDING_STATES;
            StreamInfo.StreamState streamState = null;
            if (set.contains(state)) {
                if (!set.contains(this.mState)) {
                    if (VALID_NON_PENDING_STATES_WHILE_PENDING.contains(this.mState)) {
                        State state2 = this.mState;
                        this.mNonPendingState = state2;
                        streamState = internalStateToStreamState(state2);
                    } else {
                        throw new AssertionError("Invalid state transition. Should not be transitioning to a PENDING state from state " + this.mState);
                    }
                }
            } else if (this.mNonPendingState != null) {
                this.mNonPendingState = null;
            }
            this.mState = state;
            if (streamState == null) {
                streamState = internalStateToStreamState(state);
            }
            this.mStreamInfo.setState(StreamInfo.of(this.mStreamId, streamState, this.mInProgressTransformationInfo));
            return;
        }
        throw new AssertionError("Attempted to transition to state " + state + ", but Recorder is already in state " + state);
    }

    /* access modifiers changed from: package-private */
    public void setLatestSurface(Surface surface) {
        int i;
        if (this.mLatestSurface != surface) {
            this.mLatestSurface = surface;
            synchronized (this.mLock) {
                if (surface != null) {
                    try {
                        i = surface.hashCode();
                    } catch (Throwable th) {
                        throw th;
                    }
                } else {
                    i = 0;
                }
                setStreamId(i);
            }
        }
    }

    private void setStreamId(int i) {
        if (this.mStreamId != i) {
            Logger.d(TAG, "Transitioning streamId: " + this.mStreamId + " --> " + i);
            this.mStreamId = i;
            this.mStreamInfo.setState(StreamInfo.of(i, internalStateToStreamState(this.mState), this.mInProgressTransformationInfo));
        }
    }

    /* access modifiers changed from: package-private */
    public void setInProgressTransformationInfo(SurfaceRequest.TransformationInfo transformationInfo) {
        Logger.d(TAG, "Update stream transformation info: " + transformationInfo);
        this.mInProgressTransformationInfo = transformationInfo;
        synchronized (this.mLock) {
            this.mStreamInfo.setState(StreamInfo.of(this.mStreamId, internalStateToStreamState(this.mState), transformationInfo));
        }
    }

    private void updateNonPendingState(State state) {
        if (!PENDING_STATES.contains(this.mState)) {
            throw new AssertionError("Can only updated non-pending state from a pending state, but state is " + this.mState);
        } else if (!VALID_NON_PENDING_STATES_WHILE_PENDING.contains(state)) {
            throw new AssertionError("Invalid state transition. State is not a valid non-pending state while in a pending state: " + state);
        } else if (this.mNonPendingState != state) {
            this.mNonPendingState = state;
            this.mStreamInfo.setState(StreamInfo.of(this.mStreamId, internalStateToStreamState(state), this.mInProgressTransformationInfo));
        }
    }

    private void restoreNonPendingState() {
        if (PENDING_STATES.contains(this.mState)) {
            setState(this.mNonPendingState);
            return;
        }
        throw new AssertionError("Cannot restore non-pending state when in state " + this.mState);
    }

    /* access modifiers changed from: package-private */
    public void setAudioState(AudioState audioState) {
        Logger.d(TAG, "Transitioning audio state: " + this.mAudioState + " --> " + audioState);
        this.mAudioState = audioState;
    }

    /* access modifiers changed from: private */
    public static ScheduledFuture<?> scheduleTask(Runnable runnable, Executor executor, long j, TimeUnit timeUnit) {
        return CameraXExecutors.mainThreadExecutor().schedule(new Recorder$$ExternalSyntheticLambda5(executor, runnable), j, timeUnit);
    }

    private static int supportedMuxerFormatOrDefaultFrom(VideoValidatedEncoderProfilesProxy videoValidatedEncoderProfilesProxy, int i) {
        if (videoValidatedEncoderProfilesProxy != null) {
            int recommendedFileFormat = videoValidatedEncoderProfilesProxy.getRecommendedFileFormat();
            if (recommendedFileFormat == 1) {
                return 2;
            }
            if (recommendedFileFormat == 2) {
                return 0;
            }
            if (recommendedFileFormat != 9) {
                return i;
            }
            return 1;
        }
        return i;
    }

    public static VideoCapabilities getVideoCapabilities(CameraInfo cameraInfo) {
        return getVideoCapabilities(cameraInfo, 0);
    }

    public static VideoCapabilities getVideoCapabilities(CameraInfo cameraInfo, int i) {
        return new RecorderVideoCapabilities(i, (CameraInfoInternal) cameraInfo, VideoEncoderInfoImpl.FINDER);
    }

    static abstract class RecordingRecord implements AutoCloseable {
        private final AtomicReference<AudioSourceSupplier> mAudioSourceSupplier = new AtomicReference<>((Object) null);
        private final CloseGuardHelper mCloseGuard = CloseGuardHelper.create();
        private final AtomicBoolean mInitialized = new AtomicBoolean(false);
        private final AtomicReference<MediaMuxerSupplier> mMediaMuxerSupplier = new AtomicReference<>((Object) null);
        private final AtomicBoolean mMuted = new AtomicBoolean(false);
        private final AtomicReference<Consumer<Uri>> mRecordingFinalizer = new AtomicReference<>(new Recorder$RecordingRecord$$ExternalSyntheticLambda6());
        private final MutableStateObservable<Boolean> mRecordingState = MutableStateObservable.withInitialState(false);

        private interface AudioSourceSupplier {
            AudioSource get(AudioSettings audioSettings, Executor executor) throws AudioSourceAccessException;
        }

        private interface MediaMuxerSupplier {
            MediaMuxer get(int i, Consumer<Uri> consumer) throws IOException;
        }

        static /* synthetic */ void lambda$new$0(Uri uri) {
        }

        /* access modifiers changed from: package-private */
        public abstract Executor getCallbackExecutor();

        /* access modifiers changed from: package-private */
        public abstract Consumer<VideoRecordEvent> getEventListener();

        /* access modifiers changed from: package-private */
        public abstract OutputOptions getOutputOptions();

        /* access modifiers changed from: package-private */
        public abstract long getRecordingId();

        /* access modifiers changed from: package-private */
        public abstract boolean hasAudioEnabled();

        /* access modifiers changed from: package-private */
        public abstract boolean isPersistent();

        RecordingRecord() {
        }

        static RecordingRecord from(PendingRecording pendingRecording, long j) {
            AutoValue_Recorder_RecordingRecord autoValue_Recorder_RecordingRecord = new AutoValue_Recorder_RecordingRecord(pendingRecording.getOutputOptions(), pendingRecording.getListenerExecutor(), pendingRecording.getEventListener(), pendingRecording.isAudioEnabled(), pendingRecording.isPersistent(), j);
            autoValue_Recorder_RecordingRecord.mute(pendingRecording.isAudioInitialMuted());
            return autoValue_Recorder_RecordingRecord;
        }

        /* access modifiers changed from: package-private */
        public void initializeRecording(final Context context) throws IOException {
            if (!this.mInitialized.getAndSet(true)) {
                OutputOptions outputOptions = getOutputOptions();
                boolean z = outputOptions instanceof FileDescriptorOutputOptions;
                Consumer consumer = null;
                ParcelFileDescriptor dup = z ? ((FileDescriptorOutputOptions) outputOptions).getParcelFileDescriptor().dup() : null;
                this.mCloseGuard.open("finalizeRecording");
                this.mMediaMuxerSupplier.set(new Recorder$RecordingRecord$$ExternalSyntheticLambda0(outputOptions, dup));
                if (hasAudioEnabled()) {
                    if (Build.VERSION.SDK_INT >= 31) {
                        this.mAudioSourceSupplier.set(new AudioSourceSupplier() {
                            public AudioSource get(AudioSettings audioSettings, Executor executor) throws AudioSourceAccessException {
                                return new AudioSource(audioSettings, executor, context);
                            }
                        });
                    } else {
                        this.mAudioSourceSupplier.set(new AudioSourceSupplier() {
                            public AudioSource get(AudioSettings audioSettings, Executor executor) throws AudioSourceAccessException {
                                return new AudioSource(audioSettings, executor, (Context) null);
                            }
                        });
                    }
                }
                if (outputOptions instanceof MediaStoreOutputOptions) {
                    MediaStoreOutputOptions mediaStoreOutputOptions = (MediaStoreOutputOptions) outputOptions;
                    if (Build.VERSION.SDK_INT >= 29) {
                        consumer = new Recorder$RecordingRecord$$ExternalSyntheticLambda1(mediaStoreOutputOptions);
                    } else {
                        consumer = new Recorder$RecordingRecord$$ExternalSyntheticLambda2(mediaStoreOutputOptions, context);
                    }
                } else if (z) {
                    consumer = new Recorder$RecordingRecord$$ExternalSyntheticLambda3(dup);
                }
                if (consumer != null) {
                    this.mRecordingFinalizer.set(consumer);
                    return;
                }
                return;
            }
            throw new AssertionError("Recording " + this + " has already been initialized");
        }

        static /* synthetic */ MediaMuxer lambda$initializeRecording$1(OutputOptions outputOptions, ParcelFileDescriptor parcelFileDescriptor, int i, Consumer consumer) throws IOException {
            MediaMuxer mediaMuxer;
            Uri uri = Uri.EMPTY;
            if (outputOptions instanceof FileOutputOptions) {
                File file = ((FileOutputOptions) outputOptions).getFile();
                if (!OutputUtil.createParentFolder(file)) {
                    Logger.w(Recorder.TAG, "Failed to create folder for " + file.getAbsolutePath());
                }
                mediaMuxer = new MediaMuxer(file.getAbsolutePath(), i);
                uri = Uri.fromFile(file);
            } else if (outputOptions instanceof FileDescriptorOutputOptions) {
                mediaMuxer = Api26Impl.createMediaMuxer(parcelFileDescriptor.getFileDescriptor(), i);
            } else if (outputOptions instanceof MediaStoreOutputOptions) {
                MediaStoreOutputOptions mediaStoreOutputOptions = (MediaStoreOutputOptions) outputOptions;
                ContentValues contentValues = new ContentValues(mediaStoreOutputOptions.getContentValues());
                if (Build.VERSION.SDK_INT >= 29) {
                    contentValues.put("is_pending", 1);
                }
                try {
                    uri = mediaStoreOutputOptions.getContentResolver().insert(mediaStoreOutputOptions.getCollectionUri(), contentValues);
                    if (uri != null) {
                        ParcelFileDescriptor openFileDescriptor = mediaStoreOutputOptions.getContentResolver().openFileDescriptor(uri, "rw");
                        mediaMuxer = Api26Impl.createMediaMuxer(openFileDescriptor.getFileDescriptor(), i);
                        openFileDescriptor.close();
                    } else {
                        throw new IOException("Unable to create MediaStore entry.");
                    }
                } catch (RuntimeException e) {
                    throw new IOException("Unable to create MediaStore entry by " + e, e);
                }
            } else {
                throw new AssertionError("Invalid output options type: " + outputOptions.getClass().getSimpleName());
            }
            consumer.accept(uri);
            return mediaMuxer;
        }

        static /* synthetic */ void lambda$initializeRecording$2(MediaStoreOutputOptions mediaStoreOutputOptions, Uri uri) {
            if (!uri.equals(Uri.EMPTY)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("is_pending", 0);
                mediaStoreOutputOptions.getContentResolver().update(uri, contentValues, (String) null, (String[]) null);
            }
        }

        static /* synthetic */ void lambda$initializeRecording$4(MediaStoreOutputOptions mediaStoreOutputOptions, Context context, Uri uri) {
            if (!uri.equals(Uri.EMPTY)) {
                String absolutePathFromUri = OutputUtil.getAbsolutePathFromUri(mediaStoreOutputOptions.getContentResolver(), uri, Recorder.MEDIA_COLUMN);
                if (absolutePathFromUri != null) {
                    MediaScannerConnection.scanFile(context, new String[]{absolutePathFromUri}, (String[]) null, new Recorder$RecordingRecord$$ExternalSyntheticLambda4());
                } else {
                    Logger.d(Recorder.TAG, "Skipping media scanner scan. Unable to retrieve file path from URI: " + uri);
                }
            }
        }

        static /* synthetic */ void lambda$initializeRecording$3(String str, Uri uri) {
            if (uri == null) {
                Logger.e(Recorder.TAG, String.format("File scanning operation failed [path: %s]", new Object[]{str}));
            } else {
                Logger.d(Recorder.TAG, String.format("File scan completed successfully [path: %s, URI: %s]", new Object[]{str, uri}));
            }
        }

        static /* synthetic */ void lambda$initializeRecording$5(ParcelFileDescriptor parcelFileDescriptor, Uri uri) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException e) {
                Logger.e(Recorder.TAG, "Failed to close dup'd ParcelFileDescriptor", e);
            }
        }

        /* access modifiers changed from: package-private */
        public void updateVideoRecordEvent(VideoRecordEvent videoRecordEvent) {
            if (Objects.equals(videoRecordEvent.getOutputOptions(), getOutputOptions())) {
                String str = "Sending VideoRecordEvent " + videoRecordEvent.getClass().getSimpleName();
                if (videoRecordEvent instanceof VideoRecordEvent.Finalize) {
                    VideoRecordEvent.Finalize finalize = (VideoRecordEvent.Finalize) videoRecordEvent;
                    if (finalize.hasError()) {
                        str = str + String.format(" [error: %s]", new Object[]{VideoRecordEvent.Finalize.errorToString(finalize.getError())});
                    }
                }
                Logger.d(Recorder.TAG, str);
                updateRecordingState(videoRecordEvent);
                if (getCallbackExecutor() != null && getEventListener() != null) {
                    try {
                        getCallbackExecutor().execute(new Recorder$RecordingRecord$$ExternalSyntheticLambda5(this, videoRecordEvent));
                    } catch (RejectedExecutionException e) {
                        Logger.e(Recorder.TAG, "The callback executor is invalid.", e);
                    }
                }
            } else {
                throw new AssertionError("Attempted to update event listener with event from incorrect recording [Recording: " + videoRecordEvent.getOutputOptions() + ", Expected: " + getOutputOptions() + "]");
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$updateVideoRecordEvent$6$androidx-camera-video-Recorder$RecordingRecord  reason: not valid java name */
        public /* synthetic */ void m252lambda$updateVideoRecordEvent$6$androidxcameravideoRecorder$RecordingRecord(VideoRecordEvent videoRecordEvent) {
            getEventListener().accept(videoRecordEvent);
        }

        private void updateRecordingState(VideoRecordEvent videoRecordEvent) {
            if ((videoRecordEvent instanceof VideoRecordEvent.Start) || (videoRecordEvent instanceof VideoRecordEvent.Resume)) {
                this.mRecordingState.setState(true);
            } else if ((videoRecordEvent instanceof VideoRecordEvent.Pause) || (videoRecordEvent instanceof VideoRecordEvent.Finalize)) {
                this.mRecordingState.setState(false);
            }
        }

        /* access modifiers changed from: package-private */
        public StateObservable<Boolean> getRecordingState() {
            return this.mRecordingState;
        }

        /* access modifiers changed from: package-private */
        public AudioSource performOneTimeAudioSourceCreation(AudioSettings audioSettings, Executor executor) throws AudioSourceAccessException {
            if (hasAudioEnabled()) {
                AudioSourceSupplier andSet = this.mAudioSourceSupplier.getAndSet((Object) null);
                if (andSet != null) {
                    return andSet.get(audioSettings, executor);
                }
                throw new AssertionError("One-time audio source creation has already occurred for recording " + this);
            }
            throw new AssertionError("Recording does not have audio enabled. Unable to create audio source for recording " + this);
        }

        /* access modifiers changed from: package-private */
        public MediaMuxer performOneTimeMediaMuxerCreation(int i, Consumer<Uri> consumer) throws IOException {
            if (this.mInitialized.get()) {
                MediaMuxerSupplier andSet = this.mMediaMuxerSupplier.getAndSet((Object) null);
                if (andSet != null) {
                    try {
                        return andSet.get(i, consumer);
                    } catch (RuntimeException e) {
                        throw new IOException("Failed to create MediaMuxer by " + e, e);
                    }
                } else {
                    throw new AssertionError("One-time media muxer creation has already occurred for recording " + this);
                }
            } else {
                throw new AssertionError("Recording " + this + " has not been initialized");
            }
        }

        /* access modifiers changed from: package-private */
        public void finalizeRecording(Uri uri) {
            if (this.mInitialized.get()) {
                finalizeRecordingInternal(this.mRecordingFinalizer.getAndSet((Object) null), uri);
            }
        }

        /* access modifiers changed from: package-private */
        public void mute(boolean z) {
            this.mMuted.set(z);
        }

        /* access modifiers changed from: package-private */
        public boolean isMuted() {
            return this.mMuted.get();
        }

        public void close() {
            finalizeRecording(Uri.EMPTY);
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            try {
                this.mCloseGuard.warnIfOpen();
                Consumer andSet = this.mRecordingFinalizer.getAndSet((Object) null);
                if (andSet != null) {
                    finalizeRecordingInternal(andSet, Uri.EMPTY);
                }
            } finally {
                super.finalize();
            }
        }

        private void finalizeRecordingInternal(Consumer<Uri> consumer, Uri uri) {
            if (consumer != null) {
                this.mCloseGuard.close();
                consumer.accept(uri);
                return;
            }
            throw new AssertionError("Recording " + this + " has already been finalized");
        }
    }

    public static final class Builder {
        private EncoderFactory mAudioEncoderFactory = Recorder.DEFAULT_ENCODER_FACTORY;
        private Executor mExecutor = null;
        private final MediaSpec.Builder mMediaSpecBuilder = MediaSpec.builder();
        private OutputStorage.Factory mOutputStorageFactory = Recorder.OUTPUT_STORAGE_FACTORY_DEFAULT;
        private long mRequiredFreeStorageBytes = -1;
        private int mVideoCapabilitiesSource = 0;
        private EncoderFactory mVideoEncoderFactory = Recorder.DEFAULT_ENCODER_FACTORY;

        public Builder setExecutor(Executor executor) {
            Preconditions.checkNotNull(executor, "The specified executor can't be null.");
            this.mExecutor = executor;
            return this;
        }

        public Builder setQualitySelector(QualitySelector qualitySelector) {
            Preconditions.checkNotNull(qualitySelector, "The specified quality selector can't be null.");
            this.mMediaSpecBuilder.configureVideo(new Recorder$Builder$$ExternalSyntheticLambda0(qualitySelector));
            return this;
        }

        public Builder setVideoCapabilitiesSource(int i) {
            boolean z = true;
            if (!(i == 0 || i == 1)) {
                z = false;
            }
            Preconditions.checkArgument(z, "Not a supported video capabilities source: " + i);
            this.mVideoCapabilitiesSource = i;
            return this;
        }

        public Builder setTargetVideoEncodingBitRate(int i) {
            if (i > 0) {
                this.mMediaSpecBuilder.configureVideo(new Recorder$Builder$$ExternalSyntheticLambda2(i));
                return this;
            }
            throw new IllegalArgumentException("The requested target bitrate " + i + " is not supported. Target bitrate must be greater than 0.");
        }

        public Builder setAspectRatio(int i) {
            this.mMediaSpecBuilder.configureVideo(new Recorder$Builder$$ExternalSyntheticLambda3(i));
            return this;
        }

        public Builder setRequiredFreeStorageBytes(long j) {
            Preconditions.checkArgument(j > 0);
            this.mRequiredFreeStorageBytes = j;
            return this;
        }

        public Builder setAudioSource(int i) {
            this.mMediaSpecBuilder.configureAudio(new Recorder$Builder$$ExternalSyntheticLambda1(i));
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setVideoEncoderFactory(EncoderFactory encoderFactory) {
            this.mVideoEncoderFactory = encoderFactory;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAudioEncoderFactory(EncoderFactory encoderFactory) {
            this.mAudioEncoderFactory = encoderFactory;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setOutputStorageFactory(OutputStorage.Factory factory) {
            this.mOutputStorageFactory = factory;
            return this;
        }

        public Recorder build() {
            return new Recorder(this.mExecutor, this.mMediaSpecBuilder.build(), this.mVideoCapabilitiesSource, this.mVideoEncoderFactory, this.mAudioEncoderFactory, this.mOutputStorageFactory, this.mRequiredFreeStorageBytes);
        }
    }
}
