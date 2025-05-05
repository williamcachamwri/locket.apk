package com.jimmydaddy.imagemarker.base;

import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0011HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\nR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\b\"\u0004\b\u0018\u0010\n¨\u0006 "}, d2 = {"Lcom/jimmydaddy/imagemarker/base/RNImageSRC;", "", "options", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "height", "", "getHeight", "()I", "setHeight", "(I)V", "getOptions", "()Lcom/facebook/react/bridge/ReadableMap;", "scale", "getScale", "setScale", "uri", "", "getUri", "()Ljava/lang/String;", "setUri", "(Ljava/lang/String;)V", "width", "getWidth", "setWidth", "component1", "copy", "equals", "", "other", "hashCode", "toString", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RNImageSRC.kt */
public final class RNImageSRC {
    private int height;
    private final ReadableMap options;
    private int scale = 1;
    private String uri;
    private int width;

    public static /* synthetic */ RNImageSRC copy$default(RNImageSRC rNImageSRC, ReadableMap readableMap, int i, Object obj) {
        if ((i & 1) != 0) {
            readableMap = rNImageSRC.options;
        }
        return rNImageSRC.copy(readableMap);
    }

    public final ReadableMap component1() {
        return this.options;
    }

    public final RNImageSRC copy(ReadableMap readableMap) {
        return new RNImageSRC(readableMap);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RNImageSRC) && Intrinsics.areEqual((Object) this.options, (Object) ((RNImageSRC) obj).options);
    }

    public int hashCode() {
        ReadableMap readableMap = this.options;
        if (readableMap == null) {
            return 0;
        }
        return readableMap.hashCode();
    }

    public String toString() {
        return "RNImageSRC(options=" + this.options + ")";
    }

    public RNImageSRC(ReadableMap readableMap) {
        this.options = readableMap;
        boolean z = true;
        String str = "";
        this.uri = str;
        this.width = readableMap != null && readableMap.hasKey("width") ? readableMap.getInt("width") : 0;
        this.height = readableMap != null && readableMap.hasKey("height") ? readableMap.getInt("height") : 0;
        this.scale = readableMap != null && readableMap.hasKey("scale") ? readableMap.getInt("scale") : 1;
        this.uri = (readableMap == null || !readableMap.hasKey("uri")) ? false : z ? String.valueOf(readableMap.getString("uri")) : str;
    }

    public final ReadableMap getOptions() {
        return this.options;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public final int getScale() {
        return this.scale;
    }

    public final void setScale(int i) {
        this.scale = i;
    }

    public final String getUri() {
        return this.uri;
    }

    public final void setUri(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uri = str;
    }
}
