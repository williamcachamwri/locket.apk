package com.facebook.imagepipeline.backends.okhttp3;

import android.os.Looper;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher$fetchWithRequest$1", "Lcom/facebook/imagepipeline/producers/BaseProducerContextCallbacks;", "onCancellationRequested", "", "imagepipeline-okhttp3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OkHttpNetworkFetcher.kt */
public final class OkHttpNetworkFetcher$fetchWithRequest$1 extends BaseProducerContextCallbacks {
    final /* synthetic */ Call $call;
    final /* synthetic */ OkHttpNetworkFetcher this$0;

    OkHttpNetworkFetcher$fetchWithRequest$1(Call call, OkHttpNetworkFetcher okHttpNetworkFetcher) {
        this.$call = call;
        this.this$0 = okHttpNetworkFetcher;
    }

    public void onCancellationRequested() {
        if (!Intrinsics.areEqual((Object) Looper.myLooper(), (Object) Looper.getMainLooper())) {
            this.$call.cancel();
        } else {
            this.this$0.cancellationExecutor.execute(new OkHttpNetworkFetcher$fetchWithRequest$1$$ExternalSyntheticLambda0(this.$call));
        }
    }

    /* access modifiers changed from: private */
    public static final void onCancellationRequested$lambda$0(Call call) {
        call.cancel();
    }
}
