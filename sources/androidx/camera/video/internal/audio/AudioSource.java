package androidx.camera.video.internal.audio;

import android.content.Context;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.Observable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.video.internal.BufferProvider;
import androidx.camera.video.internal.audio.AudioStream;
import androidx.camera.video.internal.encoder.InputBuffer;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class AudioSource {
    static final long DEFAULT_START_RETRY_INTERVAL_MS = 3000;
    private static final String TAG = "AudioSource";
    private FutureCallback<InputBuffer> mAcquireBufferCallback;
    long mAmplitudeTimestamp;
    double mAudioAmplitude;
    private final int mAudioFormat;
    public final int mAudioSource;
    AudioSourceCallback mAudioSourceCallback;
    final AudioStream mAudioStream;
    boolean mAudioStreamSilenced;
    BufferProvider<? extends InputBuffer> mBufferProvider;
    BufferProvider.State mBufferProviderState;
    Executor mCallbackExecutor;
    final Executor mExecutor;
    boolean mInSilentStartState;
    boolean mIsSendingAudio;
    private long mLatestFailedStartTimeNs;
    boolean mMuted;
    final AtomicReference<Boolean> mNotifiedSilenceState;
    final AtomicBoolean mNotifiedSuspendState;
    final SilentAudioStream mSilentAudioStream;
    private final long mStartRetryIntervalNs;
    InternalState mState;
    private Observable.Observer<BufferProvider.State> mStateObserver;
    private byte[] mZeroBytes;

    public interface AudioSourceCallback {
        void onAmplitudeValue(double d);

        void onError(Throwable th);

        void onSilenceStateChanged(boolean z);

        void onSuspendStateChanged(boolean z) {
        }
    }

    enum InternalState {
        CONFIGURED,
        STARTED,
        RELEASED
    }

    public AudioSource(AudioSettings audioSettings, Executor executor, Context context) throws AudioSourceAccessException {
        this(audioSettings, executor, context, new AudioSource$$ExternalSyntheticLambda8(), 3000);
    }

    AudioSource(AudioSettings audioSettings, Executor executor, Context context, AudioStreamFactory audioStreamFactory, long j) throws AudioSourceAccessException {
        this.mNotifiedSilenceState = new AtomicReference<>((Object) null);
        this.mNotifiedSuspendState = new AtomicBoolean(false);
        this.mState = InternalState.CONFIGURED;
        this.mBufferProviderState = BufferProvider.State.INACTIVE;
        this.mAmplitudeTimestamp = 0;
        Executor newSequentialExecutor = CameraXExecutors.newSequentialExecutor(executor);
        this.mExecutor = newSequentialExecutor;
        this.mStartRetryIntervalNs = TimeUnit.MILLISECONDS.toNanos(j);
        try {
            BufferedAudioStream bufferedAudioStream = new BufferedAudioStream(audioStreamFactory.create(audioSettings, context), audioSettings);
            this.mAudioStream = bufferedAudioStream;
            bufferedAudioStream.setCallback(new AudioStreamCallback(), newSequentialExecutor);
            this.mSilentAudioStream = new SilentAudioStream(audioSettings);
            this.mAudioFormat = audioSettings.getAudioFormat();
            this.mAudioSource = audioSettings.getAudioSource();
        } catch (AudioStream.AudioStreamException | IllegalArgumentException e) {
            throw new AudioSourceAccessException("Unable to create AudioStream", e);
        }
    }

    class AudioStreamCallback implements AudioStream.AudioStreamCallback {
        AudioStreamCallback() {
        }

        public void onSilenceStateChanged(boolean z) {
            AudioSource.this.mAudioStreamSilenced = z;
            if (AudioSource.this.mState == InternalState.STARTED) {
                AudioSource.this.notifySilenced();
            }
        }
    }

    public void setBufferProvider(BufferProvider<? extends InputBuffer> bufferProvider) {
        this.mExecutor.execute(new AudioSource$$ExternalSyntheticLambda0(this, bufferProvider));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setBufferProvider$0$androidx-camera-video-internal-audio-AudioSource  reason: not valid java name */
    public /* synthetic */ void m271lambda$setBufferProvider$0$androidxcameravideointernalaudioAudioSource(BufferProvider bufferProvider) {
        int ordinal = this.mState.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            if (this.mBufferProvider != bufferProvider) {
                resetBufferProvider(bufferProvider);
            }
        } else if (ordinal == 2) {
            throw new AssertionError("AudioSource is released");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$start$1$androidx-camera-video-internal-audio-AudioSource  reason: not valid java name */
    public /* synthetic */ void m272lambda$start$1$androidxcameravideointernalaudioAudioSource() {
        start(this.mMuted);
    }

    public void start() {
        this.mExecutor.execute(new AudioSource$$ExternalSyntheticLambda5(this));
    }

    public void start(boolean z) {
        this.mExecutor.execute(new AudioSource$$ExternalSyntheticLambda4(this, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$start$2$androidx-camera-video-internal-audio-AudioSource  reason: not valid java name */
    public /* synthetic */ void m273lambda$start$2$androidxcameravideointernalaudioAudioSource(boolean z) {
        int ordinal = this.mState.ordinal();
        if (ordinal == 0) {
            this.mNotifiedSilenceState.set((Object) null);
            this.mNotifiedSuspendState.set(false);
            setState(InternalState.STARTED);
            mute(z);
            updateSendingAudio();
        } else if (ordinal == 2) {
            throw new AssertionError("AudioSource is released");
        }
    }

    public void stop() {
        this.mExecutor.execute(new AudioSource$$ExternalSyntheticLambda10(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$stop$3$androidx-camera-video-internal-audio-AudioSource  reason: not valid java name */
    public /* synthetic */ void m274lambda$stop$3$androidxcameravideointernalaudioAudioSource() {
        int ordinal = this.mState.ordinal();
        if (ordinal == 1) {
            setState(InternalState.CONFIGURED);
            updateSendingAudio();
        } else if (ordinal == 2) {
            Logger.w(TAG, "AudioSource is released. Calling stop() is a no-op.");
        }
    }

    public ListenableFuture<Void> release() {
        return CallbackToFutureAdapter.getFuture(new AudioSource$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$5$androidx-camera-video-internal-audio-AudioSource  reason: not valid java name */
    public /* synthetic */ Object m269lambda$release$5$androidxcameravideointernalaudioAudioSource(CallbackToFutureAdapter.Completer completer) throws Exception {
        this.mExecutor.execute(new AudioSource$$ExternalSyntheticLambda11(this, completer));
        return "AudioSource-release";
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$release$4$androidx-camera-video-internal-audio-AudioSource  reason: not valid java name */
    public /* synthetic */ void m268lambda$release$4$androidxcameravideointernalaudioAudioSource(CallbackToFutureAdapter.Completer completer) {
        try {
            int ordinal = this.mState.ordinal();
            if (ordinal == 0 || ordinal == 1) {
                resetBufferProvider((BufferProvider<? extends InputBuffer>) null);
                this.mSilentAudioStream.release();
                this.mAudioStream.release();
                stopSendingAudio();
                setState(InternalState.RELEASED);
            }
            completer.set(null);
        } catch (Throwable th) {
            completer.setException(th);
        }
    }

    public void setAudioSourceCallback(Executor executor, AudioSourceCallback audioSourceCallback) {
        this.mExecutor.execute(new AudioSource$$ExternalSyntheticLambda9(this, executor, audioSourceCallback));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setAudioSourceCallback$6$androidx-camera-video-internal-audio-AudioSource  reason: not valid java name */
    public /* synthetic */ void m270lambda$setAudioSourceCallback$6$androidxcameravideointernalaudioAudioSource(Executor executor, AudioSourceCallback audioSourceCallback) {
        int ordinal = this.mState.ordinal();
        if (ordinal == 0) {
            this.mCallbackExecutor = executor;
            this.mAudioSourceCallback = audioSourceCallback;
        } else if (ordinal == 1 || ordinal == 2) {
            throw new AssertionError("The audio recording callback must be registered before the audio source is started.");
        }
    }

    public void mute(boolean z) {
        this.mExecutor.execute(new AudioSource$$ExternalSyntheticLambda2(this, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$mute$7$androidx-camera-video-internal-audio-AudioSource  reason: not valid java name */
    public /* synthetic */ void m266lambda$mute$7$androidxcameravideointernalaudioAudioSource(boolean z) {
        int ordinal = this.mState.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            if (this.mMuted != z) {
                this.mMuted = z;
                if (this.mState == InternalState.STARTED) {
                    notifySilenced();
                }
            }
        } else if (ordinal == 2) {
            throw new AssertionError("AudioSource is released");
        }
    }

    private void resetBufferProvider(final BufferProvider<? extends InputBuffer> bufferProvider) {
        BufferProvider<? extends InputBuffer> bufferProvider2 = this.mBufferProvider;
        if (bufferProvider2 != null) {
            bufferProvider2.removeObserver((Observable.Observer) Objects.requireNonNull(this.mStateObserver));
            this.mBufferProvider = null;
            this.mStateObserver = null;
            this.mAcquireBufferCallback = null;
            this.mBufferProviderState = BufferProvider.State.INACTIVE;
            updateSendingAudio();
        }
        if (bufferProvider != null) {
            this.mBufferProvider = bufferProvider;
            this.mStateObserver = new Observable.Observer<BufferProvider.State>() {
                public void onNewData(BufferProvider.State state) {
                    Objects.requireNonNull(state);
                    if (AudioSource.this.mBufferProvider == bufferProvider) {
                        Logger.d(AudioSource.TAG, "Receive BufferProvider state change: " + AudioSource.this.mBufferProviderState + " to " + state);
                        if (AudioSource.this.mBufferProviderState != state) {
                            AudioSource.this.mBufferProviderState = state;
                            AudioSource.this.updateSendingAudio();
                        }
                    }
                }

                public void onError(Throwable th) {
                    if (AudioSource.this.mBufferProvider == bufferProvider) {
                        AudioSource.this.notifyError(th);
                    }
                }
            };
            this.mAcquireBufferCallback = new FutureCallback<InputBuffer>() {
                public void onSuccess(InputBuffer inputBuffer) {
                    if (!AudioSource.this.mIsSendingAudio || AudioSource.this.mBufferProvider != bufferProvider) {
                        inputBuffer.cancel();
                        return;
                    }
                    if (AudioSource.this.mInSilentStartState && AudioSource.this.isStartRetryIntervalReached()) {
                        AudioSource.this.retryStartAudioStream();
                    }
                    AudioStream currentAudioStream = AudioSource.this.getCurrentAudioStream();
                    ByteBuffer byteBuffer = inputBuffer.getByteBuffer();
                    AudioStream.PacketInfo read = currentAudioStream.read(byteBuffer);
                    if (read.getSizeInBytes() > 0) {
                        if (AudioSource.this.mMuted) {
                            AudioSource.this.overrideBySilence(byteBuffer, read.getSizeInBytes());
                        }
                        if (AudioSource.this.mCallbackExecutor != null && read.getTimestampNs() - AudioSource.this.mAmplitudeTimestamp >= 200) {
                            AudioSource.this.mAmplitudeTimestamp = read.getTimestampNs();
                            AudioSource.this.postMaxAmplitude(byteBuffer);
                        }
                        byteBuffer.limit(byteBuffer.position() + read.getSizeInBytes());
                        inputBuffer.setPresentationTimeUs(TimeUnit.NANOSECONDS.toMicros(read.getTimestampNs()));
                        inputBuffer.submit();
                    } else {
                        Logger.w(AudioSource.TAG, "Unable to read data from AudioStream.");
                        inputBuffer.cancel();
                    }
                    AudioSource.this.sendNextAudio();
                }

                public void onFailure(Throwable th) {
                    if (AudioSource.this.mBufferProvider == bufferProvider) {
                        Logger.d(AudioSource.TAG, "Unable to get input buffer, the BufferProvider could be transitioning to INACTIVE state.");
                        if (!(th instanceof IllegalStateException)) {
                            AudioSource.this.notifyError(th);
                        }
                    }
                }
            };
            BufferProvider.State fetchBufferProviderState = fetchBufferProviderState(bufferProvider);
            if (fetchBufferProviderState != null) {
                this.mBufferProviderState = fetchBufferProviderState;
                updateSendingAudio();
            }
            this.mBufferProvider.addObserver(this.mExecutor, this.mStateObserver);
        }
    }

    /* access modifiers changed from: package-private */
    public AudioStream getCurrentAudioStream() {
        return this.mInSilentStartState ? this.mSilentAudioStream : this.mAudioStream;
    }

    /* access modifiers changed from: package-private */
    public void retryStartAudioStream() {
        Preconditions.checkState(this.mInSilentStartState);
        try {
            this.mAudioStream.start();
            Logger.d(TAG, "Retry start AudioStream succeed");
            this.mSilentAudioStream.stop();
            this.mInSilentStartState = false;
        } catch (AudioStream.AudioStreamException e) {
            Logger.w(TAG, "Retry start AudioStream failed", e);
            this.mLatestFailedStartTimeNs = getCurrentSystemTimeNs();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isStartRetryIntervalReached() {
        Preconditions.checkState(this.mLatestFailedStartTimeNs > 0);
        if (getCurrentSystemTimeNs() - this.mLatestFailedStartTimeNs >= this.mStartRetryIntervalNs) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void notifyError(Throwable th) {
        Executor executor = this.mCallbackExecutor;
        AudioSourceCallback audioSourceCallback = this.mAudioSourceCallback;
        if (executor != null && audioSourceCallback != null) {
            executor.execute(new AudioSource$$ExternalSyntheticLambda3(audioSourceCallback, th));
        }
    }

    /* access modifiers changed from: package-private */
    public void notifySilenced() {
        Executor executor = this.mCallbackExecutor;
        AudioSourceCallback audioSourceCallback = this.mAudioSourceCallback;
        if (executor != null && audioSourceCallback != null) {
            boolean z = this.mMuted || this.mInSilentStartState || this.mAudioStreamSilenced;
            if (!Objects.equals(this.mNotifiedSilenceState.getAndSet(Boolean.valueOf(z)), Boolean.valueOf(z))) {
                executor.execute(new AudioSource$$ExternalSyntheticLambda1(audioSourceCallback, z));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifySuspended(boolean z) {
        Executor executor = this.mCallbackExecutor;
        AudioSourceCallback audioSourceCallback = this.mAudioSourceCallback;
        if (executor != null && audioSourceCallback != null && this.mNotifiedSuspendState.getAndSet(z) != z) {
            executor.execute(new AudioSource$$ExternalSyntheticLambda6(audioSourceCallback, z));
        }
    }

    /* access modifiers changed from: package-private */
    public void overrideBySilence(ByteBuffer byteBuffer, int i) {
        byte[] bArr = this.mZeroBytes;
        if (bArr == null || bArr.length < i) {
            this.mZeroBytes = new byte[i];
        }
        int position = byteBuffer.position();
        byteBuffer.put(this.mZeroBytes, 0, i);
        byteBuffer.limit(byteBuffer.position()).position(position);
    }

    /* access modifiers changed from: package-private */
    public void updateSendingAudio() {
        if (this.mState == InternalState.STARTED) {
            boolean z = this.mBufferProviderState == BufferProvider.State.ACTIVE;
            notifySuspended(!z);
            if (z) {
                startSendingAudio();
            } else {
                stopSendingAudio();
            }
        } else {
            stopSendingAudio();
        }
    }

    private void startSendingAudio() {
        if (!this.mIsSendingAudio) {
            try {
                Logger.d(TAG, "startSendingAudio");
                this.mAudioStream.start();
                this.mInSilentStartState = false;
            } catch (AudioStream.AudioStreamException e) {
                Logger.w(TAG, "Failed to start AudioStream", e);
                this.mInSilentStartState = true;
                this.mSilentAudioStream.start();
                this.mLatestFailedStartTimeNs = getCurrentSystemTimeNs();
                notifySilenced();
            }
            this.mIsSendingAudio = true;
            sendNextAudio();
        }
    }

    private void stopSendingAudio() {
        if (this.mIsSendingAudio) {
            this.mIsSendingAudio = false;
            Logger.d(TAG, "stopSendingAudio");
            this.mAudioStream.stop();
        }
    }

    /* access modifiers changed from: package-private */
    public void sendNextAudio() {
        Futures.addCallback(((BufferProvider) Objects.requireNonNull(this.mBufferProvider)).acquireBuffer(), (FutureCallback) Objects.requireNonNull(this.mAcquireBufferCallback), this.mExecutor);
    }

    /* access modifiers changed from: package-private */
    public void setState(InternalState internalState) {
        Logger.d(TAG, "Transitioning internal state: " + this.mState + " --> " + internalState);
        this.mState = internalState;
    }

    /* access modifiers changed from: package-private */
    public void postMaxAmplitude(ByteBuffer byteBuffer) {
        Executor executor = this.mCallbackExecutor;
        AudioSourceCallback audioSourceCallback = this.mAudioSourceCallback;
        if (this.mAudioFormat == 2) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            double d = 0.0d;
            while (asShortBuffer.hasRemaining()) {
                d = Math.max(d, (double) Math.abs(asShortBuffer.get()));
            }
            this.mAudioAmplitude = d / 32767.0d;
            if (executor != null && audioSourceCallback != null) {
                executor.execute(new AudioSource$$ExternalSyntheticLambda12(this, audioSourceCallback));
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$postMaxAmplitude$11$androidx-camera-video-internal-audio-AudioSource  reason: not valid java name */
    public /* synthetic */ void m267lambda$postMaxAmplitude$11$androidxcameravideointernalaudioAudioSource(AudioSourceCallback audioSourceCallback) {
        audioSourceCallback.onAmplitudeValue(this.mAudioAmplitude);
    }

    private static BufferProvider.State fetchBufferProviderState(BufferProvider<? extends InputBuffer> bufferProvider) {
        try {
            ListenableFuture<? extends InputBuffer> fetchData = bufferProvider.fetchData();
            if (fetchData.isDone()) {
                return (BufferProvider.State) fetchData.get();
            }
            return null;
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }

    public static boolean isSettingsSupported(int i, int i2, int i3) {
        return AudioStreamImpl.isSettingsSupported(i, i2, i3);
    }

    private static long getCurrentSystemTimeNs() {
        return System.nanoTime();
    }
}
