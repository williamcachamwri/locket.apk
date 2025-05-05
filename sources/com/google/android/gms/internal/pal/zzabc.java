package com.google.android.gms.internal.pal;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzabc implements Closeable {
    int zza = 0;
    private final Reader zzb;
    private final char[] zzc = new char[1024];
    private int zzd = 0;
    private int zze = 0;
    private int zzf = 0;
    private int zzg = 0;
    private long zzh;
    private int zzi;
    private int[] zzj;
    private int zzk;
    private String[] zzl;
    private int[] zzm;

    static {
        zzzi.zza = new zzabb();
    }

    public zzabc(Reader reader) {
        int[] iArr = new int[32];
        this.zzj = iArr;
        this.zzk = 1;
        iArr[0] = 6;
        this.zzl = new String[32];
        this.zzm = new int[32];
        this.zzb = reader;
    }

    private final int zzm(boolean z) throws IOException {
        char[] cArr = this.zzc;
        int i = this.zzd;
        int i2 = this.zze;
        while (true) {
            if (i == i2) {
                this.zzd = i;
                if (zzr(1)) {
                    i = this.zzd;
                    i2 = this.zze;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input".concat(zzb()));
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == 10) {
                this.zzf++;
                this.zzg = i3;
            } else if (!(c == ' ' || c == 13 || c == 9)) {
                if (c == '/') {
                    this.zzd = i3;
                    if (i3 == i2) {
                        this.zzd = i3 - 1;
                        boolean zzr = zzr(2);
                        this.zzd++;
                        if (!zzr) {
                            return 47;
                        }
                    }
                    throw zzn("Use JsonReader.setLenient(true) to accept malformed JSON");
                } else if (c != '#') {
                    this.zzd = i3;
                    return c;
                } else {
                    this.zzd = i3;
                    throw zzn("Use JsonReader.setLenient(true) to accept malformed JSON");
                }
            }
            i = i3;
        }
    }

    private final IOException zzn(String str) throws IOException {
        throw new zzabf(str.concat(zzb()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:84:0x011c, code lost:
        if (r1 != null) goto L_0x012b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x011e, code lost:
        r1 = r2 - r3;
        r1 = new java.lang.StringBuilder(java.lang.Math.max(r1 + r1, 16));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x012b, code lost:
        r1.append(r0, r3, r2 - r3);
        r10.zzd = r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String zzo(char r11) throws java.io.IOException {
        /*
            r10 = this;
            char[] r0 = r10.zzc
            r1 = 0
        L_0x0003:
            int r2 = r10.zzd
            int r3 = r10.zze
        L_0x0007:
            r4 = r3
            r3 = r2
        L_0x0009:
            r5 = 16
            r6 = 1
            if (r2 >= r4) goto L_0x011c
            int r7 = r2 + 1
            char r2 = r0[r2]
            if (r2 != r11) goto L_0x0029
            r10.zzd = r7
            int r7 = r7 - r3
            int r7 = r7 + -1
            if (r1 != 0) goto L_0x0021
            java.lang.String r11 = new java.lang.String
            r11.<init>(r0, r3, r7)
            return r11
        L_0x0021:
            r1.append(r0, r3, r7)
            java.lang.String r11 = r1.toString()
            return r11
        L_0x0029:
            r8 = 92
            r9 = 10
            if (r2 != r8) goto L_0x0110
            r10.zzd = r7
            int r7 = r7 - r3
            int r7 = r7 + -1
            if (r1 != 0) goto L_0x0043
            int r1 = r7 + 1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            int r1 = r1 + r1
            int r1 = java.lang.Math.max(r1, r5)
            r2.<init>(r1)
            r1 = r2
        L_0x0043:
            r1.append(r0, r3, r7)
            int r2 = r10.zzd
            int r3 = r10.zze
            java.lang.String r4 = "Unterminated escape sequence"
            if (r2 != r3) goto L_0x005a
            boolean r2 = r10.zzr(r6)
            if (r2 == 0) goto L_0x0055
            goto L_0x005a
        L_0x0055:
            java.io.IOException r11 = r10.zzn(r4)
            throw r11
        L_0x005a:
            char[] r2 = r10.zzc
            int r3 = r10.zzd
            int r5 = r3 + 1
            r10.zzd = r5
            char r2 = r2[r3]
            if (r2 == r9) goto L_0x00ff
            r3 = 34
            if (r2 == r3) goto L_0x0106
            r3 = 39
            if (r2 == r3) goto L_0x0106
            r3 = 47
            if (r2 == r3) goto L_0x0106
            if (r2 == r8) goto L_0x0106
            r3 = 98
            if (r2 == r3) goto L_0x00fc
            r3 = 102(0x66, float:1.43E-43)
            if (r2 == r3) goto L_0x00f9
            r6 = 110(0x6e, float:1.54E-43)
            if (r2 == r6) goto L_0x0107
            r6 = 114(0x72, float:1.6E-43)
            if (r2 == r6) goto L_0x00f6
            r6 = 116(0x74, float:1.63E-43)
            if (r2 == r6) goto L_0x00f3
            r6 = 117(0x75, float:1.64E-43)
            if (r2 != r6) goto L_0x00ec
            int r5 = r5 + 4
            int r2 = r10.zze
            r6 = 4
            if (r5 <= r2) goto L_0x009f
            boolean r2 = r10.zzr(r6)
            if (r2 == 0) goto L_0x009a
            goto L_0x009f
        L_0x009a:
            java.io.IOException r11 = r10.zzn(r4)
            throw r11
        L_0x009f:
            int r2 = r10.zzd
            int r4 = r2 + 4
            r5 = 0
            r9 = r5
        L_0x00a5:
            if (r2 >= r4) goto L_0x00e6
            char[] r5 = r10.zzc
            char r7 = r5[r2]
            int r8 = r9 << 4
            char r8 = (char) r8
            r9 = 48
            if (r7 < r9) goto L_0x00bc
            r9 = 57
            if (r7 > r9) goto L_0x00bc
            int r7 = r7 + -48
        L_0x00b8:
            int r8 = r8 + r7
            char r5 = (char) r8
            r9 = r5
            goto L_0x00d0
        L_0x00bc:
            r9 = 97
            if (r7 < r9) goto L_0x00c5
            if (r7 > r3) goto L_0x00c5
            int r7 = r7 + -87
            goto L_0x00b8
        L_0x00c5:
            r9 = 65
            if (r7 < r9) goto L_0x00d3
            r9 = 70
            if (r7 > r9) goto L_0x00d3
            int r7 = r7 + -55
            goto L_0x00b8
        L_0x00d0:
            int r2 = r2 + 1
            goto L_0x00a5
        L_0x00d3:
            java.lang.NumberFormatException r11 = new java.lang.NumberFormatException
            java.lang.String r0 = new java.lang.String
            int r1 = r10.zzd
            r0.<init>(r5, r1, r6)
            java.lang.String r1 = "\\u"
            java.lang.String r0 = r1.concat(r0)
            r11.<init>(r0)
            throw r11
        L_0x00e6:
            int r2 = r10.zzd
            int r2 = r2 + r6
            r10.zzd = r2
            goto L_0x0107
        L_0x00ec:
            java.lang.String r11 = "Invalid escape sequence"
            java.io.IOException r11 = r10.zzn(r11)
            throw r11
        L_0x00f3:
            r9 = 9
            goto L_0x0107
        L_0x00f6:
            r9 = 13
            goto L_0x0107
        L_0x00f9:
            r9 = 12
            goto L_0x0107
        L_0x00fc:
            r9 = 8
            goto L_0x0107
        L_0x00ff:
            int r3 = r10.zzf
            int r3 = r3 + r6
            r10.zzf = r3
            r10.zzg = r5
        L_0x0106:
            r9 = r2
        L_0x0107:
            r1.append(r9)
            int r2 = r10.zzd
            int r3 = r10.zze
            goto L_0x0007
        L_0x0110:
            if (r2 != r9) goto L_0x0119
            int r2 = r10.zzf
            int r2 = r2 + r6
            r10.zzf = r2
            r10.zzg = r7
        L_0x0119:
            r2 = r7
            goto L_0x0009
        L_0x011c:
            if (r1 != 0) goto L_0x012b
            int r1 = r2 - r3
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            int r1 = r1 + r1
            int r1 = java.lang.Math.max(r1, r5)
            r4.<init>(r1)
            r1 = r4
        L_0x012b:
            int r4 = r2 - r3
            r1.append(r0, r3, r4)
            r10.zzd = r2
            boolean r2 = r10.zzr(r6)
            if (r2 == 0) goto L_0x013a
            goto L_0x0003
        L_0x013a:
            java.lang.String r11 = "Unterminated string"
            java.io.IOException r11 = r10.zzn(r11)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzabc.zzo(char):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x004e, code lost:
        throw zzn("Use JsonReader.setLenient(true) to accept malformed JSON");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String zzp() throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            r2 = r1
        L_0x0003:
            int r3 = r5.zzd
            int r3 = r3 + r2
            int r4 = r5.zze
            if (r3 >= r4) goto L_0x004f
            char[] r4 = r5.zzc
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L_0x005b
            r4 = 10
            if (r3 == r4) goto L_0x005b
            r4 = 12
            if (r3 == r4) goto L_0x005b
            r4 = 13
            if (r3 == r4) goto L_0x005b
            r4 = 32
            if (r3 == r4) goto L_0x005b
            r4 = 35
            if (r3 == r4) goto L_0x0048
            r4 = 44
            if (r3 == r4) goto L_0x005b
            r4 = 47
            if (r3 == r4) goto L_0x0048
            r4 = 61
            if (r3 == r4) goto L_0x0048
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L_0x005b
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L_0x005b
            r4 = 58
            if (r3 == r4) goto L_0x005b
            r4 = 59
            if (r3 == r4) goto L_0x0048
            switch(r3) {
                case 91: goto L_0x005b;
                case 92: goto L_0x0048;
                case 93: goto L_0x005b;
                default: goto L_0x0045;
            }
        L_0x0045:
            int r2 = r2 + 1
            goto L_0x0003
        L_0x0048:
            java.lang.String r0 = "Use JsonReader.setLenient(true) to accept malformed JSON"
            java.io.IOException r0 = r5.zzn(r0)
            throw r0
        L_0x004f:
            r3 = 1024(0x400, float:1.435E-42)
            if (r2 >= r3) goto L_0x005d
            int r3 = r2 + 1
            boolean r3 = r5.zzr(r3)
            if (r3 != 0) goto L_0x0003
        L_0x005b:
            r1 = r2
            goto L_0x007d
        L_0x005d:
            if (r0 != 0) goto L_0x006a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r0.<init>(r3)
        L_0x006a:
            char[] r3 = r5.zzc
            int r4 = r5.zzd
            r0.append(r3, r4, r2)
            int r3 = r5.zzd
            int r3 = r3 + r2
            r5.zzd = r3
            r2 = 1
            boolean r2 = r5.zzr(r2)
            if (r2 != 0) goto L_0x0002
        L_0x007d:
            if (r0 != 0) goto L_0x0089
            java.lang.String r0 = new java.lang.String
            char[] r2 = r5.zzc
            int r3 = r5.zzd
            r0.<init>(r2, r3, r1)
            goto L_0x0094
        L_0x0089:
            char[] r2 = r5.zzc
            int r3 = r5.zzd
            r0.append(r2, r3, r1)
            java.lang.String r0 = r0.toString()
        L_0x0094:
            int r2 = r5.zzd
            int r2 = r2 + r1
            r5.zzd = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzabc.zzp():java.lang.String");
    }

    private final void zzq(int i) {
        int i2 = this.zzk;
        int[] iArr = this.zzj;
        if (i2 == iArr.length) {
            int i3 = i2 + i2;
            this.zzj = Arrays.copyOf(iArr, i3);
            this.zzm = Arrays.copyOf(this.zzm, i3);
            this.zzl = (String[]) Arrays.copyOf(this.zzl, i3);
        }
        int[] iArr2 = this.zzj;
        int i4 = this.zzk;
        this.zzk = i4 + 1;
        iArr2[i4] = i;
    }

    private final boolean zzr(int i) throws IOException {
        int i2;
        char[] cArr = this.zzc;
        int i3 = this.zzg;
        int i4 = this.zzd;
        this.zzg = i3 - i4;
        int i5 = this.zze;
        if (i5 != i4) {
            int i6 = i5 - i4;
            this.zze = i6;
            System.arraycopy(cArr, i4, cArr, 0, i6);
        } else {
            this.zze = 0;
        }
        this.zzd = 0;
        do {
            Reader reader = this.zzb;
            int i7 = this.zze;
            int read = reader.read(cArr, i7, 1024 - i7);
            if (read == -1) {
                return false;
            }
            i2 = this.zze + read;
            this.zze = i2;
            if (this.zzf == 0 && this.zzg == 0 && i2 > 0 && cArr[0] == 65279) {
                this.zzd++;
                this.zzg = 1;
                i++;
                continue;
            }
        } while (i2 < i);
        return true;
    }

    private final boolean zzs(char c) throws IOException {
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
        throw zzn("Use JsonReader.setLenient(true) to accept malformed JSON");
    }

    public final void close() throws IOException {
        this.zza = 0;
        this.zzj[0] = 8;
        this.zzk = 1;
        this.zzb.close();
    }

    public final String toString() {
        return String.valueOf(getClass().getSimpleName()).concat(zzb());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x01bc, code lost:
        if (zzs(r14) == false) goto L_0x01c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x01c0, code lost:
        if (r9 != 2) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x01c2, code lost:
        if (r17 == false) goto L_0x01e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x01c8, code lost:
        if (r12 != Long.MIN_VALUE) goto L_0x01ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x01ca, code lost:
        if (r18 == false) goto L_0x01e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x01cc, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x01ce, code lost:
        r3 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x01d2, code lost:
        if (r12 != 0) goto L_0x01d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x01d4, code lost:
        if (r3 != false) goto L_0x01e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x01d7, code lost:
        if (r3 == false) goto L_0x01da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x01da, code lost:
        r12 = -r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x01db, code lost:
        r0.zzh = r12;
        r0.zzd += r8;
        r0.zza = 15;
        r6 = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x01e9, code lost:
        r9 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x01ea, code lost:
        if (r9 == 2) goto L_0x01f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x01ed, code lost:
        if (r9 == 4) goto L_0x01f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x01f0, code lost:
        if (r9 != 7) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x01f2, code lost:
        r0.zzi = r8;
        r6 = 16;
        r0.zza = 16;
     */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0245 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0121  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza() throws java.io.IOException {
        /*
            r22 = this;
            r0 = r22
            int[] r1 = r0.zzj
            int r2 = r0.zzk
            r3 = -1
            int r2 = r2 + r3
            r4 = r1[r2]
            r7 = 93
            r8 = 59
            r9 = 44
            r10 = 6
            r11 = 3
            r12 = 7
            java.lang.String r13 = "Use JsonReader.setLenient(true) to accept malformed JSON"
            r14 = 4
            r15 = 5
            r5 = 2
            r6 = 0
            r3 = 1
            if (r4 != r3) goto L_0x001f
            r1[r2] = r5
            goto L_0x0078
        L_0x001f:
            if (r4 != r5) goto L_0x003a
            int r1 = r0.zzm(r3)
            if (r1 == r9) goto L_0x0078
            if (r1 == r8) goto L_0x0035
            if (r1 != r7) goto L_0x002e
            r0.zza = r14
            return r14
        L_0x002e:
            java.lang.String r1 = "Unterminated array"
            java.io.IOException r1 = r0.zzn(r1)
            throw r1
        L_0x0035:
            java.io.IOException r1 = r0.zzn(r13)
            throw r1
        L_0x003a:
            if (r4 == r11) goto L_0x027c
            if (r4 != r15) goto L_0x0040
            goto L_0x027c
        L_0x0040:
            if (r4 != r14) goto L_0x005c
            r1[r2] = r15
            int r1 = r0.zzm(r3)
            r2 = 58
            if (r1 == r2) goto L_0x0078
            r2 = 61
            if (r1 == r2) goto L_0x0057
            java.lang.String r1 = "Expected ':'"
            java.io.IOException r1 = r0.zzn(r1)
            throw r1
        L_0x0057:
            java.io.IOException r1 = r0.zzn(r13)
            throw r1
        L_0x005c:
            if (r4 != r10) goto L_0x0061
            r1[r2] = r12
            goto L_0x0078
        L_0x0061:
            if (r4 != r12) goto L_0x0074
            int r1 = r0.zzm(r6)
            r2 = -1
            if (r1 != r2) goto L_0x006f
            r1 = 17
            r0.zza = r1
            return r1
        L_0x006f:
            java.io.IOException r1 = r0.zzn(r13)
            throw r1
        L_0x0074:
            r1 = 8
            if (r4 == r1) goto L_0x0274
        L_0x0078:
            int r1 = r0.zzm(r3)
            r2 = 34
            if (r1 == r2) goto L_0x026f
            r2 = 39
            if (r1 == r2) goto L_0x0269
            if (r1 == r9) goto L_0x0256
            if (r1 == r8) goto L_0x0256
            r2 = 91
            if (r1 == r2) goto L_0x0253
            if (r1 == r7) goto L_0x024a
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L_0x0246
            int r1 = r0.zzd
            r2 = -1
            int r1 = r1 + r2
            r0.zzd = r1
            char[] r2 = r0.zzc
            char r1 = r2[r1]
            r2 = 116(0x74, float:1.63E-43)
            if (r1 == r2) goto L_0x00c6
            r2 = 84
            if (r1 != r2) goto L_0x00a5
            goto L_0x00c6
        L_0x00a5:
            r2 = 102(0x66, float:1.43E-43)
            if (r1 == r2) goto L_0x00c0
            r2 = 70
            if (r1 != r2) goto L_0x00ae
            goto L_0x00c0
        L_0x00ae:
            r2 = 110(0x6e, float:1.54E-43)
            if (r1 == r2) goto L_0x00ba
            r2 = 78
            if (r1 != r2) goto L_0x00b7
            goto L_0x00ba
        L_0x00b7:
            r4 = r6
            goto L_0x011f
        L_0x00ba:
            java.lang.String r1 = "null"
            java.lang.String r2 = "NULL"
            r4 = r12
            goto L_0x00cb
        L_0x00c0:
            java.lang.String r1 = "false"
            java.lang.String r2 = "FALSE"
            r4 = r10
            goto L_0x00cb
        L_0x00c6:
            java.lang.String r1 = "true"
            java.lang.String r2 = "TRUE"
            r4 = r15
        L_0x00cb:
            int r7 = r1.length()
            r8 = r3
        L_0x00d0:
            if (r8 >= r7) goto L_0x00fa
            int r9 = r0.zzd
            int r9 = r9 + r8
            int r6 = r0.zze
            if (r9 < r6) goto L_0x00e2
            int r6 = r8 + 1
            boolean r6 = r0.zzr(r6)
            if (r6 != 0) goto L_0x00e2
            goto L_0x0116
        L_0x00e2:
            char[] r6 = r0.zzc
            int r9 = r0.zzd
            int r9 = r9 + r8
            char r6 = r6[r9]
            char r9 = r1.charAt(r8)
            if (r6 == r9) goto L_0x00f6
            char r9 = r2.charAt(r8)
            if (r6 == r9) goto L_0x00f6
            goto L_0x0116
        L_0x00f6:
            int r8 = r8 + 1
            r6 = 0
            goto L_0x00d0
        L_0x00fa:
            int r1 = r0.zzd
            int r1 = r1 + r7
            int r2 = r0.zze
            if (r1 < r2) goto L_0x0109
            int r1 = r7 + 1
            boolean r1 = r0.zzr(r1)
            if (r1 == 0) goto L_0x0118
        L_0x0109:
            char[] r1 = r0.zzc
            int r2 = r0.zzd
            int r2 = r2 + r7
            char r1 = r1[r2]
            boolean r1 = r0.zzs(r1)
            if (r1 == 0) goto L_0x0118
        L_0x0116:
            r4 = 0
            goto L_0x011f
        L_0x0118:
            int r1 = r0.zzd
            int r1 = r1 + r7
            r0.zzd = r1
            r0.zza = r4
        L_0x011f:
            if (r4 != 0) goto L_0x0245
            char[] r1 = r0.zzc
            int r2 = r0.zzd
            int r4 = r0.zze
            r6 = 0
            r17 = r3
            r16 = r13
            r8 = 0
            r9 = 0
            r18 = 0
            r12 = r6
        L_0x0132:
            int r14 = r2 + r8
            if (r14 != r4) goto L_0x014b
            r2 = 1024(0x400, float:1.435E-42)
            if (r8 != r2) goto L_0x013d
        L_0x013a:
            r6 = 0
            goto L_0x0228
        L_0x013d:
            int r2 = r8 + 1
            boolean r2 = r0.zzr(r2)
            if (r2 != 0) goto L_0x0147
            goto L_0x01c0
        L_0x0147:
            int r2 = r0.zzd
            int r4 = r0.zze
        L_0x014b:
            int r14 = r2 + r8
            char r14 = r1[r14]
            r10 = 43
            if (r14 == r10) goto L_0x0219
            r10 = 69
            if (r14 == r10) goto L_0x020e
            r10 = 101(0x65, float:1.42E-43)
            if (r14 == r10) goto L_0x020e
            r10 = 45
            if (r14 == r10) goto L_0x0201
            r10 = 46
            if (r14 == r10) goto L_0x01f9
            r10 = 48
            if (r14 < r10) goto L_0x01b8
            r10 = 57
            if (r14 <= r10) goto L_0x016c
            goto L_0x01b8
        L_0x016c:
            if (r9 == r3) goto L_0x01ad
            if (r9 != 0) goto L_0x0171
            goto L_0x01ad
        L_0x0171:
            if (r9 != r5) goto L_0x019c
            int r10 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r10 != 0) goto L_0x0178
            goto L_0x013a
        L_0x0178:
            r19 = 10
            long r19 = r19 * r12
            int r14 = r14 + -48
            r21 = r4
            long r3 = (long) r14
            long r19 = r19 - r3
            r3 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r3 = (r12 > r3 ? 1 : (r12 == r3 ? 0 : -1))
            if (r3 > 0) goto L_0x0195
            if (r3 != 0) goto L_0x0193
            int r3 = (r19 > r12 ? 1 : (r19 == r12 ? 0 : -1))
            if (r3 >= 0) goto L_0x0193
            goto L_0x0195
        L_0x0193:
            r3 = 0
            goto L_0x0196
        L_0x0195:
            r3 = 1
        L_0x0196:
            r17 = r17 & r3
            r12 = r19
            r3 = 6
            goto L_0x01b5
        L_0x019c:
            r21 = r4
            r3 = 6
            if (r9 != r11) goto L_0x01a5
            r4 = 7
            r9 = 4
            goto L_0x0220
        L_0x01a5:
            r4 = 7
            if (r9 == r15) goto L_0x01aa
            if (r9 != r3) goto L_0x0220
        L_0x01aa:
            r9 = 7
            goto L_0x0220
        L_0x01ad:
            r21 = r4
            r3 = 6
            int r14 = r14 + -48
            int r4 = -r14
            long r12 = (long) r4
            r9 = r5
        L_0x01b5:
            r4 = 7
            goto L_0x0220
        L_0x01b8:
            boolean r1 = r0.zzs(r14)
            if (r1 == 0) goto L_0x01c0
            goto L_0x013a
        L_0x01c0:
            if (r9 != r5) goto L_0x01ea
            if (r17 == 0) goto L_0x01e9
            r1 = -9223372036854775808
            int r1 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x01ce
            if (r18 == 0) goto L_0x01e9
            r3 = 1
            goto L_0x01d0
        L_0x01ce:
            r3 = r18
        L_0x01d0:
            int r1 = (r12 > r6 ? 1 : (r12 == r6 ? 0 : -1))
            if (r1 != 0) goto L_0x01d7
            if (r3 != 0) goto L_0x01e9
            goto L_0x01da
        L_0x01d7:
            if (r3 == 0) goto L_0x01da
            goto L_0x01db
        L_0x01da:
            long r12 = -r12
        L_0x01db:
            r0.zzh = r12
            int r1 = r0.zzd
            int r1 = r1 + r8
            r0.zzd = r1
            r1 = 15
            r0.zza = r1
            r6 = 15
            goto L_0x0228
        L_0x01e9:
            r9 = r5
        L_0x01ea:
            if (r9 == r5) goto L_0x01f2
            r1 = 4
            if (r9 == r1) goto L_0x01f2
            r4 = 7
            if (r9 != r4) goto L_0x013a
        L_0x01f2:
            r0.zzi = r8
            r6 = 16
            r0.zza = r6
            goto L_0x0228
        L_0x01f9:
            r21 = r4
            r3 = 6
            r4 = 7
            if (r9 != r5) goto L_0x013a
            r9 = r11
            goto L_0x0220
        L_0x0201:
            r21 = r4
            r3 = 6
            r4 = 7
            if (r9 != 0) goto L_0x020b
            r9 = 1
            r18 = 1
            goto L_0x0220
        L_0x020b:
            if (r9 != r15) goto L_0x013a
            goto L_0x021f
        L_0x020e:
            r21 = r4
            r3 = 6
            r4 = 7
            if (r9 == r5) goto L_0x0217
            r14 = 4
            if (r9 != r14) goto L_0x013a
        L_0x0217:
            r9 = r15
            goto L_0x0220
        L_0x0219:
            r21 = r4
            r3 = 6
            r4 = 7
            if (r9 != r15) goto L_0x013a
        L_0x021f:
            r9 = r3
        L_0x0220:
            int r8 = r8 + 1
            r10 = r3
            r4 = r21
            r3 = 1
            goto L_0x0132
        L_0x0228:
            if (r6 == 0) goto L_0x022b
            return r6
        L_0x022b:
            char[] r1 = r0.zzc
            int r2 = r0.zzd
            char r1 = r1[r2]
            boolean r1 = r0.zzs(r1)
            if (r1 != 0) goto L_0x023e
            java.lang.String r1 = "Expected value"
            java.io.IOException r1 = r0.zzn(r1)
            throw r1
        L_0x023e:
            r3 = r16
            java.io.IOException r1 = r0.zzn(r3)
            throw r1
        L_0x0245:
            return r4
        L_0x0246:
            r1 = r3
            r0.zza = r1
            return r1
        L_0x024a:
            r1 = r3
            r3 = r13
            if (r4 == r1) goto L_0x024f
            goto L_0x0258
        L_0x024f:
            r2 = 4
            r0.zza = r2
            return r2
        L_0x0253:
            r0.zza = r11
            return r11
        L_0x0256:
            r1 = r3
            r3 = r13
        L_0x0258:
            if (r4 == r1) goto L_0x0264
            if (r4 != r5) goto L_0x025d
            goto L_0x0264
        L_0x025d:
            java.lang.String r1 = "Unexpected value"
            java.io.IOException r1 = r0.zzn(r1)
            throw r1
        L_0x0264:
            java.io.IOException r1 = r0.zzn(r3)
            throw r1
        L_0x0269:
            r3 = r13
            java.io.IOException r1 = r0.zzn(r3)
            throw r1
        L_0x026f:
            r1 = 9
            r0.zza = r1
            return r1
        L_0x0274:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "JsonReader is closed"
            r1.<init>(r2)
            throw r1
        L_0x027c:
            r3 = r13
            r6 = r14
            r1[r2] = r6
            r1 = 125(0x7d, float:1.75E-43)
            if (r4 != r15) goto L_0x029e
            r2 = 1
            int r6 = r0.zzm(r2)
            if (r6 == r9) goto L_0x029e
            if (r6 == r8) goto L_0x0299
            if (r6 != r1) goto L_0x0292
            r0.zza = r5
            return r5
        L_0x0292:
            java.lang.String r1 = "Unterminated object"
            java.io.IOException r1 = r0.zzn(r1)
            throw r1
        L_0x0299:
            java.io.IOException r1 = r0.zzn(r3)
            throw r1
        L_0x029e:
            r2 = 1
            int r2 = r0.zzm(r2)
            r6 = 34
            if (r2 == r6) goto L_0x02c3
            r6 = 39
            if (r2 == r6) goto L_0x02be
            if (r2 != r1) goto L_0x02b9
            if (r4 == r15) goto L_0x02b2
            r0.zza = r5
            return r5
        L_0x02b2:
            java.lang.String r1 = "Expected name"
            java.io.IOException r1 = r0.zzn(r1)
            throw r1
        L_0x02b9:
            java.io.IOException r1 = r0.zzn(r3)
            throw r1
        L_0x02be:
            java.io.IOException r1 = r0.zzn(r3)
            throw r1
        L_0x02c3:
            r1 = 13
            r0.zza = r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.pal.zzabc.zza():int");
    }

    /* access modifiers changed from: package-private */
    public final String zzb() {
        int i = this.zzf;
        int i2 = this.zzd;
        int i3 = this.zzg;
        StringBuilder sb = new StringBuilder(" at line ");
        sb.append(i + 1);
        sb.append(" column ");
        sb.append((i2 - i3) + 1);
        sb.append(" path ");
        StringBuilder sb2 = new StringBuilder("$");
        for (int i4 = 0; i4 < this.zzk; i4++) {
            int i5 = this.zzj[i4];
            if (i5 == 1 || i5 == 2) {
                int i6 = this.zzm[i4];
                sb2.append(AbstractJsonLexerKt.BEGIN_LIST);
                sb2.append(i6);
                sb2.append(AbstractJsonLexerKt.END_LIST);
            } else if (i5 == 3 || i5 == 4 || i5 == 5) {
                sb2.append(FilenameUtils.EXTENSION_SEPARATOR);
                String str = this.zzl[i4];
                if (str != null) {
                    sb2.append(str);
                }
            }
        }
        sb.append(sb2.toString());
        return sb.toString();
    }

    public final String zzc() throws IOException {
        String str;
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 14) {
            str = zzp();
        } else if (i == 12) {
            str = zzo('\'');
        } else if (i == 13) {
            str = zzo('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + zzabd.zza(zzl()) + zzb());
        }
        this.zza = 0;
        this.zzl[this.zzk - 1] = str;
        return str;
    }

    public final String zzd() throws IOException {
        String str;
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 10) {
            str = zzp();
        } else if (i == 8) {
            str = zzo('\'');
        } else if (i == 9) {
            str = zzo('\"');
        } else if (i == 11) {
            str = null;
        } else if (i == 15) {
            str = Long.toString(this.zzh);
        } else if (i == 16) {
            str = new String(this.zzc, this.zzd, this.zzi);
            this.zzd += this.zzi;
        } else {
            throw new IllegalStateException("Expected a string but was " + zzabd.zza(zzl()) + zzb());
        }
        this.zza = 0;
        int[] iArr = this.zzm;
        int i2 = this.zzk - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public final void zze() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 3) {
            zzq(1);
            this.zzm[this.zzk - 1] = 0;
            this.zza = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + zzabd.zza(zzl()) + zzb());
    }

    public final void zzf() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 1) {
            zzq(3);
            this.zza = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + zzabd.zza(zzl()) + zzb());
    }

    public final void zzg() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 4) {
            int i2 = this.zzk - 1;
            this.zzk = i2;
            int[] iArr = this.zzm;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.zza = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + zzabd.zza(zzl()) + zzb());
    }

    public final void zzh() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 2) {
            int i2 = this.zzk - 1;
            this.zzk = i2;
            this.zzl[i2] = null;
            int[] iArr = this.zzm;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.zza = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + zzabd.zza(zzl()) + zzb());
    }

    public final void zzi() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 7) {
            this.zza = 0;
            int[] iArr = this.zzm;
            int i2 = this.zzk - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + zzabd.zza(zzl()) + zzb());
    }

    public final boolean zzj() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        return (i == 2 || i == 4 || i == 17) ? false : true;
    }

    public final boolean zzk() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
        }
        if (i == 5) {
            this.zza = 0;
            int[] iArr = this.zzm;
            int i2 = this.zzk - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.zza = 0;
            int[] iArr2 = this.zzm;
            int i3 = this.zzk - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + zzabd.zza(zzl()) + zzb());
        }
    }

    public final int zzl() throws IOException {
        int i = this.zza;
        if (i == 0) {
            i = zza();
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
}
