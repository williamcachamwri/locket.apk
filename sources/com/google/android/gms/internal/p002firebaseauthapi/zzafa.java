package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzafa  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
final class zzafa extends BroadcastReceiver {
    private final String zza;
    private final /* synthetic */ zzaew zzb;

    public zzafa(zzaew zzaew, String str) {
        this.zzb = zzaew;
        this.zza = str;
    }

    public final void onReceive(Context context, Intent intent) {
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            if (((Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")).getStatusCode() == 0) {
                String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                zzaez zzaez = (zzaez) this.zzb.zzd.get(this.zza);
                if (zzaez == null) {
                    zzaew.zza.e("Verification code received with no active retrieval session.", new Object[0]);
                } else {
                    zzaez.zze = zzaew.zza(str);
                    if (zzaez.zze == null) {
                        zzaew.zza.e("Unable to extract verification code.", new Object[0]);
                    } else if (!zzag.zzc(zzaez.zzd)) {
                        zzaew.zza(this.zzb, this.zza);
                    }
                }
            }
            context.getApplicationContext().unregisterReceiver(this);
        }
    }
}
