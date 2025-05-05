package com.google.ads.interactivemedia.v3.internal;

import android.content.Context;
import android.os.Looper;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzlr extends BaseGmsClient {
    protected zzlr(Context context, Looper looper, int i, BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        super(context, looper, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, baseConnectionCallbacks, baseOnConnectionFailedListener, (String) null);
    }
}
