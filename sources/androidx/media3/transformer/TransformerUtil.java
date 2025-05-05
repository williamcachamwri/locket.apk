package androidx.media3.transformer;

import android.content.Context;
import android.util.Pair;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.effect.GlEffect;
import androidx.media3.effect.ScaleAndRotateTransformation;
import androidx.media3.extractor.metadata.mp4.SlowMotionData;
import androidx.media3.transformer.Codec;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.util.Objects;

public final class TransformerUtil {
    public static int getMediaCodecFlags(int i) {
        int i2 = 1;
        if ((i & 1) != 1) {
            i2 = 0;
        }
        return (i & 4) == 4 ? i2 | 4 : i2;
    }

    private TransformerUtil() {
    }

    public static int getProcessedTrackType(String str) {
        int trackType = MimeTypes.getTrackType(str);
        if (trackType == 4) {
            return 2;
        }
        return trackType;
    }

    public static boolean shouldTranscodeAudio(Format format, Composition composition, int i, TransformationRequest transformationRequest, Codec.EncoderFactory encoderFactory, MuxerWrapper muxerWrapper) {
        boolean z = false;
        if (composition.sequences.size() > 1 || ((EditedMediaItemSequence) composition.sequences.get(i)).editedMediaItems.size() > 1) {
            if (!composition.hasGaps() || !composition.transmuxAudio) {
                z = true;
            }
            Assertions.checkArgument(z, "Gaps can not be transmuxed.");
            return !composition.transmuxAudio;
        } else if (composition.hasGaps() || encoderFactory.audioNeedsEncoding()) {
            return true;
        } else {
            if (transformationRequest.audioMimeType != null && !transformationRequest.audioMimeType.equals(format.sampleMimeType)) {
                return true;
            }
            if (transformationRequest.audioMimeType == null && !muxerWrapper.supportsSampleMimeType(format.sampleMimeType)) {
                return true;
            }
            EditedMediaItem editedMediaItem = (EditedMediaItem) ((EditedMediaItemSequence) composition.sequences.get(i)).editedMediaItems.get(0);
            return (editedMediaItem.flattenForSlowMotion && containsSlowMotionData(format)) || !editedMediaItem.effects.audioProcessors.isEmpty() || !composition.effects.audioProcessors.isEmpty();
        }
    }

    private static boolean containsSlowMotionData(Format format) {
        Metadata metadata = format.metadata;
        if (metadata == null) {
            return false;
        }
        for (int i = 0; i < metadata.length(); i++) {
            if (metadata.get(i) instanceof SlowMotionData) {
                return true;
            }
        }
        return false;
    }

    public static boolean shouldTranscodeVideo(Format format, Composition composition, int i, TransformationRequest transformationRequest, Codec.EncoderFactory encoderFactory, MuxerWrapper muxerWrapper) {
        if (composition.sequences.size() > 1 || ((EditedMediaItemSequence) composition.sequences.get(i)).editedMediaItems.size() > 1) {
            return !composition.transmuxVideo;
        }
        if (encoderFactory.videoNeedsEncoding() || transformationRequest.hdrMode != 0) {
            return true;
        }
        if (transformationRequest.videoMimeType != null && !transformationRequest.videoMimeType.equals(format.sampleMimeType)) {
            return true;
        }
        if ((transformationRequest.videoMimeType == null && !muxerWrapper.supportsSampleMimeType(format.sampleMimeType)) || format.pixelWidthHeightRatio != 1.0f) {
            return true;
        }
        ImmutableList build = new ImmutableList.Builder().addAll((Iterable) ((EditedMediaItem) ((EditedMediaItemSequence) composition.sequences.get(i)).editedMediaItems.get(0)).effects.videoEffects).addAll((Iterable) composition.effects.videoEffects).build();
        if (build.isEmpty() || maybeCalculateTotalRotationDegreesAppliedInEffects(build, format) != -1.0f) {
            return false;
        }
        return true;
    }

    private static float maybeCalculateTotalRotationDegreesAppliedInEffects(ImmutableList<Effect> immutableList, Format format) {
        int i = format.rotationDegrees % RotationOptions.ROTATE_180 == 0 ? format.width : format.height;
        int i2 = format.rotationDegrees % RotationOptions.ROTATE_180 == 0 ? format.height : format.width;
        float f = 0.0f;
        for (int i3 = 0; i3 < immutableList.size(); i3++) {
            Effect effect = (Effect) immutableList.get(i3);
            if (!(effect instanceof GlEffect)) {
                return -1.0f;
            }
            GlEffect glEffect = (GlEffect) effect;
            if (effect instanceof ScaleAndRotateTransformation) {
                ScaleAndRotateTransformation scaleAndRotateTransformation = (ScaleAndRotateTransformation) effect;
                if (scaleAndRotateTransformation.scaleX != 1.0f || scaleAndRotateTransformation.scaleY != 1.0f) {
                    return -1.0f;
                }
                float f2 = scaleAndRotateTransformation.rotationDegrees;
                if (f2 % 90.0f != 0.0f) {
                    return -1.0f;
                }
                f += f2;
                int i4 = ((f % 180.0f) > 0.0f ? 1 : ((f % 180.0f) == 0.0f ? 0 : -1));
                int i5 = i4 == 0 ? format.width : format.height;
                i2 = i4 == 0 ? format.height : format.width;
                i = i5;
            } else if (!glEffect.isNoOp(i, i2)) {
                return -1.0f;
            }
        }
        float f3 = f % 360.0f;
        if (f3 % 90.0f == 0.0f) {
            return f3;
        }
        return -1.0f;
    }

    public static void maybeSetMuxerWrapperAdditionalRotationDegrees(MuxerWrapper muxerWrapper, ImmutableList<Effect> immutableList, Format format) {
        float maybeCalculateTotalRotationDegreesAppliedInEffects = maybeCalculateTotalRotationDegreesAppliedInEffects(immutableList, format);
        if (maybeCalculateTotalRotationDegreesAppliedInEffects == 90.0f || maybeCalculateTotalRotationDegreesAppliedInEffects == 180.0f || maybeCalculateTotalRotationDegreesAppliedInEffects == 270.0f) {
            muxerWrapper.setAdditionalRotationDegrees(360 - Math.round(maybeCalculateTotalRotationDegreesAppliedInEffects));
        }
    }

    public static ColorInfo getValidColor(ColorInfo colorInfo) {
        if (colorInfo == null || !colorInfo.isDataSpaceValid()) {
            return ColorInfo.SDR_BT709_LIMITED;
        }
        return colorInfo;
    }

    public static ColorInfo getDecoderOutputColor(ColorInfo colorInfo, boolean z) {
        return (!z || !ColorInfo.isTransferHdr(colorInfo)) ? colorInfo : ColorInfo.SDR_BT709_LIMITED;
    }

    public static Pair<String, Integer> getOutputMimeTypeAndHdrModeAfterFallback(int i, String str, ColorInfo colorInfo) {
        if (i == 0 && ColorInfo.isTransferHdr(colorInfo) && EncoderUtil.getSupportedEncodersForHdrEditing(str, colorInfo).isEmpty()) {
            if (!EncoderUtil.getSupportedEncodersForHdrEditing(MimeTypes.VIDEO_H265, colorInfo).isEmpty()) {
                str = MimeTypes.VIDEO_H265;
            } else {
                i = 2;
            }
        }
        return Pair.create(str, Integer.valueOf(i));
    }

    public static boolean isImage(Context context, MediaItem mediaItem) {
        String imageMimeType = getImageMimeType(context, mediaItem);
        return imageMimeType != null && MimeTypes.isImage(imageMimeType);
    }

    public static String getImageMimeType(Context context, MediaItem mediaItem) {
        if (mediaItem.localConfiguration == null) {
            return null;
        }
        MediaItem.LocalConfiguration localConfiguration = mediaItem.localConfiguration;
        String str = localConfiguration.mimeType;
        if (str != null) {
            return str;
        }
        if (Objects.equals(localConfiguration.uri.getScheme(), "content")) {
            return context.getContentResolver().getType(localConfiguration.uri);
        }
        String path = localConfiguration.uri.getPath();
        if (path == null) {
            return null;
        }
        int lastIndexOf = path.lastIndexOf(".");
        return (lastIndexOf < 0 || lastIndexOf >= path.length() + -1) ? str : getCommonImageMimeTypeFromExtension(Ascii.toLowerCase(path.substring(lastIndexOf + 1)));
    }

    private static String getCommonImageMimeTypeFromExtension(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 96870:
                if (str.equals("arw")) {
                    c = 0;
                    break;
                }
                break;
            case 97669:
                if (str.equals("bmp")) {
                    c = 1;
                    break;
                }
                break;
            case 98723:
                if (str.equals("cr2")) {
                    c = 2;
                    break;
                }
                break;
            case 99453:
                if (str.equals("dib")) {
                    c = 3;
                    break;
                }
                break;
            case 102340:
                if (str.equals("gif")) {
                    c = 4;
                    break;
                }
                break;
            case 104085:
                if (str.equals("ico")) {
                    c = 5;
                    break;
                }
                break;
            case 104430:
                if (str.equals("k25")) {
                    c = 6;
                    break;
                }
                break;
            case 105133:
                if (str.equals("jfi")) {
                    c = 7;
                    break;
                }
                break;
            case 105223:
                if (str.equals("jif")) {
                    c = 8;
                    break;
                }
                break;
            case 105439:
                if (str.equals("jpe")) {
                    c = 9;
                    break;
                }
                break;
            case 105441:
                if (str.equals("jpg")) {
                    c = 10;
                    break;
                }
                break;
            case 111145:
                if (str.equals("png")) {
                    c = 11;
                    break;
                }
                break;
            case 112680:
                if (str.equals("raw")) {
                    c = 12;
                    break;
                }
                break;
            case 114276:
                if (str.equals("svg")) {
                    c = 13;
                    break;
                }
                break;
            case 114833:
                if (str.equals("tif")) {
                    c = 14;
                    break;
                }
                break;
            case 3006482:
                if (str.equals("avif")) {
                    c = 15;
                    break;
                }
                break;
            case 3198679:
                if (str.equals("heic")) {
                    c = 16;
                    break;
                }
                break;
            case 3198682:
                if (str.equals("heif")) {
                    c = 17;
                    break;
                }
                break;
            case 3259225:
                if (str.equals("jfif")) {
                    c = 18;
                    break;
                }
                break;
            case 3268712:
                if (str.equals("jpeg")) {
                    c = 19;
                    break;
                }
                break;
            case 3542678:
                if (str.equals("svgz")) {
                    c = 20;
                    break;
                }
                break;
            case 3559925:
                if (str.equals("tiff")) {
                    c = 21;
                    break;
                }
                break;
            case 3645340:
                if (str.equals("webp")) {
                    c = 22;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 2:
            case 6:
            case 12:
                return MimeTypes.IMAGE_RAW;
            case 1:
            case 3:
                return MimeTypes.IMAGE_BMP;
            case 4:
                return "image/gif";
            case 5:
                return "image/x-icon";
            case 7:
            case 8:
            case 9:
            case 10:
            case 18:
            case 19:
                return "image/jpeg";
            case 11:
                return MimeTypes.IMAGE_PNG;
            case 13:
            case 20:
                return "image/svg+xml";
            case 14:
            case 21:
                return "image/tiff";
            case 15:
                return MimeTypes.IMAGE_AVIF;
            case 16:
                return "image/heic";
            case 17:
                return "image/heif";
            case 22:
                return "image/webp";
            default:
                return null;
        }
    }
}
