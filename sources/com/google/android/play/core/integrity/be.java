package com.google.android.play.core.integrity;

import android.content.Context;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.ai;
import com.google.android.play.integrity.internal.t;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class be extends t {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f46a;
    final /* synthetic */ bn b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    be(bn bnVar, TaskCompletionSource taskCompletionSource, Context context) {
        super(taskCompletionSource);
        this.b = bnVar;
        this.f46a = context;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        this.b.d.trySetResult(Integer.valueOf(ai.a(this.f46a)));
    }
}
