package androidx.media3.exoplayer.source.preload;

import androidx.media3.common.MediaItem;

public final class PreloadException extends Exception {
    public final MediaItem mediaItem;

    public PreloadException(MediaItem mediaItem2, String str, Throwable th) {
        super(str, th);
        this.mediaItem = mediaItem2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        if (r3 == null) goto L_0x0041;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean errorInfoEquals(androidx.media3.exoplayer.source.preload.PreloadException r7) {
        /*
            r6 = this;
            r0 = 1
            if (r6 != r7) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r7 == 0) goto L_0x005c
            java.lang.Class r2 = r6.getClass()
            java.lang.Class r3 = r7.getClass()
            if (r2 == r3) goto L_0x0012
            goto L_0x005c
        L_0x0012:
            java.lang.Throwable r2 = r6.getCause()
            java.lang.Throwable r3 = r7.getCause()
            if (r2 == 0) goto L_0x003c
            if (r3 == 0) goto L_0x003c
            java.lang.String r4 = r2.getMessage()
            java.lang.String r5 = r3.getMessage()
            boolean r4 = java.util.Objects.equals(r4, r5)
            if (r4 != 0) goto L_0x002d
            return r1
        L_0x002d:
            java.lang.Class r2 = r2.getClass()
            java.lang.Class r3 = r3.getClass()
            boolean r2 = java.util.Objects.equals(r2, r3)
            if (r2 != 0) goto L_0x0041
            return r1
        L_0x003c:
            if (r2 != 0) goto L_0x005c
            if (r3 == 0) goto L_0x0041
            goto L_0x005c
        L_0x0041:
            androidx.media3.common.MediaItem r2 = r6.mediaItem
            androidx.media3.common.MediaItem r3 = r7.mediaItem
            boolean r2 = java.util.Objects.equals(r2, r3)
            if (r2 == 0) goto L_0x005a
            java.lang.String r2 = r6.getMessage()
            java.lang.String r7 = r7.getMessage()
            boolean r7 = java.util.Objects.equals(r2, r7)
            if (r7 == 0) goto L_0x005a
            goto L_0x005b
        L_0x005a:
            r0 = r1
        L_0x005b:
            return r0
        L_0x005c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.preload.PreloadException.errorInfoEquals(androidx.media3.exoplayer.source.preload.PreloadException):boolean");
    }
}
