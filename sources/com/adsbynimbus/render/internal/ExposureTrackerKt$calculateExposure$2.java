package com.adsbynimbus.render.internal;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import com.adsbynimbus.render.NimbusAdView;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.render.internal.ExposureTrackerKt$calculateExposure$2", f = "ExposureTracker.kt", i = {}, l = {220}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ExposureTracker.kt */
final class ExposureTrackerKt$calculateExposure$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Map<View, Rect> $obstructingViews;
    final /* synthetic */ NimbusAdView $this_calculateExposure;
    final /* synthetic */ Map<ViewGroup, Rect> $viewGroups;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExposureTrackerKt$calculateExposure$2(NimbusAdView nimbusAdView, Map<View, Rect> map, Map<ViewGroup, Rect> map2, Continuation<? super ExposureTrackerKt$calculateExposure$2> continuation) {
        super(2, continuation);
        this.$this_calculateExposure = nimbusAdView;
        this.$obstructingViews = map;
        this.$viewGroups = map2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ExposureTrackerKt$calculateExposure$2 exposureTrackerKt$calculateExposure$2 = new ExposureTrackerKt$calculateExposure$2(this.$this_calculateExposure, this.$obstructingViews, this.$viewGroups, continuation);
        exposureTrackerKt$calculateExposure$2.L$0 = obj;
        return exposureTrackerKt$calculateExposure$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExposureTrackerKt$calculateExposure$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01a9, code lost:
        if (r0 == null) goto L_0x01ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01ce, code lost:
        r5.element = r12;
        r0 = r7.entrySet().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01dc, code lost:
        if (r0.hasNext() == false) goto L_0x0205;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01de, code lost:
        r3 = r0.next();
        r3 = (android.graphics.Rect) r3.getValue();
        r13 = r3.contains(r9.getExposureRect$render_release());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0200, code lost:
        if (com.adsbynimbus.render.internal.ExposureTrackerKt.findObstructions((android.view.ViewGroup) r3.getKey(), r3, r8, r12, r9.getTmpRect$render_release()) == false) goto L_0x01d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0202, code lost:
        if (r13 == false) goto L_0x01d8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0205, code lost:
        r0 = ((android.view.ViewGroup) r5.element).getParent();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x020f, code lost:
        if ((r0 instanceof android.view.ViewGroup) == false) goto L_0x0215;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0211, code lost:
        r12 = (android.view.ViewGroup) r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0215, code lost:
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0219, code lost:
        r8.clear();
        r9.getExposureRect$render_release().setEmpty();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0193, code lost:
        if ((r0 == null || !r0.isVisible() || r0.getAlpha() <= 0) != false) goto L_0x0195;
     */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01c8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x019a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r1 = r17
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r1.label
            r3 = 1
            if (r0 == 0) goto L_0x001a
            if (r0 != r3) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r18)
            goto L_0x02a7
        L_0x0012:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x001a:
            kotlin.ResultKt.throwOnFailure(r18)
            java.lang.Object r0 = r1.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            com.adsbynimbus.render.NimbusAdView r0 = r1.$this_calculateExposure
            r4 = 0
            r0.setNeedsExposureUpdate$render_release(r4)
            kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
            r5.<init>()
            com.adsbynimbus.render.NimbusAdView r0 = r1.$this_calculateExposure
            r5.element = r0
            com.adsbynimbus.render.NimbusAdView r0 = r1.$this_calculateExposure
            boolean r0 = r0.isVisibleInWindow()
            if (r0 == 0) goto L_0x0247
            com.adsbynimbus.render.NimbusAdView r0 = r1.$this_calculateExposure
            int r0 = r0.getWidth()
            if (r0 <= 0) goto L_0x0247
            com.adsbynimbus.render.NimbusAdView r0 = r1.$this_calculateExposure
            int r0 = r0.getHeight()
            if (r0 <= 0) goto L_0x0247
            java.util.Map<android.view.ViewGroup, android.graphics.Rect> r7 = r1.$viewGroups
            java.util.Map<android.view.View, android.graphics.Rect> r8 = r1.$obstructingViews
            com.adsbynimbus.render.NimbusAdView r9 = r1.$this_calculateExposure
            long r10 = java.lang.System.currentTimeMillis()
            T r0 = r5.element
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.ViewParent r0 = r0.getParent()
            boolean r12 = r0 instanceof android.view.ViewGroup
            if (r12 == 0) goto L_0x0061
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            goto L_0x0062
        L_0x0061:
            r0 = 0
        L_0x0062:
            boolean r12 = com.adsbynimbus.render.internal.ExposureTrackerKt.isExposedOnScreen(r9)
            if (r12 == 0) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r0 = 0
        L_0x006a:
            r12 = r0
        L_0x006b:
            if (r12 == 0) goto L_0x0223
            r7.clear()
            r0 = r12
            android.view.View r0 = (android.view.View) r0
            int r13 = r0.getVisibility()
            r14 = 0
            if (r13 != 0) goto L_0x0085
            float r0 = r0.getAlpha()
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 > 0) goto L_0x0083
            goto L_0x0085
        L_0x0083:
            r0 = r4
            goto L_0x0086
        L_0x0085:
            r0 = r3
        L_0x0086:
            if (r0 != 0) goto L_0x0219
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x009c }
            T r0 = r5.element     // Catch:{ all -> 0x009c }
            android.view.View r0 = (android.view.View) r0     // Catch:{ all -> 0x009c }
            android.graphics.Rect r13 = r9.getExposureRect$render_release()     // Catch:{ all -> 0x009c }
            r12.offsetDescendantRectToMyCoords(r0, r13)     // Catch:{ all -> 0x009c }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x009c }
            java.lang.Object r0 = kotlin.Result.m2444constructorimpl(r0)     // Catch:{ all -> 0x009c }
            goto L_0x00a7
        L_0x009c:
            r0 = move-exception
            kotlin.Result$Companion r13 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m2444constructorimpl(r0)
        L_0x00a7:
            boolean r0 = kotlin.Result.m2450isFailureimpl(r0)
            if (r0 == 0) goto L_0x00af
            goto L_0x0219
        L_0x00af:
            kotlin.jvm.internal.Ref$IntRef r13 = new kotlin.jvm.internal.Ref$IntRef
            r13.<init>()
            r15 = -1
            r13.element = r15
            int r0 = r12.getChildCount()
            int r0 = r0 - r3
            r6 = r0
        L_0x00bd:
            if (r15 >= r6) goto L_0x01ce
            android.view.View r15 = r12.getChildAt(r6)
            if (r15 == 0) goto L_0x01c8
            java.lang.String r0 = "getChildAt(i)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r0)
            kotlin.Result$Companion r0 = kotlin.Result.Companion     // Catch:{ all -> 0x00fa }
            float r0 = androidx.core.view.ViewCompat.getZ(r15)     // Catch:{ all -> 0x00fa }
            T r3 = r5.element     // Catch:{ all -> 0x00fa }
            android.view.View r3 = (android.view.View) r3     // Catch:{ all -> 0x00fa }
            float r3 = androidx.core.view.ViewCompat.getZ(r3)     // Catch:{ all -> 0x00fa }
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 > 0) goto L_0x00e0
            int r0 = r13.element     // Catch:{ all -> 0x00fa }
            if (r6 <= r0) goto L_0x00f0
        L_0x00e0:
            android.graphics.Rect r0 = r9.getExposureRect$render_release()     // Catch:{ all -> 0x00fa }
            android.graphics.Rect r3 = r9.getTmpRect$render_release()     // Catch:{ all -> 0x00fa }
            boolean r0 = com.adsbynimbus.render.internal.ExposureTrackerKt.overlaps(r15, r0, r3)     // Catch:{ all -> 0x00fa }
            if (r0 == 0) goto L_0x00f0
            r0 = 1
            goto L_0x00f1
        L_0x00f0:
            r0 = r4
        L_0x00f1:
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)     // Catch:{ all -> 0x00fa }
            java.lang.Object r0 = kotlin.Result.m2444constructorimpl(r0)     // Catch:{ all -> 0x00fa }
            goto L_0x0105
        L_0x00fa:
            r0 = move-exception
            kotlin.Result$Companion r3 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m2444constructorimpl(r0)
        L_0x0105:
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            boolean r16 = kotlin.Result.m2450isFailureimpl(r0)
            if (r16 == 0) goto L_0x0110
            r0 = r3
        L_0x0110:
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0119
            goto L_0x011a
        L_0x0119:
            r15 = 0
        L_0x011a:
            if (r15 != 0) goto L_0x011e
            goto L_0x01c8
        L_0x011e:
            T r0 = r5.element
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r0)
            if (r0 == 0) goto L_0x012a
            r13.element = r6
            goto L_0x01c8
        L_0x012a:
            int r0 = r15.getVisibility()
            if (r0 != 0) goto L_0x013b
            float r0 = r15.getAlpha()
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 > 0) goto L_0x0139
            goto L_0x013b
        L_0x0139:
            r0 = r4
            goto L_0x013c
        L_0x013b:
            r0 = 1
        L_0x013c:
            if (r0 != 0) goto L_0x01c8
            boolean r0 = r15 instanceof android.view.ViewGroup
            if (r0 == 0) goto L_0x0159
            r0 = r15
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            int r0 = r0.getChildCount()
            if (r0 <= 0) goto L_0x0159
            android.graphics.Rect r0 = new android.graphics.Rect
            android.graphics.Rect r3 = r9.getTmpRect$render_release()
            r0.<init>(r3)
            r7.put(r15, r0)
            goto L_0x01c8
        L_0x0159:
            boolean r0 = r15.willNotDraw()
            if (r0 == 0) goto L_0x0197
            android.graphics.drawable.Drawable r0 = r15.getBackground()
            if (r0 == 0) goto L_0x0174
            boolean r3 = r0.isVisible()
            if (r3 == 0) goto L_0x0174
            int r0 = r0.getAlpha()
            if (r0 > 0) goto L_0x0172
            goto L_0x0174
        L_0x0172:
            r0 = r4
            goto L_0x0175
        L_0x0174:
            r0 = 1
        L_0x0175:
            if (r0 == 0) goto L_0x0197
            boolean r0 = com.adsbynimbus.internal.Components.isApi23()
            if (r0 == 0) goto L_0x0195
            android.graphics.drawable.Drawable r0 = r15.getForeground()
            if (r0 == 0) goto L_0x0192
            boolean r3 = r0.isVisible()
            if (r3 == 0) goto L_0x0192
            int r0 = r0.getAlpha()
            if (r0 > 0) goto L_0x0190
            goto L_0x0192
        L_0x0190:
            r0 = r4
            goto L_0x0193
        L_0x0192:
            r0 = 1
        L_0x0193:
            if (r0 == 0) goto L_0x0197
        L_0x0195:
            r0 = 1
            goto L_0x0198
        L_0x0197:
            r0 = r4
        L_0x0198:
            if (r0 != 0) goto L_0x01c8
            java.lang.Object r0 = r8.get(r15)
            android.graphics.Rect r0 = (android.graphics.Rect) r0
            if (r0 == 0) goto L_0x01ab
            android.graphics.Rect r3 = r9.getTmpRect$render_release()
            r0.set(r3)
            if (r0 != 0) goto L_0x01b4
        L_0x01ab:
            android.graphics.Rect r0 = new android.graphics.Rect
            android.graphics.Rect r3 = r9.getTmpRect$render_release()
            r0.<init>(r3)
        L_0x01b4:
            r8.put(r15, r0)
            android.graphics.Rect r0 = r9.getTmpRect$render_release()
            android.graphics.Rect r3 = r9.getExposureRect$render_release()
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x01c8
            r5.element = r12
            goto L_0x0223
        L_0x01c8:
            int r6 = r6 + -1
            r3 = 1
            r15 = -1
            goto L_0x00bd
        L_0x01ce:
            r5.element = r12
            java.util.Set r0 = r7.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x01d8:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0205
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r6 = r3.getKey()
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            java.lang.Object r3 = r3.getValue()
            android.graphics.Rect r3 = (android.graphics.Rect) r3
            android.graphics.Rect r13 = r9.getExposureRect$render_release()
            boolean r13 = r3.contains(r13)
            android.graphics.Rect r14 = r9.getTmpRect$render_release()
            boolean r3 = com.adsbynimbus.render.internal.ExposureTrackerKt.findObstructions(r6, r3, r8, r12, r14)
            if (r3 == 0) goto L_0x01d8
            if (r13 == 0) goto L_0x01d8
            goto L_0x0223
        L_0x0205:
            T r0 = r5.element
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            android.view.ViewParent r0 = r0.getParent()
            boolean r3 = r0 instanceof android.view.ViewGroup
            if (r3 == 0) goto L_0x0215
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            r12 = r0
            goto L_0x0216
        L_0x0215:
            r12 = 0
        L_0x0216:
            r3 = 1
            goto L_0x006b
        L_0x0219:
            r8.clear()
            android.graphics.Rect r0 = r9.getExposureRect$render_release()
            r0.setEmpty()
        L_0x0223:
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r10
            r8 = 100
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x0250
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "Tree walk took "
            r0.<init>(r3)
            java.lang.StringBuilder r0 = r0.append(r6)
            java.lang.String r3 = " ms"
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.adsbynimbus.internal.Logger.verbose(r0)
            goto L_0x0250
        L_0x0247:
            com.adsbynimbus.render.NimbusAdView r0 = r1.$this_calculateExposure
            android.graphics.Rect r0 = r0.getExposureRect$render_release()
            r0.setEmpty()
        L_0x0250:
            com.adsbynimbus.render.NimbusAdView r0 = r1.$this_calculateExposure
            int r0 = r0.getWidth()
            com.adsbynimbus.render.NimbusAdView r3 = r1.$this_calculateExposure
            int r3 = r3.getHeight()
            int r0 = r0 * r3
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            r3 = r0
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            if (r3 <= 0) goto L_0x026c
            r3 = 1
            goto L_0x026d
        L_0x026c:
            r3 = r4
        L_0x026d:
            if (r3 == 0) goto L_0x0271
            r6 = r0
            goto L_0x0272
        L_0x0271:
            r6 = 0
        L_0x0272:
            if (r6 == 0) goto L_0x0292
            com.adsbynimbus.render.NimbusAdView r0 = r1.$this_calculateExposure
            java.util.Map<android.view.View, android.graphics.Rect> r9 = r1.$obstructingViews
            java.lang.Number r6 = (java.lang.Number) r6
            int r3 = r6.intValue()
            android.graphics.Rect r7 = r0.getExposureRect$render_release()
            T r0 = r5.element
            r8 = r0
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            r10 = 0
            r11 = 4
            r12 = 0
            int r0 = com.adsbynimbus.render.internal.ExposureTrackerKt.exposedPercent$default(r7, r8, r9, r10, r11, r12)
            int r0 = r0 * 100
            int r4 = r0 / r3
        L_0x0292:
            com.adsbynimbus.render.NimbusAdView r0 = r1.$this_calculateExposure
            android.graphics.Rect r3 = r0.getExposureRect$render_release()
            java.util.Map<android.view.View, android.graphics.Rect> r5 = r1.$obstructingViews
            r6 = r1
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r7 = 1
            r1.label = r7
            java.lang.Object r0 = com.adsbynimbus.render.internal.ExposureTrackerKt.updateExposure(r0, r4, r3, r5, r6)
            if (r0 != r2) goto L_0x02a7
            return r2
        L_0x02a7:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.render.internal.ExposureTrackerKt$calculateExposure$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
