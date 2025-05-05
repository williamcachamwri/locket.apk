package expo.modules.blur;

import androidx.tracing.Trace;
import expo.modules.blur.enums.BlurMethod;
import expo.modules.blur.enums.TintStyle;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.views.ConcreteViewProp;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lexpo/modules/blur/BlurModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-blur_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BlurModule.kt */
public final class BlurModule extends Module {
    public ModuleDefinitionData definition() {
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoBlurView");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ExpoBlurView.class);
            if (moduleDefinitionBuilder.getViewManagerDefinition() == null) {
                ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(orCreateKotlinClass, new LazyKType(Reflection.getOrCreateKotlinClass(ExpoBlurView.class), false, BlurModule$definition$lambda$2$$inlined$View$1.INSTANCE, 2, (DefaultConstructorMarker) null));
                viewDefinitionBuilder.getProps().put("intensity", new ConcreteViewProp("intensity", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), false, BlurModule$definition$lambda$2$lambda$1$$inlined$Prop$1.INSTANCE)), BlurModule$definition$1$1$1.INSTANCE));
                viewDefinitionBuilder.getProps().put("tint", new ConcreteViewProp("tint", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(TintStyle.class), false, BlurModule$definition$lambda$2$lambda$1$$inlined$Prop$2.INSTANCE)), BlurModule$definition$1$1$2.INSTANCE));
                viewDefinitionBuilder.getProps().put("blurReductionFactor", new ConcreteViewProp("blurReductionFactor", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(Float.class), false, BlurModule$definition$lambda$2$lambda$1$$inlined$Prop$3.INSTANCE)), BlurModule$definition$1$1$3.INSTANCE));
                viewDefinitionBuilder.getProps().put("experimentalBlurMethod", new ConcreteViewProp("experimentalBlurMethod", new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(BlurMethod.class), false, BlurModule$definition$lambda$2$lambda$1$$inlined$Prop$4.INSTANCE)), BlurModule$definition$1$1$4.INSTANCE));
                viewDefinitionBuilder.setOnViewDidUpdateProps(new BlurModule$definition$lambda$2$lambda$1$$inlined$OnViewDidUpdateProps$1());
                moduleDefinitionBuilder.setViewManagerDefinition(viewDefinitionBuilder.build());
                return moduleDefinitionBuilder.buildModule();
            }
            throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
        } finally {
            Trace.endSection();
        }
    }
}
