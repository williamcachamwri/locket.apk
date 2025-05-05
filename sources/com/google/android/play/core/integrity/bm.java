package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.af;
import com.google.android.play.integrity.internal.t;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
abstract class bm extends t {
    final /* synthetic */ bn f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    bm(bn bnVar, TaskCompletionSource taskCompletionSource) {
        super(taskCompletionSource);
        this.f = bnVar;
    }

    public final void a(Exception exc) {
        if (!(exc instanceof af)) {
            super.a(exc);
        } else if (bn.k(this.f)) {
            super.a(new StandardIntegrityException(-2, exc));
        } else {
            super.a(new StandardIntegrityException(-9, exc));
        }
    }
}
