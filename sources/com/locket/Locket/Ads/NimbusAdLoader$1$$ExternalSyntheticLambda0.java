package com.locket.Locket.Ads;

import android.widget.FrameLayout;
import com.locket.Locket.Ads.NimbusAdLoader;
import java.util.HashMap;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NimbusAdLoader$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ NimbusAdLoader.AnonymousClass1 f$0;
    public final /* synthetic */ FrameLayout f$1;
    public final /* synthetic */ HashMap f$2;

    public /* synthetic */ NimbusAdLoader$1$$ExternalSyntheticLambda0(NimbusAdLoader.AnonymousClass1 r1, FrameLayout frameLayout, HashMap hashMap) {
        this.f$0 = r1;
        this.f$1 = frameLayout;
        this.f$2 = hashMap;
    }

    public final void run() {
        this.f$0.lambda$onAdRendered$0(this.f$1, this.f$2);
    }
}
