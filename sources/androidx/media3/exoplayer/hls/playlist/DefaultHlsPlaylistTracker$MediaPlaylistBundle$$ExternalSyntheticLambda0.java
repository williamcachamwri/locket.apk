package androidx.media3.exoplayer.hls.playlist;

import android.net.Uri;
import androidx.media3.exoplayer.hls.playlist.DefaultHlsPlaylistTracker;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultHlsPlaylistTracker$MediaPlaylistBundle$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DefaultHlsPlaylistTracker.MediaPlaylistBundle f$0;
    public final /* synthetic */ Uri f$1;

    public /* synthetic */ DefaultHlsPlaylistTracker$MediaPlaylistBundle$$ExternalSyntheticLambda0(DefaultHlsPlaylistTracker.MediaPlaylistBundle mediaPlaylistBundle, Uri uri) {
        this.f$0 = mediaPlaylistBundle;
        this.f$1 = uri;
    }

    public final void run() {
        this.f$0.m521lambda$loadPlaylistInternal$0$androidxmedia3exoplayerhlsplaylistDefaultHlsPlaylistTracker$MediaPlaylistBundle(this.f$1);
    }
}
