package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import io.sentry.protocol.ViewHierarchyNode;
import java.lang.reflect.Type;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzfg implements zzwd {
    zzfg(zzfh zzfh) {
    }

    public final /* bridge */ /* synthetic */ zzvw zza(Object obj, Type type, zzwc zzwc) {
        CompanionAdSlot companionAdSlot = (CompanionAdSlot) obj;
        int width = companionAdSlot.getWidth();
        int height = companionAdSlot.getHeight();
        return new zzwb(width + ViewHierarchyNode.JsonKeys.X + height);
    }
}
