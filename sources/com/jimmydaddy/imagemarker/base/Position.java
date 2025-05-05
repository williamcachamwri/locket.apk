package com.jimmydaddy.imagemarker.base;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/Position;", "", "x", "", "y", "(FF)V", "getX", "()F", "setX", "(F)V", "getY", "setY", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Position.kt */
public final class Position {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private float x;
    private float y;

    public static /* synthetic */ Position copy$default(Position position, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = position.x;
        }
        if ((i & 2) != 0) {
            f2 = position.y;
        }
        return position.copy(f, f2);
    }

    @JvmStatic
    public static final Position getImageRectFromPosition(PositionEnum positionEnum, int i, int i2, int i3, int i4) {
        return Companion.getImageRectFromPosition(positionEnum, i, i2, i3, i4);
    }

    public final float component1() {
        return this.x;
    }

    public final float component2() {
        return this.y;
    }

    public final Position copy(float f, float f2) {
        return new Position(f, f2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Position)) {
            return false;
        }
        Position position = (Position) obj;
        return Float.compare(this.x, position.x) == 0 && Float.compare(this.y, position.y) == 0;
    }

    public int hashCode() {
        return (Float.hashCode(this.x) * 31) + Float.hashCode(this.y);
    }

    public String toString() {
        float f = this.x;
        return "Position(x=" + f + ", y=" + this.y + ")";
    }

    public Position(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final void setX(float f) {
        this.x = f;
    }

    public final void setY(float f) {
        this.y = f;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0007J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bJ0\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bJ8\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\b¨\u0006\u0011"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/Position$Companion;", "", "()V", "getImageRectFromPosition", "Lcom/jimmydaddy/imagemarker/base/Position;", "position", "Lcom/jimmydaddy/imagemarker/base/PositionEnum;", "width", "", "height", "imageWidth", "imageHeight", "", "getTextPosition", "textWidth", "textHeight", "margin", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Position.kt */
    public static final class Companion {

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
        /* compiled from: Position.kt */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|17) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
            static {
                /*
                    com.jimmydaddy.imagemarker.base.PositionEnum[] r0 = com.jimmydaddy.imagemarker.base.PositionEnum.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.jimmydaddy.imagemarker.base.PositionEnum r1 = com.jimmydaddy.imagemarker.base.PositionEnum.TOP_CENTER     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.jimmydaddy.imagemarker.base.PositionEnum r1 = com.jimmydaddy.imagemarker.base.PositionEnum.TOP_RIGHT     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    com.jimmydaddy.imagemarker.base.PositionEnum r1 = com.jimmydaddy.imagemarker.base.PositionEnum.CENTER     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    com.jimmydaddy.imagemarker.base.PositionEnum r1 = com.jimmydaddy.imagemarker.base.PositionEnum.BOTTOM_LEFT     // Catch:{ NoSuchFieldError -> 0x002b }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                L_0x002b:
                    com.jimmydaddy.imagemarker.base.PositionEnum r1 = com.jimmydaddy.imagemarker.base.PositionEnum.BOTTOM_CENTER     // Catch:{ NoSuchFieldError -> 0x0034 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                    r2 = 5
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                L_0x0034:
                    com.jimmydaddy.imagemarker.base.PositionEnum r1 = com.jimmydaddy.imagemarker.base.PositionEnum.BOTTOM_RIGHT     // Catch:{ NoSuchFieldError -> 0x003d }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                    r2 = 6
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
                L_0x003d:
                    com.jimmydaddy.imagemarker.base.PositionEnum r1 = com.jimmydaddy.imagemarker.base.PositionEnum.TOP_LEFT     // Catch:{ NoSuchFieldError -> 0x0046 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                    r2 = 7
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
                L_0x0046:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jimmydaddy.imagemarker.base.Position.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Position getTextPosition(String str, int i, int i2, int i3, int i4, int i5) {
            if (str == null) {
                float f = (float) i;
                return new Position(f, f);
            }
            switch (str.hashCode()) {
                case -1682792238:
                    if (str.equals("bottomLeft")) {
                        return new Position(0.0f, (float) ((i3 - i5) - i));
                    }
                    break;
                case -1364013995:
                    if (str.equals(TtmlNode.CENTER)) {
                        return new Position((float) ((i2 - i4) / 2), (float) ((i3 - i5) / 2));
                    }
                    break;
                case -978346553:
                    if (str.equals("topRight")) {
                        return new Position((float) (i2 - i4), (float) i);
                    }
                    break;
                case -696883702:
                    if (str.equals("topCenter")) {
                        return new Position((float) ((i2 - i4) / 2), (float) i);
                    }
                    break;
                case 1781909088:
                    if (str.equals("bottomCenter")) {
                        return new Position((float) ((i2 - i4) / 2), (float) (i3 - i5));
                    }
                    break;
            }
            return new Position((float) ((i2 - i4) - i), (float) ((i3 - i5) - i));
        }

        public final Position getTextPosition(PositionEnum positionEnum, int i, int i2, int i3, int i4) {
            if (positionEnum == null) {
                float f = (float) 20;
                return new Position(f, f);
            }
            switch (WhenMappings.$EnumSwitchMapping$0[positionEnum.ordinal()]) {
                case 1:
                    return new Position((float) ((i - i3) / 2), (float) 20);
                case 2:
                    return new Position((float) (i - i3), (float) 20);
                case 3:
                    return new Position((float) ((i - i3) / 2), (float) ((i2 - i4) / 2));
                case 4:
                    return new Position((float) 20, (float) ((i2 - i4) - 20));
                case 5:
                    return new Position((float) ((i - i3) / 2), (float) (i2 - i4));
                case 6:
                    return new Position((float) ((i - i3) - 20), (float) ((i2 - i4) - 20));
                default:
                    float f2 = (float) 20;
                    return new Position(f2, f2);
            }
        }

        public final Position getImageRectFromPosition(String str, int i, int i2, int i3, int i4) {
            int i5 = i3 - i;
            Position position = new Position((float) 20, (float) 40);
            if (str == null) {
                return position;
            }
            switch (str.hashCode()) {
                case -1682792238:
                    if (str.equals("bottomLeft")) {
                        position.setY((float) ((i4 - i2) - 20));
                        break;
                    }
                    break;
                case -1364013995:
                    if (str.equals(TtmlNode.CENTER)) {
                        position.setX((float) ((i3 / 2) - (i / 2)));
                        position.setY((float) ((i4 / 2) - (i2 / 2)));
                        break;
                    }
                    break;
                case -978346553:
                    if (str.equals("topRight")) {
                        position.setX((float) (i5 - 20));
                        break;
                    }
                    break;
                case -696883702:
                    if (str.equals("topCenter")) {
                        position.setX((float) ((i3 / 2) - (i / 2)));
                        break;
                    }
                    break;
                case -621290831:
                    if (str.equals("bottomRight")) {
                        position.setX((float) ((i5 - 20) - 20));
                        position.setY((float) ((i4 - i2) - 20));
                        break;
                    }
                    break;
                case 1781909088:
                    if (str.equals("bottomCenter")) {
                        position.setX((float) (((i3 / 2) - (i / 2)) - 20));
                        position.setY((float) ((i4 - i2) - 20));
                        break;
                    }
                    break;
            }
            return position;
        }

        @JvmStatic
        public final Position getImageRectFromPosition(PositionEnum positionEnum, int i, int i2, int i3, int i4) {
            int i5 = i3 - i;
            float f = (float) 20;
            Position position = new Position(f, f);
            if (positionEnum == null) {
                return position;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[positionEnum.ordinal()]) {
                case 1:
                    position.setX((float) ((i3 / 2) - (i / 2)));
                    break;
                case 2:
                    position.setX((float) (i5 - 20));
                    break;
                case 3:
                    position.setX((float) ((i3 / 2) - (i / 2)));
                    position.setY((float) ((i4 / 2) - (i2 / 2)));
                    break;
                case 4:
                    position.setY((float) ((i4 - i2) - 20));
                    break;
                case 5:
                    position.setX((float) (((i3 / 2) - (i / 2)) - 20));
                    position.setY((float) ((i4 - i2) - 20));
                    break;
                case 6:
                    position.setX((float) ((i5 - 20) - 20));
                    position.setY((float) ((i4 - i2) - 20));
                    break;
                case 7:
                    position.setX(f);
                    position.setY(f);
                    break;
            }
            return position;
        }
    }
}
