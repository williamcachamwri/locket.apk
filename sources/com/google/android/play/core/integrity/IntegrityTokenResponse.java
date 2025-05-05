package com.google.android.play.core.integrity;

import android.app.Activity;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public abstract class IntegrityTokenResponse {
    public abstract Task<Integer> showDialog(Activity activity, int i);

    public abstract String token();
}
