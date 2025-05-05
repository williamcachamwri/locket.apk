package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.recaptcha:recaptcha@@18.7.0-beta01 */
final class zzoo implements zzoy {
    private final zzok zza;
    private final zzpn zzb;
    private final boolean zzc;
    private final zzmr zzd;

    private zzoo(zzpn zzpn, zzmr zzmr, zzok zzok) {
        this.zzb = zzpn;
        this.zzc = zzok instanceof zznc;
        this.zzd = zzmr;
        this.zza = zzok;
    }

    static zzoo zzc(zzpn zzpn, zzmr zzmr, zzok zzok) {
        return new zzoo(zzpn, zzmr, zzok);
    }

    public final int zza(Object obj) {
        zzpo zzpo = ((zznf) obj).zzc;
        zzpo zzpo2 = zzpo;
        int zzb2 = zzpo.zzb();
        return this.zzc ? zzb2 + ((zznc) obj).zzb.zzb() : zzb2;
    }

    public final int zzb(Object obj) {
        int hashCode = ((zznf) obj).zzc.hashCode();
        return this.zzc ? (hashCode * 53) + ((zznc) obj).zzb.zza.hashCode() : hashCode;
    }

    public final Object zze() {
        zzok zzok = this.zza;
        if (zzok instanceof zznf) {
            return ((zznf) zzok).zzv();
        }
        return zzok.zzad().zzl();
    }

    public final void zzf(Object obj) {
        this.zzb.zzi(obj);
        this.zzd.zza(obj);
    }

    public final void zzg(Object obj, Object obj2) {
        zzpa.zzq(this.zzb, obj, obj2);
        if (this.zzc) {
            zzpa.zzp(this.zzd, obj, obj2);
        }
    }

    public final void zzh(Object obj, zzox zzox, zzmq zzmq) throws IOException {
        boolean z;
        zzpn zzpn = this.zzb;
        Object zza2 = zzpn.zza(obj);
        ((zznc) obj).zzi();
        while (true) {
            if (zzox.zzc() == Integer.MAX_VALUE) {
                break;
            }
            int zzd2 = zzox.zzd();
            int i = 0;
            if (zzd2 != 11) {
                if ((zzd2 & 7) == 2) {
                    zzne zza3 = zzmq.zza(this.zza, zzd2 >>> 3);
                    if (zza3 == null) {
                        z = zzpn.zzk(zza2, zzox, 0);
                    } else {
                        zzne zzne = zza3;
                        throw null;
                    }
                } else {
                    z = zzox.zzO();
                }
                if (!z) {
                    break;
                }
            } else {
                zzne zzne2 = null;
                zzlg zzlg = null;
                while (true) {
                    try {
                        if (zzox.zzc() == Integer.MAX_VALUE) {
                            break;
                        }
                        int zzd3 = zzox.zzd();
                        if (zzd3 == 16) {
                            i = zzox.zzj();
                            zzne2 = zzmq.zza(this.zza, i);
                        } else if (zzd3 == 26) {
                            if (zzne2 == null) {
                                zzlg = zzox.zzp();
                            } else {
                                zzne zzne3 = zzne2;
                                throw null;
                            }
                        } else if (!zzox.zzO()) {
                            break;
                        }
                    } catch (Throwable th) {
                        zzpn.zzj(obj, zza2);
                        throw th;
                    }
                }
                if (zzox.zzd() != 12) {
                    throw new zznp("Protocol message end-group tag did not match expected tag.");
                } else if (zzlg == null) {
                    continue;
                } else if (zzne2 == null) {
                    zzpn.zzg(zza2, i, zzlg);
                } else {
                    zzne zzne4 = zzne2;
                    throw null;
                }
            }
        }
        zzpn.zzj(obj, zza2);
    }

    public final void zzi(Object obj, byte[] bArr, int i, int i2, zzkv zzkv) throws IOException {
        zznf zznf = (zznf) obj;
        zzpo zzpo = zznf.zzc;
        if (zzpo == zzpo.zzc()) {
            zzpo = zzpo.zzf();
            zznf.zzc = zzpo;
        }
        ((zznc) obj).zzi();
        zzne zzne = null;
        while (i < i2) {
            int zzi = zzkw.zzi(bArr, i, zzkv);
            int i3 = zzkv.zza;
            if (i3 == 11) {
                int i4 = 0;
                zzlg zzlg = null;
                while (zzi < i2) {
                    zzi = zzkw.zzi(bArr, zzi, zzkv);
                    int i5 = zzkv.zza;
                    int i6 = i5 >>> 3;
                    int i7 = i5 & 7;
                    if (i6 != 2) {
                        if (i6 == 3) {
                            if (zzne != null) {
                                int i8 = zzou.zza;
                                throw null;
                            } else if (i7 == 2) {
                                zzi = zzkw.zza(bArr, zzi, zzkv);
                                zzlg = (zzlg) zzkv.zzc;
                            }
                        }
                    } else if (i7 == 0) {
                        zzi = zzkw.zzi(bArr, zzi, zzkv);
                        i4 = zzkv.zza;
                        zzne = zzkv.zzd.zza(this.zza, i4);
                        zzne zzne2 = zzne;
                    }
                    if (i5 == 12) {
                        break;
                    }
                    zzi = zzkw.zzo(i5, bArr, zzi, i2, zzkv);
                }
                if (zzlg != null) {
                    zzpo.zzj((i4 << 3) | 2, zzlg);
                }
                i = zzi;
            } else if ((i3 & 7) == 2) {
                zzne = zzkv.zzd.zza(this.zza, i3 >>> 3);
                zzne zzne3 = zzne;
                if (zzne == null) {
                    i = zzkw.zzh(i3, bArr, zzi, i2, zzpo, zzkv);
                } else {
                    int i9 = zzou.zza;
                    throw null;
                }
            } else {
                i = zzkw.zzo(i3, bArr, zzi, i2, zzkv);
            }
        }
        if (i != i2) {
            throw new zznp("Failed to parse the message.");
        }
    }

    public final void zzj(Object obj, zzqa zzqa) throws IOException {
        Iterator zzf = ((zznc) obj).zzb.zzf();
        while (zzf.hasNext()) {
            Map.Entry entry = (Map.Entry) zzf.next();
            zzmu zzmu = (zzmu) entry.getKey();
            if (zzmu.zze() == zzpz.MESSAGE) {
                zzmu.zzg();
                zzmu.zzf();
                if (entry instanceof zznr) {
                    zzqa.zzw(zzmu.zza(), ((zznr) entry).zza().zzb());
                } else {
                    zzqa.zzw(zzmu.zza(), entry.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        zzpo zzpo = ((zznf) obj).zzc;
        zzpo zzpo2 = zzpo;
        zzpo.zzk(zzqa);
    }

    public final boolean zzk(Object obj, Object obj2) {
        if (!((zznf) obj).zzc.equals(((zznf) obj2).zzc)) {
            return false;
        }
        if (this.zzc) {
            return ((zznc) obj).zzb.equals(((zznc) obj2).zzb);
        }
        return true;
    }

    public final boolean zzl(Object obj) {
        return ((zznc) obj).zzb.zzk();
    }
}
