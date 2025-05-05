package androidx.media3.exoplayer.hls.playlist;

import androidx.media3.exoplayer.hls.HlsDataSourceFactory;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultHlsPlaylistTracker$$ExternalSyntheticLambda0 implements HlsPlaylistTracker.Factory {
    public final HlsPlaylistTracker createTracker(HlsDataSourceFactory hlsDataSourceFactory, LoadErrorHandlingPolicy loadErrorHandlingPolicy, HlsPlaylistParserFactory hlsPlaylistParserFactory) {
        return new DefaultHlsPlaylistTracker(hlsDataSourceFactory, loadErrorHandlingPolicy, hlsPlaylistParserFactory);
    }
}
