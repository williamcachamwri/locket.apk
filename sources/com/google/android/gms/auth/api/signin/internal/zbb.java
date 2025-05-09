package com.google.android.gms.auth.api.signin.internal;

import androidx.browser.trusted.sharing.ShareTarget;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusPendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* compiled from: com.google.android.gms:play-services-auth@@21.2.0 */
public final class zbb implements Runnable {
    private static final Logger zba = new Logger("RevokeAccessOperation", new String[0]);
    private final String zbb;
    private final StatusPendingResult zbc = new StatusPendingResult((GoogleApiClient) null);

    public zbb(String str) {
        this.zbb = Preconditions.checkNotEmpty(str);
    }

    public static PendingResult zba(String str) {
        if (str == null) {
            return PendingResults.immediateFailedResult(new Status(4), (GoogleApiClient) null);
        }
        zbb zbb2 = new zbb(str);
        new Thread(zbb2).start();
        return zbb2.zbc;
    }

    public final void run() {
        Status status = Status.RESULT_INTERNAL_ERROR;
        try {
            String str = this.zbb;
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://accounts.google.com/o/oauth2/revoke?token=" + str).openConnection();
            httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_TYPE, ShareTarget.ENCODING_TYPE_URL_ENCODED);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                status = Status.RESULT_SUCCESS;
            } else {
                zba.e("Unable to revoke access!", new Object[0]);
            }
            Logger logger = zba;
            logger.d("Response Code: " + responseCode, new Object[0]);
        } catch (IOException e) {
            zba.e("IOException when revoking access: ".concat(String.valueOf(e.toString())), new Object[0]);
        } catch (Exception e2) {
            zba.e("Exception when revoking access: ".concat(String.valueOf(e2.toString())), new Object[0]);
        }
        this.zbc.setResult(status);
    }
}
