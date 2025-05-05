package androidx.media3.common.audio;

import androidx.media3.common.util.Assertions;
import java.nio.ShortBuffer;
import java.util.Arrays;

final class Sonic {
    private static final int AMDF_FREQUENCY = 4000;
    private static final int BYTES_PER_SAMPLE = 2;
    private static final int MAXIMUM_PITCH = 400;
    private static final int MINIMUM_PITCH = 65;
    private double accumulatedSpeedAdjustmentError;
    private final int channelCount;
    private final short[] downSampleBuffer;
    private short[] inputBuffer;
    private int inputFrameCount;
    private final int inputSampleRateHz;
    private int maxDiff;
    private final int maxPeriod;
    private final int maxRequiredFrameCount;
    private int minDiff;
    private final int minPeriod;
    private int newRatePosition;
    private int oldRatePosition;
    private short[] outputBuffer;
    private int outputFrameCount;
    private final float pitch;
    private short[] pitchBuffer;
    private int pitchFrameCount;
    private int prevMinDiff;
    private int prevPeriod;
    private final float rate;
    private int remainingInputToCopyFrameCount;
    private final float speed;

    public Sonic(int i, int i2, float f, float f2, int i3) {
        this.inputSampleRateHz = i;
        this.channelCount = i2;
        this.speed = f;
        this.pitch = f2;
        this.rate = ((float) i) / ((float) i3);
        this.minPeriod = i / 400;
        int i4 = i / 65;
        this.maxPeriod = i4;
        int i5 = i4 * 2;
        this.maxRequiredFrameCount = i5;
        this.downSampleBuffer = new short[i5];
        this.inputBuffer = new short[(i5 * i2)];
        this.outputBuffer = new short[(i5 * i2)];
        this.pitchBuffer = new short[(i5 * i2)];
    }

    public int getPendingInputBytes() {
        return this.inputFrameCount * this.channelCount * 2;
    }

    public void queueInput(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        int i = this.channelCount;
        int i2 = remaining / i;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.inputBuffer, this.inputFrameCount, i2);
        this.inputBuffer = ensureSpaceForAdditionalFrames;
        shortBuffer.get(ensureSpaceForAdditionalFrames, this.inputFrameCount * this.channelCount, ((i * i2) * 2) / 2);
        this.inputFrameCount += i2;
        processStreamInput();
    }

    public void getOutput(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.channelCount, this.outputFrameCount);
        shortBuffer.put(this.outputBuffer, 0, this.channelCount * min);
        int i = this.outputFrameCount - min;
        this.outputFrameCount = i;
        short[] sArr = this.outputBuffer;
        int i2 = this.channelCount;
        System.arraycopy(sArr, min * i2, sArr, 0, i * i2);
    }

    public void queueEndOfStream() {
        int i;
        int i2 = this.inputFrameCount;
        float f = this.speed;
        float f2 = this.pitch;
        int i3 = this.remainingInputToCopyFrameCount;
        int i4 = this.outputFrameCount + ((int) ((((((((double) (i2 - i3)) / ((double) (f / f2))) + ((double) i3)) + this.accumulatedSpeedAdjustmentError) + ((double) this.pitchFrameCount)) / ((double) (this.rate * f2))) + 0.5d));
        this.accumulatedSpeedAdjustmentError = 0.0d;
        this.inputBuffer = ensureSpaceForAdditionalFrames(this.inputBuffer, i2, (this.maxRequiredFrameCount * 2) + i2);
        int i5 = 0;
        while (true) {
            i = this.maxRequiredFrameCount;
            int i6 = this.channelCount;
            if (i5 >= i * 2 * i6) {
                break;
            }
            this.inputBuffer[(i6 * i2) + i5] = 0;
            i5++;
        }
        this.inputFrameCount += i * 2;
        processStreamInput();
        if (this.outputFrameCount > i4) {
            this.outputFrameCount = i4;
        }
        this.inputFrameCount = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.pitchFrameCount = 0;
    }

    public void flush() {
        this.inputFrameCount = 0;
        this.outputFrameCount = 0;
        this.pitchFrameCount = 0;
        this.oldRatePosition = 0;
        this.newRatePosition = 0;
        this.remainingInputToCopyFrameCount = 0;
        this.prevPeriod = 0;
        this.prevMinDiff = 0;
        this.minDiff = 0;
        this.maxDiff = 0;
        this.accumulatedSpeedAdjustmentError = 0.0d;
    }

    public int getOutputSize() {
        return this.outputFrameCount * this.channelCount * 2;
    }

    private short[] ensureSpaceForAdditionalFrames(short[] sArr, int i, int i2) {
        int length = sArr.length;
        int i3 = this.channelCount;
        int i4 = length / i3;
        if (i + i2 <= i4) {
            return sArr;
        }
        return Arrays.copyOf(sArr, (((i4 * 3) / 2) + i2) * i3);
    }

    private void removeProcessedInputFrames(int i) {
        int i2 = this.inputFrameCount - i;
        short[] sArr = this.inputBuffer;
        int i3 = this.channelCount;
        System.arraycopy(sArr, i * i3, sArr, 0, i3 * i2);
        this.inputFrameCount = i2;
    }

    private void copyToOutput(short[] sArr, int i, int i2) {
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i2);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        int i3 = this.channelCount;
        System.arraycopy(sArr, i * i3, ensureSpaceForAdditionalFrames, this.outputFrameCount * i3, i3 * i2);
        this.outputFrameCount += i2;
    }

    private int copyInputToOutput(int i) {
        int min = Math.min(this.maxRequiredFrameCount, this.remainingInputToCopyFrameCount);
        copyToOutput(this.inputBuffer, i, min);
        this.remainingInputToCopyFrameCount -= min;
        return min;
    }

    private void downSampleInput(short[] sArr, int i, int i2) {
        int i3 = this.maxRequiredFrameCount / i2;
        int i4 = this.channelCount;
        int i5 = i2 * i4;
        int i6 = i * i4;
        for (int i7 = 0; i7 < i3; i7++) {
            int i8 = 0;
            for (int i9 = 0; i9 < i5; i9++) {
                i8 += sArr[(i7 * i5) + i6 + i9];
            }
            this.downSampleBuffer[i7] = (short) (i8 / i5);
        }
    }

    private int findPitchPeriodInRange(short[] sArr, int i, int i2, int i3) {
        int i4 = i * this.channelCount;
        int i5 = 255;
        int i6 = 1;
        int i7 = 0;
        int i8 = 0;
        while (i2 <= i3) {
            int i9 = 0;
            for (int i10 = 0; i10 < i2; i10++) {
                i9 += Math.abs(sArr[i4 + i10] - sArr[(i4 + i2) + i10]);
            }
            if (i9 * i7 < i6 * i2) {
                i7 = i2;
                i6 = i9;
            }
            if (i9 * i5 > i8 * i2) {
                i5 = i2;
                i8 = i9;
            }
            i2++;
        }
        this.minDiff = i6 / i7;
        this.maxDiff = i8 / i5;
        return i7;
    }

    private boolean previousPeriodBetter(int i, int i2) {
        if (i == 0 || this.prevPeriod == 0 || i2 > i * 3 || i * 2 <= this.prevMinDiff * 3) {
            return false;
        }
        return true;
    }

    private int findPitchPeriod(short[] sArr, int i) {
        int i2;
        int i3 = this.inputSampleRateHz;
        int i4 = i3 > AMDF_FREQUENCY ? i3 / AMDF_FREQUENCY : 1;
        if (this.channelCount == 1 && i4 == 1) {
            i2 = findPitchPeriodInRange(sArr, i, this.minPeriod, this.maxPeriod);
        } else {
            downSampleInput(sArr, i, i4);
            int findPitchPeriodInRange = findPitchPeriodInRange(this.downSampleBuffer, 0, this.minPeriod / i4, this.maxPeriod / i4);
            if (i4 != 1) {
                int i5 = findPitchPeriodInRange * i4;
                int i6 = i4 * 4;
                int i7 = i5 - i6;
                int i8 = i5 + i6;
                int i9 = this.minPeriod;
                if (i7 < i9) {
                    i7 = i9;
                }
                int i10 = this.maxPeriod;
                if (i8 > i10) {
                    i8 = i10;
                }
                if (this.channelCount == 1) {
                    i2 = findPitchPeriodInRange(sArr, i, i7, i8);
                } else {
                    downSampleInput(sArr, i, 1);
                    i2 = findPitchPeriodInRange(this.downSampleBuffer, 0, i7, i8);
                }
            } else {
                i2 = findPitchPeriodInRange;
            }
        }
        int i11 = previousPeriodBetter(this.minDiff, this.maxDiff) ? this.prevPeriod : i2;
        this.prevMinDiff = this.minDiff;
        this.prevPeriod = i2;
        return i11;
    }

    private void moveNewSamplesToPitchBuffer(int i) {
        int i2 = this.outputFrameCount - i;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.pitchBuffer, this.pitchFrameCount, i2);
        this.pitchBuffer = ensureSpaceForAdditionalFrames;
        short[] sArr = this.outputBuffer;
        int i3 = this.channelCount;
        System.arraycopy(sArr, i * i3, ensureSpaceForAdditionalFrames, this.pitchFrameCount * i3, i3 * i2);
        this.outputFrameCount = i;
        this.pitchFrameCount += i2;
    }

    private void removePitchFrames(int i) {
        if (i != 0) {
            short[] sArr = this.pitchBuffer;
            int i2 = this.channelCount;
            System.arraycopy(sArr, i * i2, sArr, 0, (this.pitchFrameCount - i) * i2);
            this.pitchFrameCount -= i;
        }
    }

    private short interpolate(short[] sArr, int i, long j, long j2) {
        short s = sArr[i];
        short s2 = sArr[i + this.channelCount];
        long j3 = ((long) this.newRatePosition) * j;
        int i2 = this.oldRatePosition;
        long j4 = ((long) i2) * j2;
        long j5 = ((long) (i2 + 1)) * j2;
        long j6 = j5 - j3;
        long j7 = j5 - j4;
        return (short) ((int) (((((long) s) * j6) + ((j7 - j6) * ((long) s2))) / j7));
    }

    private void adjustRate(float f, int i) {
        int i2;
        int i3;
        int i4 = i;
        if (this.outputFrameCount != i4) {
            int i5 = this.inputSampleRateHz;
            long j = (long) (((float) i5) / f);
            long j2 = (long) i5;
            while (j != 0 && j2 != 0 && j % 2 == 0 && j2 % 2 == 0) {
                j /= 2;
                j2 /= 2;
            }
            moveNewSamplesToPitchBuffer(i4);
            int i6 = 0;
            while (true) {
                int i7 = this.pitchFrameCount;
                boolean z = true;
                if (i6 < i7 - 1) {
                    while (true) {
                        i2 = this.oldRatePosition;
                        i3 = this.newRatePosition;
                        if (((long) (i2 + 1)) * j <= ((long) i3) * j2) {
                            break;
                        }
                        this.outputBuffer = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, 1);
                        int i8 = 0;
                        while (true) {
                            int i9 = this.channelCount;
                            if (i8 >= i9) {
                                break;
                            }
                            short[] sArr = this.outputBuffer;
                            sArr[(this.outputFrameCount * i9) + i8] = interpolate(this.pitchBuffer, (i9 * i6) + i8, j2, j);
                            i8++;
                        }
                        this.newRatePosition++;
                        this.outputFrameCount++;
                    }
                    int i10 = i2 + 1;
                    this.oldRatePosition = i10;
                    if (((long) i10) == j2) {
                        this.oldRatePosition = 0;
                        if (((long) i3) != j) {
                            z = false;
                        }
                        Assertions.checkState(z);
                        this.newRatePosition = 0;
                    }
                    i6++;
                } else {
                    removePitchFrames(i7 - 1);
                    return;
                }
            }
        }
    }

    private int skipPitchPeriod(short[] sArr, int i, double d, int i2) {
        int i3;
        int i4 = i2;
        if (d >= 2.0d) {
            double d2 = (((double) i4) / (d - 1.0d)) + this.accumulatedSpeedAdjustmentError;
            i3 = (int) Math.round(d2);
            this.accumulatedSpeedAdjustmentError = d2 - ((double) i3);
        } else {
            double d3 = ((((double) i4) * (2.0d - d)) / (d - 1.0d)) + this.accumulatedSpeedAdjustmentError;
            int round = (int) Math.round(d3);
            this.remainingInputToCopyFrameCount = round;
            this.accumulatedSpeedAdjustmentError = d3 - ((double) round);
            i3 = i4;
        }
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i3);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        overlapAdd(i3, this.channelCount, ensureSpaceForAdditionalFrames, this.outputFrameCount, sArr, i, sArr, i + i4);
        this.outputFrameCount += i3;
        return i3;
    }

    private int insertPitchPeriod(short[] sArr, int i, double d, int i2) {
        int i3;
        int i4 = i2;
        if (d < 0.5d) {
            double d2 = ((((double) i4) * d) / (1.0d - d)) + this.accumulatedSpeedAdjustmentError;
            int round = (int) Math.round(d2);
            this.accumulatedSpeedAdjustmentError = d2 - ((double) round);
            i3 = round;
        } else {
            double d3 = ((((double) i4) * ((2.0d * d) - 1.0d)) / (1.0d - d)) + this.accumulatedSpeedAdjustmentError;
            int round2 = (int) Math.round(d3);
            this.remainingInputToCopyFrameCount = round2;
            this.accumulatedSpeedAdjustmentError = d3 - ((double) round2);
            i3 = i4;
        }
        int i5 = i4 + i3;
        short[] ensureSpaceForAdditionalFrames = ensureSpaceForAdditionalFrames(this.outputBuffer, this.outputFrameCount, i5);
        this.outputBuffer = ensureSpaceForAdditionalFrames;
        int i6 = this.channelCount;
        System.arraycopy(sArr, i * i6, ensureSpaceForAdditionalFrames, this.outputFrameCount * i6, i6 * i4);
        overlapAdd(i3, this.channelCount, this.outputBuffer, this.outputFrameCount + i4, sArr, i + i4, sArr, i);
        this.outputFrameCount += i5;
        return i3;
    }

    private void changeSpeed(double d) {
        int insertPitchPeriod;
        int i = this.inputFrameCount;
        if (i >= this.maxRequiredFrameCount) {
            int i2 = 0;
            do {
                if (this.remainingInputToCopyFrameCount > 0) {
                    insertPitchPeriod = copyInputToOutput(i2);
                } else {
                    int findPitchPeriod = findPitchPeriod(this.inputBuffer, i2);
                    if (d > 1.0d) {
                        i2 += findPitchPeriod + skipPitchPeriod(this.inputBuffer, i2, d, findPitchPeriod);
                    } else {
                        insertPitchPeriod = insertPitchPeriod(this.inputBuffer, i2, d, findPitchPeriod);
                    }
                }
                i2 += insertPitchPeriod;
            } while (this.maxRequiredFrameCount + i2 <= i);
            removeProcessedInputFrames(i2);
        }
    }

    private void processStreamInput() {
        int i = this.outputFrameCount;
        float f = this.speed;
        float f2 = this.pitch;
        double d = (double) (f / f2);
        float f3 = this.rate * f2;
        if (d > 1.00001d || d < 0.99999d) {
            changeSpeed(d);
        } else {
            copyToOutput(this.inputBuffer, 0, this.inputFrameCount);
            this.inputFrameCount = 0;
        }
        if (f3 != 1.0f) {
            adjustRate(f3, i);
        }
    }

    private static void overlapAdd(int i, int i2, short[] sArr, int i3, short[] sArr2, int i4, short[] sArr3, int i5) {
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = (i3 * i2) + i6;
            int i8 = (i5 * i2) + i6;
            int i9 = (i4 * i2) + i6;
            for (int i10 = 0; i10 < i; i10++) {
                sArr[i7] = (short) (((sArr2[i9] * (i - i10)) + (sArr3[i8] * i10)) / i);
                i7 += i2;
                i9 += i2;
                i8 += i2;
            }
        }
    }
}
