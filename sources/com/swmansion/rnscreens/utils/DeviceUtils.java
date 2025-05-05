package com.swmansion.rnscreens.utils;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/swmansion/rnscreens/utils/DeviceUtils;", "", "()V", "isPlatformAndroidTV", "", "context", "Landroid/content/Context;", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DeviceUtils.kt */
public final class DeviceUtils {
    public static final DeviceUtils INSTANCE = new DeviceUtils();

    private DeviceUtils() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0003, code lost:
        r3 = r3.getPackageManager();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isPlatformAndroidTV(android.content.Context r3) {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L_0x0013
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            if (r3 == 0) goto L_0x0013
            java.lang.String r1 = "android.software.leanback"
            boolean r3 = r3.hasSystemFeature(r1)
            r1 = 1
            if (r3 != r1) goto L_0x0013
            r0 = r1
        L_0x0013:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.utils.DeviceUtils.isPlatformAndroidTV(android.content.Context):boolean");
    }
}
