package com.google.firebase.auth;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Qualified;

/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public final /* synthetic */ class zzaf implements ComponentFactory {
    private /* synthetic */ Qualified zza;
    private /* synthetic */ Qualified zzb;
    private /* synthetic */ Qualified zzc;
    private /* synthetic */ Qualified zzd;
    private /* synthetic */ Qualified zze;

    public /* synthetic */ zzaf(Qualified qualified, Qualified qualified2, Qualified qualified3, Qualified qualified4, Qualified qualified5) {
        this.zza = qualified;
        this.zzb = qualified2;
        this.zzc = qualified3;
        this.zzd = qualified4;
        this.zze = qualified5;
    }

    public final Object create(ComponentContainer componentContainer) {
        return FirebaseAuthRegistrar.lambda$getComponents$0(this.zza, this.zzb, this.zzc, this.zzd, this.zze, componentContainer);
    }
}
