package androidx.media3.extractor.text.ssa;

final class SsaDialogueFormat {
    public final int endTimeIndex;
    public final int length;
    public final int startTimeIndex;
    public final int styleIndex;
    public final int textIndex;

    private SsaDialogueFormat(int i, int i2, int i3, int i4, int i5) {
        this.startTimeIndex = i;
        this.endTimeIndex = i2;
        this.styleIndex = i3;
        this.textIndex = i4;
        this.length = i5;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.media3.extractor.text.ssa.SsaDialogueFormat fromFormatLine(java.lang.String r9) {
        /*
            java.lang.String r0 = "Format:"
            boolean r0 = r9.startsWith(r0)
            androidx.media3.common.util.Assertions.checkArgument(r0)
            r0 = 7
            java.lang.String r9 = r9.substring(r0)
            java.lang.String r0 = ","
            java.lang.String[] r9 = android.text.TextUtils.split(r9, r0)
            r0 = -1
            r1 = 0
            r4 = r0
            r5 = r4
            r6 = r5
            r7 = r6
            r2 = r1
        L_0x001b:
            int r3 = r9.length
            if (r2 >= r3) goto L_0x0070
            r3 = r9[r2]
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = com.google.common.base.Ascii.toLowerCase((java.lang.String) r3)
            r3.hashCode()
            int r8 = r3.hashCode()
            switch(r8) {
                case 100571: goto L_0x0058;
                case 3556653: goto L_0x004c;
                case 109757538: goto L_0x0040;
                case 109780401: goto L_0x0034;
                default: goto L_0x0032;
            }
        L_0x0032:
            r3 = r0
            goto L_0x0062
        L_0x0034:
            java.lang.String r8 = "style"
            boolean r3 = r3.equals(r8)
            if (r3 != 0) goto L_0x003e
            goto L_0x0032
        L_0x003e:
            r3 = 3
            goto L_0x0062
        L_0x0040:
            java.lang.String r8 = "start"
            boolean r3 = r3.equals(r8)
            if (r3 != 0) goto L_0x004a
            goto L_0x0032
        L_0x004a:
            r3 = 2
            goto L_0x0062
        L_0x004c:
            java.lang.String r8 = "text"
            boolean r3 = r3.equals(r8)
            if (r3 != 0) goto L_0x0056
            goto L_0x0032
        L_0x0056:
            r3 = 1
            goto L_0x0062
        L_0x0058:
            java.lang.String r8 = "end"
            boolean r3 = r3.equals(r8)
            if (r3 != 0) goto L_0x0061
            goto L_0x0032
        L_0x0061:
            r3 = r1
        L_0x0062:
            switch(r3) {
                case 0: goto L_0x006c;
                case 1: goto L_0x006a;
                case 2: goto L_0x0068;
                case 3: goto L_0x0066;
                default: goto L_0x0065;
            }
        L_0x0065:
            goto L_0x006d
        L_0x0066:
            r6 = r2
            goto L_0x006d
        L_0x0068:
            r4 = r2
            goto L_0x006d
        L_0x006a:
            r7 = r2
            goto L_0x006d
        L_0x006c:
            r5 = r2
        L_0x006d:
            int r2 = r2 + 1
            goto L_0x001b
        L_0x0070:
            if (r4 == r0) goto L_0x007e
            if (r5 == r0) goto L_0x007e
            if (r7 == r0) goto L_0x007e
            androidx.media3.extractor.text.ssa.SsaDialogueFormat r0 = new androidx.media3.extractor.text.ssa.SsaDialogueFormat
            int r8 = r9.length
            r3 = r0
            r3.<init>(r4, r5, r6, r7, r8)
            goto L_0x007f
        L_0x007e:
            r0 = 0
        L_0x007f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.ssa.SsaDialogueFormat.fromFormatLine(java.lang.String):androidx.media3.extractor.text.ssa.SsaDialogueFormat");
    }
}
