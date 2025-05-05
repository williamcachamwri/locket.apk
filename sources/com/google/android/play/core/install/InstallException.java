package com.google.android.play.core.install;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.play.core.install.model.zza;
import java.util.Locale;

/* compiled from: com.google.android.play:app-update@@2.1.0 */
public class InstallException extends ApiException {
    public InstallException(int i) {
        super(new Status(i, String.format(Locale.getDefault(), "Install Error(%d): %s", new Object[]{Integer.valueOf(i), zza.zza(i)})));
        if (i == 0) {
            throw new IllegalArgumentException("errorCode should not be 0.");
        }
    }

    public int getErrorCode() {
        return super.getStatusCode();
    }
}
