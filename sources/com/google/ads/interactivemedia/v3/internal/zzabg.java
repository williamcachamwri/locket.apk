package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzabg extends zzwj {
    private final Map zza = new HashMap();
    private final Map zzb = new HashMap();
    private final Map zzc = new HashMap();

    public zzabg(Class cls) {
        try {
            ArrayList arrayList = new ArrayList(r1);
            for (Field field : cls.getDeclaredFields()) {
                if (field.isEnumConstant()) {
                    arrayList.add(field);
                }
            }
            Field[] fieldArr = (Field[]) arrayList.toArray(new Field[0]);
            AccessibleObject.setAccessible(fieldArr, true);
            for (Field field2 : fieldArr) {
                Enum enumR = (Enum) field2.get((Object) null);
                String name = enumR.name();
                String str = enumR.toString();
                zzwm zzwm = (zzwm) field2.getAnnotation(zzwm.class);
                if (zzwm != null) {
                    name = zzwm.zza();
                    for (String put : zzwm.zzb()) {
                        this.zza.put(put, enumR);
                    }
                }
                this.zza.put(name, enumR);
                this.zzb.put(str, enumR);
                this.zzc.put(enumR, name);
            }
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public final /* bridge */ /* synthetic */ Object read(zzacc zzacc) throws IOException {
        if (zzacc.zzr() == 9) {
            zzacc.zzm();
            return null;
        }
        String zzh = zzacc.zzh();
        Enum enumR = (Enum) this.zza.get(zzh);
        if (enumR != null) {
            return enumR;
        }
        return (Enum) this.zzb.get(zzh);
    }

    public final /* bridge */ /* synthetic */ void write(zzace zzace, Object obj) throws IOException {
        String str;
        Enum enumR = (Enum) obj;
        if (enumR == null) {
            str = null;
        } else {
            str = (String) this.zzc.get(enumR);
        }
        zzace.zzl(str);
    }
}
