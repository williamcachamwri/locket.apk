package com.adsbynimbus.render.internal;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.render.NimbusAdView;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0006\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0007H\u0000\u001a>\u0010\u0013\u001a\u00020\u0012*\u00020\u00072\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00020\u00152\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00020\u0015H@¢\u0006\u0002\u0010\u0018\u001a2\u0010\u0019\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00172\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u0002H\u0001\u001a<\u0010\u001d\u001a\u00020\u0006*\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u00022\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00020\u00152\b\b\u0002\u0010\u001f\u001a\u00020\u00172\b\b\u0002\u0010 \u001a\u00020\u0002\u001a\u001c\u0010!\u001a\u00020\u0006*\u00020\r2\u0006\u0010\"\u001a\u00020\u00022\b\b\u0002\u0010 \u001a\u00020\u0002\u001a\f\u0010#\u001a\u00020\u0012*\u00020\u0007H\u0000\u001a\u0016\u0010$\u001a\u00020\u0012*\u00020\u00072\b\b\u0002\u0010%\u001a\u00020&H\u0001\u001a\u0012\u0010'\u001a\u00020\u0012*\u00020\u00022\u0006\u0010(\u001a\u00020\u0002\u001a6\u0010)\u001a\u00020\u0012*\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u00022\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00020\u0015H@¢\u0006\u0002\u0010+\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\b\"\u0018\u0010\t\u001a\u00020\u0006*\u0004\u0018\u00010\n8Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\u000b\"\u0016\u0010\f\u001a\u00020\u0006*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\f\u0010\u000e\"\u0016\u0010\u000f\u001a\u00020\u0006*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e¨\u0006,"}, d2 = {"area", "", "Landroid/graphics/Rect;", "getArea", "(Landroid/graphics/Rect;)I", "isExposedOnScreen", "", "Lcom/adsbynimbus/render/NimbusAdView;", "(Lcom/adsbynimbus/render/NimbusAdView;)Z", "isNotViewable", "Landroid/graphics/drawable/Drawable;", "(Landroid/graphics/drawable/Drawable;)Z", "isNotVisible", "Landroid/view/View;", "(Landroid/view/View;)Z", "willNotDraw", "getWillNotDraw", "attachListeners", "", "calculateExposure", "obstructingViews", "", "viewGroups", "Landroid/view/ViewGroup;", "(Lcom/adsbynimbus/render/NimbusAdView;Ljava/util/Map;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exposedPercent", "container", "obstructions", "tmpRect", "findObstructions", "exposureRect", "parent", "testRect", "overlaps", "visibleRect", "removeListeners", "scheduleExposureCheck", "timeSinceLastReport", "", "slice", "other", "updateExposure", "exposedRect", "(Lcom/adsbynimbus/render/NimbusAdView;ILandroid/graphics/Rect;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "render_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExposureTracker.kt */
public final class ExposureTrackerKt {
    public static final int getArea(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        if (rect.isEmpty()) {
            return 0;
        }
        return rect.height() * rect.width();
    }

    public static final void slice(Rect rect, Rect rect2) {
        Intrinsics.checkNotNullParameter(rect, "<this>");
        Intrinsics.checkNotNullParameter(rect2, "other");
        if (rect2.height() >= rect.height()) {
            if (rect2.left <= rect.left && rect2.right >= rect.left) {
                rect.left = rect2.right;
            }
            if (rect2.right >= rect.right && rect2.left <= rect.right) {
                rect.right = rect2.left;
            }
        }
        if (rect2.width() >= rect.width()) {
            if (rect2.top <= rect.top && rect2.bottom >= rect.top) {
                rect.top = rect2.bottom;
            }
            if (rect2.bottom >= rect.bottom && rect2.top <= rect.bottom) {
                rect.bottom = rect2.top;
            }
        }
    }

    public static /* synthetic */ int exposedPercent$default(Rect rect, ViewGroup viewGroup, Map map, Rect rect2, int i, Object obj) {
        if ((i & 4) != 0) {
            rect2 = new Rect();
        }
        return exposedPercent(rect, viewGroup, map, rect2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x002d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int exposedPercent(android.graphics.Rect r6, android.view.ViewGroup r7, java.util.Map<android.view.View, android.graphics.Rect> r8, android.graphics.Rect r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "container"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "obstructions"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "tmpRect"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            boolean r0 = r6.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x001e
            goto L_0x0126
        L_0x001e:
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.util.Map r0 = (java.util.Map) r0
            java.util.Set r8 = r8.entrySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x002d:
            boolean r2 = r8.hasNext()
            if (r2 == 0) goto L_0x0079
            java.lang.Object r2 = r8.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            android.view.View r3 = (android.view.View) r3
            java.lang.Object r4 = r2.getValue()
            android.graphics.Rect r4 = (android.graphics.Rect) r4
            boolean r5 = r3.isLaidOut()
            if (r5 == 0) goto L_0x006a
            kotlin.Result$Companion r5 = kotlin.Result.Companion     // Catch:{ all -> 0x0057 }
            r7.offsetDescendantRectToMyCoords(r3, r4)     // Catch:{ all -> 0x0057 }
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0057 }
            java.lang.Object r3 = kotlin.Result.m2444constructorimpl(r3)     // Catch:{ all -> 0x0057 }
            goto L_0x0062
        L_0x0057:
            r3 = move-exception
            kotlin.Result$Companion r4 = kotlin.Result.Companion
            java.lang.Object r3 = kotlin.ResultKt.createFailure(r3)
            java.lang.Object r3 = kotlin.Result.m2444constructorimpl(r3)
        L_0x0062:
            boolean r3 = kotlin.Result.m2451isSuccessimpl(r3)
            if (r3 == 0) goto L_0x006a
            r3 = 1
            goto L_0x006b
        L_0x006a:
            r3 = r1
        L_0x006b:
            if (r3 == 0) goto L_0x002d
            java.lang.Object r3 = r2.getKey()
            java.lang.Object r2 = r2.getValue()
            r0.put(r3, r2)
            goto L_0x002d
        L_0x0079:
            java.util.Collection r7 = r0.values()
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            com.adsbynimbus.render.internal.ExposureTrackerKt$exposedPercent$$inlined$sortedBy$1 r8 = new com.adsbynimbus.render.internal.ExposureTrackerKt$exposedPercent$$inlined$sortedBy$1
            r8.<init>()
            java.util.Comparator r8 = (java.util.Comparator) r8
            java.util.List r7 = kotlin.collections.CollectionsKt.sortedWith(r7, r8)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.Iterator r8 = r7.iterator()
        L_0x0090:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L_0x00a0
            java.lang.Object r0 = r8.next()
            android.graphics.Rect r0 = (android.graphics.Rect) r0
            slice(r6, r0)
            goto L_0x0090
        L_0x00a0:
            java.util.Iterator r7 = r7.iterator()
            boolean r8 = r7.hasNext()
            if (r8 != 0) goto L_0x00af
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()
            goto L_0x00fd
        L_0x00af:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r0 = r7.next()
        L_0x00ba:
            boolean r2 = r7.hasNext()
            if (r2 == 0) goto L_0x00fc
            java.lang.Object r2 = r7.next()
            r3 = r2
            android.graphics.Rect r3 = (android.graphics.Rect) r3
            android.graphics.Rect r0 = (android.graphics.Rect) r0
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x00d1
            r4 = r1
            goto L_0x00da
        L_0x00d1:
            int r4 = r3.width()
            int r5 = r3.height()
            int r4 = r4 * r5
        L_0x00da:
            boolean r0 = r9.setIntersect(r0, r3)
            if (r0 == 0) goto L_0x00f1
            boolean r0 = r9.isEmpty()
            if (r0 == 0) goto L_0x00e7
            goto L_0x00f1
        L_0x00e7:
            int r0 = r9.width()
            int r3 = r9.height()
            int r0 = r0 * r3
            goto L_0x00f2
        L_0x00f1:
            r0 = r1
        L_0x00f2:
            int r4 = r4 - r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r4)
            r8.add(r0)
            r0 = r2
            goto L_0x00ba
        L_0x00fc:
            r7 = r8
        L_0x00fd:
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            boolean r8 = r6.isEmpty()
            if (r8 == 0) goto L_0x0106
            goto L_0x0110
        L_0x0106:
            int r8 = r6.width()
            int r6 = r6.height()
            int r1 = r8 * r6
        L_0x0110:
            java.util.Iterator r6 = r7.iterator()
        L_0x0114:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0126
            java.lang.Object r7 = r6.next()
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            int r1 = r1 - r7
            goto L_0x0114
        L_0x0126:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.internal.ExposureTrackerKt.exposedPercent(android.graphics.Rect, android.view.ViewGroup, java.util.Map, android.graphics.Rect):int");
    }

    public static final boolean isNotViewable(Drawable drawable) {
        return drawable == null || !drawable.isVisible() || drawable.getAlpha() <= 0;
    }

    public static final boolean isNotVisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.getVisibility() != 0 || view.getAlpha() <= 0.0f;
    }

    public static final boolean getWillNotDraw(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (!view.willNotDraw()) {
            return false;
        }
        Drawable background = view.getBackground();
        if (!(background == null || !background.isVisible() || background.getAlpha() <= 0)) {
            return false;
        }
        if (Components.isApi23()) {
            Drawable foreground = view.getForeground();
            if (!(foreground == null || !foreground.isVisible() || foreground.getAlpha() <= 0)) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean overlaps$default(View view, Rect rect, Rect rect2, int i, Object obj) {
        if ((i & 2) != 0) {
            rect2 = new Rect();
        }
        return overlaps(view, rect, rect2);
    }

    public static final boolean overlaps(View view, Rect rect, Rect rect2) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(rect, "visibleRect");
        Intrinsics.checkNotNullParameter(rect2, "testRect");
        view.getHitRect(rect2);
        Unit unit = Unit.INSTANCE;
        return rect2.intersect(rect);
    }

    public static /* synthetic */ boolean findObstructions$default(ViewGroup viewGroup, Rect rect, Map map, ViewGroup viewGroup2, Rect rect2, int i, Object obj) {
        if ((i & 2) != 0) {
            map = new LinkedHashMap();
        }
        if ((i & 4) != 0) {
            ViewParent parent = viewGroup.getParent();
            Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
            viewGroup2 = (ViewGroup) parent;
        }
        if ((i & 8) != 0) {
            rect2 = new Rect();
        }
        return findObstructions(viewGroup, rect, map, viewGroup2, rect2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a6, code lost:
        if (r4 == false) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b8, code lost:
        if (r4 == null) goto L_0x00ba;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0055 A[Catch:{ all -> 0x00de }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008a A[Catch:{ all -> 0x00de }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ad A[Catch:{ all -> 0x00de }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00c9 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00c9 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean findObstructions(android.view.ViewGroup r7, android.graphics.Rect r8, java.util.Map<android.view.View, android.graphics.Rect> r9, android.view.ViewGroup r10, android.graphics.Rect r11) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "exposureRect"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "obstructions"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "parent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "testRect"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            r0 = 0
            kotlin.Result$Companion r1 = kotlin.Result.Companion     // Catch:{ all -> 0x00de }
            r1 = r7
            android.view.View r1 = (android.view.View) r1     // Catch:{ all -> 0x00de }
            r10.offsetRectIntoDescendantCoords(r1, r8)     // Catch:{ all -> 0x00de }
            int r1 = r7.getChildCount()     // Catch:{ all -> 0x00de }
            r2 = r0
        L_0x0028:
            if (r2 >= r1) goto L_0x00cd
            android.view.View r3 = r7.getChildAt(r2)     // Catch:{ all -> 0x00de }
            java.lang.String r4 = "it"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch:{ all -> 0x00de }
            boolean r4 = overlaps(r3, r8, r11)     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x003a
            goto L_0x003b
        L_0x003a:
            r3 = 0
        L_0x003b:
            if (r3 != 0) goto L_0x003f
            goto L_0x00c9
        L_0x003f:
            int r4 = r3.getVisibility()     // Catch:{ all -> 0x00de }
            r5 = 1
            if (r4 != 0) goto L_0x0052
            float r4 = r3.getAlpha()     // Catch:{ all -> 0x00de }
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x0050
            goto L_0x0052
        L_0x0050:
            r4 = r0
            goto L_0x0053
        L_0x0052:
            r4 = r5
        L_0x0053:
            if (r4 != 0) goto L_0x00c9
            boolean r4 = r3 instanceof android.view.ViewGroup     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x006c
            r4 = r3
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4     // Catch:{ all -> 0x00de }
            int r4 = r4.getChildCount()     // Catch:{ all -> 0x00de }
            if (r4 <= 0) goto L_0x006c
            android.view.ViewGroup r3 = (android.view.ViewGroup) r3     // Catch:{ all -> 0x00de }
            boolean r3 = findObstructions(r3, r8, r9, r7, r11)     // Catch:{ all -> 0x00de }
            if (r3 == 0) goto L_0x00c9
            goto L_0x00ce
        L_0x006c:
            boolean r4 = r3.willNotDraw()     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x00aa
            android.graphics.drawable.Drawable r4 = r3.getBackground()     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x0087
            boolean r6 = r4.isVisible()     // Catch:{ all -> 0x00de }
            if (r6 == 0) goto L_0x0087
            int r4 = r4.getAlpha()     // Catch:{ all -> 0x00de }
            if (r4 > 0) goto L_0x0085
            goto L_0x0087
        L_0x0085:
            r4 = r0
            goto L_0x0088
        L_0x0087:
            r4 = r5
        L_0x0088:
            if (r4 == 0) goto L_0x00aa
            boolean r4 = com.adsbynimbus.internal.Components.isApi23()     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x00a8
            android.graphics.drawable.Drawable r4 = r3.getForeground()     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x00a5
            boolean r6 = r4.isVisible()     // Catch:{ all -> 0x00de }
            if (r6 == 0) goto L_0x00a5
            int r4 = r4.getAlpha()     // Catch:{ all -> 0x00de }
            if (r4 > 0) goto L_0x00a3
            goto L_0x00a5
        L_0x00a3:
            r4 = r0
            goto L_0x00a6
        L_0x00a5:
            r4 = r5
        L_0x00a6:
            if (r4 == 0) goto L_0x00aa
        L_0x00a8:
            r4 = r5
            goto L_0x00ab
        L_0x00aa:
            r4 = r0
        L_0x00ab:
            if (r4 != 0) goto L_0x00c9
            java.lang.Object r4 = r9.get(r3)     // Catch:{ all -> 0x00de }
            android.graphics.Rect r4 = (android.graphics.Rect) r4     // Catch:{ all -> 0x00de }
            if (r4 == 0) goto L_0x00ba
            r4.set(r11)     // Catch:{ all -> 0x00de }
            if (r4 != 0) goto L_0x00bf
        L_0x00ba:
            android.graphics.Rect r4 = new android.graphics.Rect     // Catch:{ all -> 0x00de }
            r4.<init>(r11)     // Catch:{ all -> 0x00de }
        L_0x00bf:
            r9.put(r3, r4)     // Catch:{ all -> 0x00de }
            boolean r3 = r11.contains(r8)     // Catch:{ all -> 0x00de }
            if (r3 == 0) goto L_0x00c9
            goto L_0x00ce
        L_0x00c9:
            int r2 = r2 + 1
            goto L_0x0028
        L_0x00cd:
            r5 = r0
        L_0x00ce:
            android.view.ViewParent r9 = r7.getParent()     // Catch:{ all -> 0x00de }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)     // Catch:{ all -> 0x00de }
            if (r9 == 0) goto L_0x00dd
            android.view.View r7 = (android.view.View) r7     // Catch:{ all -> 0x00de }
            r10.offsetDescendantRectToMyCoords(r7, r8)     // Catch:{ all -> 0x00de }
        L_0x00dd:
            return r5
        L_0x00de:
            r7 = move-exception
            kotlin.Result$Companion r8 = kotlin.Result.Companion
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m2444constructorimpl(r7)
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r0)
            boolean r9 = kotlin.Result.m2450isFailureimpl(r7)
            if (r9 == 0) goto L_0x00f4
            r7 = r8
        L_0x00f4:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.internal.ExposureTrackerKt.findObstructions(android.view.ViewGroup, android.graphics.Rect, java.util.Map, android.view.ViewGroup, android.graphics.Rect):boolean");
    }

    public static final void attachListeners(NimbusAdView nimbusAdView) {
        Intrinsics.checkNotNullParameter(nimbusAdView, "<this>");
        nimbusAdView.getViewTreeObserver().addOnGlobalLayoutListener(nimbusAdView);
        nimbusAdView.getViewTreeObserver().addOnScrollChangedListener(nimbusAdView);
    }

    public static final void removeListeners(NimbusAdView nimbusAdView) {
        Intrinsics.checkNotNullParameter(nimbusAdView, "<this>");
        nimbusAdView.getViewTreeObserver().removeOnGlobalLayoutListener(nimbusAdView);
        nimbusAdView.getViewTreeObserver().removeOnScrollChangedListener(nimbusAdView);
    }

    public static final boolean isExposedOnScreen(NimbusAdView nimbusAdView) {
        Intrinsics.checkNotNullParameter(nimbusAdView, "<this>");
        if (nimbusAdView.getAlpha() > 0.0f) {
            boolean globalVisibleRect = nimbusAdView.getGlobalVisibleRect(nimbusAdView.getExposureRect$render_release(), nimbusAdView.getOffset$render_release());
            if (globalVisibleRect) {
                nimbusAdView.getExposureRect$render_release().offset(-nimbusAdView.getOffset$render_release().x, -nimbusAdView.getOffset$render_release().y);
            } else {
                nimbusAdView.getExposureRect$render_release().setEmpty();
            }
            if (globalVisibleRect) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ void scheduleExposureCheck$default(NimbusAdView nimbusAdView, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = System.currentTimeMillis() - nimbusAdView.getLastReportTime$render_release();
        }
        scheduleExposureCheck(nimbusAdView, j);
    }

    public static final void scheduleExposureCheck(NimbusAdView nimbusAdView, long j) {
        Intrinsics.checkNotNullParameter(nimbusAdView, "<this>");
        nimbusAdView.setNeedsExposureUpdate$render_release(true);
        if (!nimbusAdView.getExposureScheduled$render_release()) {
            nimbusAdView.setExposureScheduled$render_release(true);
            Context context = nimbusAdView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            Job unused = BuildersKt__Builders_commonKt.launch$default(Components.getLifecycleOrNimbusScope(context), (CoroutineContext) null, (CoroutineStart) null, new ExposureTrackerKt$scheduleExposureCheck$1(j, nimbusAdView, (Continuation<? super ExposureTrackerKt$scheduleExposureCheck$1>) null), 3, (Object) null);
        }
    }

    public static /* synthetic */ Object calculateExposure$default(NimbusAdView nimbusAdView, Map map, Map map2, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            map = new LinkedHashMap();
        }
        if ((i & 2) != 0) {
            map2 = new LinkedHashMap();
        }
        return calculateExposure(nimbusAdView, map, map2, continuation);
    }

    public static final Object calculateExposure(NimbusAdView nimbusAdView, Map<View, Rect> map, Map<ViewGroup, Rect> map2, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getDefault(), new ExposureTrackerKt$calculateExposure$2(nimbusAdView, map, map2, (Continuation<? super ExposureTrackerKt$calculateExposure$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    public static final Object updateExposure(NimbusAdView nimbusAdView, int i, Rect rect, Map<View, Rect> map, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new ExposureTrackerKt$updateExposure$2(nimbusAdView, map, i, rect, (Continuation<? super ExposureTrackerKt$updateExposure$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
