package com.adsbynimbus.render.internal;

import com.iab.omid.library.adsbynimbus.adsession.PossibleObstructionListener;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class OMSession$adSession$2$$ExternalSyntheticLambda0 implements PossibleObstructionListener {
    public final /* synthetic */ Function1 f$0;

    public /* synthetic */ OMSession$adSession$2$$ExternalSyntheticLambda0(Function1 function1) {
        this.f$0 = function1;
    }

    public final void onPossibleObstructionsDetected(String str, List list) {
        OMSession$adSession$2.invoke$lambda$2$lambda$1$lambda$0(this.f$0, str, list);
    }
}
