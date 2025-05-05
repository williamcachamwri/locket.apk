package androidx.media3.transformer;

import androidx.media3.common.Format;

interface OnMediaItemChangedListener {
    void onMediaItemChanged(EditedMediaItem editedMediaItem, long j, Format format, boolean z);
}
