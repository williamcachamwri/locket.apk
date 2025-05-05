package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzib;
import com.google.android.gms.internal.measurement.zzid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.1.2 */
public abstract class zzid<MessageType extends zzib<MessageType, BuilderType>, BuilderType extends zzid<MessageType, BuilderType>> implements zzlb {
    /* renamed from: zza */
    public abstract BuilderType zzb(zziw zziw, zzjg zzjg) throws IOException;

    /* renamed from: zzaf */
    public abstract BuilderType clone();

    public BuilderType zza(byte[] bArr, int i, int i2) throws zzkb {
        try {
            zziw zza = zziw.zza(bArr, 0, i2, false);
            zzb(zza, zzjg.zza);
            zza.zzc(0);
            return this;
        } catch (zzkb e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(zza("byte array"), e2);
        }
    }

    public BuilderType zza(byte[] bArr, int i, int i2, zzjg zzjg) throws zzkb {
        try {
            zziw zza = zziw.zza(bArr, 0, i2, false);
            zzb(zza, zzjg);
            zza.zzc(0);
            return this;
        } catch (zzkb e) {
            throw e;
        } catch (IOException e2) {
            throw new RuntimeException(zza("byte array"), e2);
        }
    }

    public final /* synthetic */ zzlb zza(byte[] bArr) throws zzkb {
        return zza(bArr, 0, bArr.length);
    }

    public final /* synthetic */ zzlb zza(byte[] bArr, zzjg zzjg) throws zzkb {
        return zza(bArr, 0, bArr.length, zzjg);
    }

    private final String zza(String str) {
        return "Reading " + getClass().getName() + " from a " + str + " threw an IOException (should never happen).";
    }

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzjv.zza(iterable);
        if (iterable instanceof zzkj) {
            List<?> zza = ((zzkj) iterable).zza();
            zzkj zzkj = (zzkj) list;
            int size = list.size();
            for (Object next : zza) {
                if (next == null) {
                    String str = "Element at index " + (zzkj.size() - size) + " is null.";
                    for (int size2 = zzkj.size() - 1; size2 >= size; size2--) {
                        zzkj.remove(size2);
                    }
                    throw new NullPointerException(str);
                } else if (next instanceof zzik) {
                    zzkj.zza((zzik) next);
                } else if (next instanceof byte[]) {
                    zzkj.zza(zzik.zza((byte[]) next));
                } else {
                    zzkj.add((String) next);
                }
            }
        } else if (iterable instanceof zzlo) {
            list.addAll((Collection) iterable);
        } else {
            if (iterable instanceof Collection) {
                int size3 = ((Collection) iterable).size();
                if (list instanceof ArrayList) {
                    ((ArrayList) list).ensureCapacity(list.size() + size3);
                }
                if (list instanceof zzlp) {
                    ((zzlp) list).zzb(list.size() + size3);
                }
            }
            int size4 = list.size();
            if (!(iterable instanceof List) || !(iterable instanceof RandomAccess)) {
                for (T next2 : iterable) {
                    if (next2 == null) {
                        zza((List<?>) list, size4);
                    }
                    list.add(next2);
                }
                return;
            }
            List list2 = (List) iterable;
            int size5 = list2.size();
            for (int i = 0; i < size5; i++) {
                Object obj = list2.get(i);
                if (obj == null) {
                    zza((List<?>) list, size4);
                }
                list.add(obj);
            }
        }
    }

    private static void zza(List<?> list, int i) {
        String str = "Element at index " + (list.size() - i) + " is null.";
        for (int size = list.size() - 1; size >= i; size--) {
            list.remove(size);
        }
        throw new NullPointerException(str);
    }
}
