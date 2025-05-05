package com.google.ads.interactivemedia.v3.impl.data;

import com.google.ads.interactivemedia.v3.api.FriendlyObstruction;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public abstract class zzca {
    public abstract zzcd build();

    public zzca friendlyObstructions(Collection<FriendlyObstruction> collection) {
        ArrayList arrayList = new ArrayList();
        for (FriendlyObstruction next : collection) {
            arrayList.add(zzcc.builder().view(next.getView()).purpose(next.getPurpose()).detailedReason(next.getDetailedReason()).build());
        }
        return obstructions(arrayList);
    }

    public abstract zzca obstructions(List<zzcc> list);
}
