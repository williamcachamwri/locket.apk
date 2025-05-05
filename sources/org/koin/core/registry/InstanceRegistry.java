package org.koin.core.registry;

import androidx.exifinterface.media.ExifInterface;
import expo.modules.interfaces.permissions.PermissionsResponse;
import io.sentry.SentryEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.Koin;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.BeanDefinitionKt;
import org.koin.core.definition.Kind;
import org.koin.core.instance.InstanceContext;
import org.koin.core.instance.InstanceFactory;
import org.koin.core.instance.ScopedInstanceFactory;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.module.Module;
import org.koin.core.module.ModuleKt;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.mp.KoinPlatformTools;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\r\u0010\u0019\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u001aJ\r\u0010\u001b\u001a\u00020\u0016H\u0000¢\u0006\u0002\b\u001cJ\u001a\u0010\u001d\u001a\u00020\u00162\u0010\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u001eH\u0002JH\u0010\u001f\u001a\u00020\u0016\"\u0006\b\u0000\u0010 \u0018\u00012\u0006\u0010!\u001a\u0002H 2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\u0012\b\u0002\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030&0%2\b\b\u0002\u0010'\u001a\u00020(H\b¢\u0006\u0002\u0010)J\\\u0010*\u001a\u00020\u0016\"\u0006\b\u0000\u0010 \u0018\u00012\u0006\u0010!\u001a\u0002H 2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\u0012\b\u0002\u0010$\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030&0%2\b\b\u0002\u0010'\u001a\u00020(2\u0006\u0010+\u001a\u00020#2\n\u0010,\u001a\u00060\u0007j\u0002`-H\b¢\u0006\u0002\u0010.J\u0015\u0010/\u001a\u00020\u00162\u0006\u00100\u001a\u000201H\u0000¢\u0006\u0002\b2J-\u00103\u001a\b\u0012\u0004\u0012\u0002H 0%\"\u0004\b\u0000\u0010 2\n\u00104\u001a\u0006\u0012\u0002\b\u00030&2\u0006\u00105\u001a\u000206H\u0000¢\u0006\u0002\b7J\u0018\u00108\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010'\u001a\u00020(H\u0002J#\u00109\u001a\u00020\u00162\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00180;2\u0006\u0010'\u001a\u00020(H\u0000¢\u0006\u0002\b<J1\u0010=\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t2\n\u00104\u001a\u0006\u0012\u0002\b\u00030&2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010+\u001a\u00020#H\u0000¢\u0006\u0002\b>J=\u0010?\u001a\u0004\u0018\u0001H \"\u0004\b\u0000\u0010 2\b\u0010\"\u001a\u0004\u0018\u00010#2\n\u00104\u001a\u0006\u0012\u0002\b\u00030&2\u0006\u0010+\u001a\u00020#2\u0006\u00105\u001a\u000206H\u0000¢\u0006\u0004\b@\u0010AJ2\u0010B\u001a\u00020\u00162\u0006\u0010'\u001a\u00020(2\n\u0010C\u001a\u00060\u0007j\u0002`\b2\n\u0010D\u001a\u0006\u0012\u0002\b\u00030\t2\b\b\u0002\u0010E\u001a\u00020(H\u0007J\u0006\u0010F\u001a\u00020\u000eJ\u0010\u0010G\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u001b\u0010H\u001a\u00020\u00162\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00180;H\u0000¢\u0006\u0002\bIR\"\u0010\u0005\u001a\u0016\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR2\u0010\f\u001a&\u0012\u0004\u0012\u00020\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\rj\u0012\u0012\u0004\u0012\u00020\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f`\u0010X\u0004¢\u0006\u0002\n\u0000R%\u0010\u0011\u001a\u0016\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006J"}, d2 = {"Lorg/koin/core/registry/InstanceRegistry;", "", "_koin", "Lorg/koin/core/Koin;", "(Lorg/koin/core/Koin;)V", "_instances", "", "", "Lorg/koin/core/definition/IndexKey;", "Lorg/koin/core/instance/InstanceFactory;", "get_koin", "()Lorg/koin/core/Koin;", "eagerInstances", "Ljava/util/HashMap;", "", "Lorg/koin/core/instance/SingleInstanceFactory;", "Lkotlin/collections/HashMap;", "instances", "", "getInstances", "()Ljava/util/Map;", "addAllEagerInstances", "", "module", "Lorg/koin/core/module/Module;", "close", "close$koin_core", "createAllEagerInstances", "createAllEagerInstances$koin_core", "createEagerInstances", "", "declareRootInstance", "T", "instance", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "secondaryTypes", "", "Lkotlin/reflect/KClass;", "allowOverride", "", "(Ljava/lang/Object;Lorg/koin/core/qualifier/Qualifier;Ljava/util/List;Z)V", "declareScopedInstance", "scopeQualifier", "scopeID", "Lorg/koin/core/scope/ScopeID;", "(Ljava/lang/Object;Lorg/koin/core/qualifier/Qualifier;Ljava/util/List;ZLorg/koin/core/qualifier/Qualifier;Ljava/lang/String;)V", "dropScopeInstances", "scope", "Lorg/koin/core/scope/Scope;", "dropScopeInstances$koin_core", "getAll", "clazz", "instanceContext", "Lorg/koin/core/instance/InstanceContext;", "getAll$koin_core", "loadModule", "loadModules", "modules", "", "loadModules$koin_core", "resolveDefinition", "resolveDefinition$koin_core", "resolveInstance", "resolveInstance$koin_core", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lorg/koin/core/instance/InstanceContext;)Ljava/lang/Object;", "saveMapping", "mapping", "factory", "logWarning", "size", "unloadModule", "unloadModules", "unloadModules$koin_core", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: InstanceRegistry.kt */
public final class InstanceRegistry {
    private final Map<String, InstanceFactory<?>> _instances = KoinPlatformTools.INSTANCE.safeHashMap();
    private final Koin _koin;
    private final HashMap<Integer, SingleInstanceFactory<?>> eagerInstances = new HashMap<>();

    public InstanceRegistry(Koin koin) {
        Intrinsics.checkNotNullParameter(koin, "_koin");
        this._koin = koin;
    }

    public final Koin get_koin() {
        return this._koin;
    }

    public final Map<String, InstanceFactory<?>> getInstances() {
        return this._instances;
    }

    public final void loadModules$koin_core(Set<Module> set, boolean z) {
        Intrinsics.checkNotNullParameter(set, SentryEvent.JsonKeys.MODULES);
        for (Module module : set) {
            loadModule(module, z);
            addAllEagerInstances(module);
        }
    }

    private final void addAllEagerInstances(Module module) {
        for (SingleInstanceFactory singleInstanceFactory : module.getEagerInstances()) {
            this.eagerInstances.put(Integer.valueOf(singleInstanceFactory.hashCode()), singleInstanceFactory);
        }
    }

    public final void createAllEagerInstances$koin_core() {
        Collection<SingleInstanceFactory<?>> values = this.eagerInstances.values();
        Intrinsics.checkNotNullExpressionValue(values, "eagerInstances.values");
        createEagerInstances(values);
        this.eagerInstances.clear();
    }

    private final void loadModule(Module module, boolean z) {
        for (Map.Entry entry : module.getMappings().entrySet()) {
            saveMapping$default(this, z, (String) entry.getKey(), (InstanceFactory) entry.getValue(), false, 8, (Object) null);
        }
    }

    public static /* synthetic */ void saveMapping$default(InstanceRegistry instanceRegistry, boolean z, String str, InstanceFactory instanceFactory, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z2 = true;
        }
        instanceRegistry.saveMapping(z, str, instanceFactory, z2);
    }

    public final void saveMapping(boolean z, String str, InstanceFactory<?> instanceFactory, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "mapping");
        Intrinsics.checkNotNullParameter(instanceFactory, "factory");
        if (this._instances.containsKey(str)) {
            if (!z) {
                ModuleKt.overrideError(instanceFactory, str);
            } else if (z2) {
                Logger logger = this._koin.getLogger();
                String str2 = "(+) override index '" + str + "' -> '" + instanceFactory.getBeanDefinition() + '\'';
                Level level = Level.WARNING;
                if (logger.isAt(level)) {
                    logger.display(level, str2);
                }
            }
        }
        Logger logger2 = this._koin.getLogger();
        String str3 = "(+) index '" + str + "' -> '" + instanceFactory.getBeanDefinition() + '\'';
        Level level2 = Level.DEBUG;
        if (logger2.isAt(level2)) {
            logger2.display(level2, str3);
        }
        this._instances.put(str, instanceFactory);
    }

    private final void createEagerInstances(Collection<? extends SingleInstanceFactory<?>> collection) {
        if (!collection.isEmpty()) {
            InstanceContext instanceContext = new InstanceContext(this._koin.getLogger(), this._koin.getScopeRegistry().getRootScope(), (ParametersHolder) null, 4, (DefaultConstructorMarker) null);
            for (SingleInstanceFactory singleInstanceFactory : collection) {
                singleInstanceFactory.get(instanceContext);
            }
        }
    }

    public final InstanceFactory<?> resolveDefinition$koin_core(KClass<?> kClass, Qualifier qualifier, Qualifier qualifier2) {
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        Intrinsics.checkNotNullParameter(qualifier2, "scopeQualifier");
        return this._instances.get(BeanDefinitionKt.indexKey(kClass, qualifier, qualifier2));
    }

    public final <T> T resolveInstance$koin_core(Qualifier qualifier, KClass<?> kClass, Qualifier qualifier2, InstanceContext instanceContext) {
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        Intrinsics.checkNotNullParameter(qualifier2, "scopeQualifier");
        Intrinsics.checkNotNullParameter(instanceContext, "instanceContext");
        InstanceFactory<?> resolveDefinition$koin_core = resolveDefinition$koin_core(kClass, qualifier, qualifier2);
        T t = resolveDefinition$koin_core != null ? resolveDefinition$koin_core.get(instanceContext) : null;
        if (t == null) {
            return null;
        }
        return t;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: org.koin.core.instance.ScopedInstanceFactory} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void declareScopedInstance$default(org.koin.core.registry.InstanceRegistry r12, java.lang.Object r13, org.koin.core.qualifier.Qualifier r14, java.util.List r15, boolean r16, org.koin.core.qualifier.Qualifier r17, java.lang.String r18, int r19, java.lang.Object r20) {
        /*
            r0 = r13
            r1 = r18
            r2 = r19 & 2
            r3 = 0
            if (r2 == 0) goto L_0x000a
            r7 = r3
            goto L_0x000b
        L_0x000a:
            r7 = r14
        L_0x000b:
            r2 = r19 & 4
            if (r2 == 0) goto L_0x0015
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            r10 = r2
            goto L_0x0016
        L_0x0015:
            r10 = r15
        L_0x0016:
            r2 = r19 & 8
            if (r2 == 0) goto L_0x001c
            r2 = 1
            goto L_0x001e
        L_0x001c:
            r2 = r16
        L_0x001e:
            java.lang.String r4 = "secondaryTypes"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r4)
            java.lang.String r4 = "scopeQualifier"
            r5 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
            java.lang.String r4 = "scopeID"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            org.koin.core.definition.Kind r9 = org.koin.core.definition.Kind.Scoped
            kotlin.jvm.internal.Intrinsics.needClassReification()
            org.koin.core.registry.InstanceRegistry$declareScopedInstance$def$1 r4 = new org.koin.core.registry.InstanceRegistry$declareScopedInstance$def$1
            r4.<init>(r13)
            r8 = r4
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            org.koin.core.definition.BeanDefinition r11 = new org.koin.core.definition.BeanDefinition
            java.lang.String r4 = "T"
            r6 = 4
            kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r6, r4)
            java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            r4 = r11
            r4.<init>(r5, r6, r7, r8, r9, r10)
            kotlin.reflect.KClass r4 = r11.getPrimaryType()
            org.koin.core.qualifier.Qualifier r5 = r11.getQualifier()
            org.koin.core.qualifier.Qualifier r6 = r11.getScopeQualifier()
            java.lang.String r4 = org.koin.core.definition.BeanDefinitionKt.indexKey(r4, r5, r6)
            java.util.Map r5 = r12.getInstances()
            java.lang.Object r5 = r5.get(r4)
            boolean r6 = r5 instanceof org.koin.core.instance.ScopedInstanceFactory
            if (r6 == 0) goto L_0x006d
            r3 = r5
            org.koin.core.instance.ScopedInstanceFactory r3 = (org.koin.core.instance.ScopedInstanceFactory) r3
        L_0x006d:
            if (r3 == 0) goto L_0x007b
            java.lang.String r2 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13, r2)
            r2 = r0
            java.lang.Object r2 = (java.lang.Object) r2
            r3.refreshInstance(r1, r13)
            goto L_0x00c9
        L_0x007b:
            org.koin.core.instance.ScopedInstanceFactory r0 = new org.koin.core.instance.ScopedInstanceFactory
            r0.<init>(r11)
            org.koin.core.instance.InstanceFactory r0 = (org.koin.core.instance.InstanceFactory) r0
            r1 = 0
            r3 = 8
            r5 = 0
            r13 = r12
            r14 = r2
            r15 = r4
            r16 = r0
            r17 = r1
            r18 = r3
            r19 = r5
            saveMapping$default(r13, r14, r15, r16, r17, r18, r19)
            java.util.List r1 = r11.getSecondaryTypes()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x009e:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00c9
            java.lang.Object r3 = r1.next()
            kotlin.reflect.KClass r3 = (kotlin.reflect.KClass) r3
            org.koin.core.qualifier.Qualifier r4 = r11.getQualifier()
            org.koin.core.qualifier.Qualifier r5 = r11.getScopeQualifier()
            java.lang.String r3 = org.koin.core.definition.BeanDefinitionKt.indexKey(r3, r4, r5)
            r4 = 0
            r5 = 8
            r6 = 0
            r13 = r12
            r14 = r2
            r15 = r3
            r16 = r0
            r17 = r4
            r18 = r5
            r19 = r6
            saveMapping$default(r13, r14, r15, r16, r17, r18, r19)
            goto L_0x009e
        L_0x00c9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.registry.InstanceRegistry.declareScopedInstance$default(org.koin.core.registry.InstanceRegistry, java.lang.Object, org.koin.core.qualifier.Qualifier, java.util.List, boolean, org.koin.core.qualifier.Qualifier, java.lang.String, int, java.lang.Object):void");
    }

    public final /* synthetic */ <T> void declareScopedInstance(T t, Qualifier qualifier, List<? extends KClass<?>> list, boolean z, Qualifier qualifier2, String str) {
        T t2 = t;
        String str2 = str;
        List<? extends KClass<?>> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "secondaryTypes");
        Qualifier qualifier3 = qualifier2;
        Intrinsics.checkNotNullParameter(qualifier3, "scopeQualifier");
        Intrinsics.checkNotNullParameter(str2, "scopeID");
        Kind kind = Kind.Scoped;
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition beanDefinition = new BeanDefinition(qualifier3, Reflection.getOrCreateKotlinClass(Object.class), qualifier, new InstanceRegistry$declareScopedInstance$def$1(t2), kind, list2);
        String indexKey = BeanDefinitionKt.indexKey(beanDefinition.getPrimaryType(), beanDefinition.getQualifier(), beanDefinition.getScopeQualifier());
        InstanceFactory<?> instanceFactory = getInstances().get(indexKey);
        ScopedInstanceFactory scopedInstanceFactory = instanceFactory instanceof ScopedInstanceFactory ? (ScopedInstanceFactory) instanceFactory : null;
        if (scopedInstanceFactory != null) {
            Intrinsics.checkNotNull(t2, "null cannot be cast to non-null type kotlin.Any");
            Object obj = (Object) t2;
            scopedInstanceFactory.refreshInstance(str2, t2);
            return;
        }
        InstanceFactory scopedInstanceFactory2 = new ScopedInstanceFactory(beanDefinition);
        saveMapping$default(this, z, indexKey, scopedInstanceFactory2, false, 8, (Object) null);
        for (KClass indexKey2 : beanDefinition.getSecondaryTypes()) {
            saveMapping$default(this, z, BeanDefinitionKt.indexKey(indexKey2, beanDefinition.getQualifier(), beanDefinition.getScopeQualifier()), scopedInstanceFactory2, false, 8, (Object) null);
        }
    }

    public static /* synthetic */ void declareRootInstance$default(InstanceRegistry instanceRegistry, Object obj, Qualifier qualifier, List list, boolean z, int i, Object obj2) {
        Qualifier qualifier2 = (i & 2) != 0 ? null : qualifier;
        List emptyList = (i & 4) != 0 ? CollectionsKt.emptyList() : list;
        boolean z2 = (i & 8) != 0 ? true : z;
        Intrinsics.checkNotNullParameter(emptyList, "secondaryTypes");
        Qualifier scopeQualifier = instanceRegistry.get_koin().getScopeRegistry().getRootScope().getScopeQualifier();
        Kind kind = Kind.Scoped;
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition beanDefinition = new BeanDefinition(scopeQualifier, Reflection.getOrCreateKotlinClass(Object.class), qualifier2, new InstanceRegistry$declareRootInstance$def$1(obj), kind, emptyList);
        InstanceFactory singleInstanceFactory = new SingleInstanceFactory(beanDefinition);
        saveMapping$default(instanceRegistry, z2, BeanDefinitionKt.indexKey(beanDefinition.getPrimaryType(), beanDefinition.getQualifier(), beanDefinition.getScopeQualifier()), singleInstanceFactory, false, 8, (Object) null);
        for (KClass indexKey : beanDefinition.getSecondaryTypes()) {
            saveMapping$default(instanceRegistry, z2, BeanDefinitionKt.indexKey(indexKey, beanDefinition.getQualifier(), beanDefinition.getScopeQualifier()), singleInstanceFactory, false, 8, (Object) null);
        }
    }

    public final /* synthetic */ <T> void declareRootInstance(T t, Qualifier qualifier, List<? extends KClass<?>> list, boolean z) {
        List<? extends KClass<?>> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "secondaryTypes");
        Qualifier scopeQualifier = get_koin().getScopeRegistry().getRootScope().getScopeQualifier();
        Kind kind = Kind.Scoped;
        Intrinsics.needClassReification();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition beanDefinition = new BeanDefinition(scopeQualifier, Reflection.getOrCreateKotlinClass(Object.class), qualifier, new InstanceRegistry$declareRootInstance$def$1(t), kind, list2);
        InstanceFactory singleInstanceFactory = new SingleInstanceFactory(beanDefinition);
        saveMapping$default(this, z, BeanDefinitionKt.indexKey(beanDefinition.getPrimaryType(), beanDefinition.getQualifier(), beanDefinition.getScopeQualifier()), singleInstanceFactory, false, 8, (Object) null);
        for (KClass indexKey : beanDefinition.getSecondaryTypes()) {
            saveMapping$default(this, z, BeanDefinitionKt.indexKey(indexKey, beanDefinition.getQualifier(), beanDefinition.getScopeQualifier()), singleInstanceFactory, false, 8, (Object) null);
        }
    }

    public final void dropScopeInstances$koin_core(Scope scope) {
        Intrinsics.checkNotNullParameter(scope, PermissionsResponse.SCOPE_KEY);
        Collection arrayList = new ArrayList();
        for (Object next : this._instances.values()) {
            if (next instanceof ScopedInstanceFactory) {
                arrayList.add(next);
            }
        }
        for (ScopedInstanceFactory drop : (List) arrayList) {
            drop.drop(scope);
        }
    }

    public final void close$koin_core() {
        for (Map.Entry next : this._instances.entrySet()) {
            String str = (String) next.getKey();
            ((InstanceFactory) next.getValue()).dropAll();
        }
        this._instances.clear();
    }

    public final <T> List<T> getAll$koin_core(KClass<?> kClass, InstanceContext instanceContext) {
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        Intrinsics.checkNotNullParameter(instanceContext, "instanceContext");
        Collection arrayList = new ArrayList();
        for (Object next : this._instances.values()) {
            if (Intrinsics.areEqual((Object) ((InstanceFactory) next).getBeanDefinition().getScopeQualifier(), (Object) instanceContext.getScope().getScopeQualifier())) {
                arrayList.add(next);
            }
        }
        Collection arrayList2 = new ArrayList();
        for (Object next2 : (List) arrayList) {
            InstanceFactory instanceFactory = (InstanceFactory) next2;
            if (Intrinsics.areEqual((Object) instanceFactory.getBeanDefinition().getPrimaryType(), (Object) kClass) || instanceFactory.getBeanDefinition().getSecondaryTypes().contains(kClass)) {
                arrayList2.add(next2);
            }
        }
        Iterable<InstanceFactory> distinct = CollectionsKt.distinct((List) arrayList2);
        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(distinct, 10));
        for (InstanceFactory instanceFactory2 : distinct) {
            arrayList3.add(instanceFactory2.get(instanceContext));
        }
        return (List) arrayList3;
    }

    public final void unloadModules$koin_core(Set<Module> set) {
        Intrinsics.checkNotNullParameter(set, SentryEvent.JsonKeys.MODULES);
        for (Module unloadModule : set) {
            unloadModule(unloadModule);
        }
    }

    private final void unloadModule(Module module) {
        Set<String> keySet = module.getMappings().keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "module.mappings.keys");
        for (String str : keySet) {
            if (this._instances.containsKey(str)) {
                InstanceFactory instanceFactory = this._instances.get(str);
                if (instanceFactory != null) {
                    instanceFactory.dropAll();
                }
                this._instances.remove(str);
            }
        }
    }

    public final int size() {
        return this._instances.size();
    }
}
