package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.io.FilenameUtils;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzyz extends zzacc {
    private static final Reader zzb = new zzyy();
    private static final Object zzc = new Object();
    private Object[] zzd = new Object[32];
    private int zze = 0;
    private String[] zzf = new String[32];
    private int[] zzg = new int[32];

    public zzyz(zzvw zzvw) {
        super(zzb);
        zzD(zzvw);
    }

    private final String zzA(boolean z) {
        StringBuilder sb = new StringBuilder("$");
        int i = 0;
        while (true) {
            int i2 = this.zze;
            if (i >= i2) {
                return sb.toString();
            }
            Object[] objArr = this.zzd;
            Object obj = objArr[i];
            if (obj instanceof zzvu) {
                i++;
                if (i < i2 && (objArr[i] instanceof Iterator)) {
                    int i3 = this.zzg[i];
                    if (z && i3 > 0 && (i == i2 - 1 || i == i2 - 2)) {
                        i3--;
                    }
                    sb.append(AbstractJsonLexerKt.BEGIN_LIST);
                    sb.append(i3);
                    sb.append(AbstractJsonLexerKt.END_LIST);
                }
            } else if ((obj instanceof zzvz) && (i = i + 1) < i2 && (objArr[i] instanceof Iterator)) {
                sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                String str = this.zzf[i];
                if (str != null) {
                    sb.append(str);
                }
            }
            i++;
        }
    }

    private final String zzB() {
        return " at path ".concat(zzA(false));
    }

    private final String zzC(boolean z) throws IOException {
        zzE(5);
        Map.Entry entry = (Map.Entry) ((Iterator) zzy()).next();
        String str = (String) entry.getKey();
        this.zzf[this.zze - 1] = true != z ? str : "<skipped>";
        zzD(entry.getValue());
        return str;
    }

    private final void zzD(Object obj) {
        int i = this.zze;
        Object[] objArr = this.zzd;
        if (i == objArr.length) {
            int i2 = i + i;
            this.zzd = Arrays.copyOf(objArr, i2);
            this.zzg = Arrays.copyOf(this.zzg, i2);
            this.zzf = (String[]) Arrays.copyOf(this.zzf, i2);
        }
        Object[] objArr2 = this.zzd;
        int i3 = this.zze;
        this.zze = i3 + 1;
        objArr2[i3] = obj;
    }

    private final void zzE(int i) throws IOException {
        if (zzr() != i) {
            String zza = zzacd.zza(zzr());
            String zzB = zzB();
            throw new IllegalStateException("Expected " + zzacd.zza(i) + " but was " + zza + zzB);
        }
    }

    private final Object zzy() {
        return this.zzd[this.zze - 1];
    }

    private final Object zzz() {
        Object[] objArr = this.zzd;
        int i = this.zze - 1;
        this.zze = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    public final void close() throws IOException {
        this.zzd = new Object[]{zzc};
        this.zze = 1;
    }

    public final String toString() {
        return String.valueOf(getClass().getSimpleName()).concat(zzB());
    }

    public final double zza() throws IOException {
        int zzr = zzr();
        if (zzr == 7 || zzr == 6) {
            double zza = ((zzwb) zzy()).zza();
            if (zzx() || (!Double.isNaN(zza) && !Double.isInfinite(zza))) {
                zzz();
                int i = this.zze;
                if (i > 0) {
                    int[] iArr = this.zzg;
                    int i2 = i - 1;
                    iArr[i2] = iArr[i2] + 1;
                }
                return zza;
            }
            throw new zzacf("JSON forbids NaN and infinities: " + zza);
        }
        throw new IllegalStateException("Expected NUMBER but was " + zzacd.zza(zzr) + zzB());
    }

    public final int zzb() throws IOException {
        int zzr = zzr();
        if (zzr == 7 || zzr == 6) {
            int zzb2 = ((zzwb) zzy()).zzb();
            zzz();
            int i = this.zze;
            if (i > 0) {
                int[] iArr = this.zzg;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return zzb2;
        }
        throw new IllegalStateException("Expected NUMBER but was " + zzacd.zza(zzr) + zzB());
    }

    public final long zzc() throws IOException {
        int zzr = zzr();
        if (zzr == 7 || zzr == 6) {
            long zzc2 = ((zzwb) zzy()).zzc();
            zzz();
            int i = this.zze;
            if (i > 0) {
                int[] iArr = this.zzg;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return zzc2;
        }
        throw new IllegalStateException("Expected NUMBER but was " + zzacd.zza(zzr) + zzB());
    }

    /* access modifiers changed from: package-private */
    public final zzvw zzd() throws IOException {
        int zzr = zzr();
        if (zzr == 5 || zzr == 2 || zzr == 4 || zzr == 10) {
            throw new IllegalStateException("Unexpected " + zzacd.zza(zzr) + " when reading a JsonElement.");
        }
        zzvw zzvw = (zzvw) zzy();
        zzo();
        return zzvw;
    }

    public final String zze() {
        return zzA(false);
    }

    public final String zzf() {
        return zzA(true);
    }

    public final String zzg() throws IOException {
        return zzC(false);
    }

    public final String zzh() throws IOException {
        int zzr = zzr();
        if (zzr == 6 || zzr == 7) {
            String zze2 = ((zzwb) zzz()).zze();
            int i = this.zze;
            if (i > 0) {
                int[] iArr = this.zzg;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return zze2;
        }
        throw new IllegalStateException("Expected STRING but was " + zzacd.zza(zzr) + zzB());
    }

    public final void zzi() throws IOException {
        zzE(1);
        zzD(((zzvu) zzy()).iterator());
        this.zzg[this.zze - 1] = 0;
    }

    public final void zzj() throws IOException {
        zzE(3);
        zzD(((zzvz) zzy()).zza().iterator());
    }

    public final void zzk() throws IOException {
        zzE(2);
        zzz();
        zzz();
        int i = this.zze;
        if (i > 0) {
            int[] iArr = this.zzg;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    public final void zzl() throws IOException {
        zzE(4);
        this.zzf[this.zze - 1] = null;
        zzz();
        zzz();
        int i = this.zze;
        if (i > 0) {
            int[] iArr = this.zzg;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    public final void zzm() throws IOException {
        zzE(9);
        zzz();
        int i = this.zze;
        if (i > 0) {
            int[] iArr = this.zzg;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    public final void zzn() throws IOException {
        zzE(5);
        Map.Entry entry = (Map.Entry) ((Iterator) zzy()).next();
        zzD(entry.getValue());
        zzD(new zzwb((String) entry.getKey()));
    }

    public final void zzo() throws IOException {
        int zzr = zzr() - 1;
        if (zzr == 1) {
            zzk();
        } else if (zzr == 9) {
        } else {
            if (zzr == 3) {
                zzl();
            } else if (zzr != 4) {
                zzz();
                int i = this.zze;
                if (i > 0) {
                    int[] iArr = this.zzg;
                    int i2 = i - 1;
                    iArr[i2] = iArr[i2] + 1;
                }
            } else {
                zzC(true);
            }
        }
    }

    public final boolean zzp() throws IOException {
        int zzr = zzr();
        return (zzr == 4 || zzr == 2 || zzr == 10) ? false : true;
    }

    public final boolean zzq() throws IOException {
        zzE(8);
        boolean zzh = ((zzwb) zzz()).zzh();
        int i = this.zze;
        if (i > 0) {
            int[] iArr = this.zzg;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return zzh;
    }

    public final int zzr() throws IOException {
        if (this.zze == 0) {
            return 10;
        }
        Object zzy = zzy();
        if (zzy instanceof Iterator) {
            boolean z = this.zzd[this.zze - 2] instanceof zzvz;
            Iterator it = (Iterator) zzy;
            if (!it.hasNext()) {
                return z ? 4 : 2;
            }
            if (z) {
                return 5;
            }
            zzD(it.next());
            return zzr();
        } else if (zzy instanceof zzvz) {
            return 3;
        } else {
            if (zzy instanceof zzvu) {
                return 1;
            }
            if (zzy instanceof zzwb) {
                zzwb zzwb = (zzwb) zzy;
                if (zzwb.zzk()) {
                    return 6;
                }
                if (zzwb.zzi()) {
                    return 8;
                }
                if (zzwb.zzj()) {
                    return 7;
                }
                throw new AssertionError();
            } else if (zzy instanceof zzvy) {
                return 9;
            } else {
                if (zzy == zzc) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                String name = zzy.getClass().getName();
                throw new zzacf("Custom JsonElement subclass " + name + " is not supported");
            }
        }
    }
}
