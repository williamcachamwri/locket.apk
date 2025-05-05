package com.google.android.gms.internal.pal;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzcs extends zzct {
    private static final String zzv = "zzcs";
    private AdvertisingIdClient.Info zzw;

    protected zzcs(Context context) {
        super(context, "");
    }

    public static zzcs zzl(Context context) {
        zzt(context, true);
        return new zzcs(context);
    }

    /* access modifiers changed from: protected */
    public final zzr zzh(Context context, View view, Activity activity) {
        return null;
    }

    /* access modifiers changed from: protected */
    public final zzr zzj(Context context, View view, Activity activity) {
        return null;
    }

    public final String zzm(String str, String str2) {
        byte[] zze = zzbn.zze(str, str2, true);
        if (zze != null) {
            return zzbj.zza(zze, true);
        }
        return Integer.toString(7);
    }

    /* access modifiers changed from: protected */
    public final List zzn(zzdu zzdu, Context context, zzr zzr, zzi zzi) {
        ArrayList arrayList = new ArrayList();
        if (zzdu.zzk() == null) {
            return arrayList;
        }
        arrayList.add(new zzem(zzdu, "ysEnh8zkgcN8WwINs5FP7vGybZW2TtVSX36HO6emvdUrcCkVbC9hrF5Pe5ZSZx3i", "3LpdW89cIASEFv5WvS5ZDEWsiVGQitP33SL3WZgJ6zE=", zzr, zzdu.zza(), 24));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final void zzo(zzdu zzdu, Context context, zzr zzr, zzi zzi) {
        if (zzdu.zzb) {
            AdvertisingIdClient.Info info = this.zzw;
            if (info != null) {
                String id = info.getId();
                if (!TextUtils.isEmpty(id)) {
                    zzr.zzs(zzdx.zzd(id));
                    zzr.zzac(6);
                    zzr.zzr(this.zzw.isLimitAdTrackingEnabled());
                }
                this.zzw = null;
                return;
            }
            return;
        }
        zzu(zzn(zzdu, context, zzr, zzi));
    }

    public final void zzp(AdvertisingIdClient.Info info) {
        this.zzw = info;
    }
}
