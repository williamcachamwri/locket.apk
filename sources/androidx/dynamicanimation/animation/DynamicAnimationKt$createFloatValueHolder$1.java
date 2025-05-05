package androidx.dynamicanimation.animation;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"androidx/dynamicanimation/animation/DynamicAnimationKt$createFloatValueHolder$1", "Landroidx/dynamicanimation/animation/FloatValueHolder;", "getValue", "", "setValue", "", "value", "dynamicanimation-ktx_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DynamicAnimation.kt */
public final class DynamicAnimationKt$createFloatValueHolder$1 extends FloatValueHolder {
    final /* synthetic */ Function0 $getter;
    final /* synthetic */ Function1 $setter;

    DynamicAnimationKt$createFloatValueHolder$1(Function0 function0, Function1 function1) {
        this.$getter = function0;
        this.$setter = function1;
    }

    public float getValue() {
        return ((Number) this.$getter.invoke()).floatValue();
    }

    public void setValue(float f) {
        this.$setter.invoke(Float.valueOf(f));
    }
}
