package com.jimmydaddy.imagemarker.base;

import android.graphics.Color;
import android.util.Log;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÖ\u0001J\u0012\u0010\t\u001a\u00020 2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0014H\u0002J\t\u0010!\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\""}, d2 = {"Lcom/jimmydaddy/imagemarker/base/TextBackgroundStyle;", "Lcom/jimmydaddy/imagemarker/base/Padding;", "readableMap", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "color", "", "getColor", "()I", "setColor", "(I)V", "cornerRadius", "Lcom/jimmydaddy/imagemarker/base/CornerRadius;", "getCornerRadius", "()Lcom/jimmydaddy/imagemarker/base/CornerRadius;", "setCornerRadius", "(Lcom/jimmydaddy/imagemarker/base/CornerRadius;)V", "getReadableMap", "()Lcom/facebook/react/bridge/ReadableMap;", "type", "", "getType", "()Ljava/lang/String;", "setType", "(Ljava/lang/String;)V", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TextBackgroundStyle.kt */
public final class TextBackgroundStyle extends Padding {
    private int color;
    private CornerRadius cornerRadius;
    private final ReadableMap readableMap;
    private String type = "";

    public static /* synthetic */ TextBackgroundStyle copy$default(TextBackgroundStyle textBackgroundStyle, ReadableMap readableMap2, int i, Object obj) {
        if ((i & 1) != 0) {
            readableMap2 = textBackgroundStyle.readableMap;
        }
        return textBackgroundStyle.copy(readableMap2);
    }

    public final ReadableMap component1() {
        return this.readableMap;
    }

    public final TextBackgroundStyle copy(ReadableMap readableMap2) {
        return new TextBackgroundStyle(readableMap2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TextBackgroundStyle) && Intrinsics.areEqual((Object) this.readableMap, (Object) ((TextBackgroundStyle) obj).readableMap);
    }

    public int hashCode() {
        ReadableMap readableMap2 = this.readableMap;
        if (readableMap2 == null) {
            return 0;
        }
        return readableMap2.hashCode();
    }

    public String toString() {
        return "TextBackgroundStyle(readableMap=" + this.readableMap + ")";
    }

    public TextBackgroundStyle(ReadableMap readableMap2) {
        super(readableMap2);
        this.readableMap = readableMap2;
        if (readableMap2 != null) {
            try {
                this.type = readableMap2.getString("type");
                setColor(readableMap2.getString("color"));
                if (readableMap2.hasKey("cornerRadius")) {
                    ReadableMap map = readableMap2.getMap("cornerRadius");
                    CornerRadius cornerRadius2 = map != null ? new CornerRadius(map) : null;
                    Intrinsics.checkNotNull(cornerRadius2);
                    this.cornerRadius = cornerRadius2;
                }
            } catch (Exception e) {
                Log.d(Utils.Companion.getTAG(), "Unknown text background options ", e);
            }
        }
    }

    public final ReadableMap getReadableMap() {
        return this.readableMap;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final int getColor() {
        return this.color;
    }

    public final void setColor(int i) {
        this.color = i;
    }

    public final CornerRadius getCornerRadius() {
        return this.cornerRadius;
    }

    public final void setCornerRadius(CornerRadius cornerRadius2) {
        this.cornerRadius = cornerRadius2;
    }

    private final void setColor(String str) {
        try {
            this.color = Color.parseColor(Utils.Companion.transRGBColor(str));
        } catch (Exception e) {
            Log.d(Utils.Companion.getTAG(), "Unknown color string ", e);
        }
    }
}
