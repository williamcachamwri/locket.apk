package com.swmansion.rnscreens;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.swmansion.rnscreens.events.HeaderBackButtonClickedEvent;
import com.swmansion.rnscreens.events.ScreenAppearEvent;
import com.swmansion.rnscreens.events.ScreenDisappearEvent;
import com.swmansion.rnscreens.events.ScreenDismissedEvent;
import com.swmansion.rnscreens.events.ScreenTransitionProgressEvent;
import com.swmansion.rnscreens.events.ScreenWillAppearEvent;
import com.swmansion.rnscreens.events.ScreenWillDisappearEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 C2\u00020\u00012\u00020\u0002:\u0003CDEB\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u000f\b\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\fH\u0016J\u0010\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u001eH\u0016J\u0018\u0010$\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"2\u0006\u0010%\u001a\u00020\u0002H\u0016J\u0010\u0010&\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010'\u001a\u00020\u001eH\u0002J\b\u0010(\u001a\u00020\u001eH\u0002J\b\u0010)\u001a\u00020\u001eH\u0002J\b\u0010*\u001a\u00020\u001eH\u0002J\u0018\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\bH\u0016J\u0010\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\bH\u0002J\b\u00100\u001a\u00020\u001eH\u0016J&\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\b\u0010\u001f\u001a\u0004\u0018\u0001052\b\u00106\u001a\u0004\u0018\u000107H\u0016J\b\u00108\u001a\u00020\u001eH\u0016J\b\u00109\u001a\u00020\u001eH\u0016J\b\u0010:\u001a\u00020\u001eH\u0016J\b\u0010;\u001a\u00020\u001eH\u0016J\u0010\u0010<\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\fH\u0016J\n\u0010=\u001a\u0004\u0018\u00010>H\u0016J\n\u0010?\u001a\u0004\u0018\u00010@H\u0016J\u0010\u0010A\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010B\u001a\u00020\u001eH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00018VX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R \u0010\u0017\u001a\u00020\u0005X.¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0003\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u0006R\u000e\u0010\u001c\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/swmansion/rnscreens/ScreenFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/swmansion/rnscreens/ScreenFragmentWrapper;", "()V", "screenView", "Lcom/swmansion/rnscreens/Screen;", "(Lcom/swmansion/rnscreens/Screen;)V", "canDispatchAppear", "", "canDispatchWillAppear", "childScreenContainers", "", "Lcom/swmansion/rnscreens/ScreenContainer;", "getChildScreenContainers", "()Ljava/util/List;", "fragment", "getFragment", "()Landroidx/fragment/app/Fragment;", "isTransitioning", "mChildScreenContainers", "", "mProgress", "", "screen", "getScreen$annotations", "getScreen", "()Lcom/swmansion/rnscreens/Screen;", "setScreen", "shouldUpdateOnResume", "addChildScreenContainer", "", "container", "canDispatchLifecycleEvent", "event", "Lcom/swmansion/rnscreens/ScreenFragment$ScreenLifecycleEvent;", "dispatchHeaderBackButtonClickedEvent", "dispatchLifecycleEvent", "fragmentWrapper", "dispatchLifecycleEventInChildContainers", "dispatchOnAppear", "dispatchOnDisappear", "dispatchOnWillAppear", "dispatchOnWillDisappear", "dispatchTransitionProgressEvent", "alpha", "closing", "dispatchViewAnimationEvent", "animationEnd", "onContainerUpdate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "onViewAnimationEnd", "onViewAnimationStart", "removeChildScreenContainer", "tryGetActivity", "Landroid/app/Activity;", "tryGetContext", "Lcom/facebook/react/bridge/ReactContext;", "updateLastEventDispatched", "updateWindowTraits", "Companion", "ScreenLifecycleEvent", "ScreensFrameLayout", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ScreenFragment.kt */
public class ScreenFragment extends Fragment implements ScreenFragmentWrapper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean canDispatchAppear = true;
    private boolean canDispatchWillAppear = true;
    private boolean isTransitioning;
    private final List<ScreenContainer> mChildScreenContainers = new ArrayList();
    private float mProgress = -1.0f;
    public Screen screen;
    private boolean shouldUpdateOnResume;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ScreenFragment.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.swmansion.rnscreens.ScreenFragment$ScreenLifecycleEvent[] r0 = com.swmansion.rnscreens.ScreenFragment.ScreenLifecycleEvent.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.swmansion.rnscreens.ScreenFragment$ScreenLifecycleEvent r1 = com.swmansion.rnscreens.ScreenFragment.ScreenLifecycleEvent.WillAppear     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.swmansion.rnscreens.ScreenFragment$ScreenLifecycleEvent r1 = com.swmansion.rnscreens.ScreenFragment.ScreenLifecycleEvent.Appear     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.swmansion.rnscreens.ScreenFragment$ScreenLifecycleEvent r1 = com.swmansion.rnscreens.ScreenFragment.ScreenLifecycleEvent.WillDisappear     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.swmansion.rnscreens.ScreenFragment$ScreenLifecycleEvent r1 = com.swmansion.rnscreens.ScreenFragment.ScreenLifecycleEvent.Disappear     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.ScreenFragment.WhenMappings.<clinit>():void");
        }
    }

    public static /* synthetic */ void getScreen$annotations() {
    }

    @JvmStatic
    protected static final View recycleView(View view) {
        return Companion.recycleView(view);
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/swmansion/rnscreens/ScreenFragment$ScreenLifecycleEvent;", "", "(Ljava/lang/String;I)V", "Appear", "WillAppear", "Disappear", "WillDisappear", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ScreenFragment.kt */
    public enum ScreenLifecycleEvent {
        Appear,
        WillAppear,
        Disappear,
        WillDisappear;

        public static EnumEntries<ScreenLifecycleEvent> getEntries() {
            return $ENTRIES;
        }

        static {
            ScreenLifecycleEvent[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }

    public Fragment getFragment() {
        return this;
    }

    public Screen getScreen() {
        Screen screen2 = this.screen;
        if (screen2 != null) {
            return screen2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("screen");
        return null;
    }

    public void setScreen(Screen screen2) {
        Intrinsics.checkNotNullParameter(screen2, "<set-?>");
        this.screen = screen2;
    }

    public ScreenFragment() {
        throw new IllegalStateException("Screen fragments should never be restored. Follow instructions from https://github.com/software-mansion/react-native-screens/issues/17#issuecomment-424704067 to properly configure your main activity.");
    }

    public ScreenFragment(Screen screen2) {
        Intrinsics.checkNotNullParameter(screen2, "screenView");
        setScreen(screen2);
    }

    public void onResume() {
        super.onResume();
        if (this.shouldUpdateOnResume) {
            this.shouldUpdateOnResume = false;
            ScreenWindowTraits.INSTANCE.trySetWindowTraits$react_native_screens_release(getScreen(), tryGetActivity(), tryGetContext());
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ScreensFrameLayout screensFrameLayout;
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        getScreen().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        Context context = getContext();
        if (context != null) {
            screensFrameLayout = new ScreensFrameLayout(context);
            screensFrameLayout.addView(recycleView(getScreen()));
        } else {
            screensFrameLayout = null;
        }
        return screensFrameLayout;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/swmansion/rnscreens/ScreenFragment$ScreensFrameLayout;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "clearFocus", "", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ScreenFragment.kt */
    private static final class ScreensFrameLayout extends FrameLayout {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ScreensFrameLayout(Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
        }

        public void clearFocus() {
            if (getVisibility() != 4) {
                super.clearFocus();
            }
        }
    }

    public void onContainerUpdate() {
        updateWindowTraits();
    }

    private final void updateWindowTraits() {
        Activity activity = getActivity();
        if (activity == null) {
            this.shouldUpdateOnResume = true;
        } else {
            ScreenWindowTraits.INSTANCE.trySetWindowTraits$react_native_screens_release(getScreen(), activity, tryGetContext());
        }
    }

    public Activity tryGetActivity() {
        Fragment fragment;
        FragmentActivity activity;
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            return activity2;
        }
        Context context = getScreen().getContext();
        if (context instanceof ReactContext) {
            ReactContext reactContext = (ReactContext) context;
            if (reactContext.getCurrentActivity() != null) {
                return reactContext.getCurrentActivity();
            }
        }
        for (ViewParent container = getScreen().getContainer(); container != null; container = container.getParent()) {
            if ((container instanceof Screen) && (fragment = ((Screen) container).getFragment()) != null && (activity = fragment.getActivity()) != null) {
                return activity;
            }
        }
        return null;
    }

    public ReactContext tryGetContext() {
        if (getContext() instanceof ReactContext) {
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            return (ReactContext) context;
        } else if (getScreen().getContext() instanceof ReactContext) {
            Context context2 = getScreen().getContext();
            Intrinsics.checkNotNull(context2, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            return (ReactContext) context2;
        } else {
            for (ViewParent container = getScreen().getContainer(); container != null; container = container.getParent()) {
                if (container instanceof Screen) {
                    Screen screen2 = (Screen) container;
                    if (screen2.getContext() instanceof ReactContext) {
                        Context context3 = screen2.getContext();
                        Intrinsics.checkNotNull(context3, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                        return (ReactContext) context3;
                    }
                }
            }
            return null;
        }
    }

    public List<ScreenContainer> getChildScreenContainers() {
        return this.mChildScreenContainers;
    }

    public boolean canDispatchLifecycleEvent(ScreenLifecycleEvent screenLifecycleEvent) {
        Intrinsics.checkNotNullParameter(screenLifecycleEvent, NotificationCompat.CATEGORY_EVENT);
        int i = WhenMappings.$EnumSwitchMapping$0[screenLifecycleEvent.ordinal()];
        if (i == 1) {
            return this.canDispatchWillAppear;
        }
        if (i == 2) {
            return this.canDispatchAppear;
        }
        if (i != 3) {
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            } else if (!this.canDispatchAppear) {
                return true;
            }
        } else if (!this.canDispatchWillAppear) {
            return true;
        }
        return false;
    }

    public void updateLastEventDispatched(ScreenLifecycleEvent screenLifecycleEvent) {
        Intrinsics.checkNotNullParameter(screenLifecycleEvent, NotificationCompat.CATEGORY_EVENT);
        int i = WhenMappings.$EnumSwitchMapping$0[screenLifecycleEvent.ordinal()];
        if (i == 1) {
            this.canDispatchWillAppear = false;
        } else if (i == 2) {
            this.canDispatchAppear = false;
        } else if (i == 3) {
            this.canDispatchWillAppear = true;
        } else if (i == 4) {
            this.canDispatchAppear = true;
        }
    }

    private final void dispatchOnWillAppear() {
        dispatchLifecycleEvent(ScreenLifecycleEvent.WillAppear, this);
        dispatchTransitionProgressEvent(0.0f, false);
    }

    private final void dispatchOnAppear() {
        dispatchLifecycleEvent(ScreenLifecycleEvent.Appear, this);
        dispatchTransitionProgressEvent(1.0f, false);
    }

    private final void dispatchOnWillDisappear() {
        dispatchLifecycleEvent(ScreenLifecycleEvent.WillDisappear, this);
        dispatchTransitionProgressEvent(0.0f, true);
    }

    private final void dispatchOnDisappear() {
        dispatchLifecycleEvent(ScreenLifecycleEvent.Disappear, this);
        dispatchTransitionProgressEvent(1.0f, true);
    }

    public void dispatchLifecycleEvent(ScreenLifecycleEvent screenLifecycleEvent, ScreenFragmentWrapper screenFragmentWrapper) {
        Event event;
        Intrinsics.checkNotNullParameter(screenLifecycleEvent, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(screenFragmentWrapper, "fragmentWrapper");
        Fragment fragment = screenFragmentWrapper.getFragment();
        if (fragment instanceof ScreenStackFragment) {
            ScreenStackFragment screenStackFragment = (ScreenStackFragment) fragment;
            if (screenStackFragment.canDispatchLifecycleEvent(screenLifecycleEvent)) {
                Screen screen2 = screenStackFragment.getScreen();
                screenFragmentWrapper.updateLastEventDispatched(screenLifecycleEvent);
                int surfaceId = UIManagerHelper.getSurfaceId((View) screen2);
                int i = WhenMappings.$EnumSwitchMapping$0[screenLifecycleEvent.ordinal()];
                if (i == 1) {
                    event = new ScreenWillAppearEvent(surfaceId, screen2.getId());
                } else if (i == 2) {
                    event = new ScreenAppearEvent(surfaceId, screen2.getId());
                } else if (i == 3) {
                    event = new ScreenWillDisappearEvent(surfaceId, screen2.getId());
                } else if (i == 4) {
                    event = new ScreenDisappearEvent(surfaceId, screen2.getId());
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                Context context = getScreen().getContext();
                Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getScreen().getId());
                if (eventDispatcherForReactTag != null) {
                    eventDispatcherForReactTag.dispatchEvent(event);
                }
                screenFragmentWrapper.dispatchLifecycleEventInChildContainers(screenLifecycleEvent);
            }
        }
    }

    public void dispatchLifecycleEventInChildContainers(ScreenLifecycleEvent screenLifecycleEvent) {
        ScreenFragmentWrapper fragmentWrapper;
        Intrinsics.checkNotNullParameter(screenLifecycleEvent, NotificationCompat.CATEGORY_EVENT);
        Collection arrayList = new ArrayList();
        for (Object next : this.mChildScreenContainers) {
            if (((ScreenContainer) next).getScreenCount() > 0) {
                arrayList.add(next);
            }
        }
        for (ScreenContainer topScreen : (List) arrayList) {
            Screen topScreen2 = topScreen.getTopScreen();
            if (!(topScreen2 == null || (fragmentWrapper = topScreen2.getFragmentWrapper()) == null)) {
                dispatchLifecycleEvent(screenLifecycleEvent, fragmentWrapper);
            }
        }
    }

    public void dispatchHeaderBackButtonClickedEvent() {
        Context context = getScreen().getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        ReactContext reactContext = (ReactContext) context;
        int surfaceId = UIManagerHelper.getSurfaceId((Context) reactContext);
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, getScreen().getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new HeaderBackButtonClickedEvent(surfaceId, getScreen().getId()));
        }
    }

    public void dispatchTransitionProgressEvent(float f, boolean z) {
        if (this instanceof ScreenStackFragment) {
            int i = 1;
            boolean z2 = false;
            if (!(this.mProgress == f)) {
                float max = Math.max(0.0f, Math.min(1.0f, f));
                this.mProgress = max;
                if (!(max == 0.0f)) {
                    if (max != 1.0f) {
                        i = 0;
                    }
                    i = i != 0 ? 2 : 3;
                }
                short s = (short) i;
                ScreenContainer container = getScreen().getContainer();
                if (container instanceof ScreenStack) {
                    z2 = ((ScreenStack) container).getGoingForward();
                }
                boolean z3 = z2;
                Context context = getScreen().getContext();
                Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
                ReactContext reactContext = (ReactContext) context;
                EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, getScreen().getId());
                if (eventDispatcherForReactTag != null) {
                    eventDispatcherForReactTag.dispatchEvent(new ScreenTransitionProgressEvent(UIManagerHelper.getSurfaceId((Context) reactContext), getScreen().getId(), this.mProgress, z, z3, s));
                }
            }
        }
    }

    public void addChildScreenContainer(ScreenContainer screenContainer) {
        Intrinsics.checkNotNullParameter(screenContainer, TtmlNode.RUBY_CONTAINER);
        this.mChildScreenContainers.add(screenContainer);
    }

    public void removeChildScreenContainer(ScreenContainer screenContainer) {
        Intrinsics.checkNotNullParameter(screenContainer, TtmlNode.RUBY_CONTAINER);
        this.mChildScreenContainers.remove(screenContainer);
    }

    public void onViewAnimationStart() {
        dispatchViewAnimationEvent(false);
    }

    public void onViewAnimationEnd() {
        dispatchViewAnimationEvent(true);
    }

    private final void dispatchViewAnimationEvent(boolean z) {
        this.isTransitioning = !z;
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (!(parentFragment instanceof ScreenFragment) || ((ScreenFragment) parentFragment).isTransitioning)) {
            return;
        }
        if (isResumed()) {
            UiThreadUtil.runOnUiThread(new ScreenFragment$$ExternalSyntheticLambda0(z, this));
        } else if (z) {
            dispatchOnDisappear();
        } else {
            dispatchOnWillDisappear();
        }
    }

    /* access modifiers changed from: private */
    public static final void dispatchViewAnimationEvent$lambda$8(boolean z, ScreenFragment screenFragment) {
        Intrinsics.checkNotNullParameter(screenFragment, "this$0");
        if (z) {
            screenFragment.dispatchOnAppear();
        } else {
            screenFragment.dispatchOnWillAppear();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        ScreenContainer container = getScreen().getContainer();
        if (container == null || !container.hasScreen(this)) {
            Context context = getScreen().getContext();
            if (context instanceof ReactContext) {
                int surfaceId = UIManagerHelper.getSurfaceId(context);
                EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getScreen().getId());
                if (eventDispatcherForReactTag != null) {
                    eventDispatcherForReactTag.dispatchEvent(new ScreenDismissedEvent(surfaceId, getScreen().getId()));
                }
            }
        }
        this.mChildScreenContainers.clear();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0005¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/ScreenFragment$Companion;", "", "()V", "recycleView", "Landroid/view/View;", "view", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ScreenFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: protected */
        @JvmStatic
        public final View recycleView(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            ViewParent parent = view.getParent();
            if (parent != null) {
                ViewGroup viewGroup = (ViewGroup) parent;
                viewGroup.endViewTransition(view);
                viewGroup.removeView(view);
            }
            view.setVisibility(0);
            return view;
        }
    }
}
