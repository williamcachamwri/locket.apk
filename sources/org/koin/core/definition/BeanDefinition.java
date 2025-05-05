package org.koin.core.definition;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.koin.core.module.KoinDslMarker;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.ext.KClassExtKt;

@KoinDslMarker
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002Bj\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012'\u0010\b\u001a#\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\tj\b\u0012\u0004\u0012\u00028\u0000`\f¢\u0006\u0002\b\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0012\b\u0002\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0011¢\u0006\u0002\u0010\u0012J\t\u00100\u001a\u00020\u0004HÆ\u0003J\r\u00101\u001a\u0006\u0012\u0002\b\u00030\u0006HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0004HÆ\u0003J*\u00103\u001a#\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\tj\b\u0012\u0004\u0012\u00028\u0000`\f¢\u0006\u0002\b\rHÆ\u0003J\t\u00104\u001a\u00020\u000fHÆ\u0003J\u0013\u00105\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0011HÆ\u0003J|\u00106\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\f\b\u0002\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042)\b\u0002\u0010\b\u001a#\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\tj\b\u0012\u0004\u0012\u00028\u0000`\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0012\b\u0002\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0011HÆ\u0001J\u0013\u00107\u001a\u00020\u00142\b\u00108\u001a\u0004\u0018\u00010\u0002H\u0002J\u0012\u00109\u001a\u00020\u00142\n\u0010:\u001a\u0006\u0012\u0002\b\u00030\u0006J\b\u0010;\u001a\u00020<H\u0016J$\u0010=\u001a\u00020\u00142\n\u0010:\u001a\u0006\u0012\u0002\b\u00030\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010>\u001a\u00020\u0004J\b\u0010?\u001a\u00020@H\u0016R$\u0010\u0013\u001a\u00020\u00148\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R2\u0010\b\u001a#\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00028\u00000\tj\b\u0012\u0004\u0012\u00028\u0000`\f¢\u0006\u0002\b\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0015\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010(R$\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u0006A"}, d2 = {"Lorg/koin/core/definition/BeanDefinition;", "T", "", "scopeQualifier", "Lorg/koin/core/qualifier/Qualifier;", "primaryType", "Lkotlin/reflect/KClass;", "qualifier", "definition", "Lkotlin/Function2;", "Lorg/koin/core/scope/Scope;", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/definition/Definition;", "Lkotlin/ExtensionFunctionType;", "kind", "Lorg/koin/core/definition/Kind;", "secondaryTypes", "", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function2;Lorg/koin/core/definition/Kind;Ljava/util/List;)V", "_createdAtStart", "", "get_createdAtStart$annotations", "()V", "get_createdAtStart", "()Z", "set_createdAtStart", "(Z)V", "callbacks", "Lorg/koin/core/definition/Callbacks;", "getCallbacks", "()Lorg/koin/core/definition/Callbacks;", "setCallbacks", "(Lorg/koin/core/definition/Callbacks;)V", "getDefinition", "()Lkotlin/jvm/functions/Function2;", "getKind", "()Lorg/koin/core/definition/Kind;", "getPrimaryType", "()Lkotlin/reflect/KClass;", "getQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "setQualifier", "(Lorg/koin/core/qualifier/Qualifier;)V", "getScopeQualifier", "getSecondaryTypes", "()Ljava/util/List;", "setSecondaryTypes", "(Ljava/util/List;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hasType", "clazz", "hashCode", "", "is", "scopeDefinition", "toString", "", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeanDefinition.kt */
public final class BeanDefinition<T> {
    private boolean _createdAtStart;
    private Callbacks<T> callbacks;
    private final Function2<Scope, ParametersHolder, T> definition;
    private final Kind kind;
    private final KClass<?> primaryType;
    private Qualifier qualifier;
    private final Qualifier scopeQualifier;
    private List<? extends KClass<?>> secondaryTypes;

    public static /* synthetic */ BeanDefinition copy$default(BeanDefinition beanDefinition, Qualifier qualifier2, KClass<?> kClass, Qualifier qualifier3, Function2<Scope, ParametersHolder, T> function2, Kind kind2, List<? extends KClass<?>> list, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier2 = beanDefinition.scopeQualifier;
        }
        if ((i & 2) != 0) {
            kClass = beanDefinition.primaryType;
        }
        KClass<?> kClass2 = kClass;
        if ((i & 4) != 0) {
            qualifier3 = beanDefinition.qualifier;
        }
        Qualifier qualifier4 = qualifier3;
        if ((i & 8) != 0) {
            function2 = beanDefinition.definition;
        }
        Function2<Scope, ParametersHolder, T> function22 = function2;
        if ((i & 16) != 0) {
            kind2 = beanDefinition.kind;
        }
        Kind kind3 = kind2;
        if ((i & 32) != 0) {
            list = beanDefinition.secondaryTypes;
        }
        return beanDefinition.copy(qualifier2, kClass2, qualifier4, function22, kind3, list);
    }

    public static /* synthetic */ void get_createdAtStart$annotations() {
    }

    public final Qualifier component1() {
        return this.scopeQualifier;
    }

    public final KClass<?> component2() {
        return this.primaryType;
    }

    public final Qualifier component3() {
        return this.qualifier;
    }

    public final Function2<Scope, ParametersHolder, T> component4() {
        return this.definition;
    }

    public final Kind component5() {
        return this.kind;
    }

    public final List<KClass<?>> component6() {
        return this.secondaryTypes;
    }

    public final BeanDefinition<T> copy(Qualifier qualifier2, KClass<?> kClass, Qualifier qualifier3, Function2<? super Scope, ? super ParametersHolder, ? extends T> function2, Kind kind2, List<? extends KClass<?>> list) {
        Intrinsics.checkNotNullParameter(qualifier2, "scopeQualifier");
        Intrinsics.checkNotNullParameter(kClass, "primaryType");
        Intrinsics.checkNotNullParameter(function2, "definition");
        Intrinsics.checkNotNullParameter(kind2, "kind");
        Intrinsics.checkNotNullParameter(list, "secondaryTypes");
        return new BeanDefinition(qualifier2, kClass, qualifier3, function2, kind2, list);
    }

    public BeanDefinition(Qualifier qualifier2, KClass<?> kClass, Qualifier qualifier3, Function2<? super Scope, ? super ParametersHolder, ? extends T> function2, Kind kind2, List<? extends KClass<?>> list) {
        Intrinsics.checkNotNullParameter(qualifier2, "scopeQualifier");
        Intrinsics.checkNotNullParameter(kClass, "primaryType");
        Intrinsics.checkNotNullParameter(function2, "definition");
        Intrinsics.checkNotNullParameter(kind2, "kind");
        Intrinsics.checkNotNullParameter(list, "secondaryTypes");
        this.scopeQualifier = qualifier2;
        this.primaryType = kClass;
        this.qualifier = qualifier3;
        this.definition = function2;
        this.kind = kind2;
        this.secondaryTypes = list;
        this.callbacks = new Callbacks<>((Function1) null, 1, (DefaultConstructorMarker) null);
    }

    public final Qualifier getScopeQualifier() {
        return this.scopeQualifier;
    }

    public final KClass<?> getPrimaryType() {
        return this.primaryType;
    }

    public final Qualifier getQualifier() {
        return this.qualifier;
    }

    public final void setQualifier(Qualifier qualifier2) {
        this.qualifier = qualifier2;
    }

    public final Function2<Scope, ParametersHolder, T> getDefinition() {
        return this.definition;
    }

    public final Kind getKind() {
        return this.kind;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BeanDefinition(Qualifier qualifier2, KClass kClass, Qualifier qualifier3, Function2 function2, Kind kind2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(qualifier2, kClass, (i & 4) != 0 ? null : qualifier3, function2, kind2, (i & 32) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<KClass<?>> getSecondaryTypes() {
        return this.secondaryTypes;
    }

    public final void setSecondaryTypes(List<? extends KClass<?>> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.secondaryTypes = list;
    }

    public final Callbacks<T> getCallbacks() {
        return this.callbacks;
    }

    public final void setCallbacks(Callbacks<T> callbacks2) {
        Intrinsics.checkNotNullParameter(callbacks2, "<set-?>");
        this.callbacks = callbacks2;
    }

    public final boolean get_createdAtStart() {
        return this._createdAtStart;
    }

    public final void set_createdAtStart(boolean z) {
        this._createdAtStart = z;
    }

    public String toString() {
        String str;
        String kind2 = this.kind.toString();
        String str2 = "'" + KClassExtKt.getFullName(this.primaryType) + '\'';
        String str3 = "";
        if (this.qualifier == null || (str = ",qualifier:" + this.qualifier) == null) {
            str = str3;
        }
        String str4 = Intrinsics.areEqual((Object) this.scopeQualifier, (Object) ScopeRegistry.Companion.getRootScopeQualifier()) ? str3 : ",scope:" + this.scopeQualifier;
        if (!this.secondaryTypes.isEmpty()) {
            str3 = ",binds:" + CollectionsKt.joinToString$default(this.secondaryTypes, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, BeanDefinition$toString$defOtherTypes$typesAsString$1.INSTANCE, 30, (Object) null);
        }
        return "[" + kind2 + AbstractJsonLexerKt.COLON + str2 + str + str4 + str3 + AbstractJsonLexerKt.END_LIST;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.koin.core.definition.BeanDefinition<*>");
        BeanDefinition beanDefinition = (BeanDefinition) obj;
        if (Intrinsics.areEqual((Object) this.primaryType, (Object) beanDefinition.primaryType) && Intrinsics.areEqual((Object) this.qualifier, (Object) beanDefinition.qualifier) && Intrinsics.areEqual((Object) this.scopeQualifier, (Object) beanDefinition.scopeQualifier)) {
            return true;
        }
        return false;
    }

    public final boolean hasType(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        return Intrinsics.areEqual((Object) this.primaryType, (Object) kClass) || this.secondaryTypes.contains(kClass);
    }

    public final boolean is(KClass<?> kClass, Qualifier qualifier2, Qualifier qualifier3) {
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        Intrinsics.checkNotNullParameter(qualifier3, "scopeDefinition");
        return hasType(kClass) && Intrinsics.areEqual((Object) this.qualifier, (Object) qualifier2) && Intrinsics.areEqual((Object) this.scopeQualifier, (Object) qualifier3);
    }

    public int hashCode() {
        Qualifier qualifier2 = this.qualifier;
        return ((((qualifier2 != null ? qualifier2.hashCode() : 0) * 31) + this.primaryType.hashCode()) * 31) + this.scopeQualifier.hashCode();
    }
}
