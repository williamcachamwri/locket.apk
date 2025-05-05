package com.ijzerenhein.sharedelement;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nullable;

class RNSharedElementNode {
    private static final String LOG_TAG = "RNSharedElementNode";
    private View mAncestorView;
    private RNSharedElementContent mContentCache = null;
    private ArrayList<Callback> mContentCallbacks = null;
    private final Context mContext;
    private BaseControllerListener<ImageInfo> mDraweeControllerListener = null;
    private float mHideAlpha = 1.0f;
    private int mHideRefCount = 0;
    private final boolean mIsParent;
    private final int mReactTag;
    private int mRefCount = 1;
    private final RNSharedElementStyle mResolveStyle;
    private View mResolvedView = null;
    /* access modifiers changed from: private */
    public Handler mRetryHandler = null;
    private RNSharedElementStyle mStyleCache = null;
    private ArrayList<Callback> mStyleCallbacks = null;
    private ReadableMap mStyleConfig;
    private View mView;

    RNSharedElementNode(Context context, int i, View view, boolean z, View view2, ReadableMap readableMap) {
        this.mReactTag = i;
        this.mView = view;
        this.mAncestorView = view2;
        this.mIsParent = z;
        this.mStyleConfig = readableMap;
        this.mResolveStyle = new RNSharedElementStyle(readableMap, context);
        this.mContext = context;
    }

    /* access modifiers changed from: package-private */
    public int getReactTag() {
        return this.mReactTag;
    }

    /* access modifiers changed from: package-private */
    public int addRef() {
        int i = this.mRefCount + 1;
        this.mRefCount = i;
        return i;
    }

    /* access modifiers changed from: package-private */
    public int releaseRef() {
        int i = this.mRefCount - 1;
        this.mRefCount = i;
        if (i == 0) {
            removeDraweeControllerListener(this.mResolvedView);
            stopRetryLoop();
            this.mView = null;
            this.mAncestorView = null;
            this.mStyleConfig = null;
            this.mResolvedView = null;
            this.mContentCache = null;
            this.mContentCallbacks = null;
            this.mStyleCache = null;
            this.mStyleCallbacks = null;
        }
        return this.mRefCount;
    }

    /* access modifiers changed from: package-private */
    public void addHideRef() {
        int i = this.mHideRefCount + 1;
        this.mHideRefCount = i;
        if (i == 1) {
            this.mHideAlpha = this.mView.getAlpha();
            this.mView.setAlpha(0.0f);
        }
    }

    /* access modifiers changed from: package-private */
    public void releaseHideRef() {
        int i = this.mHideRefCount - 1;
        this.mHideRefCount = i;
        if (i == 0) {
            this.mView.setAlpha(this.mHideAlpha);
        }
    }

    private static View resolveView(View view, RNSharedElementStyle rNSharedElementStyle) {
        if (view == null) {
            return null;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                View childAt = viewGroup.getChildAt(0);
                if (childAt instanceof ImageView) {
                    int left = childAt.getLeft();
                    int top = childAt.getTop();
                    int width = childAt.getWidth();
                    int height = childAt.getHeight();
                    int round = Math.round(rNSharedElementStyle.borderWidth);
                    int round2 = Math.round(rNSharedElementStyle.borderWidth);
                    int round3 = Math.round(((float) viewGroup.getWidth()) - (rNSharedElementStyle.borderWidth * 2.0f));
                    int round4 = Math.round(((float) viewGroup.getHeight()) - (rNSharedElementStyle.borderWidth * 2.0f));
                    return (left < round + -1 || left > round + 1 || top < round2 + -1 || top > round2 + 1 || width < round3 + -1 || width > round3 + 1 || height < round4 + -1 || height > round4 + 1) ? view : childAt;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public View getAncestorView() {
        return this.mAncestorView;
    }

    /* access modifiers changed from: package-private */
    public View getResolvedView() {
        View view = this.mResolvedView;
        if (view != null) {
            return view;
        }
        View view2 = this.mView;
        if (this.mIsParent) {
            int childCount = ((ViewGroup) view2).getChildCount();
            if (childCount == 1) {
                view2 = ((ViewGroup) this.mView).getChildAt(0);
            } else if (childCount <= 0) {
                Log.d(LOG_TAG, "Child for parent doesn't exist");
                return null;
            }
        }
        View resolveView = resolveView(view2, this.mResolveStyle);
        this.mResolvedView = resolveView;
        return resolveView;
    }

    /* access modifiers changed from: package-private */
    public void requestStyle(Callback callback) {
        RNSharedElementStyle rNSharedElementStyle = this.mStyleCache;
        if (rNSharedElementStyle != null) {
            callback.invoke(rNSharedElementStyle, this);
            return;
        }
        if (this.mStyleCallbacks == null) {
            this.mStyleCallbacks = new ArrayList<>();
        }
        this.mStyleCallbacks.add(callback);
        if (!fetchInitialStyle()) {
            startRetryLoop();
        }
    }

    /* access modifiers changed from: private */
    public boolean fetchInitialStyle() {
        View resolvedView = getResolvedView();
        if (resolvedView == null) {
            return false;
        }
        if (this.mStyleCallbacks == null) {
            return true;
        }
        int left = resolvedView.getLeft();
        int top = resolvedView.getTop();
        int width = resolvedView.getWidth();
        int height = resolvedView.getHeight();
        if (width == 0 && height == 0) {
            return false;
        }
        Matrix absoluteViewTransform = RNSharedElementStyle.getAbsoluteViewTransform(resolvedView);
        Matrix absoluteViewTransform2 = RNSharedElementStyle.getAbsoluteViewTransform(this.mAncestorView);
        if (absoluteViewTransform == null || absoluteViewTransform2 == null) {
            return false;
        }
        Rect rect = new Rect(left, top, width + left, height + top);
        RNSharedElementStyle rNSharedElementStyle = new RNSharedElementStyle(this.mStyleConfig, this.mContext);
        RNSharedElementStyle.getLayoutOnScreen(resolvedView, rNSharedElementStyle.layout);
        rNSharedElementStyle.frame = rect;
        RNSharedElementStyle.getLayoutOnScreen(this.mAncestorView, rNSharedElementStyle.ancestorLayout);
        rNSharedElementStyle.ancestorTransform = absoluteViewTransform2;
        rNSharedElementStyle.opacity = resolvedView.getAlpha();
        this.mStyleCache = rNSharedElementStyle;
        ArrayList<Callback> arrayList = this.mStyleCallbacks;
        this.mStyleCallbacks = null;
        Iterator<Callback> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().invoke(rNSharedElementStyle, this);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void requestContent(Callback callback) {
        RNSharedElementContent rNSharedElementContent = this.mContentCache;
        if (rNSharedElementContent != null) {
            callback.invoke(rNSharedElementContent, this);
            return;
        }
        if (this.mContentCallbacks == null) {
            this.mContentCallbacks = new ArrayList<>();
        }
        this.mContentCallbacks.add(callback);
        if (!fetchInitialContent()) {
            startRetryLoop();
        }
    }

    /* access modifiers changed from: private */
    public boolean fetchInitialContent() {
        View resolvedView = getResolvedView();
        if (resolvedView == null) {
            return false;
        }
        if (this.mContentCallbacks == null) {
            return true;
        }
        int width = resolvedView.getWidth();
        int height = resolvedView.getHeight();
        if (width == 0 && height == 0) {
            return false;
        }
        RectF size = RNSharedElementContent.getSize(resolvedView);
        if (size == null) {
            addDraweeControllerListener(resolvedView);
            return false;
        }
        RNSharedElementContent rNSharedElementContent = new RNSharedElementContent();
        rNSharedElementContent.view = resolvedView;
        rNSharedElementContent.size = size;
        this.mContentCache = rNSharedElementContent;
        ArrayList<Callback> arrayList = this.mContentCallbacks;
        this.mContentCallbacks = null;
        Iterator<Callback> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().invoke(rNSharedElementContent, this);
        }
        return true;
    }

    private void startRetryLoop() {
        if (this.mRetryHandler == null) {
            Handler handler = new Handler();
            this.mRetryHandler = handler;
            handler.postDelayed(new RetryRunnable() {
                public void run() {
                    if (RNSharedElementNode.this.mRetryHandler != null) {
                        this.numRetries++;
                        boolean r0 = RNSharedElementNode.this.fetchInitialContent();
                        boolean r1 = RNSharedElementNode.this.fetchInitialStyle();
                        if (!r0 || !r1) {
                            RNSharedElementNode.this.mRetryHandler.postDelayed(this, 8);
                        } else {
                            RNSharedElementNode.this.mRetryHandler = null;
                        }
                    }
                }
            }, 4);
        }
    }

    private void stopRetryLoop() {
        if (this.mRetryHandler != null) {
            this.mRetryHandler = null;
        }
    }

    private void addDraweeControllerListener(final View view) {
        if (this.mDraweeControllerListener == null && (view instanceof GenericDraweeView)) {
            DraweeController controller = ((GenericDraweeView) view).getController();
            if (controller instanceof PipelineDraweeController) {
                AnonymousClass2 r1 = new BaseControllerListener<ImageInfo>() {
                    public void onSubmit(String str, Object obj) {
                    }

                    public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                        RNSharedElementNode.this.removeDraweeControllerListener(view);
                        boolean unused = RNSharedElementNode.this.fetchInitialContent();
                    }

                    public void onFailure(String str, Throwable th) {
                        Log.d(RNSharedElementNode.LOG_TAG, "mDraweeControllerListener.onFailure: " + str + ", throwable: " + th);
                    }
                };
                this.mDraweeControllerListener = r1;
                ((PipelineDraweeController) controller).addControllerListener(r1);
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeDraweeControllerListener(View view) {
        if (this.mDraweeControllerListener != null) {
            DraweeController controller = ((GenericDraweeView) view).getController();
            if (controller instanceof PipelineDraweeController) {
                ((PipelineDraweeController) controller).removeControllerListener(this.mDraweeControllerListener);
                this.mDraweeControllerListener = null;
            }
        }
    }
}
