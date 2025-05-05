package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

/* compiled from: com.google.android.gms:play-services-fido@@20.1.0 */
final class zzdv {
    private final Deque zza = new ArrayDeque(16);

    private zzdv(boolean z) {
    }

    public static zzdv zza() {
        return new zzdv(false);
    }

    private final long zzh() {
        if (this.zza.isEmpty()) {
            return 0;
        }
        return ((Long) this.zza.peek()).longValue();
    }

    private final void zzi(long j) {
        this.zza.pop();
        this.zza.push(Long.valueOf(j));
    }

    public final void zzb() throws IOException {
        if (!this.zza.isEmpty()) {
            throw new IOException(String.format("data item not completed, stackSize: %s scope: %s", new Object[]{Integer.valueOf(this.zza.size()), Long.valueOf(zzh())}));
        }
    }

    public final void zzc() throws IOException {
        long zzh = zzh();
        if (zzh >= 0) {
            throw new IOException(String.format("expected indefinite length scope but found %s", new Object[]{Long.valueOf(zzh)}));
        } else if (zzh != -5) {
            this.zza.pop();
        } else {
            throw new IOException("expected a value for dangling key in indefinite-length map");
        }
    }

    public final void zzd() throws IOException {
        long zzh = zzh();
        if (zzh != -1) {
            if (zzh == -2) {
                zzh = -2;
            } else {
                return;
            }
        }
        throw new IOException(String.format("expected non-string scope but found %s", new Object[]{Long.valueOf(zzh)}));
    }

    public final void zze(long j) throws IOException {
        long zzh = zzh();
        if (zzh != j) {
            if (zzh != -1) {
                if (zzh == -2) {
                    zzh = -2;
                } else {
                    return;
                }
            }
            throw new IOException(String.format("expected non-string scope or scope %s but found %s", new Object[]{Long.valueOf(j), Long.valueOf(zzh)}));
        }
    }

    public final void zzf() {
        long zzh = zzh();
        int i = (zzh > 1 ? 1 : (zzh == 1 ? 0 : -1));
        if (i == 0) {
            this.zza.pop();
        } else if (i > 0) {
            zzi(zzh - 1);
        } else if (zzh == -4) {
            zzi(-5);
        } else if (zzh == -5) {
            zzi(-4);
        }
    }

    public final void zzg(long j) {
        this.zza.push(Long.valueOf(j));
    }
}
