package androidx.media3.exoplayer.source;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import com.google.common.base.Supplier;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ProgressiveMediaSource$Factory$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ Supplier f$0;
    public final /* synthetic */ Consumer f$1;

    public /* synthetic */ ProgressiveMediaSource$Factory$$ExternalSyntheticLambda1(Supplier supplier, Consumer consumer) {
        this.f$0 = supplier;
        this.f$1 = consumer;
    }

    public final Object get() {
        return ReleasableExecutor.from((Executor) this.f$0.get(), this.f$1);
    }
}
