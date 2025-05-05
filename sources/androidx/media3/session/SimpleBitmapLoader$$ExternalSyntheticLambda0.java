package androidx.media3.session;

import android.net.Uri;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBitmapLoader$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ Uri f$0;

    public /* synthetic */ SimpleBitmapLoader$$ExternalSyntheticLambda0(Uri uri) {
        this.f$0 = uri;
    }

    public final Object call() {
        return SimpleBitmapLoader.load(this.f$0);
    }
}
