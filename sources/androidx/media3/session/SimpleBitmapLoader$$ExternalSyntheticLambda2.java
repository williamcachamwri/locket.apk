package androidx.media3.session;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executors;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBitmapLoader$$ExternalSyntheticLambda2 implements Supplier {
    public final Object get() {
        return MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
    }
}
