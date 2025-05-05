package androidx.media3.transformer;

import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.OnInputFrameProcessedListener;
import androidx.media3.common.util.Assertions;
import androidx.media3.transformer.AssetLoader;
import com.google.common.collect.ImmutableMap;

@Deprecated
public final class TextureAssetLoader implements AssetLoader {
    private final AssetLoader.Listener assetLoaderListener;
    private final EditedMediaItem editedMediaItem;
    private final Format format;
    private final OnInputFrameProcessedListener frameProcessedListener;
    private boolean isEndOfStreamSignaled;
    private volatile boolean isStarted;
    private boolean isTrackAdded;
    private volatile long lastQueuedPresentationTimeUs;
    private int progressState;
    private SampleConsumer sampleConsumer;

    public TextureAssetLoader(EditedMediaItem editedMediaItem2, AssetLoader.Listener listener, Format format2, OnInputFrameProcessedListener onInputFrameProcessedListener) {
        boolean z = true;
        Assertions.checkArgument(editedMediaItem2.durationUs != C.TIME_UNSET);
        Assertions.checkArgument((format2.height == -1 || format2.width == -1) ? false : z);
        this.editedMediaItem = editedMediaItem2;
        this.assetLoaderListener = listener;
        this.format = format2.buildUpon().setColorInfo(TransformerUtil.getValidColor(format2.colorInfo)).setSampleMimeType(MimeTypes.VIDEO_RAW).build();
        this.frameProcessedListener = onInputFrameProcessedListener;
        this.progressState = 0;
    }

    public void start() {
        this.progressState = 2;
        this.assetLoaderListener.onDurationUs(this.editedMediaItem.durationUs);
        this.assetLoaderListener.onTrackCount(1);
        this.isStarted = true;
    }

    public int getProgress(ProgressHolder progressHolder) {
        if (this.progressState == 2) {
            progressHolder.progress = Math.round((((float) this.lastQueuedPresentationTimeUs) / ((float) this.editedMediaItem.durationUs)) * 100.0f);
        }
        return this.progressState;
    }

    public ImmutableMap<Integer, String> getDecoderNames() {
        return ImmutableMap.of();
    }

    public void release() {
        this.progressState = 0;
    }

    public boolean queueInputTexture(int i, long j) {
        try {
            if (!this.isTrackAdded) {
                if (!this.isStarted) {
                    return false;
                }
                this.assetLoaderListener.onTrackAdded(this.format, 2);
                this.isTrackAdded = true;
            }
            if (this.sampleConsumer == null) {
                SampleConsumer onOutputFormat = this.assetLoaderListener.onOutputFormat(this.format);
                if (onOutputFormat == null) {
                    return false;
                }
                this.sampleConsumer = onOutputFormat;
                onOutputFormat.setOnInputFrameProcessedListener(this.frameProcessedListener);
            }
            int queueInputTexture = this.sampleConsumer.queueInputTexture(i, j);
            if (queueInputTexture == 2) {
                return false;
            }
            if (queueInputTexture == 3) {
                this.isEndOfStreamSignaled = true;
            }
            this.lastQueuedPresentationTimeUs = j;
            return true;
        } catch (ExportException e) {
            this.assetLoaderListener.onError(e);
            return false;
        } catch (RuntimeException e2) {
            this.assetLoaderListener.onError(ExportException.createForAssetLoader(e2, 1000));
            return false;
        }
    }

    public void signalEndOfVideoInput() {
        try {
            if (!this.isEndOfStreamSignaled) {
                this.isEndOfStreamSignaled = true;
                ((SampleConsumer) Assertions.checkNotNull(this.sampleConsumer)).signalEndOfVideoInput();
            }
        } catch (RuntimeException e) {
            this.assetLoaderListener.onError(ExportException.createForAssetLoader(e, 1000));
        }
    }
}
