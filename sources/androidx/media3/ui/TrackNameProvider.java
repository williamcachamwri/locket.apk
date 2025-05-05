package androidx.media3.ui;

import androidx.media3.common.Format;

public interface TrackNameProvider {
    String getTrackName(Format format);
}
