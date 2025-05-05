package com.iab.omid.library.adsbynimbus.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.iab.omid.library.adsbynimbus.adsession.DeviceCategory;
import com.iab.omid.library.adsbynimbus.adsession.OutputDeviceStatus;

public class e {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static OutputDeviceStatus f117a = OutputDeviceStatus.UNKNOWN;

    class a extends BroadcastReceiver {
        a() {
        }

        public void onReceive(Context context, Intent intent) {
            OutputDeviceStatus outputDeviceStatus;
            if (intent.getAction() == "android.media.action.HDMI_AUDIO_PLUG") {
                int intExtra = intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", -1);
                if (intExtra == 0) {
                    outputDeviceStatus = OutputDeviceStatus.NOT_DETECTED;
                } else if (intExtra == 1) {
                    outputDeviceStatus = OutputDeviceStatus.UNKNOWN;
                } else {
                    return;
                }
                OutputDeviceStatus unused = e.f117a = outputDeviceStatus;
            }
        }
    }

    public static OutputDeviceStatus a() {
        return a.a() != DeviceCategory.CTV ? OutputDeviceStatus.UNKNOWN : f117a;
    }

    public static void a(Context context) {
        context.registerReceiver(new a(), new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
    }
}
