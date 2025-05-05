package com.google.android.recaptcha.internal;

import com.google.android.recaptcha.internal.zzkp;
import com.google.android.recaptcha.internal.zzkq;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public abstract class zzkp<MessageType extends zzkq<MessageType, BuilderType>, BuilderType extends zzkp<MessageType, BuilderType>> implements zzoj {
    protected static void zzd(Iterable iterable, List list) {
        byte[] bArr = zznn.zzb;
        iterable.getClass();
        if (iterable instanceof zznw) {
            List zza = ((zznw) iterable).zza();
            zznw zznw = (zznw) list;
            int size = list.size();
            for (Object next : zza) {
                if (next == null) {
                    String str = "Element at index " + (zznw.size() - size) + " is null.";
                    int size2 = zznw.size();
                    while (true) {
                        size2--;
                        if (size2 >= size) {
                            zznw.remove(size2);
                        } else {
                            throw new NullPointerException(str);
                        }
                    }
                } else if (next instanceof zzlg) {
                    zzlg zzlg = (zzlg) next;
                    zznw.zzb();
                } else if (next instanceof byte[]) {
                    byte[] bArr2 = (byte[]) next;
                    zzlg.zzl(bArr2, 0, bArr2.length);
                    zznw.zzb();
                } else {
                    zznw.add((String) next);
                }
            }
        } else if (!(iterable instanceof zzot)) {
            if (iterable instanceof Collection) {
                int size3 = ((Collection) iterable).size();
                if (list instanceof ArrayList) {
                    ((ArrayList) list).ensureCapacity(list.size() + size3);
                }
                if (list instanceof zzov) {
                    ((zzov) list).zzf(list.size() + size3);
                }
            }
            int size4 = list.size();
            if (!(iterable instanceof List) || !(iterable instanceof RandomAccess)) {
                for (Object next2 : iterable) {
                    if (next2 == null) {
                        zze(list, size4);
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
                    zze(list, size4);
                }
                list.add(obj);
            }
        } else {
            list.addAll((Collection) iterable);
        }
    }

    private static void zze(List list, int i) {
        String str = "Element at index " + (list.size() - i) + " is null.";
        int size = list.size();
        while (true) {
            size--;
            if (size >= i) {
                list.remove(size);
            } else {
                throw new NullPointerException(str);
            }
        }
    }

    /* renamed from: zza */
    public abstract zzkp clone();

    /* access modifiers changed from: protected */
    public abstract zzkp zzb(zzkq zzkq);

    public final /* bridge */ /* synthetic */ zzoj zzc(zzok zzok) {
        if (zzm().getClass().isInstance(zzok)) {
            return zzb((zzkq) zzok);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
