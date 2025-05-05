package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;

/* compiled from: com.google.mlkit:barcode-scanning@@17.2.0 */
public class zzeo extends IOException {
    private zzfo zza = null;

    public zzeo(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    static zzen zza() {
        return new zzen("Protocol message tag had invalid wire type.");
    }

    static zzeo zzb() {
        return new zzeo("Protocol message contained an invalid tag (zero).");
    }

    static zzeo zzc() {
        return new zzeo("Protocol message had invalid UTF-8.");
    }

    static zzeo zzd() {
        return new zzeo("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzeo zze() {
        return new zzeo("Failed to parse the message.");
    }

    static zzeo zzg() {
        return new zzeo("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzeo zzf(zzfo zzfo) {
        this.zza = zzfo;
        return this;
    }

    public zzeo(String str) {
        super(str);
    }
}
