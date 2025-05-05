package com.mrousavy.camera.core.extensions;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "V", "run"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ListenableFuture+await.kt */
final class ListenableFuture_awaitKt$await$2$1 implements Runnable {
    final /* synthetic */ Continuation<V> $continuation;
    final /* synthetic */ ListenableFuture<V> $this_await;

    ListenableFuture_awaitKt$await$2$1(ListenableFuture<V> listenableFuture, Continuation<? super V> continuation) {
        this.$this_await = listenableFuture;
        this.$continuation = continuation;
    }

    public final void run() {
        if (this.$this_await.isCancelled() || !JobKt.isActive(this.$continuation.getContext())) {
            throw new CancellationException("ListenableFuture<V> has been canceled!");
        }
        try {
            Continuation<V> continuation = this.$continuation;
            Result.Companion companion = Result.Companion;
            continuation.resumeWith(Result.m2444constructorimpl(this.$this_await.get()));
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                Continuation<V> continuation2 = this.$continuation;
                Result.Companion companion2 = Result.Companion;
                continuation2.resumeWith(Result.m2444constructorimpl(ResultKt.createFailure(cause)));
                return;
            }
            throw e;
        }
    }
}
