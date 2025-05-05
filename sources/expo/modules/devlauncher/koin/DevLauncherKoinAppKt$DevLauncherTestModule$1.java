package expo.modules.devlauncher.koin;

import expo.modules.devlauncher.tests.DevLauncherTestInterceptor;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.definition.KoinDefinition;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.ScopeRegistry;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lorg/koin/core/module/Module;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherKoinApp.kt */
final class DevLauncherKoinAppKt$DevLauncherTestModule$1 extends Lambda implements Function1<Module, Unit> {
    public static final DevLauncherKoinAppKt$DevLauncherTestModule$1 INSTANCE = new DevLauncherKoinAppKt$DevLauncherTestModule$1();

    DevLauncherKoinAppKt$DevLauncherTestModule$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Module) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Module module) {
        Intrinsics.checkNotNullParameter(module, "$this$module");
        Qualifier rootScopeQualifier = ScopeRegistry.Companion.getRootScopeQualifier();
        Kind kind = Kind.Singleton;
        List emptyList = CollectionsKt.emptyList();
        SingleInstanceFactory singleInstanceFactory = new SingleInstanceFactory(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(DevLauncherTestInterceptor.class), (Qualifier) null, AnonymousClass1.INSTANCE, kind, emptyList));
        InstanceFactory instanceFactory = singleInstanceFactory;
        module.indexPrimaryType(instanceFactory);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(singleInstanceFactory);
        }
        new KoinDefinition(module, instanceFactory);
    }
}
