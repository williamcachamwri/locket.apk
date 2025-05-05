package com.facebook.react.uimanager;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.ReactConstants;

public class TransformHelper {
    private static ThreadLocal<double[]> sHelperMatrix = new ThreadLocal<double[]>() {
        /* access modifiers changed from: protected */
        public double[] initialValue() {
            return new double[16];
        }
    };

    private static double convertToRadians(ReadableMap readableMap, String str) {
        double d;
        boolean z = true;
        if (readableMap.getType(str) == ReadableType.String) {
            String string = readableMap.getString(str);
            if (string.endsWith("rad")) {
                string = string.substring(0, string.length() - 3);
            } else if (string.endsWith("deg")) {
                string = string.substring(0, string.length() - 3);
                z = false;
            }
            d = (double) Float.parseFloat(string);
        } else {
            d = readableMap.getDouble(str);
        }
        return z ? d : MatrixMathHelper.degreesToRadians(d);
    }

    public static void processTransform(ReadableArray readableArray, double[] dArr) {
        processTransform(readableArray, dArr, 0.0f, 0.0f, (ReadableArray) null);
    }

    public static void processTransform(ReadableArray readableArray, double[] dArr, float f, float f2, ReadableArray readableArray2) {
        int i;
        ReadableArray readableArray3 = readableArray;
        double[] dArr2 = dArr;
        double[] dArr3 = sHelperMatrix.get();
        MatrixMathHelper.resetIdentityMatrix(dArr);
        float[] translateForTransformOrigin = getTranslateForTransformOrigin(f, f2, readableArray2);
        if (translateForTransformOrigin != null) {
            MatrixMathHelper.resetIdentityMatrix(dArr3);
            MatrixMathHelper.applyTranslate3D(dArr3, (double) translateForTransformOrigin[0], (double) translateForTransformOrigin[1], (double) translateForTransformOrigin[2]);
            MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
        }
        if (readableArray.size() == 16 && readableArray3.getType(0) == ReadableType.Number) {
            MatrixMathHelper.resetIdentityMatrix(dArr3);
            for (int i2 = 0; i2 < readableArray.size(); i2++) {
                dArr3[i2] = readableArray3.getDouble(i2);
            }
            MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
        } else {
            int size = readableArray.size();
            int i3 = 0;
            while (i3 < size) {
                ReadableMap map = readableArray3.getMap(i3);
                String nextKey = map.keySetIterator().nextKey();
                MatrixMathHelper.resetIdentityMatrix(dArr3);
                if ("matrix".equals(nextKey)) {
                    ReadableArray array = map.getArray(nextKey);
                    for (int i4 = 0; i4 < 16; i4++) {
                        dArr3[i4] = array.getDouble(i4);
                    }
                } else if ("perspective".equals(nextKey)) {
                    MatrixMathHelper.applyPerspective(dArr3, map.getDouble(nextKey));
                } else if ("rotateX".equals(nextKey)) {
                    MatrixMathHelper.applyRotateX(dArr3, convertToRadians(map, nextKey));
                } else if ("rotateY".equals(nextKey)) {
                    MatrixMathHelper.applyRotateY(dArr3, convertToRadians(map, nextKey));
                } else if ("rotate".equals(nextKey) || "rotateZ".equals(nextKey)) {
                    i = i3;
                    MatrixMathHelper.applyRotateZ(dArr3, convertToRadians(map, nextKey));
                    MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
                    i3 = i + 1;
                } else if ("scale".equals(nextKey)) {
                    double d = map.getDouble(nextKey);
                    MatrixMathHelper.applyScaleX(dArr3, d);
                    MatrixMathHelper.applyScaleY(dArr3, d);
                } else if ("scaleX".equals(nextKey)) {
                    MatrixMathHelper.applyScaleX(dArr3, map.getDouble(nextKey));
                } else if ("scaleY".equals(nextKey)) {
                    MatrixMathHelper.applyScaleY(dArr3, map.getDouble(nextKey));
                } else {
                    double d2 = 0.0d;
                    if ("translate".equals(nextKey)) {
                        ReadableArray array2 = map.getArray(nextKey);
                        double d3 = array2.getDouble(0);
                        double d4 = array2.getDouble(1);
                        if (array2.size() > 2) {
                            d2 = array2.getDouble(2);
                        }
                        double d5 = d2;
                        double d6 = d4;
                        i = i3;
                        MatrixMathHelper.applyTranslate3D(dArr3, d3, d6, d5);
                    } else {
                        i = i3;
                        if (ViewProps.TRANSLATE_X.equals(nextKey)) {
                            MatrixMathHelper.applyTranslate2D(dArr3, map.getDouble(nextKey), 0.0d);
                        } else if (ViewProps.TRANSLATE_Y.equals(nextKey)) {
                            MatrixMathHelper.applyTranslate2D(dArr3, 0.0d, map.getDouble(nextKey));
                        } else if ("skewX".equals(nextKey)) {
                            MatrixMathHelper.applySkewX(dArr3, convertToRadians(map, nextKey));
                        } else if ("skewY".equals(nextKey)) {
                            MatrixMathHelper.applySkewY(dArr3, convertToRadians(map, nextKey));
                        } else {
                            FLog.w(ReactConstants.TAG, "Unsupported transform type: " + nextKey);
                        }
                    }
                    MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
                    i3 = i + 1;
                }
                i = i3;
                MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
                i3 = i + 1;
            }
        }
        if (translateForTransformOrigin != null) {
            MatrixMathHelper.resetIdentityMatrix(dArr3);
            MatrixMathHelper.applyTranslate3D(dArr3, (double) (-translateForTransformOrigin[0]), (double) (-translateForTransformOrigin[1]), (double) (-translateForTransformOrigin[2]));
            MatrixMathHelper.multiplyInto(dArr2, dArr2, dArr3);
        }
    }

    /* renamed from: com.facebook.react.uimanager.TransformHelper$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$bridge$ReadableType = r0
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$bridge$ReadableType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.TransformHelper.AnonymousClass2.<clinit>():void");
        }
    }

    private static float[] getTranslateForTransformOrigin(float f, float f2, ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        if (f2 == 0.0f && f == 0.0f) {
            return null;
        }
        float f3 = f / 2.0f;
        float f4 = f2 / 2.0f;
        float[] fArr = {f3, f4, 0.0f};
        int i = 0;
        while (i < readableArray.size() && i < 3) {
            int i2 = AnonymousClass2.$SwitchMap$com$facebook$react$bridge$ReadableType[readableArray.getType(i).ordinal()];
            if (i2 == 1) {
                fArr[i] = (float) readableArray.getDouble(i);
            } else if (i2 == 2) {
                String string = readableArray.getString(i);
                if (string.endsWith("%")) {
                    fArr[i] = ((i == 0 ? f : f2) * Float.parseFloat(string.substring(0, string.length() - 1))) / 100.0f;
                }
            }
            i++;
        }
        return new float[]{(-f3) + fArr[0], (-f4) + fArr[1], fArr[2]};
    }
}
