package androidx.media3.transformer;

import androidx.media3.common.C;
import androidx.media3.common.Effect;
import androidx.media3.common.MediaItem;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Objects;

public final class EditedMediaItem {
    static final String GAP_MEDIA_ID = "androidx-media3-GapMediaItem";
    public final long durationUs;
    public final Effects effects;
    public final boolean flattenForSlowMotion;
    public final int frameRate;
    public final MediaItem mediaItem;
    private long presentationDurationUs;
    public final boolean removeAudio;
    public final boolean removeVideo;

    public static final class Builder {
        private long durationUs;
        private Effects effects;
        private boolean flattenForSlowMotion;
        private int frameRate;
        private MediaItem mediaItem;
        private boolean removeAudio;
        private boolean removeVideo;

        public Builder(MediaItem mediaItem2) {
            long j;
            this.mediaItem = mediaItem2;
            if (mediaItem2.localConfiguration == null) {
                j = C.TIME_UNSET;
            } else {
                j = Util.msToUs(mediaItem2.localConfiguration.imageDurationMs);
            }
            this.durationUs = j;
            this.frameRate = C.RATE_UNSET_INT;
            this.effects = Effects.EMPTY;
        }

        private Builder(EditedMediaItem editedMediaItem) {
            this.mediaItem = editedMediaItem.mediaItem;
            this.removeAudio = editedMediaItem.removeAudio;
            this.removeVideo = editedMediaItem.removeVideo;
            this.flattenForSlowMotion = editedMediaItem.flattenForSlowMotion;
            this.durationUs = editedMediaItem.durationUs;
            this.frameRate = editedMediaItem.frameRate;
            this.effects = editedMediaItem.effects;
        }

        public Builder setRemoveAudio(boolean z) {
            this.removeAudio = z;
            return this;
        }

        public Builder setRemoveVideo(boolean z) {
            this.removeVideo = z;
            return this;
        }

        public Builder setFlattenForSlowMotion(boolean z) {
            Assertions.checkArgument(this.mediaItem.clippingConfiguration.equals(MediaItem.ClippingConfiguration.UNSET) || !z, "Slow motion flattening is not supported when clipping is requested");
            this.flattenForSlowMotion = z;
            return this;
        }

        public Builder setDurationUs(long j) {
            Assertions.checkArgument(j > 0);
            this.durationUs = j;
            return this;
        }

        public Builder setFrameRate(int i) {
            Assertions.checkArgument(i > 0);
            this.frameRate = i;
            return this;
        }

        public Builder setEffects(Effects effects2) {
            this.effects = effects2;
            return this;
        }

        public EditedMediaItem build() {
            return new EditedMediaItem(this.mediaItem, this.removeAudio, this.removeVideo, this.flattenForSlowMotion, this.durationUs, this.frameRate, this.effects);
        }

        /* access modifiers changed from: package-private */
        public Builder setMediaItem(MediaItem mediaItem2) {
            this.mediaItem = mediaItem2;
            return this;
        }
    }

    private EditedMediaItem(MediaItem mediaItem2, boolean z, boolean z2, boolean z3, long j, int i, Effects effects2) {
        boolean z4 = false;
        Assertions.checkState(!z || !z2, "Audio and video cannot both be removed");
        if (isGap(mediaItem2)) {
            Assertions.checkArgument(j != C.TIME_UNSET);
            if (!z && !z3 && effects2.audioProcessors.isEmpty()) {
                z4 = true;
            }
            Assertions.checkArgument(z4);
        }
        this.mediaItem = mediaItem2;
        this.removeAudio = z;
        this.removeVideo = z2;
        this.flattenForSlowMotion = z3;
        this.durationUs = j;
        this.frameRate = i;
        this.effects = effects2;
        this.presentationDurationUs = C.TIME_UNSET;
    }

    public Builder buildUpon() {
        return new Builder();
    }

    /* access modifiers changed from: package-private */
    public long getPresentationDurationUs() {
        if (this.presentationDurationUs == C.TIME_UNSET) {
            if (this.mediaItem.clippingConfiguration.equals(MediaItem.ClippingConfiguration.UNSET) || this.durationUs == C.TIME_UNSET) {
                this.presentationDurationUs = this.durationUs;
            } else {
                MediaItem.ClippingConfiguration clippingConfiguration = this.mediaItem.clippingConfiguration;
                boolean z = true;
                Assertions.checkArgument(!clippingConfiguration.relativeToDefaultPosition);
                if (clippingConfiguration.endPositionUs == Long.MIN_VALUE) {
                    this.presentationDurationUs = this.durationUs - clippingConfiguration.startPositionUs;
                } else {
                    if (clippingConfiguration.endPositionUs > this.durationUs) {
                        z = false;
                    }
                    Assertions.checkArgument(z);
                    this.presentationDurationUs = clippingConfiguration.endPositionUs - clippingConfiguration.startPositionUs;
                }
            }
            this.presentationDurationUs = getDurationAfterEffectsApplied(this.presentationDurationUs);
        }
        return this.presentationDurationUs;
    }

    /* access modifiers changed from: package-private */
    public long getDurationAfterEffectsApplied(long j) {
        long j2;
        boolean z = this.removeAudio;
        long j3 = C.TIME_UNSET;
        if (z) {
            j2 = -9223372036854775807L;
        } else {
            UnmodifiableIterator<AudioProcessor> it = this.effects.audioProcessors.iterator();
            j2 = j;
            while (it.hasNext()) {
                j2 = it.next().getDurationAfterProcessorApplied(j2);
            }
        }
        if (!this.removeVideo) {
            UnmodifiableIterator<Effect> it2 = this.effects.videoEffects.iterator();
            while (it2.hasNext()) {
                j = it2.next().getDurationAfterEffectApplied(j);
            }
            j3 = j;
        }
        return Math.max(j2, j3);
    }

    /* access modifiers changed from: package-private */
    public boolean isGap() {
        return isGap(this.mediaItem);
    }

    private static boolean isGap(MediaItem mediaItem2) {
        return Objects.equals(mediaItem2.mediaId, GAP_MEDIA_ID);
    }
}
