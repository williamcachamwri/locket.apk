package com.google.android.play.core.integrity;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.play.integrity.internal.s;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
abstract class y {

    /* renamed from: a  reason: collision with root package name */
    private final s f71a = new s("IntegrityDialogWrapper");
    private final String b;
    private final long c;
    private final Object d = new Object();
    private boolean e;

    y(String str, long j) {
        this.b = str;
        this.c = j;
    }

    public final Task a(Activity activity, int i) {
        synchronized (this.d) {
            if (this.e) {
                Task forResult = Tasks.forResult(0);
                return forResult;
            }
            this.e = true;
            this.f71a.a("checkAndShowDialog(%s)", Integer.valueOf(i));
            Bundle bundle = new Bundle();
            bundle.putInt("dialog.intent.type", i);
            bundle.putString("package.name", this.b);
            bundle.putInt("playcore.integrity.version.major", 1);
            bundle.putInt("playcore.integrity.version.minor", 3);
            bundle.putInt("playcore.integrity.version.patch", 0);
            bundle.putLong("request.token.sid", this.c);
            return b(activity, bundle);
        }
    }

    /* access modifiers changed from: package-private */
    public abstract Task b(Activity activity, Bundle bundle);
}
