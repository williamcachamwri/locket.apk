package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzll implements zzox {
    private final zzlk zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzll(zzlk zzlk) {
        byte[] bArr = zznn.zzb;
        zzlk zzlk2 = zzlk;
        this.zza = zzlk;
        zzlk.zzc = this;
    }

    private final void zzP(Object obj, zzoy zzoy, zzmq zzmq) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            zzoy.zzh(obj, this, zzmq);
            if (this.zzb != this.zzc) {
                throw new zznp("Failed to parse the message.");
            }
        } finally {
            this.zzc = i;
        }
    }

    private final void zzQ(Object obj, zzoy zzoy, zzmq zzmq) throws IOException {
        zzlk zzlk = this.zza;
        int zzn = zzlk.zzn();
        if (zzlk.zza < zzlk.zzb) {
            int zze = zzlk.zze(zzn);
            this.zza.zza++;
            zzoy.zzh(obj, this, zzmq);
            this.zza.zzz(0);
            zzlk zzlk2 = this.zza;
            zzlk2.zza--;
            zzlk2.zzA(zze);
            return;
        }
        throw new zznp("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
    }

    private final void zzR(int i) throws IOException {
        if (this.zza.zzd() != i) {
            throw new zznp("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    private final void zzS(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw new zzno("Protocol message tag had invalid wire type.");
        }
    }

    private static final void zzT(int i) throws IOException {
        if ((i & 3) != 0) {
            throw new zznp("Failed to parse the message.");
        }
    }

    private static final void zzU(int i) throws IOException {
        if ((i & 7) != 0) {
            throw new zznp("Failed to parse the message.");
        }
    }

    public static zzll zzq(zzlk zzlk) {
        zzll zzll = zzlk.zzc;
        return zzll != null ? zzll : new zzll(zzlk);
    }

    public final void zzA(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    zznz.zzg(this.zza.zzo());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzU(zzn);
                int zzd2 = zzn + this.zza.zzd();
                do {
                    zznz.zzg(this.zza.zzo());
                } while (this.zza.zzd() < zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzU(zzn2);
                int zzd3 = zzn2 + this.zza.zzd();
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzB(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzmx) {
            zzmx zzmx = (zzmx) list;
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzT(zzn);
                int zzd2 = this.zza.zzd() + zzn;
                do {
                    zzmx.zzf(this.zza.zzc());
                } while (this.zza.zzd() < zzd2);
                return;
            } else if (i2 == 5) {
                do {
                    zzmx.zzf(this.zza.zzc());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzT(zzn2);
                int zzd3 = this.zza.zzd() + zzn2;
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Float.valueOf(this.zza.zzc()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    @Deprecated
    public final void zzC(List list, zzoy zzoy, zzmq zzmq) throws IOException {
        int zzm;
        int i = this.zzb;
        if ((i & 7) == 3) {
            do {
                Object zze = zzoy.zze();
                zzP(zze, zzoy, zzmq);
                zzoy.zzf(zze);
                list.add(zze);
                if (!this.zza.zzC() && this.zzd == 0) {
                    zzm = this.zza.zzm();
                } else {
                    return;
                }
            } while (zzm == i);
            this.zzd = zzm;
            return;
        }
        throw new zzno("Protocol message tag had invalid wire type.");
    }

    public final void zzD(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzng.zzh(this.zza.zzh());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzlk zzlk = this.zza;
                int zzd2 = zzlk.zzd() + zzlk.zzn();
                do {
                    zzng.zzh(this.zza.zzh());
                } while (this.zza.zzd() < zzd2);
                zzR(zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzlk zzlk2 = this.zza;
                int zzd3 = zzlk2.zzd() + zzlk2.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzh()));
                } while (this.zza.zzd() < zzd3);
                zzR(zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzE(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zznz.zzg(this.zza.zzp());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzlk zzlk = this.zza;
                int zzd2 = zzlk.zzd() + zzlk.zzn();
                do {
                    zznz.zzg(this.zza.zzp());
                } while (this.zza.zzd() < zzd2);
                zzR(zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzlk zzlk2 = this.zza;
                int zzd3 = zzlk2.zzd() + zzlk2.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzp()));
                } while (this.zza.zzd() < zzd3);
                zzR(zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzF(List list, zzoy zzoy, zzmq zzmq) throws IOException {
        int zzm;
        int i = this.zzb;
        if ((i & 7) == 2) {
            do {
                Object zze = zzoy.zze();
                zzQ(zze, zzoy, zzmq);
                zzoy.zzf(zze);
                list.add(zze);
                if (!this.zza.zzC() && this.zzd == 0) {
                    zzm = this.zza.zzm();
                } else {
                    return;
                }
            } while (zzm == i);
            this.zzd = zzm;
            return;
        }
        throw new zzno("Protocol message tag had invalid wire type.");
    }

    public final void zzG(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzT(zzn);
                int zzd2 = this.zza.zzd() + zzn;
                do {
                    zzng.zzh(this.zza.zzk());
                } while (this.zza.zzd() < zzd2);
                return;
            } else if (i2 == 5) {
                do {
                    zzng.zzh(this.zza.zzk());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzT(zzn2);
                int zzd3 = this.zza.zzd() + zzn2;
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzH(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    zznz.zzg(this.zza.zzt());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzU(zzn);
                int zzd2 = zzn + this.zza.zzd();
                do {
                    zznz.zzg(this.zza.zzt());
                } while (this.zza.zzd() < zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzt()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzU(zzn2);
                int zzd3 = zzn2 + this.zza.zzd();
                do {
                    list.add(Long.valueOf(this.zza.zzt()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzI(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzng.zzh(this.zza.zzl());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzlk zzlk = this.zza;
                int zzd2 = zzlk.zzd() + zzlk.zzn();
                do {
                    zzng.zzh(this.zza.zzl());
                } while (this.zza.zzd() < zzd2);
                zzR(zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzl()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzlk zzlk2 = this.zza;
                int zzd3 = zzlk2.zzd() + zzlk2.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzl()));
                } while (this.zza.zzd() < zzd3);
                zzR(zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzJ(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zznz.zzg(this.zza.zzu());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzlk zzlk = this.zza;
                int zzd2 = zzlk.zzd() + zzlk.zzn();
                do {
                    zznz.zzg(this.zza.zzu());
                } while (this.zza.zzd() < zzd2);
                zzR(zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzu()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzlk zzlk2 = this.zza;
                int zzd3 = zzlk2.zzd() + zzlk2.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzu()));
                } while (this.zza.zzd() < zzd3);
                zzR(zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzK(List list, boolean z) throws IOException {
        int zzm;
        int i;
        if ((this.zzb & 7) == 2) {
            if ((list instanceof zznw) && !z) {
                zznw zznw = (zznw) list;
                do {
                    zzp();
                    zznw.zzb();
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                do {
                    list.add(z ? zzs() : zzr());
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            }
            this.zzd = i;
            return;
        }
        throw new zzno("Protocol message tag had invalid wire type.");
    }

    public final void zzL(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzng.zzh(this.zza.zzn());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzlk zzlk = this.zza;
                int zzd2 = zzlk.zzd() + zzlk.zzn();
                do {
                    zzng.zzh(this.zza.zzn());
                } while (this.zza.zzd() < zzd2);
                zzR(zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzn()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzlk zzlk2 = this.zza;
                int zzd3 = zzlk2.zzd() + zzlk2.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzn()));
                } while (this.zza.zzd() < zzd3);
                zzR(zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzM(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zznz) {
            zznz zznz = (zznz) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zznz.zzg(this.zza.zzv());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzlk zzlk = this.zza;
                int zzd2 = zzlk.zzd() + zzlk.zzn();
                do {
                    zznz.zzg(this.zza.zzv());
                } while (this.zza.zzd() < zzd2);
                zzR(zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzv()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzlk zzlk2 = this.zza;
                int zzd3 = zzlk2.zzd() + zzlk2.zzn();
                do {
                    list.add(Long.valueOf(this.zza.zzv()));
                } while (this.zza.zzd() < zzd3);
                zzR(zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final boolean zzN() throws IOException {
        zzS(0);
        return this.zza.zzD();
    }

    public final boolean zzO() throws IOException {
        int i;
        if (this.zza.zzC() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return this.zza.zzE(i);
    }

    public final double zza() throws IOException {
        zzS(1);
        return this.zza.zzb();
    }

    public final float zzb() throws IOException {
        zzS(5);
        return this.zza.zzc();
    }

    public final int zzc() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            i = this.zza.zzm();
            this.zzb = i;
        }
        if (i == 0 || i == this.zzc) {
            return Integer.MAX_VALUE;
        }
        return i >>> 3;
    }

    public final int zzd() {
        return this.zzb;
    }

    public final int zze() throws IOException {
        zzS(0);
        return this.zza.zzf();
    }

    public final int zzf() throws IOException {
        zzS(5);
        return this.zza.zzg();
    }

    public final int zzg() throws IOException {
        zzS(0);
        return this.zza.zzh();
    }

    public final int zzh() throws IOException {
        zzS(5);
        return this.zza.zzk();
    }

    public final int zzi() throws IOException {
        zzS(0);
        return this.zza.zzl();
    }

    public final int zzj() throws IOException {
        zzS(0);
        return this.zza.zzn();
    }

    public final long zzk() throws IOException {
        zzS(1);
        return this.zza.zzo();
    }

    public final long zzl() throws IOException {
        zzS(0);
        return this.zza.zzp();
    }

    public final long zzm() throws IOException {
        zzS(1);
        return this.zza.zzt();
    }

    public final long zzn() throws IOException {
        zzS(0);
        return this.zza.zzu();
    }

    public final long zzo() throws IOException {
        zzS(0);
        return this.zza.zzv();
    }

    public final zzlg zzp() throws IOException {
        zzS(2);
        return this.zza.zzw();
    }

    public final String zzr() throws IOException {
        zzS(2);
        return this.zza.zzx();
    }

    public final String zzs() throws IOException {
        zzS(2);
        return this.zza.zzy();
    }

    public final void zzt(Object obj, zzoy zzoy, zzmq zzmq) throws IOException {
        zzS(3);
        zzP(obj, zzoy, zzmq);
    }

    public final void zzu(Object obj, zzoy zzoy, zzmq zzmq) throws IOException {
        zzS(2);
        zzQ(obj, zzoy, zzmq);
    }

    public final void zzv(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzkx) {
            zzkx zzkx = (zzkx) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzkx.zze(this.zza.zzD());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzlk zzlk = this.zza;
                int zzd2 = zzlk.zzd() + zzlk.zzn();
                do {
                    zzkx.zze(this.zza.zzD());
                } while (this.zza.zzd() < zzd2);
                zzR(zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zza.zzD()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzlk zzlk2 = this.zza;
                int zzd3 = zzlk2.zzd() + zzlk2.zzn();
                do {
                    list.add(Boolean.valueOf(this.zza.zzD()));
                } while (this.zza.zzd() < zzd3);
                zzR(zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzw(List list) throws IOException {
        int zzm;
        if ((this.zzb & 7) == 2) {
            do {
                list.add(zzp());
                if (!this.zza.zzC()) {
                    zzm = this.zza.zzm();
                } else {
                    return;
                }
            } while (zzm == this.zzb);
            this.zzd = zzm;
            return;
        }
        throw new zzno("Protocol message tag had invalid wire type.");
    }

    public final void zzx(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzmk) {
            zzmk zzmk = (zzmk) list;
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    zzmk.zzf(this.zza.zzb());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzU(zzn);
                int zzd2 = zzn + this.zza.zzd();
                do {
                    zzmk.zzf(this.zza.zzb());
                } while (this.zza.zzd() < zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    list.add(Double.valueOf(this.zza.zzb()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzU(zzn2);
                int zzd3 = zzn2 + this.zza.zzd();
                do {
                    list.add(Double.valueOf(this.zza.zzb()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzy(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzng.zzh(this.zza.zzf());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzlk zzlk = this.zza;
                int zzd2 = zzlk.zzd() + zzlk.zzn();
                do {
                    zzng.zzh(this.zza.zzf());
                } while (this.zza.zzd() < zzd2);
                zzR(zzd2);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else if (i3 == 2) {
                zzlk zzlk2 = this.zza;
                int zzd3 = zzlk2.zzd() + zzlk2.zzn();
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                } while (this.zza.zzd() < zzd3);
                zzR(zzd3);
                return;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzz(List list) throws IOException {
        int i;
        int zzm;
        if (list instanceof zzng) {
            zzng zzng = (zzng) list;
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzn = this.zza.zzn();
                zzT(zzn);
                int zzd2 = this.zza.zzd() + zzn;
                do {
                    zzng.zzh(this.zza.zzg());
                } while (this.zza.zzd() < zzd2);
                return;
            } else if (i2 == 5) {
                do {
                    zzng.zzh(this.zza.zzg());
                    if (!this.zza.zzC()) {
                        i = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzn2 = this.zza.zzn();
                zzT(zzn2);
                int zzd3 = this.zza.zzd() + zzn2;
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                } while (this.zza.zzd() < zzd3);
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                    if (!this.zza.zzC()) {
                        zzm = this.zza.zzm();
                    } else {
                        return;
                    }
                } while (zzm == this.zzb);
                i = zzm;
            } else {
                throw new zzno("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }
}
