package com.google.android.play.core.integrity;

import android.app.Activity;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class aq extends IntegrityTokenResponse {

    /* renamed from: a  reason: collision with root package name */
    private final String f34a;
    private final y b;

    aq(String str, y yVar) {
        this.f34a = str;
        this.b = yVar;
    }

    public final Task<Integer> showDialog(Activity activity, int i) {
        return this.b.a(activity, i);
    }

    public final String token() {
        return this.f34a;
    }
}
