package expo.modules.lineargradient;

import androidx.tracing.Trace;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.views.ConcreteViewProp;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/lineargradient/LinearGradientModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-linear-gradient_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LinearGradientModule.kt */
public final class LinearGradientModule extends Module {
    public ModuleDefinitionData definition() {
        Class<float[]> cls = float[].class;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoLinearGradient");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(LinearGradientView.class);
            if (moduleDefinitionBuilder.getViewManagerDefinition() == null) {
                ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(orCreateKotlinClass, new LazyKType(Reflection.getOrCreateKotlinClass(LinearGradientView.class), false, LinearGradientModule$definition$lambda$1$$inlined$View$1.INSTANCE, 2, (DefaultConstructorMarker) null));
                viewDefinitionBuilder.getProps().put("colors", new ConcreteViewProp("colors", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(int[].class), false, LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$1.INSTANCE)), LinearGradientModule$definition$1$1$1.INSTANCE));
                viewDefinitionBuilder.getProps().put("locations", new ConcreteViewProp("locations", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(cls), true, LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$2.INSTANCE)), LinearGradientModule$definition$1$1$2.INSTANCE));
                viewDefinitionBuilder.getProps().put("startPoint", new ConcreteViewProp("startPoint", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Pair.class), true, LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$3.INSTANCE)), LinearGradientModule$definition$1$1$3.INSTANCE));
                viewDefinitionBuilder.getProps().put("endPoint", new ConcreteViewProp("endPoint", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Pair.class), true, LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$4.INSTANCE)), LinearGradientModule$definition$1$1$4.INSTANCE));
                viewDefinitionBuilder.getProps().put("borderRadii", new ConcreteViewProp("borderRadii", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(cls), true, LinearGradientModule$definition$lambda$1$lambda$0$$inlined$Prop$5.INSTANCE)), LinearGradientModule$definition$1$1$5.INSTANCE));
                moduleDefinitionBuilder.setViewManagerDefinition(viewDefinitionBuilder.build());
                return moduleDefinitionBuilder.buildModule();
            }
            throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
        } finally {
            Trace.endSection();
        }
    }
}
