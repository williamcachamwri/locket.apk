package com.google.ads.interactivemedia.omid.library.adsession;

import android.view.View;
import com.google.ads.interactivemedia.v3.internal.zzcp;
import com.google.ads.interactivemedia.v3.internal.zzcr;
import com.google.ads.interactivemedia.v3.internal.zzcv;
import com.google.ads.interactivemedia.v3.internal.zzcz;
import com.google.ads.interactivemedia.v3.internal.zzdf;
import com.google.ads.interactivemedia.v3.internal.zzdg;
import com.google.ads.interactivemedia.v3.internal.zzdj;
import com.google.ads.interactivemedia.v3.internal.zzdp;
import com.google.ads.interactivemedia.v3.internal.zzeg;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zze extends zza {
    private final zzc zza;
    private final zzcv zzb = new zzcv();
    private zzeg zzc;
    private zzdf zzd;
    private boolean zze = false;
    private boolean zzf = false;
    private final String zzg;

    zze(zzb zzb2, zzc zzc2) {
        String uuid = UUID.randomUUID().toString();
        this.zza = zzc2;
        this.zzg = uuid;
        zzl((View) null);
        if (zzc2.zzc() == zzd.HTML || zzc2.zzc() == zzd.JAVASCRIPT) {
            this.zzd = new zzdg(uuid, zzc2.zza());
        } else {
            this.zzd = new zzdj(uuid, zzc2.zzh(), (String) null);
        }
        this.zzd.zzn();
        zzcr.zza().zzd(this);
        this.zzd.zzf(zzb2);
    }

    private final void zzl(View view) {
        this.zzc = new zzeg(view);
    }

    public final void zzb(View view, FriendlyObstructionPurpose friendlyObstructionPurpose, String str) {
        if (!this.zzf) {
            this.zzb.zzb(view, friendlyObstructionPurpose, str);
        }
    }

    public final void zzc() {
        if (!this.zzf) {
            this.zzc.clear();
            zze();
            this.zzf = true;
            this.zzd.zze();
            zzcr.zza().zze(this);
            this.zzd.zzc();
            this.zzd = null;
        }
    }

    public final void zzd(View view) {
        if (!this.zzf) {
            zzdp.zzb(view, "AdView is null");
            if (zzg() != view) {
                zzl(view);
                this.zzd.zzb();
                Collection<zze> zzc2 = zzcr.zza().zzc();
                if (zzc2 != null && !zzc2.isEmpty()) {
                    for (zze zze2 : zzc2) {
                        if (zze2 != this && zze2.zzg() == view) {
                            zze2.zzc.clear();
                        }
                    }
                }
            }
        }
    }

    public final void zze() {
        if (!this.zzf) {
            this.zzb.zzc();
        }
    }

    public final void zzf() {
        if (!this.zze) {
            this.zze = true;
            zzcr.zza().zzf(this);
            this.zzd.zzl(zzcz.zzb().zza());
            this.zzd.zzg(zzcp.zza().zzb());
            this.zzd.zzi(this, this.zza);
        }
    }

    public final View zzg() {
        return (View) this.zzc.get();
    }

    public final zzdf zzh() {
        return this.zzd;
    }

    public final String zzi() {
        return this.zzg;
    }

    public final List zzj() {
        return this.zzb.zza();
    }

    public final boolean zzk() {
        return this.zze && !this.zzf;
    }
}
