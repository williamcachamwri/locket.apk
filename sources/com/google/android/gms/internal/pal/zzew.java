package com.google.android.gms.internal.pal;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzew extends zzfg {
    private List zzi = null;

    public zzew(zzdu zzdu, String str, String str2, zzr zzr, int i, int i2) {
        super(zzdu, "tPxcLkiesd8JzrYIyuRbLGxWAQfsX+C1jrJaS2rsRu6lU/ve1b9hEzSSzo6VwqXx", "0RWQk7vrKrAXtxwBYq7T9nI/JnUnK0yiZtx30+mk7/g=", zzr, i, 31);
    }

    /* access modifiers changed from: protected */
    public final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zze.zzX(-1);
        this.zze.zzT(-1);
        if (this.zzi == null) {
            this.zzi = (List) this.zzf.invoke((Object) null, new Object[]{this.zzb.zzb()});
        }
        List list = this.zzi;
        if (list != null && list.size() == 2) {
            synchronized (this.zze) {
                this.zze.zzX(((Long) this.zzi.get(0)).longValue());
                this.zze.zzT(((Long) this.zzi.get(1)).longValue());
            }
        }
    }
}
