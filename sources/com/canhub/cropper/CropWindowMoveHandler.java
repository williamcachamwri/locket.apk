package com.canhub.cropper;

import android.graphics.PointF;
import android.graphics.RectF;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001f\u0018\u0000 82\u00020\u0001:\u000289B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJH\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J@\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u001bH\u0002J\u0018\u0010\"\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J \u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002JH\u0010$\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u001bH\u0002J\u0018\u0010'\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J@\u0010(\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J \u0010*\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u0018\u0010+\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J \u0010,\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002JN\u0010-\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u001b2\u0006\u0010\u0019\u001a\u00020\u0007J@\u00101\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u00102\u001a\u00020\u0007H\u0002JH\u00103\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J@\u00104\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007H\u0002J \u00105\u001a\u00020\u00112\u0006\u00106\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u0007H\u0002R\u000e\u0010\n\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/canhub/cropper/CropWindowMoveHandler;", "", "type", "Lcom/canhub/cropper/CropWindowMoveHandler$Type;", "cropWindowHandler", "Lcom/canhub/cropper/CropWindowHandler;", "touchX", "", "touchY", "(Lcom/canhub/cropper/CropWindowMoveHandler$Type;Lcom/canhub/cropper/CropWindowHandler;FF)V", "mMaxCropHeight", "mMaxCropWidth", "mMinCropHeight", "mMinCropWidth", "mTouchOffset", "Landroid/graphics/PointF;", "adjustBottom", "", "rect", "Landroid/graphics/RectF;", "bottom", "bounds", "viewHeight", "", "snapMargin", "aspectRatio", "leftMoves", "", "rightMoves", "adjustBottomByAspectRatio", "adjustLeft", "left", "topMoves", "bottomMoves", "adjustLeftByAspectRatio", "adjustLeftRightByAspectRatio", "adjustRight", "right", "viewWidth", "adjustRightByAspectRatio", "adjustTop", "top", "adjustTopBottomByAspectRatio", "adjustTopByAspectRatio", "calculateTouchOffset", "move", "x", "y", "fixedAspectRatio", "moveCenter", "snapRadius", "moveSizeWithFixedAspectRatio", "moveSizeWithFreeAspectRatio", "snapEdgesToBounds", "edges", "margin", "Companion", "Type", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropWindowMoveHandler.kt */
public final class CropWindowMoveHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final float mMaxCropHeight;
    private final float mMaxCropWidth;
    private final float mMinCropHeight;
    private final float mMinCropWidth;
    private final PointF mTouchOffset = new PointF(0.0f, 0.0f);
    private final Type type;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/canhub/cropper/CropWindowMoveHandler$Type;", "", "(Ljava/lang/String;I)V", "TOP_LEFT", "TOP_RIGHT", "BOTTOM_LEFT", "BOTTOM_RIGHT", "LEFT", "TOP", "RIGHT", "BOTTOM", "CENTER", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropWindowMoveHandler.kt */
    public enum Type {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        CENTER
    }

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropWindowMoveHandler.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Type.values().length];
            iArr[Type.TOP_LEFT.ordinal()] = 1;
            iArr[Type.TOP_RIGHT.ordinal()] = 2;
            iArr[Type.BOTTOM_LEFT.ordinal()] = 3;
            iArr[Type.BOTTOM_RIGHT.ordinal()] = 4;
            iArr[Type.LEFT.ordinal()] = 5;
            iArr[Type.TOP.ordinal()] = 6;
            iArr[Type.RIGHT.ordinal()] = 7;
            iArr[Type.BOTTOM.ordinal()] = 8;
            iArr[Type.CENTER.ordinal()] = 9;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public CropWindowMoveHandler(Type type2, CropWindowHandler cropWindowHandler, float f, float f2) {
        Intrinsics.checkNotNullParameter(type2, "type");
        Intrinsics.checkNotNullParameter(cropWindowHandler, "cropWindowHandler");
        this.type = type2;
        this.mMinCropWidth = cropWindowHandler.getMinCropWidth();
        this.mMinCropHeight = cropWindowHandler.getMinCropHeight();
        this.mMaxCropWidth = cropWindowHandler.getMaxCropWidth();
        this.mMaxCropHeight = cropWindowHandler.getMaxCropHeight();
        calculateTouchOffset(cropWindowHandler.getRect(), f, f2);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002¨\u0006\t"}, d2 = {"Lcom/canhub/cropper/CropWindowMoveHandler$Companion;", "", "()V", "calculateAspectRatio", "", "left", "top", "right", "bottom", "cropper_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: CropWindowMoveHandler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final float calculateAspectRatio(float f, float f2, float f3, float f4) {
            return (f3 - f) / (f4 - f2);
        }

        private Companion() {
        }
    }

    public final void move(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3, boolean z, float f4) {
        RectF rectF3 = rectF;
        Intrinsics.checkNotNullParameter(rectF, "rect");
        RectF rectF4 = rectF2;
        Intrinsics.checkNotNullParameter(rectF2, "bounds");
        float f5 = f + this.mTouchOffset.x;
        float f6 = f2 + this.mTouchOffset.y;
        if (this.type == Type.CENTER) {
            moveCenter(rectF, f5, f6, rectF2, i, i2, f3);
        } else if (z) {
            moveSizeWithFixedAspectRatio(rectF, f5, f6, rectF2, i, i2, f3, f4);
        } else {
            moveSizeWithFreeAspectRatio(rectF, f5, f6, rectF2, i, i2, f3);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        r4 = r4 - r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        r3.mTouchOffset.x = r1;
        r3.mTouchOffset.y = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        r1 = r4 - r5;
        r4 = 0.0f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void calculateTouchOffset(android.graphics.RectF r4, float r5, float r6) {
        /*
            r3 = this;
            com.canhub.cropper.CropWindowMoveHandler$Type r0 = r3.type
            int[] r1 = com.canhub.cropper.CropWindowMoveHandler.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 0
            switch(r0) {
                case 1: goto L_0x0040;
                case 2: goto L_0x0039;
                case 3: goto L_0x0032;
                case 4: goto L_0x002b;
                case 5: goto L_0x0024;
                case 6: goto L_0x0021;
                case 7: goto L_0x001e;
                case 8: goto L_0x001b;
                case 9: goto L_0x0010;
                default: goto L_0x000e;
            }
        L_0x000e:
            r4 = r1
            goto L_0x0047
        L_0x0010:
            float r0 = r4.centerX()
            float r1 = r0 - r5
            float r4 = r4.centerY()
            goto L_0x0046
        L_0x001b:
            float r4 = r4.bottom
            goto L_0x0046
        L_0x001e:
            float r4 = r4.right
            goto L_0x0026
        L_0x0021:
            float r4 = r4.top
            goto L_0x0046
        L_0x0024:
            float r4 = r4.left
        L_0x0026:
            float r4 = r4 - r5
            r2 = r1
            r1 = r4
            r4 = r2
            goto L_0x0047
        L_0x002b:
            float r0 = r4.right
            float r1 = r0 - r5
            float r4 = r4.bottom
            goto L_0x0046
        L_0x0032:
            float r0 = r4.left
            float r1 = r0 - r5
            float r4 = r4.bottom
            goto L_0x0046
        L_0x0039:
            float r0 = r4.right
            float r1 = r0 - r5
            float r4 = r4.top
            goto L_0x0046
        L_0x0040:
            float r0 = r4.left
            float r1 = r0 - r5
            float r4 = r4.top
        L_0x0046:
            float r4 = r4 - r6
        L_0x0047:
            android.graphics.PointF r5 = r3.mTouchOffset
            r5.x = r1
            android.graphics.PointF r5 = r3.mTouchOffset
            r5.y = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.canhub.cropper.CropWindowMoveHandler.calculateTouchOffset(android.graphics.RectF, float, float):void");
    }

    private final void moveCenter(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3) {
        float centerX = f - rectF.centerX();
        float centerY = f2 - rectF.centerY();
        if (rectF.left + centerX < 0.0f || rectF.right + centerX > ((float) i) || rectF.left + centerX < rectF2.left || rectF.right + centerX > rectF2.right) {
            centerX /= 1.05f;
            this.mTouchOffset.x -= centerX / ((float) 2);
        }
        if (rectF.top + centerY < 0.0f || rectF.bottom + centerY > ((float) i2) || rectF.top + centerY < rectF2.top || rectF.bottom + centerY > rectF2.bottom) {
            centerY /= 1.05f;
            this.mTouchOffset.y -= centerY / ((float) 2);
        }
        rectF.offset(centerX, centerY);
        snapEdgesToBounds(rectF, rectF2, f3);
    }

    private final void moveSizeWithFreeAspectRatio(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3) {
        switch (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()]) {
            case 1:
                RectF rectF3 = rectF;
                RectF rectF4 = rectF2;
                float f4 = f3;
                adjustTop(rectF3, f2, rectF4, f4, 0.0f, false, false);
                adjustLeft(rectF3, f, rectF4, f4, 0.0f, false, false);
                return;
            case 2:
                RectF rectF5 = rectF;
                RectF rectF6 = rectF2;
                adjustTop(rectF5, f2, rectF6, f3, 0.0f, false, false);
                adjustRight(rectF5, f, rectF6, i, f3, 0.0f, false, false);
                return;
            case 3:
                RectF rectF7 = rectF;
                RectF rectF8 = rectF2;
                adjustBottom(rectF7, f2, rectF8, i2, f3, 0.0f, false, false);
                adjustLeft(rectF7, f, rectF8, f3, 0.0f, false, false);
                return;
            case 4:
                RectF rectF9 = rectF;
                RectF rectF10 = rectF2;
                float f5 = f3;
                adjustBottom(rectF9, f2, rectF10, i2, f5, 0.0f, false, false);
                adjustRight(rectF9, f, rectF10, i, f5, 0.0f, false, false);
                return;
            case 5:
                adjustLeft(rectF, f, rectF2, f3, 0.0f, false, false);
                return;
            case 6:
                adjustTop(rectF, f2, rectF2, f3, 0.0f, false, false);
                return;
            case 7:
                adjustRight(rectF, f, rectF2, i, f3, 0.0f, false, false);
                return;
            case 8:
                adjustBottom(rectF, f2, rectF2, i2, f3, 0.0f, false, false);
                return;
            default:
                return;
        }
    }

    private final void moveSizeWithFixedAspectRatio(RectF rectF, float f, float f2, RectF rectF2, int i, int i2, float f3, float f4) {
        RectF rectF3 = rectF;
        float f5 = f;
        float f6 = f2;
        RectF rectF4 = rectF2;
        float f7 = f4;
        switch (WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()]) {
            case 1:
                if (Companion.calculateAspectRatio(f, f6, rectF3.right, rectF3.bottom) < f7) {
                    adjustTop(rectF, f2, rectF2, f3, f4, true, false);
                    adjustLeftByAspectRatio(rectF, f7);
                    return;
                }
                adjustLeft(rectF, f, rectF2, f3, f4, true, false);
                adjustTopByAspectRatio(rectF, f7);
                return;
            case 2:
                if (Companion.calculateAspectRatio(rectF3.left, f6, f, rectF3.bottom) < f7) {
                    adjustTop(rectF, f2, rectF2, f3, f4, false, true);
                    adjustRightByAspectRatio(rectF, f7);
                    return;
                }
                adjustRight(rectF, f, rectF2, i, f3, f4, true, false);
                adjustTopByAspectRatio(rectF, f7);
                return;
            case 3:
                if (Companion.calculateAspectRatio(f, rectF3.top, rectF3.right, f6) < f7) {
                    adjustBottom(rectF, f2, rectF2, i2, f3, f4, true, false);
                    adjustLeftByAspectRatio(rectF, f7);
                    return;
                }
                adjustLeft(rectF, f, rectF2, f3, f4, false, true);
                adjustBottomByAspectRatio(rectF, f7);
                return;
            case 4:
                if (Companion.calculateAspectRatio(rectF3.left, rectF3.top, f, f6) < f7) {
                    adjustBottom(rectF, f2, rectF2, i2, f3, f4, false, true);
                    adjustRightByAspectRatio(rectF, f7);
                    return;
                }
                adjustRight(rectF, f, rectF2, i, f3, f4, false, true);
                adjustBottomByAspectRatio(rectF, f7);
                return;
            case 5:
                adjustLeft(rectF, f, rectF2, f3, f4, true, true);
                adjustTopBottomByAspectRatio(rectF, rectF4, f7);
                return;
            case 6:
                adjustTop(rectF, f2, rectF2, f3, f4, true, true);
                adjustLeftRightByAspectRatio(rectF, rectF4, f7);
                return;
            case 7:
                adjustRight(rectF, f, rectF2, i, f3, f4, true, true);
                adjustTopBottomByAspectRatio(rectF, rectF4, f7);
                return;
            case 8:
                adjustBottom(rectF, f2, rectF2, i2, f3, f4, true, true);
                adjustLeftRightByAspectRatio(rectF, rectF4, f7);
                return;
            default:
                return;
        }
    }

    private final void snapEdgesToBounds(RectF rectF, RectF rectF2, float f) {
        if (rectF.left < rectF2.left + f) {
            rectF.offset(rectF2.left - rectF.left, 0.0f);
        }
        if (rectF.top < rectF2.top + f) {
            rectF.offset(0.0f, rectF2.top - rectF.top);
        }
        if (rectF.right > rectF2.right - f) {
            rectF.offset(rectF2.right - rectF.right, 0.0f);
        }
        if (rectF.bottom > rectF2.bottom - f) {
            rectF.offset(0.0f, rectF2.bottom - rectF.bottom);
        }
    }

    private final void adjustLeft(RectF rectF, float f, RectF rectF2, float f2, float f3, boolean z, boolean z2) {
        if (f < 0.0f) {
            f /= 1.05f;
            this.mTouchOffset.x -= f / 1.1f;
        }
        if (f < rectF2.left) {
            this.mTouchOffset.x -= (f - rectF2.left) / 2.0f;
        }
        if (f - rectF2.left < f2) {
            f = rectF2.left;
        }
        if (rectF.right - f < this.mMinCropWidth) {
            f = rectF.right - this.mMinCropWidth;
        }
        if (rectF.right - f > this.mMaxCropWidth) {
            f = rectF.right - this.mMaxCropWidth;
        }
        if (f - rectF2.left < f2) {
            f = rectF2.left;
        }
        if (f3 > 0.0f) {
            float f4 = (rectF.right - f) / f3;
            if (f4 < this.mMinCropHeight) {
                f = Math.max(rectF2.left, rectF.right - (this.mMinCropHeight * f3));
                f4 = (rectF.right - f) / f3;
            }
            if (f4 > this.mMaxCropHeight) {
                f = Math.max(rectF2.left, rectF.right - (this.mMaxCropHeight * f3));
                f4 = (rectF.right - f) / f3;
            }
            if (!z || !z2) {
                if (z && rectF.bottom - f4 < rectF2.top) {
                    f = Math.max(rectF2.left, rectF.right - ((rectF.bottom - rectF2.top) * f3));
                    f4 = (rectF.right - f) / f3;
                }
                if (z2 && rectF.top + f4 > rectF2.bottom) {
                    f = Math.max(f, Math.max(rectF2.left, rectF.right - ((rectF2.bottom - rectF.top) * f3)));
                }
            } else {
                f = Math.max(f, Math.max(rectF2.left, rectF.right - (rectF2.height() * f3)));
            }
        }
        rectF.left = f;
    }

    private final void adjustRight(RectF rectF, float f, RectF rectF2, int i, float f2, float f3, boolean z, boolean z2) {
        float f4 = (float) i;
        if (f > f4) {
            f = ((f - f4) / 1.05f) + f4;
            this.mTouchOffset.x -= (f - f4) / 1.1f;
        }
        if (f > rectF2.right) {
            this.mTouchOffset.x -= (f - rectF2.right) / 2.0f;
        }
        if (rectF2.right - f < f2) {
            f = rectF2.right;
        }
        if (f - rectF.left < this.mMinCropWidth) {
            f = rectF.left + this.mMinCropWidth;
        }
        if (f - rectF.left > this.mMaxCropWidth) {
            f = rectF.left + this.mMaxCropWidth;
        }
        if (rectF2.right - f < f2) {
            f = rectF2.right;
        }
        if (f3 > 0.0f) {
            float f5 = (f - rectF.left) / f3;
            if (f5 < this.mMinCropHeight) {
                f = Math.min(rectF2.right, rectF.left + (this.mMinCropHeight * f3));
                f5 = (f - rectF.left) / f3;
            }
            if (f5 > this.mMaxCropHeight) {
                f = Math.min(rectF2.right, rectF.left + (this.mMaxCropHeight * f3));
                f5 = (f - rectF.left) / f3;
            }
            if (!z || !z2) {
                if (z && rectF.bottom - f5 < rectF2.top) {
                    f = Math.min(rectF2.right, rectF.left + ((rectF.bottom - rectF2.top) * f3));
                    f5 = (f - rectF.left) / f3;
                }
                if (z2 && rectF.top + f5 > rectF2.bottom) {
                    f = Math.min(f, Math.min(rectF2.right, rectF.left + ((rectF2.bottom - rectF.top) * f3)));
                }
            } else {
                f = Math.min(f, Math.min(rectF2.right, rectF.left + (rectF2.height() * f3)));
            }
        }
        rectF.right = f;
    }

    private final void adjustTop(RectF rectF, float f, RectF rectF2, float f2, float f3, boolean z, boolean z2) {
        if (f < 0.0f) {
            f /= 1.05f;
            this.mTouchOffset.y -= f / 1.1f;
        }
        if (f < rectF2.top) {
            this.mTouchOffset.y -= (f - rectF2.top) / 2.0f;
        }
        if (f - rectF2.top < f2) {
            f = rectF2.top;
        }
        if (rectF.bottom - f < this.mMinCropHeight) {
            f = rectF.bottom - this.mMinCropHeight;
        }
        if (rectF.bottom - f > this.mMaxCropHeight) {
            f = rectF.bottom - this.mMaxCropHeight;
        }
        if (f - rectF2.top < f2) {
            f = rectF2.top;
        }
        if (f3 > 0.0f) {
            float f4 = (rectF.bottom - f) * f3;
            if (f4 < this.mMinCropWidth) {
                f = Math.max(rectF2.top, rectF.bottom - (this.mMinCropWidth / f3));
                f4 = (rectF.bottom - f) * f3;
            }
            if (f4 > this.mMaxCropWidth) {
                f = Math.max(rectF2.top, rectF.bottom - (this.mMaxCropWidth / f3));
                f4 = (rectF.bottom - f) * f3;
            }
            if (!z || !z2) {
                if (z && rectF.right - f4 < rectF2.left) {
                    f = Math.max(rectF2.top, rectF.bottom - ((rectF.right - rectF2.left) / f3));
                    f4 = (rectF.bottom - f) * f3;
                }
                if (z2 && rectF.left + f4 > rectF2.right) {
                    f = Math.max(f, Math.max(rectF2.top, rectF.bottom - ((rectF2.right - rectF.left) / f3)));
                }
            } else {
                f = Math.max(f, Math.max(rectF2.top, rectF.bottom - (rectF2.width() / f3)));
            }
        }
        rectF.top = f;
    }

    private final void adjustBottom(RectF rectF, float f, RectF rectF2, int i, float f2, float f3, boolean z, boolean z2) {
        float f4 = (float) i;
        if (f > f4) {
            f = ((f - f4) / 1.05f) + f4;
            this.mTouchOffset.y -= (f - f4) / 1.1f;
        }
        if (f > rectF2.bottom) {
            this.mTouchOffset.y -= (f - rectF2.bottom) / 2.0f;
        }
        if (rectF2.bottom - f < f2) {
            f = rectF2.bottom;
        }
        if (f - rectF.top < this.mMinCropHeight) {
            f = rectF.top + this.mMinCropHeight;
        }
        if (f - rectF.top > this.mMaxCropHeight) {
            f = rectF.top + this.mMaxCropHeight;
        }
        if (rectF2.bottom - f < f2) {
            f = rectF2.bottom;
        }
        if (f3 > 0.0f) {
            float f5 = (f - rectF.top) * f3;
            if (f5 < this.mMinCropWidth) {
                f = Math.min(rectF2.bottom, rectF.top + (this.mMinCropWidth / f3));
                f5 = (f - rectF.top) * f3;
            }
            if (f5 > this.mMaxCropWidth) {
                f = Math.min(rectF2.bottom, rectF.top + (this.mMaxCropWidth / f3));
                f5 = (f - rectF.top) * f3;
            }
            if (!z || !z2) {
                if (z && rectF.right - f5 < rectF2.left) {
                    f = Math.min(rectF2.bottom, rectF.top + ((rectF.right - rectF2.left) / f3));
                    f5 = (f - rectF.top) * f3;
                }
                if (z2 && rectF.left + f5 > rectF2.right) {
                    f = Math.min(f, Math.min(rectF2.bottom, rectF.top + ((rectF2.right - rectF.left) / f3)));
                }
            } else {
                f = Math.min(f, Math.min(rectF2.bottom, rectF.top + (rectF2.width() / f3)));
            }
        }
        rectF.bottom = f;
    }

    private final void adjustLeftByAspectRatio(RectF rectF, float f) {
        rectF.left = rectF.right - (rectF.height() * f);
    }

    private final void adjustTopByAspectRatio(RectF rectF, float f) {
        rectF.top = rectF.bottom - (rectF.width() / f);
    }

    private final void adjustRightByAspectRatio(RectF rectF, float f) {
        rectF.right = rectF.left + (rectF.height() * f);
    }

    private final void adjustBottomByAspectRatio(RectF rectF, float f) {
        rectF.bottom = rectF.top + (rectF.width() / f);
    }

    private final void adjustLeftRightByAspectRatio(RectF rectF, RectF rectF2, float f) {
        rectF.inset((rectF.width() - (rectF.height() * f)) / ((float) 2), 0.0f);
        if (rectF.left < rectF2.left) {
            rectF.offset(rectF2.left - rectF.left, 0.0f);
        }
        if (rectF.right > rectF2.right) {
            rectF.offset(rectF2.right - rectF.right, 0.0f);
        }
    }

    private final void adjustTopBottomByAspectRatio(RectF rectF, RectF rectF2, float f) {
        rectF.inset(0.0f, (rectF.height() - (rectF.width() / f)) / ((float) 2));
        if (rectF.top < rectF2.top) {
            rectF.offset(0.0f, rectF2.top - rectF.top);
        }
        if (rectF.bottom > rectF2.bottom) {
            rectF.offset(0.0f, rectF2.bottom - rectF.bottom);
        }
    }
}
