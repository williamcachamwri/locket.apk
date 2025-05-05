package com.google.android.play.core.integrity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.media3.common.C;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.common.PlayCoreDialogWrapperActivity;
import com.google.android.play.integrity.internal.ae;
import com.google.android.play.integrity.internal.q;
import com.google.android.play.integrity.internal.s;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
final class as extends q {

    /* renamed from: a  reason: collision with root package name */
    final TaskCompletionSource f36a;
    final ae b;
    private final s c = new s("RequestDialogCallbackImpl");
    private final String d;
    private final k e;
    private final Activity f;

    as(Context context, k kVar, Activity activity, TaskCompletionSource taskCompletionSource, ae aeVar) {
        this.d = context.getPackageName();
        this.e = kVar;
        this.f36a = taskCompletionSource;
        this.f = activity;
        this.b = aeVar;
    }

    public final void b(Bundle bundle) {
        this.b.v(this.f36a);
        this.c.d("onRequestDialog(%s)", this.d);
        ApiException a2 = this.e.a(bundle);
        if (a2 != null) {
            this.f36a.trySetException(a2);
            return;
        }
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("dialog.intent");
        if (pendingIntent == null) {
            this.c.b("onRequestDialog(%s): got null dialog intent", this.d);
            this.f36a.trySetResult(0);
            return;
        }
        Intent intent = new Intent(this.f, PlayCoreDialogWrapperActivity.class);
        intent.putExtra("confirmation_intent", pendingIntent);
        intent.setFlags(C.BUFFER_FLAG_LAST_SAMPLE);
        intent.putExtra("result_receiver", new ar(this, this.b.c()));
        this.c.a("Starting dialog intent...", new Object[0]);
        this.f.startActivityForResult(intent, 0);
    }
}
