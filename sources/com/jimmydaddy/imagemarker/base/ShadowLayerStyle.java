package com.jimmydaddy.imagemarker.base;

import android.graphics.Color;
import android.util.Log;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÖ\u0001J\u0012\u0010\t\u001a\u00020\u001f2\b\u0010\u0005\u001a\u0004\u0018\u00010 H\u0002J\t\u0010!\u001a\u00020 HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\""}, d2 = {"Lcom/jimmydaddy/imagemarker/base/ShadowLayerStyle;", "", "readableMap", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "color", "", "getColor", "()I", "setColor", "(I)V", "dx", "", "getDx", "()F", "setDx", "(F)V", "dy", "getDy", "setDy", "radius", "getRadius", "setRadius", "getReadableMap", "()Lcom/facebook/react/bridge/ReadableMap;", "component1", "copy", "equals", "", "other", "hashCode", "", "", "toString", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ShadowLayerStyle.kt */
public final class ShadowLayerStyle {
    private int color;
    private float dx;
    private float dy;
    private float radius;
    private final ReadableMap readableMap;

    public static /* synthetic */ ShadowLayerStyle copy$default(ShadowLayerStyle shadowLayerStyle, ReadableMap readableMap2, int i, Object obj) {
        if ((i & 1) != 0) {
            readableMap2 = shadowLayerStyle.readableMap;
        }
        return shadowLayerStyle.copy(readableMap2);
    }

    public final ReadableMap component1() {
        return this.readableMap;
    }

    public final ShadowLayerStyle copy(ReadableMap readableMap2) {
        return new ShadowLayerStyle(readableMap2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ShadowLayerStyle) && Intrinsics.areEqual((Object) this.readableMap, (Object) ((ShadowLayerStyle) obj).readableMap);
    }

    public int hashCode() {
        ReadableMap readableMap2 = this.readableMap;
        if (readableMap2 == null) {
            return 0;
        }
        return readableMap2.hashCode();
    }

    public String toString() {
        return "ShadowLayerStyle(readableMap=" + this.readableMap + ")";
    }

    public ShadowLayerStyle(ReadableMap readableMap2) {
        this.readableMap = readableMap2;
        if (readableMap2 != null) {
            try {
                setColor(readableMap2.getString("color"));
                this.dx = (float) readableMap2.getDouble("dx");
                this.dy = (float) readableMap2.getDouble("dy");
                this.radius = (float) readableMap2.getDouble("radius");
            } catch (Exception e) {
                Log.d(Utils.Companion.getTAG(), "Unknown shadow style options ", e);
            }
        }
    }

    public final ReadableMap getReadableMap() {
        return this.readableMap;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final void setRadius(float f) {
        this.radius = f;
    }

    public final float getDx() {
        return this.dx;
    }

    public final void setDx(float f) {
        this.dx = f;
    }

    public final float getDy() {
        return this.dy;
    }

    public final void setDy(float f) {
        this.dy = f;
    }

    public final int getColor() {
        return this.color;
    }

    public final void setColor(int i) {
        this.color = i;
    }

    private final void setColor(String str) {
        try {
            this.color = Color.parseColor(Utils.Companion.transRGBColor(str));
        } catch (Exception e) {
            Log.d(Utils.Companion.getTAG(), "Unknown color string ", e);
        }
    }
}
