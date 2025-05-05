package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzakf  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzakf extends IOException {
    private boolean zza;

    static zzake zza() {
        return new zzake("Protocol message tag had invalid wire type.");
    }

    static zzakf zzb() {
        return new zzakf("Protocol message end-group tag did not match expected tag.");
    }

    static zzakf zzc() {
        return new zzakf("Protocol message contained an invalid tag (zero).");
    }

    static zzakf zzd() {
        return new zzakf("Protocol message had invalid UTF-8.");
    }

    static zzakf zze() {
        return new zzakf("CodedInputStream encountered a malformed varint.");
    }

    static zzakf zzf() {
        return new zzakf("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzakf zzg() {
        return new zzakf("Failed to parse the message.");
    }

    static zzakf zzh() {
        return new zzakf("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
    }

    static zzakf zzi() {
        return new zzakf("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    static zzakf zzj() {
        return new zzakf("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public zzakf(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    public zzakf(String str) {
        super(str);
    }

    /* access modifiers changed from: package-private */
    public final void zzk() {
        this.zza = true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzl() {
        return this.zza;
    }
}
