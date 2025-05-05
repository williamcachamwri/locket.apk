package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzagg {
    public static long zza(long j, int i) {
        long j2 = (long) i;
        long j3 = j * j2;
        if (j3 / j2 == j) {
            return j3;
        }
        StringBuilder sb = new StringBuilder(67);
        sb.append("Multiplication overflows a long: ");
        sb.append(j);
        sb.append(" * ");
        sb.append(i);
        throw new ArithmeticException(sb.toString());
    }
}
