package expo.modules.kotlin.views;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.PropSetException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.types.AnyType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u00020\u0004BM\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u00126\u0010\t\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\u0005\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u000b\u0012\b\b\u0005\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\n¢\u0006\u0002\u0010\u000fJ\"\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u0014\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0012R>\u0010\t\u001a2\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000b\u0012\b\b\u0005\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u000b\u0012\b\b\u0005\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lexpo/modules/kotlin/views/ConcreteViewProp;", "ViewType", "Landroid/view/View;", "PropType", "Lexpo/modules/kotlin/views/AnyViewProp;", "name", "", "propType", "Lexpo/modules/kotlin/types/AnyType;", "setter", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "view", "prop", "", "(Ljava/lang/String;Lexpo/modules/kotlin/types/AnyType;Lkotlin/jvm/functions/Function2;)V", "isNullable", "", "()Z", "set", "Lcom/facebook/react/bridge/Dynamic;", "onView", "appContext", "Lexpo/modules/kotlin/AppContext;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ConcreteViewProp.kt */
public final class ConcreteViewProp<ViewType extends View, PropType> extends AnyViewProp {
    private final boolean isNullable;
    private final Function2<ViewType, PropType, Unit> setter;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConcreteViewProp(String str, AnyType anyType, Function2<? super ViewType, ? super PropType, Unit> function2) {
        super(str, anyType);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(anyType, "propType");
        Intrinsics.checkNotNullParameter(function2, "setter");
        this.setter = function2;
        this.isNullable = anyType.getKType().isMarkedNullable();
    }

    public void set(Dynamic dynamic, View view, AppContext appContext) {
        CodedException codedException;
        Intrinsics.checkNotNullParameter(dynamic, "prop");
        Intrinsics.checkNotNullParameter(view, "onView");
        try {
            this.setter.invoke(view, getType$expo_modules_core_release().convert(dynamic, appContext));
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            if (th instanceof CodedException) {
                codedException = th;
            } else if (th instanceof expo.modules.core.errors.CodedException) {
                String code = th.getCode();
                Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                codedException = new CodedException(code, th.getMessage(), th.getCause());
            } else {
                codedException = new UnexpectedException((Throwable) th);
            }
            throw new PropSetException(getName(), Reflection.getOrCreateKotlinClass(view.getClass()), codedException);
        }
    }

    public boolean isNullable() {
        return this.isNullable;
    }
}
