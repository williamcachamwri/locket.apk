package com.google.ads.interactivemedia.v3.internal;

import sun.misc.Unsafe;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class zztj$$ExternalSyntheticBackportWithForwarding0 {
    public static /* synthetic */ boolean m(Unsafe unsafe, Object obj, long j, Object obj2, Object obj3) {
        while (!unsafe.compareAndSwapObject(obj, j, obj2, obj3)) {
            if (unsafe.getObject(obj, j) != obj2) {
                return false;
            }
        }
        return true;
    }
}
