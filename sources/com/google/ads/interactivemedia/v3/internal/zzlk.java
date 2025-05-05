package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.Executor;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzlk extends zzln {
    private final zzia zza;
    private final zzii zzb;

    public zzlk(Context context, Executor executor, zzm zzm) {
        zzie zzie = new zzie(context, executor, zzm);
        this.zza = zzie;
        this.zzb = new zzii(zzie);
    }

    @Deprecated
    private final IObjectWrapper zzt(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, boolean z) {
        Uri uri;
        try {
            Uri uri2 = (Uri) ObjectWrapper.unwrap(iObjectWrapper);
            Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper2);
            if (z) {
                uri = this.zzb.zzb(uri2, context);
            } else {
                uri = this.zzb.zza(uri2, context, (View) null, (Activity) null);
            }
            return ObjectWrapper.wrap(uri);
        } catch (zzij unused) {
            return null;
        }
    }

    public final int zzb() {
        zzia zzia = this.zza;
        if (!(zzia instanceof zzie)) {
            return -1;
        }
        zzia zza2 = ((zzie) zzia).zza();
        if (zza2 instanceof zzih) {
            return 1;
        }
        if (zza2 instanceof zzhx) {
            return 2;
        }
        return -1;
    }

    @Deprecated
    public final IObjectWrapper zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return zzt(iObjectWrapper, iObjectWrapper2, false);
    }

    @Deprecated
    public final IObjectWrapper zzd(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return zzt(iObjectWrapper, iObjectWrapper2, true);
    }

    @Deprecated
    public final String zze(IObjectWrapper iObjectWrapper, String str) {
        return ((zzie) this.zza).zze((Context) ObjectWrapper.unwrap(iObjectWrapper), str, (View) null, (Activity) null);
    }

    @Deprecated
    public final String zzf(IObjectWrapper iObjectWrapper) {
        return zzg(iObjectWrapper, (byte[]) null);
    }

    @Deprecated
    public final String zzg(IObjectWrapper iObjectWrapper, byte[] bArr) {
        return this.zza.zzg((Context) ObjectWrapper.unwrap(iObjectWrapper), bArr);
    }

    public final String zzh(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3, IObjectWrapper iObjectWrapper4) {
        return this.zza.zze((Context) ObjectWrapper.unwrap(iObjectWrapper), (String) ObjectWrapper.unwrap(iObjectWrapper2), (View) ObjectWrapper.unwrap(iObjectWrapper3), (Activity) ObjectWrapper.unwrap(iObjectWrapper4));
    }

    public final String zzi(IObjectWrapper iObjectWrapper) {
        return ((zzie) this.zza).zzg((Context) ObjectWrapper.unwrap(iObjectWrapper), (byte[]) null);
    }

    public final String zzj() {
        return "ms";
    }

    public final String zzk(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        return this.zza.zzh((Context) ObjectWrapper.unwrap(iObjectWrapper), (View) ObjectWrapper.unwrap(iObjectWrapper2), (Activity) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzb.zzc((MotionEvent) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzm(IObjectWrapper iObjectWrapper) {
        this.zza.zzn((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Deprecated
    public final void zzn(String str, String str2) {
        this.zzb.zzd(str, str2);
    }

    @Deprecated
    public final void zzo(String str) {
        this.zzb.zze(str);
    }

    @Deprecated
    public final boolean zzp(IObjectWrapper iObjectWrapper) {
        return this.zzb.zzg((Uri) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Deprecated
    public final boolean zzq(IObjectWrapper iObjectWrapper) {
        return this.zzb.zzf((Uri) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final boolean zzr() {
        return this.zza.zzq();
    }

    public final boolean zzs() {
        return this.zza.zzs();
    }
}
