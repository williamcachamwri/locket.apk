package androidx.camera.video.internal.audio;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioRecordingConfiguration;
import android.os.Build;
import androidx.camera.core.Logger;
import androidx.camera.video.internal.audio.AudioStream;
import androidx.camera.video.internal.compat.Api23Impl;
import androidx.camera.video.internal.compat.Api24Impl;
import androidx.camera.video.internal.compat.Api29Impl;
import androidx.camera.video.internal.compat.Api31Impl;
import androidx.camera.video.internal.compat.quirk.AudioTimestampFramePositionIncorrectQuirk;
import androidx.camera.video.internal.compat.quirk.DeviceQuirks;
import androidx.core.util.Preconditions;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class AudioStreamImpl implements AudioStream {
    private static final long DIFF_LIMIT_FROM_SYSTEM_TIME_NS = TimeUnit.MILLISECONDS.toNanos(500);
    private static final String TAG = "AudioStreamImpl";
    /* access modifiers changed from: private */
    public AudioRecord mAudioRecord;
    private AudioManager.AudioRecordingCallback mAudioRecordingCallback;
    private AudioStream.AudioStreamCallback mAudioStreamCallback;
    private final int mBufferSize;
    private final int mBytesPerFrame;
    private Executor mCallbackExecutor;
    private final AtomicBoolean mIsReleased = new AtomicBoolean(false);
    private final AtomicBoolean mIsStarted = new AtomicBoolean(false);
    private final AtomicReference<Boolean> mNotifiedSilenceState = new AtomicReference<>((Object) null);
    private final AudioSettings mSettings;
    private boolean mShouldFallbackToSystemTime = false;
    private long mTotalFramesRead;

    public AudioStreamImpl(AudioSettings audioSettings, Context context) throws IllegalArgumentException, AudioStream.AudioStreamException {
        boolean z = false;
        if (isSettingsSupported(audioSettings.getSampleRate(), audioSettings.getChannelCount(), audioSettings.getAudioFormat())) {
            this.mSettings = audioSettings;
            this.mBytesPerFrame = audioSettings.getBytesPerFrame();
            int minBufferSize = getMinBufferSize(audioSettings.getSampleRate(), audioSettings.getChannelCount(), audioSettings.getAudioFormat());
            Preconditions.checkState(minBufferSize > 0 ? true : z);
            int i = minBufferSize * 2;
            this.mBufferSize = i;
            AudioRecord createAudioRecord = createAudioRecord(i, audioSettings, context);
            this.mAudioRecord = createAudioRecord;
            checkAudioRecordInitialStateOrReleaseAndThrow(createAudioRecord);
            return;
        }
        throw new UnsupportedOperationException(String.format("The combination of sample rate %d, channel count %d and audio format %d is not supported.", new Object[]{Integer.valueOf(audioSettings.getSampleRate()), Integer.valueOf(audioSettings.getChannelCount()), Integer.valueOf(audioSettings.getAudioFormat())}));
    }

    public void start() throws AudioStream.AudioStreamException {
        checkNotReleasedOrThrow();
        boolean z = true;
        if (!this.mIsStarted.getAndSet(true)) {
            if (hasAudioTimestampQuirk()) {
                checkAudioRecordInitialStateOrReleaseAndThrow(this.mAudioRecord);
            }
            this.mAudioRecord.startRecording();
            boolean z2 = false;
            if (this.mAudioRecord.getRecordingState() == 3) {
                this.mTotalFramesRead = 0;
                this.mShouldFallbackToSystemTime = false;
                this.mNotifiedSilenceState.set((Object) null);
                if (Build.VERSION.SDK_INT >= 29) {
                    AudioRecordingConfiguration activeRecordingConfiguration = Api29Impl.getActiveRecordingConfiguration(this.mAudioRecord);
                    if (activeRecordingConfiguration == null || !Api29Impl.isClientSilenced(activeRecordingConfiguration)) {
                        z = false;
                    }
                    z2 = z;
                }
                notifySilenced(z2);
                return;
            }
            this.mIsStarted.set(false);
            throw new AudioStream.AudioStreamException("Unable to start AudioRecord with state: " + this.mAudioRecord.getRecordingState());
        }
    }

    public void stop() {
        checkNotReleasedOrThrow();
        if (this.mIsStarted.getAndSet(false)) {
            this.mAudioRecord.stop();
            if (this.mAudioRecord.getRecordingState() != 1) {
                Logger.w(TAG, "Failed to stop AudioRecord with state: " + this.mAudioRecord.getRecordingState());
            }
            if (hasAudioTimestampQuirk()) {
                this.mAudioRecord.release();
                this.mAudioRecord = createAudioRecord(this.mBufferSize, this.mSettings, (Context) null);
            }
        }
    }

    public void release() {
        AudioManager.AudioRecordingCallback audioRecordingCallback;
        if (!this.mIsReleased.getAndSet(true)) {
            if (Build.VERSION.SDK_INT >= 29 && (audioRecordingCallback = this.mAudioRecordingCallback) != null) {
                Api29Impl.unregisterAudioRecordingCallback(this.mAudioRecord, audioRecordingCallback);
            }
            this.mAudioRecord.release();
        }
    }

    public AudioStream.PacketInfo read(ByteBuffer byteBuffer) {
        long j;
        checkNotReleasedOrThrow();
        checkStartedOrThrow();
        int read = this.mAudioRecord.read(byteBuffer, this.mBufferSize);
        if (read > 0) {
            byteBuffer.limit(read);
            j = generatePresentationTimeNs();
            this.mTotalFramesRead += AudioUtils.sizeToFrameCount((long) read, this.mBytesPerFrame);
        } else {
            j = 0;
        }
        return AudioStream.PacketInfo.of(read, j);
    }

    public void setCallback(AudioStream.AudioStreamCallback audioStreamCallback, Executor executor) {
        boolean z = true;
        Preconditions.checkState(!this.mIsStarted.get(), "AudioStream can not be started when setCallback.");
        checkNotReleasedOrThrow();
        if (audioStreamCallback != null && executor == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "executor can't be null with non-null callback.");
        this.mAudioStreamCallback = audioStreamCallback;
        this.mCallbackExecutor = executor;
        if (Build.VERSION.SDK_INT >= 29) {
            AudioManager.AudioRecordingCallback audioRecordingCallback = this.mAudioRecordingCallback;
            if (audioRecordingCallback != null) {
                Api29Impl.unregisterAudioRecordingCallback(this.mAudioRecord, audioRecordingCallback);
            }
            if (audioStreamCallback != null) {
                if (this.mAudioRecordingCallback == null) {
                    this.mAudioRecordingCallback = new AudioRecordingApi29Callback();
                }
                Api29Impl.registerAudioRecordingCallback(this.mAudioRecord, executor, this.mAudioRecordingCallback);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifySilenced(boolean z) {
        Executor executor = this.mCallbackExecutor;
        AudioStream.AudioStreamCallback audioStreamCallback = this.mAudioStreamCallback;
        if (executor != null && audioStreamCallback != null && !Objects.equals(this.mNotifiedSilenceState.getAndSet(Boolean.valueOf(z)), Boolean.valueOf(z))) {
            executor.execute(new AudioStreamImpl$$ExternalSyntheticLambda0(audioStreamCallback, z));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long generatePresentationTimeNs() {
        /*
            r9 = this;
            boolean r0 = r9.mShouldFallbackToSystemTime
            r1 = -1
            if (r0 != 0) goto L_0x003b
            android.media.AudioTimestamp r0 = new android.media.AudioTimestamp
            r0.<init>()
            android.media.AudioRecord r3 = r9.mAudioRecord
            r4 = 0
            int r3 = androidx.camera.video.internal.compat.Api24Impl.getTimestamp(r3, r0, r4)
            if (r3 != 0) goto L_0x0034
            androidx.camera.video.internal.audio.AudioSettings r3 = r9.mSettings
            int r3 = r3.getSampleRate()
            long r4 = r9.mTotalFramesRead
            long r3 = androidx.camera.video.internal.audio.AudioUtils.computeInterpolatedTimeNs(r3, r4, r0)
            long r5 = java.lang.System.nanoTime()
            long r5 = r3 - r5
            long r5 = java.lang.Math.abs(r5)
            long r7 = DIFF_LIMIT_FROM_SYSTEM_TIME_NS
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x003c
            r0 = 1
            r9.mShouldFallbackToSystemTime = r0
            goto L_0x003b
        L_0x0034:
            java.lang.String r0 = "AudioStreamImpl"
            java.lang.String r3 = "Unable to get audio timestamp"
            androidx.camera.core.Logger.w(r0, r3)
        L_0x003b:
            r3 = r1
        L_0x003c:
            int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r0 != 0) goto L_0x0044
            long r3 = java.lang.System.nanoTime()
        L_0x0044:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.video.internal.audio.AudioStreamImpl.generatePresentationTimeNs():long");
    }

    private void checkNotReleasedOrThrow() {
        Preconditions.checkState(!this.mIsReleased.get(), "AudioStream has been released.");
    }

    private void checkStartedOrThrow() {
        Preconditions.checkState(this.mIsStarted.get(), "AudioStream has not been started.");
    }

    private static AudioRecord createAudioRecord(int i, AudioSettings audioSettings, Context context) {
        AudioFormat build = new AudioFormat.Builder().setSampleRate(audioSettings.getSampleRate()).setChannelMask(AudioUtils.channelCountToChannelMask(audioSettings.getChannelCount())).setEncoding(audioSettings.getAudioFormat()).build();
        AudioRecord.Builder createAudioRecordBuilder = Api23Impl.createAudioRecordBuilder();
        if (Build.VERSION.SDK_INT >= 31 && context != null) {
            Api31Impl.setContext(createAudioRecordBuilder, context);
        }
        Api23Impl.setAudioSource(createAudioRecordBuilder, audioSettings.getAudioSource());
        Api23Impl.setAudioFormat(createAudioRecordBuilder, build);
        Api23Impl.setBufferSizeInBytes(createAudioRecordBuilder, i);
        return Api23Impl.build(createAudioRecordBuilder);
    }

    private static void checkAudioRecordInitialStateOrReleaseAndThrow(AudioRecord audioRecord) throws AudioStream.AudioStreamException {
        if (audioRecord.getState() != 1) {
            audioRecord.release();
            throw new AudioStream.AudioStreamException("Unable to initialize AudioRecord");
        }
    }

    public static boolean isSettingsSupported(int i, int i2, int i3) {
        return i > 0 && i2 > 0 && getMinBufferSize(i, i2, i3) > 0;
    }

    private static boolean hasAudioTimestampQuirk() {
        return DeviceQuirks.get(AudioTimestampFramePositionIncorrectQuirk.class) != null;
    }

    private static int getMinBufferSize(int i, int i2, int i3) {
        return AudioRecord.getMinBufferSize(i, AudioUtils.channelCountToChannelConfig(i2), i3);
    }

    class AudioRecordingApi29Callback extends AudioManager.AudioRecordingCallback {
        AudioRecordingApi29Callback() {
        }

        public void onRecordingConfigChanged(List<AudioRecordingConfiguration> list) {
            for (AudioRecordingConfiguration next : list) {
                if (Api24Impl.getClientAudioSessionId(next) == AudioStreamImpl.this.mAudioRecord.getAudioSessionId()) {
                    AudioStreamImpl.this.notifySilenced(Api29Impl.isClientSilenced(next));
                    return;
                }
            }
        }
    }
}
