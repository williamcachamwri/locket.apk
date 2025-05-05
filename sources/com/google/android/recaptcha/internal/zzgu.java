package com.google.android.recaptcha.internal;

import java.lang.reflect.Proxy;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzgu implements zzgz {
    public static final zzgu zza = new zzgu();

    private zzgu() {
    }

    public final void zza(int i, zzgf zzgf, zzug... zzugArr) throws zzcg {
        int i2;
        int length = zzugArr.length;
        if (length == 4 || length == 5) {
            Object zza2 = zzgf.zzc().zza(zzugArr[0]);
            if (true != (zza2 instanceof String)) {
                zza2 = null;
            }
            String str = (String) zza2;
            if (str != null) {
                Object zza3 = zzgf.zzc().zza(zzugArr[1]);
                if (true != (zza3 instanceof Object)) {
                    zza3 = null;
                }
                if (zza3 != null) {
                    Object zza4 = zzgf.zzc().zza(zzugArr[2]);
                    if (true != (zza4 instanceof String)) {
                        zza4 = null;
                    }
                    String str2 = (String) zza4;
                    if (str2 != null) {
                        String zza5 = zzgf.zzh().zza(str2);
                        Object zza6 = zzgf.zzc().zza(zzugArr[3]);
                        if (length == 5) {
                            Object zza7 = zzgf.zzc().zza(zzugArr[4]);
                            if (true != (zza7 instanceof Integer)) {
                                zza7 = null;
                            }
                            Integer num = (Integer) zza7;
                            if (num != null) {
                                i2 = num.intValue();
                            } else {
                                throw new zzcg(4, 5, (Throwable) null);
                            }
                        } else {
                            i2 = -1;
                        }
                        try {
                            if (zza3 instanceof String) {
                                zza3 = zzgf.zzh().zza((String) zza3);
                            }
                            Class zza8 = zzge.zza(zza3);
                            zzgf.zzc().zze(i, Proxy.newProxyInstance(zza8.getClassLoader(), new Class[]{zza8}, new zzga(new zzgt(zzgf, str, i2), zza5, zza6)));
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
            throw new zzcg(4, 3, (Throwable) null);
        }
    }
}
