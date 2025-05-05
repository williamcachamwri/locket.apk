package androidx.media3.common.audio;

import androidx.media3.common.C;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.LongArrayQueue;
import androidx.media3.common.util.SpeedProviderUtil;
import androidx.media3.common.util.TimestampConsumer;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class SpeedChangingAudioProcessor extends BaseAudioProcessor {
    private long bytesRead;
    private float currentSpeed;
    private boolean endOfStreamQueuedToSonic;
    private LongArray inputSegmentStartTimesUs;
    private long lastProcessedInputTimeUs;
    private long lastSpeedAdjustedInputTimeUs;
    private long lastSpeedAdjustedOutputTimeUs;
    private final Object lock;
    private LongArray outputSegmentStartTimesUs;
    private final LongArrayQueue pendingCallbackInputTimesUs = new LongArrayQueue();
    private final Queue<TimestampConsumer> pendingCallbacks = new ArrayDeque();
    private final SynchronizedSonicAudioProcessor sonicAudioProcessor;
    private long speedAdjustedTimeAsyncInputTimeUs = C.TIME_UNSET;
    private final SpeedProvider speedProvider;

    private static double divide(long j, long j2) {
        return ((double) j) / ((double) j2);
    }

    public SpeedChangingAudioProcessor(SpeedProvider speedProvider2) {
        this.speedProvider = speedProvider2;
        Object obj = new Object();
        this.lock = obj;
        this.sonicAudioProcessor = new SynchronizedSonicAudioProcessor(obj);
        resetState();
    }

    public long getDurationAfterProcessorApplied(long j) {
        return SpeedProviderUtil.getDurationAfterSpeedProviderApplied(this.speedProvider, j);
    }

    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        return this.sonicAudioProcessor.configure(audioFormat);
    }

    public void queueInput(ByteBuffer byteBuffer) {
        int i;
        ByteBuffer byteBuffer2 = byteBuffer;
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(this.bytesRead, 1000000, ((long) this.inputAudioFormat.sampleRate) * ((long) this.inputAudioFormat.bytesPerFrame));
        float speed = this.speedProvider.getSpeed(scaleLargeTimestamp);
        long nextSpeedChangeTimeUs = this.speedProvider.getNextSpeedChangeTimeUs(scaleLargeTimestamp);
        if (getSampleRateAlignedTimestamp(nextSpeedChangeTimeUs, this.inputAudioFormat.sampleRate) == scaleLargeTimestamp) {
            long sampleCountToDurationUs = Util.sampleCountToDurationUs(1, this.inputAudioFormat.sampleRate) + scaleLargeTimestamp;
            float speed2 = this.speedProvider.getSpeed(sampleCountToDurationUs);
            long nextSpeedChangeTimeUs2 = this.speedProvider.getNextSpeedChangeTimeUs(sampleCountToDurationUs);
            speed = speed2;
            nextSpeedChangeTimeUs = nextSpeedChangeTimeUs2;
        }
        updateSpeed(speed, scaleLargeTimestamp);
        int limit = byteBuffer.limit();
        if (nextSpeedChangeTimeUs != C.TIME_UNSET) {
            i = (int) Util.scaleLargeTimestamp(nextSpeedChangeTimeUs - scaleLargeTimestamp, ((long) this.inputAudioFormat.sampleRate) * ((long) this.inputAudioFormat.bytesPerFrame), 1000000);
            int i2 = this.inputAudioFormat.bytesPerFrame - (i % this.inputAudioFormat.bytesPerFrame);
            if (i2 != this.inputAudioFormat.bytesPerFrame) {
                i += i2;
            }
            byteBuffer2.limit(Math.min(limit, byteBuffer.position() + i));
        } else {
            i = -1;
        }
        long position = (long) byteBuffer.position();
        if (isUsingSonic()) {
            this.sonicAudioProcessor.queueInput(byteBuffer2);
            if (i != -1 && ((long) byteBuffer.position()) - position == ((long) i)) {
                this.sonicAudioProcessor.queueEndOfStream();
                this.endOfStreamQueuedToSonic = true;
            }
        } else {
            ByteBuffer replaceOutputBuffer = replaceOutputBuffer(byteBuffer.remaining());
            if (byteBuffer.hasRemaining()) {
                replaceOutputBuffer.put(byteBuffer2);
            }
            replaceOutputBuffer.flip();
        }
        this.bytesRead += ((long) byteBuffer.position()) - position;
        updateLastProcessedInputTime();
        byteBuffer2.limit(limit);
    }

    /* access modifiers changed from: protected */
    public void onQueueEndOfStream() {
        if (!this.endOfStreamQueuedToSonic) {
            this.sonicAudioProcessor.queueEndOfStream();
            this.endOfStreamQueuedToSonic = true;
        }
    }

    public ByteBuffer getOutput() {
        ByteBuffer output = isUsingSonic() ? this.sonicAudioProcessor.getOutput() : super.getOutput();
        processPendingCallbacks();
        return output;
    }

    public boolean isEnded() {
        return super.isEnded() && this.sonicAudioProcessor.isEnded();
    }

    /* access modifiers changed from: protected */
    public void onFlush() {
        resetState();
        this.sonicAudioProcessor.flush();
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        resetState();
        this.sonicAudioProcessor.reset();
    }

    public void getSpeedAdjustedTimeAsync(long j, TimestampConsumer timestampConsumer) {
        synchronized (this.lock) {
            Assertions.checkArgument(this.speedAdjustedTimeAsyncInputTimeUs < j);
            this.speedAdjustedTimeAsyncInputTimeUs = j;
            if ((j > this.lastProcessedInputTimeUs || !this.pendingCallbackInputTimesUs.isEmpty()) && !isEnded()) {
                this.pendingCallbackInputTimesUs.add(j);
                this.pendingCallbacks.add(timestampConsumer);
                return;
            }
            timestampConsumer.onTimestamp(calculateSpeedAdjustedTime(j));
        }
    }

    public long getMediaDurationUs(long j) {
        long j2;
        long j3;
        synchronized (this.lock) {
            int size = this.outputSegmentStartTimesUs.size() - 1;
            while (size > 0 && this.outputSegmentStartTimesUs.get(size) > j) {
                size--;
            }
            long j4 = j - this.outputSegmentStartTimesUs.get(size);
            if (size == this.outputSegmentStartTimesUs.size() - 1) {
                j2 = getMediaDurationUsAtCurrentSpeed(j4);
            } else {
                int i = size + 1;
                j2 = Math.round(((double) j4) * divide(this.inputSegmentStartTimesUs.get(i) - this.inputSegmentStartTimesUs.get(size), this.outputSegmentStartTimesUs.get(i) - this.outputSegmentStartTimesUs.get(size)));
            }
            j3 = this.inputSegmentStartTimesUs.get(size) + j2;
        }
        return j3;
    }

    private long calculateSpeedAdjustedTime(long j) {
        long j2;
        int size = this.inputSegmentStartTimesUs.size() - 1;
        while (size > 0 && this.inputSegmentStartTimesUs.get(size) > j) {
            size--;
        }
        if (size == this.inputSegmentStartTimesUs.size() - 1) {
            if (this.lastSpeedAdjustedInputTimeUs < this.inputSegmentStartTimesUs.get(size)) {
                this.lastSpeedAdjustedInputTimeUs = this.inputSegmentStartTimesUs.get(size);
                this.lastSpeedAdjustedOutputTimeUs = this.outputSegmentStartTimesUs.get(size);
            }
            j2 = getPlayoutDurationUsAtCurrentSpeed(j - this.lastSpeedAdjustedInputTimeUs);
        } else {
            int i = size + 1;
            j2 = Math.round(((double) (j - this.lastSpeedAdjustedInputTimeUs)) * divide(this.outputSegmentStartTimesUs.get(i) - this.outputSegmentStartTimesUs.get(size), this.inputSegmentStartTimesUs.get(i) - this.inputSegmentStartTimesUs.get(size)));
        }
        this.lastSpeedAdjustedInputTimeUs = j;
        long j3 = this.lastSpeedAdjustedOutputTimeUs + j2;
        this.lastSpeedAdjustedOutputTimeUs = j3;
        return j3;
    }

    private void processPendingCallbacks() {
        synchronized (this.lock) {
            while (!this.pendingCallbacks.isEmpty() && (this.pendingCallbackInputTimesUs.element() <= this.lastProcessedInputTimeUs || isEnded())) {
                this.pendingCallbacks.remove().onTimestamp(calculateSpeedAdjustedTime(this.pendingCallbackInputTimesUs.remove()));
            }
        }
    }

    private void updateSpeed(float f, long j) {
        synchronized (this.lock) {
            if (f != this.currentSpeed) {
                updateSpeedChangeArrays(j);
                this.currentSpeed = f;
                if (isUsingSonic()) {
                    this.sonicAudioProcessor.setSpeed(f);
                    this.sonicAudioProcessor.setPitch(f);
                }
                this.sonicAudioProcessor.flush();
                this.endOfStreamQueuedToSonic = false;
                super.getOutput();
            }
        }
    }

    private void updateSpeedChangeArrays(long j) {
        LongArray longArray = this.outputSegmentStartTimesUs;
        long j2 = longArray.get(longArray.size() - 1);
        LongArray longArray2 = this.inputSegmentStartTimesUs;
        long j3 = j - longArray2.get(longArray2.size() - 1);
        this.inputSegmentStartTimesUs.add(j);
        this.outputSegmentStartTimesUs.add(j2 + getPlayoutDurationUsAtCurrentSpeed(j3));
    }

    private long getPlayoutDurationUsAtCurrentSpeed(long j) {
        return isUsingSonic() ? this.sonicAudioProcessor.getPlayoutDuration(j) : j;
    }

    private long getMediaDurationUsAtCurrentSpeed(long j) {
        return isUsingSonic() ? this.sonicAudioProcessor.getMediaDuration(j) : j;
    }

    private void updateLastProcessedInputTime() {
        synchronized (this.lock) {
            if (isUsingSonic()) {
                long scaleLargeTimestamp = Util.scaleLargeTimestamp(this.sonicAudioProcessor.getProcessedInputBytes(), 1000000, ((long) this.inputAudioFormat.sampleRate) * ((long) this.inputAudioFormat.bytesPerFrame));
                LongArray longArray = this.inputSegmentStartTimesUs;
                this.lastProcessedInputTimeUs = longArray.get(longArray.size() - 1) + scaleLargeTimestamp;
            } else {
                this.lastProcessedInputTimeUs = Util.scaleLargeTimestamp(this.bytesRead, 1000000, ((long) this.inputAudioFormat.sampleRate) * ((long) this.inputAudioFormat.bytesPerFrame));
            }
        }
    }

    private boolean isUsingSonic() {
        boolean z;
        synchronized (this.lock) {
            z = this.currentSpeed != 1.0f;
        }
        return z;
    }

    @RequiresNonNull({"lock"})
    @EnsuresNonNull({"inputSegmentStartTimesUs", "outputSegmentStartTimesUs"})
    private void resetState() {
        synchronized (this.lock) {
            this.inputSegmentStartTimesUs = new LongArray();
            this.outputSegmentStartTimesUs = new LongArray();
            this.inputSegmentStartTimesUs.add(0);
            this.outputSegmentStartTimesUs.add(0);
            this.lastProcessedInputTimeUs = 0;
            this.lastSpeedAdjustedInputTimeUs = 0;
            this.lastSpeedAdjustedOutputTimeUs = 0;
            this.currentSpeed = 1.0f;
        }
        this.bytesRead = 0;
        this.endOfStreamQueuedToSonic = false;
    }

    private static long getSampleRateAlignedTimestamp(long j, int i) {
        long j2 = (long) i;
        return Util.scaleLargeTimestamp(Util.scaleLargeTimestamp(j, j2, 1000000), 1000000, j2);
    }
}
