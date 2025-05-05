package com.google.ads.interactivemedia.v3.internal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzwb extends zzvw {
    private final Object zza;

    public zzwb(Boolean bool) {
        this.zza = Objects.requireNonNull(bool);
    }

    private static boolean zzl(zzwb zzwb) {
        Object obj = zzwb.zza;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzwb zzwb = (zzwb) obj;
            if (this.zza == null) {
                return zzwb.zza == null;
            }
            if (!zzl(this) || !zzl(zzwb)) {
                Object obj2 = this.zza;
                if (obj2 instanceof Number) {
                    Object obj3 = zzwb.zza;
                    if (obj3 instanceof Number) {
                        if ((obj2 instanceof BigDecimal) && (obj3 instanceof BigDecimal)) {
                            return zzf().compareTo(zzwb.zzf()) == 0;
                        }
                        double zza2 = zza();
                        double zza3 = zzwb.zza();
                        if (zza2 == zza3) {
                            return true;
                        }
                        if (Double.isNaN(zza2) && Double.isNaN(zza3)) {
                            return true;
                        }
                    }
                }
                return obj2.equals(zzwb.zza);
            } else if ((this.zza instanceof BigInteger) || (zzwb.zza instanceof BigInteger)) {
                return zzg().equals(zzwb.zzg());
            } else {
                return zzd().longValue() == zzwb.zzd().longValue();
            }
        }
        return false;
    }

    public final int hashCode() {
        long doubleToLongBits;
        if (this.zza == null) {
            return 31;
        }
        if (zzl(this)) {
            doubleToLongBits = zzd().longValue();
        } else {
            Object obj = this.zza;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            doubleToLongBits = Double.doubleToLongBits(zzd().doubleValue());
        }
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    public final double zza() {
        return this.zza instanceof Number ? zzd().doubleValue() : Double.parseDouble(zze());
    }

    public final int zzb() {
        return this.zza instanceof Number ? zzd().intValue() : Integer.parseInt(zze());
    }

    public final long zzc() {
        return this.zza instanceof Number ? zzd().longValue() : Long.parseLong(zze());
    }

    public final Number zzd() {
        Object obj = this.zza;
        if (obj instanceof Number) {
            return (Number) obj;
        }
        if (obj instanceof String) {
            return new zzxq((String) obj);
        }
        throw new UnsupportedOperationException("Primitive is neither a number nor a string");
    }

    public final String zze() {
        Object obj = this.zza;
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Number) {
            return zzd().toString();
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).toString();
        }
        throw new AssertionError("Unexpected value type: ".concat(String.valueOf(String.valueOf(obj.getClass()))));
    }

    public final BigDecimal zzf() {
        Object obj = this.zza;
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        return zzxz.zza(zze());
    }

    public final BigInteger zzg() {
        Object obj = this.zza;
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if (zzl(this)) {
            return BigInteger.valueOf(zzd().longValue());
        }
        return zzxz.zzb(zze());
    }

    public final boolean zzh() {
        Object obj = this.zza;
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return Boolean.parseBoolean(zze());
    }

    public final boolean zzi() {
        return this.zza instanceof Boolean;
    }

    public final boolean zzj() {
        return this.zza instanceof Number;
    }

    public final boolean zzk() {
        return this.zza instanceof String;
    }

    public zzwb(Number number) {
        this.zza = Objects.requireNonNull(number);
    }

    public zzwb(String str) {
        this.zza = Objects.requireNonNull(str);
    }
}
