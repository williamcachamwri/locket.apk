package com.google.android.gms.internal.pal;

import android.content.Context;
import android.os.Looper;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzfu extends BaseGmsClient {
    protected zzfu(Context context, Looper looper, int i, BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        super(context, looper, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, baseConnectionCallbacks, baseOnConnectionFailedListener, (String) null);
    }
}
