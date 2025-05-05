package com.google.android.gms.internal.pal;

import androidx.media3.common.C;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public final class zzaf extends zzacz implements zzaeg {
    /* access modifiers changed from: private */
    public static final zzaf zzb;
    private long zzA;
    private long zzB;
    private long zzC;
    private String zzD = "";
    private String zzE = "D";
    private String zzF = "";
    private long zzG;
    private long zzH;
    private long zzI;
    private String zzJ = "";
    private long zzK;
    private long zzL = -1;
    private long zzM = -1;
    private zzah zzN;
    private long zzO = -1;
    private long zzP = -1;
    private long zzQ = -1;
    private long zzR = -1;
    private long zzS = -1;
    private long zzT = -1;
    private String zzU = "D";
    private String zzV = "D";
    private long zzW = -1;
    private int zzX = 1000;
    private int zzY = 1000;
    private long zzZ = -1;
    private zzadf zzaA = zzaz();
    private int zzaB = 1000;
    private zzadf zzaC = zzaz();
    private zzaa zzaD;
    private String zzaE = "";
    private long zzaF = -1;
    private long zzaG = -1;
    private long zzaH = -1;
    private long zzaI = -1;
    private long zzaJ;
    private long zzaK = -1;
    private String zzaL = "";
    private long zzaM;
    private String zzaN = "";
    private int zzaO = 2;
    private boolean zzaP;
    private String zzaQ = "";
    private long zzaR;
    private zzaq zzaS;
    private long zzaT;
    private String zzaU = "";
    private long zzaa = -1;
    private long zzab = -1;
    private long zzac = -1;
    private long zzad = -1;
    private int zzae = 1000;
    private zzac zzaf;
    /* access modifiers changed from: private */
    public zzadf zzag = zzaz();
    private zzae zzah;
    private long zzai = -1;
    private long zzaj = -1;
    private long zzak = -1;
    private long zzal = -1;
    private long zzam = -1;
    private long zzan = -1;
    private long zzao = -1;
    private long zzap = -1;
    private String zzaq = "D";
    private long zzar = -1;
    private int zzas;
    private int zzat;
    private int zzau;
    private zzas zzav;
    private long zzaw = -1;
    private int zzax = 1000;
    private int zzay = 1000;
    private String zzaz = "D";
    private int zze;
    private int zzf;
    private int zzg;
    private String zzh = "";
    private String zzi = "";
    private long zzj;
    private long zzk;
    private long zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private long zzp;
    private long zzq;
    private long zzr;
    private long zzs;
    private String zzt = "";
    private long zzu;
    private long zzv;
    private long zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    static {
        zzaf zzaf2 = new zzaf();
        zzb = zzaf2;
        zzacz.zzaF(zzaf.class, zzaf2);
    }

    private zzaf() {
    }

    static /* synthetic */ void zzA(zzaf zzaf2, long j) {
        zzaf2.zze |= C.BUFFER_FLAG_LAST_SAMPLE;
        zzaf2.zzK = j;
    }

    static /* synthetic */ void zzB(zzaf zzaf2, long j) {
        zzaf2.zze |= 1073741824;
        zzaf2.zzL = j;
    }

    static /* synthetic */ void zzC(zzaf zzaf2, long j) {
        zzaf2.zze |= Integer.MIN_VALUE;
        zzaf2.zzM = j;
    }

    static /* synthetic */ void zzD(zzaf zzaf2, long j) {
        zzaf2.zzf |= 2;
        zzaf2.zzO = j;
    }

    static /* synthetic */ void zzE(zzaf zzaf2, long j) {
        zzaf2.zzf |= 4;
        zzaf2.zzP = j;
    }

    static /* synthetic */ void zzF(zzaf zzaf2, long j) {
        zzaf2.zzf |= 8;
        zzaf2.zzQ = j;
    }

    static /* synthetic */ void zzG(zzaf zzaf2, long j) {
        zzaf2.zzf |= 16;
        zzaf2.zzR = j;
    }

    static /* synthetic */ void zzH(zzaf zzaf2, long j) {
        zzaf2.zzf |= 32;
        zzaf2.zzS = j;
    }

    static /* synthetic */ void zzI(zzaf zzaf2, long j) {
        zzaf2.zzf |= 64;
        zzaf2.zzT = j;
    }

    static /* synthetic */ void zzJ(zzaf zzaf2, String str) {
        str.getClass();
        zzaf2.zzf |= 128;
        zzaf2.zzU = str;
    }

    static /* synthetic */ void zzK(zzaf zzaf2, String str) {
        str.getClass();
        zzaf2.zzf |= 256;
        zzaf2.zzV = str;
    }

    static /* synthetic */ void zzL(zzaf zzaf2, long j) {
        zzaf2.zzf |= 4096;
        zzaf2.zzZ = j;
    }

    static /* synthetic */ void zzM(zzaf zzaf2, long j) {
        zzaf2.zzf |= 8192;
        zzaf2.zzaa = j;
    }

    static /* synthetic */ void zzN(zzaf zzaf2, long j) {
        zzaf2.zzf |= 16384;
        zzaf2.zzab = j;
    }

    static /* synthetic */ void zzO(zzaf zzaf2, zzac zzac2) {
        zzac2.getClass();
        zzaf2.zzaf = zzac2;
        zzaf2.zzf |= 262144;
    }

    static /* synthetic */ void zzP(zzaf zzaf2, zzac zzac2) {
        zzac2.getClass();
        zzadf zzadf = zzaf2.zzag;
        if (!zzadf.zzc()) {
            zzaf2.zzag = zzacz.zzaA(zzadf);
        }
        zzaf2.zzag.add(zzac2);
    }

    static /* synthetic */ void zzR(zzaf zzaf2, zzae zzae2) {
        zzae2.getClass();
        zzaf2.zzah = zzae2;
        zzaf2.zzf |= 524288;
    }

    static /* synthetic */ void zzS(zzaf zzaf2, long j) {
        zzaf2.zzf |= 2097152;
        zzaf2.zzaj = j;
    }

    static /* synthetic */ void zzT(zzaf zzaf2, long j) {
        zzaf2.zzf |= 4194304;
        zzaf2.zzak = j;
    }

    static /* synthetic */ void zzU(zzaf zzaf2, long j) {
        zzaf2.zzf |= 8388608;
        zzaf2.zzal = j;
    }

    static /* synthetic */ void zzV(zzaf zzaf2, long j) {
        zzaf2.zzf |= 67108864;
        zzaf2.zzao = j;
    }

    static /* synthetic */ void zzW(zzaf zzaf2, long j) {
        zzaf2.zzf |= C.BUFFER_FLAG_FIRST_SAMPLE;
        zzaf2.zzap = j;
    }

    static /* synthetic */ void zzX(zzaf zzaf2, String str) {
        str.getClass();
        zzaf2.zzf |= 268435456;
        zzaf2.zzaq = str;
    }

    static /* synthetic */ void zzY(zzaf zzaf2, zzas zzas2) {
        zzas2.getClass();
        zzaf2.zzav = zzas2;
        zzaf2.zzg |= 2;
    }

    static /* synthetic */ void zzZ(zzaf zzaf2, long j) {
        zzaf2.zzg |= 512;
        zzaf2.zzaF = j;
    }

    public static zzr zza() {
        return (zzr) zzb.zzau();
    }

    static /* synthetic */ void zzaa(zzaf zzaf2, long j) {
        zzaf2.zzg |= 1024;
        zzaf2.zzaG = j;
    }

    static /* synthetic */ void zzab(zzaf zzaf2, long j) {
        zzaf2.zzg |= 2048;
        zzaf2.zzaH = j;
    }

    static /* synthetic */ void zzac(zzaf zzaf2, long j) {
        zzaf2.zzg |= 4096;
        zzaf2.zzaI = j;
    }

    static /* synthetic */ void zzad(zzaf zzaf2, String str) {
        str.getClass();
        zzaf2.zzg |= 32768;
        zzaf2.zzaL = str;
    }

    static /* synthetic */ void zzae(zzaf zzaf2, String str) {
        str.getClass();
        zzaf2.zzg |= 131072;
        zzaf2.zzaN = str;
    }

    static /* synthetic */ void zzaf(zzaf zzaf2, boolean z) {
        zzaf2.zzg |= 524288;
        zzaf2.zzaP = z;
    }

    static /* synthetic */ void zzag(zzaf zzaf2, long j) {
        zzaf2.zzg |= 2097152;
        zzaf2.zzaR = j;
    }

    static /* synthetic */ void zzaj(zzaf zzaf2, int i) {
        zzaf2.zzX = i - 1;
        zzaf2.zzf |= 1024;
    }

    static /* synthetic */ void zzak(zzaf zzaf2, int i) {
        zzaf2.zzY = i - 1;
        zzaf2.zzf |= 2048;
    }

    static /* synthetic */ void zzal(zzaf zzaf2, int i) {
        zzaf2.zzae = i - 1;
        zzaf2.zzf |= 131072;
    }

    static /* synthetic */ void zzam(zzaf zzaf2, int i) {
        zzaf2.zzax = i - 1;
        zzaf2.zzg |= 8;
    }

    static /* synthetic */ void zzan(zzaf zzaf2, int i) {
        zzaf2.zzay = i - 1;
        zzaf2.zzg |= 16;
    }

    static /* synthetic */ void zzao(zzaf zzaf2, int i) {
        zzaf2.zzaO = 5;
        zzaf2.zzg |= 262144;
    }

    public static zzaf zzd(byte[] bArr, zzacm zzacm) throws zzadi {
        return (zzaf) zzacz.zzax(zzb, bArr, zzacm);
    }

    static /* synthetic */ void zzg(zzaf zzaf2, String str) {
        str.getClass();
        zzaf2.zze |= 1;
        zzaf2.zzh = str;
    }

    static /* synthetic */ void zzh(zzaf zzaf2, String str) {
        zzaf2.zze |= 2;
        zzaf2.zzi = str;
    }

    static /* synthetic */ void zzi(zzaf zzaf2, long j) {
        zzaf2.zze |= 4;
        zzaf2.zzj = j;
    }

    static /* synthetic */ void zzj(zzaf zzaf2, long j) {
        zzaf2.zze |= 16;
        zzaf2.zzl = j;
    }

    static /* synthetic */ void zzk(zzaf zzaf2, long j) {
        zzaf2.zze |= 32;
        zzaf2.zzm = j;
    }

    static /* synthetic */ void zzl(zzaf zzaf2, long j) {
        zzaf2.zze |= 1024;
        zzaf2.zzr = j;
    }

    static /* synthetic */ void zzm(zzaf zzaf2, long j) {
        zzaf2.zze |= 2048;
        zzaf2.zzs = j;
    }

    static /* synthetic */ void zzn(zzaf zzaf2, long j) {
        zzaf2.zze |= 8192;
        zzaf2.zzu = j;
    }

    static /* synthetic */ void zzo(zzaf zzaf2, long j) {
        zzaf2.zze |= 16384;
        zzaf2.zzv = j;
    }

    static /* synthetic */ void zzp(zzaf zzaf2, long j) {
        zzaf2.zze |= 32768;
        zzaf2.zzw = j;
    }

    static /* synthetic */ void zzq(zzaf zzaf2, long j) {
        zzaf2.zze |= 65536;
        zzaf2.zzx = j;
    }

    static /* synthetic */ void zzr(zzaf zzaf2, long j) {
        zzaf2.zze |= 524288;
        zzaf2.zzA = j;
    }

    static /* synthetic */ void zzs(zzaf zzaf2, long j) {
        zzaf2.zze |= 1048576;
        zzaf2.zzB = j;
    }

    static /* synthetic */ void zzt(zzaf zzaf2, long j) {
        zzaf2.zze |= 2097152;
        zzaf2.zzC = j;
    }

    static /* synthetic */ void zzu(zzaf zzaf2, String str) {
        str.getClass();
        zzaf2.zze |= 4194304;
        zzaf2.zzD = str;
    }

    static /* synthetic */ void zzv(zzaf zzaf2, String str) {
        str.getClass();
        zzaf2.zze |= 16777216;
        zzaf2.zzF = str;
    }

    static /* synthetic */ void zzw(zzaf zzaf2, long j) {
        zzaf2.zze |= 33554432;
        zzaf2.zzG = j;
    }

    static /* synthetic */ void zzx(zzaf zzaf2, long j) {
        zzaf2.zze |= 67108864;
        zzaf2.zzH = j;
    }

    static /* synthetic */ void zzy(zzaf zzaf2, long j) {
        zzaf2.zze |= C.BUFFER_FLAG_FIRST_SAMPLE;
        zzaf2.zzI = j;
    }

    static /* synthetic */ void zzz(zzaf zzaf2, String str) {
        str.getClass();
        zzaf2.zze |= 268435456;
        zzaf2.zzJ = str;
    }

    public final boolean zzah() {
        return (this.zze & 4194304) != 0;
    }

    public final boolean zzai() {
        return (this.zzg & 4194304) != 0;
    }

    /* access modifiers changed from: protected */
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            zzadd zzadd = zzan.zza;
            zzadd zzadd2 = zzadd;
            zzadd zzadd3 = zzadd;
            return zzaE(zzb, "\u0001\\\u0000\u0003\u0001Į\\\u0000\u0003\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဂ\u0007\tဂ\b\nဂ\t\u000bဂ\n\fဂ\u000b\rဈ\f\u000eဂ\r\u000fဂ\u000e\u0010ဂ\u000f\u0011ဂ\u0010\u0012ဂ\u0011\u0013ဂ\u0012\u0014ဂ\u0013\u0015ဂP\u0016ဂ\u0014\u0017ဂ\u0015\u0018ဈQ\u0019ဂU\u001aဌR\u001bဈ\u0016\u001cဇS\u001dဈ\u0018\u001eဈT\u001fဂ\u0019 ဂ\u001a!ဂ\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဂ\u001f&ဉ 'ဂ!(ဂ\")ဂ#*ဂ$+\u001b,ဂ%-ဂ&.ဈ'/ဈ(0ဌ*1ဌ+2ဉ23ဂ,4ဂ-5ဂ.6ဂ/7ဂ08ဌ19ဉ3:ဂ4;ဂ5<ဂ6=ဂ7>ဂ:?ဂ;@ဂ=Aဌ>Bဌ?Cဈ<Dဌ@EဉAFဂBGဂ8Hဂ9IဌCJဂ)Kဈ\u0017LဌDMဈEN\u001bOဌFP\u001bQဉGRဈHSဂITဂJUဂKVဂLWဂMXဂNYဈOÉဉVĭဂWĮဈX", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzaM", "zzB", "zzC", "zzaN", "zzaR", "zzaO", zzv.zza, "zzD", "zzaP", "zzF", "zzaQ", "zzG", "zzH", "zzI", "zzJ", "zzK", "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzag", zzac.class, "zzS", "zzT", "zzU", "zzV", "zzX", zzadd, "zzY", zzadd, "zzaf", "zzZ", "zzaa", "zzab", "zzac", "zzad", "zzae", zzadd, "zzah", "zzai", "zzaj", "zzak", "zzal", "zzao", "zzap", "zzar", "zzas", zzam.zza, "zzat", zzao.zza, "zzaq", "zzau", zzs.zza, "zzav", "zzaw", "zzam", "zzan", "zzax", zzadd2, "zzW", "zzE", "zzay", zzadd3, "zzaz", "zzaA", zzy.class, "zzaB", zzadd, "zzaC", zzu.class, "zzaD", "zzaE", "zzaF", "zzaG", "zzaH", "zzaI", "zzaJ", "zzaK", "zzaL", "zzaS", "zzaT", "zzaU"});
        } else if (i2 == 3) {
            return new zzaf();
        } else {
            if (i2 == 4) {
                return new zzr((zzq) null);
            }
            if (i2 != 5) {
                return null;
            }
            return zzb;
        }
    }

    public final zzaq zze() {
        zzaq zzaq2 = this.zzaS;
        return zzaq2 == null ? zzaq.zzd() : zzaq2;
    }

    public final String zzf() {
        return this.zzD;
    }
}
