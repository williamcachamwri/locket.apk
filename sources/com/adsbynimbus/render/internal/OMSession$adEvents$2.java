package com.adsbynimbus.render.internal;

import com.iab.omid.library.adsbynimbus.adsession.AdEvents;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/iab/omid/library/adsbynimbus/adsession/AdEvents;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OpenMeasurement.kt */
final class OMSession$adEvents$2 extends Lambda implements Function0<AdEvents> {
    final /* synthetic */ OMSession this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OMSession$adEvents$2(OMSession oMSession) {
        super(0);
        this.this$0 = oMSession;
    }

    public final AdEvents invoke() {
        return AdEvents.createAdEvents(this.this$0.getAdSession());
    }
}
