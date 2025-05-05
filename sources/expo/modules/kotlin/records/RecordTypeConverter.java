package expo.modules.kotlin.records;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.allocators.ObjectConstructor;
import expo.modules.kotlin.allocators.ObjectConstructorFactory;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.FieldRequiredException;
import expo.modules.kotlin.exception.RecordCastException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.CppType;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.records.Record;
import expo.modules.kotlin.types.DynamicAwareTypeConverters;
import expo.modules.kotlin.types.TypeConverter;
import expo.modules.kotlin.types.TypeConverterProvider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001,B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u0018J\u0015\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u001aH\u0016¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001c\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020!H\u0016J\"\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00010#\"\u0004\b\u0001\u0010\u00012\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00010%H\u0002J&\u0010&\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030(0'2\u0012\u0010)\u001a\u000e\u0012\u0006\b\u0001\u0012\u00020\u000e\u0012\u0002\b\u00030\rH\u0002J\b\u0010*\u001a\u00020+H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R3\u0010\u000b\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u000e\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u000f0\f8BX\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006-"}, d2 = {"Lexpo/modules/kotlin/records/RecordTypeConverter;", "T", "Lexpo/modules/kotlin/records/Record;", "Lexpo/modules/kotlin/types/DynamicAwareTypeConverters;", "converterProvider", "Lexpo/modules/kotlin/types/TypeConverterProvider;", "type", "Lkotlin/reflect/KType;", "(Lexpo/modules/kotlin/types/TypeConverterProvider;Lkotlin/reflect/KType;)V", "objectConstructorFactory", "Lexpo/modules/kotlin/allocators/ObjectConstructorFactory;", "propertyDescriptors", "", "Lkotlin/reflect/KProperty1;", "", "Lexpo/modules/kotlin/records/RecordTypeConverter$PropertyDescriptor;", "getPropertyDescriptors", "()Ljava/util/Map;", "propertyDescriptors$delegate", "Lkotlin/Lazy;", "getType", "()Lkotlin/reflect/KType;", "convertFromAny", "value", "(Ljava/lang/Object;)Lexpo/modules/kotlin/records/Record;", "convertFromDynamic", "Lcom/facebook/react/bridge/Dynamic;", "(Lcom/facebook/react/bridge/Dynamic;)Lexpo/modules/kotlin/records/Record;", "convertFromReadableMap", "jsMap", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/react/bridge/ReadableMap;)Lexpo/modules/kotlin/records/Record;", "getCppRequiredTypes", "Lexpo/modules/kotlin/jni/ExpectedType;", "getObjectConstructor", "Lexpo/modules/kotlin/allocators/ObjectConstructor;", "clazz", "Ljava/lang/Class;", "getValidators", "", "Lexpo/modules/kotlin/records/FieldValidator;", "property", "isTrivial", "", "PropertyDescriptor", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RecordTypeConverter.kt */
public final class RecordTypeConverter<T extends Record> extends DynamicAwareTypeConverters<T> {
    /* access modifiers changed from: private */
    public final TypeConverterProvider converterProvider;
    private final ObjectConstructorFactory objectConstructorFactory = new ObjectConstructorFactory();
    private final Lazy propertyDescriptors$delegate = LazyKt.lazy(new RecordTypeConverter$propertyDescriptors$2(this));
    private final KType type;

    public boolean isTrivial() {
        return false;
    }

    public final KType getType() {
        return this.type;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RecordTypeConverter(TypeConverterProvider typeConverterProvider, KType kType) {
        super(kType.isMarkedNullable());
        Intrinsics.checkNotNullParameter(typeConverterProvider, "converterProvider");
        Intrinsics.checkNotNullParameter(kType, "type");
        this.converterProvider = typeConverterProvider;
        this.type = kType;
    }

    private final Map<KProperty1<? extends Object, ?>, PropertyDescriptor> getPropertyDescriptors() {
        return (Map) this.propertyDescriptors$delegate.getValue();
    }

    public T convertFromDynamic(Dynamic dynamic) {
        CodedException codedException;
        Intrinsics.checkNotNullParameter(dynamic, "value");
        try {
            ReadableMap asMap = dynamic.asMap();
            Intrinsics.checkNotNull(asMap);
            return convertFromReadableMap(asMap);
        } catch (Throwable th) {
            if (th instanceof CodedException) {
                codedException = th;
            } else if (th instanceof expo.modules.core.errors.CodedException) {
                String code = th.getCode();
                Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                codedException = new CodedException(code, th.getMessage(), th.getCause());
            } else {
                codedException = new UnexpectedException((Throwable) th);
            }
            throw new RecordCastException(this.type, codedException);
        }
    }

    public T convertFromAny(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "value");
        if (obj instanceof ReadableMap) {
            return convertFromReadableMap((ReadableMap) obj);
        }
        return (Record) obj;
    }

    public ExpectedType getCppRequiredTypes() {
        return new ExpectedType(CppType.READABLE_MAP);
    }

    private final T convertFromReadableMap(ReadableMap readableMap) {
        CodedException codedException;
        KClassifier classifier = this.type.getClassifier();
        Intrinsics.checkNotNull(classifier, "null cannot be cast to non-null type kotlin.reflect.KClass<*>");
        T construct = getObjectConstructor(JvmClassMappingKt.getJavaClass((KClass) classifier)).construct();
        for (Map.Entry next : getPropertyDescriptors().entrySet()) {
            KProperty1 kProperty1 = (KProperty1) next.getKey();
            PropertyDescriptor propertyDescriptor = (PropertyDescriptor) next.getValue();
            String key = propertyDescriptor.getFieldAnnotation().key();
            if (StringsKt.isBlank(key)) {
                key = null;
            }
            if (key == null) {
                key = kProperty1.getName();
            }
            if (readableMap.hasKey(key)) {
                Dynamic dynamic = readableMap.getDynamic(key);
                Intrinsics.checkNotNullExpressionValue(dynamic, "getDynamic(...)");
                try {
                    Field javaField = ReflectJvmMapping.getJavaField(kProperty1);
                    Intrinsics.checkNotNull(javaField);
                    Object convert$default = TypeConverter.convert$default(propertyDescriptor.getTypeConverter(), dynamic, (AppContext) null, 2, (Object) null);
                    if (convert$default != null) {
                        for (FieldValidator fieldValidator : propertyDescriptor.getValidators()) {
                            Intrinsics.checkNotNull(fieldValidator, "null cannot be cast to non-null type expo.modules.kotlin.records.FieldValidator<kotlin.Any>");
                            fieldValidator.validate(convert$default);
                        }
                    }
                    javaField.setAccessible(true);
                    javaField.set(construct, convert$default);
                    Unit unit = Unit.INSTANCE;
                    dynamic.recycle();
                } catch (Throwable th) {
                    dynamic.recycle();
                    throw th;
                }
            } else if (propertyDescriptor.isRequired()) {
                throw new FieldRequiredException(kProperty1);
            }
        }
        Intrinsics.checkNotNull(construct, "null cannot be cast to non-null type T of expo.modules.kotlin.records.RecordTypeConverter");
        return (Record) construct;
    }

    private final <T> ObjectConstructor<T> getObjectConstructor(Class<T> cls) {
        return this.objectConstructorFactory.get(cls);
    }

    /* access modifiers changed from: private */
    public final List<FieldValidator<?>> getValidators(KProperty1<? extends Object, ?> kProperty1) {
        Pair pair;
        Object obj;
        Iterable<Annotation> annotations = kProperty1.getAnnotations();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(annotations, 10));
        for (Annotation annotation : annotations) {
            Iterator it = JvmClassMappingKt.getAnnotationClass(annotation).getAnnotations().iterator();
            while (true) {
                pair = null;
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (((Annotation) obj) instanceof BindUsing) {
                    break;
                }
            }
            BindUsing bindUsing = (BindUsing) obj;
            if (bindUsing != null) {
                pair = TuplesKt.to(annotation, bindUsing);
            }
            arrayList.add(pair);
        }
        Iterable<Pair> filterNotNull = CollectionsKt.filterNotNull((List) arrayList);
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(filterNotNull, 10));
        for (Pair pair2 : filterNotNull) {
            Object createInstance = KClasses.createInstance(Reflection.getOrCreateKotlinClass(((BindUsing) pair2.component2()).binder()));
            Intrinsics.checkNotNull(createInstance, "null cannot be cast to non-null type expo.modules.kotlin.records.ValidationBinder");
            arrayList2.add(((ValidationBinder) createInstance).bind((Annotation) pair2.component1(), kProperty1.getReturnType()));
        }
        return (List) arrayList2;
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B3\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t¢\u0006\u0002\u0010\u000bJ\r\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u0013\u0010\u0016\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÆ\u0003J?\u0010\u0017\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0012\b\u0002\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\tHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lexpo/modules/kotlin/records/RecordTypeConverter$PropertyDescriptor;", "", "typeConverter", "Lexpo/modules/kotlin/types/TypeConverter;", "fieldAnnotation", "Lexpo/modules/kotlin/records/Field;", "isRequired", "", "validators", "", "Lexpo/modules/kotlin/records/FieldValidator;", "(Lexpo/modules/kotlin/types/TypeConverter;Lexpo/modules/kotlin/records/Field;ZLjava/util/List;)V", "getFieldAnnotation", "()Lexpo/modules/kotlin/records/Field;", "()Z", "getTypeConverter", "()Lexpo/modules/kotlin/types/TypeConverter;", "getValidators", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RecordTypeConverter.kt */
    private static final class PropertyDescriptor {
        private final Field fieldAnnotation;
        private final boolean isRequired;
        private final TypeConverter<?> typeConverter;
        private final List<FieldValidator<?>> validators;

        public static /* synthetic */ PropertyDescriptor copy$default(PropertyDescriptor propertyDescriptor, TypeConverter<?> typeConverter2, Field field, boolean z, List<FieldValidator<?>> list, int i, Object obj) {
            if ((i & 1) != 0) {
                typeConverter2 = propertyDescriptor.typeConverter;
            }
            if ((i & 2) != 0) {
                field = propertyDescriptor.fieldAnnotation;
            }
            if ((i & 4) != 0) {
                z = propertyDescriptor.isRequired;
            }
            if ((i & 8) != 0) {
                list = propertyDescriptor.validators;
            }
            return propertyDescriptor.copy(typeConverter2, field, z, list);
        }

        public final TypeConverter<?> component1() {
            return this.typeConverter;
        }

        public final Field component2() {
            return this.fieldAnnotation;
        }

        public final boolean component3() {
            return this.isRequired;
        }

        public final List<FieldValidator<?>> component4() {
            return this.validators;
        }

        public final PropertyDescriptor copy(TypeConverter<?> typeConverter2, Field field, boolean z, List<? extends FieldValidator<?>> list) {
            Intrinsics.checkNotNullParameter(typeConverter2, "typeConverter");
            Intrinsics.checkNotNullParameter(field, "fieldAnnotation");
            Intrinsics.checkNotNullParameter(list, "validators");
            return new PropertyDescriptor(typeConverter2, field, z, list);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PropertyDescriptor)) {
                return false;
            }
            PropertyDescriptor propertyDescriptor = (PropertyDescriptor) obj;
            return Intrinsics.areEqual((Object) this.typeConverter, (Object) propertyDescriptor.typeConverter) && Intrinsics.areEqual((Object) this.fieldAnnotation, (Object) propertyDescriptor.fieldAnnotation) && this.isRequired == propertyDescriptor.isRequired && Intrinsics.areEqual((Object) this.validators, (Object) propertyDescriptor.validators);
        }

        public int hashCode() {
            return (((((this.typeConverter.hashCode() * 31) + this.fieldAnnotation.hashCode()) * 31) + Boolean.hashCode(this.isRequired)) * 31) + this.validators.hashCode();
        }

        public String toString() {
            TypeConverter<?> typeConverter2 = this.typeConverter;
            Field field = this.fieldAnnotation;
            boolean z = this.isRequired;
            return "PropertyDescriptor(typeConverter=" + typeConverter2 + ", fieldAnnotation=" + field + ", isRequired=" + z + ", validators=" + this.validators + ")";
        }

        public PropertyDescriptor(TypeConverter<?> typeConverter2, Field field, boolean z, List<? extends FieldValidator<?>> list) {
            Intrinsics.checkNotNullParameter(typeConverter2, "typeConverter");
            Intrinsics.checkNotNullParameter(field, "fieldAnnotation");
            Intrinsics.checkNotNullParameter(list, "validators");
            this.typeConverter = typeConverter2;
            this.fieldAnnotation = field;
            this.isRequired = z;
            this.validators = list;
        }

        public final TypeConverter<?> getTypeConverter() {
            return this.typeConverter;
        }

        public final Field getFieldAnnotation() {
            return this.fieldAnnotation;
        }

        public final boolean isRequired() {
            return this.isRequired;
        }

        public final List<FieldValidator<?>> getValidators() {
            return this.validators;
        }
    }
}
