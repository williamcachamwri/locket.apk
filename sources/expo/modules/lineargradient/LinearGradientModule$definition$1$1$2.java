package expo.modules.lineargradient;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "view", "Lexpo/modules/lineargradient/LinearGradientView;", "locations", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: LinearGradientModule.kt */
final class LinearGradientModule$definition$1$1$2 extends Lambda implements Function2<LinearGradientView, float[], Unit> {
    public static final LinearGradientModule$definition$1$1$2 INSTANCE = new LinearGradientModule$definition$1$1$2();

    LinearGradientModule$definition$1$1$2() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((LinearGradientView) obj, (float[]) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(LinearGradientView linearGradientView, float[] fArr) {
        Intrinsics.checkNotNullParameter(linearGradientView, "view");
        if (fArr != null) {
            linearGradientView.setLocations(fArr);
        }
    }
}
