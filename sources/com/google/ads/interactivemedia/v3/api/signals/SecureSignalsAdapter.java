package com.google.ads.interactivemedia.v3.api.signals;

import android.content.Context;
import com.google.ads.interactivemedia.v3.api.VersionInfo;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public interface SecureSignalsAdapter {
    void collectSignals(Context context, SecureSignalsCollectSignalsCallback secureSignalsCollectSignalsCallback);

    VersionInfo getSDKVersion();

    VersionInfo getVersion();

    void initialize(Context context, SecureSignalsInitializeCallback secureSignalsInitializeCallback);
}
