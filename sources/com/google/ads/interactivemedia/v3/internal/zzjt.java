package com.google.ads.interactivemedia.v3.internal;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzjt extends zzkx {
    private final Activity zzh;
    private final View zzi;

    public zzjt(zzjj zzjj, String str, String str2, zzan zzan, int i, int i2, View view, Activity activity) {
        super(zzjj, "3uIyPH12G92QFP63DNIOh82j8VF90h9kFqPNhDqRUCo8ufPXfg4SvIOT6xTdvJUh", "mkv0O+E3pw6iWtJ8IDlF26p17YivjEWbfcApoyQN9bA=", zzan, i, 62);
        this.zzi = view;
        this.zzh = activity;
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzi != null) {
            boolean booleanValue = ((Boolean) zzls.zzc().zza(zzmj.zzm)).booleanValue();
            Object[] objArr = (Object[]) this.zze.invoke((Object) null, new Object[]{this.zzi, this.zzh, Boolean.valueOf(booleanValue)});
            synchronized (this.zzd) {
                this.zzd.zzc(((Long) objArr[0]).longValue());
                this.zzd.zze(((Long) objArr[1]).longValue());
                if (booleanValue) {
                    this.zzd.zzd((String) objArr[2]);
                }
            }
        }
    }
}
