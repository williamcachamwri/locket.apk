package com.google.android.gms.internal.mlkit_vision_common;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.inject.Provider;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public final /* synthetic */ class zzmm implements Provider {
    public final /* synthetic */ TransportFactory zza;

    public /* synthetic */ zzmm(TransportFactory transportFactory) {
        this.zza = transportFactory;
    }

    public final Object get() {
        return this.zza.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), zzmo.zza);
    }
}
