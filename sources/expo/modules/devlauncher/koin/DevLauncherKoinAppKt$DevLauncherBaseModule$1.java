package expo.modules.devlauncher.koin;

import expo.modules.devlauncher.helpers.DevLauncherInstallationIDHelper;
import expo.modules.devlauncher.launcher.DevLauncherIntentRegistryInterface;
import expo.modules.devlauncher.launcher.DevLauncherLifecycle;
import expo.modules.devlauncher.launcher.loaders.DevLauncherAppLoaderFactoryInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import okhttp3.OkHttpClient;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.definition.KoinDefinition;
import org.koin.core.instance.FactoryInstanceFactory;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.ScopeRegistry;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lorg/koin/core/module/Module;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherKoinApp.kt */
final class DevLauncherKoinAppKt$DevLauncherBaseModule$1 extends Lambda implements Function1<Module, Unit> {
    public static final DevLauncherKoinAppKt$DevLauncherBaseModule$1 INSTANCE = new DevLauncherKoinAppKt$DevLauncherBaseModule$1();

    DevLauncherKoinAppKt$DevLauncherBaseModule$1() {
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
        SingleInstanceFactory singleInstanceFactory = new SingleInstanceFactory(new BeanDefinition(rootScopeQualifier, Reflection.getOrCreateKotlinClass(DevLauncherIntentRegistryInterface.class), (Qualifier) null, AnonymousClass1.INSTANCE, kind, emptyList));
        InstanceFactory instanceFactory = singleInstanceFactory;
        module.indexPrimaryType(instanceFactory);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(singleInstanceFactory);
        }
        new KoinDefinition(module, instanceFactory);
        Qualifier rootScopeQualifier2 = ScopeRegistry.Companion.getRootScopeQualifier();
        Kind kind2 = Kind.Singleton;
        List emptyList2 = CollectionsKt.emptyList();
        SingleInstanceFactory singleInstanceFactory2 = new SingleInstanceFactory(new BeanDefinition(rootScopeQualifier2, Reflection.getOrCreateKotlinClass(OkHttpClient.class), (Qualifier) null, AnonymousClass2.INSTANCE, kind2, emptyList2));
        InstanceFactory instanceFactory2 = singleInstanceFactory2;
        module.indexPrimaryType(instanceFactory2);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(singleInstanceFactory2);
        }
        new KoinDefinition(module, instanceFactory2);
        Qualifier rootScopeQualifier3 = ScopeRegistry.Companion.getRootScopeQualifier();
        Kind kind3 = Kind.Singleton;
        List emptyList3 = CollectionsKt.emptyList();
        SingleInstanceFactory singleInstanceFactory3 = new SingleInstanceFactory(new BeanDefinition(rootScopeQualifier3, Reflection.getOrCreateKotlinClass(DevLauncherLifecycle.class), (Qualifier) null, AnonymousClass3.INSTANCE, kind3, emptyList3));
        InstanceFactory instanceFactory3 = singleInstanceFactory3;
        module.indexPrimaryType(instanceFactory3);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(singleInstanceFactory3);
        }
        new KoinDefinition(module, instanceFactory3);
        Qualifier rootScopeQualifier4 = ScopeRegistry.Companion.getRootScopeQualifier();
        Kind kind4 = Kind.Singleton;
        List emptyList4 = CollectionsKt.emptyList();
        SingleInstanceFactory singleInstanceFactory4 = new SingleInstanceFactory(new BeanDefinition(rootScopeQualifier4, Reflection.getOrCreateKotlinClass(DevLauncherInstallationIDHelper.class), (Qualifier) null, AnonymousClass4.INSTANCE, kind4, emptyList4));
        InstanceFactory instanceFactory4 = singleInstanceFactory4;
        module.indexPrimaryType(instanceFactory4);
        if (module.get_createdAtStart()) {
            module.prepareForCreationAtStart(singleInstanceFactory4);
        }
        new KoinDefinition(module, instanceFactory4);
        Qualifier rootScopeQualifier5 = ScopeRegistry.Companion.getRootScopeQualifier();
        Kind kind5 = Kind.Factory;
        List emptyList5 = CollectionsKt.emptyList();
        InstanceFactory factoryInstanceFactory = new FactoryInstanceFactory(new BeanDefinition(rootScopeQualifier5, Reflection.getOrCreateKotlinClass(DevLauncherAppLoaderFactoryInterface.class), (Qualifier) null, AnonymousClass5.INSTANCE, kind5, emptyList5));
        module.indexPrimaryType(factoryInstanceFactory);
        new KoinDefinition(module, factoryInstanceFactory);
    }
}
