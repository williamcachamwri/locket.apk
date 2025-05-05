package com.ijzerenhein.sharedelement;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

class RNSharedElementTransitionItem {
    private RectF mClippedLayoutCache;
    private RNSharedElementContent mContent;
    private boolean mHasCalledOnMeasure;
    private boolean mHidden;
    private final String mName;
    private boolean mNeedsContent;
    private boolean mNeedsStyle;
    private RNSharedElementNode mNode = null;
    private final RNSharedElementNodeManager mNodeManager;
    private RNSharedElementStyle mStyle;

    RNSharedElementTransitionItem(RNSharedElementNodeManager rNSharedElementNodeManager, String str) {
        this.mNodeManager = rNSharedElementNodeManager;
        this.mName = str;
        this.mHidden = false;
        this.mNeedsStyle = false;
        this.mStyle = null;
        this.mNeedsContent = false;
        this.mContent = null;
        this.mClippedLayoutCache = null;
        this.mHasCalledOnMeasure = false;
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return this.mName;
    }

    /* access modifiers changed from: package-private */
    public void setHidden(boolean z) {
        if (this.mHidden != z) {
            this.mHidden = z;
            RNSharedElementNode rNSharedElementNode = this.mNode;
            if (rNSharedElementNode != null) {
                if (z) {
                    rNSharedElementNode.addHideRef();
                } else {
                    rNSharedElementNode.releaseHideRef();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getHidden() {
        return this.mHidden;
    }

    /* access modifiers changed from: package-private */
    public RNSharedElementNode getNode() {
        return this.mNode;
    }

    /* access modifiers changed from: package-private */
    public void setNode(RNSharedElementNode rNSharedElementNode) {
        RNSharedElementNode rNSharedElementNode2 = this.mNode;
        if (rNSharedElementNode2 != rNSharedElementNode) {
            if (rNSharedElementNode2 != null) {
                if (this.mHidden) {
                    rNSharedElementNode2.releaseHideRef();
                }
                this.mNodeManager.release(this.mNode);
            }
            this.mNode = rNSharedElementNode;
            boolean z = true;
            this.mNeedsStyle = rNSharedElementNode != null;
            this.mStyle = null;
            if (rNSharedElementNode == null) {
                z = false;
            }
            this.mNeedsContent = z;
            this.mContent = null;
            if (rNSharedElementNode != null && this.mHidden) {
                rNSharedElementNode.addHideRef();
            }
        } else if (rNSharedElementNode != null) {
            this.mNodeManager.release(rNSharedElementNode);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean getNeedsStyle() {
        return this.mNeedsStyle;
    }

    /* access modifiers changed from: package-private */
    public void setNeedsStyle(boolean z) {
        this.mNeedsStyle = z;
    }

    /* access modifiers changed from: package-private */
    public void setStyle(RNSharedElementStyle rNSharedElementStyle) {
        this.mStyle = rNSharedElementStyle;
    }

    /* access modifiers changed from: package-private */
    public RNSharedElementStyle getStyle() {
        return this.mStyle;
    }

    /* access modifiers changed from: package-private */
    public boolean getNeedsContent() {
        return this.mNeedsContent;
    }

    /* access modifiers changed from: package-private */
    public void setNeedsContent(boolean z) {
        this.mNeedsContent = z;
    }

    /* access modifiers changed from: package-private */
    public void setContent(RNSharedElementContent rNSharedElementContent) {
        this.mContent = rNSharedElementContent;
    }

    /* access modifiers changed from: package-private */
    public RNSharedElementContent getContent() {
        return this.mContent;
    }

    /* access modifiers changed from: package-private */
    public void setHasCalledOnMeasure(boolean z) {
        this.mHasCalledOnMeasure = z;
    }

    /* access modifiers changed from: package-private */
    public boolean getHasCalledOnMeasure() {
        return this.mHasCalledOnMeasure;
    }

    /* access modifiers changed from: package-private */
    public View getView() {
        RNSharedElementNode rNSharedElementNode = this.mNode;
        if (rNSharedElementNode != null) {
            return rNSharedElementNode.getResolvedView();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public RectF getClippedLayout() {
        RectF rectF = this.mClippedLayoutCache;
        if (rectF != null) {
            return rectF;
        }
        if (this.mStyle == null) {
            return null;
        }
        View ancestorView = this.mNode.getAncestorView();
        RectF rectF2 = new RectF(this.mStyle.layout);
        ViewParent parent = getView().getParent();
        RectF rectF3 = new RectF();
        while (true) {
            if (parent == null || !(parent instanceof ViewGroup)) {
                break;
            }
            ViewGroup viewGroup = (ViewGroup) parent;
            if (viewGroup.getClipChildren()) {
                RNSharedElementStyle.getLayoutOnScreen(viewGroup, rectF3);
                if (!rectF2.intersect(rectF3)) {
                    if (rectF2.bottom < rectF3.top) {
                        rectF2.top = rectF3.top;
                        rectF2.bottom = rectF3.top;
                    }
                    if (rectF2.top > rectF3.bottom) {
                        rectF2.top = rectF3.bottom;
                        rectF2.bottom = rectF3.bottom;
                    }
                    if (rectF2.right < rectF3.left) {
                        rectF2.left = rectF3.left;
                        rectF2.right = rectF3.left;
                    }
                    if (rectF2.left > rectF3.right) {
                        rectF2.left = rectF3.right;
                        rectF2.right = rectF3.right;
                    }
                }
            }
            if (parent == ancestorView) {
                break;
            }
            parent = parent.getParent();
        }
        this.mClippedLayoutCache = rectF2;
        return rectF2;
    }
}
