package com.swmansion.gesturehandler.react;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.scroll.ReactHorizontalScrollView;
import com.facebook.react.views.scroll.ReactScrollView;
import com.facebook.react.views.view.ReactViewGroup;
import com.swmansion.gesturehandler.core.PointerEventsConfig;
import com.swmansion.gesturehandler.core.ViewConfigurationHelper;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016¨\u0006\u000e"}, d2 = {"Lcom/swmansion/gesturehandler/react/RNViewConfigurationHelper;", "Lcom/swmansion/gesturehandler/core/ViewConfigurationHelper;", "()V", "getChildInDrawingOrderAtIndex", "Landroid/view/View;", "parent", "Landroid/view/ViewGroup;", "index", "", "getPointerEventsConfigForView", "Lcom/swmansion/gesturehandler/core/PointerEventsConfig;", "view", "isViewClippingChildren", "", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RNViewConfigurationHelper.kt */
public final class RNViewConfigurationHelper implements ViewConfigurationHelper {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RNViewConfigurationHelper.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.facebook.react.uimanager.PointerEvents[] r0 = com.facebook.react.uimanager.PointerEvents.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.uimanager.PointerEvents r1 = com.facebook.react.uimanager.PointerEvents.BOX_ONLY     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.uimanager.PointerEvents r1 = com.facebook.react.uimanager.PointerEvents.BOX_NONE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.uimanager.PointerEvents r1 = com.facebook.react.uimanager.PointerEvents.NONE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.react.uimanager.PointerEvents r1 = com.facebook.react.uimanager.PointerEvents.AUTO     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.react.RNViewConfigurationHelper.WhenMappings.<clinit>():void");
        }
    }

    public PointerEventsConfig getPointerEventsConfigForView(View view) {
        PointerEvents pointerEvents;
        Intrinsics.checkNotNullParameter(view, "view");
        if (view instanceof ReactPointerEventsView) {
            pointerEvents = ((ReactPointerEventsView) view).getPointerEvents();
            Intrinsics.checkNotNull(pointerEvents);
        } else {
            pointerEvents = PointerEvents.AUTO;
        }
        if (!view.isEnabled()) {
            if (pointerEvents == PointerEvents.AUTO) {
                return PointerEventsConfig.BOX_NONE;
            }
            if (pointerEvents == PointerEvents.BOX_ONLY) {
                return PointerEventsConfig.NONE;
            }
        }
        int i = WhenMappings.$EnumSwitchMapping$0[pointerEvents.ordinal()];
        if (i == 1) {
            return PointerEventsConfig.BOX_ONLY;
        }
        if (i == 2) {
            return PointerEventsConfig.BOX_NONE;
        }
        if (i == 3) {
            return PointerEventsConfig.NONE;
        }
        if (i == 4) {
            return PointerEventsConfig.AUTO;
        }
        throw new NoWhenBranchMatchedException();
    }

    public View getChildInDrawingOrderAtIndex(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        if (viewGroup instanceof ReactViewGroup) {
            View childAt = viewGroup.getChildAt(((ReactViewGroup) viewGroup).getZIndexMappedChildIndex(i));
            Intrinsics.checkNotNull(childAt);
            return childAt;
        }
        View childAt2 = viewGroup.getChildAt(i);
        Intrinsics.checkNotNullExpressionValue(childAt2, "getChildAt(...)");
        return childAt2;
    }

    public boolean isViewClippingChildren(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "view");
        if (viewGroup.getClipChildren()) {
            return true;
        }
        if (viewGroup instanceof ReactScrollView) {
            if (!Intrinsics.areEqual((Object) ((ReactScrollView) viewGroup).getOverflow(), (Object) ViewProps.VISIBLE)) {
                return true;
            }
        } else if (viewGroup instanceof ReactHorizontalScrollView) {
            if (!Intrinsics.areEqual((Object) ((ReactHorizontalScrollView) viewGroup).getOverflow(), (Object) ViewProps.VISIBLE)) {
                return true;
            }
        } else if (viewGroup instanceof ReactViewGroup) {
            return Intrinsics.areEqual((Object) ((ReactViewGroup) viewGroup).getOverflow(), (Object) "hidden");
        }
        return false;
    }
}
