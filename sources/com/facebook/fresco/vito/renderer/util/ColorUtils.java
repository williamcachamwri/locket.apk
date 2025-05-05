package com.facebook.fresco.vito.renderer.util;

import androidx.core.view.ViewCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/facebook/fresco/vito/renderer/util/ColorUtils;", "", "()V", "Companion", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ColorUtils.kt */
public final class ColorUtils {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"Lcom/facebook/fresco/vito/renderer/util/ColorUtils$Companion;", "", "()V", "multiplyColorAlpha", "", "color", "alpha", "renderer_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ColorUtils.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int multiplyColorAlpha(int i, int i2) {
            if (i2 == 0) {
                return i & ViewCompat.MEASURED_SIZE_MASK;
            }
            if (i2 == 255) {
                return i;
            }
            int i3 = i2 + (i2 >> 7);
            return (i & ViewCompat.MEASURED_SIZE_MASK) | ((((i >>> 24) * i3) >> 8) << 24);
        }

        private Companion() {
        }
    }
}
