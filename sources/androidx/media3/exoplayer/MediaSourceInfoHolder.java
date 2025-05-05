package androidx.media3.exoplayer;

import androidx.media3.common.Timeline;

interface MediaSourceInfoHolder {
    Timeline getTimeline();

    Object getUid();
}
