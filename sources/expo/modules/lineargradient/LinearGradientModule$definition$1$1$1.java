package expo.modules.lineargradient;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "view", "Lexpo/modules/lineargradient/LinearGradientView;", "colors", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: LinearGradientModule.kt */
final class LinearGradientModule$definition$1$1$1 extends Lambda implements Function2<LinearGradientView, int[], Unit> {
    public static final LinearGradientModule$definition$1$1$1 INSTANCE = new LinearGradientModule$definition$1$1$1();

    LinearGradientModule$definition$1$1$1() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LinearGradientView) obj, (int[]) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(LinearGradientView linearGradientView, int[] iArr) {
        Intrinsics.checkNotNullParameter(linearGradientView, "view");
        Intrinsics.checkNotNullParameter(iArr, "colors");
        linearGradientView.setColors(iArr);
    }
}
