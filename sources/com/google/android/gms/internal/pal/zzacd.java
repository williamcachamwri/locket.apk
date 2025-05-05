package com.google.android.gms.internal.pal;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
final class zzacd implements zzaeq {
    private final zzacc zza;
    private int zzb;
    private int zzc;
    private int zzd = 0;

    private zzacd(zzacc zzacc) {
        zzadg.zzf(zzacc, "input");
        this.zza = zzacc;
        zzacc.zzc = this;
    }

    private final Object zzP(zzaer zzaer, zzacm zzacm) throws IOException {
        int i = this.zzc;
        this.zzc = ((this.zzb >>> 3) << 3) | 4;
        try {
            Object zze = zzaer.zze();
            zzaer.zzh(zze, this, zzacm);
            zzaer.zzf(zze);
            if (this.zzb == this.zzc) {
                return zze;
            }
            throw zzadi.zzg();
        } finally {
            this.zzc = i;
        }
    }

    private final Object zzQ(zzaer zzaer, zzacm zzacm) throws IOException {
        int zze = ((zzaca) this.zza).zze();
        zzacc zzacc = this.zza;
        if (zzacc.zza < zzacc.zzb) {
            int zzc2 = zzacc.zzc(zze);
            Object zze2 = zzaer.zze();
            this.zza.zza++;
            zzaer.zzh(zze2, this, zzacm);
            zzaer.zzf(zze2);
            this.zza.zzm(0);
            zzacc zzacc2 = this.zza;
            zzacc2.zza--;
            zzacc2.zzn(zzc2);
            return zze2;
        }
        throw new zzadi("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final void zzR(int i) throws IOException {
        if (this.zza.zzb() != i) {
            throw zzadi.zzi();
        }
    }

    private final void zzS(int i) throws IOException {
        if ((this.zzb & 7) != i) {
            throw zzadi.zza();
        }
    }

    private static final void zzT(int i) throws IOException {
        if ((i & 3) != 0) {
            throw zzadi.zzg();
        }
    }

    private static final void zzU(int i) throws IOException {
        if ((i & 7) != 0) {
            throw zzadi.zzg();
        }
    }

    public static zzacd zzq(zzacc zzacc) {
        zzacd zzacd = zzacc.zzc;
        return zzacd != null ? zzacd : new zzacd(zzacc);
    }

    public final void zzA(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadu) {
            zzadu zzadu = (zzadu) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzadu.zzf(((zzaca) this.zza).zzg());
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zze = ((zzaca) this.zza).zze();
                zzU(zze);
                int zzb2 = this.zza.zzb() + zze;
                do {
                    zzadu.zzf(((zzaca) this.zza).zzg());
                } while (this.zza.zzb() < zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(((zzaca) this.zza).zzg()));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zze2 = ((zzaca) this.zza).zze();
                zzU(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Long.valueOf(((zzaca) this.zza).zzg()));
                } while (this.zza.zzb() < zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzB(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzact) {
            zzact zzact = (zzact) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int zze = ((zzaca) this.zza).zze();
                zzT(zze);
                int zzb2 = this.zza.zzb() + zze;
                do {
                    zzact.zze(Float.intBitsToFloat(((zzaca) this.zza).zzd()));
                } while (this.zza.zzb() < zzb2);
            } else if (i == 5) {
                do {
                    zzact.zze(Float.intBitsToFloat(((zzaca) this.zza).zzd()));
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zze2 = ((zzaca) this.zza).zze();
                zzT(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Float.valueOf(Float.intBitsToFloat(((zzaca) this.zza).zzd())));
                } while (this.zza.zzb() < zzb3);
            } else if (i2 == 5) {
                do {
                    list.add(Float.valueOf(Float.intBitsToFloat(((zzaca) this.zza).zzd())));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else {
                throw zzadi.zza();
            }
        }
    }

    @Deprecated
    public final void zzC(List list, zzaer zzaer, zzacm zzacm) throws IOException {
        int zzf;
        int i = this.zzb;
        if ((i & 7) == 3) {
            do {
                list.add(zzP(zzaer, zzacm));
                zzacc zzacc = this.zza;
                if (!zzacc.zzp() && this.zzd == 0) {
                    zzf = zzacc.zzf();
                } else {
                    return;
                }
            } while (zzf == i);
            this.zzd = zzf;
            return;
        }
        throw zzadi.zza();
    }

    public final void zzD(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzada) {
            zzada zzada = (zzada) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzada.zzg(((zzaca) this.zza).zze());
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zzb2 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    zzada.zzg(((zzaca) this.zza).zze());
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(((zzaca) this.zza).zze()));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zzb3 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzaca) this.zza).zze()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzE(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadu) {
            zzadu zzadu = (zzadu) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzadu.zzf(((zzaca) this.zza).zzh());
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zzb2 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    zzadu.zzf(((zzaca) this.zza).zzh());
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(((zzaca) this.zza).zzh()));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zzb3 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    list.add(Long.valueOf(((zzaca) this.zza).zzh()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzF(List list, zzaer zzaer, zzacm zzacm) throws IOException {
        int zzf;
        int i = this.zzb;
        if ((i & 7) == 2) {
            do {
                list.add(zzQ(zzaer, zzacm));
                zzacc zzacc = this.zza;
                if (!zzacc.zzp() && this.zzd == 0) {
                    zzf = zzacc.zzf();
                } else {
                    return;
                }
            } while (zzf == i);
            this.zzd = zzf;
            return;
        }
        throw zzadi.zza();
    }

    public final void zzG(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzada) {
            zzada zzada = (zzada) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int zze = ((zzaca) this.zza).zze();
                zzT(zze);
                int zzb2 = this.zza.zzb() + zze;
                do {
                    zzada.zzg(((zzaca) this.zza).zzd());
                } while (this.zza.zzb() < zzb2);
            } else if (i == 5) {
                do {
                    zzada.zzg(((zzaca) this.zza).zzd());
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zze2 = ((zzaca) this.zza).zze();
                zzT(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Integer.valueOf(((zzaca) this.zza).zzd()));
                } while (this.zza.zzb() < zzb3);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(((zzaca) this.zza).zzd()));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzH(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadu) {
            zzadu zzadu = (zzadu) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzadu.zzf(((zzaca) this.zza).zzg());
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zze = ((zzaca) this.zza).zze();
                zzU(zze);
                int zzb2 = this.zza.zzb() + zze;
                do {
                    zzadu.zzf(((zzaca) this.zza).zzg());
                } while (this.zza.zzb() < zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    list.add(Long.valueOf(((zzaca) this.zza).zzg()));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zze2 = ((zzaca) this.zza).zze();
                zzU(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Long.valueOf(((zzaca) this.zza).zzg()));
                } while (this.zza.zzb() < zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzI(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzada) {
            zzada zzada = (zzada) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzada.zzg(zzaca.zzs(((zzaca) this.zza).zze()));
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zzb2 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    zzada.zzg(zzaca.zzs(((zzaca) this.zza).zze()));
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(zzaca.zzs(((zzaca) this.zza).zze())));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zzb3 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    list.add(Integer.valueOf(zzaca.zzs(((zzaca) this.zza).zze())));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzJ(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadu) {
            zzadu zzadu = (zzadu) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzadu.zzf(zzaca.zzt(((zzaca) this.zza).zzh()));
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zzb2 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    zzadu.zzf(zzaca.zzt(((zzaca) this.zza).zzh()));
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(zzaca.zzt(((zzaca) this.zza).zzh())));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zzb3 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    list.add(Long.valueOf(zzaca.zzt(((zzaca) this.zza).zzh())));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzK(List list, boolean z) throws IOException {
        int zzf;
        int zzf2;
        if ((this.zzb & 7) != 2) {
            throw zzadi.zza();
        } else if ((list instanceof zzadn) && !z) {
            zzadn zzadn = (zzadn) list;
            do {
                zzadn.zzi(zzp());
                zzacc zzacc = this.zza;
                if (!zzacc.zzp()) {
                    zzf2 = zzacc.zzf();
                } else {
                    return;
                }
            } while (zzf2 == this.zzb);
            this.zzd = zzf2;
        } else {
            do {
                list.add(z ? zzu() : zzt());
                zzacc zzacc2 = this.zza;
                if (!zzacc2.zzp()) {
                    zzf = zzacc2.zzf();
                } else {
                    return;
                }
            } while (zzf == this.zzb);
            this.zzd = zzf;
        }
    }

    public final void zzL(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzada) {
            zzada zzada = (zzada) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzada.zzg(((zzaca) this.zza).zze());
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zzb2 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    zzada.zzg(((zzaca) this.zza).zze());
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(((zzaca) this.zza).zze()));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zzb3 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzaca) this.zza).zze()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzM(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzadu) {
            zzadu zzadu = (zzadu) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzadu.zzf(((zzaca) this.zza).zzh());
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zzb2 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    zzadu.zzf(((zzaca) this.zza).zzh());
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Long.valueOf(((zzaca) this.zza).zzh()));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zzb3 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    list.add(Long.valueOf(((zzaca) this.zza).zzh()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final boolean zzN() throws IOException {
        zzS(0);
        return this.zza.zzq();
    }

    public final boolean zzO() throws IOException {
        int i;
        zzacc zzacc = this.zza;
        if (zzacc.zzp() || (i = this.zzb) == this.zzc) {
            return false;
        }
        return zzacc.zzr(i);
    }

    public final double zza() throws IOException {
        zzS(1);
        return Double.longBitsToDouble(((zzaca) this.zza).zzg());
    }

    public final float zzb() throws IOException {
        zzS(5);
        return Float.intBitsToFloat(((zzaca) this.zza).zzd());
    }

    public final int zzc() throws IOException {
        int i = this.zzd;
        if (i != 0) {
            this.zzb = i;
            this.zzd = 0;
        } else {
            i = this.zza.zzf();
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
        return ((zzaca) this.zza).zze();
    }

    public final int zzf() throws IOException {
        zzS(5);
        return ((zzaca) this.zza).zzd();
    }

    public final int zzg() throws IOException {
        zzS(0);
        return ((zzaca) this.zza).zze();
    }

    public final int zzh() throws IOException {
        zzS(5);
        return ((zzaca) this.zza).zzd();
    }

    public final int zzi() throws IOException {
        zzS(0);
        return zzaca.zzs(((zzaca) this.zza).zze());
    }

    public final int zzj() throws IOException {
        zzS(0);
        return ((zzaca) this.zza).zze();
    }

    public final long zzk() throws IOException {
        zzS(1);
        return ((zzaca) this.zza).zzg();
    }

    public final long zzl() throws IOException {
        zzS(0);
        return ((zzaca) this.zza).zzh();
    }

    public final long zzm() throws IOException {
        zzS(1);
        return ((zzaca) this.zza).zzg();
    }

    public final long zzn() throws IOException {
        zzS(0);
        return zzaca.zzt(((zzaca) this.zza).zzh());
    }

    public final long zzo() throws IOException {
        zzS(0);
        return ((zzaca) this.zza).zzh();
    }

    public final zzaby zzp() throws IOException {
        zzS(2);
        return this.zza.zzj();
    }

    @Deprecated
    public final Object zzr(zzaer zzaer, zzacm zzacm) throws IOException {
        zzS(3);
        return zzP(zzaer, zzacm);
    }

    public final Object zzs(zzaer zzaer, zzacm zzacm) throws IOException {
        zzS(2);
        return zzQ(zzaer, zzacm);
    }

    public final String zzt() throws IOException {
        zzS(2);
        return this.zza.zzk();
    }

    public final String zzu() throws IOException {
        zzS(2);
        return this.zza.zzl();
    }

    public final void zzv(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzabn) {
            zzabn zzabn = (zzabn) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzabn.zze(this.zza.zzq());
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zzb2 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    zzabn.zze(this.zza.zzq());
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Boolean.valueOf(this.zza.zzq()));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zzb3 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    list.add(Boolean.valueOf(this.zza.zzq()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzw(List list) throws IOException {
        int zzf;
        if ((this.zzb & 7) == 2) {
            do {
                list.add(zzp());
                zzacc zzacc = this.zza;
                if (!zzacc.zzp()) {
                    zzf = zzacc.zzf();
                } else {
                    return;
                }
            } while (zzf == this.zzb);
            this.zzd = zzf;
            return;
        }
        throw zzadi.zza();
    }

    public final void zzx(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzacj) {
            zzacj zzacj = (zzacj) list;
            int i = this.zzb & 7;
            if (i == 1) {
                do {
                    zzacj.zze(Double.longBitsToDouble(((zzaca) this.zza).zzg()));
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zze = ((zzaca) this.zza).zze();
                zzU(zze);
                int zzb2 = this.zza.zzb() + zze;
                do {
                    zzacj.zze(Double.longBitsToDouble(((zzaca) this.zza).zzg()));
                } while (this.zza.zzb() < zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 1) {
                do {
                    list.add(Double.valueOf(Double.longBitsToDouble(((zzaca) this.zza).zzg())));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zze2 = ((zzaca) this.zza).zze();
                zzU(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Double.valueOf(Double.longBitsToDouble(((zzaca) this.zza).zzg())));
                } while (this.zza.zzb() < zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzy(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzada) {
            zzada zzada = (zzada) list;
            int i = this.zzb & 7;
            if (i == 0) {
                do {
                    zzada.zzg(((zzaca) this.zza).zze());
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else if (i == 2) {
                int zzb2 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    zzada.zzg(((zzaca) this.zza).zze());
                } while (this.zza.zzb() < zzb2);
                zzR(zzb2);
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 0) {
                do {
                    list.add(Integer.valueOf(((zzaca) this.zza).zze()));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else if (i2 == 2) {
                int zzb3 = this.zza.zzb() + ((zzaca) this.zza).zze();
                do {
                    list.add(Integer.valueOf(((zzaca) this.zza).zze()));
                } while (this.zza.zzb() < zzb3);
                zzR(zzb3);
            } else {
                throw zzadi.zza();
            }
        }
    }

    public final void zzz(List list) throws IOException {
        int zzf;
        int zzf2;
        if (list instanceof zzada) {
            zzada zzada = (zzada) list;
            int i = this.zzb & 7;
            if (i == 2) {
                int zze = ((zzaca) this.zza).zze();
                zzT(zze);
                int zzb2 = this.zza.zzb() + zze;
                do {
                    zzada.zzg(((zzaca) this.zza).zzd());
                } while (this.zza.zzb() < zzb2);
            } else if (i == 5) {
                do {
                    zzada.zzg(((zzaca) this.zza).zzd());
                    zzacc zzacc = this.zza;
                    if (!zzacc.zzp()) {
                        zzf2 = zzacc.zzf();
                    } else {
                        return;
                    }
                } while (zzf2 == this.zzb);
                this.zzd = zzf2;
            } else {
                throw zzadi.zza();
            }
        } else {
            int i2 = this.zzb & 7;
            if (i2 == 2) {
                int zze2 = ((zzaca) this.zza).zze();
                zzT(zze2);
                int zzb3 = this.zza.zzb() + zze2;
                do {
                    list.add(Integer.valueOf(((zzaca) this.zza).zzd()));
                } while (this.zza.zzb() < zzb3);
            } else if (i2 == 5) {
                do {
                    list.add(Integer.valueOf(((zzaca) this.zza).zzd()));
                    zzacc zzacc2 = this.zza;
                    if (!zzacc2.zzp()) {
                        zzf = zzacc2.zzf();
                    } else {
                        return;
                    }
                } while (zzf == this.zzb);
                this.zzd = zzf;
            } else {
                throw zzadi.zza();
            }
        }
    }
}
