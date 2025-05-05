package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.OnFailureListener;
import io.sentry.android.core.SentryLogcatAdapter;

/* compiled from: com.google.mlkit:common@@18.11.0 */
public final /* synthetic */ class zzr implements OnFailureListener {
    public final void onFailure(Exception exc) {
        SentryLogcatAdapter.e("OptionalModuleUtils", "Failed to check feature availability", exc);
    }
}
