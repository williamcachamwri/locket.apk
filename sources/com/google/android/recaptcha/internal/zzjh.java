package com.google.android.recaptcha.internal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
public final class zzjh {
    public static void zza(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void zzb(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException((String) obj);
        }
    }

    public static void zzc(boolean z, String str, char c) {
        if (!z) {
            throw new IllegalArgumentException(zzjk.zza(str, Character.valueOf(c)));
        }
    }

    public static void zzd(int i, int i2, int i3) {
        String str;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                str = zzf(i, i3, "start index");
            } else if (i2 < 0 || i2 > i3) {
                str = zzf(i2, i3, "end index");
            } else {
                str = zzjk.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }

    public static void zze(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException((String) obj);
        }
    }

    private static String zzf(int i, int i2, String str) {
        if (i < 0) {
            return zzjk.zza("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        return zzjk.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
    }
}
