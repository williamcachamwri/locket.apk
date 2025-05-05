package expo.modules.splashscreen;

import android.app.Activity;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.splashscreen.singletons.SplashScreen;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u00012\u0010\u0010\u0004\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"<anonymous>", "", "R", "P0", "<anonymous parameter 0>", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$5"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class SplashScreenModule$definition$lambda$6$$inlined$AsyncFunction$4 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ ModuleDefinitionBuilder $this_ModuleDefinition$inlined;
    final /* synthetic */ SplashScreenModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SplashScreenModule$definition$lambda$6$$inlined$AsyncFunction$4(SplashScreenModule splashScreenModule, ModuleDefinitionBuilder moduleDefinitionBuilder) {
        super(2);
        this.this$0 = splashScreenModule;
        this.$this_ModuleDefinition$inlined = moduleDefinitionBuilder;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Unit unit;
        Intrinsics.checkNotNullParameter(objArr, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        Activity currentActivity = this.this$0.getAppContext().getCurrentActivity();
        if (currentActivity != null) {
            SplashScreen.INSTANCE.hide(currentActivity, new SplashScreenModule$definition$1$2$1$1(promise), new SplashScreenModule$definition$1$2$1$2(promise));
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            promise.resolve(false);
        }
    }
}
