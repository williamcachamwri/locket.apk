package com.locket.Locket;

import android.app.Activity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.play.core.appupdate.AppUpdateInfo;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppUpdateModule$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ AppUpdateModule f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Activity f$3;

    public /* synthetic */ AppUpdateModule$$ExternalSyntheticLambda0(AppUpdateModule appUpdateModule, int i, String str, Activity activity) {
        this.f$0 = appUpdateModule;
        this.f$1 = i;
        this.f$2 = str;
        this.f$3 = activity;
    }

    public final void onSuccess(Object obj) {
        this.f$0.lambda$startUpdate$0(this.f$1, this.f$2, this.f$3, (AppUpdateInfo) obj);
    }
}
