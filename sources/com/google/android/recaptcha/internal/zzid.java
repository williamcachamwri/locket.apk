package com.google.android.recaptcha.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzid implements zzij {
    private final Context zza;

    public zzid(Context context) {
        this.zza = context;
    }

    public final /* synthetic */ Object cs(Object[] objArr) {
        return zzig.zza(this, objArr);
    }

    public final Object zza(Object... objArr) {
        Intent intent;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        if (Build.VERSION.SDK_INT >= 33) {
            intent = this.zza.registerReceiver((BroadcastReceiver) null, intentFilter, 4);
        } else {
            intent = this.zza.registerReceiver((BroadcastReceiver) null, intentFilter);
        }
        if (intent != null) {
            int intExtra = intent.getIntExtra("health", -1);
            int intExtra2 = intent.getIntExtra("level", -1);
            int intExtra3 = intent.getIntExtra("plugged", -1);
            boolean booleanExtra = intent.getBooleanExtra("present", false);
            int intExtra4 = intent.getIntExtra("scale", -1);
            int intExtra5 = intent.getIntExtra("status", -1);
            String stringExtra = intent.getStringExtra("technology");
            if (stringExtra == null) {
                stringExtra = "";
            }
            return new Object[]{Integer.valueOf(intExtra), Integer.valueOf(intExtra2), Integer.valueOf(intExtra3), Boolean.valueOf(booleanExtra), Integer.valueOf(intExtra4), Integer.valueOf(intExtra5), stringExtra, Integer.valueOf(intent.getIntExtra("temperature", -1)), Integer.valueOf(intent.getIntExtra("voltage", -1))};
        }
        throw new zzcg(7, 19, (Throwable) null);
    }
}
