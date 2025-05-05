package com.google.android.gms.internal.pal;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzage implements zzagd {
    protected zzage() {
    }

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        int i = (zzd() > ((zzagd) obj).zzd() ? 1 : (zzd() == ((zzagd) obj).zzd() ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zzagd) && zzd() == ((zzagd) obj).zzd();
    }

    public final int hashCode() {
        long zzd = zzd();
        return (int) (zzd ^ (zzd >>> 32));
    }

    public final String toString() {
        long zzd = zzd();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PT");
        zzagh.zza(stringBuffer, zzd);
        while (true) {
            int i = (zzd > 0 ? 1 : (zzd == 0 ? 0 : -1));
            if (stringBuffer.length() >= (i < 0 ? 7 : 6)) {
                break;
            }
            stringBuffer.insert(i < 0 ? 3 : 2, "0");
        }
        if ((zzd / 1000) * 1000 == zzd) {
            stringBuffer.setLength(stringBuffer.length() - 3);
        } else {
            stringBuffer.insert(stringBuffer.length() - 3, ".");
        }
        stringBuffer.append('S');
        return stringBuffer.toString();
    }
}
