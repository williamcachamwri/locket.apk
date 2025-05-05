package com.google.ads.interactivemedia.v3.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final /* synthetic */ class zznp implements Continuation {
    public final Object then(Task task) {
        return Boolean.valueOf(task.isSuccessful());
    }
}
