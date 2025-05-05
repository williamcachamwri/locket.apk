package com.google.android.gms.internal.pal;

import com.google.android.gms.internal.pal.zzacv;
import com.google.android.gms.internal.pal.zzacz;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public class zzacv<MessageType extends zzacz<MessageType, BuilderType>, BuilderType extends zzacv<MessageType, BuilderType>> extends zzabh<MessageType, BuilderType> {
    protected zzacz zza;
    protected boolean zzb = false;
    private final zzacz zzc;

    protected zzacv(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (zzacz) messagetype.zzb(4, (Object) null, (Object) null);
    }

    private static final void zza(zzacz zzacz, zzacz zzacz2) {
        zzaen.zza().zzb(zzacz.getClass()).zzg(zzacz, zzacz2);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzabh zzai(zzabi zzabi) {
        zzal((zzacz) zzabi);
        return this;
    }

    /* renamed from: zzak */
    public final zzacv zzah() {
        zzacv zzacv = (zzacv) this.zzc.zzb(5, (Object) null, (Object) null);
        zzacv.zzal(zzap());
        return zzacv;
    }

    public final zzacv zzal(zzacz zzacz) {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        zza(this.zza, zzacz);
        return this;
    }

    public final zzacv zzam(byte[] bArr, int i, int i2, zzacm zzacm) throws zzadi {
        if (this.zzb) {
            zzar();
            this.zzb = false;
        }
        try {
            zzaen.zza().zzb(this.zza.getClass()).zzi(this.zza, bArr, 0, i2, new zzabl(zzacm));
            return this;
        } catch (zzadi e) {
            throw e;
        } catch (IndexOutOfBoundsException unused) {
            throw zzadi.zzi();
        } catch (IOException e2) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
        }
    }

    public final MessageType zzan() {
        MessageType zzao = zzap();
        if (zzao.zzaH()) {
            return zzao;
        }
        throw new zzafh(zzao);
    }

    /* renamed from: zzao */
    public MessageType zzap() {
        if (this.zzb) {
            return this.zza;
        }
        zzacz zzacz = this.zza;
        zzaen.zza().zzb(zzacz.getClass()).zzf(zzacz);
        this.zzb = true;
        return this.zza;
    }

    public final /* synthetic */ zzaef zzaq() {
        return this.zzc;
    }

    /* access modifiers changed from: protected */
    public void zzar() {
        zzacz zzacz = (zzacz) this.zza.zzb(4, (Object) null, (Object) null);
        zza(zzacz, this.zza);
        this.zza = zzacz;
    }
}
