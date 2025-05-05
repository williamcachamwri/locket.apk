package com.jimmydaddy.imagemarker.base;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\bHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/Radius;", "", "options", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "getOptions", "()Lcom/facebook/react/bridge/ReadableMap;", "x", "", "getX", "()Ljava/lang/String;", "setX", "(Ljava/lang/String;)V", "y", "getY", "setY", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Radius.kt */
public final class Radius {
    private final ReadableMap options;
    private String x = "0";
    private String y = "0";

    public static /* synthetic */ Radius copy$default(Radius radius, ReadableMap readableMap, int i, Object obj) {
        if ((i & 1) != 0) {
            readableMap = radius.options;
        }
        return radius.copy(readableMap);
    }

    public final ReadableMap component1() {
        return this.options;
    }

    public final Radius copy(ReadableMap readableMap) {
        return new Radius(readableMap);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Radius) && Intrinsics.areEqual((Object) this.options, (Object) ((Radius) obj).options);
    }

    public int hashCode() {
        ReadableMap readableMap = this.options;
        if (readableMap == null) {
            return 0;
        }
        return readableMap.hashCode();
    }

    public String toString() {
        return "Radius(options=" + this.options + ")";
    }

    public Radius(ReadableMap readableMap) {
        this.options = readableMap;
        Dynamic dynamic = null;
        this.x = Utils.Companion.handleDynamicToString(readableMap != null ? readableMap.getDynamic(ViewHierarchyNode.JsonKeys.X) : null);
        this.y = Utils.Companion.handleDynamicToString(readableMap != null ? readableMap.getDynamic(ViewHierarchyNode.JsonKeys.Y) : dynamic);
    }

    public final ReadableMap getOptions() {
        return this.options;
    }

    public final String getX() {
        return this.x;
    }

    public final void setX(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.x = str;
    }

    public final String getY() {
        return this.y;
    }

    public final void setY(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.y = str;
    }
}
