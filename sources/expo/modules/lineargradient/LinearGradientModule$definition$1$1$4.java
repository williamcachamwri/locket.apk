package expo.modules.lineargradient;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "view", "Lexpo/modules/lineargradient/LinearGradientView;", "endPoint", "Lkotlin/Pair;", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: LinearGradientModule.kt */
final class LinearGradientModule$definition$1$1$4 extends Lambda implements Function2<LinearGradientView, Pair<? extends Float, ? extends Float>, Unit> {
    public static final LinearGradientModule$definition$1$1$4 INSTANCE = new LinearGradientModule$definition$1$1$4();

    LinearGradientModule$definition$1$1$4() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LinearGradientView) obj, (Pair<Float, Float>) (Pair) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(LinearGradientView linearGradientView, Pair<Float, Float> pair) {
        Intrinsics.checkNotNullParameter(linearGradientView, "view");
        linearGradientView.setEndPosition(pair != null ? pair.getFirst().floatValue() : 0.5f, pair != null ? pair.getSecond().floatValue() : 1.0f);
    }
}
