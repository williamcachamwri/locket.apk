package com.jimmydaddy.imagemarker.base;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.text.ReactFontManager;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J&\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0016HÖ\u0001J\t\u0010\u001e\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/TextOptions;", "", "options", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)V", "getOptions", "()Lcom/facebook/react/bridge/ReadableMap;", "positionEnum", "Lcom/jimmydaddy/imagemarker/base/PositionEnum;", "style", "Lcom/jimmydaddy/imagemarker/base/TextStyle;", "text", "", "x", "y", "applyStyle", "", "context", "Lcom/facebook/react/bridge/ReactApplicationContext;", "canvas", "Landroid/graphics/Canvas;", "maxWidth", "", "maxHeight", "component1", "copy", "equals", "", "other", "hashCode", "toString", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TextOptions.kt */
public final class TextOptions {
    private final ReadableMap options;
    private PositionEnum positionEnum;
    private TextStyle style;
    private String text;
    private String x;
    private String y;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: TextOptions.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                android.graphics.Paint$Align[] r0 = android.graphics.Paint.Align.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.graphics.Paint$Align r1 = android.graphics.Paint.Align.RIGHT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                android.graphics.Paint$Align r1 = android.graphics.Paint.Align.CENTER     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                android.graphics.Paint$Align r1 = android.graphics.Paint.Align.LEFT     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jimmydaddy.imagemarker.base.TextOptions.WhenMappings.<clinit>():void");
        }
    }

    public static /* synthetic */ TextOptions copy$default(TextOptions textOptions, ReadableMap readableMap, int i, Object obj) {
        if ((i & 1) != 0) {
            readableMap = textOptions.options;
        }
        return textOptions.copy(readableMap);
    }

    public final ReadableMap component1() {
        return this.options;
    }

    public final TextOptions copy(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "options");
        return new TextOptions(readableMap);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TextOptions) && Intrinsics.areEqual((Object) this.options, (Object) ((TextOptions) obj).options);
    }

    public int hashCode() {
        return this.options.hashCode();
    }

    public String toString() {
        return "TextOptions(options=" + this.options + ")";
    }

    public TextOptions(ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(readableMap, "options");
        this.options = readableMap;
        String string = readableMap.getString("text");
        this.text = string;
        if (string != null) {
            PositionEnum positionEnum2 = null;
            ReadableMap map = readableMap.getMap(ViewProps.POSITION) != null ? readableMap.getMap(ViewProps.POSITION) : null;
            Intrinsics.checkNotNull(map);
            this.x = map.hasKey("X") ? Utils.Companion.handleDynamicToString(map.getDynamic("X")) : null;
            this.y = map.hasKey("Y") ? Utils.Companion.handleDynamicToString(map.getDynamic("Y")) : null;
            this.positionEnum = map.getString(ViewProps.POSITION) != null ? PositionEnum.Companion.getPosition(map.getString(ViewProps.POSITION)) : positionEnum2;
            this.style = new TextStyle(readableMap.getMap(TtmlNode.TAG_STYLE));
            return;
        }
        throw new MarkerError(ErrorCode.PARAMS_REQUIRED, "mark text is required");
    }

    public final ReadableMap getOptions() {
        return this.options;
    }

    public final void applyStyle(ReactApplicationContext reactApplicationContext, Canvas canvas, int i, int i2) {
        int i3;
        float f;
        StaticLayout staticLayout;
        Canvas canvas2 = canvas;
        int i4 = i;
        int i5 = i2;
        Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
        Intrinsics.checkNotNullParameter(canvas2, "canvas");
        TextPaint textPaint = new TextPaint(257);
        textPaint.setAntiAlias(true);
        if (this.style.getShadowLayerStyle() != null) {
            ShadowLayerStyle shadowLayerStyle = this.style.getShadowLayerStyle();
            Intrinsics.checkNotNull(shadowLayerStyle);
            float radius = shadowLayerStyle.getRadius();
            ShadowLayerStyle shadowLayerStyle2 = this.style.getShadowLayerStyle();
            Intrinsics.checkNotNull(shadowLayerStyle2);
            float dx = shadowLayerStyle2.getDx();
            ShadowLayerStyle shadowLayerStyle3 = this.style.getShadowLayerStyle();
            Intrinsics.checkNotNull(shadowLayerStyle3);
            float dy = shadowLayerStyle3.getDy();
            ShadowLayerStyle shadowLayerStyle4 = this.style.getShadowLayerStyle();
            Intrinsics.checkNotNull(shadowLayerStyle4);
            textPaint.setShadowLayer(radius, dx, dy, shadowLayerStyle4.getColor());
        }
        Typeface typeface = Typeface.DEFAULT;
        if (this.style.getFontName() != null) {
            try {
                ReactFontManager instance = ReactFontManager.getInstance();
                String fontName = this.style.getFontName();
                Intrinsics.checkNotNull(fontName);
                typeface = instance.getTypeface(fontName, 0, reactApplicationContext.getAssets());
            } catch (Exception e) {
                SentryLogcatAdapter.e(Constants.IMAGE_MARKER_TAG, "Could not get typeface: " + e.getMessage());
                typeface = Typeface.DEFAULT;
            }
        }
        float fontSize = this.style.getFontSize();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(fontSize);
        Log.i(Constants.IMAGE_MARKER_TAG, "textSize: " + fontSize + " fontSize: " + this.style.getFontSize() + " displayMetrics: " + reactApplicationContext.getResources().getDisplayMetrics());
        textPaint.setColor(Color.parseColor(Utils.Companion.transRGBColor(this.style.getColor())));
        textPaint.setUnderlineText(this.style.getUnderline());
        Float skewX = this.style.getSkewX();
        Intrinsics.checkNotNull(skewX);
        textPaint.setTextSkewX(skewX.floatValue());
        Typeface create = Typeface.create(typeface, 0);
        if (this.style.getItalic() && this.style.getBold()) {
            create = Typeface.create(typeface, 3);
        } else if (this.style.getItalic()) {
            create = Typeface.create(typeface, 2);
        } else if (this.style.getBold()) {
            create = Typeface.create(typeface, 1);
        }
        textPaint.setStrikeThruText(this.style.getStrikeThrough());
        textPaint.setTypeface(create);
        textPaint.setTextAlign(this.style.getTextAlign());
        String str = this.text;
        Intrinsics.checkNotNull(str);
        String str2 = this.text;
        Intrinsics.checkNotNull(str2);
        StaticLayout.Builder obtain = StaticLayout.Builder.obtain(str, 0, str2.length(), textPaint, canvas.getWidth());
        Intrinsics.checkNotNullExpressionValue(obtain, "obtain(...)");
        obtain.setAlignment(Layout.Alignment.ALIGN_NORMAL);
        obtain.setLineSpacing(0.0f, 1.0f);
        obtain.setIncludePad(false);
        StaticLayout build = obtain.build();
        Intrinsics.checkNotNull(build);
        int height = build.getHeight();
        int lineCount = build.getLineCount();
        int i6 = 0;
        for (int i7 = 0; i7 < lineCount; i7++) {
            i6 = (int) Math.ceil((double) RangesKt.coerceAtLeast((float) i6, build.getLineWidth(i7) + build.getLineLeft(i7)));
        }
        Position position = new Position(20.0f, 20.0f);
        if (this.positionEnum != null) {
            i3 = i6;
            position = Position.Companion.getTextPosition(this.positionEnum, i, i2, i6, height);
        } else {
            i3 = i6;
            if (this.x != null) {
                position.setX(Utils.Companion.parseSpreadValue(this.x, (float) i4));
            }
            if (this.y != null) {
                position.setY(Utils.Companion.parseSpreadValue(this.y, (float) i5));
            }
        }
        float x2 = position.getX();
        float y2 = position.getY();
        canvas.save();
        float f2 = (float) i3;
        float f3 = (float) height;
        RectF rectF = new RectF(x2, y2, f2, f3);
        canvas2.rotate((float) this.style.getRotate(), rectF.centerX(), rectF.centerY());
        if (this.style.getTextBackgroundStyle() != null) {
            Paint paint = new Paint(65);
            paint.setStyle(Paint.Style.FILL);
            TextBackgroundStyle textBackgroundStyle = this.style.getTextBackgroundStyle();
            Intrinsics.checkNotNull(textBackgroundStyle);
            paint.setColor(textBackgroundStyle.getColor());
            TextBackgroundStyle textBackgroundStyle2 = this.style.getTextBackgroundStyle();
            Intrinsics.checkNotNull(textBackgroundStyle2);
            MarkerInsets edgeInsets = textBackgroundStyle2.toEdgeInsets(i4, i5);
            float f4 = x2 + f2;
            staticLayout = build;
            float f5 = f3 + y2;
            f = f2;
            RectF rectF2 = new RectF(x2 - ((float) edgeInsets.getLeft()), y2 - ((float) edgeInsets.getTop()), f4 + ((float) edgeInsets.getRight()), ((float) edgeInsets.getBottom()) + f5);
            TextBackgroundStyle textBackgroundStyle3 = this.style.getTextBackgroundStyle();
            Intrinsics.checkNotNull(textBackgroundStyle3);
            String type = textBackgroundStyle3.getType();
            if (Intrinsics.areEqual((Object) type, (Object) "stretchX")) {
                rectF2 = new RectF(0.0f, y2 - ((float) edgeInsets.getTop()), (float) i4, f5 + ((float) edgeInsets.getBottom()));
            } else if (Intrinsics.areEqual((Object) type, (Object) "stretchY")) {
                rectF2 = new RectF(x2 - ((float) edgeInsets.getLeft()), 0.0f, f4 + ((float) edgeInsets.getRight()), (float) i5);
            }
            TextBackgroundStyle textBackgroundStyle4 = this.style.getTextBackgroundStyle();
            Intrinsics.checkNotNull(textBackgroundStyle4);
            if (textBackgroundStyle4.getCornerRadius() != null) {
                Path path = new Path();
                TextBackgroundStyle textBackgroundStyle5 = this.style.getTextBackgroundStyle();
                Intrinsics.checkNotNull(textBackgroundStyle5);
                CornerRadius cornerRadius = textBackgroundStyle5.getCornerRadius();
                Intrinsics.checkNotNull(cornerRadius);
                path.addRoundRect(rectF2, cornerRadius.radii(rectF2), Path.Direction.CW);
                canvas2.drawPath(path, paint);
            } else {
                canvas2.drawRect(rectF2, paint);
            }
        } else {
            staticLayout = build;
            f = f2;
        }
        Paint.Align textAlign = textPaint.getTextAlign();
        int i8 = textAlign == null ? -1 : WhenMappings.$EnumSwitchMapping$0[textAlign.ordinal()];
        if (i8 == 1) {
            x2 += f;
        } else if (i8 == 2) {
            x2 += (float) (i3 / 2);
        }
        canvas2.translate(x2, y2);
        staticLayout.draw(canvas2);
        canvas.restore();
    }
}
