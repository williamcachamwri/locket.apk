package androidx.media3.common.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Util;
import java.nio.ByteBuffer;

public final class AudioMixingUtil {
    private static final float FLOAT_PCM_MAX_VALUE = 1.0f;
    private static final float FLOAT_PCM_MIN_VALUE = -1.0f;

    private static float int16SampleToFloatPcm(short s) {
        return ((float) s) / ((float) (s < 0 ? 32768 : 32767));
    }

    public static boolean canMix(AudioProcessor.AudioFormat audioFormat) {
        if (audioFormat.sampleRate == -1 || audioFormat.channelCount == -1) {
            return false;
        }
        if (audioFormat.encoding == 2 || audioFormat.encoding == 4) {
            return true;
        }
        return false;
    }

    public static boolean canMix(AudioProcessor.AudioFormat audioFormat, AudioProcessor.AudioFormat audioFormat2) {
        if (audioFormat.sampleRate == audioFormat2.sampleRate && canMix(audioFormat) && canMix(audioFormat2)) {
            return true;
        }
        return false;
    }

    public static ByteBuffer mix(ByteBuffer byteBuffer, AudioProcessor.AudioFormat audioFormat, ByteBuffer byteBuffer2, AudioProcessor.AudioFormat audioFormat2, ChannelMixingMatrix channelMixingMatrix, int i, boolean z, boolean z2) {
        boolean z3;
        AudioProcessor.AudioFormat audioFormat3;
        float f;
        ByteBuffer byteBuffer3 = byteBuffer2;
        boolean z4 = true;
        if (audioFormat.encoding == 2) {
            audioFormat3 = audioFormat2;
            z3 = true;
        } else {
            audioFormat3 = audioFormat2;
            z3 = false;
        }
        if (audioFormat3.encoding != 2) {
            z4 = false;
        }
        int inputChannelCount = channelMixingMatrix.getInputChannelCount();
        int outputChannelCount = channelMixingMatrix.getOutputChannelCount();
        float[] fArr = new float[inputChannelCount];
        float[] fArr2 = new float[outputChannelCount];
        int i2 = i;
        for (int i3 = 0; i3 < i2; i3++) {
            if (z) {
                int position = byteBuffer2.position();
                for (int i4 = 0; i4 < outputChannelCount; i4++) {
                    fArr2[i4] = getPcmSample(byteBuffer3, z4, z4);
                }
                byteBuffer3.position(position);
            }
            for (int i5 = 0; i5 < inputChannelCount; i5++) {
                fArr[i5] = getPcmSample(byteBuffer, z3, z4);
            }
            ByteBuffer byteBuffer4 = byteBuffer;
            for (int i6 = 0; i6 < outputChannelCount; i6++) {
                for (int i7 = 0; i7 < inputChannelCount; i7++) {
                    fArr2[i6] = fArr2[i6] + (fArr[i7] * channelMixingMatrix.getMixingCoefficient(i7, i6));
                }
                ChannelMixingMatrix channelMixingMatrix2 = channelMixingMatrix;
                if (z4) {
                    byteBuffer3.putShort((short) ((int) Util.constrainValue(fArr2[i6], -32768.0f, 32767.0f)));
                } else {
                    if (z2) {
                        f = Util.constrainValue(fArr2[i6], (float) FLOAT_PCM_MIN_VALUE, 1.0f);
                    } else {
                        f = fArr2[i6];
                    }
                    byteBuffer3.putFloat(f);
                }
                fArr2[i6] = 0.0f;
            }
            ChannelMixingMatrix channelMixingMatrix3 = channelMixingMatrix;
        }
        return byteBuffer3;
    }

    private static float getPcmSample(ByteBuffer byteBuffer, boolean z, boolean z2) {
        return z2 ? z ? (float) byteBuffer.getShort() : floatSampleToInt16Pcm(byteBuffer.getFloat()) : z ? int16SampleToFloatPcm(byteBuffer.getShort()) : byteBuffer.getFloat();
    }

    private static float floatSampleToInt16Pcm(float f) {
        return Util.constrainValue(f * ((float) (f < 0.0f ? 32768 : 32767)), -32768.0f, 32767.0f);
    }

    private AudioMixingUtil() {
    }
}
