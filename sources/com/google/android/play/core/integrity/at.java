package com.google.android.play.core.integrity;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.ae;
import com.google.android.play.integrity.internal.an;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class at {

    /* renamed from: a  reason: collision with root package name */
    private final an f37a;
    private final an b;

    at(an anVar, an anVar2) {
        this.f37a = anVar;
        this.b = anVar2;
    }

    /* access modifiers changed from: package-private */
    public final as a(Activity activity, TaskCompletionSource taskCompletionSource, ae aeVar) {
        Object a2 = this.f37a.a();
        a2.getClass();
        k kVar = (k) this.b.a();
        kVar.getClass();
        activity.getClass();
        aeVar.getClass();
        return new as((Context) a2, kVar, activity, taskCompletionSource, aeVar);
    }
}
