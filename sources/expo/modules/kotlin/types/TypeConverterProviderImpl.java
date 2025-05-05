package expo.modules.kotlin.types;

import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import expo.modules.annotation.Config;
import expo.modules.core.arguments.ReadableArguments;
import expo.modules.kotlin.exception.MissingTypeConverter;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JavaScriptFunction;
import expo.modules.kotlin.jni.JavaScriptObject;
import expo.modules.kotlin.jni.JavaScriptValue;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.records.RecordTypeConverter;
import expo.modules.kotlin.sharedobjects.SharedObject;
import expo.modules.kotlin.sharedobjects.SharedObjectTypeConverter;
import expo.modules.kotlin.typedarray.BigInt64Array;
import expo.modules.kotlin.typedarray.BigUint64Array;
import expo.modules.kotlin.typedarray.Float32Array;
import expo.modules.kotlin.typedarray.Float64Array;
import expo.modules.kotlin.typedarray.Int16Array;
import expo.modules.kotlin.typedarray.Int32Array;
import expo.modules.kotlin.typedarray.Int8Array;
import expo.modules.kotlin.typedarray.TypedArray;
import expo.modules.kotlin.typedarray.Uint16Array;
import expo.modules.kotlin.typedarray.Uint32Array;
import expo.modules.kotlin.typedarray.Uint8Array;
import expo.modules.kotlin.typedarray.Uint8ClampedArray;
import expo.modules.kotlin.types.io.FileTypeConverter;
import expo.modules.kotlin.types.io.PathTypeConverter;
import expo.modules.kotlin.types.net.JavaURITypeConverter;
import expo.modules.kotlin.types.net.URLTypConverter;
import expo.modules.kotlin.types.net.UriTypeConverter;
import expo.modules.kotlin.views.ViewTypeConverter;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\f\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u0010\u000f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\tH\u0002J\"\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\t2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0002J\"\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\u0006\u0010\u0012\u001a\u00020\t2\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0002J\u0014\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0012\u001a\u00020\tH\u0016R\"\u0010\u0003\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0004X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\bX\u0004¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\bX\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lexpo/modules/kotlin/types/TypeConverterProviderImpl;", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "()V", "cachedConverters", "", "Lkotlin/reflect/KClass;", "Lexpo/modules/kotlin/types/TypeConverter;", "cachedCustomConverters", "", "Lkotlin/reflect/KType;", "cachedRecordConverters", "nullableCachedConverters", "createCachedConverters", "isOptional", "", "getCachedConverter", "inputType", "handelCustomConverter", "type", "kClass", "handelEither", "jClass", "Ljava/lang/Class;", "obtainTypeConverter", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TypeConverterProvider.kt */
public final class TypeConverterProviderImpl implements TypeConverterProvider {
    public static final TypeConverterProviderImpl INSTANCE;
    private static final Map<KClass<?>, TypeConverter<?>> cachedConverters;
    private static final Map<KType, TypeConverter<?>> cachedCustomConverters = new LinkedHashMap();
    private static final Map<KClass<?>, TypeConverter<?>> cachedRecordConverters = new LinkedHashMap();
    private static final Map<KClass<?>, TypeConverter<?>> nullableCachedConverters;

    private TypeConverterProviderImpl() {
    }

    static {
        TypeConverterProviderImpl typeConverterProviderImpl = new TypeConverterProviderImpl();
        INSTANCE = typeConverterProviderImpl;
        cachedConverters = typeConverterProviderImpl.createCachedConverters(false);
        nullableCachedConverters = typeConverterProviderImpl.createCachedConverters(true);
    }

    private final TypeConverter<?> getCachedConverter(KType kType) {
        if (kType.isMarkedNullable()) {
            return nullableCachedConverters.get(kType.getClassifier());
        }
        return cachedConverters.get(kType.getClassifier());
    }

    public TypeConverter<?> obtainTypeConverter(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "type");
        TypeConverter<?> cachedConverter = getCachedConverter(kType);
        if (cachedConverter != null) {
            return cachedConverter;
        }
        KClassifier classifier = kType.getClassifier();
        KClass kClass = classifier instanceof KClass ? (KClass) classifier : null;
        if (kClass != null) {
            Class javaClass = JvmClassMappingKt.getJavaClass(kClass);
            if (javaClass.isArray() || Object[].class.isAssignableFrom(javaClass)) {
                return new ArrayTypeConverter(this, kType);
            }
            if (List.class.isAssignableFrom(javaClass)) {
                return new ListTypeConverter(this, kType);
            }
            if (Map.class.isAssignableFrom(javaClass)) {
                return new MapTypeConverter(this, kType);
            }
            if (Pair.class.isAssignableFrom(javaClass)) {
                return new PairTypeConverter(this, kType);
            }
            if (Set.class.isAssignableFrom(javaClass)) {
                return new SetTypeConverter(this, kType);
            }
            if (javaClass.isEnum()) {
                return new EnumTypeConverter(kClass, kType.isMarkedNullable());
            }
            Map<KClass<?>, TypeConverter<?>> map = cachedRecordConverters;
            TypeConverter<?> typeConverter = map.get(kClass);
            if (typeConverter != null) {
                return typeConverter;
            }
            if (Record.class.isAssignableFrom(javaClass)) {
                RecordTypeConverter recordTypeConverter = new RecordTypeConverter(this, kType);
                map.put(kClass, recordTypeConverter);
                return recordTypeConverter;
            } else if (View.class.isAssignableFrom(javaClass)) {
                return new ViewTypeConverter<>(kType);
            } else {
                if (SharedObject.class.isAssignableFrom(javaClass)) {
                    return new SharedObjectTypeConverter<>(kType);
                }
                if (JavaScriptFunction.class.isAssignableFrom(javaClass)) {
                    return new JavaScriptFunctionTypeConverter<>(kType);
                }
                TypeConverter<?> handelEither = handelEither(kType, javaClass);
                if (handelEither != null || (handelEither = handelCustomConverter(kType, kClass)) != null) {
                    return handelEither;
                }
                throw new MissingTypeConverter(kType);
            }
        } else {
            throw new MissingTypeConverter(kType);
        }
    }

    private final TypeConverter<?> handelEither(KType kType, Class<?> cls) {
        if (!Either.class.isAssignableFrom(cls)) {
            return null;
        }
        if (EitherOfFour.class.isAssignableFrom(cls)) {
            return new EitherOfFourTypeConverter<>(this, kType);
        }
        if (EitherOfThree.class.isAssignableFrom(cls)) {
            return new EitherOfThreeTypeConverter<>(this, kType);
        }
        return new EitherTypeConverter<>(this, kType);
    }

    private final TypeConverter<?> handelCustomConverter(KType kType, KClass<?> kClass) {
        Map<KType, TypeConverter<?>> map = cachedCustomConverters;
        TypeConverter<?> typeConverter = map.get(kType);
        if (typeConverter != null) {
            return typeConverter;
        }
        String canonicalName = JvmClassMappingKt.getJavaClass(kClass).getCanonicalName();
        if (canonicalName == null) {
            return null;
        }
        try {
            Object newInstance = Class.forName(Config.packageNamePrefix + canonicalName + Config.classNameSuffix).newInstance();
            Object invoke = newInstance.getClass().getMethod(Config.converterProviderFunctionName, new Class[]{KType.class}).invoke(newInstance, new Object[]{kType});
            Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type expo.modules.kotlin.types.TypeConverter<*>");
            TypeConverter<?> typeConverter2 = (TypeConverter) invoke;
            map.put(kType, typeConverter2);
            return typeConverter2;
        } catch (Throwable unused) {
            return null;
        }
    }

    private final Map<KClass<?>, TypeConverter<?>> createCachedConverters(boolean z) {
        TypeConverter typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$1 = new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$1(z, new ExpectedType(CppType.INT));
        TypeConverter typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$2 = new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$2(z, new ExpectedType(CppType.LONG));
        TypeConverter typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$3 = new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$3(z, new ExpectedType(CppType.DOUBLE));
        TypeConverter typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$4 = new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$4(z, new ExpectedType(CppType.FLOAT));
        TypeConverter typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$5 = new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$5(z, new ExpectedType(CppType.BOOLEAN));
        return MapsKt.plus(MapsKt.mapOf(TuplesKt.to(Reflection.getOrCreateKotlinClass(Integer.TYPE), typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$1), TuplesKt.to(Reflection.getOrCreateKotlinClass(Integer.class), typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$1), TuplesKt.to(Reflection.getOrCreateKotlinClass(Long.TYPE), typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$2), TuplesKt.to(Reflection.getOrCreateKotlinClass(Long.class), typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$2), TuplesKt.to(Reflection.getOrCreateKotlinClass(Double.TYPE), typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$3), TuplesKt.to(Reflection.getOrCreateKotlinClass(Double.class), typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$3), TuplesKt.to(Reflection.getOrCreateKotlinClass(Float.TYPE), typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$4), TuplesKt.to(Reflection.getOrCreateKotlinClass(Float.class), typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$4), TuplesKt.to(Reflection.getOrCreateKotlinClass(Boolean.TYPE), typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$5), TuplesKt.to(Reflection.getOrCreateKotlinClass(Boolean.class), typeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$5), TuplesKt.to(Reflection.getOrCreateKotlinClass(String.class), new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$6(z, new ExpectedType(CppType.STRING))), TuplesKt.to(Reflection.getOrCreateKotlinClass(ReadableArray.class), new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$7(z, new ExpectedType(CppType.READABLE_ARRAY))), TuplesKt.to(Reflection.getOrCreateKotlinClass(ReadableMap.class), new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$8(z, new ExpectedType(CppType.READABLE_MAP))), TuplesKt.to(Reflection.getOrCreateKotlinClass(int[].class), new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$9(z, ExpectedType.Companion.forPrimitiveArray(CppType.INT))), TuplesKt.to(Reflection.getOrCreateKotlinClass(double[].class), new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$10(z, ExpectedType.Companion.forPrimitiveArray(CppType.DOUBLE))), TuplesKt.to(Reflection.getOrCreateKotlinClass(float[].class), new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$11(z, ExpectedType.Companion.forPrimitiveArray(CppType.FLOAT))), TuplesKt.to(Reflection.getOrCreateKotlinClass(boolean[].class), new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$12(z, ExpectedType.Companion.forPrimitiveArray(CppType.BOOLEAN))), TuplesKt.to(Reflection.getOrCreateKotlinClass(byte[].class), new ByteArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(JavaScriptValue.class), new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$default$1(z, new ExpectedType(CppType.JS_VALUE))), TuplesKt.to(Reflection.getOrCreateKotlinClass(JavaScriptObject.class), new TypeConverterProviderImpl$createCachedConverters$$inlined$createTrivialTypeConverter$default$2(z, new ExpectedType(CppType.JS_OBJECT))), TuplesKt.to(Reflection.getOrCreateKotlinClass(Int8Array.class), new Int8ArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Int16Array.class), new Int16ArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Int32Array.class), new Int32ArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Uint8Array.class), new Uint8ArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Uint8ClampedArray.class), new Uint8ClampedArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Uint16Array.class), new Uint16ArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Uint32Array.class), new Uint32ArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Float32Array.class), new Float32ArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Float64Array.class), new Float64ArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(BigInt64Array.class), new BigInt64ArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(BigUint64Array.class), new BigUint64ArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(TypedArray.class), new TypedArrayTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(URL.class), new URLTypConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Uri.class), new UriTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(URI.class), new JavaURITypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(File.class), new FileTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Object.class), new AnyTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Unit.class), new UnitTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(ReadableArguments.class), new ReadableArgumentsTypeConverter(z))), MapsKt.mapOf(TuplesKt.to(Reflection.getOrCreateKotlinClass(Path.class), new PathTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(Color.class), new ColorTypeConverter(z)), TuplesKt.to(Reflection.getOrCreateKotlinClass(LocalDate.class), new DateTypeConverter(z))));
    }
}
