package com.google.android.gms.internal.pal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzfn extends zzfq {
    private final zzcq zza;
    private final zzcs zzb;
    private final zzcv zzc;
    private boolean zzd = false;

    @Deprecated
    public zzfn(String str, Context context, boolean z) {
        zzcu zzl = zzcu.zzl("h.3.2.2/n.android.3.2.2", context, false);
        this.zza = zzl;
        this.zzc = new zzcv(zzl);
        this.zzb = zzcs.zzl(context);
    }

    @Deprecated
    private final IObjectWrapper zzs(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, boolean z) {
        Uri uri;
        try {
            Uri uri2 = (Uri) ObjectWrapper.unwrap(iObjectWrapper);
            Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper2);
            if (z) {
                uri = this.zzc.zzb(uri2, context);
            } else {
                uri = this.zzc.zza(uri2, context, (View) null, (Activity) null);
            }
            return ObjectWrapper.wrap(uri);
        } catch (zzcw unused) {
            return null;
        }
    }

    public final int zzb() {
        return this.zza instanceof zzcu ? 1 : -1;
    }

    @Deprecated
    public final IObjectWrapper zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return zzs(iObjectWrapper, iObjectWrapper2, false);
    }

    @Deprecated
    public final IObjectWrapper zzd(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return zzs(iObjectWrapper, iObjectWrapper2, true);
    }

    @Deprecated
    public final String zze(IObjectWrapper iObjectWrapper, String str) {
        return ((zzcr) this.zza).zza((Context) ObjectWrapper.unwrap(iObjectWrapper), str, (View) null, (Activity) null);
    }

    @Deprecated
    public final String zzf(IObjectWrapper iObjectWrapper) {
        return zzg(iObjectWrapper, (byte[]) null);
    }

    @Deprecated
    public final String zzg(IObjectWrapper iObjectWrapper, byte[] bArr) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        String zzc2 = this.zza.zzc(context, bArr);
        zzcs zzcs = this.zzb;
        if (zzcs == null || !this.zzd) {
            return zzc2;
        }
        String zzm = this.zzb.zzm(zzc2, zzcs.zzc(context, bArr));
        this.zzd = false;
        return zzm;
    }

    public final String zzh(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, IObjectWrapper iObjectWrapper4) {
        return this.zza.zza((Context) ObjectWrapper.unwrap(iObjectWrapper), (String) ObjectWrapper.unwrap(iObjectWrapper2), (View) ObjectWrapper.unwrap(iObjectWrapper3), (Activity) ObjectWrapper.unwrap(iObjectWrapper4));
    }

    public final String zzi(IObjectWrapper iObjectWrapper) {
        return this.zza.zzb((Context) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final String zzj() {
        return "ms";
    }

    public final String zzk(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        return this.zza.zzd((Context) ObjectWrapper.unwrap(iObjectWrapper), (View) ObjectWrapper.unwrap(iObjectWrapper2), (Activity) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzc.zzc((MotionEvent) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzm(IObjectWrapper iObjectWrapper) {
        this.zza.zzf((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Deprecated
    public final void zzn(String str, String str2) {
        this.zzc.zzd(str, str2);
    }

    @Deprecated
    public final void zzo(String str) {
        this.zzc.zze(str);
    }

    @Deprecated
    public final boolean zzp(IObjectWrapper iObjectWrapper) {
        return this.zzc.zzg((Uri) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Deprecated
    public final boolean zzq(IObjectWrapper iObjectWrapper) {
        return this.zzc.zzf((Uri) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Deprecated
    public final boolean zzr(String str, boolean z) {
        if (this.zzb == null) {
            return false;
        }
        this.zzb.zzp(new AdvertisingIdClient.Info(str, z));
        this.zzd = true;
        return true;
    }
}
