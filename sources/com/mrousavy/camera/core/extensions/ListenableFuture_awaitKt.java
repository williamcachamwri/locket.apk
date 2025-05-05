package com.mrousavy.camera.core.extensions;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004H@¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"await", "V", "Lcom/google/common/util/concurrent/ListenableFuture;", "executor", "Ljava/util/concurrent/Executor;", "(Lcom/google/common/util/concurrent/ListenableFuture;Ljava/util/concurrent/Executor;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "react-native-vision-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ListenableFuture+await.kt */
public final class ListenableFuture_awaitKt {
    public static /* synthetic */ Object await$default(ListenableFuture listenableFuture, Executor executor, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            executor = null;
        }
        return await(listenableFuture, executor, continuation);
    }

    public static final <V> Object await(ListenableFuture<V> listenableFuture, Executor executor, Continuation<? super V> continuation) {
        if (listenableFuture.isCancelled()) {
            throw new CancellationException("ListenableFuture<V> has been canceled!");
        } else if (listenableFuture.isDone()) {
            return listenableFuture.get();
        } else {
            SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
            Runnable listenableFuture_awaitKt$await$2$1 = new ListenableFuture_awaitKt$await$2$1(listenableFuture, safeContinuation);
            if (executor == null) {
                executor = ExecutorsKt.asExecutor(Dispatchers.getMain());
            }
            listenableFuture.addListener(listenableFuture_awaitKt$await$2$1, executor);
            Object orThrow = safeContinuation.getOrThrow();
            if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return orThrow;
        }
    }
}
