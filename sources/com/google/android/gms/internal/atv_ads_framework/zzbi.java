package com.google.android.gms.internal.atv_ads_framework;

import androidx.media3.common.MimeTypes;
import java.util.Arrays;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.tv:tv-ads@@1.0.0 */
public abstract class zzbi extends zzbb implements Set {
    @CheckForNull
    private transient zzbe zza;

    zzbi() {
    }

    static int zzh(int i) {
        int max = Math.max(i, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            do {
                highestOneBit += highestOneBit;
            } while (((double) highestOneBit) * 0.7d < ((double) max));
            return highestOneBit;
        } else if (max < 1073741824) {
            return 1073741824;
        } else {
            throw new IllegalArgumentException("collection too large");
        }
    }

    public static zzbi zzj() {
        return zzbr.zza;
    }

    public static zzbi zzk(Object obj, Object obj2) {
        return zzo(2, obj, obj2);
    }

    public static zzbi zzl(Object obj, Object obj2, Object obj3, Object obj4) {
        return zzo(4, "http", "https", "mailto", "ftp");
    }

    @SafeVarargs
    public static zzbi zzm(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object... objArr) {
        Object[] objArr2 = new Object[29];
        objArr2[0] = "audio/3gpp2";
        objArr2[1] = MimeTypes.AUDIO_AMR_NB;
        objArr2[2] = "audio/aac";
        objArr2[3] = MimeTypes.AUDIO_MIDI;
        objArr2[4] = "audio/mp3";
        objArr2[5] = MimeTypes.AUDIO_MP4;
        System.arraycopy(objArr, 0, objArr2, 6, 23);
        return zzo(29, objArr2);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzbi) && zzn() && ((zzbi) obj).zzn() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size() && containsAll(set)) {
                    return true;
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public int hashCode() {
        return zzbs.zza(this);
    }

    public zzbe zzd() {
        zzbe zzbe = this.zza;
        if (zzbe != null) {
            return zzbe;
        }
        zzbe zzi = zzi();
        this.zza = zzi;
        return zzi;
    }

    /* renamed from: zze */
    public abstract zzbu iterator();

    /* access modifiers changed from: package-private */
    public zzbe zzi() {
        Object[] array = toArray();
        int i = zzbe.zzd;
        return zzbe.zzi(array, array.length);
    }

    /* access modifiers changed from: package-private */
    public boolean zzn() {
        return false;
    }

    private static zzbi zzo(int i, Object... objArr) {
        if (i == 0) {
            return zzbr.zza;
        }
        if (i != 1) {
            int zzh = zzh(i);
            Object[] objArr2 = new Object[zzh];
            int i2 = zzh - 1;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                Object obj = objArr[i5];
                zzbk.zza(obj, i5);
                int hashCode = obj.hashCode();
                int zza2 = zzba.zza(hashCode);
                while (true) {
                    int i6 = zza2 & i2;
                    Object obj2 = objArr2[i6];
                    if (obj2 != null) {
                        if (obj2.equals(obj)) {
                            break;
                        }
                        zza2++;
                    } else {
                        objArr[i4] = obj;
                        objArr2[i6] = obj;
                        i3 += hashCode;
                        i4++;
                        break;
                    }
                }
            }
            Arrays.fill(objArr, i4, i, (Object) null);
            if (i4 == 1) {
                Object obj3 = objArr[0];
                obj3.getClass();
                return new zzbt(obj3);
            }
            if (zzh(i4) < zzh / 2) {
                return zzo(i4, objArr);
            }
            int length = objArr.length;
            if (i4 < (length >> 1) + (length >> 2)) {
                objArr = Arrays.copyOf(objArr, i4);
            }
            return new zzbr(objArr, i3, objArr2, i2, i4);
        }
        Object obj4 = objArr[0];
        obj4.getClass();
        return new zzbt(obj4);
    }
}
