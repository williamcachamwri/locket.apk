package com.google.android.play.core.integrity;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class ah extends y {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ai f27a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ah(ai aiVar, String str, long j) {
        super(str, j);
        this.f27a = aiVar;
    }

    /* access modifiers changed from: package-private */
    public final Task b(Activity activity, Bundle bundle) {
        return this.f27a.f28a.b(activity, bundle);
    }
}
