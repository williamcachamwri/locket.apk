package com.google.android.gms.internal.fido;

import com.google.common.primitives.SignedBytes;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import kotlin.time.DurationKt;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
public final class zzdu implements Closeable {
    private final InputStream zza;
    private zzdt zzb;
    private final byte[] zzc = new byte[8];
    private final zzdv zzd = zzdv.zza();

    public zzdu(InputStream inputStream) {
        this.zza = inputStream;
    }

    private final long zzh() throws IOException {
        if (this.zzb.zza() < 24) {
            long zza2 = (long) this.zzb.zza();
            this.zzb = null;
            return zza2;
        } else if (this.zzb.zza() == 24) {
            int read = this.zza.read();
            if (read != -1) {
                this.zzb = null;
                return ((long) read) & 255;
            }
            throw new EOFException();
        } else if (this.zzb.zza() == 25) {
            zzk(this.zzc, 2);
            byte[] bArr = this.zzc;
            return ((((long) bArr[0]) & 255) << 8) | (((long) bArr[1]) & 255);
        } else if (this.zzb.zza() == 26) {
            zzk(this.zzc, 4);
            byte[] bArr2 = this.zzc;
            long j = (long) bArr2[0];
            long j2 = (((long) bArr2[1]) & 255) << 16;
            return (((long) bArr2[3]) & 255) | j2 | ((j & 255) << 24) | ((((long) bArr2[2]) & 255) << 8);
        } else if (this.zzb.zza() == 27) {
            zzk(this.zzc, 8);
            byte[] bArr3 = this.zzc;
            long j3 = (long) bArr3[0];
            long j4 = (long) bArr3[1];
            long j5 = (long) bArr3[2];
            long j6 = (long) bArr3[3];
            long j7 = (long) bArr3[4];
            long j8 = (long) bArr3[5];
            long j9 = (j4 & 255) << 48;
            long j10 = (((long) bArr3[6]) & 255) << 8;
            return (((long) bArr3[7]) & 255) | j10 | j9 | ((j3 & 255) << 56) | ((j5 & 255) << 40) | ((j6 & 255) << 32) | ((j7 & 255) << 24) | ((j8 & 255) << 16);
        } else {
            throw new IOException(String.format("invalid additional information %s for major type %s", new Object[]{Byte.valueOf(this.zzb.zza()), Integer.valueOf(this.zzb.zzc())}));
        }
    }

    private final void zzi() throws IOException {
        zzd();
        if (this.zzb.zza() == 31) {
            throw new IllegalStateException(String.format("expected definite length but found %s", new Object[]{Byte.valueOf(this.zzb.zza())}));
        }
    }

    private final void zzj(byte b) throws IOException {
        zzd();
        if (this.zzb.zzb() != b) {
            throw new IllegalStateException(String.format("expected major type %s but found %s", new Object[]{Integer.valueOf((b >> 5) & 7), Integer.valueOf(this.zzb.zzc())}));
        }
    }

    private final void zzk(byte[] bArr, int i) throws IOException {
        int i2 = 0;
        while (i2 != i) {
            int read = this.zza.read(bArr, i2, i - i2);
            if (read != -1) {
                i2 += read;
            } else {
                throw new EOFException();
            }
        }
        this.zzb = null;
    }

    private final byte[] zzl() throws IOException {
        zzi();
        long zzh = zzh();
        if (zzh < 0 || zzh > 2147483647L) {
            throw new UnsupportedOperationException(String.format("the maximum supported byte/text string length is %s bytes", new Object[]{Integer.MAX_VALUE}));
        } else if (((long) this.zza.available()) >= zzh) {
            int i = (int) zzh;
            byte[] bArr = new byte[i];
            zzk(bArr, i);
            return bArr;
        } else {
            throw new EOFException();
        }
    }

    public final void close() throws IOException {
        this.zza.close();
        this.zzd.zzb();
    }

    public final long zza() throws IOException {
        zzj(Byte.MIN_VALUE);
        zzi();
        long zzh = zzh();
        int i = (zzh > 0 ? 1 : (zzh == 0 ? 0 : -1));
        if (i >= 0) {
            if (i > 0) {
                this.zzd.zzg(zzh);
            }
            return zzh;
        }
        throw new UnsupportedOperationException(String.format("the maximum supported array length is %s", new Object[]{Long.MAX_VALUE}));
    }

    public final long zzb() throws IOException {
        boolean z;
        zzd();
        if (this.zzb.zzb() == 0) {
            z = true;
        } else if (this.zzb.zzb() == 32) {
            z = false;
        } else {
            throw new IllegalStateException(String.format("expected major type 0 or 1 but found %s", new Object[]{Integer.valueOf(this.zzb.zzc())}));
        }
        long zzh = zzh();
        if (zzh >= 0) {
            return z ? zzh : ~zzh;
        }
        throw new UnsupportedOperationException(String.format("the maximum supported unsigned/negative integer is %s", new Object[]{Long.MAX_VALUE}));
    }

    public final long zzc() throws IOException {
        zzj((byte) -96);
        zzi();
        long zzh = zzh();
        int i = (zzh > 0 ? 1 : (zzh == 0 ? 0 : -1));
        if (i < 0 || zzh > DurationKt.MAX_MILLIS) {
            throw new UnsupportedOperationException("the maximum supported map length is 4611686018427387903L");
        }
        if (i > 0) {
            this.zzd.zzg(zzh + zzh);
        }
        return zzh;
    }

    public final zzdt zzd() throws IOException {
        if (this.zzb == null) {
            int read = this.zza.read();
            if (read == -1) {
                this.zzd.zzb();
                return null;
            }
            zzdt zzdt = new zzdt(read);
            this.zzb = zzdt;
            byte zzb2 = zzdt.zzb();
            if (!(zzb2 == Byte.MIN_VALUE || zzb2 == -96 || zzb2 == -64)) {
                if (zzb2 != -32) {
                    if (!(zzb2 == 0 || zzb2 == 32)) {
                        if (zzb2 == 64) {
                            this.zzd.zze(-1);
                        } else if (zzb2 == 96) {
                            this.zzd.zze(-2);
                        } else {
                            throw new IllegalStateException(String.format("invalid major type: %s", new Object[]{Integer.valueOf(this.zzb.zzc())}));
                        }
                        this.zzd.zzf();
                    }
                } else if (this.zzb.zza() == 31) {
                    this.zzd.zzc();
                }
            }
            this.zzd.zzd();
            this.zzd.zzf();
        }
        return this.zzb;
    }

    public final String zze() throws IOException {
        zzj((byte) 96);
        return new String(zzl(), StandardCharsets.UTF_8);
    }

    public final boolean zzf() throws IOException {
        zzj((byte) -32);
        if (this.zzb.zza() <= 24) {
            int zzh = (int) zzh();
            if (zzh == 20) {
                return false;
            }
            if (zzh == 21) {
                return true;
            }
            throw new IllegalStateException(String.format("expected FALSE or TRUE", new Object[0]));
        }
        throw new IllegalStateException("expected simple value");
    }

    public final byte[] zzg() throws IOException {
        zzj(SignedBytes.MAX_POWER_OF_TWO);
        return zzl();
    }
}
