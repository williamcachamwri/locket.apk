package com.facebook.react.views.text;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.LruCache;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaMeasureMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TextLayoutManagerMapBuffer {
    public static final short AS_KEY_CACHE_ID = 3;
    public static final short AS_KEY_FRAGMENTS = 2;
    public static final short AS_KEY_HASH = 0;
    public static final short AS_KEY_STRING = 1;
    private static final boolean DEFAULT_INCLUDE_FONT_PADDING = true;
    private static final boolean ENABLE_MEASURE_LOGGING = false;
    public static final short FR_KEY_HEIGHT = 4;
    public static final short FR_KEY_IS_ATTACHMENT = 2;
    public static final short FR_KEY_REACT_TAG = 1;
    public static final short FR_KEY_STRING = 0;
    public static final short FR_KEY_TEXT_ATTRIBUTES = 5;
    public static final short FR_KEY_WIDTH = 3;
    private static final String INLINE_VIEW_PLACEHOLDER = "0";
    public static final short PA_KEY_ADJUST_FONT_SIZE_TO_FIT = 3;
    public static final short PA_KEY_ELLIPSIZE_MODE = 1;
    public static final short PA_KEY_HYPHENATION_FREQUENCY = 5;
    public static final short PA_KEY_INCLUDE_FONT_PADDING = 4;
    public static final short PA_KEY_MAX_NUMBER_OF_LINES = 0;
    public static final short PA_KEY_TEXT_BREAK_STRATEGY = 2;
    private static final String TAG = "TextLayoutManagerMapBuffer";
    private static final Object sCacheLock = new Object();
    private static final LruCache<ReadableMapBuffer, Spannable> sSpannableCache = new LruCache<>(10000);
    private static final ConcurrentHashMap<Integer, Spannable> sTagToSpannableCache = new ConcurrentHashMap<>();
    private static final TextPaint sTextPaintInstance = new TextPaint(1);
    private static final short spannableCacheSize = 10000;

    public static void setCachedSpannabledForTag(int i, Spannable spannable) {
        sTagToSpannableCache.put(Integer.valueOf(i), spannable);
    }

    public static void deleteCachedSpannableForTag(int i) {
        sTagToSpannableCache.remove(Integer.valueOf(i));
    }

    public static boolean isRTL(MapBuffer mapBuffer) {
        MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(2);
        if (mapBuffer2.getCount() == 0) {
            return false;
        }
        MapBuffer mapBuffer3 = mapBuffer2.getMapBuffer(0).getMapBuffer(5);
        if (mapBuffer3.contains(23) && TextAttributeProps.getLayoutDirection(mapBuffer3.getString(23)) == 1) {
            return true;
        }
        return false;
    }

    private static void buildSpannableFromFragment(Context context, MapBuffer mapBuffer, SpannableStringBuilder spannableStringBuilder, List<SetSpanOperation> list) {
        List<SetSpanOperation> list2 = list;
        int count = mapBuffer.getCount();
        for (int i = 0; i < count; i++) {
            MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(i);
            int length = spannableStringBuilder.length();
            TextAttributeProps fromMapBuffer = TextAttributeProps.fromMapBuffer(mapBuffer2.getMapBuffer(5));
            spannableStringBuilder.append(TextTransform.apply(mapBuffer2.getString(0), fromMapBuffer.mTextTransform));
            int length2 = spannableStringBuilder.length();
            boolean z = true;
            int i2 = mapBuffer2.contains(1) ? mapBuffer2.getInt(1) : -1;
            if (mapBuffer2.contains(2) && mapBuffer2.getBoolean(2)) {
                list2.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), new TextInlineViewPlaceholderSpan(i2, (int) PixelUtil.toPixelFromSP(mapBuffer2.getDouble(3)), (int) PixelUtil.toPixelFromSP(mapBuffer2.getDouble(4)))));
            } else if (length2 >= length) {
                if (fromMapBuffer.mRole == null ? fromMapBuffer.mAccessibilityRole != ReactAccessibilityDelegate.AccessibilityRole.LINK : fromMapBuffer.mRole != ReactAccessibilityDelegate.Role.LINK) {
                    z = false;
                }
                if (z) {
                    list2.add(new SetSpanOperation(length, length2, new ReactClickableSpan(i2)));
                }
                if (fromMapBuffer.mIsColorSet) {
                    list2.add(new SetSpanOperation(length, length2, new ReactForegroundColorSpan(fromMapBuffer.mColor)));
                }
                if (fromMapBuffer.mIsBackgroundColorSet) {
                    list2.add(new SetSpanOperation(length, length2, new ReactBackgroundColorSpan(fromMapBuffer.mBackgroundColor)));
                }
                if (!Float.isNaN(fromMapBuffer.getLetterSpacing())) {
                    list2.add(new SetSpanOperation(length, length2, new CustomLetterSpacingSpan(fromMapBuffer.getLetterSpacing())));
                }
                list2.add(new SetSpanOperation(length, length2, new ReactAbsoluteSizeSpan(fromMapBuffer.mFontSize)));
                if (!(fromMapBuffer.mFontStyle == -1 && fromMapBuffer.mFontWeight == -1 && fromMapBuffer.mFontFamily == null)) {
                    list2.add(new SetSpanOperation(length, length2, new CustomStyleSpan(fromMapBuffer.mFontStyle, fromMapBuffer.mFontWeight, fromMapBuffer.mFontFeatureSettings, fromMapBuffer.mFontFamily, context.getAssets())));
                }
                if (fromMapBuffer.mIsUnderlineTextDecorationSet) {
                    list2.add(new SetSpanOperation(length, length2, new ReactUnderlineSpan()));
                }
                if (fromMapBuffer.mIsLineThroughTextDecorationSet) {
                    list2.add(new SetSpanOperation(length, length2, new ReactStrikethroughSpan()));
                }
                if (!((fromMapBuffer.mTextShadowOffsetDx == 0.0f && fromMapBuffer.mTextShadowOffsetDy == 0.0f && fromMapBuffer.mTextShadowRadius == 0.0f) || Color.alpha(fromMapBuffer.mTextShadowColor) == 0)) {
                    list2.add(new SetSpanOperation(length, length2, new ShadowStyleSpan(fromMapBuffer.mTextShadowOffsetDx, fromMapBuffer.mTextShadowOffsetDy, fromMapBuffer.mTextShadowRadius, fromMapBuffer.mTextShadowColor)));
                }
                if (!Float.isNaN(fromMapBuffer.getEffectiveLineHeight())) {
                    list2.add(new SetSpanOperation(length, length2, new CustomLineHeightSpan(fromMapBuffer.getEffectiveLineHeight())));
                }
                list2.add(new SetSpanOperation(length, length2, new ReactTagSpan(i2)));
            }
        }
    }

    public static Spannable getOrCreateSpannableForText(Context context, MapBuffer mapBuffer, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        Spannable spannable;
        if (mapBuffer.contains(3)) {
            return sTagToSpannableCache.get(Integer.valueOf(mapBuffer.getInt(3)));
        } else if (!ReactFeatureFlags.enableTextSpannableCache || !(mapBuffer instanceof ReadableMapBuffer)) {
            return createSpannableFromAttributedString(context, mapBuffer, reactTextViewManagerCallback);
        } else {
            ReadableMapBuffer readableMapBuffer = (ReadableMapBuffer) mapBuffer;
            synchronized (sCacheLock) {
                LruCache<ReadableMapBuffer, Spannable> lruCache = sSpannableCache;
                Spannable spannable2 = lruCache.get(readableMapBuffer);
                if (spannable2 == null) {
                    spannable = createSpannableFromAttributedString(context, mapBuffer, reactTextViewManagerCallback);
                    lruCache.put(readableMapBuffer, spannable);
                } else {
                    spannable = spannable2;
                }
            }
            return spannable;
        }
    }

    private static Spannable createSpannableFromAttributedString(Context context, MapBuffer mapBuffer, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList arrayList = new ArrayList();
        buildSpannableFromFragment(context, mapBuffer.getMapBuffer(2), spannableStringBuilder, arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            ((SetSpanOperation) arrayList.get((arrayList.size() - i) - 1)).execute(spannableStringBuilder, i);
        }
        if (reactTextViewManagerCallback != null) {
            reactTextViewManagerCallback.onPostProcessSpannable(spannableStringBuilder);
        }
        return spannableStringBuilder;
    }

    private static Layout createLayout(Spannable spannable, BoringLayout.Metrics metrics, float f, YogaMeasureMode yogaMeasureMode, boolean z, int i, int i2) {
        int i3;
        Spannable spannable2 = spannable;
        BoringLayout.Metrics metrics2 = metrics;
        float f2 = f;
        boolean z2 = z;
        int i4 = i;
        int i5 = i2;
        int length = spannable.length();
        boolean z3 = yogaMeasureMode == YogaMeasureMode.UNDEFINED || f2 < 0.0f;
        float desiredWidth = metrics2 == null ? Layout.getDesiredWidth(spannable, sTextPaintInstance) : Float.NaN;
        if (metrics2 == null && (z3 || (!YogaConstants.isUndefined(desiredWidth) && desiredWidth <= f2))) {
            return StaticLayout.Builder.obtain(spannable, 0, length, sTextPaintInstance, (int) Math.ceil((double) desiredWidth)).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(z2).setBreakStrategy(i4).setHyphenationFrequency(i5).build();
        } else if (metrics2 == null || (!z3 && ((float) metrics2.width) > f2)) {
            StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(spannable, 0, length, sTextPaintInstance, (int) f2).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(z2).setBreakStrategy(i4).setHyphenationFrequency(i5);
            if (Build.VERSION.SDK_INT >= 28) {
                hyphenationFrequency.setUseLineSpacingFromFallbacks(true);
            }
            return hyphenationFrequency.build();
        } else {
            int i6 = metrics2.width;
            if (metrics2.width < 0) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Text width is invalid: " + metrics2.width));
                i3 = 0;
            } else {
                i3 = i6;
            }
            return BoringLayout.make(spannable, sTextPaintInstance, i3, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, metrics, z);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009d, code lost:
        if (r3 > r21) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bd, code lost:
        if (r0 > r23) goto L_0x00bf;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0159  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long measureText(android.content.Context r18, com.facebook.react.common.mapbuffer.MapBuffer r19, com.facebook.react.common.mapbuffer.MapBuffer r20, float r21, com.facebook.yoga.YogaMeasureMode r22, float r23, com.facebook.yoga.YogaMeasureMode r24, com.facebook.react.views.text.ReactTextViewManagerCallback r25, float[] r26) {
        /*
            r0 = r20
            r8 = r22
            r9 = r24
            r1 = r18
            r2 = r19
            r3 = r25
            android.text.Spannable r10 = getOrCreateSpannableForText(r1, r2, r3)
            if (r10 != 0) goto L_0x0015
            r0 = 0
            return r0
        L_0x0015:
            r1 = 2
            java.lang.String r1 = r0.getString(r1)
            int r6 = com.facebook.react.views.text.TextAttributeProps.getTextBreakStrategy(r1)
            r1 = 4
            boolean r2 = r0.contains(r1)
            r11 = 1
            if (r2 == 0) goto L_0x002c
            boolean r1 = r0.getBoolean(r1)
            r5 = r1
            goto L_0x002d
        L_0x002c:
            r5 = r11
        L_0x002d:
            r1 = 5
            java.lang.String r1 = r0.getString(r1)
            int r7 = com.facebook.react.views.text.TextAttributeProps.getHyphenationFrequency(r1)
            android.text.TextPaint r1 = sTextPaintInstance
            android.text.BoringLayout$Metrics r2 = android.text.BoringLayout.isBoring(r10, r1)
            r1 = r10
            r3 = r21
            r4 = r22
            android.text.Layout r1 = createLayout(r1, r2, r3, r4, r5, r6, r7)
            r2 = 0
            boolean r3 = r0.contains(r2)
            r4 = -1
            if (r3 == 0) goto L_0x0052
            int r0 = r0.getInt(r2)
            goto L_0x0053
        L_0x0052:
            r0 = r4
        L_0x0053:
            if (r0 == r4) goto L_0x0061
            if (r0 != 0) goto L_0x0058
            goto L_0x0061
        L_0x0058:
            int r3 = r1.getLineCount()
            int r0 = java.lang.Math.min(r0, r3)
            goto L_0x0065
        L_0x0061:
            int r0 = r1.getLineCount()
        L_0x0065:
            com.facebook.yoga.YogaMeasureMode r3 = com.facebook.yoga.YogaMeasureMode.EXACTLY
            r5 = 10
            if (r8 != r3) goto L_0x006c
            goto L_0x009f
        L_0x006c:
            r3 = 0
            r6 = r2
        L_0x006e:
            if (r6 >= r0) goto L_0x0097
            int r7 = r10.length()
            if (r7 <= 0) goto L_0x0083
            int r7 = r1.getLineEnd(r6)
            int r7 = r7 - r11
            char r7 = r10.charAt(r7)
            if (r7 != r5) goto L_0x0083
            r7 = r11
            goto L_0x0084
        L_0x0083:
            r7 = r2
        L_0x0084:
            if (r7 == 0) goto L_0x008b
            float r7 = r1.getLineMax(r6)
            goto L_0x008f
        L_0x008b:
            float r7 = r1.getLineWidth(r6)
        L_0x008f:
            int r12 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r12 <= 0) goto L_0x0094
            r3 = r7
        L_0x0094:
            int r6 = r6 + 1
            goto L_0x006e
        L_0x0097:
            com.facebook.yoga.YogaMeasureMode r6 = com.facebook.yoga.YogaMeasureMode.AT_MOST
            if (r8 != r6) goto L_0x00a1
            int r6 = (r3 > r21 ? 1 : (r3 == r21 ? 0 : -1))
            if (r6 <= 0) goto L_0x00a1
        L_0x009f:
            r3 = r21
        L_0x00a1:
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 29
            if (r6 <= r7) goto L_0x00ad
            double r6 = (double) r3
            double r6 = java.lang.Math.ceil(r6)
            float r3 = (float) r6
        L_0x00ad:
            com.facebook.yoga.YogaMeasureMode r6 = com.facebook.yoga.YogaMeasureMode.EXACTLY
            if (r9 == r6) goto L_0x00bf
            int r0 = r0 - r11
            int r0 = r1.getLineBottom(r0)
            float r0 = (float) r0
            com.facebook.yoga.YogaMeasureMode r6 = com.facebook.yoga.YogaMeasureMode.AT_MOST
            if (r9 != r6) goto L_0x00c1
            int r6 = (r0 > r23 ? 1 : (r0 == r23 ? 0 : -1))
            if (r6 <= 0) goto L_0x00c1
        L_0x00bf:
            r0 = r23
        L_0x00c1:
            r6 = r2
            r7 = r6
        L_0x00c3:
            int r8 = r10.length()
            if (r6 >= r8) goto L_0x01a9
            int r8 = r10.length()
            java.lang.Class<com.facebook.react.views.text.TextInlineViewPlaceholderSpan> r9 = com.facebook.react.views.text.TextInlineViewPlaceholderSpan.class
            int r8 = r10.nextSpanTransition(r6, r8, r9)
            java.lang.Class<com.facebook.react.views.text.TextInlineViewPlaceholderSpan> r9 = com.facebook.react.views.text.TextInlineViewPlaceholderSpan.class
            java.lang.Object[] r6 = r10.getSpans(r6, r8, r9)
            com.facebook.react.views.text.TextInlineViewPlaceholderSpan[] r6 = (com.facebook.react.views.text.TextInlineViewPlaceholderSpan[]) r6
            int r9 = r6.length
            r12 = r2
        L_0x00dd:
            if (r12 >= r9) goto L_0x01a6
            r13 = r6[r12]
            int r14 = r10.getSpanStart(r13)
            int r15 = r1.getLineForOffset(r14)
            int r16 = r1.getEllipsisCount(r15)
            if (r16 <= 0) goto L_0x00f2
            r16 = r11
            goto L_0x00f4
        L_0x00f2:
            r16 = r2
        L_0x00f4:
            if (r16 == 0) goto L_0x010d
            int r16 = r1.getLineStart(r15)
            int r17 = r1.getEllipsisStart(r15)
            int r2 = r16 + r17
            if (r14 < r2) goto L_0x010d
            int r2 = r1.getLineEnd(r15)
            if (r14 < r2) goto L_0x0109
            goto L_0x010d
        L_0x0109:
            r4 = r5
            r5 = r11
            goto L_0x019e
        L_0x010d:
            int r2 = r13.getWidth()
            float r2 = (float) r2
            int r13 = r13.getHeight()
            float r13 = (float) r13
            boolean r5 = r1.isRtlCharAt(r14)
            int r11 = r1.getParagraphDirection(r15)
            if (r11 != r4) goto L_0x0123
            r11 = 1
            goto L_0x0124
        L_0x0123:
            r11 = 0
        L_0x0124:
            int r16 = r10.length()
            r17 = 1
            int r4 = r16 + -1
            if (r14 != r4) goto L_0x0161
            int r4 = r10.length()
            if (r4 <= 0) goto L_0x0145
            int r4 = r1.getLineEnd(r15)
            int r4 = r4 + -1
            char r4 = r10.charAt(r4)
            r5 = 10
            if (r4 != r5) goto L_0x0147
            r17 = 1
            goto L_0x0149
        L_0x0145:
            r5 = 10
        L_0x0147:
            r17 = 0
        L_0x0149:
            if (r17 == 0) goto L_0x0150
            float r4 = r1.getLineMax(r15)
            goto L_0x0154
        L_0x0150:
            float r4 = r1.getLineWidth(r15)
        L_0x0154:
            if (r11 == 0) goto L_0x0159
            float r2 = r3 - r4
            goto L_0x015f
        L_0x0159:
            float r4 = r1.getLineRight(r15)
            float r2 = r4 - r2
        L_0x015f:
            r4 = r5
            goto L_0x0186
        L_0x0161:
            r4 = 10
            if (r11 != r5) goto L_0x0168
            r17 = 1
            goto L_0x016a
        L_0x0168:
            r17 = 0
        L_0x016a:
            if (r17 == 0) goto L_0x0171
            float r14 = r1.getPrimaryHorizontal(r14)
            goto L_0x0175
        L_0x0171:
            float r14 = r1.getSecondaryHorizontal(r14)
        L_0x0175:
            if (r11 == 0) goto L_0x017f
            float r11 = r1.getLineRight(r15)
            float r11 = r11 - r14
            float r11 = r3 - r11
            goto L_0x0180
        L_0x017f:
            r11 = r14
        L_0x0180:
            if (r5 == 0) goto L_0x0185
            float r2 = r11 - r2
            goto L_0x0186
        L_0x0185:
            r2 = r11
        L_0x0186:
            int r5 = r1.getLineBaseline(r15)
            float r5 = (float) r5
            float r5 = r5 - r13
            int r11 = r7 * 2
            float r5 = com.facebook.react.uimanager.PixelUtil.toDIPFromPixel(r5)
            r26[r11] = r5
            r5 = 1
            int r11 = r11 + r5
            float r2 = com.facebook.react.uimanager.PixelUtil.toDIPFromPixel(r2)
            r26[r11] = r2
            int r7 = r7 + 1
        L_0x019e:
            int r12 = r12 + 1
            r11 = r5
            r2 = 0
            r5 = r4
            r4 = -1
            goto L_0x00dd
        L_0x01a6:
            r6 = r8
            goto L_0x00c3
        L_0x01a9:
            float r1 = com.facebook.react.uimanager.PixelUtil.toDIPFromPixel(r3)
            float r0 = com.facebook.react.uimanager.PixelUtil.toDIPFromPixel(r0)
            long r0 = com.facebook.yoga.YogaMeasureOutput.make((float) r1, (float) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextLayoutManagerMapBuffer.measureText(android.content.Context, com.facebook.react.common.mapbuffer.MapBuffer, com.facebook.react.common.mapbuffer.MapBuffer, float, com.facebook.yoga.YogaMeasureMode, float, com.facebook.yoga.YogaMeasureMode, com.facebook.react.views.text.ReactTextViewManagerCallback, float[]):long");
    }

    public static WritableArray measureLines(Context context, MapBuffer mapBuffer, MapBuffer mapBuffer2, float f) {
        Spannable orCreateSpannableForText = getOrCreateSpannableForText(context, mapBuffer, (ReactTextViewManagerCallback) null);
        TextPaint textPaint = sTextPaintInstance;
        return FontMetricsUtil.getFontMetrics(orCreateSpannableForText, createLayout(orCreateSpannableForText, BoringLayout.isBoring(orCreateSpannableForText, textPaint), f, YogaMeasureMode.EXACTLY, mapBuffer2.contains(4) ? mapBuffer2.getBoolean(4) : true, TextAttributeProps.getTextBreakStrategy(mapBuffer2.getString(2)), TextAttributeProps.getTextBreakStrategy(mapBuffer2.getString(5))), textPaint, context);
    }
}
