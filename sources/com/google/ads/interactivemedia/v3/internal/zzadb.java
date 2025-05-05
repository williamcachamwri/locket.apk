package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzadb implements zzafl {
    private final zzada zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzadb(zzada zzada) {
        byte[] bArr = zzaee.zzb;
        zzada zzada2 = zzada;
        this.zza = zzada;
        zzada.zzc = this;
    }

    private final void zzO(Object obj, zzaft zzaft, zzadk zzadk) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            zzaft.zzh(obj, this, zzadk);
            if (this.zzb != this.zzc) {
                throw new zzaeg("Failed to parse the message.");
            }
        } finally {
            this.zzc = i;
        }
    }

    private final void zzP(Object obj, zzaft zzaft, zzadk zzadk) throws IOException {
        zzada zzada = this.zza;
        int zzm = zzada.zzm();
        if (zzada.zza < zzada.zzb) {
            int zzd2 = zzada.zzd(zzm);
            this.zza.zza++;
            zzaft.zzh(obj, this, zzadk);
            this.zza.zzy(0);
            zzada zzada2 = this.zza;
            zzada2.zza--;
            zzada2.zzz(zzd2);
            return;
        }
        throw new zzaeg("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
    }

    private final void zzQ(int i) throws IOException {
        if (this.zza.zzc() != i) {
            throw new zzaeg("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    private final void zzR(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw new zzaef("Protocol message tag had invalid wire type.");
        }
    }

    private static final void zzS(int i) throws IOException {
        if ((i & 3) != 0) {
            throw new zzaeg("Failed to parse the message.");
        }
    }

    private static final void zzT(int i) throws IOException {
        if ((i & 7) != 0) {
            throw new zzaeg("Failed to parse the message.");
        }
    }

    public static zzadb zzq(zzada zzada) {
        zzadb zzadb = zzada.zzc;
        return zzadb != null ? zzadb : new zzadb(zzada);
    }

    public final void zzA(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    zzaeq.zzf(this.zza.zzn());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                int zzm = this.zza.zzm();
                zzT(zzm);
                int zzc2 = zzm + this.zza.zzc();
                do {
                    zzaeq.zzf(this.zza.zzn());
                } while (this.zza.zzc() < zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzn()));
                    zzada zzada2 = this.zza;
                    if (!zzada2.zzA()) {
                        zzl = zzada2.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                int zzm2 = this.zza.zzm();
                zzT(zzm2);
                int zzc3 = zzm2 + this.zza.zzc();
                do {
                    list.add(Long.valueOf(this.zza.zzn()));
                } while (this.zza.zzc() < zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzB(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzadr) {
            zzadr zzadr = (zzadr) list;
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzm = this.zza.zzm();
                zzS(zzm);
                int zzc2 = this.zza.zzc() + zzm;
                do {
                    zzadr.zzf(this.zza.zzb());
                } while (this.zza.zzc() < zzc2);
                return;
            } else if (i2 == 5) {
                do {
                    zzadr.zzf(this.zza.zzb());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzm2 = this.zza.zzm();
                zzS(zzm2);
                int zzc3 = this.zza.zzc() + zzm2;
                do {
                    list.add(Float.valueOf(this.zza.zzb()));
                } while (this.zza.zzc() < zzc3);
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Float.valueOf(this.zza.zzb()));
                    zzada zzada2 = this.zza;
                    if (!zzada2.zzA()) {
                        zzl = zzada2.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    @Deprecated
    public final void zzC(List list, zzaft zzaft, zzadk zzadk) throws IOException {
        int zzl;
        int i = this.zzb;
        if ((i & 7) == 3) {
            do {
                Object zze = zzaft.zze();
                zzO(zze, zzaft, zzadk);
                zzaft.zzf(zze);
                list.add(zze);
                zzada zzada = this.zza;
                if (!zzada.zzA() && this.zzd == 0) {
                    zzl = zzada.zzl();
                } else {
                    return;
                }
            } while (zzl == i);
            this.zzd = zzl;
            return;
        }
        throw new zzaef("Protocol message tag had invalid wire type.");
    }

    public final void zzD(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzadz.zzg(this.zza.zzg());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzada zzada2 = this.zza;
                int zzc2 = zzada2.zzc() + zzada2.zzm();
                do {
                    zzadz.zzg(this.zza.zzg());
                } while (this.zza.zzc() < zzc2);
                zzQ(zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                    zzada zzada3 = this.zza;
                    if (!zzada3.zzA()) {
                        zzl = zzada3.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                zzada zzada4 = this.zza;
                int zzc3 = zzada4.zzc() + zzada4.zzm();
                do {
                    list.add(Integer.valueOf(this.zza.zzg()));
                } while (this.zza.zzc() < zzc3);
                zzQ(zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzE(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzaeq.zzf(this.zza.zzo());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzada zzada2 = this.zza;
                int zzc2 = zzada2.zzc() + zzada2.zzm();
                do {
                    zzaeq.zzf(this.zza.zzo());
                } while (this.zza.zzc() < zzc2);
                zzQ(zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                    zzada zzada3 = this.zza;
                    if (!zzada3.zzA()) {
                        zzl = zzada3.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                zzada zzada4 = this.zza;
                int zzc3 = zzada4.zzc() + zzada4.zzm();
                do {
                    list.add(Long.valueOf(this.zza.zzo()));
                } while (this.zza.zzc() < zzc3);
                zzQ(zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzF(List list, zzaft zzaft, zzadk zzadk) throws IOException {
        int zzl;
        int i = this.zzb;
        if ((i & 7) == 2) {
            do {
                Object zze = zzaft.zze();
                zzP(zze, zzaft, zzadk);
                zzaft.zzf(zze);
                list.add(zze);
                zzada zzada = this.zza;
                if (!zzada.zzA() && this.zzd == 0) {
                    zzl = zzada.zzl();
                } else {
                    return;
                }
            } while (zzl == i);
            this.zzd = zzl;
            return;
        }
        throw new zzaef("Protocol message tag had invalid wire type.");
    }

    public final void zzG(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzm = this.zza.zzm();
                zzS(zzm);
                int zzc2 = this.zza.zzc() + zzm;
                do {
                    zzadz.zzg(this.zza.zzj());
                } while (this.zza.zzc() < zzc2);
                return;
            } else if (i2 == 5) {
                do {
                    zzadz.zzg(this.zza.zzj());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzm2 = this.zza.zzm();
                zzS(zzm2);
                int zzc3 = this.zza.zzc() + zzm2;
                do {
                    list.add(Integer.valueOf(this.zza.zzj()));
                } while (this.zza.zzc() < zzc3);
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Integer.valueOf(this.zza.zzj()));
                    zzada zzada2 = this.zza;
                    if (!zzada2.zzA()) {
                        zzl = zzada2.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzH(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    zzaeq.zzf(this.zza.zzs());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                int zzm = this.zza.zzm();
                zzT(zzm);
                int zzc2 = zzm + this.zza.zzc();
                do {
                    zzaeq.zzf(this.zza.zzs());
                } while (this.zza.zzc() < zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    list.add(Long.valueOf(this.zza.zzs()));
                    zzada zzada2 = this.zza;
                    if (!zzada2.zzA()) {
                        zzl = zzada2.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                int zzm2 = this.zza.zzm();
                zzT(zzm2);
                int zzc3 = zzm2 + this.zza.zzc();
                do {
                    list.add(Long.valueOf(this.zza.zzs()));
                } while (this.zza.zzc() < zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzI(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzadz.zzg(this.zza.zzk());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzada zzada2 = this.zza;
                int zzc2 = zzada2.zzc() + zzada2.zzm();
                do {
                    zzadz.zzg(this.zza.zzk());
                } while (this.zza.zzc() < zzc2);
                zzQ(zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                    zzada zzada3 = this.zza;
                    if (!zzada3.zzA()) {
                        zzl = zzada3.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                zzada zzada4 = this.zza;
                int zzc3 = zzada4.zzc() + zzada4.zzm();
                do {
                    list.add(Integer.valueOf(this.zza.zzk()));
                } while (this.zza.zzc() < zzc3);
                zzQ(zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzJ(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzaeq.zzf(this.zza.zzt());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzada zzada2 = this.zza;
                int zzc2 = zzada2.zzc() + zzada2.zzm();
                do {
                    zzaeq.zzf(this.zza.zzt());
                } while (this.zza.zzc() < zzc2);
                zzQ(zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzt()));
                    zzada zzada3 = this.zza;
                    if (!zzada3.zzA()) {
                        zzl = zzada3.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                zzada zzada4 = this.zza;
                int zzc3 = zzada4.zzc() + zzada4.zzm();
                do {
                    list.add(Long.valueOf(this.zza.zzt()));
                } while (this.zza.zzc() < zzc3);
                zzQ(zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzK(List list, boolean z) throws IOException {
        int zzl;
        int i;
        if ((this.zzb & 7) == 2) {
            if ((list instanceof zzaen) && !z) {
                zzaen zzaen = (zzaen) list;
                do {
                    zzp();
                    zzaen.zza();
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                do {
                    list.add(z ? zzs() : zzr());
                    zzada zzada2 = this.zza;
                    if (!zzada2.zzA()) {
                        zzl = zzada2.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            }
            this.zzd = i;
            return;
        }
        throw new zzaef("Protocol message tag had invalid wire type.");
    }

    public final void zzL(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzadz.zzg(this.zza.zzm());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzada zzada2 = this.zza;
                int zzc2 = zzada2.zzc() + zzada2.zzm();
                do {
                    zzadz.zzg(this.zza.zzm());
                } while (this.zza.zzc() < zzc2);
                zzQ(zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zzm()));
                    zzada zzada3 = this.zza;
                    if (!zzada3.zzA()) {
                        zzl = zzada3.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                zzada zzada4 = this.zza;
                int zzc3 = zzada4.zzc() + zzada4.zzm();
                do {
                    list.add(Integer.valueOf(this.zza.zzm()));
                } while (this.zza.zzc() < zzc3);
                zzQ(zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzM(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzaeq) {
            zzaeq zzaeq = (zzaeq) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzaeq.zzf(this.zza.zzu());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzada zzada2 = this.zza;
                int zzc2 = zzada2.zzc() + zzada2.zzm();
                do {
                    zzaeq.zzf(this.zza.zzu());
                } while (this.zza.zzc() < zzc2);
                zzQ(zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Long.valueOf(this.zza.zzu()));
                    zzada zzada3 = this.zza;
                    if (!zzada3.zzA()) {
                        zzl = zzada3.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                zzada zzada4 = this.zza;
                int zzc3 = zzada4.zzc() + zzada4.zzm();
                do {
                    list.add(Long.valueOf(this.zza.zzu()));
                } while (this.zza.zzc() < zzc3);
                zzQ(zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final boolean zzN() throws IOException {
        zzR(0);
        return this.zza.zzB();
    }

    public final double zza() throws IOException {
        zzR(1);
        return this.zza.zza();
    }

    public final float zzb() throws IOException {
        zzR(5);
        return this.zza.zzb();
    }

    public final int zzc() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            i = this.zza.zzl();
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
        zzR(0);
        return this.zza.zze();
    }

    public final int zzf() throws IOException {
        zzR(5);
        return this.zza.zzf();
    }

    public final int zzg() throws IOException {
        zzR(0);
        return this.zza.zzg();
    }

    public final int zzh() throws IOException {
        zzR(5);
        return this.zza.zzj();
    }

    public final int zzi() throws IOException {
        zzR(0);
        return this.zza.zzk();
    }

    public final int zzj() throws IOException {
        zzR(0);
        return this.zza.zzm();
    }

    public final long zzk() throws IOException {
        zzR(1);
        return this.zza.zzn();
    }

    public final long zzl() throws IOException {
        zzR(0);
        return this.zza.zzo();
    }

    public final long zzm() throws IOException {
        zzR(1);
        return this.zza.zzs();
    }

    public final long zzn() throws IOException {
        zzR(0);
        return this.zza.zzt();
    }

    public final long zzo() throws IOException {
        zzR(0);
        return this.zza.zzu();
    }

    public final zzacw zzp() throws IOException {
        zzR(2);
        return this.zza.zzv();
    }

    public final String zzr() throws IOException {
        zzR(2);
        return this.zza.zzw();
    }

    public final String zzs() throws IOException {
        zzR(2);
        return this.zza.zzx();
    }

    public final void zzt(Object obj, zzaft zzaft, zzadk zzadk) throws IOException {
        zzR(3);
        zzO(obj, zzaft, zzadk);
    }

    public final void zzu(Object obj, zzaft zzaft, zzadk zzadk) throws IOException {
        zzR(2);
        zzP(obj, zzaft, zzadk);
    }

    public final void zzv(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzacn) {
            zzacn zzacn = (zzacn) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzacn.zze(this.zza.zzB());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzada zzada2 = this.zza;
                int zzc2 = zzada2.zzc() + zzada2.zzm();
                do {
                    zzacn.zze(this.zza.zzB());
                } while (this.zza.zzc() < zzc2);
                zzQ(zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zza.zzB()));
                    zzada zzada3 = this.zza;
                    if (!zzada3.zzA()) {
                        zzl = zzada3.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                zzada zzada4 = this.zza;
                int zzc3 = zzada4.zzc() + zzada4.zzm();
                do {
                    list.add(Boolean.valueOf(this.zza.zzB()));
                } while (this.zza.zzc() < zzc3);
                zzQ(zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzw(List list) throws IOException {
        int zzl;
        if ((this.zzb & 7) == 2) {
            do {
                list.add(zzp());
                zzada zzada = this.zza;
                if (!zzada.zzA()) {
                    zzl = zzada.zzl();
                } else {
                    return;
                }
            } while (zzl == this.zzb);
            this.zzd = zzl;
            return;
        }
        throw new zzaef("Protocol message tag had invalid wire type.");
    }

    public final void zzx(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzadh) {
            zzadh zzadh = (zzadh) list;
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    zzadh.zzf(this.zza.zza());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                int zzm = this.zza.zzm();
                zzT(zzm);
                int zzc2 = zzm + this.zza.zzc();
                do {
                    zzadh.zzf(this.zza.zza());
                } while (this.zza.zzc() < zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 1) {
                do {
                    list.add(Double.valueOf(this.zza.zza()));
                    zzada zzada2 = this.zza;
                    if (!zzada2.zzA()) {
                        zzl = zzada2.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                int zzm2 = this.zza.zzm();
                zzT(zzm2);
                int zzc3 = zzm2 + this.zza.zzc();
                do {
                    list.add(Double.valueOf(this.zza.zza()));
                } while (this.zza.zzc() < zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzy(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    zzadz.zzg(this.zza.zze());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else if (i2 == 2) {
                zzada zzada2 = this.zza;
                int zzc2 = zzada2.zzc() + zzada2.zzm();
                do {
                    zzadz.zzg(this.zza.zze());
                } while (this.zza.zzc() < zzc2);
                zzQ(zzc2);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 0) {
                do {
                    list.add(Integer.valueOf(this.zza.zze()));
                    zzada zzada3 = this.zza;
                    if (!zzada3.zzA()) {
                        zzl = zzada3.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else if (i3 == 2) {
                zzada zzada4 = this.zza;
                int zzc3 = zzada4.zzc() + zzada4.zzm();
                do {
                    list.add(Integer.valueOf(this.zza.zze()));
                } while (this.zza.zzc() < zzc3);
                zzQ(zzc3);
                return;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }

    public final void zzz(List list) throws IOException {
        int i;
        int zzl;
        if (list instanceof zzadz) {
            zzadz zzadz = (zzadz) list;
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zzm = this.zza.zzm();
                zzS(zzm);
                int zzc2 = this.zza.zzc() + zzm;
                do {
                    zzadz.zzg(this.zza.zzf());
                } while (this.zza.zzc() < zzc2);
                return;
            } else if (i2 == 5) {
                do {
                    zzadz.zzg(this.zza.zzf());
                    zzada zzada = this.zza;
                    if (!zzada.zzA()) {
                        i = zzada.zzl();
                    } else {
                        return;
                    }
                } while (i == this.zzb);
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        } else {
            int i3 = this.zzb & 7;
            if (i3 == 2) {
                int zzm2 = this.zza.zzm();
                zzS(zzm2);
                int zzc3 = this.zza.zzc() + zzm2;
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                } while (this.zza.zzc() < zzc3);
                return;
            } else if (i3 == 5) {
                do {
                    list.add(Integer.valueOf(this.zza.zzf()));
                    zzada zzada2 = this.zza;
                    if (!zzada2.zzA()) {
                        zzl = zzada2.zzl();
                    } else {
                        return;
                    }
                } while (zzl == this.zzb);
                i = zzl;
            } else {
                throw new zzaef("Protocol message tag had invalid wire type.");
            }
        }
        this.zzd = i;
    }
}
