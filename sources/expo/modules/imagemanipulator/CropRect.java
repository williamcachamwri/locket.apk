package expo.modules.imagemanipulator;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000bR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\t\u001a\u0004\b\u000f\u0010\u000bR\u001c\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\t\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u001f"}, d2 = {"Lexpo/modules/imagemanipulator/CropRect;", "Lexpo/modules/kotlin/records/Record;", "originX", "", "originY", "width", "height", "(DDDD)V", "getHeight$annotations", "()V", "getHeight", "()D", "getOriginX$annotations", "getOriginX", "getOriginY$annotations", "getOriginY", "getWidth$annotations", "getWidth", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ManipulationArguments.kt */
public final class CropRect implements Record {
    private final double height;
    private final double originX;
    private final double originY;
    private final double width;

    public CropRect() {
        this(0.0d, 0.0d, 0.0d, 0.0d, 15, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ CropRect copy$default(CropRect cropRect, double d, double d2, double d3, double d4, int i, Object obj) {
        CropRect cropRect2 = cropRect;
        return cropRect.copy((i & 1) != 0 ? cropRect2.originX : d, (i & 2) != 0 ? cropRect2.originY : d2, (i & 4) != 0 ? cropRect2.width : d3, (i & 8) != 0 ? cropRect2.height : d4);
    }

    @Field
    public static /* synthetic */ void getHeight$annotations() {
    }

    @Field
    public static /* synthetic */ void getOriginX$annotations() {
    }

    @Field
    public static /* synthetic */ void getOriginY$annotations() {
    }

    @Field
    public static /* synthetic */ void getWidth$annotations() {
    }

    public final double component1() {
        return this.originX;
    }

    public final double component2() {
        return this.originY;
    }

    public final double component3() {
        return this.width;
    }

    public final double component4() {
        return this.height;
    }

    public final CropRect copy(double d, double d2, double d3, double d4) {
        return new CropRect(d, d2, d3, d4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CropRect)) {
            return false;
        }
        CropRect cropRect = (CropRect) obj;
        return Double.compare(this.originX, cropRect.originX) == 0 && Double.compare(this.originY, cropRect.originY) == 0 && Double.compare(this.width, cropRect.width) == 0 && Double.compare(this.height, cropRect.height) == 0;
    }

    public int hashCode() {
        return (((((Double.hashCode(this.originX) * 31) + Double.hashCode(this.originY)) * 31) + Double.hashCode(this.width)) * 31) + Double.hashCode(this.height);
    }

    public String toString() {
        double d = this.originX;
        double d2 = this.originY;
        double d3 = this.width;
        return "CropRect(originX=" + d + ", originY=" + d2 + ", width=" + d3 + ", height=" + this.height + ")";
    }

    public CropRect(double d, double d2, double d3, double d4) {
        this.originX = d;
        this.originY = d2;
        this.width = d3;
        this.height = d4;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ CropRect(double r10, double r12, double r14, double r16, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {
        /*
            r9 = this;
            r0 = r18 & 1
            r1 = 0
            if (r0 == 0) goto L_0x0008
            r3 = r1
            goto L_0x0009
        L_0x0008:
            r3 = r10
        L_0x0009:
            r0 = r18 & 2
            if (r0 == 0) goto L_0x000f
            r5 = r1
            goto L_0x0010
        L_0x000f:
            r5 = r12
        L_0x0010:
            r0 = r18 & 4
            if (r0 == 0) goto L_0x0016
            r7 = r1
            goto L_0x0017
        L_0x0016:
            r7 = r14
        L_0x0017:
            r0 = r18 & 8
            if (r0 == 0) goto L_0x001c
            goto L_0x001e
        L_0x001c:
            r1 = r16
        L_0x001e:
            r10 = r9
            r11 = r3
            r13 = r5
            r15 = r7
            r17 = r1
            r10.<init>(r11, r13, r15, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagemanipulator.CropRect.<init>(double, double, double, double, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final double getOriginX() {
        return this.originX;
    }

    public final double getOriginY() {
        return this.originY;
    }

    public final double getWidth() {
        return this.width;
    }

    public final double getHeight() {
        return this.height;
    }
}
