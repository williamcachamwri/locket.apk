package org.koin.mp;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/koin/mp/KoinPlatformTimeTools;", "", "()V", "getTimeInNanoSeconds", "", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KoinPlatformTimeTools.kt */
public final class KoinPlatformTimeTools {
    public static final KoinPlatformTimeTools INSTANCE = new KoinPlatformTimeTools();

    private KoinPlatformTimeTools() {
    }

    public final long getTimeInNanoSeconds() {
        return System.nanoTime();
    }
}
