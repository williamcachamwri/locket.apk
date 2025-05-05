package com.google.ads.interactivemedia.v3.api;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public interface StreamManager extends BaseManager {
    @Deprecated
    double getContentTimeForStreamTime(double d);

    long getContentTimeMsForStreamTimeMs(long j);

    List<CuePoint> getCuePoints();

    @Deprecated
    CuePoint getPreviousCuePointForStreamTime(double d);

    CuePoint getPreviousCuePointForStreamTimeMs(long j);

    String getStreamId();

    @Deprecated
    double getStreamTimeForContentTime(double d);

    long getStreamTimeMsForContentTimeMs(long j);

    void loadThirdPartyStream(String str, List<? extends Map<String, String>> list);

    void replaceAdTagParameters(Map<String, String> map);
}
