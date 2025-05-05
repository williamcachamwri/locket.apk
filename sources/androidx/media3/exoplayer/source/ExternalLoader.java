package androidx.media3.exoplayer.source;

import android.net.Uri;
import com.google.common.util.concurrent.ListenableFuture;

public interface ExternalLoader {
    ListenableFuture<?> load(LoadRequest loadRequest);

    public static final class LoadRequest {
        public final Uri uri;

        public LoadRequest(Uri uri2) {
            this.uri = uri2;
        }
    }
}
