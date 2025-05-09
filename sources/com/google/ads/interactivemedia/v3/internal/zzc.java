package com.google.ads.interactivemedia.v3.internal;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
final class zzc implements zzb {
    private final FileChannel zza;
    private final long zzb;
    private final long zzc;

    public zzc(FileChannel fileChannel, long j, long j2) {
        this.zza = fileChannel;
        this.zzb = j;
        this.zzc = j2;
    }

    public final long zza() {
        return this.zzc;
    }

    public final void zzb(MessageDigest[] messageDigestArr, long j, int i) throws IOException {
        MappedByteBuffer map = this.zza.map(FileChannel.MapMode.READ_ONLY, this.zzb + j, (long) i);
        map.load();
        for (MessageDigest update : messageDigestArr) {
            map.position(0);
            update.update(map);
        }
    }
}
