package org.koin.core.scope;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.Koin;
import org.koin.core.error.ClosedScopeException;
import org.koin.core.error.MissingPropertyException;
import org.koin.core.error.NoBeanDefFoundException;
import org.koin.core.instance.InstanceContext;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.module.KoinDslMarker;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.ext.KClassExtKt;
import org.koin.mp.KoinPlatformTimeTools;
import org.koin.mp.KoinPlatformTools;

@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\b\u0018\u00002\u00060\u0001j\u0002`\u0002B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010.\u001a\u00020/J\t\u00100\u001a\u00020\u0004HÆ\u0003J\r\u00101\u001a\u00060\u0006j\u0002`\u0007HÆ\u0003J\t\u00102\u001a\u00020\tHÆ\u0003J\u000e\u00103\u001a\u00020\u000bHÀ\u0003¢\u0006\u0002\b4J5\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\f\b\u0002\u0010\u0005\u001a\u00060\u0006j\u0002`\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u001b\u00106\u001a\u00020/2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000008H\u0000¢\u0006\u0002\b9JH\u0010:\u001a\u00020/\"\u0006\b\u0000\u0010;\u0018\u00012\u0006\u0010<\u001a\u0002H;2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00042\u0012\b\u0002\u0010>\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030?082\b\b\u0002\u0010@\u001a\u00020\tH\b¢\u0006\u0002\u0010AJ\u0013\u0010B\u001a\u00020\t2\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0003JA\u0010D\u001a\u0004\u0018\u0001H;\"\u0004\b\u0000\u0010;2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\b\u0010=\u001a\u0004\u0018\u00010\u00042\u0014\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010Gj\u0004\u0018\u0001`HH\u0002¢\u0006\u0002\u0010IJA\u0010J\u001a\u0002H;\"\u0004\b\u0000\u0010;2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010Gj\u0004\u0018\u0001`H¢\u0006\u0002\u0010IJA\u0010J\u001a\u0002H;\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u00012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00042\u0016\b\n\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010Gj\u0004\u0018\u0001`HH\bø\u0001\u0000¢\u0006\u0002\u0010KJ\u001b\u0010L\u001a\b\u0012\u0004\u0012\u0002H;08\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u0001H\bJ\u001e\u0010L\u001a\b\u0012\u0004\u0012\u0002H;08\"\u0004\b\u0000\u0010;2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?J!\u0010M\u001a\u0004\u0018\u0001H;\"\u0004\b\u0000\u0010;2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?H\u0002¢\u0006\u0002\u0010NJ\u0006\u0010O\u001a\u00020\u000bJC\u0010P\u001a\u0004\u0018\u0001H;\"\u0004\b\u0000\u0010;2\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00042\u0016\b\u0002\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010Gj\u0004\u0018\u0001`H¢\u0006\u0002\u0010IJC\u0010P\u001a\u0004\u0018\u0001H;\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u00012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00042\u0016\b\n\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010Gj\u0004\u0018\u0001`HH\bø\u0001\u0000¢\u0006\u0002\u0010KJ\u001d\u0010Q\u001a\u0002H;\"\b\b\u0000\u0010;*\u00020\u00012\u0006\u0010R\u001a\u00020\u0006¢\u0006\u0002\u0010SJ%\u0010Q\u001a\u0002H;\"\b\b\u0000\u0010;*\u00020\u00012\u0006\u0010R\u001a\u00020\u00062\u0006\u0010T\u001a\u0002H;¢\u0006\u0002\u0010UJ\u001f\u0010V\u001a\u0004\u0018\u0001H;\"\b\b\u0000\u0010;*\u00020\u00012\u0006\u0010R\u001a\u00020\u0006¢\u0006\u0002\u0010SJ\u0012\u0010W\u001a\u00020\u00002\n\u0010X\u001a\u00060\u0006j\u0002`\u0007J\u001c\u0010Y\u001a\u0004\u0018\u0001H;\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u0001H\b¢\u0006\u0002\u0010\u001fJ\t\u0010Z\u001a\u00020[HÖ\u0001JL\u0010\\\u001a\b\u0012\u0004\u0012\u0002H;0]\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u00012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010^\u001a\u00020_2\u0016\b\n\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010Gj\u0004\u0018\u0001`HH\bø\u0001\u0000JN\u0010`\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H;0]\"\n\b\u0000\u0010;\u0018\u0001*\u00020\u00012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010^\u001a\u00020_2\u0016\b\n\u0010F\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010Gj\u0004\u0018\u0001`HH\bø\u0001\u0000J\u0006\u0010a\u001a\u00020\tJ\u001f\u0010b\u001a\u00020/2\u0012\u0010c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000d\"\u00020\u0000¢\u0006\u0002\u0010eJ\u000e\u0010f\u001a\u00020/2\u0006\u0010g\u001a\u00020\u000fJ?\u0010h\u001a\u0002H;\"\u0004\b\u0000\u0010;2\b\u0010=\u001a\u0004\u0018\u00010\u00042\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\u0014\u0010i\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010Gj\u0004\u0018\u0001`HH\u0002¢\u0006\u0002\u0010jJL\u0010k\u001a\u0004\b\u0002H;\"\u0004\b\u0000\u0010;2\b\u0010=\u001a\u0004\u0018\u00010\u00042\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?2\u0006\u0010l\u001a\u00020m2\u0014\u0010i\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010Gj\u0004\u0018\u0001`HH\u0002ø\u0001\u0001¢\u0006\u0002\u0010nJ\u001e\u0010o\u001a\u00020p2\b\u0010=\u001a\u0004\u0018\u00010\u00042\n\u0010E\u001a\u0006\u0012\u0002\b\u00030?H\u0002J\b\u0010q\u001a\u00020\u0006H\u0016J\u001f\u0010r\u001a\u00020/2\u0012\u0010c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000d\"\u00020\u0000¢\u0006\u0002\u0010eR\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u00020\u000b8\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\"\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00178\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u001a\u0010\u001bR&\u0010\u001c\u001a\u0004\u0018\u00010\u00018\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001d\u0010\u0013\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0015\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010$R\u001e\u0010'\u001a\u0012\u0012\u0004\u0012\u00020\u00000\u000ej\b\u0012\u0004\u0012\u00020\u0000`\u0010X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010(\u001a\u00020)8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-\u0002\u000b\n\u0005\b20\u0001\n\u0002\b9¨\u0006s"}, d2 = {"Lorg/koin/core/scope/Scope;", "", "Lorg/koin/mp/Lockable;", "scopeQualifier", "Lorg/koin/core/qualifier/Qualifier;", "id", "", "Lorg/koin/core/scope/ScopeID;", "isRoot", "", "_koin", "Lorg/koin/core/Koin;", "(Lorg/koin/core/qualifier/Qualifier;Ljava/lang/String;ZLorg/koin/core/Koin;)V", "_callbacks", "Ljava/util/ArrayList;", "Lorg/koin/core/scope/ScopeCallback;", "Lkotlin/collections/ArrayList;", "_closed", "get_koin$annotations", "()V", "get_koin", "()Lorg/koin/core/Koin;", "_parameterStack", "Lkotlin/collections/ArrayDeque;", "Lorg/koin/core/parameter/ParametersHolder;", "get_parameterStack$annotations", "get_parameterStack", "()Lkotlin/collections/ArrayDeque;", "_source", "get_source$annotations", "get_source", "()Ljava/lang/Object;", "set_source", "(Ljava/lang/Object;)V", "closed", "getClosed", "()Z", "getId", "()Ljava/lang/String;", "linkedScopes", "logger", "Lorg/koin/core/logger/Logger;", "getLogger", "()Lorg/koin/core/logger/Logger;", "getScopeQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "close", "", "component1", "component2", "component3", "component4", "component4$koin_core", "copy", "create", "links", "", "create$koin_core", "declare", "T", "instance", "qualifier", "secondaryTypes", "Lkotlin/reflect/KClass;", "allowOverride", "(Ljava/lang/Object;Lorg/koin/core/qualifier/Qualifier;Ljava/util/List;Z)V", "equals", "other", "findInOtherScope", "clazz", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lkotlin/reflect/KClass;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "get", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getAll", "getFromSource", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "getKoin", "getOrNull", "getProperty", "key", "(Ljava/lang/String;)Ljava/lang/Object;", "defaultValue", "(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getPropertyOrNull", "getScope", "scopeID", "getSource", "hashCode", "", "inject", "Lkotlin/Lazy;", "mode", "Lkotlin/LazyThreadSafetyMode;", "injectOrNull", "isNotClosed", "linkTo", "scopes", "", "([Lorg/koin/core/scope/Scope;)V", "registerCallback", "callback", "resolveInstance", "parameterDef", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "resolveValue", "instanceContext", "Lorg/koin/core/instance/InstanceContext;", "(Lorg/koin/core/qualifier/Qualifier;Lkotlin/reflect/KClass;Lorg/koin/core/instance/InstanceContext;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "throwDefinitionNotFound", "", "toString", "unlink", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KoinDslMarker
/* compiled from: Scope.kt */
public final class Scope {
    /* access modifiers changed from: private */
    public final ArrayList<ScopeCallback> _callbacks;
    /* access modifiers changed from: private */
    public boolean _closed;
    private final Koin _koin;
    private final ArrayDeque<ParametersHolder> _parameterStack;
    private Object _source;
    private final String id;
    private final boolean isRoot;
    private final ArrayList<Scope> linkedScopes;
    private final Qualifier scopeQualifier;

    public static /* synthetic */ Scope copy$default(Scope scope, Qualifier qualifier, String str, boolean z, Koin koin, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = scope.scopeQualifier;
        }
        if ((i & 2) != 0) {
            str = scope.id;
        }
        if ((i & 4) != 0) {
            z = scope.isRoot;
        }
        if ((i & 8) != 0) {
            koin = scope._koin;
        }
        return scope.copy(qualifier, str, z, koin);
    }

    public static /* synthetic */ void get_koin$annotations() {
    }

    public static /* synthetic */ void get_parameterStack$annotations() {
    }

    public static /* synthetic */ void get_source$annotations() {
    }

    public final Qualifier component1() {
        return this.scopeQualifier;
    }

    public final String component2() {
        return this.id;
    }

    public final boolean component3() {
        return this.isRoot;
    }

    public final Koin component4$koin_core() {
        return this._koin;
    }

    public final Scope copy(Qualifier qualifier, String str, boolean z, Koin koin) {
        Intrinsics.checkNotNullParameter(qualifier, "scopeQualifier");
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(koin, "_koin");
        return new Scope(qualifier, str, z, koin);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        Scope scope = (Scope) obj;
        return Intrinsics.areEqual((Object) this.scopeQualifier, (Object) scope.scopeQualifier) && Intrinsics.areEqual((Object) this.id, (Object) scope.id) && this.isRoot == scope.isRoot && Intrinsics.areEqual((Object) this._koin, (Object) scope._koin);
    }

    public int hashCode() {
        int hashCode = ((this.scopeQualifier.hashCode() * 31) + this.id.hashCode()) * 31;
        boolean z = this.isRoot;
        if (z) {
            z = true;
        }
        return ((hashCode + (z ? 1 : 0)) * 31) + this._koin.hashCode();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Scope(Qualifier qualifier, String str, boolean z, Koin koin, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(qualifier, str, (i & 4) != 0 ? false : z, koin);
    }

    public final Qualifier getScopeQualifier() {
        return this.scopeQualifier;
    }

    public final String getId() {
        return this.id;
    }

    public final boolean isRoot() {
        return this.isRoot;
    }

    public final Koin get_koin() {
        return this._koin;
    }

    public Scope(Qualifier qualifier, String str, boolean z, Koin koin) {
        Intrinsics.checkNotNullParameter(qualifier, "scopeQualifier");
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(koin, "_koin");
        this.scopeQualifier = qualifier;
        this.id = str;
        this.isRoot = z;
        this._koin = koin;
        this.linkedScopes = new ArrayList<>();
        this._callbacks = new ArrayList<>();
        this._parameterStack = new ArrayDeque<>();
    }

    public final Object get_source() {
        return this._source;
    }

    public final void set_source(Object obj) {
        this._source = obj;
    }

    public final boolean getClosed() {
        return this._closed;
    }

    public final boolean isNotClosed() {
        return !getClosed();
    }

    public final ArrayDeque<ParametersHolder> get_parameterStack() {
        return this._parameterStack;
    }

    public final Logger getLogger() {
        return this._koin.getLogger();
    }

    public final void create$koin_core(List<Scope> list) {
        Intrinsics.checkNotNullParameter(list, "links");
        this.linkedScopes.addAll(list);
    }

    public final void linkTo(Scope... scopeArr) {
        Intrinsics.checkNotNullParameter(scopeArr, "scopes");
        if (!this.isRoot) {
            CollectionsKt.addAll(this.linkedScopes, (T[]) scopeArr);
            return;
        }
        throw new IllegalStateException("Can't add scope link to a root scope".toString());
    }

    public final void unlink(Scope... scopeArr) {
        Intrinsics.checkNotNullParameter(scopeArr, "scopes");
        if (!this.isRoot) {
            CollectionsKt.removeAll(this.linkedScopes, (T[]) scopeArr);
            return;
        }
        throw new IllegalStateException("Can't remove scope link to a root scope".toString());
    }

    public static /* synthetic */ Lazy inject$default(Scope scope, Qualifier qualifier, LazyThreadSafetyMode lazyThreadSafetyMode, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        Intrinsics.checkNotNullParameter(lazyThreadSafetyMode, "mode");
        Intrinsics.needClassReification();
        return LazyKt.lazy(lazyThreadSafetyMode, new Scope$inject$1(scope, qualifier, function0));
    }

    public final /* synthetic */ <T> Lazy<T> inject(Qualifier qualifier, LazyThreadSafetyMode lazyThreadSafetyMode, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter(lazyThreadSafetyMode, "mode");
        Intrinsics.needClassReification();
        return LazyKt.lazy(lazyThreadSafetyMode, new Scope$inject$1(this, qualifier, function0));
    }

    public static /* synthetic */ Lazy injectOrNull$default(Scope scope, Qualifier qualifier, LazyThreadSafetyMode lazyThreadSafetyMode, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            lazyThreadSafetyMode = LazyThreadSafetyMode.SYNCHRONIZED;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        Intrinsics.checkNotNullParameter(lazyThreadSafetyMode, "mode");
        Intrinsics.needClassReification();
        return LazyKt.lazy(lazyThreadSafetyMode, new Scope$injectOrNull$1(scope, qualifier, function0));
    }

    public final /* synthetic */ <T> Lazy<T> injectOrNull(Qualifier qualifier, LazyThreadSafetyMode lazyThreadSafetyMode, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter(lazyThreadSafetyMode, "mode");
        Intrinsics.needClassReification();
        return LazyKt.lazy(lazyThreadSafetyMode, new Scope$injectOrNull$1(this, qualifier, function0));
    }

    public static /* synthetic */ Object get$default(Scope scope, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }

    public final /* synthetic */ <T> T get(Qualifier qualifier, Function0<? extends ParametersHolder> function0) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }

    @Deprecated(message = "No need to use getSource(). You can an use get() directly.", replaceWith = @ReplaceWith(expression = "get()", imports = {}))
    public final /* synthetic */ <T> T getSource() {
        T t = get_source();
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        Object obj = (Object) t;
        return t;
    }

    public static /* synthetic */ Object getOrNull$default(Scope scope, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return scope.getOrNull(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }

    public final /* synthetic */ <T> T getOrNull(Qualifier qualifier, Function0<? extends ParametersHolder> function0) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return getOrNull(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }

    public static /* synthetic */ Object getOrNull$default(Scope scope, KClass kClass, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return scope.getOrNull(kClass, qualifier, function0);
    }

    public final <T> T getOrNull(KClass<?> kClass, Qualifier qualifier, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        try {
            return get(kClass, qualifier, function0);
        } catch (ClosedScopeException unused) {
            Logger logger = this._koin.getLogger();
            String str = "* Scope closed - no instance found for " + KClassExtKt.getFullName(kClass) + " on scope " + this;
            Level level = Level.DEBUG;
            if (!logger.isAt(level)) {
                return null;
            }
            logger.display(level, str);
            return null;
        } catch (NoBeanDefFoundException unused2) {
            Logger logger2 = this._koin.getLogger();
            String str2 = "* No instance found for " + KClassExtKt.getFullName(kClass) + " on scope " + this;
            Level level2 = Level.DEBUG;
            if (!logger2.isAt(level2)) {
                return null;
            }
            logger2.display(level2, str2);
            return null;
        }
    }

    public static /* synthetic */ Object get$default(Scope scope, KClass kClass, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return scope.get(kClass, qualifier, function0);
    }

    public final <T> T get(KClass<?> kClass, Qualifier qualifier, Function0<? extends ParametersHolder> function0) {
        String str;
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        if (!this._koin.getLogger().isAt(Level.DEBUG)) {
            return resolveInstance(qualifier, kClass, function0);
        }
        if (qualifier == null || (str = " with qualifier '" + qualifier + '\'') == null) {
            str = "";
        }
        this._koin.getLogger().display(Level.DEBUG, "|- '" + KClassExtKt.getFullName(kClass) + '\'' + str + " ...");
        long timeInNanoSeconds = KoinPlatformTimeTools.INSTANCE.getTimeInNanoSeconds();
        T resolveInstance = resolveInstance(qualifier, kClass, function0);
        this._koin.getLogger().display(Level.DEBUG, "|- '" + KClassExtKt.getFullName(kClass) + "' in " + (((double) (KoinPlatformTimeTools.INSTANCE.getTimeInNanoSeconds() - timeInNanoSeconds)) / 1000000.0d) + " ms");
        return resolveInstance;
    }

    private final <T> T resolveInstance(Qualifier qualifier, KClass<?> kClass, Function0<? extends ParametersHolder> function0) {
        if (!this._closed) {
            ParametersHolder parametersHolder = function0 != null ? (ParametersHolder) function0.invoke() : null;
            if (parametersHolder != null) {
                Logger logger = this._koin.getLogger();
                Level level = Level.DEBUG;
                if (logger.isAt(level)) {
                    logger.display(level, "| >> parameters " + parametersHolder + ' ');
                }
                KoinPlatformTools.INSTANCE.m2096synchronized(this, new Scope$resolveInstance$2(this, parametersHolder));
            }
            T resolveValue = resolveValue(qualifier, kClass, new InstanceContext(this._koin.getLogger(), this, parametersHolder), function0);
            if (parametersHolder != null) {
                Logger logger2 = this._koin.getLogger();
                Level level2 = Level.DEBUG;
                if (logger2.isAt(level2)) {
                    logger2.display(level2, "| << parameters");
                }
                KoinPlatformTools.INSTANCE.m2096synchronized(this, new Scope$resolveInstance$3(this));
            }
            return resolveValue;
        }
        throw new ClosedScopeException("Scope '" + this.id + "' is closed");
    }

    private final <T> T resolveValue(Qualifier qualifier, KClass<?> kClass, InstanceContext instanceContext, Function0<? extends ParametersHolder> function0) {
        T t;
        T resolveInstance$koin_core = this._koin.getInstanceRegistry().resolveInstance$koin_core(qualifier, kClass, this.scopeQualifier, instanceContext);
        if (resolveInstance$koin_core == null) {
            Scope scope = this;
            Logger logger = this._koin.getLogger();
            String str = "|- ? t:'" + KClassExtKt.getFullName(kClass) + "' - q:'" + qualifier + "' look in injected parameters";
            Level level = Level.DEBUG;
            if (logger.isAt(level)) {
                logger.display(level, str);
            }
            ParametersHolder firstOrNull = this._parameterStack.firstOrNull();
            T t2 = null;
            resolveInstance$koin_core = firstOrNull != null ? firstOrNull.getOrNull(kClass) : null;
            if (resolveInstance$koin_core == null) {
                Logger logger2 = this._koin.getLogger();
                String str2 = "|- ? t:'" + KClassExtKt.getFullName(kClass) + "' - q:'" + qualifier + "' look at scope source";
                Level level2 = Level.DEBUG;
                if (logger2.isAt(level2)) {
                    logger2.display(level2, str2);
                }
                Object obj = this._source;
                if (obj != null && Intrinsics.areEqual((Object) Reflection.getOrCreateKotlinClass(obj.getClass()), (Object) kClass) && qualifier == null && (t = this._source) != null) {
                    t2 = t;
                }
                resolveInstance$koin_core = t2;
                if (resolveInstance$koin_core == null) {
                    Logger logger3 = this._koin.getLogger();
                    String str3 = "|- ? t:'" + KClassExtKt.getFullName(kClass) + "' - q:'" + qualifier + "' look in other scopes";
                    Level level3 = Level.DEBUG;
                    if (logger3.isAt(level3)) {
                        logger3.display(level3, str3);
                    }
                    resolveInstance$koin_core = findInOtherScope(kClass, qualifier, function0);
                    if (resolveInstance$koin_core == null) {
                        KoinPlatformTools.INSTANCE.m2096synchronized(this, new Scope$resolveValue$4$1(this));
                        Logger logger4 = this._koin.getLogger();
                        Level level4 = Level.DEBUG;
                        if (logger4.isAt(level4)) {
                            logger4.display(level4, "|- << parameters");
                        }
                        throwDefinitionNotFound(qualifier, kClass);
                        throw new KotlinNothingValueException();
                    }
                }
            }
        }
        return resolveInstance$koin_core;
    }

    private final <T> T getFromSource(KClass<?> kClass) {
        T t;
        if (!kClass.isInstance(this._source) || (t = this._source) == null) {
            return null;
        }
        return t;
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0007 A[LOOP:0: B:1:0x0007->B:4:0x0017, LOOP_START, PHI: r1 
      PHI: (r1v1 T) = (r1v0 T), (r1v5 T) binds: [B:0:0x0000, B:4:0x0017] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T> T findInOtherScope(kotlin.reflect.KClass<?> r4, org.koin.core.qualifier.Qualifier r5, kotlin.jvm.functions.Function0<? extends org.koin.core.parameter.ParametersHolder> r6) {
        /*
            r3 = this;
            java.util.ArrayList<org.koin.core.scope.Scope> r0 = r3.linkedScopes
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        L_0x0007:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0019
            java.lang.Object r1 = r0.next()
            org.koin.core.scope.Scope r1 = (org.koin.core.scope.Scope) r1
            java.lang.Object r1 = r1.getOrNull(r4, r5, r6)
            if (r1 == 0) goto L_0x0007
        L_0x0019:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.scope.Scope.findInOtherScope(kotlin.reflect.KClass, org.koin.core.qualifier.Qualifier, kotlin.jvm.functions.Function0):java.lang.Object");
    }

    private final Void throwDefinitionNotFound(Qualifier qualifier, KClass<?> kClass) {
        String str;
        if (qualifier == null || (str = " & qualifier:'" + qualifier + '\'') == null) {
            str = "";
        }
        throw new NoBeanDefFoundException("No definition found for class:'" + KClassExtKt.getFullName(kClass) + "' q:'" + str + "'. Check your definitions!");
    }

    public static /* synthetic */ void declare$default(Scope scope, Object obj, Qualifier qualifier, List list, boolean z, int i, Object obj2) {
        if ((i & 2) != 0) {
            qualifier = null;
        }
        Qualifier qualifier2 = qualifier;
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        List list2 = list;
        if ((i & 8) != 0) {
            z = true;
        }
        Intrinsics.checkNotNullParameter(list2, "secondaryTypes");
        KoinPlatformTools koinPlatformTools = KoinPlatformTools.INSTANCE;
        Intrinsics.needClassReification();
        koinPlatformTools.m2096synchronized(scope, new Scope$declare$1(scope, obj, qualifier2, list2, z));
    }

    public final /* synthetic */ <T> void declare(T t, Qualifier qualifier, List<? extends KClass<?>> list, boolean z) {
        Intrinsics.checkNotNullParameter(list, "secondaryTypes");
        KoinPlatformTools koinPlatformTools = KoinPlatformTools.INSTANCE;
        Intrinsics.needClassReification();
        koinPlatformTools.m2096synchronized(this, new Scope$declare$1(this, t, qualifier, list, z));
    }

    public final Koin getKoin() {
        return this._koin;
    }

    public final Scope getScope(String str) {
        Intrinsics.checkNotNullParameter(str, "scopeID");
        return getKoin().getScope(str);
    }

    public final void registerCallback(ScopeCallback scopeCallback) {
        Intrinsics.checkNotNullParameter(scopeCallback, "callback");
        this._callbacks.add(scopeCallback);
    }

    public final /* synthetic */ <T> List<T> getAll() {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return getAll(Reflection.getOrCreateKotlinClass(Object.class));
    }

    public final <T> List<T> getAll(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        Collection all$koin_core = this._koin.getInstanceRegistry().getAll$koin_core(kClass, new InstanceContext(this._koin.getLogger(), this, (ParametersHolder) null, 4, (DefaultConstructorMarker) null));
        Collection arrayList = new ArrayList();
        for (Scope all : this.linkedScopes) {
            CollectionsKt.addAll(arrayList, all.getAll(kClass));
        }
        return CollectionsKt.plus(all$koin_core, (List) arrayList);
    }

    public final <T> T getProperty(String str, T t) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(t, "defaultValue");
        return this._koin.getProperty(str, t);
    }

    public final <T> T getPropertyOrNull(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        return this._koin.getProperty(str);
    }

    public final <T> T getProperty(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        T property = this._koin.getProperty(str);
        if (property != null) {
            return property;
        }
        throw new MissingPropertyException("Property '" + str + "' not found");
    }

    public final void close() {
        KoinPlatformTools.INSTANCE.m2096synchronized(this, new Scope$close$1(this));
    }

    public String toString() {
        return "['" + this.id + "']";
    }
}
