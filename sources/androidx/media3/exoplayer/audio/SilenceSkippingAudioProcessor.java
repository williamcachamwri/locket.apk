package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

public final class SilenceSkippingAudioProcessor extends BaseAudioProcessor {
    private static final int AVOID_TRUNCATION_FACTOR = 1000;
    public static final long DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US = 2000000;
    public static final long DEFAULT_MINIMUM_SILENCE_DURATION_US = 100000;
    public static final int DEFAULT_MIN_VOLUME_TO_KEEP_PERCENTAGE = 10;
    @Deprecated
    public static final long DEFAULT_PADDING_SILENCE_US = 20000;
    public static final float DEFAULT_SILENCE_RETENTION_RATIO = 0.2f;
    public static final short DEFAULT_SILENCE_THRESHOLD_LEVEL = 1024;
    private static final int DO_NOT_CHANGE_VOLUME = 3;
    private static final int FADE_IN = 2;
    private static final int FADE_OUT = 0;
    private static final int MUTE = 1;
    private static final int STATE_NOISY = 0;
    private static final int STATE_SHORTENING_SILENCE = 1;
    private int bytesPerFrame;
    private byte[] contiguousOutputBuffer;
    private boolean enabled;
    private final long maxSilenceToKeepDurationUs;
    private byte[] maybeSilenceBuffer;
    private int maybeSilenceBufferContentsSize;
    private int maybeSilenceBufferStartIndex;
    private final int minVolumeToKeepPercentageWhenMuting;
    private final long minimumSilenceDurationUs;
    private int outputSilenceFramesSinceNoise;
    private final float silenceRetentionRatio;
    private final short silenceThresholdLevel;
    private long skippedFrames;
    private int state;

    private static int twoByteSampleToInt(byte b, byte b2) {
        return (b << 8) | (b2 & 255);
    }

    public SilenceSkippingAudioProcessor() {
        this(DEFAULT_MINIMUM_SILENCE_DURATION_US, 0.2f, DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US, 10, DEFAULT_SILENCE_THRESHOLD_LEVEL);
    }

    @Deprecated
    public SilenceSkippingAudioProcessor(long j, long j2, short s) {
        this(j, ((float) j2) / ((float) j), j, 0, s);
    }

    public SilenceSkippingAudioProcessor(long j, float f, long j2, int i, short s) {
        boolean z = false;
        this.outputSilenceFramesSinceNoise = 0;
        this.maybeSilenceBufferStartIndex = 0;
        this.maybeSilenceBufferContentsSize = 0;
        if (f >= 0.0f && f <= 1.0f) {
            z = true;
        }
        Assertions.checkArgument(z);
        this.minimumSilenceDurationUs = j;
        this.silenceRetentionRatio = f;
        this.maxSilenceToKeepDurationUs = j2;
        this.minVolumeToKeepPercentageWhenMuting = i;
        this.silenceThresholdLevel = s;
        this.maybeSilenceBuffer = Util.EMPTY_BYTE_ARRAY;
        this.contiguousOutputBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public long getSkippedFrames() {
        return this.skippedFrames;
    }

    /* access modifiers changed from: protected */
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat audioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        if (audioFormat.encoding == 2) {
            return audioFormat.sampleRate == -1 ? AudioProcessor.AudioFormat.NOT_SET : audioFormat;
        }
        throw new AudioProcessor.UnhandledAudioFormatException(audioFormat);
    }

    public boolean isActive() {
        return super.isActive() && this.enabled;
    }

    public void queueInput(ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining() && !hasPendingOutput()) {
            int i = this.state;
            if (i == 0) {
                processNoisy(byteBuffer);
            } else if (i == 1) {
                shortenSilenceSilenceUntilNoise(byteBuffer);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public void onQueueEndOfStream() {
        if (this.maybeSilenceBufferContentsSize > 0) {
            outputShortenedSilenceBuffer(true);
            this.outputSilenceFramesSinceNoise = 0;
        }
    }

    public void onFlush() {
        if (isActive()) {
            this.bytesPerFrame = this.inputAudioFormat.channelCount * 2;
            int alignToBytePerFrameBoundary = alignToBytePerFrameBoundary(durationUsToFrames(this.minimumSilenceDurationUs) / 2) * 2;
            if (this.maybeSilenceBuffer.length != alignToBytePerFrameBoundary) {
                this.maybeSilenceBuffer = new byte[alignToBytePerFrameBoundary];
                this.contiguousOutputBuffer = new byte[alignToBytePerFrameBoundary];
            }
        }
        this.state = 0;
        this.skippedFrames = 0;
        this.outputSilenceFramesSinceNoise = 0;
        this.maybeSilenceBufferStartIndex = 0;
        this.maybeSilenceBufferContentsSize = 0;
    }

    public void onReset() {
        this.enabled = false;
        this.maybeSilenceBuffer = Util.EMPTY_BYTE_ARRAY;
        this.contiguousOutputBuffer = Util.EMPTY_BYTE_ARRAY;
    }

    private void processNoisy(ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        byteBuffer.limit(Math.min(limit, byteBuffer.position() + this.maybeSilenceBuffer.length));
        int findNoiseLimit = findNoiseLimit(byteBuffer);
        if (findNoiseLimit == byteBuffer.position()) {
            this.state = 1;
        } else {
            byteBuffer.limit(Math.min(findNoiseLimit, byteBuffer.capacity()));
            output(byteBuffer);
        }
        byteBuffer.limit(limit);
    }

    private void shortenSilenceSilenceUntilNoise(ByteBuffer byteBuffer) {
        int i;
        int i2;
        boolean z = true;
        Assertions.checkState(this.maybeSilenceBufferStartIndex < this.maybeSilenceBuffer.length);
        int limit = byteBuffer.limit();
        int findNoisePosition = findNoisePosition(byteBuffer);
        int position = findNoisePosition - byteBuffer.position();
        int i3 = this.maybeSilenceBufferStartIndex;
        int i4 = this.maybeSilenceBufferContentsSize;
        int i5 = i3 + i4;
        byte[] bArr = this.maybeSilenceBuffer;
        if (i5 < bArr.length) {
            i = bArr.length - (i4 + i3);
            i2 = i3 + i4;
        } else {
            int length = i4 - (bArr.length - i3);
            i = i3 - length;
            i2 = length;
        }
        boolean z2 = findNoisePosition < limit;
        int min = Math.min(position, i);
        byteBuffer.limit(byteBuffer.position() + min);
        byteBuffer.get(this.maybeSilenceBuffer, i2, min);
        int i6 = this.maybeSilenceBufferContentsSize + min;
        this.maybeSilenceBufferContentsSize = i6;
        Assertions.checkState(i6 <= this.maybeSilenceBuffer.length);
        if (!z2 || position >= i) {
            z = false;
        }
        outputShortenedSilenceBuffer(z);
        if (z) {
            this.state = 0;
            this.outputSilenceFramesSinceNoise = 0;
        }
        byteBuffer.limit(limit);
    }

    private void outputShortenedSilenceBuffer(boolean z) {
        int i;
        int i2;
        int i3 = this.maybeSilenceBufferContentsSize;
        byte[] bArr = this.maybeSilenceBuffer;
        if (i3 == bArr.length || z) {
            boolean z2 = false;
            if (this.outputSilenceFramesSinceNoise == 0) {
                if (z) {
                    outputSilence(i3, 3);
                    i = i3;
                } else {
                    Assertions.checkState(i3 >= bArr.length / 2);
                    i = this.maybeSilenceBuffer.length / 2;
                    outputSilence(i, 0);
                }
                i2 = i;
            } else if (z) {
                int length = i3 - (bArr.length / 2);
                int length2 = (bArr.length / 2) + length;
                int calculateShortenedSilenceLength = calculateShortenedSilenceLength(length) + (this.maybeSilenceBuffer.length / 2);
                outputSilence(calculateShortenedSilenceLength, 2);
                int i4 = length2;
                i2 = calculateShortenedSilenceLength;
                i = i4;
            } else {
                i = i3 - (bArr.length / 2);
                i2 = calculateShortenedSilenceLength(i);
                outputSilence(i2, 1);
            }
            Assertions.checkState(i % this.bytesPerFrame == 0, "bytesConsumed is not aligned to frame size: %s" + i);
            if (i3 >= i2) {
                z2 = true;
            }
            Assertions.checkState(z2);
            this.maybeSilenceBufferContentsSize -= i;
            int i5 = this.maybeSilenceBufferStartIndex + i;
            this.maybeSilenceBufferStartIndex = i5;
            this.maybeSilenceBufferStartIndex = i5 % this.maybeSilenceBuffer.length;
            int i6 = this.outputSilenceFramesSinceNoise;
            int i7 = this.bytesPerFrame;
            this.outputSilenceFramesSinceNoise = i6 + (i2 / i7);
            this.skippedFrames += (long) ((i - i2) / i7);
        }
    }

    private int calculateShortenedSilenceLength(int i) {
        int durationUsToFrames = ((durationUsToFrames(this.maxSilenceToKeepDurationUs) - this.outputSilenceFramesSinceNoise) * this.bytesPerFrame) - (this.maybeSilenceBuffer.length / 2);
        Assertions.checkState(durationUsToFrames >= 0);
        return alignToBytePerFrameBoundary(Math.min((((float) i) * this.silenceRetentionRatio) + 0.5f, (float) durationUsToFrames));
    }

    private int alignToBytePerFrameBoundary(int i) {
        int i2 = this.bytesPerFrame;
        return (i / i2) * i2;
    }

    private int alignToBytePerFrameBoundary(float f) {
        return alignToBytePerFrameBoundary((int) f);
    }

    private void outputRange(byte[] bArr, int i, int i2) {
        Assertions.checkArgument(i % this.bytesPerFrame == 0, "byteOutput size is not aligned to frame size " + i);
        modifyVolume(bArr, i, i2);
        replaceOutputBuffer(i).put(bArr, 0, i).flip();
    }

    private void outputSilence(int i, int i2) {
        if (i != 0) {
            boolean z = true;
            Assertions.checkArgument(this.maybeSilenceBufferContentsSize >= i);
            if (i2 == 2) {
                int i3 = this.maybeSilenceBufferStartIndex;
                int i4 = this.maybeSilenceBufferContentsSize;
                int i5 = i3 + i4;
                byte[] bArr = this.maybeSilenceBuffer;
                if (i5 <= bArr.length) {
                    System.arraycopy(bArr, (i3 + i4) - i, this.contiguousOutputBuffer, 0, i);
                } else {
                    int length = i4 - (bArr.length - i3);
                    if (length >= i) {
                        System.arraycopy(bArr, length - i, this.contiguousOutputBuffer, 0, i);
                    } else {
                        int i6 = i - length;
                        System.arraycopy(bArr, bArr.length - i6, this.contiguousOutputBuffer, 0, i6);
                        System.arraycopy(this.maybeSilenceBuffer, 0, this.contiguousOutputBuffer, i6, length);
                    }
                }
            } else {
                int i7 = this.maybeSilenceBufferStartIndex;
                int i8 = i7 + i;
                byte[] bArr2 = this.maybeSilenceBuffer;
                if (i8 <= bArr2.length) {
                    System.arraycopy(bArr2, i7, this.contiguousOutputBuffer, 0, i);
                } else {
                    int length2 = bArr2.length - i7;
                    System.arraycopy(bArr2, i7, this.contiguousOutputBuffer, 0, length2);
                    System.arraycopy(this.maybeSilenceBuffer, 0, this.contiguousOutputBuffer, length2, i - length2);
                }
            }
            Assertions.checkArgument(i % this.bytesPerFrame == 0, "sizeToOutput is not aligned to frame size: " + i);
            if (this.maybeSilenceBufferStartIndex >= this.maybeSilenceBuffer.length) {
                z = false;
            }
            Assertions.checkState(z);
            outputRange(this.contiguousOutputBuffer, i, i2);
        }
    }

    private void modifyVolume(byte[] bArr, int i, int i2) {
        int i3;
        if (i2 != 3) {
            for (int i4 = 0; i4 < i; i4 += 2) {
                int twoByteSampleToInt = twoByteSampleToInt(bArr[i4 + 1], bArr[i4]);
                if (i2 == 0) {
                    i3 = calculateFadeOutPercentage(i4, i - 1);
                } else if (i2 == 2) {
                    i3 = calculateFadeInPercentage(i4, i - 1);
                } else {
                    i3 = this.minVolumeToKeepPercentageWhenMuting;
                }
                sampleIntToTwoBigEndianBytes(bArr, i4, (twoByteSampleToInt * i3) / 100);
            }
        }
    }

    private int calculateFadeOutPercentage(int i, int i2) {
        return (((this.minVolumeToKeepPercentageWhenMuting - 100) * ((i * 1000) / i2)) / 1000) + 100;
    }

    private int calculateFadeInPercentage(int i, int i2) {
        int i3 = this.minVolumeToKeepPercentageWhenMuting;
        return i3 + ((((100 - i3) * (i * 1000)) / i2) / 1000);
    }

    private static void sampleIntToTwoBigEndianBytes(byte[] bArr, int i, int i2) {
        if (i2 >= 32767) {
            bArr[i] = -1;
            bArr[i + 1] = Byte.MAX_VALUE;
        } else if (i2 <= -32768) {
            bArr[i] = 0;
            bArr[i + 1] = Byte.MIN_VALUE;
        } else {
            bArr[i] = (byte) (i2 & 255);
            bArr[i + 1] = (byte) (i2 >> 8);
        }
    }

    private void output(ByteBuffer byteBuffer) {
        replaceOutputBuffer(byteBuffer.remaining()).put(byteBuffer).flip();
    }

    private int durationUsToFrames(long j) {
        return (int) ((j * ((long) this.inputAudioFormat.sampleRate)) / 1000000);
    }

    private int findNoisePosition(ByteBuffer byteBuffer) {
        for (int position = byteBuffer.position() + 1; position < byteBuffer.limit(); position += 2) {
            if (isNoise(byteBuffer.get(position), byteBuffer.get(position - 1))) {
                int i = this.bytesPerFrame;
                return i * (position / i);
            }
        }
        return byteBuffer.limit();
    }

    private int findNoiseLimit(ByteBuffer byteBuffer) {
        for (int limit = byteBuffer.limit() - 1; limit >= byteBuffer.position(); limit -= 2) {
            if (isNoise(byteBuffer.get(limit), byteBuffer.get(limit - 1))) {
                int i = this.bytesPerFrame;
                return ((limit / i) * i) + i;
            }
        }
        return byteBuffer.position();
    }

    private boolean isNoise(byte b, byte b2) {
        return Math.abs(twoByteSampleToInt(b, b2)) > this.silenceThresholdLevel;
    }
}
