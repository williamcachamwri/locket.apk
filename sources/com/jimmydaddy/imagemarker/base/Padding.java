package com.jimmydaddy.imagemarker.base;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/Padding;", "", "paddingData", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "paddingBottom", "", "paddingLeft", "paddingRight", "paddingTop", "toEdgeInsets", "Lcom/jimmydaddy/imagemarker/base/MarkerInsets;", "width", "", "height", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Padding.kt */
public class Padding {
    private String paddingBottom;
    private String paddingLeft;
    private String paddingRight;
    private String paddingTop;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v23, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v28, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v33, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v18, resolved type: java.lang.String} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x001c, code lost:
        continue;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        if ((r7 instanceof java.lang.String) == false) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0055, code lost:
        r4 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005e, code lost:
        if (com.jimmydaddy.imagemarker.base.Utils.Companion.checkSpreadValue(r4, 1) == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0060, code lost:
        r1 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0067, code lost:
        throw new java.lang.Exception("padding is invalid");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x006a, code lost:
        if ((r7 instanceof java.lang.Number) == false) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006c, code lost:
        r1 = r7.toString();
        r4 = r7.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x010c, code lost:
        if ((r7 instanceof java.lang.String) == false) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x010e, code lost:
        r5 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0117, code lost:
        if (com.jimmydaddy.imagemarker.base.Utils.Companion.checkSpreadValue(r5, 1) == false) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0119, code lost:
        r3 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0121, code lost:
        throw new java.lang.Exception("padding is invalid");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0124, code lost:
        if ((r7 instanceof java.lang.Number) == false) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0126, code lost:
        r3 = r7.toString();
        r5 = r7.toString();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Padding(com.facebook.react.bridge.ReadableMap r18) {
        /*
            r17 = this;
            r0 = r17
            r17.<init>()
            java.lang.String r1 = "0"
            r0.paddingTop = r1
            r0.paddingLeft = r1
            r0.paddingBottom = r1
            r0.paddingRight = r1
            if (r18 == 0) goto L_0x0016
            java.util.Iterator r2 = r18.getEntryIterator()
            goto L_0x0017
        L_0x0016:
            r2 = 0
        L_0x0017:
            r3 = r1
            r4 = r3
            r5 = r4
            if (r2 == 0) goto L_0x0229
        L_0x001c:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L_0x0229
            java.lang.Object r6 = r2.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getValue()
            java.lang.Object r6 = r6.getKey()
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L_0x001c
            int r8 = r6.hashCode()
            java.lang.String r9 = "padding is invalid"
            r10 = 1
            switch(r8) {
                case -1501175880: goto L_0x01fd;
                case -806339567: goto L_0x0130;
                case -359890155: goto L_0x0100;
                case 90130308: goto L_0x00d4;
                case 202355100: goto L_0x00a8;
                case 713848971: goto L_0x007e;
                case 773277287: goto L_0x0075;
                case 773277288: goto L_0x0048;
                case 1343645351: goto L_0x003f;
                default: goto L_0x003e;
            }
        L_0x003e:
            goto L_0x001c
        L_0x003f:
            java.lang.String r8 = "paddingVertical"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x0051
            goto L_0x001c
        L_0x0048:
            java.lang.String r8 = "paddingY"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x0051
            goto L_0x001c
        L_0x0051:
            boolean r6 = r7 instanceof java.lang.String
            if (r6 == 0) goto L_0x0068
            com.jimmydaddy.imagemarker.base.Utils$Companion r1 = com.jimmydaddy.imagemarker.base.Utils.Companion
            r4 = r7
            java.lang.String r4 = (java.lang.String) r4
            boolean r1 = r1.checkSpreadValue(r4, r10)
            if (r1 == 0) goto L_0x0062
            r1 = r4
            goto L_0x001c
        L_0x0062:
            java.lang.Exception r1 = new java.lang.Exception
            r1.<init>(r9)
            throw r1
        L_0x0068:
            boolean r6 = r7 instanceof java.lang.Number
            if (r6 == 0) goto L_0x001c
            java.lang.String r1 = r7.toString()
            java.lang.String r4 = r7.toString()
            goto L_0x001c
        L_0x0075:
            java.lang.String r8 = "paddingX"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x010a
            goto L_0x001c
        L_0x007e:
            java.lang.String r8 = "paddingRight"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x0087
            goto L_0x001c
        L_0x0087:
            boolean r6 = r7 instanceof java.lang.String
            if (r6 == 0) goto L_0x009e
            com.jimmydaddy.imagemarker.base.Utils$Companion r5 = com.jimmydaddy.imagemarker.base.Utils.Companion
            r6 = r7
            java.lang.String r6 = (java.lang.String) r6
            boolean r5 = r5.checkSpreadValue(r6, r10)
            if (r5 == 0) goto L_0x0098
            r5 = r6
            goto L_0x001c
        L_0x0098:
            java.lang.Exception r1 = new java.lang.Exception
            r1.<init>(r9)
            throw r1
        L_0x009e:
            boolean r6 = r7 instanceof java.lang.Number
            if (r6 == 0) goto L_0x001c
            java.lang.String r5 = r7.toString()
            goto L_0x001c
        L_0x00a8:
            java.lang.String r8 = "paddingBottom"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x00b2
            goto L_0x001c
        L_0x00b2:
            boolean r6 = r7 instanceof java.lang.String
            if (r6 == 0) goto L_0x00ca
            com.jimmydaddy.imagemarker.base.Utils$Companion r4 = com.jimmydaddy.imagemarker.base.Utils.Companion
            r6 = r7
            java.lang.String r6 = (java.lang.String) r6
            boolean r4 = r4.checkSpreadValue(r6, r10)
            if (r4 == 0) goto L_0x00c4
            r4 = r6
            goto L_0x001c
        L_0x00c4:
            java.lang.Exception r1 = new java.lang.Exception
            r1.<init>(r9)
            throw r1
        L_0x00ca:
            boolean r6 = r7 instanceof java.lang.Number
            if (r6 == 0) goto L_0x001c
            java.lang.String r4 = r7.toString()
            goto L_0x001c
        L_0x00d4:
            java.lang.String r8 = "paddingTop"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x00de
            goto L_0x001c
        L_0x00de:
            boolean r6 = r7 instanceof java.lang.String
            if (r6 == 0) goto L_0x00f6
            com.jimmydaddy.imagemarker.base.Utils$Companion r1 = com.jimmydaddy.imagemarker.base.Utils.Companion
            r6 = r7
            java.lang.String r6 = (java.lang.String) r6
            boolean r1 = r1.checkSpreadValue(r6, r10)
            if (r1 == 0) goto L_0x00f0
            r1 = r6
            goto L_0x001c
        L_0x00f0:
            java.lang.Exception r1 = new java.lang.Exception
            r1.<init>(r9)
            throw r1
        L_0x00f6:
            boolean r6 = r7 instanceof java.lang.Number
            if (r6 == 0) goto L_0x001c
            java.lang.String r1 = r7.toString()
            goto L_0x001c
        L_0x0100:
            java.lang.String r8 = "paddingHorizontal"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x010a
            goto L_0x001c
        L_0x010a:
            boolean r6 = r7 instanceof java.lang.String
            if (r6 == 0) goto L_0x0122
            com.jimmydaddy.imagemarker.base.Utils$Companion r3 = com.jimmydaddy.imagemarker.base.Utils.Companion
            r5 = r7
            java.lang.String r5 = (java.lang.String) r5
            boolean r3 = r3.checkSpreadValue(r5, r10)
            if (r3 == 0) goto L_0x011c
            r3 = r5
            goto L_0x001c
        L_0x011c:
            java.lang.Exception r1 = new java.lang.Exception
            r1.<init>(r9)
            throw r1
        L_0x0122:
            boolean r6 = r7 instanceof java.lang.Number
            if (r6 == 0) goto L_0x001c
            java.lang.String r3 = r7.toString()
            java.lang.String r5 = r7.toString()
            goto L_0x001c
        L_0x0130:
            java.lang.String r8 = "padding"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x013a
            goto L_0x001c
        L_0x013a:
            boolean r6 = r7 instanceof java.lang.String
            if (r6 == 0) goto L_0x01e7
            java.lang.String r7 = (java.lang.String) r7
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            java.lang.CharSequence r6 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r7)
            java.lang.String r6 = r6.toString()
            com.jimmydaddy.imagemarker.base.Utils$Companion r7 = com.jimmydaddy.imagemarker.base.Utils.Companion
            r8 = 4
            boolean r7 = r7.checkSpreadValue(r6, r8)
            if (r7 == 0) goto L_0x01e1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r11 = r6
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            java.lang.String r6 = " "
            java.lang.String[] r12 = new java.lang.String[]{r6}
            r13 = 0
            r14 = 0
            r15 = 6
            r16 = 0
            java.util.List r6 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r11, (java.lang.String[]) r12, (boolean) r13, (int) r14, (int) r15, (java.lang.Object) r16)
            int r7 = r6.size()
            r9 = 0
            if (r7 == r10) goto L_0x01c7
            r11 = 2
            if (r7 == r11) goto L_0x01ad
            r12 = 3
            if (r7 == r12) goto L_0x0193
            if (r7 == r8) goto L_0x0179
            goto L_0x001c
        L_0x0179:
            java.lang.Object r1 = r6.get(r9)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r6.get(r10)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r6.get(r11)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r6.get(r12)
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x001c
        L_0x0193:
            java.lang.Object r1 = r6.get(r9)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r6.get(r10)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r6.get(r11)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r6.get(r10)
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x001c
        L_0x01ad:
            java.lang.Object r1 = r6.get(r9)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r6.get(r10)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r6.get(r9)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r6.get(r10)
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x001c
        L_0x01c7:
            java.lang.Object r1 = r6.get(r9)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r6.get(r9)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r4 = r6.get(r9)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r6.get(r9)
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x001c
        L_0x01e1:
            java.lang.Exception r1 = new java.lang.Exception
            r1.<init>(r9)
            throw r1
        L_0x01e7:
            boolean r6 = r7 instanceof java.lang.Number
            if (r6 == 0) goto L_0x001c
            java.lang.String r1 = r7.toString()
            java.lang.String r3 = r7.toString()
            java.lang.String r4 = r7.toString()
            java.lang.String r5 = r7.toString()
            goto L_0x001c
        L_0x01fd:
            java.lang.String r8 = "paddingLeft"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x0207
            goto L_0x001c
        L_0x0207:
            boolean r6 = r7 instanceof java.lang.String
            if (r6 == 0) goto L_0x021f
            com.jimmydaddy.imagemarker.base.Utils$Companion r3 = com.jimmydaddy.imagemarker.base.Utils.Companion
            r6 = r7
            java.lang.String r6 = (java.lang.String) r6
            boolean r3 = r3.checkSpreadValue(r6, r10)
            if (r3 == 0) goto L_0x0219
            r3 = r6
            goto L_0x001c
        L_0x0219:
            java.lang.Exception r1 = new java.lang.Exception
            r1.<init>(r9)
            throw r1
        L_0x021f:
            boolean r6 = r7 instanceof java.lang.Number
            if (r6 == 0) goto L_0x001c
            java.lang.String r3 = r7.toString()
            goto L_0x001c
        L_0x0229:
            r0.paddingTop = r1
            r0.paddingLeft = r3
            r0.paddingBottom = r4
            r0.paddingRight = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jimmydaddy.imagemarker.base.Padding.<init>(com.facebook.react.bridge.ReadableMap):void");
    }

    public final MarkerInsets toEdgeInsets(int i, int i2) {
        float f = (float) i2;
        float f2 = (float) i;
        return new MarkerInsets((int) Utils.Companion.parseSpreadValue(this.paddingTop, f), (int) Utils.Companion.parseSpreadValue(this.paddingLeft, f2), (int) Utils.Companion.parseSpreadValue(this.paddingBottom, f), (int) Utils.Companion.parseSpreadValue(this.paddingRight, f2));
    }
}
