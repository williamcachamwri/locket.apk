package expo.modules.application;

import android.provider.Settings;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\u0010\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"<anonymous>", "", "T", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/PropertyComponentBuilder$get$1$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PropertyComponentBuilder.kt */
final class ApplicationModule$definition$lambda$4$$inlined$Property$1 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ ApplicationModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ApplicationModule$definition$lambda$4$$inlined$Property$1(ApplicationModule applicationModule) {
        super(1);
        this.this$0 = applicationModule;
    }

    public final Object invoke(Object[] objArr) {
        Intrinsics.checkNotNullParameter(objArr, "it");
        return Settings.Secure.getString(this.this$0.getContext().getContentResolver(), "android_id");
    }
}
