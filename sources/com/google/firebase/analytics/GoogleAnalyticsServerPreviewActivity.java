package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzdy;

/* compiled from: com.google.android.gms:play-services-measurement-api@@22.1.2 */
public class GoogleAnalyticsServerPreviewActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        zzdy.zza((Context) this).zza(getIntent());
        finish();
    }
}
