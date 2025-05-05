package com.google.android.gms.internal.pal;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzku {
    private final Class zza;
    private ConcurrentMap zzb = new ConcurrentHashMap();
    private zzkv zzc;
    private zzrb zzd;

    /* synthetic */ zzku(Class cls, zzkt zzkt) {
        this.zza = cls;
        this.zzd = zzrb.zza;
    }

    private final zzku zze(Object obj, zzwa zzwa, boolean z) throws GeneralSecurityException {
        zzks zzks;
        byte[] bArr;
        if (this.zzb == null) {
            throw new IllegalStateException("addPrimitive cannot be called after build");
        } else if (zzwa.zzi() == 3) {
            ConcurrentMap concurrentMap = this.zzb;
            Integer valueOf = Integer.valueOf(zzwa.zza());
            if (zzwa.zzj() == 5) {
                valueOf = null;
            }
            zzka zza2 = zzpj.zzb().zza(zzps.zzf(zzwa.zzc().zzg(), zzwa.zzc().zzf(), zzwa.zzc().zzc(), zzwa.zzj(), valueOf), zzlg.zza());
            if (zza2 instanceof zzpc) {
                zzks = new zzkz(zzwa.zzc().zzg(), zzwa.zzj(), (zzky) null);
            } else {
                zzks = zza2.zza();
            }
            zzks zzks2 = zzks;
            int zzj = zzwa.zzj() - 2;
            if (zzj != 1) {
                if (zzj != 2) {
                    if (zzj == 3) {
                        bArr = zzjv.zza;
                    } else if (zzj != 4) {
                        throw new GeneralSecurityException("unknown output prefix type");
                    }
                }
                bArr = ByteBuffer.allocate(5).put((byte) 0).putInt(zzwa.zza()).array();
            } else {
                bArr = ByteBuffer.allocate(5).put((byte) 1).putInt(zzwa.zza()).array();
            }
            zzkv zzkv = new zzkv(obj, bArr, zzwa.zzi(), zzwa.zzj(), zzwa.zza(), zza2, zzks2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzkv);
            zzkx zzkx = new zzkx(zzkv.zzd(), (zzkw) null);
            List list = (List) concurrentMap.put(zzkx, Collections.unmodifiableList(arrayList));
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(list);
                arrayList2.add(zzkv);
                concurrentMap.put(zzkx, Collections.unmodifiableList(arrayList2));
            }
            if (z) {
                if (this.zzc == null) {
                    this.zzc = zzkv;
                } else {
                    throw new IllegalStateException("you cannot set two primary primitives");
                }
            }
            return this;
        } else {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
    }

    public final zzku zza(Object obj, zzwa zzwa) throws GeneralSecurityException {
        zze(obj, zzwa, true);
        return this;
    }

    public final zzku zzb(Object obj, zzwa zzwa) throws GeneralSecurityException {
        zze(obj, zzwa, false);
        return this;
    }

    public final zzku zzc(zzrb zzrb) {
        if (this.zzb != null) {
            this.zzd = zzrb;
            return this;
        }
        throw new IllegalStateException("setAnnotations cannot be called after build");
    }

    public final zzlb zzd() throws GeneralSecurityException {
        ConcurrentMap concurrentMap = this.zzb;
        if (concurrentMap != null) {
            zzlb zzlb = new zzlb(concurrentMap, this.zzc, this.zzd, this.zza, (zzla) null);
            this.zzb = null;
            return zzlb;
        }
        throw new IllegalStateException("build cannot be called twice");
    }
}
