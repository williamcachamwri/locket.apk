package androidx.camera.video.internal.audio;

import androidx.camera.core.Logger;
import androidx.camera.video.internal.audio.AudioStream;
import androidx.core.util.Preconditions;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class SilentAudioStream implements AudioStream {
    private static final String TAG = "SilentAudioStream";
    private AudioStream.AudioStreamCallback mAudioStreamCallback;
    private final int mBytesPerFrame;
    private Executor mCallbackExecutor;
    private long mCurrentReadTimeNs;
    private final AtomicBoolean mIsReleased = new AtomicBoolean(false);
    private final AtomicBoolean mIsStarted = new AtomicBoolean(false);
    private final int mSampleRate;
    private byte[] mZeroBytes;

    public SilentAudioStream(AudioSettings audioSettings) {
        this.mBytesPerFrame = audioSettings.getBytesPerFrame();
        this.mSampleRate = audioSettings.getSampleRate();
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
    }

    public void start() {
        checkNotReleasedOrThrow();
        if (!this.mIsStarted.getAndSet(true)) {
            this.mCurrentReadTimeNs = currentSystemTimeNs();
            notifySilenced();
        }
    }

    public void stop() {
        checkNotReleasedOrThrow();
        this.mIsStarted.set(false);
    }

    public void release() {
        this.mIsReleased.getAndSet(true);
    }

    public AudioStream.PacketInfo read(ByteBuffer byteBuffer) {
        checkNotReleasedOrThrow();
        checkStartedOrThrow();
        long sizeToFrameCount = AudioUtils.sizeToFrameCount((long) byteBuffer.remaining(), this.mBytesPerFrame);
        int frameCountToSize = (int) AudioUtils.frameCountToSize(sizeToFrameCount, this.mBytesPerFrame);
        if (frameCountToSize <= 0) {
            return AudioStream.PacketInfo.of(0, this.mCurrentReadTimeNs);
        }
        long frameCountToDurationNs = this.mCurrentReadTimeNs + AudioUtils.frameCountToDurationNs(sizeToFrameCount, this.mSampleRate);
        blockUntilSystemTimeReached(frameCountToDurationNs);
        writeSilenceToBuffer(byteBuffer, frameCountToSize);
        AudioStream.PacketInfo of = AudioStream.PacketInfo.of(frameCountToSize, this.mCurrentReadTimeNs);
        this.mCurrentReadTimeNs = frameCountToDurationNs;
        return of;
    }

    private void writeSilenceToBuffer(ByteBuffer byteBuffer, int i) {
        Preconditions.checkState(i <= byteBuffer.remaining());
        byte[] bArr = this.mZeroBytes;
        if (bArr == null || bArr.length < i) {
            this.mZeroBytes = new byte[i];
        }
        int position = byteBuffer.position();
        byteBuffer.put(this.mZeroBytes, 0, i).limit(i + position).position(position);
    }

    private void notifySilenced() {
        AudioStream.AudioStreamCallback audioStreamCallback = this.mAudioStreamCallback;
        Executor executor = this.mCallbackExecutor;
        if (audioStreamCallback != null && executor != null) {
            executor.execute(new SilentAudioStream$$ExternalSyntheticLambda0(audioStreamCallback));
        }
    }

    private void checkNotReleasedOrThrow() {
        Preconditions.checkState(!this.mIsReleased.get(), "AudioStream has been released.");
    }

    private void checkStartedOrThrow() {
        Preconditions.checkState(this.mIsStarted.get(), "AudioStream has not been started.");
    }

    private static long currentSystemTimeNs() {
        return System.nanoTime();
    }

    private static void blockUntilSystemTimeReached(long j) {
        long currentSystemTimeNs = j - currentSystemTimeNs();
        if (currentSystemTimeNs > 0) {
            try {
                Thread.sleep(TimeUnit.NANOSECONDS.toMillis(currentSystemTimeNs));
            } catch (InterruptedException e) {
                Logger.w(TAG, "Ignore interruption", e);
            }
        }
    }
}
