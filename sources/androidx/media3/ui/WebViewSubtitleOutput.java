package androidx.media3.ui;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Util;
import androidx.media3.ui.SubtitleView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class WebViewSubtitleOutput extends FrameLayout implements SubtitleView.Output {
    private static final float CSS_LINE_HEIGHT = 1.2f;
    private static final String DEFAULT_BACKGROUND_CSS_CLASS = "default_bg";
    private float bottomPaddingFraction;
    private final CanvasSubtitleOutput canvasSubtitleOutput;
    private float defaultTextSize;
    private int defaultTextSizeType;
    private CaptionStyleCompat style;
    private List<Cue> textCues;
    private final WebView webView;

    private static int anchorTypeToTranslatePercent(int i) {
        if (i != 1) {
            return i != 2 ? 0 : -100;
        }
        return -50;
    }

    private static String convertVerticalTypeToCss(int i) {
        return i != 1 ? i != 2 ? "horizontal-tb" : "vertical-lr" : "vertical-rl";
    }

    public WebViewSubtitleOutput(Context context) {
        this(context, (AttributeSet) null);
    }

    public WebViewSubtitleOutput(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textCues = Collections.emptyList();
        this.style = CaptionStyleCompat.DEFAULT;
        this.defaultTextSize = 0.0533f;
        this.defaultTextSizeType = 0;
        this.bottomPaddingFraction = 0.08f;
        CanvasSubtitleOutput canvasSubtitleOutput2 = new CanvasSubtitleOutput(context, attributeSet);
        this.canvasSubtitleOutput = canvasSubtitleOutput2;
        AnonymousClass1 r2 = new WebView(context, attributeSet) {
            public boolean onTouchEvent(MotionEvent motionEvent) {
                super.onTouchEvent(motionEvent);
                return false;
            }

            public boolean performClick() {
                super.performClick();
                return false;
            }
        };
        this.webView = r2;
        r2.setBackgroundColor(0);
        addView(canvasSubtitleOutput2);
        addView(r2);
    }

    public void update(List<Cue> list, CaptionStyleCompat captionStyleCompat, float f, int i, float f2) {
        this.style = captionStyleCompat;
        this.defaultTextSize = f;
        this.defaultTextSizeType = i;
        this.bottomPaddingFraction = f2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            Cue cue = list.get(i2);
            if (cue.bitmap != null) {
                arrayList.add(cue);
            } else {
                arrayList2.add(cue);
            }
        }
        if (!this.textCues.isEmpty() || !arrayList2.isEmpty()) {
            this.textCues = arrayList2;
            updateWebView();
        }
        this.canvasSubtitleOutput.update(arrayList, captionStyleCompat, f, i, f2);
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z && !this.textCues.isEmpty()) {
            updateWebView();
        }
    }

    public void destroy() {
        this.webView.destroy();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0146, code lost:
        if (r5 != false) goto L_0x014c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0149, code lost:
        if (r5 != false) goto L_0x014b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0124  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01e3  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0203  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateWebView() {
        /*
            r32 = this;
            r0 = r32
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            androidx.media3.ui.CaptionStyleCompat r2 = r0.style
            int r2 = r2.foregroundColor
            java.lang.String r2 = androidx.media3.ui.HtmlUtils.toCssRgba(r2)
            int r3 = r0.defaultTextSizeType
            float r4 = r0.defaultTextSize
            java.lang.String r3 = r0.convertTextSizeToCss(r3, r4)
            r4 = 1067030938(0x3f99999a, float:1.2)
            java.lang.Float r5 = java.lang.Float.valueOf(r4)
            androidx.media3.ui.CaptionStyleCompat r6 = r0.style
            java.lang.String r6 = convertCaptionStyleToCssTextShadow(r6)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3, r5, r6}
            java.lang.String r3 = "<body><div style='-webkit-user-select:none;position:fixed;top:0;bottom:0;left:0;right:0;color:%s;font-size:%s;line-height:%.2f;text-shadow:%s;'>"
            java.lang.String r2 = androidx.media3.common.util.Util.formatInvariant(r3, r2)
            r1.append(r2)
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.lang.String r3 = "default_bg"
            java.lang.String r5 = androidx.media3.ui.HtmlUtils.cssAllClassDescendantsSelector(r3)
            androidx.media3.ui.CaptionStyleCompat r6 = r0.style
            int r6 = r6.backgroundColor
            java.lang.String r6 = androidx.media3.ui.HtmlUtils.toCssRgba(r6)
            java.lang.Object[] r6 = new java.lang.Object[]{r6}
            java.lang.String r7 = "background-color:%s;"
            java.lang.String r6 = androidx.media3.common.util.Util.formatInvariant(r7, r6)
            r2.put(r5, r6)
            r6 = 0
        L_0x0052:
            java.util.List<androidx.media3.common.text.Cue> r7 = r0.textCues
            int r7 = r7.size()
            r8 = 1
            if (r6 >= r7) goto L_0x0211
            java.util.List<androidx.media3.common.text.Cue> r7 = r0.textCues
            java.lang.Object r7 = r7.get(r6)
            androidx.media3.common.text.Cue r7 = (androidx.media3.common.text.Cue) r7
            float r9 = r7.position
            r10 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            r11 = 1120403456(0x42c80000, float:100.0)
            if (r9 == 0) goto L_0x0072
            float r9 = r7.position
            float r9 = r9 * r11
            goto L_0x0074
        L_0x0072:
            r9 = 1112014848(0x42480000, float:50.0)
        L_0x0074:
            int r12 = r7.positionAnchor
            int r12 = anchorTypeToTranslatePercent(r12)
            float r13 = r7.line
            int r13 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            r14 = 1065353216(0x3f800000, float:1.0)
            java.lang.String r15 = "%.2f%%"
            if (r13 == 0) goto L_0x00dd
            int r13 = r7.lineType
            if (r13 == r8) goto L_0x00aa
            float r13 = r7.line
            float r13 = r13 * r11
            java.lang.Float r13 = java.lang.Float.valueOf(r13)
            java.lang.Object[] r13 = new java.lang.Object[]{r13}
            java.lang.String r13 = androidx.media3.common.util.Util.formatInvariant(r15, r13)
            int r14 = r7.verticalType
            if (r14 != r8) goto L_0x00a3
            int r14 = r7.lineAnchor
            int r14 = anchorTypeToTranslatePercent(r14)
            int r14 = -r14
            goto L_0x00ef
        L_0x00a3:
            int r14 = r7.lineAnchor
            int r14 = anchorTypeToTranslatePercent(r14)
            goto L_0x00ef
        L_0x00aa:
            float r13 = r7.line
            r16 = 0
            int r13 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            java.lang.String r5 = "%.2fem"
            if (r13 < 0) goto L_0x00c7
            float r13 = r7.line
            float r13 = r13 * r4
            java.lang.Float r13 = java.lang.Float.valueOf(r13)
            java.lang.Object[] r13 = new java.lang.Object[]{r13}
            java.lang.String r13 = androidx.media3.common.util.Util.formatInvariant(r5, r13)
            r21 = r13
            r5 = 0
            goto L_0x00db
        L_0x00c7:
            float r13 = r7.line
            float r13 = -r13
            float r13 = r13 - r14
            float r13 = r13 * r4
            java.lang.Float r13 = java.lang.Float.valueOf(r13)
            java.lang.Object[] r13 = new java.lang.Object[]{r13}
            java.lang.String r13 = androidx.media3.common.util.Util.formatInvariant(r5, r13)
            r5 = r8
            r21 = r13
        L_0x00db:
            r14 = 0
            goto L_0x00f2
        L_0x00dd:
            float r5 = r0.bottomPaddingFraction
            float r14 = r14 - r5
            float r14 = r14 * r11
            java.lang.Float r5 = java.lang.Float.valueOf(r14)
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            java.lang.String r13 = androidx.media3.common.util.Util.formatInvariant(r15, r5)
            r14 = -100
        L_0x00ef:
            r21 = r13
            r5 = 0
        L_0x00f2:
            float r13 = r7.size
            int r10 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            if (r10 == 0) goto L_0x0108
            float r10 = r7.size
            float r10 = r10 * r11
            java.lang.Float r10 = java.lang.Float.valueOf(r10)
            java.lang.Object[] r10 = new java.lang.Object[]{r10}
            java.lang.String r10 = androidx.media3.common.util.Util.formatInvariant(r15, r10)
            goto L_0x010a
        L_0x0108:
            java.lang.String r10 = "fit-content"
        L_0x010a:
            r23 = r10
            android.text.Layout$Alignment r10 = r7.textAlignment
            java.lang.String r24 = convertAlignmentToCss(r10)
            int r10 = r7.verticalType
            java.lang.String r25 = convertVerticalTypeToCss(r10)
            int r10 = r7.textSizeType
            float r11 = r7.textSize
            java.lang.String r26 = r0.convertTextSizeToCss(r10, r11)
            boolean r10 = r7.windowColorSet
            if (r10 == 0) goto L_0x0127
            int r10 = r7.windowColor
            goto L_0x012b
        L_0x0127:
            androidx.media3.ui.CaptionStyleCompat r10 = r0.style
            int r10 = r10.windowColor
        L_0x012b:
            java.lang.String r27 = androidx.media3.ui.HtmlUtils.toCssRgba(r10)
            int r10 = r7.verticalType
            java.lang.String r11 = "right"
            r13 = 2
            java.lang.String r15 = "left"
            java.lang.String r17 = "top"
            if (r10 == r8) goto L_0x0149
            if (r10 == r13) goto L_0x0146
            if (r5 == 0) goto L_0x0141
            java.lang.String r17 = "bottom"
        L_0x0141:
            r18 = r15
            r20 = r17
            goto L_0x0150
        L_0x0146:
            if (r5 == 0) goto L_0x014b
            goto L_0x014c
        L_0x0149:
            if (r5 == 0) goto L_0x014c
        L_0x014b:
            r11 = r15
        L_0x014c:
            r20 = r11
            r18 = r17
        L_0x0150:
            int r5 = r7.verticalType
            if (r5 == r13) goto L_0x015f
            int r5 = r7.verticalType
            if (r5 != r8) goto L_0x0159
            goto L_0x015f
        L_0x0159:
            java.lang.String r5 = "width"
            r22 = r5
            goto L_0x0168
        L_0x015f:
            java.lang.String r5 = "height"
            r22 = r5
            r31 = r14
            r14 = r12
            r12 = r31
        L_0x0168:
            java.lang.CharSequence r5 = r7.text
            android.content.Context r10 = r32.getContext()
            android.content.res.Resources r10 = r10.getResources()
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()
            float r10 = r10.density
            androidx.media3.ui.SpannedToHtmlConverter$HtmlAndCss r5 = androidx.media3.ui.SpannedToHtmlConverter.convert(r5, r10)
            java.util.Set r10 = r2.keySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x0184:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x01b0
            java.lang.Object r11 = r10.next()
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r13 = r2.get(r11)
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r13 = r2.put(r11, r13)
            java.lang.String r13 = (java.lang.String) r13
            if (r13 == 0) goto L_0x01ab
            java.lang.Object r11 = r2.get(r11)
            boolean r11 = r13.equals(r11)
            if (r11 == 0) goto L_0x01a9
            goto L_0x01ab
        L_0x01a9:
            r11 = 0
            goto L_0x01ac
        L_0x01ab:
            r11 = r8
        L_0x01ac:
            androidx.media3.common.util.Assertions.checkState(r11)
            goto L_0x0184
        L_0x01b0:
            java.lang.Integer r17 = java.lang.Integer.valueOf(r6)
            java.lang.Float r19 = java.lang.Float.valueOf(r9)
            java.lang.Integer r28 = java.lang.Integer.valueOf(r12)
            java.lang.Integer r29 = java.lang.Integer.valueOf(r14)
            java.lang.String r30 = getBlockShearTransformFunction(r7)
            java.lang.Object[] r8 = new java.lang.Object[]{r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30}
            java.lang.String r9 = "<div style='position:absolute;z-index:%s;%s:%.2f%%;%s:%s;%s:%s;text-align:%s;writing-mode:%s;font-size:%s;background-color:%s;transform:translate(%s%%,%s%%)%s;'>"
            java.lang.String r8 = androidx.media3.common.util.Util.formatInvariant(r9, r8)
            java.lang.StringBuilder r8 = r1.append(r8)
            java.lang.String r9 = "<span class='%s'>"
            java.lang.Object[] r10 = new java.lang.Object[]{r3}
            java.lang.String r9 = androidx.media3.common.util.Util.formatInvariant(r9, r10)
            r8.append(r9)
            android.text.Layout$Alignment r8 = r7.multiRowAlignment
            if (r8 == 0) goto L_0x0203
            android.text.Layout$Alignment r7 = r7.multiRowAlignment
            java.lang.String r7 = convertAlignmentToCss(r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.String r8 = "<span style='display:inline-block; text-align:%s;'>"
            java.lang.String r7 = androidx.media3.common.util.Util.formatInvariant(r8, r7)
            java.lang.StringBuilder r7 = r1.append(r7)
            java.lang.String r5 = r5.html
            java.lang.StringBuilder r5 = r7.append(r5)
            java.lang.String r7 = "</span>"
            r5.append(r7)
            goto L_0x0208
        L_0x0203:
            java.lang.String r5 = r5.html
            r1.append(r5)
        L_0x0208:
            java.lang.String r5 = "</span></div>"
            r1.append(r5)
            int r6 = r6 + 1
            goto L_0x0052
        L_0x0211:
            java.lang.String r3 = "</div></body></html>"
            r1.append(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "<html><head><style>"
            r3.<init>(r4)
            java.util.Set r4 = r2.keySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0225:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x024d
            java.lang.Object r5 = r4.next()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.StringBuilder r6 = r3.append(r5)
            java.lang.String r7 = "{"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.Object r5 = r2.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.StringBuilder r5 = r6.append(r5)
            java.lang.String r6 = "}"
            r5.append(r6)
            goto L_0x0225
        L_0x024d:
            java.lang.String r2 = "</style></head>"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r3 = 0
            r1.insert(r3, r2)
            android.webkit.WebView r2 = r0.webView
            java.lang.String r1 = r1.toString()
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8
            byte[] r1 = r1.getBytes(r3)
            java.lang.String r1 = android.util.Base64.encodeToString(r1, r8)
            java.lang.String r3 = "text/html"
            java.lang.String r4 = "base64"
            r2.loadData(r1, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.ui.WebViewSubtitleOutput.updateWebView():void");
    }

    private static String getBlockShearTransformFunction(Cue cue) {
        if (cue.shearDegrees == 0.0f) {
            return "";
        }
        return Util.formatInvariant("%s(%.2fdeg)", (cue.verticalType == 2 || cue.verticalType == 1) ? "skewY" : "skewX", Float.valueOf(cue.shearDegrees));
    }

    private String convertTextSizeToCss(int i, float f) {
        float resolveTextSize = SubtitleViewUtils.resolveTextSize(i, f, getHeight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        if (resolveTextSize == -3.4028235E38f) {
            return "unset";
        }
        return Util.formatInvariant("%.2fpx", Float.valueOf(resolveTextSize / getContext().getResources().getDisplayMetrics().density));
    }

    private static String convertCaptionStyleToCssTextShadow(CaptionStyleCompat captionStyleCompat) {
        int i = captionStyleCompat.edgeType;
        if (i == 1) {
            return Util.formatInvariant("1px 1px 0 %1$s, 1px -1px 0 %1$s, -1px 1px 0 %1$s, -1px -1px 0 %1$s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
        }
        if (i == 2) {
            return Util.formatInvariant("0.1em 0.12em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
        }
        if (i == 3) {
            return Util.formatInvariant("0.06em 0.08em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
        }
        if (i != 4) {
            return "unset";
        }
        return Util.formatInvariant("-0.05em -0.05em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
    }

    /* renamed from: androidx.media3.ui.WebViewSubtitleOutput$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$text$Layout$Alignment;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                android.text.Layout$Alignment[] r0 = android.text.Layout.Alignment.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$android$text$Layout$Alignment = r0
                android.text.Layout$Alignment r1 = android.text.Layout.Alignment.ALIGN_NORMAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$android$text$Layout$Alignment     // Catch:{ NoSuchFieldError -> 0x001d }
                android.text.Layout$Alignment r1 = android.text.Layout.Alignment.ALIGN_OPPOSITE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$android$text$Layout$Alignment     // Catch:{ NoSuchFieldError -> 0x0028 }
                android.text.Layout$Alignment r1 = android.text.Layout.Alignment.ALIGN_CENTER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.ui.WebViewSubtitleOutput.AnonymousClass2.<clinit>():void");
        }
    }

    private static String convertAlignmentToCss(Layout.Alignment alignment) {
        if (alignment == null) {
            return TtmlNode.CENTER;
        }
        int i = AnonymousClass2.$SwitchMap$android$text$Layout$Alignment[alignment.ordinal()];
        if (i != 1) {
            return i != 2 ? TtmlNode.CENTER : "end";
        }
        return "start";
    }
}
