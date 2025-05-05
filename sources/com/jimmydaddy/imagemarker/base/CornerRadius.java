package com.jimmydaddy.imagemarker.base;

import android.graphics.RectF;
import com.facebook.react.bridge.ReadableMap;
import com.jimmydaddy.imagemarker.base.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/CornerRadius;", "", "opts", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "all", "Lcom/jimmydaddy/imagemarker/base/Radius;", "bottomLeft", "bottomRight", "getOpts", "()Lcom/facebook/react/bridge/ReadableMap;", "topLeft", "topRight", "component1", "copy", "equals", "", "other", "hashCode", "", "radii", "", "rect", "Landroid/graphics/RectF;", "toString", "", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CornerRadius.kt */
public final class CornerRadius {
    private Radius all;
    private Radius bottomLeft;
    private Radius bottomRight;
    private final ReadableMap opts;
    private Radius topLeft;
    private Radius topRight;

    public static /* synthetic */ CornerRadius copy$default(CornerRadius cornerRadius, ReadableMap readableMap, int i, Object obj) {
        if ((i & 1) != 0) {
            readableMap = cornerRadius.opts;
        }
        return cornerRadius.copy(readableMap);
    }

    public final ReadableMap component1() {
        return this.opts;
    }

    public final CornerRadius copy(ReadableMap readableMap) {
        return new CornerRadius(readableMap);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CornerRadius) && Intrinsics.areEqual((Object) this.opts, (Object) ((CornerRadius) obj).opts);
    }

    public int hashCode() {
        ReadableMap readableMap = this.opts;
        if (readableMap == null) {
            return 0;
        }
        return readableMap.hashCode();
    }

    public String toString() {
        return "CornerRadius(opts=" + this.opts + ")";
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CornerRadius(com.facebook.react.bridge.ReadableMap r4) {
        /*
            r3 = this;
            r3.<init>()
            r3.opts = r4
            if (r4 == 0) goto L_0x000c
            java.util.Iterator r4 = r4.getEntryIterator()
            goto L_0x000d
        L_0x000c:
            r4 = 0
        L_0x000d:
            if (r4 == 0) goto L_0x008f
        L_0x000f:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x008f
            java.lang.Object r0 = r4.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getValue()
            java.lang.Object r0 = r0.getKey()
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L_0x0083
            int r2 = r0.hashCode()
            switch(r2) {
                case -1682792238: goto L_0x006e;
                case -1140120836: goto L_0x0059;
                case -978346553: goto L_0x0044;
                case -621290831: goto L_0x002f;
                default: goto L_0x002e;
            }
        L_0x002e:
            goto L_0x0083
        L_0x002f:
            java.lang.String r2 = "bottomRight"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0038
            goto L_0x0083
        L_0x0038:
            if (r1 == 0) goto L_0x008f
            com.jimmydaddy.imagemarker.base.Radius r0 = new com.jimmydaddy.imagemarker.base.Radius
            com.facebook.react.bridge.ReadableMap r1 = (com.facebook.react.bridge.ReadableMap) r1
            r0.<init>(r1)
            r3.bottomRight = r0
            goto L_0x000f
        L_0x0044:
            java.lang.String r2 = "topRight"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x004d
            goto L_0x0083
        L_0x004d:
            if (r1 == 0) goto L_0x008f
            com.jimmydaddy.imagemarker.base.Radius r0 = new com.jimmydaddy.imagemarker.base.Radius
            com.facebook.react.bridge.ReadableMap r1 = (com.facebook.react.bridge.ReadableMap) r1
            r0.<init>(r1)
            r3.topRight = r0
            goto L_0x000f
        L_0x0059:
            java.lang.String r2 = "topLeft"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0062
            goto L_0x0083
        L_0x0062:
            if (r1 == 0) goto L_0x008f
            com.jimmydaddy.imagemarker.base.Radius r0 = new com.jimmydaddy.imagemarker.base.Radius
            com.facebook.react.bridge.ReadableMap r1 = (com.facebook.react.bridge.ReadableMap) r1
            r0.<init>(r1)
            r3.topLeft = r0
            goto L_0x000f
        L_0x006e:
            java.lang.String r2 = "bottomLeft"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0077
            goto L_0x0083
        L_0x0077:
            if (r1 == 0) goto L_0x008f
            com.jimmydaddy.imagemarker.base.Radius r0 = new com.jimmydaddy.imagemarker.base.Radius
            com.facebook.react.bridge.ReadableMap r1 = (com.facebook.react.bridge.ReadableMap) r1
            r0.<init>(r1)
            r3.bottomLeft = r0
            goto L_0x000f
        L_0x0083:
            if (r1 == 0) goto L_0x008f
            com.jimmydaddy.imagemarker.base.Radius r0 = new com.jimmydaddy.imagemarker.base.Radius
            com.facebook.react.bridge.ReadableMap r1 = (com.facebook.react.bridge.ReadableMap) r1
            r0.<init>(r1)
            r3.all = r0
            goto L_0x000f
        L_0x008f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jimmydaddy.imagemarker.base.CornerRadius.<init>(com.facebook.react.bridge.ReadableMap):void");
    }

    public final ReadableMap getOpts() {
        return this.opts;
    }

    public final float[] radii(RectF rectF) {
        float f;
        float f2;
        Intrinsics.checkNotNullParameter(rectF, "rect");
        if (this.all != null) {
            Utils.Companion companion = Utils.Companion;
            Radius radius = this.all;
            Intrinsics.checkNotNull(radius);
            f2 = companion.parseSpreadValue(radius.getX(), rectF.width());
            Utils.Companion companion2 = Utils.Companion;
            Radius radius2 = this.all;
            Intrinsics.checkNotNull(radius2);
            f = companion2.parseSpreadValue(radius2.getY(), rectF.height());
        } else {
            f2 = 0.0f;
            f = 0.0f;
        }
        float[] fArr = {f2, f, f2, f, f2, f, f2, f};
        if (this.topLeft != null) {
            Utils.Companion companion3 = Utils.Companion;
            Radius radius3 = this.topLeft;
            Intrinsics.checkNotNull(radius3);
            fArr[0] = companion3.parseSpreadValue(radius3.getX(), rectF.width());
            Utils.Companion companion4 = Utils.Companion;
            Radius radius4 = this.topLeft;
            Intrinsics.checkNotNull(radius4);
            fArr[1] = companion4.parseSpreadValue(radius4.getY(), rectF.height());
        }
        if (this.topRight != null) {
            Utils.Companion companion5 = Utils.Companion;
            Radius radius5 = this.topRight;
            Intrinsics.checkNotNull(radius5);
            fArr[2] = companion5.parseSpreadValue(radius5.getX(), rectF.width());
            Utils.Companion companion6 = Utils.Companion;
            Radius radius6 = this.topRight;
            Intrinsics.checkNotNull(radius6);
            fArr[3] = companion6.parseSpreadValue(radius6.getY(), rectF.height());
        }
        if (this.bottomRight != null) {
            Utils.Companion companion7 = Utils.Companion;
            Radius radius7 = this.bottomRight;
            Intrinsics.checkNotNull(radius7);
            fArr[4] = companion7.parseSpreadValue(radius7.getX(), rectF.width());
            Utils.Companion companion8 = Utils.Companion;
            Radius radius8 = this.bottomRight;
            Intrinsics.checkNotNull(radius8);
            fArr[5] = companion8.parseSpreadValue(radius8.getY(), rectF.height());
        }
        if (this.bottomLeft != null) {
            Utils.Companion companion9 = Utils.Companion;
            Radius radius9 = this.bottomLeft;
            Intrinsics.checkNotNull(radius9);
            fArr[6] = companion9.parseSpreadValue(radius9.getX(), rectF.width());
            Utils.Companion companion10 = Utils.Companion;
            Radius radius10 = this.bottomLeft;
            Intrinsics.checkNotNull(radius10);
            fArr[7] = companion10.parseSpreadValue(radius10.getY(), rectF.height());
        }
        return fArr;
    }
}
