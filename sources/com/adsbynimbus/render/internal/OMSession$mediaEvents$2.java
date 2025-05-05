package com.adsbynimbus.render.internal;

import com.iab.omid.library.adsbynimbus.adsession.CreativeType;
import com.iab.omid.library.adsbynimbus.adsession.media.MediaEvents;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/iab/omid/library/adsbynimbus/adsession/media/MediaEvents;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OpenMeasurement.kt */
final class OMSession$mediaEvents$2 extends Lambda implements Function0<MediaEvents> {
    final /* synthetic */ CreativeType $creativeType;
    final /* synthetic */ OMSession this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OMSession$mediaEvents$2(CreativeType creativeType, OMSession oMSession) {
        super(0);
        this.$creativeType = creativeType;
        this.this$0 = oMSession;
    }

    public final MediaEvents invoke() {
        if (this.$creativeType == CreativeType.VIDEO) {
            return MediaEvents.createMediaEvents(this.this$0.getAdSession());
        }
        return null;
    }
}
