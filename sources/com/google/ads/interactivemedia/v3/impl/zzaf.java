package com.google.ads.interactivemedia.v3.impl;

import android.view.View;
import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import com.google.ads.interactivemedia.v3.api.FriendlyObstruction;
import com.google.ads.interactivemedia.v3.api.FriendlyObstructionPurpose;
import com.google.ads.interactivemedia.v3.impl.data.zzbg;
import com.google.ads.interactivemedia.v3.impl.data.zzbh;
import com.google.ads.interactivemedia.v3.internal.zzqh;
import com.google.ads.interactivemedia.v3.internal.zzrm;
import com.google.ads.interactivemedia.v3.internal.zzro;
import com.google.ads.interactivemedia.v3.internal.zzru;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public class zzaf implements BaseDisplayContainer {
    private static int zza;
    private ViewGroup zzb;
    private Collection zzc = zzrm.zzm();
    private Map zzd = zzru.zzo();
    private final Set zze = new HashSet();
    private zzae zzf = null;
    private boolean zzg = false;

    public zzaf(ViewGroup viewGroup) {
        this.zzb = viewGroup;
    }

    public final void claim() {
        zzqh.zze(!this.zzg, "A given DisplayContainer may only be used once");
        this.zzg = true;
    }

    public final void destroy() {
        ViewGroup viewGroup = this.zzb;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        this.zzf = null;
    }

    public final ViewGroup getAdContainer() {
        return this.zzb;
    }

    public final Collection<CompanionAdSlot> getCompanionSlots() {
        return this.zzc;
    }

    public final void registerFriendlyObstruction(FriendlyObstruction friendlyObstruction) {
        if (friendlyObstruction != null && !this.zze.contains(friendlyObstruction)) {
            this.zze.add(friendlyObstruction);
            zzae zzae = this.zzf;
            if (zzae != null) {
                ((zzbt) zzae).zza(friendlyObstruction);
            }
        }
    }

    public final void registerVideoControlsOverlay(View view) {
        if (view != null) {
            zzbg builder = zzbh.builder();
            builder.view(view);
            builder.purpose(FriendlyObstructionPurpose.VIDEO_CONTROLS);
            zzbh build = builder.build();
            if (!this.zze.contains(build)) {
                this.zze.add(build);
                zzae zzae = this.zzf;
                if (zzae != null) {
                    ((zzbt) zzae).zza(build);
                }
            }
        }
    }

    public final void setAdContainer(ViewGroup viewGroup) {
        viewGroup.getClass();
        this.zzb = viewGroup;
    }

    public final void setCompanionSlots(Collection<CompanionAdSlot> collection) {
        if (collection == null) {
            collection = zzrm.zzm();
        }
        zzro zzro = new zzro();
        for (CompanionAdSlot next : collection) {
            if (next != null) {
                int i = zza;
                zza = i + 1;
                zzro.zza("compSlot_" + i, next);
            }
        }
        this.zzd = zzro.zzc();
        this.zzc = collection;
    }

    public final void unregisterAllFriendlyObstructions() {
        this.zze.clear();
        zzae zzae = this.zzf;
        if (zzae != null) {
            ((zzbt) zzae).zzf();
        }
    }

    public final void unregisterAllVideoControlsOverlays() {
        this.zze.clear();
        zzae zzae = this.zzf;
        if (zzae != null) {
            ((zzbt) zzae).zzf();
        }
    }

    public final Map zza() {
        return this.zzd;
    }

    public final Set zzb() {
        return new HashSet(this.zze);
    }

    public final void zzc(zzae zzae) {
        this.zzf = zzae;
    }
}
