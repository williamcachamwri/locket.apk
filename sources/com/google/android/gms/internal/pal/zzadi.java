package com.google.android.gms.internal.pal;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public class zzadi extends IOException {
    private zzaef zza = null;

    public zzadi(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    static zzadh zza() {
        return new zzadh("Protocol message tag had invalid wire type.");
    }

    static zzadi zzb() {
        return new zzadi("Protocol message end-group tag did not match expected tag.");
    }

    static zzadi zzc() {
        return new zzadi("Protocol message contained an invalid tag (zero).");
    }

    static zzadi zzd() {
        return new zzadi("Protocol message had invalid UTF-8.");
    }

    static zzadi zze() {
        return new zzadi("CodedInputStream encountered a malformed varint.");
    }

    static zzadi zzf() {
        return new zzadi("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzadi zzg() {
        return new zzadi("Failed to parse the message.");
    }

    static zzadi zzi() {
        return new zzadi("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzadi zzh(zzaef zzaef) {
        this.zza = zzaef;
        return this;
    }

    public zzadi(String str) {
        super(str);
    }
}
