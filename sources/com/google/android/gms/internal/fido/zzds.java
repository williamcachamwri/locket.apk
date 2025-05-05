package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzds {
    static final zzdr zza(InputStream inputStream, zzdu zzdu) throws zzdl {
        try {
            return zzb(inputStream, zzdu);
        } finally {
            try {
                zzdu.close();
            } catch (IOException unused) {
            }
        }
    }

    private static final zzdr zzb(InputStream inputStream, zzdu zzdu) throws zzdl {
        try {
            zzdt zzd = zzdu.zzd();
            if (zzd != null) {
                try {
                    byte zza = zzd.zza();
                    byte zzb = zzd.zzb();
                    int i = 0;
                    if (zzb == Byte.MIN_VALUE) {
                        long zza2 = zzdu.zza();
                        if (zza2 <= 1000) {
                            zzc(zza, zza2, inputStream, zzdu);
                            zzdr[] zzdrArr = new zzdr[((int) zza2)];
                            while (((long) i) < zza2) {
                                zzdrArr[i] = zzb(inputStream, zzdu);
                                i++;
                            }
                            return new zzdi(zzaz.zzi(zzdrArr));
                        }
                        throw new zzdl("Parser being asked to read a large CBOR array");
                    } else if (zzb == -96) {
                        long zzc = zzdu.zzc();
                        if (zzc <= 1000) {
                            zzc(zza, zzc, inputStream, zzdu);
                            int i2 = (int) zzc;
                            zzdn[] zzdnArr = new zzdn[i2];
                            zzdr zzdr = null;
                            int i3 = 0;
                            while (((long) i3) < zzc) {
                                zzdr zzb2 = zzb(inputStream, zzdu);
                                if (zzdr != null) {
                                    if (zzb2.compareTo(zzdr) <= 0) {
                                        throw new zzdh(String.format("Keys in CBOR Map not in strictly ascending natural order:\nPrevious key: %s\nCurrent key: %s", new Object[]{zzdr.toString(), zzb2.toString()}));
                                    }
                                }
                                zzdnArr[i3] = new zzdn(zzb2, zzb(inputStream, zzdu));
                                i3++;
                                zzdr = zzb2;
                            }
                            TreeMap treeMap = new TreeMap();
                            while (i < i2) {
                                zzdn zzdn = zzdnArr[i];
                                if (!treeMap.containsKey(zzdn.zza())) {
                                    treeMap.put(zzdn.zza(), zzdn.zzb());
                                    i++;
                                } else {
                                    throw new zzdh("Attempted to add duplicate key to canonical CBOR Map.");
                                }
                            }
                            return new zzdo(zzbg.zzf(treeMap));
                        }
                        throw new zzdl("Parser being asked to read a large CBOR map");
                    } else if (zzb == -64) {
                        throw new zzdl("Tags are currently unsupported");
                    } else if (zzb == -32) {
                        return new zzdj(zzdu.zzf());
                    } else {
                        if (zzb == 0 || zzb == 32) {
                            long zzb3 = zzdu.zzb();
                            zzc(zza, zzb3 > 0 ? zzb3 : ~zzb3, inputStream, zzdu);
                            return new zzdm(zzb3);
                        } else if (zzb == 64) {
                            byte[] zzg = zzdu.zzg();
                            int length = zzg.length;
                            zzc(zza, (long) length, inputStream, zzdu);
                            return new zzdk(zzcz.zzl(zzg, 0, length));
                        } else if (zzb == 96) {
                            String zze = zzdu.zze();
                            zzc(zza, (long) zze.length(), inputStream, zzdu);
                            return new zzdp(zze);
                        } else {
                            throw new zzdl("Unidentifiable major type: " + zzd.zzc());
                        }
                    }
                } catch (IOException | RuntimeException e) {
                    throw new zzdl("Error in decoding CborValue from bytes", e);
                }
            } else {
                throw new zzdl("Parser being asked to parse an empty input stream");
            }
        } catch (IOException e2) {
            throw new zzdl("Error in decoding CborValue from bytes", e2);
        }
    }

    private static final void zzc(byte b, long j, InputStream inputStream, zzdu zzdu) throws IOException {
        switch (b) {
            case 24:
                if (j < 24) {
                    throw new zzdh("Integer value " + j + " after add info could have been represented in 0 additional bytes, but used 1");
                }
                return;
            case 25:
                if (j < 256) {
                    throw new zzdh("Integer value " + j + " after add info could have been represented in 0-1 additional bytes, but used 2");
                }
                return;
            case 26:
                if (j < 65536) {
                    throw new zzdh("Integer value " + j + " after add info could have been represented in 0-2 additional bytes, but used 4");
                }
                return;
            case 27:
                if (j < 4294967296L) {
                    throw new zzdh("Integer value " + j + " after add info could have been represented in 0-4 additional bytes, but used 8");
                }
                return;
            default:
                return;
        }
    }
}
