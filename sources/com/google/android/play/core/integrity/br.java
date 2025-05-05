package com.google.android.play.core.integrity;

import android.app.Activity;
import com.google.android.gms.tasks.Task;
import com.google.android.play.core.integrity.StandardIntegrityManager;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class br extends StandardIntegrityManager.StandardIntegrityToken {

    /* renamed from: a  reason: collision with root package name */
    private final String f55a;
    private final y b;

    br(String str, y yVar) {
        this.f55a = str;
        this.b = yVar;
    }

    public final Task<Integer> showDialog(Activity activity, int i) {
        return this.b.a(activity, i);
    }

    public final String token() {
        return this.f55a;
    }
}
