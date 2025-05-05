package com.ijzerenhein.sharedelement;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.ViewGroup;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.ijzerenhein.sharedelement.RNSharedElementDrawable;
import expo.modules.notifications.notifications.channels.serializers.NotificationsChannelSerializer;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import java.util.Iterator;

public class RNSharedElementTransition extends ViewGroup {
    private RNSharedElementAlign mAlign = RNSharedElementAlign.CENTER_CENTER;
    private RNSharedElementAnimation mAnimation = RNSharedElementAnimation.MOVE;
    private final RNSharedElementView mEndView;
    private boolean mInitialLayoutPassCompleted = false;
    private boolean mInitialNodePositionSet = false;
    private int mInitialVisibleAncestorIndex;
    private final ArrayList<RNSharedElementTransitionItem> mItems;
    private final RNSharedElementNodeManager mNodeManager;
    private float mNodePosition = 0.0f;
    private final int[] mParentOffset;
    private boolean mReactLayoutSet = false;
    private boolean mRequiresClipping;
    private RNSharedElementResize mResize = RNSharedElementResize.STRETCH;
    private final RNSharedElementView mStartView;

    public boolean hasOverlappingRendering() {
        return false;
    }

    public void requestLayout() {
    }

    enum Item {
        START(0),
        END(1);
        
        private final int value;

        private Item(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public RNSharedElementTransition(ThemedReactContext themedReactContext, RNSharedElementNodeManager rNSharedElementNodeManager) {
        super(themedReactContext);
        ArrayList<RNSharedElementTransitionItem> arrayList = new ArrayList<>();
        this.mItems = arrayList;
        this.mParentOffset = new int[2];
        this.mRequiresClipping = false;
        this.mInitialVisibleAncestorIndex = -1;
        this.mNodeManager = rNSharedElementNodeManager;
        arrayList.add(new RNSharedElementTransitionItem(rNSharedElementNodeManager, "start"));
        arrayList.add(new RNSharedElementTransitionItem(rNSharedElementNodeManager, "end"));
        RNSharedElementView rNSharedElementView = new RNSharedElementView(themedReactContext);
        this.mStartView = rNSharedElementView;
        addView(rNSharedElementView);
        RNSharedElementView rNSharedElementView2 = new RNSharedElementView(themedReactContext);
        this.mEndView = rNSharedElementView2;
        addView(rNSharedElementView2);
    }

    /* access modifiers changed from: package-private */
    public void releaseData() {
        Iterator<RNSharedElementTransitionItem> it = this.mItems.iterator();
        while (it.hasNext()) {
            it.next().setNode((RNSharedElementNode) null);
        }
    }

    /* access modifiers changed from: package-private */
    public RNSharedElementNodeManager getNodeManager() {
        return this.mNodeManager;
    }

    /* access modifiers changed from: package-private */
    public void setItemNode(Item item, RNSharedElementNode rNSharedElementNode) {
        this.mItems.get(item.getValue()).setNode(rNSharedElementNode);
        requestStylesAndContent(false);
    }

    /* access modifiers changed from: package-private */
    public void setAnimation(RNSharedElementAnimation rNSharedElementAnimation) {
        if (this.mAnimation != rNSharedElementAnimation) {
            this.mAnimation = rNSharedElementAnimation;
            updateLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void setResize(RNSharedElementResize rNSharedElementResize) {
        if (this.mResize != rNSharedElementResize) {
            this.mResize = rNSharedElementResize;
            updateLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void setAlign(RNSharedElementAlign rNSharedElementAlign) {
        if (this.mAlign != rNSharedElementAlign) {
            this.mAlign = rNSharedElementAlign;
            updateLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void setNodePosition(float f) {
        if (this.mNodePosition != f) {
            this.mNodePosition = f;
            this.mInitialNodePositionSet = true;
            updateLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (!this.mReactLayoutSet) {
            this.mReactLayoutSet = true;
            requestStylesAndContent(true);
            this.mInitialLayoutPassCompleted = true;
            updateLayout();
            updateNodeVisibility();
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.mRequiresClipping) {
            canvas.clipRect(0, 0, getWidth(), getHeight());
        }
        super.dispatchDraw(canvas);
    }

    private void requestStylesAndContent(boolean z) {
        if (this.mInitialLayoutPassCompleted || z) {
            Iterator<RNSharedElementTransitionItem> it = this.mItems.iterator();
            while (it.hasNext()) {
                RNSharedElementTransitionItem next = it.next();
                if (next.getNeedsStyle()) {
                    next.setNeedsStyle(false);
                    next.getNode().requestStyle(new RNSharedElementTransition$$ExternalSyntheticLambda0(this, next));
                }
                if (next.getNeedsContent()) {
                    next.setNeedsContent(false);
                    next.getNode().requestContent(new RNSharedElementTransition$$ExternalSyntheticLambda1(this, next));
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestStylesAndContent$0(RNSharedElementTransitionItem rNSharedElementTransitionItem, Object[] objArr) {
        rNSharedElementTransitionItem.setStyle(objArr[0]);
        updateLayout();
        updateNodeVisibility();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestStylesAndContent$1(RNSharedElementTransitionItem rNSharedElementTransitionItem, Object[] objArr) {
        rNSharedElementTransitionItem.setContent(objArr[0]);
        updateLayout();
        updateNodeVisibility();
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0204  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0221  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x02b8  */
    /* JADX WARNING: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateLayout() {
        /*
            r33 = this;
            r0 = r33
            boolean r1 = r0.mInitialLayoutPassCompleted
            if (r1 != 0) goto L_0x0007
            return
        L_0x0007:
            java.util.ArrayList<com.ijzerenhein.sharedelement.RNSharedElementTransitionItem> r1 = r0.mItems
            com.ijzerenhein.sharedelement.RNSharedElementTransition$Item r2 = com.ijzerenhein.sharedelement.RNSharedElementTransition.Item.START
            int r2 = r2.getValue()
            java.lang.Object r1 = r1.get(r2)
            com.ijzerenhein.sharedelement.RNSharedElementTransitionItem r1 = (com.ijzerenhein.sharedelement.RNSharedElementTransitionItem) r1
            java.util.ArrayList<com.ijzerenhein.sharedelement.RNSharedElementTransitionItem> r2 = r0.mItems
            com.ijzerenhein.sharedelement.RNSharedElementTransition$Item r3 = com.ijzerenhein.sharedelement.RNSharedElementTransition.Item.END
            int r3 = r3.getValue()
            java.lang.Object r2 = r2.get(r3)
            com.ijzerenhein.sharedelement.RNSharedElementTransitionItem r2 = (com.ijzerenhein.sharedelement.RNSharedElementTransitionItem) r2
            android.view.ViewParent r3 = r33.getParent()
            android.view.View r3 = (android.view.View) r3
            if (r3 != 0) goto L_0x002c
            return
        L_0x002c:
            int[] r4 = r0.mParentOffset
            r3.getLocationInWindow(r4)
            com.ijzerenhein.sharedelement.RNSharedElementStyle r4 = r1.getStyle()
            com.ijzerenhein.sharedelement.RNSharedElementStyle r5 = r2.getStyle()
            if (r4 != 0) goto L_0x003e
            if (r5 != 0) goto L_0x003e
            return
        L_0x003e:
            com.ijzerenhein.sharedelement.RNSharedElementContent r6 = r1.getContent()
            com.ijzerenhein.sharedelement.RNSharedElementContent r12 = r2.getContent()
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r7 = r0.mAnimation
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r8 = com.ijzerenhein.sharedelement.RNSharedElementAnimation.MOVE
            if (r7 != r8) goto L_0x0053
            if (r6 != 0) goto L_0x0053
            if (r12 == 0) goto L_0x0053
            r18 = r12
            goto L_0x0055
        L_0x0053:
            r18 = r6
        L_0x0055:
            int r6 = r0.mInitialVisibleAncestorIndex
            r7 = 0
            r11 = 1
            if (r6 >= 0) goto L_0x0090
            if (r4 == 0) goto L_0x006b
            if (r5 != 0) goto L_0x006b
            com.ijzerenhein.sharedelement.RNSharedElementNode r3 = r2.getNode()
            if (r3 != 0) goto L_0x0067
            r3 = r11
            goto L_0x0068
        L_0x0067:
            r3 = r7
        L_0x0068:
            r0.mInitialVisibleAncestorIndex = r3
            goto L_0x0090
        L_0x006b:
            if (r5 == 0) goto L_0x007b
            if (r4 != 0) goto L_0x007b
            com.ijzerenhein.sharedelement.RNSharedElementNode r3 = r1.getNode()
            if (r3 != 0) goto L_0x0077
            r3 = r7
            goto L_0x0078
        L_0x0077:
            r3 = r11
        L_0x0078:
            r0.mInitialVisibleAncestorIndex = r3
            goto L_0x0090
        L_0x007b:
            if (r4 == 0) goto L_0x0090
            if (r5 == 0) goto L_0x0090
            float r6 = com.ijzerenhein.sharedelement.RNSharedElementStyle.getAncestorVisibility(r3, r4)
            float r3 = com.ijzerenhein.sharedelement.RNSharedElementStyle.getAncestorVisibility(r3, r5)
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 <= 0) goto L_0x008d
            r3 = r11
            goto L_0x008e
        L_0x008d:
            r3 = r7
        L_0x008e:
            r0.mInitialVisibleAncestorIndex = r3
        L_0x0090:
            int r3 = r0.mInitialVisibleAncestorIndex
            if (r3 != r11) goto L_0x0096
            r3 = r11
            goto L_0x0097
        L_0x0096:
            r3 = r7
        L_0x0097:
            int[] r6 = r0.mParentOffset
            android.graphics.RectF r6 = com.ijzerenhein.sharedelement.RNSharedElementStyle.normalizeLayout(r3, r4, r6)
            if (r4 == 0) goto L_0x00a2
            android.graphics.Rect r8 = r4.frame
            goto L_0x00a4
        L_0x00a2:
            android.graphics.Rect r8 = com.ijzerenhein.sharedelement.RNSharedElementStyle.EMPTY_RECT
        L_0x00a4:
            r17 = r8
            int r8 = r0.mInitialVisibleAncestorIndex
            if (r8 != 0) goto L_0x00ac
            r8 = r11
            goto L_0x00ad
        L_0x00ac:
            r8 = r7
        L_0x00ad:
            int[] r9 = r0.mParentOffset
            android.graphics.RectF r10 = com.ijzerenhein.sharedelement.RNSharedElementStyle.normalizeLayout(r8, r5, r9)
            if (r5 == 0) goto L_0x00b8
            android.graphics.Rect r9 = r5.frame
            goto L_0x00ba
        L_0x00b8:
            android.graphics.Rect r9 = com.ijzerenhein.sharedelement.RNSharedElementStyle.EMPTY_RECT
        L_0x00ba:
            r24 = r9
            if (r4 == 0) goto L_0x00c3
            android.graphics.RectF r9 = r1.getClippedLayout()
            goto L_0x00c5
        L_0x00c3:
            android.graphics.RectF r9 = com.ijzerenhein.sharedelement.RNSharedElementStyle.EMPTY_RECTF
        L_0x00c5:
            int[] r13 = r0.mParentOffset
            android.graphics.RectF r3 = com.ijzerenhein.sharedelement.RNSharedElementStyle.normalizeLayout(r3, r9, r4, r13)
            android.graphics.RectF r26 = getClipInsets(r6, r3)
            if (r5 == 0) goto L_0x00d6
            android.graphics.RectF r9 = r2.getClippedLayout()
            goto L_0x00d8
        L_0x00d6:
            android.graphics.RectF r9 = com.ijzerenhein.sharedelement.RNSharedElementStyle.EMPTY_RECTF
        L_0x00d8:
            int[] r13 = r0.mParentOffset
            android.graphics.RectF r9 = com.ijzerenhein.sharedelement.RNSharedElementStyle.normalizeLayout(r8, r9, r5, r13)
            android.graphics.RectF r28 = getClipInsets(r10, r9)
            r8 = 1065353216(0x3f800000, float:1.0)
            if (r4 == 0) goto L_0x0104
            if (r5 == 0) goto L_0x0104
            float r13 = r0.mNodePosition
            android.graphics.RectF r13 = com.ijzerenhein.sharedelement.RNSharedElementStyle.getInterpolatedLayout(r6, r10, r13)
            float r14 = r0.mNodePosition
            r25 = r13
            r27 = r3
            r29 = r9
            r30 = r14
            android.graphics.RectF r26 = getInterpolatedClipInsets(r25, r26, r27, r28, r29, r30)
            float r14 = r0.mNodePosition
            com.ijzerenhein.sharedelement.RNSharedElementStyle r14 = com.ijzerenhein.sharedelement.RNSharedElementStyle.getInterpolatedStyle(r4, r6, r5, r10, r14)
            r15 = r13
            goto L_0x0108
        L_0x0104:
            if (r4 == 0) goto L_0x010b
            r14 = r4
            r15 = r6
        L_0x0108:
            r13 = r26
            goto L_0x0117
        L_0x010b:
            boolean r13 = r0.mInitialNodePositionSet
            if (r13 != 0) goto L_0x0113
            r0.mNodePosition = r8
            r0.mInitialNodePositionSet = r11
        L_0x0113:
            r14 = r5
            r15 = r10
            r13 = r28
        L_0x0117:
            float r8 = r13.left
            r11 = 0
            int r8 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r8 > 0) goto L_0x013e
            float r8 = r13.top
            int r8 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r8 > 0) goto L_0x013e
            float r8 = r13.right
            int r8 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r8 > 0) goto L_0x013e
            float r8 = r13.bottom
            int r8 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r8 <= 0) goto L_0x0131
            goto L_0x013e
        L_0x0131:
            android.graphics.RectF r8 = new android.graphics.RectF
            r8.<init>(r6)
            r8.union(r10)
            r0.mRequiresClipping = r7
            r11 = r8
            r7 = 1
            goto L_0x0163
        L_0x013e:
            android.graphics.RectF r8 = new android.graphics.RectF
            r8.<init>(r15)
            float r11 = r8.left
            float r7 = r13.left
            float r11 = r11 + r7
            r8.left = r11
            float r7 = r8.top
            float r11 = r13.top
            float r7 = r7 + r11
            r8.top = r7
            float r7 = r8.right
            float r11 = r13.right
            float r7 = r7 - r11
            r8.right = r7
            float r7 = r8.bottom
            float r11 = r13.bottom
            float r7 = r7 - r11
            r8.bottom = r7
            r7 = 1
            r0.mRequiresClipping = r7
            r11 = r8
        L_0x0163:
            int[] r8 = r0.mParentOffset
            r27 = r9
            r13 = 0
            r9 = r8[r13]
            int r9 = -r9
            r8 = r8[r7]
            int r7 = -r8
            float r8 = r11.width()
            r28 = r10
            int[] r10 = r0.mParentOffset
            r10 = r10[r13]
            float r10 = (float) r10
            float r8 = r8 - r10
            r29 = r2
            r30 = r3
            double r2 = (double) r8
            double r2 = java.lang.Math.ceil(r2)
            int r2 = (int) r2
            float r3 = r11.height()
            int[] r8 = r0.mParentOffset
            r10 = 1
            r8 = r8[r10]
            float r8 = (float) r8
            float r3 = r3 - r8
            r31 = r12
            double r12 = (double) r3
            double r12 = java.lang.Math.ceil(r12)
            int r3 = (int) r12
            super.layout(r9, r7, r2, r3)
            float r2 = r11.left
            r0.setTranslationX(r2)
            float r2 = r11.top
            r0.setTranslationY(r2)
            int[] r2 = com.ijzerenhein.sharedelement.RNSharedElementTransition.AnonymousClass1.$SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementAnimation
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r3 = r0.mAnimation
            int r3 = r3.ordinal()
            r2 = r2[r3]
            r3 = 1
            if (r2 == r3) goto L_0x01f4
            r7 = 2
            if (r2 == r7) goto L_0x01dc
            r7 = 3
            if (r2 == r7) goto L_0x01cf
            r7 = 4
            if (r2 == r7) goto L_0x01bf
            r2 = 1065353216(0x3f800000, float:1.0)
            r12 = 1065353216(0x3f800000, float:1.0)
            goto L_0x01fe
        L_0x01bf:
            if (r4 == 0) goto L_0x01c4
            float r2 = r4.opacity
            goto L_0x01c6
        L_0x01c4:
            r2 = 1065353216(0x3f800000, float:1.0)
        L_0x01c6:
            float r7 = r0.mNodePosition
            r8 = 1065353216(0x3f800000, float:1.0)
            float r8 = r8 - r7
            float r8 = r8 * r2
            r2 = r8
            r12 = 0
            goto L_0x01fe
        L_0x01cf:
            if (r5 == 0) goto L_0x01d4
            float r8 = r5.opacity
            goto L_0x01d6
        L_0x01d4:
            r8 = 1065353216(0x3f800000, float:1.0)
        L_0x01d6:
            float r2 = r0.mNodePosition
            float r8 = r8 * r2
            r12 = r8
            r2 = 0
            goto L_0x01fe
        L_0x01dc:
            if (r4 == 0) goto L_0x01e1
            float r8 = r4.opacity
            goto L_0x01e3
        L_0x01e1:
            r8 = 1065353216(0x3f800000, float:1.0)
        L_0x01e3:
            float r2 = r0.mNodePosition
            r7 = 1065353216(0x3f800000, float:1.0)
            float r2 = r7 - r2
            float r8 = r8 * r2
            if (r5 == 0) goto L_0x01ef
            float r2 = r5.opacity
            goto L_0x01f0
        L_0x01ef:
            r2 = r7
        L_0x01f0:
            float r7 = r0.mNodePosition
            float r2 = r2 * r7
            goto L_0x01fc
        L_0x01f4:
            float r8 = r14.opacity
            if (r4 != 0) goto L_0x01fb
            float r2 = r14.opacity
            goto L_0x01fc
        L_0x01fb:
            r2 = 0
        L_0x01fc:
            r12 = r2
            r2 = r8
        L_0x01fe:
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r7 = r0.mAnimation
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r8 = com.ijzerenhein.sharedelement.RNSharedElementAnimation.FADE_IN
            if (r7 == r8) goto L_0x0221
            com.ijzerenhein.sharedelement.RNSharedElementView r13 = r0.mStartView
            com.ijzerenhein.sharedelement.RNSharedElementResize r7 = r0.mResize
            com.ijzerenhein.sharedelement.RNSharedElementAlign r8 = r0.mAlign
            float r9 = r0.mNodePosition
            r10 = r14
            r14 = r15
            r25 = r15
            r15 = r11
            r16 = r6
            r19 = r10
            r20 = r2
            r21 = r7
            r22 = r8
            r23 = r9
            r13.updateViewAndDrawable(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            goto L_0x0224
        L_0x0221:
            r10 = r14
            r25 = r15
        L_0x0224:
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r7 = r0.mAnimation
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r8 = com.ijzerenhein.sharedelement.RNSharedElementAnimation.FADE
            if (r7 == r8) goto L_0x0241
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r7 = r0.mAnimation
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r8 = com.ijzerenhein.sharedelement.RNSharedElementAnimation.FADE_IN
            if (r7 == r8) goto L_0x0241
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r7 = r0.mAnimation
            com.ijzerenhein.sharedelement.RNSharedElementAnimation r8 = com.ijzerenhein.sharedelement.RNSharedElementAnimation.MOVE
            if (r7 != r8) goto L_0x0239
            if (r4 != 0) goto L_0x0239
            goto L_0x0241
        L_0x0239:
            com.ijzerenhein.sharedelement.RNSharedElementView r2 = r0.mEndView
            r2.reset()
            r32 = r28
            goto L_0x029b
        L_0x0241:
            com.ijzerenhein.sharedelement.RNSharedElementView r7 = r0.mEndView
            com.ijzerenhein.sharedelement.RNSharedElementResize r15 = r0.mResize
            com.ijzerenhein.sharedelement.RNSharedElementAlign r14 = r0.mAlign
            float r13 = r0.mNodePosition
            r8 = r25
            r3 = r27
            r9 = r11
            r18 = r10
            r11 = r28
            r10 = r11
            r32 = r11
            r3 = 0
            r11 = r24
            r19 = r12
            r12 = r31
            r17 = r13
            r13 = r18
            r16 = r14
            r14 = r19
            r7.updateViewAndDrawable(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            r14 = r18
            float r7 = r14.elevation
            int r7 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x029b
            int r7 = android.os.Build.VERSION.SDK_INT
            r8 = 28
            if (r7 < r8) goto L_0x029b
            com.ijzerenhein.sharedelement.RNSharedElementView r7 = r0.mStartView
            int r8 = android.graphics.Color.argb(r2, r3, r3, r3)
            r7.setOutlineAmbientShadowColor(r8)
            com.ijzerenhein.sharedelement.RNSharedElementView r7 = r0.mStartView
            int r2 = android.graphics.Color.argb(r2, r3, r3, r3)
            r7.setOutlineSpotShadowColor(r2)
            com.ijzerenhein.sharedelement.RNSharedElementView r2 = r0.mEndView
            r8 = r19
            int r7 = android.graphics.Color.argb(r8, r3, r3, r3)
            r2.setOutlineAmbientShadowColor(r7)
            com.ijzerenhein.sharedelement.RNSharedElementView r2 = r0.mEndView
            int r3 = android.graphics.Color.argb(r8, r3, r3, r3)
            r2.setOutlineSpotShadowColor(r3)
        L_0x029b:
            if (r4 == 0) goto L_0x02af
            boolean r2 = r1.getHasCalledOnMeasure()
            if (r2 != 0) goto L_0x02af
            r2 = 1
            r1.setHasCalledOnMeasure(r2)
            java.lang.String r3 = "startNode"
            r4 = r30
            r0.fireMeasureEvent(r3, r1, r6, r4)
            goto L_0x02b0
        L_0x02af:
            r2 = 1
        L_0x02b0:
            if (r5 == 0) goto L_0x02c6
            boolean r1 = r29.getHasCalledOnMeasure()
            if (r1 != 0) goto L_0x02c6
            r1 = r29
            r1.setHasCalledOnMeasure(r2)
            java.lang.String r2 = "endNode"
            r4 = r27
            r3 = r32
            r0.fireMeasureEvent(r2, r1, r3, r4)
        L_0x02c6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ijzerenhein.sharedelement.RNSharedElementTransition.updateLayout():void");
    }

    /* renamed from: com.ijzerenhein.sharedelement.RNSharedElementTransition$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementAnimation;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.ijzerenhein.sharedelement.RNSharedElementAnimation[] r0 = com.ijzerenhein.sharedelement.RNSharedElementAnimation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementAnimation = r0
                com.ijzerenhein.sharedelement.RNSharedElementAnimation r1 = com.ijzerenhein.sharedelement.RNSharedElementAnimation.MOVE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementAnimation     // Catch:{ NoSuchFieldError -> 0x001d }
                com.ijzerenhein.sharedelement.RNSharedElementAnimation r1 = com.ijzerenhein.sharedelement.RNSharedElementAnimation.FADE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementAnimation     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.ijzerenhein.sharedelement.RNSharedElementAnimation r1 = com.ijzerenhein.sharedelement.RNSharedElementAnimation.FADE_IN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$ijzerenhein$sharedelement$RNSharedElementAnimation     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.ijzerenhein.sharedelement.RNSharedElementAnimation r1 = com.ijzerenhein.sharedelement.RNSharedElementAnimation.FADE_OUT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ijzerenhein.sharedelement.RNSharedElementTransition.AnonymousClass1.<clinit>():void");
        }
    }

    private void updateNodeVisibility() {
        Iterator<RNSharedElementTransitionItem> it = this.mItems.iterator();
        while (it.hasNext()) {
            RNSharedElementTransitionItem next = it.next();
            boolean z = false;
            boolean z2 = (!this.mInitialLayoutPassCompleted || next.getStyle() == null || next.getContent() == null) ? false : true;
            if (z2 && this.mAnimation == RNSharedElementAnimation.FADE_IN && next.getName().equals("start")) {
                z2 = false;
            }
            if (!z2 || this.mAnimation != RNSharedElementAnimation.FADE_OUT || !next.getName().equals("end")) {
                z = z2;
            }
            next.setHidden(z);
        }
    }

    private static RectF getClipInsets(RectF rectF, RectF rectF2) {
        return new RectF(rectF2.left - rectF.left, rectF2.top - rectF.top, rectF.right - rectF2.right, rectF.bottom - rectF2.bottom);
    }

    private static RectF getInterpolatedClipInsets(RectF rectF, RectF rectF2, RectF rectF3, RectF rectF4, RectF rectF5, float f) {
        RectF rectF6 = new RectF();
        if (rectF4.top == 0.0f && rectF2.top != 0.0f && rectF3.top <= rectF5.top) {
            rectF6.top = Math.max(0.0f, rectF3.top - rectF.top);
        } else if (rectF2.top != 0.0f || rectF4.top == 0.0f || rectF5.top > rectF3.top) {
            rectF6.top = rectF2.top + ((rectF4.top - rectF2.top) * f);
        } else {
            rectF6.top = Math.max(0.0f, rectF5.top - rectF.top);
        }
        if (rectF4.bottom == 0.0f && rectF2.bottom != 0.0f && rectF3.bottom >= rectF5.bottom) {
            rectF6.bottom = Math.max(0.0f, rectF.bottom - rectF3.bottom);
        } else if (rectF2.bottom != 0.0f || rectF4.bottom == 0.0f || rectF5.bottom < rectF3.bottom) {
            rectF6.bottom = rectF2.bottom + ((rectF4.bottom - rectF2.bottom) * f);
        } else {
            rectF6.bottom = Math.max(0.0f, rectF.bottom - rectF5.bottom);
        }
        if (rectF4.left == 0.0f && rectF2.left != 0.0f && rectF3.left <= rectF5.left) {
            rectF6.left = Math.max(0.0f, rectF3.left - rectF.left);
        } else if (rectF2.left != 0.0f || rectF4.left == 0.0f || rectF5.left > rectF3.left) {
            rectF6.left = rectF2.left + ((rectF4.left - rectF2.left) * f);
        } else {
            rectF6.left = Math.max(0.0f, rectF5.left - rectF.left);
        }
        if (rectF4.right == 0.0f && rectF2.right != 0.0f && rectF3.right >= rectF5.right) {
            rectF6.right = Math.max(0.0f, rectF.right - rectF3.right);
        } else if (rectF2.right != 0.0f || rectF4.right == 0.0f || rectF5.right < rectF3.right) {
            rectF6.right = rectF2.right + ((rectF4.right - rectF2.right) * f);
        } else {
            rectF6.right = Math.max(0.0f, rectF.right - rectF5.right);
        }
        return rectF6;
    }

    private void fireMeasureEvent(String str, RNSharedElementTransitionItem rNSharedElementTransitionItem, RectF rectF, RectF rectF2) {
        RNSharedElementDrawable.ViewType viewType;
        ReactContext reactContext = (ReactContext) getContext();
        RNSharedElementStyle style = rNSharedElementTransitionItem.getStyle();
        RNSharedElementContent content = rNSharedElementTransitionItem.getContent();
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble(ViewHierarchyNode.JsonKeys.X, (double) PixelUtil.toDIPFromPixel(rectF.left - ((float) this.mParentOffset[0])));
        createMap.putDouble(ViewHierarchyNode.JsonKeys.Y, (double) PixelUtil.toDIPFromPixel(rectF.top - ((float) this.mParentOffset[1])));
        createMap.putDouble("width", (double) PixelUtil.toDIPFromPixel(rectF.width()));
        createMap.putDouble("height", (double) PixelUtil.toDIPFromPixel(rectF.height()));
        createMap.putDouble("visibleX", (double) PixelUtil.toDIPFromPixel(rectF2.left - ((float) this.mParentOffset[0])));
        createMap.putDouble("visibleY", (double) PixelUtil.toDIPFromPixel(rectF2.top - ((float) this.mParentOffset[1])));
        createMap.putDouble("visibleWidth", (double) PixelUtil.toDIPFromPixel(rectF2.width()));
        createMap.putDouble("visibleHeight", (double) PixelUtil.toDIPFromPixel(rectF2.height()));
        createMap.putDouble("contentX", (double) PixelUtil.toDIPFromPixel(rectF.left - ((float) this.mParentOffset[0])));
        createMap.putDouble("contentY", (double) PixelUtil.toDIPFromPixel(rectF.top - ((float) this.mParentOffset[1])));
        createMap.putDouble("contentWidth", (double) PixelUtil.toDIPFromPixel(rectF.width()));
        createMap.putDouble("contentHeight", (double) PixelUtil.toDIPFromPixel(rectF.height()));
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putDouble(ViewProps.BORDER_TOP_LEFT_RADIUS, (double) PixelUtil.toDIPFromPixel(style.borderTopLeftRadius));
        createMap2.putDouble(ViewProps.BORDER_TOP_RIGHT_RADIUS, (double) PixelUtil.toDIPFromPixel(style.borderTopRightRadius));
        createMap2.putDouble(ViewProps.BORDER_BOTTOM_LEFT_RADIUS, (double) PixelUtil.toDIPFromPixel(style.borderBottomLeftRadius));
        createMap2.putDouble(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, (double) PixelUtil.toDIPFromPixel(style.borderBottomRightRadius));
        WritableMap createMap3 = Arguments.createMap();
        createMap3.putString("node", str);
        createMap3.putMap(TtmlNode.TAG_LAYOUT, createMap);
        if (content != null) {
            viewType = RNSharedElementDrawable.getViewType(content.view, style);
        } else {
            viewType = RNSharedElementDrawable.ViewType.NONE;
        }
        createMap3.putString(NotificationsChannelSerializer.AUDIO_ATTRIBUTES_CONTENT_TYPE_KEY, viewType.getValue());
        createMap3.putMap(TtmlNode.TAG_STYLE, createMap2);
        ((RCTEventEmitter) reactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(getId(), "onMeasureNode", createMap3);
    }
}
