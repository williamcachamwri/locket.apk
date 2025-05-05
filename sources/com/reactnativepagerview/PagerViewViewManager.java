package com.reactnativepagerview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.reactnativepagerview.event.PageScrollEvent;
import com.reactnativepagerview.event.PageScrollStateChangedEvent;
import com.reactnativepagerview.event.PageSelectedEvent;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 ,2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0002H\u0016J \u0010\u0013\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u00160\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J$\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00022\b\u0010\u001c\u001a\u0004\u0018\u00010\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0002H\u0016J\u0018\u0010 \u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\nH\u0016J\u0018\u0010\"\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0019\u0010#\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\fH\u0002J\u0018\u0010%\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\fH\u0007J\u0018\u0010&\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0015H\u0007J\u0018\u0010'\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0015H\u0007J\u0018\u0010(\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0015H\u0007J\u0018\u0010)\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010*\u001a\u00020\fH\u0007J\u0018\u0010+\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0019H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/reactnativepagerview/PagerViewViewManager;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Lcom/reactnativepagerview/NestedScrollableHost;", "()V", "eventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "addView", "", "host", "child", "Landroid/view/View;", "index", "", "createViewInstance", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getChildAt", "parent", "getChildCount", "getExportedCustomDirectEventTypeConstants", "", "", "", "getName", "needsCustomLayoutForChildren", "", "receiveCommand", "root", "commandId", "args", "Lcom/facebook/react/bridge/ReadableArray;", "removeAllViews", "removeView", "view", "removeViewAt", "set", "value", "setInitialPage", "setLayoutDirection", "setOrientation", "setOverScrollMode", "setPageMargin", "margin", "setScrollEnabled", "Companion", "react-native-pager-view_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PagerViewViewManager.kt */
public final class PagerViewViewManager extends ViewGroupManager<NestedScrollableHost> {
    private static final String COMMAND_SET_PAGE = "setPage";
    private static final String COMMAND_SET_PAGE_WITHOUT_ANIMATION = "setPageWithoutAnimation";
    private static final String COMMAND_SET_SCROLL_ENABLED = "setScrollEnabledImperatively";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public EventDispatcher eventDispatcher;

    public String getName() {
        return PagerViewViewManagerImpl.NAME;
    }

    /* access modifiers changed from: protected */
    public NestedScrollableHost createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Context context = themedReactContext;
        NestedScrollableHost nestedScrollableHost = new NestedScrollableHost(context);
        nestedScrollableHost.setId(View.generateViewId());
        nestedScrollableHost.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        nestedScrollableHost.setSaveEnabled(false);
        ViewPager2 viewPager2 = new ViewPager2(context);
        viewPager2.setAdapter(new ViewPagerAdapter());
        viewPager2.setSaveEnabled(false);
        NativeModule nativeModule = themedReactContext.getNativeModule(UIManagerModule.class);
        Intrinsics.checkNotNull(nativeModule);
        EventDispatcher eventDispatcher2 = ((UIManagerModule) nativeModule).getEventDispatcher();
        Intrinsics.checkNotNullExpressionValue(eventDispatcher2, "getEventDispatcher(...)");
        this.eventDispatcher = eventDispatcher2;
        viewPager2.post(new PagerViewViewManager$$ExternalSyntheticLambda0(viewPager2, this, nestedScrollableHost));
        nestedScrollableHost.addView(viewPager2);
        return nestedScrollableHost;
    }

    /* access modifiers changed from: private */
    public static final void createViewInstance$lambda$0(ViewPager2 viewPager2, PagerViewViewManager pagerViewViewManager, NestedScrollableHost nestedScrollableHost) {
        Intrinsics.checkNotNullParameter(viewPager2, "$vp");
        Intrinsics.checkNotNullParameter(pagerViewViewManager, "this$0");
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "$host");
        viewPager2.registerOnPageChangeCallback(new PagerViewViewManager$createViewInstance$1$1(pagerViewViewManager, nestedScrollableHost));
        EventDispatcher eventDispatcher2 = pagerViewViewManager.eventDispatcher;
        if (eventDispatcher2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventDispatcher");
            eventDispatcher2 = null;
        }
        eventDispatcher2.dispatchEvent(new PageSelectedEvent(nestedScrollableHost.getId(), viewPager2.getCurrentItem()));
    }

    public void addView(NestedScrollableHost nestedScrollableHost, View view, int i) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "host");
        PagerViewViewManagerImpl.INSTANCE.addView(nestedScrollableHost, view, i);
    }

    public int getChildCount(NestedScrollableHost nestedScrollableHost) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "parent");
        return PagerViewViewManagerImpl.INSTANCE.getChildCount(nestedScrollableHost);
    }

    public View getChildAt(NestedScrollableHost nestedScrollableHost, int i) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "parent");
        return PagerViewViewManagerImpl.INSTANCE.getChildAt(nestedScrollableHost, i);
    }

    public void removeView(NestedScrollableHost nestedScrollableHost, View view) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "parent");
        Intrinsics.checkNotNullParameter(view, "view");
        PagerViewViewManagerImpl.INSTANCE.removeView(nestedScrollableHost, view);
    }

    public void removeAllViews(NestedScrollableHost nestedScrollableHost) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "parent");
        PagerViewViewManagerImpl.INSTANCE.removeAllViews(nestedScrollableHost);
    }

    public void removeViewAt(NestedScrollableHost nestedScrollableHost, int i) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "parent");
        PagerViewViewManagerImpl.INSTANCE.removeViewAt(nestedScrollableHost, i);
    }

    public boolean needsCustomLayoutForChildren() {
        return PagerViewViewManagerImpl.INSTANCE.needsCustomLayoutForChildren();
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public final void setScrollEnabled(NestedScrollableHost nestedScrollableHost, boolean z) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "host");
        PagerViewViewManagerImpl.INSTANCE.setScrollEnabled(nestedScrollableHost, z);
    }

    @ReactProp(defaultInt = 0, name = "initialPage")
    public final void setInitialPage(NestedScrollableHost nestedScrollableHost, int i) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "host");
        PagerViewViewManagerImpl.INSTANCE.setInitialPage(nestedScrollableHost, i);
    }

    @ReactProp(name = "orientation")
    public final void setOrientation(NestedScrollableHost nestedScrollableHost, String str) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "host");
        Intrinsics.checkNotNullParameter(str, "value");
        PagerViewViewManagerImpl.INSTANCE.setOrientation(nestedScrollableHost, str);
    }

    @ReactProp(defaultInt = -1, name = "offscreenPageLimit")
    public final void set(NestedScrollableHost nestedScrollableHost, int i) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "host");
        PagerViewViewManagerImpl.INSTANCE.setOffscreenPageLimit(nestedScrollableHost, i);
    }

    @ReactProp(name = "overScrollMode")
    public final void setOverScrollMode(NestedScrollableHost nestedScrollableHost, String str) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "host");
        Intrinsics.checkNotNullParameter(str, "value");
        PagerViewViewManagerImpl.INSTANCE.setOverScrollMode(nestedScrollableHost, str);
    }

    @ReactProp(name = "layoutDirection")
    public final void setLayoutDirection(NestedScrollableHost nestedScrollableHost, String str) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "host");
        Intrinsics.checkNotNullParameter(str, "value");
        PagerViewViewManagerImpl.INSTANCE.setLayoutDirection(nestedScrollableHost, str);
    }

    public Map<String, Map<String, String>> getExportedCustomDirectEventTypeConstants() {
        Map<String, Map<String, String>> of = MapBuilder.of(PageScrollEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageScroll"), PageScrollStateChangedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageScrollStateChanged"), PageSelectedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageSelected"));
        Intrinsics.checkNotNullExpressionValue(of, "of(...)");
        return of;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
        if (r7.equals(COMMAND_SET_PAGE) != false) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005f, code lost:
        if (r7.equals(COMMAND_SET_PAGE_WITHOUT_ANIMATION) != false) goto L_0x0061;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void receiveCommand(com.reactnativepagerview.NestedScrollableHost r6, java.lang.String r7, com.facebook.react.bridge.ReadableArray r8) {
        /*
            r5 = this;
            java.lang.String r0 = "root"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            r0 = r6
            android.view.View r0 = (android.view.View) r0
            super.receiveCommand(r0, (java.lang.String) r7, (com.facebook.react.bridge.ReadableArray) r8)
            com.reactnativepagerview.PagerViewViewManagerImpl r0 = com.reactnativepagerview.PagerViewViewManagerImpl.INSTANCE
            androidx.viewpager2.widget.ViewPager2 r6 = r0.getViewPager(r6)
            com.facebook.infer.annotation.Assertions.assertNotNull(r6)
            com.facebook.infer.annotation.Assertions.assertNotNull(r8)
            androidx.recyclerview.widget.RecyclerView$Adapter r0 = r6.getAdapter()
            if (r0 == 0) goto L_0x0026
            int r0 = r0.getItemCount()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x0027
        L_0x0026:
            r0 = 0
        L_0x0027:
            if (r7 == 0) goto L_0x0085
            int r1 = r7.hashCode()
            r2 = -445763635(0xffffffffe56e2fcd, float:-7.030031E22)
            java.lang.String r3 = "setPage"
            r4 = 0
            if (r1 == r2) goto L_0x0059
            r2 = 1747675147(0x682b680b, float:3.2377757E24)
            if (r1 == r2) goto L_0x0046
            r2 = 1984860689(0x764e9211, float:1.0474372E33)
            if (r1 != r2) goto L_0x0085
            boolean r1 = r7.equals(r3)
            if (r1 == 0) goto L_0x0085
            goto L_0x0061
        L_0x0046:
            java.lang.String r0 = "setScrollEnabledImperatively"
            boolean r0 = r7.equals(r0)
            if (r0 == 0) goto L_0x0085
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            boolean r7 = r8.getBoolean(r4)
            r6.setUserInputEnabled(r7)
            goto L_0x0084
        L_0x0059:
            java.lang.String r1 = "setPageWithoutAnimation"
            boolean r1 = r7.equals(r1)
            if (r1 == 0) goto L_0x0085
        L_0x0061:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            int r8 = r8.getInt(r4)
            if (r0 == 0) goto L_0x0079
            int r1 = r0.intValue()
            if (r1 <= 0) goto L_0x0079
            if (r8 < 0) goto L_0x0079
            int r0 = r0.intValue()
            if (r8 >= r0) goto L_0x0079
            r4 = 1
        L_0x0079:
            if (r4 == 0) goto L_0x0084
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r3)
            com.reactnativepagerview.PagerViewViewManagerImpl r0 = com.reactnativepagerview.PagerViewViewManagerImpl.INSTANCE
            r0.setCurrentItem(r6, r8, r7)
        L_0x0084:
            return
        L_0x0085:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            kotlin.jvm.internal.StringCompanionObject r8 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.Class r8 = r5.getClass()
            java.lang.String r8 = r8.getSimpleName()
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r8}
            r8 = 2
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r8)
            java.lang.String r8 = "Unsupported command %d received by %s."
            java.lang.String r7 = java.lang.String.format(r8, r7)
            java.lang.String r8 = "format(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativepagerview.PagerViewViewManager.receiveCommand(com.reactnativepagerview.NestedScrollableHost, java.lang.String, com.facebook.react.bridge.ReadableArray):void");
    }

    @ReactProp(defaultInt = 0, name = "pageMargin")
    public final void setPageMargin(NestedScrollableHost nestedScrollableHost, int i) {
        Intrinsics.checkNotNullParameter(nestedScrollableHost, "host");
        PagerViewViewManagerImpl.INSTANCE.setPageMargin(nestedScrollableHost, i);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/reactnativepagerview/PagerViewViewManager$Companion;", "", "()V", "COMMAND_SET_PAGE", "", "COMMAND_SET_PAGE_WITHOUT_ANIMATION", "COMMAND_SET_SCROLL_ENABLED", "react-native-pager-view_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PagerViewViewManager.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
