package expo.modules.kotlin.modules;

import expo.modules.kotlin.classcomponent.ClassComponentBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lexpo/modules/kotlin/classcomponent/ClassComponentBuilder;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 176)
/* compiled from: ModuleDefinitionBuilder.kt */
public final class ModuleDefinitionBuilder$Class$1 extends Lambda implements Function1<ClassComponentBuilder<Unit>, Unit> {
    public static final ModuleDefinitionBuilder$Class$1 INSTANCE = new ModuleDefinitionBuilder$Class$1();

    public ModuleDefinitionBuilder$Class$1() {
        super(1);
    }

    public final void invoke(ClassComponentBuilder<Unit> classComponentBuilder) {
        Intrinsics.checkNotNullParameter(classComponentBuilder, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ClassComponentBuilder<Unit>) (ClassComponentBuilder) obj);
        return Unit.INSTANCE;
    }
}
