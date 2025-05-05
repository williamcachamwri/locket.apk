package com.google.ads.interactivemedia.v3.internal;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Objects;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public class zzacc implements Closeable {
    int zza = 0;
    private final Reader zzb;
    private zzwg zzc = zzwg.LEGACY_STRICT;
    private final char[] zzd = new char[1024];
    private int zze = 0;
    private int zzf = 0;
    private int zzg = 0;
    private int zzh = 0;
    private long zzi;
    private int zzj;
    private String zzk;
    private int[] zzl;
    private int zzm;
    private String[] zzn;
    private int[] zzo;

    static {
        zzxp.zza = new zzacb();
    }

    public zzacc(Reader reader) {
        int[] iArr = new int[32];
        this.zzl = iArr;
        this.zzm = 1;
        iArr[0] = 6;
        this.zzn = new String[32];
        this.zzo = new int[32];
        this.zzb = (Reader) Objects.requireNonNull(reader, "in == null");
    }

    private String zzA(boolean z) {
        StringBuilder sb = new StringBuilder("$");
        int i = 0;
        while (true) {
            int i2 = this.zzm;
            if (i >= i2) {
                return sb.toString();
            }
            int i3 = this.zzl[i];
            switch (i3) {
                case 1:
                case 2:
                    int i4 = this.zzo[i];
                    if (z && i4 > 0 && i == i2 - 1) {
                        i4--;
                    }
                    sb.append(AbstractJsonLexerKt.BEGIN_LIST);
                    sb.append(i4);
                    sb.append(AbstractJsonLexerKt.END_LIST);
                    break;
                case 3:
                case 4:
                case 5:
                    sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                    String str = this.zzn[i];
                    if (str == null) {
                        break;
                    } else {
                        sb.append(str);
                        break;
                    }
                case 6:
                case 7:
                case 8:
                    break;
                default:
                    throw new AssertionError("Unknown scope value: " + i3);
            }
            i++;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0072, code lost:
        r3 = r1 - r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0074, code lost:
        if (r0 != null) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0076, code lost:
        r0 = new java.lang.StringBuilder(java.lang.Math.max(r3 + r3, 16));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0082, code lost:
        r0.append(r4, r2, r3);
        r10.zze = r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String zzB(char r11) throws java.io.IOException {
        /*
            r10 = this;
            r0 = 0
        L_0x0001:
            int r1 = r10.zze
            int r2 = r10.zzf
            r3 = r2
            r2 = r1
        L_0x0007:
            char[] r4 = r10.zzd
            r5 = 16
            r6 = 1
            if (r1 >= r3) goto L_0x0072
            int r7 = r1 + 1
            char r1 = r4[r1]
            com.google.ads.interactivemedia.v3.internal.zzwg r8 = r10.zzc
            com.google.ads.interactivemedia.v3.internal.zzwg r9 = com.google.ads.interactivemedia.v3.internal.zzwg.STRICT
            if (r8 != r9) goto L_0x0024
            r8 = 32
            if (r1 < r8) goto L_0x001d
            goto L_0x0024
        L_0x001d:
            java.lang.String r11 = "Unescaped control characters (\\u0000-\\u001F) are not allowed in strict mode"
            com.google.ads.interactivemedia.v3.internal.zzacf r11 = r10.zzy(r11)
            throw r11
        L_0x0024:
            if (r1 != r11) goto L_0x003c
            int r11 = r7 - r2
            int r11 = r11 + -1
            r10.zze = r7
            if (r0 != 0) goto L_0x0034
            java.lang.String r0 = new java.lang.String
            r0.<init>(r4, r2, r11)
            return r0
        L_0x0034:
            r0.append(r4, r2, r11)
            java.lang.String r11 = r0.toString()
            return r11
        L_0x003c:
            r8 = 92
            if (r1 != r8) goto L_0x0065
            int r1 = r7 - r2
            int r1 = r1 + -1
            r10.zze = r7
            if (r0 != 0) goto L_0x0055
            int r0 = r1 + 1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            int r0 = r0 + r0
            int r0 = java.lang.Math.max(r0, r5)
            r3.<init>(r0)
            r0 = r3
        L_0x0055:
            r0.append(r4, r2, r1)
            char r1 = r10.zzd()
            r0.append(r1)
            int r2 = r10.zze
            int r3 = r10.zzf
            r1 = r2
            goto L_0x0007
        L_0x0065:
            r4 = 10
            if (r1 != r4) goto L_0x0070
            int r1 = r10.zzg
            int r1 = r1 + r6
            r10.zzg = r1
            r10.zzh = r7
        L_0x0070:
            r1 = r7
            goto L_0x0007
        L_0x0072:
            int r3 = r1 - r2
            if (r0 != 0) goto L_0x0082
            int r0 = r3 + r3
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            int r0 = java.lang.Math.max(r0, r5)
            r7.<init>(r0)
            r0 = r7
        L_0x0082:
            r0.append(r4, r2, r3)
            r10.zze = r1
            boolean r1 = r10.zzI(r6)
            if (r1 == 0) goto L_0x008f
            goto L_0x0001
        L_0x008f:
            java.lang.String r11 = "Unterminated string"
            com.google.ads.interactivemedia.v3.internal.zzacf r11 = r10.zzy(r11)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzacc.zzB(char):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0048, code lost:
        zzD();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String zzC() throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            r2 = r0
        L_0x0003:
            int r3 = r5.zze
            int r3 = r3 + r2
            int r4 = r5.zzf
            if (r3 >= r4) goto L_0x004c
            char[] r4 = r5.zzd
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L_0x0059
            r4 = 10
            if (r3 == r4) goto L_0x0059
            r4 = 12
            if (r3 == r4) goto L_0x0059
            r4 = 13
            if (r3 == r4) goto L_0x0059
            r4 = 32
            if (r3 == r4) goto L_0x0059
            r4 = 35
            if (r3 == r4) goto L_0x0048
            r4 = 44
            if (r3 == r4) goto L_0x0059
            r4 = 47
            if (r3 == r4) goto L_0x0048
            r4 = 61
            if (r3 == r4) goto L_0x0048
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L_0x0059
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L_0x0059
            r4 = 58
            if (r3 == r4) goto L_0x0059
            r4 = 59
            if (r3 == r4) goto L_0x0048
            switch(r3) {
                case 91: goto L_0x0059;
                case 92: goto L_0x0048;
                case 93: goto L_0x0059;
                default: goto L_0x0045;
            }
        L_0x0045:
            int r2 = r2 + 1
            goto L_0x0003
        L_0x0048:
            r5.zzD()
            goto L_0x0059
        L_0x004c:
            r3 = 1024(0x400, float:1.435E-42)
            if (r2 >= r3) goto L_0x005b
            int r3 = r2 + 1
            boolean r3 = r5.zzI(r3)
            if (r3 == 0) goto L_0x0059
            goto L_0x0003
        L_0x0059:
            r0 = r2
            goto L_0x007b
        L_0x005b:
            if (r1 != 0) goto L_0x0068
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r1.<init>(r3)
        L_0x0068:
            char[] r3 = r5.zzd
            int r4 = r5.zze
            r1.append(r3, r4, r2)
            int r3 = r5.zze
            int r3 = r3 + r2
            r5.zze = r3
            r2 = 1
            boolean r2 = r5.zzI(r2)
            if (r2 != 0) goto L_0x0002
        L_0x007b:
            if (r1 != 0) goto L_0x0087
            char[] r1 = r5.zzd
            java.lang.String r2 = new java.lang.String
            int r3 = r5.zze
            r2.<init>(r1, r3, r0)
            goto L_0x0092
        L_0x0087:
            char[] r2 = r5.zzd
            int r3 = r5.zze
            r1.append(r2, r3, r0)
            java.lang.String r2 = r1.toString()
        L_0x0092:
            int r1 = r5.zze
            int r1 = r1 + r0
            r5.zze = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzacc.zzC():java.lang.String");
    }

    private final void zzD() throws zzacf {
        if (this.zzc != zzwg.LENIENT) {
            throw zzy("Use JsonReader.setStrictness(Strictness.LENIENT) to accept malformed JSON");
        }
    }

    private final void zzE(int i) throws zzacf {
        int i2 = this.zzm;
        if (i2 - 1 < 1280) {
            int[] iArr = this.zzl;
            if (i2 == iArr.length) {
                int i3 = i2 + i2;
                this.zzl = Arrays.copyOf(iArr, i3);
                this.zzo = Arrays.copyOf(this.zzo, i3);
                this.zzn = (String[]) Arrays.copyOf(this.zzn, i3);
            }
            int[] iArr2 = this.zzl;
            int i4 = this.zzm;
            this.zzm = i4 + 1;
            iArr2[i4] = i;
            return;
        }
        String zzv = zzv();
        throw new zzacf("Nesting limit 1280 reached" + zzv);
    }

    private final void zzF(char c) throws IOException {
        do {
            int i = this.zze;
            int i2 = this.zzf;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = this.zzd[i];
                if (c2 == c) {
                    this.zze = i3;
                    return;
                } else if (c2 == '\\') {
                    this.zze = i3;
                    zzd();
                    i = this.zze;
                    i2 = this.zzf;
                } else {
                    if (c2 == 10) {
                        this.zzg++;
                        this.zzh = i3;
                    }
                    i = i3;
                }
            }
            this.zze = i;
        } while (zzI(1));
        throw zzy("Unterminated string");
    }

    private final void zzG() throws IOException {
        char c;
        do {
            if (this.zze < this.zzf || zzI(1)) {
                char[] cArr = this.zzd;
                int i = this.zze;
                int i2 = i + 1;
                this.zze = i2;
                c = cArr[i];
                if (c == 10) {
                    this.zzg++;
                    this.zzh = i2;
                    return;
                }
            } else {
                return;
            }
        } while (c != 13);
    }

    private final void zzH() throws IOException {
        do {
            int i = 0;
            while (true) {
                int i2 = this.zze + i;
                if (i2 < this.zzf) {
                    char c = this.zzd[i2];
                    if (!(c == 9 || c == 10 || c == 12 || c == 13 || c == ' ')) {
                        if (c != '#') {
                            if (c != ',') {
                                if (!(c == '/' || c == '=')) {
                                    if (!(c == '{' || c == '}' || c == ':')) {
                                        if (c != ';') {
                                            switch (c) {
                                                case '[':
                                                case ']':
                                                    break;
                                                case '\\':
                                                    break;
                                                default:
                                                    i++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    this.zze = i2;
                }
            }
            zzD();
            this.zze += i;
            return;
        } while (zzI(1));
    }

    private final boolean zzI(int i) throws IOException {
        int i2;
        int i3 = this.zzh;
        int i4 = this.zze;
        this.zzh = i3 - i4;
        char[] cArr = this.zzd;
        int i5 = this.zzf;
        if (i5 != i4) {
            int i6 = i5 - i4;
            this.zzf = i6;
            System.arraycopy(cArr, i4, cArr, 0, i6);
        } else {
            this.zzf = 0;
        }
        this.zze = 0;
        do {
            Reader reader = this.zzb;
            int i7 = this.zzf;
            int read = reader.read(cArr, i7, 1024 - i7);
            if (read == -1) {
                return false;
            }
            i2 = this.zzf + read;
            this.zzf = i2;
            if (this.zzg == 0 && this.zzh == 0 && i2 > 0 && cArr[0] == 65279) {
                this.zze++;
                this.zzh = 1;
                i++;
                continue;
            }
        } while (i2 < i);
        return true;
    }

    private final boolean zzJ(char c) throws IOException {
        if (c == 9 || c == 10 || c == 12 || c == 13 || c == ' ') {
            return false;
        }
        if (c != '#') {
            if (c == ',') {
                return false;
            }
            if (!(c == '/' || c == '=')) {
                if (c == '{' || c == '}' || c == ':') {
                    return false;
                }
                if (c != ';') {
                    switch (c) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        zzD();
        return false;
    }

    private final char zzd() throws IOException {
        int i;
        if (this.zze != this.zzf || zzI(1)) {
            char[] cArr = this.zzd;
            int i2 = this.zze;
            int i3 = i2 + 1;
            this.zze = i3;
            char c = cArr[i2];
            if (c != 10) {
                if (c != '\"') {
                    if (c != '\'') {
                        if (!(c == '/' || c == '\\')) {
                            if (c == 'b') {
                                return 8;
                            }
                            if (c == 'f') {
                                return 12;
                            }
                            if (c == 'n') {
                                return 10;
                            }
                            if (c == 'r') {
                                return 13;
                            }
                            if (c == 't') {
                                return 9;
                            }
                            if (c != 'u') {
                                throw zzy("Invalid escape sequence");
                            } else if (i3 + 4 <= this.zzf || zzI(4)) {
                                int i4 = this.zze;
                                int i5 = i4 + 4;
                                int i6 = 0;
                                while (i4 < i5) {
                                    char[] cArr2 = this.zzd;
                                    int i7 = i6 << 4;
                                    char c2 = cArr2[i4];
                                    if (c2 >= '0' && c2 <= '9') {
                                        i = c2 - '0';
                                    } else if (c2 >= 'a' && c2 <= 'f') {
                                        i = c2 - 'W';
                                    } else if (c2 < 'A' || c2 > 'F') {
                                        throw zzy("Malformed Unicode escape \\u".concat(new String(cArr2, this.zze, 4)));
                                    } else {
                                        i = c2 - '7';
                                    }
                                    i6 = i7 + i;
                                    i4++;
                                }
                                this.zze += 4;
                                return (char) i6;
                            } else {
                                throw zzy("Unterminated escape sequence");
                            }
                        }
                    }
                }
                return c;
            } else if (this.zzc != zzwg.STRICT) {
                this.zzg++;
                this.zzh = i3;
            } else {
                throw zzy("Cannot escape a newline character in strict mode");
            }
            if (this.zzc == zzwg.STRICT) {
                throw zzy("Invalid escaped character \"'\" in strict mode");
            }
            return c;
        }
        throw zzy("Unterminated escape sequence");
    }

    private final int zzn(boolean z) throws IOException {
        int i = this.zze;
        int i2 = this.zzf;
        while (true) {
            if (i == i2) {
                this.zze = i;
                if (zzI(1)) {
                    i = this.zze;
                    i2 = this.zzf;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input".concat(zzv()));
                }
            }
            char[] cArr = this.zzd;
            int i3 = i + 1;
            char c = cArr[i];
            if (c == 10) {
                this.zzg++;
                this.zzh = i3;
            } else if (!(c == ' ' || c == 13 || c == 9)) {
                if (c == '/') {
                    this.zze = i3;
                    if (i3 == i2) {
                        this.zze = i3 - 1;
                        boolean zzI = zzI(2);
                        this.zze++;
                        if (!zzI) {
                            return 47;
                        }
                    }
                    zzD();
                    int i4 = this.zze;
                    char c2 = cArr[i4];
                    if (c2 == '*') {
                        this.zze = i4 + 1;
                        while (true) {
                            if (this.zze + 2 <= this.zzf || zzI(2)) {
                                char[] cArr2 = this.zzd;
                                int i5 = this.zze;
                                if (cArr2[i5] != 10) {
                                    int i6 = 0;
                                    while (i6 < 2) {
                                        if (this.zzd[this.zze + i6] == "*/".charAt(i6)) {
                                            i6++;
                                        }
                                    }
                                    i = 2 + this.zze;
                                    i2 = this.zzf;
                                    break;
                                }
                                this.zzg++;
                                this.zzh = i5 + 1;
                                this.zze++;
                            } else {
                                throw zzy("Unterminated comment");
                            }
                        }
                    } else if (c2 != '/') {
                        return 47;
                    } else {
                        this.zze = i4 + 1;
                        zzG();
                        i = this.zze;
                        i2 = this.zzf;
                    }
                } else if (c == '#') {
                    this.zze = i3;
                    zzD();
                    zzG();
                    i = this.zze;
                    i2 = this.zzf;
                } else {
                    this.zze = i3;
                    return c;
                }
            }
            i = i3;
        }
    }

    private final zzacf zzy(String str) throws zzacf {
        String zzv = zzv();
        throw new zzacf(str + zzv + "\nSee https://github.com/google/gson/blob/main/Troubleshooting.md#malformed-json");
    }

    /* access modifiers changed from: private */
    public final IllegalStateException zzz(String str) throws IOException {
        int zzr = zzr();
        String zza2 = zzacd.zza(zzr());
        String zzv = zzv();
        StringBuilder sb = new StringBuilder("Expected ");
        sb.append(str);
        sb.append(" but was ");
        sb.append(zza2);
        sb.append(zzv);
        sb.append("\nSee ");
        sb.append("https://github.com/google/gson/blob/main/Troubleshooting.md#".concat(zzr == 9 ? "adapter-not-null-safe" : "unexpected-json-structure"));
        return new IllegalStateException(sb.toString());
    }

    public void close() throws IOException {
        this.zza = 0;
        this.zzl[0] = 8;
        this.zzm = 1;
        this.zzb.close();
    }

    public String toString() {
        return String.valueOf(getClass().getSimpleName()).concat(zzv());
    }

    public double zza() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 15) {
            this.zza = 0;
            int[] iArr = this.zzo;
            int i2 = this.zzm - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.zzi;
        }
        if (i == 16) {
            char[] cArr = this.zzd;
            int i3 = this.zze;
            int i4 = this.zzj;
            this.zzk = new String(cArr, i3, i4);
            this.zze = i3 + i4;
        } else if (i == 8 || i == 9) {
            this.zzk = zzB(i == 8 ? '\'' : '\"');
        } else if (i == 10) {
            this.zzk = zzC();
        } else if (i != 11) {
            throw zzz("a double");
        }
        this.zza = 11;
        double parseDouble = Double.parseDouble(this.zzk);
        if (this.zzc == zzwg.LENIENT || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.zzk = null;
            this.zza = 0;
            int[] iArr2 = this.zzo;
            int i5 = this.zzm - 1;
            iArr2[i5] = iArr2[i5] + 1;
            return parseDouble;
        }
        throw zzy("JSON forbids NaN and infinities: " + parseDouble);
    }

    public int zzb() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 15) {
            long j = this.zzi;
            int i2 = (int) j;
            if (j == ((long) i2)) {
                this.zza = 0;
                int[] iArr = this.zzo;
                int i3 = this.zzm - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + j + zzv());
        }
        if (i == 16) {
            char[] cArr = this.zzd;
            int i4 = this.zze;
            int i5 = this.zzj;
            this.zzk = new String(cArr, i4, i5);
            this.zze = i4 + i5;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                this.zzk = zzC();
            } else {
                this.zzk = zzB(i == 8 ? '\'' : '\"');
            }
            try {
                int parseInt = Integer.parseInt(this.zzk);
                this.zza = 0;
                int[] iArr2 = this.zzo;
                int i6 = this.zzm - 1;
                iArr2[i6] = iArr2[i6] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw zzz("an int");
        }
        this.zza = 11;
        double parseDouble = Double.parseDouble(this.zzk);
        int i7 = (int) parseDouble;
        if (((double) i7) == parseDouble) {
            this.zzk = null;
            this.zza = 0;
            int[] iArr3 = this.zzo;
            int i8 = this.zzm - 1;
            iArr3[i8] = iArr3[i8] + 1;
            return i7;
        }
        throw new NumberFormatException("Expected an int but was " + this.zzk + zzv());
    }

    public long zzc() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 15) {
            this.zza = 0;
            int[] iArr = this.zzo;
            int i2 = this.zzm - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.zzi;
        }
        if (i == 16) {
            char[] cArr = this.zzd;
            int i3 = this.zze;
            int i4 = this.zzj;
            this.zzk = new String(cArr, i3, i4);
            this.zze = i3 + i4;
        } else if (i == 8 || i == 9 || i == 10) {
            if (i == 10) {
                this.zzk = zzC();
            } else {
                this.zzk = zzB(i == 8 ? '\'' : '\"');
            }
            try {
                long parseLong = Long.parseLong(this.zzk);
                this.zza = 0;
                int[] iArr2 = this.zzo;
                int i5 = this.zzm - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw zzz("a long");
        }
        this.zza = 11;
        double parseDouble = Double.parseDouble(this.zzk);
        long j = (long) parseDouble;
        if (((double) j) == parseDouble) {
            this.zzk = null;
            this.zza = 0;
            int[] iArr3 = this.zzo;
            int i6 = this.zzm - 1;
            iArr3[i6] = iArr3[i6] + 1;
            return j;
        }
        throw new NumberFormatException("Expected a long but was " + this.zzk + zzv());
    }

    public String zze() {
        return zzA(false);
    }

    public String zzf() {
        return zzA(true);
    }

    public String zzg() throws IOException {
        String str;
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 14) {
            str = zzC();
        } else if (i == 12) {
            str = zzB('\'');
        } else if (i == 13) {
            str = zzB('\"');
        } else {
            throw zzz("a name");
        }
        this.zza = 0;
        this.zzn[this.zzm - 1] = str;
        return str;
    }

    public String zzh() throws IOException {
        String str;
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 10) {
            str = zzC();
        } else if (i == 8) {
            str = zzB('\'');
        } else if (i == 9) {
            str = zzB('\"');
        } else if (i == 11) {
            str = this.zzk;
            this.zzk = null;
        } else if (i == 15) {
            str = Long.toString(this.zzi);
        } else if (i == 16) {
            String str2 = new String(this.zzd, this.zze, this.zzj);
            this.zze += this.zzj;
            str = str2;
        } else {
            throw zzz("a string");
        }
        this.zza = 0;
        int[] iArr = this.zzo;
        int i2 = this.zzm - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public void zzi() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 3) {
            zzE(1);
            this.zzo[this.zzm - 1] = 0;
            this.zza = 0;
            return;
        }
        throw zzz("BEGIN_ARRAY");
    }

    public void zzj() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 1) {
            zzE(3);
            this.zza = 0;
            return;
        }
        throw zzz("BEGIN_OBJECT");
    }

    public void zzk() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 4) {
            int i2 = this.zzm - 1;
            this.zzm = i2;
            int[] iArr = this.zzo;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.zza = 0;
            return;
        }
        throw zzz("END_ARRAY");
    }

    public void zzl() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 2) {
            int i2 = this.zzm - 1;
            this.zzm = i2;
            this.zzn[i2] = null;
            int[] iArr = this.zzo;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.zza = 0;
            return;
        }
        throw zzz("END_OBJECT");
    }

    public void zzm() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 7) {
            this.zza = 0;
            int[] iArr = this.zzo;
            int i2 = this.zzm - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw zzz("null");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0048, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005c, code lost:
        r1 = r1 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007a, code lost:
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007c, code lost:
        r7.zza = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzo() throws java.io.IOException {
        /*
            r7 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            int r2 = r7.zza
            if (r2 != 0) goto L_0x000a
            int r2 = r7.zzs()
        L_0x000a:
            r3 = 34
            r4 = 39
            java.lang.String r5 = "<skipped>"
            r6 = 1
            switch(r2) {
                case 1: goto L_0x0076;
                case 2: goto L_0x0063;
                case 3: goto L_0x005f;
                case 4: goto L_0x0056;
                case 5: goto L_0x0014;
                case 6: goto L_0x0014;
                case 7: goto L_0x0014;
                case 8: goto L_0x0052;
                case 9: goto L_0x004e;
                case 10: goto L_0x004a;
                case 11: goto L_0x0014;
                case 12: goto L_0x003b;
                case 13: goto L_0x002d;
                case 14: goto L_0x001f;
                case 15: goto L_0x0014;
                case 16: goto L_0x0017;
                case 17: goto L_0x0016;
                default: goto L_0x0014;
            }
        L_0x0014:
            goto L_0x007c
        L_0x0016:
            return
        L_0x0017:
            int r2 = r7.zze
            int r3 = r7.zzj
            int r2 = r2 + r3
            r7.zze = r2
            goto L_0x007c
        L_0x001f:
            r7.zzH()
            if (r1 != 0) goto L_0x007c
            java.lang.String[] r1 = r7.zzn
            int r2 = r7.zzm
            int r2 = r2 + -1
            r1[r2] = r5
            goto L_0x0048
        L_0x002d:
            r7.zzF(r3)
            if (r1 != 0) goto L_0x007c
            java.lang.String[] r1 = r7.zzn
            int r2 = r7.zzm
            int r2 = r2 + -1
            r1[r2] = r5
            goto L_0x0048
        L_0x003b:
            r7.zzF(r4)
            if (r1 != 0) goto L_0x007c
            java.lang.String[] r1 = r7.zzn
            int r2 = r7.zzm
            int r2 = r2 + -1
            r1[r2] = r5
        L_0x0048:
            r1 = r0
            goto L_0x007c
        L_0x004a:
            r7.zzH()
            goto L_0x007c
        L_0x004e:
            r7.zzF(r3)
            goto L_0x007c
        L_0x0052:
            r7.zzF(r4)
            goto L_0x007c
        L_0x0056:
            int r2 = r7.zzm
            int r2 = r2 + -1
            r7.zzm = r2
        L_0x005c:
            int r1 = r1 + -1
            goto L_0x007c
        L_0x005f:
            r7.zzE(r6)
            goto L_0x007a
        L_0x0063:
            if (r1 != 0) goto L_0x006f
            java.lang.String[] r1 = r7.zzn
            int r2 = r7.zzm
            int r2 = r2 + -1
            r3 = 0
            r1[r2] = r3
            r1 = r0
        L_0x006f:
            int r2 = r7.zzm
            int r2 = r2 + -1
            r7.zzm = r2
            goto L_0x005c
        L_0x0076:
            r2 = 3
            r7.zzE(r2)
        L_0x007a:
            int r1 = r1 + 1
        L_0x007c:
            r7.zza = r0
            if (r1 > 0) goto L_0x0002
            int[] r0 = r7.zzo
            int r1 = r7.zzm
            int r1 = r1 + -1
            r2 = r0[r1]
            int r2 = r2 + r6
            r0[r1] = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzacc.zzo():void");
    }

    public boolean zzp() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        return (i == 2 || i == 4 || i == 17) ? false : true;
    }

    public boolean zzq() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        if (i == 5) {
            this.zza = 0;
            int[] iArr = this.zzo;
            int i2 = this.zzm - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.zza = 0;
            int[] iArr2 = this.zzo;
            int i3 = this.zzm - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            throw zzz("a boolean");
        }
    }

    public int zzr() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zzs();
        }
        switch (i) {
            case 1:
                return 3;
            case 2:
                return 4;
            case 3:
                return 1;
            case 4:
                return 2;
            case 5:
            case 6:
                return 8;
            case 7:
                return 9;
            case 8:
            case 9:
            case 10:
            case 11:
                return 6;
            case 12:
            case 13:
            case 14:
                return 5;
            case 15:
            case 16:
                return 7;
            default:
                return 10;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x01a7, code lost:
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0218, code lost:
        if (zzJ(r1) == false) goto L_0x01a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x021b, code lost:
        if (r9 != 2) goto L_0x0247;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x021d, code lost:
        if (r15 == false) goto L_0x0245;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x0223, code lost:
        if (r11 != Long.MIN_VALUE) goto L_0x0228;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0225, code lost:
        if (r16 == 0) goto L_0x0245;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0228, code lost:
        r14 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x022e, code lost:
        if (r11 != 0) goto L_0x0233;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0230, code lost:
        if (r14 != 0) goto L_0x0245;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0233, code lost:
        if (r14 == 0) goto L_0x0236;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0236, code lost:
        r11 = -r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0237, code lost:
        r0.zzi = r11;
        r0.zze += r10;
        r0.zza = 15;
        r14 = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0245, code lost:
        r1 = 2;
        r9 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0247, code lost:
        if (r9 == r1) goto L_0x024f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x024a, code lost:
        if (r9 == 4) goto L_0x024f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x024d, code lost:
        if (r9 != 7) goto L_0x019c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x024f, code lost:
        r0.zzj = r10;
        r14 = 16;
        r0.zza = 16;
     */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x02a2 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x02cc  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzs() throws java.io.IOException {
        /*
            r21 = this;
            r0 = r21
            int[] r1 = r0.zzl
            int r2 = r0.zzm
            r3 = -1
            int r2 = r2 + r3
            r4 = r1[r2]
            r6 = 10
            r8 = 39
            r9 = 93
            r10 = 59
            r11 = 44
            r12 = 6
            r13 = 3
            r15 = 4
            r5 = 5
            r7 = 2
            r14 = 1
            if (r4 != r14) goto L_0x0021
            r1[r2] = r7
        L_0x001e:
            r1 = 0
            goto L_0x00e0
        L_0x0021:
            if (r4 != r7) goto L_0x003b
            int r1 = r0.zzn(r14)
            if (r1 == r11) goto L_0x001e
            if (r1 == r10) goto L_0x0037
            if (r1 != r9) goto L_0x0030
            r13 = r15
            goto L_0x032a
        L_0x0030:
            java.lang.String r1 = "Unterminated array"
            com.google.ads.interactivemedia.v3.internal.zzacf r1 = r0.zzy(r1)
            throw r1
        L_0x0037:
            r21.zzD()
            goto L_0x001e
        L_0x003b:
            r7 = 125(0x7d, float:1.75E-43)
            if (r4 == r13) goto L_0x02d7
            if (r4 != r5) goto L_0x0045
            r9 = r15
            r6 = 2
            goto L_0x02d9
        L_0x0045:
            if (r4 != r15) goto L_0x0079
            r1[r2] = r5
            int r1 = r0.zzn(r14)
            r2 = 58
            if (r1 == r2) goto L_0x001e
            r2 = 61
            if (r1 != r2) goto L_0x0072
            r21.zzD()
            int r1 = r0.zze
            int r2 = r0.zzf
            if (r1 < r2) goto L_0x0064
            boolean r1 = r0.zzI(r14)
            if (r1 == 0) goto L_0x001e
        L_0x0064:
            char[] r1 = r0.zzd
            int r2 = r0.zze
            char r1 = r1[r2]
            r7 = 62
            if (r1 != r7) goto L_0x001e
            int r2 = r2 + r14
            r0.zze = r2
            goto L_0x001e
        L_0x0072:
            java.lang.String r1 = "Expected ':'"
            com.google.ads.interactivemedia.v3.internal.zzacf r1 = r0.zzy(r1)
            throw r1
        L_0x0079:
            if (r4 != r12) goto L_0x00c4
            com.google.ads.interactivemedia.v3.internal.zzwg r1 = r0.zzc
            com.google.ads.interactivemedia.v3.internal.zzwg r2 = com.google.ads.interactivemedia.v3.internal.zzwg.LENIENT
            if (r1 != r2) goto L_0x00ba
            r0.zzn(r14)
            int r1 = r0.zze
            int r1 = r1 + r3
            r0.zze = r1
            int r1 = r1 + r5
            int r2 = r0.zzf
            if (r1 <= r2) goto L_0x0095
            boolean r1 = r0.zzI(r5)
            if (r1 != 0) goto L_0x0095
            goto L_0x00ba
        L_0x0095:
            int r1 = r0.zze
            char[] r2 = r0.zzd
            char r15 = r2[r1]
            r12 = 41
            if (r15 != r12) goto L_0x00ba
            int r12 = r1 + 1
            char r12 = r2[r12]
            if (r12 != r9) goto L_0x00ba
            int r12 = r1 + 2
            char r12 = r2[r12]
            if (r12 != r7) goto L_0x00ba
            int r7 = r1 + 3
            char r7 = r2[r7]
            if (r7 != r8) goto L_0x00ba
            int r7 = r1 + 4
            char r2 = r2[r7]
            if (r2 != r6) goto L_0x00ba
            int r1 = r1 + r5
            r0.zze = r1
        L_0x00ba:
            int[] r1 = r0.zzl
            int r2 = r0.zzm
            int r2 = r2 + r3
            r7 = 7
            r1[r2] = r7
            goto L_0x001e
        L_0x00c4:
            r7 = 7
            if (r4 != r7) goto L_0x00db
            r1 = 0
            int r2 = r0.zzn(r1)
            if (r2 != r3) goto L_0x00d2
            r13 = 17
            goto L_0x032a
        L_0x00d2:
            r21.zzD()
            int r2 = r0.zze
            int r2 = r2 + r3
            r0.zze = r2
            goto L_0x00e0
        L_0x00db:
            r1 = 0
            r2 = 8
            if (r4 == r2) goto L_0x02cf
        L_0x00e0:
            int r2 = r0.zzn(r14)
            r7 = 34
            if (r2 == r7) goto L_0x02cc
            if (r2 == r8) goto L_0x02c4
            if (r2 == r11) goto L_0x02ab
            if (r2 == r10) goto L_0x02ab
            r7 = 91
            if (r2 == r7) goto L_0x032a
            if (r2 == r9) goto L_0x02a6
            r4 = 123(0x7b, float:1.72E-43)
            if (r2 == r4) goto L_0x02a3
            int r2 = r0.zze
            int r2 = r2 + r3
            r0.zze = r2
            char[] r3 = r0.zzd
            char r2 = r3[r2]
            r3 = 116(0x74, float:1.63E-43)
            if (r2 == r3) goto L_0x0127
            r3 = 84
            if (r2 != r3) goto L_0x010a
            goto L_0x0127
        L_0x010a:
            r3 = 102(0x66, float:1.43E-43)
            if (r2 == r3) goto L_0x0121
            r3 = 70
            if (r2 != r3) goto L_0x0113
            goto L_0x0121
        L_0x0113:
            r3 = 110(0x6e, float:1.54E-43)
            if (r2 == r3) goto L_0x011b
            r3 = 78
            if (r2 != r3) goto L_0x017b
        L_0x011b:
            java.lang.String r2 = "NULL"
            java.lang.String r3 = "null"
            r4 = 7
            goto L_0x012c
        L_0x0121:
            java.lang.String r2 = "FALSE"
            java.lang.String r3 = "false"
            r4 = 6
            goto L_0x012c
        L_0x0127:
            java.lang.String r2 = "TRUE"
            java.lang.String r3 = "true"
            r4 = r5
        L_0x012c:
            com.google.ads.interactivemedia.v3.internal.zzwg r7 = r0.zzc
            com.google.ads.interactivemedia.v3.internal.zzwg r8 = com.google.ads.interactivemedia.v3.internal.zzwg.STRICT
            r9 = r1
        L_0x0131:
            int r10 = r3.length()
            if (r9 >= r10) goto L_0x015f
            int r10 = r0.zze
            int r10 = r10 + r9
            int r11 = r0.zzf
            if (r10 < r11) goto L_0x0147
            int r10 = r9 + 1
            boolean r10 = r0.zzI(r10)
            if (r10 != 0) goto L_0x0147
            goto L_0x017b
        L_0x0147:
            char[] r10 = r0.zzd
            int r11 = r0.zze
            int r11 = r11 + r9
            char r10 = r10[r11]
            char r11 = r3.charAt(r9)
            if (r10 == r11) goto L_0x015c
            if (r7 == r8) goto L_0x017b
            char r11 = r2.charAt(r9)
            if (r10 != r11) goto L_0x017b
        L_0x015c:
            int r9 = r9 + 1
            goto L_0x0131
        L_0x015f:
            int r2 = r0.zze
            int r2 = r2 + r10
            int r3 = r0.zzf
            if (r2 < r3) goto L_0x016e
            int r2 = r10 + 1
            boolean r2 = r0.zzI(r2)
            if (r2 == 0) goto L_0x017d
        L_0x016e:
            char[] r2 = r0.zzd
            int r3 = r0.zze
            int r3 = r3 + r10
            char r2 = r2[r3]
            boolean r2 = r0.zzJ(r2)
            if (r2 == 0) goto L_0x017d
        L_0x017b:
            r4 = r1
            goto L_0x0184
        L_0x017d:
            int r2 = r0.zze
            int r2 = r2 + r10
            r0.zze = r2
            r0.zza = r4
        L_0x0184:
            if (r4 != 0) goto L_0x02a2
            char[] r2 = r0.zzd
            int r3 = r0.zze
            int r4 = r0.zzf
            r7 = 0
            r9 = r1
            r10 = r9
            r16 = r10
            r11 = r7
            r15 = r14
        L_0x0194:
            int r1 = r3 + r10
            if (r1 != r4) goto L_0x01b0
            r1 = 1024(0x400, float:1.435E-42)
            if (r10 != r1) goto L_0x019f
        L_0x019c:
            r14 = 0
            goto L_0x0284
        L_0x019f:
            int r1 = r10 + 1
            boolean r1 = r0.zzI(r1)
            if (r1 != 0) goto L_0x01aa
        L_0x01a7:
            r1 = 2
            goto L_0x021b
        L_0x01aa:
            int r1 = r0.zze
            int r3 = r0.zzf
            r4 = r3
            r3 = r1
        L_0x01b0:
            int r1 = r3 + r10
            char r1 = r2[r1]
            r6 = 43
            if (r1 == r6) goto L_0x0276
            r6 = 69
            if (r1 == r6) goto L_0x026b
            r6 = 101(0x65, float:1.42E-43)
            if (r1 == r6) goto L_0x026b
            r6 = 45
            if (r1 == r6) goto L_0x025e
            r6 = 46
            if (r1 == r6) goto L_0x0256
            r6 = 48
            if (r1 < r6) goto L_0x0214
            r6 = 57
            if (r1 <= r6) goto L_0x01d1
            goto L_0x0214
        L_0x01d1:
            if (r9 == r14) goto L_0x020a
            if (r9 != 0) goto L_0x01d6
            goto L_0x020a
        L_0x01d6:
            r6 = 2
            if (r9 != r6) goto L_0x01ff
            int r6 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x01de
            goto L_0x019c
        L_0x01de:
            int r1 = r1 + -48
            r17 = 10
            long r17 = r17 * r11
            r19 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r6 = (r11 > r19 ? 1 : (r11 == r19 ? 0 : -1))
            long r7 = (long) r1
            long r17 = r17 - r7
            if (r6 > 0) goto L_0x01f9
            if (r6 != 0) goto L_0x01f7
            int r1 = (r17 > r11 ? 1 : (r17 == r11 ? 0 : -1))
            if (r1 >= 0) goto L_0x01f7
            goto L_0x01f9
        L_0x01f7:
            r1 = 0
            goto L_0x01fa
        L_0x01f9:
            r1 = r14
        L_0x01fa:
            r15 = r15 & r1
            r11 = r17
            r7 = 6
            goto L_0x0210
        L_0x01ff:
            r7 = 6
            if (r9 != r13) goto L_0x0204
            r9 = 4
            goto L_0x0210
        L_0x0204:
            if (r9 == r5) goto L_0x0208
            if (r9 != r7) goto L_0x0210
        L_0x0208:
            r9 = 7
            goto L_0x0210
        L_0x020a:
            r7 = 6
            int r1 = r1 + -48
            int r1 = -r1
            long r11 = (long) r1
            r9 = 2
        L_0x0210:
            r17 = 0
            goto L_0x027c
        L_0x0214:
            boolean r1 = r0.zzJ(r1)
            if (r1 == 0) goto L_0x01a7
            goto L_0x019c
        L_0x021b:
            if (r9 != r1) goto L_0x0247
            if (r15 == 0) goto L_0x0245
            r1 = -9223372036854775808
            int r1 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x0228
            if (r16 == 0) goto L_0x0245
            goto L_0x022a
        L_0x0228:
            r14 = r16
        L_0x022a:
            r17 = 0
            int r1 = (r11 > r17 ? 1 : (r11 == r17 ? 0 : -1))
            if (r1 != 0) goto L_0x0233
            if (r14 != 0) goto L_0x0245
            goto L_0x0236
        L_0x0233:
            if (r14 == 0) goto L_0x0236
            goto L_0x0237
        L_0x0236:
            long r11 = -r11
        L_0x0237:
            r0.zzi = r11
            int r1 = r0.zze
            int r1 = r1 + r10
            r0.zze = r1
            r1 = 15
            r0.zza = r1
            r14 = 15
            goto L_0x0284
        L_0x0245:
            r1 = 2
            r9 = 2
        L_0x0247:
            if (r9 == r1) goto L_0x024f
            r1 = 4
            if (r9 == r1) goto L_0x024f
            r1 = 7
            if (r9 != r1) goto L_0x019c
        L_0x024f:
            r0.zzj = r10
            r14 = 16
            r0.zza = r14
            goto L_0x0284
        L_0x0256:
            r17 = r7
            r1 = 2
            r7 = 6
            if (r9 != r1) goto L_0x019c
            r9 = r13
            goto L_0x027c
        L_0x025e:
            r17 = r7
            r1 = 2
            r7 = 6
            if (r9 != 0) goto L_0x0268
            r9 = r14
            r16 = r9
            goto L_0x027c
        L_0x0268:
            if (r9 != r5) goto L_0x019c
            goto L_0x027b
        L_0x026b:
            r17 = r7
            r1 = 2
            r7 = 6
            if (r9 == r1) goto L_0x0274
            r1 = 4
            if (r9 != r1) goto L_0x019c
        L_0x0274:
            r9 = r5
            goto L_0x027c
        L_0x0276:
            r17 = r7
            r7 = 6
            if (r9 != r5) goto L_0x019c
        L_0x027b:
            r9 = r7
        L_0x027c:
            int r10 = r10 + 1
            r7 = r17
            r6 = 10
            goto L_0x0194
        L_0x0284:
            if (r14 == 0) goto L_0x0287
            return r14
        L_0x0287:
            char[] r1 = r0.zzd
            int r2 = r0.zze
            char r1 = r1[r2]
            boolean r1 = r0.zzJ(r1)
            if (r1 == 0) goto L_0x029b
            r21.zzD()
            r1 = 10
            r0.zza = r1
            return r1
        L_0x029b:
            java.lang.String r1 = "Expected value"
            com.google.ads.interactivemedia.v3.internal.zzacf r1 = r0.zzy(r1)
            throw r1
        L_0x02a2:
            return r4
        L_0x02a3:
            r13 = r14
            goto L_0x032a
        L_0x02a6:
            if (r4 != r14) goto L_0x02ab
            r13 = 4
            goto L_0x032a
        L_0x02ab:
            if (r4 == r14) goto L_0x02b8
            r6 = 2
            if (r4 != r6) goto L_0x02b1
            goto L_0x02b8
        L_0x02b1:
            java.lang.String r1 = "Unexpected value"
            com.google.ads.interactivemedia.v3.internal.zzacf r1 = r0.zzy(r1)
            throw r1
        L_0x02b8:
            r21.zzD()
            int r1 = r0.zze
            int r1 = r1 + r3
            r0.zze = r1
            r1 = 7
            r0.zza = r1
            return r1
        L_0x02c4:
            r21.zzD()
            r1 = 8
            r0.zza = r1
            return r1
        L_0x02cc:
            r13 = 9
            goto L_0x032a
        L_0x02cf:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "JsonReader is closed"
            r1.<init>(r2)
            throw r1
        L_0x02d7:
            r6 = 2
            r9 = r15
        L_0x02d9:
            r1[r2] = r9
            if (r4 != r5) goto L_0x02f3
            int r1 = r0.zzn(r14)
            if (r1 == r11) goto L_0x02f3
            if (r1 == r10) goto L_0x02f0
            if (r1 != r7) goto L_0x02e9
        L_0x02e7:
            r13 = r6
            goto L_0x032a
        L_0x02e9:
            java.lang.String r1 = "Unterminated object"
            com.google.ads.interactivemedia.v3.internal.zzacf r1 = r0.zzy(r1)
            throw r1
        L_0x02f0:
            r21.zzD()
        L_0x02f3:
            int r1 = r0.zzn(r14)
            r2 = 34
            if (r1 == r2) goto L_0x0328
            if (r1 == r8) goto L_0x0320
            java.lang.String r2 = "Expected name"
            if (r1 == r7) goto L_0x0318
            r21.zzD()
            int r4 = r0.zze
            int r4 = r4 + r3
            r0.zze = r4
            char r1 = (char) r1
            boolean r1 = r0.zzJ(r1)
            if (r1 == 0) goto L_0x0313
            r13 = 14
            goto L_0x032a
        L_0x0313:
            com.google.ads.interactivemedia.v3.internal.zzacf r1 = r0.zzy(r2)
            throw r1
        L_0x0318:
            if (r4 == r5) goto L_0x031b
            goto L_0x02e7
        L_0x031b:
            com.google.ads.interactivemedia.v3.internal.zzacf r1 = r0.zzy(r2)
            throw r1
        L_0x0320:
            r21.zzD()
            r1 = 12
            r0.zza = r1
            return r1
        L_0x0328:
            r13 = 13
        L_0x032a:
            r0.zza = r13
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.interactivemedia.v3.internal.zzacc.zzs():int");
    }

    public final zzwg zzt() {
        return this.zzc;
    }

    /* access modifiers changed from: package-private */
    public String zzv() {
        int i = this.zze - this.zzh;
        String zze2 = zze();
        return " at line " + (this.zzg + 1) + " column " + (i + 1) + " path " + zze2;
    }

    public final void zzw(zzwg zzwg) {
        Objects.requireNonNull(zzwg);
        this.zzc = zzwg;
    }

    public final boolean zzx() {
        return this.zzc == zzwg.LENIENT;
    }
}
