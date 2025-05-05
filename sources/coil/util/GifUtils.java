package coil.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PostProcessor;
import android.graphics.drawable.Animatable2;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import coil.size.Dimension;
import coil.size.Scale;
import coil.size.Size;
import coil.size.Sizes;
import coil.transform.AnimatedTransformation;
import coil.transform.PixelOpacity;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a(\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0001\u001a(\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0001\u001a-\u0010\u0014\u001a\u00020\r\"\u0004\b\u0000\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u00150\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u00020\r0\u0018H\b\u001a#\u0010\u0019\u001a\u00020\u0001*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\b\u001a\u0014\u0010\u001e\u001a\u00020\u0001*\u00020\u001f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0000\u001a#\u0010 \u001a\u00020\u0001*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\b\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00078@X\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\b¨\u0006!"}, d2 = {"flag", "", "Lcoil/transform/PixelOpacity;", "getFlag", "(Lcoil/transform/PixelOpacity;)I", "isHardware", "", "Landroid/graphics/Bitmap$Config;", "(Landroid/graphics/Bitmap$Config;)Z", "animatable2CallbackOf", "Landroid/graphics/drawable/Animatable2$AnimationCallback;", "onStart", "Lkotlin/Function0;", "", "onEnd", "animatable2CompatCallbackOf", "Landroidx/vectordrawable/graphics/drawable/Animatable2Compat$AnimationCallback;", "asPostProcessor", "Landroid/graphics/PostProcessor;", "Lcoil/transform/AnimatedTransformation;", "forEachIndices", "T", "", "action", "Lkotlin/Function1;", "heightPx", "Lcoil/size/Size;", "scale", "Lcoil/size/Scale;", "original", "toPx", "Lcoil/size/Dimension;", "widthPx", "coil-gif_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: coil.util.-GifUtils  reason: invalid class name */
/* compiled from: Utils.kt */
public final class GifUtils {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* renamed from: coil.util.-GifUtils$WhenMappings */
    /* compiled from: Utils.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|(2:1|2)|3|5|6|(2:7|8)|9|11|12|13|14|15|17) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0033 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0019 */
        static {
            /*
                coil.transform.PixelOpacity[] r0 = coil.transform.PixelOpacity.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                r1 = 1
                coil.transform.PixelOpacity r2 = coil.transform.PixelOpacity.UNCHANGED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                r2 = 2
                coil.transform.PixelOpacity r3 = coil.transform.PixelOpacity.TRANSLUCENT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r0[r3] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                coil.transform.PixelOpacity r3 = coil.transform.PixelOpacity.OPAQUE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r4 = 3
                r0[r3] = r4     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                coil.size.Scale[] r0 = coil.size.Scale.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                coil.size.Scale r3 = coil.size.Scale.FILL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                coil.size.Scale r1 = coil.size.Scale.FIT     // Catch:{ NoSuchFieldError -> 0x003b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003b }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003b }
            L_0x003b:
                $EnumSwitchMapping$1 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: coil.util.GifUtils.WhenMappings.<clinit>():void");
        }
    }

    public static final PostProcessor asPostProcessor(AnimatedTransformation animatedTransformation) {
        return new GifUtils$$ExternalSyntheticLambda0(animatedTransformation);
    }

    /* access modifiers changed from: private */
    public static final int asPostProcessor$lambda$0(AnimatedTransformation animatedTransformation, Canvas canvas) {
        return getFlag(animatedTransformation.transform(canvas));
    }

    public static final int getFlag(PixelOpacity pixelOpacity) {
        int i = WhenMappings.$EnumSwitchMapping$0[pixelOpacity.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return -3;
        }
        if (i == 3) {
            return -1;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final Animatable2.AnimationCallback animatable2CallbackOf(Function0<Unit> function0, Function0<Unit> function02) {
        return new GifUtils$animatable2CallbackOf$1(function0, function02);
    }

    public static final Animatable2Compat.AnimationCallback animatable2CompatCallbackOf(Function0<Unit> function0, Function0<Unit> function02) {
        return new GifUtils$animatable2CompatCallbackOf$1(function0, function02);
    }

    public static final <T> void forEachIndices(List<? extends T> list, Function1<? super T, Unit> function1) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            function1.invoke(list.get(i));
        }
    }

    public static final boolean isHardware(Bitmap.Config config) {
        return config == Bitmap.Config.HARDWARE;
    }

    public static final int widthPx(Size size, Scale scale, Function0<Integer> function0) {
        return Sizes.isOriginal(size) ? function0.invoke().intValue() : toPx(size.getWidth(), scale);
    }

    public static final int heightPx(Size size, Scale scale, Function0<Integer> function0) {
        return Sizes.isOriginal(size) ? function0.invoke().intValue() : toPx(size.getHeight(), scale);
    }

    public static final int toPx(Dimension dimension, Scale scale) {
        if (dimension instanceof Dimension.Pixels) {
            return ((Dimension.Pixels) dimension).px;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[scale.ordinal()];
        if (i == 1) {
            return Integer.MIN_VALUE;
        }
        if (i == 2) {
            return Integer.MAX_VALUE;
        }
        throw new NoWhenBranchMatchedException();
    }
}
