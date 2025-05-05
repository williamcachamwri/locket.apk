package com.google.ads.interactivemedia.v3.internal;

import com.facebook.hermes.intl.Constants;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public class zzace implements Closeable, Flushable {
    private static final Pattern zza = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");
    private static final String[] zzb = new String[128];
    private static final String[] zzc;
    private final Writer zzd;
    private int[] zze = new int[32];
    private int zzf = 0;
    private zzvl zzg;
    private String zzh;
    private String zzi;
    private boolean zzj;
    private zzwg zzk;
    private boolean zzl;
    private String zzm;
    private boolean zzn;

    static {
        for (int i = 0; i <= 31; i++) {
            zzb[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        String[] strArr = zzb;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        zzc = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public zzace(Writer writer) {
        zzz(6);
        this.zzk = zzwg.LEGACY_STRICT;
        this.zzn = true;
        this.zzd = (Writer) Objects.requireNonNull(writer, "out == null");
        zzo(zzvl.zza);
    }

    private final void zzA(int i) {
        this.zze[this.zzf - 1] = i;
    }

    private final void zzB(String str) throws IOException {
        String[] strArr;
        String str2;
        if (this.zzl) {
            strArr = zzc;
        } else {
            strArr = zzb;
        }
        this.zzd.write(34);
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            char charAt = str.charAt(i);
            if (charAt < 128) {
                str2 = strArr[charAt];
                if (str2 == null) {
                    i = i3;
                }
            } else if (charAt == 8232) {
                str2 = "\\u2028";
            } else if (charAt == 8233) {
                str2 = "\\u2029";
            } else {
                i = i3;
            }
            if (i2 < i) {
                this.zzd.write(str, i2, i - i2);
            }
            this.zzd.write(str2);
            i2 = i3;
            i = i3;
        }
        if (i2 < length) {
            this.zzd.write(str, i2, length - i2);
        }
        this.zzd.write(34);
    }

    private final void zzC() throws IOException {
        if (this.zzm != null) {
            int zza2 = zza();
            if (zza2 == 5) {
                this.zzd.write(this.zzi);
            } else if (zza2 != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            zzy();
            zzA(4);
            zzB(this.zzm);
            this.zzm = null;
        }
    }

    private final int zza() {
        int i = this.zzf;
        if (i != 0) {
            return this.zze[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private final zzace zzv(int i, int i2, char c) throws IOException {
        int zza2 = zza();
        if (zza2 == i2 || zza2 == i) {
            String str = this.zzm;
            if (str == null) {
                this.zzf--;
                if (zza2 == i2) {
                    zzy();
                }
                this.zzd.write(c);
                return this;
            }
            throw new IllegalStateException("Dangling name: ".concat(str));
        }
        throw new IllegalStateException("Nesting problem.");
    }

    private final zzace zzw(int i, char c) throws IOException {
        zzx();
        zzz(i);
        this.zzd.write(c);
        return this;
    }

    private final void zzx() throws IOException {
        int zza2 = zza();
        if (zza2 == 1) {
            zzA(2);
            zzy();
        } else if (zza2 == 2) {
            this.zzd.append(this.zzi);
            zzy();
        } else if (zza2 != 4) {
            if (zza2 != 6) {
                if (zza2 != 7) {
                    throw new IllegalStateException("Nesting problem.");
                } else if (this.zzk != zzwg.LENIENT) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            zzA(7);
        } else {
            this.zzd.append(this.zzh);
            zzA(5);
        }
    }

    private final void zzy() throws IOException {
        if (!this.zzj) {
            this.zzd.write(this.zzg.zzb());
            int i = this.zzf;
            for (int i2 = 1; i2 < i; i2++) {
                this.zzd.write(this.zzg.zza());
            }
        }
    }

    private final void zzz(int i) {
        int i2 = this.zzf;
        int[] iArr = this.zze;
        if (i2 == iArr.length) {
            this.zze = Arrays.copyOf(iArr, i2 + i2);
        }
        int[] iArr2 = this.zze;
        int i3 = this.zzf;
        this.zzf = i3 + 1;
        iArr2[i3] = i;
    }

    public void close() throws IOException {
        this.zzd.close();
        int i = this.zzf;
        if (i > 1 || (i == 1 && this.zze[0] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.zzf = 0;
    }

    public void flush() throws IOException {
        if (this.zzf != 0) {
            this.zzd.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public zzace zzb() throws IOException {
        zzC();
        zzw(1, AbstractJsonLexerKt.BEGIN_LIST);
        return this;
    }

    public zzace zzc() throws IOException {
        zzC();
        zzw(3, AbstractJsonLexerKt.BEGIN_OBJ);
        return this;
    }

    public zzace zzd() throws IOException {
        zzv(1, 2, AbstractJsonLexerKt.END_LIST);
        return this;
    }

    public zzace zze() throws IOException {
        zzv(3, 5, AbstractJsonLexerKt.END_OBJ);
        return this;
    }

    public zzace zzf(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.zzm == null) {
            int zza2 = zza();
            if (zza2 == 3 || zza2 == 5) {
                this.zzm = str;
                return this;
            }
            throw new IllegalStateException("Please begin an object before writing a name.");
        }
        throw new IllegalStateException("Already wrote a name, expecting a value.");
    }

    public zzace zzg() throws IOException {
        if (this.zzm != null) {
            if (this.zzn) {
                zzC();
            } else {
                this.zzm = null;
                return this;
            }
        }
        zzx();
        this.zzd.write("null");
        return this;
    }

    public zzace zzh(double d) throws IOException {
        zzC();
        if (this.zzk == zzwg.LENIENT || (!Double.isNaN(d) && !Double.isInfinite(d))) {
            zzx();
            this.zzd.append(Double.toString(d));
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
    }

    public zzace zzi(long j) throws IOException {
        zzC();
        zzx();
        this.zzd.write(Long.toString(j));
        return this;
    }

    public zzace zzj(Boolean bool) throws IOException {
        if (bool == null) {
            zzg();
            return this;
        }
        zzC();
        zzx();
        this.zzd.write(true != bool.booleanValue() ? Constants.CASEFIRST_FALSE : "true");
        return this;
    }

    public zzace zzk(Number number) throws IOException {
        if (number == null) {
            zzg();
            return this;
        }
        zzC();
        String obj = number.toString();
        Class<?> cls = number.getClass();
        if (!(cls == Integer.class || cls == Long.class || cls == Byte.class || cls == Short.class || cls == BigDecimal.class || cls == BigInteger.class || cls == AtomicInteger.class || cls == AtomicLong.class)) {
            if (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN")) {
                if (this.zzk != zzwg.LENIENT) {
                    throw new IllegalArgumentException("Numeric values must be finite, but was ".concat(String.valueOf(obj)));
                }
            } else if (!(cls == Float.class || cls == Double.class || zza.matcher(obj).matches())) {
                String valueOf = String.valueOf(cls);
                throw new IllegalArgumentException("String created by " + valueOf + " is not a valid JSON number: " + obj);
            }
        }
        zzx();
        this.zzd.append(obj);
        return this;
    }

    public zzace zzl(String str) throws IOException {
        if (str == null) {
            zzg();
            return this;
        }
        zzC();
        zzx();
        zzB(str);
        return this;
    }

    public zzace zzm(boolean z) throws IOException {
        zzC();
        zzx();
        this.zzd.write(true != z ? Constants.CASEFIRST_FALSE : "true");
        return this;
    }

    public final zzwg zzn() {
        return this.zzk;
    }

    public final void zzo(zzvl zzvl) {
        zzvl zzvl2 = (zzvl) Objects.requireNonNull(zzvl);
        this.zzg = zzvl2;
        this.zzi = ",";
        if (zzvl2.zzc()) {
            this.zzh = ": ";
            if (this.zzg.zzb().isEmpty()) {
                this.zzi = ", ";
            }
        } else {
            this.zzh = ":";
        }
        boolean z = false;
        if (this.zzg.zzb().isEmpty() && this.zzg.zza().isEmpty()) {
            z = true;
        }
        this.zzj = z;
    }

    public final void zzp(boolean z) {
        this.zzl = z;
    }

    public final void zzq(boolean z) {
        this.zzn = z;
    }

    public final void zzr(zzwg zzwg) {
        this.zzk = (zzwg) Objects.requireNonNull(zzwg);
    }

    public final boolean zzs() {
        return this.zzn;
    }

    public final boolean zzt() {
        return this.zzl;
    }

    public final boolean zzu() {
        return this.zzk == zzwg.LENIENT;
    }
}
