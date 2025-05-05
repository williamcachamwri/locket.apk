package expo.modules.kotlin.records;

import expo.modules.kotlin.records.RecordTypeConverter;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty1;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0003\u0012\u0002\b\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "Lkotlin/reflect/KProperty1;", "", "Lexpo/modules/kotlin/records/RecordTypeConverter$PropertyDescriptor;", "T", "Lexpo/modules/kotlin/records/Record;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RecordTypeConverter.kt */
final class RecordTypeConverter$propertyDescriptors$2 extends Lambda implements Function0<Map<KProperty1<? extends Object, ?>, ? extends RecordTypeConverter.PropertyDescriptor>> {
    final /* synthetic */ RecordTypeConverter<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RecordTypeConverter$propertyDescriptors$2(RecordTypeConverter<T> recordTypeConverter) {
        super(0);
        this.this$0 = recordTypeConverter;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: kotlin.Pair} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: kotlin.Pair} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: kotlin.Pair} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: kotlin.Pair} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: java.lang.annotation.Annotation} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: kotlin.Pair} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<kotlin.reflect.KProperty1<? extends java.lang.Object, ?>, expo.modules.kotlin.records.RecordTypeConverter.PropertyDescriptor> invoke() {
        /*
            r10 = this;
            expo.modules.kotlin.records.RecordTypeConverter<T> r0 = r10.this$0
            kotlin.reflect.KType r0 = r0.getType()
            kotlin.reflect.KClassifier r0 = r0.getClassifier()
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.reflect.KClass<*>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            kotlin.reflect.KClass r0 = (kotlin.reflect.KClass) r0
            java.util.Collection r0 = kotlin.reflect.full.KClasses.getMemberProperties(r0)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            expo.modules.kotlin.records.RecordTypeConverter<T> r1 = r10.this$0
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r3)
            r2.<init>(r3)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r0 = r0.iterator()
        L_0x002a:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x00a2
            java.lang.Object r3 = r0.next()
            kotlin.reflect.KProperty1 r3 = (kotlin.reflect.KProperty1) r3
            r4 = r3
            kotlin.reflect.KAnnotatedElement r4 = (kotlin.reflect.KAnnotatedElement) r4
            java.util.List r5 = r4.getAnnotations()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x0043:
            boolean r6 = r5.hasNext()
            r7 = 0
            if (r6 == 0) goto L_0x0056
            java.lang.Object r6 = r5.next()
            r8 = r6
            java.lang.annotation.Annotation r8 = (java.lang.annotation.Annotation) r8
            boolean r8 = r8 instanceof expo.modules.kotlin.records.Field
            if (r8 == 0) goto L_0x0043
            goto L_0x0057
        L_0x0056:
            r6 = r7
        L_0x0057:
            expo.modules.kotlin.records.Field r6 = (expo.modules.kotlin.records.Field) r6
            java.lang.annotation.Annotation r6 = (java.lang.annotation.Annotation) r6
            expo.modules.kotlin.records.Field r6 = (expo.modules.kotlin.records.Field) r6
            if (r6 != 0) goto L_0x0060
            goto L_0x009e
        L_0x0060:
            expo.modules.kotlin.types.TypeConverterProvider r5 = r1.converterProvider
            kotlin.reflect.KType r8 = r3.getReturnType()
            expo.modules.kotlin.types.TypeConverter r5 = r5.obtainTypeConverter(r8)
            java.util.List r4 = r4.getAnnotations()
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
        L_0x0076:
            boolean r8 = r4.hasNext()
            if (r8 == 0) goto L_0x0088
            java.lang.Object r8 = r4.next()
            r9 = r8
            java.lang.annotation.Annotation r9 = (java.lang.annotation.Annotation) r9
            boolean r9 = r9 instanceof expo.modules.kotlin.records.Required
            if (r9 == 0) goto L_0x0076
            r7 = r8
        L_0x0088:
            expo.modules.kotlin.records.Required r7 = (expo.modules.kotlin.records.Required) r7
            java.lang.annotation.Annotation r7 = (java.lang.annotation.Annotation) r7
            if (r7 == 0) goto L_0x0090
            r4 = 1
            goto L_0x0091
        L_0x0090:
            r4 = 0
        L_0x0091:
            java.util.List r7 = r1.getValidators(r3)
            expo.modules.kotlin.records.RecordTypeConverter$PropertyDescriptor r8 = new expo.modules.kotlin.records.RecordTypeConverter$PropertyDescriptor
            r8.<init>(r5, r6, r4, r7)
            kotlin.Pair r7 = kotlin.TuplesKt.to(r3, r8)
        L_0x009e:
            r2.add(r7)
            goto L_0x002a
        L_0x00a2:
            java.util.List r2 = (java.util.List) r2
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.List r0 = kotlin.collections.CollectionsKt.filterNotNull(r2)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Map r0 = kotlin.collections.MapsKt.toMap(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.records.RecordTypeConverter$propertyDescriptors$2.invoke():java.util.Map");
    }
}
