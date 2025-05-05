package androidx.camera.video.internal;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build;
import android.text.TextUtils;
import androidx.camera.core.Logger;
import androidx.camera.video.internal.compat.Api28Impl;
import androidx.camera.video.internal.compat.Api31Impl;
import androidx.core.util.Preconditions;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public final class DebugUtils {
    private static final String AUDIO_CAPS_PREFIX = "[AudioCaps] ";
    private static final String CODEC_CAPS_PREFIX = "[CodecCaps] ";
    private static final String ENCODER_CAPS_PREFIX = "[EncoderCaps] ";
    private static final String TAG = "DebugUtils";
    private static final String VIDEO_CAPS_PREFIX = "[VideoCaps] ";

    private DebugUtils() {
    }

    public static String readableUs(long j) {
        return readableMs(TimeUnit.MICROSECONDS.toMillis(j));
    }

    public static String readableMs(long j) {
        return formatInterval(j);
    }

    public static String readableBufferInfo(MediaCodec.BufferInfo bufferInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Dump BufferInfo: " + bufferInfo.toString() + "\n");
        sb.append("\toffset: " + bufferInfo.offset + "\n");
        sb.append("\tsize: " + bufferInfo.size + "\n");
        sb.append("\tflag: " + bufferInfo.flags);
        ArrayList arrayList = new ArrayList();
        if ((bufferInfo.flags & 4) != 0) {
            arrayList.add("EOS");
        }
        if ((bufferInfo.flags & 2) != 0) {
            arrayList.add("CODEC_CONFIG");
        }
        if ((bufferInfo.flags & 1) != 0) {
            arrayList.add("KEY_FRAME");
        }
        if ((bufferInfo.flags & 8) != 0) {
            arrayList.add("PARTIAL_FRAME");
        }
        if (!arrayList.isEmpty()) {
            sb.append(" (").append(TextUtils.join(" | ", arrayList)).append(")");
        }
        sb.append("\n");
        sb.append("\tpresentationTime: " + bufferInfo.presentationTimeUs + " (" + readableUs(bufferInfo.presentationTimeUs) + ")\n");
        return sb.toString();
    }

    private static String formatInterval(long j) {
        long hours = TimeUnit.MILLISECONDS.toHours(j);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(j - TimeUnit.HOURS.toMillis(hours));
        long seconds = TimeUnit.MILLISECONDS.toSeconds((j - TimeUnit.HOURS.toMillis(hours)) - TimeUnit.MINUTES.toMillis(minutes));
        return String.format(Locale.US, "%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds), Long.valueOf(((j - TimeUnit.HOURS.toMillis(hours)) - TimeUnit.MINUTES.toMillis(minutes)) - TimeUnit.SECONDS.toMillis(seconds))});
    }

    public static String dumpMediaCodecListForFormat(MediaCodecList mediaCodecList, MediaFormat mediaFormat) {
        StringBuilder sb = new StringBuilder();
        logToString(sb, "[Start] Dump MediaCodecList for mediaFormat " + mediaFormat);
        String string = mediaFormat.getString("mime");
        for (MediaCodecInfo mediaCodecInfo : mediaCodecList.getCodecInfos()) {
            if (mediaCodecInfo.isEncoder()) {
                boolean z = true;
                try {
                    Preconditions.checkArgument(string != null);
                    MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(string);
                    if (capabilitiesForType == null) {
                        z = false;
                    }
                    Preconditions.checkArgument(z);
                    logToString(sb, "[Start] [" + mediaCodecInfo.getName() + "]");
                    dumpCodecCapabilities(sb, capabilitiesForType, mediaFormat);
                    logToString(sb, "[End] [" + mediaCodecInfo.getName() + "]");
                } catch (IllegalArgumentException unused) {
                    logToString(sb, "[" + mediaCodecInfo.getName() + "] does not support mime " + string);
                }
            }
        }
        logToString(sb, "[End] Dump MediaCodecList");
        String sb2 = sb.toString();
        stringToLog(sb2);
        return sb2;
    }

    public static String dumpCodecCapabilities(String str, MediaCodec mediaCodec, MediaFormat mediaFormat) {
        StringBuilder sb = new StringBuilder();
        try {
            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodec.getCodecInfo().getCapabilitiesForType(str);
            Preconditions.checkArgument(capabilitiesForType != null);
            dumpCodecCapabilities(sb, capabilitiesForType, mediaFormat);
        } catch (IllegalArgumentException unused) {
            logToString(sb, "[" + mediaCodec.getName() + "] does not support mime " + str);
        }
        return sb.toString();
    }

    private static void dumpCodecCapabilities(StringBuilder sb, MediaCodecInfo.CodecCapabilities codecCapabilities, MediaFormat mediaFormat) {
        try {
            logToString(sb, "[CodecCaps] isFormatSupported = " + codecCapabilities.isFormatSupported(mediaFormat));
        } catch (ClassCastException unused) {
            logToString(sb, "[CodecCaps] isFormatSupported=false");
        }
        logToString(sb, "[CodecCaps] getDefaultFormat = " + codecCapabilities.getDefaultFormat());
        if (codecCapabilities.profileLevels != null) {
            StringBuilder sb2 = new StringBuilder("[");
            ArrayList arrayList = new ArrayList();
            for (MediaCodecInfo.CodecProfileLevel debugUtils : codecCapabilities.profileLevels) {
                arrayList.add(toString(debugUtils));
            }
            sb2.append(TextUtils.join(", ", arrayList)).append("]");
            logToString(sb, "[CodecCaps] profileLevels = " + sb2);
        }
        if (codecCapabilities.colorFormats != null) {
            logToString(sb, "[CodecCaps] colorFormats = " + Arrays.toString(codecCapabilities.colorFormats));
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities != null) {
            dumpVideoCapabilities(sb, videoCapabilities, mediaFormat);
        }
        MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
        if (audioCapabilities != null) {
            dumpAudioCapabilities(sb, audioCapabilities, mediaFormat);
        }
        MediaCodecInfo.EncoderCapabilities encoderCapabilities = codecCapabilities.getEncoderCapabilities();
        if (encoderCapabilities != null) {
            dumpEncoderCapabilities(sb, encoderCapabilities, mediaFormat);
        }
    }

    private static void dumpVideoCapabilities(StringBuilder sb, MediaCodecInfo.VideoCapabilities videoCapabilities, MediaFormat mediaFormat) {
        boolean z;
        int i;
        int i2;
        logToString(sb, "[VideoCaps] getBitrateRange = " + videoCapabilities.getBitrateRange());
        logToString(sb, "[VideoCaps] getSupportedWidths = " + videoCapabilities.getSupportedWidths() + ", getWidthAlignment = " + videoCapabilities.getWidthAlignment());
        logToString(sb, "[VideoCaps] getSupportedHeights = " + videoCapabilities.getSupportedHeights() + ", getHeightAlignment = " + videoCapabilities.getHeightAlignment());
        int i3 = 0;
        boolean z2 = true;
        try {
            i2 = mediaFormat.getInteger("width");
            i = mediaFormat.getInteger("height");
            Preconditions.checkArgument(i2 > 0 && i > 0);
            z = true;
        } catch (IllegalArgumentException | NullPointerException unused) {
            logToString(sb, "[VideoCaps] mediaFormat does not contain valid width and height");
            i2 = 0;
            i = 0;
            z = false;
        }
        if (z) {
            try {
                logToString(sb, "[VideoCaps] getSupportedHeightsFor " + i2 + " = " + videoCapabilities.getSupportedHeightsFor(i2));
            } catch (IllegalArgumentException unused2) {
                logToString(sb, "[VideoCaps] could not getSupportedHeightsFor " + i2);
            }
            try {
                logToString(sb, "[VideoCaps] getSupportedWidthsFor " + i + " = " + videoCapabilities.getSupportedWidthsFor(i));
            } catch (IllegalArgumentException unused3) {
                logToString(sb, "[VideoCaps] could not getSupportedWidthsFor " + i);
            }
            logToString(sb, "[VideoCaps] isSizeSupported for " + i2 + ViewHierarchyNode.JsonKeys.X + i + " = " + videoCapabilities.isSizeSupported(i2, i));
        }
        logToString(sb, "[VideoCaps] getSupportedFrameRates = " + videoCapabilities.getSupportedFrameRates());
        try {
            int integer = mediaFormat.getInteger("frame-rate");
            if (integer <= 0) {
                z2 = false;
            }
            Preconditions.checkArgument(z2);
            i3 = integer;
        } catch (IllegalArgumentException | NullPointerException unused4) {
            logToString(sb, "[VideoCaps] mediaFormat does not contain frame rate");
        }
        if (z) {
            logToString(sb, "[VideoCaps] getSupportedFrameRatesFor " + i2 + ViewHierarchyNode.JsonKeys.X + i + " = " + videoCapabilities.getSupportedFrameRatesFor(i2, i));
        }
        if (z && i3 > 0) {
            logToString(sb, "[VideoCaps] areSizeAndRateSupported for " + i2 + ViewHierarchyNode.JsonKeys.X + i + ", " + i3 + " = " + videoCapabilities.areSizeAndRateSupported(i2, i, (double) i3));
        }
    }

    private static void dumpAudioCapabilities(StringBuilder sb, MediaCodecInfo.AudioCapabilities audioCapabilities, MediaFormat mediaFormat) {
        logToString(sb, "[AudioCaps] getBitrateRange = " + audioCapabilities.getBitrateRange());
        logToString(sb, "[AudioCaps] getMaxInputChannelCount = " + audioCapabilities.getMaxInputChannelCount());
        if (Build.VERSION.SDK_INT >= 31) {
            logToString(sb, "[AudioCaps] getMinInputChannelCount = " + Api31Impl.getMinInputChannelCount(audioCapabilities));
            logToString(sb, "[AudioCaps] getInputChannelCountRanges = " + Arrays.toString(Api31Impl.getInputChannelCountRanges(audioCapabilities)));
        }
        logToString(sb, "[AudioCaps] getSupportedSampleRateRanges = " + Arrays.toString(audioCapabilities.getSupportedSampleRateRanges()));
        logToString(sb, "[AudioCaps] getSupportedSampleRates = " + Arrays.toString(audioCapabilities.getSupportedSampleRates()));
        try {
            int integer = mediaFormat.getInteger("sample-rate");
            logToString(sb, "[AudioCaps] isSampleRateSupported for " + integer + " = " + audioCapabilities.isSampleRateSupported(integer));
        } catch (IllegalArgumentException | NullPointerException unused) {
            logToString(sb, "[AudioCaps] mediaFormat does not contain sample rate");
        }
    }

    private static void dumpEncoderCapabilities(StringBuilder sb, MediaCodecInfo.EncoderCapabilities encoderCapabilities, MediaFormat mediaFormat) {
        logToString(sb, "[EncoderCaps] getComplexityRange = " + encoderCapabilities.getComplexityRange());
        if (Build.VERSION.SDK_INT >= 28) {
            logToString(sb, "[EncoderCaps] getQualityRange = " + Api28Impl.getQualityRange(encoderCapabilities));
        }
        try {
            logToString(sb, "[EncoderCaps] isBitrateModeSupported = " + encoderCapabilities.isBitrateModeSupported(mediaFormat.getInteger("bitrate-mode")));
        } catch (IllegalArgumentException | NullPointerException unused) {
            logToString(sb, "[EncoderCaps] mediaFormat does not contain bitrate mode");
        }
    }

    private static void logToString(StringBuilder sb, String str) {
        sb.append(str);
        sb.append("\n");
    }

    private static void stringToLog(String str) {
        if (Logger.isInfoEnabled(TAG)) {
            Scanner scanner = new Scanner(str);
            while (scanner.hasNextLine()) {
                Logger.i(TAG, scanner.nextLine());
            }
        }
    }

    private static String toString(MediaCodecInfo.CodecProfileLevel codecProfileLevel) {
        return codecProfileLevel == null ? "null" : String.format("{level=%d, profile=%d}", new Object[]{Integer.valueOf(codecProfileLevel.level), Integer.valueOf(codecProfileLevel.profile)});
    }
}
