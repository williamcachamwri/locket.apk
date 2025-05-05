package com.swmansion.reanimated.layoutReanimation;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.view.ReactViewGroup;
import com.swmansion.reanimated.Utils;
import io.sentry.android.core.SentryLogcatAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public class SharedTransitionManager {
    private final List<View> mAddedSharedViews = new ArrayList();
    /* access modifiers changed from: private */
    public final AnimationsManager mAnimationsManager;
    private final Map<Integer, View> mCurrentSharedTransitionViews = new HashMap();
    private final Map<Integer, Integer> mDisableCleaningForViewTag = new HashMap();
    /* access modifiers changed from: private */
    public NativeMethodsHolder mNativeMethodsHolder;
    private final Set<View> mReattachedViews = new HashSet();
    /* access modifiers changed from: private */
    public final List<View> mRemovedSharedViews = new ArrayList();
    private List<SharedElement> mSharedElements = new ArrayList();
    private final Map<Integer, SharedElement> mSharedElementsLookup = new HashMap();
    private final List<SharedElement> mSharedElementsWithAnimation = new ArrayList();
    private final List<SharedElement> mSharedElementsWithProgress = new ArrayList();
    private final Map<Integer, Integer> mSharedTransitionInParentIndex = new HashMap();
    private final Map<Integer, View> mSharedTransitionParent = new HashMap();
    private final Map<Integer, Snapshot> mSnapshotRegistry = new HashMap();
    private View mTransitionContainer;
    private final Set<Integer> mViewTagsToHide = new HashSet();

    interface TreeVisitor {
        void run(View view);
    }

    public SharedTransitionManager(AnimationsManager animationsManager) {
        this.mAnimationsManager = animationsManager;
    }

    /* access modifiers changed from: protected */
    public void notifyAboutNewView(View view) {
        this.mAddedSharedViews.add(view);
    }

    /* access modifiers changed from: protected */
    public void notifyAboutRemovedView(View view) {
        this.mRemovedSharedViews.add(view);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public View getTransitioningView(int i) {
        return this.mCurrentSharedTransitionViews.get(Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void screenDidLayout() {
        tryStartSharedTransitionForViews(this.mAddedSharedViews, true);
        this.mAddedSharedViews.clear();
    }

    /* access modifiers changed from: protected */
    public void viewDidLayout(View view) {
        maybeRestartAnimationWithNewLayout(view);
    }

    /* access modifiers changed from: protected */
    public void onViewsRemoval(int[] iArr) {
        if (iArr != null) {
            visitTreeForTags(iArr, new SnapshotTreeVisitor());
            if (this.mRemovedSharedViews.size() > 0) {
                if (!tryStartSharedTransitionForViews(this.mRemovedSharedViews, false)) {
                    this.mRemovedSharedViews.clear();
                }
                visitTreeForTags(iArr, new ConfigCleanerTreeVisitor());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doSnapshotForTopScreenViews(ViewGroup viewGroup) {
        if (viewGroup.getChildCount() > 0) {
            View childAt = viewGroup.getChildAt(0);
            if (childAt instanceof ViewGroup) {
                visitNativeTreeAndMakeSnapshot(((ViewGroup) childAt).getChildAt(0));
            } else {
                SentryLogcatAdapter.e("[Reanimated]", "Unable to recognize screen on stack.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setNativeMethods(NativeMethodsHolder nativeMethodsHolder) {
        this.mNativeMethodsHolder = nativeMethodsHolder;
    }

    private void maybeRestartAnimationWithNewLayout(View view) {
        View view2 = this.mCurrentSharedTransitionViews.get(Integer.valueOf(view.getId()));
        if (view2 != null) {
            ArrayList arrayList = new ArrayList();
            for (SharedElement next : this.mSharedElements) {
                if (next.targetView == view2) {
                    arrayList.add(next);
                    View view3 = next.sourceView;
                    View view4 = next.targetView;
                    Snapshot snapshot = new Snapshot(view3);
                    Snapshot snapshot2 = this.mSnapshotRegistry.get(Integer.valueOf(view4.getId()));
                    Snapshot snapshot3 = new Snapshot(view4);
                    int i = (snapshot2.originX - snapshot2.originXByParent) + snapshot3.originX;
                    int i2 = (snapshot2.originY - snapshot2.originYByParent) + snapshot3.originY;
                    snapshot2.originX = i;
                    snapshot2.originY = i2;
                    snapshot2.globalOriginX = i;
                    snapshot2.globalOriginY = i2;
                    snapshot2.originXByParent = snapshot3.originXByParent;
                    snapshot2.originYByParent = snapshot3.originYByParent;
                    snapshot2.height = snapshot3.height;
                    snapshot2.width = snapshot3.width;
                    next.sourceViewSnapshot = snapshot;
                    next.targetViewSnapshot = snapshot2;
                    disableCleaningForViewTag(view3.getId());
                    disableCleaningForViewTag(view4.getId());
                }
            }
            startSharedTransition(arrayList, 4);
        }
    }

    private boolean tryStartSharedTransitionForViews(List<View> list, boolean z) {
        if (list.isEmpty()) {
            return false;
        }
        sortViewsByTags(list);
        List<SharedElement> sharedElementsForCurrentTransition = getSharedElementsForCurrentTransition(list, z);
        if (sharedElementsForCurrentTransition.isEmpty()) {
            return false;
        }
        setupTransitionContainer();
        reparentSharedViewsForCurrentTransition(sharedElementsForCurrentTransition);
        orderByAnimationTypes(sharedElementsForCurrentTransition);
        startSharedTransition(this.mSharedElementsWithAnimation, 4);
        startSharedTransition(this.mSharedElementsWithProgress, 5);
        return true;
    }

    private void sortViewsByTags(List<View> list) {
        Collections.sort(list, new SharedTransitionManager$$ExternalSyntheticLambda1());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x013b, code lost:
        if ((!r20 ? r1.getId() == r13.getId() && r2.getId() == r14.getId() : r2.getId() == r13.getId() && r1.getId() == r14.getId()) == false) goto L_0x0197;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.swmansion.reanimated.layoutReanimation.SharedElement> getSharedElementsForCurrentTransition(java.util.List<android.view.View> r19, boolean r20) {
        /*
            r18 = this;
            r0 = r18
            java.util.Set<android.view.View> r1 = r0.mReattachedViews
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x000c
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = 0
        L_0x000d:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            if (r20 != 0) goto L_0x0035
            java.util.Iterator r6 = r19.iterator()
        L_0x001d:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0035
            java.lang.Object r7 = r6.next()
            android.view.View r7 = (android.view.View) r7
            int r7 = r7.getId()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r5.add(r7)
            goto L_0x001d
        L_0x0035:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            com.swmansion.reanimated.layoutReanimation.AnimationsManager r7 = r0.mAnimationsManager
            com.swmansion.reanimated.layoutReanimation.ReanimatedNativeHierarchyManager r7 = r7.getReanimatedNativeHierarchyManager()
            java.util.HashSet r8 = new java.util.HashSet
            r8.<init>()
            java.util.List<android.view.View> r9 = r0.mRemovedSharedViews
            java.util.Iterator r9 = r9.iterator()
        L_0x004b:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0063
            java.lang.Object r10 = r9.next()
            android.view.View r10 = (android.view.View) r10
            int r10 = r10.getId()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r8.add(r10)
            goto L_0x004b
        L_0x0063:
            java.util.Iterator r9 = r19.iterator()
        L_0x0067:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x019b
            java.lang.Object r10 = r9.next()
            android.view.View r10 = (android.view.View) r10
            com.swmansion.reanimated.layoutReanimation.NativeMethodsHolder r11 = r0.mNativeMethodsHolder
            int r12 = r10.getId()
            int r11 = r11.findPrecedingViewTagForTransition(r12)
            if (r1 == 0) goto L_0x0099
        L_0x007f:
            java.lang.Integer r12 = java.lang.Integer.valueOf(r11)
            boolean r12 = r8.contains(r12)
            if (r12 == 0) goto L_0x0099
            com.swmansion.reanimated.layoutReanimation.NativeMethodsHolder r12 = r0.mNativeMethodsHolder
            r12.clearAnimationConfig(r11)
            com.swmansion.reanimated.layoutReanimation.NativeMethodsHolder r11 = r0.mNativeMethodsHolder
            int r12 = r10.getId()
            int r11 = r11.findPrecedingViewTagForTransition(r12)
            goto L_0x007f
        L_0x0099:
            if (r20 != 0) goto L_0x00a7
            java.lang.Integer r12 = java.lang.Integer.valueOf(r11)
            boolean r12 = r5.contains(r12)
            if (r12 == 0) goto L_0x00a7
            r12 = 1
            goto L_0x00a8
        L_0x00a7:
            r12 = 0
        L_0x00a8:
            if (r11 >= 0) goto L_0x00ab
            goto L_0x0067
        L_0x00ab:
            if (r20 == 0) goto L_0x00b7
            android.view.View r11 = r7.resolveView(r11)
            r17 = r11
            r11 = r10
            r10 = r17
            goto L_0x00bb
        L_0x00b7:
            android.view.View r11 = r7.resolveView(r11)
        L_0x00bb:
            if (r12 == 0) goto L_0x00c4
            r0.clearAllSharedConfigsForView(r10)
            r0.clearAllSharedConfigsForView(r11)
            goto L_0x0067
        L_0x00c4:
            java.util.Map<java.lang.Integer, android.view.View> r12 = r0.mCurrentSharedTransitionViews
            int r13 = r10.getId()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            boolean r12 = r12.containsKey(r13)
            if (r12 != 0) goto L_0x013e
            android.view.View r13 = r0.findScreen(r10)
            android.view.View r14 = r0.findScreen(r11)
            if (r13 == 0) goto L_0x0067
            if (r14 != 0) goto L_0x00e1
            goto L_0x0067
        L_0x00e1:
            android.view.View r15 = r0.findStack(r13)
            android.view.ViewGroup r15 = (android.view.ViewGroup) r15
            if (r15 != 0) goto L_0x00eb
            goto L_0x0067
        L_0x00eb:
            int r2 = r15.getId()
            com.facebook.react.uimanager.ViewManager r2 = r7.resolveViewManager(r2)
            com.facebook.react.uimanager.ViewGroupManager r2 = (com.facebook.react.uimanager.ViewGroupManager) r2
            int r3 = r2.getChildCount(r15)
            r16 = r1
            r1 = 2
            if (r3 >= r1) goto L_0x0100
            goto L_0x0188
        L_0x0100:
            int r1 = r3 + -1
            android.view.View r1 = r2.getChildAt(r15, (int) r1)
            int r3 = r3 + -2
            android.view.View r2 = r2.getChildAt(r15, (int) r3)
            if (r20 == 0) goto L_0x0126
            int r2 = r2.getId()
            int r3 = r13.getId()
            if (r2 != r3) goto L_0x0124
            int r1 = r1.getId()
            int r2 = r14.getId()
            if (r1 != r2) goto L_0x0124
        L_0x0122:
            r1 = 1
            goto L_0x013b
        L_0x0124:
            r1 = 0
            goto L_0x013b
        L_0x0126:
            int r1 = r1.getId()
            int r3 = r13.getId()
            if (r1 != r3) goto L_0x0124
            int r1 = r2.getId()
            int r2 = r14.getId()
            if (r1 != r2) goto L_0x0124
            goto L_0x0122
        L_0x013b:
            if (r1 != 0) goto L_0x0140
            goto L_0x0188
        L_0x013e:
            r16 = r1
        L_0x0140:
            r1 = 0
            if (r20 == 0) goto L_0x015f
            java.util.Set<java.lang.Integer> r2 = r0.mViewTagsToHide
            int r3 = r10.getId()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2.add(r3)
            if (r12 == 0) goto L_0x0158
            com.swmansion.reanimated.layoutReanimation.Snapshot r1 = new com.swmansion.reanimated.layoutReanimation.Snapshot
            r1.<init>(r10)
            goto L_0x015b
        L_0x0158:
            r0.makeSnapshot(r10)
        L_0x015b:
            r0.makeSnapshot(r11)
            goto L_0x0164
        L_0x015f:
            if (r12 == 0) goto L_0x0164
            r0.makeSnapshot(r10)
        L_0x0164:
            if (r1 != 0) goto L_0x0176
            java.util.Map<java.lang.Integer, com.swmansion.reanimated.layoutReanimation.Snapshot> r1 = r0.mSnapshotRegistry
            int r2 = r10.getId()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Object r1 = r1.get(r2)
            com.swmansion.reanimated.layoutReanimation.Snapshot r1 = (com.swmansion.reanimated.layoutReanimation.Snapshot) r1
        L_0x0176:
            java.util.Map<java.lang.Integer, com.swmansion.reanimated.layoutReanimation.Snapshot> r2 = r0.mSnapshotRegistry
            int r3 = r11.getId()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object r2 = r2.get(r3)
            com.swmansion.reanimated.layoutReanimation.Snapshot r2 = (com.swmansion.reanimated.layoutReanimation.Snapshot) r2
            if (r2 != 0) goto L_0x0189
        L_0x0188:
            goto L_0x0197
        L_0x0189:
            r4.add(r10)
            r4.add(r11)
            com.swmansion.reanimated.layoutReanimation.SharedElement r3 = new com.swmansion.reanimated.layoutReanimation.SharedElement
            r3.<init>(r10, r1, r11, r2)
            r6.add(r3)
        L_0x0197:
            r1 = r16
            goto L_0x0067
        L_0x019b:
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L_0x0226
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List<com.swmansion.reanimated.layoutReanimation.SharedElement> r2 = r0.mSharedElements
            java.util.Iterator r2 = r2.iterator()
        L_0x01ac:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x01be
            java.lang.Object r3 = r2.next()
            com.swmansion.reanimated.layoutReanimation.SharedElement r3 = (com.swmansion.reanimated.layoutReanimation.SharedElement) r3
            android.view.View r3 = r3.sourceView
            r1.add(r3)
            goto L_0x01ac
        L_0x01be:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Iterator r3 = r6.iterator()
        L_0x01c7:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x01d9
            java.lang.Object r5 = r3.next()
            com.swmansion.reanimated.layoutReanimation.SharedElement r5 = (com.swmansion.reanimated.layoutReanimation.SharedElement) r5
            android.view.View r5 = r5.sourceView
            r2.add(r5)
            goto L_0x01c7
        L_0x01d9:
            java.util.Iterator r1 = r1.iterator()
        L_0x01dd:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0203
            java.lang.Object r3 = r1.next()
            android.view.View r3 = (android.view.View) r3
            boolean r5 = r2.contains(r3)
            if (r5 != 0) goto L_0x0201
            java.util.Set<java.lang.Integer> r5 = r0.mViewTagsToHide
            int r7 = r3.getId()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r5.remove(r7)
            r5 = 0
            r3.setVisibility(r5)
            goto L_0x01dd
        L_0x0201:
            r5 = 0
            goto L_0x01dd
        L_0x0203:
            java.util.Map<java.lang.Integer, android.view.View> r1 = r0.mCurrentSharedTransitionViews
            r1.clear()
            java.util.Iterator r1 = r4.iterator()
        L_0x020c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0226
            java.lang.Object r2 = r1.next()
            android.view.View r2 = (android.view.View) r2
            java.util.Map<java.lang.Integer, android.view.View> r3 = r0.mCurrentSharedTransitionViews
            int r4 = r2.getId()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3.put(r4, r2)
            goto L_0x020c
        L_0x0226:
            r0.mSharedElements = r6
            java.util.Iterator r1 = r6.iterator()
        L_0x022c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0248
            java.lang.Object r2 = r1.next()
            com.swmansion.reanimated.layoutReanimation.SharedElement r2 = (com.swmansion.reanimated.layoutReanimation.SharedElement) r2
            java.util.Map<java.lang.Integer, com.swmansion.reanimated.layoutReanimation.SharedElement> r3 = r0.mSharedElementsLookup
            android.view.View r4 = r2.sourceView
            int r4 = r4.getId()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3.put(r4, r2)
            goto L_0x022c
        L_0x0248:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.reanimated.layoutReanimation.SharedTransitionManager.getSharedElementsForCurrentTransition(java.util.List, boolean):java.util.List");
    }

    private void setupTransitionContainer() {
        Activity currentActivity;
        if (this.mTransitionContainer == null) {
            this.mTransitionContainer = new ReactViewGroup(this.mAnimationsManager.getContext());
        }
        if (this.mTransitionContainer.getParent() == null && (currentActivity = this.mAnimationsManager.getContext().getCurrentActivity()) != null) {
            ((ViewGroup) currentActivity.getWindow().getDecorView().getRootView()).addView(this.mTransitionContainer);
            this.mTransitionContainer.bringToFront();
        }
    }

    private void reparentSharedViewsForCurrentTransition(List<SharedElement> list) {
        for (SharedElement sharedElement : list) {
            View view = sharedElement.sourceView;
            if (!this.mSharedTransitionParent.containsKey(Integer.valueOf(view.getId()))) {
                this.mSharedTransitionParent.put(Integer.valueOf(view.getId()), (View) view.getParent());
                this.mSharedTransitionInParentIndex.put(Integer.valueOf(view.getId()), Integer.valueOf(((ViewGroup) view.getParent()).indexOfChild(view)));
                ((ViewGroup) view.getParent()).removeView(view);
                ((ViewGroup) this.mTransitionContainer).addView(view);
                this.mReattachedViews.add(view);
            }
        }
    }

    private void startSharedTransition(List<SharedElement> list, int i) {
        for (SharedElement next : list) {
            View view = next.sourceView;
            view.setVisibility(0);
            startSharedAnimationForView(view, next.sourceViewSnapshot, next.targetViewSnapshot, i);
            next.targetView.setVisibility(4);
        }
    }

    private void startSharedAnimationForView(View view, Snapshot snapshot, Snapshot snapshot2, int i) {
        HashMap<String, Object> targetMap = snapshot2.toTargetMap();
        HashMap<String, Object> prepareDataForAnimationWorklet = this.mAnimationsManager.prepareDataForAnimationWorklet(snapshot.toCurrentMap(), false, true);
        HashMap hashMap = new HashMap(this.mAnimationsManager.prepareDataForAnimationWorklet(targetMap, true, true));
        hashMap.putAll(prepareDataForAnimationWorklet);
        this.mNativeMethodsHolder.startAnimation(view.getId(), i, hashMap);
    }

    /* access modifiers changed from: protected */
    public void finishSharedAnimation(int i) {
        ViewParent parent;
        if (this.mDisableCleaningForViewTag.containsKey(Integer.valueOf(i))) {
            enableCleaningForViewTag(i);
            return;
        }
        SharedElement sharedElement = this.mSharedElementsLookup.get(Integer.valueOf(i));
        if (sharedElement != null) {
            this.mSharedElementsLookup.remove(Integer.valueOf(i));
            View view = sharedElement.sourceView;
            if (this.mReattachedViews.contains(view)) {
                this.mReattachedViews.remove(view);
                int id = view.getId();
                ((ViewGroup) this.mTransitionContainer).removeView(view);
                int intValue = this.mSharedTransitionInParentIndex.get(Integer.valueOf(id)).intValue();
                ViewGroup viewGroup = (ViewGroup) this.mSharedTransitionParent.get(Integer.valueOf(id));
                if (intValue <= viewGroup.getChildCount()) {
                    viewGroup.addView(view, intValue);
                } else {
                    viewGroup.addView(view);
                }
                Snapshot snapshot = this.mSnapshotRegistry.get(Integer.valueOf(id));
                if (snapshot != null) {
                    int i2 = snapshot.originY;
                    if (findStack(view) == null) {
                        snapshot.originY = snapshot.originYByParent;
                    }
                    HashMap<String, Object> basicMap = snapshot.toBasicMap();
                    HashMap hashMap = new HashMap();
                    for (String next : basicMap.keySet()) {
                        Object obj = basicMap.get(next);
                        if (next.equals(Snapshot.TRANSFORM_MATRIX)) {
                            hashMap.put(next, obj);
                        } else {
                            hashMap.put(next, Double.valueOf((double) PixelUtil.toDIPFromPixel(Utils.convertToFloat(obj))));
                        }
                    }
                    this.mAnimationsManager.progressLayoutAnimation(id, hashMap, true);
                    snapshot.originY = i2;
                }
                if (this.mViewTagsToHide.contains(Integer.valueOf(i))) {
                    view.setVisibility(4);
                }
                this.mCurrentSharedTransitionViews.remove(Integer.valueOf(sharedElement.targetView.getId()));
                this.mCurrentSharedTransitionViews.remove(Integer.valueOf(id));
                this.mSharedTransitionParent.remove(Integer.valueOf(id));
                this.mSharedTransitionInParentIndex.remove(Integer.valueOf(id));
            }
            sharedElement.targetView.setVisibility(0);
            if (this.mRemovedSharedViews.contains(view)) {
                this.mRemovedSharedViews.remove(view);
                this.mSnapshotRegistry.remove(Integer.valueOf(view.getId()));
                this.mNativeMethodsHolder.clearAnimationConfig(view.getId());
            }
            if (this.mReattachedViews.isEmpty()) {
                View view2 = this.mTransitionContainer;
                if (!(view2 == null || (parent = view2.getParent()) == null)) {
                    this.mTransitionContainer.post(new SharedTransitionManager$$ExternalSyntheticLambda0(this, parent));
                }
                this.mSharedElements.clear();
                this.mSharedElementsWithProgress.clear();
                this.mSharedElementsWithAnimation.clear();
                this.mViewTagsToHide.clear();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finishSharedAnimation$1(ViewParent viewParent) {
        if (this.mReattachedViews.size() <= 0) {
            ((ViewGroup) viewParent).removeView(this.mTransitionContainer);
        }
    }

    @Nullable
    private View findScreen(View view) {
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent.getClass().getSimpleName().equals("Screen")) {
                return (View) parent;
            }
        }
        return null;
    }

    @Nullable
    private View findStack(View view) {
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent.getClass().getSimpleName().equals("ScreenStack")) {
                return (View) parent;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void makeSnapshot(View view) {
        this.mSnapshotRegistry.put(Integer.valueOf(view.getId()), new Snapshot(view));
    }

    class SnapshotTreeVisitor implements TreeVisitor {
        SnapshotTreeVisitor() {
        }

        public void run(View view) {
            if (SharedTransitionManager.this.mAnimationsManager.hasAnimationForTag(view.getId(), 4)) {
                SharedTransitionManager.this.mRemovedSharedViews.add(view);
                SharedTransitionManager.this.makeSnapshot(view);
            }
        }
    }

    class ConfigCleanerTreeVisitor implements TreeVisitor {
        ConfigCleanerTreeVisitor() {
        }

        public void run(View view) {
            SharedTransitionManager.this.mNativeMethodsHolder.clearAnimationConfig(view.getId());
        }
    }

    /* access modifiers changed from: protected */
    public void visitTreeForTags(int[] iArr, TreeVisitor treeVisitor) {
        if (iArr != null) {
            ReanimatedNativeHierarchyManager reanimatedNativeHierarchyManager = this.mAnimationsManager.getReanimatedNativeHierarchyManager();
            for (int resolveView : iArr) {
                visitTree(reanimatedNativeHierarchyManager.resolveView(resolveView), treeVisitor);
            }
        }
    }

    private void visitTree(View view, TreeVisitor treeVisitor) {
        int id = view.getId();
        if (id != -1) {
            ReanimatedNativeHierarchyManager reanimatedNativeHierarchyManager = this.mAnimationsManager.getReanimatedNativeHierarchyManager();
            try {
                treeVisitor.run(view);
                if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    ViewManager resolveViewManager = reanimatedNativeHierarchyManager.resolveViewManager(id);
                    ViewGroupManager viewGroupManager = resolveViewManager instanceof ViewGroupManager ? (ViewGroupManager) resolveViewManager : null;
                    if (viewGroupManager != null) {
                        for (int i = 0; i < viewGroupManager.getChildCount(viewGroup); i++) {
                            visitTree(viewGroupManager.getChildAt(viewGroup, i), treeVisitor);
                        }
                    }
                }
            } catch (IllegalViewOperationException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void visitNativeTreeAndMakeSnapshot(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (this.mAnimationsManager.hasAnimationForTag(view.getId(), 4)) {
                makeSnapshot(view);
            }
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                visitNativeTreeAndMakeSnapshot(viewGroup.getChildAt(i));
            }
        }
    }

    private void clearAllSharedConfigsForView(View view) {
        int id = view.getId();
        this.mSnapshotRegistry.remove(Integer.valueOf(id));
        this.mNativeMethodsHolder.clearAnimationConfig(id);
    }

    private void disableCleaningForViewTag(int i) {
        Integer num = this.mDisableCleaningForViewTag.get(Integer.valueOf(i));
        if (num != null) {
            this.mDisableCleaningForViewTag.put(Integer.valueOf(i), Integer.valueOf(num.intValue() + 1));
        } else {
            this.mDisableCleaningForViewTag.put(Integer.valueOf(i), 1);
        }
    }

    private void enableCleaningForViewTag(int i) {
        Integer num = this.mDisableCleaningForViewTag.get(Integer.valueOf(i));
        if (num != null) {
            if (num.intValue() == 1) {
                this.mDisableCleaningForViewTag.remove(Integer.valueOf(i));
            } else {
                this.mDisableCleaningForViewTag.put(Integer.valueOf(i), Integer.valueOf(num.intValue() - 1));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void orderByAnimationTypes(List<SharedElement> list) {
        this.mSharedElementsWithProgress.clear();
        this.mSharedElementsWithAnimation.clear();
        for (SharedElement next : list) {
            if (this.mAnimationsManager.hasAnimationForTag(next.sourceView.getId(), 5)) {
                this.mSharedElementsWithProgress.add(next);
            } else {
                this.mSharedElementsWithAnimation.add(next);
            }
        }
    }
}
