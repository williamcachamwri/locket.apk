package com.mrousavy.camera.core.utils;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: runOnUiThread.kt */
public final class RunOnUiThreadKt$runOnUiThread$1 implements Runnable {
    final /* synthetic */ Function0<Unit> $function;

    public RunOnUiThreadKt$runOnUiThread$1(Function0<Unit> function0) {
        this.$function = function0;
    }

    public final void run() {
        this.$function.invoke();
    }
}
