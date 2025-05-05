package com.adsbynimbus.render.internal;

import com.iab.omid.library.adsbynimbus.adsession.AdSessionConfiguration;
import com.iab.omid.library.adsbynimbus.adsession.CreativeType;
import com.iab.omid.library.adsbynimbus.adsession.ImpressionType;
import com.iab.omid.library.adsbynimbus.adsession.Owner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/iab/omid/library/adsbynimbus/adsession/AdSessionConfiguration;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OpenMeasurement.kt */
final class OMSession$configuration$2 extends Lambda implements Function0<AdSessionConfiguration> {
    final /* synthetic */ CreativeType $creativeType;
    final /* synthetic */ OMSession this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OMSession$configuration$2(CreativeType creativeType, OMSession oMSession) {
        super(0);
        this.$creativeType = creativeType;
        this.this$0 = oMSession;
    }

    public final AdSessionConfiguration invoke() {
        return AdSessionConfiguration.createAdSessionConfiguration(this.$creativeType, ImpressionType.ONE_PIXEL, Owner.NATIVE, this.this$0.getMediaEventsOwner(), false);
    }
}
