package expo.modules.splashscreen;

import android.app.Activity;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.splashscreen.singletons.SplashScreen;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "R", "P0", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$7"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class SplashScreenModule$definition$lambda$6$$inlined$AsyncFunction$3 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ ModuleDefinitionBuilder $this_ModuleDefinition$inlined;
    final /* synthetic */ SplashScreenModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SplashScreenModule$definition$lambda$6$$inlined$AsyncFunction$3(SplashScreenModule splashScreenModule, ModuleDefinitionBuilder moduleDefinitionBuilder) {
        super(1);
        this.this$0 = splashScreenModule;
        this.$this_ModuleDefinition$inlined = moduleDefinitionBuilder;
    }

    public final Object invoke(Object[] objArr) {
        Unit unit;
        Intrinsics.checkNotNullParameter(objArr, "it");
        Promise promise = objArr[0];
        if (promise != null) {
            Promise promise2 = promise;
            Activity currentActivity = this.this$0.getAppContext().getCurrentActivity();
            if (currentActivity != null) {
                SplashScreen.INSTANCE.preventAutoHide(currentActivity, new SplashScreenModule$definition$1$1$1$1(promise2), new SplashScreenModule$definition$1$1$1$2(promise2));
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                promise2.resolve(false);
            }
            return Unit.INSTANCE;
        }
        throw new NullPointerException("null cannot be cast to non-null type expo.modules.kotlin.Promise");
    }
}
