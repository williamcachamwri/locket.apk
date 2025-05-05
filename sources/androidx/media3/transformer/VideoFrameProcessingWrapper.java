package androidx.media3.transformer;

import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.VideoFrameProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TimestampIterator;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

final class VideoFrameProcessingWrapper implements GraphInput {
    private final long initialTimestampOffsetUs;
    private final AtomicLong mediaItemOffsetUs = new AtomicLong();
    private final List<Effect> postProcessingEffects;
    private final VideoFrameProcessor videoFrameProcessor;

    public VideoFrameProcessingWrapper(VideoFrameProcessor videoFrameProcessor2, List<Effect> list, long j) {
        this.videoFrameProcessor = videoFrameProcessor2;
        this.postProcessingEffects = list;
        this.initialTimestampOffsetUs = j;
    }

    public void onMediaItemChanged(EditedMediaItem editedMediaItem, long j, Format format, boolean z) {
        int i;
        Assertions.checkArgument(!editedMediaItem.isGap());
        boolean isMediaItemForSurfaceAssetLoader = isMediaItemForSurfaceAssetLoader(editedMediaItem);
        long durationAfterEffectsApplied = editedMediaItem.getDurationAfterEffectsApplied(j);
        if (format != null) {
            Size decodedSize = getDecodedSize(format);
            ImmutableList build = new ImmutableList.Builder().addAll((Iterable) editedMediaItem.effects.videoEffects).addAll((Iterable) this.postProcessingEffects).build();
            VideoFrameProcessor videoFrameProcessor2 = this.videoFrameProcessor;
            if (isMediaItemForSurfaceAssetLoader) {
                i = 4;
            } else {
                i = getInputTypeForMimeType((String) Assertions.checkNotNull(format.sampleMimeType));
            }
            videoFrameProcessor2.registerInputStream(i, build, new FrameInfo.Builder((ColorInfo) Assertions.checkNotNull(format.colorInfo), decodedSize.getWidth(), decodedSize.getHeight()).setPixelWidthHeightRatio(format.pixelWidthHeightRatio).setOffsetToAddUs(this.initialTimestampOffsetUs + this.mediaItemOffsetUs.get()).build());
        }
        this.mediaItemOffsetUs.addAndGet(durationAfterEffectsApplied);
    }

    public int queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator) {
        return this.videoFrameProcessor.queueInputBitmap(bitmap, timestampIterator) ? 1 : 2;
    }

    public void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener) {
        this.videoFrameProcessor.setOnInputFrameProcessedListener(onInputFrameProcessedListener);
    }

    public void setOnInputSurfaceReadyListener(Runnable runnable) {
        this.videoFrameProcessor.setOnInputSurfaceReadyListener(runnable);
    }

    public int queueInputTexture(int i, long j) {
        return this.videoFrameProcessor.queueInputTexture(i, j) ? 1 : 2;
    }

    public Surface getInputSurface() {
        return this.videoFrameProcessor.getInputSurface();
    }

    public int getPendingVideoFrameCount() {
        return this.videoFrameProcessor.getPendingInputFrameCount();
    }

    public boolean registerVideoFrame(long j) {
        return this.videoFrameProcessor.registerInputFrame();
    }

    public void signalEndOfVideoInput() {
        this.videoFrameProcessor.signalEndOfInput();
    }

    public void release() {
        this.videoFrameProcessor.release();
    }

    private static Size getDecodedSize(Format format) {
        return new Size(format.rotationDegrees % RotationOptions.ROTATE_180 == 0 ? format.width : format.height, format.rotationDegrees % RotationOptions.ROTATE_180 == 0 ? format.height : format.width);
    }

    private static int getInputTypeForMimeType(String str) {
        if (MimeTypes.isImage(str)) {
            return 2;
        }
        if (str.equals(MimeTypes.VIDEO_RAW)) {
            return 3;
        }
        if (MimeTypes.isVideo(str)) {
            return 1;
        }
        throw new IllegalArgumentException("MIME type not supported " + str);
    }

    private static boolean isMediaItemForSurfaceAssetLoader(EditedMediaItem editedMediaItem) {
        String scheme;
        MediaItem.LocalConfiguration localConfiguration = editedMediaItem.mediaItem.localConfiguration;
        if (localConfiguration == null || (scheme = localConfiguration.uri.getScheme()) == null) {
            return false;
        }
        return scheme.equals(SurfaceAssetLoader.MEDIA_ITEM_URI_SCHEME);
    }
}
