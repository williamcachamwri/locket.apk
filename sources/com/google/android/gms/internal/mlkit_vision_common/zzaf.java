package com.google.android.gms.internal.mlkit_vision_common;

import java.io.OutputStream;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
final class zzaf extends OutputStream {
    private long zza = 0;

    zzaf() {
    }

    public final void write(int i) {
        this.zza++;
    }

    public final void write(byte[] bArr) {
        this.zza += (long) bArr.length;
    }

    /* access modifiers changed from: package-private */
    public final long zza() {
        return this.zza;
    }

    public final void write(byte[] bArr, int i, int i2) {
        int length;
        int i3;
        if (i < 0 || i > (length = bArr.length) || i2 < 0 || (i3 = i + i2) > length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        this.zza += (long) i2;
    }
}
