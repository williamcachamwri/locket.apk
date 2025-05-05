package org.koin.core.scope;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.Kind;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.instance.ScopedInstanceFactory;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.InstanceRegistry;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "T", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* compiled from: Scope.kt */
public final class Scope$declare$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ boolean $allowOverride;
    final /* synthetic */ T $instance;
    final /* synthetic */ Qualifier $qualifier;
    final /* synthetic */ List<KClass<?>> $secondaryTypes;
    final /* synthetic */ Scope this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Scope$declare$1(Scope scope, T t, Qualifier qualifier, List<? extends KClass<?>> list, boolean z) {
        super(0);
        this.this$0 = scope;
        this.$instance = t;
        this.$qualifier = qualifier;
        this.$secondaryTypes = list;
        this.$allowOverride = z;
    }

    public final void invoke() {
        InstanceRegistry instanceRegistry = this.this$0.get_koin().getInstanceRegistry();
        T t = this.$instance;
        Qualifier qualifier = this.$qualifier;
        List<KClass<?>> list = this.$secondaryTypes;
        boolean z = this.$allowOverride;
        Qualifier scopeQualifier = this.this$0.getScopeQualifier();
        String id = this.this$0.getId();
        Kind kind = Kind.Scoped;
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition beanDefinition = new BeanDefinition(scopeQualifier, Reflection.getOrCreateKotlinClass(Object.class), qualifier, new Scope$declare$1$invoke$$inlined$declareScopedInstance$1(t), kind, list);
        String indexKey = BeanDefinitionKt.indexKey(beanDefinition.getPrimaryType(), beanDefinition.getQualifier(), beanDefinition.getScopeQualifier());
        InstanceFactory<?> instanceFactory = instanceRegistry.getInstances().get(indexKey);
        ScopedInstanceFactory scopedInstanceFactory = instanceFactory instanceof ScopedInstanceFactory ? (ScopedInstanceFactory) instanceFactory : null;
        if (scopedInstanceFactory != null) {
            Intrinsics.checkNotNull(t, "null cannot be cast to non-null type kotlin.Any");
            Object obj = (Object) t;
            scopedInstanceFactory.refreshInstance(id, t);
            return;
        }
        InstanceFactory scopedInstanceFactory2 = new ScopedInstanceFactory(beanDefinition);
        InstanceRegistry.saveMapping$default(instanceRegistry, z, indexKey, scopedInstanceFactory2, false, 8, (Object) null);
        for (KClass indexKey2 : beanDefinition.getSecondaryTypes()) {
            InstanceRegistry.saveMapping$default(instanceRegistry, z, BeanDefinitionKt.indexKey(indexKey2, beanDefinition.getQualifier(), beanDefinition.getScopeQualifier()), scopedInstanceFactory2, false, 8, (Object) null);
        }
    }
}
