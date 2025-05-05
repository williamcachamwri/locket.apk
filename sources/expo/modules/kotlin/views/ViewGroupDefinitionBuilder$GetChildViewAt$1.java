package expo.modules.kotlin.views;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "ChildViewType", "Landroid/view/View;", "ParentType", "Landroid/view/ViewGroup;", "view", "index", "", "invoke", "(Landroid/view/ViewGroup;I)Landroid/view/View;"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: ViewGroupDefinitionBuilder.kt */
public final class ViewGroupDefinitionBuilder$GetChildViewAt$1 extends Lambda implements Function2<ViewGroup, Integer, ChildViewType> {
    final /* synthetic */ Function2<ParentType, Integer, ChildViewType> $body;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ViewGroupDefinitionBuilder$GetChildViewAt$1(Function2<? super ParentType, ? super Integer, ? extends ChildViewType> function2) {
        super(2);
        this.$body = function2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return invoke((ViewGroup) obj, ((Number) obj2).intValue());
    }

    public final ChildViewType invoke(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "view");
        return (View) this.$body.invoke(viewGroup, Integer.valueOf(i));
    }
}
