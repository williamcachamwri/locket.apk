package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzps;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzrp;

@zzps(zza = zzau.class, zzb = {"extraParams", "isTv", "ignoreStrictModeFalsePositives"})
/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class TestingConfiguration {
    public static final String PARAMETER_KEY = "tcnfp";

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    public interface Builder {
        TestingConfiguration build();

        Builder disableExperiments(boolean z);

        Builder disableOnScreenDetection(boolean z);

        Builder disableSkipFadeTransition(boolean z);

        Builder enableMonitorAppLifecycle(boolean z);

        Builder extraParams(zzrp<String, Object> zzrp);

        Builder forceAndroidTvMode(boolean z);

        Builder forceExperimentIds(zzrm<Integer> zzrm);

        Builder forceTvMode(boolean z);

        Builder ignoreStrictModeFalsePositives(boolean z);

        Builder useTestStreamManager(boolean z);

        Builder useVideoElementMock(boolean z);

        Builder videoElementMockDuration(float f);
    }

    TestingConfiguration() {
    }

    public static Builder builder() {
        zzas zzas = new zzas();
        zzas.disableExperiments(true);
        zzas.disableOnScreenDetection(false);
        zzas.disableSkipFadeTransition(true);
        zzas.useVideoElementMock(false);
        zzas.videoElementMockDuration(30.0f);
        zzas.useTestStreamManager(false);
        zzas.ignoreStrictModeFalsePositives(false);
        zzas.forceTvMode(false);
        zzas.forceAndroidTvMode(false);
        zzas.forceExperimentIds((zzrm<Integer>) null);
        zzas.enableMonitorAppLifecycle(true);
        return zzas;
    }

    public Builder copy() {
        zzas zzas = new zzas();
        zzas.disableExperiments(disableExperiments());
        zzas.disableOnScreenDetection(disableOnScreenDetection());
        zzas.disableSkipFadeTransition(disableSkipFadeTransition());
        zzas.useVideoElementMock(useVideoElementMock());
        zzas.videoElementMockDuration(videoElementMockDuration());
        zzas.useTestStreamManager(useTestStreamManager());
        zzas.forceExperimentIds(forceExperimentIds());
        zzas.enableMonitorAppLifecycle(enableMonitorAppLifecycle());
        zzas.forceTvMode(forceTvMode());
        zzas.forceAndroidTvMode(forceAndroidTvMode());
        zzas.ignoreStrictModeFalsePositives(ignoreStrictModeFalsePositives());
        zzas.extraParams(extraParams());
        return zzas;
    }

    public abstract boolean disableExperiments();

    public abstract boolean disableOnScreenDetection();

    public abstract boolean disableSkipFadeTransition();

    public abstract boolean enableMonitorAppLifecycle();

    public abstract zzrp<String, Object> extraParams();

    public abstract boolean forceAndroidTvMode();

    public abstract zzrm<Integer> forceExperimentIds();

    public abstract boolean forceTvMode();

    public abstract boolean ignoreStrictModeFalsePositives();

    public abstract boolean useTestStreamManager();

    public abstract boolean useVideoElementMock();

    public abstract float videoElementMockDuration();
}
