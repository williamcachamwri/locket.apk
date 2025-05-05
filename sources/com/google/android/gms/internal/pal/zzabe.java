package com.google.android.gms.internal.pal;

import com.facebook.hermes.intl.Constants;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzabe implements Closeable, Flushable {
    private static final Pattern zza = Pattern.compile("-?(?:0|[1-9][0-9]*)(?:\\.[0-9]+)?(?:[eE][-+]?[0-9]+)?");
    private static final String[] zzb = new String[128];
    private static final String[] zzc;
    private final Writer zzd;
    private int[] zze = new int[32];
    private int zzf = 0;
    private final String zzg;
    private boolean zzh;
    private String zzi;

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

    public zzabe(Writer writer) {
        zzo(6);
        this.zzg = ":";
        this.zzd = writer;
    }

    private final int zzk() {
        int i = this.zzf;
        if (i != 0) {
            return this.zze[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private final zzabe zzl(int i, int i2, char c) throws IOException {
        int zzk = zzk();
        if (zzk == i2 || zzk == i) {
            String str = this.zzi;
            if (str == null) {
                this.zzf--;
                this.zzd.write(c);
                return this;
            }
            throw new IllegalStateException("Dangling name: ".concat(str));
        }
        throw new IllegalStateException("Nesting problem.");
    }

    private final zzabe zzm(int i, char c) throws IOException {
        zzn();
        zzo(i);
        this.zzd.write(c);
        return this;
    }

    private final void zzn() throws IOException {
        int zzk = zzk();
        if (zzk == 1) {
            zzp(2);
        } else if (zzk == 2) {
            this.zzd.append(AbstractJsonLexerKt.COMMA);
        } else if (zzk != 4) {
            if (zzk != 6) {
                if (zzk != 7) {
                    throw new IllegalStateException("Nesting problem.");
                } else if (!this.zzh) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            zzp(7);
        } else {
            this.zzd.append(this.zzg);
            zzp(5);
        }
    }

    private final void zzo(int i) {
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

    private final void zzp(int i) {
        this.zze[this.zzf - 1] = i;
    }

    private final void zzq(String str) throws IOException {
        String str2;
        String[] strArr = zzb;
        this.zzd.write(34);
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt < 128) {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
            } else if (charAt == 8232) {
                str2 = "\\u2028";
            } else if (charAt == 8233) {
                str2 = "\\u2029";
            }
            if (i < i2) {
                this.zzd.write(str, i, i2 - i);
            }
            this.zzd.write(str2);
            i = i2 + 1;
        }
        if (i < length) {
            this.zzd.write(str, i, length - i);
        }
        this.zzd.write(34);
    }

    private final void zzr() throws IOException {
        if (this.zzi != null) {
            int zzk = zzk();
            if (zzk == 5) {
                this.zzd.write(44);
            } else if (zzk != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            zzp(4);
            zzq(this.zzi);
            this.zzi = null;
        }
    }

    public final void close() throws IOException {
        this.zzd.close();
        int i = this.zzf;
        if (i > 1 || (i == 1 && this.zze[0] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.zzf = 0;
    }

    public final void flush() throws IOException {
        if (this.zzf != 0) {
            this.zzd.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final zzabe zza() throws IOException {
        zzr();
        zzm(1, AbstractJsonLexerKt.BEGIN_LIST);
        return this;
    }

    public final zzabe zzb() throws IOException {
        zzr();
        zzm(3, AbstractJsonLexerKt.BEGIN_OBJ);
        return this;
    }

    public final zzabe zzc() throws IOException {
        zzl(1, 2, AbstractJsonLexerKt.END_LIST);
        return this;
    }

    public final zzabe zzd() throws IOException {
        zzl(3, 5, AbstractJsonLexerKt.END_OBJ);
        return this;
    }

    public final zzabe zze(String str) throws IOException {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.zzi != null) {
            throw new IllegalStateException();
        } else if (this.zzf != 0) {
            this.zzi = str;
            return this;
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    public final zzabe zzf() throws IOException {
        if (this.zzi != null) {
            zzr();
        }
        zzn();
        this.zzd.write("null");
        return this;
    }

    public final zzabe zzg(Number number) throws IOException {
        zzr();
        String obj = number.toString();
        if (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN")) {
            Class<?> cls = number.getClass();
            if (!(cls == Integer.class || cls == Long.class || cls == Double.class || cls == Float.class || cls == Byte.class || cls == Short.class || cls == BigDecimal.class || cls == BigInteger.class || cls == AtomicInteger.class || cls == AtomicLong.class || zza.matcher(obj).matches())) {
                throw new IllegalArgumentException("String created by " + cls + " is not a valid JSON number: " + obj);
            }
        } else if (!this.zzh) {
            throw new IllegalArgumentException("Numeric values must be finite, but was ".concat(String.valueOf(obj)));
        }
        zzn();
        this.zzd.append(obj);
        return this;
    }

    public final zzabe zzh(String str) throws IOException {
        if (str == null) {
            zzf();
            return this;
        }
        zzr();
        zzn();
        zzq(str);
        return this;
    }

    public final zzabe zzi(boolean z) throws IOException {
        zzr();
        zzn();
        this.zzd.write(true != z ? Constants.CASEFIRST_FALSE : "true");
        return this;
    }

    public final void zzj(boolean z) {
        this.zzh = true;
    }
}
