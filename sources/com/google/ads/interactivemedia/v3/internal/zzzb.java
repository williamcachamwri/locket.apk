package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzzb extends zzace {
    private static final Writer zza = new zzza();
    private static final zzwb zzb = new zzwb("closed");
    private final List zzc = new ArrayList();
    private String zzd;
    private zzvw zze = zzvy.zza;

    public zzzb() {
        super(zza);
    }

    private final zzvw zzv() {
        return (zzvw) this.zzc.get(this.zzc.size() - 1);
    }

    private final void zzw(zzvw zzvw) {
        if (this.zzd != null) {
            if (!(zzvw instanceof zzvy) || zzs()) {
                ((zzvz) zzv()).zzb(this.zzd, zzvw);
            }
            this.zzd = null;
        } else if (this.zzc.isEmpty()) {
            this.zze = zzvw;
        } else {
            zzvw zzv = zzv();
            if (zzv instanceof zzvu) {
                ((zzvu) zzv).zza(zzvw);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public final void close() throws IOException {
        if (this.zzc.isEmpty()) {
            this.zzc.add(zzb);
            return;
        }
        throw new IOException("Incomplete document");
    }

    public final void flush() throws IOException {
    }

    public final zzvw zza() {
        if (this.zzc.isEmpty()) {
            return this.zze;
        }
        throw new IllegalStateException("Expected one JSON element but was ".concat(this.zzc.toString()));
    }

    public final zzace zzb() throws IOException {
        zzvu zzvu = new zzvu();
        zzw(zzvu);
        this.zzc.add(zzvu);
        return this;
    }

    public final zzace zzc() throws IOException {
        zzvz zzvz = new zzvz();
        zzw(zzvz);
        this.zzc.add(zzvz);
        return this;
    }

    public final zzace zzd() throws IOException {
        if (this.zzc.isEmpty() || this.zzd != null) {
            throw new IllegalStateException();
        } else if (zzv() instanceof zzvu) {
            List list = this.zzc;
            list.remove(list.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final zzace zze() throws IOException {
        if (this.zzc.isEmpty() || this.zzd != null) {
            throw new IllegalStateException();
        } else if (zzv() instanceof zzvz) {
            List list = this.zzc;
            list.remove(list.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final zzace zzf(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.zzc.isEmpty() || this.zzd != null) {
            throw new IllegalStateException("Did not expect a name");
        } else if (zzv() instanceof zzvz) {
            this.zzd = str;
            return this;
        } else {
            throw new IllegalStateException("Please begin an object before writing a name.");
        }
    }

    public final zzace zzg() throws IOException {
        zzw(zzvy.zza);
        return this;
    }

    public final zzace zzh(double d) throws IOException {
        if (zzu() || (!Double.isNaN(d) && !Double.isInfinite(d))) {
            zzw(new zzwb((Number) Double.valueOf(d)));
            return this;
        }
        throw new IllegalArgumentException("JSON forbids NaN and infinities: " + d);
    }

    public final zzace zzi(long j) throws IOException {
        zzw(new zzwb((Number) Long.valueOf(j)));
        return this;
    }

    public final zzace zzj(Boolean bool) throws IOException {
        if (bool == null) {
            zzw(zzvy.zza);
            return this;
        }
        zzw(new zzwb(bool));
        return this;
    }

    public final zzace zzk(Number number) throws IOException {
        if (number == null) {
            zzw(zzvy.zza);
            return this;
        }
        if (!zzu()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: ".concat(number.toString()));
            }
        }
        zzw(new zzwb(number));
        return this;
    }

    public final zzace zzl(String str) throws IOException {
        if (str == null) {
            zzw(zzvy.zza);
            return this;
        }
        zzw(new zzwb(str));
        return this;
    }

    public final zzace zzm(boolean z) throws IOException {
        zzw(new zzwb(Boolean.valueOf(z)));
        return this;
    }
}
