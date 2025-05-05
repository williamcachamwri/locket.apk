package com.facebook.imagepipeline.filter;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0007J\b\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/imagepipeline/filter/RenderScriptBlurFilter;", "", "()V", "BLUR_MAX_RADIUS", "", "blurBitmap", "", "dest", "Landroid/graphics/Bitmap;", "src", "context", "Landroid/content/Context;", "radius", "canUseRenderScript", "", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RenderScriptBlurFilter.kt */
public final class RenderScriptBlurFilter {
    public static final int BLUR_MAX_RADIUS = 25;
    public static final RenderScriptBlurFilter INSTANCE = new RenderScriptBlurFilter();

    @JvmStatic
    public static final boolean canUseRenderScript() {
        return true;
    }

    private RenderScriptBlurFilter() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x007d  */
    @kotlin.jvm.JvmStatic
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void blurBitmap(android.graphics.Bitmap r3, android.graphics.Bitmap r4, android.content.Context r5, int r6) {
        /*
            java.lang.String r0 = "dest"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "src"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            if (r6 <= 0) goto L_0x0018
            r0 = 25
            if (r6 > r0) goto L_0x0018
            r0 = 1
            goto L_0x0019
        L_0x0018:
            r0 = 0
        L_0x0019:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            com.facebook.common.internal.Preconditions.checkArgument(r0)
            r0 = 0
            android.renderscript.RenderScript r5 = android.renderscript.RenderScript.create(r5)     // Catch:{ all -> 0x007a }
            java.lang.String r1 = "Required value was null."
            if (r5 == 0) goto L_0x0070
            android.renderscript.Element r0 = android.renderscript.Element.U8_4(r5)     // Catch:{ all -> 0x006d }
            android.renderscript.ScriptIntrinsicBlur r0 = android.renderscript.ScriptIntrinsicBlur.create(r5, r0)     // Catch:{ all -> 0x006d }
            android.renderscript.Allocation r4 = android.renderscript.Allocation.createFromBitmap(r5, r4)     // Catch:{ all -> 0x006d }
            if (r4 == 0) goto L_0x0063
            android.renderscript.Allocation r2 = android.renderscript.Allocation.createFromBitmap(r5, r3)     // Catch:{ all -> 0x006d }
            if (r2 == 0) goto L_0x0059
            float r6 = (float) r6     // Catch:{ all -> 0x006d }
            r0.setRadius(r6)     // Catch:{ all -> 0x006d }
            r0.setInput(r4)     // Catch:{ all -> 0x006d }
            r0.forEach(r2)     // Catch:{ all -> 0x006d }
            r2.copyTo(r3)     // Catch:{ all -> 0x006d }
            r0.destroy()     // Catch:{ all -> 0x006d }
            r4.destroy()     // Catch:{ all -> 0x006d }
            r2.destroy()     // Catch:{ all -> 0x006d }
            if (r5 == 0) goto L_0x0058
            r5.destroy()
        L_0x0058:
            return
        L_0x0059:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x006d }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x006d }
            r3.<init>(r4)     // Catch:{ all -> 0x006d }
            throw r3     // Catch:{ all -> 0x006d }
        L_0x0063:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x006d }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x006d }
            r3.<init>(r4)     // Catch:{ all -> 0x006d }
            throw r3     // Catch:{ all -> 0x006d }
        L_0x006d:
            r3 = move-exception
            r0 = r5
            goto L_0x007b
        L_0x0070:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch:{ all -> 0x007a }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x007a }
            r3.<init>(r4)     // Catch:{ all -> 0x007a }
            throw r3     // Catch:{ all -> 0x007a }
        L_0x007a:
            r3 = move-exception
        L_0x007b:
            if (r0 == 0) goto L_0x0080
            r0.destroy()
        L_0x0080:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.filter.RenderScriptBlurFilter.blurBitmap(android.graphics.Bitmap, android.graphics.Bitmap, android.content.Context, int):void");
    }
}
