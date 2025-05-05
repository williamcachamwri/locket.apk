package androidx.media3.exoplayer;

import androidx.media3.common.PlaybackParameters;

public interface MediaClock {
    PlaybackParameters getPlaybackParameters();

    long getPositionUs();

    boolean hasSkippedSilenceSinceLastCall() {
        return false;
    }

    void setPlaybackParameters(PlaybackParameters playbackParameters);
}
