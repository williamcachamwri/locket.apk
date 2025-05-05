package com.horcrux.svg;

import android.view.View;
import java.util.ArrayList;

class TextLayoutAlgorithm {
    TextLayoutAlgorithm() {
    }

    class CharacterInformation {
        boolean addressable = true;
        double advance;
        boolean anchoredChunk = false;
        char character;
        TextView element;
        boolean firstCharacterInResolvedDescendant = false;
        boolean hidden = false;
        int index;
        boolean middle = false;
        boolean resolved = false;
        double rotate = 0.0d;
        boolean rotateSpecified = false;
        double x = 0.0d;
        boolean xSpecified = false;
        double y = 0.0d;
        boolean ySpecified = false;

        CharacterInformation(int i, char c) {
            this.index = i;
            this.character = c;
        }
    }

    class LayoutInput {
        boolean horizontal;
        TextView text;

        LayoutInput() {
        }
    }

    private void getSubTreeTypographicCharacterPositions(ArrayList<TextPathView> arrayList, ArrayList<TextView> arrayList2, StringBuilder sb, View view, TextPathView textPathView) {
        int i = 0;
        if (view instanceof TSpanView) {
            TSpanView tSpanView = (TSpanView) view;
            String str = tSpanView.mContent;
            if (str == null) {
                while (i < tSpanView.getChildCount()) {
                    getSubTreeTypographicCharacterPositions(arrayList, arrayList2, sb, tSpanView.getChildAt(i), textPathView);
                    i++;
                }
                return;
            }
            while (i < str.length()) {
                arrayList2.add(tSpanView);
                arrayList.add(textPathView);
                i++;
            }
            sb.append(str);
            return;
        }
        if (view instanceof TextPathView) {
            textPathView = (TextPathView) view;
        }
        while (i < textPathView.getChildCount()) {
            getSubTreeTypographicCharacterPositions(arrayList, arrayList2, sb, textPathView.getChildAt(i), textPathView);
            i++;
        }
    }

    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4, types: [android.graphics.Canvas, android.graphics.Paint] */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x019f, code lost:
        if (r18 == Double.POSITIVE_INFINITY) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01cc, code lost:
        if (r9 == com.horcrux.svg.TextProperties.Direction.ltr) goto L_0x01de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01d9, code lost:
        if (r9 == com.horcrux.svg.TextProperties.Direction.ltr) goto L_0x01db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01de, code lost:
        r14 = r14 - r16;
     */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0300  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0330  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x01e4  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01e9 A[LOOP:6: B:87:0x01e7->B:88:0x01e9, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.horcrux.svg.TextLayoutAlgorithm.CharacterInformation[] layoutText(com.horcrux.svg.TextLayoutAlgorithm.LayoutInput r30) {
        /*
            r29 = this;
            r8 = r29
            r0 = r30
            com.horcrux.svg.TextView r9 = r0.text
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            r5 = 0
            r0 = r29
            r1 = r10
            r3 = r6
            r4 = r9
            r0.getSubTreeTypographicCharacterPositions(r1, r2, r3, r4, r5)
            java.lang.String r0 = r6.toString()
            char[] r0 = r0.toCharArray()
            int r11 = r0.length
            com.horcrux.svg.TextLayoutAlgorithm$CharacterInformation[] r12 = new com.horcrux.svg.TextLayoutAlgorithm.CharacterInformation[r11]
            r13 = 0
            r1 = r13
        L_0x002b:
            if (r1 >= r11) goto L_0x0039
            com.horcrux.svg.TextLayoutAlgorithm$CharacterInformation r2 = new com.horcrux.svg.TextLayoutAlgorithm$CharacterInformation
            char r3 = r0[r1]
            r2.<init>(r1, r3)
            r12[r1] = r2
            int r1 = r1 + 1
            goto L_0x002b
        L_0x0039:
            if (r11 != 0) goto L_0x003c
            return r12
        L_0x003c:
            android.graphics.PointF[] r14 = new android.graphics.PointF[r11]
            r0 = r13
        L_0x003f:
            r15 = 0
            if (r0 >= r11) goto L_0x004c
            android.graphics.PointF r1 = new android.graphics.PointF
            r1.<init>(r15, r15)
            r14[r0] = r1
            int r0 = r0 + 1
            goto L_0x003f
        L_0x004c:
            r0 = r13
        L_0x004d:
            r7 = 1
            if (r0 >= r11) goto L_0x0080
            r1 = r12[r0]
            r1.addressable = r7
            r1 = r12[r0]
            r1.middle = r13
            r1 = r12[r0]
            if (r0 != 0) goto L_0x005d
            goto L_0x005e
        L_0x005d:
            r7 = r13
        L_0x005e:
            r1.anchoredChunk = r7
            r1 = r12[r0]
            boolean r1 = r1.addressable
            if (r1 == 0) goto L_0x0072
            r1 = r12[r0]
            boolean r1 = r1.middle
            if (r1 != 0) goto L_0x0072
            r1 = r14[r0]
            r1.set(r15, r15)
            goto L_0x007d
        L_0x0072:
            if (r0 <= 0) goto L_0x007d
            r1 = r14[r0]
            int r2 = r0 + -1
            r2 = r14[r2]
            r1.set(r2)
        L_0x007d:
            int r0 = r0 + 1
            goto L_0x004d
        L_0x0080:
            java.lang.String[] r6 = new java.lang.String[r11]
            java.lang.String[] r5 = new java.lang.String[r11]
            java.lang.String[] r4 = new java.lang.String[r11]
            java.lang.String[] r3 = new java.lang.String[r11]
            com.horcrux.svg.TextLayoutAlgorithm$1CharacterPositioningResolver r0 = new com.horcrux.svg.TextLayoutAlgorithm$1CharacterPositioningResolver
            r16 = 0
            r1 = r29
            r2 = r12
            r17 = r3
            r3 = r6
            r18 = r4
            r4 = r5
            r19 = r5
            r5 = r18
            r18 = r6
            r6 = r17
            r7 = r16
            r0.<init>(r2, r3, r4, r5, r6)
            android.graphics.PointF r0 = new android.graphics.PointF
            r0.<init>(r15, r15)
            r1 = r13
        L_0x00a8:
            if (r1 >= r11) goto L_0x00f3
            r2 = r18[r1]
            java.lang.String r3 = ""
            boolean r2 = r2.equals(r3)
            java.lang.String r4 = "0"
            if (r2 == 0) goto L_0x00b8
            r18[r1] = r4
        L_0x00b8:
            r2 = r19[r1]
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00c2
            r19[r1] = r4
        L_0x00c2:
            float r2 = r0.x
            r3 = r18[r1]
            float r3 = java.lang.Float.parseFloat(r3)
            float r2 = r2 + r3
            r0.x = r2
            float r2 = r0.y
            r3 = r19[r1]
            float r3 = java.lang.Float.parseFloat(r3)
            float r2 = r2 + r3
            r0.y = r2
            r2 = r12[r1]
            r3 = r14[r1]
            float r3 = r3.x
            float r4 = r0.x
            float r3 = r3 + r4
            double r3 = (double) r3
            r2.x = r3
            r2 = r12[r1]
            r3 = r14[r1]
            float r3 = r3.y
            float r4 = r0.y
            float r3 = r3 + r4
            double r3 = (double) r3
            r2.y = r3
            int r1 = r1 + 1
            goto L_0x00a8
        L_0x00f3:
            com.horcrux.svg.TextLayoutAlgorithm$1TextLengthResolver r1 = new com.horcrux.svg.TextLayoutAlgorithm$1TextLengthResolver
            r1.<init>(r12)
            r1.resolveTextLength(r9)
            r0.set(r15, r15)
            r7 = 1
        L_0x00ff:
            if (r7 >= r11) goto L_0x0151
            r1 = r18[r7]
            if (r1 == 0) goto L_0x0111
            double r1 = java.lang.Double.parseDouble(r1)
            r3 = r12[r7]
            double r3 = r3.x
            double r1 = r1 - r3
            float r1 = (float) r1
            r0.x = r1
        L_0x0111:
            r1 = r19[r7]
            if (r1 == 0) goto L_0x0121
            double r1 = java.lang.Double.parseDouble(r1)
            r3 = r12[r7]
            double r3 = r3.y
            double r1 = r1 - r3
            float r1 = (float) r1
            r0.y = r1
        L_0x0121:
            r1 = r12[r7]
            double r2 = r1.x
            float r4 = r0.x
            double r4 = (double) r4
            double r2 = r2 + r4
            r1.x = r2
            r1 = r12[r7]
            double r2 = r1.y
            float r4 = r0.y
            double r4 = (double) r4
            double r2 = r2 + r4
            r1.y = r2
            r1 = r12[r7]
            boolean r1 = r1.middle
            if (r1 == 0) goto L_0x0145
            r1 = r12[r7]
            boolean r1 = r1.anchoredChunk
            if (r1 == 0) goto L_0x0145
            r1 = r12[r7]
            r1.anchoredChunk = r13
        L_0x0145:
            int r7 = r7 + 1
            if (r7 >= r11) goto L_0x014f
            r1 = r12[r7]
            r2 = 1
            r1.anchoredChunk = r2
            goto L_0x00ff
        L_0x014f:
            r2 = 1
            goto L_0x00ff
        L_0x0151:
            r2 = 1
            r5 = r13
            r9 = r5
            r6 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            r16 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            r18 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            r20 = -4503599627370496(0xfff0000000000000, double:-Infinity)
        L_0x015c:
            r22 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r5 >= r11) goto L_0x020d
            r1 = r12[r5]
            boolean r1 = r1.addressable
            if (r1 != 0) goto L_0x0168
            goto L_0x0204
        L_0x0168:
            r1 = r12[r5]
            boolean r1 = r1.anchoredChunk
            if (r1 == 0) goto L_0x0175
            r18 = r6
            r6 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            r13 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            goto L_0x0179
        L_0x0175:
            r13 = r16
            r16 = r20
        L_0x0179:
            r1 = r12[r5]
            double r0 = r1.x
            r15 = r12[r5]
            double r2 = r15.advance
            double r2 = r2 + r0
            r4 = r9
            double r8 = java.lang.Math.min(r0, r2)
            double r6 = java.lang.Math.min(r6, r8)
            double r0 = java.lang.Math.max(r0, r2)
            double r0 = java.lang.Math.max(r13, r0)
            if (r5 <= 0) goto L_0x01a2
            r2 = r12[r5]
            boolean r2 = r2.anchoredChunk
            if (r2 == 0) goto L_0x01a2
            r2 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            int r8 = (r18 > r2 ? 1 : (r18 == r2 ? 0 : -1))
            if (r8 != 0) goto L_0x01a8
            goto L_0x01a4
        L_0x01a2:
            r2 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
        L_0x01a4:
            int r8 = r11 + -1
            if (r5 != r8) goto L_0x01fb
        L_0x01a8:
            com.horcrux.svg.TextProperties$TextAnchor r8 = com.horcrux.svg.TextProperties.TextAnchor.start
            com.horcrux.svg.TextProperties$Direction r9 = com.horcrux.svg.TextProperties.Direction.ltr
            int r13 = r11 + -1
            if (r5 != r13) goto L_0x01b4
            r16 = r0
            r18 = r6
        L_0x01b4:
            r14 = r12[r4]
            double r14 = r14.x
            int[] r26 = com.horcrux.svg.TextLayoutAlgorithm.AnonymousClass1.$SwitchMap$com$horcrux$svg$TextProperties$TextAnchor
            int r8 = r8.ordinal()
            r8 = r26[r8]
            r2 = 1
            if (r8 == r2) goto L_0x01d7
            r2 = 2
            if (r8 == r2) goto L_0x01cf
            r2 = 3
            if (r8 == r2) goto L_0x01ca
            goto L_0x01e0
        L_0x01ca:
            com.horcrux.svg.TextProperties$Direction r2 = com.horcrux.svg.TextProperties.Direction.ltr
            if (r9 != r2) goto L_0x01db
            goto L_0x01de
        L_0x01cf:
            com.horcrux.svg.TextProperties$Direction r2 = com.horcrux.svg.TextProperties.Direction.ltr
            double r2 = r18 + r16
            double r2 = r2 / r22
            double r14 = r14 - r2
            goto L_0x01e0
        L_0x01d7:
            com.horcrux.svg.TextProperties$Direction r2 = com.horcrux.svg.TextProperties.Direction.ltr
            if (r9 != r2) goto L_0x01de
        L_0x01db:
            double r14 = r14 - r18
            goto L_0x01e0
        L_0x01de:
            double r14 = r14 - r16
        L_0x01e0:
            if (r5 != r13) goto L_0x01e4
            r2 = r5
            goto L_0x01e6
        L_0x01e4:
            int r2 = r5 + -1
        L_0x01e6:
            r9 = r4
        L_0x01e7:
            if (r9 > r2) goto L_0x01f7
            r3 = r12[r9]
            r20 = r0
            double r0 = r3.x
            double r0 = r0 + r14
            r3.x = r0
            int r9 = r9 + 1
            r0 = r20
            goto L_0x01e7
        L_0x01f7:
            r20 = r0
            r9 = r5
            goto L_0x01fe
        L_0x01fb:
            r20 = r0
            r9 = r4
        L_0x01fe:
            r27 = r16
            r16 = r20
            r20 = r27
        L_0x0204:
            int r5 = r5 + 1
            r8 = r29
            r2 = 1
            r13 = 0
            r15 = 0
            goto L_0x015c
        L_0x020d:
            r4 = r9
            android.graphics.PointF r0 = new android.graphics.PointF
            r2 = 0
            r0.<init>(r2, r2)
            android.graphics.PathMeasure r2 = new android.graphics.PathMeasure
            r2.<init>()
            r3 = 0
            r8 = r3
            r5 = 0
            r6 = 0
            r7 = 0
        L_0x021e:
            if (r6 >= r11) goto L_0x03ba
            java.lang.Object r9 = r10.get(r6)
            com.horcrux.svg.TextPathView r9 = (com.horcrux.svg.TextPathView) r9
            if (r9 == 0) goto L_0x035b
            r13 = r12[r6]
            boolean r13 = r13.addressable
            if (r13 == 0) goto L_0x035b
            android.graphics.Path r8 = r9.getTextPath(r3, r3)
            r5 = r12[r6]
            boolean r5 = r5.middle
            if (r5 != 0) goto L_0x0333
            r9.getSide()
            com.horcrux.svg.TextProperties$TextPathSide r5 = com.horcrux.svg.TextProperties.TextPathSide.right
            r5 = 0
            r2.setPath(r8, r5)
            float r5 = r2.getLength()
            double r13 = (double) r5
            com.horcrux.svg.SVGLength r5 = r9.getStartOffset()
            r15 = r4
            double r3 = r5.value
            r5 = r12[r6]
            r17 = r2
            double r1 = r5.advance
            r5 = r12[r6]
            r18 = r7
            r19 = r8
            double r7 = r5.x
            r5 = r12[r6]
            r24 = r10
            r25 = r11
            double r10 = r5.y
            r5 = r12[r6]
            double r10 = r5.rotate
            double r1 = r1 / r22
            double r7 = r7 + r1
            double r7 = r7 + r3
            boolean r1 = r17.isClosed()
            r2 = 0
            if (r1 != 0) goto L_0x0280
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 < 0) goto L_0x027b
            int r1 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r1 <= 0) goto L_0x0280
        L_0x027b:
            r1 = r12[r6]
            r4 = 1
            r1.hidden = r4
        L_0x0280:
            boolean r1 = r17.isClosed()
            if (r1 == 0) goto L_0x02f8
            com.horcrux.svg.TextProperties$TextAnchor r1 = com.horcrux.svg.TextProperties.TextAnchor.start
            com.horcrux.svg.TextProperties$Direction r4 = com.horcrux.svg.TextProperties.Direction.ltr
            r5 = r12[r15]
            double r10 = r5.x
            int[] r5 = com.horcrux.svg.TextLayoutAlgorithm.AnonymousClass1.$SwitchMap$com$horcrux$svg$TextProperties$TextAnchor
            int r1 = r1.ordinal()
            r1 = r5[r1]
            r5 = 1
            if (r1 == r5) goto L_0x02d6
            r5 = 2
            if (r1 == r5) goto L_0x02c1
            r10 = 3
            if (r1 == r10) goto L_0x02a0
            goto L_0x02f8
        L_0x02a0:
            com.horcrux.svg.TextProperties$Direction r1 = com.horcrux.svg.TextProperties.Direction.ltr
            if (r4 != r1) goto L_0x02b3
            double r4 = -r13
            int r1 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r1 < 0) goto L_0x02ad
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x02f8
        L_0x02ad:
            r1 = r12[r6]
            r4 = 1
            r1.hidden = r4
            goto L_0x02f9
        L_0x02b3:
            r4 = 1
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 < 0) goto L_0x02bc
            int r1 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r1 <= 0) goto L_0x02f9
        L_0x02bc:
            r1 = r12[r6]
            r1.hidden = r4
            goto L_0x02f9
        L_0x02c1:
            r10 = 3
            double r1 = -r13
            double r1 = r1 / r22
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x02cf
            double r1 = r13 / r22
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x02f8
        L_0x02cf:
            r1 = r12[r6]
            r2 = 1
            r1.hidden = r2
            r4 = r2
            goto L_0x02f9
        L_0x02d6:
            r10 = 3
            com.horcrux.svg.TextProperties$Direction r1 = com.horcrux.svg.TextProperties.Direction.ltr
            if (r4 != r1) goto L_0x02e9
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 < 0) goto L_0x02e3
            int r1 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r1 <= 0) goto L_0x02f8
        L_0x02e3:
            r1 = r12[r6]
            r4 = 1
            r1.hidden = r4
            goto L_0x02f9
        L_0x02e9:
            r4 = 1
            double r10 = -r13
            int r1 = (r7 > r10 ? 1 : (r7 == r10 ? 0 : -1))
            if (r1 < 0) goto L_0x02f3
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x02f9
        L_0x02f3:
            r1 = r12[r6]
            r1.hidden = r4
            goto L_0x02f9
        L_0x02f8:
            r4 = 1
        L_0x02f9:
            double r7 = r7 % r13
            r1 = r12[r6]
            boolean r1 = r1.hidden
            if (r1 != 0) goto L_0x0330
            r1 = 2
            float[] r2 = new float[r1]
            float[] r3 = new float[r1]
            float r1 = (float) r7
            r7 = r17
            r7.getPosTan(r1, r2, r3)
            r1 = r3[r4]
            double r1 = (double) r1
            r4 = 0
            r3 = r3[r4]
            double r3 = (double) r3
            double r1 = java.lang.Math.atan2(r1, r3)
            r3 = 4633260481411531256(0x404ca5dc1a63c1f8, double:57.29577951308232)
            double r1 = r1 * r3
            r3 = 4636033603912859648(0x4056800000000000, double:90.0)
            double r3 = r3 + r1
            java.lang.Math.cos(r3)
            java.lang.Math.sin(r3)
            r3 = r12[r6]
            double r4 = r3.rotate
            double r4 = r4 + r1
            r3.rotate = r4
            goto L_0x0357
        L_0x0330:
            r7 = r17
            goto L_0x0357
        L_0x0333:
            r15 = r4
            r18 = r7
            r19 = r8
            r24 = r10
            r25 = r11
            r7 = r2
            r1 = r12[r6]
            int r2 = r6 + -1
            r3 = r12[r2]
            double r3 = r3.x
            r1.x = r3
            r1 = r12[r6]
            r3 = r12[r2]
            double r3 = r3.y
            r1.y = r3
            r1 = r12[r6]
            r2 = r12[r2]
            double r2 = r2.rotate
            r1.rotate = r2
        L_0x0357:
            r8 = r19
            r5 = 1
            goto L_0x0363
        L_0x035b:
            r15 = r4
            r18 = r7
            r24 = r10
            r25 = r11
            r7 = r2
        L_0x0363:
            if (r9 != 0) goto L_0x03ab
            r1 = r12[r6]
            boolean r1 = r1.addressable
            if (r1 == 0) goto L_0x03ab
            r1 = 0
            if (r5 == 0) goto L_0x0388
            r7.setPath(r8, r1)
            r2 = 2
            float[] r3 = new float[r2]
            float r4 = r7.getLength()
            r9 = 0
            r7.getPosTan(r4, r3, r9)
            r4 = r3[r1]
            r10 = 1
            r3 = r3[r10]
            r0.set(r4, r3)
            r5 = r1
            r18 = r10
            goto L_0x038b
        L_0x0388:
            r2 = 2
            r9 = 0
            r10 = 1
        L_0x038b:
            if (r18 == 0) goto L_0x03ad
            r3 = r12[r6]
            boolean r3 = r3.anchoredChunk
            if (r3 == 0) goto L_0x0396
            r18 = r1
            goto L_0x03ad
        L_0x0396:
            r3 = r12[r6]
            double r13 = r3.x
            float r4 = r0.x
            double r1 = (double) r4
            double r13 = r13 + r1
            r3.x = r13
            r1 = r12[r6]
            double r2 = r1.y
            float r4 = r0.y
            double r13 = (double) r4
            double r2 = r2 + r13
            r1.y = r2
            goto L_0x03ad
        L_0x03ab:
            r9 = 0
            r10 = 1
        L_0x03ad:
            int r6 = r6 + 1
            r2 = r7
            r3 = r9
            r4 = r15
            r7 = r18
            r10 = r24
            r11 = r25
            goto L_0x021e
        L_0x03ba:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TextLayoutAlgorithm.layoutText(com.horcrux.svg.TextLayoutAlgorithm$LayoutInput):com.horcrux.svg.TextLayoutAlgorithm$CharacterInformation[]");
    }

    /* renamed from: com.horcrux.svg.TextLayoutAlgorithm$1CharacterPositioningResolver  reason: invalid class name */
    class AnonymousClass1CharacterPositioningResolver {
        private int global;
        private boolean horizontal;
        private boolean in_text_path;
        private String[] resolve_dx;
        private String[] resolve_dy;
        private String[] resolve_x;
        private String[] resolve_y;
        private CharacterInformation[] result;

        private AnonymousClass1CharacterPositioningResolver(CharacterInformation[] characterInformationArr, String[] strArr, String[] strArr2, String[] strArr3, String[] strArr4) {
            this.global = 0;
            this.horizontal = true;
            this.in_text_path = false;
            this.result = characterInformationArr;
            this.resolve_x = strArr;
            this.resolve_y = strArr2;
            this.resolve_dx = strArr3;
            this.resolve_dy = strArr4;
        }

        private void resolveCharacterPositioning(TextView textView) {
            int i;
            int i2;
            TextView textView2 = textView;
            boolean z = true;
            if (textView.getClass() == TextView.class || textView.getClass() == TSpanView.class) {
                int i3 = this.global;
                String[] strArr = new String[0];
                String[] strArr2 = new String[0];
                String[] strArr3 = new String[0];
                String[] strArr4 = new String[0];
                double[] dArr = new double[0];
                if (!this.in_text_path) {
                    i = Math.max(0, 0);
                } else {
                    i = 0;
                }
                String str = ((TSpanView) textView2).mContent;
                if (str == null) {
                    i2 = 0;
                } else {
                    i2 = str.length();
                }
                int i4 = 0;
                int i5 = 0;
                while (i4 < i2) {
                    int i6 = i3 + i4;
                    if (this.result[i6].addressable) {
                        this.result[i6].anchoredChunk = i5 < i ? z : false;
                        if (i5 < 0) {
                            this.resolve_x[i6] = strArr[i5];
                        }
                        boolean z2 = this.in_text_path;
                        if (z2 && !this.horizontal) {
                            this.resolve_x[i3] = "";
                        }
                        if (i5 < 0) {
                            this.resolve_y[i6] = strArr2[i5];
                        }
                        if (z2 && this.horizontal) {
                            this.resolve_y[i3] = "";
                        }
                        if (i5 < 0) {
                            this.resolve_dx[i6] = strArr3[i5];
                        }
                        if (i5 < 0) {
                            this.resolve_dy[i6] = strArr4[i5];
                        }
                        if (i5 < 0) {
                            this.result[i6].rotate = dArr[i5];
                        }
                    }
                    i5++;
                    i4++;
                    z = true;
                }
            } else if (textView.getClass() == TextPathView.class) {
                this.result[this.global].anchoredChunk = true;
                this.in_text_path = true;
                for (int i7 = 0; i7 < textView.getChildCount(); i7++) {
                    resolveCharacterPositioning((TextView) textView2.getChildAt(i7));
                }
                if (textView2 instanceof TextPathView) {
                    this.in_text_path = false;
                }
            }
        }
    }

    /* renamed from: com.horcrux.svg.TextLayoutAlgorithm$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.horcrux.svg.TextProperties$TextAnchor[] r0 = com.horcrux.svg.TextProperties.TextAnchor.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor = r0
                com.horcrux.svg.TextProperties$TextAnchor r1 = com.horcrux.svg.TextProperties.TextAnchor.start     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor     // Catch:{ NoSuchFieldError -> 0x001d }
                com.horcrux.svg.TextProperties$TextAnchor r1 = com.horcrux.svg.TextProperties.TextAnchor.middle     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$horcrux$svg$TextProperties$TextAnchor     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.horcrux.svg.TextProperties$TextAnchor r1 = com.horcrux.svg.TextProperties.TextAnchor.end     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.TextLayoutAlgorithm.AnonymousClass1.<clinit>():void");
        }
    }
}
