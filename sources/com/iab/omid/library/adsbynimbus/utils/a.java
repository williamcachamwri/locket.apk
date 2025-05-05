package com.iab.omid.library.adsbynimbus.utils;

import android.app.UiModeManager;
import android.content.Context;
import com.iab.omid.library.adsbynimbus.adsession.DeviceCategory;

public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static UiModeManager f113a;

    public static DeviceCategory a() {
        UiModeManager uiModeManager = f113a;
        if (uiModeManager == null) {
            return DeviceCategory.OTHER;
        }
        int currentModeType = uiModeManager.getCurrentModeType();
        return currentModeType != 1 ? currentModeType != 4 ? DeviceCategory.OTHER : DeviceCategory.CTV : DeviceCategory.MOBILE;
    }

    public static void a(Context context) {
        if (context != null) {
            f113a = (UiModeManager) context.getSystemService("uimode");
        }
    }
}
