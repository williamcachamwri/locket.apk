package androidx.media3.transformer;

import android.content.Context;
import android.media.MediaFormat;
import android.os.Build;
import android.util.Pair;
import android.view.Surface;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.MediaFormatUtil;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import androidx.media3.transformer.Codec;
import androidx.media3.transformer.ExportException;
import com.google.android.gms.common.Scopes;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import io.sentry.protocol.SentryThread;
import java.util.ArrayList;
import java.util.List;

public final class DefaultDecoderFactory implements Codec.DecoderFactory {
    private static final String TAG = "DefaultDecoderFactory";
    private final int codecPriority;
    private final Context context;
    private final boolean dynamicSchedulingEnabled;
    private final boolean enableDecoderFallback;
    private final Listener listener;
    private final MediaCodecSelector mediaCodecSelector;
    private final boolean shouldConfigureOperatingRate;

    public interface Listener {
        void onCodecInitialized(String str, List<ExportException> list);
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public int codecPriority = C.PRIORITY_PROCESSING_FOREGROUND;
        /* access modifiers changed from: private */
        public final Context context;
        /* access modifiers changed from: private */
        public boolean dynamicSchedulingEnabled;
        /* access modifiers changed from: private */
        public boolean enableDecoderFallback;
        /* access modifiers changed from: private */
        public Listener listener = new DefaultDecoderFactory$Builder$$ExternalSyntheticLambda0();
        /* access modifiers changed from: private */
        public MediaCodecSelector mediaCodecSelector = MediaCodecSelector.DEFAULT;
        /* access modifiers changed from: private */
        public boolean shouldConfigureOperatingRate = false;

        static /* synthetic */ void lambda$new$0(String str, List list) {
        }

        public Builder(Context context2) {
            this.context = context2.getApplicationContext();
        }

        public Builder setListener(Listener listener2) {
            this.listener = listener2;
            return this;
        }

        public Builder setEnableDecoderFallback(boolean z) {
            this.enableDecoderFallback = z;
            return this;
        }

        public Builder setCodecPriority(int i) {
            this.codecPriority = i;
            return this;
        }

        public Builder setShouldConfigureOperatingRate(boolean z) {
            this.shouldConfigureOperatingRate = z;
            return this;
        }

        public Builder setMediaCodecSelector(MediaCodecSelector mediaCodecSelector2) {
            this.mediaCodecSelector = mediaCodecSelector2;
            return this;
        }

        public Builder experimentalSetDynamicSchedulingEnabled(boolean z) {
            this.dynamicSchedulingEnabled = z;
            return this;
        }

        public DefaultDecoderFactory build() {
            return new DefaultDecoderFactory(this);
        }
    }

    @Deprecated
    public DefaultDecoderFactory(Context context2) {
        this(new Builder(context2));
    }

    @Deprecated
    public DefaultDecoderFactory(Context context2, boolean z, Listener listener2) {
        this(new Builder(context2).setEnableDecoderFallback(z).setListener(listener2));
    }

    private DefaultDecoderFactory(Builder builder) {
        this.context = builder.context;
        this.enableDecoderFallback = builder.enableDecoderFallback;
        this.listener = builder.listener;
        this.codecPriority = builder.codecPriority;
        this.shouldConfigureOperatingRate = builder.shouldConfigureOperatingRate;
        this.mediaCodecSelector = builder.mediaCodecSelector;
        this.dynamicSchedulingEnabled = builder.dynamicSchedulingEnabled;
    }

    public DefaultCodec createForAudioDecoding(Format format) throws ExportException {
        return createCodecForMediaFormat(MediaFormatUtil.createMediaFormatFromFormat(format), format, (Surface) null, false);
    }

    public DefaultCodec createForVideoDecoding(Format format, Surface surface, boolean z) throws ExportException {
        if (ColorInfo.isTransferHdr(format.colorInfo)) {
            if (z && (Util.SDK_INT < 31 || deviceNeedsDisableToneMappingWorkaround(((ColorInfo) Assertions.checkNotNull(format.colorInfo)).colorTransfer))) {
                throw createExportException(format, "Tone-mapping HDR is not supported on this device.");
            } else if (Util.SDK_INT < 29) {
                throw createExportException(format, "Decoding HDR is not supported on this device.");
            }
        }
        if (!deviceNeedsDisable8kWorkaround(format)) {
            if (deviceNeedsNoFrameRateWorkaround()) {
                format = format.buildUpon().setFrameRate(-1.0f).build();
            }
            MediaFormat createMediaFormatFromFormat = MediaFormatUtil.createMediaFormatFromFormat(format);
            if (decoderSupportsKeyAllowFrameDrop(this.context)) {
                createMediaFormatFromFormat.setInteger("allow-frame-drop", 0);
            }
            if (Util.SDK_INT >= 31 && z) {
                createMediaFormatFromFormat.setInteger("color-transfer-request", 3);
            }
            Pair<Integer, Integer> codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format);
            if (codecProfileAndLevel != null) {
                MediaFormatUtil.maybeSetInteger(createMediaFormatFromFormat, Scopes.PROFILE, ((Integer) codecProfileAndLevel.first).intValue());
                MediaFormatUtil.maybeSetInteger(createMediaFormatFromFormat, "level", ((Integer) codecProfileAndLevel.second).intValue());
            }
            if (Util.SDK_INT >= 35) {
                createMediaFormatFromFormat.setInteger(NotificationsChannelSerializer.IMPORTANCE_KEY, Math.max(0, -this.codecPriority));
            }
            if (this.shouldConfigureOperatingRate) {
                configureOperatingRate(createMediaFormatFromFormat);
            }
            return createCodecForMediaFormat(createMediaFormatFromFormat, format, surface, devicePrefersSoftwareDecoder(format));
        }
        throw createExportException(format, "Decoding 8k is not supported on this device.");
    }

    private DefaultCodec createCodecForMediaFormat(MediaFormat mediaFormat, Format format, Surface surface, boolean z) throws ExportException {
        ImmutableList.of();
        Assertions.checkNotNull(format.sampleMimeType);
        try {
            List<MediaCodecInfo> decoderInfosSortedByFormatSupport = MediaCodecUtil.getDecoderInfosSortedByFormatSupport(MediaCodecUtil.getDecoderInfosSoftMatch(this.mediaCodecSelector, format, false, false), format);
            if (!decoderInfosSortedByFormatSupport.isEmpty()) {
                if (z) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < decoderInfosSortedByFormatSupport.size(); i++) {
                        MediaCodecInfo mediaCodecInfo = decoderInfosSortedByFormatSupport.get(i);
                        if (!mediaCodecInfo.hardwareAccelerated) {
                            arrayList.add(mediaCodecInfo);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        decoderInfosSortedByFormatSupport = arrayList;
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                Context context2 = this.context;
                if (!this.enableDecoderFallback) {
                    decoderInfosSortedByFormatSupport = decoderInfosSortedByFormatSupport.subList(0, 1);
                }
                DefaultCodec createCodecFromDecoderInfos = createCodecFromDecoderInfos(context2, decoderInfosSortedByFormatSupport, format, mediaFormat, surface, arrayList2);
                this.listener.onCodecInitialized(createCodecFromDecoderInfos.getName(), arrayList2);
                return createCodecFromDecoderInfos;
            }
            throw createExportException(format, "No decoders for format");
        } catch (MediaCodecUtil.DecoderQueryException e) {
            Log.e(TAG, "Error querying decoders", e);
            throw createExportException(format, "Querying codecs failed");
        }
    }

    public boolean isDynamicSchedulingEnabled() {
        return this.dynamicSchedulingEnabled;
    }

    private static DefaultCodec createCodecFromDecoderInfos(Context context2, List<MediaCodecInfo> list, Format format, MediaFormat mediaFormat, Surface surface, List<ExportException> list2) throws ExportException {
        for (MediaCodecInfo next : list) {
            mediaFormat.setString("mime", next.codecMimeType);
            try {
                return new DefaultCodec(context2, format, mediaFormat, next.name, true, surface);
            } catch (ExportException e) {
                list2.add(e);
            }
        }
        throw list2.get(0);
    }

    private static void configureOperatingRate(MediaFormat mediaFormat) {
        if (Util.SDK_INT >= 25) {
            if (deviceNeedsPriorityWorkaround()) {
                mediaFormat.setInteger(SentryThread.JsonKeys.PRIORITY, 1);
            }
            mediaFormat.setInteger("operating-rate", 10000);
        }
    }

    private static boolean deviceNeedsPriorityWorkaround() {
        return Util.SDK_INT >= 31 && (Build.SOC_MODEL.equals("s5e8835") || Build.SOC_MODEL.equals("SA8155P"));
    }

    private static boolean deviceNeedsDisable8kWorkaround(Format format) {
        return Util.SDK_INT < 31 && format.width >= 7680 && format.height >= 4320 && format.sampleMimeType != null && format.sampleMimeType.equals(MimeTypes.VIDEO_H265) && (Util.MODEL.equals("SM-F711U1") || Util.MODEL.equals("SM-F926U1"));
    }

    private static boolean deviceNeedsDisableToneMappingWorkaround(int i) {
        if (Util.MANUFACTURER.equals("Google") && Build.ID.startsWith("TP1A")) {
            return true;
        }
        if (i == 7 && (Util.MODEL.startsWith("SM-F936") || Util.MODEL.startsWith("SM-F916") || Util.MODEL.startsWith("SM-F721") || Util.MODEL.equals("SM-X900"))) {
            return true;
        }
        if (Util.SDK_INT >= 34 || i != 6 || !Util.MODEL.startsWith("SM-F936")) {
            return false;
        }
        return true;
    }

    private static boolean deviceNeedsNoFrameRateWorkaround() {
        return Util.SDK_INT < 30 && Util.DEVICE.equals("joyeuse");
    }

    private static boolean decoderSupportsKeyAllowFrameDrop(Context context2) {
        return Util.SDK_INT >= 29 && context2.getApplicationInfo().targetSdkVersion >= 29;
    }

    private static boolean devicePrefersSoftwareDecoder(Format format) {
        return format.width * format.height >= 2073600 && (Ascii.equalsIgnoreCase(Util.MODEL, "vivo 1906") || Ascii.equalsIgnoreCase(Util.MODEL, "redmi 7a") || Ascii.equalsIgnoreCase(Util.MODEL, "redmi 8"));
    }

    private static ExportException createExportException(Format format, String str) {
        return ExportException.createForCodec(new IllegalArgumentException(str), 3003, new ExportException.CodecInfo(format.toString(), MimeTypes.isVideo((String) Assertions.checkNotNull(format.sampleMimeType)), true, (String) null));
    }
}
