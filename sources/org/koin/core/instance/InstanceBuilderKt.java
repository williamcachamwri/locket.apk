package org.koin.core.instance;

import androidx.exifinterface.media.ExifInterface;
import io.sentry.protocol.Message;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.koin.core.annotation.KoinReflectAPI;
import org.koin.core.error.NoBeanDefFoundException;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.parameter.ParametersHolderKt;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.ext.KClassExtKt;
import org.koin.mp.KoinPlatformTimeTools;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a+\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0005H\u0002¢\u0006\u0002\u0010\u0006\u001a/\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\f\u001a1\u0010\r\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u0001*\u00020\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00102\u0006\u0010\u0011\u001a\u00020\u000bH\u0007¢\u0006\u0002\u0010\u0012\u001a(\u0010\r\u001a\u0002H\u000e\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\u0001*\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u000bH\b¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"createInstance", "", "args", "", "constructor", "Ljava/lang/reflect/Constructor;", "([Ljava/lang/Object;Ljava/lang/reflect/Constructor;)Ljava/lang/Object;", "getArguments", "scope", "Lorg/koin/core/scope/Scope;", "parameters", "Lorg/koin/core/parameter/ParametersHolder;", "(Ljava/lang/reflect/Constructor;Lorg/koin/core/scope/Scope;Lorg/koin/core/parameter/ParametersHolder;)[Ljava/lang/Object;", "newInstance", "T", "kClass", "Lkotlin/reflect/KClass;", "params", "(Lorg/koin/core/scope/Scope;Lkotlin/reflect/KClass;Lorg/koin/core/parameter/ParametersHolder;)Ljava/lang/Object;", "defParams", "(Lorg/koin/core/scope/Scope;Lorg/koin/core/parameter/ParametersHolder;)Ljava/lang/Object;", "koin-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: InstanceBuilder.kt */
public final class InstanceBuilderKt {
    public static /* synthetic */ Object newInstance$default(Scope scope, ParametersHolder parametersHolder, int i, Object obj) {
        if ((i & 1) != 0) {
            parametersHolder = ParametersHolderKt.emptyParametersHolder();
        }
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(parametersHolder, "defParams");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return newInstance(scope, Reflection.getOrCreateKotlinClass(Object.class), parametersHolder);
    }

    @Deprecated(message = "Koin Reflection API is deprecated")
    @KoinReflectAPI
    public static final /* synthetic */ <T> T newInstance(Scope scope, ParametersHolder parametersHolder) {
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(parametersHolder, "defParams");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return newInstance(scope, Reflection.getOrCreateKotlinClass(Object.class), parametersHolder);
    }

    @Deprecated(message = "Koin Reflection API is deprecated")
    @KoinReflectAPI
    public static final <T> T newInstance(Scope scope, KClass<T> kClass, ParametersHolder parametersHolder) {
        Object[] objArr;
        T t;
        Intrinsics.checkNotNullParameter(scope, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(parametersHolder, Message.JsonKeys.PARAMS);
        if (scope.getLogger().getLevel() == Level.DEBUG) {
            Logger logger = scope.getLogger();
            String str = "|- creating new instance - " + KClassExtKt.getFullName(kClass);
            Level level = Level.DEBUG;
            if (logger.isAt(level)) {
                logger.display(level, str);
            }
        }
        Constructor[] constructors = JvmClassMappingKt.getJavaClass(kClass).getConstructors();
        Intrinsics.checkNotNullExpressionValue(constructors, "kClass.java.constructors");
        Constructor constructor = (Constructor) ArraysKt.firstOrNull((T[]) (Object[]) constructors);
        if (constructor != null) {
            if (scope.getLogger().getLevel() == Level.DEBUG) {
                Pair pair = new Pair(getArguments(constructor, scope, parametersHolder), Double.valueOf(((double) (KoinPlatformTimeTools.INSTANCE.getTimeInNanoSeconds() - KoinPlatformTimeTools.INSTANCE.getTimeInNanoSeconds())) / 1000000.0d));
                Pair pair2 = new Pair(pair.component1(), Double.valueOf(((Number) pair.component2()).doubleValue()));
                objArr = (Object[]) pair2.component1();
                double doubleValue = ((Number) pair2.component2()).doubleValue();
                Logger logger2 = scope.getLogger();
                String str2 = "|- got arguments in " + doubleValue + " ms";
                Level level2 = Level.DEBUG;
                if (logger2.isAt(level2)) {
                    logger2.display(level2, str2);
                }
            } else {
                objArr = getArguments(constructor, scope, parametersHolder);
            }
            if (scope.getLogger().getLevel() == Level.DEBUG) {
                Pair pair3 = new Pair(createInstance(objArr, constructor), Double.valueOf(((double) (KoinPlatformTimeTools.INSTANCE.getTimeInNanoSeconds() - KoinPlatformTimeTools.INSTANCE.getTimeInNanoSeconds())) / 1000000.0d));
                Pair pair4 = new Pair(pair3.component1(), Double.valueOf(((Number) pair3.component2()).doubleValue()));
                t = pair4.component1();
                double doubleValue2 = ((Number) pair4.component2()).doubleValue();
                Logger logger3 = scope.getLogger();
                String str3 = "|- created instance in " + doubleValue2 + " ms";
                Level level3 = Level.DEBUG;
                if (logger3.isAt(level3)) {
                    logger3.display(level3, str3);
                }
            } else {
                t = createInstance(objArr, constructor);
            }
            Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of org.koin.core.instance.InstanceBuilderKt.newInstance");
            return t;
        }
        throw new IllegalStateException(("No constructor found for class '" + KClassExtKt.getFullName(kClass) + '\'').toString());
    }

    private static final Object createInstance(Object[] objArr, Constructor<? extends Object> constructor) {
        Object obj;
        if (objArr.length == 0) {
            obj = constructor.newInstance(new Object[0]);
        } else {
            obj = constructor.newInstance(Arrays.copyOf(objArr, objArr.length));
        }
        Intrinsics.checkNotNullExpressionValue(obj, "if (args.isEmpty()) {\n  ….newInstance(*args)\n    }");
        return obj;
    }

    private static final Object[] getArguments(Constructor<?> constructor, Scope scope, ParametersHolder parametersHolder) {
        int length = constructor.getParameterTypes().length;
        if (length == 0) {
            return new Object[0];
        }
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = Unit.INSTANCE;
        }
        for (int i2 = 0; i2 < length; i2++) {
            Class cls = constructor.getParameterTypes()[i2];
            Intrinsics.checkNotNullExpressionValue(cls, "p");
            KClass kotlinClass = JvmClassMappingKt.getKotlinClass(cls);
            Object orNull = scope.getOrNull(kotlinClass, (Qualifier) null, new InstanceBuilderKt$getArguments$1(parametersHolder));
            if (orNull == null && (orNull = parametersHolder.getOrNull(kotlinClass)) == null) {
                throw new NoBeanDefFoundException("No definition found for class '" + kotlinClass + '\'');
            }
            objArr[i2] = orNull;
        }
        return objArr;
    }
}
