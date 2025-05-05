package androidx.camera.video.internal.encoder;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Range;
import android.view.Surface;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.Timebase;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.video.internal.BufferProvider;
import androidx.camera.video.internal.DebugUtils;
import androidx.camera.video.internal.compat.quirk.AudioEncoderIgnoresInputTimestampQuirk;
import androidx.camera.video.internal.compat.quirk.CameraUseInconsistentTimebaseQuirk;
import androidx.camera.video.internal.compat.quirk.CodecStuckOnFlushQuirk;
import androidx.camera.video.internal.compat.quirk.DeviceQuirks;
import androidx.camera.video.internal.compat.quirk.EncoderNotUsePersistentInputSurfaceQuirk;
import androidx.camera.video.internal.compat.quirk.PrematureEndOfStreamVideoQuirk;
import androidx.camera.video.internal.compat.quirk.SignalEosOutputBufferNotComeQuirk;
import androidx.camera.video.internal.compat.quirk.StopCodecAfterSurfaceRemovalCrashMediaServerQuirk;
import androidx.camera.video.internal.compat.quirk.VideoEncoderSuspendDoesNotIncludeSuspendTimeQuirk;
import androidx.camera.video.internal.encoder.Encoder;
import androidx.camera.video.internal.utils.CodecUtil;
import androidx.camera.video.internal.workaround.VideoTimebaseConverter;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import androidx.media3.effect.DebugTraceUtil;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class EncoderImpl implements Encoder {
    private static final boolean DEBUG = false;
    private static final long NO_LIMIT_LONG = Long.MAX_VALUE;
    private static final Range<Long> NO_RANGE = Range.create(Long.MAX_VALUE, Long.MAX_VALUE);
    private static final long SIGNAL_EOS_TIMEOUT_MS = 1000;
    private static final long STOP_TIMEOUT_MS = 1000;
    private final Queue<CallbackToFutureAdapter.Completer<InputBuffer>> mAcquisitionQueue = new ArrayDeque();
    final Deque<Range<Long>> mActivePauseResumeTimeRanges = new ArrayDeque();
    final Set<EncodedDataImpl> mEncodedDataSet = new HashSet();
    EncoderCallback mEncoderCallback = EncoderCallback.EMPTY;
    Executor mEncoderCallbackExecutor = CameraXExecutors.directExecutor();
    final Executor mEncoderExecutor;
    private final EncoderInfo mEncoderInfo;
    final Encoder.EncoderInput mEncoderInput;
    final Queue<Integer> mFreeInputBufferIndexQueue = new ArrayDeque();
    private final Set<InputBuffer> mInputBufferSet = new HashSet();
    final Timebase mInputTimebase;
    private boolean mIsFlushedAfterEndOfStream = false;
    final boolean mIsVideoEncoder;
    Long mLastDataStopTimestamp = null;
    final Object mLock = new Object();
    final MediaCodec mMediaCodec;
    private MediaCodecCallback mMediaCodecCallback = null;
    boolean mMediaCodecEosSignalled = false;
    /* access modifiers changed from: private */
    public final MediaFormat mMediaFormat;
    boolean mPendingCodecStop = false;
    private final CallbackToFutureAdapter.Completer<Void> mReleasedCompleter;
    private final ListenableFuture<Void> mReleasedFuture;
    /* access modifiers changed from: private */
    public Future<?> mSignalEosTimeoutFuture;
    private boolean mSourceStoppedSignalled = false;
    Range<Long> mStartStopTimeRangeUs = NO_RANGE;
    InternalState mState;
    Future<?> mStopTimeoutFuture = null;
    final String mTag;
    final TimeProvider mTimeProvider = new SystemTimeProvider();
    long mTotalPausedDurationUs = 0;

    enum InternalState {
        CONFIGURED,
        STARTED,
        PAUSED,
        STOPPING,
        PENDING_START,
        PENDING_START_PAUSED,
        PENDING_RELEASE,
        ERROR,
        RELEASED
    }

    public EncoderImpl(Executor executor, EncoderConfig encoderConfig) throws InvalidConfigException {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(encoderConfig);
        MediaCodec createCodec = CodecUtil.createCodec(encoderConfig);
        this.mMediaCodec = createCodec;
        MediaCodecInfo codecInfo = createCodec.getCodecInfo();
        this.mEncoderExecutor = CameraXExecutors.newSequentialExecutor(executor);
        MediaFormat mediaFormat = encoderConfig.toMediaFormat();
        this.mMediaFormat = mediaFormat;
        Timebase inputTimebase = encoderConfig.getInputTimebase();
        this.mInputTimebase = inputTimebase;
        if (encoderConfig instanceof AudioEncoderConfig) {
            this.mTag = DebugTraceUtil.COMPONENT_AUDIO_ENCODER;
            this.mIsVideoEncoder = false;
            this.mEncoderInput = new ByteBufferInput();
            this.mEncoderInfo = new AudioEncoderInfoImpl(codecInfo, encoderConfig.getMimeType());
        } else if (encoderConfig instanceof VideoEncoderConfig) {
            this.mTag = DebugTraceUtil.COMPONENT_VIDEO_ENCODER;
            this.mIsVideoEncoder = true;
            this.mEncoderInput = new SurfaceInput();
            VideoEncoderInfoImpl videoEncoderInfoImpl = new VideoEncoderInfoImpl(codecInfo, encoderConfig.getMimeType());
            clampVideoBitrateIfNotSupported(videoEncoderInfoImpl, mediaFormat);
            this.mEncoderInfo = videoEncoderInfoImpl;
        } else {
            throw new InvalidConfigException("Unknown encoder config type");
        }
        Logger.d(this.mTag, "mInputTimebase = " + inputTimebase);
        Logger.d(this.mTag, "mMediaFormat = " + mediaFormat);
        try {
            reset();
            AtomicReference atomicReference = new AtomicReference();
            this.mReleasedFuture = Futures.nonCancellationPropagating(CallbackToFutureAdapter.getFuture(new EncoderImpl$$ExternalSyntheticLambda7(atomicReference)));
            this.mReleasedCompleter = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
            setState(InternalState.CONFIGURED);
        } catch (MediaCodec.CodecException e) {
            throw new InvalidConfigException((Throwable) e);
        }
    }

    private void clampVideoBitrateIfNotSupported(VideoEncoderInfo videoEncoderInfo, MediaFormat mediaFormat) {
        Preconditions.checkState(this.mIsVideoEncoder);
        if (mediaFormat.containsKey("bitrate")) {
            int integer = mediaFormat.getInteger("bitrate");
            int intValue = videoEncoderInfo.getSupportedBitrateRange().clamp(Integer.valueOf(integer)).intValue();
            if (integer != intValue) {
                mediaFormat.setInteger("bitrate", intValue);
                Logger.d(this.mTag, "updated bitrate from " + integer + " to " + intValue);
            }
        }
    }

    private void reset() {
        this.mStartStopTimeRangeUs = NO_RANGE;
        this.mTotalPausedDurationUs = 0;
        this.mActivePauseResumeTimeRanges.clear();
        this.mFreeInputBufferIndexQueue.clear();
        for (CallbackToFutureAdapter.Completer cancelled : this.mAcquisitionQueue) {
            cancelled.setCancelled();
        }
        this.mAcquisitionQueue.clear();
        this.mMediaCodec.reset();
        this.mIsFlushedAfterEndOfStream = false;
        this.mSourceStoppedSignalled = false;
        this.mMediaCodecEosSignalled = false;
        this.mPendingCodecStop = false;
        Future<?> future = this.mStopTimeoutFuture;
        if (future != null) {
            future.cancel(true);
            this.mStopTimeoutFuture = null;
        }
        Future<?> future2 = this.mSignalEosTimeoutFuture;
        if (future2 != null) {
            future2.cancel(false);
            this.mSignalEosTimeoutFuture = null;
        }
        MediaCodecCallback mediaCodecCallback = this.mMediaCodecCallback;
        if (mediaCodecCallback != null) {
            mediaCodecCallback.stop();
        }
        MediaCodecCallback mediaCodecCallback2 = new MediaCodecCallback();
        this.mMediaCodecCallback = mediaCodecCallback2;
        this.mMediaCodec.setCallback(mediaCodecCallback2);
        this.mMediaCodec.configure(this.mMediaFormat, (Surface) null, (MediaCrypto) null, 1);
        Encoder.EncoderInput encoderInput = this.mEncoderInput;
        if (encoderInput instanceof SurfaceInput) {
            ((SurfaceInput) encoderInput).resetSurface();
        }
    }

    public Encoder.EncoderInput getInput() {
        return this.mEncoderInput;
    }

    public EncoderInfo getEncoderInfo() {
        return this.mEncoderInfo;
    }

    public int getConfiguredBitrate() {
        if (this.mMediaFormat.containsKey("bitrate")) {
            return this.mMediaFormat.getInteger("bitrate");
        }
        return 0;
    }

    public void start() {
        this.mEncoderExecutor.execute(new EncoderImpl$$ExternalSyntheticLambda1(this, generatePresentationTimeUs()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$start$1$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    public /* synthetic */ void m288lambda$start$1$androidxcameravideointernalencoderEncoderImpl(long j) {
        switch (this.mState.ordinal()) {
            case 0:
                this.mLastDataStopTimestamp = null;
                Logger.d(this.mTag, "Start on " + DebugUtils.readableUs(j));
                try {
                    if (this.mIsFlushedAfterEndOfStream) {
                        reset();
                    }
                    this.mStartStopTimeRangeUs = Range.create(Long.valueOf(j), Long.MAX_VALUE);
                    this.mMediaCodec.start();
                    Encoder.EncoderInput encoderInput = this.mEncoderInput;
                    if (encoderInput instanceof ByteBufferInput) {
                        ((ByteBufferInput) encoderInput).setActive(true);
                    }
                    setState(InternalState.STARTED);
                    return;
                } catch (MediaCodec.CodecException e) {
                    handleEncodeError(e);
                    return;
                }
            case 1:
            case 4:
            case 7:
                return;
            case 2:
                this.mLastDataStopTimestamp = null;
                Range removeLast = this.mActivePauseResumeTimeRanges.removeLast();
                Preconditions.checkState(removeLast != null && ((Long) removeLast.getUpper()).longValue() == Long.MAX_VALUE, "There should be a \"pause\" before \"resume\"");
                long longValue = ((Long) removeLast.getLower()).longValue();
                this.mActivePauseResumeTimeRanges.addLast(Range.create(Long.valueOf(longValue), Long.valueOf(j)));
                Logger.d(this.mTag, "Resume on " + DebugUtils.readableUs(j) + "\nPaused duration = " + DebugUtils.readableUs(j - longValue));
                if ((this.mIsVideoEncoder || DeviceQuirks.get(AudioEncoderIgnoresInputTimestampQuirk.class) == null) && (!this.mIsVideoEncoder || DeviceQuirks.get(VideoEncoderSuspendDoesNotIncludeSuspendTimeQuirk.class) == null)) {
                    setMediaCodecPaused(false);
                    Encoder.EncoderInput encoderInput2 = this.mEncoderInput;
                    if (encoderInput2 instanceof ByteBufferInput) {
                        ((ByteBufferInput) encoderInput2).setActive(true);
                    }
                }
                if (this.mIsVideoEncoder) {
                    requestKeyFrameToMediaCodec();
                }
                setState(InternalState.STARTED);
                return;
            case 3:
            case 5:
                setState(InternalState.PENDING_START);
                return;
            case 6:
            case 8:
                throw new IllegalStateException("Encoder is released");
            default:
                throw new IllegalStateException("Unknown state: " + this.mState);
        }
    }

    public void stop() {
        stop(-1);
    }

    public void stop(long j) {
        this.mEncoderExecutor.execute(new EncoderImpl$$ExternalSyntheticLambda9(this, j, generatePresentationTimeUs()));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ab  */
    /* renamed from: lambda$stop$4$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void m291lambda$stop$4$androidxcameravideointernalencoderEncoderImpl(long r6, long r8) {
        /*
            r5 = this;
            androidx.camera.video.internal.encoder.EncoderImpl$InternalState r0 = r5.mState
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L_0x00bb;
                case 1: goto L_0x002f;
                case 2: goto L_0x002f;
                case 3: goto L_0x00bb;
                case 4: goto L_0x0028;
                case 5: goto L_0x0028;
                case 6: goto L_0x0020;
                case 7: goto L_0x00bb;
                case 8: goto L_0x0020;
                default: goto L_0x0009;
            }
        L_0x0009:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Unknown state: "
            r7.<init>(r8)
            androidx.camera.video.internal.encoder.EncoderImpl$InternalState r8 = r5.mState
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x0020:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "Encoder is released"
            r6.<init>(r7)
            throw r6
        L_0x0028:
            androidx.camera.video.internal.encoder.EncoderImpl$InternalState r6 = androidx.camera.video.internal.encoder.EncoderImpl.InternalState.CONFIGURED
            r5.setState(r6)
            goto L_0x00bb
        L_0x002f:
            androidx.camera.video.internal.encoder.EncoderImpl$InternalState r0 = r5.mState
            androidx.camera.video.internal.encoder.EncoderImpl$InternalState r1 = androidx.camera.video.internal.encoder.EncoderImpl.InternalState.STOPPING
            r5.setState(r1)
            android.util.Range<java.lang.Long> r1 = r5.mStartStopTimeRangeUs
            java.lang.Comparable r1 = r1.getLower()
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x00b3
            r3 = -1
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x0052
            goto L_0x005d
        L_0x0052:
            int r3 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x005e
            java.lang.String r6 = r5.mTag
            java.lang.String r7 = "The expected stop time is less than the start time. Use current time as stop time."
            androidx.camera.core.Logger.w(r6, r7)
        L_0x005d:
            r6 = r8
        L_0x005e:
            int r8 = (r6 > r1 ? 1 : (r6 == r1 ? 0 : -1))
            if (r8 < 0) goto L_0x00ab
            java.lang.Long r8 = java.lang.Long.valueOf(r1)
            java.lang.Long r9 = java.lang.Long.valueOf(r6)
            android.util.Range r8 = android.util.Range.create(r8, r9)
            r5.mStartStopTimeRangeUs = r8
            java.lang.String r8 = r5.mTag
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r1 = "Stop on "
            r9.<init>(r1)
            java.lang.String r6 = androidx.camera.video.internal.DebugUtils.readableUs(r6)
            java.lang.StringBuilder r6 = r9.append(r6)
            java.lang.String r6 = r6.toString()
            androidx.camera.core.Logger.d(r8, r6)
            androidx.camera.video.internal.encoder.EncoderImpl$InternalState r6 = androidx.camera.video.internal.encoder.EncoderImpl.InternalState.PAUSED
            if (r0 != r6) goto L_0x0094
            java.lang.Long r6 = r5.mLastDataStopTimestamp
            if (r6 == 0) goto L_0x0094
            r5.signalCodecStop()
            goto L_0x00bb
        L_0x0094:
            r6 = 1
            r5.mPendingCodecStop = r6
            java.util.concurrent.ScheduledExecutorService r6 = androidx.camera.core.impl.utils.executor.CameraXExecutors.mainThreadExecutor()
            androidx.camera.video.internal.encoder.EncoderImpl$$ExternalSyntheticLambda17 r7 = new androidx.camera.video.internal.encoder.EncoderImpl$$ExternalSyntheticLambda17
            r7.<init>(r5)
            r8 = 1000(0x3e8, double:4.94E-321)
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.util.concurrent.ScheduledFuture r6 = r6.schedule(r7, r8, r0)
            r5.mStopTimeoutFuture = r6
            goto L_0x00bb
        L_0x00ab:
            java.lang.AssertionError r6 = new java.lang.AssertionError
            java.lang.String r7 = "The start time should be before the stop time."
            r6.<init>(r7)
            throw r6
        L_0x00b3:
            java.lang.AssertionError r6 = new java.lang.AssertionError
            java.lang.String r7 = "There should be a \"start\" before \"stop\""
            r6.<init>(r7)
            throw r6
        L_0x00bb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.encoder.EncoderImpl.m291lambda$stop$4$androidxcameravideointernalencoderEncoderImpl(long, long):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$stop$3$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    public /* synthetic */ void m290lambda$stop$3$androidxcameravideointernalencoderEncoderImpl() {
        this.mEncoderExecutor.execute(new EncoderImpl$$ExternalSyntheticLambda15(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$stop$2$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    public /* synthetic */ void m289lambda$stop$2$androidxcameravideointernalencoderEncoderImpl() {
        if (this.mPendingCodecStop) {
            Logger.w(this.mTag, "The data didn't reach the expected timestamp before timeout, stop the codec.");
            this.mLastDataStopTimestamp = null;
            signalCodecStop();
            this.mPendingCodecStop = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void signalCodecStop() {
        Logger.d(this.mTag, "signalCodecStop");
        Encoder.EncoderInput encoderInput = this.mEncoderInput;
        if (encoderInput instanceof ByteBufferInput) {
            ((ByteBufferInput) encoderInput).setActive(false);
            ArrayList arrayList = new ArrayList();
            for (InputBuffer terminationFuture : this.mInputBufferSet) {
                arrayList.add(terminationFuture.getTerminationFuture());
            }
            Futures.successfulAsList(arrayList).addListener(new EncoderImpl$$ExternalSyntheticLambda11(this), this.mEncoderExecutor);
        } else if (encoderInput instanceof SurfaceInput) {
            try {
                addSignalEosTimeoutIfNeeded();
                this.mMediaCodec.signalEndOfInputStream();
                this.mMediaCodecEosSignalled = true;
            } catch (MediaCodec.CodecException e) {
                handleEncodeError(e);
            }
        }
    }

    public void pause() {
        this.mEncoderExecutor.execute(new EncoderImpl$$ExternalSyntheticLambda0(this, generatePresentationTimeUs()));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$pause$5$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    public /* synthetic */ void m284lambda$pause$5$androidxcameravideointernalencoderEncoderImpl(long j) {
        switch (this.mState.ordinal()) {
            case 0:
            case 2:
            case 3:
            case 5:
            case 7:
                return;
            case 1:
                Logger.d(this.mTag, "Pause on " + DebugUtils.readableUs(j));
                this.mActivePauseResumeTimeRanges.addLast(Range.create(Long.valueOf(j), Long.MAX_VALUE));
                setState(InternalState.PAUSED);
                return;
            case 4:
                setState(InternalState.PENDING_START_PAUSED);
                return;
            case 6:
            case 8:
                throw new IllegalStateException("Encoder is released");
            default:
                throw new IllegalStateException("Unknown state: " + this.mState);
        }
    }

    public void release() {
        this.mEncoderExecutor.execute(new EncoderImpl$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$6$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    public /* synthetic */ void m285lambda$release$6$androidxcameravideointernalencoderEncoderImpl() {
        switch (this.mState.ordinal()) {
            case 0:
            case 1:
            case 2:
            case 7:
                releaseInternal();
                return;
            case 3:
            case 4:
            case 5:
                setState(InternalState.PENDING_RELEASE);
                return;
            case 6:
            case 8:
                return;
            default:
                throw new IllegalStateException("Unknown state: " + this.mState);
        }
    }

    public ListenableFuture<Void> getReleasedFuture() {
        return this.mReleasedFuture;
    }

    public void signalSourceStopped() {
        this.mEncoderExecutor.execute(new EncoderImpl$$ExternalSyntheticLambda16(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$signalSourceStopped$7$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    public /* synthetic */ void m287lambda$signalSourceStopped$7$androidxcameravideointernalencoderEncoderImpl() {
        this.mSourceStoppedSignalled = true;
        if (this.mIsFlushedAfterEndOfStream) {
            this.mMediaCodec.stop();
            reset();
        }
    }

    private void releaseInternal() {
        if (this.mIsFlushedAfterEndOfStream) {
            this.mMediaCodec.stop();
            this.mIsFlushedAfterEndOfStream = false;
        }
        this.mMediaCodec.release();
        Encoder.EncoderInput encoderInput = this.mEncoderInput;
        if (encoderInput instanceof SurfaceInput) {
            ((SurfaceInput) encoderInput).releaseSurface();
        }
        setState(InternalState.RELEASED);
        this.mReleasedCompleter.set(null);
    }

    public void setEncoderCallback(EncoderCallback encoderCallback, Executor executor) {
        synchronized (this.mLock) {
            this.mEncoderCallback = encoderCallback;
            this.mEncoderCallbackExecutor = executor;
        }
    }

    public void requestKeyFrame() {
        this.mEncoderExecutor.execute(new EncoderImpl$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$requestKeyFrame$8$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    public /* synthetic */ void m286lambda$requestKeyFrame$8$androidxcameravideointernalencoderEncoderImpl() {
        int ordinal = this.mState.ordinal();
        if (ordinal == 1) {
            requestKeyFrameToMediaCodec();
        } else if (ordinal == 6 || ordinal == 8) {
            throw new IllegalStateException("Encoder is released");
        }
    }

    private void setState(InternalState internalState) {
        if (this.mState != internalState) {
            Logger.d(this.mTag, "Transitioning encoder internal state: " + this.mState + " --> " + internalState);
            this.mState = internalState;
        }
    }

    /* access modifiers changed from: package-private */
    public void setMediaCodecPaused(boolean z) {
        Bundle bundle = new Bundle();
        bundle.putInt("drop-input-frames", z ? 1 : 0);
        this.mMediaCodec.setParameters(bundle);
    }

    /* access modifiers changed from: package-private */
    public void requestKeyFrameToMediaCodec() {
        Bundle bundle = new Bundle();
        bundle.putInt("request-sync", 0);
        this.mMediaCodec.setParameters(bundle);
    }

    private void addSignalEosTimeoutIfNeeded() {
        if (DeviceQuirks.get(SignalEosOutputBufferNotComeQuirk.class) != null) {
            MediaCodecCallback mediaCodecCallback = this.mMediaCodecCallback;
            Executor executor = this.mEncoderExecutor;
            Future<?> future = this.mSignalEosTimeoutFuture;
            if (future != null) {
                future.cancel(false);
            }
            this.mSignalEosTimeoutFuture = CameraXExecutors.mainThreadExecutor().schedule(new EncoderImpl$$ExternalSyntheticLambda3(executor, mediaCodecCallback), 1000, TimeUnit.MILLISECONDS);
        }
    }

    static /* synthetic */ void lambda$addSignalEosTimeoutIfNeeded$9(Executor executor, MediaCodecCallback mediaCodecCallback) {
        Objects.requireNonNull(mediaCodecCallback);
        executor.execute(new EncoderImpl$$ExternalSyntheticLambda6(mediaCodecCallback));
    }

    /* access modifiers changed from: private */
    public void signalEndOfInputStream() {
        Futures.addCallback(acquireInputBuffer(), new FutureCallback<InputBuffer>() {
            public void onSuccess(InputBuffer inputBuffer) {
                inputBuffer.setPresentationTimeUs(EncoderImpl.this.generatePresentationTimeUs());
                inputBuffer.setEndOfStream(true);
                inputBuffer.submit();
                Futures.addCallback(inputBuffer.getTerminationFuture(), new FutureCallback<Void>() {
                    public void onSuccess(Void voidR) {
                    }

                    public void onFailure(Throwable th) {
                        if (th instanceof MediaCodec.CodecException) {
                            EncoderImpl.this.handleEncodeError((MediaCodec.CodecException) th);
                        } else {
                            EncoderImpl.this.handleEncodeError(0, th.getMessage(), th);
                        }
                    }
                }, EncoderImpl.this.mEncoderExecutor);
            }

            public void onFailure(Throwable th) {
                EncoderImpl.this.handleEncodeError(0, "Unable to acquire InputBuffer.", th);
            }
        }, this.mEncoderExecutor);
    }

    /* access modifiers changed from: package-private */
    public void handleEncodeError(MediaCodec.CodecException codecException) {
        handleEncodeError(1, codecException.getMessage(), codecException);
    }

    /* access modifiers changed from: package-private */
    public void handleEncodeError(int i, String str, Throwable th) {
        switch (this.mState.ordinal()) {
            case 0:
                m282lambda$handleEncodeError$10$androidxcameravideointernalencoderEncoderImpl(i, str, th);
                reset();
                return;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                setState(InternalState.ERROR);
                stopMediaCodec(new EncoderImpl$$ExternalSyntheticLambda2(this, i, str, th));
                return;
            case 7:
                Logger.w(this.mTag, "Get more than one error: " + str + "(" + i + ")", th);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: notifyError */
    public void m282lambda$handleEncodeError$10$androidxcameravideointernalencoderEncoderImpl(int i, String str, Throwable th) {
        EncoderCallback encoderCallback;
        Executor executor;
        synchronized (this.mLock) {
            encoderCallback = this.mEncoderCallback;
            executor = this.mEncoderCallbackExecutor;
        }
        try {
            executor.execute(new EncoderImpl$$ExternalSyntheticLambda8(encoderCallback, i, str, th));
        } catch (RejectedExecutionException e) {
            Logger.e(this.mTag, "Unable to post to the supplied executor.", e);
        }
    }

    /* access modifiers changed from: package-private */
    public void stopMediaCodec(Runnable runnable) {
        Logger.d(this.mTag, "stopMediaCodec");
        ArrayList arrayList = new ArrayList();
        for (EncodedDataImpl closedFuture : this.mEncodedDataSet) {
            arrayList.add(closedFuture.getClosedFuture());
        }
        for (InputBuffer terminationFuture : this.mInputBufferSet) {
            arrayList.add(terminationFuture.getTerminationFuture());
        }
        if (!arrayList.isEmpty()) {
            Logger.d(this.mTag, "Waiting for resources to return. encoded data = " + this.mEncodedDataSet.size() + ", input buffers = " + this.mInputBufferSet.size());
        }
        Futures.successfulAsList(arrayList).addListener(new EncoderImpl$$ExternalSyntheticLambda12(this, arrayList, runnable), this.mEncoderExecutor);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$stopMediaCodec$12$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    public /* synthetic */ void m292lambda$stopMediaCodec$12$androidxcameravideointernalencoderEncoderImpl(List list, Runnable runnable) {
        if (this.mState != InternalState.ERROR) {
            if (!list.isEmpty()) {
                Logger.d(this.mTag, "encoded data and input buffers are returned");
            }
            if (!(this.mEncoderInput instanceof SurfaceInput) || this.mSourceStoppedSignalled || hasStopCodecAfterSurfaceRemovalCrashMediaServerQuirk()) {
                this.mMediaCodec.stop();
            } else {
                this.mMediaCodec.flush();
                this.mIsFlushedAfterEndOfStream = true;
            }
        }
        if (runnable != null) {
            runnable.run();
        }
        handleStopped();
    }

    /* access modifiers changed from: package-private */
    public void handleStopped() {
        if (this.mState == InternalState.PENDING_RELEASE) {
            releaseInternal();
            return;
        }
        InternalState internalState = this.mState;
        if (!this.mIsFlushedAfterEndOfStream) {
            reset();
        }
        setState(InternalState.CONFIGURED);
        if (internalState == InternalState.PENDING_START || internalState == InternalState.PENDING_START_PAUSED) {
            start();
            if (internalState == InternalState.PENDING_START_PAUSED) {
                pause();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateTotalPausedDuration(long j) {
        while (!this.mActivePauseResumeTimeRanges.isEmpty()) {
            Range first = this.mActivePauseResumeTimeRanges.getFirst();
            if (j > ((Long) first.getUpper()).longValue()) {
                this.mActivePauseResumeTimeRanges.removeFirst();
                this.mTotalPausedDurationUs += ((Long) first.getUpper()).longValue() - ((Long) first.getLower()).longValue();
                Logger.d(this.mTag, "Total paused duration = " + DebugUtils.readableUs(this.mTotalPausedDurationUs));
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public long getAdjustedTimeUs(MediaCodec.BufferInfo bufferInfo) {
        if (this.mTotalPausedDurationUs > 0) {
            return bufferInfo.presentationTimeUs - this.mTotalPausedDurationUs;
        }
        return bufferInfo.presentationTimeUs;
    }

    /* access modifiers changed from: package-private */
    public boolean isInPauseRange(long j) {
        for (Range next : this.mActivePauseResumeTimeRanges) {
            if (!next.contains(Long.valueOf(j))) {
                if (j < ((Long) next.getLower()).longValue()) {
                    break;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public ListenableFuture<InputBuffer> acquireInputBuffer() {
        switch (this.mState.ordinal()) {
            case 0:
                return Futures.immediateFailedFuture(new IllegalStateException("Encoder is not started yet."));
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                AtomicReference atomicReference = new AtomicReference();
                ListenableFuture<InputBuffer> future = CallbackToFutureAdapter.getFuture(new EncoderImpl$$ExternalSyntheticLambda13(atomicReference));
                CallbackToFutureAdapter.Completer completer = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
                this.mAcquisitionQueue.offer(completer);
                completer.addCancellationListener(new EncoderImpl$$ExternalSyntheticLambda14(this, completer), this.mEncoderExecutor);
                matchAcquisitionsAndFreeBufferIndexes();
                return future;
            case 7:
                return Futures.immediateFailedFuture(new IllegalStateException("Encoder is in error state."));
            case 8:
                return Futures.immediateFailedFuture(new IllegalStateException("Encoder is released."));
            default:
                throw new IllegalStateException("Unknown state: " + this.mState);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$acquireInputBuffer$14$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    public /* synthetic */ void m281lambda$acquireInputBuffer$14$androidxcameravideointernalencoderEncoderImpl(CallbackToFutureAdapter.Completer completer) {
        this.mAcquisitionQueue.remove(completer);
    }

    /* access modifiers changed from: package-private */
    public void matchAcquisitionsAndFreeBufferIndexes() {
        while (!this.mAcquisitionQueue.isEmpty() && !this.mFreeInputBufferIndexQueue.isEmpty()) {
            CallbackToFutureAdapter.Completer completer = (CallbackToFutureAdapter.Completer) Objects.requireNonNull(this.mAcquisitionQueue.poll());
            try {
                InputBufferImpl inputBufferImpl = new InputBufferImpl(this.mMediaCodec, ((Integer) Objects.requireNonNull(this.mFreeInputBufferIndexQueue.poll())).intValue());
                if (completer.set(inputBufferImpl)) {
                    this.mInputBufferSet.add(inputBufferImpl);
                    inputBufferImpl.getTerminationFuture().addListener(new EncoderImpl$$ExternalSyntheticLambda10(this, inputBufferImpl), this.mEncoderExecutor);
                } else {
                    inputBufferImpl.cancel();
                }
            } catch (MediaCodec.CodecException e) {
                handleEncodeError(e);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$matchAcquisitionsAndFreeBufferIndexes$15$androidx-camera-video-internal-encoder-EncoderImpl  reason: not valid java name */
    public /* synthetic */ void m283lambda$matchAcquisitionsAndFreeBufferIndexes$15$androidxcameravideointernalencoderEncoderImpl(InputBufferImpl inputBufferImpl) {
        this.mInputBufferSet.remove(inputBufferImpl);
    }

    /* access modifiers changed from: package-private */
    public long generatePresentationTimeUs() {
        return this.mTimeProvider.uptimeUs();
    }

    static boolean isKeyFrame(MediaCodec.BufferInfo bufferInfo) {
        return (bufferInfo.flags & 1) != 0;
    }

    static boolean hasEndOfStreamFlag(MediaCodec.BufferInfo bufferInfo) {
        return (bufferInfo.flags & 4) != 0;
    }

    private boolean hasStopCodecAfterSurfaceRemovalCrashMediaServerQuirk() {
        return DeviceQuirks.get(StopCodecAfterSurfaceRemovalCrashMediaServerQuirk.class) != null;
    }

    class MediaCodecCallback extends MediaCodec.Callback {
        private boolean mHasEndData = false;
        private boolean mHasFirstData = false;
        private boolean mHasSendStartCallback = false;
        private boolean mIsFirstVideoOutput;
        private boolean mIsKeyFrameRequired = false;
        private boolean mIsOutputBufferInPauseState = false;
        private long mLastPresentationTimeUs = 0;
        private long mLastSentAdjustedTimeUs = 0;
        private boolean mReachStopTimeAsEos = true;
        private boolean mStopped = false;
        private final VideoTimebaseConverter mVideoTimestampConverter;

        static /* synthetic */ MediaFormat lambda$onOutputFormatChanged$5(MediaFormat mediaFormat) {
            return mediaFormat;
        }

        MediaCodecCallback() {
            this.mIsFirstVideoOutput = EncoderImpl.this.mIsVideoEncoder;
            if (EncoderImpl.this.mIsVideoEncoder) {
                this.mVideoTimestampConverter = new VideoTimebaseConverter(EncoderImpl.this.mTimeProvider, EncoderImpl.this.mInputTimebase, (CameraUseInconsistentTimebaseQuirk) DeviceQuirks.get(CameraUseInconsistentTimebaseQuirk.class));
            } else {
                this.mVideoTimestampConverter = null;
            }
            CodecStuckOnFlushQuirk codecStuckOnFlushQuirk = (CodecStuckOnFlushQuirk) DeviceQuirks.get(CodecStuckOnFlushQuirk.class);
            if (codecStuckOnFlushQuirk != null && codecStuckOnFlushQuirk.isProblematicMimeType(EncoderImpl.this.mMediaFormat.getString("mime"))) {
                this.mReachStopTimeAsEos = false;
            }
        }

        public void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
            EncoderImpl.this.mEncoderExecutor.execute(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda8(this, i));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onInputBufferAvailable$0$androidx-camera-video-internal-encoder-EncoderImpl$MediaCodecCallback  reason: not valid java name */
        public /* synthetic */ void m302lambda$onInputBufferAvailable$0$androidxcameravideointernalencoderEncoderImpl$MediaCodecCallback(int i) {
            if (this.mStopped) {
                Logger.w(EncoderImpl.this.mTag, "Receives input frame after codec is reset.");
                return;
            }
            switch (EncoderImpl.this.mState.ordinal()) {
                case 0:
                case 7:
                case 8:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    EncoderImpl.this.mFreeInputBufferIndexQueue.offer(Integer.valueOf(i));
                    EncoderImpl.this.matchAcquisitionsAndFreeBufferIndexes();
                    return;
                default:
                    throw new IllegalStateException("Unknown state: " + EncoderImpl.this.mState);
            }
        }

        public void onOutputBufferAvailable(MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo) {
            EncoderImpl.this.mEncoderExecutor.execute(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda7(this, bufferInfo, mediaCodec, i));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onOutputBufferAvailable$1$androidx-camera-video-internal-encoder-EncoderImpl$MediaCodecCallback  reason: not valid java name */
        public /* synthetic */ void m303lambda$onOutputBufferAvailable$1$androidxcameravideointernalencoderEncoderImpl$MediaCodecCallback(MediaCodec.BufferInfo bufferInfo, MediaCodec mediaCodec, int i) {
            EncoderCallback encoderCallback;
            Executor executor;
            if (this.mStopped) {
                Logger.w(EncoderImpl.this.mTag, "Receives frame after codec is reset.");
                return;
            }
            switch (EncoderImpl.this.mState.ordinal()) {
                case 0:
                case 7:
                case 8:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    synchronized (EncoderImpl.this.mLock) {
                        encoderCallback = EncoderImpl.this.mEncoderCallback;
                        executor = EncoderImpl.this.mEncoderCallbackExecutor;
                    }
                    if (!this.mHasSendStartCallback) {
                        this.mHasSendStartCallback = true;
                        try {
                            Objects.requireNonNull(encoderCallback);
                            executor.execute(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda10(encoderCallback));
                        } catch (RejectedExecutionException e) {
                            Logger.e(EncoderImpl.this.mTag, "Unable to post to the supplied executor.", e);
                        }
                    }
                    if (checkBufferInfo(bufferInfo)) {
                        if (!this.mHasFirstData) {
                            this.mHasFirstData = true;
                            Logger.d(EncoderImpl.this.mTag, "data timestampUs = " + bufferInfo.presentationTimeUs + ", data timebase = " + EncoderImpl.this.mInputTimebase + ", current system uptimeMs = " + SystemClock.uptimeMillis() + ", current system realtimeMs = " + SystemClock.elapsedRealtime());
                        }
                        MediaCodec.BufferInfo resolveOutputBufferInfo = resolveOutputBufferInfo(bufferInfo);
                        this.mLastSentAdjustedTimeUs = resolveOutputBufferInfo.presentationTimeUs;
                        try {
                            sendEncodedData(new EncodedDataImpl(mediaCodec, i, resolveOutputBufferInfo), encoderCallback, executor);
                        } catch (MediaCodec.CodecException e2) {
                            EncoderImpl.this.handleEncodeError(e2);
                            return;
                        }
                    } else {
                        try {
                            EncoderImpl.this.mMediaCodec.releaseOutputBuffer(i, false);
                        } catch (MediaCodec.CodecException e3) {
                            EncoderImpl.this.handleEncodeError(e3);
                            return;
                        }
                    }
                    if (!this.mHasEndData && isEndOfStream(bufferInfo)) {
                        reachEndData();
                    }
                    if (this.mIsFirstVideoOutput) {
                        this.mIsFirstVideoOutput = false;
                        return;
                    }
                    return;
                default:
                    throw new IllegalStateException("Unknown state: " + EncoderImpl.this.mState);
            }
        }

        /* access modifiers changed from: package-private */
        public void reachEndData() {
            EncoderCallback encoderCallback;
            Executor executor;
            if (!this.mHasEndData) {
                this.mHasEndData = true;
                if (EncoderImpl.this.mSignalEosTimeoutFuture != null) {
                    EncoderImpl.this.mSignalEosTimeoutFuture.cancel(false);
                    Future unused = EncoderImpl.this.mSignalEosTimeoutFuture = null;
                }
                synchronized (EncoderImpl.this.mLock) {
                    encoderCallback = EncoderImpl.this.mEncoderCallback;
                    executor = EncoderImpl.this.mEncoderCallbackExecutor;
                }
                EncoderImpl.this.stopMediaCodec(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda2(this, executor, encoderCallback));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$reachEndData$2$androidx-camera-video-internal-encoder-EncoderImpl$MediaCodecCallback  reason: not valid java name */
        public /* synthetic */ void m305lambda$reachEndData$2$androidxcameravideointernalencoderEncoderImpl$MediaCodecCallback(Executor executor, EncoderCallback encoderCallback) {
            if (EncoderImpl.this.mState != InternalState.ERROR) {
                try {
                    Objects.requireNonNull(encoderCallback);
                    executor.execute(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda4(encoderCallback));
                } catch (RejectedExecutionException e) {
                    Logger.e(EncoderImpl.this.mTag, "Unable to post to the supplied executor.", e);
                }
            }
        }

        private MediaCodec.BufferInfo resolveOutputBufferInfo(MediaCodec.BufferInfo bufferInfo) {
            long adjustedTimeUs = EncoderImpl.this.getAdjustedTimeUs(bufferInfo);
            if (bufferInfo.presentationTimeUs == adjustedTimeUs) {
                return bufferInfo;
            }
            Preconditions.checkState(adjustedTimeUs > this.mLastSentAdjustedTimeUs);
            MediaCodec.BufferInfo bufferInfo2 = new MediaCodec.BufferInfo();
            bufferInfo2.set(bufferInfo.offset, bufferInfo.size, adjustedTimeUs, bufferInfo.flags);
            return bufferInfo2;
        }

        private void sendEncodedData(final EncodedDataImpl encodedDataImpl, EncoderCallback encoderCallback, Executor executor) {
            EncoderImpl.this.mEncodedDataSet.add(encodedDataImpl);
            Futures.addCallback(encodedDataImpl.getClosedFuture(), new FutureCallback<Void>() {
                public void onSuccess(Void voidR) {
                    EncoderImpl.this.mEncodedDataSet.remove(encodedDataImpl);
                }

                public void onFailure(Throwable th) {
                    EncoderImpl.this.mEncodedDataSet.remove(encodedDataImpl);
                    if (th instanceof MediaCodec.CodecException) {
                        EncoderImpl.this.handleEncodeError((MediaCodec.CodecException) th);
                    } else {
                        EncoderImpl.this.handleEncodeError(0, th.getMessage(), th);
                    }
                }
            }, EncoderImpl.this.mEncoderExecutor);
            try {
                executor.execute(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda9(encoderCallback, encodedDataImpl));
            } catch (RejectedExecutionException e) {
                Logger.e(EncoderImpl.this.mTag, "Unable to post to the supplied executor.", e);
                encodedDataImpl.close();
            }
        }

        private boolean checkBufferInfo(MediaCodec.BufferInfo bufferInfo) {
            if (this.mHasEndData) {
                Logger.d(EncoderImpl.this.mTag, "Drop buffer by already reach end of stream.");
                return false;
            } else if (bufferInfo.size <= 0) {
                Logger.d(EncoderImpl.this.mTag, "Drop buffer by invalid buffer size.");
                return false;
            } else if ((bufferInfo.flags & 2) != 0) {
                Logger.d(EncoderImpl.this.mTag, "Drop buffer by codec config.");
                return false;
            } else {
                VideoTimebaseConverter videoTimebaseConverter = this.mVideoTimestampConverter;
                if (videoTimebaseConverter != null) {
                    bufferInfo.presentationTimeUs = videoTimebaseConverter.convertToUptimeUs(bufferInfo.presentationTimeUs);
                }
                if (bufferInfo.presentationTimeUs <= this.mLastPresentationTimeUs) {
                    Logger.d(EncoderImpl.this.mTag, "Drop buffer by out of order buffer from MediaCodec.");
                    return false;
                }
                this.mLastPresentationTimeUs = bufferInfo.presentationTimeUs;
                if (!EncoderImpl.this.mStartStopTimeRangeUs.contains(Long.valueOf(bufferInfo.presentationTimeUs))) {
                    Logger.d(EncoderImpl.this.mTag, "Drop buffer by not in start-stop range.");
                    if (EncoderImpl.this.mPendingCodecStop && bufferInfo.presentationTimeUs >= EncoderImpl.this.mStartStopTimeRangeUs.getUpper().longValue()) {
                        if (EncoderImpl.this.mStopTimeoutFuture != null) {
                            EncoderImpl.this.mStopTimeoutFuture.cancel(true);
                        }
                        EncoderImpl.this.mLastDataStopTimestamp = Long.valueOf(bufferInfo.presentationTimeUs);
                        EncoderImpl.this.signalCodecStop();
                        EncoderImpl.this.mPendingCodecStop = false;
                    }
                    return false;
                } else if (updatePauseRangeStateAndCheckIfBufferPaused(bufferInfo)) {
                    Logger.d(EncoderImpl.this.mTag, "Drop buffer by pause.");
                    return false;
                } else if (EncoderImpl.this.getAdjustedTimeUs(bufferInfo) <= this.mLastSentAdjustedTimeUs) {
                    Logger.d(EncoderImpl.this.mTag, "Drop buffer by adjusted time is less than the last sent time.");
                    if (EncoderImpl.this.mIsVideoEncoder && EncoderImpl.isKeyFrame(bufferInfo)) {
                        this.mIsKeyFrameRequired = true;
                    }
                    return false;
                } else {
                    if (!this.mHasFirstData && !this.mIsKeyFrameRequired && EncoderImpl.this.mIsVideoEncoder) {
                        this.mIsKeyFrameRequired = true;
                    }
                    if (this.mIsKeyFrameRequired) {
                        if (!EncoderImpl.isKeyFrame(bufferInfo)) {
                            Logger.d(EncoderImpl.this.mTag, "Drop buffer by not a key frame.");
                            EncoderImpl.this.requestKeyFrameToMediaCodec();
                            return false;
                        }
                        this.mIsKeyFrameRequired = false;
                    }
                    return true;
                }
            }
        }

        private boolean isEndOfStream(MediaCodec.BufferInfo bufferInfo) {
            return (EncoderImpl.hasEndOfStreamFlag(bufferInfo) && !shouldSkipPrematureEos()) || (this.mReachStopTimeAsEos && isEosSignalledAndStopTimeReached(bufferInfo));
        }

        private boolean shouldSkipPrematureEos() {
            return this.mIsFirstVideoOutput && DeviceQuirks.get(PrematureEndOfStreamVideoQuirk.class) != null;
        }

        private boolean isEosSignalledAndStopTimeReached(MediaCodec.BufferInfo bufferInfo) {
            return EncoderImpl.this.mMediaCodecEosSignalled && bufferInfo.presentationTimeUs > EncoderImpl.this.mStartStopTimeRangeUs.getUpper().longValue();
        }

        private boolean updatePauseRangeStateAndCheckIfBufferPaused(MediaCodec.BufferInfo bufferInfo) {
            Executor executor;
            EncoderCallback encoderCallback;
            EncoderImpl.this.updateTotalPausedDuration(bufferInfo.presentationTimeUs);
            boolean isInPauseRange = EncoderImpl.this.isInPauseRange(bufferInfo.presentationTimeUs);
            boolean z = this.mIsOutputBufferInPauseState;
            if (!z && isInPauseRange) {
                Logger.d(EncoderImpl.this.mTag, "Switch to pause state");
                this.mIsOutputBufferInPauseState = true;
                synchronized (EncoderImpl.this.mLock) {
                    executor = EncoderImpl.this.mEncoderCallbackExecutor;
                    encoderCallback = EncoderImpl.this.mEncoderCallback;
                }
                Objects.requireNonNull(encoderCallback);
                executor.execute(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda0(encoderCallback));
                if (EncoderImpl.this.mState == InternalState.PAUSED && ((EncoderImpl.this.mIsVideoEncoder || DeviceQuirks.get(AudioEncoderIgnoresInputTimestampQuirk.class) == null) && (!EncoderImpl.this.mIsVideoEncoder || DeviceQuirks.get(VideoEncoderSuspendDoesNotIncludeSuspendTimeQuirk.class) == null))) {
                    if (EncoderImpl.this.mEncoderInput instanceof ByteBufferInput) {
                        ((ByteBufferInput) EncoderImpl.this.mEncoderInput).setActive(false);
                    }
                    EncoderImpl.this.setMediaCodecPaused(true);
                }
                EncoderImpl.this.mLastDataStopTimestamp = Long.valueOf(bufferInfo.presentationTimeUs);
                if (EncoderImpl.this.mPendingCodecStop) {
                    if (EncoderImpl.this.mStopTimeoutFuture != null) {
                        EncoderImpl.this.mStopTimeoutFuture.cancel(true);
                    }
                    EncoderImpl.this.signalCodecStop();
                    EncoderImpl.this.mPendingCodecStop = false;
                }
            } else if (z && !isInPauseRange) {
                Logger.d(EncoderImpl.this.mTag, "Switch to resume state");
                this.mIsOutputBufferInPauseState = false;
                if (EncoderImpl.this.mIsVideoEncoder && !EncoderImpl.isKeyFrame(bufferInfo)) {
                    this.mIsKeyFrameRequired = true;
                }
            }
            return this.mIsOutputBufferInPauseState;
        }

        public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
            EncoderImpl.this.mEncoderExecutor.execute(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda3(this, codecException));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onError$4$androidx-camera-video-internal-encoder-EncoderImpl$MediaCodecCallback  reason: not valid java name */
        public /* synthetic */ void m301lambda$onError$4$androidxcameravideointernalencoderEncoderImpl$MediaCodecCallback(MediaCodec.CodecException codecException) {
            switch (EncoderImpl.this.mState.ordinal()) {
                case 0:
                case 7:
                case 8:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    EncoderImpl.this.handleEncodeError(codecException);
                    return;
                default:
                    throw new IllegalStateException("Unknown state: " + EncoderImpl.this.mState);
            }
        }

        public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
            EncoderImpl.this.mEncoderExecutor.execute(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda1(this, mediaFormat));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$onOutputFormatChanged$7$androidx-camera-video-internal-encoder-EncoderImpl$MediaCodecCallback  reason: not valid java name */
        public /* synthetic */ void m304lambda$onOutputFormatChanged$7$androidxcameravideointernalencoderEncoderImpl$MediaCodecCallback(MediaFormat mediaFormat) {
            EncoderCallback encoderCallback;
            Executor executor;
            if (this.mStopped) {
                Logger.w(EncoderImpl.this.mTag, "Receives onOutputFormatChanged after codec is reset.");
                return;
            }
            switch (EncoderImpl.this.mState.ordinal()) {
                case 0:
                case 7:
                case 8:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    synchronized (EncoderImpl.this.mLock) {
                        encoderCallback = EncoderImpl.this.mEncoderCallback;
                        executor = EncoderImpl.this.mEncoderCallbackExecutor;
                    }
                    try {
                        executor.execute(new EncoderImpl$MediaCodecCallback$$ExternalSyntheticLambda5(encoderCallback, mediaFormat));
                        return;
                    } catch (RejectedExecutionException e) {
                        Logger.e(EncoderImpl.this.mTag, "Unable to post to the supplied executor.", e);
                        return;
                    }
                default:
                    throw new IllegalStateException("Unknown state: " + EncoderImpl.this.mState);
            }
        }

        /* access modifiers changed from: package-private */
        public void stop() {
            this.mStopped = true;
        }
    }

    class SurfaceInput implements Encoder.SurfaceInput {
        private final Object mLock = new Object();
        private final Set<Surface> mObsoleteSurfaces = new HashSet();
        private Surface mSurface;
        private Executor mSurfaceUpdateExecutor;
        private Encoder.SurfaceInput.OnSurfaceUpdateListener mSurfaceUpdateListener;

        SurfaceInput() {
        }

        public void setOnSurfaceUpdateListener(Executor executor, Encoder.SurfaceInput.OnSurfaceUpdateListener onSurfaceUpdateListener) {
            Surface surface;
            synchronized (this.mLock) {
                this.mSurfaceUpdateListener = (Encoder.SurfaceInput.OnSurfaceUpdateListener) Preconditions.checkNotNull(onSurfaceUpdateListener);
                this.mSurfaceUpdateExecutor = (Executor) Preconditions.checkNotNull(executor);
                surface = this.mSurface;
            }
            if (surface != null) {
                notifySurfaceUpdate(executor, onSurfaceUpdateListener, surface);
            }
        }

        /* access modifiers changed from: package-private */
        public void resetSurface() {
            Surface surface;
            Encoder.SurfaceInput.OnSurfaceUpdateListener onSurfaceUpdateListener;
            Executor executor;
            EncoderNotUsePersistentInputSurfaceQuirk encoderNotUsePersistentInputSurfaceQuirk = (EncoderNotUsePersistentInputSurfaceQuirk) DeviceQuirks.get(EncoderNotUsePersistentInputSurfaceQuirk.class);
            synchronized (this.mLock) {
                if (encoderNotUsePersistentInputSurfaceQuirk == null) {
                    if (this.mSurface == null) {
                        surface = Api23Impl.createPersistentInputSurface();
                        this.mSurface = surface;
                    } else {
                        surface = null;
                    }
                    Api23Impl.setInputSurface(EncoderImpl.this.mMediaCodec, this.mSurface);
                } else {
                    Surface surface2 = this.mSurface;
                    if (surface2 != null) {
                        this.mObsoleteSurfaces.add(surface2);
                    }
                    surface = EncoderImpl.this.mMediaCodec.createInputSurface();
                    this.mSurface = surface;
                }
                onSurfaceUpdateListener = this.mSurfaceUpdateListener;
                executor = this.mSurfaceUpdateExecutor;
            }
            if (surface != null && onSurfaceUpdateListener != null && executor != null) {
                notifySurfaceUpdate(executor, onSurfaceUpdateListener, surface);
            }
        }

        /* access modifiers changed from: package-private */
        public void releaseSurface() {
            Surface surface;
            HashSet<Surface> hashSet;
            synchronized (this.mLock) {
                surface = this.mSurface;
                this.mSurface = null;
                hashSet = new HashSet<>(this.mObsoleteSurfaces);
                this.mObsoleteSurfaces.clear();
            }
            if (surface != null) {
                surface.release();
            }
            for (Surface release : hashSet) {
                release.release();
            }
        }

        private void notifySurfaceUpdate(Executor executor, Encoder.SurfaceInput.OnSurfaceUpdateListener onSurfaceUpdateListener, Surface surface) {
            try {
                executor.execute(new EncoderImpl$SurfaceInput$$ExternalSyntheticLambda0(onSurfaceUpdateListener, surface));
            } catch (RejectedExecutionException e) {
                Logger.e(EncoderImpl.this.mTag, "Unable to post to the supplied executor.", e);
            }
        }
    }

    class ByteBufferInput implements Encoder.ByteBufferInput {
        private final List<ListenableFuture<InputBuffer>> mAcquisitionList = new ArrayList();
        private BufferProvider.State mBufferProviderState = BufferProvider.State.INACTIVE;
        private final Map<Observable.Observer<? super BufferProvider.State>, Executor> mStateObservers = new LinkedHashMap();

        ByteBufferInput() {
        }

        public ListenableFuture<BufferProvider.State> fetchData() {
            return CallbackToFutureAdapter.getFuture(new EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda8(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$fetchData$0$androidx-camera-video-internal-encoder-EncoderImpl$ByteBufferInput  reason: not valid java name */
        public /* synthetic */ void m298lambda$fetchData$0$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(CallbackToFutureAdapter.Completer completer) {
            completer.set(this.mBufferProviderState);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$fetchData$1$androidx-camera-video-internal-encoder-EncoderImpl$ByteBufferInput  reason: not valid java name */
        public /* synthetic */ Object m299lambda$fetchData$1$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(CallbackToFutureAdapter.Completer completer) throws Exception {
            EncoderImpl.this.mEncoderExecutor.execute(new EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda4(this, completer));
            return "fetchData";
        }

        public ListenableFuture<InputBuffer> acquireBuffer() {
            return CallbackToFutureAdapter.getFuture(new EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda3(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$acquireBuffer$5$androidx-camera-video-internal-encoder-EncoderImpl$ByteBufferInput  reason: not valid java name */
        public /* synthetic */ Object m296lambda$acquireBuffer$5$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(CallbackToFutureAdapter.Completer completer) throws Exception {
            EncoderImpl.this.mEncoderExecutor.execute(new EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda1(this, completer));
            return "acquireBuffer";
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$acquireBuffer$4$androidx-camera-video-internal-encoder-EncoderImpl$ByteBufferInput  reason: not valid java name */
        public /* synthetic */ void m295lambda$acquireBuffer$4$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(CallbackToFutureAdapter.Completer completer) {
            if (this.mBufferProviderState == BufferProvider.State.ACTIVE) {
                ListenableFuture<InputBuffer> acquireInputBuffer = EncoderImpl.this.acquireInputBuffer();
                Futures.propagate(acquireInputBuffer, completer);
                completer.addCancellationListener(new EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda5(this, acquireInputBuffer), CameraXExecutors.directExecutor());
                this.mAcquisitionList.add(acquireInputBuffer);
                acquireInputBuffer.addListener(new EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda6(this, acquireInputBuffer), EncoderImpl.this.mEncoderExecutor);
            } else if (this.mBufferProviderState == BufferProvider.State.INACTIVE) {
                completer.setException(new IllegalStateException("BufferProvider is not active."));
            } else {
                completer.setException(new IllegalStateException("Unknown state: " + this.mBufferProviderState));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$acquireBuffer$3$androidx-camera-video-internal-encoder-EncoderImpl$ByteBufferInput  reason: not valid java name */
        public /* synthetic */ void m294lambda$acquireBuffer$3$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(ListenableFuture listenableFuture) {
            this.mAcquisitionList.remove(listenableFuture);
        }

        /* access modifiers changed from: private */
        /* renamed from: cancelInputBuffer */
        public void m293lambda$acquireBuffer$2$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(ListenableFuture<InputBuffer> listenableFuture) {
            if (!listenableFuture.cancel(true)) {
                Preconditions.checkState(listenableFuture.isDone());
                try {
                    ((InputBuffer) listenableFuture.get()).cancel();
                } catch (InterruptedException | CancellationException | ExecutionException e) {
                    Logger.w(EncoderImpl.this.mTag, "Unable to cancel the input buffer: " + e);
                }
            }
        }

        public void addObserver(Executor executor, Observable.Observer<? super BufferProvider.State> observer) {
            EncoderImpl.this.mEncoderExecutor.execute(new EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda9(this, observer, executor));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$addObserver$7$androidx-camera-video-internal-encoder-EncoderImpl$ByteBufferInput  reason: not valid java name */
        public /* synthetic */ void m297lambda$addObserver$7$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(Observable.Observer observer, Executor executor) {
            this.mStateObservers.put((Observable.Observer) Preconditions.checkNotNull(observer), (Executor) Preconditions.checkNotNull(executor));
            executor.execute(new EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda2(observer, this.mBufferProviderState));
        }

        public void removeObserver(Observable.Observer<? super BufferProvider.State> observer) {
            EncoderImpl.this.mEncoderExecutor.execute(new EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda7(this, observer));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$removeObserver$8$androidx-camera-video-internal-encoder-EncoderImpl$ByteBufferInput  reason: not valid java name */
        public /* synthetic */ void m300lambda$removeObserver$8$androidxcameravideointernalencoderEncoderImpl$ByteBufferInput(Observable.Observer observer) {
            this.mStateObservers.remove(Preconditions.checkNotNull(observer));
        }

        /* access modifiers changed from: package-private */
        public void setActive(boolean z) {
            BufferProvider.State state = z ? BufferProvider.State.ACTIVE : BufferProvider.State.INACTIVE;
            if (this.mBufferProviderState != state) {
                this.mBufferProviderState = state;
                if (state == BufferProvider.State.INACTIVE) {
                    for (ListenableFuture<InputBuffer> cancel : this.mAcquisitionList) {
                        cancel.cancel(true);
                    }
                    this.mAcquisitionList.clear();
                }
                for (Map.Entry next : this.mStateObservers.entrySet()) {
                    try {
                        ((Executor) next.getValue()).execute(new EncoderImpl$ByteBufferInput$$ExternalSyntheticLambda0(next, state));
                    } catch (RejectedExecutionException e) {
                        Logger.e(EncoderImpl.this.mTag, "Unable to post to the supplied executor.", e);
                    }
                }
            }
        }
    }

    private static class Api23Impl {
        private Api23Impl() {
        }

        static Surface createPersistentInputSurface() {
            return MediaCodec.createPersistentInputSurface();
        }

        static void setInputSurface(MediaCodec mediaCodec, Surface surface) {
            mediaCodec.setInputSurface(surface);
        }
    }
}
