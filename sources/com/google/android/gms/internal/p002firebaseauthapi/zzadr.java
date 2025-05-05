package com.google.android.gms.internal.p002firebaseauthapi;

import java.lang.reflect.Type;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@23.1.0 */
public class zzadr {
    public static Object zza(String str, Type type) throws zzabg {
        if (type == String.class) {
            try {
                zzafj zzafj = (zzafj) new zzafj().zza(str);
                if (zzafj.zzb()) {
                    return zzafj.zza();
                }
                throw new zzabg("No error message: " + str);
            } catch (Exception e) {
                throw new zzabg("Json conversion failed! " + e.getMessage(), e);
            }
        } else if (type == Void.class) {
            return null;
        } else {
            try {
                try {
                    return ((zzadt) ((Class) type).getConstructor(new Class[0]).newInstance(new Object[0])).zza(str);
                } catch (Exception e2) {
                    throw new zzabg("Json conversion failed! " + e2.getMessage(), e2);
                }
            } catch (Exception e3) {
                throw new zzabg("Instantiation of JsonResponse failed! " + String.valueOf(type), e3);
            }
        }
    }

    private zzadr() {
    }
}
