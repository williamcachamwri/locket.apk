package com.facebook.react.animated;

import androidx.core.graphics.ColorUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InterpolationAnimatedNode extends ValueAnimatedNode {
    private static final String COLOR_OUTPUT_TYPE = "color";
    public static final String EXTRAPOLATE_TYPE_CLAMP = "clamp";
    public static final String EXTRAPOLATE_TYPE_EXTEND = "extend";
    public static final String EXTRAPOLATE_TYPE_IDENTITY = "identity";
    private static final Pattern sNumericPattern = Pattern.compile("[+-]?(\\d+\\.?\\d*|\\.\\d+)([eE][+-]?\\d+)?");
    private final String mExtrapolateLeft;
    private final String mExtrapolateRight;
    private final double[] mInputRange;
    private Object mObjectValue;
    private final Object mOutputRange;
    private final OutputType mOutputType;
    private ValueAnimatedNode mParent;
    private final String mPattern;

    private enum OutputType {
        Number,
        Color,
        String
    }

    private static double[] fromDoubleArray(ReadableArray readableArray) {
        int size = readableArray.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = readableArray.getDouble(i);
        }
        return dArr;
    }

    private static int[] fromIntArray(ReadableArray readableArray) {
        int size = readableArray.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = readableArray.getInt(i);
        }
        return iArr;
    }

    private static double[][] fromStringPattern(ReadableArray readableArray) {
        int size = readableArray.size();
        double[][] dArr = new double[size][];
        Matcher matcher = sNumericPattern.matcher(readableArray.getString(0));
        ArrayList arrayList = new ArrayList();
        while (matcher.find()) {
            arrayList.add(Double.valueOf(Double.parseDouble(matcher.group())));
        }
        int size2 = arrayList.size();
        double[] dArr2 = new double[size2];
        for (int i = 0; i < arrayList.size(); i++) {
            dArr2[i] = ((Double) arrayList.get(i)).doubleValue();
        }
        dArr[0] = dArr2;
        for (int i2 = 1; i2 < size; i2++) {
            double[] dArr3 = new double[size2];
            Matcher matcher2 = sNumericPattern.matcher(readableArray.getString(i2));
            int i3 = 0;
            while (matcher2.find() && i3 < size2) {
                dArr3[i3] = Double.parseDouble(matcher2.group());
                i3++;
            }
            dArr[i2] = dArr3;
        }
        return dArr;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006e, code lost:
        if (r1.equals(EXTRAPOLATE_TYPE_CLAMP) == false) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double interpolate(double r13, double r15, double r17, double r19, double r21, java.lang.String r23, java.lang.String r24) {
        /*
            r0 = r23
            r1 = r24
            int r2 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            java.lang.String r3 = "Invalid extrapolation type "
            r4 = 2
            java.lang.String r5 = "clamp"
            r6 = 1
            java.lang.String r7 = "identity"
            r8 = 0
            java.lang.String r9 = "extend"
            r10 = -1
            if (r2 >= 0) goto L_0x0059
            r23.hashCode()
            int r11 = r23.hashCode()
            switch(r11) {
                case -1289044198: goto L_0x0032;
                case -135761730: goto L_0x0029;
                case 94742715: goto L_0x0020;
                default: goto L_0x001e;
            }
        L_0x001e:
            r11 = r10
            goto L_0x003a
        L_0x0020:
            boolean r11 = r0.equals(r5)
            if (r11 != 0) goto L_0x0027
            goto L_0x001e
        L_0x0027:
            r11 = r4
            goto L_0x003a
        L_0x0029:
            boolean r11 = r0.equals(r7)
            if (r11 != 0) goto L_0x0030
            goto L_0x001e
        L_0x0030:
            r11 = r6
            goto L_0x003a
        L_0x0032:
            boolean r11 = r0.equals(r9)
            if (r11 != 0) goto L_0x0039
            goto L_0x001e
        L_0x0039:
            r11 = r8
        L_0x003a:
            switch(r11) {
                case 0: goto L_0x0059;
                case 1: goto L_0x0058;
                case 2: goto L_0x0056;
                default: goto L_0x003d;
            }
        L_0x003d:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r1 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r2 = "for left extrapolation"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0056:
            r11 = r15
            goto L_0x005a
        L_0x0058:
            return r13
        L_0x0059:
            r11 = r13
        L_0x005a:
            int r0 = (r11 > r17 ? 1 : (r11 == r17 ? 0 : -1))
            if (r0 <= 0) goto L_0x00a2
            r24.hashCode()
            int r0 = r24.hashCode()
            switch(r0) {
                case -1289044198: goto L_0x007a;
                case -135761730: goto L_0x0071;
                case 94742715: goto L_0x006a;
                default: goto L_0x0068;
            }
        L_0x0068:
            r4 = r10
            goto L_0x0082
        L_0x006a:
            boolean r0 = r1.equals(r5)
            if (r0 != 0) goto L_0x0082
            goto L_0x0068
        L_0x0071:
            boolean r0 = r1.equals(r7)
            if (r0 != 0) goto L_0x0078
            goto L_0x0068
        L_0x0078:
            r4 = r6
            goto L_0x0082
        L_0x007a:
            boolean r0 = r1.equals(r9)
            if (r0 != 0) goto L_0x0081
            goto L_0x0068
        L_0x0081:
            r4 = r8
        L_0x0082:
            switch(r4) {
                case 0: goto L_0x00a2;
                case 1: goto L_0x00a1;
                case 2: goto L_0x009e;
                default: goto L_0x0085;
            }
        L_0x0085:
            com.facebook.react.bridge.JSApplicationIllegalArgumentException r0 = new com.facebook.react.bridge.JSApplicationIllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r2 = "for right extrapolation"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x009e:
            r11 = r17
            goto L_0x00a2
        L_0x00a1:
            return r11
        L_0x00a2:
            int r0 = (r19 > r21 ? 1 : (r19 == r21 ? 0 : -1))
            if (r0 != 0) goto L_0x00a7
            return r19
        L_0x00a7:
            int r0 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r0 != 0) goto L_0x00af
            if (r2 > 0) goto L_0x00ae
            return r19
        L_0x00ae:
            return r21
        L_0x00af:
            double r0 = r21 - r19
            double r11 = r11 - r15
            double r0 = r0 * r11
            double r2 = r17 - r15
            double r0 = r0 / r2
            double r0 = r19 + r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.InterpolationAnimatedNode.interpolate(double, double, double, double, double, java.lang.String, java.lang.String):double");
    }

    static double interpolate(double d, double[] dArr, double[] dArr2, String str, String str2) {
        int findRangeIndex = findRangeIndex(d, dArr);
        int i = findRangeIndex + 1;
        return interpolate(d, dArr[findRangeIndex], dArr[i], dArr2[findRangeIndex], dArr2[i], str, str2);
    }

    static int interpolateColor(double d, double[] dArr, int[] iArr) {
        int findRangeIndex = findRangeIndex(d, dArr);
        int i = iArr[findRangeIndex];
        int i2 = findRangeIndex + 1;
        int i3 = iArr[i2];
        if (i == i3) {
            return i;
        }
        double d2 = dArr[findRangeIndex];
        double d3 = dArr[i2];
        if (d2 == d3) {
            return d <= d2 ? i : i3;
        }
        return ColorUtils.blendARGB(i, i3, (float) ((d - d2) / (d3 - d2)));
    }

    static String interpolateString(String str, double d, double[] dArr, double[][] dArr2, String str2, String str3) {
        int findRangeIndex = findRangeIndex(d, dArr);
        StringBuffer stringBuffer = new StringBuffer(str.length());
        Matcher matcher = sNumericPattern.matcher(str);
        int i = 0;
        while (matcher.find()) {
            double[] dArr3 = dArr2[findRangeIndex];
            if (i >= dArr3.length) {
                break;
            }
            int i2 = findRangeIndex + 1;
            double interpolate = interpolate(d, dArr[findRangeIndex], dArr[i2], dArr3[i], dArr2[i2][i], str2, str3);
            int i3 = (int) interpolate;
            matcher.appendReplacement(stringBuffer, ((double) i3) != interpolate ? Double.toString(interpolate) : Integer.toString(i3));
            i++;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static int findRangeIndex(double d, double[] dArr) {
        int i = 1;
        while (i < dArr.length - 1 && dArr[i] < d) {
            i++;
        }
        return i - 1;
    }

    public InterpolationAnimatedNode(ReadableMap readableMap) {
        this.mInputRange = fromDoubleArray(readableMap.getArray("inputRange"));
        ReadableArray array = readableMap.getArray("outputRange");
        if ("color".equals(readableMap.getString("outputType"))) {
            this.mOutputType = OutputType.Color;
            this.mOutputRange = fromIntArray(array);
            this.mPattern = null;
        } else if (array.getType(0) == ReadableType.String) {
            this.mOutputType = OutputType.String;
            this.mOutputRange = fromStringPattern(array);
            this.mPattern = array.getString(0);
        } else {
            this.mOutputType = OutputType.Number;
            this.mOutputRange = fromDoubleArray(array);
            this.mPattern = null;
        }
        this.mExtrapolateLeft = readableMap.getString("extrapolateLeft");
        this.mExtrapolateRight = readableMap.getString("extrapolateRight");
    }

    public void onAttachedToNode(AnimatedNode animatedNode) {
        if (this.mParent != null) {
            throw new IllegalStateException("Parent already attached");
        } else if (animatedNode instanceof ValueAnimatedNode) {
            this.mParent = (ValueAnimatedNode) animatedNode;
        } else {
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
    }

    public void onDetachedFromNode(AnimatedNode animatedNode) {
        if (animatedNode == this.mParent) {
            this.mParent = null;
            return;
        }
        throw new IllegalArgumentException("Invalid parent node provided");
    }

    public void update() {
        ValueAnimatedNode valueAnimatedNode = this.mParent;
        if (valueAnimatedNode != null) {
            double value = valueAnimatedNode.getValue();
            int i = AnonymousClass1.$SwitchMap$com$facebook$react$animated$InterpolationAnimatedNode$OutputType[this.mOutputType.ordinal()];
            if (i == 1) {
                this.mValue = interpolate(value, this.mInputRange, (double[]) this.mOutputRange, this.mExtrapolateLeft, this.mExtrapolateRight);
            } else if (i == 2) {
                this.mObjectValue = Integer.valueOf(interpolateColor(value, this.mInputRange, (int[]) this.mOutputRange));
            } else if (i == 3) {
                this.mObjectValue = interpolateString(this.mPattern, value, this.mInputRange, (double[][]) this.mOutputRange, this.mExtrapolateLeft, this.mExtrapolateRight);
            }
        }
    }

    /* renamed from: com.facebook.react.animated.InterpolationAnimatedNode$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$animated$InterpolationAnimatedNode$OutputType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.facebook.react.animated.InterpolationAnimatedNode$OutputType[] r0 = com.facebook.react.animated.InterpolationAnimatedNode.OutputType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$animated$InterpolationAnimatedNode$OutputType = r0
                com.facebook.react.animated.InterpolationAnimatedNode$OutputType r1 = com.facebook.react.animated.InterpolationAnimatedNode.OutputType.Number     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$animated$InterpolationAnimatedNode$OutputType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.animated.InterpolationAnimatedNode$OutputType r1 = com.facebook.react.animated.InterpolationAnimatedNode.OutputType.Color     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$animated$InterpolationAnimatedNode$OutputType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.animated.InterpolationAnimatedNode$OutputType r1 = com.facebook.react.animated.InterpolationAnimatedNode.OutputType.String     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.InterpolationAnimatedNode.AnonymousClass1.<clinit>():void");
        }
    }

    public Object getAnimatedObject() {
        return this.mObjectValue;
    }

    public String prettyPrint() {
        return "InterpolationAnimatedNode[" + this.mTag + "] super: " + super.prettyPrint();
    }
}
