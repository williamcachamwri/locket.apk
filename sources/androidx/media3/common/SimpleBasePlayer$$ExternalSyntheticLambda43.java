package androidx.media3.common;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda43 implements AsyncFunction {
    public final /* synthetic */ ListenableFuture f$0;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda43(ListenableFuture listenableFuture) {
        this.f$0 = listenableFuture;
    }

    public final ListenableFuture apply(Object obj) {
        return SimpleBasePlayer.lambda$handleReplaceMediaItems$31(this.f$0, obj);
    }
}
