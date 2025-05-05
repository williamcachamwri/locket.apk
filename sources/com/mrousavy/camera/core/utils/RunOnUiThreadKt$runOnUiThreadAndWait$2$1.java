package com.mrousavy.camera.core.utils;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "T", "run"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: runOnUiThread.kt */
public final class RunOnUiThreadKt$runOnUiThreadAndWait$2$1 implements Runnable {
    final /* synthetic */ CancellableContinuation<T> $continuation;
    final /* synthetic */ Function0<T> $function;

    public RunOnUiThreadKt$runOnUiThreadAndWait$2$1(CancellableContinuation<? super T> cancellableContinuation, Function0<? extends T> function0) {
        this.$continuation = cancellableContinuation;
        this.$function = function0;
    }

    public final void run() {
        if (!this.$continuation.isCancelled()) {
            T invoke = this.$function.invoke();
            Result.Companion companion = Result.Companion;
            this.$continuation.resumeWith(Result.m2444constructorimpl(invoke));
            return;
        }
        throw new CancellationException();
    }
}
