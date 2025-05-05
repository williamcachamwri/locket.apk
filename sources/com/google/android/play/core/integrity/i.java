package com.google.android.play.core.integrity;

import android.os.Bundle;
import com.google.android.gms.common.api.ApiException;

/* compiled from: com.google.android.play:integrity@@1.3.0 */
public final class i implements k {
    i() {
    }

    public final ApiException a(Bundle bundle) {
        int i = bundle.getInt("error");
        if (i == 0) {
            return null;
        }
        return new IntegrityServiceException(i, (Throwable) null);
    }
}
