package com.google.android.recaptcha.internal;

import java.lang.reflect.Proxy;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgv implements zzgz {
    public static final zzgv zza = new zzgv();

    private zzgv() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        int length = zzugArr.length;
        Object obj = null;
        if (length == 4 || length == 5) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof Integer)) {
                zza2 = null;
            }
            Integer num = (Integer) zza2;
            if (num != null) {
                int intValue = num.intValue();
                Object zza3 = zzgf.zzc().zza(zzugArr[1]);
                if (true != (zza3 instanceof Integer)) {
                    zza3 = null;
                }
                Integer num2 = (Integer) zza3;
                if (num2 != null) {
                    int intValue2 = num2.intValue();
                    Object zza4 = zzgf.zzc().zza(zzugArr[2]);
                    if (true != (zza4 instanceof String)) {
                        zza4 = null;
                    }
                    String str = (String) zza4;
                    if (str != null) {
                        String zza5 = zzgf.zzh().zza(str);
                        Object zza6 = zzgf.zzc().zza(zzugArr[3]);
                        if (true != (zza6 instanceof String)) {
                            zza6 = null;
                        }
                        String str2 = (String) zza6;
                        if (str2 != null) {
                            String zza7 = zzgf.zzh().zza(str2);
                            if (length == 5) {
                                obj = zzgf.zzc().zza(zzugArr[4]);
                            }
                            zzgb zzgb = new zzgb(intValue2);
                            try {
                                Class zza8 = zzge.zza(zza5);
                                zzgf.zzc().zze(intValue, Proxy.newProxyInstance(zza8.getClassLoader(), new Class[]{zza8}, new zzgc(zzgb, zza7, obj)));
                                zzgf.zzc().zze(i, zzgb);
                            } catch (Exception e) {
                                throw new zzcg(6, 20, e);
                            }
                        } else {
                            throw new zzcg(4, 5, (Throwable) null);
                        }
                    } else {
                        throw new zzcg(4, 5, (Throwable) null);
                    }
                } else {
                    throw new zzcg(4, 5, (Throwable) null);
                }
            } else {
                throw new zzcg(4, 5, (Throwable) null);
            }
        } else {
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
