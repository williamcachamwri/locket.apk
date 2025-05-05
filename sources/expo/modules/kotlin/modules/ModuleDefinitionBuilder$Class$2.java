package expo.modules.kotlin.modules;

import expo.modules.kotlin.classcomponent.ClassComponentBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "SharedObjectType", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "Lexpo/modules/kotlin/classcomponent/ClassComponentBuilder;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class ModuleDefinitionBuilder$Class$2 extends Lambda implements Function1<ClassComponentBuilder<SharedObjectType>, Unit> {
    public static final ModuleDefinitionBuilder$Class$2 INSTANCE = new ModuleDefinitionBuilder$Class$2();

    static {
        Intrinsics.needClassReification();
    }

    public ModuleDefinitionBuilder$Class$2() {
        super(1);
    }

    public final void invoke(ClassComponentBuilder<SharedObjectType> classComponentBuilder) {
        Intrinsics.checkNotNullParameter(classComponentBuilder, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ClassComponentBuilder) obj);
        return Unit.INSTANCE;
    }
}
