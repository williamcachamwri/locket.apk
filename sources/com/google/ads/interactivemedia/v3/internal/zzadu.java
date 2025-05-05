package com.google.ads.interactivemedia.v3.internal;

import com.google.ads.interactivemedia.v3.internal.zzadu;
import com.google.ads.interactivemedia.v3.internal.zzady;
import java.io.IOException;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public class zzadu<MessageType extends zzady<MessageType, BuilderType>, BuilderType extends zzadu<MessageType, BuilderType>> extends zzacg<MessageType, BuilderType> {
    protected zzady zza;
    private final zzady zzb;

    protected zzadu(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzaP()) {
            this.zza = messagetype.zzaB();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zza(Object obj, Object obj2) {
        zzafi.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    public final /* synthetic */ zzafb zzaS() {
        throw null;
    }

    public final boolean zzaT() {
        return zzady.zzaO(this.zza, false);
    }

    /* renamed from: zzai */
    public final zzadu zzah() {
        zzadu zzadu = (zzadu) this.zzb.zzm(5, (Object) null, (Object) null);
        zzadu.zza = zzan();
        return zzadu;
    }

    public final zzadu zzaj(zzady zzady) {
        if (!this.zzb.equals(zzady)) {
            if (!this.zza.zzaP()) {
                zzaq();
            }
            zza(this.zza, zzady);
        }
        return this;
    }

    public final zzadu zzak(byte[] bArr, int i, int i2, zzadk zzadk) throws zzaeg {
        if (!this.zza.zzaP()) {
            zzaq();
        }
        try {
            zzafi.zza().zzb(this.zza.getClass()).zzi(this.zza, bArr, 0, i2, new zzacl(zzadk));
            return this;
        } catch (zzaeg e) {
            throw e;
        } catch (IndexOutOfBoundsException unused) {
            throw new zzaeg("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        } catch (IOException e2) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
        }
    }

    public final MessageType zzal() {
        MessageType zzam = zzan();
        if (zzady.zzaO(zzam, true)) {
            return zzam;
        }
        throw new zzagg(zzam);
    }

    /* renamed from: zzam */
    public MessageType zzan() {
        if (!this.zza.zzaP()) {
            return this.zza;
        }
        this.zza.zzaK();
        return this.zza;
    }

    /* access modifiers changed from: protected */
    public final void zzap() {
        if (!this.zza.zzaP()) {
            zzaq();
        }
    }

    /* access modifiers changed from: protected */
    public void zzaq() {
        zzady zzaB = this.zzb.zzaB();
        zza(zzaB, this.zza);
        this.zza = zzaB;
    }
}
