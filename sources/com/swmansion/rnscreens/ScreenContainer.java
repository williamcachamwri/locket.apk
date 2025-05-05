package com.swmansion.rnscreens;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.ChoreographerCompat;
import com.facebook.react.modules.core.ReactChoreographer;
import com.swmansion.rnscreens.Screen;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0019H\u0014J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0015J\u0018\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020#H\u0004J\u0018\u0010'\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020*H\u0002J\u0012\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020\u0010H\u0002J\u000e\u0010.\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u0015J\u000e\u0010/\u001a\u00020\u00102\u0006\u0010 \u001a\u00020\u0015J\u0012\u00100\u001a\u00020\u00062\b\u0010-\u001a\u0004\u0018\u00010\u0010H\u0016J\u0006\u00101\u001a\u00020\u001fJ\b\u00102\u001a\u00020\u001fH\u0014J\b\u00103\u001a\u00020\u001fH\u0014J\b\u00104\u001a\u00020\u001fH\u0014J0\u00105\u001a\u00020\u001f2\u0006\u00106\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00152\u0006\u00108\u001a\u00020\u00152\u0006\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u0015H\u0014J\u0018\u0010;\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u00152\u0006\u0010=\u001a\u00020\u0015H\u0014J\b\u0010>\u001a\u00020\u001fH\u0002J\b\u0010?\u001a\u00020\u001fH\u0016J\u0006\u0010@\u001a\u00020\u001fJ\b\u0010A\u001a\u00020\u001fH\u0004J\b\u0010B\u001a\u00020\u001fH\u0016J\u0010\u0010C\u001a\u00020\u001f2\u0006\u0010D\u001a\u00020\tH\u0002J\u0010\u0010E\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0015H\u0016J\u0010\u0010F\u001a\u00020\u001f2\u0006\u0010G\u001a\u00020HH\u0016J\b\u0010I\u001a\u00020\u001fH\u0016J\u0010\u0010J\u001a\u00020\u001f2\u0006\u0010K\u001a\u00020\tH\u0002J\b\u0010L\u001a\u00020\u001fH\u0002R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0014\u0010\b\u001a\u0004\u0018\u00010\t8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000R \u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0012j\b\u0012\u0004\u0012\u00020\u0010`\u00138\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u00198VX\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006M"}, d2 = {"Lcom/swmansion/rnscreens/ScreenContainer;", "Landroid/view/ViewGroup;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isNested", "", "()Z", "mFragmentManager", "Landroidx/fragment/app/FragmentManager;", "mIsAttached", "mLayoutCallback", "Lcom/facebook/react/modules/core/ChoreographerCompat$FrameCallback;", "mLayoutEnqueued", "mNeedUpdate", "mParentScreenFragment", "Lcom/swmansion/rnscreens/ScreenFragmentWrapper;", "mScreenFragments", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "screenCount", "", "getScreenCount", "()I", "topScreen", "Lcom/swmansion/rnscreens/Screen;", "getTopScreen", "()Lcom/swmansion/rnscreens/Screen;", "adapt", "screen", "addScreen", "", "index", "attachScreen", "transaction", "Landroidx/fragment/app/FragmentTransaction;", "fragment", "Landroidx/fragment/app/Fragment;", "createTransaction", "detachScreen", "findFragmentManagerForReactRootView", "rootView", "Lcom/facebook/react/ReactRootView;", "getActivityState", "Lcom/swmansion/rnscreens/Screen$ActivityState;", "screenFragmentWrapper", "getScreenAt", "getScreenFragmentWrapperAt", "hasScreen", "notifyChildUpdate", "notifyContainerUpdate", "onAttachedToWindow", "onDetachedFromWindow", "onLayout", "changed", "l", "t", "r", "b", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onScreenChanged", "onUpdate", "performUpdates", "performUpdatesNow", "removeAllScreens", "removeMyFragments", "fragmentManager", "removeScreenAt", "removeView", "view", "Landroid/view/View;", "requestLayout", "setFragmentManager", "fm", "setupFragmentManager", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ScreenContainer.kt */
public class ScreenContainer extends ViewGroup {
    protected FragmentManager mFragmentManager;
    private boolean mIsAttached;
    private final ChoreographerCompat.FrameCallback mLayoutCallback = new ScreenContainer$mLayoutCallback$1(this);
    /* access modifiers changed from: private */
    public boolean mLayoutEnqueued;
    private boolean mNeedUpdate;
    private ScreenFragmentWrapper mParentScreenFragment;
    protected final ArrayList<ScreenFragmentWrapper> mScreenFragments = new ArrayList<>();

    public ScreenContainer(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            getChildAt(i5).layout(0, 0, getWidth(), getHeight());
        }
    }

    public void removeView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view == getFocusedChild()) {
            Object systemService = getContext().getSystemService("input_method");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            ((InputMethodManager) systemService).hideSoftInputFromWindow(getWindowToken(), 2);
        }
        super.removeView(view);
    }

    public void requestLayout() {
        super.requestLayout();
        if (!this.mLayoutEnqueued && this.mLayoutCallback != null) {
            this.mLayoutEnqueued = true;
            ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer.CallbackType.NATIVE_ANIMATED_MODULE, this.mLayoutCallback);
        }
    }

    public final boolean isNested() {
        return this.mParentScreenFragment != null;
    }

    public final void notifyChildUpdate() {
        performUpdatesNow();
    }

    /* access modifiers changed from: protected */
    public ScreenFragmentWrapper adapt(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        return new ScreenFragment(screen);
    }

    public final void addScreen(Screen screen, int i) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        ScreenFragmentWrapper adapt = adapt(screen);
        screen.setFragmentWrapper(adapt);
        this.mScreenFragments.add(i, adapt);
        screen.setContainer(this);
        onScreenChanged();
    }

    public void removeScreenAt(int i) {
        this.mScreenFragments.get(i).getScreen().setContainer((ScreenContainer) null);
        this.mScreenFragments.remove(i);
        onScreenChanged();
    }

    public void removeAllScreens() {
        Iterator<ScreenFragmentWrapper> it = this.mScreenFragments.iterator();
        while (it.hasNext()) {
            it.next().getScreen().setContainer((ScreenContainer) null);
        }
        this.mScreenFragments.clear();
        onScreenChanged();
    }

    public final int getScreenCount() {
        return this.mScreenFragments.size();
    }

    public final Screen getScreenAt(int i) {
        return this.mScreenFragments.get(i).getScreen();
    }

    public final ScreenFragmentWrapper getScreenFragmentWrapperAt(int i) {
        ScreenFragmentWrapper screenFragmentWrapper = this.mScreenFragments.get(i);
        Intrinsics.checkNotNullExpressionValue(screenFragmentWrapper, "get(...)");
        return screenFragmentWrapper;
    }

    public Screen getTopScreen() {
        Object obj;
        boolean z;
        Iterator it = this.mScreenFragments.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (getActivityState((ScreenFragmentWrapper) obj) == Screen.ActivityState.ON_TOP) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        ScreenFragmentWrapper screenFragmentWrapper = (ScreenFragmentWrapper) obj;
        if (screenFragmentWrapper != null) {
            return screenFragmentWrapper.getScreen();
        }
        return null;
    }

    private final void setFragmentManager(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
        performUpdatesNow();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0015  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final androidx.fragment.app.FragmentManager findFragmentManagerForReactRootView(com.facebook.react.ReactRootView r4) {
        /*
            r3 = this;
            android.content.Context r0 = r4.getContext()
        L_0x0004:
            boolean r1 = r0 instanceof androidx.fragment.app.FragmentActivity
            if (r1 != 0) goto L_0x0013
            boolean r2 = r0 instanceof android.content.ContextWrapper
            if (r2 == 0) goto L_0x0013
            android.content.ContextWrapper r0 = (android.content.ContextWrapper) r0
            android.content.Context r0 = r0.getBaseContext()
            goto L_0x0004
        L_0x0013:
            if (r1 == 0) goto L_0x0040
            androidx.fragment.app.FragmentActivity r0 = (androidx.fragment.app.FragmentActivity) r0
            androidx.fragment.app.FragmentManager r1 = r0.getSupportFragmentManager()
            java.util.List r1 = r1.getFragments()
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x002d
            androidx.fragment.app.FragmentManager r4 = r0.getSupportFragmentManager()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            goto L_0x003f
        L_0x002d:
            android.view.View r4 = (android.view.View) r4     // Catch:{ IllegalStateException -> 0x0038 }
            androidx.fragment.app.Fragment r4 = androidx.fragment.app.FragmentManager.findFragment(r4)     // Catch:{ IllegalStateException -> 0x0038 }
            androidx.fragment.app.FragmentManager r4 = r4.getChildFragmentManager()     // Catch:{ IllegalStateException -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            androidx.fragment.app.FragmentManager r4 = r0.getSupportFragmentManager()
        L_0x003c:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
        L_0x003f:
            return r4
        L_0x0040:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "In order to use RNScreens components your app's activity need to extend ReactActivity"
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenContainer.findFragmentManagerForReactRootView(com.facebook.react.ReactRootView):androidx.fragment.app.FragmentManager");
    }

    private final void setupFragmentManager() {
        boolean z;
        Unit unit;
        ViewParent viewParent = this;
        while (true) {
            z = viewParent instanceof ReactRootView;
            if (!z && !(viewParent instanceof Screen) && viewParent.getParent() != null) {
                viewParent = viewParent.getParent();
                Intrinsics.checkNotNullExpressionValue(viewParent, "getParent(...)");
            }
        }
        if (viewParent instanceof Screen) {
            ScreenFragmentWrapper fragmentWrapper = ((Screen) viewParent).getFragmentWrapper();
            if (fragmentWrapper != null) {
                this.mParentScreenFragment = fragmentWrapper;
                fragmentWrapper.addChildScreenContainer(this);
                FragmentManager childFragmentManager = fragmentWrapper.getFragment().getChildFragmentManager();
                Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                setFragmentManager(childFragmentManager);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                throw new IllegalStateException("Parent Screen does not have its Fragment attached".toString());
            }
        } else if (z) {
            setFragmentManager(findFragmentManagerForReactRootView((ReactRootView) viewParent));
        } else {
            throw new IllegalStateException("ScreenContainer is not attached under ReactRootView".toString());
        }
    }

    /* access modifiers changed from: protected */
    public final FragmentTransaction createTransaction() {
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null) {
            FragmentTransaction reorderingAllowed = fragmentManager.beginTransaction().setReorderingAllowed(true);
            Intrinsics.checkNotNullExpressionValue(reorderingAllowed, "setReorderingAllowed(...)");
            return reorderingAllowed;
        }
        throw new IllegalArgumentException("mFragmentManager is null when creating transaction".toString());
    }

    private final void attachScreen(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.add(getId(), fragment);
    }

    private final void detachScreen(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.remove(fragment);
    }

    private final Screen.ActivityState getActivityState(ScreenFragmentWrapper screenFragmentWrapper) {
        return screenFragmentWrapper.getScreen().getActivityState();
    }

    public boolean hasScreen(ScreenFragmentWrapper screenFragmentWrapper) {
        return CollectionsKt.contains(this.mScreenFragments, screenFragmentWrapper);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsAttached = true;
        setupFragmentManager();
    }

    private final void removeMyFragments(FragmentManager fragmentManager) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "beginTransaction(...)");
        boolean z = false;
        for (Fragment next : fragmentManager.getFragments()) {
            if ((next instanceof ScreenFragment) && ((ScreenFragment) next).getScreen().getContainer() == this) {
                beginTransaction.remove(next);
                z = true;
            }
        }
        if (z) {
            beginTransaction.commitNowAllowingStateLoss();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null && !fragmentManager.isDestroyed()) {
            removeMyFragments(fragmentManager);
            fragmentManager.executePendingTransactions();
        }
        ScreenFragmentWrapper screenFragmentWrapper = this.mParentScreenFragment;
        if (screenFragmentWrapper != null) {
            screenFragmentWrapper.removeChildScreenContainer(this);
        }
        this.mParentScreenFragment = null;
        super.onDetachedFromWindow();
        this.mIsAttached = false;
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (-1 < childCount) {
                removeViewAt(childCount);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).measure(i, i2);
        }
    }

    private final void onScreenChanged() {
        this.mNeedUpdate = true;
        Context context = getContext();
        ReactContext reactContext = context instanceof ReactContext ? (ReactContext) context : null;
        if (reactContext != null) {
            reactContext.runOnUiQueueThread(new ScreenContainer$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void onScreenChanged$lambda$7(ScreenContainer screenContainer) {
        Intrinsics.checkNotNullParameter(screenContainer, "this$0");
        screenContainer.performUpdates();
    }

    /* access modifiers changed from: protected */
    public final void performUpdatesNow() {
        this.mNeedUpdate = true;
        performUpdates();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
        if (r0.isDestroyed() == true) goto L_0x0018;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void performUpdates() {
        /*
            r3 = this;
            boolean r0 = r3.mNeedUpdate
            if (r0 == 0) goto L_0x0023
            boolean r0 = r3.mIsAttached
            if (r0 == 0) goto L_0x0023
            androidx.fragment.app.FragmentManager r0 = r3.mFragmentManager
            if (r0 == 0) goto L_0x0023
            r1 = 0
            if (r0 == 0) goto L_0x0017
            boolean r0 = r0.isDestroyed()
            r2 = 1
            if (r0 != r2) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r2 = r1
        L_0x0018:
            if (r2 == 0) goto L_0x001b
            goto L_0x0023
        L_0x001b:
            r3.mNeedUpdate = r1
            r3.onUpdate()
            r3.notifyContainerUpdate()
        L_0x0023:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenContainer.performUpdates():void");
    }

    public void onUpdate() {
        FragmentTransaction createTransaction = createTransaction();
        FragmentManager fragmentManager = this.mFragmentManager;
        if (fragmentManager != null) {
            Set hashSet = new HashSet(fragmentManager.getFragments());
            Iterator<ScreenFragmentWrapper> it = this.mScreenFragments.iterator();
            while (it.hasNext()) {
                ScreenFragmentWrapper next = it.next();
                Intrinsics.checkNotNull(next);
                if (getActivityState(next) == Screen.ActivityState.INACTIVE && next.getFragment().isAdded()) {
                    detachScreen(createTransaction, next.getFragment());
                }
                hashSet.remove(next.getFragment());
            }
            Collection collection = hashSet;
            boolean z = false;
            if (!collection.isEmpty()) {
                for (Fragment fragment : (Fragment[]) collection.toArray(new Fragment[0])) {
                    if ((fragment instanceof ScreenFragment) && ((ScreenFragment) fragment).getScreen().getContainer() == null) {
                        detachScreen(createTransaction, fragment);
                    }
                }
            }
            boolean z2 = getTopScreen() == null;
            ArrayList arrayList = new ArrayList();
            Iterator<ScreenFragmentWrapper> it2 = this.mScreenFragments.iterator();
            while (it2.hasNext()) {
                ScreenFragmentWrapper next2 = it2.next();
                Intrinsics.checkNotNull(next2);
                Screen.ActivityState activityState = getActivityState(next2);
                if (activityState != Screen.ActivityState.INACTIVE && !next2.getFragment().isAdded()) {
                    attachScreen(createTransaction, next2.getFragment());
                    z = true;
                } else if (activityState != Screen.ActivityState.INACTIVE && z) {
                    detachScreen(createTransaction, next2.getFragment());
                    arrayList.add(next2);
                }
                next2.getScreen().setTransitioning(z2);
            }
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                attachScreen(createTransaction, ((ScreenFragmentWrapper) it3.next()).getFragment());
            }
            createTransaction.commitNowAllowingStateLoss();
            return;
        }
        throw new IllegalArgumentException("mFragmentManager is null when performing update in ScreenContainer".toString());
    }

    /* access modifiers changed from: protected */
    public void notifyContainerUpdate() {
        ScreenFragmentWrapper fragmentWrapper;
        Screen topScreen = getTopScreen();
        if (topScreen != null && (fragmentWrapper = topScreen.getFragmentWrapper()) != null) {
            fragmentWrapper.onContainerUpdate();
        }
    }
}
