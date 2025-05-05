package com.jimmydaddy.imagemarker;

import android.graphics.Bitmap;
import com.jimmydaddy.imagemarker.base.ImageOptions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.jimmydaddy.imagemarker.MarkerImageLoader$loadImages$2", f = "MarkerImageLoader.kt", i = {}, l = {113}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MarkerImageLoader.kt */
final class MarkerImageLoader$loadImages$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Bitmap>>, Object> {
    final /* synthetic */ List<ImageOptions> $images;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MarkerImageLoader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MarkerImageLoader$loadImages$2(List<ImageOptions> list, MarkerImageLoader markerImageLoader, Continuation<? super MarkerImageLoader$loadImages$2> continuation) {
        super(2, continuation);
        this.$images = list;
        this.this$0 = markerImageLoader;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MarkerImageLoader$loadImages$2 markerImageLoader$loadImages$2 = new MarkerImageLoader$loadImages$2(this.$images, this.this$0, continuation);
        markerImageLoader$loadImages$2.L$0 = obj;
        return markerImageLoader$loadImages$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<Bitmap>> continuation) {
        return ((MarkerImageLoader$loadImages$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Iterable<ImageOptions> iterable = this.$images;
            MarkerImageLoader markerImageLoader = this.this$0;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (ImageOptions markerImageLoader$loadImages$2$deferredList$1$1 : iterable) {
                arrayList.add(BuildersKt__Builders_commonKt.async$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new MarkerImageLoader$loadImages$2$deferredList$1$1(markerImageLoader, markerImageLoader$loadImages$2$deferredList$1$1, (Continuation<? super MarkerImageLoader$loadImages$2$deferredList$1$1>) null), 3, (Object) null));
            }
            this.label = 1;
            obj = AwaitKt.awaitAll((List) arrayList, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
