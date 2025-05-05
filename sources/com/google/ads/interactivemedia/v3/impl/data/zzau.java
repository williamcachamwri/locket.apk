package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzrp;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzau extends TestingConfiguration {
    private final boolean disableExperiments;
    private final boolean disableOnScreenDetection;
    private final boolean disableSkipFadeTransition;
    private final boolean enableMonitorAppLifecycle;
    private final zzrp<String, Object> extraParams;
    private final boolean forceAndroidTvMode;
    private final zzrm<Integer> forceExperimentIds;
    private final boolean forceTvMode;
    private final boolean ignoreStrictModeFalsePositives;
    private final boolean useTestStreamManager;
    private final boolean useVideoElementMock;
    private final float videoElementMockDuration;

    private zzau(boolean z, boolean z2, boolean z3, zzrm<Integer> zzrm, boolean z4, float f, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, zzrp<String, Object> zzrp) {
        this.disableExperiments = z;
        this.disableOnScreenDetection = z2;
        this.disableSkipFadeTransition = z3;
        this.forceExperimentIds = zzrm;
        this.useVideoElementMock = z4;
        this.videoElementMockDuration = f;
        this.useTestStreamManager = z5;
        this.enableMonitorAppLifecycle = z6;
        this.forceTvMode = z7;
        this.forceAndroidTvMode = z8;
        this.ignoreStrictModeFalsePositives = z9;
        this.extraParams = zzrp;
    }

    public boolean disableExperiments() {
        return this.disableExperiments;
    }

    public boolean disableOnScreenDetection() {
        return this.disableOnScreenDetection;
    }

    public boolean disableSkipFadeTransition() {
        return this.disableSkipFadeTransition;
    }

    public boolean enableMonitorAppLifecycle() {
        return this.enableMonitorAppLifecycle;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        r1 = r4.forceExperimentIds;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0078, code lost:
        r1 = r4.extraParams;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration
            r2 = 0
            if (r1 == 0) goto L_0x008f
            com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration r5 = (com.google.ads.interactivemedia.v3.impl.data.TestingConfiguration) r5
            boolean r1 = r4.disableExperiments
            boolean r3 = r5.disableExperiments()
            if (r1 != r3) goto L_0x008f
            boolean r1 = r4.disableOnScreenDetection
            boolean r3 = r5.disableOnScreenDetection()
            if (r1 != r3) goto L_0x008f
            boolean r1 = r4.disableSkipFadeTransition
            boolean r3 = r5.disableSkipFadeTransition()
            if (r1 != r3) goto L_0x008f
            com.google.ads.interactivemedia.v3.internal.zzrm<java.lang.Integer> r1 = r4.forceExperimentIds
            if (r1 != 0) goto L_0x002e
            com.google.ads.interactivemedia.v3.internal.zzrm r1 = r5.forceExperimentIds()
            if (r1 != 0) goto L_0x008f
            goto L_0x0038
        L_0x002e:
            com.google.ads.interactivemedia.v3.internal.zzrm r3 = r5.forceExperimentIds()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x008f
        L_0x0038:
            boolean r1 = r4.useVideoElementMock
            boolean r3 = r5.useVideoElementMock()
            if (r1 != r3) goto L_0x008f
            float r1 = r4.videoElementMockDuration
            int r1 = java.lang.Float.floatToIntBits(r1)
            float r3 = r5.videoElementMockDuration()
            int r3 = java.lang.Float.floatToIntBits(r3)
            if (r1 != r3) goto L_0x008f
            boolean r1 = r4.useTestStreamManager
            boolean r3 = r5.useTestStreamManager()
            if (r1 != r3) goto L_0x008f
            boolean r1 = r4.enableMonitorAppLifecycle
            boolean r3 = r5.enableMonitorAppLifecycle()
            if (r1 != r3) goto L_0x008f
            boolean r1 = r4.forceTvMode
            boolean r3 = r5.forceTvMode()
            if (r1 != r3) goto L_0x008f
            boolean r1 = r4.forceAndroidTvMode
            boolean r3 = r5.forceAndroidTvMode()
            if (r1 != r3) goto L_0x008f
            boolean r1 = r4.ignoreStrictModeFalsePositives
            boolean r3 = r5.ignoreStrictModeFalsePositives()
            if (r1 != r3) goto L_0x008f
            com.google.ads.interactivemedia.v3.internal.zzrp<java.lang.String, java.lang.Object> r1 = r4.extraParams
            if (r1 != 0) goto L_0x0083
            com.google.ads.interactivemedia.v3.internal.zzrp r5 = r5.extraParams()
            if (r5 != 0) goto L_0x008f
            goto L_0x008e
        L_0x0083:
            com.google.ads.interactivemedia.v3.internal.zzrp r5 = r5.extraParams()
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x008e
            goto L_0x008f
        L_0x008e:
            return r0
        L_0x008f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.impl.data.zzau.equals(java.lang.Object):boolean");
    }

    public zzrp<String, Object> extraParams() {
        return this.extraParams;
    }

    public boolean forceAndroidTvMode() {
        return this.forceAndroidTvMode;
    }

    public zzrm<Integer> forceExperimentIds() {
        return this.forceExperimentIds;
    }

    public boolean forceTvMode() {
        return this.forceTvMode;
    }

    public boolean ignoreStrictModeFalsePositives() {
        return this.ignoreStrictModeFalsePositives;
    }

    public String toString() {
        zzrp<String, Object> zzrp = this.extraParams;
        String valueOf = String.valueOf(this.forceExperimentIds);
        String valueOf2 = String.valueOf(zzrp);
        return "TestingConfiguration{disableExperiments=" + this.disableExperiments + ", disableOnScreenDetection=" + this.disableOnScreenDetection + ", disableSkipFadeTransition=" + this.disableSkipFadeTransition + ", forceExperimentIds=" + valueOf + ", useVideoElementMock=" + this.useVideoElementMock + ", videoElementMockDuration=" + this.videoElementMockDuration + ", useTestStreamManager=" + this.useTestStreamManager + ", enableMonitorAppLifecycle=" + this.enableMonitorAppLifecycle + ", forceTvMode=" + this.forceTvMode + ", forceAndroidTvMode=" + this.forceAndroidTvMode + ", ignoreStrictModeFalsePositives=" + this.ignoreStrictModeFalsePositives + ", extraParams=" + valueOf2 + "}";
    }

    public boolean useTestStreamManager() {
        return this.useTestStreamManager;
    }

    public boolean useVideoElementMock() {
        return this.useVideoElementMock;
    }

    public float videoElementMockDuration() {
        return this.videoElementMockDuration;
    }

    public int hashCode() {
        int i;
        zzrm<Integer> zzrm = this.forceExperimentIds;
        int i2 = 0;
        if (zzrm == null) {
            i = 0;
        } else {
            i = zzrm.hashCode();
        }
        int i3 = 1237;
        int floatToIntBits = (((((((((((((i ^ (((((((true != this.disableExperiments ? 1237 : 1231) ^ 1000003) * 1000003) ^ (true != this.disableOnScreenDetection ? 1237 : 1231)) * 1000003) ^ (true != this.disableSkipFadeTransition ? 1237 : 1231)) * 1000003)) * 1000003) ^ (true != this.useVideoElementMock ? 1237 : 1231)) * 1000003) ^ Float.floatToIntBits(this.videoElementMockDuration)) * 1000003) ^ (true != this.useTestStreamManager ? 1237 : 1231)) * 1000003) ^ (true != this.enableMonitorAppLifecycle ? 1237 : 1231)) * 1000003) ^ (true != this.forceTvMode ? 1237 : 1231)) * 1000003) ^ (true != this.forceAndroidTvMode ? 1237 : 1231)) * 1000003;
        if (true == this.ignoreStrictModeFalsePositives) {
            i3 = 1231;
        }
        int i4 = (floatToIntBits ^ i3) * 1000003;
        zzrp<String, Object> zzrp = this.extraParams;
        if (zzrp != null) {
            i2 = zzrp.hashCode();
        }
        return i4 ^ i2;
    }
}
