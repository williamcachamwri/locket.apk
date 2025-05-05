package androidx.media3.transformer;

import android.content.Context;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Build;
import android.util.Size;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Util;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.ExportException;
import androidx.media3.transformer.VideoEncoderSettings;
import com.google.android.gms.common.Scopes;
import com.google.common.collect.ImmutableList;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class DefaultEncoderFactory implements Codec.EncoderFactory {
    private static final int DEFAULT_AUDIO_BITRATE = 131072;
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final int PRIORITY_BEST_EFFORT = 1;
    private final int codecPriority;
    private final Context context;
    private final boolean enableFallback;
    private final AudioEncoderSettings requestedAudioEncoderSettings;
    private final VideoEncoderSettings requestedVideoEncoderSettings;
    private final EncoderSelector videoEncoderSelector;

    private interface EncoderFallbackCost {
        int getParameterSupportGap(MediaCodecInfo mediaCodecInfo);
    }

    private static int getSuggestedBitrate(int i, int i2, float f) {
        return (int) (((double) (((float) (i * i2)) * f)) * 0.07d * 2.0d);
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public int codecPriority = C.PRIORITY_PROCESSING_FOREGROUND;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public boolean enableFallback = true;
        /* access modifiers changed from: private */
        public AudioEncoderSettings requestedAudioEncoderSettings = AudioEncoderSettings.DEFAULT;
        /* access modifiers changed from: private */
        public VideoEncoderSettings requestedVideoEncoderSettings = VideoEncoderSettings.DEFAULT;
        /* access modifiers changed from: private */
        public EncoderSelector videoEncoderSelector = EncoderSelector.DEFAULT;

        public Builder(Context context2) {
            this.context = context2.getApplicationContext();
        }

        public Builder setVideoEncoderSelector(EncoderSelector encoderSelector) {
            this.videoEncoderSelector = encoderSelector;
            return this;
        }

        public Builder setRequestedVideoEncoderSettings(VideoEncoderSettings videoEncoderSettings) {
            this.requestedVideoEncoderSettings = videoEncoderSettings;
            return this;
        }

        public Builder setRequestedAudioEncoderSettings(AudioEncoderSettings audioEncoderSettings) {
            this.requestedAudioEncoderSettings = audioEncoderSettings;
            return this;
        }

        public Builder setEnableFallback(boolean z) {
            this.enableFallback = z;
            return this;
        }

        public Builder setCodecPriority(int i) {
            this.codecPriority = i;
            return this;
        }

        public DefaultEncoderFactory build() {
            return new DefaultEncoderFactory(this);
        }
    }

    private DefaultEncoderFactory(Builder builder) {
        this.context = builder.context;
        this.videoEncoderSelector = builder.videoEncoderSelector;
        this.requestedVideoEncoderSettings = builder.requestedVideoEncoderSettings;
        this.requestedAudioEncoderSettings = builder.requestedAudioEncoderSettings;
        this.enableFallback = builder.enableFallback;
        this.codecPriority = builder.codecPriority;
    }

    public DefaultCodec createForAudioEncoding(Format format) throws ExportException {
        if (format.bitrate == -1) {
            format = format.buildUpon().setAverageBitrate(131072).build();
        }
        Format format2 = format;
        int i = 0;
        if (format2.sampleMimeType != null) {
            MediaFormat createMediaFormatFromFormat = MediaFormatUtil.createMediaFormatFromFormat(format2);
            ImmutableList<MediaCodecInfo> supportedEncoders = EncoderUtil.getSupportedEncoders(format2.sampleMimeType);
            if (!supportedEncoders.isEmpty()) {
                MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) supportedEncoders.get(0);
                if (this.requestedAudioEncoderSettings.profile != -1) {
                    while (true) {
                        if (i >= supportedEncoders.size()) {
                            break;
                        }
                        MediaCodecInfo mediaCodecInfo2 = (MediaCodecInfo) supportedEncoders.get(i);
                        if (EncoderUtil.findSupportedEncodingProfiles(mediaCodecInfo2, format2.sampleMimeType).contains(Integer.valueOf(this.requestedAudioEncoderSettings.profile))) {
                            if (format2.sampleMimeType.equals(MimeTypes.AUDIO_AAC)) {
                                createMediaFormatFromFormat.setInteger("aac-profile", this.requestedAudioEncoderSettings.profile);
                            }
                            createMediaFormatFromFormat.setInteger(Scopes.PROFILE, this.requestedAudioEncoderSettings.profile);
                            mediaCodecInfo = mediaCodecInfo2;
                        } else {
                            i++;
                        }
                    }
                }
                if (this.requestedAudioEncoderSettings.bitrate != -1) {
                    createMediaFormatFromFormat.setInteger("bitrate", this.requestedAudioEncoderSettings.bitrate);
                }
                return new DefaultCodec(this.context, format2, createMediaFormatFromFormat, mediaCodecInfo.getName(), false, (Surface) null);
            }
            throw createExportException(format2, "No audio media codec found");
        }
        throw createNoSupportedMimeTypeException(format2, false);
    }

    public DefaultCodec createForVideoEncoding(Format format) throws ExportException {
        int i;
        if (format.frameRate == -1.0f || deviceNeedsDefaultFrameRateWorkaround()) {
            format = format.buildUpon().setFrameRate(30.0f).build();
        }
        int i2 = 1;
        if (format.sampleMimeType != null) {
            Assertions.checkArgument(format.width != -1);
            Assertions.checkArgument(format.height != -1);
            Assertions.checkArgument(format.rotationDegrees == 0);
            Assertions.checkStateNotNull(this.videoEncoderSelector);
            VideoEncoderQueryResult findEncoderWithClosestSupportedFormat = findEncoderWithClosestSupportedFormat(format, this.requestedVideoEncoderSettings, this.videoEncoderSelector, this.enableFallback);
            if (findEncoderWithClosestSupportedFormat != null) {
                MediaCodecInfo mediaCodecInfo = findEncoderWithClosestSupportedFormat.encoder;
                Format format2 = findEncoderWithClosestSupportedFormat.supportedFormat;
                VideoEncoderSettings videoEncoderSettings = findEncoderWithClosestSupportedFormat.supportedEncoderSettings;
                String str = (String) Assertions.checkNotNull(format2.sampleMimeType);
                if (this.enableFallback) {
                    i = videoEncoderSettings.bitrate;
                } else if (videoEncoderSettings.bitrate != -1) {
                    i = videoEncoderSettings.bitrate;
                } else if (videoEncoderSettings.enableHighQualityTargeting) {
                    i = new DeviceMappedEncoderBitrateProvider().getBitrate(mediaCodecInfo.getName(), format2.width, format2.height, format2.frameRate);
                } else if (format2.averageBitrate != -1) {
                    i = format2.averageBitrate;
                } else {
                    i = getSuggestedBitrate(format2.width, format2.height, format2.frameRate);
                }
                Format build = format2.buildUpon().setAverageBitrate(i).build();
                MediaFormat createMediaFormatFromFormat = MediaFormatUtil.createMediaFormatFromFormat(build);
                createMediaFormatFromFormat.setInteger("bitrate-mode", videoEncoderSettings.bitrateMode);
                createMediaFormatFromFormat.setInteger("frame-rate", Math.round(build.frameRate));
                if (!(videoEncoderSettings.profile == -1 || videoEncoderSettings.level == -1 || Util.SDK_INT < 24)) {
                    createMediaFormatFromFormat.setInteger(Scopes.PROFILE, videoEncoderSettings.profile);
                    createMediaFormatFromFormat.setInteger("level", videoEncoderSettings.level);
                }
                if (str.equals(MimeTypes.VIDEO_H264)) {
                    adjustMediaFormatForH264EncoderSettings(format.colorInfo, mediaCodecInfo, createMediaFormatFromFormat);
                }
                if (Util.SDK_INT < 31 || !ColorInfo.isTransferHdr(format.colorInfo)) {
                    createMediaFormatFromFormat.setInteger("color-format", 2130708361);
                } else if (EncoderUtil.getSupportedColorFormats(mediaCodecInfo, str).contains(2130750114)) {
                    createMediaFormatFromFormat.setInteger("color-format", 2130750114);
                } else {
                    throw createExportException(format, "Encoding HDR is not supported on this device.");
                }
                if (Util.SDK_INT >= 25) {
                    createMediaFormatFromFormat.setFloat("i-frame-interval", videoEncoderSettings.iFrameIntervalSeconds);
                } else {
                    float f = videoEncoderSettings.iFrameIntervalSeconds;
                    if (f <= 0.0f || f > 1.0f) {
                        i2 = (int) Math.floor((double) f);
                    }
                    createMediaFormatFromFormat.setInteger("i-frame-interval", i2);
                }
                int i3 = videoEncoderSettings.operatingRate;
                int i4 = videoEncoderSettings.priority;
                if (!(Util.SDK_INT < 23 || i3 == -2 || i4 == -2)) {
                    if (i3 == -1 && i4 == -1) {
                        adjustMediaFormatForEncoderPerformanceSettings(createMediaFormatFromFormat);
                    } else {
                        if (i3 != -1) {
                            createMediaFormatFromFormat.setInteger("operating-rate", i3);
                        }
                        if (i4 != -1) {
                            createMediaFormatFromFormat.setInteger(SentryThread.JsonKeys.PRIORITY, i4);
                        }
                    }
                }
                if (Util.SDK_INT >= 35) {
                    createMediaFormatFromFormat.setInteger(NotificationsChannelSerializer.IMPORTANCE_KEY, Math.max(0, -this.codecPriority));
                }
                return new DefaultCodec(this.context, build, createMediaFormatFromFormat, mediaCodecInfo.getName(), false, (Surface) null);
            }
            throw createExportException(format, "The requested video encoding format is not supported.");
        }
        throw createNoSupportedMimeTypeException(format, true);
    }

    public boolean videoNeedsEncoding() {
        return !this.requestedVideoEncoderSettings.equals(VideoEncoderSettings.DEFAULT);
    }

    @RequiresNonNull({"#1.sampleMimeType"})
    private static VideoEncoderQueryResult findEncoderWithClosestSupportedFormat(Format format, VideoEncoderSettings videoEncoderSettings, EncoderSelector encoderSelector, boolean z) {
        int i;
        String str = (String) Assertions.checkNotNull(format.sampleMimeType);
        ImmutableList<MediaCodecInfo> selectEncoderInfos = encoderSelector.selectEncoderInfos(str);
        if (selectEncoderInfos.isEmpty()) {
            return null;
        }
        if (!z) {
            return new VideoEncoderQueryResult((MediaCodecInfo) selectEncoderInfos.get(0), format, videoEncoderSettings);
        }
        ImmutableList<MediaCodecInfo> filterEncodersByResolution = filterEncodersByResolution(selectEncoderInfos, str, format.width, format.height);
        if (filterEncodersByResolution.isEmpty()) {
            return null;
        }
        Size size = (Size) Assertions.checkNotNull(EncoderUtil.getSupportedResolution((MediaCodecInfo) filterEncodersByResolution.get(0), str, format.width, format.height));
        if (!videoEncoderSettings.enableHighQualityTargeting) {
            if (videoEncoderSettings.bitrate != -1) {
                i = videoEncoderSettings.bitrate;
            } else if (format.averageBitrate != -1) {
                i = format.averageBitrate;
            } else {
                i = getSuggestedBitrate(size.getWidth(), size.getHeight(), format.frameRate);
            }
            filterEncodersByResolution = filterEncodersByBitrate(filterEncodersByResolution, str, i);
            if (filterEncodersByResolution.isEmpty()) {
                return null;
            }
        } else {
            i = -1;
        }
        ImmutableList<MediaCodecInfo> filterEncodersByBitrateMode = filterEncodersByBitrateMode(filterEncodersByResolution, str, videoEncoderSettings.bitrateMode);
        if (filterEncodersByBitrateMode.isEmpty()) {
            return null;
        }
        VideoEncoderSettings.Builder buildUpon = videoEncoderSettings.buildUpon();
        Format.Builder height = format.buildUpon().setSampleMimeType(str).setWidth(size.getWidth()).setHeight(size.getHeight());
        MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) filterEncodersByBitrateMode.get(0);
        if (videoEncoderSettings.enableHighQualityTargeting) {
            i = new DeviceMappedEncoderBitrateProvider().getBitrate(mediaCodecInfo.getName(), size.getWidth(), size.getHeight(), format.frameRate);
            buildUpon.experimentalSetEnableHighQualityTargeting(false);
        }
        int intValue = EncoderUtil.getSupportedBitrateRange(mediaCodecInfo, str).clamp(Integer.valueOf(i)).intValue();
        buildUpon.setBitrate(intValue);
        height.setAverageBitrate(intValue);
        if (videoEncoderSettings.profile == -1 || videoEncoderSettings.level == -1 || videoEncoderSettings.level > EncoderUtil.findHighestSupportedEncodingLevel(mediaCodecInfo, str, videoEncoderSettings.profile)) {
            buildUpon.setEncodingProfileLevel(-1, -1);
        }
        return new VideoEncoderQueryResult(mediaCodecInfo, height.build(), buildUpon.build());
    }

    private static ImmutableList<MediaCodecInfo> filterEncodersByResolution(List<MediaCodecInfo> list, String str, int i, int i2) {
        return filterEncoders(list, new DefaultEncoderFactory$$ExternalSyntheticLambda0(str, i, i2));
    }

    static /* synthetic */ int lambda$filterEncodersByResolution$0(String str, int i, int i2, MediaCodecInfo mediaCodecInfo) {
        Size supportedResolution = EncoderUtil.getSupportedResolution(mediaCodecInfo, str, i, i2);
        if (supportedResolution == null) {
            return Integer.MAX_VALUE;
        }
        return Math.abs((i * i2) - (supportedResolution.getWidth() * supportedResolution.getHeight()));
    }

    private static ImmutableList<MediaCodecInfo> filterEncodersByBitrate(List<MediaCodecInfo> list, String str, int i) {
        return filterEncoders(list, new DefaultEncoderFactory$$ExternalSyntheticLambda1(str, i));
    }

    private static ImmutableList<MediaCodecInfo> filterEncodersByBitrateMode(List<MediaCodecInfo> list, String str, int i) {
        return filterEncoders(list, new DefaultEncoderFactory$$ExternalSyntheticLambda2(str, i));
    }

    static /* synthetic */ int lambda$filterEncodersByBitrateMode$2(String str, int i, MediaCodecInfo mediaCodecInfo) {
        return EncoderUtil.isBitrateModeSupported(mediaCodecInfo, str, i) ? 0 : Integer.MAX_VALUE;
    }

    private static final class VideoEncoderQueryResult {
        public final MediaCodecInfo encoder;
        public final VideoEncoderSettings supportedEncoderSettings;
        public final Format supportedFormat;

        public VideoEncoderQueryResult(MediaCodecInfo mediaCodecInfo, Format format, VideoEncoderSettings videoEncoderSettings) {
            this.encoder = mediaCodecInfo;
            this.supportedFormat = format;
            this.supportedEncoderSettings = videoEncoderSettings;
        }
    }

    private static void adjustMediaFormatForEncoderPerformanceSettings(MediaFormat mediaFormat) {
        if (Util.SDK_INT >= 25) {
            mediaFormat.setInteger(SentryThread.JsonKeys.PRIORITY, 1);
            if (Util.SDK_INT == 26) {
                mediaFormat.setInteger("operating-rate", 30);
            } else if (deviceNeedsLowerOperatingRateAvoidingOverflowWorkaround()) {
                mediaFormat.setInteger("operating-rate", 1000);
            } else {
                mediaFormat.setInteger("operating-rate", Integer.MAX_VALUE);
            }
        }
    }

    private static boolean deviceNeedsLowerOperatingRateAvoidingOverflowWorkaround() {
        return Util.SDK_INT >= 31 && Util.SDK_INT <= 34 && (Build.SOC_MODEL.equals("SM8550") || Build.SOC_MODEL.equals("SM7450") || Build.SOC_MODEL.equals("SM6450") || Build.SOC_MODEL.equals("SC9863A") || Build.SOC_MODEL.equals("T612") || Build.SOC_MODEL.equals("T606") || Build.SOC_MODEL.equals("T603"));
    }

    private static void adjustMediaFormatForH264EncoderSettings(ColorInfo colorInfo, MediaCodecInfo mediaCodecInfo, MediaFormat mediaFormat) {
        boolean z = false;
        int i = 8;
        if (Util.SDK_INT >= 29) {
            if (colorInfo != null) {
                ImmutableList<Integer> codecProfilesForHdrFormat = EncoderUtil.getCodecProfilesForHdrFormat(MimeTypes.VIDEO_H264, colorInfo.colorTransfer);
                if (!codecProfilesForHdrFormat.isEmpty()) {
                    i = ((Integer) codecProfilesForHdrFormat.get(0)).intValue();
                }
            }
            int findHighestSupportedEncodingLevel = EncoderUtil.findHighestSupportedEncodingLevel(mediaCodecInfo, MimeTypes.VIDEO_H264, i);
            if (findHighestSupportedEncodingLevel != -1) {
                mediaFormat.setInteger(Scopes.PROFILE, i);
                if (!mediaFormat.containsKey("level")) {
                    mediaFormat.setInteger("level", findHighestSupportedEncodingLevel);
                }
            }
        } else if (Util.SDK_INT >= 26 && !deviceNeedsNoH264HighProfileWorkaround()) {
            int findHighestSupportedEncodingLevel2 = EncoderUtil.findHighestSupportedEncodingLevel(mediaCodecInfo, MimeTypes.VIDEO_H264, 8);
            if (findHighestSupportedEncodingLevel2 != -1) {
                mediaFormat.setInteger(Scopes.PROFILE, 8);
                if (!mediaFormat.containsKey("level")) {
                    mediaFormat.setInteger("level", findHighestSupportedEncodingLevel2);
                }
                mediaFormat.setInteger("latency", 1);
            }
        } else if (Util.SDK_INT >= 24) {
            int findHighestSupportedEncodingLevel3 = EncoderUtil.findHighestSupportedEncodingLevel(mediaCodecInfo, MimeTypes.VIDEO_H264, 1);
            if (findHighestSupportedEncodingLevel3 != -1) {
                z = true;
            }
            Assertions.checkState(z);
            mediaFormat.setInteger(Scopes.PROFILE, 1);
            if (!mediaFormat.containsKey("level")) {
                mediaFormat.setInteger("level", findHighestSupportedEncodingLevel3);
            }
        }
    }

    private static ImmutableList<MediaCodecInfo> filterEncoders(List<MediaCodecInfo> list, EncoderFallbackCost encoderFallbackCost) {
        ArrayList arrayList = new ArrayList(list.size());
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < list.size(); i2++) {
            MediaCodecInfo mediaCodecInfo = list.get(i2);
            int parameterSupportGap = encoderFallbackCost.getParameterSupportGap(mediaCodecInfo);
            if (parameterSupportGap != Integer.MAX_VALUE) {
                if (parameterSupportGap < i) {
                    arrayList.clear();
                    arrayList.add(mediaCodecInfo);
                    i = parameterSupportGap;
                } else if (parameterSupportGap == i) {
                    arrayList.add(mediaCodecInfo);
                }
            }
        }
        return ImmutableList.copyOf(arrayList);
    }

    private static ExportException createNoSupportedMimeTypeException(Format format, boolean z) {
        return ExportException.createForCodec(new IllegalArgumentException((!z || !ColorInfo.isTransferHdr(format.colorInfo)) ? "No MIME type is supported by both encoder and muxer." : "No MIME type is supported by both encoder and muxer. Requested HDR colorInfo: " + format.colorInfo), 4003, new ExportException.CodecInfo(format.toString(), z, false, (String) null));
    }

    @RequiresNonNull({"#1.sampleMimeType"})
    private static ExportException createExportException(Format format, String str) {
        return ExportException.createForCodec(new IllegalArgumentException(str), 4003, new ExportException.CodecInfo(format.toString(), MimeTypes.isVideo(format.sampleMimeType), false, (String) null));
    }

    private static boolean deviceNeedsDefaultFrameRateWorkaround() {
        return Util.SDK_INT < 30 && Util.DEVICE.equals("joyeuse");
    }

    private static boolean deviceNeedsNoH264HighProfileWorkaround() {
        return Util.SDK_INT == 27 && (Util.DEVICE.equals("ASUS_X00T_3") || Util.DEVICE.equals("TC77"));
    }
}
