package com.adsbynimbus.render.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OpenMeasurement.kt */
final class OpenMeasurement$serviceJs$2 extends Lambda implements Function0<String> {
    public static final OpenMeasurement$serviceJs$2 INSTANCE = new OpenMeasurement$serviceJs$2();

    OpenMeasurement$serviceJs$2() {
        super(0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002e, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String invoke() {
        /*
            r3 = this;
            com.adsbynimbus.Nimbus r0 = com.adsbynimbus.Nimbus.INSTANCE
            android.app.Application r0 = com.adsbynimbus.internal.PlatformKt.getApplication()
            android.content.res.Resources r0 = r0.getResources()
            int r1 = com.adsbynimbus.render.R.raw.nimbus_omsdk_v1
            java.io.InputStream r0 = r0.openRawResource(r1)
            java.lang.String r1 = "Nimbus.applicationContex…ce(R.raw.nimbus_omsdk_v1)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            okio.Source r0 = okio.Okio.source((java.io.InputStream) r0)
            okio.BufferedSource r0 = okio.Okio.buffer((okio.Source) r0)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = r0
            okio.BufferedSource r1 = (okio.BufferedSource) r1     // Catch:{ all -> 0x002b }
            java.lang.String r1 = r1.readUtf8()     // Catch:{ all -> 0x002b }
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            return r1
        L_0x002b:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x002d }
        L_0x002d:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.internal.OpenMeasurement$serviceJs$2.invoke():java.lang.String");
    }
}
