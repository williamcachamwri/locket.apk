package com.google.android.gms.internal.pal;

import com.google.android.gms.internal.pal.zzabh;
import com.google.android.gms.internal.pal.zzabi;

/* compiled from: com.google.android.gms:play-services-pal@@20.0.1 */
public abstract class zzabh<MessageType extends zzabi<MessageType, BuilderType>, BuilderType extends zzabh<MessageType, BuilderType>> implements zzaee {
    /* renamed from: zzah */
    public abstract zzabh clone();

    /* access modifiers changed from: protected */
    public abstract zzabh zzai(zzabi zzabi);

    public final /* bridge */ /* synthetic */ zzaee zzaj(zzaef zzaef) {
        if (zzaq().getClass().isInstance(zzaef)) {
            return zzai((zzabi) zzaef);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
