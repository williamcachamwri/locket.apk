package expo.modules.kotlin.views;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"isErrorView", "", "Landroid/view/View;", "expo-modules-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ErrorView.kt */
public final class ErrorViewKt {
    public static final boolean isErrorView(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return (view instanceof ErrorView) || (view instanceof ErrorGroupView);
    }
}
