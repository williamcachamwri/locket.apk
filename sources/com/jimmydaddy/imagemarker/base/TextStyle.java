package com.jimmydaddy.imagemarker.base;

import android.graphics.Paint;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010D\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010E\u001a\u00020\u00062\b\u0010F\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010G\u001a\u00020 HÖ\u0001J\t\u0010H\u001a\u00020\fHÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001e\u0010+\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0010\n\u0002\u00100\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00101\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\b\"\u0004\b3\u0010\nR\u001a\u00104\u001a\u000205X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001c\u0010:\u001a\u0004\u0018\u00010;X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001a\u0010@\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\b\"\u0004\bB\u0010\n¨\u0006I"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/TextStyle;", "", "options", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "bold", "", "getBold", "()Z", "setBold", "(Z)V", "color", "", "getColor", "()Ljava/lang/String;", "setColor", "(Ljava/lang/String;)V", "fontName", "getFontName", "setFontName", "fontSize", "", "getFontSize", "()F", "setFontSize", "(F)V", "italic", "getItalic", "setItalic", "getOptions", "()Lcom/facebook/react/bridge/ReadableMap;", "rotate", "", "getRotate", "()I", "setRotate", "(I)V", "shadowLayerStyle", "Lcom/jimmydaddy/imagemarker/base/ShadowLayerStyle;", "getShadowLayerStyle", "()Lcom/jimmydaddy/imagemarker/base/ShadowLayerStyle;", "setShadowLayerStyle", "(Lcom/jimmydaddy/imagemarker/base/ShadowLayerStyle;)V", "skewX", "getSkewX", "()Ljava/lang/Float;", "setSkewX", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "strikeThrough", "getStrikeThrough", "setStrikeThrough", "textAlign", "Landroid/graphics/Paint$Align;", "getTextAlign", "()Landroid/graphics/Paint$Align;", "setTextAlign", "(Landroid/graphics/Paint$Align;)V", "textBackgroundStyle", "Lcom/jimmydaddy/imagemarker/base/TextBackgroundStyle;", "getTextBackgroundStyle", "()Lcom/jimmydaddy/imagemarker/base/TextBackgroundStyle;", "setTextBackgroundStyle", "(Lcom/jimmydaddy/imagemarker/base/TextBackgroundStyle;)V", "underline", "getUnderline", "setUnderline", "component1", "copy", "equals", "other", "hashCode", "toString", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TextStyle.kt */
public final class TextStyle {
    private boolean bold;
    private String color;
    private String fontName;
    private float fontSize;
    private boolean italic;
    private final ReadableMap options;
    private int rotate;
    private ShadowLayerStyle shadowLayerStyle;
    private Float skewX;
    private boolean strikeThrough;
    private Paint.Align textAlign;
    private TextBackgroundStyle textBackgroundStyle;
    private boolean underline;

    public static /* synthetic */ TextStyle copy$default(TextStyle textStyle, ReadableMap readableMap, int i, Object obj) {
        if ((i & 1) != 0) {
            readableMap = textStyle.options;
        }
        return textStyle.copy(readableMap);
    }

    public final ReadableMap component1() {
        return this.options;
    }

    public final TextStyle copy(ReadableMap readableMap) {
        return new TextStyle(readableMap);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TextStyle) && Intrinsics.areEqual((Object) this.options, (Object) ((TextStyle) obj).options);
    }

    public int hashCode() {
        ReadableMap readableMap = this.options;
        if (readableMap == null) {
            return 0;
        }
        return readableMap.hashCode();
    }

    public String toString() {
        return "TextStyle(options=" + this.options + ")";
    }

    public TextStyle(ReadableMap readableMap) {
        Paint.Align align;
        this.options = readableMap;
        Intrinsics.checkNotNull(readableMap);
        TextBackgroundStyle textBackgroundStyle2 = null;
        this.color = readableMap.getString("color") != null ? readableMap.getString("color") : null;
        this.fontName = (readableMap != null ? readableMap.getString("fontName") : null) != null ? readableMap.getString("fontName") : null;
        boolean z = true;
        this.fontSize = readableMap != null && readableMap.hasKey("fontSize") ? (float) readableMap.getDouble("fontSize") : 14.0f;
        this.underline = readableMap != null && readableMap.hasKey(TtmlNode.UNDERLINE) ? readableMap.getBoolean(TtmlNode.UNDERLINE) : false;
        this.skewX = Float.valueOf(readableMap != null && readableMap.hasKey("skewX") ? (float) readableMap.getDouble("skewX") : 0.0f);
        this.strikeThrough = readableMap != null && readableMap.hasKey("strikeThrough") ? readableMap.getBoolean("strikeThrough") : false;
        this.italic = readableMap != null && readableMap.hasKey(TtmlNode.ITALIC) ? readableMap.getBoolean(TtmlNode.ITALIC) : false;
        this.bold = readableMap != null && readableMap.hasKey(TtmlNode.BOLD) ? readableMap.getBoolean(TtmlNode.BOLD) : false;
        this.rotate = readableMap != null && readableMap.hasKey("rotate") ? readableMap.getInt("rotate") : 0;
        ReadableMap map = readableMap != null ? readableMap.getMap("shadowStyle") : null;
        this.shadowLayerStyle = map != null ? new ShadowLayerStyle(map) : null;
        ReadableMap map2 = readableMap != null ? readableMap.getMap("textBackgroundStyle") : null;
        this.textBackgroundStyle = map2 != null ? new TextBackgroundStyle(map2) : textBackgroundStyle2;
        this.textAlign = Paint.Align.LEFT;
        if ((readableMap == null || !readableMap.hasKey("textAlign")) ? false : z) {
            String string = readableMap.getString("textAlign");
            if (string != null) {
                int hashCode = string.hashCode();
                if (hashCode != -1364013995) {
                    if (hashCode == 108511772 && string.equals("right")) {
                        align = Paint.Align.RIGHT;
                        this.textAlign = align;
                    }
                } else if (string.equals(TtmlNode.CENTER)) {
                    align = Paint.Align.CENTER;
                    this.textAlign = align;
                }
            }
            align = Paint.Align.LEFT;
            this.textAlign = align;
        }
    }

    public final ReadableMap getOptions() {
        return this.options;
    }

    public final String getColor() {
        return this.color;
    }

    public final void setColor(String str) {
        this.color = str;
    }

    public final String getFontName() {
        return this.fontName;
    }

    public final void setFontName(String str) {
        this.fontName = str;
    }

    public final float getFontSize() {
        return this.fontSize;
    }

    public final void setFontSize(float f) {
        this.fontSize = f;
    }

    public final ShadowLayerStyle getShadowLayerStyle() {
        return this.shadowLayerStyle;
    }

    public final void setShadowLayerStyle(ShadowLayerStyle shadowLayerStyle2) {
        this.shadowLayerStyle = shadowLayerStyle2;
    }

    public final TextBackgroundStyle getTextBackgroundStyle() {
        return this.textBackgroundStyle;
    }

    public final void setTextBackgroundStyle(TextBackgroundStyle textBackgroundStyle2) {
        this.textBackgroundStyle = textBackgroundStyle2;
    }

    public final boolean getUnderline() {
        return this.underline;
    }

    public final void setUnderline(boolean z) {
        this.underline = z;
    }

    public final Float getSkewX() {
        return this.skewX;
    }

    public final void setSkewX(Float f) {
        this.skewX = f;
    }

    public final boolean getStrikeThrough() {
        return this.strikeThrough;
    }

    public final void setStrikeThrough(boolean z) {
        this.strikeThrough = z;
    }

    public final Paint.Align getTextAlign() {
        return this.textAlign;
    }

    public final void setTextAlign(Paint.Align align) {
        Intrinsics.checkNotNullParameter(align, "<set-?>");
        this.textAlign = align;
    }

    public final boolean getItalic() {
        return this.italic;
    }

    public final void setItalic(boolean z) {
        this.italic = z;
    }

    public final boolean getBold() {
        return this.bold;
    }

    public final void setBold(boolean z) {
        this.bold = z;
    }

    public final int getRotate() {
        return this.rotate;
    }

    public final void setRotate(int i) {
        this.rotate = i;
    }
}
