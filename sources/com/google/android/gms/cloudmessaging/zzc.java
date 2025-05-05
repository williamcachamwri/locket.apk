package com.google.android.gms.cloudmessaging;

import android.util.Log;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-cloud-messaging@@17.2.0 */
public final class zzc extends ClassLoader {
    /* access modifiers changed from: protected */
    public final Class loadClass(String str, boolean z) throws ClassNotFoundException {
        if (!Objects.equals(str, "com.google.android.gms.iid.MessengerCompat")) {
            return super.loadClass(str, z);
        }
        if (!Log.isLoggable("CloudMessengerCompat", 3)) {
            return zzd.class;
        }
        Log.d("CloudMessengerCompat", "Using renamed FirebaseIidMessengerCompat class");
        return zzd.class;
    }
}
