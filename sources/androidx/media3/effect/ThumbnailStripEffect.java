package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.VideoFrameProcessingException;
import java.util.ArrayList;
import java.util.List;

final class ThumbnailStripEffect implements GlEffect {
    private int currentThumbnailIndex;
    final int stripHeight;
    final int stripWidth;
    private final List<Long> timestampsMs = new ArrayList();

    public ThumbnailStripEffect(int i, int i2) {
        this.stripWidth = i;
        this.stripHeight = i2;
    }

    public ThumbnailStripShaderProgram toGlShaderProgram(Context context, boolean z) throws VideoFrameProcessingException {
        return new ThumbnailStripShaderProgram(context, z, this);
    }

    public void setTimestampsMs(List<Long> list) {
        this.timestampsMs.clear();
        this.timestampsMs.addAll(list);
        this.currentThumbnailIndex = 0;
    }

    public boolean isDone() {
        return this.currentThumbnailIndex >= this.timestampsMs.size();
    }

    public int getNextThumbnailIndex() {
        return this.currentThumbnailIndex;
    }

    public long getNextTimestampMs() {
        if (isDone()) {
            return Long.MIN_VALUE;
        }
        return this.timestampsMs.get(this.currentThumbnailIndex).longValue();
    }

    public int getNumberOfThumbnails() {
        return this.timestampsMs.size();
    }

    /* access modifiers changed from: package-private */
    public void onThumbnailDrawn() {
        this.currentThumbnailIndex++;
    }
}
